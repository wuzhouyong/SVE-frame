<template>
  <div class="base-list">
    <!-- 操作按钮 -->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAdd">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="!single"
          @click="handleUpdate"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
        >删除</el-button>
      </el-col>
    </el-row>
    <!-- 表格 -->
    <el-table
      :data="tableData"
      v-loading="loading"
      highlight-current-row
      @selection-change="handleSelectionChange"
      style="width: 100%;flex:1;"
      ref="oTable"
      height="100%"
      border
      size="mini"
    >
      <el-table-column type="selection" width="55" align="center"></el-table-column>
      <el-table-column prop="facilityName" width="500" label="生产线电表名称"></el-table-column>
      <el-table-column prop="memo" label="备注"></el-table-column>
    </el-table>
    <!-- 分页 -->
    <pagination :total="total" :page.sync="page.pageNum" :limit.sync="page.pageSize" />
    <!-- @pagination="getList(queryForm)" -->
    <disChargeForm
      :title="title"
      :values="editValues"
      :visible.sync="visible"
      @completed="successRequest"
    ></disChargeForm>
  </div>
</template>
<script>
import disChargeForm from "./form";
import FactorType from "@/api/voc/discharge";
export default {
  data() {
    return {
      title: "",
      visible: false,
      loading: false,
      values: {},
      editValues: {},
      tableData: [],
      selectRows: [],
      page: {
        pageNum: 1,
        pageSize: 30,
      },
      total: 0,
    };
  },
  created() {
    this.getProductionList();
  },
  computed: {
    single() {
      return this.selectRows.length === 1;
    },
    multiple() {
      return !this.selectRows.length;
    },
    ids() {
      return this.selectRows.map((item) => item.facilityId);
    },
  },
  components: {
    disChargeForm,
  },
  methods: {
    //获取表格数据
    getProductionList() {
      this.loading = true;
      FactorType.getProduction().then((res) => {
        if (res.code == 200) {
          this.tableData = res.data || res.rows;
          this.total = res.total;
        }
        this.loading = false;
      });
    },
    //添加操作
    handleAdd() {
      this.title = "新增VOCs生产线电表";
      this.editValues = {};
      this.visible = true;
    },
    //修改操作
    handleUpdate() {
      this.title = "编辑VOCs生产线电表";
      this.visible = true;
      this.editValues = JSON.parse(JSON.stringify(this.selectRows[0]));
    },
    //删除操作
    handleDelete() {
      this.$confirm("是否确认删除选中数据数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          return FactorType.removeProduction(this.ids);
        })
        .then(() => {
          this.getProductionList();
          this.msgSuccess("删除成功");
        })
        .catch((e) => {});
    },

    handleSelectionChange(selection) {
      this.selectRows = selection;
    },
    successRequest() {
      this.getProductionList();
      this.visible = false;
      this.editValues = {};
    },
  },
};
</script>
<style scoped>
</style>