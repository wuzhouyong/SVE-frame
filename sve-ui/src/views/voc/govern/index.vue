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
      <el-form-item label="治理设施名称">
        <el-input
          v-model="queryForm.facilityName"
          placeholder="请输入治理设施名称"
          clearable
          size="mini"
          style="width: 180px"
          @keyup.enter.native="handleQuery"
        />
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
          <div>
            <p
              class="relation-style"
            >治理设施关联生产线：{{ props.row.pidName ? props.row.pidName.join("、") : "-" }}</p>
            <div v-for="i in props.row.techno" :key="i.techId">
              <el-card class="box-card tech-style">
                <div slot="header" class="clearfix">
                  <span>
                    {{ techType[i.techType] || "-" }}
                    <span
                      style="padding-left:5px;"
                      v-if="i.memo"
                    >( 备注：{{ i.memo }} )</span>
                  </span>
                </div>
                <div class="el-show-style">
                  <el-card class="box-card el-box-card" v-for="o in i.entity" :key="o.facilityId">
                    <el-form label-position="left">
                      <el-form label-position="left" class>
                        <el-form-item label="因子类型：">
                          <span>{{ elType[o.eleTypeId] }}</span>
                        </el-form-item>
                        <el-form-item label="因子名称：">
                          <span>{{ o.eleDesc }}</span>
                        </el-form-item>
                        <el-form-item label="因子编码：">
                          <span>{{ o.eleCode }}</span>
                        </el-form-item>
                        <el-form-item label="数采名称：">
                          <span>{{ o.cpName }}</span>
                        </el-form-item>
                        <el-form-item label="安装位置：">
                          <span>{{ o.installLocation || "-" }}</span>
                        </el-form-item>
                      </el-form>
                    </el-form>
                  </el-card>
                </div>
              </el-card>
            </div>
          </div>
        </template>
      </el-table-column>
      <el-table-column prop="fullName" label="污染源名称"></el-table-column>
      <el-table-column prop="facilityName" label="治理设施名称"></el-table-column>
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
import { FacilityList } from "@/api/voc";

export default {
  data() {
    return {
      queryForm: { pageNum: 1, pageSize: 30 },
      tableData: [],
      loading: false,
      total: 0,
      techType: {
        voc_uvgj: "UV光解",
        voc_hxt: "活性炭吸附",
        voc_chrs: "催化燃烧",
        voc_pl: "喷淋",
        voc_dlz: "等离子",
        voc_lnhs: "冷凝回收",
        voc_jdxf: "静电吸附"
      },
      elType: {
        e700: "治理设施用电量",
        e701: "治理设施功率",
        e702: "VOCs治理设施电流",
        g184: "活性炭吸附进出口压力差",
        g181: "处理湿度",
        g180: "处理温度"
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    getList() {
      FacilityList(this.queryForm).then((res) => {
        if (res.code === 200) {
          this.tableData = res.data || res.rows;
          this.total = res.total;
        }
      });
    },
    handleQuery() {
      this.queryForm.pageNum = 1;
      this.getList();
    }
  }
};
</script>
<style lang="scss">
.tech-style {
  margin-bottom: 5px;
}

.el-show-style {
  display: flex;
  flex-wrap: wrap;

  .el-box-card {
    flex: 1 1 280px; /*  flex-grow: 1 ，表示自动延展到最大宽度 */
    flex: 0 1 280px; /*  No stretching: */
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

p.relation-style {
  font-weight: bold;
  letter-spacing: 2px;
  font-size: 14px;
  margin: 5px 0;
  padding-left: 5px;
}
</style>
