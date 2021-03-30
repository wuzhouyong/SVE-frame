<template>
  <div class="form-dialog">
    <el-dialog
      :title="title"
      :visible.sync="formVisible"
      custom-class="isDialog"
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
        :label-position="labelPosition"
      >
        <slot></slot>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm" size="mini">提交</el-button>
        <el-button @click="$emit('update:visible', false)" size="mini">取消</el-button>
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
      type: String
    },
    width: {
      type: String,
      default: "500px"
    },
    labelWidth: {
      type: String,
      default: "130px"
    },
    values: {
      type: Object,
      default: () => ({})
    },
    rules: {
      type: Object
    },
    visible: {
      type: Boolean,
      default: false
    },
    labelPosition: {
      type: String,
      default: "right"
    }
  },
  computed: {
    sys_name () {
      return this.$route.path.substr(this.$route.path.lastIndexOf("/") + 1);
    },
    formVisible: {
      get () {
        return !!this.visible;
      },
      set (v) {
        this.$emit("update:visible", v);
      }
    }
  },
  methods: {
    submitForm () {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          this.$emit("submit");
        }
      });
    }
  }
};
</script>
<style lang="scss" scoped>
.isDialog {
  height: auto;
  max-height: calc(100vh - 16vh);
  display: flex;
  flex-direction: column;
  overflow: hidden;

  /deep/ .el-dialog__body {
    flex: 1;
    overflow-y: auto;
  }
}
</style>
