<template>
  <div class="warn-handle-div">
    <el-form label-width="110px" class="dataForm">
      <el-row :gutter="15">
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="企业名称">
            {{ hData.sourceName }}
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="任务编号">
            {{ hData.taskHeadId }}
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="任务状态">
            {{ taskstatus[hData.taskStatus] }}
          </el-form-item>
        </el-col>
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="任务标题">
            {{ hData.taskTitle }}
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="报警级别">
            {{ warnType[hData.alarmLevel] }}
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="任务发出时间">
            {{ hData.taskSentDate }}
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="开始时间">
            {{ hData.startDate }}
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="结束时间">
            {{ hData.endDate }}
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="监测点">
            {{ hData.checkpointDesc }}
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="异常类型">
            {{ abnortype[hData.abnorTaskType] }}
          </el-form-item>
        </el-col>
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="异常描述">
            {{ hData.abnorDesc }}
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <el-table
      :data="elsData"
      border
      size="mini"
      max-height="400px"
      style="width: 100%;margin: 5px 0">
      <el-table-column
        prop="taskHeadId"
        label="任务编号"
        width="140">
      </el-table-column>
      <el-table-column
        prop="elementDesc"
        label="因子名称"
        show-overflow-tooltip
        min-width="140">
      </el-table-column>
      <el-table-column
        prop="dataDate"
        label="监测时间"
        width="120">
      </el-table-column>
      <el-table-column
        prop="amount"
        label="监测值"
        width="80">
      </el-table-column>
      <el-table-column
        prop="upperLimit"
        label="超标上限"
        width="80">
      </el-table-column>
      <el-table-column
        prop="lowerLimit"
        label="超标下限"
        width="80">
      </el-table-column>
    </el-table>
    <el-divider content-position="left">企业处理</el-divider>
    <el-form label-width="110px" class="dataForm">
      <el-row :gutter="15">
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="异常原因">
            {{ reasons[hData.abnorReason].desc }}
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="报告时间">
            {{ hData.dateReported || "-" }}
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="是否恢复正常">
            {{ hData.isReturnNormal ? "是" : "否" }}
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="恢复时间">
            {{ hData.returnNormalDate || "-" }}
          </el-form-item>
        </el-col>
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="处理情况说明">
            {{ hData.entProcessMemo || "-" }}
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <el-divider content-position="left">监管部门处理</el-divider>
    <el-form label-width="110px" class="dataForm">
      <el-row :gutter="15">
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="监管部门名称">
            {{ hData.adminUnitName || "-" }}
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="处理时间">
            {{ hData.adminUnitProcDate || "-" }}
          </el-form-item>
        </el-col>
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="处理情况说明">
            {{ hData.adminUnitProcDesc || "-" }}
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <n-upload :taskHeadId="taskHeadIds" ref="nUpload"></n-upload>
  </div>
</template>

<script>
import {omAbnorTaskTable} from "@/api/om/task";
import nUpload from "./upload";

export default {
  components: {nUpload},
  props: {
    hData: {
      type: Object,
      default: () => ({})
    },
  },
  data () {
    return {
      isMore: false,
      elsData: [],
      taskHeadIds: null,
      abnortype: ["", "超标", "零值", "静止", "缺失", "负值", "其他"],
      warnType: ["", "白色", "黄色", "红色"],
      taskstatus: ["", "待初核", "待处置", "退回初核", "退回处置", "已完成"],
      reason: ["", "断电", "通讯中断", "采集故障", "设备故障", "企业停产", "停用在线设备", "设备维修", "设备更换", "其他情况", "生产排放原因"],
      reasons: [
        {desc: "断电", id: 1},
        {desc: "通讯中断", id: 2},
        {desc: "采集故障", id: 3},
        {desc: "设备故障", id: 4},
        {desc: "企业停产", id: 5},
        {desc: "停用在线设备", id: 6},
        {desc: "设备维修", id: 7},
        {desc: "设备更换", id: 8},
        {desc: "其他情况", id: 9},
        {desc: "生产排放原因", id: 10}
      ],
    };
  },
  watch: {
    hData: {
      handler (v) {
        this.taskHeadIds = v.taskHeadId;
        omAbnorTaskTable(v.taskHeadId).then(res => {
          this.elsData = res.data;
        });
      },
      immediate: true
    }
  },
  methods: {}
};
</script>

<style lang='scss' scoped>
.warn-handle-div {
  /deep/ .dataForm {
    .el-form-item {
      margin-bottom: 0px;
    }
  }
}
</style>
