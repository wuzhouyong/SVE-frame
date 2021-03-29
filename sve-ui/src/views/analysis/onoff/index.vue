<template>
  <div class="task-wk">
    <el-table
      :data="tableData"
      height="100%"
      size="mini"
      border
      style="width: 100%">
      <el-table-column
        prop="town_name"
        label="镇街">
      </el-table-column>
      <el-table-column
        prop="cnt"
        label="数采总数">
      </el-table-column>
      <el-table-column
        prop="online_cnt"
        label="在线个数">
      </el-table-column>
      <el-table-column
        prop="offline_cnt"
        label="离线个数">
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import {stTownChcekOnOff} from '@/api/currency'
import Cookies from "js-cookie";

export default {
  created() {
    let systemKey = Cookies.get("system-name");
    this.getList(systemKey);
  },
  data() {
    return {
      tableData: []
    }
  },
  methods: {
    getList(v) {
      stTownChcekOnOff(v).then(res => {
        if (res.code === 200) {
          this.tableData = res.data;
        }
      })
    }

  }
}
</script>

<style lang='scss' scoped>

</style>
