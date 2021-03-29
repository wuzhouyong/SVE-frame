<template>
  <div>
    <template v-for="i in systemList">
      <BranchInner
        :key="i.tagHeadId"
        :oI="i"
        v-bind="$attrs"
        @change="cBoxChange"
        :value="itemValue(i.tagHeadId)"
      ></BranchInner>
    </template>
  </div>
</template>
<script>
import BranchInner from "./branchinner";
export default {
  components: { BranchInner },
  props: {
    systemList: {
      type: Array,
      default: () => [],
    },
    value: {
      type: Array,
      default: () => [],
    },
  },
  watch: {
    value(v) {
      this.sysArray = v;
    },
  },
  data() {
    return {
      sysArray: this.value || [],
    };
  },
  methods: {
    itemValue(id) {
      return this.sysArray.find((m) => m.tagHeadId === id);
    },
    cBoxChange(v) {
      const obj = this.sysArray.find((p) => p.tagHeadId === v.tagHeadId);
      obj ? Object.assign(obj, v) : this.sysArray.push(v);
      this.$emit("change", this.sysArray);
    },
  },
};
</script>