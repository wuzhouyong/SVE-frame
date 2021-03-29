<template>
  <!--  信息备案-->
  <div class="task-wk">
    <!-- 搜索表单 -->
    <el-form :model="queryForm" ref="queryForm" :inline="true">
      <el-form-item label="排污单位" prop="sourcename">
        <el-input
          v-model="queryForm.sourcename"
          placeholder="请输入排污单位"
          clearable
          size="mini"
          style="width: 180px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="排放类型" prop="checkpointtype">
        <el-radio-group v-model="queryForm.checkpointtype" size="mini" @change="handleQuery">
          <el-radio-button :label="1">水</el-radio-button>
          <el-radio-button :label="2">气</el-radio-button>
        </el-radio-group>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <el-table
      :data="tableData"
      @row-dblclick="tableClick"
      :highlight-current-row="true"
      height="100%"
      size="mini"
      border
      style="width: 100%">
      <el-table-column
        show-overflow-tooltip
        fixed
        prop="sourcename"
        label="排污单位"
        width="200">
      </el-table-column>
      <el-table-column
        show-overflow-tooltip
        label="排放类型">
        <template slot-scope="scope">
          {{ scope.row.checkpointtype === 1 ? '水' : '气' }}
        </template>
      </el-table-column>
      <el-table-column
        show-overflow-tooltip
        prop="sourceenvhead"
        label="环保联系人"
        width="90">
      </el-table-column>
      <el-table-column
        show-overflow-tooltip
        prop="sourceenvheadinfo"
        label="联系方式"
        width="110">
      </el-table-column>
      <el-table-column
        show-overflow-tooltip
        prop="opcompanyname"
        label="运营单位"
        width="200">
      </el-table-column>
      <el-table-column
        show-overflow-tooltip
        prop="opcompanyhead"
        label="单位负责人"
        width="90">
      </el-table-column>
      <el-table-column
        show-overflow-tooltip
        prop="opcompanyheadinfo"
        label="联系方式"
        width="110">
      </el-table-column>
      <el-table-column
        show-overflow-tooltip
        prop="checkpointname"
        label="排污口名称"
        width="200">
      </el-table-column>
      <el-table-column
        show-overflow-tooltip
        prop="oppersonname"
        label="运维人员姓名"
        width="110">
      </el-table-column>
      <el-table-column
        show-overflow-tooltip
        prop="oppersontel"
        label="联系方式"
        width="110">
      </el-table-column>
      <el-table-column
        label="运营开始时间"
        width="110">
        <template slot-scope="scope">
          {{ (scope.row.opstartdate && scope.row.opstartdate.substr(0, 10)) || '-' }}
        </template>
      </el-table-column>
      <el-table-column
        label="运营结束时间"
        width="110">
        <template slot-scope="scope">
          {{ (scope.row.openddate && scope.row.openddate.substr(0, 10)) || '-' }}
        </template>
      </el-table-column>
      <el-table-column
        show-overflow-tooltip
        prop="oplicensenum"
        label="运营证号"
        width="110">
      </el-table-column>
      <el-table-column
        show-overflow-tooltip
        prop="oplicissueunit"
        label="发证单位">
      </el-table-column>
      <el-table-column
        label="发证时间"
        width="110">
        <template slot-scope="scope">
          {{ (scope.row.oplicissuedate && scope.row.oplicissuedate.substr(0, 10)) || '-' }}
        </template>
      </el-table-column>
      <el-table-column
        show-overflow-tooltip
        prop="opnolicreason"
        label="无证明原因"
        width="90">`
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <pagination
      :total="total"
      :page.sync="page.pageNum"
      :limit.sync="page.pageSize"
      @pagination="getList(queryForm)"
    />
    <el-dialog
      :title="digTitle"
      :visible.sync="visible"
      custom-class="filing-style"
      v-if="visible"
      width="700px">
      <n-water v-if="checkpointtype===1" :waterMes="waterMes" :rows="rowsData"></n-water>
      <n-gas v-else :rows="rowsData" :gasMes="gasMes"></n-gas>
    </el-dialog>
  </div>
</template>

<script>
import {dgomFacility, dgomOpgDevice, dgomOpwdevice} from '@/api/om'
import nWater from './nWater'
import nGas from './nGas'

export default {
  components: {nWater, nGas},
  data() {
    return {
      queryForm: {
        sourcename: null,
        checkpointtype: null
      },
      tableData: [],
      // 分页
      page: {
        pageNum: 1,
        pageSize: 30
      },
      total: 0,
      visible: false,
      digTitle: null,
      checkpointtype: 1,
      waterMes: [],
      gasMes: [],
      rowsData: {}
    }
  },
  created() {
    this.getList();
  },
  methods: {
    getList(v = {}) {
      dgomFacility(v, this.urlParameter(this.page)).then(res => {
        this.tableData = res.rows;
        this.total = res.total;
      })
    },
    tableClick(row) {
      let oId = row.checkpointid;
      this.digTitle = row.sourcename;
      this.checkpointtype = row.checkpointtype;
      this.rowsData = row;
      if (this.checkpointtype === 1) {
        dgomOpwdevice(oId).then(res => {
          if (res.code === 200) {
            this.waterMes = res.data;
            this.visible = true;
          }
        })
      } else {
        dgomOpgDevice(oId).then(res => {
          if (res.code === 200) {
            this.gasMes = res.data;
            this.visible = true;
          }
        })
      }
    },
    handleQuery() {
      this.page.pageNum = 1;
      this.getList(this.queryForm);
    },
    resetQuery() {
      this.page.pageNum = 1;
      this.queryForm = {
        sourcename: null,
        checkpointtype: null
      };
      this.getList();
    }
  }
}
</script>

<style lang='scss' scoped>
.task-wk {
  /deep/ .filing-style {
    /deep/ .el-dialog__body {
      padding: 10px 5px;
    }
  }
}

</style>
