<template>
  <div class="label-wk">
    <div class="label-left">
      <div class="label-left-top">
        <span style="font-size:12px;padding-left:5px;">
          <i class="el-icon-s-flag" style="padding-right:5px"></i>标签管理
        </span>
        <el-button type="primary" icon="el-icon-plus" plain @click="addLabel(1)">新增分类</el-button>
        <!--        <el-button type="danger" icon="el-icon-delete" plain>删除分类</el-button>-->
      </div>
      <div class="label-left-bottom">
        <el-tree :data="treeData" node-key="labelHeadId" default-expand-all>
          <div class="custom-tree-node treeOther" slot-scope="{ node, data }">
            <span style="font-size:12px;">
              <i class="el-icon-folder-opened" style="color:#f0c78a;padding-right:5px" v-if="data.parentId===null"></i>
              <i class="el-icon-folder" style="color:#f0c78a;padding-right:5px" v-else></i>
              {{ data.labelHeadName }}
            </span>
            <el-dropdown trigger="click">
                 <span class="el-dropdown-link">
                    <i class="el-icon-more el-icon--right iMore"></i>
                  </span>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item>
                  <el-link type="success" icon="el-icon-paperclip" style="font-size:12px;font-weight: bold"
                           :underline="false"
                           v-if="data.parentId" @click.stop="relationSource(data)">关 联 企 业
                  </el-link>
                </el-dropdown-item>
                <el-dropdown-item>
                  <el-link type="primary" icon="el-icon-plus" style="font-size:12px;font-weight: bold"
                           :underline="false"
                           v-if="!data.parentId" @click.stop="addchildrenLabel(data)">新 增 子 标 签
                  </el-link>
                </el-dropdown-item>
                <el-dropdown-item>
                  <el-link type="warning" icon="el-icon-edit" style="font-size:12px;font-weight: bold"
                           :underline="false"
                           @click.stop="editLabel(data)">编 辑 标 签
                  </el-link>
                </el-dropdown-item>
                <el-dropdown-item>
                  <el-link type="danger" icon="el-icon-delete" style="font-size:12px;font-weight: bold"
                           :underline="false"
                           v-if="isDel(data)"
                           @click.stop="handleDelete(data)">删 除 标 签
                  </el-link>
                </el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </div>
        </el-tree>
      </div>
    </div>
    <div class="label-right">
      <el-form :model="queryForm" ref="queryForm" :inline="true" class="outwater-serchForm">
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
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" @click="handleQuery" size="mini">搜索</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        </el-form-item>
      </el-form>
      <!--      表格-->
      <el-table
        :data="tableData"
        v-loading="loading"
        row-key="pagehelper_row_id"
        style="width: 100%"
        height="100%"
        border
      >
        <el-table-column label="企业名称" width="260" prop="fullName" show-overflow-tooltip></el-table-column>
        <el-table-column prop="townName" label="所属镇街" width="100"></el-table-column>
        <el-table-column prop="labelList" label="标签"></el-table-column>
        <el-table-column label="操作" width="100" align="center">
          <template slot-scope="scope">
            <el-link type="primary" icon="el-icon-star-on" style="font-size:12px;" :underline="false"
                     @click="stickFil(scope.row)">贴标签
            </el-link>
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
    </div>
    <el-dialog
      :title="title"
      :visible.sync="visible"
      :close-on-click-modal="false"
      v-if="visible"
      width="30%">
      <el-form
        ref="elForm"
        :model="form"
        :rules="rules"
        size="mini"
        label-width="80px"
      >
        <el-form-item label="标签名称" prop="labelHeadName">
          <el-input
            v-model="form.labelHeadName"
            placeholder="请输入标签名称"
            maxlength="16"
            show-word-limit
            clearable
            :style="{width: '100%'}"
          ></el-input>
        </el-form-item>
        <el-form-item label="所属分类" prop="parentId" v-if="isParent===2">
          <el-select v-model="form.parentId" placeholder="请选择所属分类" style="width:100%">
            <el-option :label="i.labelHeadName" :value="i.labelHeadId" v-for="i in treeData"
                       :key="i.labelHeadId"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="subLabel">确 定</el-button>
      </span>
    </el-dialog>
    <el-dialog
      :title="ralationTitle"
      :visible.sync="relationVisible"
      custom-class="transferDialog"
      :close-on-click-modal="false"
      @close="onClose"
      v-if="relationVisible">
      <n-transfer :labelHeadId="relationId"></n-transfer>
    </el-dialog>
    <el-dialog
      title="贴标签"
      :visible.sync="stickVisible"
      custom-class="transferDialog"
      :close-on-click-modal="false"
      width="400px"
      @close="onStickClose"
      v-if="stickVisible">
      <div style="height:100%;overflow-y: auto">
        <el-tree
          :data="checkTree"
          node-key="labelHeadId"
          @check-change="isCheck"
          ref="stickTree"
          default-expand-all
          show-checkbox
          :default-checked-keys="defaultKeys"
        >
          <div class="custom-tree-node treeOther" slot-scope="{ node, data }">
            <span style="font-size:12px;">
              <i class="el-icon-folder-opened" style="color:#f0c78a;padding-right:5px" v-if="data.parentId===null"></i>
              <i class="el-icon-folder" style="color:#f0c78a;padding-right:5px" v-else></i>
              {{ data.labelHeadName }}
            </span>
          </div>
        </el-tree>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  labelByUser,
  labelAdd,
  labelEdit,
  labelRemove,
  labelSourceList,
  labelRelatioLabel,
  labelUnrelationLabel
} from '@/api/system/label'
import {getRegion} from "@/api/composite/source";
import nTransfer from "./transfer";

