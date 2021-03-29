<template>
  <div class="task-wk">
    <div class="check_type">
      <slot name="checkType"></slot>
    </div>
    <div class="contain-wk">
      <div class="source-left">
        <slot name="sourceLeft"></slot>
      </div>
      <div class="contain-right">
        <slot name="contain"></slot>
        <!-- 表格 -->
        <el-table
          v-loading="loading"
          :data="tableData"
          style="width: 100%;flex:1;"
          ref="oTable"
          height="100%"
          min-height="100%"
          size="mini"
          border
        >
          <el-table-column
            :prop="i.dataIndex"
            :label="i.title"
            v-for="i in cols"
            :key="i.dataIndex"
            :fixed="i.dataIndex==='date'"
            align="center"
            :minWidth="i.minWidth"
          >
            <template v-if="i.children">
              <el-table-column
                :prop="c.dataIndex"
                :label="c.title"
                v-for="c in i.children"
                :key="c.dataIndex"
                :width="c.minWidth ? null : (c.width || '150')"
                :min-width="c.minWidth"
              ></el-table-column>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  props: {
    tableData: {
      type: Array,
      default: () => []
    },
    loading: {
      type: Boolean,
      default: false
    },
    cols: {
      type: Array,
      default: () => []
    }
  }
};
</script>
<style lang="scss" scoped>
.task-wk {
  width: 100%;
  height: 100%;
  display: flex;
  overflow: hidden;
  flex-direction: column;

  .contain-wk {
    display: flex;
    width: 100%;
    flex: 1;
    overflow: hidden;

    .contain-right {
      flex: 1;
      height: 100%;
      overflow: hidden;
      margin-left: 10px;
      display: flex;
      flex-direction: column;
    }
  }
}
</style>
