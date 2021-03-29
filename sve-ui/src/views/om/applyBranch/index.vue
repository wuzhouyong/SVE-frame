<template>
  <div class="task-wk">
    <!-- 搜索表单 -->
    <el-form :model="queryForm" ref="queryForm" :inline="true">
      <!--      <el-form-item label="污染源" prop="sourceId">-->
      <!--        <el-select v-model="queryForm.sourceId" placeholder="请选择污染源" style="width:300px" filterable size="mini">-->
      <!--          <el-option :label="i.zdatasourname" :value="i.zdatasourid" v-for="i in sourceList"-->
      <!--                     :key="i.zdatasourid"></el-option>-->
      <!--        </el-select>-->
      <!--      </el-form-item>-->
      <el-form-item label="污染源" prop="sourceName">
        <el-input v-model="queryForm.sourceName" style="width:100%" size="mini"></el-input>
      </el-form-item>
      <el-form-item label="镇街" prop="townId" v-if="!isTown">
        <el-select v-model="queryForm.townId" size="mini" placeholder="请选择镇街" style="width:100%">
          <el-option :label="i.name" :value="i.id" v-for="i in townList" :key="i.id"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="行业" prop="industryId">
        <el-select v-model="queryForm.industryId" placeholder="请选择行业" size="mini" style="width:100%">
          <el-option :label="i.name" :value="i.id" v-for="i in industrysList" :key="i.id"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="申请状态" prop="status">
        <el-select v-model="queryForm.status" placeholder="请选择申请状态" size="mini" style="width:100%">
          <el-option :label="i.name" :value="i.id" v-for="i in statusList" :key="i.id"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <el-table
      :data="tableData"
      v-loading="loading"
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
        prop="townName"
        label="镇街"
        width="100">
      </el-table-column>
      <el-table-column
        prop="industryName"
        label="行业"
        width="140">
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
        prop="muApprovedBy"
        label="市局确认人"
        width="120px">
      </el-table-column>
      <el-table-column
        prop="muApprovedDate"
        label="市局确认时间"
        width="140px">
      </el-table-column>
      <el-table-column
        prop="muApprovalDesc"
        label="市局确认意见"
        min-width="140px"
        show-overflow-tooltip>
      </el-table-column>
      <el-table-column
        label="操作"
        width="80px"
        align="center"
      >
        <template slot-scope="scope">
          <el-link type="primary" :underline="false" style="font-size:12px;" @click="applyMsg(scope.row)">审核</el-link>
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
      title="分局审批"
      :visible.sync="applyVisible"
      custom-class="applyDialog"
      width="800px">
      <n-form :detailsData="detailsData" v-if="applyVisible" :rowData="rowData" ref="nForm"
              @complete="complete"></n-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="success" @click="subMit(2)">同 意</el-button>
        <el-button type="danger" @click="subMit(3)">退 回</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {dgomTowns, dgomIndustrys, dgomEnterprise, dgomModel, dgomNetworkingList} from "@/api/om";
import nForm from "./nForm";
import {mapState} from "vuex";
import {fun_date} from "@/utils/ruoyi";

export default {
  components: {nForm},
  data: () => ({
    tableData: [],
    statusType: ["", "待审核", "分局同意", "分局拒绝", "市局同意", "市局退回", "已联网"],
    statusList: [
      {
        name: "待审核",
        id: 1
      },
      {
        name: "市局退回",
        id: 5
      }
    ],
    //查询表单
    queryForm: {
      sourceName: null,
      townId: null,
      industryId: null,
    },
    // 分页
    page: {
      pageNum: 1,
      pageSize: 30
    },
    townCode: null,
    total: 0,
    townList: [],
    industrysList: [],
    sourceList: [],
    loading: false,
    applyVisible: false,
    detailsData: {},
    rowData: {},
    isTown: false,
  }),
  computed: {
    ...mapState({
      regions: state => state.user.regions
    })
  },
  watch: {
    regions: {
      handler (v) {
        let newVal = v.replace(/[']/g, "");
        if (newVal !== "4419" && newVal !== "0") {
          this.isTown = true;
          this.queryForm.townCode = newVal;
          this.townCode = newVal;
        } else {
          this.isTown = false;
          this.queryForm.townCode = null;
        }
        this.getList(this.queryForm);
      },
      immediate: true
    }
  },
  created () {
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
    });
  },
  methods: {
    getList (v = {}) {
      this.loading = true;
      let def = {status: "1,5"};
      def = Object.assign(def, v);
      dgomNetworkingList(def, this.urlParameter(this.page)).then(res => {
        this.tableData = res.rows;
        this.total = res.total;
        this.loading = false;
      });
    },
    handleQuery () {
      this.page.pageNum = 1;
      this.getList(this.queryForm);
    },
    resetQuery () {
      this.queryForm = {
        sourceId: null,
        townId: null,
        industryId: null,
      };
      if (this.townCode !== "4419" && this.townCode !== "0") {
        this.queryForm.townCode = this.townCode;
      }
      this.handleQuery();
    },
    applyMsg (v) {
      this.rowData = v;
      dgomModel(v.applyId).then(res => {
        if (res.code === 200) {
          this.detailsData = res.data;
          this.applyVisible = true;
        }
      });
    },
    subMit (v) {
      this.$refs.nForm.submitForm(v);
    },
    complete () {
      this.getList();
      this.applyVisible = false;
    }
  }
};
</script>

<style lang="scss" scoped>
.task-wk {
  /deep/ .applyDialog {
    height: calc(100% - 12vh) !important;
    overflow: hidden;
    display: flex;
    flex-direction: column;

    .el-dialog__body {
      flex: 1;
      overflow-y: auto;
      padding: 5px 10px;
    }
  }
}
</style>
