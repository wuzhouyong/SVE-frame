<template>
  <div class="task-wk">
    <!-- 搜索表单 -->
    <el-form :model="queryForm" ref="queryForm" :inline="true">
      <el-form-item label="企业名称" prop="sourceName">
        <el-input
          v-model="queryForm.sourceName"
          placeholder="请输入企业名称"
          clearable
          size="mini"
          style="width: 180px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="预警级别" prop="warnLevel">
        <el-select v-model="queryForm.warnLevel" size="mini" style="width:110px">
          <el-option label="白" value="白"></el-option>
          <el-option label="黄" value="黄"></el-option>
          <el-option label="红" value="红"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="企业类型" prop="keyPoint">
        <el-select v-model="queryForm.keyPoint" size="mini" style="width:150px">
          <el-option label="重点企业" value="重点企业"></el-option>
          <el-option label="非重点企业" value="非重点企业"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="镇街名称" prop="townCode">
        <el-select
          v-model="queryForm.townCode"
          placeholder="请选择镇街名称"
          clearable
          :style="{width: '160px'}"
        >
          <el-option
            v-for="(item, index) in townCodeOptions"
            :key="index"
            :label="item.regionName"
            :value="item.regionCode"
            :disabled="item.disabled"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="选择日期" prop="oTime">
        <el-date-picker
          v-model="queryForm.oTime"
          style="width:220px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-edit"
          size="mini"
          :disabled="!single"
          @click="handleEdit"
        >编辑
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
            icon="el-icon-download"
            size="mini"
            :loading="uploadLoading"
          >{{ uploadLoading ? "表格导入中,请稍后" : "导入表格" }}
          </el-button>
        </el-upload>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-upload2"
          size="mini"
          @click="exportTable"
        >导出数据
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          icon="el-icon-document"
          size="mini"
        >
          <el-link style="font-size:12px;    margin-top: -3px;" href="/static/巡检统计说明.zip" :underline="false"
                   target="_blank">导入说明下载
          </el-link>
        </el-button>
      </el-col>
    </el-row>
    <!-- 表格 -->
    <el-table
      v-loading="loading"
      :data="tableData"
      style="width: 100%;flex:1;"
      @selection-change="handleSelectionChange"
      ref="oTable"
      size="mini"
      height="100%"
      border
    >
      <el-table-column type="selection" width="55" align="center"></el-table-column>
      <el-table-column prop="townName" label="镇街" width="80px"></el-table-column>
      <el-table-column prop="sourceName" label="企业名称" show-overflow-tooltip width="260px"></el-table-column>
      <el-table-column prop="inspectDate" label="巡检日期" width="100px"></el-table-column>
      <el-table-column label="存在问题" min-width="220px" show-overflow-tooltip>
        <template slot-scope="scope">
          <pre>{{ scope.row.abnormalCause }}</pre>
        </template>
      </el-table-column>
      <el-table-column prop="warnLevel" label="预警级别白/黄/红" width="80px" align="center"></el-table-column>
      <el-table-column prop="keyPoint" label="重点/非重点排污单位" width="150px"></el-table-column>
      <el-table-column label="整改情况" width="260px" show-overflow-tooltip>
        <template slot-scope="scope">
          <pre>{{ scope.row.rectification }}</pre>
        </template>
      </el-table-column>
      <el-table-column prop="expectFinishDate" label="预计整改完成时间" width="130px"></el-table-column>
      <el-table-column label="操作" width="110px" align="center">
        <template slot-scope="scope">
          <el-link type="primary" style="font-size:12px;" :underline="false" @click="rectData(scope.row)">整改</el-link>
          <el-divider direction="vertical" v-if="!(scope.row.rectification&&scope.row.expectFinishDate)"></el-divider>
          <el-link type="danger" style="font-size:12px;" :underline="false"
                   v-if="!(scope.row.rectification&&scope.row.expectFinishDate)"
                   @click="handleDelete(scope.row.statId)">删除
          </el-link>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <pagination
      :total="total"
      :page.sync="page.pageNum"
      :limit.sync="page.pageSize"
      @pagination="getList(queryForm)"
    />
    <el-dialog
      title="导入表格数据"
      :visible.sync="excelVisible"
      class="excel-table"
      :close-on-click-modal="false"
      width="1200px">
      <el-table
        :data="excelData"
        size="mini"
        class="taskTable"
        border
        height="100%"
        style="width: 100%">
        <el-table-column
          type="index" label="序号" align="center" width="50px">
        </el-table-column>
        <el-table-column prop="townName" label="镇街" width="80px"></el-table-column>
        <el-table-column prop="sourceName" label="企业名称" show-overflow-tooltip width="260px"></el-table-column>
        <el-table-column prop="inspectDate" label="巡检日期" width="120px"></el-table-column>
        <el-table-column label="存在问题" min-width="220px" show-overflow-tooltip>
          <template slot-scope="scope">
            <pre>{{ scope.row.abnormalCause }}</pre>
          </template>
        </el-table-column>
        <el-table-column prop="warnLevel" label="预警级别白/黄/红" width="80px" align="center"></el-table-column>
        <el-table-column prop="keyPoint" label="重点/非重点排污单位" width="120px"></el-table-column>
      </el-table>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="confirmSub">确认导入</el-button>
      </span>
    </el-dialog>
    <el-dialog
      title="整改"
      :visible.sync="rectVisible"
      class="inspect-details"
      :close-on-click-modal="false"
      width="700px">
      <n-details :rectDatas="rectDatas" @complete="complete" v-if="rectVisible"></n-details>
    </el-dialog>
    <el-dialog :title="title"
               :visible.sync="formVisible"
               class="inspect-details"
               :close-on-click-modal="false"
               width="700px">
      <n-form ref="nFormRef" :values="values" @complete="completes"></n-form>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="$refs.nFormRef.sumitMes()">提 交</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import {inspectImport, inspectBatchAdd, inspectTaskList, inspectExport, inspectDelete} from "@/api/inspection";
