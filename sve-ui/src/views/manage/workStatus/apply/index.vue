<template>
  <div class="workstatus_wk">
    <!--    工况停运、故障申报-->
    <!-- 搜索表单 -->
    <el-form :model="queryForm" ref="queryForm" :inline="true">
      <el-form-item label="企业名称" prop="sourceName">
        <el-input
          v-model="queryForm.sourceName"
          placeholder="企业名称"
          clearable
          size="mini"
          style="width: 180px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="监控任务" prop="tagHeadId">
        <el-select
          v-model="queryForm.tagHeadId"
          @change="tagChange"
          placeholder="请选择监控任务"
          :style="{width: '100%'}"
          size="mini"
        >
          <el-option
            v-for="item in sysList"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="标记状态" prop="workStatus">
        <el-select
          v-model="queryForm.workStatus"
          :style="{width: '100%'}"
          size="mini"
        >
          <el-option v-for="i in wrokStatusDict" :key="i.dictValue" :label="i.dictLabel"
                     :value="i.dictValue"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-radio-group v-model="radioType" size="mini" @change="typeChange">
          <el-radio-button :label="0">工况停运、故障申报</el-radio-button>
          <el-radio-button :label="1">申报时间变更</el-radio-button>
        </el-radio-group>
      </el-col>
    </el-row>
    <!-- 表格 -->
    <el-table
      v-loading="loading"
      :data="tableData"
      style="width: 100%;flex:1;"
      ref="oTable"
      size="mini"
      height="100%"
      border
    >
      <el-table-column prop="sourceName" label="企业名称" width="260px" show-overflow-tooltip></el-table-column>
      <el-table-column prop="townName" label="镇街" width="90px"></el-table-column>
      <el-table-column prop="tagHeadName" label="监控任务" width="180px"></el-table-column>
      <el-table-column prop="collectName" label="排放口" width="180px" show-overflow-tooltip
                       v-if="isShow"></el-table-column>
      <el-table-column prop="workStatus" label="标记状态" width="90px" :formatter="statusFormat"></el-table-column>
      <el-table-column prop="startTime" label="开始时间" width="130px"></el-table-column>
      <el-table-column prop="startTime" label="开始时间(变更后)" width="130px" v-if="isType===1"></el-table-column>
      <el-table-column prop="endTime" label="结束时间" width="130px"></el-table-column>
      <el-table-column prop="endTime" label="结束时间(变更后)" width="130px" v-if="isType===1"></el-table-column>
      <el-table-column prop="explain" :label="isType===0?'申报说明':'变更说明'" show-overflow-tooltip></el-table-column>
      <el-table-column label="附件" width="80" align="center">
        <template slot-scope="scope">
          <el-dropdown trigger="click" @visible-change="fileClick($event,scope.row)" placement="bottom-start">
            <span class="el-dropdown-link" style="font-size:12px;cursor: pointer;color: #46a6ff;">
              查看<i class="el-icon-arrow-down el-icon--right"></i>
            </span>
            <el-dropdown-menu slot="dropdown" style="width:400px;padding:10px" v-loading="fileLoading"
                              v-if="fileList.length>0">
              <el-dropdown-item v-for="(item,index) in fileList" :key="item.Id">
                <el-link
                  type="primary"
                  :underline="false"
                  target="_blank"
                  :href="getlink(item.Id)"
                  style="font-size:12px;"
                >{{ index + 1 }}.{{ item.showName }}
                </el-link>
              </el-dropdown-item>
            </el-dropdown-menu>
            <el-dropdown-menu slot="dropdown" v-else>
              <p style="width:300px;font-size:12px;line-height:30px;color:#666;text-align: center;letter-spacing: 2px">
                无附件</p>
            </el-dropdown-menu>
          </el-dropdown>
        </template>
      </el-table-column>
      <el-table-column label="审批" align="center" width="120px" v-if="isType===0">
        <template slot-scope="scope">
          <el-link type="primary" style="font-size:12px;" :underline="false" @click="handleApply(3,scope.row.workId)">
            同意
          </el-link>
          <el-divider direction="vertical"></el-divider>
          <el-link type="danger" style="font-size:12px;" :underline="false" @click="handleApply(2,scope.row.workId)">
            不同意
          </el-link>
        </template>
      </el-table-column>
      <el-table-column label="审批" align="center" width="120px" v-else>
        <template slot-scope="scope">
          <el-link type="primary" style="font-size:12px;" :underline="false" @click="changeApply(3,scope.row.changeId)">
            同意
          </el-link>
          <el-divider direction="vertical"></el-divider>
          <el-link type="danger" style="font-size:12px;" :underline="false" @click="changeApply(2,scope.row.changeId)">
            不同意
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
      :title="title"
      :visible.sync="visible"
      :close-on-click-modal="false"
      width="500px">
      <el-form label-width="80px" :model="applyForm" ref="applyForm">
        <el-form-item label="审批说明" prop="auditDesc" :rules="[{ required: true, message: '请输入审批说明', trigger: 'blur' }]">
          <el-input v-model="applyForm.auditDesc" type="textarea" rows="4"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitApply" size="mini">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import {workStatusList, workStatusAudit, workStatusChangeList, workStatusAuditChangeTime} from "@/api/workstatus";
