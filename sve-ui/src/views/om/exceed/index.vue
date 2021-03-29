<template>
  <div class="task-wk">
    <el-form :model="queryForm" ref="queryForm" :inline="true" class="guocheng-serchForm">
      <el-form-item label="企业名称" prop="sourceId">
        <el-select v-model="queryForm.sourceId" placeholder="请选择企业名称" style="width:180px" filterable size="mini">
          <el-option :label="i.zdatasourname" :value="i.zdatasourid" v-for="i in sourceList"
                     :key="i.zdatasourid"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="常用因子" prop="elementid">
        <el-select v-model="queryForm.elementid" placeholder="请选择常用因子" style="width:140px" filterable size="mini">
          <el-option :label="i.zelementdesc" :value="i.zelementid" v-for="i in elementList"
                     :key="i.zelementid"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="日期范围" prop="date">
        <el-date-picker
          style="width:240px"
          v-model="queryForm.date"
          type="daterange"
          value-format="yyyy-MM-dd"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item prop="ismanualtask">
        <el-radio-group v-model="queryForm.ismanualtask" size="mini">
          <el-radio-button label="1">日均值</el-radio-button>
          <el-radio-button label="0">小时均值</el-radio-button>
        </el-radio-group>
      </el-form-item>
      <el-form-item prop="taskstatus">
        <el-checkbox v-model="taskstatus">零值数据</el-checkbox>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="getList">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <el-table
      :data="tableData"
      v-loading="loading"
      style="width: 100%;flex:1"
      size="mini"
      height="100%"
      border>
      <el-table-column
        prop="datadate"
        label="日期"
        width="150"
        align="center">
        <template slot-scope="scope">
          {{ queryForm.ismanualtask === "1" ? scope.row.datadate.substr(0, 10) : scope.row.datadate }}
        </template>
      </el-table-column>
      <el-table-column
        prop="sourceName"
        label="企业名称"
        width="250"
        show-overflow-tooltip>
      </el-table-column>
      <el-table-column
        prop="townName"
        label="镇街"
        width="100">
      </el-table-column>
      <el-table-column
        prop="categoryname"
        label="行业"
        width="150">
      </el-table-column>
      <el-table-column
        prop="checkpointdesc"
        label="排放口"
        width="190">
      </el-table-column>
      <el-table-column
        prop="elementdesc"
        label="因子"
        width="150">
      </el-table-column>
      <el-table-column
        prop="upperlimit"
        label="上限"
        width="150">
      </el-table-column>
      <el-table-column
        prop="amount"
        label="监测值">
      </el-table-column>
      <el-table-column
        label="超标倍数"
        min-width="150">
        <template slot-scope="scope">
          {{ scope.row.amount === 0 ? "-" : (scope.row.amount / scope.row.upperlimit).toFixed(2) }}
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="page.pageNum"
      :limit.sync="page.pageSize"
      @pagination="getList()"
    />
  </div>
</template>
<script>
import { dgomEnterprise, dgomElement, dgomZerovalueH } from "@/api/om"
import { fun_date } from "@/utils/ruoyi";

export default {
  data: () => ({
    queryForm: {
      sourceId: undefined,
      elementid: undefined,
      date: undefined,
      ismanualtask: "1"
    },
    sourceList: [],
    elementList: [],
    tableData: [],
    page: {
      pageNum: 1,
      pageSize: 30
    },
    total: 0,
    taskstatus: false,
    loading: false,
    isShow: true
  }),
  created() {
    let oDate = this.parseTime(new Date()).substr(0, 10);
    this.queryForm.date = [fun_date(-6), oDate];
    this.getEnterprise();
    this.getElement();
    this.getList();
  },
  methods: {
    getList() {
      this.loading = true;
      let oVal = {};
      if (!this.queryForm.date) {
        this.msgError("请选择需要查询的时间");
        this.loading = false;
        return;
      } else {
        oVal.startDate = this.queryForm.date[0];
        oVal.endDate = this.queryForm.date[1];
      }
      oVal.ismanualtask = this.queryForm.ismanualtask;
      if (this.taskstatus) {
        oVal.taskstatus = 0;
      } else {
        oVal.taskstatus = 1;
      }
      if (this.queryForm.sourceId) oVal.sourceId = this.queryForm.sourceId;
      if (this.queryForm.elementid) oVal.elementid = this.queryForm.elementid;
      dgomZerovalueH(oVal, this.urlParameter(this.page)).then(res => {
          if (res.code === 200) {
            this.tableData = res.rows;
            this.total = res.total;
            this.isShow = !this.taskstatus;
          }
          this.loading = false;
        }
      )
    },
    //获取企业列表
    getEnterprise() {
      dgomEnterprise().then(res => {
        if (res.code === 200) this.sourceList = res.data;
      })
    },
    //常用因子列表
    getElement() {
      dgomElement().then(res => {
        if (res.code === 200) this.elementList = res.data;
      })
    },
    resetQuery() {
      this.resetForm("queryForm");
      let oDate = this.parseTime(new Date()).substr(0, 10);
      this.queryForm = {
        sourceId: undefined,
        elementid: undefined,
        date: [fun_date(-6), oDate],
        ismanualtask: "1"
      };
      this.taskstatus = false;
      this.getList();
    }
  }
}
</script>

<style lang="scss">

</style>
