<template>
  <div class="task-wk">
    <el-form :model="queryForm" ref="queryForm" :inline="true">
      <el-form-item label="污染源" prop="sourceId">
        <el-select v-model="queryForm.sourceId" placeholder="请选择污染源" style="width:300px" filterable size="mini">
          <el-option :label="i.zdatasourname" :value="i.zdatasourid" v-for="i in sourceList"
                     :key="i.zdatasourid"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="镇街" prop="townId" v-if="!isTown">
        <el-select v-model="queryForm.townId" size="mini" placeholder="请选择镇街" style="width:100%">
          <el-option :label="i.name" :value="i.id" v-for="i in townList" :key="i.id"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="申请状态" prop="status">
        <el-select v-model="queryForm.status" placeholder="申请状态" style="width:130px" size="mini">
          <el-option label="待批复" value="2"></el-option>
          <el-option label="同意" value="3"></el-option>
          <el-option label="退回" value="4"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="日期范围" prop="date">
        <el-date-picker
          v-model="queryForm.date"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          :clearable="false">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        <el-button
          type="warning"
          icon="el-icon-mobile"
          size="mini"
          :disabled="!(selectRow&&selectRow.applyId&&selectRow.status===2)"
          @click="acceptDevice"
        >审批
        </el-button>
      </el-form-item>
    </el-form>
    <!--    表格-->
    <el-table
      v-loading="loading"
      :data="tableData"
      highlight-current-row
      @current-change="handleCurrentChange"
      style="width: 100%;flex:1;"
      ref="oTable"
      height="100%"
      border
    >
      <el-table-column prop="sourceName" label="企业名称" width="250" show-overflow-tooltip></el-table-column>
      <el-table-column prop="townName" label="镇街" width="90"></el-table-column>
      <el-table-column label="申请状态" width="120">
        <template slot-scope="scope">
          {{ statusType[scope.row.status] }}
        </template>
      </el-table-column>
      <el-table-column prop="acceptDate" label="验收时间" width="120"></el-table-column>
      <el-table-column prop="chargeMan" label="验收负责人" width="120"></el-table-column>
      <el-table-column prop="chargeManTitle" label="负责人职务" width="120"></el-table-column>
      <el-table-column prop="chargeManTel" label="负责人电话" width="180"></el-table-column>
      <el-table-column prop="memo" label="备注" show-overflow-tooltip></el-table-column>
      <el-table-column prop="approvedBy" label="确认人" width="120"></el-table-column>
      <el-table-column prop="approvedDate" label="确认时间" width="120"></el-table-column>
      <el-table-column prop="approvalDesc" label="确认意见" show-overflow-tooltip></el-table-column>
      <el-table-column label="操作" width="140" align="center">
        <template slot-scope="scope">
          <el-link type="primary" style="font-size:12px;" :underline="false" @click="showDevice(scope.row)">备案设备
          </el-link>
          <el-divider direction="vertical"></el-divider>
          <el-dropdown trigger="hover" @visible-change="fileClick($event,scope.row)" placement="bottom-start">
            <span class="el-dropdown-link" style="font-size:12px;color:#1682e6">
              附件<i class="el-icon-arrow-down el-icon--right"></i>
            </span>
            <el-dropdown-menu slot="dropdown" style="width:400px;padding:10px" v-loading="fileLoading">
              <el-dropdown-item v-for="(item,index) in fileList" :key="item.Id">
                <el-link
                  type="primary"
                  :underline="false"
                  target="_blank"
                  :href="getlink(item.attach_id)"
                  style="font-size:12px;"
                >{{ index + 1 }}.{{ item.file_name }}
                </el-link>
              </el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
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
      title="备案设备"
      custom-class="dgom-wran-dialog"
      :visible.sync="deviceVisible"
      width="900px">
      <n-device :applyId="applyId" :details="details"></n-device>
    </el-dialog>
    <el-dialog
      title="审批"
      :close-on-click-modal="false"
      @close="handleClose"
      custom-class="dgom-wran-dialog"
      :visible.sync="acceptVisible"
      width="600px">
      <div v-if="acceptVisible">
        <el-form :model="handleForm" ref="handleForm" label-width="110px" :rules="rules">
          <el-row :gutter="15">
            <el-col :lg="24" :md="24" :sm="24">
              <el-form-item label="确认人" prop="approvedBy">
                <el-input
                  size="mini"
                  v-model="handleForm.approvedBy"
                  :style="{width: '100%'}"
                ></el-input>
              </el-form-item>
            </el-col>
            <el-col :lg="24" :md="24" :sm="24">
              <el-form-item label="确认意见" prop="approvalDesc">
                <el-input
                  size="mini"
                  type="textarea"
                  v-model="handleForm.approvalDesc"
                  :style="{width: '100%'}"
                ></el-input>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" size="mini" @click="submitAccpet(3)">同意</el-button>
        <el-button size="mini" @click="submitAccpet(4)">退回</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import {dgomEnterprise, dgomTowns, dgomEntAcceptList, dgomEntAcceptApproval} from "@/api/om";
