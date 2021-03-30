<template>
  <form-dialog
    title="新增设备"
    :values.sync="form"
    :rules="rules"
    label-width="130px"
    :visible.sync="dialogVisible"
    width="500px"
    @submit="submitForm"
  >
    <el-form-item label="监控因子" prop="element">
      <el-input v-model="form.element" size="mini"/>
    </el-form-item>
    <el-form-item label="设备品牌" prop="brand">
      <el-input v-model="form.brand" size="mini"/>
    </el-form-item>
    <el-form-item label="设配型号" prop="type">
      <el-input v-model="form.type" size="mini"/>
    </el-form-item>
    <el-form-item label="报警上限" prop="alarmMax">
      <el-input v-model="form.alarmMax" size="mini"/>
    </el-form-item>
    <el-form-item label="报警下限" prop="alarmMin">
      <el-input v-model="form.alarmMin" size="mini"/>
    </el-form-item>
  </form-dialog>
</template>

<script>
import FormDialog from "@/views/components/form-dialog";
import {
  validateFullVolume
} from "@/utils/rules.js";

export default {
  components: {
    FormDialog
  },
  props: {
    visible: {
      type: Boolean,
      default: false
    },
    guid: {
      type: String
    }
  },
  data: () => ({
    rules: {
      element: [
        { required: true, message: "监控因子必填", trigger: "blur" }
      ],
      brand: [
        { required: true, message: "设备品牌必填", trigger: "blur" }
      ],
      type: [
        { required: true, message: "设配型号必填", trigger: "blur" }
      ],
      alarmMax: [
        { required: true, message: "报警上限必填", trigger: "blur" },
        {
          validator: validateFullVolume,
          trigger: "blur"
        }
      ],
      alarmMin: [
        { required: true, message: "报警下限必填", trigger: "blur" },
        {
          validator: validateFullVolume,
          trigger: "blur"
        }
      ]
    },
    form: {}
  }),
  computed: {
    dialogVisible: {
      get() {
        return this.visible || false;
      },
      set(v) {
        this.$emit("update:visible", v);
      }
    }
  },
  created() {},
  methods: {
    submitForm() {
      this.form.parentId = this.guid;
      this.form.deviceId = this.deviceguid();
      this.$emit("successDevice", this.form);
    },
    deviceguid() {
      return "xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx".replace(/[xy]/g, function(c) {
        let r = Math.random() * 16 | 0, v = c === "x" ? r : (r & 0x3 | 0x8);
        return v.toString(16);
      });
    }
  }
};
</script>

<style lang="scss">

</style>
