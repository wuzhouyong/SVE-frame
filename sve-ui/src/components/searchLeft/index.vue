<template>
  <el-card class="box-card sourceLeft-box">
    <!-- 查询 -->
    <el-form :inline="true" :model="form">
      <el-form-item label="所属镇街" prop="townCode">
        <el-select
          v-model="form.townCode"
          placeholder="请选择镇街"
          style="width:100%"
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
      <el-form-item label="企业名称" prop="sourceName">
        <el-input v-model="form.sourceName" placeholder="企业名称" @keyup.enter.native="searchChange"></el-input>
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
        <div style="display:flex">
          <el-button type="primary" class="condit-searchBtn" @click="searchChange" style="flex:1">查 询</el-button>
          <el-button type="info" class="condit-searchBtn" @click="resetQ" style="flex:1">重 置</el-button>
        </div>
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
      <el-table-column prop="sourceName" label="企业名称" show-overflow-tooltip></el-table-column>
    </el-table>
    <!-- 分页 -->
    <div class="condit-page">
      <el-pagination
        :current-page.sync="page.pageNum"
        :page-size="page.pageSize"
        :pager-count="pagerCount"
        layout="prev, pager, next"
        :total="total"
        @current-change="cChange"
      ></el-pagination>
    </div>
  </el-card>
</template>
<script>
import {getRegion} from "@/api/composite/source";
import {getSourceByTag} from "@/api/composite/source";
import {mapState} from "vuex";

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
      form: {tagHeadId: null, townCode: null, sourceName: null, constructionStatus: null},
      // 分页
      page: {
        pageNum: 1,
        pageSize: 30
      },
      total: 0,
      townCodeOptions: [],
      tableData: [],
      construceState: [],
      currentRow: null,
      pagerCount: 5,
    };
  },
  computed: {
    ...mapState("label", ["searchData"]),
    changeData() {
      const {tagHeadId, searchData} = this;
      return {
        tagHeadId,
        searchData
      }
    }
  },
  watch: {
    changeData: {
      handler(v) {
        this.form.tagHeadId = v.tagHeadId;
        this.getList();
      },
      immediate: true,
      deep: true
    }
    // tagHeadId: {
    //   handler(v) {
    //     if (v) {
    //       this.form.tagHeadId = v;
    //       this.getList();
    //     }
    //   },
    //   immediate: true
    // },
    // searchData: {
    //   handler() {
    //     this.getList();
    //   },
    //   immediate: true
    // },
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
      // this.form = this.removePropertyOfNull(this.form);
      let oVal = Object.assign({labelIds: this.searchData}, this.form);
      getSourceByTag(oVal, this.urlParameter(this.page)).then((res) => {
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
      this.page.pageNum = v;
      this.getList();
    },
    searchChange() {
      this.page.pageNum = 1;
      this.getList();
    },
    resetQ() {
      this.form = {
        tagHeadId: this.tagHeadId,
        townCode: null,
        sourceName: null,
        constructionStatus: null
      };
      this.getList();
      this.$emit("resetQuery");
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
