<!--复用表格-->
<template>
  <el-table
    v-loading="loading"
    :data="tableData"
    style="width: 100%;flex:1;"
    @selection-change="handleSelectionChange"
    :highlight-current-row="highlight"
    ref="oTable"
    :height="height"
    :border="border"
    size="mini"
  >
    <el-table-column type="selection" width="55" align="center" v-if="selection"></el-table-column>
    <template v-for="(item, index) in column">
      <el-table-column
        :key="index"
        :prop="item.value"
        :width="typeof item.width === 'undefined' ? '' : item.width"
        :label="item.label"
        :show-overflow-tooltip="item.tooltip?true:false"
        :align="item.isAlign?item.isAlign:'left'"
      >
        <template slot-scope="scope">
          <div v-if="item.action">
            <el-button
              v-for="(o, i) in item.operation"
              :key="i"
              @click="o.clickFun(scope.row)"
              style="width:100%"
              type="text"
              size="mini"
            >{{o.name}}</el-button>
          </div>
          <div v-else>
            <span v-if="!item.format">{{ scope.row[item.value] }}</span>
            <!-- 需要格式化的数据结构 -->
            <span v-else>{{ item.format(scope.row) }}</span>
          </div>
        </template>
      </el-table-column>
    </template>
  </el-table>
</template>
<script>
export default {
  props: {
    //加载中
    loading: {
      type: Boolean,
      default: false
    },
    //表格数据
    tableData: {
      type: Array,
      default: []
    },
    //是否高亮当前选中行
    highlight: {
      type: Boolean,
      default: false
    },
    //是否显示边框
    border: {
      type: Boolean,
      default: false
    },
    //固定头部高度
    height: {
      type: String,
      default: "100%"
    },
    //表头
    column: {
      type: Array,
      required: true
    },
    //多选
    selection: {
      type: Boolean,
      default: true
    }
  },

  methods: {
    handleSelectionChange(val) {
      this.$emit("coloumSelect", val);
    }
  }
};
</script>