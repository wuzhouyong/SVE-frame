<template>
  <div class="task-wk">
    <!-- 搜索表单 -->
    <el-form :model="queryForm" ref="queryForm" :inline="true" class="outwater-serchForm">
      <el-form-item label="处置企业" prop="proc_source_id">
        <el-select
          v-model="queryForm.proc_source_id"
          placeholder="请选择处置企业"
          style="width:250px"
          size="mini"
        >
          <el-option
            :label="i.fullName"
            :value="i.sourceId"
            v-for="i in sourceList"
            :key="i.sourceId"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="联单号" prop="bill_no">
        <el-input v-model="queryForm.bill_no" placeholder="联单号"></el-input>
      </el-form-item>
      <el-form-item label="联单状态" prop="bill_status">
        <el-select
          v-model="queryForm.bill_status"
          placeholder="联单状态"
          clearable
          :style="{width: '160px'}"
        >
          <el-option
            v-for="i in joinListType"
            :key="i.dictValue"
            :label="i.dictLabel"
            :value="i.dictValue"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="时间" prop="oTime">
        <el-date-picker
          v-model="queryForm.oTime"
          :clearable="false"
          style="width:280px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button type="info" icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <!-- 表格 -->
    <el-table
      v-loading="loading"
      :data="tableData"
      style="width: 100%;flex:1;"
      @row-dblclick="rowClick"
      ref="oTable"
      size="mini"
      height="100%"
      border
    >
      <el-table-column prop="bill_no" label="环保联单号" width="180"></el-table-column>
      <el-table-column prop="source_name" label="产生单位" width="220" show-overflow-tooltip></el-table-column>
      <el-table-column prop="proc_source_name" label="接收单位" width="180" show-overflow-tooltip></el-table-column>
      <el-table-column label="联单状态" width="80">
        <template slot-scope="scope">
          {{ billStatus[scope.row.bill_status] }}
        </template>
      </el-table-column>
      <el-table-column prop="wastewater_name" label="废水名称" width="180" show-overflow-tooltip></el-table-column>
      <el-table-column prop="wastewater_type" label="废水类别" width="140" show-overflow-tooltip></el-table-column>
      <el-table-column prop="wastewater_amt" label="确认数量(吨)" width="100"></el-table-column>
      <el-table-column prop="factinamt" label="计算数量(吨)" width="100"></el-table-column>
      <el-table-column prop="wastewater_nature" label="废水特性" width="100"></el-table-column>
      <el-table-column prop="trans_date" label="转移时间" width="180"></el-table-column>
      <el-table-column prop="carrier1_platenum" label="车牌号" width="120"></el-table-column>
      <el-table-column prop="carrier1_name" label="司机" width="120"></el-table-column>
      <el-table-column prop="trans_destination" label="运输终点" width="240" show-overflow-tooltip></el-table-column>
    </el-table>
    <pagination
      :total="total"
      :page.sync="page.pageNum"
      :limit.sync="page.pageSize"
      @pagination="getList(queryForm)"
    />
    <!-- 联单详情 -->
    <el-dialog title="联单明细" :visible.sync="visible" width="800px">
      <n-details :clickData="clickData"></n-details>
    </el-dialog>
  </div>
</template>

<script>
import {lxBillinfoList, tankcarEnterprise} from '@/api/lingxing'
import nDetails from './nDetails'

export default {
  components: {nDetails},
  data() {
    return {
      tableData: [],
      loading: false,
      queryForm: {
        proc_source_id: null,
        bill_no: null,
        bill_status: null,
        oTime: null
      },
      billStatus: ["", "预联单", "进行中", "已完成", "已作废"],
      visible: false,
      clickData: {},
      joinListType: [
        {
          dictValue: 1,
          dictLabel: "预联单"
        },
        {
          dictValue: 2,
          dictLabel: "进行中"
        },
        {
          dictValue: 3,
          dictLabel: "已完成"
        },
        {
          dictValue: 4,
          dictLabel: "已作废"
        }],
      sourceList: [],
      // 分页
      page: {
        pageNum: 1,
        pageSize: 30
      },
      total: 0,
    }
  },
  created() {
    this.getSourceList();
    this.getList();
  },
  methods: {
    getList(v = {}) {
      this.loading = true;
      lxBillinfoList(v, this.urlParameter(this.page)).then(res => {
        if (res.code === 200) {
          this.tableData = res.rows;
          this.total = res.total;
        }
        this.loading = false;
      })
    },
    rowClick(row) {
      this.clickData = row;
      this.visible = true;
    },
    //处置企业
    getSourceList() {
      tankcarEnterprise().then((res) => {
        if (res.code === 200) {
          this.sourceList = res.data || res.rows;
        }
      });
    },
    handleQuery() {
      this.page.pageNum = 1;
      if (this.queryForm.oTime && this.queryForm.oTime.length > 0) {
        this.queryForm.beginTime = this.queryForm.oTime[0];
        this.queryForm.endTime = this.queryForm.oTime[1];
      }
      this.getList(this.queryForm);
    },
    resetQuery() {
      this.page.pageNum = 1;
      this.queryForm = {
        proc_source_id: null,
        bill_no: null,
        bill_status: null,
        oTime: null
      };
      this.getList();
    }
  }
}
</script>

<style scoped>

</style>
