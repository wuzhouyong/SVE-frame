<template>
  <div>
    <template v-for="i in checkList">
      <ElCard :key="i.dictValue" :isIds="i" @change="elementChange" :value="itemValue(i.dictValue)"></ElCard>
    </template>
  </div>
</template>
<script>
import ElCard from "./elCard";
export default {
  components: { ElCard },
  model: {
    prop: "value",
    event: "change"
  },
  props: {
    value: {
      type: Array
    },
    checkList: {
      type: Array
    }
  },
  data() {
    return {
      elements: this.value || []
    };
  },
  methods: {
    itemValue(elementId) {
      return this.elements.find(m => m.elementId === elementId);
    },
    elementChange(v) {
      const obj = this.elements.find(p => p.elementId === v.elementId);
      obj ? Object.assign(obj, v) : this.elements.push(v);
      this.$emit("change", this.elements);
      this.$parent.$emit("validate", this.elements);
    }
  }
};
</script>