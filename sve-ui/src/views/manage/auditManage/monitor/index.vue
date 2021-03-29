<template>
  <div class="task-wk">
    <!-- 搜索表单 -->
    <el-form :model="queryForm" ref="queryForm" :inline="true" class="guocheng-serchForm">
      <el-form-item label="企业名称" prop="fullName">
        <el-input
          v-model="queryForm.fullName"
          placeholder
          clearable
          size="mini"
          style="width: 150px"
        />
      </el-form-item>
      <el-form-item label="监控任务" prop="tagHeadId">
        <el-select
          v-model="queryForm.tagHeadId"
          placeholder="请选择监控任务"
          clearable
          :style="{width: '170px'}"
        >
          <el-option v-for="i in cities" :key="i.id" :label="i.name" :value="i.id"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="所属镇街" prop="townCode">
        <el-select
          v-model="queryForm.townCode"
          placeholder="请选择所属镇街"
          clearable
          :style="{width: '120px'}"
        >
          <el-option
            v-for="i in townCodeOptions"
            :key="i.regionCode"
            :label="i.regionName"
            :value="i.regionCode"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="运行状态" prop="sourceStatus">
        <el-select
          v-model="queryForm.sourceStatus"
          placeholder="运行状态"
          clearable
          :style="{width: '100px'}"
        >
          <el-option
            v-for="i in sourceStatusOptions"
            :key="i.dictValue"
            :label="i.dictLabel"
            :value="i.dictValue"
          ></el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="任务进度" prop="constructionStatus">
        <el-select
          v-model="queryForm.constructionStatus"
          placeholder="任务进度"
          clearable
          :style="{width: '100px'}"
        >
          <el-option
            v-for="i in construceState"
            :key="i.dictValue"
            :label="i.dictLabel"
            :value="i.dictValue"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="任务状态" prop="verifyStatus">
        <el-select
          v-model="queryForm.verifyStatus"
          placeholder="任务状态"
          clearable
          :style="{width: '100px'}"
        >
          <el-option
            label="正常"
            value="0"
          ></el-option>
          <el-option
            label="不安装"
            value="1"
          ></el-option>
          <el-option
            label="暂缓"
            value="2"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="登录状态" prop="loginFlag">
        <el-select
          v-model="queryForm.loginFlag"
          placeholder="登录状态"
          clearable
          :style="{width: '100px'}"
        >
          <el-option
            label="未登录"
            value="0"
          ></el-option>
          <el-option
            label="已登录"
            value="1"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item prop="listType">
        <el-radio-group v-model="queryForm.listType" size="mini">
          <el-radio-button :label="-1">全部</el-radio-button>
          <el-radio-button :label="0">考核名单</el-radio-button>
          <el-radio-button :label="1">非考核名单</el-radio-button>
        </el-radio-group>
      </el-form-item>
      <el-form-item prop="isFinish">
        <el-radio-group v-model="queryForm.isFinish" size="mini">
          <el-radio-button :label="-1">全部</el-radio-button>
          <el-radio-button :label="0">未完成</el-radio-button>
          <el-radio-button :label="1">已完成</el-radio-button>
        </el-radio-group>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        <el-button icon="el-icon-upload2" size="mini" type="warning" @click="exportTables">导出表格</el-button>
      </el-form-item>
      <el-checkbox
        v-model="isApply"
        label="申请记录清单"
        border
        size="mini"
        @change="isApplyChange"
      ></el-checkbox>
    </el-form>
    <!-- 表格 -->
    <el-table
      title="双击查看详情"
      v-loading="loading"
      element-loading-text="数据查询中,请稍后!"
      :data="tableData"
      style="width: 100%;flex:1"
      ref="oTable"
      height="100%"
      @row-dblclick="see"
      border
    >
      <el-table-column prop="tagHeadName" label="监控任务" width="180"></el-table-column>
      <el-table-column prop="fullName" label="企业名称" min-width="280" show-overflow-tooltip></el-table-column>
      <el-table-column prop="townName" label="所属镇街" width="80"></el-table-column>
      <el-table-column prop="envContactName" label="联系人" width="80"></el-table-column>
      <el-table-column prop="envContactPhone" label="联系电话" width="120"></el-table-column>
      <el-table-column label="登录状态" width="90" align="center">
        <template slot-scope="scope">
          {{ scope.row.loginFlag === "1" ? "已登录" : "未登录" }}
        </template>
      </el-table-column>
      <el-table-column prop="sourceStatus" label="运行状态" width="80" align="center"></el-table-column>
      <el-table-column prop="status" label="任务进度" width="90" align="center"></el-table-column>
      <el-table-column label="任务状态" width="90" align="center">
        <template slot-scope="scope">
          {{ verifyType[scope.row.verifyStatus] }}
        </template>
      </el-table-column>
      <el-table-column prop="constructionUnit" label="施工单位名称" width="210" show-overflow-tooltip></el-table-column>
      <el-table-column prop="constructionPerson" label="施工单位联系人" width="120"></el-table-column>
      <el-table-column prop="constructionPhone" label="施工单位联系电话" width="130"></el-table-column>
      <el-table-column align="center" label="操作" width="190" fixed="right">
        <template slot-scope="scope">
          <div>
            <el-button
              type="text"
              size="small"
              v-show="!scope.row.taskAdjustId"
              v-hasPermi="['monitor:task:apply']"
              @click.stop="trimClick(scope.row,'0')"
            >任务调整申请
            </el-button>
            <el-button
              type="text"
              size="small"
              v-show="scope.row.tagHeadId!=='1003'&&!scope.row.taskAdjustId"
              v-hasPermi="['monitor:citytask:apply']"
              @click.stop="trimClick(scope.row,'1')"
            >任务调整
            </el-button>
            <el-button
              type="text"
              size="small"
              @click.stop="reviewDetails(scope.row)"
              v-hasPermi="['review:task:details']"
            >审核记录明细
            </el-button>
          </div>
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
    <!-- 查看 -->
    <el-dialog
      title="监控任务详情"
      :visible.sync="visible"
      width="40%"
      v-if="visible"
      :destroy-on-close="true"
    >
      <seeChunk :oId="oId" :platFormPath="platFormPath" :refType="refType"></seeChunk>
    </el-dialog>
    <!-- 任务调整申请 -->
    <el-dialog
      :title="`任务调整申请( ${sourceTitle} )`"
      :visible.sync="trimVisible"
      width="40%"
      :close-on-click-modal="false"
    >
      <trimChunk :rowsData="rowsData" ref="trimChunk" v-if="trimVisible" :trimType="trimType"
                 @successUpload="successApply"></trimChunk>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="$refs.trimChunk.submitTxt()" v-preventClick>提交申请</el-button>
      </span>
    </el-dialog>
    <!-- 审核明细 -->
    <el-dialog
      :title="`审核记录明细( ${sourceTitle} )`"
      :visible.sync="reviewVisible"
      width="40%"
      :destroy-on-close="true"
      custom-class="reviewDialog"
    >
      <reviewChunk :reviewData="reviewData" :isLingxing="isLingxing" v-if="reviewVisible"></reviewChunk>
    </el-dialog>
  </div>
