<template>
  <div class="hix">
    <el-button type="warning" icon="el-icon-upload" style="margin-bottom:10px;" @click="exportList">导出当前企业列表</el-button>
    <el-table
      :data="tableData"
      border
      height="100%"
      style="width: 100%:flex:1">
      <el-table-column
        prop="fullName"
        label="企业名称"
        show-overflow-tooltip
        width="200">
      </el-table-column>
      <el-table-column
        prop="mcnt"
        label="分钟传输率">
      </el-table-column>
      <el-table-column
        prop="hcnt"
        label="小时传输率">
      </el-table-column>
      <el-table-column
        prop="dcnt"
        label="日传输率">
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <pagination
      :total="total"
      :page.sync="page.pageNum"
      :limit.sync="page.pageSize"
      @pagination="changepage"
    />
  </div>
</template>

<script>
import {stEnterpriseNetwork, stExportNetwork} from '@/api/currency'
import Cookies from "js-cookie";

export default {
  props: ['towncode'],
  watch: {
    towncode: {
      handler(v) {
        let taskName = Cookies.get("system-name");
        this.getList(taskName, {townCode: v})
      },
      immediate: true
    }
  },
  data() {
    return {
      tableData: [],
      // 分页
      page: {
        pageNum: 1,
        pageSize: 30
      },
      total: 0,
    }
  },
  methods: {
    changepage() {
      let taskName = Cookies.get("system-name");
      this.getList(taskName, {townCode: this.towncode})
    },
    getList(task, v) {
      stEnterpriseNetwork(task, v, this.urlParameter(this.page)).then(res => {
        if (res.code === 200) {
          this.tableData = res.rows;
          this.total = res.total;
        }
      })
    },
    exportList() {
      this.$confirm("是否导出数据?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          let taskName = Cookies.get("system-name");
          return stExportNetwork(taskName, {townCode: this.towncode});
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
.hix {
  width: 100%;
  height: 600px;
  overflow: hidden;
  display: flex;
  flex-direction: column;
}
</style>
