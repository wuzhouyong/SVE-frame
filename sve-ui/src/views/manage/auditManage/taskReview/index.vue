<template>
  <div class="task-wk">
    <!-- 搜索表单 -->
    <el-form :model="queryForm" ref="queryForm" :inline="true" class="guocheng-serchForm">
      <el-form-item label="企业名称" prop="fullName">
        <el-input
          v-model="queryForm.fullName"
          placeholder
          clearable
          size="mini"
          style="width: 180px"
        />
      </el-form-item>
      <el-form-item label="监控任务" prop="tagHeadId">
        <el-select
          v-model="queryForm.tagHeadId"
          placeholder="请选择监控任务"
          clearable
          :style="{width: '160px'}"
        >
          <el-option v-for="i in cities" :key="i.id" :label="i.name" :value="i.id"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="所属镇街" prop="townCode">
        <el-select
          v-model="queryForm.townCode"
          placeholder="请选择所属镇街"
          clearable
          :style="{width: '160px'}"
        >
          <el-option
            v-for="i in townCodeOptions"
            :key="i.regionCode"
            :label="i.regionName"
            :value="i.regionCode"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="运行状态" prop="sourceStatus">
        <el-select
          v-model="queryForm.sourceStatus"
          placeholder="请选择运行状态"
          clearable
          :style="{width: '160px'}"
        >
          <el-option
            v-for="i in sourceStatusOptions"
            :key="i.dictValue"
            :label="i.dictLabel"
            :value="i.dictValue"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <!-- 表格 -->
    <el-table
      v-loading="loading"
      :data="tableData"
      style="width: 100%;flex:1"
      ref="oTable"
      height="100%"
      border
    >
      <el-table-column prop="tagHeadName" label="监控任务" width="150"></el-table-column>
      <el-table-column prop="fullName" label="企业名称" min-width="300"></el-table-column>
      <el-table-column prop="townName" label="所属镇街" width="90"></el-table-column>
      <el-table-column prop="envContactName" label="联系人" width="90"></el-table-column>
      <el-table-column prop="envContactPhone" label="联系电话" width="120"></el-table-column>
      <el-table-column prop="sourceStatus" label="运行状态" width="100"></el-table-column>
      <el-table-column prop="status" label="任务进度" width="120"></el-table-column>
      <el-table-column prop="constructionUnit" label="施工单位名称" width="180"></el-table-column>
      <el-table-column prop="constructionPerson" label="施工单位联系人" width="120"></el-table-column>
      <el-table-column prop="constructionPhone" label="施工单位联系电话" width="160"></el-table-column>
      <el-table-column align="center" label="操作" width="120">
        <template slot-scope="scope">
          <el-button
            type="text"
            size="small"
            v-if="scope.row.taskAdjustId"
            @click="applyTask(scope.row)"
          >{{ (scope.row.tagHeadId === "1003" && scope.row.waitReview === 1) ? "待复核" : "任务调整审核" }}
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <pagination
      :total="total"
      :page.sync="page.pageNum"
      :limit.sync="page.pageSize"
      @pagination="getList(queryForm)"
    />
    <!-- 任务调整审核 -->
    <el-dialog
      :title="`任务调整审核( ${souceTitle} )`"
      :visible.sync="taskVisible"
      width="40%"
      :destroy-on-close="true"
    >
      <examineChunk :applyData="applyData" @successExam="successExam" @closeon="taskVisible=false"
                    :isLingXing="isLingXing"></examineChunk>
    </el-dialog>
  </div>
</template>
<script>
import {analysis} from "@/config";
import {monitorTaskReview, monitorAudit} from "@/api/composite/monitor";
import {getTagList, GetRegionPower} from "@/api/composite/source";
import examineChunk from "./examine";
import {mapState} from "vuex";

export default {
  components: {examineChunk},
  data() {
    return {
      queryForm: {
        tagHeadIds: null,
        townCode: null,
        fullName: null,
        sourceStatus: null
      },
      page: {
        pageNum: 1,
        pageSize: 30
      },
      tableData: [],
      total: 0,
      cities: [],
      loading: false,
      townCodeOptions: [],
      sourceStatusOptions: [],
      visible: false,
      platFormPath: "",
      oId: "",
      refType: "",
      platArr: [],
      taskVisible: false,
      applyData: {},
      souceTitle: "",
      isLingXing: null
    };
  },
  created() {
    GetRegionPower().then((res) => (this.townCodeOptions = res.data));
    this.getDicts("source_status").then((res) => {
      this.sourceStatusOptions = res.data;
    });
  },
  computed: {
    ...mapState("label", ["searchData"])
  },
  watch: {
    searchData: {
      handler() {
        this.getSourceTag();
      },
      immediate: true
    },
  },
  methods: {
    getList(v = {}) {
      let oVal = Object.assign({labelIds: this.searchData}, v);
      monitorTaskReview(oVal, this.urlParameter(this.page)).then((res) => {
        if (res.code === 200) {
          this.tableData = res.rows;
          this.total = res.total;
        }
      });
    },
    // 监控任务
    getSourceTag() {
      getTagList().then((res) => {
        if (res.code === 200) {
          let oArr = res.rows;
          this.cities = analysis.filter((p) =>
            oArr.some((s) => s.tagHeadId === p.id)
          );

          let checkArr = [];
          this.cities.forEach((v, i, a) => {
            checkArr.push(v.id);
          });
          this.platArr = checkArr;
          this.queryForm.tagHeadIds = this.platArr;
          this.getList(this.queryForm);
        }
      });
    },
    handleQuery() {
      this.page.pageNum = 1;
      if (this.queryForm.tagHeadId) {
        this.queryForm.tagHeadIds = [this.queryForm.tagHeadId];
      } else {
        this.queryForm.tagHeadIds = this.platArr;
      }
      this.getList(this.queryForm);
    },
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    applyTask(v) {
      this.isLingXing = v.tagHeadId === "1003" ? true : false;
      this.applyData = v;
      this.souceTitle = v.fullName;
      this.taskVisible = true;
    },
    successExam() {
      this.isLingXing = false;
      this.taskVisible = false;
      this.getList(this.queryForm);
    }
  }
};
</script>
