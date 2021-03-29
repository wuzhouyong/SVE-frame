<template>
  <div class="task-wk">
    <!-- 搜索表单 -->
    <el-form :model="queryForm" ref="queryForm" :inline="true" class="guocheng-serchForm">
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
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <!-- 表格 -->
    <el-table
      style="width: 100%;flex:1;"
      ref="oTable"
      height="100%"
      border
      :data="tableData"
      @row-click="rowClick"
      class="changeTable"
    >
      <el-table-column type="index" label="序号" align="center" width="50"></el-table-column>
      <el-table-column label="污染源信息变更待审核列表">
        <template slot-scope="scope">
          <el-button type="text" size="small">{{ scope.row.fullName }}</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog :title="title" :visible.sync="visible" width="50%" custom-class="sourceChangeData">
      <div class="sourceMeseage">
        <el-row :gutter="15">
          <el-col :lg="12" :md="12" :sm="24">
            <div class="sourceLeft">
              <h4>
                <i class="el-icon-document"></i> 信息变更前
              </h4>
              <el-card class="box-card" shadow="hover">
                <ul>
                  <li>
                    <p>企业名称:</p>
                    <span>{{ oldData.fullName || "-" }}</span>
                  </li>
                  <li>
                    <p>统一社会信用代码:</p>
                    <span>{{ oldData.socialCreditCode || "-" }}</span>
                  </li>
                  <li>
                    <p>排污许可证编号:</p>
                    <span>{{ oldData.emissionPermitCode || "-" }}</span>
                  </li>
                  <li>
                    <p>经度:</p>
                    <span>{{ oldData.longitude || "-" }}</span>
                  </li>
                  <li>
                    <p>维度:</p>
                    <span>{{ oldData.latitude || "-" }}</span>
                  </li>
                  <li>
                    <p>所属镇街:</p>
                    <span>{{ oldData.townName || "-" }}</span>
                  </li>
                  <li>
                    <p>企业地址:</p>
                    <span>{{ oldData.address || "-" }}</span>
                  </li>
                  <li>
                    <p>企业经营状态:</p>
                    <span>{{ dataState[oldData.sourceStatus] || "-" }}</span>
                  </li>
                  <li>
                    <p>行业类别:</p>
                    <span>{{ oldData.industryName || "-" }}</span>
                  </li>
                  <li>
                    <p>企业规模:</p>
                    <span>{{ source_scale[oldData.sourceScale] || "-" }}</span>
                  </li>
                  <li>
                    <p>法定代表人:</p>
                    <span>{{ oldData.legalPersonName || "-" }}</span>
                  </li>
                  <li>
                    <p>法人联系电话:</p>
                    <span>{{ oldData.legalPersonPhone || "-" }}</span>
                  </li>
                </ul>
              </el-card>
            </div>
          </el-col>
          <el-col :lg="12" :md="12" :sm="24">
            <div class="sourceRight">
              <h4>
                <i class="el-icon-edit"></i> 信息变更后
              </h4>
              <el-card class="box-card" shadow="hover">
                <ul>
                  <li>
                    <p>企业名称:</p>
                    <span>{{ cData.fullName || "-" }}</span>
                  </li>
                  <li>
                    <p>统一社会信用代码:</p>
                    <span>{{ cData.socialCreditCode || "-" }}</span>
                  </li>
                  <li>
                    <p>排污许可证编号:</p>
                    <span>{{ cData.emissionPermitCode || "-" }}</span>
                  </li>
                  <li>
                    <p>经度:</p>
                    <span>{{ cData.longitude || "-" }}</span>
                  </li>
                  <li>
                    <p>维度:</p>
                    <span>{{ cData.latitude || "-" }}</span>
                  </li>
                  <li>
                    <p>所属镇街:</p>
                    <span>{{ cData.townName || "-" }}</span>
                  </li>
                  <li>
                    <p>企业地址:</p>
                    <span>{{ cData.address || "-" }}</span>
                  </li>
                  <li>
                    <p>企业经营状态:</p>
                    <span>{{ dataState[cData.sourceStatus] || "-" }}</span>
                  </li>
                  <li>
                    <p>行业类别:</p>
                    <span>{{ cData.industryName || "-" }}</span>
                  </li>
                  <li>
                    <p>企业规模:</p>
                    <span>{{ source_scale[cData.sourceScale] || "-" }}</span>
                  </li>
                  <li>
                    <p>法定代表人:</p>
                    <span>{{ cData.legalPersonName || "-" }}</span>
                  </li>
                  <li>
                    <p>法人联系电话:</p>
                    <span>{{ cData.legalPersonPhone || "-" }}</span>
                  </li>
                </ul>
              </el-card>
            </div>
          </el-col>
        </el-row>
        <el-table :data="fileList" stripe style="width: 100%;margin-top:20px;" height="160px">
          <el-table-column prop="showName" label="附件名称">
            <template slot-scope="scope">
              <el-link
                type="primary"
                :underline="false"
                target="_blank"
                :href="getlink(scope.row.Id)"
                style="font-size:12px;"
              >{{ scope.row.showName }}
              </el-link>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="agreeChange">同意变更</el-button>
        <el-button type="danger" plain @click="refusedChange">拒绝变更</el-button>
      </span>
    </el-dialog>
  </div>
