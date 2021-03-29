<template>
  <div class="task-wk">
    <!-- 搜索表单 -->
    <el-form :model="queryForm" ref="queryForm" :inline="true">
      <el-form-item label="企业名称" prop="sourceId">
        <el-select v-model="queryForm.sourceId" placeholder="企业名称" style="width:220px" filterable size="mini">
          <el-option :label="i.zdatasourname" :value="i.zdatasourid" v-for="i in enterprise"
                     :key="i.zdatasourid"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="镇街" prop="townId" v-if="!isTown">
        <el-select v-model="queryForm.townId" size="mini" placeholder="请选择镇街" style="width:100%">
          <el-option :label="i.name" :value="i.id" v-for="i in townList" :key="i.id"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="是否存在问题" prop="issue">
        <el-select v-model="queryForm.issue" size="mini" style="width:100%">
          <el-option label="无" :value="0"></el-option>
          <el-option label="存在问题" :value="1"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="年份" prop="year">
        <el-select v-model="queryForm.year" size="mini" placeholder="选择年份" style="width:140px">
          <el-option :label="`${i.value}年`" :value="i.value" v-for="i in yearList" :key="i.value"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="月份" prop="month">
        <el-select v-model="queryForm.month" size="mini" placeholder="选择年份" style="width:140px">
          <el-option :label="i.name" :value="i.value" v-for="i in monthList" :key="i.value"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-radio-group v-model="queryForm.status" size="mini">
          <el-radio-button :label="0">未录入</el-radio-button>
          <el-radio-button :label="1">已录入</el-radio-button>
        </el-radio-group>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        <el-button type="warning" icon="el-icon-upload2" size="mini">导出</el-button>
      </el-form-item>
    </el-form>
    <el-table
      v-loading="loading"
      :data="tableData"
      size="mini"
      class="taskTable"
      border
      height="100%"
      style="width: 100%">
      <el-table-column
        label="企业名称"
        prop="sourceName"
        width="280"
        show-overflow-tooltip>
      </el-table-column>
      <el-table-column
        label="所属镇街"
        prop="townName"
        width="80">
      </el-table-column>
      <el-table-column
        label="存在的主要问题"
        prop="issue"
        width="180"
        show-overflow-tooltip>
      </el-table-column>
      <el-table-column
        label="责任方"
        prop="dutyCom"
        width="260"
        show-overflow-tooltip>
      </el-table-column>
      <el-table-column
        label="执法检查单位"
        prop="deptCode"
        width="190"
        show-overflow-tooltip>
      </el-table-column>
      <el-table-column
        label="执法检查人员姓名"
        prop="checkBy"
        width="130"
        show-overflow-tooltip>
      </el-table-column>
      <el-table-column
        label="检查时间"
        prop="dateChecked"
        width="90">
      </el-table-column>
      <el-table-column
        label="整改要求"
        prop="request"
        width="180"
        show-overflow-tooltip>
      </el-table-column>
      <el-table-column
        label="处理处置结果"
        prop="procResult"
        width="150"
        show-overflow-tooltip>
      </el-table-column>
      <el-table-column
        label="备注"
        prop="memo"
        width="130"
        show-overflow-tooltip>
      </el-table-column>
      <el-table-column
        label="状态"
        prop="status"
        width="90">
        <template slot-scope="scope">
          {{ scope.row.status === 0 ? "未录入" : "已录入" }}
        </template>
      </el-table-column>
      <el-table-column
        label="年月"
        prop="reportMonth"
        width="90">
      </el-table-column>
      <el-table-column
        label="操作"
        prop="taskType"
        align="center"
        fixed="right"
        width="110">
        <template slot-scope="scope">
          <el-link type="primary" :underline="false" style="font-size:12px;" v-if="scope.row.status===0"
                   @click="inputMsg(scope.row)">
            检查信息录入
          </el-link>
          <el-link v-else type="info" disabled style="font-size:12px;">信息已录入</el-link>
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
    <el-dialog
      title="检查信息录入"
      :visible.sync="visible"
      :close-on-click-modal="false0"
      width="600px">
      <n-form :values="values" v-if="visible" ref="nform" @isSuccess="isSuccess"></n-form>
      <span slot="footer" class="dialog-footer">
      <el-button type="primary" @click="mesSub">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import {dgomEnterprise, dgomTowns, dgomCheckReport} from "@/api/om";
import {mapState} from "vuex";
import nForm from "./nForm";

export default {
  components: {nForm},
  data () {
    return {
      queryForm: {
        sourceId: null,
        townId: null,
        issue: null,
        year: null,
        month: null,
        status: null
      },
      enterprise: [],
      townList: [],
      tableData: [],
      monthList: [
        {value: 1, name: "一月"},
        {value: 2, name: "二月"},
        {value: 3, name: "三月"},
        {value: 4, name: "四月"},
        {value: 5, name: "五月"},
        {value: 6, name: "六月"},
        {value: 7, name: "七月"},
        {value: 8, name: "八月"},
        {value: 9, name: "九月"},
        {value: 10, name: "十月"},
        {value: 11, name: "十一月"},
        {value: 12, name: "十二月"},
      ],
      yearList: [],
      // 分页
      page: {
        pageNum: 1,
        pageSize: 30
      },
      total: 0,
      isTown: false,
      townCode: null,
      loading: false,
      visible: false,
      values: {}
    };
  },
  computed: {
    ...mapState({
      regions: state => state.user.regions
    })
  },
  watch: {
    regions: {
      handler (v) {
        let newVal = v.replace(/[']/g, "");
        if (newVal !== "4419" && newVal !== "0") {
          this.isTown = true;
          this.queryForm.townCode = newVal;
          this.townCode = newVal;
        } else {
          this.isTown = false;
          this.queryForm.townCode = null;
        }
        this.getList(this.queryForm);
      },
      immediate: true
    }
  },
  created () {
    let oYear = [{value: new Date().getFullYear()}];
    let year = new Date().getFullYear();
    for (let i = 0; i < 4; i++) {
      year -= 1;
      oYear.push({value: year});
    }
    this.yearList = oYear;
    dgomEnterprise().then(res => this.enterprise = res.data);
    dgomTowns().then(res => this.townList = res.data);
  },
  methods: {
    getList (v = {}) {
      this.loading = true;
      dgomCheckReport(v, this.urlParameter(this.page)).then(res => {
        if (res.code === 200) {
          this.tableData = res.rows;
          this.total = res.total;
        }
        this.loading = false;
      });
    },
    handleQuery () {
      this.page.pageNum = 1;
      this.getList(this.queryForm);
    },
    resetQuery () {
      this.queryForm = {
        sourceId: null,
        townId: null,
        issue: null,
        year: null,
        month: null,
        status: null
      };
      if (this.townCode !== "4419" && this.townCode !== "0") {
        this.queryForm.townCode = this.townCode;
      }
      this.handleQuery();
    },
    inputMsg (v) {
      this.values = v;
      this.visible = true;
    },
    mesSub () {
      this.$refs.nform.submitMsg();
    },
    isSuccess () {
      this.visible = false;
      this.getList(this.queryForm);
    }
  }
};
</script>

<style lang='scss' scoped>

</style>
