<template>
  <div class="base-list">
    <!-- 搜索表单 -->
    <el-form
      :model="queryForm"
      ref="queryForm"
      :inline="true"
      class="search-form"
    >
      <el-form-item label="预警级别" prop="warnLevel">
        <el-select
          v-model="queryForm.warnLevel"
          placeholder="请选择预警级别"
          icon="el-icon-plus"
          size="mini"
          clearable
        >
          <el-option label="一级预警" value="1"></el-option>
          <el-option label="二级预警" value="2"></el-option>
          <el-option label="三级预警" value="3"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="处理状态" prop="procStateStr">
        <el-select
          v-model="queryForm.procStateStr"
          placeholder="请选择处理状态"
          icon="el-icon-plus"
          size="mini"
          clearable
        >
          <el-option label="待处理" value="0"></el-option>
          <el-option label="退回处理" value="1"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="选择时间">
        <el-date-picker
          v-model="queryForm.dateRange"
          type="daterange"
          size="mini"
          style="width: 220px"
          value-format="yyyy-MM-dd"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
          >搜索</el-button
        >
      </el-form-item>
    </el-form>
    <!-- 操作按钮 -->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          :disabled="!single"
          @click="handleWarn"
          >异常处理</el-button
        >
      </el-col>
    </el-row>
    <!-- 表格 -->
    <el-table
      v-loading="loading"
      :data="tableData"
      highlight-current-row
      @current-change="handleCurrentChange"
      style="width: 100%; flex: 1"
      ref="oTable"
      height="100%"
      border
    >
      <el-table-column
        prop="levelName"
        label="预警级别"
        width="180"
      ></el-table-column>
      <el-table-column
        prop="procName"
        label="处理状态"
        width="180"
      ></el-table-column>
      <el-table-column
        prop="typeName"
        label="预警类型"
        width="180"
      ></el-table-column>
      <el-table-column
        prop="startDate"
        label="预警时间"
        width="180"
      ></el-table-column>
      <el-table-column prop="warnDesc" label="预警描述"></el-table-column>
    </el-table>
    <!-- 分页 -->
    <pagination
      :total="total"
      :page.sync="page.pageNum"
      :limit.sync="page.pageSize"
      @pagination="getList(queryForm)"
    />
    <!-- 新增修改 -->
    <process-form
      :title="title"
      :values="editValues"
      :visible.sync="editVisible"
      @completed="successRequest"
    ></process-form>
  </div>
</template>
<script>
import { factorylist } from "@/api/warn";
import ProcessForm from "./form";
export default {
  components: { ProcessForm },
  data () {
    return {
      loading: true,
      title: undefined,
      editVisible: false,
      editValues: {},
      //查询表单
      queryForm: {},
      // 分页
      page: {
        pageNum: 1,
        pageSize: 30
      },
      total: 0,
      tableData: [],
      selectRow: {}
    };
  },
  computed: {
    sys_name () {
      return this.$route.path.substr(this.$route.path.lastIndexOf("/") + 1);
    },
    id () {
      return this.selectRow.id;
    },
    single () {
      return this.selectRow && this.selectRow.id != null;
    }
  },
  created () {
    this.getList();
  },
  methods: {
    /** 获取列表 **/
    getList (v = {}) {
      factorylist(this.sys_name, v, this.urlParameter(this.page)).then(res => {
        if (res.code === 200) {
          this.tableData = res.rows || res.data;
          this.total = res.total;
        }
      });
      this.loading = false;
    },
    /** 搜索按钮操作 */
    handleQuery () {
      let form = Object.assign({}, this.queryForm);
      if (form.dateRange) {
        let date_range = {
          startDate: form.dateRange[0],
          endDate: form.dateRange[1]
        };
        form = Object.assign({}, form, date_range);
        delete form.dateRange;
      }
      this.page.pageNum = 1;
      this.getList(form);
    },
    // 多选框选中数据
    handleCurrentChange (row) {
      this.selectRow = row;
    },
    //操作成功
    successRequest () {
      this.getList(this.queryForm);
      this.editVisible = false;
      this.editValues = {};
    },
    /** 新增按钮操作 */
    handleWarn () {
      this.editVisible = true;
      this.editValues = this.selectRow;
      this.title = "异常处理";
    }
  }
};
</script>