<template>
  <div style="margin-top:10px;position: relative">
    <el-button type="warning" icon="el-icon-upload2" size="mini" @click="exportTable"
               style="position: absolute;right:0px;top:-30px;">导出涉水责考评分表格
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
        align="center"
        prop="norm"
        label="指标分值">
      </el-table-column>
      <el-table-column label="涉水在线监测监控系统建设">
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
          prop="limit"
          align="center"
          label="考核限值">
        </el-table-column>
        <el-table-column
          align="center"
          label="扣分">
          <template slot-scope="scope">
            {{ scope.row.score > 2 ? 2 : scope.row.score }}
          </template>
        </el-table-column>
      </el-table-column>
      <el-table-column label="排水户">
        <el-table-column
          prop="in_total1012"
          align="center"
          label="考核名单任务量">
        </el-table-column>
        <el-table-column
          prop="in_list1012"
          align="center"
          label="考核名单完成量">
        </el-table-column>
        <el-table-column
          prop="rate1012"
          align="center"
          label="完成率%">
        </el-table-column>
        <el-table-column
          prop="unfinish1012"
          align="center"
          label="考核名单未完成量">
        </el-table-column>
        <el-table-column
          prop="out_list1012"
          align="center"
          label="非考核名单完成量">
        </el-table-column>
      </el-table-column>
      <el-table-column label="零星废水">
        <el-table-column
          prop="in_total1003"
          align="center"
          label="考核名单任务量">
        </el-table-column>
        <el-table-column
          prop="in_list1003"
          align="center"
          label="考核名单完成量">
        </el-table-column>
        <el-table-column
          prop="rate1003"
          align="center"
          label="完成率%">
        </el-table-column>
        <el-table-column
          prop="unfinish1003"
          align="center"
          label="考核名单未完成量">
        </el-table-column>
        <el-table-column
          prop="out_list1003"
          align="center"
          label="非考核名单完成量">
        </el-table-column>
      </el-table-column>
      <el-table-column label="四大流域">
        <el-table-column
          prop="in_total1002"
          align="center"
          label="考核名单任务量">
        </el-table-column>
        <el-table-column
          prop="in_list1002"
          align="center"
          label="考核名单完成量">
        </el-table-column>
        <el-table-column
          prop="rate1002"
          align="center"
          label="完成率%">
        </el-table-column>
        <el-table-column
          prop="unfinish1002"
          align="center"
          label="考核名单未完成量">
        </el-table-column>
        <el-table-column
          prop="out_list1002"
          align="center"
          label="非考核名单完成量">
        </el-table-column>
      </el-table-column>
      <el-table-column label="造纸">
        <el-table-column
          prop="in_total1019"
          align="center"
          label="考核名单任务量">
        </el-table-column>
        <el-table-column
          prop="in_list1019"
          align="center"
          label="考核名单完成量">
        </el-table-column>
        <el-table-column
          prop="rate1019"
          align="center"
          label="完成率%">
        </el-table-column>
        <el-table-column
          prop="unfinish1019"
          align="center"
          label="考核名单未完成量">
        </el-table-column>
        <el-table-column
          prop="out_list1019"
          align="center"
          label="非考核名单完成量">
        </el-table-column>
      </el-table-column>
      <el-table-column label="金属表面">
        <el-table-column
          prop="in_total1015"
          align="center"
          label="考核名单任务量">
        </el-table-column>
        <el-table-column
          prop="in_list1015"
          align="center"
          label="考核名单完成量">
        </el-table-column>
        <el-table-column
          prop="rate1015"
          align="center"
          label="完成率%">
        </el-table-column>
        <el-table-column
          prop="unfinish1015"
          align="center"
          label="考核名单未完成量">
        </el-table-column>
        <el-table-column
          prop="out_list1015"
          align="center"
          label="非考核名单完成量">
        </el-table-column>
      </el-table-column>
      <el-table-column label="樟村一体化">
        <el-table-column
          prop="in_total1016"
          align="center"
          label="考核名单任务量">
        </el-table-column>
        <el-table-column
          prop="in_list1016"
          align="center"
          label="考核名单完成量">
        </el-table-column>
        <el-table-column
          prop="rate1016"
          align="center"
          label="完成率%">
        </el-table-column>
        <el-table-column
          prop="unfinish1016"
          align="center"
          label="考核名单未完成量">
        </el-table-column>
        <el-table-column
          prop="out_list1016"
          align="center"
          label="非考核名单完成量">
        </el-table-column>
      </el-table-column>
      <el-table-column label="水重点排污">
        <el-table-column
          prop="in_total1017"
          align="center"
          label="考核名单任务量">
        </el-table-column>
        <el-table-column
          prop="in_list1017"
          align="center"
          label="考核名单完成量">
        </el-table-column>
        <el-table-column
          prop="rate1017"
          align="center"
          label="完成率%">
        </el-table-column>
        <el-table-column
          prop="unfinish1017"
          align="center"
          label="考核名单未完成量">
        </el-table-column>
        <el-table-column
          prop="out_list1017"
          align="center"
          label="非考核名单完成量">
        </el-table-column>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import { taskScoreWater, taskExportWater } from "@/api/analysis"

export default {
  data() {
    return {
      tableData: []
    }
  },
  created() {
    taskScoreWater().then(res => {
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
          return taskExportWater();
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
