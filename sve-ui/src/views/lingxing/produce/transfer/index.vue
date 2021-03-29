<template>
  <!--  零废转移统计-->
  <div class="task-wk">
    <!-- 搜索表单 -->
    <el-form :model="queryForm" ref="queryForm" :inline="true">
      <el-form-item label="企业名称" prop="fullNames">
        <el-input
          v-model="queryForm.fullName"
          placeholder="请输入企业名称"
          clearable
          size="mini"
          style="width: 180px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="镇街" prop="townCode">
        <el-select
          v-model="queryForm.townCode"
          placeholder="请选择镇街名称"
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
      <el-form-item label="日期" prop="jobDate">
        <el-date-picker type="date" :clearable="false" placeholder="选择日期" value-format="yyyy-MM-dd"
                        v-model="queryForm.jobDate"
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
      <el-table-column prop="fullName" label="企业名称" show-overflow-tooltip></el-table-column>
      <el-table-column prop="townName" label="镇街" width="180"></el-table-column>
      <el-table-column prop="socialCreditCode" label="税号" width="180"></el-table-column>
      <el-table-column prop="jobDate" label="时间" width="180"></el-table-column>
      <el-table-column prop="beforeAmt" label="转移前水量" width="180"></el-table-column>
      <el-table-column prop="afterAmt" label="转移后水量" width="180"></el-table-column>
      <el-table-column prop="amt" label="转移量" width="180"></el-table-column>
      <el-table-column prop="factInAmt" label="转移联单登记水量" width="180"></el-table-column>
    </el-table>
    <!-- 分页 -->
    <pagination
      :total="total"
      :page.sync="page.pageNum"
      :limit.sync="page.pageSize"
      @pagination="getList(queryForm)"
    />
  </div>
</template>
<script>
import {lxTransferAmount} from "@/api/lingxing";
import {getRegion} from "@/api/composite/source";
import {fun_date} from '@/utils/ruoyi'

export default {
  data() {
    return {
      loading: true,
      title: null,
      editVisable: false,
      editValues: {},
      //查询表单
      queryForm: {
        fullName: null,
        townCode: null,
        jobDate: null
      },
      // 分页
      page: {
        pageNum: 1,
        pageSize: 30
      },
      total: 0,
      tableData: [],
      selectRows: [],
      townCodeOptions: [],
    };
  },
  created() {
    //镇街列表
    getRegion(4419).then((res) => (this.townCodeOptions = res.data));
    this.queryForm.jobDate = fun_date(-1);
    this.getList(this.queryForm);
  },
  methods: {
    /** 获取列表 **/
    getList(v = {}) {
      if (!v.jobDate) {
        this.msgError("日期必选!");
        return;
      }
      lxTransferAmount(v, this.urlParameter(this.page)).then(res => {
        if (res.code === 200) {
          this.tableData = res.rows;
          this.total = res.total;
        }
      });
      this.loading = false;
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.page.pageNum = 1;
      this.getList(this.queryForm);
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.queryForm = {
        fullName: null,
        townCode: null,
        jobDate: null
      };
      this.handleQuery();
    },

  }
};
</script>
