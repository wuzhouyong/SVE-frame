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
        width="250">
      </el-table-column>
      <el-table-column
        prop="elementdesc"
        label="因子名称"
        width="180">
      </el-table-column>
      <el-table-column
        prop="elementid"
        label="因子编码"
        width="90">
      </el-table-column>
      <el-table-column
        prop="minvalue"
        label="最小值"
        width="80">
      </el-table-column>
      <el-table-column
        prop="maxvalue"
        label="最大值"
        width="80">
      </el-table-column>
      <el-table-column
        prop="rangemin"
        label="最小量程"
        width="80">
      </el-table-column>
      <el-table-column
        prop="rangemax"
        label="最大量程"
        width="80">
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
import { dgomElement } from "@/api/dgom";

export default {
  data: () => ({
    tableData: [],
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
      dgomElement(v, this.urlParameter(this.page)).then(res => {
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
