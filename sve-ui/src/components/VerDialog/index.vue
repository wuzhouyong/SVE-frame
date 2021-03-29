<!--dialog弹窗+新增编辑表单-->
<template>
  <el-dialog
    :title="dialogDatas.title"
    :width="dialogDatas.width"
    :visible.sync="visible"
    @close="$emit('updateShow', false)"
    :show="show"
  >
    <VerForm v-bind="$attrs" v-on="$listeners" ref="verForm"></VerForm>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取 消</el-button>
      <el-button type="primary" @click="$refs.verForm.submitForm()">提 交</el-button>
    </span>
  </el-dialog>
</template>
<script>
import VerForm from "@/components/VerForm";
export default {
  inheritAttrs: false,
  components: { VerForm },
  props: {
    show: {
      type: Boolean,
      default: false
    },
    dialogDatas: {
      type: Object,
      required: true
    }
  },
  watch: {
    show() {
      this.visible = this.show;
    }
  },
  data() {
    return {
      visible: this.show
    };
  }
};
</script>