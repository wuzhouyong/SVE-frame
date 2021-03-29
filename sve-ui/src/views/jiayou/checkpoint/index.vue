<template>
  <div class="task-wk">
    <!-- 搜索表单 -->
    <el-form :model="queryForm" ref="queryForm" :inline="true" class="jiayou-serchForm">
      <el-form-item label="污染源" prop="sourceName">
        <el-input
          v-model="queryForm.sourceName"
          placeholder="请输入污染源"
          clearable
          size="mini"
          style="width: 180px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="数采名称" prop="cpName">
        <el-input
          v-model="queryForm.cpName"
          placeholder="请输入数采名称"
          clearable
          size="mini"
          style="width: 180px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="MN码" prop="mn">
        <el-input
          v-model="queryForm.mn"
          placeholder="请输入MN码"
          clearable
          size="mini"
          style="width: 180px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <!-- 操作按钮 -->
    <!--    <el-row :gutter="10" class="mb8">-->
    <!--      <el-col :span="1.5">-->
    <!--        <el-button-->
    <!--          type="primary"-->
    <!--          icon="el-icon-plus"-->
    <!--          size="mini"-->
    <!--          @click="handleAdd"-->
    <!--          v-hasPermi="['jiayou:checkpoint:add']"-->
    <!--        >新增</el-button>-->
    <!--      </el-col>-->
    <!--      <el-col :span="1.5">-->
    <!--        <el-button-->
    <!--          type="success"-->
    <!--          icon="el-icon-edit"-->
    <!--          size="mini"-->
    <!--          :disabled="!single"-->
    <!--          @click="handleUpdate"-->
    <!--          v-hasPermi="['jiayou:checkpoint:edit']"-->
    <!--        >修改</el-button>-->
    <!--      </el-col>-->
    <!--      <el-col :span="1.5">-->
    <!--        <el-button-->
    <!--          type="danger"-->
    <!--          icon="el-icon-delete"-->
    <!--          size="mini"-->
    <!--          :disabled="multiple"-->
    <!--          @click="handleDelete"-->
    <!--          v-hasPermi="['jiayou:checkpoint:del']"-->
    <!--        >删除</el-button>-->
    <!--      </el-col>-->
    <!--    </el-row>-->
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
      <el-table-column prop="sourceName" label="污染源" show-overflow-tooltip></el-table-column>
      <el-table-column prop="cpName" label="数采名称" width="180"></el-table-column>
      <el-table-column prop="mn" label="MN码" width="180"></el-table-column>
      <el-table-column prop="acquisitionBrand" label="品牌" width="180"></el-table-column>
      <el-table-column prop="ipAddress" label="Ip地址" width="180"></el-table-column>
      <el-table-column prop="checkDate" label="检测时间" width="180">
        <template slot-scope="scope">{{ scope.row.checkDate ? scope.row.checkDate : "-" }}</template>
      </el-table-column>
      <el-table-column prop="isEnvcert" label="是否中环认证" width="180">
        <template slot-scope="scope">{{ scope.row.isEnvcert === "false" ? "否" : "是" }}</template>
      </el-table-column>
      <el-table-column prop="isOnline" align="center" label="是否在线" width="100">
        <template slot-scope="scope">{{ scope.row.isOnline === "1" ? "在线" : "离线" }}</template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <pagination
      :total="total"
      :page.sync="page.pageNum"
      :limit.sync="page.pageSize"
      @pagination="getList(queryForm)"
    />
    <!-- 新增修改 -->
    <checkpoint-form
      :title="title"
      :values="editValues"
      :visible.sync="editVisable"
      @completed="successRequest"
    ></checkpoint-form>
  </div>
</template>
<script>
import { checkpointList, checkpointDel } from "@/api/jiayou";
import { mapActions } from "vuex";
import CheckpointForm from "./form";

export default {
  components: { CheckpointForm },
  data() {
    return {
      loading: true,
      title: undefined,
      editVisable: false,
      editValues: {},
      //查询表单
      queryForm: {
        fullName: undefined,
        mn: undefined,
        cpName: undefined
      },
      // 分页
      page: {
        pageNum: 1,
        pageSize: 30
      },
      total: 0,
      tableData: [],
      selectRows: []
    };
  },
  computed: {
    ids() {
      return this.selectRows.map(item => item.collectId);
    },
    single() {
      return this.selectRows.length === 1;
    },
    multiple() {
      return !this.selectRows.length;
    }
  },
  created() {
    this.getList();
  },
  methods: {
    ...mapActions("company", ["SaveSearchCompany"]),
    /** 获取列表 **/
    getList(v = {}) {
      checkpointList(v, this.urlParameter(this.page)).then(res => {
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
        fullName: undefined,
        mn: undefined,
        cpName: undefined
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
      this.title = "新增数采设备";
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.selectRows = selection;
    },
    /** 修改按钮操作 */
    handleUpdate() {
      this.editVisable = true;
      this.editValues = this.selectRows[0];
      this.title = "修改数采设备";
    },
    /** 删除按钮操作 */

    handleDelete() {
      const postIds = this.ids.join(",");
      this.$confirm("是否确认删除选中数据数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(function() {
          return checkpointDel(postIds);
        })
        .then(() => {
          this.getList(this.queryForm);
          this.msgSuccess("删除成功");
        })
        .catch(function() {});
    }
  }
};
</script>
