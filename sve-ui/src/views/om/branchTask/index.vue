<template>
  <div class="task-wk">
    <!-- 搜索表单 -->
    <el-form :model="queryForm" ref="queryForm" :inline="true">
      <el-form-item label="企业名称" prop="factoryName">
        <el-input
          v-model="queryForm.factoryName"
          placeholder="企业名称"
          clearable
          style="width:180px"
        >
        </el-input>
      </el-form-item>
      <el-form-item label="监控点" prop="checkpointName">
        <el-input
          v-model="queryForm.checkpointName"
          placeholder="监控点名称"
          clearable
          style="width:120px"
        ></el-input>
      </el-form-item>
      <el-form-item label="监测时间" prop="monitorTime">
        <el-date-picker
          v-model="queryForm.monitorTime"
          :clearable="false"
          style="width:220px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>

      </el-form-item>
      <el-form-item label="任务状态" prop="taskStatus">
        <el-select v-model="queryForm.taskStatus" size="mini" placeholder="任务状态" style="width:110px">
          <el-option label="分局待处理" value="2"></el-option>
          <el-option label="超时未处理" value="3"></el-option>
          <el-option label="市局退回" value="5"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="开始时间" prop="startDate">
        <el-date-picker
          type="date"
          placeholder="选择开始时间"
          v-model="queryForm.startDate"
          value-format="yyyy-MM-dd"
          style="width:130px"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="结束时间" prop="endDate">
        <el-date-picker
          type="date"
          placeholder="选择结束时间"
          v-model="queryForm.endDate"
          value-format="yyyy-MM-dd"
          style="width:130px"
        ></el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <!-- 操作按钮 -->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          :disabled="multiple"
          @click="proHandle"
        >分局处理
        </el-button>
      </el-col>
    </el-row>
    <el-table
      :data="tableData"
      size="mini"
      class="taskTable"
      v-loading="loading"
      border
      height="100%"
      @selection-change="handleSelectionChange"
      style="width: 100%">
      <el-table-column
        align="center"
        type="selection"
        width="55">
      </el-table-column>
      <el-table-column
        label="督办类型"
        prop="taskType"
        align="center"
        fixed="left"
        width="90">
      </el-table-column>
      <el-table-column
        prop="factoryName"
        fixed="left"
        label="企业名称"
        show-overflow-tooltip
        width="250">
      </el-table-column>
      <el-table-column
        prop="checkpointName"
        label="监控点名称"
        show-overflow-tooltip
        width="140">
      </el-table-column>
      <el-table-column
        prop="elementName"
        width="140"
        show-overflow-tooltip
        label="监控因子">
      </el-table-column>
      <el-table-column
        prop="overDays"
        label="超标天数">
      </el-table-column>
      <el-table-column
        prop="monitorTime"
        label="监测时间"
        width="100">
      </el-table-column>
      <el-table-column
        prop="amount"
        label="排放浓度">
      </el-table-column>
      <el-table-column
        prop="standardval"
        label="排放标准">
      </el-table-column>
      <el-table-column
        prop="overTimes"
        label="超标倍数">
      </el-table-column>
      <el-table-column
        prop="taskStatus"
        width="90"
        label="任务状态">
        <template slot-scope="scope">
          {{ statusType[scope.row.taskStatus] }}
        </template>
      </el-table-column>
      <el-table-column
        prop="startDate"
        label="任务开始时间"
        width="100">
      </el-table-column>
      <el-table-column
        prop="endDate"
        label="任务结束时间"
        width="100">
      </el-table-column>
      <el-table-column
        label="超标是否属实"
        width="100">
        <template slot-scope="scope">
          {{ scope.row.istrue === 0 ? "否" : scope.row.istrue === 1 ? "是" : "-" }}
        </template>
      </el-table-column>
      <el-table-column
        prop="reason"
        label="超标原因"
        width="200">
      </el-table-column>
      <el-table-column
        prop="recityinfo"
        label="整改情况"
        width="200">
      </el-table-column>
      <el-table-column
        prop="situation"
        label="查处情况"
        width="200">
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <pagination
      v-if="total>0"
      :total="total"
      :page.sync="page.pageNum"
      :limit.sync="page.pageSize"
      @pagination="getList(searchData)"
    />
    <el-dialog
      :title="title"
      :visible.sync="taskvisible"
      :close-on-click-modal="false"
      class="branchDialog"
      width="800px">
      <n-form :sData="selectRows" ref="useForm" v-if="taskvisible" @completed="completed"></n-form>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="$refs.useForm.handleTask()">提 交</el-button>
        <el-button @click="taskvisible = false">取 消</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { dgomTowns } from "@/api/om";
import { dgomDivideList } from "@/api/om/task"
import NForm from "./nForm"

export default {
  components: { NForm },
  data() {
    return {
      tableData: [],
      queryForm: {
        factoryName: null,
        checkpointName: null,
        monitorTime: null,
        startDate: null,
        endDate: null,
        taskStatus: null
      },
      loading: false,
      sourceList: [],
      pointList: [],
      townList: [],
      selectRow: {},
      selectRows: [],
      // 分页
      page: {
        pageNum: 1,
        pageSize: 30
      },
      total: 0,
      statusType: ["", "草稿", "分局待处理", "超时未处理", "市局待核实", "市局退回", "已完成"],
      //处理表单
      title: "分局处理",
      taskvisible: false,
      //查询条件
      searchData: {}

    }
  },
  computed: {
    multiple() {
      return !this.selectRows.length;
    }
  },
  created() {
    this.getList();
  },
  methods: {
    getList(v = {}) {
      this.loading = true;
      dgomDivideList(v, this.urlParameter(this.page)).then(res => {
        if (res.code === 200) {
          this.tableData = res.rows;
          this.total = res.total;
        }
        this.loading = false;
      })
    },
    handleQuery() {
      let oVal = {};
      let newForm = JSON.parse(JSON.stringify(this.queryForm));
      oVal = Object.assign(newForm, oVal);
      if (newForm.monitorTime) {
        oVal.beginTime = newForm.monitorTime[0];
        oVal.endTime = newForm.monitorTime[1];
        delete oVal.monitorTime;
      }
      this.searchData = oVal;
      this.getList(oVal);
    },
    resetQuery() {
      this.queryForm = {
        factoryName: null,
        checkpointName: null,
        monitorTime: null,
        startDate: null,
        endDate: null,
        taskStatus: null
      }
      this.searchData = {};
      this.getList();
    },
    //分局处理
    proHandle() {
      this.taskvisible = true;
    },
    handleSelectionChange(v) {
      this.selectRows = v;
      this.selectRow = v[0];
    },
    completed() {
      this.taskvisible = false;
      this.getList();
    }
  }
}
</script>

<style lang="scss" scoped>
.branchDialog {
  /deep/ .el-dialog {
    height: calc(100% - 12vh);
    overflow: hidden;
    display: flex;
    flex-direction: column;

    .el-dialog__body {
      padding: 5px 10px;
      flex: 1;
      overflow-y: auto;
    }
  }
}
</style>
