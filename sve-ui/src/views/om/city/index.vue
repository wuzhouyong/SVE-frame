<template>
  <div class="task-wk">
    <!-- 搜索表单 -->
    <el-form :model="queryForm" ref="queryForm" :inline="true" class="qixiu-serchForm">
      <el-form-item label="污染源" prop="sourceId">
        <el-select v-model="queryForm.sourceId" placeholder="请选择污染源" style="width:300px" filterable size="mini">
          <el-option :label="i.zdatasourname" :value="i.zdatasourid" v-for="i in sourceList"
                     :key="i.zdatasourid"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="镇街" prop="townId">
        <el-select v-model="queryForm.townId" size="mini" placeholder="请选择镇街" style="width:100%">
          <el-option :label="i.name" :value="i.id" v-for="i in townList" :key="i.id"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="行业" prop="industryId">
        <el-select v-model="queryForm.industryId" placeholder="请选择行业" size="mini" style="width:100%">
          <el-option :label="i.name" :value="i.id" v-for="i in industrysList" :key="i.id"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <el-table
      :data="tableData"
      border
      size="mini"
      height="100%"
      style="width: 100%">
      <el-table-column
        prop="sourceName"
        label="污染源"
        width="280px"
        show-overflow-tooltip>
      </el-table-column>
      <el-table-column
        prop="townname"
        label="镇街"
        width="100">
      </el-table-column>
      <el-table-column
        prop="industryname"
        label="行业"
        width="120">
      </el-table-column>
      <el-table-column
        label="申请状态"
        width="100px">
        <template slot-scope="scope">
          {{ statusType[scope.row.status] }}
        </template>
      </el-table-column>
      <el-table-column
        prop="applyDate"
        label="申请时间"
        width="140px">
      </el-table-column>
      <el-table-column
        prop="applyPerson"
        label="申请人"
        width="120px">
      </el-table-column>
      <el-table-column
        prop="approvedby"
        label="分局经办人"
        width="120px">
      </el-table-column>
      <el-table-column
        prop="approveddate"
        label="分局批复时间"
        width="140px">
      </el-table-column>
      <el-table-column
        prop="approveddesc"
        label="分局批复意见"
      >
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-link type="primary" @click="examine(scope.row)">审核</el-link>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <pagination
      :total="total"
      :page.sync="page.pageNum"
      :limit.sync="page.pageSize"
      @pagination="getList(queryForm)"
    />
    <el-dialog
      title="联网申请市局审核"
      :visible.sync="applyVisible"
      custom-class="applyDialog"
      width="800px">
      <applyTem :detailsData="detailsData" v-if="applyVisible"></applyTem>
      <span slot="footer" class="dialog-footer">
         <el-button type="primary">提 交</el-button>
        <el-button>取 消</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { dgomTowns, dgomIndustrys, dgomEnterprise, dgomModel } from "@/api/om";
import applyTem from "./apply"

export default {
  components: { applyTem },
  data: () => ({
    tableData: [],
    statusType: ["", "待审核", "分局同意", "分局拒绝", "市局同意", "市局退回", "已联网"],
    //查询表单
    queryForm: {},
    // 分页
    page: {
      pageNum: 1,
      pageSize: 30
    },
    total: 0,
    townList: [],
    industrysList: [],
    sourceList: [],
    applyVisible: false,
    detailsData: {}
  }),
  created() {
    dgomTowns().then(res => {
      if (res.code === 200) {
        this.townList = res.data;
      }
    });
    dgomIndustrys().then(res => {
      if (res.code === 200) {
        this.industrysList = res.data;
      }
    });
    dgomEnterprise().then(res => {
      if (res.code === 200) {
        this.sourceList = res.data;
      }
    })
  },
  methods: {
    getList() {},
    handleQuery() {},
    resetQuery() {},
    examine(v) {
      dgomModel(v.applyId).then(res => {
        if (res.code === 200) {
          this.detailsData = res.data;
          this.applyVisible = true;
        }
      });
    }
  }
}
</script>

<style lang="scss">
.applyDialog {
  height: calc(100% - 12vh) !important;
  overflow-y: auto;
}
</style>
