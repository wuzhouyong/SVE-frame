<template>
  <div class="warn-handle-div">
    <el-form label-width="110px" class="dataForm">
      <el-row :gutter="15">
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="企业名称">
            {{ hData[0].sourceName }}
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="任务编号">
            {{ hData[0].taskHeadId }}
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="任务状态">
            {{ taskstatus[hData[0].taskStatus] }}
          </el-form-item>
        </el-col>
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="任务标题">
            {{ hData[0].taskTitle }}
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="报警级别">
            {{ warnType[hData[0].alarmLevel] }}
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="任务发出时间">
            {{ hData[0].taskSentDate }}
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="开始时间">
            {{ hData[0].startDate }}
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="结束时间">
            {{ hData[0].endDate }}
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="监测点">
            {{ hData[0].checkpointDesc }}
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="异常类型">
            {{ abnortype[hData[0].abnorTaskType] }}
          </el-form-item>
        </el-col>
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="异常描述">
            {{ hData[0].abnorDesc }}
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
            {{ reasons[hData[0].abnorReason].desc }}
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="报告时间">
            {{ hData[0].dateReported || "-" }}
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="是否恢复正常">
            {{ hData[0].isReturnNormal ? "是" : "否" }}
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="恢复时间">
            {{ hData[0].returnNormalDate || "-" }}
          </el-form-item>
        </el-col>
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="处理情况说明">
            {{ hData[0].entProcessMemo || "-" }}
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <el-divider content-position="left">监管部门处理</el-divider>
    <el-form label-width="110px" class="dataForm">
      <el-row :gutter="15">
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="监管部门名称">
            {{ hData[0].adminUnitName }}
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="处理时间">
            {{ hData[0].adminUnitProcDate }}
          </el-form-item>
        </el-col>
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="处理情况说明">
            {{ hData[0].adminUnitProcDesc }}
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <el-divider content-position="left">退回任务</el-divider>
    <el-form :model="returnForm" ref="returnForm" label-width="110px" :rules="returnRules">
      <el-row :gutter="15">
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="退回原因" prop="returnReason">
            <el-input
              clearable
              v-model="returnForm.returnReason"
              maxlength="500"
              show-word-limit
              type="textarea"
              :rows="2"
              :style="{width: '100%'}"
            ></el-input>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <n-upload :taskHeadId="taskIds" ref="nUpload"></n-upload>
  </div>
</template>

<script>
import {omAbnorTaskTable, omAbnorTaskCityReturn} from "@/api/om/task";
import nUpload from "./upload";

export default {
  components: {nUpload},
  props: {
    hData: {
      type: Array,
      default: () => []
    },
    isAgree: {
      type: Boolean,
      default: false
    }
  },
  data () {
    return {
      returnForm: {
        returnUnit: null,
        returnReason: null
      },
      isMore: false,
      elsData: [],
      taskIds: [],
      returnRules: {
        returnReason: [
          {
            required: true,
            message: "退回原因不能为空",
            trigger: "blur",
          },
        ],
      },
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
        this.isMore = v.length === 1 ? false : true;
        let tskIds = [];
        v.forEach(i => {
          tskIds.push(i.taskHeadId);
        });
        this.taskIds = tskIds;
        if (tskIds.length > 0) {
          omAbnorTaskTable(tskIds.join(",")).then(res => {
            this.elsData = res.data;
          });
        }
      },
      immediate: true
    }
  },
  methods: {
    submitHandle () {
      this.$refs["returnForm"].validate((valid) => {
        if (!valid) return;
        omAbnorTaskCityReturn(this.taskIds.join(","), this.returnForm).then(res => {
          console.log(res);
        });
      });
    }
  }
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
