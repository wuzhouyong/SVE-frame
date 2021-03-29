<template>
  <div class="task-wk">
    <!-- 搜索表单 -->
    <el-form :model="queryForm" ref="queryForm" :inline="true">
      <el-form-item label="运维单位" prop="fullName">
        <el-input
          v-model="queryForm.fullName"
          placeholder="运维单位"
          clearable
          size="mini"
          style="width: 180px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="运维类型" prop="opSourceType">
        <el-radio-group v-model="queryForm.opSourceType" size="mini">
          <el-radio-button border label="0">施工单位</el-radio-button>
          <el-radio-button border label="1">施工、运维单位</el-radio-button>
          <el-radio-button border label="2">运维单位</el-radio-button>
        </el-radio-group>
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
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          @click="handleUpdate"
          :disabled="!single"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
        >删除
        </el-button>
      </el-col>
    </el-row>
    <!-- 表格 -->
    <el-table
      v-loading="loading"
      :data="tableData"
      size="mini"
      style="width: 100%;flex:1;"
      @selection-change="handleSelectionChange"
      ref="oTable"
      height="100%"
      border
    >
      <el-table-column type="selection" width="55" align="center"></el-table-column>
      <el-table-column prop="fullName" label="运维单位" show-overflow-tooltip></el-table-column>
      <el-table-column prop="shortName" label="简称" width="180px"></el-table-column>
      <el-table-column label="运维类型" width="140px">
        <template slot-scope="scope">
          {{ devopsTypes[scope.row.opSourceType] }}
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <pagination
      v-if="total>0"
      :total="total"
      :page.sync="page.pageNum"
      :limit.sync="page.pageSize"
      @pagination="getList(queryForm)"
    />
    <!-- 新增修改 -->
    <n-form
      :title="title"
      :values="values"
      :visible.sync="visible"
      @completed="successRequest"
    ></n-form>
  </div>
</template>
<script>
import {mochaDevopsList, mochaDevopsDel} from '@/api/mocha'
import nForm from "./form";

export default {
  components: {nForm},
  data() {
    return {
      loading: false,
      title: null,
      visible: false,
      values: {},
      //查询表单
      queryForm: {
        fullName: null,
        opSourceType: null,
      },
      // 分页
      page: {
        pageNum: 1,
        pageSize: 30
      },
      total: 0,
      tableData: [],
      selectRows: [],
      devopsTypes: ['施工单位', '施工、运维单位', '运维单位']
    };
  },
  computed: {
    ids() {
      return this.selectRows.map(item => item.opSourceId);
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
      mochaDevopsList(v, this.urlParameter(this.page)).then(res => {
        this.tableData = res.rows;
        this.total = res.total;
      })
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.page.pageNum = 1;
      this.getList(this.queryForm);
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.queryForm = {
        fullName: null,
        opSourceType: null,
      };
      this.handleQuery();
    },
    //操作成功
    successRequest() {
      this.getList(this.queryForm);
      this.visible = false;
      this.values = {};
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.visible = true;
      this.values = {};
      this.title = "新增运维单位";
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.selectRows = selection;
    },
    /** 修改按钮操作 */
    handleUpdate() {
      this.visible = true;
      this.values = this.selectRows[0];
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
        .then(function () {
          return mochaDevopsDel(postIds);
        })
        .then(() => {
          this.getList(this.queryForm);
          this.msgSuccess("删除成功");
        })
        .catch(function () {
        });
    }
  }
};
</script>
