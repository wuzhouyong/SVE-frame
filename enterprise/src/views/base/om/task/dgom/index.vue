<template>
  <div class="base-list">
    <!-- 搜索表单 -->
    <el-form :model="queryForm" ref="queryForm" :inline="true" class="search-form">
      <el-form-item label="任务状态" prop="taskStatus">
        <el-select v-model="queryForm.taskStatus" placeholder="任务状态" style="width:130px" filterable size="mini">
          <el-option label="全部" value="-1"></el-option>
          <el-option label="待初核" value="1"></el-option>
          <el-option label="待处置" value="2"></el-option>
          <el-option label="退回初核" value="3"></el-option>
          <el-option label="退回处置" value="4"></el-option>
          <el-option label="已完成" value="5"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="日期范围" prop="date">
        <el-date-picker
          v-model="queryForm.date"
          size="mini"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          :clearable="false">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <!-- 操作按钮 -->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" icon="el-icon-edit-outline" size="mini" :disabled="!multiple" @click="handleTask">
          任务处理
        </el-button>
      </el-col>
    </el-row>
    <el-table
      :data="tableData"
      v-loading="loading"
      @selection-change="handleSelectionChange"
      element-loading-text="数据加载中.."
      highlight-current-row
      border
      size="mini"
      height="100%"
      style="width: 100%">
      <el-table-column type="selection" width="55" align="center"></el-table-column>
      <el-table-column
        prop="taskHeadId"
        label="任务编号"
        width="140">
      </el-table-column>
      <el-table-column
        label="任务状态"
        width="90">
        <template slot-scope="scope">
          {{ taskstatus[scope.row.taskStatus] }}
        </template>
      </el-table-column>
      <el-table-column
        label="异常类型"
        width="80">
        <template slot-scope="scope">
          {{ abnortype[scope.row.abnorTaskType] }}
        </template>
      </el-table-column>
      <el-table-column
        prop="elementDesc"
        label="异常因子"
        width="130"
        show-overflow-tooltip>
      </el-table-column>
      <el-table-column
        prop="taskSentDate"
        label="任务发出时间"
        width="140">
      </el-table-column>
      <el-table-column
        prop="startDate"
        label="开始时间"
        width="140">
      </el-table-column>
      <el-table-column
        prop="endDate"
        label="结束时间"
        width="140">
      </el-table-column>
      <el-table-column
        prop="checkpointDesc"
        label="监测点"
        min-width="120"
        show-overflow-tooltip>
      </el-table-column>
      <el-table-column
        label="报警级别"
        align="center"
        width="80">
        <template slot-scope="scope">
          {{ warnType[scope.row.alarmLevel] }}
        </template>
      </el-table-column>
      <el-table-column
        prop="dateReported"
        label="报告时间"
        width="140">
      </el-table-column>
      <el-table-column
        label="是否恢复正常"
        width="110"
        align="center">
        <template slot-scope="scope">
          {{ scope.row.isReturnNormal ? "是" : "否" }}
        </template>
      </el-table-column>
      <el-table-column
        prop="returnNormalDate"
        label="恢复正常时间"
        width="140">
      </el-table-column>
      <el-table-column label="操作" fixed="right" width="80x" align="center">
        <template slot-scope="scope">
          <el-link type="primary" style="font-size:12px" :underline="false" @click="sHistory(scope.row)">操作记录</el-link>
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
    <el-dialog
      title="异常任务处理"
      custom-class="dgom-wran-dialog"
      :close-on-click-modal="false"
      :visible.sync="handleVisible"
      width="700px">
      <n-handle :hData="selectRows" v-if="handleVisible" ref="nHandle" @completed="completed"></n-handle>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" size="mini" @click="$refs.nHandle.submitHandle()">提 交</el-button>
        <el-button size="mini" @click="handleVisible=false">取 消</el-button>
      </span>
    </el-dialog>
    <el-dialog
      title="操作记录"
      custom-class="dgom-wran-dialog"
      :visible.sync="historyVisible"
      width="700px">
      <div class="dgom-history">
        <el-card class="box-card" v-for="item in historyList" :key="item.procDetailId" style="margin-bottom:5px;">
          <el-form label-width="80px">
            <el-form-item label="处理时间">{{ item.procDate }}</el-form-item>
            <el-form-item label="处理类型"> {{ userTypes[item.userType] }}</el-form-item>
            <el-form-item label="处理单位"> {{ item.procUnit }}</el-form-item>
            <el-form-item label="操作用户"> {{ item.procUserName }}</el-form-item>
            <el-form-item label="处理描述"> {{ item.procDesc }}</el-form-item>
          </el-form>
        </el-card>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {omAbnorTaskList, omAbnorTaskProcDetails} from "@/api/dgom/task";
