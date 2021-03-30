<template>
  <span class="change-password">
    <el-link
      icon="el-icon-key"
      :underline="false"
      @click="dialogVisible = true"
      >{{ isBinding ? "更换绑定手机" : "绑定手机" }}</el-link
    >
    <el-dialog
      :title="isBinding ? '更换绑定手机' : '绑定手机'"
      :visible.sync="dialogVisible"
      :close-on-click-modal="false"
      width="620px"
      append-to-body
      destroy-on-close
    >
      <el-steps
        align-center
        :active="active"
        finish-status="success"
        v-if="isBinding"
      >
        <el-step title="验证原手机"></el-step>
        <el-step title="更换绑定手机"></el-step>
      </el-steps>
      <div class="firstStep" v-if="active == 0">
        <el-form
          :model="ruleForm"
          :rules="rules"
          ref="ruleForm"
          label-width="100px"
          class="demo-ruleForm"
        >
          <el-form-item label="手机号码" prop="shielding">
            <el-input
              :disabled="codeStatus"
              style="position: relative"
              v-model="ruleForm.shielding"
            ></el-input>
            <el-button
              style="position: absolute; right: -95px; top: 5px"
              type="primary"
              size="mini"
              :disabled="disabled"
              @click="sendcode"
              >{{ btntxt }}</el-button
            >
          </el-form-item>

          <el-form-item label="验证码" prop="code">
            <el-input v-model="ruleForm.code"></el-input>
          </el-form-item>

        </el-form>
         <div class="tips">
            <h4>Tips:为确认身份,我们仍需要验证您的安全手机</h4>
            <h4>点击发送验证码按钮,将会收到一条验证码的短信至您手机</h4>
         </div>
      </div>
      <div class="secondStep" v-if="active == 1">
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
              :disabled="codeStatus"
            ></el-input>
            <el-button
              style="position: absolute; right: -95px; top: 5px"
              type="primary"
              size="mini"
              @click="sendcode"
              >{{ btntxt }}</el-button
            >
          </el-form-item>

          <el-form-item label="验证码" prop="code">
            <el-input v-model="ruleForm.code"></el-input>
          </el-form-item>
        </el-form>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button
          type="primary"
          @click="submitForm"
          v-if="active == 0 && isBinding"
          >下一步</el-button
        >
        <el-button
          type="primary"
          @click="submitForm"
          v-if="(isBinding && active == 1) || (!isBinding && active == 0)"
          >确定</el-button
        >

        <el-button @click="dialogVisible = false">取消</el-button>
      </span>
    </el-dialog>
  </span>
</template>
<script>
import { changePwd } from "@/api/login";