export default {
  components: {nTransfer},
  created() {
    this.getList(this.queryForm);
    this.getUser();
    //镇街列表
    getRegion(4419).then((res) => (this.townCodeOptions = res.data));
  },
  data() {
    return {
      loading: false,
      form: {
        labelHeadName: null,
        parentId: null
      },
      rules: {
        labelHeadName: [
          {
            required: true,
            message: "请输入标签名称",
            trigger: "blur"
          }
        ],
        parentId: [
          {
            required: true,
            message: "所属分类必选",
            trigger: "blur"
          }
        ],
      },
      treeData: [],
      checkTree: [],
      tableData: [],
      townCodeOptions: [],
      visible: false,
      relationVisible: false,
      title: null,
      ralationTitle: null,
      relationId: null,
      isParent: 1,
      queryForm: {
        sourceName: null,
        townCode: null
      },
      // 分页
      page: {
        pageNum: 1,
        pageSize: 30
      },
      total: 0,
      stickVisible: false,
      defaultKeys: [],
      checkSourceId: null
    }
  },
  methods: {
    getUser() {
      labelByUser().then(res => {
        if (res.code === 200) {
          let oRes = JSON.parse(JSON.stringify(res.data));
          oRes.forEach(i => {
            i.disabled = true
            // if (!i.parentId || (i.children === null || i.children.length < 1)) {
            //   i.disabled = true
            // }
          })
          this.checkTree = oRes;
          this.treeData = res.data;
        }
      })
    },
    getList(v = {}) {
      this.loading = true;
      labelSourceList(v, this.urlParameter(this.page)).then((res) => {
        if (res.code === 200) {
          this.tableData = res.rows;
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
      this.queryForm = {
        sourceName: null,
        townCode: null
      }
      this.page.pageNum = 1;
      this.getList();
    },
    addLabel() {
      this.isParent = 1;
      this.title = '新增分类';
      this.visible = true;
    },
    addchildrenLabel(v) {
      this.form.parentId = v.labelHeadId;
      this.isParent = 2;
      this.title = '新增子标签';
      this.visible = true;
    },
    subLabel() {
      this.$refs["elForm"].validate((valid) => {
        if (!valid) return;
        if (this.form.labelHeadId) {
          labelEdit(this.form).then(res => {
            if (res.code === 200) {
              this.msgSuccess("修改成功!");
              this.successMes();
            }
          })
        } else {
          labelAdd(this.form).then(res => {
            if (res.code === 200) {
              this.msgSuccess("新增成功!");
              this.successMes();
            }
          })
        }
      })
    },
    successMes() {
      this.getUser();
      this.visible = false;
      this.isParent = 1;
      this.form = {
        labelHeadName: null,
        parentId: null
      }
    },
    editLabel(v) {
      this.isParent = v.parentId === null ? 1 : 2
      this.form = Object.assign({}, this.form, v);
      this.visible = true;
    },
    handleDelete(v) {
      this.$confirm("确定删除此标签?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(function () {
          return labelRemove(v.labelHeadId);
        })
        .then(() => {
          this.getUser();
          this.msgSuccess("删除成功");
        })
        .catch(function () {
        });
    },
    //关联企业
    relationSource(v) {
      this.ralationTitle = v.labelHeadName;
      this.relationId = v.labelHeadId;
      this.relationVisible = true;
    },
    onClose() {
      this.getList(this.queryForm);
    },
    onStickClose() {
      this.getList(this.queryForm);
      this.checkSourceId = null;
    },
    //贴标签
    stickFil(v) {
      this.defaultKeys = v.labelIdList&&v.labelIdList.split(',');
      this.checkSourceId = v.sourceId;
      this.stickVisible = true;
    },
    isCheck(labelId, isCheck) {
      if (labelId.parentId) {
        let oId = [labelId.labelHeadId];
        if (isCheck) {
          labelRelatioLabel(oId, this.checkSourceId).then(res => {
            this.msgSuccess(res.msg);
          })
        } else {
          labelUnrelationLabel(oId, this.checkSourceId).then(res => {
            this.msgSuccess(res.msg);
          })
        }
      }
    },
    isDel(v) {
      if (v.isDefault) {
        return false;
      } else {
        if (v.children === null) {
          return true;
        }
        if (v.children.length > 0) {
          return false;
        }
      }
    }
  }
}
</script>

<style lang='scss' scoped>
.label-wk {
  width: 100%;
  height: 100%;
  display: flex;
  overflow: hidden;
  padding: 3px 0 5px;

  /deep/ .transferDialog {
    height: calc(100vh - 16vh);
    display: flex;
    flex-direction: column;
    overflow: hidden;

    .el-dialog__body {
      flex: 1;
      overflow: hidden;
    }
  }

  .label-left {
    width: 280px;
    height: 100%;
    box-shadow: 2px 0 6px rgba(0, 21, 41, 0.25);
    overflow-y: auto;
    padding: 2px;
    display: flex;
    flex-direction: column;
    overflow: hidden;

    .label-left-top {
      padding: 5px;
      border: 1px solid #ddd;
      box-shadow: 0px 3px 5px 1px #ddd;
      display: flex;
      justify-content: space-between;
      align-items: center;
    }

    .label-left-bottom {
      flex: 1;
      border: 1px solid #ddd;
      box-shadow: 0px 3px 5px 1px #ddd;
      margin-top: 2px;
      padding: 5px;

      .treeOther {
        display: flex;
        justify-content: space-between;
        align-items: center;
        overflow: hidden;
        width: 100%;
        padding-right: 10px;

        .iMore {
          color: rgba(64, 158, 255, 0.6);

          &:hover {
            color: rgba(64, 158, 255, 1);
          }
        }
      }
    }
  }

  .label-right {
    flex: 1;
    height: 100%;
    margin-left: 10px;
    box-shadow: -2px 0 6px rgba(0, 21, 41, 0.25);
    padding: 10px;
    display: flex;
    flex-direction: column;
    overflow: hidden;
  }
}
</style>
