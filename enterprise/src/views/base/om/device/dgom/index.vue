<template>
  <div style="width:100%;height:100%;overflow: hidden">
    <el-table
      ref="filterTable"
      height="100%"
      :data="tableData"
      border
      size="mini"
      style="width: 100%">
      <el-table-column
        prop="checkpointdesc"
        label="排放口名称"
        width="250">
      </el-table-column>
      <el-table-column
        prop="datadevdesc"
        label="设备名称"
        width="180">
      </el-table-column>
      <el-table-column
        prop="equipmentdesc"
        label="品牌"
        width="180">
      </el-table-column>
      <el-table-column
        label="设备状态"
        width="100">
        <template slot-scope="scope">
          {{
            devstatusType[scope.row.devstatus]
          }}
        </template>
      </el-table-column>
      <el-table-column
        label="类型"
        width="140"
        align="center"
        :filters="[{ text: '水重点排污单位', value: 1 }, { text: '气重点排污单位', value: 2 }]"
        :filter-method="filterTag"
        filter-placement="bottom-end">
        <template slot-scope="scope">
          {{ scope.row.checkpointtype === 1 ? "水重点排污单位" : "气重点排污单位" }}
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import { dgomDevice } from "@/api/dgom";

export default {
  data: () => ({
    tableData: [],
    devstatusType: ["", "未联网", "已联网", "已验收", "停用"],
    page: {
      pageNum: 1,
      pageSize: 30
    }
  }),
  created() {
    this.getList();
  },
  methods: {
    getList(v = {}) {
      dgomDevice(v, this.urlParameter(this.page)).then(res => {
        if (res.code === 200) {
          this.tableData = res.rows;
        }
      });
    },
    clearFilter() {
      this.$refs.filterTable.clearFilter();
    },
    filterTag(value, row) {
      return row.checkpointtype === value;
    }
  }
};
</script>

<style lang="scss">

</style>
