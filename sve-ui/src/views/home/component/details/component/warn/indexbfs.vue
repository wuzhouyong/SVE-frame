<template>
  <div class="home-warn-wk">
    <el-form :model="queryForm" ref="queryForm" :inline="true" class="home-queryForm">
      <el-form-item label="处理状态" prop="procStatus">
        <el-select
          v-model="queryForm.procStatus"
          size="mini"
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
      <el-form-item label="异常级别" prop="warnLevel">
        <el-select
          v-model="queryForm.warnLevel"
          placeholder="请选择异常级别"
          size="mini"
          style="width:140px"
        >
          <el-option
            :label="i.dictLabel"
            :value="i.dictValue"
            v-for="i in warnLevels"
            :key="i.dictValue"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="异常类型" prop="warnType">
        <el-select v-model="queryForm.warnType" size="mini" placeholder="请选择异常类型" style="width:140px">
          <el-option
            :label="i.dictLabel"
            :value="i.dictValue"
            v-for="i in warnTypes"
            :key="i.dictValue"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="选择日期" prop="timeSection">
        <el-date-picker
          v-model="queryForm.oTime"
          style="width:220px"
          value-format="yyyy-MM-dd"
          size="mini"
          :clearable="false"
          type="daterange"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <!-- 表格 -->
    <el-table
      v-loading="loading"
      element-loading-text="数据查询中,请稍后!"
      title="双击查看详情"
      @row-dblclick="rowClick"
      :data="tableData"
      size="mini"
      style="width: 100%;"
      ref="oTable"
      height="100%"
      border
    >
      <el-table-column prop="warnLevelName" label="异常级别" width="100"></el-table-column>
      <el-table-column prop="procStatusName" label="处理状态" width="80"></el-table-column>
      <el-table-column label="异常时间" width="170">
        <template slot-scope="scope">{{ scope.row.startDate }} ~ {{ scope.row.endDate }}</template>
      </el-table-column>
      <el-table-column prop="warnTypeName" label="异常类型" width="160" show-overflow-tooltip></el-table-column>
      <el-table-column prop="warnDesc" label="异常描述" show-overflow-tooltip></el-table-column>
    </el-table>
    <!-- 分页 -->
    <div class="mapindex-page">
      <el-pagination
        :current-page.sync="page.pageNum"
        :page-size="page.pageSize"
        :pager-count="pagerCount"
        layout="total, prev, pager, next"
        :total="total"
        @current-change="cChange"
      ></el-pagination>
    </div>
    <!--    查看详情-->
    <el-dialog
      title="异常详情"
      :visible.sync="detailsVisible"
      :modal="false"
      width="700px"
      custom-class="home_warn_details">
      <n-details :oPath="oPath" :values="values" v-if="detailsVisible"></n-details>
    </el-dialog>
  </div>
</template>

<script>
import {warnList} from "@/api/warnType";
import {platform_list} from "@/config";
import nDetails from "./nDetails";

export default {
  components: {nDetails},
  props: {
    warnTypes: {
      type: Array,
      default: () => []
    },
    warnLevels: {
      type: Array,
      default: () => []
    },
    platFormPath: {
      type: String,
      default: ""
    },
    oId: {
      type: String,
      default: "'"
    }
  },
  computed: {
    tagHead () {
      return platform_list.find(i => i.path === this.platFormPath).id;
    }
  },
  watch: {
    platFormPath: {
      handler (v) {
        if (v) {
          this.getList();
        }
      },
      immediate: true
    }
  },
  data () {
    return {
      detailsVisible: false,
      loading: false,
      oPath: "",
      values: {},
      pagerCount: 5,
      queryForm: {
        procStatus: null,
        warnLevel: null,
        warnType: null,
        oTime: null,
        tagHeadId: null,
        sourceId: null
      },
      tableData: [],
      page: {
        pageNum: 1,
        pageSize: 30,
      },
      total: 0
    };
  },
  methods: {
    getList (v = {}) {
      this.loading = true;
      let {oTime, ...rest} = v;
      if (v.oTime && v.oTime.length > 0) {
        rest.startDate = v.oTime[0];
        rest.endDate = v.oTime[1];
      }
      rest.tagHeadId = this.tagHead;
      rest.sourceId = this.oId;
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
    rowClick (v) {
      this.oPath = platform_list.find(i => i.id === v.tagHeadId.toString()).path;
      this.values = v;
      this.detailsVisible = true;
    },
    cChange (v) {
      this.page.pageNum = v;
      this.getList(this.queryForm);
    },
    handleQuery () {
      this.page.pageNum = 1;
      this.getList(this.queryForm);
    },
    resetQuery () {
      this.queryForm = {
        procStatus: null,
        warnLevel: null,
        warnType: null,
        oTime: null,
        tagHeadId: null,
        sourceId: null
      };
      this.handleQuery();
    }
  }
};
</script>

<style lang='scss' scoped>
.home-warn-wk {
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  overflow: hidden;

  .home-queryForm {
    /deep/ .el-form-item__label {
      color: #fff;
    }
  }

  /deep/ .home_warn_details {
    width: 100%;
    height: auto;
    max-height: calc(100% - 12vh);
    border: 2px solid #797979;
    overflow: hidden;
    display: flex;
    flex-direction: column;
    background: rgba(23, 32, 47, 1);

    .el-dialog__header {
      .el-dialog__title {
        color: #fff;
      }

      button {
        i {
          color: #fff;
        }
      }
    }

    .el-dialog__body {
      flex: 1;
      overflow: hidden;
      padding: 10px;
    }
  }
}

.mapindex-page {
  margin-top: 8px;

  /deep/ .el-pagination .btn-next {
    background-color: transparent !important;
    color: #eee;
  }

  /deep/ .el-pagination {
    text-align: center;
  }

  /deep/ .el-pagination__total {
    color: #eee;
  }

  /deep/ .el-pagination button:disabled {
    background-color: transparent !important;
    color: #eee;

    &:hover {
      color: #eee;
    }
  }

  /deep/ .el-pagination .btn-prev,
  .el-pagination .btn-next {
    background-color: transparent !important;
    color: #eee;

    &:hover {
      color: #1890ff;
    }
  }

  /deep/ .el-pager {
    li {
      background-color: transparent !important;
      color: #eee;

      &:hover {
        color: #1890ff;
      }

      &.active {
        color: #1890ff;
      }
    }
  }
}
</style>
