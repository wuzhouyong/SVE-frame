<template>
  <div class="user_platform_trans">
    <div class="platform_choose">
      <p class="pName">平台选择：</p>
      <el-select v-model="value" placeholder="请选择" class="pSelect" @change="onChange">
        <el-option v-for="item in analysis" :key="item.id" :label="item.name" :value="item.id"></el-option>
      </el-select>
      <el-button icon="el-icon-sort" type="primary" size="mini" style="margin-left:5px;" @click="taskBatchUser(1)">批量关联
      </el-button>
      <el-button icon="el-icon-scissors" type="warning" size="mini" @click="taskBatchUser(0)">批量取消</el-button>
    </div>
    <div class="platform_trans">
      <!-- 左侧 -->
      <div class="initial_source">
        <el-form ref="initialForm" :model="initialForm">
          <el-form-item>
            <el-input v-model="initialForm.fullName" placeholder="企业搜索">
              <el-button slot="append" icon="el-icon-search" @click="getLeftList(initialForm)">搜索</el-button>
            </el-input>
          </el-form-item>
        </el-form>
        <el-table
          :data="initialData"
          style="width: 100%"
          height="100%"
          size="mini"
          ref="initialTabel"
          v-loading="initialLoading"
          @selection-change="handleChange"
          @row-click="(row)=>$refs.initialTabel.toggleRowSelection(row)"
        >
          <el-table-column type="selection" width="55"></el-table-column>
          <el-table-column prop="fullName" label="企业名称"></el-table-column>
        </el-table>
        <!-- 分页 -->
        <pagination
          :total="initialTotal"
          :page.sync="initialPage.pageNum"
          :limit.sync="initialPage.pageSize"
          :pagerCount="pagerCount"
          layout="total, prev, pager, next"
          @pagination="getLeftList(initialForm)"
        />
      </div>
      <div class="transform_btn">
        <el-button type="primary" icon="el-icon-arrow-right" title="添加企业" @click="sourceAdd"></el-button>
        <el-button type="primary" icon="el-icon-arrow-left" title="取消企业" @click="sourceUpdate"></el-button>
      </div>
      <!-- 右侧 -->
      <div class="grant_source">
        <el-form ref="grantForm" :model="grantForm">
          <el-form-item>
            <el-input v-model="grantForm.fullName" placeholder="企业搜索">
              <el-button slot="append" icon="el-icon-search" @click="getRightList(grantForm)">搜索</el-button>
            </el-input>
          </el-form-item>
        </el-form>
        <el-table
          :data="grantData"
          style="width: 100%"
          height="100%"
          size="mini"
          v-loading="grantLoading"
          ref="grantTabel"
          @selection-change="handleGrantChange"
          @row-click="(row)=>$refs.grantTabel.toggleRowSelection(row)"
        >
          <el-table-column type="selection" width="55"></el-table-column>
          <el-table-column prop="fullName" label="企业名称"></el-table-column>
        </el-table>
        <!-- 分页 -->
        <pagination
          :total="grantTotal"
          :page.sync="grantPage.pageNum"
          :limit.sync="grantPage.pageSize"
          layout="total, prev, pager, next"
          :pagerCount="pagerCount"
        />
      </div>
    </div>
  </div>
</template>
<script>
import {analysis} from "@/config";
import {
  userTaskLeft,
  userTaskRight,
  userTaskUpdate,
  userTaskAdd,
  taskBatch
} from "@/api/system/user";

