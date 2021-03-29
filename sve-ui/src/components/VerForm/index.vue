<!--新增编辑表单-->
<template>
  <el-form ref="ruleform" :label-width="labelWidth" :model="formData" :rules="formRules">
    <el-form-item v-for="item in formItem" :label="item.label" :prop="item.prop" :key="item.prop">
      <!-- 输入框 -->
      <el-input
        v-if="item.type==='Input'"
        v-model="formData[item.prop]"
        :placeholder="item.placeholder"
        :style="{width:item.width?item.width:'100%'}"
      ></el-input>
      <!-- 下拉框 -->
      <el-select
        clearable
        v-if="item.type==='Select'"
        :placeholder="item.placeholder"
        v-model="formData[item.prop]"
        :style="{width:item.width?item.width:'100%'}"
      >
        <el-option
          v-for="op in item.list"
          :label="op.dictLabel"
          :value="op.dictValue"
          :key="op.dictValue"
        ></el-option>
      </el-select>
      <!--污染源下拉-->
      <SelectSource
        v-if="item.type==='sourceSelect'"
        v-model="formData[item.prop]"
        :placeholder="item.placeholder"
      ></SelectSource>
    </el-form-item>
  </el-form>
</template>
<script>
import SelectSource from "@/components/SelectSource";
export default {
  components: { SelectSource },
  props: {
    labelWidth: {
      type: String,
      default: "100px"
    },
    formItem: {
      type: Array,
      default: []
    },

    formData: {
      type: Object,
      default: {}
    },
    formRules: { type: Object, default: null }
  },
  data() {
    return {
      components: { SelectSource }
    };
  },
  methods: {
    submitForm(v) {
      this.$refs["ruleform"].validate(valid => {
        if (!valid) {
          this.msgError("请完整填写必填项!");
          return;
        }
        this.$emit("sendForm", this.formDataCom);
      });
    }
  }
};
</script>
<style lang="scss">
.handle-btn {
  width: 100%;
  height: auto;
  display: flex;
  .el-form-item__content {
    margin-left: 10px !important;
  }
}
</style>