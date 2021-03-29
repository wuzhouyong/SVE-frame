<template>
  <div>
    <template v-for="i in systemList">
      <Cbox :key="i.tagHeadId" :oI="i" v-bind="$attrs" @change="cBoxChange" :value="itemValue(i.tagHeadId)"></Cbox>
    </template>
  </div>
</template>
<script>
import Cbox from "./cbox";
export default {
  model: {
    prop: "value",
    event: "change",
  },
  props: {
    value: {
      type: Array,
    },
    systemList: {
      type: Array,
    },
  },
  data() {
    return {
      sysArray: this.value || [],
    };
  },
  watch: {
    value(v) {
      this.sysArray = v;
    },
  },
  components: { Cbox },
  methods: {
    itemValue(id) {
      return this.sysArray.find((m) => m.tagHeadId === id);
    },
    cBoxChange(v) {
      const obj = this.sysArray.find((p) => p.tagHeadId === v.tagHeadId);
      obj ? Object.assign(obj, v) : this.sysArray.push(v);
      // this.sysArray = this.sysArray.filter(i => {
      //   return i.isCheck === true;
      // });
      this.$emit("change", this.sysArray);
    },
  },
};
</script>