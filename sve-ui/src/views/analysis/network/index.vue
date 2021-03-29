<template>
  <div class="task-wk">
    <p class="p_memo">
      注：联网数位为企业联网完成+待验收+已验收
      <el-button type="warning" icon="el-icon-upload" style="margin-left:20px;" @click="exportTables">导出全部不合格企业
      </el-button>
    </p>
    <!-- 表格 -->
    <el-table
      :data="tableData"
      style="width: 100%;flex:1;"
      ref="oTable"
      height="100%"
      border
      size="mini"
    >
      <el-table-column prop="townname" label="镇街" align="center"></el-table-column>
      <el-table-column prop="source_cnt" label="企业数" align="center"></el-table-column>
      <el-table-column prop="network_cnt" label="联网数" align="center"></el-table-column>
      <el-table-column prop="nocnt" label="传输率不合格企业数（近七天平均）" align="center">
        <template slot-scope="scope">
          <el-link type="primary" style="font-size:12px;" @click="clickNum(scope.row)">{{ scope.row.nocnt }}</el-link>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog
      title="传输率不合格企业列表"
      :visible.sync="visible"
      width="30%">
      <n-source :towncode="towncode" v-if="visible"></n-source>
    </el-dialog>
  </div>
</template>

<script>
import {stTownNetwork, stExportNetwork} from '@/api/currency'
import Cookies from "js-cookie";
import nSource from './nSource'

export default {
  components: {nSource},
  created() {
    let taskName = Cookies.get("system-name");
    this.getList(taskName)
  },
  data() {
    return {
      tableData: [],
      visible: false,
      towncode: ''
    }
  },
  methods: {
    getList(task, v = {}) {
      stTownNetwork(task, v).then(res => {
        if (res.code === 200) {
          this.tableData = res.data;
        }
      })
    },
    clickNum(v) {
      this.visible = true;
      this.towncode = v.towncode;
    },
    exportTables() {
      this.$confirm("是否导出数据?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          console.log('22')
          let taskName = Cookies.get("system-name");
          return stExportNetwork(taskName, {});
        })
        .then((response) => {
          this.download(response.msg);
        })
        .catch(function () {
        });
    },
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
