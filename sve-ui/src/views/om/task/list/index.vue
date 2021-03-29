<template>
  <div class="task-wk">
    <!-- 搜索表单 -->
    <el-form :model="queryForm" ref="queryForm" :inline="true" class="guocheng-serchForm">
      <el-form-item label="企业名称" prop="sourceId">
        <el-select v-model="queryForm.sourceId" placeholder="企业名称" style="width:220px" filterable size="mini">
          <el-option :label="i.zdatasourname" :value="i.zdatasourid" v-for="i in enterprise"
                     :key="i.zdatasourid"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="异常因子" prop="elementId">
        <el-select v-model="queryForm.elementId" placeholder="常用因子" style="width:160px" filterable size="mini">
          <el-option :label="i.zelementdesc" :value="i.zelementid" v-for="i in element"
                     :key="i.zelementid"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="是否已报告" prop="isReported">
        <el-radio-group v-model="queryForm.isReported" size="mini">
          <el-radio-button :label=false>否</el-radio-button>
          <el-radio-button :label=true>是</el-radio-button>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="是否已恢复" prop="isReturnNormal">
        <el-radio-group v-model="queryForm.isReturnNormal" size="mini">
          <el-radio-button :label=false>否</el-radio-button>
          <el-radio-button :label=true>是</el-radio-button>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="任务状态" prop="taskStatus">
        <el-select v-model="queryForm.taskStatus" placeholder="任务状态" style="width:130px" filterable size="mini">
          <el-option label="待初核" value="1"></el-option>
          <el-option label="待处置" value="2"></el-option>
          <el-option label="退回初核" value="3"></el-option>
          <el-option label="退回处置" value="4"></el-option>
          <el-option label="已完成" value="5"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="异常类型" prop="abnorTaskType">
        <el-select v-model="queryForm.abnorTaskType" placeholder="异常类型" style="width:130px" size="mini">
          <el-option label="超标" value="1"></el-option>
          <el-option label="零值" value="2"></el-option>
          <el-option label="静止" value="3"></el-option>
          <el-option label="缺失" value="4"></el-option>
          <el-option label="负值" value="5"></el-option>
          <el-option label="其他" value="6"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="日期范围" prop="date">
        <el-date-picker
          v-model="queryForm.date"
          value-format="yyyy-MM-dd"
          style="width:220px"
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
        <el-button type="warning" icon="el-icon-upload" style="margin-left:20px;" @click="exportTables">导出数据</el-button>
      </el-form-item>
    </el-form>
    <el-table
      :data="tableData"
      v-loading="loading"
      @row-dblclick="showDetails"
      element-loading-text="数据加载中.."
      highlight-current-row
      border
      size="mini"
      height="100%"
      style="width: 100%">
      <el-table-column
        prop="sourceName"
        label="企业名称"
        show-overflow-tooltip
        width="220">
      </el-table-column>
      <el-table-column
        prop="townName"
        label="镇街"
        width="80">
      </el-table-column>
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
      title="查看详情"
      custom-class="dgom-wran-dialog"
      :visible.sync="handleVisible"
      width="700px">
      <n-details :hData="selectRows" v-if="handleVisible" ref="nHandle"></n-details>
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
import {omAbnorTaskList, omAbnorTaskProcDetails, omAbnorTaskExport} from "@/api/om/task";
import {fun_date} from "@/utils/ruoyi";
import nDetails from "./details";
import {dgomElement, dgomEnterprise} from "@/api/om";
import {mapState} from "vuex";

export default {
  components: {nDetails},
  data () {
    return {
      page: {
        pageNum: 1,
        pageSize: 30
      },
      selectRows: {},
      total: 0,
      enterprise: [],
      element: [],
      queryForm: {
        sourceId: null,
        elementId: null,
        isReported: null,
        isReturnNormal: null,
        taskStatus: null,
        abnorTaskType: null,
        date: null
      },
      historyList: [],
      historyVisible: false,
      handleVisible: false,
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
    ...mapState({
      regions: state => state.user.regions
    })
  },
  watch: {
    regions: {
      handler (v) {
        let newVal = v.replace(/[']/g, "");
        if (newVal !== "4419" && newVal !== "0") {
          this.queryForm.townCode = newVal;
          this.townCode = newVal;
        } else {
          this.queryForm.townCode = null;
        }
        let oDate = this.parseTime(new Date()).substr(0, 10);
        this.queryForm.date = [fun_date(-7), oDate];
        this.getList(this.queryForm);
      },
      immediate: true
    }
  },
  created () {
    dgomEnterprise().then(res => this.enterprise = res.data);
    dgomElement().then(res => this.element = res.data);
    // this.getList(this.queryForm);
  },
  methods: {
    getList (v = {}) {
      this.loading = true;
      let oV = v;
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
        sourceId: null,
        elementId: null,
        isReported: null,
        isReturnNormal: null,
        taskStatus: null,
        abnorTaskType: null,
        townCode: this.townCode,
        date: null
      };
      let oDate = this.parseTime(new Date()).substr(0, 10);
      this.queryForm.date = [fun_date(-7), oDate];
      this.handleQuery();
    },
    showDetails (v) {
      this.selectRows = v;
      this.handleVisible = true;
    },
    sHistory (v) {
      omAbnorTaskProcDetails(v.taskHeadId).then(res => {
        this.historyList = res.data;
      });
      this.historyVisible = true;
    },
    exportTables () {
      this.$confirm("是否导出数据?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          let oV = this.queryForm;
          if (this.queryForm.date) {
            oV.startDate = this.queryForm.date[0];
            oV.endDate = this.queryForm.date[1];
          }
          return omAbnorTaskExport(oV);
        })
        .then((response) => {
          this.download(response.msg);
        })
        .catch(function () {
        });
    },
  }
};
</script>

<style lang='scss' scoped>
.task-wk {
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
}

.dgom-history {
  /deep/ .el-card__body {
    padding: 10px 15px;
  }

  /deep/ .el-form-item {
    margin-bottom: 5px;
  }
}
</style>
