<template>
  <!--施工单位统计-->
  <div class="task-wk equipment-style">
    <el-tabs v-model="activeName" @tab-click="handleClick">
      <el-tab-pane :label="i.name" :name="i.key" v-for="i in systemList" :key="i.key"></el-tab-pane>
    </el-tabs>
    <el-table
      v-loading="loading"
      element-loading-text="数据加载中,请稍后!"
      :data="tableData"
      ref="analysisTable"
      style="width: 100%;"
      size="mini"
      height="100%"
      border>
      <el-table-column prop="town_name" width="120" align="center">
      </el-table-column>
      <el-table-column label="合计" align="center">
        <el-table-column label="企业数量" prop="town_cnt" width="140" align="center"></el-table-column>
      </el-table-column>
      <template v-for="(val,key,index) in col">
        <el-table-column :label="val" :key="index" align="center">
          <el-table-column label="企业数量" :prop="key+'_cnt'" width="100" align="center">
            <template slot-scope="scope">
              {{ scope.row[key + '_cnt'] }}
            </template>
          </el-table-column>
          <el-table-column label="占比" width="80" align="center">
            <template slot-scope="scope">
              {{ ((scope.row[key + '_cnt'] / scope.row.town_cnt) * 100).toFixed(2) }}%
            </template>
          </el-table-column>
          <el-table-column label="设备传输率(平均)" :prop="key+'_trans'" width="130" align="center"></el-table-column>
        </el-table-column>
      </template>
    </el-table>
  </div>
</template>

<script>
import {equipConstructionList} from '@/api/currency'

export default {
  data() {
    return {
      tableData: [],
      activeName: 'outwater',
      col: {},
      sysName: 'outwater',
      loading: false,
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
        // {
        //   name: '加油站',
        //   key: 'jiayou'
        // }
      ]
    }
  },
  watch: {
    sysName: {
      handler() {
        this.tableData = [];
        this.col = [];
        this.$refs.analysisTable.bodyWrapper.scrollLeft = 0;
        this.$refs.analysisTable.bodyWrapper.scrollTop = 0;
        this.getList();
      }
    }
  },
  created() {
    this.getList();
  },
  methods: {
    getList() {
      this.loading = true;
      equipConstructionList(this.sysName).then(res => {
        if (res.code === 200) {
          this.col = res.data.head;
          this.tableData = res.data.data;

        }
        this.loading = false;
      })
    },
    handleClick(tab) {
      this.sysName = tab.name;
    }
  }
}
</script>

<style lang="scss" scoped>
.equipment-style {
  /deep/ .el-table thead.is-group th {
    background: #fff;
  }

  /deep/ .el-table thead.is-group tr:first-of-type th:first-of-type:before {
    content: '镇街';
    text-align: center;
    position: absolute;
    width: 152px;
    height: 1px;
    bottom: 27px;
    right: 10px;
  }

  /deep/ .el-table thead.is-group tr:first-of-type th:first-of-type:after {
    content: '施工单位';
    text-align: center;
    position: absolute;
    width: 152px;
    top: 13px;
    left: 8px;
  }

  /deep/ .el-table thead.is-group tr:first-of-type th:first-of-type .cell {
    position: absolute;
    top: 0;
    left: 0;
    width: 152px;
    height: 1px;
    background-color: #EBEEF5;
    display: block;
    text-align: center;
    transform: rotate(38deg);
    transform-origin: top left;
    -ms-transform: rotate(34deg);
    -ms-transform-origin: top left;
    -webkit-transform: rotate(34deg);
    -webkit-transform-origin: top left;
  }
}
</style>