</template>
<script>
import {dataModify, getSource, modifyAgree} from "@/api/composite/source";
import {fileList} from "@/api/upload";
import {mapState} from "vuex";

export default {
  data () {
    return {
      queryForm: {
        fullName: null,
      },
      cData: {},
      oldData: {},
      tableData: [],
      fileList: [],
      source_scale: ["", "微型", "小型", "中型", "大型"],
      dataState: ["", "", "", "在产", "关停", "关闭", "其他"],
      visible: false,
      title: "",
    };
  },
  computed: {
    ...mapState("label", ["searchData"])
  },
  watch: {
    searchData: {
      handler () {
        this.handleQuery();
      },
      immediate: true
    },
  },
  created () {
    this.getList();
  },
  methods: {
    getSourceDetails (v) {
      getSource(v).then((res) => {
        if (res.code === 200) {
          this.oldData = res.data;
        }
      });
    },
    getList (v = {}) {
      let oVal = Object.assign({labelIds: this.searchData}, v);
      dataModify(oVal).then((res) => {
        if (res.code === 200) {
          this.tableData = res.rows;
        }
      });
    },
    getfileList (id) {
      let ref = {
        refId: id,
        refType: "source_change_prove",
      };
      fileList(ref).then((res) => {
        if (res.code === 200) {
          this.fileList = res.data;
        }
      });
    },
    handleQuery () {
      this.getList(this.queryForm);
    },
    resetQuery () {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    rowClick (v) {
      this.getSourceDetails(v.sourceId);
      this.getfileList(v.modifyId);
      this.cData = v;
      this.visible = true;
    },
    getlink (id) {
      return `${process.env.VUE_APP_BASE_API}/com/getfile/${id}`;
    },
    agreeChange () {
      this.cData.auditState = 1;
      modifyAgree(this.cData).then((res) => {
        if (res.code === 200) {
          this.msgSuccess("操作成功");
          this.visible = false;
          this.getList();
        }
      });
    },
    refusedChange () {
      this.cData.auditState = 2;
      modifyAgree(this.cData).then((res) => {
        if (res.code === 200) {
          this.msgSuccess("操作成功");
          this.visible = false;
          this.getList();
        }
      });
    },
  },
};
</script>
<style lang="scss" scope>
.changeTable {
  tr.el-table__row {
    td {
      padding: 1px 0;
    }
  }
}

.sourceChangeData {
  .el-dialog__header {
    padding: 8px 10px;

    .el-dialog__headerbtn {
      top: 10px;
    }

    span {
      font-size: 16px;
    }
  }

  .el-dialog__body {
    padding: 5px 10px;
  }
}

.sourceMeseage {
  width: 100%;
  height: 100%;
  overflow: hidden;

  ul {
    padding: 0;
    margin: 0;

    li {
      list-style-type: none;
      display: flex;
      align-items: center;

      p {
        width: 120px;
        text-align: right;
        font-weight: bold;
        margin: 8px;
      }

      span {
        flex: 1;
        padding-left: 5px;
      }
    }
  }
}
</style>
