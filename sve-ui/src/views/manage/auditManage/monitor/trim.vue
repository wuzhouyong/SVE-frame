<template>
  <div>
    <el-form ref="ruleForm" :model="form" label-width="98px" :rules="rules">
      <el-form-item label="调整事项" prop="adjustItem">
        <el-radio-group v-model="form.adjustItem" @change="onChange">
          <el-radio label="0">申请不安装</el-radio>
          <el-radio label="1">申请暂缓</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item :label="adjustItem==='0'?'不安装原因':'暂缓原因'" prop="adjustReason">
        <el-select
          v-model="form.adjustReason"
          :placeholder="adjustItem==='0'?'请选择不安装原因':'请选择暂缓原因'"
          style="width:100%"
        >
          <el-option
            :label="i.dictLabel"
            :value="i.dictValue"
            v-for="i in reasonList"
            :key="i.dictCode"
          ></el-option>
          <el-option label="其他" value="-1"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item prop="suspendDate" label="暂缓至" v-if="adjustItem==='1'">
        <el-date-picker
          type="date"
          placeholder="暂缓至"
          v-model="form.suspendDate"
          value-format="yyyy-MM-dd"
          style="width: 100%;"
          :picker-options="pickerOptions0"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="其他原因">
        <el-input v-model="form.otherReason" placeholder="请输入其他原因" type="textarea"></el-input>
      </el-form-item>
      <el-form-item label="备注" prop="memo">
        <el-input v-model="form.memo" placeholder="请填写经办人姓名和电话" type="textarea"></el-input>
      </el-form-item>
    </el-form>
    <el-divider content-position="left">
      <i class="el-icon-files" style="padding-right:5px;"></i>附件管理
    </el-divider>
    <Upload ref="uploadTem" :refId="id" v-on="$listeners" :trimType="trimType"></Upload>
  </div>
</template>
<script>
import Upload from "./upload";
import { adjustAdd } from "@/api/composite/monitor";
import {noticeOperaAdd} from "@/api/system/config";

export default {
  components: { Upload },
  props: {
    rowsData: {
      type: Object,
      default: () => {}
    },
    trimType: {
      type: String,
      default: "0"
    }
  },
  data() {
    return {
      form: {
        adjustItem: "0",
        adjustReason: null
      },
      adjustItem: "0",
      rules: {
        adjustItem: [{ required: true, message: "必填", trigger: "blur" }],
        adjustReason: [
          { required: true, message: "原因必选", trigger: "blur" }
        ],
        suspendDate: [
          { required: true, message: "暂缓至时间必选", trigger: "blur" }
        ],
        memo: [
          { required: true, message: "经办人姓名和电话必填", trigger: "blur" }
        ]
      },
      cancelList: [],
      postponeList: [],
      reasonList: [],
      ref_id: "",
      id: "",
      pickerOptions0: {
        disabledDate(time) {
          return time.getTime() < Date.now() - 8.64e7; //如果没有后面的-8.64e6就是不可以选择今天的
        }
      }
    };
  },
  created() {
    this.getCancel();
    this.getPostpone();
    this.guid();
  },
  methods: {
    guid() {
      let s = [];
      let hexDigits = "0123456789abcdef";
      for (var i = 0; i < 36; i++) {
        s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
      }
      s[14] = "4"; // bits 12-15 of the time_hi_and_version field to 0010
      s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1); // bits 6-7 of the clock_seq_hi_and_reserved to 01
      s[8] = s[13] = s[18] = s[23] = "-";
      this.id = s.join("");
    },
    onChange(v) {
      this.adjustItem = v;
      this.form.adjustReason = "";
      this.reasonList =
        this.adjustItem === "0" ? this.cancelList : this.postponeList;
    },
    getCancel() {
      this.getDicts("task_cancel_reason").then((res) => {
        this.cancelList = res.data;
        this.reasonList = this.cancelList;
      });
    },
    getPostpone() {
      this.getDicts("task_postpone_reason").then((res) => {
        this.postponeList = res.data;
      });
    },
    submitTxt() {
      if (this.$refs.uploadTem.hasFile()) {
        this.$refs["ruleForm"].validate((valid) => {
          if (valid) {
            this.form.pfSourceId = this.rowsData.sourceId;
            this.form.tagHeadId = this.rowsData.tagHeadId;
            this.form.taskAdjustId = this.id;
            if (this.trimType === "1") {
              this.form.aduitStatus = "1";
            }
            if (this.form.adjustReason === "-1") {
              if (this.form.otherReason) {
                adjustAdd(this.form).then((res) => {
                  let journal = {
                    module: "综合管理 → 监控任务管理 → 监控任务清单",
                    action: `(${this.rowsData.tagHeadName})${this.rowsData.fullName},${this.form.adjustItem==='0'?'申请不安装':'申请暂缓'}`,
                    operaResult: res.code === 200 ? "操作成功" : "操作失败",
                    request: "/enterprise/adjust/add"
                  }
                  noticeOperaAdd(journal);
                  if (res.code === 200) {
                    this.$emit("successUpload");
                    // this.$refs.uploadTem.sendFile(res.data);
                    // this.ref_id = res.data;
                  }
                });
              } else {
                this.msgError("原因选择其他,请在其他原因处详细描述");
              }
            } else {
              adjustAdd(this.form).then((res) => {
                let journal = {
                  module: "综合管理 → 监控任务管理 → 监控任务清单",
                  action: `(${this.rowsData.tagHeadName})${this.rowsData.fullName},${this.form.adjustItem==='0'?'申请不安装':'申请暂缓'}`,
                  operaResult: res.code === 200 ? "操作成功" : "操作失败",
                  request: "/enterprise/adjust/add"
                }
                noticeOperaAdd(journal);

                if (res.code === 200) {
                  this.$emit("successUpload");
                  // this.$refs.uploadTem.sendFile(res.data);
                  // this.ref_id = res.data;
                  // this.msgSuccess("申请提交成功");
                  // this.$emit("successApply");
                }
              });
            }
          } else {
            this.msgError("完整填写数据");
          }
        });
      }else{
        this.msgError("请上传附件");
      }
    }
  }
};
</script>
