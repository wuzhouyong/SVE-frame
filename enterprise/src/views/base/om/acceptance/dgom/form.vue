<template>
  <div class="accept-form">
    <el-form :model="handleForm" ref="handleForm" label-width="110px" :rules="rules">
      <el-row :gutter="15">
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="验收时间" prop="acceptDate">
            <el-date-picker
              size="mini"
              style="width:100%"
              v-model="handleForm.acceptDate"
              value-format="yyyy-MM-dd"
              type="date">
            </el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="验收负责人" prop="chargeMan">
            <el-input
              size="mini"
              v-model="handleForm.chargeMan"
              :style="{width: '100%'}"
            ></el-input>
          </el-form-item>
        </el-col>

        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="负责人职务" prop="chargeManTitle">
            <el-input
              size="mini"
              v-model="handleForm.chargeManTitle"
              :style="{width: '100%'}"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="负责人电话" prop="chargeManTel">
            <el-input
              size="mini"
              v-model="handleForm.chargeManTel"
              :style="{width: '100%'}"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="备注" prop="memo">
            <el-input
              size="mini"
              type="textarea"
              v-model="handleForm.memo"
              :style="{width: '100%'}"
            ></el-input>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <n-upload :applyId="applyId" ref="nUpload" refType="Ent_AcceptApplyHead"></n-upload>
  </div>
</template>
<script>
import nUpload from "./upload";
import {dgomEntAcceptAddHead, dgomEntAcceptEditHead} from "@/api/dgom/accept";

export default {

  components: {nUpload},
  props: {
    selectRow: {
      type: Object,
      default: () => ({})
    }
  },
  data () {
    var checkphone = (rule, value, callback) => {
      // let phoneReg = /(^1[3|4|5|6|7|8|9]\d{9}$)|(^09\d{8}$)/;
      if (value == "") {
        callback(new Error("请输入手机号"));
      } else if (!this.isCellPhone(value)) {
        //引入methods中封装的检查手机格式的方法
        callback(new Error("请输入正确的手机号!"));
      } else {
        callback();
      }
    };
    return {
      handleForm: {},
      rules: {
        acceptDate: [
          {
            required: true,
            message: "验收时间必填",
            trigger: "blur",
          },
        ],
        chargeMan: [
          {
            required: true,
            message: "验收负责人必填",
            trigger: "blur",
          },
        ],
        chargeManTel: [
          {
            required: true,
            message: "负责人电话必填",
            trigger: "blur",
          },
          {validator: checkphone, trigger: "blur"},
        ],
        chargeManTitle: [
          {
            required: true,
            message: "负责人职务必填",
            trigger: "blur",
          },
        ],
      },
      applyId: null
    };
  },
  watch: {
    selectRow: {
      handler (v) {
        if (v && v.applyId) {
          this.applyId = v.applyId;
          this.handleForm = Object.assign({}, this.handleForm, v);
        } else {
          this.guid();
        }
      },
      immediate: true
    }
  },
  methods: {
    guid () {
      let s = [];
      let hexDigits = "0123456789abcdef";
      for (var i = 0; i < 36; i++) {
        s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
      }
      s[14] = "4"; // bits 12-15 of the time_hi_and_version field to 0010
      s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1); // bits 6-7 of the clock_seq_hi_and_reserved to 01
      s[8] = s[13] = s[18] = s[23] = "-";
      this.applyId = s.join("");
    },
    submitHandle () {
      this.$refs["handleForm"].validate((valid) => {
        if (!valid) return;
        if (this.$refs.nUpload.isFile()) {
          if (this.selectRow && this.selectRow.applyId) {
            dgomEntAcceptEditHead(this.handleForm).then(res => {
              if (res.code === 200) {
                this.msgSuccess("编辑备案成功");
                this.$emit("completed");
              }
            });
          } else {
            this.handleForm.applyId = this.applyId;
            dgomEntAcceptAddHead(this.handleForm).then(res => {
              if (res.code === 200) {
                this.msgSuccess("新增备案成功");
                this.$emit("completed");
              }
            });
          }

        } else {
          this.msgError("附件必须上传");
        }
      });
    },
    //检查手机号
    isCellPhone (val) {
      if (!/^1(3|4|5|6|7|8)\d{9}$/.test(val)) {
        return false;
      } else {
        return true;
      }
    },
  }
};
</script>
