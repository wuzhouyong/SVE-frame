<template>
  <div class="task-wk">
    <el-form :model="queryForm" ref="queryForm" :inline="true" class="voc-serchForm">
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
    <!--    重点监控企业异常处理结果-->
    <el-table
      v-loading="loading"
      :data="tableData"
      height="100%"
      size="mini"
      border
      style="width: 100%">
      <el-table-column
        prop="sourceName"
        label="企业名称"
        min-width="250px"
        show-overflow-tooltip>
      </el-table-column>
      <el-table-column
        prop="townName"
        label="镇街"
        width="80px">
      </el-table-column>
      <el-table-column
        prop="startDate"
        label="异常开始时间"
        width="100px">
      </el-table-column>
      <el-table-column
        prop="abnorDesc"
        label="异常原因"
        min-width="360px"
        show-overflow-tooltip>
      </el-table-column>
      <el-table-column
        prop="isReported"
        label="是否已报告"
        width="90px">
      </el-table-column>
      <el-table-column
        prop="dateReported"
        label="报告时间"
        width="100px">
      </el-table-column>
      <el-table-column
        prop="isReturnNormal"
        label="是否恢复正常"
        width="100px">
      </el-table-column>
      <el-table-column
        prop="returnNormalDate"
        label="恢复正常时间"
        width="100px">
      </el-table-column>
      <el-table-column
        prop="processMemo"
        label="备注"
        min-width="335px"
        show-overflow-tooltip>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import {dgomDutyAbnorProcessResult, dgomDutyAbnorProcessResultExport} from '@/api/om';
import {fun_date} from "@/utils/ruoyi";

export default {
  data() {
    return {
      tableData: [],
      queryForm: {},
      loading: false
    }
  },
  created() {
    let oDate = this.parseTime(new Date()).substr(0, 10);
    this.queryForm.isDate = [fun_date(-10), oDate];
    this.getList(this.queryForm);
  },
  methods: {
    getList(v) {
      this.loading = true;
      let oVal = {
        startDate: v.isDate[0],
        endDate: v.isDate[1]
      }
      dgomDutyAbnorProcessResult(oVal).then(res => {
        this.tableData = res.data;
        this.loading = false
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
          return dgomDutyAbnorProcessResultExport(oVal);
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
