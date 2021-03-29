<template>
  <div class="head-label-style">
    <h3><i class="el-icon-c-scale-to-original" style="margin-right:5px;"></i>{{ labelTree.labelHeadName }}：</h3>
    <div v-if="labelTree.children&&labelTree.children.length>0" class="labelChildren">
      <el-checkbox-group v-model="checkList" style="flex:1" size="mini" @change="onChange">
        <el-checkbox :label="j.labelHeadId" v-for="j in labelTree.children" :key="j.labelHeadId">
          {{ j.labelHeadName }}
        </el-checkbox>
      </el-checkbox-group>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    labelTree: {
      type: Object,
      default: () => ({})
    },
    oKey: {
      type: String,
      default: ''
    },
    value: {
      type: Array,
      default: () => []
    }
  },
  data() {
    return {
      checkList: this.value || []
    }
  },
  watch: {
    checkList: {
      handler(v) {
        let oV = {};
        oV[this.oKey] = v;
        this.$emit('change', oV);
      },
      immediate: true
    }
  },
  methods: {
    onChange(v) {
      let oV = {};
      oV[this.oKey] = v;
      this.$emit('change', oV);
    },
    clearList() {
      this.checkList = []
    }
  }
}
</script>
<style lang="scss">
.labelChildren {
  padding: 10px;
}
</style>
<style lang='scss' scoped>
.head-label-style {
  width: 100%;
  display: flex;
  flex-direction: column;
  overflow: hidden;
  border-bottom: 1px dotted #ddd;

  h3 {
    margin: 0;
    padding: 0;
    height: 26px;
    line-height: 26px;
    font-size: 14px;
    background: #f3f3f3;
    padding-left: 10px;
    letter-spacing: 2px;
  }
}


</style>
