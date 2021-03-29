<template>
  <div class="form-dialog" :loading="true">
    <el-dialog
      :title="title"
      v-dialogDrag
      :visible.sync="formVisible"
      :width="width"
      append-to-body
      :destroy-on-close="true"
      :close-on-click-modal="false"
    >
      <el-form
        ref="form"
        :model="values"
        :rules="rules"
        :label-width="labelWidth"
        @submit.native.prevent="submitForm"
      >
        <slot></slot>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm" v-preventClick>提交</el-button>
        <el-button @click="$emit('update:visible', false)">取消</el-button>
      </span>
    </el-dialog>
  </div>
</template>
<script>
export default {
  name: "form-dialog",
  inheritAttrs: false,
  props: {
    title: {
      type: String,
    },
    width: {
      type: String,
      default: "500px",
    },
    labelWidth: {
      type: String,
      default: "130px",
    },
    values: {
      type: Object,
      default: () => ({}),
    },
    rules: {
      type: Object,
      default: () => ({}),
    },
    visible: {
      type: Boolean,
      default: false,
    },
  },
  computed: {
    formVisible: {
      get() {
        return !!this.visible;
      },
      set(v) {
        this.$emit("update:visible", v);
      },
    },
  },
  methods: {
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          this.$emit("submit");
        }
      });
    },
  },
};
</script>