import {
  getVertufyCode,
  forgetBindCode,
  modifypsd,
  bindingPhone,
  verifyCode,
} from "@/api/password";
export default {
  name: "change-password",
  //   props: ["isBinds"],
  computed: {
    //   是否绑定手机号
    isBinds() {
      return this.$store.getters.isBinding;
    },
    UserMobie() {
      return this.$store.getters.mobile;
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
    return {
      isBinding: false,
      dialogVisible: false,
      handleText: "绑定手机",
      active: 0,
      ruleForm: {
        mobile: "",
        code: "",
        shielding: "",
      },
      rules: {
        mobile: [
          { required: true, message: "请输入手机号", trigger: "blur" },
          { validator: checkphone, trigger: "blur" },
        ],
        shielding: [
          { required: true, message: "请输入手机号", trigger: "blur" },
          //   { validator: checkphone, trigger: "blur" },
        ],
        code: [{ required: true, message: "请输入验证码", trigger: "blur" }],

        // codeStatus2: true,
      },
      btntxt: "发送验证码",
      time: 60,
      disabled: false,
      // 验证码输入状态
      codeStatus: false,
    };
  },
  methods: {
    submitForm() {
      let { mobile, code, shielding } = this.ruleForm;
      let { isBinding, active } = this;
      this.$refs["ruleForm"].validate((valid) => {
        if (valid) {
          if (isBinding && active === 0) {
            verifyCode(mobile, { code }).then((res) => {
              this.msgSuccess("身份验证成功");
              this.ruleForm.mobile = "";
              this.ruleForm.code = "";
              this.active = 1;
              this.codeStatus = false;
            });
          } else if (!isBinding && active === 0) {
            bindingPhone(shielding, { code }).then((res) => {
              this.msgSuccess("绑定手机成功");

              this.isBinding = true;
              this.active = 0;
              let mobile = this.ruleForm.shielding;
              this.ruleForm.mobile = mobile;
              this.ruleForm.shielding = this.shieldeNums(mobile);
              this.codeStatus = true;
              this.dialogVisible = false;
            });
          } else if (isBinding && active === 1) {
            bindingPhone(mobile, { code }).then((res) => {
              this.msgSuccess("更换绑定手机成功");
              this.dialogVisible = false;
              this.ruleForm.mobile = mobile;
              this.ruleForm.shielding = this.shieldeNums(mobile);
            });
          }
        } else {
          this.msgError("请完整填写信息!");
        }
      });
    },
    // 发送验证码
    sendcode() {
      let { mobile, shielding } = this.ruleForm;
      let { isBinds, isBinding } = this;

      if (isBinding && !this.isCellPhone(mobile)) {
        console.log(mobile);
        this.$refs.ruleForm.validateField("mobile");
        return;
      }
      let phone = isBinding ? mobile : shielding;
      getVertufyCode(phone).then((res) => {
        console.log(res);
        this.disabled = true;
        this.time =60;
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
    // 屏蔽手机号
    shieldeNums(mobile) {
      return mobile.substring(0, 3) + "****" + mobile.substr(mobile.length - 4);
    },
  },
  mounted() {
    this.isBinding = this.isBinds == 1 ? true : false;

    if (this.isBinding && this.UserMobie) {
      this.codeStatus = true;
      this.ruleForm.mobile = this.UserMobie;
      this.ruleForm.shielding = this.shieldeNums(this.UserMobie);
    }
  },
  watch: {
    dialogVisible(newVal, oldVal) {
      if (this.isBinding && this.UserMobie) {
        this.ruleForm.mobile = this.UserMobie;
        this.ruleForm.shielding = this.shieldeNums(this.UserMobie);
      }

      this.ruleForm.code = "";
      this.active = 0;
    },
    "ruleForm.code": {
      handler: function (newVal, oldVal) {
        let { active, isBinding, ruleForm } = this;

        //
        // if (
        //   newVal.length != 0 &&
        //   active == 0 &&
        //   !isBinding &&
        //   this.isCellPhone(ruleForm.shielding)
        // ) {
        //   this.codeStatus = true;
        // } else if (
        //   newVal.length == 0 &&
        //   active == 0 &&
        //   !isBinding &&
        //   this.isCellPhone(ruleForm.shielding)
        // ) {
        //   this.codeStatus = false;
        // } else if (
        //   newVal.length != 0 &&
        //   active == 1 &&
        //   this.isCellPhone(ruleForm.mobile) &&
        //   isBinding
        // ) {
        //   this.codeStatus = true;
        // } else if (
        //   newVal.length != 0 &&
        //   active == 0 &&
        //   this.isCellPhone(ruleForm.shielding) &&
        //   isBinding
        // ) {
        //   this.codeStatus = true;
        // } else if (
        //   newVal.length == 0 &&
        //   active == 1 &&
        //   this.isCellPhone(ruleForm.mobile) &&
        //   isBinding
        // ) {
        //   this.codeStatus = false;
        // }

        if (active == 0 && this.isCellPhone(ruleForm.shielding)) {
          if (newVal.length != 0) {
            this.codeStatus = true;
          } else if (newVal.length == 0) {
            this.codeStatus = false;
          }
        } else if (
          active == 1 &&
          this.isCellPhone(ruleForm.mobile) &&
          isBinding
        ) {
          if (newVal.length != 0) {
            this.codeStatus = true;
          } else if (newVal.length == 0) {
            this.codeStatus = false;
          }
        }
        // else if (
        //   active == 0 &&
        //   this.isCellPhone(ruleForm.shielding) &&
        //   isBinding
        // ) {
        //   if (newVal.length != 0) {
        //     this.codeStatus = true;
        //   }
        // }
      },
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
}
.secondStep {
  margin-top: 20px;
  margin-left: -70px;
}
.firstStep {
  margin-top: 20px;
  margin-left: -70px;
  .tips{
    text-align: center;
  }
}
</style>
