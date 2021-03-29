<template>
  <div class="task-warn-wk">
    <!--    分局处理-->
    <el-tabs v-model="activeName" @tab-click="handleClick">
      <el-tab-pane label="待处理" name="pending"></el-tab-pane>
      <el-tab-pane label="全部" name="all"></el-tab-pane>
    </el-tabs>
    <!-- 搜索表单 -->
    <el-form :model="queryForm" class="task-warn-form" ref="queryForm" label-width="80px" :inline="true">
      <el-form-item label="监控任务" prop="tagHeadId">
        <el-select
          v-model="queryForm.tagHeadId"
          @change="sysChange"
          style="width:164px"
        >
          <el-option
            v-for="item in cities"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="异常类型" prop="warnType" v-show="isChangeSys">
        <el-select v-model="queryForm.warnType" style="width:164px">
          <el-option
            :label="i.dictLabel"
            :value="i.dictValue"
            v-for="i in warnTypes"
            :key="i.dictValue"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="企业名称" prop="sourceName">
        <el-input
          v-model="queryForm.sourceName"
          clearable
          size="mini"
          style="width:164px"
        />
      </el-form-item>
      <el-form-item label="品牌" prop="acquisitionBrand">
        <el-input
          v-model="queryForm.acquisitionBrand"
          size="mini"
          style="width:164px"
        />
      </el-form-item>
      <el-form-item label="施工单位" prop="constructionUnit">
        <el-input
          v-model="queryForm.constructionUnit"
          size="mini"
          style="width:164px"
        />
      </el-form-item>
      <el-form-item label="处理状态" prop="procStatus" v-if="activeName!=='pending'">
        <el-select
          v-model="queryForm.procStatus"
          style="width:164px"

        >
          <el-option
            label="待处理"
            :value="0"
          ></el-option>
          <el-option
            label="已完成"
            :value="1"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="异常级别" prop="warnLevel" v-if="activeName!=='pending'">
        <el-select v-model="queryForm.warnLevel" clearable style="width:164px">
          <el-option
            :label="i.dictLabel"
            :value="i.dictValue"
            v-for="i in warnLevels"
            :key="i.dictValue"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="选择日期" prop="oTime">
        <el-date-picker
          v-model="queryForm.oTime"
          style="width:220px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        <el-button type="warning" icon="el-icon-edit" size="mini" @click="warnHandle" :disabled="!idMultiple">异常处理
        </el-button>
        <el-button type="info" icon="el-icon-position" size="mini" :disabled="true" v-if="activeName==='pending'">
          移交给分局
        </el-button>
      </el-form-item>
    </el-form>
    <!-- 表格 -->
    <el-table
      v-loading="loading"
      element-loading-text="数据查询中,请稍后!"
      title="双击查看详情"
      @row-dblclick="rowClick"
      @selection-change="handleSelectionChange"
      :data="tableData"
      size="mini"
      style="width: 100%;"
      ref="oTable"
      height="100%"
      border
    >
      <el-table-column type="selection" width="55" align="center"></el-table-column>
      <el-table-column prop="tagHeadName" label="监控任务" width="180" show-overflow-tooltip></el-table-column>
      <el-table-column prop="sourceName" label="企业名称" width="260" show-overflow-tooltip></el-table-column>
      <el-table-column prop="townName" label="镇街" width="80"></el-table-column>
      <el-table-column prop="warnLevelName" label="异常级别" width="100"></el-table-column>
      <el-table-column prop="procStatus" label="处理状态" width="80">
        <template slot-scope="scope">
          {{ ["待处理", "已完成"][scope.row.procStatus] }}
        </template>
      </el-table-column>
      <el-table-column label="异常时间" width="170">
        <template slot-scope="scope">{{ scope.row.startDate }} ~ {{ scope.row.endDate }}</template>
      </el-table-column>
      <el-table-column prop="warnTypeName" label="异常类型" width="160" show-overflow-tooltip></el-table-column>
      <el-table-column prop="warnDesc" label="异常描述" show-overflow-tooltip></el-table-column>
      <el-table-column prop="acquisitionBrand" label="品牌" width="100" show-overflow-tooltip></el-table-column>
      <el-table-column prop="constructionUnit" label="施工单位" width="180" show-overflow-tooltip></el-table-column>
    </el-table>
    <!-- 分页 -->
    <pagination
      :total="total"
      :page.sync="page.pageNum"
      :limit.sync="page.pageSize"
      @pagination="getList(queryForm)"
    />
    <!--异常处理-->
    <el-dialog
      title="异常处理"
      :visible.sync="warnVisible"
      :close-on-click-modal="false"
      width="500px"
      custom-class="warn_handle_form">
      <n-form :selectRows="selectRows" v-if="warnVisible" ref="nForm" @complete="complete"></n-form>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" icon="el-icon-thumb" @click="submitForm">提 交</el-button>
      </span>
    </el-dialog>
    <!--    查看详情-->
    <el-dialog
      title="异常详情"
      :visible.sync="detailsVisible"
      width="700px"
      custom-class="warn_details_form">
      <n-details :oPath="oPath" :values="values" v-if="detailsVisible"></n-details>
    </el-dialog>
  </div>
