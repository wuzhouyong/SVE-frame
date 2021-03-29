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
      <el-form-item label="镇街" prop="omtownId">
        <el-select v-model="queryForm.omtownId" size="mini" placeholder="镇街" style="width:100px">
          <el-option :label="i.name" :value="i.id" v-for="i in townList" :key="i.id"></el-option>
        </el-select>
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
          type="success"
          icon="el-icon-check"
          size="mini"
          :disabled="multiple"
          @click="citySub(6)"
        >市局确认
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-close"
          size="mini"
          :disabled="multiple"
          @click="citySub(5)"
        >市局退回
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          :disabled="multiple"
        >
          <span v-if="multiple">批量下载</span>
          <a :href="allDown()" target="_blank" v-else>批量下载</a>
        </el-button>
      </el-col>
    </el-row>
    <el-table
      :data="tableData"
      @cell-dblclick="showDetails"
      @selection-change="handleSelectionChange"
      size="mini"
      class="taskTable"
      border
      height="100%"
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
        prop="townName"
        fixed="left"
        label="镇街"
        width="100">
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
        <template>
          市局待核实
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
        show-overflow-tooltip
        width="200">
      </el-table-column>
      <el-table-column
        prop="recityinfo"
        label="整改情况"
        show-overflow-tooltip
        width="200">
      </el-table-column>
      <el-table-column
        prop="situation"
        label="查处情况"
        show-overflow-tooltip
        width="200">
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <pagination
      v-if="total>0"
      :total="total"
      :page.sync="page.pageNum"
      :limit.sync="page.pageSize"
      @pagination="getList(queryForm)"
    />
    <el-dialog
      title="查看详情"
      :visible.sync="taskvisible"
      class="branchDialog"
      width="800px">

      <n-see :sData="clickData" ref="useSee" @completed="completed"></n-see>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="$refs.useSee.cityHandle(6)">市局确认</el-button>
        <el-button type="warning" @click="$refs.useSee.cityHandle(5)">市局退回</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { dgomTowns } from "@/api/om";
import { dgomSuperviseList, dgomCityHandle } from "@/api/om/task";
import NSee from "./nSee";

export default {
  components: { NSee },
  data() {
    return {
      tableData: [],
      //查询条件
      queryForm: {
        factoryName: null,
        checkpointName: null,
        omtownId: null,
        monitorTime: null,
        startDate: null,
        endDate: null,
        taskStatus: 4
      },
      searchData: {},
      clickData: {},
      townList: [],
      taskvisible: false,
      selectRows: [],
      // 分页
      page: {
        pageNum: 1,
        pageSize: 30
      },
      total: 0
    }
  },
  computed: {
    multiple() {
      return !this.selectRows.length;
    }
  },
  created() {
    //镇街
    dgomTowns().then(res => {
      if (res.code === 200) this.townList = res.data;
    });
    this.getList({ taskStatus: 4 });
  },
  methods: {
    getList(v = {}) {
      dgomSuperviseList(v, this.urlParameter(this.page)).then(res => {
        if (res.code === 200) {
          this.tableData = res.rows;
          this.total = res.total;
        }
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
        omtownId: null,
        monitorTime: null,
        startDate: null,
        endDate: null,
        taskStatus: 4
      }
      this.getList(this.queryForm);
    },
    handleSelectionChange(v) {
      this.selectRows = v;
    },
    showDetails(v) {
      this.clickData = v;
      this.taskvisible = true;
    },
    completed() {
      this.taskvisible = false;
      this.getList(this.queryForm);
    },
    citySub(v) {
      this.$confirm("是否确定此操作?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "info"
      })
        .then(() => {
          let oArr = [];
          this.selectRows.forEach(i => {
            let o = {
              suptaskId: i.suptaskId,
              taskStatus: v
            }
            oArr.push(o);
          })
          dgomCityHandle(oArr).then(res => {
            if (res.code === 200) {
              this.getList(this.queryForm);
            }
          })
        })
        .catch(function() {});

    },
    allDown() {
      let ids = this.selectRows.map(x => {return x.suptaskId});
      return `${process.env.VUE_APP_BASE_API}/com/ref_files?refType=dgom_branch_task&refId=${ids.join(",")}`;
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
