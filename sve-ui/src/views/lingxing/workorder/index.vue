<template>
  <div class="task-wk">
    <!-- 搜索表单 -->
    <el-form :model="queryForm" ref="queryForm" :inline="true" class="outwater-serchForm">
      <el-form-item label="处置企业" prop="procSourceid">
        <el-select
          v-model="queryForm.procSourceid"
          placeholder="请选择处置企业"
          style="width:250px"
          size="mini"
        >
          <el-option
            :label="i.fullName"
            :value="i.sourceId"
            v-for="i in sourceList"
            :key="i.sourceId"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="工作单号" prop="jobHeadid">
        <el-input v-model="queryForm.jobHeadid" placeholder="请输入工作单号"></el-input>
      </el-form-item>
      <el-form-item label="状态" prop="jobStatus">
        <el-select
          v-model="queryForm.jobStatus"
          placeholder="请选择状态"
          clearable
          :style="{width: '160px'}"
        >
          <el-option
            v-for="i in joinListType"
            :key="i.dictValue"
            :label="i.dictLabel"
            :value="i.dictValue"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="时间" prop="oTime">
        <el-date-picker
          v-model="queryForm.oTime"
          :clearable="false"
          :picker-options="pickerOptions"
          style="width:280px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button type="info" icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <el-table
      v-loading="loading"
      :data="tableData"
      style="width: 100%;flex:1;"
      ref="oTable"
      height="100%"
      border
      size="mini"
    >
      <el-table-column prop="jobDate" label="工作日期" width="140px"></el-table-column>
      <el-table-column prop="jobHeadid" label="工作单号"></el-table-column>
      <el-table-column prop="fullName" label="处置企业" show-overflow-tooltip min-width="200px"></el-table-column>
      <el-table-column prop="status" label="状态"></el-table-column>
      <el-table-column prop="carTag" label="车牌号"></el-table-column>
      <el-table-column prop="driverName" label="司机"></el-table-column>
      <el-table-column prop="leaveTime" label="离开时间" width="140px"></el-table-column>
      <el-table-column prop="returnTime" label="返回时间" width="140px"></el-table-column>
      <el-table-column prop="flowoutamt" label="计算数量(吨)"></el-table-column>
      <el-table-column prop="factoutamt" label="实际数量(吨)"></el-table-column>
      <el-table-column prop="memo" label="备注"></el-table-column>
      <el-table-column label="操作" width="80" align="center">
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="clickJoint(scope.row)">查看联单</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="page.pageNum"
      :limit.sync="page.pageSize"
      @pagination="getList(queryForm)"
    />
    <!-- 联单列表 -->
    <el-dialog title="联单列表" :visible.sync="visible" width="1000px">
      <Detailed :jobHeadid="jobHeadid"></Detailed>
    </el-dialog>
  </div>
</template>
<script>
import { tankcarEnterprise, getTransJobHead } from "@/api/lingxing";
import Detailed from "./detailed";
export default {
  components: { Detailed },
  data() {
    return {
      queryForm: {},
      sourceList: [],
      joinListType: [],
      tableData: [],
      loading: false,
      total: 0,
      page: {
        pageNum: 1,
        pageSize: 30,
      },
      pickerOptions: {
        disabledDate: this.disabledDate,
      },
      visible: false,
      jobHeadid: "",
    };
  },
  created() {
    this.getSourceList();
    this.getList();
    this.getDicts("joint_list_type").then((res) => {
      this.joinListType = res.data;
    });
  },
  methods: {
    getList(v = {}) {
      this.loading = true;
      let oVal = {};
      if (Object.keys(v).length > 0) {
        if (v.oTime && v.oTime.length > 0) {
          oVal.beginTime = v.oTime[0];
          oVal.endTime = v.oTime[1];
        }
        if (v.jobStatus) oVal.jobStatus = v.jobStatus;
        if (v.procSourceid) oVal.procSourceid = v.procSourceid;
        if (v.jobHeadid) oVal.jobHeadid = v.jobHeadid;
      }
      getTransJobHead(oVal, this.urlParameter(this.page)).then((res) => {
        if (res.code === 200) {
          this.tableData = res.data || res.rows;
          this.total = res.total;
        }
        this.loading = false;
      });
    },
    getSourceList() {
      tankcarEnterprise().then((res) => {
        if (res.code === 200) {
          this.sourceList = res.data || res.rows;
        }
      });
    },
    handleQuery() {
      this.page.pageNum = 1;
      this.getList(this.queryForm);
    },
    resetQuery() {
      this.resetForm("queryForm");
      this.queryForm = {};
      this.handleQuery();
    },
    disabledDate(time) {
      return time.getTime() > Date.now();
    },
    clickJoint(v) {
      this.jobHeadid = v.jobHeadid;
      this.visible = true;
    },
  },
};
</script>
