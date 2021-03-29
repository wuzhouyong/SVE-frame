<template>
  <form-dialog
    :title="title"
    :values.sync="form"
    :rules="rules"
    label-width="130px"
    :visible.sync="dialogVisible"
    @submit="submitForm"
  >
    <el-form-item label="所属企业" prop="sourceId">
      <el-select v-model="form.sourceId" placeholder="请选择所属企业" style="width:100%" size="mini">
        <el-option
          :label="i.fullName"
          :value="i.sourceId"
          v-for="i in sourceList"
          :key="i.sourceId"
        ></el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="司机姓名" prop="driverName">
      <el-input v-model="form.driverName" placeholder="请输入司机姓名" />
    </el-form-item>
    <el-form-item label="身份证号" prop="driverId">
      <el-input v-model="form.driverId" placeholder="请输入身份证号" />
    </el-form-item>
    <el-form-item label="登录账号" prop="loginAccount">
      <el-input v-model="form.loginAccount" placeholder="请输入登录账号" />
    </el-form-item>
    <el-form-item label="登录密码" prop="loginPassword">
      <el-input v-model="form.loginPassword" placeholder="请输入登录密码" />
    </el-form-item>
    <el-form-item label="备注">
      <el-input v-model="form.memo" type="textarea" />
    </el-form-item>
  </form-dialog>
</template>
<script>
import { driverinfoAdd, driverinfoEdit } from "@/api/lingxing";
import FormDialog from "@/views/components/form-dialog";
export default {
  name: "checkpoint-form",
  components: { FormDialog },
  props: {
    title: {
      type: String,
    },
    values: {
      type: Object,
      default: () => ({}),
    },
    visible: {
      type: Boolean,
      default: false,
    },
    sourceList: {
      type: Array,
      default: () => {
        [];
      },
    },
  },
  data() {
    var validateID = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入密码"));
      } else {
        if (this.ruleForm.checkPass !== "") {
          this.$refs.ruleForm.validateField("checkPass");
        }
        callback();
      }
    };
    return {
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        sourceId: [
          {
            required: true,
            message: "所属企业不能为空",
            trigger: "blur",
          },
        ],
        driverName: [
          {
            required: true,
            message: "司机名称不能为空",
            trigger: "blur",
          },
        ],
        driverId: [
          {
            required: true,
            message: "身份证号不能为空",
            trigger: "blur",
          },
          {
            pattern: /^\d{6}(18|19|20)?\d{2}(0[1-9]|1[012])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/i,
            message: "请正确输入身份证",
            trigger: "blur",
          },
        ],
        loginAccount: [
          {
            required: true,
            message: "登录账号不能为空",
            trigger: "blur",
          },
        ],
        loginPassword: [
          {
            required: true,
            message: "登录密码不能为空",
            trigger: "blur",
          },
        ],
      },
    };
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
  watch: {
    values() {
      this.form = Object.assign({}, this.values);
    },
  },
  methods: {
    submitForm() {
      if (this.form.driverId && this.form.fullName) {
        driverinfoEdit(this.form).then((res) => {
          if (res.code === 200) {
            this.msgSuccess("修改成功");
            this.$emit("completed");
          } else {
            this.msgError(res.message);
          }
        });
      } else {
        driverinfoAdd(this.form).then((res) => {
          if (res.code === 200) {
            this.msgSuccess("添加成功");
            this.$emit("completed");
          } else {
            this.msgError(res.message);
          }
        });
      }
    },
  },
};
</script>