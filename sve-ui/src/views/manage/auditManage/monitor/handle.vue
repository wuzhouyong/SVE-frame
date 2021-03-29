<template>
  <div class="task-wk">
    <!-- 搜索表单 -->
    <el-form :model="queryForm" ref="queryForm" :inline="true">
      <el-form-item label="企业名称" prop="sourceName">
        <el-input
          v-model="queryForm.sourceName"
          placeholder
          clearable
          size="mini"
          style="width: 180px"
        />
      </el-form-item>
      <el-form-item label="监控任务" prop="tagHeadId">
        <el-select
          v-model="queryForm.tagHeadId"
          placeholder="请选择监控任务"
          clearable
          :style="{width: '160px'}"
        >
          <el-option v-for="i in cities" :key="i.id" :label="i.name" :value="i.id"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="所属镇街" prop="townCode">
        <el-select
          v-model="queryForm.townCode"
          placeholder="请选择所属镇街"
          clearable
          :style="{width: '160px'}"
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
          placeholder="请选择运行状态"
          clearable
          :style="{width: '160px'}"
        >
          <el-option
            v-for="i in sourceStatusOptions"
            :key="i.dictValue"
            :label="i.dictLabel"
            :value="i.dictValue"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
      <el-checkbox
        v-model="isApply"
        label="审核记录清单"
        border
        size="mini"
        @change="isApplyType"
      ></el-checkbox>
    </el-form>
    <!-- 表格 -->
    <el-table
      v-loading="loading"
      :data="tableData"
      key="1"
      style="width: 100%;flex:1"
      ref="oTable"
      height="100%"
      border
      v-if="queryForm.isAudited===1"
    >
      <el-table-column prop="tagHeadName" label="监控任务" width="180"></el-table-column>
      <el-table-column prop="sourceName" label="企业名称" width="300"></el-table-column>
      <el-table-column prop="townName" label="所属镇街" width="90"></el-table-column>
      <el-table-column prop="constructionStatusName" label="任务进度" width="90"></el-table-column>
      <el-table-column prop="acceptBy" label="审核人" width="150"></el-table-column>
      <el-table-column prop="acceptDate" label="审核时间" width="150"></el-table-column>
      <el-table-column prop="acceptResult" label="审核结果" width="110">
        <template slot-scope="scope">
          {{ scope.row.acceptResult ? scope.row.acceptResult === "1" ? "验收确认" : "验收退回" : "-" }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="操作" width="80">
        <template slot-scope="scope">
          <el-button
            type="text"
            size="small"
            @click="historyList(scope.row)"
          >查看
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-table
      v-loading="loading"
      :data="tableData"
      style="width: 100%;flex:1"
      key="2"
      ref="oTable"
      height="100%"
      border
      v-else
    >

      <el-table-column prop="tagHeadName" label="监控任务" width="180"></el-table-column>
      <el-table-column prop="sourceName" label="企业名称" width="300"></el-table-column>
      <el-table-column prop="townName" label="所属镇街" width="90"></el-table-column>
      <el-table-column prop="constructionStatusName" label="任务进度" width="90"></el-table-column>
      <el-table-column prop="envContactName" label="联系人" width="90"></el-table-column>
      <el-table-column prop="envContactPhone" label="联系电话" width="120"></el-table-column>
      <el-table-column prop="sourceStatusName" label="运行状态" width="90"></el-table-column>
      <el-table-column prop="constructionUnit" label="施工单位名称" width="180" show-overflow-tooltip></el-table-column>
      <el-table-column prop="constructionPerson" label="施工单位联系人" width="120" show-overflow-tooltip></el-table-column>
      <el-table-column prop="constructionPhone" label="施工单位联系电话" width="140"></el-table-column>
      <el-table-column align="center" label="操作" width="180">
        <template slot-scope="scope">
          <el-button
            type="text"
            size="small"
            @click="see(scope.row)"
            v-if="!scope.row.taskAdjustId"
          >查看
          </el-button>
          <el-button
            type="text"
            size="small"
            @click="accept(scope.row)"
          >验收确认
          </el-button>
          <el-button
            type="text"
            size="small"
            @click="goback(scope.row)"
          >验收退回
          </el-button>
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
    <!--    验收退回-->
    <el-dialog
      title="验收退回"
      :visible.sync="gobackVisible"
      :close-on-click-modal="false"
      width="600px"
      v-if="gobackVisible"
      v-dialogDrag
      :destroy-on-close="true"
    >
      <gobackTem :g="gobackData" @gobackSuccess="gobackSuccess"></gobackTem>
    </el-dialog>
    <!--    历史记录-->
    <!-- 查看 -->
    <el-dialog
      title="审核历史"
      :visible.sync="historyVisible"
      width="500px"
      v-if="historyVisible"
      :destroy-on-close="true"
    >
      <nHistory :historyData="historyData" :clickData="clickData"></nHistory>
    </el-dialog>
  </div>
</template>
<script>
import {analysis} from "@/config";
import {
  monitorExamineList,
  monitorRejectList,
  monitorAccept
} from "@/api/composite/monitor";
import {getTagList, GetRegionPower} from "@/api/composite/source";
import seeChunk from "./see";
import gobackTem from "./goback";
import nHistory from "./history";
import {mapState} from "vuex";

export default {
  components: {seeChunk, gobackTem, nHistory},
  data () {
    return {
      queryForm: {
        tagHeadIds: null,
        townCode: null,
        sourceName: null,
        sourceStatus: null,
        isAudited: null
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
      visible: false,
      gobackVisible: false,
      platFormPath: "",
      oId: "",
      refType: "",
      platArr: [],
      applyData: {},
      gobackData: {},
      isApply: 0,
      historyVisible: false,
      historyData: [],
      clickData: {}
    };
  },
  computed: {
    ...mapState("label", ["searchData"])
  },
  watch: {
    searchData: {
      handler () {
        this.getSourceTag();
      },
      immediate: true
    },
  },
  created () {
    GetRegionPower().then((res) => (this.townCodeOptions = res.data));
    this.getDicts("source_status").then((res) => {
      this.sourceStatusOptions = res.data;
    });
  },
  methods: {
    getList (v = {}) {
      this.loading = true;
      let oVal = Object.assign({labelIds: this.searchData}, v);
      monitorExamineList(oVal, this.urlParameter(this.page)).then((res) => {
        if (res.code === 200) {
          this.tableData = res.rows;
          this.total = res.total;
        }
        this.loading = false;
      });
    },
    // 监控任务
    getSourceTag () {
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
    handleQuery () {
      this.page.pageNum = 1;
      if (this.queryForm.tagHeadId) {
        this.queryForm.tagHeadIds = [this.queryForm.tagHeadId];
      } else {
        this.queryForm.tagHeadIds = this.platArr;
      }
      this.getList(this.queryForm);
    },
    resetQuery () {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // audit(v) {
    //   this.$confirm("确认施工单位信息、施工合同或设备采购合同无误吗?", {
    //     confirmButtonText: "确定",
    //     cancelButtonText: "取消",
    //     type: "info",
    //   })
    //     .then(() => {
    //       let oVal = {
    //         pfSourceId: v.sourceId,
    //         tagHeadId: v.tagHeadId,
    //       };
    //       monitorAudit(oVal).then((res) => {
    //         if (res.code === 200) {
    //           this.handleQuery();
    //           this.$message({
    //             type: "success",
    //             message: res.msg,
    //           });
    //         }
    //       });
    //     })
    //     .catch(() => {
    //       this.$message({
    //         type: "info",
    //         message: "已取消",
    //       });
    //     });
    // },
    accept (v) {
      this.$confirm("确认企业按建设要求施工、联网并完整上传相关验收资料吗?", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "info"
      })
        .then(() => {
          let oVal = {
            sourceId: v.sourceId,
            tagHeadId: v.tagHeadId
          };
          monitorAccept(oVal).then((res) => {
            if (res.code === 200) {
              this.handleQuery();
              this.$message({
                type: "success",
                message: res.message
              });
            }
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消"
          });
        });
    },
    see (v) {
      let a = analysis.filter((p) => {
        return Number(p.id) === v.tagHeadId;
      });
      this.platFormPath = a[0].path;
      this.refType = a[0].ref_type;
      this.oId = v.sourceId;
      this.visible = true;
    },
    goback (v) {
      this.gobackData = v;
      this.gobackVisible = true;
    },
    gobackSuccess () {
      this.handleQuery();
      this.gobackVisible = false;
    },
    isApplyType (v) {
      this.queryForm.isAudited = v ? 1 : null;
      this.tableData = [];
      this.handleQuery();
    },
    historyList (v) {
      this.clickData = v;
      monitorRejectList(v.tagHeadId, v.sourceId).then(res => {
        if (res.code === 200) {
          this.historyVisible = true;
          this.historyData = res.data;
        }
      });
    }
  }
};
</script>
<style lang="scss" scoped>
.task-wk {
  /deep/ .el-dialog__body {
    padding: 15px 10px;
  }
}
</style>

