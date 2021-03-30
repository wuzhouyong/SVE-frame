<template>
  <div class="login">
    <div class="word_desribe">东莞市污染源在线监控平台-企业端</div>
    <div class="login-download">
      <span class="downLoadS" title="下载操作手册">
        <el-link
          icon="el-icon-notebook-2"
          href="/static/企业视频联网指南.zip"
          target="_blank"
          :underline="false"
        >企业视频联网指南</el-link
        >
      </span>
      <span class="downLoadS" title="下载操作手册">
        <el-link
          icon="el-icon-notebook-2"
          href="/static/东莞市在线监控平台企业端操作手册.pdf"
          target="_blank"
          :underline="false"
        >操作手册</el-link
        >
      </span>
      <span class="downLoadS" title="下载因子编码表">
        <el-link
          icon="el-icon-notebook-2"
          href="/static/因子编码表.pdf"
          target="_blank"
          :underline="false"
        >因子编码表</el-link
        >
      </span>
      <span class="downLoadS" title="下载数据传输网络表">
        <el-link
          icon="el-icon-notebook-2"
          href="/static/数据传输网络表.pdf"
          target="_blank"
          :underline="false"
        >数据传输网络表</el-link
        >
      </span>
    </div>
    <el-form
      ref="loginForm"
      :model="loginForm"
      :rules="loginRules"
      class="login-form"
    >
      <h3 class="title">登录</h3>
      <el-form-item prop="username">
        <el-input
          v-model="loginForm.username"
          type="text"
          auto-complete="off"
          placeholder="账号"
        >
          <svg-icon
            slot="prefix"
            icon-class="user"
            class="el-input__icon input-icon"
          />
        </el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input
          v-model="loginForm.password"
          type="password"
          auto-complete="off"
          placeholder="密码"
          @keyup.enter.native="handleLogin"
        >
          <svg-icon
            slot="prefix"
            icon-class="password"
            class="el-input__icon input-icon"
          />
        </el-input>
      </el-form-item>
      <el-form-item prop="code">
        <el-input
          v-model="loginForm.code"
          auto-complete="off"
          placeholder="验证码"
          style="width: 63%"
          @keyup.enter.native="handleLogin"
        >
          <svg-icon
            slot="prefix"
            icon-class="validCode"
            class="el-input__icon input-icon"
          />
        </el-input>
        <div class="login-code">
          <img :src="codeUrl" @click="getCode"/>
        </div>
      </el-form-item>
      <div class="passwordMsg">
        <el-checkbox v-model="loginForm.rememberMe">记住密码</el-checkbox>
        <el-button type="text" size="small" @click="resetVisable = true"
        >忘记密码?
        </el-button>
      </div>

      <el-form-item style="width: 100%">
        <el-button
          size="medium"
          type="primary"
          style="width: 100%"
          @click.native.prevent="handleLogin"
          :loading="loading"
        >
          <span v-if="!loading">登 录</span>
          <span v-else>登 录 中...</span>
        </el-button>
      </el-form-item>
    </el-form>
    <el-dialog
      title="当前密码为初始密码,请先修改后再登录."
      :visible.sync="pwdVisible"
      width="30%"
      :close-on-click-modal="false"
      :show-close="false"
    >
      <el-form
        ref="formData"
        :model="formData"
        :rules="rules"
        label-width="100px"
      >
        <el-form-item prop="newPassword" label="新密码">
          <el-input
            placeholder="请输入新密码"
            v-model="formData.newPassword"
            show-password
            size="mini"
          ></el-input>
        </el-form-item>
        <el-form-item prop="confirmPassword" label="确认密码">
          <el-input
            placeholder="请输入确认密码"
            v-model="formData.confirmPassword"
            show-password
            size="mini"
          ></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="resWord" size="mini"
        >修改密码</el-button
        >
      </span>
    </el-dialog>
    <div class="el-login-footer">
      <span>技术支持 0769-22261089</span>
    </div>

    <reset-psd :title="'忘记密码'" :visible.sync="resetVisable"></reset-psd>
    <el-dialog
      title="关于东莞市污染源在线监控平台合并事项的通知"
      :visible.sync="centerDialogVisible"
      width="30%"
      center
    >
      <div class="wrap" style="font-size: 16px">
        <p class="notice">
          各排污单位：
        </p>
        <p style="text-indent: 2em;line-height:28px;margin:0;margin-bottom:5px">
          “东莞市重点污染源在线监控平台（在线监控二期）”因功能升级，原在线监控二期平台的功能全部合并在“东莞市污染源在线监控平台”（http://119.145.97.36:5678），上述域名现可正常登录。
        </p>
        <p style="text-indent: 2em;line-height:28px;margin:0;margin-bottom:5px;">
          重点提示：
        </p>
        <p style="text-indent: 2em;line-height:28px;margin:0;margin-bottom:5px">
          1.企业需登陆原在线监控二期平台进行账号绑定。
        </p>
        <p style="text-indent: 2em;line-height:28px;margin:0;margin-bottom:5px">
          2.新增“工况停运、故障申报功能”，企业可在企业端进行工况停运、故障的申报。
        </p>
        <p style="text-indent: 2em;line-height:28px;margin:0;margin-bottom:5px">
          （联系电话：18566537652）
        </p>
        <p style="width:100%;text-align: right;margin:0;padding:0;">东莞市污染源在线监控中心</p>
        <p style="width:100%;text-align: right;margin:0;padding:0;margin-top:6px">2021年2月1日</p>
        <div style="text-align: left;margin-top:10px;padding-left:40px;">
          <el-link
            icon="el-icon-notebook-2"
            type="primary"
            href="/static/企业端跳转绑定操作指引.pdf"
            target="_blank"
            :underline="false"
          >附件：企业端跳转绑定操作指引.pdf
          </el-link>
        </div>
      </div>

      <!--      <span slot="footer" class="dialog-footer">-->
      <!--        <el-button :disabled="disabled" type="primary" @click="confirm" size="mini">确定</el-button>-->
      <!--      </span>-->
    </el-dialog>
  </div>
