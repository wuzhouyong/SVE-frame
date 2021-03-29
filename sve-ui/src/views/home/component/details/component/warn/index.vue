<template>
  <div class="home-warn-wk">
    <!-- 搜索表单 -->
    <el-form :model="queryForm" ref="queryForm" :inline="true" class="qixiu-serchForm">
      <el-form-item label="选择日期" prop="timeSection">
        <el-date-picker
          v-model="queryForm.timeSection"
          style="width:220px"
          value-format="yyyy-MM-dd"
          :clearable="false"
          type="daterange"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="处理状态" prop="procState">
        <el-select
          v-model="queryForm.procState"
          clearable
          placeholder="请选择处理状态"
          style="width:140px"
        >
          <el-option
            :label="i.dictLabel"
            :value="i.dictValue"
            v-for="i in procStates"
            :key="i.dictValue"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="异常级别" prop="warnLevel">
        <el-select
          v-model="queryForm.warnLevel"
          clearable
          placeholder="请选择异常级别"
          style="width:140px"
        >
          <el-option
            :label="i.dictLabel"
            :value="i.dictValue"
            v-for="i in warnLevels"
            :key="i.dictValue"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="异常类型" prop="warnType">
        <el-select v-model="queryForm.warnType" clearable placeholder="请选择异常类型" style="width:140px">
          <el-option
            :label="i.dictLabel"
            :value="i.dictValue"
            v-for="i in warnTypes"
            :key="i.dictValue"
          ></el-option>
        </el-select>
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
      style="width: 100%;flex:1"
      height="100%"
      ref="oTable"
      border
    >
      <el-table-column prop="warnLevelName" label="异常级别" width="100"></el-table-column>
      <el-table-column prop="procStateName" label="处理状态" width="100"></el-table-column>
      <el-table-column label="异常时间" width="170">
        <template slot-scope="scope">{{ scope.row.startDate }} ~ {{ scope.row.endDate }}</template>
      </el-table-column>
      <el-table-column prop="warnTypeName" label="异常类型" width="160"></el-table-column>
      <el-table-column prop="warnDesc" label="异常描述" show-overflow-tooltip></el-table-column>
    </el-table>
  </div>
</template>
<script>
import {warnList} from "@/api/warn_handle";

export default {
  props: [
    "warnTypes",
    "warnLevels",
    "procStates",
    "oId",
    "isMenu",
    "platFormPath",
  ],
  data () {
    return {
      //查询表单
      queryForm: {
        timeSection: null,
        procState: null,
        warnLevel: null,
        warnType: null,
      },
      loading: true,
      tableData: [],
      // 查询参数
      page: {
        pageNum: 1,
        pageSize: 200,
      },
      total: 0
    };
  },
  watch: {
    isMenu: {
      handler (v) {
        if (v) {
          this.queryForm.sourceId = this.oId;
          this.getList(this.queryForm);
        }
      },
    },
  },
  methods: {
    getList (v = {}) {
      this.loading = true;
      warnList(v, this.platFormPath, this.urlParameter(this.page)).then(
        (res) => {
          if (res.code === 200) {
            this.tableData = res.rows;
            this.total = res.total;
          }
          this.loading = false;
        }
      );

    },
    handleQuery () {
      if (this.queryForm.timeSection) {
        this.queryForm.startDate = this.queryForm.timeSection[0];
        this.queryForm.endDate = this.queryForm.timeSection[1];
      }
      this.getList(this.queryForm);
    },
    resetQuery () {
      this.queryForm = {
        timeSection: null,
        procState: null,
        warnLevel: null,
        warnType: null,
        sourceId: this.oId
      };
      this.handleQuery();
    },
  },
};
</script>
<style lang="scss">
.home-warn-wk {
  width: 100%;
  height: 100%;
  overflow: hidden;
  display: flex;
  flex-direction: column;
}
</style>
