<template>
  <!--  异常处理情况统计-->
  <div class="task-wk">
    <el-tabs v-model="activeName" @tab-click="handleClick">
      <el-tab-pane :label="i.name" :name="i.key" v-for="i in systemList" :key="i.key"></el-tab-pane>
    </el-tabs>
    <p class="p_memo">
      注：本数据统计以近三十天为一个月。
    </p>
    <el-table
      :data="tableData"
      style="width: 100%;flex:1"
      size="mini"
      height="100%"
      border>
      <el-table-column
        prop="townName"
        label="镇街"
        width="180">
      </el-table-column>
      <el-table-column
        prop="cnt"
        label="企业数"
        width="180">
      </el-table-column>
      <el-table-column
        label="上月异常数" align="center">
        <el-table-column
          prop="lastcnt1"
          label="一级" align="center">
        </el-table-column>
        <el-table-column
          prop="lastcnt2"
          label="二级" align="center">
        </el-table-column>
        <el-table-column
          prop="lastcnt3"
          label="三级" align="center">
        </el-table-column>
      </el-table-column>
      <el-table-column
        label="本月异常数" align="center">
        <el-table-column
          prop="currcnt1"
          label="一级" align="center">
        </el-table-column>
        <el-table-column
          prop="currcnt2"
          label="二级" align="center">
        </el-table-column>
        <el-table-column
          prop="currcnt3"
          label="三级" align="center">
        </el-table-column>
      </el-table-column>
      <el-table-column
        label="上月核减异常数" align="center">
        <el-table-column
          prop="lastproccnt1"
          label="一级" align="center">
        </el-table-column>
        <el-table-column
          prop="lastproccnt2"
          label="二级" align="center">
        </el-table-column>
        <el-table-column
          prop="lastproccnt3"
          label="三级" align="center">
        </el-table-column>
      </el-table-column>
      <el-table-column
        label="本月核减异常数" align="center">
        <el-table-column
          prop="currproccnt1"
          label="一级" align="center">
        </el-table-column>
        <el-table-column
          prop="currproccnt2"
          label="二级" align="center">
        </el-table-column>
        <el-table-column
          prop="currproccnt3"
          label="三级" align="center">
        </el-table-column>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import {abSourceTownCount} from '@/api/currency';

export default {
  data() {
    return {
      tableData: [],
      activeName: '1012',
      systemList: [
        {
          name: '排水户',
          key: '1012'
        },
        {
          name: '零星废水',
          key: '1003'
        },
        {
          name: '四大流域',
          key: '1002'
        },
        {
          name: '金属表面处理',
          key: '1015'
        },
        {
          name: '造纸行业',
          key: '1019'
        },
        {
          name: 'VOCs',
          key: '1004'
        },
        {
          name: '汽修行业',
          key: '1013'
        },
        {
          name: '餐饮行业',
          key: '1006'
        },
        {
          name: '加油站',
          key: '1014'
        }
      ]
    }
  },
  created() {
    this.getList();
  },
  watch: {
    activeName: {
      handler() {
        this.getList();
      }
    }
  },
  methods: {
    getList() {
      abSourceTownCount(this.activeName).then(res => {
        if (res.code === 200) {
          this.tableData = res.data;
        }
      })
    },
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
</style>
