<template>
  <!--  排放分析-->
  <div class="task-wk task-issue">
    <el-tabs v-model="oType">
      <el-tab-pane label="涉水" name="water"></el-tab-pane>
      <el-tab-pane label="涉气" name="gas"></el-tab-pane>
    </el-tabs>
    <el-tabs v-model="activeName" tab-position="left" class="tab_btm">
      <el-tab-pane :label="i.name" :name="i.key" v-for="i in systemList" :key="i.key"></el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
export default {
  data() {
    return {
      tableData: [],
      oType: 'water',
      activeName: 'guocheng',
      systemList: [],
      water: [
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
          name: '水重点',
          key: 'dgom'
        }
      ],
      gas: [
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
  watch: {
    oType: {
      handler(v) {
        if (v === 'water') {
          this.systemList = this.water;
          this.activeName = 'guocheng';
        } else {
          this.systemList = this.gas;
          this.activeName = 'outwater';
        }
      },
      immediate: true
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
  margin-bottom: 5px;
}

.task-issue {
  width: 100%;
  height: 100%;
  overflow: hidden;
  display: flex;
  flex-direction: column;

  .tab_btm {
    width: 100%;
    flex: 1;
    overflow: hidden;
  }
}
</style>
