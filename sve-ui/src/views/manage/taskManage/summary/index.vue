<template>
  <div class="task-wk">
    <!-- 操作按钮 -->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-radio-group v-model="listType" size="mini" @change="onChange">
          <el-radio-button :label="-1">全部</el-radio-button>
          <el-radio-button :label="0">考核名单</el-radio-button>
          <el-radio-button :label="1">非考核名单</el-radio-button>
        </el-radio-group>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" icon="el-icon-upload2" size="mini" @click="exportTable">导出表格</el-button>
      </el-col>
    </el-row>
    <el-table
      :data="data"
      height="100%"
      border
      v-loading="loading"
      :row-class-name="tableRowClassName"
    >
      <el-table-column type="index" label="序号" align="center" width="50"></el-table-column>
      <el-table-column prop="townName" label="镇街" width="130">
        <template slot-scope="scope">
          <el-link
            v-if="scope.row.townName !== ' 合计'"
            type="primary"
            @click="jumpTask(scope.row)"
            :underline="false"
            style="font-size:12px"
          >{{scope.row.townName}}</el-link>
          <span v-else>{{scope.row.townName}}</span>
        </template>
      </el-table-column>
      <el-table-column prop="qyzs" label="总任务数" width="80"></el-table-column>
      <el-table-column label="各监控任务数" align="center">
        <el-table-column prop="shuizhongdian" label="涉水重点排污单位" width="130"></el-table-column>
        <el-table-column prop="qizhongdian" label="涉气重点排污单位" width="130"></el-table-column>
        <el-table-column prop="paishui" label="重点排水户" width="100"></el-table-column>
        <el-table-column prop="lsprod" label="零散废水产生单位" width="125"></el-table-column>
        <el-table-column prop="lsprocess" label="零散废水处理企业" width="125"></el-table-column>
        <el-table-column prop="guocheng" label="四大流域过程" width="100"></el-table-column>
        <el-table-column prop="zaozhi" label="造纸" width="70"></el-table-column>
        <el-table-column prop="jinshu" label="金属表面" width="80"></el-table-column>
        <el-table-column prop="yitihua" label="一体化污水处理" width="120"></el-table-column>
        <el-table-column prop="waterv" label="水重点排污监控(加装视频)" width="110"></el-table-column>
        <el-table-column prop="voc" label="市级以上VOCs" width="120"></el-table-column>
        <el-table-column prop="qixiu" label="汽修行业" width="80"></el-table-column>
        <el-table-column prop="youyan" label="餐饮行业" width="80"></el-table-column>
        <el-table-column prop="jiayou" label="加油站" min-width="80"></el-table-column>
        <el-table-column prop="gasv" label="VOCs重点排放源自动监控" width="110"></el-table-column>
        <el-table-column prop="xinfang" label="重点信访企业" width="110"></el-table-column>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import { sourceSummary, exportSummary } from "@/api/monitor_task";

export default {
  data() {
    return {
      data: [],
      listType: 0,
      loading: false,
    };
  },
  created() {
    this.getList({ listType: this.listType });
  },
  methods: {
    getList(v = {}) {
      this.loading = true;
      sourceSummary(v).then((res) => {
        this.data = res.rows;
        this.loading = false;
      });
    },
    exportTable() {
      let _that = this;
      this.$confirm("是否导出数据?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(function () {
          return exportSummary({ listType: _that.listType });
        })
        .then((response) => {
          this.download(response.msg);
        })
        .catch(function () {});
    },
    jumpTask(v) {
      this.$router.push({
        path: "task",
        query: { townCode: v.townCode, listType: this.listType },
      });
    },
    onChange(v) {
      this.getList({ listType: v });
    },
    //表格样式
    tableRowClassName({ row, rowIndex }) {
      if (row.townName === " 合计") {
        return "warning-row";
      }
      return "";
    },
  },
};
</script>

<style lang="scss">
.el-table .warning-row {
  background: oldlace;
  font-weight: bold;
}
</style>
