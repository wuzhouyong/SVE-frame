<template>
  <div class="base">
    <!-- 搜索表单 -->
    <el-form
      :model="queryForm"
      ref="queryForm"
      :inline="true"
      class="search-form"
    >
      <el-form-item label="运维单位" prop="devopsName">
        <el-input
          v-model="queryForm.devopsName"
          placeholder="请输入运维单位"
          clearable
          size="mini"
          style="width: 180px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="运维类型" prop="devopsType">
        <el-select
          v-model="queryForm.devopsType"
          placeholder="请选择运维类型"
          :style="{ width: '100%' }"
          size="mini"
        >
          <el-option
            v-for="item in operatetype"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <!-- <el-form-item label="镇街" prop="labelheadid">
        <el-select
          size="mini"
          v-model="queryForm.townid"
          placeholder="请选择镇街"
        >
          <el-option
            v-for="item in townList"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          >
          </el-option>
        </el-select>
      </el-form-item> -->
      <!-- <el-form-item label="申请状态" prop="status">
        <el-select
          size="mini"
          v-model="queryForm.status"
          placeholder="请选择申请状态"
        >
          <el-option
            v-for="item in applyStatus"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
      </el-form-item> -->
      <!-- <el-form-item label="日期范围" prop="dateRange">
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
      </el-form-item> -->
      <el-form-item>
        <el-button
          type="primary"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
          >搜索</el-button
        >
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
          >重置</el-button
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
      v-loading="loading"
      :data="tableData"
      style="width: 100%; flex: 1"
      @selection-change="handleSelectionChange"
      ref="oTable"
      height="100%"
      border
    >
      <el-table-column
        type="selection"
        width="55"
        align="center"
      ></el-table-column>
      <el-table-column prop="devopsName" label="运维单位"></el-table-column>
      <!-- <el-table-column prop="townname" label="运维类型"></el-table-column> -->

      <el-table-column prop="devopsType" label="运维类型">
        <template slot-scope="scope">
          <span>{{ scope.row.devopsType | statusFormat }}</span>
        </template>
      </el-table-column>

      <el-table-column prop="contacts" label="负责人"></el-table-column>

      <el-table-column prop="contactNum" label="联系电话"></el-table-column>
    </el-table>
    <!-- 分页 -->
    <pagination
      :total="total"
      :page.sync="page.pageNum"
      :limit.sync="page.pageSize"
      @pagination="getList(queryForm)"
    />
    <element-form
      v-if="editVisible"
      :handleType="handleType"
      :title="title"
      :submitSatus="submitSatus"
      :values="editValues"
      :visible.sync="editVisible"
      @completed="successRequest()"
    ></element-form>
  </div>
</template>
<script>
import { list, remove } from "@/api/element";
import ElementForm from "./form";
import {
  operateUnit_list,
  operateUnit_add,
  operateUnit_edit,
  operateUnit_del,
} from "@/api/operation_unit";
export default {
  components: { ElementForm },
  data() {
    return {
      loading: false,
      title: undefined,
      editVisible: false,
      editValues: {},
      //查询表单
      queryForm: {},
      // 查询参数
      page: {
        pageNum: 1,
        pageSize: 30,
      },
      total: 0,
      tableData: [],
      selectRows: [],
      handleType: "",
      // 运维类型
      operatetype: [
        {
          value: "0",
          label: "施工单位",
        },
        {
          value: "1",
          label: "施工运维单位",
        },
        {
          value: "2",
          label: "运维单位",
        },
      ],
    };
  },
  filters: {
    statusFormat: function (val) {
      if (val =="0") {
        return "施工单位";
      } else if (val == "1") {
        return "施工运维单位";
      } else if (val == "2") {
        return "运维单位";
      }
    },
  },
  computed: {
    sys_name() {
      return this.$route.path.substr(this.$route.path.lastIndexOf("/") + 1);
    },
    ids() {
      return this.selectRows.map((item) => item.devopsId);
    },
    single() {
      return this.selectRows.length === 1;
    },
    multiple() {
      return !this.selectRows.length;
    },
  },
  created() {
    this.getList();
  },
  methods: {
    /** 获取列表 **/
    getList(v = {}) {
      operateUnit_list(v, this.urlParameter(this.page)).then((res) => {
        if (res.code === 200) {
          this.tableData = res.rows || res.data;
          this.total = res.total;
          this.loading = false;
        }
      });
    },

    /** 搜索按钮操作 */
    handleQuery() {
      let form = Object.assign({}, this.queryForm);
      this.page.pageNum = 1;
      //   if (form.dateRange) {
      //     var date_range = {
      //       beginTime: form.dateRange[0],
      //       endTime: form.dateRange[1],
      //     };
      //     form = Object.assign({}, form, date_range);
      //     // console.log(form);
      //     delete form.dateRange;
      //   }
      //   console.log(form);
      this.getList(form);
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 新增按钮操作 */
    handleAdd() {
      console.log(123);
      this.handleType = false;
      this.submitSatus = true;
      this.editValues = {};
      this.title = "添加";
      this.editVisible = true;
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.selectRows = selection;
    },
    /** 修改按钮操作 */
    handleUpdate() {
      this.editValues = this.selectRows[0];

      this.handleType = true;
      this.title = "修改";
      this.editVisible = true;
    },

    //操作成功
    successRequest() {
      this.getList();
      this.editVisible = false;
    },
    /** 删除按钮操作 */

    handleDelete() {
      this.$confirm("是否确认删除选中数据数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          return operateUnit_del(this.ids);
        })
        .then(() => {
          this.getList(this.queryForm);
          this.msgSuccess("删除成功");
        })
        .catch(() => {});
    },
  },
};
</script>
<style lang="scss" scoped>
</style>