<template>
  <div style="margin-top:10px;position: relative">
    <el-button type="warning" icon="el-icon-upload2" size="mini" @click="exportTable"
               style="position: absolute;right:0px;top:-30px;">导出涉气责考评分表格
    </el-button>
    <el-table
      :data="tableData"
      style="width: 100%"
      class="completTable"
      max-height="600">
      <el-table-column
        type="index"
        align="center">
      </el-table-column>
      <el-table-column
        prop="town_name"
        align="center"
        width="90"
        label="镇街">
      </el-table-column>
      <el-table-column
        prop="norm"
        align="center"
        label="指标分值">
      </el-table-column>
      <el-table-column>
        <template slot="header">
          VOCs固定源在线监控建设<br/>（加油站、汽修企业、餐饮单位、涉VOCs重点工业企业）（3分）
        </template>
        <el-table-column
          prop="in_total"
          align="center"
          label="考核名单任务量">
        </el-table-column>
        <el-table-column
          prop="finish_cnt"
          align="center"
          label="考核名单完成量">
        </el-table-column>
        <el-table-column
          prop="check_cnt"
          align="center"
          label="考核名单量">
        </el-table-column>
        <el-table-column
          prop="rate"
          align="center"
          label="完成率%">
        </el-table-column>
        <el-table-column
          prop="unfinish_cnt"
          align="center"
          label="考核名单未完成量">
        </el-table-column>
        <el-table-column
          prop="out_list"
          align="center"
          label="非考核名单完成量">
        </el-table-column>
        <el-table-column
          prop="limit"
          align="center"
          label="考核限值">
        </el-table-column>
        <el-table-column
          prop="score"
          align="center"
          label="得分">
          <template slot-scope="scope">
            {{ scope.row.score > 3 ? 3 : scope.row.score }}
          </template>
        </el-table-column>
      </el-table-column>
      <el-table-column label="加油站">
        <el-table-column
          prop="in_total1014"
          align="center"
          label="考核名单任务量">
        </el-table-column>
        <el-table-column
          prop="in_list1014"
          align="center"
          label="考核名单完成量">
        </el-table-column>
        <el-table-column
          prop="rate1014"
          align="center"
          label="完成率%">
        </el-table-column>
        <el-table-column
          prop="unfinish1014"
          align="center"
          label="考核名单未完成量">
        </el-table-column>
        <el-table-column
          prop="out_list1014"
          align="center"
          label="非考核名单完成量">
        </el-table-column>
      </el-table-column>
      <el-table-column label="餐饮单位">
        <el-table-column
          prop="in_total1006"
          align="center"
          label="考核名单任务量">
        </el-table-column>
        <el-table-column
          prop="in_list1006"
          align="center"
          label="考核名单完成量">
        </el-table-column>
        <el-table-column
          prop="rate1006"
          align="center"
          label="完成率%">
        </el-table-column>
        <el-table-column
          prop="unfinish1006"
          align="center"
          label="考核名单未完成量">
        </el-table-column>
        <el-table-column
          prop="out_list1006"
          align="center"
          label="非考核名单完成量">
        </el-table-column>
      </el-table-column>
      <el-table-column label="汽修企业">
        <el-table-column
          prop="in_total1013"
          align="center"
          label="考核名单任务量">
        </el-table-column>
        <el-table-column
          prop="in_list1013"
          align="center"
          label="考核名单完成量">
        </el-table-column>
        <el-table-column
          prop="rate1013"
          align="center"
          label="完成率%">
        </el-table-column>
        <el-table-column
          prop="unfinish1013"
          align="center"
          label="考核名单未完成量">
        </el-table-column>
        <el-table-column
          prop="out_list1013"
          align="center"
          label="非考核名单完成量">
        </el-table-column>
      </el-table-column>
      <el-table-column label="涉VOCs重点工业企业">
        <el-table-column
          prop="in_total1004"
          align="center"
          label="考核名单任务量">
        </el-table-column>
        <el-table-column
          prop="in_list1004"
          align="center"
          label="考核名单完成量">
        </el-table-column>
        <el-table-column
          prop="rate1004"
          align="center"
          label="完成率%">
        </el-table-column>
        <el-table-column
          prop="unfinish1004"
          align="center"
          label="考核名单未完成量">
        </el-table-column>
        <el-table-column
          prop="out_list1004"
          align="center"
          label="非考核名单完成量">
        </el-table-column>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import { taskScoreGas, taskExportGas } from "@/api/analysis"

export default {
  data() {
    return {
      tableData: []
    }
  },
  created() {
    taskScoreGas().then(res => {
      if (res.code === 200) {
        this.tableData = res.data;
      }
    })
  },
  methods: {
    exportTable() {
      let _that = this;
      this.$confirm("是否导出数据?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(function() {
          return taskExportGas();
        })
        .then((response) => {
          this.download(response.msg);
        })
        .catch(function() {});
    }
  }
}
</script>

<style lang="scss" scoped>
.completTable {
  background-color: transparent;
  margin-top: 5px;

  /deep/ .el-table__header-wrapper {
    tr {
      background-color: transparent;

      th {
        background-color: transparent;
        color: #fff;
      }

      td {
        background-color: transparent;
        color: #fff;
      }
    }
  }

  /deep/ .el-table__body-wrapper {
    tr {
      background-color: transparent;

      &:hover {
        td {
          background-color: rgba(0, 0, 0, 0.1);
        }
      }

      td {
        background-color: transparent;
        color: #fff;
      }
    }
  }
}
</style>
