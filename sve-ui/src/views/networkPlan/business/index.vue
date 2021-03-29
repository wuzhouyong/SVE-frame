<template>
  <div class="task-wk">
    <!-- 搜索表单 -->
    <el-form :model="queryForm" ref="queryForm" :inline="true" class="outwater-serchForm">
      <el-form-item label="企业名称" prop="fullName">
        <el-input
          v-model="queryForm.fullName"
          placeholder="企业名称"
          clearable
          size="mini"
          style="width: 180px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="所属镇街" prop="townCode">
        <el-select v-model="queryForm.townCode" placeholder="镇街名称" clearable style="width: 150px">
          <el-option
            v-for="(item, index) in townCodeOptions"
            :key="index"
            :label="item.regionName"
            :value="item.regionCode"
            :disabled="item.disabled"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="企业运行状态" prop="factoryStatus">
        <el-select
          v-model="queryForm.factoryStatus"
          placeholder="企业运行状态"
          clearable
          style="width: 150px"
        >
          <el-option
            v-for="(item, index) in sourceStatusOptions"
            :key="index"
            :label="item.dictLabel"
            :value="item.dictValue"
            :disabled="item.disabled"
          ></el-option>
        </el-select>
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
          v-hasPermi="['networkplan:buiness:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['networkplan:buiness:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDel"
          v-hasPermi="['networkplan:buiness:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <!-- <el-button type="warning" icon="el-icon-upload2" size="mini" @click="handleExport">导出监控企业名录</el-button> -->
        <el-popover placement="bottom-start" width="160" trigger="click" @hide="oHide">
          <el-radio-group v-model="radio" @change="temChange">
            <el-radio :label="1">按省厅模板导出</el-radio>
            <el-radio :label="2" style="margin:10px 0;">按市局模板导出</el-radio>
          </el-radio-group>
          <el-select v-model="dateValue" placeholder="请选择" size="mini" @change="accessDateChange">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            ></el-option>
          </el-select>
          <el-button style="margin-top:10px;width:100%" @click="handleExport">确定导出</el-button>
          <el-button slot="reference" type="warning" icon="el-icon-upload2" size="mini">
            导出监控企业名录
            <i class="el-icon-caret-bottom"></i>
          </el-button>
        </el-popover>
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
    >
      <el-table-column type="selection" width="55" align="center"></el-table-column>
      <el-table-column prop="fullName" label="企业名称" show-overflow-tooltip></el-table-column>
      <el-table-column prop="townName" label="所属镇街" show-overflow-tooltip></el-table-column>
      <el-table-column prop="industryName" label="行业类型" show-overflow-tooltip></el-table-column>
      <el-table-column prop="socialCreditCode" label="统一社会信用代码" show-overflow-tooltip></el-table-column>
      <el-table-column prop="envContactName" label="环保联系人" show-overflow-tooltip></el-table-column>
      <el-table-column prop="envContactPhone" label="联系电话" show-overflow-tooltip></el-table-column>
      <!-- <el-table-column prop="rwjd" label="任务进度" show-overflow-tooltip></el-table-column>
      <el-table-column prop="sgdw" label="施工单位名称" show-overflow-tooltip></el-table-column>
      <el-table-column prop="lxr" label="施工单位联系人" show-overflow-tooltip></el-table-column>
      <el-table-column prop="dh" label="施工单位联系电话" show-overflow-tooltip></el-table-column>-->
    </el-table>
    <!-- 分页 -->
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="page.pageNum"
      :limit.sync="page.pageSize"
      @pagination="getList(queryForm)"
    />
    <!--弹窗-->
    <VerDialog
      :show.sync="show"
      :dialogDatas="dialogDatas"
      :formData="formData"
      :townCodeOptions="townCodeOptions"
      @updateShow="show = false"
      @sendForm="sendForm"
    ></VerDialog>
  </div>
