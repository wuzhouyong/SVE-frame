<template>
  <div class="task-wk">
    <el-tabs v-model="activeName" @tab-click="handleClick">
      <el-tab-pane :label="i.name" :name="i.path" v-for="i in cities" :key="i.path"></el-tab-pane>
    </el-tabs>
    <!-- 搜索表单 -->
    <el-form :model="queryForm" ref="queryForm" :inline="true" class="outwater-serchForm">
      <el-form-item label="污染源" prop="sourceName">
        <el-input
          v-model="queryForm.sourceName"
          placeholder="请输入污染源"
          clearable
          size="mini"
          style="width: 180px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="选择日期" prop="startTime">
        <el-date-picker
          v-model="queryForm.startTime"
          style="width:240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="处理状态" prop="procStateStr">
        <el-select
          v-model="queryForm.procStateStr"
          clearable
          placeholder="请选择处理状态"
          style="width:140px"
        >
          <el-option
            :label="i.dictLabel"
            :value="i.dictValue"
            v-for="i in procStates"
            :key="i.dictValue"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="异常级别" prop="warnLevel">
        <el-select
          v-model="queryForm.warnLevel"
          clearable
          placeholder="请选择异常级别"
          style="width:140px"
        >
          <el-option
            :label="i.dictLabel"
            :value="i.dictValue"
            v-for="i in warnLevels"
            :key="i.dictValue"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="异常类型" prop="warnType">
        <el-select v-model="queryForm.warnType" clearable placeholder="请选择异常类型" style="width:100%">
          <el-option
            :label="i.dictLabel"
            :value="i.dictValue"
            v-for="i in warnTypes"
            :key="i.dictValue"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        <el-button
          icon="el-icon-edit-outline"
          type="warning"
          size="mini"
          @click="ondutyHandle"
          v-hasPermi="['warn:onduty:verify']"
          :disabled="!single"
        >值守核实</el-button>
      </el-form-item>
    </el-form>
    <!-- 操作按钮 -->
    <el-row :gutter="10" class="mb8"></el-row>
    <!-- 表格 -->
    <el-table
      v-loading="loading"
      :data="tableData"
      style="width: 100%;flex:1"
      @selection-change="handleSelectionChange"
      ref="oTable"
      height="100%"
      border
    >
      <el-table-column type="selection" width="55" align="center"></el-table-column>
      <el-table-column prop="sourceName" label="污染源" width="220"></el-table-column>
      <el-table-column prop="warnLevelName" label="异常级别" width="100"></el-table-column>
      <el-table-column prop="procStateName" label="处理状态" width="100"></el-table-column>
      <el-table-column prop="startDate" label="异常时间" width="100"></el-table-column>
      <el-table-column prop="warnTypeName" label="异常类型" width="160"></el-table-column>
      <el-table-column prop="warnDesc" label="异常描述"></el-table-column>
    </el-table>
    <!-- 分页 -->
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="page.pageNum"
      :limit.sync="page.pageSize"
      @pagination="handleQuery()"
    />
    <!-- 异常处理 -->
    <el-dialog
      title="值守核实"
      width="40%"
      :visible.sync="visible"
      custom-class="handleWarnDialog"
      v-if="visible"
    >
      <handleForm
        :selectRows="selectRows"
        ref="verForm"
        :ids="ids"
        :activeName="activeName"
        :upName="activeName"
        @suc="suc"
      ></handleForm>
      <span slot="footer" class="dialog-footer">
        <el-button @click="visible = false">取 消</el-button>
        <el-button type="primary" @click="$refs.verForm.submitForm()" v-preventClick>提交处理</el-button>
      </span>
    </el-dialog>
  </div>
</template>
<script>
import { warnDutyList } from "@/api/warn_handle";
import { platform_list, contrast } from "@/config";
import { getTagList } from "@/api/composite/source";
import handleForm from "./form";
export default {
  name: "Onduty",
  components: { handleForm },
  data() {
    return {
      // 查询参数
      page: {
        pageNum: 1,
        pageSize: 30,
      },
      dateRange: {
        startDate: null,
        endDate: null,
      },
      total: 0,
      //查询表单
      queryForm: {
        sourceName: undefined,
        startTime: undefined,
        procStateStr: undefined,
        warnLevel: undefined,
        warnType: undefined,
      },
      // 是否显示弹出层
      open: false,
      // 选中数组
      ids: [],
      //选中详情
      selectRows: [],
      sSelectRows: {},
      // 弹出层标题
      title: "",
      // 非多个禁用
      multiple: true,
      //表格数据
      loading: true,
      tableData: [],
      constructionStates: [],
      procStates: [
        { dictLabel: "逾期未处理", dictValue: "2" },
        { dictLabel: "值守待核实", dictValue: "3" },
        { dictLabel: "退回核实", dictValue: "4" },
      ],
      warnLevels: [],
      warnTypes: [],
      visible: false,
      cities: [],
      activeName: "",
    };
  },
  created() {
    this.getDicts("psh_construct_state").then((response) => {
      this.constructionStates = response.data;
    });
    this.getDicts("ty_warn_level").then((response) => {
      this.warnLevels = response.data;
    });

    this.getSourceTagList();
  },
  computed: {
    single() {
      return this.selectRows.length === 1;
    },
  },
  methods: {
    /** 获取列表 **/
    getList(v = {}) {
      warnDutyList(v, this.activeName, this.urlParameter(this.page)).then(
        (res) => {
          if (res.code === 200) {
            this.tableData = res.rows;
            this.total = res.total;
          }
          this.loading = false;
        }
      );
    },
    /** 搜索按钮操作 */
    handleQuery() {
      let { startTime } = this.queryForm;
      let query = Object.assign({}, this.queryForm, this.dateRange);
      if (startTime) {
        let oVal = {
          startDate: startTime[0],
          endDate: startTime[1],
        };
        query = Object.assign({}, query, oVal);
      }
      delete query.startTime;
      this.getList(query);
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.selectRows = selection;
      this.ids = selection.map((item) => item.warnId);
      // this.multiple = !selection.length;
    },
    //值守待核实
    ondutyHandle() {
      this.visible = true;
    },
    //平台数据
    getSourceTagList() {
      getTagList().then((res) => {
        if (res.code === 200) {
          let oArr = res.rows;
          this.cities = platform_list.filter((p) =>
            oArr.some((s) => s.tagHeadId === p.id)
          );
          this.activeName = this.cities[0].path;
          this.handleQuery();
          this.getDicts(contrast[this.activeName] + "_warn_type").then(
            (response) => {
              this.warnTypes = response.data;
            }
          );
        }
      });
    },
    handleClick(tab, event) {
      this.activeName = tab.name;
      this.resetForm("queryForm");
      this.queryForm = {
        sourceName: undefined,
        startTime: undefined,
        procStateStr: undefined,
        warnLevel: undefined,
        warnType: undefined,
      };
      this.tableData = [];
      this.getDicts(contrast[this.activeName] + "_warn_type").then(
        (response) => {
          this.warnTypes = response.data;
        }
      );
      this.handleQuery();
    },
    suc() {
      this.visible = false;
      this.msgSuccess("异常处理成功!");
      this.handleQuery();
    },
  },
};
</script>
