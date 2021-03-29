<template>
  <div class="task-wk">
    <!-- 搜索表单 -->
    <el-form :model="queryForm" ref="queryForm" :inline="true" class="voc-serchForm">
      <el-form-item label="污染源">
        <el-input
          v-model="queryForm.fullName"
          placeholder="请输入污染源"
          clearable
          size="mini"
          style="width: 200px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="废水收集池(桶)">
        <el-input
          v-model="queryForm.storageName"
          placeholder="废水收集池(桶)"
          clearable
          size="mini"
          style="width: 180px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="储罐类型">
        <el-select
          v-model="queryForm.storageType"
          placeholder="请选择储罐类型"
          clearable
          :style="{width: '100%'}"
          size="mini"
        >
          <el-option
            v-for="i in storageType"
            :key="i.dictValue"
            :label="i.dictLabel"
            :value="i.dictValue"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
      </el-form-item>
    </el-form>
    <!-- 表格 -->
    <el-table
      v-loading="loading"
      :data="tableData"
      style="width: 100%;flex:1"
      ref="oTable"
      height="100%"
      @row-click="(row)=>$refs.oTable.toggleRowExpansion(row)"
      border
    >
      <el-table-column type="expand">
        <template slot-scope="props">
          <div class="el-show-style">
            <el-card class="box-card el-box-card" v-for="o in props.row.entity" :key="o.facilityId">
              <el-form label-position="left">
                <el-form label-position="left" class>
                  <el-form-item label="因子类型：">
                    <span>{{elType[o.eleTypeId]}}</span>
                  </el-form-item>
                  <el-form-item label="因子名称：">
                    <span>{{o.eleDesc}}</span>
                  </el-form-item>
                  <el-form-item label="因子编码：">
                    <span>{{o.eleCode}}</span>
                  </el-form-item>
                  <el-form-item label="数采名称：">
                    <span>{{o.cpName}}</span>
                  </el-form-item>
<!--                  <el-form-item label="安装位置：">-->
<!--                    <span>{{o.installLocation||'-'}}</span>-->
<!--                  </el-form-item>-->
                </el-form>
              </el-form>
            </el-card>
          </div>
        </template>
      </el-table-column>
      <el-table-column prop="fullName" label="污染源名称" width="220px" show-overflow-tooltip></el-table-column>
      <el-table-column prop="storageName" label="废水收集池(桶)名称" width="220px" show-overflow-tooltip></el-table-column>
      <el-table-column prop="storageType" label="储罐类型" width="100px">
        <template slot-scope="scope">{{tankName[scope.row.storageType]}}</template>
      </el-table-column>
      <!-- 水量预警线/废水收集桶高度 -->
      <el-table-column prop="storageType" label="预警桶占比(%)" width="140px">
        <template slot-scope="scope">
          <span>{{(Number(scope.row.warnRate)/Number(scope.row.maxHeight)).toFixed(2)}}</span>
        </template>
      </el-table-column>
      <el-table-column prop="fullVolume" label="容积(L)" width="100px"></el-table-column>
      <el-table-column prop="sectionArea" label="截面积(㎡)" width="100px"></el-table-column>
      <el-table-column prop="density" label="密度(g/cm³)" width="100px"></el-table-column>
      <el-table-column prop="memo" label="备注"></el-table-column>
    </el-table>
    <!-- 分页 -->
    <pagination
      :total="total"
      :page.sync="queryForm.pageNum"
      :limit.sync="queryForm.pageSize"
      @pagination="getList()"
    />
  </div>
</template>
<script>
import { lxStorageList } from "@/api/lingxing";
export default {
  data() {
    return {
      queryForm: {
        pageNum: 1,
        pageSize: 30,
      },
      tableData: [],
      loading: false,
      total: 0,
      elType: {
        we11: "水池液位高度",
        we00: "零星废水储量",
      },
      tankName: {
        cct: "储存桶",
        zjc: "自建池",
        qt: "其他",
      },
      storageType: [],
    };
  },
  created() {
    this.getList();
    this.getDicts("lx_storage_type").then((res) => {
      this.storageType = res.data;
    });
  },
  methods: {
    getList() {
      lxStorageList(this.queryForm).then((res) => {
        if (res.code === 200) {
          this.tableData = res.data || res.rows;
          this.total = res.total;
        }
      });
    },
    handleQuery() {
      this.queryForm.pageNum = 1;
      this.getList();
    },
  },
};
</script>
<style lang="scss">
.el-show-style {
  display: flex;
  flex-wrap: wrap;
  .el-box-card {
    flex: 1 1 240px; /*  flex-grow: 1 ，表示自动延展到最大宽度 */
    flex: 0 1 240px; /*  No stretching: */
    margin: 5px;
    .el-card__body {
      padding: 10px 15px 15px 15px;
      .el-form-item {
        margin-bottom: 0px;
        margin-right: 0px;
      }
    }
  }
}
</style>
