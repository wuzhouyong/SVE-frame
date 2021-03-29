<template>
  <el-card class="box-card sourceLeft-box">
    <!-- 查询 -->
    <el-form :inline="true" :model="form">
      <el-form-item label="所属镇街" prop="townCode">
        <el-select
          v-model="form.townCode"
          placeholder="请选择镇街"
          style="width:100%"
          clearable
          @change="searchChange"
        >
          <el-option
            v-for="(item, index) in townCodeOptions"
            :key="index"
            :label="item.regionName"
            :value="item.regionCode"
            :disabled="item.disabled"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="企业名称" prop="fullName">
        <el-input v-model="form.fullName" placeholder="企业名称" @keyup.enter.native="searchChange"></el-input>
      </el-form-item>
      <el-form-item label="施工进度" prop="constructionStatus">
        <el-select
          v-model="form.constructionStatus"
          placeholder="请选择施工进度"
          clearable
          :style="{width: '100%'}"
          size="mini"
        >
          <el-option
            v-for="i in construceState"
            :key="i.dictValue"
            :label="i.dictLabel"
            :value="i.dictValue"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" class="condit-searchBtn" @click="searchChange">查 询</el-button>
        <!--        <div style="display:flex">-->
        <!--          <el-button type="primary" class="condit-searchBtn" @click="searchChange" style="flex:1">查 询</el-button>-->
        <!--          <el-button type="info" class="condit-searchBtn" @click="resetQ" style="flex:1">重 置</el-button>-->
        <!--        </div>-->
      </el-form-item>
    </el-form>
    <!-- 表格 -->
    <el-table
      v-loading="loading"
      :data="tableData"
      border
      @current-change="handleCurrentChange"
      highlight-current-row
      ref="sourceTable"
      class="source-table"
      height="100%"
    >
      <el-table-column prop="townName" label="镇街" width="70" align="center" show-overflow-tooltip></el-table-column>
      <el-table-column prop="fullName" label="企业名称" show-overflow-tooltip></el-table-column>
    </el-table>
    <!-- 分页 -->
    <div class="condit-page">
      <el-pagination
        :current-page.sync="form.pageNum"
        :page-size="form.pageSize"
        :pager-count="pagerCount"
        layout="prev, pager, next"
        :total="total"
        @current-change="cChange"
      ></el-pagination>
    </div>
  </el-card>
</template>
<script>
import { getRegion } from "@/api/composite/source";
import { getSourceList } from "@/api/composite/source";

export default {
  props: {
    tagHeadId: {
      type: String
    }
  },
  data() {
    return {
      btnLoading: false,
      loading: false,
      form: { pageNum: 1, pageSize: 30, tagHeadId: null, townCode: null, fullName: null, constructionStatus: null },
      townCodeOptions: [],
      tableData: [],
      construceState: [],
      currentRow: null,
      pagerCount: 5,
      total: 3
    };
  },
  watch: {
    tagHeadId: {
      handler(v) {
        if (v) {
          this.form.tagHeadId = v;
          this.getList();
        }
      },
      immediate: true
    }
  },
  created() {
    getRegion(4419).then((res) => {
      this.townCodeOptions = res.data;
    });
    this.getDicts("ty_construct_state").then((res) => {
      this.construceState = res.data;
    });
  },
  methods: {
    removePropertyOfNull(v) {
      Object.keys(v).forEach(item => {
        if (!v[item]) delete v[item]
      })
      return v;
    },
    getList() {
      this.loading = true;
      this.form = this.removePropertyOfNull(this.form);
      getSourceList(this.urlParameter(this.form)).then((res) => {
        if (res.code === 200) {
          this.tableData = res.rows;
          this.total = res.total;
        }
        this.loading = false;
      });
    },
    handleCurrentChange(val) {
      if (val) {
        this.currentRow = val;
        this.$emit("selectSource", val);
      }
    },
    cChange(v) {
      this.form.pageNum = v;
      this.getList();
    },
    searchChange() {
      this.form.pageNum = 1;
      this.getList();
    }
  }
};
</script>
<style lang="scss" scope>
.sourceLeft-box {
  width: 100%;
  height: 100%;

  .el-card__body {
    height: 100%;
    width: 100%;
    padding: 5px;
    display: flex;
    flex-direction: column;
    overflow: hidden;

    .el-form-item {
      margin-right: 0;
      margin-bottom: 5px;
      width: 100%;
      display: flex;

      .el-form-item__content {
        flex: 1;
      }
    }
  }

  .condit-searchBtn {
    width: 100%;
  }

  .source-table {
    flex: 1;
    width: 100%;
    overflow: hidden;
  }

  .condit-page {
    .el-pagination {
      text-align: right;
    }
  }
}
</style>
