<template>
  <div>
    <el-col :span="12">
      <el-checkbox
        v-model="isCheck"
        @change="chekcChange"
        style="width:100%"
        :disabled="isDisabled"
      >{{oI.tagHeadName}}{{isDisabled?'(考核名单)':''}}</el-checkbox>
    </el-col>
  </div>
</template>
<script>
export default {
  props: {
    value: {
      type: Object,
      default: () => ({}),
    },
    oI: {
      type: Object,
      default: () => ({}),
    },
    relationSourceId: {
      type: String,
    },
  },
  watch: {
    value: {
      handler() {
        this.isValue();
      },
      immediate: true,
    },
  },
  data() {
    return {
      isCheck: false,
      isDisabled: false,
    };
  },
  methods: {
    isValue() {
      if (this.value !== undefined) {
        this.isCheck = this.value.tagHeadId === this.oI.tagHeadId ? true : false;
        this.isDisabled = this.value.listType === "0" ? true : false;
      }
    },
    chekcChange(v) {
      let obj = {
        pfSourceId: this.relationSourceId,
        tagHeadId: this.oI.tagHeadId,
        isCheck: v,
        listType: "1",
      };
      this.$emit("change", obj);
    },
  },
};
</script>
