<template>
  <div class="task-wk">
    <!-- 搜索表单 -->
    <el-form :model="queryForm" ref="queryForm" :inline="true">
      <el-form-item label="污染源" prop="fullName">
        <el-input
          v-model="queryForm.fullName"
          placeholder="请输入污染源"
          clearable
          size="mini"
          style="width: 180px"
        />
      </el-form-item>
      <el-form-item label="镇街" prop="townCode">
        <el-select
          v-model="queryForm.townCode"
          placeholder="镇街"
          clearable
          :style="{width: '160px'}"
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
      <el-form-item label="品牌" prop="brand">
        <el-input
          v-model="queryForm.brand"
          placeholder="品牌"
          clearable
          size="mini"
          style="width: 180px"
        />
      </el-form-item>
      <el-form-item label="施工单位" prop="constructionUnit">
        <el-input
          v-model="queryForm.constructionUnit"
          placeholder="施工单位"
          clearable
          size="mini"
          style="width: 180px"
        />
      </el-form-item>
      <el-form-item label="选择日期" prop="startTime">
        <el-date-picker
          v-model="queryForm.startTime"
          style="width:280px"
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
        <el-button icon="el-icon-upload2" size="mini" type="warning" @click="exportTables">导出表格</el-button>
      </el-form-item>
    </el-form>
    <!--    表格-->
    <el-table
      v-loading="loading"
      :data="tableData"
      style="width: 100%;flex:1;"
      ref="oTable"
      height="100%"
      border
      size="mini"
    >
      <el-table-column prop="fullName" label="污染源" width="220" show-overflow-tooltip></el-table-column>
      <el-table-column prop="townName" label="镇街" width="100"></el-table-column>
      <el-table-column prop="brand" label="品牌" width="140" show-overflow-tooltip></el-table-column>
      <el-table-column prop="constructionUnit" label="施工单位" width="160" show-overflow-tooltip></el-table-column>
      <el-table-column label="异常时间" width="180">
        <template slot-scope="scope">{{ scope.row.startDate.substr(0, 10) }} ~ {{ scope.row.endDate.substr(0, 10) }}
        </template>
      </el-table-column>
      <el-table-column label="异常类型" width="160">
        <template>
          排水企业监管数据异常
        </template>
      </el-table-column>
      <el-table-column prop="warnDesc" label="异常描述" min-width="180"></el-table-column>
    </el-table>
    <!-- 分页 -->
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="page.pageNum"
      :limit.sync="page.pageSize"
      @pagination="getList(queryForm)"
    />
  </div>
</template>

<script>
import { pshExceptSpecList,exportSpecListTable } from "@/api/outwater"
import { GetRegionPower } from "@/api/composite/source";

export default {
  data() {
    return {
      // 分页
      page: {
        pageNum: 1,
        pageSize: 30
      },
      total: 0,
      queryForm: {
        fullName: null,
        startTime: null,
        townCode: null,
        constructionUnit: null,
        brand: null
      },
      tableData: [],
      townCodeOptions: [],
      loading: false
    }
  },
  created() {
    this.getList();
    GetRegionPower().then((res) => {
      this.townCodeOptions = res.data;
    });
  },
  methods: {
    getList(v = {}) {
      this.loading = true;
      pshExceptSpecList(v, this.urlParameter(this.page)).then(res => {
        if (res.code === 200) {
          this.tableData = res.rows;
          this.total = res.total;
        }
        this.loading = false;
      })
    },
    handleQuery() {
      this.page.pageNum = 1;
      let { startTime } = this.queryForm;
      let query = Object.assign({}, this.queryForm);
      if (startTime) {
        let oVal = {
          startDate: startTime[0],
          endDate: startTime[1]
        };
        query = Object.assign({}, query, oVal);
      }
      delete query.startTime;
      this.getList(query);
    },
    resetQuery() {
      this.page.pageNum = 1;
      this.queryForm = {
        fullName: null,
        startTime: null,
        townCode: null,
        constructionUnit: null,
        brand: null
      }
      this.getList();
    },
    exportTables() {
      this.$confirm("是否导出数据?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          let { startTime } = this.queryForm;
          let query = Object.assign({}, this.queryForm);
          if (startTime) {
            let oVal = {
              startDate: startTime[0],
              endDate: startTime[1]
            };
            query = Object.assign({}, query, oVal);
          }
          delete query.startTime;
          return exportSpecListTable(query);
        })
        .then((response) => {
          this.download(response.msg);
        })
        .catch(function () {});
    },
  }
}
</script>

<style lang="scss" scoped>

</style>
