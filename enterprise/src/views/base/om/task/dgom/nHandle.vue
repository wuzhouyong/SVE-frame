<template>
  <div class="warn-handle-div">
    <el-form v-if="!isMore" label-width="110px" class="dataForm">
      <el-row :gutter="15">
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
      max-height="300px"
      style="width: 100%">
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
    <el-divider content-position="left" v-if="!isMore">任务处理</el-divider>
    <el-form :model="handleForm" ref="handleForm" label-width="110px" :rules="rules">
      <el-row :gutter="15">
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="处理人" prop="procUserName">
            <el-input
              size="mini"
              maxlength="20"
              show-word-limit
              v-model="handleForm.procUserName"
              :style="{width: '100%'}"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="异常原因" prop="abnorReason">
            <el-select v-model="handleForm.abnorReason" placeholder="异常原因" style="width:100%" size="mini">
              <el-option :label="i.desc" :value="i.id" v-for="(i,index) in reasons" :key="index"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="报告时间" prop="dateReported">
            <el-date-picker
              size="mini"
              v-model="handleForm.dateReported"
              value-format="yyyy-MM-dd"
              style="width:100%"
              type="date">
            </el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="是否恢复正常" prop="isReturnNormal">
            <el-select v-model="handleForm.isReturnNormal" style="width:100%" size="mini">
              <el-option label="是" :value=true></el-option>
              <el-option label="否" :value=false></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="恢复时间" prop="returnNormalDate">
            <el-date-picker
              size="mini"
              style="width:100%"
              v-model="handleForm.returnNormalDate"
              value-format="yyyy-MM-dd"
              type="date">
            </el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="处理情况说明" prop="entProcessMemo">
            <el-input
              clearable
              v-model="handleForm.entProcessMemo"
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
import {omAbnorTaskTable, omAbnoEnProc} from "@/api/dgom/task";
import nUpload from "./upload";

export default {
  components: {nUpload},
  props: {
    hData: {
      type: Array,
      default: () => []
    }
  },
  data () {
    return {
      handleForm: {},
      isMore: false,
      elsData: [],
      taskIds: [],
      rules: {
        procUserName: [
          {
            required: true,
            message: "处理人必填",
            trigger: "blur",
          },
        ],
        abnorReason: [
          {
            required: true,
            message: "异常原因必选",
            trigger: "blur",
          },
        ],
        entProcessMemo: [
          {
            required: true,
            message: "处理情况说明不能为空",
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
        this.isMore = v.length !== 1;
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
      this.$refs["handleForm"].validate((valid) => {
        if (!valid) return;
        if (this.$refs.nUpload.isFile()) {
          let oV = this.handleForm;
          oV.taskStatus = 2;
          omAbnoEnProc(oV, this.taskIds.join(",")).then(res => {
            if (res.code === 200) {
              this.msgSuccess("任务处理成功");
              this.$emit("completed");
            }
          });
        } else {
          this.msgError("附件必须上传");
        }
      });
    }
  }
};
</script>

<style lang='scss' scoped>
.warn-handle-div {
  /deep/ .dataForm {
    .el-form-item {
      margin-bottom: 0;
    }
  }
}
</style>