</template>

<script>
import {getCodeImg, verifyPwd, newModifyPwd} from "@/api/login";
import Cookies from "js-cookie";
import {encrypt, decrypt} from "@/utils/jsencrypt";
import ResetPsd from "@/components/ResetPsd/index.vue";
import {getQueryObject} from "@/utils/index.js";

export default {
  name: "Login",
  components: {ResetPsd},
  data () {
    var validatePassword = (rule, value, callback) => {
      if (value) {
        const reg = /^(?![0-9]+$)(?![a-zA-Z]+$)[^]{6,18}$/;
        if (!reg.test(value)) {
          callback("密码必须包含字母和数字,且在6~18位之间");
        } else {
          callback();
        }
      } else {
        callback(new Error(rule.label + "不能为空"));
      }
    };
    var validatePassword2 = (rule, value, callback) => {
      if (!value) {
        callback(new Error(rule.label + "不能为空"));
      } else if (value !== this.formData.newPassword) {
        callback(new Error("两次输入的密码不一致!"));
      } else {
        callback();
      }
    };
    return {
      codeUrl: "",
      cookiePassword: "",
      resetVisable: false,
      loginForm: {
        username: undefined,
        password: undefined,
        rememberMe: false,
        code: "",
        uuid: ""
      },
      formData: {
        oldPassword: null,
        newPassword: null,
        confirmPassword: null
      },
      rules: {
        newPassword: [{label: "新密码", validator: validatePassword}],
        confirmPassword: [{label: "确认密码", validator: validatePassword2}]
      },
      loginRules: {
        username: [
          {required: true, trigger: "blur", message: "用户名不能为空"}
        ],
        password: [
          {required: true, trigger: "blur", message: "密码不能为空"}
        ],
        code: [
          {required: true, trigger: "change", message: "验证码不能为空"}
        ]
      },
      loading: false,
      redirect: undefined,
      pwdVisible: false,
      centerDialogVisible: false
    };
  },
  watch: {
    $route: {
      handler: function (route) {
        this.redirect = route.query && route.query.redirect;
      },
      immediate: true
    }
  },
  created () {
    // console.log("getQueryObject22222222", getQueryObject());
    // this.timer();
    // 判断是否别的平台跳转此进行需要免登录验证
    if (
      getQueryObject().hasOwnProperty("authCode") &&
      getQueryObject().authCode
    ) {
      this.advanLoginByUrl(getQueryObject().authCode);
      this.getCode();
    } else {
      this.getCode();
      this.getCookie();
    }
  },
  methods: {
    getCode () {
      getCodeImg().then((res) => {
        this.codeUrl = "data:image/gif;base64," + res.img;
        this.loginForm.uuid = res.uuid;
      });
    },
    getCookie () {
      const username = Cookies.get("username");
      const password = Cookies.get("password");
      const rememberMe = Cookies.get("rememberMe");
      // const authCode = Cookies.get('authCode')
      this.loginForm = {
        username: username === undefined ? this.loginForm.username : username,
        password:
          password === undefined ? this.loginForm.password : decrypt(password),
        rememberMe: rememberMe === undefined ? false : Boolean(rememberMe)
        // authCode: !authCode ? '' : authCode
      };
    },
    handleLogin () {
      this.$refs.loginForm.validate((valid) => {
        if (valid) {
          // 手机正则
          const reg = /^((0\d{2,3}-\d{7,8})|(1[3456789]\d{9}))$/;
          this.loading = true;
          if (this.loginForm.rememberMe) {
            Cookies.set("username", this.loginForm.username, {expires: 30});
            Cookies.set("password", encrypt(this.loginForm.password), {
              expires: 30
            });
            Cookies.set("rememberMe", this.loginForm.rememberMe, {
              expires: 30
            });
          } else {
            Cookies.remove("username");
            Cookies.remove("password");
            Cookies.remove("rememberMe");
            Cookies.remove("authCode");
          }

          //通过手机登录
          if (reg.test(this.loginForm.username)) {
            this.$store
              .dispatch("Mobile_login", this.loginForm)
              .then(() => {
                this.$router.push({path: "/"});
              })
              .catch(() => {
                this.loading = false;
                this.getCode();
              });
            return;
          }

          let oVal = {
            userName: this.loginForm.username,
            oldPassword: this.loginForm.password
          };
          oVal.oldPassword = btoa(oVal.oldPassword);
          verifyPwd(oVal).then((res) => {
            if (res.code === 200) {
              if (res.data) {
                this.pwdVisible = true;
                this.loading = false;
              } else {
                this.$store
                  .dispatch("Login", this.loginForm)
                  .then(() => {
                    this.$router.push({path: "/"});
                  })
                  .catch(() => {
                    this.loading = false;
                    this.getCode();
                  });
              }
              this.loading = false;
            }
          });
        }
      });
    },
    resWord () {
      this.$refs.formData.validate((valid) => {
        if (valid) {
          let oVal = {
            userName: this.loginForm.username,
            oldPassword: this.loginForm.password,
            newPassword: this.formData.newPassword,
            defaultPwd: this.formData.newPassword
          };
          if (this.loginForm.password === this.formData.newPassword) {
            this.msgError("新密码和旧密码不能一样");
            this.loading = false;
            return;
          } else {
            oVal.oldPassword = btoa(oVal.oldPassword);
            oVal.newPassword = btoa(oVal.newPassword);
            newModifyPwd(oVal).then((res) => {
              if (res.code === 200) {
                this.loginForm.password = oVal.defaultPwd;
                this.$store
                  .dispatch("Login", this.loginForm)
                  .then(() => {
                    this.$router.push({path: "/"});
                  })
                  .catch(() => {
                    this.getCode();
                  });
              }
            });
            this.loading = false;
          }
        }
      });
    },

    advanLoginByUrl (authCode) {
      const loading = this.$loading({
        lock: true,
        text: "Loading",
        spinner: "el-icon-loading",
        background: "rgba(0, 0, 0, 0.7)"
      });

      this.$store
        .dispatch("advanceLogin", authCode)
        .then((associated) => {
          console.log(associated);
          if (associated) {
            this.$router.push({path: "/"});
            loading.close();
          } else {
            loading.close();
            this.msgError("登录后绑定");
            this.$router.push({path: "/login"});
          }
        })
        .catch(() => {
          loading.close();
          this.$router.push({path: "/login"});
        });
    },
    // timer() {
    //   if (this.time > 0) {
    //     this.disabled = true;
    //     this.time--;
    //     this.btntxt = "我已阅读(" + this.time + ")";
    //     setTimeout(this.timer, 1000);
    //   } else {
    //     this.time = 0;
    //     this.btntxt = "确定";
    //     this.disabled = false;
    //   }
    // },
    confirm () {
      // this.$store.dispatch("SetClcikStauts");
      this.centerDialogVisible = false;
    }
  }
};
</script>