</template>

<script>
import {GetRegionPower, getTagList} from "@/api/composite/source";
import {warnList} from "@/api/warnType";
import {contrast, platform_list} from "@/config";
import nForm from "./nForm";
import nDetails from "./nDetails";
import {mapState} from "vuex";

export default {
  components: {nForm, nDetails},
  data () {
    return {
      loading: false,
      queryForm: {
        tagHeadId: null,
        warnType: null,
        sourceName: null,
        townCode: null,
        userType: "duty",
        acquisitionBrand: null,
        constructionUnit: null,
        procStatus: 0,
        warnLevel: "3",
        oTime: null
      },
      townCodeOptions: [],
      cities: [],
      warnLevels: [],
      warnTypes: [],
      tableData: [],
      isChangeSys: false,
      warnVisible: false,
      detailsVisible: false,
      page: {
        pageNum: 1,
        pageSize: 30
      },
      total: 0,
      activeName: "pending",
      oPath: null,
      values: {},
      selectRows: []
    };
  },
  created () {
    this.getSourceTagList();
    GetRegionPower().then((res) => {
      this.townCodeOptions = res.data;
    });
    this.getDicts("ty_warn_level").then((response) => {
      this.warnLevels = response.data;
    });
  },
  watch: {
    searchData: {
      handler () {
        this.getList(this.queryForm);
      },
      immediate: true
    },
  },
  computed: {
    ...mapState("label", ["searchData"]),
    idMultiple () {
      if (this.selectRows.length === 0) {
        return false;
      } else if (this.selectRows.length === 1) {
        return true;
      }
      // if (this.selectRows.length === 0) {
      //   return false;
      // } else if (this.selectRows.length > 0) {
      //   let {tagHeadId, sourceId, warnType, procStatus} = this.selectRows[0];
      //   return this.selectRows.every(i => i.tagHeadId === tagHeadId && i.sourceId === sourceId && i.warnType === warnType && i.procStatus === procStatus);
      // } else {
      //   return true;
      // }
    }
  },
  methods: {
    getList (v = {}) {
      this.loading = true;
      let {oTime, ...rest} = v;
      if (v.oTime && v.oTime.length > 0) {
        rest.startDate = v.oTime[0];
        rest.endDate = v.oTime[1];
      }
      if (this.searchData.length > 0) {
        rest.labelIds = this.searchData;
      }
      warnList(rest, this.urlParameter(this.page)).then(res => {
        if (res.code === 200) {
          this.tableData = res.rows;
          this.total = res.total;
          this.$nextTick(() => {
            if (this.$refs.oTable.bodyWrapper) {
              this.$refs.oTable.bodyWrapper.scrollTop = 0;
            }
          });
        }
        this.loading = false;
      });
    },
    handleQuery () {
      this.page.pageNum = 1;
      this.getList(this.queryForm);
    },
    resetQuery () {
      if (this.activeName === "pending") {
        this.queryForm = {
          tagHeadId: null,
          warnType: null,
          sourceName: null,
          townCode: null,
          acquisitionBrand: null,
          constructionUnit: null,
          userType: "duty",
          procStatus: 0,
          warnLevel: "3",
          oTime: null
        };
      } else {
        this.queryForm = {
          tagHeadId: null,
          warnType: null,
          sourceName: null,
          townCode: null,
          acquisitionBrand: null,
          constructionUnit: null,
          userType: "duty",
          procStatus: null,
          warnLevel: null,
          oTime: null
        };
      }
      this.isChangeSys = falses;
      this.handleQuery();
    },
    sysChange (v) {
      this.warnTypes = [];
      let oPath = this.cities.find(i => i.id === v).path;
      this.getDicts(contrast[oPath] + "_warn_type").then(res => {
        this.warnTypes = res.data;
      });
      if (v) this.isChangeSys = true;
    },
    //平台数据
    getSourceTagList () {
      getTagList().then((res) => {
        if (res.code === 200) {
          let oArr = res.rows;
          this.cities = platform_list.filter((p) =>
            oArr.some((s) => s.tagHeadId === p.id)
          );
        }
      });
    },
    warnHandle () {
      this.warnVisible = true;
    },
    //切换
    handleClick (tab) {
      this.isChangeSys = false;
      if (tab.name === "pending") {
        this.queryForm = {
          tagHeadId: null,
          warnType: null,
          sourceName: null,
          townCode: null,
          acquisitionBrand: null,
          constructionUnit: null,
          userType: "duty",
          procStatus: 0,
          warnLevel: "3",
          oTime: null
        };
      } else {
        this.queryForm = {
          tagHeadId: null,
          warnType: null,
          sourceName: null,
          townCode: null,
          acquisitionBrand: null,
          constructionUnit: null,
          userType: "duty",
          procStatus: null,
          warnLevel: null,
          oTime: null
        };
      }
      this.page.pageNum = 1;
      this.getList(this.queryForm);
    },
    complete () {
      this.page.pageNum = 1;
      this.getList(this.queryForm);
      this.warnVisible = false;
    },
    rowClick (v) {
      this.oPath = platform_list.find(i => i.id === v.tagHeadId.toString()).path;
      this.values = v;
      this.detailsVisible = true;
    },
    handleSelectionChange (selection) {
      this.selectRows = selection;
    },
    submitForm () {
      this.$refs.nForm.handlerWarn();
    }
  }
};
</script>

<style lang='scss' scoped>
.task-warn-wk {
  width: 100%;
  height: 100%;
  overflow: hidden;
  display: flex;
  flex-direction: column;
  padding: 10px;

  .task-warn-form {
    /deep/ .el-form-item {
      margin-bottom: 8px;
    }
  }

  /deep/ .warn_details_form {
    width: 100%;
    height: auto;
    max-height: calc(100vh - 12%);
    overflow: hidden;
    display: flex;
    flex-direction: column;

    .el-dialog__body {
      padding: 5px 10px;
      flex: 1;
      overflow-y: auto;
      overflow-x: hidden;

      .details_context {
        .el-form-item {
          margin-bottom: 0px;
        }
      }

      .history_context {
        .el-card__body {
          padding: 10px;

          .el-form-item {
            margin-bottom: 0px;
          }
        }
      }
    }
  }


  //
  //.task-warn-search {
  //  width: 100%;
  //  height: auto;
  //  border-left: 3px solid #438EB9;
  //  padding: 0 5px;
  //
  //}
}
</style>
