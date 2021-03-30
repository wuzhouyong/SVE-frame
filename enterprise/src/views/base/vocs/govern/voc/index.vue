<template>
  <div class="base-list">
    <!-- 操作按钮 -->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          >新增</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="!single"
          @click="handleUpdate"
          >修改</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          >删除</el-button
        >
      </el-col>
    </el-row>
    <!-- 表格 -->
    <el-table
      :data="tableData"
      v-loading="loading"
      highlight-current-row
      @selection-change="handleSelectionChange"
      style="width: 100%; flex: 1"
      ref="oTable"
      height="100%"
      border
      size="mini"
    >
      <el-table-column
        type="selection"
        width="55"
        align="center"
      ></el-table-column>
      <el-table-column
        prop="facilityName"
        width="500"
        label="治理设施名称"
      ></el-table-column>
      <el-table-column prop="memo" label="备注"></el-table-column>
    </el-table>
    <!-- 分页 -->
    <pagination
      :total="total"
      :page.sync="page.pageNum"
      :limit.sync="page.pageSize"
    />
    <formTem
      :title="title"
      :values="values"
      :visible.sync="visible"
      @completed="successRequest"
    ></formTem>
  </div>
</template>
<script>
import formTem from "./form";
import vocApi from "@/api/voc/discharge";
export default {
  components: { formTem },
  data () {
    return {
      visible: false,
      loading: false,
      title: "",
      values: {},
      tableData: [],
      selectRows: [],
      page: {
        pageNum: 1,
        pageSize: 30,
      },
      total: 0,
    };
  },
  computed: {
    single () {
      return this.selectRows.length === 1;
    },
    multiple () {
      return !this.selectRows.length;
    },
    ids () {
      return this.selectRows.map((item) => item.facilityId);
    },
  },
  created () {
    this.getList();
  },
  methods: {
    getList () {
      this.loading = true;
      vocApi.facilityList().then((res) => {
        if (res.code == 200) {
          this.tableData = res.rows || res.data;
          this.total = res.total;
        }
        this.loading = false;
      });
    },
    handleAdd () {
      this.title = "新增VOCs治理设施";
      this.values = {};
      this.visible = true;
    },
    handleUpdate () {
      console.log('11')
      this.title = "编辑VOCs治理设施";

      this.values = this.selectRows[0];
      this.visible = true;
    },
    handleDelete () {
      this.$confirm("是否确认删除选中数据数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          return vocApi.delFacility(this.ids);
        })
        .then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
        .catch((e) => { });
    },
    handleSelectionChange (selection) {
      this.selectRows = selection;
    },
    successRequest () {
      this.getList();
      this.visible = false;
      this.values = {};
    },
  },
};
</script>