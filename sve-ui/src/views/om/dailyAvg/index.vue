<template>
  <div class="task-wk">
    <!--    日均值超标统计-->
    <!--    值守日记-->
    <el-form :model="queryForm" ref="queryForm" :inline="true">
      <el-form-item label="选择日期" prop="startTime">
        <el-date-picker
          v-model="queryForm.isDate"
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
      </el-form-item>
      <el-form-item>
        <el-button type="warning" icon="el-icon-upload2" size="mini" @click="exportTable">导出表格</el-button>
      </el-form-item>
    </el-form>
    <el-table
      v-loading="loading"
      element-loading-text="拼命加载中"
      :data="tableData"
      size="mini"
      height="100%"
      border
      style="width: 100%">
      <el-table-column
        prop="townName"
        label="镇街"
        width="80">
      </el-table-column>
      <el-table-column
        prop="sourceName"
        label="企业名称"
        width="240"
        show-overflow-tooltip>
      </el-table-column>
      <el-table-column
        prop="deptName"
        label="责任单位"
        width="180">
      </el-table-column>
      <el-table-column
        prop="checkpointName"
        label="监测点"
        width="150"
        show-overflow-tooltip>
      </el-table-column>
      <el-table-column
        prop="elementName"
        label="因子"
        width="140">
      </el-table-column>
      <el-table-column
        prop="standard"
        label="执行标准"
        width="110">
      </el-table-column>
      <el-table-column
        prop="dataDate"
        label="监测日期"
        width="110">
      </el-table-column>
      <el-table-column
        prop="amount"
        label="监测值"
        width="110">
      </el-table-column>
      <el-table-column
        prop="multiple"
        label="倍数">
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <pagination
      :total="total"
      :page.sync="queryForm.pageNum"
      :limit.sync="queryForm.pageSize"
      @pagination="getList(queryForm)"
    />
  </div>
</template>

<script>
import {dgomDutyDailyAvqExport, dgomDutyDailyAvq} from '@/api/om'
import {fun_date} from "@/utils/ruoyi";

export default {
  data() {
    return {
      queryForm: {
        pageNum: 1,
        pageSize: 30
      },
      tableData: [],
      loading: false,
      total: 0,
    }
  },
  created() {
    let oDate = this.parseTime(new Date()).substr(0, 10);
    this.queryForm.isDate = [fun_date(-7), oDate];
    this.getList(this.queryForm);
  },
  methods: {
    getList(v) {
      this.loading = true;
      let oVal = {
        startDate: v.isDate[0],
        endDate: v.isDate[1],
        pageNum: this.queryForm.pageNum,
        pageSize: this.queryForm.pageSize,
      }
      dgomDutyDailyAvq(oVal).then(res => {
        this.tableData = res.rows;
        this.total = res.total;
        this.loading = false;
      })
    },
    handleQuery() {
      this.getList(this.queryForm);
    },
    exportTable() {
      let _that = this;
      this.$confirm("是否导出数据?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(function () {
          let oVal = {
            startDate: _that.queryForm.isDate[0],
            endDate: _that.queryForm.isDate[1]
          }
          return dgomDutyDailyAvqExport(oVal);
        })
        .then((response) => {
          this.download(response.msg);
        })
        .catch(function () {
        });
    },
  }
}
</script>

<style lang='scss' scoped>

</style>
