<template>
  <div class="task-wk">
    <!-- 搜索表单 -->
    <el-form :model="queryForm" ref="queryForm" :inline="true">
      <el-form-item label="企业名称" prop="fullName">
        <el-input
          v-model="queryForm.fullName"
          placeholder="企业名称"
          clearable
          size="mini"
          style="width: 180px"
        />
      </el-form-item>
      <el-form-item label="镇街名称" prop="townCode">
        <el-select
          v-model="queryForm.townCode"
          placeholder="镇街名称"
          clearable
          :style="{width: '140px'}"
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
      <el-form-item label="状态" prop="auditStatus">
        <el-select
          v-model="queryForm.auditStatus"
          placeholder="状态"
          clearable
          :style="{width: '120px'}"
        >
          <el-option
            label="待审核"
            value="0"
          ></el-option>
          <el-option
            label="已同意"
            value="2"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <!--    <el-row :gutter="10" class="mb8">-->
    <!--      <el-col :span="1.5">-->
    <!--        <el-button-->
    <!--          type="primary"-->
    <!--          icon="el-icon-plus"-->
    <!--          size="mini"-->
    <!--        >附件批量下载-->
    <!--        </el-button>-->
    <!--      </el-col>-->
    <!--    </el-row>-->
    <el-table
      :data="tableData"
      :loading="loading"
      size="mini"
      class="taskTable"
      border
      height="100%"
      style="width: 100%">
      <!--      <el-table-column-->
      <!--        align="center"-->
      <!--        type="selection"-->
      <!--        width="55">-->
      <!--      </el-table-column>-->
      <el-table-column
        prop="townName"
        width="80"
        label="镇街"
        align="center">
      </el-table-column>
      <el-table-column
        prop="fullName"
        label="企业名称"
        show-overflow-tooltip
        min-width="250">
      </el-table-column>
      <el-table-column
        label="附件">
        <template slot-scope="scope">
          <el-link type="primary" style="font-size:12px" :href="getlink(scope.row.attachmentId)">
            {{ scope.row.showName }}
          </el-link>
        </template>
      </el-table-column>
      <el-table-column
        prop="auditStatus"
        label="状态">
        <template slot-scope="scope">
          {{ scope.row.auditStatus === 0 ? "待审核" : scope.row.auditStatus === 1 ? "退回" : "已同意" }}
        </template>
      </el-table-column>
      <el-table-column label="操作" width="110" align="center">
        <template slot-scope="scope">
          <div v-if="scope.row.auditStatus=== 0">
            <el-link type="primary" style="font-size:12px" @click="isExamine(scope.row,2)">同意</el-link>
            <el-divider direction="vertical"></el-divider>
            <el-link type="primary" style="font-size:12px" @click="isExamine(scope.row,1)">退回</el-link>
          </div>
          <div v-else-if="scope.row.auditStatus=== 2">
            <el-link type="primary" style="font-size:12px" @click="isExamine(scope.row,1)">撤销同意</el-link>
          </div>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <pagination
      :total="total"
      :page.sync="page.pageNum"
      :limit.sync="page.pageSize"
      @pagination="getList()"
    />
  </div>
</template>

<script>
import { dgomReportList, dgomReportAudit } from "@/api/om"
import { getRegion } from "@/api/composite/source";

export default {
  data() {
    return {
      page: {
        pageNum: 1,
        pageSize: 30
      },
      total: 0,
      loading: false,
      queryForm: {
        fullName: null,
        townCode: null,
        auditStatus: null
      },
      tableData: [],
      townCodeOptions: []
    }
  },
  created() {
    this.getList(this.queryForm);
    //镇街列表
    getRegion(4419).then((res) => (this.townCodeOptions = res.data));
  },
  methods: {
    getList(v = {}) {
      this.loading = true;
      dgomReportList(v, this.urlParameter(this.page)).then(res => {
        if (res.code === 200) {
          this.tableData = res.rows;
          this.total = res.total;
        }
        this.loading = false;
      })
    },
    handleQuery() {
      this.page.pageNum = 1;
      this.getList(this.queryForm);
    },
    resetQuery() {
      this.queryForm = {
        fullName: null,
        townCode: null,
        auditStatus: null
      };
      this.handleQuery();
    },
    isExamine(v, status) {
      this.$confirm("是否确认此操作?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          let oVal = {
            reportId: v.reportId,
            auditStatus: status
          }
          dgomReportAudit(oVal).then(res => {
            if (res.code === 200) {
              this.getList();
            }
          })
        })
        .catch(function() {});
    },
    getlink(id) {
      return `${process.env.VUE_APP_BASE_API}/com/getfile/${id}`;
    }
  }
}
</script>

<style lang="scss" scoped>

</style>
