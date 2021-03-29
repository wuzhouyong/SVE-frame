<template>
  <div class="task-wk">
    <!-- 搜索表单 -->
    <el-form :model="queryForm" ref="queryForm" :inline="true">
      <el-form-item label="处置企业" prop="sourceId">
        <el-select
          v-model="queryForm.sourceId"
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
      <el-form-item label="司机姓名" prop="driverName">
        <el-input
          v-model="queryForm.driverName"
          placeholder="请输入司机姓名"
          clearable
          size="mini"
          style="width: 180px"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <!-- 操作按钮 -->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['lingxing:driver:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="!single"
          @click="handleUpdate"
          v-hasPermi="['lingxing:driver:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['lingxing:driver:del']"
        >删除</el-button>
      </el-col>
    </el-row>
    <!-- 表格 -->
    <el-table
      v-loading="loading"
      :data="tableData"
      style="width: 100%;flex:1;"
      @selection-change="handleSelectionChange"
      ref="oTable"
      height="100%"
      border
    >
      <el-table-column type="selection" width="55" align="center"></el-table-column>
      <el-table-column prop="fullName" label="所属企业"></el-table-column>
      <el-table-column prop="driverName" label="司机名称"></el-table-column>
      <el-table-column prop="driverId" label="身份证号"></el-table-column>
      <el-table-column prop="loginAccount" label="登录账号"></el-table-column>
      <el-table-column prop="loginPassword" label="登录密码"></el-table-column>
      <el-table-column prop="memo" label="备注"></el-table-column>
    </el-table>
    <!-- 分页 -->
    <pagination
      :total="total"
      :page.sync="page.pageNum"
      :limit.sync="page.pageSize"
      @pagination="getList(queryForm)"
    />
    <!-- 新增修改 -->
    <formTem
      :title="title"
      :values="editValues"
      :visible.sync="editVisable"
      :sourceList="sourceList"
      @completed="successRequest"
    ></formTem>
  </div>
</template>
<script>
import {
  tankcarEnterprise,
  driverinfoList,
  driverinfoDel,
} from "@/api/lingxing";
import { mapActions } from "vuex";
import formTem from "./form";
export default {
  components: { formTem },
  data() {
    return {
      loading: true,
      title: undefined,
      editVisable: false,
      editValues: {},
      //查询表单
      queryForm: { driverName: "", sourceId: "" },
      sourceList: [],
      // 分页
      page: {
        pageNum: 1,
        pageSize: 30,
      },
      total: 0,
      tableData: [],
      selectRows: [],
    };
  },
  computed: {
    ids() {
      return this.selectRows.map((item) => item.driverId);
    },
    single() {
      return this.selectRows.length === 1;
    },
    multiple() {
      return !this.selectRows.length;
    },
  },
  created() {
    this.getSourceList();
    this.getList();
  },
  methods: {
    getSourceList() {
      tankcarEnterprise().then((res) => {
        if (res.code === 200) {
          this.sourceList = res.data || res.rows;
        }
      });
    },
    /** 获取列表 **/
    getList(v = {}) {
      driverinfoList(v, this.urlParameter(this.page)).then((res) => {
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
        driverName: "",
        sourceId: "",
      };
      this.handleQuery();
    },
    //操作成功
    successRequest() {
      this.getList(this.queryForm);
      this.editVisable = false;
      this.editValues = {};
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.editVisable = true;
      this.editValues = {};
      this.title = "新增司机";
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.selectRows = selection;
    },
    /** 修改按钮操作 */
    handleUpdate() {
      this.editVisable = true;
      this.editValues = this.selectRows[0];
      this.title = "修改司机资料";
    },
    /** 删除按钮操作 */

    handleDelete() {
      const postIds = this.ids.join(",");
      this.$confirm("是否确认删除选中数据数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(function () {
          return driverinfoDel(postIds);
        })
        .then(() => {
          this.getList(this.queryForm);
          this.msgSuccess("删除成功");
        })
        .catch(function () {});
    },
  },
};
</script>