import {fun_date} from "@/utils/ruoyi";
import nHandle from "./nHandle";

export default {
  components: {nHandle},
  data () {
    return {
      page: {
        pageNum: 1,
        pageSize: 30
      },
      total: 0,
      selectRows: [],
      queryForm: {
        taskStatus: "1",
        date: null
      },
      handleVisible: false,
      historyVisible: false,
      historyList: [],
      tableData: [],
      loading: false,
      userTypes: ["", "企业", "分局/大队", "市局"],
      abnortype: ["", "超标", "零值", "静止", "缺失", "负值", "其他"],
      warnType: ["", "白色", "黄色", "红色"],
      taskstatus: ["", "待初核", "待处置", "退回初核", "退回处置", "已完成"],
      reason: ["", "断电", "通讯中断", "采集故障", "设备故障", "企业停产", "停用在线设备", "设备维修", "设备更换", "其他情况", "生产排放原因"],
    };
  },
  computed: {
    multiple () {
      //可批量处理任务状态,异常类型,异常因子相同的任务
      if (this.selectRows.length === 1) {
        if (this.selectRows[0].taskStatus === 1 || this.selectRows[0].taskStatus === 3) {
          return true;
        } else {
          return false;
        }
      } else if (this.selectRows.length > 1) {
        if (this.selectRows.every(j => j.taskStatus !== 1 && j.taskStatus !== 3)) {
          return false;
        } else {
          let isMore = this.selectRows.every(i => {
            return i.abnorTaskType === this.selectRows[0].abnorTaskType && i.taskStatus === this.selectRows[0].taskStatus && i.elementDesc === this.selectRows[0].elementDesc;
          });
          return isMore;
        }
      } else {
        return false;
      }

    }
  },
  created () {
    let oDate = this.parseTime(new Date()).substr(0, 10);
    this.queryForm.date = [fun_date(-7), oDate];
    this.getList(this.queryForm);
  },
  methods: {
    getList (v = {}) {
      this.loading = true;
      let oV = {};
      if (v.taskStatus !== "-1") {
        oV = v;
      }
      if (v.date) {
        oV.startDate = v.date[0];
        oV.endDate = v.date[1];
      }
      omAbnorTaskList(oV, this.urlParameter(this.page)).then(res => {
        this.tableData = res.rows;
        this.total = res.total;
        this.loading = false;
      });
    },
    handleQuery () {
      this.page.pageNum = 1;
      this.getList(this.queryForm);
    },
    resetQuery () {
      this.queryForm = {
        taskStatus: "1",
        date: null
      };
      let oDate = this.parseTime(new Date()).substr(0, 10);
      this.queryForm.date = [fun_date(-7), oDate];
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange (selection) {
      this.selectRows = selection;
    },
    handleTask () {
      this.handleVisible = true;
    },
    completed () {
      this.handleVisible = false;
      this.getList(this.queryForm);
    },
    sHistory (v) {
      console.log(v);
      omAbnorTaskProcDetails(v.taskHeadId).then(res => {
        this.historyList = res.data;
      });
      this.historyVisible = true;
    },
  }
};
</script>

<style lang='scss' scoped>
.base-list {
  /deep/ .dgom-wran-dialog {
    max-height: calc(100% - 12vh);
    height: auto;
    display: flex;
    flex-direction: column;
    overflow: hidden;

    .el-dialog__body {
      padding: 10px;
      flex: 1;
      overflow-y: auto;
    }
  }

  /deep/ .dgom-wran-dialog {
    max-height: calc(100% - 12vh);
    height: auto;
    display: flex;
    flex-direction: column;
    overflow: hidden;

    .el-dialog__body {
      padding: 10px;
      flex: 1;
      overflow-y: auto;
    }
  }
  .dgom-history {
    /deep/ .el-card__body {
      padding: 10px 15px;
    }

    /deep/ .el-form-item {
      margin-bottom: 5px;
    }
  }
}

</style>
