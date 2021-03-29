<template>
  <div class="task-wk">
    <!-- 搜索表单 -->
    <el-form :model="queryForm" ref="queryForm" :inline="true">
      <el-form-item label="污染源" prop="sourceName">
        <el-input
          v-model="queryForm.sourceName"
          placeholder="请输入污染源"
          clearable
          size="mini"
          style="*width: 200px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="水表名称" prop="meterName">
        <el-input
          v-model="queryForm.meterName"
          placeholder="水表名称"
          clearable
          size="mini"
          style="width: 180px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="水表类型" prop="meterType">
        <el-radio-group v-model="queryForm.meterType" size="mini">
          <el-radio-button :label="0">工业用水</el-radio-button>
          <el-radio-button :label="1">污水排放</el-radio-button>
        </el-radio-group>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
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
            <el-card class="box-card el-box-card" v-for="o in props.row.elements" :key="o.facilityId+o.cpElemId">
              <el-form label-position="left">
                <el-form label-position="left" class>
                  <el-form-item label="因子类型：">
                    <span>{{ elType[o.eleTypeId] }}</span>
                  </el-form-item>
                  <el-form-item label="数采名称：">
                    <span>{{ o.collectName }}</span>
                  </el-form-item>
                  <el-form-item label="数采关联：" v-if="o.isOther">
                    <span>{{ o.srcCollectName }}</span>
                  </el-form-item>
                  <el-form-item label="因子名称：">
                    <span>{{ o.elementDesc }}</span>
                  </el-form-item>
                  <el-form-item label="因子编码：">
                    <span>{{ o.elementCode }}</span>
                  </el-form-item>
                  <el-form-item label="因子关联：" v-if="o.isOther">
                    <span>{{ o.srcElemName }}</span>
                  </el-form-item>
                </el-form>
              </el-form>
            </el-card>
          </div>
        </template>
      </el-table-column>
      <el-table-column type="index" label="序号" width="50" align="center"></el-table-column>
      <el-table-column prop="sourceName" label="污染源名称" width="220px" show-overflow-tooltip></el-table-column>
      <el-table-column prop="meterName" label="水表名称"></el-table-column>
      <el-table-column prop="meterName" label="水表类型">
        <template slot-scope="scope">
          {{ scope.row.meterType === 0 ? "工业用水" : "污水排放" }}
        </template>
      </el-table-column>
      <el-table-column prop="amount" label="水表读数">
        <template slot-scope="scope">{{ scope.row.amount || "-" }}</template>
      </el-table-column>
      <el-table-column prop="dataDate" label="读取时间">
        <template slot-scope="scope">{{ scope.row.dataDate || "-" }}</template>
      </el-table-column>
      <el-table-column prop="dateCreated" label="创建时间"></el-table-column>
    </el-table>
    <!-- 分页 -->
    <pagination
      :total="total"
      :page.sync="page.pageNum"
      :limit.sync="page.pageSize"
      @pagination="getList()"
    />
  </div>
</template>
<script>
import {lxMeterinfoList} from "@/api/lingxing";

export default {
  data () {
    return {
      queryForm: {
        meterName: null,
        sourceName: null,
        meterType: null
      },
      tableData: [],
      page: {
        pageNum: 1,
        pageSize: 30,
      },
      loading: false,
      total: 0,
      elType: {
        w100: "自来水工业用水流量",
        w101: "自来水工业用水瞬时流量",
        w900: "工业污水排放流量",
        w901: "工业污水排放瞬时流量",
      },
    };
  },
  created () {
    this.getList();
  },
  methods: {
    getList (v = {}) {
      this.loading = true;
      lxMeterinfoList(v, this.urlParameter(this.page)).then((res) => {
        if (res.code === 200) {
          this.tableData = res.data || res.rows;
          this.total = res.total;
        }
        this.loading = false;
      });
    },
    handleQuery () {
      this.page.pageNum = 1;
      this.getList(this.queryForm);
    },
    resetQuery () {
      this.queryForm = {
        meterName: null,
        sourceName: null,
        meterType: null
      };
      this.handleQuery();
    }
  },
};
</script>
<style lang="scss" scoped>
.el-show-style {
  display: flex;
  flex-wrap: wrap;

  /deep/ .el-box-card {
    flex: 1 1 280px; /*  flex-grow: 1 ，表示自动延展到最大宽度 */
    flex: 0 1 280px; /*  No stretching: */
    margin: 5px;

    .el-card__body {
      padding: 10px 15px 15px 15px;

      .el-form-item {
        margin-bottom: 0px;
        margin-right: 0px;

        .el-form-item__content {
          flex: 1;

        }
      }
    }
  }
}
</style>