import {mapState} from "vuex";
import nDevice from "./device";
import {dgomAttachList} from "@/api/om/upload";

export default {
  components: {nDevice},
  data () {
    return {
      townList: [],
      sourceList: [],
      tableData: [],
      loading: false,
      statusType: ["", "草稿", "待批复", "同意", "退回"],
      deviceVisible: false,
      queryForm: {
        status: "2",
        townId: null,
        sourceId: null
      },
      isTown: false,
      // 分页
      page: {
        pageNum: 1,
        pageSize: 30
      },
      total: 0,
      applyId: null,
      details: [],
      fileLoading: false,
      fileList: [],
      selectRow: {},
      acceptVisible: false,
      handleForm: {},
      rules: {
        approvedBy: [
          {
            required: true,
            message: "确认人必填",
            trigger: "blur",
          },
        ],
        approvalDesc: [
          {
            required: true,
            message: "确认意见必填",
            trigger: "blur",
          },
        ],
      },
    };
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
  computed: {
    ...mapState({
      regions: state => state.user.regions
    }),
  },
  created () {
    dgomTowns().then(res => {
      if (res.code === 200) {
        this.townList = res.data;
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
      dgomEntAcceptList(v, this.urlParameter(this.page)).then(res => {
        this.tableData = res.rows;
        this.total = res.total;
        this.loading = false;
      });
    },
    handleCurrentChange (v) {
      this.selectRow = v;
    },
    handleQuery () {
      let v = {};
      if (this.queryForm.date && this.queryForm.date.length > 0) {
        v.startDate = this.queryForm.date[0];
        v.endDate = this.queryForm.date[1];
      }
      v = Object.assign(this.queryForm, v);
      this.getList(v);
    },
    resetQuery () {
      this.queryForm = {
        sourceId: null,
        townId: null,
        status: "2",
        date: null
      };
      if (this.townCode !== "4419" && this.townCode !== "0") {
        this.queryForm.townCode = this.townCode;
      }
      this.page.pageNum = 1;
      this.handleQuery();
    },
    showDevice (v) {
      this.applyId = v.applyId;
      this.details = v.details;
      this.deviceVisible = true;
    },
    fileClick (i, v) {
      if (i) {
        this.fileLoading = true;
        let ref = {
          refType: "Ent_AcceptApplyHead"
        };
        dgomAttachList(v.applyId, ref).then((res) => {
          this.fileList = res.data;
          this.fileLoading = false;
        });
      } else {
        this.fileList = [];
        this.fileLoading = false;
      }
    },
    getlink (id) {
      return `${process.env.VUE_APP_BASE_API}/dgom/attach/download/${id}`;
    },
    acceptDevice () {
      if (this.selectRow.applyId) {
        this.acceptVisible = true;
      } else {
        this.msgError("请选择需要审批的数据");
      }
    },
    submitAccpet (v) {
      this.handleForm.status = v;
      this.handleForm.applyId = this.selectRow.applyId;
      dgomEntAcceptApproval(this.handleForm).then(res => {
        if (res.code === 200) {
          this.handleQuery();
          this.msgSuccess("审批成功");
          this.acceptVisible = false;
        }
      });
    },
    handleClose () {
      this.handleForm = {};
    }
  }
};
</script>

<style lang='scss' scoped>

</style>
