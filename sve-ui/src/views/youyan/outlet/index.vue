<template>
  <div class="task-wk">
    <!-- 搜索表单 -->
    <el-form :model="queryForm" ref="queryForm" :inline="true" class="outwater-serchForm">
      <el-form-item label="污染源" prop="sourceName">
        <el-input
          v-model="queryForm.sourceName"
          placeholder="请输入污染源"
          clearable
          size="mini"
          style="width: 180px"
        />
      </el-form-item>
      <el-form-item label="排放口" prop="outletName">
        <el-input
          v-model="queryForm.outletName"
          placeholder="请输入排放口"
          clearable
          size="mini"
          style="width: 180px"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <!-- 表格 -->
    <el-table
      v-loading="loading"
      :data="tableData"
      style="width: 100%;flex:1;"
      ref="oTable"
      height="100%"
      border
    >
      <el-table-column prop="sourceName" label="污染源"></el-table-column>
      <el-table-column prop="outletName" label="排放口"></el-table-column>
      <el-table-column prop="memo" label="备注"></el-table-column>
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
import {
  outletList
} from "@/api/youyan";

export default {
  data() {
    return {
      loading: false,
      queryForm: {},
      tableData: [],
      // 分页
      page: {
        pageNum: 1,
        pageSize: 30
      },
      total: 0
    };
  },
  created() {
    this.getList(this.queryForm);
  },
  methods: {
    getList(v = {}) {
      outletList(v, this.urlParameter(this.page)).then((res) => {
        if (res.code === 200) {
          this.tableData = res.rows;
          this.total = res.total;
        }
      });
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.page.pageNum = 1;
      this.getList(this.queryForm);
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    }
  }
};
</script>
