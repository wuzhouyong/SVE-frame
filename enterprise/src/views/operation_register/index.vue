<template>
  <div class="base">
    <!-- 操作按钮 -->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
        >新增
        </el-button
        >
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
      style="width: 100%; flex: 1"
      @selection-change="handleSelectionChange"
      ref="oTable"
      height="100%"
      size="mini"
      border
    >
      <el-table-column
        type="selection"
        width="55"
        align="center"
      ></el-table-column>
      <el-table-column prop="projectTitle" label="合同标题"></el-table-column>
      <el-table-column prop="fullName" label="运维单位" width="230px"></el-table-column>
      <el-table-column prop="tagHeadName" label="监测任务"></el-table-column>
      <el-table-column label="合同开始时间">
        <template slot-scope="scope">
          {{ scope.row.startDate.substr(0, 10) }}
        </template>
      </el-table-column>
      <el-table-column label="合同结束时间">
        <template slot-scope="scope">
          {{ scope.row.endDate.substr(0, 10) }}
        </template>
      </el-table-column>
      <el-table-column prop="memo" label="备注"></el-table-column>
      <el-table-column label="附件" align="center" width="100px">
        <template slot-scope="scope">
          <el-link type="primary" style="font-size:12px;" @click="fileOpen(scope.row)" :underline="false">附件管理</el-link>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <pagination
      :total="total"
      :page.sync="page.pageNum"
      :limit.sync="page.pageSize"
      @pagination="getList()"
    />
    <element-form
      v-if="visible"
      :title="title"
      :values="values"
      :visible.sync="visible"
      @completed="successRequest"
    ></element-form>
    <el-dialog
      title="附件列表"
      :visible.sync="fileVisible"
      width="500px"
    >
      <n-file :fId="fId"></n-file>
    </el-dialog>
  </div>
</template>
<script>
import ElementForm from "./form";
import {
  operateRegister_list,
  operateRegister_del
} from "@/api/operation_unit";
import {construction_config} from "@/config";
import {getSourceTags} from "@/api/menu";
import nFile from "./file";

export default {
  components: {ElementForm, nFile},
  data () {
    return {
      loading: false,
      title: undefined,
      visible: false,
      values: {},
      fileVisible: false,
      fId: null,
      // 查询参数
      page: {
        pageNum: 1,
        pageSize: 30
      },
      total: 0,
      tableData: [],
      selectRows: [],
      tagHeadsArr: []
    };
  },
  filters: {},
  computed: {
    ids () {
      return this.selectRows.map((item) => item.opProjectId);
    },
    single () {
      return this.selectRows.length === 1;
    },
    multiple () {
      return !this.selectRows.length;
    }
  },
  created () {
    getSourceTags().then((res) => {
      this.tagHeadsArr = Object.keys(construction_config)
        .filter((f) => res.data.some((s) => s === f))
        .map(function (item, index) {
          let obj = {};
          obj.value = item;
          obj.label = construction_config[item].name;
          obj.path = construction_config[item].path;
          return obj;
        });
    });
    this.getList();
  },
  methods: {
    /** 获取列表 **/
    getList (v = {}) {
      operateRegister_list(v, this.urlParameter(this.page)).then((res) => {
        if (res.code === 200) {
          this.tableData = res.rows || res.data;
          this.total = res.total;
          this.loading = false;
        }
      });
    },
    /** 新增按钮操作 */
    handleAdd () {
      this.values = {};
      this.title = "新增运维合同";
      this.visible = true;
    },
    // 多选框选中数据
    handleSelectionChange (selection) {
      this.selectRows = selection;
    },
    /** 修改按钮操作 */
    handleUpdate () {
      this.values = this.selectRows[0];
      this.title = "修改";
      this.visible = true;
    },

    //操作成功
    successRequest () {
      this.getList();
      this.visible = false;
    },
    /** 删除按钮操作 */

    handleDelete () {
      this.$confirm("是否确认删除选中数据数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          return operateRegister_del(this.ids);
        })
        .then(() => {
          this.getList(this.queryForm);
          this.msgSuccess("删除成功");
        })
        .catch(() => {
        });
    },
    fileOpen (v) {
      this.fId = v.opProjectId;
      this.fileVisible = true;
    }
  }
};
</script>
<style lang="scss" scoped>
</style>
