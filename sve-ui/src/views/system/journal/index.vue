<template>
  <div class="task-wk">
    <!-- 搜索表单 -->
    <el-form :model="queryForm" ref="queryForm" :inline="true">
      <el-form-item label="模块" prop="module">
        <el-input
          v-model="queryForm.module"
          placeholder="模块关键字"
          clearable
          size="mini"
          style="width: 180px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="操作记录" prop="action">
        <el-input
          v-model="queryForm.action"
          placeholder="操作记录关键字"
          clearable
          size="mini"
          style="width: 180px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="操作人" prop="nickName">
        <el-input
          v-model="queryForm.nickName"
          placeholder="操作人关键字"
          clearable
          size="mini"
          style="width: 180px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <!--    表格-->
    <el-table
      v-loading="loading"
      :data="tableData"
      style="width: 100%;flex:1;"
      ref="oTable"
      height="100%"
      border
      size="mini"
    >

      <el-table-column prop="logId" label="日志ID" width="80" align="center"></el-table-column>
      <el-table-column prop="module" label="模块" width="340" show-overflow-tooltip></el-table-column>
      <el-table-column prop="action" label="操作记录" min-width="200"></el-table-column>
      <el-table-column prop="nickName" label="操作人" width="140" show-overflow-tooltip></el-table-column>
      <el-table-column prop="createdAt" label="操作时间" width="160" show-overflow-tooltip></el-table-column>
      <el-table-column prop="operaResult" label="操作结果" width="160" show-overflow-tooltip></el-table-column>
    </el-table>
    <!-- 分页 -->
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="page.pageNum"
      :limit.sync="page.pageSize"
      @pagination="getList(queryForm)"
    />
  </div>
</template>

<script>
import {noticeOperaList} from "@/api/system/config.js"

export default {
  data() {
    return {
      tableData: [],
      loading: false,
      queryForm: {
        module: null,
        action: null,
        nickName: null
      },
      // 分页
      page: {
        pageNum: 1,
        pageSize: 30
      },
      total: 0
    }
  },
  created() {
    this.getList();
  },
  methods: {
    getList(v = {}) {
      this.loading = true;
      noticeOperaList(v, this.urlParameter(this.page)).then(res => {
        if (res.code === 200) {
          this.tableData = res.rows;
          this.total = res.total;
        }
        this.loading = false;
      })
    },
    handleQuery() {
      this.page.pageNum = 1;
      this.getList(this.queryForm);
    },
    resetQuery() {
      this.page.pageNum = 1;
      this.queryForm = {
        module: null,
        action: null,
        nickName: null
      };
      this.getList();
    }
  }
}
</script>

<style lang="scss" scoped>

</style>
