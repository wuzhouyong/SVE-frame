<template>
  <div class="task-wk">
    <!--    异常企业统计-->
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
        prop="zdatasourname"
        label="企业名称"
        show-overflow-tooltip
        width="280">
      </el-table-column>
      <el-table-column
        prop="address"
        label="数据异常线索"
        width="360">
        <template slot-scope="scope">
          {{ ([scope.row.twoDayCnt, scope.row.fourDayCnt, scope.row.rateLess90].filter(i => i !== null)).join(',') }}
        </template>
      </el-table-column>
      <el-table-column
        label="责任单位"
        width="180">
        <template slot-scope="scope">
          {{ scope.row.townName }}生态环境分局
        </template>
      </el-table-column>
      <el-table-column label="备注">
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import {dgomDutyAbnorEnter, dgomDutyAbnorEnterExport} from '@/api/om'
import {fun_date} from "@/utils/ruoyi";

export default {
  data() {
    return {
      queryForm: {},
      tableData: [],
      loading: false,
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
        endDate: v.isDate[1]
      }
      dgomDutyAbnorEnter(oVal).then(res => {
        this.tableData = res.data;
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
          return dgomDutyAbnorEnterExport(oVal);
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
