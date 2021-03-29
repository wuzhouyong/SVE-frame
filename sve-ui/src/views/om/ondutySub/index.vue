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
          prop="townName"
        label="镇街"
        min-width="80px">
      </el-table-column>
      <el-table-column
        prop="sourceCount"
        label="企业数">
      </el-table-column>
      <el-table-column
        prop="checkpointCount"
        label="监控点数量"
        >
      </el-table-column>
      <el-table-column
        prop="transportRate"
        label="传输率">
      </el-table-column>
      <el-table-column
        prop="lessthanCount"
        label="传输率低于90%企业数"
        width="160px">
      </el-table-column>
      <el-table-column
        prop="overCount"
        label="超标任务(条)">
      </el-table-column>
      <el-table-column
        prop="lostCount"
        label="缺失任务(条)">
      </el-table-column>
      <el-table-column
        prop="zeroCount"
        label="零值任务(条)"
       >
      </el-table-column>
      <el-table-column
        prop="negativeCount"
        label="负值任务(条)"
        >
      </el-table-column>
      <el-table-column
        prop="staticCount"
        label="恒值任务(条)"
        >
      </el-table-column>
      <el-table-column
        prop="unhandleCount"
        label="企业未处理"
       >
      </el-table-column>
      <el-table-column
        prop="finishCount"
        label="分局完成"
        >
      </el-table-column>
      <el-table-column
        prop="finishRate"
        label="分局完成率"
       >
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import {dgomDutysubWeeklyExport, dgomDutysubWeekly} from '@/api/om';
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
      dgomDutysubWeekly(oVal).then(res => {
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
          return dgomDutysubWeeklyExport(oVal);
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
