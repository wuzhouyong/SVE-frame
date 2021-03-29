<template>
  <div class="task-wk">
    <!-- 搜索表单 -->
    <el-form :model="queryForm" ref="queryForm" :inline="true">
      <el-form-item label="问题内容" prop="questionDesc">
        <el-input
          v-model="queryForm.questionDesc"
          placeholder="问题内容"
          clearable
          size="mini"
          style="width: 180px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="问题分数" prop="fullMarks">
        <el-input
          v-model="queryForm.fullMarks"
          placeholder="请输入问题分数"
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
          :disabled="!single"
          @click="handleUpdate"
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
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-tickets"
          size="mini"
          :disabled="multiple"
          @click="releaseQuestion"
        >发布评分问卷
        </el-button>
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
      <el-table-column prop="questionDesc" label="问题内容" show-overflow-tooltip></el-table-column>
      <el-table-column prop="fullMarks" label="问题满分" width="80" align="center"></el-table-column>
      <el-table-column label="状态" width="90" align="center">
        <template slot-scope="scope">
          {{ scope.row.isUse === 0 ? '未使用' : '已使用' }}
        </template>
      </el-table-column>
    </el-table>
    <el-dialog
      title="发布评分问卷设置"
      :close-on-click-modal="false"
      :visible.sync="releaseVisible"
      width="30%">
      <n-release v-if="releaseVisible" :nQuestion="selectRows" ref="nRelease" @isSuccess="isSuccess"></n-release>
      <span slot="footer" class="dialog-footer">
    <el-button type="primary" icon="el-icon-finished" @click="subRelease">发 布</el-button>
  </span>
    </el-dialog>
    <!-- 新增修改 -->
    <n-form
      :title="title"
      :values="values"
      :visible.sync="visable"
      v-if="visable"
      @completed="successRequest"
    ></n-form>
  </div>
</template>
<script>
import {mochaQuestionDevopsList, mochaQuestionDevopsDel} from '@/api/mocha'
import nForm from "./form";
import nRelease from "./nRelease";

export default {
  components: {nForm, nRelease},
  data() {
    return {
      loading: false,
      title: null,
      visable: false,
      releaseVisible: false,
      values: {},
      //查询表单
      queryForm: {
        questionDesc: null,
        fullMarks: null
      },
      tableData: [],
      selectRows: []
    };
  },
  computed: {
    ids() {
      return this.selectRows.map((item) => item.questionId);
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
      this.loading = true;
      mochaQuestionDevopsList(v).then(res => {
        this.tableData = res.rows;
        this.loading = false;
      })
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.getList(this.queryForm);
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.queryForm = {
        questionDesc: null,
        fullMarks: null
      };
      this.handleQuery();
    },
    //操作成功
    successRequest() {
      this.getList(this.queryForm);
      this.visable = false;
      this.values = {};
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.visable = true;
      this.values = {};
      this.title = "新增问题";
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.selectRows = selection;
    },
    /** 修改按钮操作 */
    handleUpdate() {
      if (this.selectRows[0].isUse === 0) {
        this.visable = true;
        this.values = this.selectRows[0];
        console.log(this.values);
        this.title = "修改问题";
      } else {
        this.msgError('此问题已使用,无法修改!');
      }

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
          return mochaQuestionDevopsDel(postIds);
        })
        .then(() => {
          this.getList(this.queryForm);
          this.msgSuccess("删除成功");
        })
        .catch(function () {
        });
    },
    //发布问卷
    releaseQuestion() {
      this.releaseVisible = true;
    },
    subRelease() {
      this.$refs.nRelease.subMit();
    },
    isSuccess(){
      this.releaseVisible = false;
    }
  }
};
</script>
