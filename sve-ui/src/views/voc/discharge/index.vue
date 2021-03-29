<template>
  <div class="task-wk">
    <!-- 搜索表单 -->
    <el-form :model="queryForm" ref="queryForm" :inline="true" class="outwater-serchForm">
      <el-form-item label="污染源" prop="sourceName">
        <el-input
          v-model="queryForm.sourceName"
          placeholder="请输入污染源"
          clearable
          size="mini"
          style="width: 180px"
        />
      </el-form-item>
      <el-form-item label="排放口" prop="outletName">
        <el-input
          v-model="queryForm.outletName"
          placeholder="请输入检测井"
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
          v-hasPermi="['voc:discharge:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="!single"
          @click="handleUpdate"
          v-hasPermi="['voc:discharge:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['voc:discharge:del']"
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
      <el-table-column prop="sourceName" label="污染源"></el-table-column>
      <el-table-column prop="outletName" label="排放口"></el-table-column>
      <el-table-column prop="memo" label="备注"></el-table-column>
    </el-table>
    <!-- 分页 -->
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="page.pageNum"
      :limit.sync="page.pageSize"
      @pagination="getList(queryForm)"
    />
    <!-- 弹窗 -->
    <el-dialog :title="title" :visible.sync="visible" width="30%">
      <el-form ref="elForm" :model="formData" :rules="rules" size="mini" label-width="80px">
        <el-form-item label="污染源" prop="sourceId">
          <SelectSource
            v-model="formData.sourceId"
            :data="sourceList"
            placeholder="请输入污染源"
            isPlatName="voc"
          ></SelectSource>
        </el-form-item>
        <el-form-item label="排放口" prop="outletName">
          <el-input v-model="formData.outletName" placeholder="请输入排放口" clearable size="mini"></el-input>
        </el-form-item>
        <el-form-item label="备注" prop="memo">
          <el-input
            type="textarea"
            v-model="formData.memo"
            placeholder="请输入备注"
            clearable
            size="mini"
          ></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="onSubmit" v-preventClick>提 交</el-button>
        <el-button @click="visible = false">取 消</el-button>
      </span>
    </el-dialog>
  </div>
</template>
<script>
import SelectSource from "@/components/SelectSource";
import {
  sourceList,
  outletList,
  outletAdd,
  outletEdit,
  outletDel,
} from "@/api/voc";
export default {
  components: { SelectSource },
  data() {
    return {
      sourceList,
      loading: false,
      visible: false,
      queryForm: {},
      tableData: [],
      selectRows: [],
      title: "",
      formData: {},
      // 分页
      page: {
        pageNum: 1,
        pageSize: 30,
      },
      total: 0,
      rules: {
        sourceId: [
          {
            required: true,
            message: "污染源不能为空",
            trigger: "blur",
          },
        ],
        outletName: [
          {
            required: true,
            message: "排放口不能为空",
            trigger: "blur",
          },
        ],
      },
    };
  },
  created() {
    this.getList(this.queryForm);
  },
  computed: {
    ids() {
      return this.selectRows.map((item) => item.outletId);
    },
    single() {
      return this.selectRows.length === 1;
    },
    multiple() {
      return !this.selectRows.length;
    },
  },
  methods: {
    getList(v = {}) {
      outletList(v, this.urlParameter(this.page)).then((res) => {
        if (res.code === 200) {
          this.tableData = res.rows;
          this.total = res.total;
        }
      });
    },
    onSubmit() {
      this.$refs["elForm"].validate((valid) => {
        if (valid) {
          if (this.formData.outletId) {
            outletEdit(this.formData).then((res) => {
              if (res.code === 200) {
                this.msgSuccess("修改成功");
                this.visible = false;
                this.formData = {};
                this.getList(this.queryForm);
              }
            });
          } else {
            outletAdd(this.formData).then((res) => {
              if (res.code === 200) {
                this.msgSuccess("新增成功");
                this.visible = false;
                this.formData = {};
                this.getList(this.queryForm);
              }
            });
          }
        }
      });
    },
    handleAdd() {
      this.title = "新增排放口";
      this.$refs.oTable.clearSelection();
      this.formData = {};
      this.visible = true;
    },
    handleUpdate() {
      this.title = "编辑排放口";
      this.formData = this.selectRows[0];
      this.visible = true;
    },
    handleDelete() {
      const postIds = this.ids.join(",");
      this.$confirm("是否确认删除选中数据数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(function () {
          return outletDel(postIds);
        })
        .then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
        .catch(function () {});
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.selectRows = selection;
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.page.pageNum = 1;
      this.getList(this.queryForm);
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
  },
};
</script>