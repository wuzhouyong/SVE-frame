<template>
  <div class="base-list">
    <!-- 搜索表单 -->
    <el-form :model="queryForm" ref="queryForm" :inline="true" class="search-form">
      <el-form-item label="检测井" prop="checkPointName">
        <el-input
          v-model="queryForm.checkPointName"
          placeholder="请输入检测井"
          clearable
          size="mini"
          style="width: 180px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="检测井类型" prop="checkPointType">
        <el-select
          v-model="queryForm.checkPointType"
          placeholder="请选择检测井类型"
          style="width:100%"
          size="mini"
        >
          <el-option
            :label="i.dictLabel"
            :value="i.dictValue"
            v-for="i in checkPointTypeList"
            :key="i.dictValue"
          ></el-option>
        </el-select>
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
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-refresh-right"
          size="mini"
          :disabled="!single"
          @click="()=>{
                collVisible=true;
                collectId=selectRows[0].collectId
          }"
        >修改密码</el-button>
      </el-col>
      <el-col :span="21" style="display:flex;text-align:right;justify-content: flex-end;">
        <div style="font-size:13px;">
          <el-tag>WQI(ps0001)</el-tag>
        </div>
        <div style="font-size:13px;margin-left:10px;">
          污水因子:
          <el-tag>pH(wc10)</el-tag>
          <el-tag>电导率(wc12)</el-tag>
        </div>
        <div style="font-size:13px;margin-left:10px;">
          雨水因子:
          <el-tag>pH(wd10)</el-tag>
          <el-tag>电导率(wd12)</el-tag>
        </div>
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
      <el-table-column prop="checkPointName" label="检测井名称"></el-table-column>
      <el-table-column prop="checkPointType" label="检测井类型" :formatter="cPointType" width="140"></el-table-column>
      <el-table-column prop="brandName" label="设备品牌" width="180"></el-table-column>
      <el-table-column prop="mn" label="MN码" width="160"></el-table-column>
      <el-table-column prop="ipAddress" label="Ip地址" width="180"></el-table-column>
      <el-table-column prop="checkDate" label="检测时间" width="180">
        <template slot-scope="scope">{{scope.row.checkDate?scope.row.checkDate:'-'}}</template>
      </el-table-column>
      <el-table-column prop="isOnline" align="center" label="是否在线" width="100">
        <template slot-scope="scope">{{scope.row.isOnline=== "1"?"在线":"离线"}}</template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <pagination
      :total="total"
      :page.sync="page.pageNum"
      :limit.sync="page.pageSize"
      @pagination="getList(queryForm)"
    />
    <checkpoint-form
      :title="title"
      :values="editValues"
      :visible.sync="editVisible"
      :checkPointTypeList="checkPointTypeList"
      @completed="successRequest"
    ></checkpoint-form>
    <ChangePwd
      v-if="collVisible"
      :visible.sync="collVisible"
      platform="outwater"
      :collectId="collectId"
      @successPwd="successPwd"
    ></ChangePwd>
  </div>
</template>
<script>
import { list, checkConstruction, remove } from "@/api/collect";
import { getDict } from "@/api/source";
import CheckpointForm from "./form";
import ChangePwd from "../changePwd";
export default {
  components: { CheckpointForm, ChangePwd },
  data() {
    return {
      loading: true,
      title: undefined,
      editVisible: false,
      editValues: {},
      //查询表单
      queryForm: {
        mn: undefined,
        checkPointName: undefined,
        checkPointType: undefined,
      },
      // 分页
      page: {
        pageNum: 1,
        pageSize: 20,
      },
      total: 0,
      //表格数据
      tableData: [],
      selectRows: [],
      //检测井类型
      checkPointTypeList: [],
      collVisible: false,
      collectId: "",
    };
  },
  computed: {
    sys_name() {
      return this.$route.path.substr(this.$route.path.lastIndexOf("/") + 1);
    },
    ids() {
      return this.selectRows.map((item) => item.checkPointId);
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
    getDict("check_point_type").then((response) => {
      this.checkPointTypeList = response.data;
    });
  },
  methods: {
    /** 获取列表 **/
    getList(v = {}) {
      list(this.sys_name, v, this.urlParameter(this.page)).then((res) => {
        if (res.code === 200) {
          this.tableData = res.rows || res.data;
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
      this.handleQuery();
    },
    //提交
    submitFormBtn() {
      this.$refs.formRef.submitForm();
    },
    //操作成功
    successRequest() {
      this.getList();
      this.editVisible = false;
      this.editValues = {};
    },
    /** 新增按钮操作 */
    handleAdd() {
      checkConstruction(this.sys_name).then((res) => {
        if (res.code === 200) {
          if (res.data) {
            this.editVisible = true;
            this.editValues = {};
            this.title = "添加检测井";
          } else {
            this.msgError("请先到进度登记页面登记施工单位信息");
          }
        }
      });
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.selectRows = selection;
    },
    /** 修改按钮操作 */
    handleUpdate() {
      this.editVisible = true;
      this.editValues = this.selectRows[0];
      this.title = "修改检测井";
    },
    //检测井类型
    cPointType(row) {
      return this.selectDictLabel(this.checkPointTypeList, row.checkPointType);
    },
    /** 删除按钮操作 */
    handleDelete() {
      const row = this.selectRows[0];
      const postIds = row.checkPointId || this.ids;
      this.$confirm("是否确认删除选中数据数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          return remove(this.sys_name, this.ids);
        })
        .then(() => {
          this.getList(this.queryForm);
          this.msgSuccess("删除成功");
        })
        .catch(() => {});
    },
    successPwd() {
      this.collVisible = false;
      this.getList(this.queryForm);
    },
  },
};
</script>
<style lang="scss">
.mb8 {
  display: flex;
  align-items: center;
}
</style>
