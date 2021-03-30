<template>
  <div class="resetPsd-dialog">
    <el-dialog
      :title="title"
      :visible.sync="formVisible"
      :width="width"
      append-to-body
      :destroy-on-close="true"
      :close-on-click-modal="false"
    >
      <el-steps :active="active" finish-status="success">
        <el-step title="提交验证码"></el-step>
        <el-step title="重设密码"></el-step>
      </el-steps>
      <div class="firstStep" v-if="active == 0">
        <el-form
          :model="ruleForm"
          :rules="rules"
          ref="ruleForm"
          label-width="100px"
          class="demo-ruleForm"
        >
          <el-form-item label="手机号码" prop="mobile">
            <el-input
              style="position: relative"
              v-model="ruleForm.mobile"
            ></el-input>
            <el-button
              style="position: absolute; right: -120px"
              type="primary"
              :disabled="disabled"
              @click="sendcode"
              >{{ btntxt }}</el-button
            >
          </el-form-item>

          <el-form-item label="验证码" prop="code">
            <el-input v-model="ruleForm.code"></el-input>
          </el-form-item>
        </el-form>
      </div>
      <div class="secondStep" v-if="active == 1">
        <el-form
          :model="modifyPsd"
          :rules="rules"
          ref="modifyPsd"
          label-width="100px"
          class="demo-ruleForm"
        >
          <el-form-item label="新密码" prop="newPassword">
            <el-input
              type="password"
              v-model="modifyPsd.newPassword"
            ></el-input>
          </el-form-item>

          <el-form-item label="确认密码" prop="confirmPassword">
            <el-input
              type="password"
              v-model="modifyPsd.confirmPassword"
            ></el-input>
          </el-form-item>
        </el-form>
      </div>

      <!-- <el-form
        ref="form"
        :model="values"
        :rules="rules"
        :label-width="labelWidth"
        @submit.native.prevent="submitForm"
      >
        <slot></slot>
      </el-form> -->
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm" v-if="active == 0"
          >下一步</el-button
        >
        <el-button type="primary" @click="resetPsd" v-else>确定</el-button>
        <!-- <el-button @click="$emit('update:visible', false)">取消</el-button> -->
      </span>
    </el-dialog>
  </div>
</template>
<script>
import { getVertufyCode, forgetBindCode, modifypsd } from "@/api/password";
export default {
  name: "ResetPsd",
  inheritAttrs: false,
  props: {
    title: {
      type: String,
    },
    width: {
      type: String,
      //   default: "500px"
    },
    labelWidth: {
      type: String,
      default: "130px",
    },
    values: {
      type: Object,
      default: () => ({}),
    },
    // rules: {
    //   type: Object,
    // },
    visible: {
      type: Boolean,
      default: false,
    },
  },
  data() {
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
    var validatePass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入密码"));
      } else {
        const reg = /^(?![0-9]+$)(?![a-zA-Z]+$)[^]{6,18}$/;
        if (!reg.test(value)) {
          callback("密码必须包含字母和数字,且在6~18位之间");
        } else {
          callback();
        }
        if (this.modifyPsd.confirmPassword !== "") {
          this.$refs.modifyPsd.validateField("confirmPassword");
        }
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请再次输入密码"));
      } else if (value !== this.modifyPsd.newPassword) {
        callback(new Error("两次输入密码不一致!"));
      } else {
        callback();
      }
    };
    return {
      active: 0,
      ruleForm: {
        mobile: "",
        code: "",
      },
      modifyPsd: {
        newPassword: "",
        confirmPassword: "",
      },
      rules: {
        mobile: [
          { required: true, message: "请输入手机号", trigger: "blur" },
          { validator: checkphone, trigger: "blur" },
        ],
        code: [{ required: true, message: "请输入验证码", trigger: "blur" }],
        newPassword: [{ validator: validatePass, trigger: "blur" }],
        confirmPassword: [{ validator: validatePass2, trigger: "blur" }],
      },
      btntxt: "发送验证码",
      disabled: false,
      time: 60,
    };
  },
  computed: {
    sys_name() {
      return this.$route.path.substr(this.$route.path.lastIndexOf("/") + 1);
    },
    formVisible: {
      get() {
        this.init();
        return !!this.visible;
      },
      set(v) {
        this.init();
        this.$emit("update:visible", v);
      },
    },
  },
  methods: {
    submitForm() {
      let { mobile, code } = this.ruleForm;
      this.$refs["ruleForm"].validate((valid) => {
        if (valid) {
          forgetBindCode(mobile, { code }).then((res) => {
            //   下一步
            this.msgSuccess("验证通过");
            this.active = 1;
          });
        } else {
          this.msgError("请完整填写信息!");
        }
      });
    },
    resetPsd() {
      let { confirmPassword, newPassword } = this.modifyPsd;
      let { mobile } = this.ruleForm;
      this.$refs["modifyPsd"].validate((valid) => {
        if (valid) {
          modifypsd(mobile, {
            newPassword: btoa(newPassword),
            confirmPassword: btoa(confirmPassword),
          }).then((res) => {
            this.msgSuccess("密码重置成功");
            this.$emit("update:values", this.values);
          });
          this.$emit("update:visible", false);
        } else {
          this.msgError("请完整填写信息!");
        }
      });
    },
    //发送手机验证码倒计时
    timer() {
      if (this.time > 0) {
        this.disabled = true;
        this.time--;
        this.btntxt = this.time + "秒" + "再试";
        setTimeout(this.timer, 1000);
      } else {
        this.time = 0;
        this.btntxt = "发送验证码";
        this.disabled = false;
      }
    },
    // 发送验证码
    sendcode() {
      let { mobile } = this.ruleForm;
      if (!this.isCellPhone(mobile)) {
        this.$refs.ruleForm.validateField("mobile");
        return;
      }
      getVertufyCode(mobile).then((res) => {
        console.log(res);
        this.disabled = true
        this.time = 60;
        this.timer();
      });
    },
    //检查手机号
    isCellPhone(val) {
      if (!/^1(3|4|5|6|7|8)\d{9}$/.test(val)) {
        return false;
      } else {
        return true;
      }
    },
    init() {
    //   this.$refs["modifyPsd"].resetFields();
    //   this.$refs["ruleForm"].resetFields();
      this.ruleForm = {};
      this.modifypsd = {};
      this.active = 0;
    },
  },
};
</script>
<style lang="scss" scoped>
.demo-ruleForm {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  .el-form-item{
    width: 360px;
  }
}
.firstStep {
  margin-left: -95px;
}
.secondStep {
  margin-left: -95px;
}
</style>
