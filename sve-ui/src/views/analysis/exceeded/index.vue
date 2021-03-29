<template>
  <!--  超标排放统计分析-->
  <div class="task-wk">
    <el-tabs v-model="activeName" @tab-click="handleClick">
      <el-tab-pane :label="i.name" :name="i.key" v-for="i in systemList" :key="i.key"></el-tab-pane>
    </el-tabs>
    <p class="p_memo">
<!--      注：本数据统计以近三十天为一个月。-->
    </p>
    <el-table
      :data="tableData"
      style="width: 100%;flex:1"
      size="mini"
      height="100%"
      border>
      <el-table-column
        prop="date"
        label="镇街"
        width="180">
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  data() {
    return {
      tableData: [],
      activeName: 'outwater',
      systemList: [
        {
          name: '排水户',
          key: 'outwater'
        },
        {
          name: '零星废水',
          key: 'lingxing'
        },
        {
          name: '四大流域',
          key: 'guocheng'
        },
        {
          name: '金属表面处理',
          key: 'jinshu'
        },
        {
          name: '造纸行业',
          key: 'zaozhi'
        },
        {
          name: 'VOCs',
          key: 'voc'
        },
        {
          name: '汽修行业',
          key: 'qixiu'
        },
        {
          name: '餐饮行业',
          key: 'youyan'
        },
        {
          name: '加油站',
          key: 'jiayou'
        }
      ]
    }
  },
  methods: {
    handleClick(tab) {
      this.activeName = tab.name;
    }
  }
}
</script>

<style lang="scss" scoped>
.p_memo {
  color: #e6a23c;
  margin: 0;
  padding: 0;
  font-size: 12px;
  margin-bottom:5px;
}
</style>