<style rel="stylesheet/scss" lang="scss">
.login {
  // display: flex;
  // justify-content: center;
  // align-items: center;
  height: 100%;
  background: #fafafa url("../assets/image/background2.png") no-repeat left center;
  background-size: cover;
  position: relative;

  .login-download {
    position: absolute;
    right: 10px;
    top: 10px;

    span.downLoadS {
      margin-right: 5px;

      &:hover {
        i,
        span {
          color: #409eff;
          font-weight: bold;
        }
      }

      i,
      span {
        color: #fff;
        font-size: 15px;
        font-weight: bold;
        letter-spacing: 1px;
      }
    }
  }

  .word_desribe {
    color: #fff;
    font-weight: 300;
    font-size: 30px;
    position: absolute;
    top: 380px;
    left: 100px;
  }
}

.title {
  margin: 0px auto 30px auto;
  text-align: center;
  color: #707070;
}

.login-form {
  border-radius: 6px;
  background: #ffffff;
  width: 400px;
  padding: 25px 25px 5px 25px;
  position: absolute;
  right: 150px;
  top: 200px;

  .el-input {
    height: 38px;

    input {
      height: 38px;
    }
  }

  .input-icon {
    height: 39px;
    width: 14px;
    margin-left: 2px;
  }
}

.login-tip {
  font-size: 13px;
  text-align: center;
  color: #bfbfbf;
}

.login-code {
  width: 33%;
  height: 38px;
  float: right;

  img {
    cursor: pointer;
    vertical-align: middle;
  }
}

.el-login-footer {
  height: 40px;
  line-height: 40px;
  position: fixed;
  bottom: 0;
  width: 100%;
  text-align: center;
  color: #fff;
  font-family: Arial;
  font-size: 14px;
  letter-spacing: 1px;
}

.passwordMsg {
  display: flex;
  justify-content: space-between;
  margin-bottom: 20px;
  align-items: center;
}
</style>
