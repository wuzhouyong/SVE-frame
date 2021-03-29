<template>
  <!--  设备状态变更-->
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
        <el-select v-model="queryForm.status" placeholder="请选择申请状态" size="mini" style="width:100%">
          <el-option :label="i.name" :value="i.id" v-for="i in statusList" :key="i.id"></el-option>
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
      </el-form-item>
    </el-form>
    <!--    操作-->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-mobile"
          size="mini"
          @click="accept"
          :disabled="rowDataType!==2"
        >受理
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-edit-outline"
          size="mini"
          @click="approval"
          :disabled="rowDataType!==3"
        >审批
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-user"
          size="mini"
          @click="onDuty"
          :disabled="rowDataType!==5"
        >值守
        </el-button>
      </el-col>
    </el-row>
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
      <el-table-column label="申请类型" width="90">
        <template slot-scope="scope">
          {{ scope.row.applyType === 1 ? "维修(或更换)" : "停用(或拆除)" }}
        </template>
      </el-table-column>
      <el-table-column label="申请状态" width="90">
        <template slot-scope="scope">
          {{ scope.row.status ? statusArr[scope.row.status] : "-" }}
        </template>
      </el-table-column>
      <el-table-column prop="contactMan" label="联系人" width="90"></el-table-column>
      <el-table-column prop="contactTel" label="联系电话" width="110"></el-table-column>
      <el-table-column prop="changeReason" label="变更原因" width="300" show-overflow-tooltip></el-table-column>
      <el-table-column prop="applyDate" label="申请时间" width="140"></el-table-column>
      <el-table-column prop="ipAddress" label="经办人" width="90"></el-table-column>
      <el-table-column prop="ipAddress" label="批复时间" width="140"></el-table-column>
      <el-table-column prop="ipAddress" label="批复意见" min-width="130"></el-table-column>
      <el-table-column label="操作" width="80" align="center">
        <template slot-scope="scope">
          <el-link type="primary" :underline="false" style="font-size:12px" @click="seeDetails(scope.row)">
            查看详情
          </el-link>
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
      :title="title"
      :visible.sync="visible"
      custom-class="unitDialog"
      width="30%">
      <n-form :rowDataType="rowDataType" :rowData="currentRow" v-if="visible" @isSuccess="isSuccess" ref="nForm"></n-form>
      <template v-if="rowDataType===2">
        <span slot="footer" class="dialog-footer">
              <el-button type="danger" icon="el-icon-circle-close" @click="acceptOpera(4)">不 受 理</el-button>
              <el-button type="primary" icon="el-icon-circle-check" @click="acceptOpera(3)">受 理</el-button>
        </span>
      </template>
      <template v-if=" rowDataType===3">
        <span slot="footer" class="dialog-footer">
              <el-button type="danger" icon="el-icon-circle-close" @click="approvalOpera(6)">拒 绝</el-button>
              <el-button type="primary" icon="el-icon-circle-check" @click="approvalOpera(5)">同 意</el-button>
        </span>
      </template>
      <template v-if="rowDataType===5">
        <span slot="footer" class="dialog-footer">
              <el-button @click="visible = false">取消</el-button>
              <el-button type="primary" @click="dutyOpera">确 认</el-button>
        </span>
      </template>
    </el-dialog>
    <el-dialog
      :title="title"
      :visible.sync="seevisible"
      custom-class="unitDialog"
      width="30%">
      <n-see :rowData="seeData" ref="seeForm"></n-see>
    </el-dialog>
  </div>
</template>

<script>
import {dgomEnterprise, dgomTowns, dgomDeviceReplaceList, dgomDeviceAccept} from "@/api/om";
import nForm from "./nForm";
import nSee from "./nSee";
import {mapState} from "vuex";

export default {
  components: {nForm, nSee},
  data () {
    return {
      townList: [],
      sourceList: [],
      tableData: [],
      loading: false,
      statusArr: ["", "", "待受理", "受理", "不受理", "同意", "拒绝", "已处理"],
      statusList: [
        {id: "2", name: "待受理"},
        {id: "3", name: "受理"},
        {id: "4", name: "不受理"},
        {id: "5", name: "同意"},
        {id: "6", name: "拒绝"},
        {id: "7", name: "已处理"}
      ],
      queryForm: {
        sourceId: null,
        townId: null,
        status: null,
        date: null
      },
      townCode: null,
      // 分页
      page: {
        pageNum: 1,
        pageSize: 30
      },
      total: 0,
      currentRow: null,
      rowDataType: 0,
      visible: false,
      title: null,
      isTown: false,
      seevisible: false,
      seeData: {}
    };
  },
  computed: {
    ...mapState({
      regions: state => state.user.regions
    })
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
  methods: {
    getList (v = {}) {
      this.loading = true;
      dgomDeviceReplaceList(v, this.urlParameter(this.page)).then(res => {
        this.tableData = res.rows;
        this.total = res.total;
        this.loading = false;
      });
    },
    handleQuery () {
      let oVal = JSON.parse(JSON.stringify(this.queryForm));
      if (oVal.date) {
        oVal.beginTime = oVal.date[0];
        oVal.endTime = oVal.date[1];
      }
      this.page.pageNum = 1;
      this.getList(oVal);
    },
    resetQuery () {
      this.queryForm = {
        sourceId: null,
        townId: null,
        status: null,
        date: null
      };
      if (this.townCode !== "4419" && this.townCode !== "0") {
        this.queryForm.townCode = this.townCode;
      }
      this.page.pageNum = 1;
      this.handleQuery();
    },
    handleCurrentChange (v) {
      this.rowDataType = v.status;
      this.currentRow = v;
    },
    //受理
    accept () {
      this.title = "受理";
      this.visible = true;
    },
    //审核
    approval () {
      this.title = "审核";
      this.visible = true;
    },
    //值守
    onDuty () {
      this.title = "值守";
      this.visible = true;
    },
    //受理操作
    acceptOpera (v) {
      let oVal = {
        applyId: this.currentRow.applyId,
        status: v
      };
      dgomDeviceAccept(oVal).then(res => {
        if (res.code === 200) {
          this.visible = false;
          this.handleQuery();
          this.msgSuccess("处理成功!");
        }
      });
    },
    //审批
    approvalOpera (v) {
      let oVal = {
        applyId: this.currentRow.applyId,
        status: v
      };
      this.$refs.nForm.approvalSub(oVal);
    },
    //值守
    dutyOpera () {
      let oVal = {
        applyId: this.currentRow.applyId,
        status: 7
      };
      this.$refs.nForm.dutylSub(oVal);
    },
    isSuccess () {
      this.visible = false;
      this.handleQuery();
    },
    seeDetails (v) {
      this.seeData = v;
      this.seevisible = true;
    }
  }
};
</script>

<style lang='scss' scoped>

</style>
