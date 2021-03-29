<template>
  <div class="composite-style">
    <div class="composite-left">
      <el-divider content-position="left">企业监控标签</el-divider>
      <el-card
        class="box-card"
        :class="[tagHeadIds.includes(i.tagHeadId)?'check-tag-style':'']"
        shadow="hover"
        v-for="i in systemList"
        @click="changeTagHead(i.tagHeadId)"
        :key="i.tagHeadId"
      >
        <EditTagName :i="i" @changeTagHead="changeTagHead" @editSuccess="editSuccess"></EditTagName>
      </el-card>
    </div>
    <div class="composite-right">
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
        <el-form-item label="经营状态" prop="sourceStatus">
          <el-select
            v-model="queryForm.sourceStatus"
            placeholder="请选择经营状态"
            clearable
            :style="{width: '160px'}"
          >
            <el-option
              v-for="(item, index) in sourceStatusOptions"
              :key="index"
              :label="item.dictLabel"
              :value="item.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="行业名称" prop="industryCode">
          <Treeselect
            v-model="queryForm.industryCode"
            :options="industryCodeOptions"
            placeholder="请选择行业名称"
            :style="{width: '220px'}"
            size="mini"
            class="com-industry"
          />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" @click="handleQuery" size="mini">搜索</el-button>
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
            v-hasPermi="['source:admin:add']"
          >新增企业
          </el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="primary"
            icon="el-icon-edit"
            size="mini"
            @click="handleEdit"
            :disabled="single"
            v-hasPermi="['source:admin:edit']"
          >编辑企业
          </el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="primary"
            size="mini"
            icon="el-icon-c-scale-to-original"
            :disabled="single"
            @click="relation"
            v-hasPermi="['source:admin:task']"
          >任务设置
          </el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="primary"
            size="mini"
            icon="el-icon-c-scale-to-original"
            :disabled="single"
            @click="branch"
            v-hasPermi="['source:admin:branch']"
          >任务设置
          </el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="warning"
            plain
            icon="el-icon-connection"
            size="mini"
            v-if="selectRows[0]?(selectRows[0].userName?false:true):false"
            v-preventClick
            @click="addUser"
            @dbClick="()=>false"
            :disabled="single"
            v-hasPermi="['enterprise:user:add']"
          >生成账号
          </el-button>
          <el-button
            type="warning"
            plain
            size="mini"
            icon="el-icon-lock"
            @click="resetpwd"
            v-else
            :disabled="single"
            v-hasPermi="['enterprise:user:edit']"
          >重置密码
          </el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="danger"
            icon="el-icon-delete"
            plain
            size="mini"
            @click="handleDel"
            :disabled="single"
            v-hasPermi="['source:admin:del']"
          >删除企业
          </el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="warning"
            icon="el-icon-upload"
            size="mini"
            :disabled="single"
            @click="fileAdmin"
          >附件管理
          </el-button>
        </el-col>
      </el-row>
      <!--表格-->
      <el-table
        :data="tableData"
        v-loading="loading"
        style="width: 100%"
        @selection-change="handleSelectionChange"
        height="100%"
        border
      >
        <el-table-column type="selection" width="55" align="center"></el-table-column>
        <el-table-column label="企业名称" min-width="260">
          <template slot-scope="scope">
            <el-link
              type="primary"
              @click="seeCom(scope.row)"
              :underline="false"
              style="font-size:12px"
            >{{ scope.row.sourceName }}
            </el-link>
          </template>
        </el-table-column>
        <el-table-column prop="userName" label="企业账号" width="126"></el-table-column>
        <el-table-column prop="townName" label="所属镇街" width="100" show-overflow-tooltip></el-table-column>
        <el-table-column prop="villageName" label="所属社区" width="140" show-overflow-tooltip></el-table-column>
        <el-table-column prop="industryName" label="所属行业" width="200" show-overflow-tooltip></el-table-column>
        <el-table-column prop="statusName" label="经营状态" width="90"></el-table-column>
        <el-table-column prop="sourceScale" label="企业规模" width="90" :formatter="cScaleType"></el-table-column>
        <el-table-column prop="envContactName" label="环保联系人" width="120"></el-table-column>
        <el-table-column prop="envContactPhone" label="联系电话" width="130"></el-table-column>
      </el-table>
      <!-- 分页 -->
      <pagination
        v-show="total>0"
        :total="total"
        :page.sync="page.pageNum"
        :limit.sync="page.pageSize"
        @pagination="getList(queryForm)"
      />
    </div>
    <el-dialog
      title="企业基础资料"
      :visible.sync="visible"
      width="80%"
      custom-class="company-dialog"
      :close-on-click-modal="false"
      :destroy-on-close="true"
      center
      v-if="visible"
    >
      <FormChunk :sData="editSource" @successRequest="successRequest"></FormChunk>
    </el-dialog>

    <el-dialog
      title="任务设置"
      :visible.sync="relationVisible"
      width="30%"
      :destroy-on-close="true"
      @close="tagClose"
      v-if="relationVisible"
    >
      <!-- <el-checkbox-group v-model="checkboxGroup" class="checkbox-style" v-loading="tagLoading">
        <el-checkbox v-for="i in systemList" :label="i.id" :key="i.id" border>{{i.name}}</el-checkbox>
      </el-checkbox-group>-->
      <Relation
        :systemList="systemList"
        :relationSourceId="relationSourceId"
        :checkboxGroup="checkboxGroup"
        :oSource="relationSourceId"
        :fullName="fullName"
        @corre="onCorre"
      ></Relation>
    </el-dialog>
    <el-dialog
      title="任务设置(分局)"
      :visible.sync="branchVisiable"
      width="30%"
      :destroy-on-close="true"
      v-if="branchVisiable"
    >
      <BranchChunk
        :oSource="branchSourceId"
        :systemList="systemList"
        :fullName="fullName"
        :relationSourceId="branchSourceId"
        @corres="branchVisiable=false"
      ></BranchChunk>
    </el-dialog>
    <el-dialog
      title="附件管理"
      :visible.sync="fileVisible"
      width="30%"
      :close-on-click-modal="false"
      :destroy-on-close="true"
    >
      <UploadTem :ids="ids[0]"></UploadTem>
    </el-dialog>
    <el-dialog
      title="企业信息"
      :visible.sync="sourceVisible"
      custom-class="company-dialog"
      width="60%"
      :close-on-click-modal="false"
      :destroy-on-close="true"
    >
      <SeeChunk :seeData="editSource"></SeeChunk>
    </el-dialog>
  </div>
