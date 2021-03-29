<template>
  <form-dialog
    :title="title"
    :values.sync="form"
    :rules="rules"
    label-width="130px"
    :visible.sync="dialogVisible"
    @submit="submitForm"
  >
    <el-form-item label="污染源" prop="sourceId">
      <SelectSource
        v-model="form.sourceId"
        :data="sourceList"
        placeholder="请输入污染源"
        isPlatName="zaozhi"
      ></SelectSource>
    </el-form-item>
    <el-form-item label="数采名称" prop="cpName">
      <el-input v-model="form.cpName" placeholder="请输入数采名称" />
    </el-form-item>
    <el-form-item label="品牌" prop="acquisitionBrand">
      <el-input v-model="form.acquisitionBrand" placeholder="请输入品牌" />
    </el-form-item>
    <el-form-item label="Ip地址" prop="ipAddress">
      <el-input v-model="form.ipAddress" placeholder="请输入Ip地址" />
    </el-form-item>
    <el-form-item label="是否中环认证" prop="isEnvcert">
      <el-radio-group v-model="form.isEnvcert" size="mini">
        <el-radio border label="true">是</el-radio>
        <el-radio border label="false">否</el-radio>
      </el-radio-group>
    </el-form-item>
  </form-dialog>
</template>
<script>
import { sourceList, checkpointAdd, checkpointEdit } from "@/api/zaozhi";
import FormDialog from "@/views/components/form-dialog";
import SelectSource from "@/components/SelectSource";
export default {
  name: "checkpoint-form",
  components: { FormDialog, SelectSource },
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
      sourceList,
      // 表单参数
      form: {
        sourceId: undefined,
        cpName: undefined,
        acquisitionBrand: undefined,
        ipAddress: undefined,
        isEnvcert: "false",
      },
      // 表单校验
      rules: {
        sourceId: [
          {
            required: true,
            message: "污染源名称不能为空",
            trigger: "blur",
          },
        ],
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
            message: "品牌不能为空",
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
      this.form = Object.assign({ isEnvcert: "false" }, this.values);
    },
  },
  methods: {
    submitForm(v) {
      if (this.form.collectId != undefined) {
        checkpointEdit(this.form).then((res) => {
          if (res.code === 200) {
            this.msgSuccess("修改成功");
            this.$emit("completed");
          } else {
            this.msgError(res.message);
          }
        });
      } else {
        checkpointAdd(this.form).then((res) => {
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