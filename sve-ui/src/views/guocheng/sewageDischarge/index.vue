<template>
  <div class="task-wk">
    <el-form :model="queryForm" ref="queryForm" :inline="true" class="guocheng-serchForm">
      <el-form-item label="污染源" prop="fullName">
        <el-input
          v-model="queryForm.fullName"
          placeholder="污染源"
          clearable
          size="mini"
          style="width: 150px"
        />
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
      <el-form-item label="是否超标" prop="isExceed">
        <el-radio-group v-model="queryForm.isExceed">
          <el-radio-button label="1">是</el-radio-button>
          <el-radio-button label="0">否</el-radio-button>
        </el-radio-group>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        <el-button type="warning" icon="el-icon-upload" size="mini" @click="exportEn">导出</el-button>
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
      <el-table-column prop="fullName" label="污染源" show-overflow-tooltip width="240"></el-table-column>
      <el-table-column prop="townName" label="所属镇街" width="120"></el-table-column>
      <el-table-column prop="unitName" label="调研单位" width="200" show-overflow-tooltip></el-table-column>
      <el-table-column prop="constructionUnit" label="施工单位" width="200" show-overflow-tooltip></el-table-column>
      <el-table-column prop="dailyAmt" label="日允环评排放(m³)" width="180"></el-table-column>
      <el-table-column prop="totalAmt" label="	总排放量(m³)" width="180"></el-table-column>
      <el-table-column label="是否超标" width="80" align="center">
        <template slot-scope="scope">
          {{ scope.row.isExceed === 1 ? "是" : "否" }}
        </template>
      </el-table-column>
    </el-table>
    <div class="total_num">
      <p>共{{ total }}条数据</p>
    </div>
  </div>
</template>

<script>
import { GetRegionPower } from "@/api/composite/source";
import { abnormalSewage, exportSwage } from "@/api/guocheng";
import { fun_date } from "@/utils/ruoyi"

export default {
  data() {
    return {
      loading: false,
      queryForm: {
        fullName: null,
        oTime: null,
        isExceed: null
      },
      tableData: [],
      total: 0,
      townCodeOptions: []
    }
  },
  created() {
    let oDate = this.parseTime(new Date()).substr(0, 10);
    this.queryForm.oTime = [fun_date(-5), oDate];
    GetRegionPower().then((res) => {
      this.townCodeOptions = res.data;
    });
    this.handleQuery();
  },
  methods: {
    getlist(v = {}) {
      this.loading = true
      abnormalSewage(v).then(res => {
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
      oV = Object.assign(this.queryForm, oV);
      this.getlist(oV);
    },
    resetQuery() {
      let oDate = this.parseTime(new Date()).substr(0, 10);
      this.queryForm = {
        fullName: null,
        oTime: [fun_date(-5), oDate],
        isExceed: null
      }
      this.handleQuery();
    },
    exportEn() {
      let oV = {};
      if (this.queryForm.oTime && this.queryForm.oTime.length > 0) {
        oV.beginTime = this.queryForm.oTime[0];
        oV.endTime = this.queryForm.oTime[1];
      }
      oV = Object.assign(this.queryForm, oV);
      this.$confirm("是否导出数据?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          return exportSwage(oV);
        })
        .then((response) => {
          this.download(response.msg);
        })
        .catch(function() {});
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
