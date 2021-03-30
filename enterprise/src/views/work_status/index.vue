<template>
  <div class="workstatus_wk">
    <!--    工况停运故障申报-->
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
          @click="handleEdit"
          :disabled="!single"
        >编辑
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="!single"
          @click="handleDelete"
        >删除
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
      height="100%"
      border
      size="mini"
    >
      <el-table-column type="selection" width="55" align="center"></el-table-column>
      <el-table-column prop="tagHeadName" label="监控任务" width="250px" show-overflow-tooltip></el-table-column>
      <el-table-column prop="workStatus" label="标记状态" width="90px" :formatter="statusFormat"></el-table-column>
      <el-table-column prop="startTime" label="开始时间" width="130px"></el-table-column>
      <el-table-column prop="endTime" label="结束时间" width="130px"></el-table-column>
      <el-table-column prop="explain" label="申报说明" show-overflow-tooltip min-width="250px"></el-table-column>
      <el-table-column prop="auditStatus" label="审核状态" width="90px">
        <template slot-scope="scope">
          {{ auditStatusType[scope.row.auditStatus] }}
        </template>
      </el-table-column>
      <el-table-column prop="explain" label="审批说明" show-overflow-tooltip min-width="150px"></el-table-column>
      <el-table-column label="操作" width="190px" align="center">
        <template slot-scope="scope">
          <el-link type="primary" :disabled="isApply(scope.row)" :underline="false" style="font-size:12px;"
                   @click="changeTime(scope.row)">
            变更申请时间
          </el-link>
          <el-divider direction="vertical"></el-divider>
          <el-popover
            placement="top-start"
            title="申请历史"
            width="500"
            trigger="click"
            :disabled="scope.row.changes.length<1">
            <div class="apply-history">
              <el-form v-for="(i,index) in scope.row.changes" :key="index">
                <el-form-item label="更改时间">
                  {{ i.startTime }} ~ {{ i.endTime }}
                </el-form-item>
                <el-form-item label="审批结果">
                  {{ auditStatusType[i.auditStatus] }}
                </el-form-item>
                <el-divider v-if="!(index+1=== scope.row.changes.length)"></el-divider>
              </el-form>
            </div>
            <el-link slot="reference" type="primary" style="font-size:12px;" :disabled="scope.row.changes.length<1"
                     :underline="false">
              查看申请历史
            </el-link>
          </el-popover>
        </template>
      </el-table-column>
    </el-table>
    <n-form :visible.sync="visible" v-if="visible" :title="title" :values="values" @completed="completed"></n-form>
    <el-dialog
      :title="title"
      :visible.sync="changeTimeVisible"
      :close-on-click-modal="false"
      width="500px">
      <el-form label-width="80px" :model="changeForm" ref="changeForm" v-if="changeTimeVisible">
        <el-form-item label="变更时间" prop="oTime" :rules="[{ required: true, message: '请选择时间区间', trigger: 'blur' }]">
          <el-date-picker
            size="mini"
            style="width:100%"
            v-model="changeForm.oTime"
            value-format="yyyy-MM-dd HH"
            format="yyyy-MM-dd HH"
            type="datetimerange"
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="变更说明" prop="explain" :rules="[{ required: true, message: '请输入变更说明', trigger: 'blur' }]">
          <el-input v-model="changeForm.explain" type="textarea" rows="4"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitChange" size="mini">申 请</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import nForm from "./form";
import {workStatusList, workStatusDel, workStatusApplyChangeTime} from "@/api/workstatus";

export default {
  components: {nForm},
  data () {
    return {
      changeTimeVisible: false,
      selectRows: [],
      tableData: [],
      loading: false,
      title: "",
      values: {},
      visible: false,
      changeForm: {},
      workId: null,
      auditStatusType: ["", "待审核", "不同意", "同意"],
      wrokStatusDict: []
    };
  },
  computed: {
    single () {
      return this.selectRows.length === 1 && this.selectRows[0].auditStatus === 1;
    },
    ids () {
      return this.selectRows.map((item) => item.workId);
    }
  },
  created () {
    this.getList();
    this.getDicts("work_status").then((res) => {
      this.wrokStatusDict = res.data;
    });
  },
  methods: {
    getList (v = {}) {
      workStatusList(v).then(res => {
        this.tableData = res.rows;
      });
    },
    // 多选框选中数据
    handleSelectionChange (selection) {
      this.selectRows = selection;
    },
    handleAdd () {
      this.title = "新增申报";
      this.values = {};
      this.visible = true;
    },
    handleEdit () {
      this.title = "编辑申报";
      this.values = this.selectRows[0];
      this.visible = true;
    },
    handleDelete () {
      this.$confirm("是否确认删除选中数据数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          return workStatusDel(this.ids);
        })
        .then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
        .catch((e) => {
        });
    },
    completed () {
      this.visible = false;
      this.values = {};
      this.getList();
    },
    changeTime (v) {
      this.workId = v.workId;
      this.changeTimeVisible = true;
    },
    submitChange () {
      this.$refs["changeForm"].validate((valid) => {
        if (!valid) return;
        let oV = {
          workId: this.workId,
          startTime: this.changeForm.oTime[0],
          endTime: this.changeForm.oTime[1],
          explain: this.changeForm.explain,
          auditStatus: 1,
        };
        workStatusApplyChangeTime(oV).then(res => {
          if (res.code === 200) {
            this.msgSuccess("变更申请时间提交成功!");
            this.getList();
            this.changeTimeVisible = false;
          }
        });
      });
    },
    isApply (v) {
      if (v.auditStatus === 3) {
        if (v.changes.length === 0) {
          return false;
        } else {
          return v.changes.some(i => i.auditStatus <= 1);
        }
      } else {
        return true;
      }
    },
    statusFormat (v) {
      return this.selectDictLabel(this.wrokStatusDict, v.workStatus);
    }
  }
};
</script>

<style lang="scss" scoped>
.apply-history {
  /deep/ .el-form-item {
    margin-bottom: 0px;
  }
}

.workstatus_wk {
  width: 100%;
  height: 100%;
  padding: 10px;
  display: flex;
  flex-direction: column;
  overflow: hidden;
}
</style>
