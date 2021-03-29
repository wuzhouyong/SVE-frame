<template>
  <div class="task-wk">
    <!-- 搜索表单 -->
    <el-form :model="queryForm" ref="queryForm" :inline="true" class="outwater-serchForm">
      <el-form-item label="污染源" prop="fullName">
        <el-input
          v-model="queryForm.fullName"
          placeholder="请输入污染源"
          clearable
          size="mini"
          style="width: 180px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
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
        <el-select v-model="queryForm.checkPointType" placeholder="请选择检测井类型" style="width:100%">
          <el-option
            :label="i.dictLabel"
            :value="i.dictValue"
            v-for="i in checkPointTypeList"
            :key="i.dictValue"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="所属镇街" prop="townCode">
        <el-select
          v-model="queryForm.townCode"
          placeholder="请选择所属镇街"
          clearable
          :style="{width: '160px'}"
        >
          <el-option
            v-for="i in townCodeOptions"
            :key="i.regionCode"
            :label="i.regionName"
            :value="i.regionCode"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="品牌" props="brandName">
        <el-input
          v-model="queryForm.brandName"
          placeholder="请输入品牌"
          clearable
          size="mini"
          style="width: 180px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="施工单位" prop="constructionUnit">
        <el-input
          v-model="queryForm.constructionUnit"
          placeholder="请输入施工单位"
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
    <el-row :gutter="10" class="mb8">
      <!--      <el-col :span="1.5">-->
      <!--        <el-button-->
      <!--          type="primary"-->
      <!--          icon="el-icon-plus"-->
      <!--          size="mini"-->
      <!--          @click="handleAdd"-->
      <!--          v-hasPermi="['outwater:point:add']"-->
      <!--        >新增</el-button>-->
      <!--      </el-col>-->
      <!--      <el-col :span="1.5">-->
      <!--        <el-button-->
      <!--          type="success"-->
      <!--          icon="el-icon-edit"-->
      <!--          size="mini"-->
      <!--          :disabled="!single"-->
      <!--          @click="handleUpdate"-->
      <!--          v-hasPermi="['outwater:point:edit']"-->
      <!--        >修改</el-button>-->
      <!--      </el-col>-->
      <!--      <el-col :span="1.5">-->
      <!--        <el-button-->
      <!--          type="danger"-->
      <!--          icon="el-icon-delete"-->
      <!--          size="mini"-->
      <!--          :disabled="multiple"-->
      <!--          @click="handleDelete"-->
      <!--          v-hasPermi="['outwater:point:remove']"-->
      <!--        >删除</el-button>-->
      <!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-upload2"
          size="mini"
          @click="exportTable"
          v-hasPermi="['outwater:export:table']"
        >导出表格
        </el-button>
      </el-col>
    </el-row>
    <!-- 表格 -->
    <el-table
      v-loading="loading"
      :data="tableData"
      style="width: 100%;flex:1;"
      @selection-change="handleSelectionChange"
      @row-click="rowClick"
      ref="oTable"
      height="100%"
      border
      size="mini"
    >
      <el-table-column type="selection" width="55" align="center"></el-table-column>
      <el-table-column prop="fullName" label="污染源" show-overflow-tooltip></el-table-column>
      <el-table-column prop="townName" label="所属镇街" width="100"></el-table-column>
      <el-table-column prop="checkPointName" label="检测井" width="140" show-overflow-toolti></el-table-column>
      <el-table-column prop="checkPointType" label="检测井类型" :formatter="cPointType" width="100"></el-table-column>
      <el-table-column prop="mn" label="MN码" width="120"></el-table-column>

      <el-table-column prop="brandName" label="品牌" width="120"></el-table-column>
      <el-table-column prop="lon" label="经纬度" width="180">
        <template
          slot-scope="scope"
        >{{ scope.row.longitude ? scope.row.longitude + "，" + scope.row.latitude : "-" }}
        </template>
      </el-table-column>
      <el-table-column prop="ipAddress" label="Ip地址" width="180"></el-table-column>
      <el-table-column prop="constructionUnit" label="施工单位" width="180" show-overflow-tooltip></el-table-column>
      <el-table-column prop="checkDate" label="检测时间" width="180">
        <template slot-scope="scope">{{ scope.row.checkDate ? scope.row.checkDate : "-" }}</template>
      </el-table-column>
      <el-table-column prop="isOnline" align="center" label="是否在线" width="100">
        <template slot-scope="scope">{{ scope.row.isOnline === "1" ? "在线" : "离线" }}</template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="page.pageNum"
      :limit.sync="page.pageSize"
      @pagination="getList(queryForm)"
    />
    <checkpoint-form
      :title="title"
      :values="editValues"
      :visible.sync="editVisable"
      :checkPointTypeList="checkPointTypeList"
      @completed="successRequest"
    ></checkpoint-form>
  </div>
</template>
<script>
import Cookies from "js-cookie";
import { checkPointList, checkPointDel, exportTable } from "@/api/outwater";
import { GetRegionPower } from "@/api/composite/source";
import { mapActions } from "vuex";
import CheckpointForm from "./form";

export default {
  // name: "CheckPoint",
  components: { CheckpointForm },
  data() {
    return {
      loading: true,
      title: undefined,
      editVisable: false,
      editValues: {},
      //查询表单
      queryForm: {
        fullName: null,
        mn: null,
        checkPointName: null,
        checkPointType: null,
        constructionUnit: null,
        townCode: null,
        brandName: null
      },
      // 分页
      page: {
        pageNum: 1,
        pageSize: 30
      },
      total: 0,
      //表格数据
      tableData: [],
      selectRows: [],
      //检测井类型
      checkPointTypeList: [],
      townCodeOptions: []
    };
  },
  computed: {
    ids() {
      return this.selectRows.map((item) => item.cpElemId);
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
    GetRegionPower().then((res) => (this.townCodeOptions = res.data));
    this.getDicts("check_point_type").then((response) => {
      this.checkPointTypeList = response.data;
    });
  },
  methods: {
    ...mapActions("company", ["SaveSearchCompany"]),
    /** 获取列表 **/
    getList(v = {}) {
      checkPointList(v, this.urlParameter(this.page)).then((res) => {
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
        fullName: null,
        mn: null,
        checkPointName: null,
        checkPointType: null,
        constructionUnit: null,
        townCode: null,
        brandName: null
      };
      this.handleQuery();
    },
    //提交
    submitFormBtn() {
      this.$refs.formRef.submitForm();
    },
    //操作成功
    successRequest() {
      this.getList();
      this.editVisable = false;
      this.editValues = {};
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.$refs.oTable.clearSelection();
      this.editVisable = true;
      this.editValues = {};
      this.title = "新增检测井";
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.selectRows = selection;
    },
    /** 修改按钮操作 */
    handleUpdate() {
      this.editVisable = true;
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
        type: "warning"
      })
        .then(function() {
          return checkPointDel(postIds);
        })
        .then(() => {
          this.getList(this.queryForm);
          this.msgSuccess("删除成功");
        })
        .catch(function() {});
    },
    //点击行选中
    rowClick(row, column, event) {
      let refsElTable = this.$refs.oTable; // 获取表格对象
      refsElTable.toggleRowSelection(row); // 调用选中行方法
    },
    exportTable() {
      let _that = this;
      this.$confirm("是否导出数据?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(function() {
          return exportTable(_that.queryForm);
        })
        .then((response) => {
          this.download(response.msg);
        })
        .catch(function() {});
    }
  }
};
</script>
