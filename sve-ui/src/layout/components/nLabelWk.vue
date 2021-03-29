<template>
  <div>
    <template v-for="i in treeData">
      <n-label :oKey="'label_'+i.labelHeadId" :labelTree="i" :key="i.labelHeadId"
               :value="assignData['label_'+i.labelHeadId]" :ref="'islabel_'+i.labelHeadId"
               @change="onChange"></n-label>
    </template>
  </div>
</template>

<script>
import nLabel from './nLabel'
import {mapState} from "vuex";

export default {
  model: {
    prop: "value",
    event: "change",
  },
  props: {
    treeData: {
      type: Array,
      default: () => []
    },
    value: {
      type: Object,
      default: () => ({})
    }
  },
  computed: {
    ...mapState("label", ["assignData"])
  },
  components: {
    nLabel
  },
  watch: {
    value(v) {
      this.labelObj = v;
    },
  },
  data() {
    return {
      labelObj: this.value || {},
      isNull: false,
    }
  },
  methods: {
    onChange(v) {
      this.labelObj = Object.assign({}, this.labelObj, v);
      this.$emit('change', this.labelObj);
    },
    clearLabel() {
      this.treeData.forEach(i => {
        let o = "islabel_" + i.labelHeadId
        this.$refs[o][0].clearList()
      })

    }
  }
}
</script>

<style lang='scss' scoped>

</style>
