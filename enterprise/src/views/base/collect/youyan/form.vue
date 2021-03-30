<template>
  <form-dialog
    :title="title"
    :values.sync="form"
    :rules="rules"
    label-width="130px"
    :visible.sync="dialogVisible"
    @submit="submitForm"
  >
    <el-form-item label="数采名称" prop="cpName">
      <el-input v-model="form.cpName" placeholder="请输入数采名称" />
    </el-form-item>
    <el-form-item label="数采密码" prop="collectPass" v-if="!form.collectId">
      <el-input v-model="form.collectPass" placeholder="请输入数采密码" />
    </el-form-item>
    <el-form-item label="设备品牌" prop="acquisitionBrand">
      <el-input v-model="form.acquisitionBrand" placeholder="请输入设备品牌" />
    </el-form-item>
    <el-form-item label="Ip地址" prop="ipAddress">
      <el-input v-model="form.ipAddress" placeholder="请输入Ip地址" />
    </el-form-item>
    <el-form-item label="是否中环认证" prop="isEnvcert">
      <el-radio-group v-model="form.isEnvcert" placeholder="是否中环认证">
        <el-radio label="true">是</el-radio>
        <el-radio label="false">否</el-radio>
      </el-radio-group>
    </el-form-item>
  </form-dialog>
</template>
<script>
import { add, edit } from "@/api/collect";
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
  },
  data() {
    return {
      // 表单参数
      form: {
        cpName: undefined,
        acquisitionBrand: undefined,
        ipAddress: undefined,
        collectPass: undefined,
        isEnvcert: "false",
      },
      // 表单校验
      rules: {
        cpName: [
          {
            required: true,
            message: "数采名称不能为空",
            trigger: "blur",
          },
        ],
        acquisitionBrand: [
          {
            required: true,
            message: "设备品牌不能为空",
            trigger: "blur",
          },
        ],
        collectPass: [
          {
            required: true,
            message: "数采密码不能为空",
            trigger: "blur",
          },
          {
            pattern: /^\d{6}$/,
            message: "密码只能为6位数字",
          },
        ],
      },
    };
  },
  computed: {
    sys_name() {
      return this.$route.path.substr(this.$route.path.lastIndexOf("/") + 1);
    },
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
      this.form = Object.assign({ isEnvcert: "false" }, this.values);
    },
  },
  methods: {
    submitForm(v) {
      if (!this.form.collectId) {
        add(this.sys_name, this.form).then((res) => {
          if (res.code === 200) {
            this.msgSuccess("添加成功");
            this.$emit("completed");
          } else {
            this.msgError(res.message);
          }
        });
      } else {
        edit(this.sys_name, this.form).then((res) => {
          if (res.code === 200) {
            this.msgSuccess("修改成功");
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