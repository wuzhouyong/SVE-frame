<template>
  <div class="task-wk">
    <!-- 搜索表单 -->
    <el-form :model="queryForm" ref="queryForm" :inline="true" class="guocheng-serchForm">
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
      <el-form-item label="因子名称" prop="eleDesc">
        <el-input
          v-model="queryForm.eleDesc"
          placeholder="请输入因子名称"
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
    <!--          v-hasPermi="['lingxing:element:add']"-->
    <!--        >新增-->
    <!--        </el-button>-->
    <!--      </el-col>-->
    <!--      <el-col :span="1.5">-->
    <!--        <el-button-->
    <!--          type="success"-->
    <!--          icon="el-icon-edit"-->
    <!--          size="mini"-->
    <!--          :disabled="!single"-->
    <!--          @click="handleUpdate"-->
    <!--          v-hasPermi="['lingxing:element:edit']"-->
    <!--        >修改-->
    <!--        </el-button>-->
    <!--      </el-col>-->
    <!--      <el-col :span="1.5">-->
    <!--        <el-button-->
    <!--          type="danger"-->
    <!--          icon="el-icon-delete"-->
    <!--          size="mini"-->
    <!--          :disabled="multiple"-->
    <!--          @click="handleDelete"-->
    <!--          v-hasPermi="['lingxing:element:del']"-->
    <!--        >删除-->
    <!--        </el-button>-->
    <!--      </el-col>-->
    <!--    </el-row>-->

    <!-- 表格 -->
    <el-table
      v-loading="loading"
      :data="tableData"
      style="width: 100%;flex:1"
      ref="oTable"
      height="100%"
      @selection-change="handleSelectionChange"
      border
    >
      <el-table-column type="selection" width="55" align="center"></el-table-column>
      <el-table-column prop="sourceName" label="污染源" width="250"></el-table-column>
      <el-table-column prop="cpName" label="数采名称"></el-table-column>
      <el-table-column prop="eleDesc" label="因子名称"></el-table-column>
      <el-table-column prop="eleTypeDesc" label="因子类型"></el-table-column>
      <el-table-column prop="eleCode" label="因子编码"></el-table-column>
      <el-table-column prop="installLocation" label="安装位置"></el-table-column>
      <el-table-column prop="checkDate" label="检测时间">
        <template slot-scope="scope">{{ scope.row.checkDate ? scope.row.checkDate : "-" }}</template>
      </el-table-column>
      <el-table-column prop="isOnline" align="center" label="是否在线">
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
    <element-form
      :title="title"
      :values="editValues"
      :visible.sync="editVisable"
      @completed="successRequest()"
    ></element-form>
  </div>
</template>
<script>
import { elementList, elementDel } from "@/api/lingxing";
import ElementForm from "./form";

export default {
  components: { ElementForm },
  data() {
    return {
      loading: true,
      title: undefined,
      editVisable: false,
      editValues: {},
      //查询表单
      queryForm: {
        sourceName: undefined,
        cpName: undefined,
        elemName: undefined
      },
      // 查询参数
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
      return this.selectRows.map(item => item.cpElemId);
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
    /** 获取列表 **/
    getList(v = {}) {
      elementList(v, this.urlParameter(this.page)).then(res => {
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
        sourceName: undefined,
        cpName: undefined,
        elemName: undefined
      };
      this.handleQuery();
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.editVisable = true;
      this.editValues = {};
      this.title = "新增因子";
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.selectRows = selection;
    },
    /** 修改按钮操作 */
    handleUpdate() {
      this.editVisable = true;
      this.editValues = this.selectRows[0];
      this.title = "修改因子";
    },
    //操作成功
    successRequest() {
      this.getList();
      this.editVisable = false;
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
          return elementDel(postIds);
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
