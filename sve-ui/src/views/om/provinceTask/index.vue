<template>
  <div class="task-wk">
    <!-- 搜索表单 -->
    <el-form :model="queryForm" ref="queryForm" :inline="true">
      <el-form-item label="企业名称" prop="factoryName">
        <el-input
          v-model="queryForm.factoryName"
          placeholder="企业名称"
          clearable
          style="width:180px"
        >
        </el-input>
      </el-form-item>
      <el-form-item label="监控点" prop="checkpointName">
        <el-input
          v-model="queryForm.checkpointName"
          placeholder="监控点名称"
          clearable
          style="width:120px"
        ></el-input>
      </el-form-item>
      <el-form-item label="镇街" prop="omtownId">
        <el-select v-model="queryForm.omtownId" size="mini" placeholder="镇街" style="width:100px">
          <el-option :label="i.name" :value="i.id" v-for="i in townList" :key="i.id"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="监测时间" prop="monitorTime">
        <el-date-picker
          v-model="queryForm.monitorTime"
          :clearable="false"
          style="width:220px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>

      </el-form-item>
      <el-form-item label="任务状态" prop="taskStatus">
        <el-select v-model="queryForm.taskStatus" size="mini" placeholder="任务状态" style="width:110px">
          <!--          <el-option label="草稿" value="1"></el-option>-->
          <el-option label="分局待处理" value="2"></el-option>
          <el-option label="超时未处理" value="3"></el-option>
          <el-option label="市局待核实" value="4"></el-option>
          <el-option label="市局退回" value="5"></el-option>
          <el-option label="已完成" value="6"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="开始时间" prop="startDate">
        <el-date-picker
          type="date"
          placeholder="选择开始时间"
          v-model="queryForm.startDate"
          value-format="yyyy-MM-dd"
          style="width:130px"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="结束时间" prop="endDate">
        <el-date-picker
          type="date"
          placeholder="选择结束时间"
          v-model="queryForm.endDate"
          value-format="yyyy-MM-dd"
          style="width:130px"
        ></el-date-picker>
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
          @click="addTask"
        >新增任务
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-upload
          class="upload-demo"
          :on-change="handleChange"
          action="Fake Action"
          :data="{attachtype:attachtype}"
          :show-file-list="false"
          :auto-upload="false"
        >
          <el-button
            type="warning"
            icon="el-icon-upload2"
            size="mini"
            :loading="uploadLoading"
          >{{ uploadLoading ? "表格导入中,请稍后" : "导入任务" }}
          </el-button>
        </el-upload>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="delTask"
        >删除任务
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          :disabled="multiple"
        >
          <span v-if="multiple">批量下载</span>
          <a :href="allDown()" target="_blank" v-else>批量下载</a>
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="exportTable"
        >导出数据
        </el-button>
      </el-col>
    </el-row>
    <el-table
      :data="tableData"
      v-loading="loading"
      size="mini"
      class="taskTable"
      border
      height="100%"
      @selection-change="handleSelectionChange"
      @cell-dblclick="showDetails"
      style="width: 100%">
      <el-table-column
        align="center"
        type="selection"
        width="55">
      </el-table-column>
      <el-table-column
        label="督办类型"
        prop="taskType"
        align="center"
        fixed="left"
        show-overflow-tooltip
        width="130">
      </el-table-column>
      <el-table-column
        prop="townName"
        fixed="left"
        label="镇街"
        width="90">
      </el-table-column>
      <el-table-column
        prop="factoryName"
        fixed="left"
        label="企业名称"
        show-overflow-tooltip
        width="250">
      </el-table-column>
      <el-table-column
        prop="checkpointName"
        label="监控点名称"
        show-overflow-tooltip
        width="140">
      </el-table-column>
      <el-table-column
        prop="elementName"
        width="140"
        show-overflow-tooltip
        label="监控因子">
      </el-table-column>
      <el-table-column
        prop="overDays"
        label="超标天数">
      </el-table-column>
      <el-table-column
        prop="monitorTime"
        label="监测时间"
        width="100">
      </el-table-column>
      <el-table-column
        prop="amount"
        label="排放浓度">
      </el-table-column>
      <el-table-column
        prop="standardval"
        label="排放标准">
      </el-table-column>
      <el-table-column
        prop="overTimes"
        label="超标倍数">
      </el-table-column>
      <el-table-column
        prop="taskStatus"
        width="90"
        label="任务状态">
        <template slot-scope="scope">
          <!--          <el-link type="primary" v-if="scope.row.taskStatus===1" style="font-size:12px;" @click="editTask(scope.row)">-->
          <!--            草稿-->
          <!--          </el-link>-->
          <span>  {{ statusType[scope.row.taskStatus] }}</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="startDate"
        label="任务开始时间"
        width="100">
      </el-table-column>
      <el-table-column
        prop="endDate"
        label="任务结束时间"
        width="100">
      </el-table-column>
      <el-table-column
        label="超标是否属实"
        width="100">
        <template slot-scope="scope">
          {{ scope.row.istrue === 0 ? "否" : scope.row.istrue === 1 ? "是" : "-" }}
        </template>
      </el-table-column>
      <el-table-column
        prop="reason"
        label="超标原因"
        show-overflow-tooltip
        width="200">
      </el-table-column>
      <el-table-column
        prop="recityinfo"
        label="整改情况"
        show-overflow-tooltip
        width="200">
      </el-table-column>
      <el-table-column
        prop="situation"
        label="查处情况"
        show-overflow-tooltip
        width="200">
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <pagination
      v-if="total>0"
      :total="total"
      :page.sync="page.pageNum"
      :limit.sync="page.pageSize"
      @pagination="getList(searchData)"
    />
    <el-dialog
      :title="title"
      :visible.sync="taskvisible"
      width="800px">
      <n-form :townList="townList" ref="useForm" :values="values" @completed="completed" v-if="taskvisible"></n-form>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitText(2)">提 交</el-button>
        <el-button type="warning" @click="submitText(1)">暂 存</el-button>
        <el-button @click="taskvisible = false">取 消</el-button>
      </span>
    </el-dialog>
    <el-dialog
      title="导入表格数据"
      :visible.sync="excelvisible"
      class="excel-table"
      width="1200px">
      <el-table
        :data="excelData"
        size="mini"
        class="taskTable"
        border
        height="100%"
        style="width: 100%">
        <el-table-column
          label="督办类型"
          prop="taskType"
          align="center"
          show-overflow-tooltip
          width="90">
        </el-table-column>
        <el-table-column
          prop="townName"
          label="镇街"
          show-overflow-tooltip
          width="80">
        </el-table-column>
        <el-table-column
          prop="factoryName"
          show-overflow-tooltip
          label="企业名称"
          width="200">
        </el-table-column>
        <el-table-column
          prop="checkpointName"
          show-overflow-tooltip
          label="监控点名称"
          width="130">
        </el-table-column>
        <el-table-column
          prop="elementName"
          show-overflow-tooltip
          width="120"
          label="监控因子">
        </el-table-column>
        <el-table-column
          prop="overDays"
          label="超标天数"
          width="80">
        </el-table-column>
        <el-table-column
          prop="monitorTime"
          label="监测时间"
          width="100">
        </el-table-column>
        <el-table-column
          prop="amount"
          label="排放浓度"
          width="80">
        </el-table-column>
        <el-table-column
          prop="standardval"
          label="排放标准"
          width="80">
        </el-table-column>
        <el-table-column
          prop="overTimes"
          label="超标倍数"
          width="80">
        </el-table-column>
        <el-table-column
          prop="startDate"
          label="任务开始时间"
          width="100">
        </el-table-column>
        <el-table-column
          prop="endDate"
          label="任务结束时间"
          width="100">
        </el-table-column>
      </el-table>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="confirmSub">确认导入</el-button>
      </span>
    </el-dialog>
    <el-dialog
      title="查看详情"
      :visible.sync="seevisible"
      class="branchDialog"
      width="800px">
      <n-see :sData="clickData"></n-see>
    </el-dialog>
  </div>
