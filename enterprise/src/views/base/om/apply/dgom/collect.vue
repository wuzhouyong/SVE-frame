<template>
  <form-dialog
    title="新增数采"
    :values.sync="form"
    :rules="rules"
    label-width="130px"
    :visible.sync="dialogVisible"
    width="500px"
    @submit="submitForm"
  >
    <el-form-item label="监控点名称" prop="checkpointDesc">
      <el-input v-model="form.checkpointDesc" size="mini"/>
    </el-form-item>
    <el-form-item label="数采品牌" prop="collectDevDesc">
      <el-input v-model="form.collectDevDesc" size="mini"/>
    </el-form-item>
    <el-form-item label="数采型号" prop="collectDevSpec">
      <el-input v-model="form.collectDevSpec" size="mini"/>
    </el-form-item>
    <el-form-item label="MN码" prop="mn">
      <el-input v-model="form.mn" size="mini"/>
    </el-form-item>
  </form-dialog>
</template>

<script>
import FormDialog from "@/views/components/form-dialog";

export default {
  components: {
    FormDialog
  },
  props: {
    visible: {
      type: Boolean,
      default: false
    }
  },
  data: () => ({
    rules: {
      checkpointDesc: [
        { required: true, message: "监控点名称必填", trigger: "blur" }
      ],
      collectDevDesc: [
        { required: true, message: "数采品牌必填", trigger: "blur" }
      ],
      collectDevSpec: [
        { required: true, message: "数采型号必填", trigger: "blur" }
      ],
      mn: [
        { required: true, message: "MN码必填", trigger: "blur" }
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
      this.form.devices = [];
      this.form.guid = this.guid();
      this.$emit("addCollectSuccess", this.form);
    },
    guid() {
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
