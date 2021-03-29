<template>
  <div v-loading="loading" element-loading-text="数据加载中">
    <div class="examine_details">
      <el-form label-width="150px">
        <el-form-item label="调整事项：">{{ resData.adjustItem === "0" ? "申请不安装" : "申请暂缓" }}</el-form-item>
        <el-form-item :label="resData.adjustItem==='0'?'不安装原因：':'暂缓原因：'">{{ reason }}</el-form-item>
        <el-form-item v-if="resData.adjustItem==='1'" label="暂缓至：">{{ resData.suspendDate }}</el-form-item>
        <el-form-item label="其他原因：">{{ resData.otherReason }}</el-form-item>
        <el-form-item label="备注：">{{ resData.memo }}</el-form-item>
      </el-form>
    </div>
    <el-divider content-position="left">附件列表</el-divider>
    <el-table :data="fileList" stripe style="width: 100%" height="160px">
      <el-table-column prop="showName" label="附件名称">
        <template slot-scope="scope">
          <el-link
            type="primary"
            :underline="false"
            target="_blank"
            :href="getlink(scope.row.Id)"
            style="font-size:12px;"
          >{{ scope.row.showName }}
          </el-link>
        </template>
      </el-table-column>
      <el-table-column label="类型">
        <template slot-scope="scope">
          <div>{{ scope.row.attachType === "task_branch" ? "分局初审意见" : "普通附件" }}</div>
        </template>
      </el-table-column>
    </el-table>
    <div class="isLingxingStyle" v-if="isLingXing&&lxRes['userName']">
      <el-divider content-position="left">审批意见</el-divider>
      <el-form label-width="150px">
        <el-form-item label="审批人：">{{ lxRes["userName"] }}({{ lxRes["roleName"] }})</el-form-item>
        <el-form-item label="审批结果：">
          {{ lxRes["aduitStatus"] === null ? "-" : lxRes["aduitStatus"] === "1" ? "同意" : "不同意" }}
        </el-form-item>
        <el-form-item label="审批意见：">{{ lxRes["aduitReason"] ? lxRes["aduitReason"] : "-" }}</el-form-item>
      </el-form>
    </div>
    <el-divider content-position="left">审核结果</el-divider>
    <el-form ref="ruleForm" :model="form" label-width="90px">
      <el-form-item label="审核结果" prop="aduitStatus">
        <el-radio-group v-model="form.aduitStatus">
          <el-radio label="1">同意</el-radio>
          <el-radio label="2">不同意</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="审核意见" prop="aduitReason">
        <el-input v-model="form.aduitReason" placeholder="请输入审核意见" type="textarea"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">提交</el-button>
        <el-button @click="$emit('closeon')">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import { adjustAgree, adjustModel, LXadjustResult, LXadjustAgree } from "@/api/composite/monitor";
import { fileList } from "@/api/upload";
import {noticeOperaAdd} from "@/api/system/config";

export default {
  props: {
    applyData: {
      type: Object,
      default: () => {}
    },
    isLingXing: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      resData: {},
      fileList: [],
      reason: null,
      loading: false,
      form: { aduitStatus: "1" },
      lxRes: {}
    };
  },
  watch: {
    applyData: {
      handler(v) {
        if (v) {
          if (this.isLingXing) {
            this.getLXRes(v);
          }
          this.getModel({ taskAdjustId: v.taskAdjustId });
          this.getList();
        }
      },
      immediate: true,
      deep: true
    }
  },
  methods: {
    getList() {
      let ref = {
        refId: this.applyData.taskAdjustId,
        refType: "task_apply"
      };
      fileList(ref).then((res) => {
        if (res.code === 200) {
          this.fileList = res.data;
        }
      });
    },
    getLXRes(v) {
        LXadjustResult(v.taskAdjustId).then(res => {
          if (res.code === 200) {
            this.lxRes = res.data;
          }
        })
    },
    getModel(v = {}) {
      this.loading = true;
      adjustModel(v).then((res) => {
        if (res.code === 200) {
          this.resData = res.data;
          if (this.resData.adjustReason === "-1") {
            this.reason = "其他";
          } else {
            if (this.resData.adjustItem === "0") {
              this.getDicts("task_cancel_reason").then((res) => {
                this.reason = this.selectDictLabel(
                  res.data,
                  this.resData.adjustReason

                );
              });
            } else if (this.resData.adjustItem === "1") {
              this.getDicts("task_postpone_reason").then((res) => {
                this.reason = this.selectDictLabel(
                  res.data,
                  this.resData.adjustReason
                );
              });
            }
          }
        }
        setTimeout(() => {
          this.loading = false;
        }, 800);
      });
    },
    onSubmit() {
      let oVal = { ...this.resData, ...this.form };
      oVal.taskAdjustId = this.applyData.taskAdjustId;
      if (this.isLingXing) {
        LXadjustAgree(oVal).then(res => {
          let journal = {
            module: "综合管理 → 审核管理 → 任务调整审核",
            action: `(${this.applyData.tagHeadName})${this.applyData.fullName} → ${oVal.aduitStatus==='1'?'同意':'不同意'}任务调整审核申请`,
            operaResult: res.code === 200 ? "操作成功" : "操作失败",
            request: "/enterprise/adjust/audit"
          }
          noticeOperaAdd(journal);
          if (res.code === 200) {
            this.msgSuccess("审核成功");
            this.$emit("successExam");
          }
        })
      } else {
        adjustAgree(oVal).then((res) => {
          let journal = {
            module: "综合管理 → 审核管理 → 任务调整审核",
            action: `(${this.applyData.tagHeadName})${this.applyData.fullName} → ${oVal.aduitStatus==='1'?'同意':'不同意'}任务调整审核申请`,
            operaResult: res.code === 200 ? "操作成功" : "操作失败",
            request: "/enterprise/adjust/agree"
          }
          noticeOperaAdd(journal)
          if (res.code === 200) {
            this.msgSuccess("审核成功");
            this.$emit("successExam");
          }
        });
      }
    },
    getlink(id) {
      return `${process.env.VUE_APP_BASE_API}/com/getfile/${id}`;
    }
  }
};
</script>
<style lang="scss">
.examine_details {
  .el-form-item {
    margin-bottom: 5px;
  }
}

.isLingxingStyle {
  .el-form-item {
    margin-bottom: 5px;
  }
}
</style>
