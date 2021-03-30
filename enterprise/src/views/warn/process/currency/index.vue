<template>
  <div class="base-list">
    <!-- 搜索表单 -->
    <el-form :model="queryForm" ref="queryForm" :inline="true" class="search-form">
      <el-form-item label="异常级别" prop="warnLevel">
        <el-select
          v-model="queryForm.warnLevel"
          placeholder="请选择异常级别"
          icon="el-icon-plus"
          size="mini"
          clearable
        >
          <el-option label="一级异常" value="1"></el-option>
          <el-option label="二级异常" value="2"></el-option>
          <el-option label="三级异常" value="3"></el-option>
          <el-option label="四级异常" value="4"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="处理状态" prop="procState">
        <el-select
          v-model="queryForm.procState"
          placeholder="请选择处理状态"
          icon="el-icon-plus"
          size="mini"
          clearable
        >
          <el-option label="待处理" value="0"></el-option>
          <el-option label="退回处理" value="1"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="选择时间" prop="dateRange">
        <el-date-picker
          v-model="queryForm.dateRange"
          type="daterange"
          size="mini"
          style="width:220px"
          value-format="yyyy-MM-dd"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
      </el-form-item>
    </el-form>
    <!-- 表格 -->
    <el-table
      v-loading="loading"
      :data="tableData"
      highlight-current-row
      style="width: 100%;flex:1;"
      ref="oTable"
      height="100%"
      size="mini"
      border
    >
      <el-table-column prop="warnLevelName" label="异常级别" width="180"></el-table-column>
      <el-table-column prop="procStateName" label="处理状态" width="180"></el-table-column>
      <el-table-column prop="warnTypeName" label="异常类型" width="180"></el-table-column>
      <el-table-column label="异常时间" width="170">
        <template slot-scope="scope">{{ scope.row.startDate }} ~ {{ scope.row.endDate }}</template>
      </el-table-column>
      <el-table-column prop="warnDesc" label="异常描述"></el-table-column>
      <el-table-column label="操作" width="80px" align="center">
        <template slot-scope="scope">
          <el-link :underline="false" style="font-size:12px" type="primary" @click="handleWarn(scope.row)">异常处理
          </el-link>
        </template>
      </el-table-column>
    </el-table>
    <pagination
      :total="total"
      :page.sync="page.pageNum"
      :limit.sync="page.pageSize"
      @pagination="getList(queryForm)"
    />
    <!-- 新增修改 -->
    <n-form
      title="异常处理"
      :values="values"
      :sysName="sysName"
      :visible.sync="handleVisible"
      @completed="successRequest"
    ></n-form>
  </div>
</template>

<script>
import {warnHandleList} from "@/api/warn";
import nForm from "./form";

export default {
  components: {nForm},
  data () {
    return {
      queryForm: {},
      tableData: [],
      values: {},
      loading: false,
      handleVisible: false,
      sysName: null,
      // 查询参数
      page: {
        pageNum: 1,
        pageSize: 30
      },
      total: 0,
    };
  },
  watch: {
    $route: {
      handler (v) {
        let oPath = v.path.split("/");
        this.sysName = oPath[3];
        this.tableData = [];
        this.page = {
          pageNum: 1,
          pageSize: 30
        };
        this.total = 0;
        this.getList();
      },
      immediate: true
    }
  },
  methods: {
    getList (v = {}) {
      this.loading = true;
      warnHandleList(this.sysName, v, this.urlParameter(this.page)).then(res => {
        if (res.code === 200) {
          this.tableData = res.rows;
          this.total = res.total;
        }
        this.loading = false;
      });
    },
    handleWarn (v) {
      this.values = v;
      this.handleVisible = true;
    },
    handleQuery () {
      let query = {};
      if (this.queryForm.dateRange && this.queryForm.dateRange.length > 0) {
        query.startDate = this.queryForm.dateRange[0];
        query.endDate = this.queryForm.dateRange[1];
      }
      query = Object.assign(this.queryForm, query);
      this.getList(query);
    },
    successRequest () {
      this.getList(this.queryForm);
      this.handleVisible = false;
      this.values = {};
    }
  }
};
</script>

<style lang='scss' scoped>

</style>