import {getTagList} from "@/api/composite/source";
import {workStatus} from "@/config";
import {fileList} from "@/api/upload";

export default {
  data () {
    return {
      sysList: [],
      loading: false,
      visible: false,
      applyForm: {},
      //查询表单
      queryForm: {
        auditStatus: 1,
        sourceName: null,
        tagHeadId: null,
        workStatus: null
      },
      radioType: 0,
      // 分页
      page: {
        pageNum: 1,
        pageSize: 30
      },
      total: 0,
      applyPage: {
        pageNum: 1,
        pageSize: 30
      },
      applyTotal: 0,
      tableData: [],
      title: null,
      applyData: {},
      isType: 0,
      wrokStatusDict: [],
      isShow: false,
      fileList: [],
      fileLoading: false
    };
  },
  watch: {
    isType: {
      handler (v) {
        if (this.isType === 0) {
          this.getList(this.queryForm);
        } else {
          this.getApplyList(this.queryForm);
        }
      }
    }
  },
  created () {
    getTagList().then((res) => {
      if (res.code === 200) {
        let oArr = res.rows;
        this.sysList = workStatus.filter((p) =>
          oArr.some((s) => s.tagHeadId === p.id)
        );
        if (oArr.some(j => new Set(["1017", "1021", "1022", "1023"]).has(j.tagHeadId))) {
          this.sysList.push({
            id: "1001",
            name: "重点污染源在线监控"
          });
        }
        this.$nextTick(() => {
          if (this.sysList.some(i => i.id === "1001")) {
            this.isShow = true;
            this.queryForm.tagHeadId = "1001";
          } else {
            this.isShow = false;
            this.queryForm.tagHeadId = this.sysList[0].id;
          }
          this.getList(this.queryForm);
        });
      }
    });
    this.getDicts("work_status").then((res) => {
      this.wrokStatusDict = res.data;
    });
  },
  methods: {
    getList (v = {}) {
      this.loading = true;
      workStatusList(v, this.urlParameter(this.page)).then(res => {
        this.tableData = res.rows;
        this.total = res.total;
        this.loading = false;
      });
    },
    getApplyList (v = {}) {
      this.loading = true;
      workStatusChangeList(v, this.urlParameter(this.applyPage)).then(res => {
        this.tableData = res.rows;
        this.applyTotal = res.total;
        this.loading = false;
      });
    },
    handleQuery () {
      if (this.queryForm.tagHeadId === "1001") {
        this.isShow = true;
      } else {
        this.isShow = false;
      }
      if (this.isType === 0) {
        this.getList(this.queryForm);
      } else {
        this.getApplyList(this.queryForm);
      }
    },
    resetQuery () {
      this.page.pageNum = 1;
      this.applyPage.pageNum = 1;
      this.queryForm = {
        auditStatus: 1,
        sourceName: null,
        tagHeadId: null,
        workStatus: null
      };
      if (this.sysList.some(i => i.id === "1001")) {
        this.queryForm.tagHeadId = "1001";
      } else {
        this.queryForm.tagHeadId = this.sysList[0].id;
      }
      this.handleQuery();
    },
    handleApply (i, workId) {
      if (i === 3) {
        this.title = "审批申报(同意)";
      } else {
        this.title = "审批申报(不同意)";
      }
      this.applyData = {
        workId: workId,
        auditStatus: i,
      };
      this.visible = true;
    },
    submitApply () {
      this.$refs["applyForm"].validate((valid) => {
        if (!valid) return;
        let oV = Object.assign(this.applyForm, this.applyData);
        workStatusAudit(oV).then(res => {
          if (res.code === 200) {
            this.visible = false;
            this.msgSuccess(res.data);
            this.getList(this.queryForm);
          }
        });
      });
    },
    typeChange (v) {
      this.tableData = [];
      this.page.pageNum = 1;
      this.applyPage.pageNum = 1;
      this.isType = v;
    },
    changeApply (i, changeId) {
      this.$confirm("是否确定此操作?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          let oV = {
            changeId: changeId,
            auditStatus: i
          };
          workStatusAuditChangeTime(oV).then(res => {
            if (res.code === 200) {
              this.msgSuccess(res.data);
              this.getApplyList(this.queryForm);
            }
          });
        })
        .catch(function () {
        });
    },
    statusFormat (v) {
      return this.selectDictLabel(this.wrokStatusDict, v.workStatus);
    },
    tagChange () {
      this.handleQuery();
    },
    fileClick (i, v) {
      if (i) {
        this.fileLoading = true;
        let ref = {
          refId: v.workId,
          refType: "work_status_apply"
        };
        fileList(ref).then((res) => {
          if (res.code === 200) {
            this.fileList = res.rows || res.data;
            this.fileLoading = false;
          }
        });
      } else {
        this.fileList = [];
        this.fileLoading = false;
      }
    },
    getlink (id) {
      return `${process.env.VUE_APP_BASE_API}/com/getfile/${id}`;
    }
  }
};
</script>

<style lang='scss' scoped>
.workstatus_wk {
  width: 100%;
  height: 100%;
  overflow: hidden;
  display: flex;
  flex-direction: column;
  padding: 10px;
}
</style>
