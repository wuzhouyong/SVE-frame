<template>
  <div class="task-wk">
    <!-- 搜索表单 -->
    <el-form :model="queryForm" ref="queryForm" :inline="true" class="outwater-serchForm">
      <el-form-item label="处置企业" prop="sourceId">
        <el-select v-model="queryForm.sourceId" placeholder="请选择处置企业" style="width:250px">
          <el-option
            :label="i.fullName"
            :value="i.sourceId"
            v-for="i in sourceList"
            :key="i.sourceId"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="车牌号" prop="carTag">
        <el-input
          v-model="queryForm.carTag"
          placeholder="请输入车牌号"
          clearable
          size="mini"
          style="width: 140px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否完成信息化改造" prop="isSetup">
        <el-radio-group v-model="queryForm.isSetup" size="mini">
          <el-radio-button label="0">否</el-radio-button>
          <el-radio-button label="1">是</el-radio-button>
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
          v-hasPermi="['lingxing:vehicle:add']"
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
          v-hasPermi="['lingxing:vehicle:edit']"
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
          v-hasPermi="['lingxing:vehicle:del']"
        >删除
        </el-button>
      </el-col>
      <!-- <el-col :span="1.5">
        <el-button type="warning" icon="el-icon-upload2" size="mini" @click="exportTable">导出表格</el-button>
      </el-col>-->
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
      size="mini"
    >
      <el-table-column type="selection" width="55" align="center"></el-table-column>
      <el-table-column prop="fullName" label="处置企业" show-overflow-tooltip min-width="200px"></el-table-column>
      <el-table-column prop="carTag" label="车牌号" width="120px;"></el-table-column>
      <el-table-column prop="mn" label="MN码" width="140px;"></el-table-column>
      <el-table-column prop="carType" label="车型" width="100px;"></el-table-column>
      <el-table-column prop="transcert" label="道路运输证" width="150px;"></el-table-column>
      <el-table-column prop="maxLoadamt" label="载重(吨)" width="100px;"></el-table-column>
      <el-table-column prop="fullVolume" label="容积" width="100px;"></el-table-column>
      <el-table-column prop="carSize" label="车辆尺寸(长*宽*高)" width="150px;" show-overflow-tooltip></el-table-column>
      <el-table-column prop="dtuIP" label="DTU_IP" width="110px;"></el-table-column>
      <el-table-column prop="isSetup" label="是否完成信息化改造" width="150px;">
        <template slot-scope="scope">{{ scope.row.isSetup === 1 ? "是" : "否" }}</template>
      </el-table-column>
      <el-table-column prop="memo" label="备注" width="120px;" show-overflow-tooltip></el-table-column>
      <el-table-column label="操作" align="center" width="90px;">
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="tankAdmin(scope.row.carId)">水箱管理</el-button>
        </template>
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
    <!-- 弹窗 -->
    <formTem
      :title="title"
      :values="values"
      :visible.sync="visible"
      :sourceList="sourceList"
      @completed="successRequest"
    ></formTem>
    <!-- 水箱 -->

    <el-dialog
      title="储水罐信息"
      :visible.sync="tankVisible"
      width="850px"
      :close-on-click-modal="false"
      custom-class="tank-dialog-wk"
    >
      <tankTem :carId="carId"></tankTem>
    </el-dialog>
  </div>
</template>
<script>
import formTem from "./form";
import tankTem from "./tank";
import { tankcarEnterprise, tankcarList, tankcarDel } from "@/api/lingxing";

export default {
  components: { formTem, tankTem },
  data() {
    return {
      queryForm: {},
      checkPointTypeList: [],
      selectRows: [],
      loading: false,
      tableData: [],
      sourceList: [],
      total: 0,
      page: {
        pageNum: 1,
        pageSize: 30
      },
      title: "",
      values: {},
      visible: false,
      tankVisible: false,
      carId: null
    };
  },
  created() {
    this.getSourceList();
    this.getList();
  },
  computed: {
    ids() {
      return this.selectRows.map((item) => item.carId);
    },
    single() {
      return this.selectRows.length === 1;
    },
    multiple() {
      return !this.selectRows.length;
    }
  },
  methods: {
    getSourceList() {
      tankcarEnterprise().then((res) => {
        if (res.code === 200) {
          this.sourceList = res.data || res.rows;
        }
      });
    },
    getList(v = {}) {
      this.loading = true;
      tankcarList(v, this.urlParameter(this.page)).then((res) => {
        if (res.code === 200) {
          this.tableData = res.data || res.rows;
          this.total = res.total;
        }
        this.loading = false;
      });
    },
    handleQuery() {
      this.page.pageNum = 1;
      this.getList(this.queryForm);
    },
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    handleAdd() {
      this.title = "新增车辆信息";
      this.values = {};
      this.visible = true;
    },
    handleUpdate() {
      this.title = "编辑车辆信息";
      this.values = this.selectRows[0];
      this.visible = true;
    },
    handleDelete() {
      const postIds = this.ids.join(",");
      this.$confirm("是否确认删除选中数据数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(function() {
          return tankcarDel(postIds);
        })
        .then(() => {
          this.getList(this.queryForm);
          this.msgSuccess("删除成功");
        })
        .catch(function() {});
    },
    exportTable() {},
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.selectRows = selection;
    },
    successRequest() {
      this.getList();
      this.visible = false;
      this.values = {};
    },
    tankAdmin(v) {
      this.carId = v;
      this.tankVisible = true;
    }
  }
};
</script>