export default {
  props: {
    userId: {
      type: Number,
    },
  },
  data () {
    return {
      analysis,
      value: "",
      initialData: [],
      grantData: [],
      initialPage: {pageNum: 1, pageSize: 30},
      initialForm: {
        userId: null,
        tagId: "",
        fullName: "",
      },
      grantForm: {
        userId: null,
        tagId: "",
        fullName: "",
      },
      grantPage: {pageNum: 1, pageSize: 30},
      initialTotal: 0,
      grantTotal: 0,
      pagerCount: 5,
      initialLoading: false,
      grantLoading: false,
      sourceIds: [],
      sourceGrantIds: [],
      tagHeadId: null,
    };
  },
  watch: {
    userId: {
      handler (v) {
        if (v) {
          this.initialForm.userId = v;
          this.grantForm.userId = v;
        }
      },
      immediate: true,
    },
  },
  methods: {
    getLeftList (v = {}) {
      if (this.initialForm.tagId) {
        this.initialLoading = true;
        userTaskLeft(v, this.urlParameter(this.initialPage)).then((res) => {
          if (res.code === 200) {
            this.initialData = res.rows;
            this.initialTotal = res.total;
          }
          this.initialLoading = false;
        });
      } else {
        this.initialLoading = false;
        this.msgError("请选择查询平台");
      }

    },
    getRightList (v = {}) {
      if (this.grantForm.tagId) {
        this.grantLoading = true;
        userTaskRight(v, this.urlParameter(this.grantPage)).then((res) => {
          if (res.code === 200) {
            this.grantData = res.rows;
            this.grantTotal = res.total;
          }
          this.grantLoading = false;
        });
      } else {
        this.initialLoading = false;
        this.msgError("请选择查询平台");
      }

    },
    onChange (v) {
      this.tagHeadId = v;
      this.initialForm.fullName = "";
      this.grantForm.fullName = "";
      this.initialForm.tagId = v;
      this.grantForm.tagId = v;
      this.getLeftList(this.initialForm);
      this.getRightList(this.grantForm);
      // this.getInitialList();
    },
    handleChange (selection) {
      if (selection.length > 0) {
        let oArr = [];
        selection.forEach((i) => {
          oArr.push(i.sourceId);
        });
        this.sourceIds = oArr;
      }
    },
    handleGrantChange (selection) {
      if (selection.length > 0) {
        let oArr = [];
        selection.forEach((i) => {
          oArr.push(i.sourceId);
        });
        this.sourceGrantIds = oArr;
      }
    },
    sourceAdd () {
      if (this.sourceIds.length > 0) {
        let oVal = {
          userId: this.initialForm.userId,
          tagId: this.initialForm.tagId,
          sourceIds: this.sourceIds,
        };
        userTaskAdd(oVal).then((res) => {
          if (res.code === 200) {
            this.msgSuccess(res.message);
            this.getLeftList(this.initialForm);
            this.getRightList(this.grantForm);
          }
        });
      } else {
        this.msgError("请选择企业");
      }
    },
    sourceUpdate () {
      if (this.sourceGrantIds.length > 0) {
        let oVal = {
          userId: this.grantForm.userId,
          tagId: this.grantForm.tagId,
          sourceIds: this.sourceGrantIds,
        };
        userTaskUpdate(oVal).then((res) => {
          if (res.code === 200) {
            this.msgSuccess(res.message);
            this.getLeftList(this.initialForm);
            this.getRightList(this.grantForm);
          }
        });
      } else {
        this.msgError("请选择企业");
      }
    },
    taskBatchUser (v) {
      let oVal = {
        userId: this.userId,
        tagHeadId: this.tagHeadId,
        type: v
      };
      taskBatch(oVal).then(res => {
        if (res.code === 200) {
          this.msgSuccess(res.message);
          this.getLeftList(this.initialForm);
          this.getRightList(this.grantForm);
        }
      });
    }
  },
};
</script>
<style lang="scss">
.user_platform_trans {
  height: 100%;
  display: flex;
  flex-direction: column;
  overflow: hidden;

  .platform_choose {
    width: 100%;
    height: auto;
    display: flex;
    align-items: center;

    .pName {
      font-size: 14px;
      margin-right: 10px;
    }

    .pSelect {
      flex: 1;
    }
  }

  .platform_trans {
    display: flex;
    flex: 1;
    overflow: hidden;

    .transform_btn {
      width: 50px;
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;

      button {
        margin: 0;
        margin: 5px 0;
      }
    }

    .initial_source {
      border: 1px solid #ddd;
      margin-right: 5px;
      flex: 1;
      display: flex;
      flex-direction: column;
      overflow: hidden;
      padding: 5px;
    }

    .grant_source {
      border: 1px solid #ddd;
      margin-left: 5px;
      flex: 1;
      display: flex;
      flex-direction: column;
      overflow: hidden;
      padding: 5px;
    }
  }
}

.transDialog {
  .el-dialog__body {
    padding: 15px 10px;
    flex: 1;
    overflow: hidden;
  }

  th.el-table-column--selection {
    .cell {
      padding-left: 14px;
    }
  }
}
</style>
