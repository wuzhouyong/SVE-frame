<template>
  <div class="task-wk">
    <!-- 搜索表单 -->
    <el-form :model="queryForm" ref="queryForm" :inline="true">
      <el-form-item label="企业名称" prop="zdatasourname">
        <el-input
          v-model="queryForm.zdatasourname"
          placeholder="请输入企业名称"
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
    <!-- 表格 -->
    <el-table
      v-loading="loading"
      :data="tableData"
      style="width: 100%;flex:1;"
      height="100%"
      border
    >
      <el-table-column prop="zdatasourname" label="企业名称" show-overflow-tooltip></el-table-column>
      <el-table-column prop="zoperationdate" label="日期" width="180"></el-table-column>
      <el-table-column label="台账类别" width="180">
        <template slot-scope="scope">
          {{ types[scope.row.zoperationtype] }}
        </template>
      </el-table-column>
      <el-table-column prop="zdatadevdesc" label="设备名称" width="180"></el-table-column>
      <el-table-column prop="zequipmentdesc" label="规格型号" width="180"></el-table-column>
      <el-table-column prop="zdevno" label="设备编号" width="180"></el-table-column>
      <el-table-column prop="zmaintenancecorp" label="维护单位" width="180"></el-table-column>
      <el-table-column prop="zinstallplace" label="安装地点" width="180"></el-table-column>
      <el-table-column prop="zmaintenancestaff" label="维护保养人" width="180"></el-table-column>
    </el-table>
    <!-- 分页 -->
    <pagination
      :total="total"
      :page.sync="page.pageNum"
      :limit.sync="page.pageSize"
      @pagination="getList(queryForm)"
    />
  </div>
</template>

<script>
import {dgomFacilityRecord} from '@/api/om'

export default {
  data() {
    return {
      queryForm: {
        zdatasourname: null
      },
      loading: false,
      tableData: [],
      // 分页
      page: {
        pageNum: 1,
        pageSize: 30
      },
      total: 0,
      types: ['烟气日常巡检维护记录', '烟气零漂（跨漂）校准记录', '烟气校验测试记录', '污水日常巡检维护记录', '污水设备校准记录', '污水校验测试记录', '易耗品', '流量计强检']
    }
  },
  created() {
    this.getList();
  },
  methods: {
    getList(v = {}) {
      dgomFacilityRecord(v, this.urlParameter(this.page)).then(res => {
        if (res.code === 200) {
          this.tableData = res.rows;
          this.total = res.total;
        }
      })
    },
    handleQuery() {
      this.page.pageNum = 1;
      this.getList(this.queryForm);
    },
    resetQuery() {
      this.page.pageNum = 1;
      this.queryForm = {
        zdatasourname: null
      }
      this.getList();
    }
  }
}
</script>

<style lang='scss' scoped>

</style>