</template>
<script>
import {analysis} from "@/config";
import {
  monitorTask,
  exportMonitor
} from "@/api/composite/monitor";
import {getTagList, GetRegionPower} from "@/api/composite/source";
import seeChunk from "./see";
import trimChunk from "./trim";
import reviewChunk from "./review";
import {mapState} from "vuex";

export default {
  components: {seeChunk, trimChunk, reviewChunk},
  data() {
    return {
      queryForm: {
        tagHeadId: null,
        townCode: null,
        fullName: null,
        sourceStatus: null,
        constructionStatus: null,
        listType: 0,
        isFinish: -1
      },
      page: {
        pageNum: 1,
        pageSize: 30
      },
      tableData: [],
      total: 0,
      cities: [],
      loading: false,
      townCodeOptions: [],
      sourceStatusOptions: [],
      construceState: [],
      visible: false,
      platFormPath: "",
      oId: "",
      refType: "",
      platArr: [],
      trimVisible: false,
      rowsData: {},
      reviewVisible: false,
      reviewData: {},
      sourceTitle: "",
      isApply: false,
      trimType: "",
      lxDetails: {},
      isLingxing: false,
      verifyType: ["正常", "不安装", "暂缓"]
    };
  },
  computed: {
    ...mapState("label", ["searchData"])
  },
  created() {
    // this.getSourceTag();
    GetRegionPower().then((res) => (this.townCodeOptions = res.data));
    this.getDicts("source_status").then((res) => {
      this.sourceStatusOptions = res.data;
    });
    //任务进度
    this.getDicts("ty_construct_state").then((res) => {
      this.construceState = res.data;
    });
  },
  watch: {
    searchData: {
      handler() {
        this.getSourceTag();
      },
      immediate: true
    },
  },
  methods: {
    isApplyChange(v) {
      this.page.pageNum = 1;
      if (v) {
        this.$set(this.queryForm, 'isApply', 'xxx');
        // this.queryForm.isApply = "xxx";
      } else {
        this.$set(this.queryForm, 'isApply', null);
      }
      this.getList(this.queryForm);
    },
    getList(v = {}) {
      this.loading = true;
      let oVal = Object.assign({labelIds: this.searchData}, v);
      monitorTask(oVal, this.urlParameter(this.page)).then((res) => {
        if (res.code === 200) {
          this.tableData = res.rows;
          this.total = res.total;
        }
        this.loading = false;
      });
    },
    handleQuery() {
      this.page.pageNum = 1;
      if (this.queryForm.tagHeadId) {
        this.queryForm.tagHeadIds = [this.queryForm.tagHeadId];
      } else {
        this.queryForm.tagHeadIds = this.platArr;
      }
      this.getList(this.queryForm);
    },
    resetQuery() {
      this.resetForm("queryForm");
      this.queryForm = {
        tagHeadId: null,
        townCode: null,
        fullName: null,
        sourceStatus: null,
        constructionStatus: null,
        listType: 0,
        isFinish: -1
      }
      this.handleQuery();
    },
    // 监控任务
    getSourceTag() {
      getTagList().then((res) => {
        if (res.code === 200) {
          let oArr = res.rows;
          this.cities = analysis.filter((p) =>
            oArr.some((s) => s.tagHeadId === p.id)
          );

          let checkArr = [];
          this.cities.forEach((v, i, a) => {
            checkArr.push(v.id);
          });
          this.platArr = checkArr;
          this.queryForm.tagHeadIds = this.platArr;
          this.getList(this.queryForm);
        }
      });
    },
    see(v) {
      let a = analysis.filter((p) => {
        return p.id === v.tagHeadId;
      });
      this.platFormPath = a[0].path;
      this.refType = a[0].ref_type;
      // refType: "lx_sysfile_type"
      this.oId = v.sourceId;
      this.visible = true;
    },
    trimClick(v, i) {
      this.trimType = i;
      this.rowsData = v;
      this.trimVisible = true;
      this.sourceTitle = v.fullName;
    },
    successApply() {
      this.msgSuccess("提交申请成功");
      this.getList(this.queryForm);
      this.trimVisible = false;
    },
    vIfBtn(v) {
      if (v.verifyStatus === "1" || v.verifyStatus === "2") {
        return false;
      } else {
        return true;
      }
      // return !new Set(["1", "2"]).has(v.verifyStatus) ? true : false;
    },
    //审核明细
    reviewDetails(v) {
      this.isLingxing = v.tagHeadId === "1003" ? true : false;
      this.reviewData = v;
      this.reviewVisible = true;
      this.sourceTitle = v.fullName;
    },
    exportTables() {
      this.$confirm("是否导出数据?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          return exportMonitor(this.queryForm);
        })
        .then((response) => {
          this.download(response.msg);
        })
        .catch(function () {
        });
    }
  }
};
</script>
