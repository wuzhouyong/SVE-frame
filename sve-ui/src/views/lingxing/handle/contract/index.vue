<template>
  <div class="task-wk">
    <!-- 搜索表单 -->
    <el-form :model="queryForm" ref="queryForm" :inline="true" class="qixiu-serchForm">
      <el-form-item label="产生企业" prop="fullName">
        <el-input
          v-model="queryForm.fullName"
          placeholder="产生企业"
          clearable
          size="mini"
          style="width: 180px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="处置企业" prop="procSourceId">
        <el-select
          v-model="queryForm.procSourceId"
          placeholder="请选择处置企业"
          style="width:250px"
          size="mini"
        >
          <el-option
            :label="i.fullName"
            :value="i.sourceId"
            v-for="i in sourceList"
            :key="i.sourceId"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="开始时间">
        <el-date-picker type="date" placeholder="选择日期" value-format="yyyy-MM-dd" v-model="queryForm.startDate"
                        style="width: 100%;"></el-date-picker>
      </el-form-item>
      <el-form-item label="结束时间">
        <el-date-picker type="date" placeholder="选择日期" value-format="yyyy-MM-dd" v-model="queryForm.endDated"
                        style="width: 100%;"></el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <!-- 表格 -->
    <el-table
      v-loading="loading"
      :data="tableData"
      style="width: 100%;flex:1;"
      ref="oTable"
      height="100%"
      border
    >
      <el-table-column prop="fullName" label="产生企业"></el-table-column>
      <el-table-column prop="shortName" label="处置企业"></el-table-column>
      <el-table-column prop="startDate" label="合同开始时间"></el-table-column>
      <el-table-column prop="endDate" label="合同结束时间"></el-table-column>
      <el-table-column label="附件管理" align="center">
        <template slot-scope="scope">
          <el-link type="primary" style="font-size:12px;" @click="fileSee(scope.row)">附件查看</el-link>
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
      title="附件查看"
      :visible.sync="dialogVisible"
      width="30%">
      <el-table :data="fileList" size="mini" stripe style="width: 100%" height="100%" border>
        <el-table-column prop="showName" label="附件名" show-overflow-tooltip></el-table-column>
        <el-table-column prop="address" label="操作" width="100" align="center">
          <template slot-scope="scope">
            <el-link
              type="primary"
              :underline="false"
              style="font-size:12px;"
              target="_blank"
              :href="getlink(scope.row.Id)"
            >下载
            </el-link>
          </template>
        </el-table-column>
      </el-table>
    </el-dialog>
  </div>
</template>

<script>
import {lxContractList, tankcarEnterprise} from '@/api/lingxing';
import {fileList} from '@/api/upload'

export default {
  data() {
    return {
      queryForm: {
        fullName: null,
        procSourceId: null,
        startDate: null,
        endDated: null
      },
      page: {
        pageNum: 1,
        pageSize: 30
      },
      total: 0,
      tableData: [],
      loading: false,
      sourceList: [],
      dialogVisible: false,
      fileList: []
    }
  },
  created() {
    this.getList();
    this.getSourceList();
  },
  methods: {
    getList(v = {}) {
      lxContractList(v, this.urlParameter(this.page)).then(res => {
        if (res.code === 200) {
          this.tableData = res.rows;
          this.total = res.total;
        }
      })
    },
    getSourceList() {
      tankcarEnterprise().then((res) => {
        if (res.code === 200) {
          this.sourceList = res.data || res.rows;
        }
      });
    },
    handleQuery() {
      this.getList(this.queryForm);
    },
    resetQuery() {
      this.queryForm = {
        fullName: null,
        procSourceId: null,
        startDate: null,
        endDated: null
      };
      this.getList();
    },
    fileSee(v) {
      this.dialogVisible = true;
      let ref = {
        refId: v.contractId,
        refType: "lx_transport_contract"
      }
      fileList(ref).then(res => {
        if (res.code === 200) {
          this.fileList = res.data;
        }
      })
    },
    getlink(id) {
      return `${process.env.VUE_APP_BASE_API}/com/getfile/${id}`;
    },
  }
}
</script>

<style lang='scss' scoped>

</style>
