<template>
  <div>
    <el-tree
      :data="data"
      show-checkbox
      @check="checkChange"
      :getCheckedKeys="true"
      node-key="id"
      ref="tree"
      highlight-current>
    </el-tree>
  </div>
</template>

<script>
export default {
  data() {
    return {
      data: [{
        id: 1,
        label: '一级 1',
        children: [{
          id: 4,
          label: '二级 1-1',
          children: [{
            id: 9,
            label: '三级 1-1-1'
          }, {
            id: 10,
            label: '三级 1-1-2'
          }]
        }]
      }]
    }
  },
  methods: {
    checkChange(data, checked, indeterminate) {
      console.log(data, checked, indeterminate);
    }
  }
}
</script>

<style lang='scss' scoped>

</style>