</template>
<script>
import FormChunk from "./components/form";
import {
  getRegion,
  getIndustry,
  getSourceListPost,
  tagDetailList,
  sourceDel,
  sourceUserAdd,
  sourceUserReset,
  getTagList
} from "@/api/composite/source";
import utils from "@/utils/ruoyi";
import Treeselect from "@riophae/vue-treeselect";
import Relation from "./components/relation";
import UploadTem from "./components/upload";
import SeeChunk from "./components/see";
import BranchChunk from "./components/branch";
import EditTagName from "./components/editTagName";
import {noticeOperaAdd} from "@/api/system/config";

export default {
  name: "Source",
  components: {
    FormChunk,
    Treeselect,
    UploadTem,
    Relation,
    SeeChunk,
    BranchChunk,
    EditTagName
  },
  data () {
    return {
      systemList: [],
      tableData: [],
      queryForm: {
        sourceName: null,
        tagHeadIds: null,
        townCode: null,
        industryCode: null
      },
      page: {
        pageNum: 1,
        pageSize: 20,
      },
      total: 0,
      visible: false,
      fileVisible: false,
      //
      tagLoading: true,
      isClose: true,
      //镇街
      townCodeOptions: [],
      //行业
      industryCodeOptions: [],
      //选中平台
      // checkTagId: "",
      //编辑企业
      editSource: {},
      //表格
      single: true,
      loading: false,
      selectRows: [],
      ids: [],
      //平台关联
      relationVisible: false,
      checkboxGroup: [],
      relationSourceId: "",
      //监控级别
      envLevel: [],
      //企业详情
      sourceVisible: false,
      sourceStatusOptions: [],
      sourceScale: [],
      //任务设置分局
      branchVisiable: false,
      branchSourceId: null,
      edit: false,
      description: "",
      fullName: null,
      tagHeadIds: [],
    };
  },
  created () {
    //镇街列表
    getRegion(4419).then((res) => (this.townCodeOptions = res.data));
    //行业
    getIndustry().then((res) => (this.industryCodeOptions = res.data));
    //监控级别
    this.getDicts("env_monitor_level").then((response) => {
      this.envLevel = response.data;
    });
    this.getDicts("source_status").then(
      (res) => (this.sourceStatusOptions = res.data)
    );
    //企业规模
    this.getDicts("source_scale").then((response) => {
      this.sourceScale = response.data;
    });
    // //平台列表
    this.getSourceTagList();
    this.getList(utils.filterData(this.queryForm));
  },
  methods: {
    getList (v = {}) {
      this.loading = true;
      v = utils.filterData(v);
      getSourceListPost(v, this.urlParameter(this.page)).then((res) => {
        if (res.code === 200) {
          this.tableData = res.rows;
          this.total = res.total;
          this.loading = false;
        }
      });
    },
    getSourceTagList () {
      getTagList().then((res) => {
        if (res.code === 200) {
          this.systemList = res.rows;
        }
      });
    },
    // //新增
    handleAdd () {
      this.editSource = {};
      this.visible = true;
    },
    //编辑
    handleEdit () {
      this.editSource = this.selectRows[0];
      this.visible = true;
    },
    //删除
    handleDel () {
      let postId = this.selectRows[0].sourceId;
      this.$confirm("是否确认删除选中数据数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(function () {
          return sourceDel(postId);
        })
        .then(() => {
          this.getList(this.queryForm);
          this.msgSuccess("删除成功");
        })
        .catch(function () {
        });
    },
    //查看
    seeCom (v) {
      this.editSource = v;
      this.sourceVisible = true;
    },
    //关联展示
    relation () {
      this.relationSourceId = this.selectRows[0].sourceId;
      this.relationVisible = true;
    },
    onCorre () {
      this.relationVisible = false;
    },
    tagClose () {
      this.checkboxGroup = [];
    },
    changeTagHead (v) {
      if (this.tagHeadIds.includes(v)) {
        this.tagHeadIds.splice(this.tagHeadIds.findIndex(item => item === v), 1);
      } else {
        this.tagHeadIds.push(v);
      }
      this.queryForm.pageNum = 1;
      // this.checkTagId = v;
      this.queryForm.tagHeadIds = this.tagHeadIds;
      this.getList(this.queryForm);
    },
    //表格选中
    handleSelectionChange (selection) {
      this.selectRows = selection;
      if (selection.length > 0) {
        this.fullName = selection[0].sourceName;
      }
      this.ids = selection.map((item) => item.sourceId);
      this.single = selection.length != 1;
    },
    /** 搜索按钮操作 */
    handleQuery () {
      this.queryForm.pageNum = 1;
      this.getList(this.queryForm);
    },
    //重置表单
    resetQuery () {
      this.resetForm("queryForm");
      this.queryForm.tagHeadIds = [];
      this.tagHeadIds = [];
      // this.checkTagId = "";
      this.handleQuery();
    },
    //附件上传
    fileAdmin () {
      this.fileVisible = true;
    },
    successRequest () {
      this.getList(this.queryForm);
      this.visible = false;
    },
    //监控级别
    envLevelType (row) {
      return this.selectDictLabel(this.envLevel, row.envMonitorLevel);
    },
    //生成账号
    addUser () {
      sourceUserAdd({sourceId: this.selectRows[0].sourceId}).then((res) => {
        if (res.code === 200) {
          let o = res.data;
          this.$message({
            showClose: true,
            dangerouslyUseHTMLString: true,
            message: `<p style='color:teal;padding:0 5px;'>${this.selectRows[0].sourceName}，生成账号成功！
        <p style='color:teal;margin-top:10px;padding:0 5px;'>账号为：${o.userName}</p>
        <p style='color:teal;margin-top:10px;padding:0 5px;'>密码为：${o.password}</p>
        </p>`,
            type: "success",
            duration: 0
          });
          this.getList(this.queryForm);
        }
      });
    },
    //重置密码
    resetpwd () {
      sourceUserReset({sourceId: this.selectRows[0].sourceId}).then((res) => {
        let journal = {
          module: "综合管理 → 污染源列表",
          action: `重置(${this.fullName})密码操作`,
          operaResult: res.code === 200 ? "操作成功" : "操作失败",
          request: "/enterprise/user/reset"
        };
        noticeOperaAdd(journal);

        if (res.code === 200) {
          let o = res.data;
          this.$message({
            showClose: true,
            dangerouslyUseHTMLString: true,
            message: `<p style='color:teal;padding:0 5px;'>${this.selectRows[0].sourceName}，重置密码成功！<p style='color:teal;margin-top:10px;padding:0 5px;'>新密码为：${o.password}</p></p>`,
            type: "success",
            duration: 0
          });
          this.getList(this.queryForm);
        }
      });
    },
    //企业规模
    cScaleType (row) {
      return this.selectDictLabel(this.sourceScale, row.sourceScale);
    },
    branch () {
      this.branchVisiable = true;
      this.branchSourceId = this.selectRows[0].sourceId;
    },
    editSuccess () {
      this.msgSuccess("名称修改成功!");
      this.getSourceTagList();
    }
  }
};
</script>
<style lang="scss">
.composite-style {
  position: relative;
  width: 100%;
  height: calc(100vh - 100px);
  //margin-top: 12px;
  padding: 10px;
  display: flex;
  overflow: hidden;
  //padding: 0 10px;

  .composite-left {
    //position: absolute;
    width: 200px;
    height: 100%;
    box-shadow: 2px 0 6px rgba(0, 21, 41, 0.25);
    overflow-y: auto;
    padding: 2px;
  }

  .composite-right {
    flex: 1;
    height: 100%;
    margin-left: 10px;
    box-shadow: -2px 0 6px rgba(0, 21, 41, 0.25);
    padding: 10px;
    display: flex;
    flex-direction: column;
    overflow: hidden;
  }

  .box-card {
    margin-bottom: 10px;
    cursor: pointer;
    display: flex;
    width: 100%;

    .el-card__body {
      width: 100%;
      padding: 0px;
      font-size: 12px;

      &:hover {
        color: #42b983;
        font-weight: bold;
      }
    }
  }

  .check-tag-style {
    color: #42b983;
    font-weight: bold;
  }
}

.company-dialog {
  height: calc(100% - 12vh);
  overflow: hidden;

  .el-dialog__body {
    height: 100%;
    overflow-y: auto;
    padding: 10px 10px 40px;
  }
}

.checkbox-style {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;

  label {
    margin: 0;
    margin-bottom: 15px;
    margin-right: 15px;
  }

  .el-checkbox.is-bordered + .el-checkbox.is-bordered {
    margin-left: 0px;
  }

  .el-checkbox.is-bordered.el-checkbox--mini {
    height: 25px;
  }
}
</style>
