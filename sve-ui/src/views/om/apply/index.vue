<template>
  <div class="task-wk">
    <!-- 搜索表单 -->
    <el-form :model="queryForm" ref="queryForm" :inline="true">
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
        <el-button
          type="danger"
          icon="el-icon-check"
          size="mini"
          :disabled="!multiple"
          v-hasPermi="['dgom:onDuty:fix']"
          @click="onDutyFix"
        >值守确认
        </el-button>
      </el-form-item>
    </el-form>
    <el-table
      :data="tableData"
      @selection-change="handleSelectionChange"
      v-loading="loading"
      @row-dblclick="see"
      border
      size="mini"
      height="100%"
      style="width: 100%">
      <el-table-column
        align="center"
        type="selection"
        width="55">
      </el-table-column>
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
        prop="approvedBy"
        label="分局经办人"
        width="120px">
      </el-table-column>
      <el-table-column
        prop="approvedDate"
        label="分局批复时间"
        width="140px">
      </el-table-column>
      <el-table-column
        prop="approvalDesc"
        label="分局批复意见"
        min-width="140px"
        show-overflow-tooltip
      >
      </el-table-column>
      <el-table-column
        prop="muApprovedBy"
        label="市局确认人"
        width="100px">
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
    </el-table>
    <!-- 分页 -->
    <pagination
      :total="total"
      :page.sync="page.pageNum"
      :limit.sync="page.pageSize"
      @pagination="getList(queryForm)"
    />
    <el-dialog
      title="查看详情"
      :visible.sync="applyVisible"
      custom-class="applyDialog"
      width="800px">
      <seeTem :detailsData="detailsData" v-if="applyVisible" :rowData="rowData"></seeTem>
    </el-dialog>
    <el-dialog
      title="值守确认"
      :visible.sync="dutyVisible"
      width="600px">
      <el-form :model="dutyForm" label-width="140px" style="height:160px" ref="dutyForm" :rules="rules">
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="值守人员" prop="connectedBy">
            <el-input v-model="dutyForm.connectedBy" size="mini"></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="值守描述" prop="connectedDesc">
            <el-input v-model="dutyForm.connectedDesc" size="mini" type="textarea" :rows="4"></el-input>
          </el-form-item>
        </el-col>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dutyFix">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {dgomTowns, dgomIndustrys, dgomEnterprise, dgomModel, dgomNetworkingList, applyMunicipalDuty} from "@/api/om";
import seeTem from "./see";
import {mapState} from "vuex";
import {fun_date} from "@/utils/ruoyi";

export default {
  components: {seeTem},
  data: () => ({
    tableData: [],
    statusType: ["", "待审核", "分局同意", "分局拒绝", "市局同意", "市局退回", "已联网"],
    statusList: [
      {
        name: "待审核",
        id: 1
      },
      {
        name: "分局同意",
        id: 2
      },
      {
        name: "分局拒绝",
        id: 3
      },
      {
        name: "市局同意",
        id: 4
      },
      {
        name: "市局退回",
        id: 5
      }, {
        name: "已联网",
        id: 6
      }
    ],
    //查询表单
    queryForm: {
      sourceName: null,
      townId: null,
      industryId: null,
      status: null,
    },
    // 分页
    page: {
      pageNum: 1,
      pageSize: 30
    },
    total: 0,
    townCode: null,
    dutyVisible: false,
    townList: [],
    industrysList: [],
    sourceList: [],
    loading: false,
    applyVisible: false,
    detailsData: {},
    rowData: {},
    selectRows: [],
    dutyForm: {
      connectedBy: null,
      connectedDesc: null
    },
    rules: {
      connectedBy: [
        {required: true, message: "值守人员必填", trigger: "blur"}
      ],
      connectedDesc: [
        {required: true, message: "分值守描述必填", trigger: "blur"}
      ],
    },
    isTown: false,
  }),
  computed: {
    ...mapState({
      regions: state => state.user.regions
    }),
    multiple () {
      if (this.selectRows.length > 0) {
        if (this.selectRows.every(i => i.status === 4)) {
          return true;
        } else {
          return false;
        }
      } else {
        return false;
      }
    }
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
      dgomNetworkingList(v, this.urlParameter(this.page)).then(res => {
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
        sourceName: null,
        townId: null,
        industryId: null,
        status: null,
      };
      if (this.townCode !== "4419" && this.townCode !== "0") {
        this.queryForm.townCode = this.townCode;
      }
      this.handleQuery();
    },
    handleSelectionChange (v) {
      this.selectRows = v;
    },
    see (v) {
      this.rowData = v;
      dgomModel(v.applyId).then(res => {
        if (res.code === 200) {
          this.detailsData = res.data;
          this.applyVisible = true;
        }
      });
    },
    onDutyFix () {
      this.dutyVisible = true;
    },
    dutyFix () {
      this.$refs["dutyForm"].validate((valid) => {
        if (!valid) return;
        let ids = [];
        this.selectRows.forEach(i => {
          ids.push(i.applyId);
        });
        this.dutyForm.applyIds = ids;
        applyMunicipalDuty(this.dutyForm).then(res => {
          if (res.code === 200) {
            this.msgSuccess("操作成功");
            this.dutyVisible = false;
            this.handleQuery();
          }
        });
      });
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
      overflow: hidden;
      padding: 5px 10px;
    }
  }
}

</style>
