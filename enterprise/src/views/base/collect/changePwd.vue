<template>
  <form-dialog
    title="修改数采密码"
    :values.sync="form"
    :rules="rules"
    label-width="130px"
    :visible.sync="dialogVisible"
    @submit="submitForm"
  >
    <el-form-item prop="newPassword" label="新密码">
      <el-input placeholder="请输入新密码" v-model="form.newPassword" show-password></el-input>
    </el-form-item>
    <el-form-item prop="confirmPassword" label="确认密码">
      <el-input placeholder="请输入确认密码" v-model="form.confirmPassword" show-password></el-input>
    </el-form-item>
  </form-dialog>
</template>
<script>
import FormDialog from "@/views/components/form-dialog";
import { ChangePassword } from "@/api/collect";
export default {
  components: { FormDialog },
  props: {
    visible: {
      type: Boolean,
      default: false,
    },
    platform: {
      type: String,
      default: "",
    },
    collectId: {
      type: String,
      default: "",
    },
  },
  computed: {
    dialogVisible: {
      get() {
        return this.visible || false;
      },
      set(v) {
        this.$emit("update:visible", v);
      },
    },
  },
  data() {
    var validatePassword = (rule, value, callback) => {
      if (value) {
        const reg = /^\d{6}$/;
        if (!reg.test(value)) {
          callback("密码只能为6位数字");
        } else {
          callback();
        }
      } else {
        callback(new Error("新密码不能为空"));
      }
    };
    var validatePassword2 = (rule, value, callback) => {
      if (!value) {
        callback(new Error("确认新密码不能为空"));
      } else if (value !== this.form.newPassword) {
        callback(new Error("两次输入的密码不一致!"));
      } else {
        callback();
      }
    };
    return {
      form: { newPassword: "", confirmPassword: "" },
      rules: {
        newPassword: [
          {
            required: true,
            message: "新密码不能为空",
            trigger: "blur",
          },
          { validator: validatePassword },
        ],
        confirmPassword: [
          {
            required: true,
            message: "确认新密码不能为空",
            trigger: "blur",
          },
          { validator: validatePassword2 },
        ],
      },
    };
  },
  methods: {
    submitForm() {
      let oVal = {
        collectId: this.collectId,
        collectPass: this.form.newPassword,
      };
      ChangePassword(this.platform, oVal).then((res) => {
        if (res.code === 200) {
          this.msgSuccess("数采密码修改成功");
          this.$emit("successPwd");
        }
      });
    },
  },
};
</script>


