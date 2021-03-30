<template>
  <div class="base-list">
    <!-- 搜索表单 -->
    <el-form :model="queryForm" ref="queryForm" :inline="true">
      <el-form-item label="申请状态" prop="status">
        <el-select v-model="queryForm.status" placeholder="申请状态" style="width:130px" size="mini">
          <el-option label="草稿" value="1"></el-option>
          <el-option label="待批复" value="2"></el-option>
          <el-option label="同意" value="3"></el-option>
          <el-option label="退回" value="4"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="日期范围" prop="date">
        <el-date-picker
          v-model="queryForm.date"
          size="mini"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          :clearable="false">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
<!--        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>-->
      </el-form-item>
    </el-form>
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
        >新增备案
        </el-button>
      </el-col>
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="success"-->
<!--          icon="el-icon-edit"-->
<!--          size="mini"-->
<!--          :disabled="selectRow.status !== 1"-->
<!--          @click="handleEdit"-->
<!--        >编辑备案-->
<!--        </el-button>-->
<!--      </el-col>-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="danger"-->
<!--          icon="el-icon-delete"-->
<!--          size="mini"-->
<!--          @click="handleDelete"-->
<!--          :disabled="selectRow.status !== 1"-->
<!--        >删除备案-->
<!--        </el-button>-->
<!--      </el-col>-->
    </el-row>
    <el-table
      v-loading="loading"
      :data="tableData"
      style="width: 100%;flex:1;"
      @current-change="handleSelectionChange"
      highlight-current-row
      ref="oTable"
      size="mini"
      height="100%"
      border
    >
      <el-table-column label="申请状态" width="120">
        <template slot-scope="scope">
          {{ statusType[scope.row.status] }}
        </template>
      </el-table-column>
      <el-table-column prop="acceptDate" label="验收时间" width="120"></el-table-column>
      <el-table-column prop="chargeMan" label="验收负责人" width="120"></el-table-column>
      <el-table-column prop="chargeManTitle" label="负责人职务" width="120"></el-table-column>
      <el-table-column prop="chargeManTel" label="负责人电话" width="180"></el-table-column>
      <el-table-column prop="memo" label="备注" show-overflow-tooltip></el-table-column>
      <el-table-column prop="approvedBy" label="确认人" width="120"></el-table-column>
      <el-table-column prop="approvedDate" label="确认时间" width="120"></el-table-column>
      <el-table-column prop="approvalDesc" label="确认意见" show-overflow-tooltip></el-table-column>
      <el-table-column label="附件" width="80px" align="center">
        <template slot-scope="scope">
          <el-dropdown trigger="hover" @visible-change="fileClick($event,scope.row)" placement="bottom-start">
            <span class="el-dropdown-link" style="font-size:12px;color:#1682e6">
              查看<i class="el-icon-arrow-down el-icon--right"></i>
            </span>
            <el-dropdown-menu slot="dropdown" style="width:400px;padding:10px" v-loading="fileLoading">
              <el-dropdown-item v-for="(item,index) in fileList" :key="item.Id">
                <el-link
                  type="primary"
                  :underline="false"
                  target="_blank"
                  :href="getlink(item.attach_id)"
                  style="font-size:12px;"
                >{{ index + 1 }}.{{ item.file_name }}
                </el-link>
              </el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="180">
        <template slot-scope="scope">
          <el-link type="primary" :underline="false" style="font-size:12px;" @click="deviceRecord(scope.row)">
            备案设备
          </el-link>
          <el-divider direction="vertical" v-if="scope.row.status===1&&scope.row.details.length>0"></el-divider>
          <el-link type="warning" :underline="false" style="font-size:12px;"
                   v-if="scope.row.status===1&&scope.row.details.length>0" @click="putAccept(scope.row)">
            提交备案
          </el-link>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog
      :title="title"
      custom-class="dgom-wran-dialog"
      :visible.sync="masterVisible"
      width="700px">
      <n-form :selectRow="values" @completed="completed" ref="nHandle" v-if="masterVisible"></n-form>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" size="mini" @click="$refs.nHandle.submitHandle()">提 交</el-button>
        <el-button size="mini" @click="masterVisible=false">取 消</el-button>
      </span>
    </el-dialog>
    <el-dialog
      title="备案设备"
      @close="onClose"
      custom-class="dgom-wran-dialog"
      :visible.sync="deviceVisible"
      width="900px">
      <n-device :headDataId="headDataId" :headStatus="headStatus"></n-device>
    </el-dialog>
  </div>
</template>

<script>
import {dgomEntAcceptList, dgomEntAcceptDeleteHead, dgomAcceptSubmit} from "@/api/dgom/accept";
import nForm from "./form";
import nDevice from "./device";
import {dgomAttachList} from "@/api/dgom/upload";

export default {
  components: {nForm, nDevice},
  data () {
    return {
      queryForm: {
        date: null,
        status: null
      },
      loading: false,
      tableData: [],
      selectRow: {},
      statusType: ["", "草稿", "待批复", "同意", "退回"],
      masterVisible: false,
      deviceVisible: false,
      title: null,
      headDataId: "",
      headStatus: "",
      fileLoading: false,
      fileList: [],
    };
  },
  created () {
    this.getList();
  },
  methods: {
    getList (v = {}) {
      dgomEntAcceptList(v).then(res => {
        this.tableData = res.rows;
      });
    },
    // 多选框选中数据
    handleSelectionChange (selection) {
      this.selectRow = selection;
    },
    handleAdd () {
      this.title = "新增验收备案";
      this.masterVisible = true;
      this.values = {};
    },
    handleEdit () {
      this.title = "编辑验收备案";
      this.masterVisible = true;
      this.values = this.selectRow;
    },
    handleQuery () {
      let v = {};
      if (this.queryForm.date && this.queryForm.date.length > 0) {
        v.startDate = this.queryForm.date[0];
        v.endDate = this.queryForm.date[1];
      }
      if (this.queryForm.status) {
        v.status = this.queryForm.status;
      }
      this.getList(v);
    },
    // resetQuery () {
    // },
    completed () {
      this.masterVisible = false;
      this.handleQuery();
    },
    //备案设备
    deviceRecord (v) {
      this.headDataId = v.applyId;
      this.headStatus = v.status;
      this.deviceVisible = true;
    },
    onClose () {
      this.handleQuery();
    },
    handleDelete () {
      this.$confirm("是否确认删除选中数据数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          return dgomEntAcceptDeleteHead(this.selectRow.applyId);
        })
        .then(() => {
          this.handleQuery();
          this.msgSuccess("删除成功");
        })
        .catch(() => {
        });
    },
    putAccept (v) {
      if (v.details.length > 0) {
        dgomAcceptSubmit(v.applyId).then(res => {
          if (res.code === 200) {
            this.handleQuery();
            this.msgSuccess("提交成功");
          }
        });
      } else {
        this.msgSuccess("请先添加备案设备");
      }

    },
    fileClick (i, v) {
      if (i) {
        this.fileLoading = true;
        let ref = {
          refType: "Ent_AcceptApplyHead"
        };
        dgomAttachList(v.applyId, ref).then((res) => {
          this.fileList = res.data;
          this.fileLoading = false;
        });
      } else {
        this.fileList = [];
        this.fileLoading = false;
      }
    },
    getlink (id) {
      return `${process.env.VUE_APP_BASE_API}/dgom/attach/download/${id}`;
    },
  }
};
</script>

<style lang='scss' scoped>

</style>