</template>
<script>
import { getRegion, GetRegionPower } from "@/api/composite/source";
import {
  lwjhSourceAdd,
  lwjhSourceList,
  lwjhSourceDel,
  lwjhSourceEdit,
  exportTable,
  exportCityTable
} from "@/api/networkPlan";
import VerDialog from "./component/dialog";
export default {
  components: { VerDialog },
  data() {
    return {
      // 分页
      page: {
        pageNum: 1,
        pageSize: 30
      },
      total: 0,
      //按钮
      single: true,
      multiple: true,
      //查询表单
      queryForm: {
        fullName: undefined,
        townCode: undefined,
        sourceStatus: undefined
      },
      // 选中数组
      ids: [],
      //选中详情
      selectRows: [],
      //搜索
      townCodeOptions: [],
      sourceStatusOptions: [],
      //表格
      loading: false,
      tableData: [],
      //行业类型
      industryCode1Options: [],
      //弹出窗
      show: false,
      dialogDatas: {
        title: "新增", // 新增或编辑
        width: "60%" // dialog 宽度
      },
      formData: { polluteTypes: [], hasVideo: [] },
      //模板选择
      radio: 1,
      //导出时间
      dateValue: "",
      options: [
        {
          value: "2020-01-01",
          label: "2020年"
        },
        {
          value: "2019-01-01",
          label: "2019年"
        },
        {
          value: "2018-01-01",
          label: "2018年"
        }
      ]
    };
  },
  created() {
    this.getDicts("source_status").then(
      res => (this.sourceStatusOptions = res.data)
    );
    // getRegion(4419).then(res => (this.townCodeOptions = res.data));
    GetRegionPower().then(res => {
      this.townCodeOptions = res.data;
    });
    this.getList();
  },
  methods: {
    getList(v = {}) {
      this.loading = true;
      lwjhSourceList(v, this.urlParameter(this.page)).then(res => {
        if (res.code === 200) {
          this.tableData = res.rows;
          this.total = res.total;
          this.loading = false;
        }
      });
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
    //选择行
    handleSelectionChange(selection) {
      this.selectRows = selection;
      this.ids = selection.map(item => item.sourceId);
      this.single = selection.length != 1;
      this.multiple = !selection.length;
    },
    //新增
    handleAdd() {
      this.$refs.oTable.clearSelection();
      this.formData = { polluteTypes: [], hasVideo: ["1"] };
      this.dialogDatas.title = "新增监控任务";
      this.show = true;
    },
    //修改
    handleUpdate() {
      this.dialogDatas.title = "修改监控任务";
      let oBj = JSON.parse(JSON.stringify(this.selectRows[0]));
      if (oBj.hasVideo === "1") {
        oBj.hasVideo = [oBj.hasVideo];
      } else {
        oBj.hasVideo = ["0"];
      }
      if (!oBj.polluteTypes) oBj.polluteTypes = [];
      this.formData = oBj;
      this.show = true;
    },
    sendForm(v) {
      if (v.sourceId) {
        delete v.checkPoints;
        lwjhSourceEdit(v).then(res => {
          if (res.code === 200) {
            this.msgSuccess("企业修改成功");
            this.getList();
          } else {
            this.msgError(res.msg);
          }
        });
        this.show = false;
      } else {
        lwjhSourceAdd(v).then(res => {
          if (res.code === 200) {
            this.msgSuccess("企业新增成功");
            this.getList();
          } else {
            this.msgError(res.msg);
          }
        });
        this.show = false;
      }
    },
    //删除
    handleDel() {
      const postIds = this.ids;
      this.$confirm("是否确认删除选中数据数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(function() {
          return lwjhSourceDel(postIds);
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
    //导出
    handleExport() {
      const queryParams = this.queryForm;
      let _that = this;
      this.$confirm("是否确认数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(function() {
          if (_that.radio === 1) {
            return exportTable(queryParams);
          } else {
            return exportCityTable(queryParams);
          }
        })
        .then(response => {
          this.download(response.msg);
          _that.radio = 1;
        })
        .catch(function() {});
    },
    //导出时间选择
    accessDateChange(v) {
      this.queryForm.accessDate = v;
      this.dateValue = v;
    },
    //隐藏调用
    oHide() {
      this.dateValue = "";
    },
    temChange(v) {
      this.radio = v;
    }
  }
};
</script>