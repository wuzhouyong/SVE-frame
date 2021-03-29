<template>
  <div class="task-wk">
    <!-- 搜索表单 -->
    <el-form :model="queryForm" ref="queryForm" :inline="true" class="guocheng-serchForm">
      <el-form-item label="污染源" prop="sourceId">
        <el-select v-model="queryForm.sourceId" placeholder="请选择污染源" style="width:300px" filterable size="mini"
                   @change="sourceChange">
          <el-option :label="i.zdatasourname" :value="i.zdatasourid" v-for="i in sourceList"
                     :key="i.zdatasourid"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="排放口" prop="checkpointid" v-if="pointList.length>0">
        <el-select v-model="queryForm.checkpointid" placeholder="请选择排放口" style="width:200px" size="mini">
          <el-option :label="i.zcheckpointdesc" :value="i.zcheckpointid" v-for="i in pointList"
                     :key="i.zcheckpointid"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="设备名称" prop="datadevdesc">
        <el-input
          v-model="queryForm.datadevdesc"
          placeholder="设备名称"
          clearable
          size="mini"
          style="width: 180px"
        />
      </el-form-item>
      <el-form-item label="设备状态" prop="devstatus">
        <el-select
          v-model="queryForm.devstatus"
          placeholder="设备状态"
          clearable
          :style="{width: '120px'}"
        >
          <el-option
            label="未联网"
            value="1"
          ></el-option>
          <el-option
            label="已联网"
            value="2"
          ></el-option>
          <el-option
            label="已验收"
            value="3"
          ></el-option>
          <el-option
            label="停用"
            value="4"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="任务类型" prop="checkpointtype">
        <el-select
          v-model="queryForm.checkpointtype"
          placeholder="任务类型"
          clearable
          :style="{width: '120px'}"
        >
          <el-option
            label="水重点排污"
            value="1"
          ></el-option>
          <el-option
            label="气重点排污"
            value="2"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <el-table
      ref="filterTable"
      height="100%"
      :data="tableData"
      border
      size="mini"
      style="width: 100%">
      <el-table-column
        prop="datasourname"
        label="污染源"
        width="250">
      </el-table-column>
      <el-table-column
        prop="checkpointdesc"
        label="排放口名称"
        width="250">
      </el-table-column>
      <el-table-column
        prop="datadevdesc"
        label="设备名称"
        width="180">
      </el-table-column>
      <el-table-column
        prop="equipmentdesc"
        label="品牌"
        width="180">
      </el-table-column>
      <el-table-column
        label="设备状态"
        width="100">
        <template slot-scope="scope">
          {{
            devstatusType[scope.row.devstatus]
          }}
        </template>
      </el-table-column>
      <el-table-column
        label="任务类型"
        width="140"
        align="center"
        :filters="[{ text: '水重点排污单位', value: 1 }, { text: '气重点排污单位', value: 2 }]"
        :filter-method="filterTag"
        filter-placement="bottom-end">
        <template slot-scope="scope">
          {{ scope.row.checkpointtype === 1 ? "水重点排污单位" : "气重点排污单位" }}
        </template>
      </el-table-column>
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
import { dgomDevice, dgomEnterprise, dgomCheckPoint } from "@/api/om";

export default {
  data: () => ({
    tableData: [],
    page: {
      pageNum: 1,
      pageSize: 30
    },
    total: 0,
    devstatusType: ["", "未联网", "已联网", "已验收", "停用"],
    queryForm: {
      sourceId: undefined,
      checkpointid: undefined,
      datadevdesc: undefined,
      devstatus: undefined,
      checkpointtype: undefined
    },
    pointList: [],
    sourceList: []
  }),
  created() {
    this.getList();
    this.getEnterprise();
  },
  methods: {
    getList(v = {}) {
      dgomDevice(v, this.urlParameter(this.page)).then(res => {
        if (res.code === 200) {
          this.tableData = res.rows;
          this.total = res.total;
        }
      });
    },
    //获取企业列表
    getEnterprise() {
      dgomEnterprise().then(res => {
        if (res.code === 200) {
          this.sourceList = res.data;
        }
      })
    },
    //获取排放口列表
    getEmission(v) {
      dgomCheckPoint(v).then(res => {
        if (res.code === 200) {
          this.pointList = res.data;
        }
      })
    },
    //选中企业
    sourceChange(v) {
      this.getEmission(v);
    },
    clearFilter() {
      this.$refs.filterTable.clearFilter();
    },
    filterTag(value, row) {
      return row.checkpointtype === value;
    },
    handleQuery() {
      this.page.pageNum = 1;
      this.getList(this.queryForm);
    },
    resetQuery() {
      this.resetForm("queryForm");
      this.queryForm = {
        sourceId: undefined,
        checkpointid: undefined,
        datadevdesc: undefined,
        devstatus: undefined,
        checkpointtype: undefined
      };
      this.handleQuery();
    }
  }
};
</script>

<style lang="scss">

</style>
