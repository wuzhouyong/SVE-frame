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
      <!--      <el-form-item label="任务类型" prop="taskStatus">-->
      <!--        <el-select v-model="queryForm.taskStatus" placeholder="任务类型" style="width:130px" size="mini">-->
      <!--          <el-option label="自动任务" value="0"></el-option>-->
      <!--          <el-option label="手工任务" value="1"></el-option>-->
      <!--        </el-select>-->
      <!--      </el-form-item>-->
      <el-form-item label="任务状态" prop="taskStatus">
        <el-select v-model="queryForm.taskStatus" placeholder="任务状态" style="width:130px" size="mini">
          <el-option label="待处置" :value="2"></el-option>
          <el-option label="退回处置" :value="4"></el-option>
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
      </el-form-item>
      <el-form-item>
        <el-button
          type="success"
          icon="el-icon-check"
          size="mini"
          :disabled="!multiple"
          @click="agreeOpera(true)"
        >同意
        </el-button>
        <el-button
          type="danger"
          icon="el-icon-close"
          size="mini"
          :disabled="!multiple"
          @click="agreeOpera(false)"
        >退回
        </el-button>
      </el-form-item>
    </el-form>
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
    </el-table>
    <!-- 分页 -->
    <pagination
      :total="total"
      :page.sync="page.pageNum"
      :limit.sync="page.pageSize"
      @pagination="getList(queryForm)"
    />
    <el-dialog
      :title="title"
      custom-class="dgom-wran-dialog"
      :visible.sync="handleVisible"
      width="700px">
      <n-handle :hData="selectRows" :isAgree="isAgree" v-if="handleVisible" ref="nHandle"
                @completed="completed"></n-handle>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" size="mini" @click="$refs.nHandle.submitHandle()">提 交</el-button>
        <el-button size="mini" @click="handleVisible=false">取 消</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import {dgomElement, dgomEnterprise} from "@/api/om";
import {omAbnorTaskList} from "@/api/om/task";
import {fun_date} from "@/utils/ruoyi";
import nHandle from "./nHandle";
import {mapState} from "vuex";

export default {
  components: {nHandle},
  data () {
    return {
      queryForm: {
        date: null,
        taskStatus: 2
      },
      enterprise: [],
      element: [],
      tableData: [],
      loading: false,
      townCode: null,
      selectRows: [],
      page: {
        pageNum: 1,
        pageSize: 30
      },
      title: null,
      handleVisible: false,
      total: 0,
      isAgree: false,
      abnortype: ["", "超标", "零值", "静止", "缺失", "负值", "其他"],
      warnType: ["", "白色", "黄色", "红色"],
      taskstatus: ["", "待初核", "待处置", "退回初核", "退回处置", "已完成"],
      reason: ["", "断电", "通讯中断", "采集故障", "设备故障", "企业停产", "停用在线设备", "设备维修", "设备更换", "其他情况", "生产排放原因"],
    };
  },
  computed: {
    ...mapState({
      regions: state => state.user.regions
    }),
    multiple () {
      //可批量处理任务状态,异常类型,异常因子相同的任务
      if (this.selectRows.length === 1) {
        return new Set([2, 4]).has(this.selectRows[0].taskStatus);
      } else if (this.selectRows.length > 1) {
        if (this.selectRows.every(j => j.taskStatus !== 2 && j.taskStatus !== 4)) {
          return false;
        } else {
          let {abnorTaskType, taskStatus, elementDesc} = this.selectRows[0];
          let isMore = this.selectRows.every(i => {
            return i.abnorTaskType === abnorTaskType && i.taskStatus === taskStatus && i.elementDesc === elementDesc;
          });
          return isMore;
        }
      } else {
        return false;
      }
    }
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
        taskStatus: 2,
        abnorTaskType: null,
        townCode: this.townCode,
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
    agreeOpera (v) {
      this.isAgree = v;
      this.handleVisible = true;
      this.title = v ? "同意" : "退回";
    },
    completed () {
      this.handleVisible = false;
      this.getList(this.queryForm);
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
</style>