import nDetails from "./component/nDetails";
import nForm from "./component/nForm";
import {getRegion} from "@/api/composite/source";

export default {
  components: {nDetails, nForm},
  data () {
    return {
      loading: false,
      uploadLoading: false,
      rectVisible: false,
      excelVisible: false,
      attachtype: "",
      tableData: [],
      excelData: [],
      townCodeOptions: [],
      queryForm: {
        sourceName: null,
        warnLevel: null,
        keyPoint: null,
        oTime: null,
        townCode: null
      },
      rectDatas: {},
      // 分页
      page: {
        pageNum: 1,
        pageSize: 30
      },
      total: 0,
      title: "",
      formVisible: false,
      values: {},
      selectRows: []
    };
  },
  computed: {
    single () {
      if (this.selectRows.length === 1 && !this.selectRows[0].rectification && !this.selectRows[0].expectFinishDate) {
        return true;
      } else {
        return false;
      }
    },
  },
  created () {
    this.getList();
    //镇街列表
    getRegion(4419).then((res) => (this.townCodeOptions = res.data));
  },
  methods: {
    getList (v = {}) {
      inspectTaskList(v, this.urlParameter(this.page)).then(res => {
        if (res.code === 200) {
          this.tableData = res.rows;
          this.total = res.total;
        }
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
        inspectImport(data).then(res => {
          if (res.code === 200) {
            this.excelData = res.data;
            if (this.excelData.length > 0) {
              this.excelVisible = true;
            }
          }
          this.uploadLoading = false;
        });
      };
    },
    confirmSub () {
      inspectBatchAdd(this.excelData).then(res => {
        this.msgSuccess("表格数据导入成功");
        this.getList();
        this.excelVisible = false;
      });
    },
    rectData (v) {
      this.rectDatas = v;
      this.rectVisible = true;
    },
    complete () {
      this.rectDatas = {};
      this.rectVisible = false;
      this.getList();
    },
    exportTable () {
      let _that = this;
      this.$confirm("是否导出数据?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(function () {
          return inspectExport(_that.queryForm);
        })
        .then((response) => {
          this.download(response.msg);
        })
        .catch(function () {
        });
    },
    handleDelete (v) {
      this.$confirm("是否确认删除选中数据数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(function () {
          return inspectDelete(v);
        })
        .then(() => {
          this.getList(this.queryForm);
          this.msgSuccess("删除成功");
        })
        .catch(function () {
        });
    },
    handleQuery () {
      this.page.pageNum = 1;
      if (this.queryForm.oTime) {
        this.queryForm.startDate = this.queryForm.oTime[0];
        this.queryForm.endDate = this.queryForm.oTime[1];
      }
      this.getList(this.queryForm);
    },
    resetQuery () {
      this.queryForm = {
        sourceName: null,
        warnLevel: null,
        keyPoint: null,
        oTime: null,
        townCode: null
      };
      this.handleQuery();
    },
    handleAdd () {
      this.title = "新增";
      this.values = {};
      this.formVisible = true;
    },
    handleEdit () {
      if (this.selectRows.length === 1) {
        this.values = this.selectRows[0];
        this.title = "编辑";
        this.formVisible = true;
      }
    },
    handleSelectionChange (selection) {
      this.selectRows = selection;
    },
    completes () {
      this.values = {};
      this.formVisible = false;
      this.handleQuery();
    }
  }
};
</script>

<style lang='scss' scoped>
.task-wk {
  pre {
    margin: 0 !important;
  }
}

.excel-table {
  /deep/ .el-dialog {
    height: calc(100% - 12vh);
    display: flex;
    flex-direction: column;
    overflow: hidden;

    /deep/ .el-dialog__body {
      flex: 1;
      overflow: hidden;
    }
  }
}
</style>
