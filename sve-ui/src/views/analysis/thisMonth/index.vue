<template>
  <div class="task-wk">
    <!-- 表格 -->
    <el-table
      :data="tableData"
      style="width: 100%;flex:1;"
      :span-method="objectSpanMethod"
      ref="oTable"
      height="100%"
      border
      size="mini"
    >
      <el-table-column prop="warnLevel" label="级别" align="center">
        <template slot-scope="scope">
          {{ warnType[scope.row.warnLevel] }}
        </template>
      </el-table-column>
      <el-table-column prop="dictLabel" label="分类" align="center"></el-table-column>
      <el-table-column prop="sourceCnt" label="异常企业数" align="center"></el-table-column>
      <el-table-column prop="sourceCntRate" label="异常企业数(占比)" align="center"></el-table-column>
      <el-table-column prop="cnt" label="异常数" align="center"></el-table-column>
      <el-table-column prop="cntRate" label="异常数(占比)" align="center"></el-table-column>
    </el-table>
  </div>
</template>

<script>
import {thisMonthWarn} from '@/api/currency'
import Cookies from "js-cookie";

export default {
  created() {
    let taskName = Cookies.get("system-name");
    this.getList(taskName)
  },
  data() {
    return {
      tableData: [],
      warnType: ['', '一级', '二级', '三级','四级']
    }
  },
  methods: {
    getList(task, v = {}) {
      thisMonthWarn(task, v).then(res => {
        if (res.code === 200) {
          this.tableData = res.data;
        }
      })
    },
    flitterData(arr) {
      let spanOneArr = []
      let concatOne = 0
      arr.forEach((item, index) => {
        if (index === 0) {
          spanOneArr.push(1);
        } else {
          //name 修改
          if (item.warnLevel === arr[index - 1].warnLevel) { //第一列需合并相同内容的判断条件
            spanOneArr[concatOne] += 1;
            spanOneArr.push(0);
          } else {
            spanOneArr.push(1);
            concatOne = index;
          }
          ;
        }
      });
      return {
        one: spanOneArr,
      }
    },
    objectSpanMethod({row, column, rowIndex, columnIndex}) {
      if (columnIndex === 0) {
        // this.tableData  修改
        const _row = (this.flitterData(this.tableData).one)[rowIndex];
        const _col = _row > 0 ? 1 : 0;
        return {
          rowspan: _row,
          colspan: _col
        };
      }
    },
  }
}
</script>

<style scoped>

</style>
