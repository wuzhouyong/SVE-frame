<template>
  <div class="task-wk">
    <el-form :model="queryForm" ref="queryForm" :inline="true" class="guocheng-serchForm">
      <el-form-item label="镇街名称" prop="townCode">
        <el-select
          v-model="queryForm.townCode"
          placeholder="镇街名称"
          clearable
          :style="{width: '120px'}"
        >
          <el-option
            v-for="(item, index) in townCodeOptions"
            :key="index"
            :label="item.regionName"
            :value="item.regionCode"
            :disabled="item.disabled"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="选择日期" prop="oTime">
        <el-date-picker
          v-model="queryForm.oTime"
          style="width:260px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="异常等级" prop="warnLevel">
        <el-select
          v-model="queryForm.warnLevel"
          placeholder="异常等级"
          clearable
          :style="{width: '120px'}"
        >
          <el-option label="全部" value="-1"></el-option>
          <el-option label="一级异常" value="1"></el-option>
          <el-option label="二级异常" value="2"></el-option>
          <el-option label="三级异常" value="3"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        <el-button type="warning" icon="el-icon-upload" size="mini" @click="exportTown">导出</el-button>
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
      <el-table-column prop="townName" label="镇街" width="180"></el-table-column>
      <el-table-column label="异常等级" width="180">
        <template slot-scope="scope">
          {{ levelTypes[scope.row.warnLevel] }}
        </template>
      </el-table-column>
      <el-table-column prop="warnCount" label="产生异常数" width="180"></el-table-column>
      <el-table-column prop="factoryCount" label="企业未处理" width="180"></el-table-column>
      <el-table-column prop="dutyCount" label="核收未核实" width="180"></el-table-column>
      <el-table-column prop="substationCount" label="分局未处理" width="180"></el-table-column>
    </el-table>
    <div class="total_num">
      <p>共{{ total }}条数据</p>
    </div>
  </div>
</template>

<script>
import {GetRegionPower} from "@/api/composite/source";
import {abnormalTown, exportTown} from "@/api/currency";

export default {
  data() {
    return {
      loading: false,
      queryForm: {
        townCode: null,
        oTime: null,
        warnLevel: null
      },
      levelTypes: ["", "一级异常", "二级异常", "三级异常", "四级异常"],
      tableData: [],
      townCodeOptions: [],
      total: 0
    }
  },
  created() {
    GetRegionPower().then((res) => {
      this.townCodeOptions = res.data;
    });
    this.getlist();
  },
  methods: {
    getlist(v = {}) {
      this.loading = true
      abnormalTown('youyan', v).then(res => {
        if (res.code === 200) {
          this.tableData = res.data;
          this.total = res.data.length;
        }
        this.loading = false;
      })
    },
    handleQuery() {
      let oV = {};
      if (this.queryForm.oTime && this.queryForm.oTime.length > 0) {
        oV.beginTime = this.queryForm.oTime[0];
        oV.endTime = this.queryForm.oTime[1];
      }
      if (this.queryForm.warnLevel === "-1") {
        oV.warnLevel = null
      }
      oV = Object.assign(this.queryForm, oV);
      this.getlist(oV);
    },
    resetQuery() {
      this.queryForm = {
        townCode: null,
        oTime: null,
        warnLevel: null
      };
      this.getlist();
    },
    exportTown() {
      let oV = {};
      if (this.queryForm.oTime && this.queryForm.oTime.length > 0) {
        oV.beginTime = this.queryForm.oTime[0];
        oV.endTime = this.queryForm.oTime[1];
      }
      if (this.queryForm.warnLevel === "-1") {
        oV.warnLevel = null
      }
      oV = Object.assign(this.queryForm, oV);
      this.$confirm("是否导出数据?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          return exportTown('youyan', oV);
        })
        .then((response) => {
          this.download(response.msg);
        })
        .catch(function () {
        });
    }
  }
}
</script>

<style lang="scss" scoped>
.total_num {
  width: 100%;
  text-align: right;
  margin-top: 10px;
  padding-right: 10px;

  p {
    margin: 0;
    font-size: 14px;
    letter-spacing: 1px;
    font-width: 900;
  }
}
</style>