</template>

<script>
import {dgomTowns} from "@/api/om";
import NForm from "./nForm";
import NSee from "./nSee";
import {
  dgomSuperviseList,
  dgomSuperviseDel,
  dgomSuperviseImport,
  dgomBatchAdd,
  dgomexportSupervise
} from "@/api/om/task";

export default {
  components: {NForm, NSee},
  data () {
    return {
      //查询条件
      queryForm: {
        factoryName: null,
        checkpointName: null,
        omtownId: null,
        monitorTime: null,
        startDate: null,
        endDate: null,
        taskStatus: null
      },
      searchData: {},
      clickData: {},
      attachtype: "",
      values: {},
      tableData: [],
      townList: [],
      selectRows: [],
      excelData: [],
      statusType: ["", "草稿", "分局待处理", "超时未处理", "市局待核实", "市局退回", "已完成"],
      taskvisible: false,
      excelvisible: false,
      uploadLoading: false,
      seevisible: false,
      title: "",
      // 分页
      page: {
        pageNum: 1,
        pageSize: 30
      },
      total: 0,
      fileIds: [],
      loading: false

    };
  },
  computed: {
    ids () {
      return this.selectRows.map((item) => item.suptaskId);
    },
    multiple () {
      return !this.selectRows.length;
    }
  },
  created () {
    //镇街
    dgomTowns().then(res => {
      if (res.code === 200) this.townList = res.data;
    });
    this.getList();
  },
  methods: {
    getList (v = {}) {
      this.loading = true;
      dgomSuperviseList(v, this.urlParameter(this.page)).then(res => {
        if (res.code === 200) {
          this.tableData = res.rows;
          this.total = res.total;
        }
        this.loading = false;
      });
    },
    handleQuery () {
      let oVal = {};
      let newForm = JSON.parse(JSON.stringify(this.queryForm));
      oVal = Object.assign(newForm, oVal);
      if (newForm.monitorTime) {
        oVal.beginTime = newForm.monitorTime[0];
        oVal.endTime = newForm.monitorTime[1];
        delete oVal.monitorTime;
      }
      this.searchData = oVal;
      this.getList(oVal);
    },
    resetQuery () {
      this.queryForm = {
        factoryName: null,
        checkpointName: null,
        omtownId: null,
        monitorTime: null,
        startDate: null,
        endDate: null,
        taskStatus: null
      };
      this.getList();
    },
    addTask () {
      this.values = {};
      this.title = "新增任务";
      this.taskvisible = true;
    },
    //修改
    editTask (v) {
      this.values = v;
      this.title = "修改任务";
      this.taskvisible = true;
    },
    submitText (v) {
      this.$refs.useForm.submit(v);
    },
    completed () {
      this.taskvisible = false;
      this.getList();
    },
    //选择
    handleSelectionChange (v) {
      this.selectRows = v;
    },
    delTask () {
      const postIds = this.ids.join(",");
      let _that = this;
      this.$confirm("是否确认删除选中数据数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(function () {
          return dgomSuperviseDel(postIds);
        })
        .then(() => {
          _that.getList(this.queryForm);
          _that.msgSuccess("删除成功");
        })
        .catch(function () {
        });
    },
    handleChange (file) {
      this.uploadLoading = true;
      var reader = new FileReader();
      reader.readAsDataURL(file.raw);
      reader.onload = (e) => {
        var imgcode = e.target.result;
        const data = {
          file: imgcode
        };
        dgomSuperviseImport(data).then(res => {
          if (res.code === 200) {
            this.excelData = res.data;
            if (this.excelData.length > 0) {
              this.excelvisible = true;
            }
            this.uploadLoading = false;
          }
        });
      };
    },
    confirmSub () {
      dgomBatchAdd(this.excelData).then(res => {
        if (res.code === 200) {
          this.msgSuccess("表格数据导入成功");
          this.getList();
          this.excelvisible = false;
        }
      });
    },
    showDetails (v) {
      this.clickData = v;
      this.seevisible = true;
    },
    allDown () {
      let ids = this.selectRows.map(x => {
        return x.suptaskId;
      });
      return `${process.env.VUE_APP_BASE_API}/com/ref_files?refType=dgom_branch_task&refId=${ids.join(",")}`;
    },
    exportTable () {
      let _that = this;
      this.$confirm("是否导出数据?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(function () {
          return dgomexportSupervise(_that.searchData);
        })
        .then((response) => {
          this.download(response.msg);
        })
        .catch(function () {
        });
    }
  }
};
</script>

<style lang="scss" scoped>
.taskTable {
  /deep/ .el-table__fixed-header-wrapper {
    tr {
      th {
        background-color: #f8f8f9 !important;

        &.is-leaf {
          border-bottom: none;
        }

        div {
          color: #515a6e;
        }
      }
    }
  }
}

.excel-table {
  /deep/ .el-dialog {
    height: calc(100% - 12vh);
    display: flex;
    flex-direction: column;
    overflow: hidden;

   .el-dialog__body {
      flex: 1;
      overflow: hidden;
    }
  }
}

.branchDialog {
  /deep/ .el-dialog {
    height: calc(100% - 12vh);
    overflow: hidden;
    display: flex;
    flex-direction: column;

    .el-dialog__body {
      padding: 5px 10px;
      flex: 1;
      overflow-y: auto;
    }
  }
}
</style>
