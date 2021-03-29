<template>
  <div class="user_platform_trans">
    <div class="platform_trans">
      <!-- 左侧 -->
      <div class="initial_source">
        <el-form ref="initialForm" :model="initialForm">
          <el-form-item>
            <el-input v-model="initialForm.sourceName" placeholder="企业名称" @keyup.enter.native="getLeftList(initialForm)">
              <el-select
                v-model="initialForm.townCode"
                slot="prepend"
                placeholder="镇街"
              >
                <el-option
                  v-for="(item, index) in townCodeOptions"
                  :key="index"
                  :label="item.regionName"
                  :value="item.regionCode"
                  :disabled="item.disabled"
                ></el-option>
              </el-select>
              <el-button slot="append" icon="el-icon-search" @click="getLeftList(initialForm)">搜索</el-button>
            </el-input>
          </el-form-item>
        </el-form>
        <el-table
          :data="initialData"
          style="width: 100%"
          height="100%"
          border
          size="mini"
          ref="initialTabel"
          v-loading="initialLoading"
          @selection-change="handleChange"
          @row-click="(row)=>$refs.initialTabel.toggleRowSelection(row)"
        >
          <el-table-column type="selection" width="55" align="center"></el-table-column>
          <el-table-column prop="fullName" label="企业名称" show-overflow-tooltip></el-table-column>
          <el-table-column prop="townName" label="镇街" width="80"></el-table-column>
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
        <el-button type="primary" icon="el-icon-arrow-left" title="移除企业" @click="sourceUpdate"></el-button>
      </div>
      <!-- 右侧 -->
      <div class="grant_source">
        <el-form ref="grantForm" :model="grantForm">
          <el-form-item>
            <el-input v-model="grantForm.sourceName" placeholder="企业名称" @keyup.enter.native="getRightList(grantForm)">
              <el-select
                v-model="grantForm.townCode"
                slot="prepend"
                placeholder="镇街"
              >
                <el-option
                  v-for="(item, index) in townCodeOptions"
                  :key="index"
                  :label="item.regionName"
                  :value="item.regionCode"
                  :disabled="item.disabled"
                ></el-option>
              </el-select>
              <el-button slot="append" icon="el-icon-search" @click="getRightList(grantForm)">搜索</el-button>
            </el-input>
          </el-form-item>
        </el-form>
        <el-table
          :data="grantData"
          style="width: 100%"
          size="mini"
          border
          v-loading="grantLoading"
          ref="grantTabel"
          @selection-change="handleGrantChange"
          @row-click="(row)=>$refs.grantTabel.toggleRowSelection(row)"
        >
          <el-table-column type="selection" width="55" align="center"></el-table-column>
          <el-table-column prop="fullName" label="企业名称" show-overflow-tooltip></el-table-column>
          <el-table-column prop="townName" label="镇街" width="80"></el-table-column>
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
import {
  sourceListByLabel,
  labelRelatioSource,
  labelUnrelationSource
} from "@/api/system/label";
import {getRegion} from "@/api/composite/source";

export default {
  props: {
    labelHeadId: {
      type: Number,
    },
  },
  created() {
    getRegion(4419).then((res) => {
      this.townCodeOptions = res.data;
    });
  },
  data() {
    return {
      value: "",
      initialData: [],
      grantData: [],
      initialPage: {pageNum: 1, pageSize: 30},
      initialForm: {
        sourceName: null,
      },
      grantForm: {
        sourceName: null,
      },
      grantPage: {pageNum: 1, pageSize: 30},
      initialTotal: 0,
      grantTotal: 0,
      tagHeadId: "",
      pagerCount: 5,
      initialLoading: false,
      grantLoading: false,
      sourceIds: [],
      sourceGrantIds: [],
      townCodeOptions: [],
    };
  },
  watch: {
    labelHeadId: {
      handler(v) {
        if (v) {
          this.getLeftList();
          this.getRightList();
        }
      },
      immediate: true,
    },
  },
  methods: {
    getLeftList(v = {}) {
      this.initialLoading = true;
      let oVal = {
        type: "left",
        labelHeadId: this.labelHeadId
      }
      oVal = Object.assign(oVal, v);
      sourceListByLabel(oVal, this.urlParameter(this.initialPage)).then((res) => {
        if (res.code === 200) {
          this.initialData = res.rows;
          this.initialTotal = res.total;
        }
        this.initialLoading = false;
      });
    },
    getRightList(v = {}) {
      this.grantLoading = true;
      let oVal = {
        type: "right",
        labelHeadId: this.labelHeadId,
      }
      oVal = Object.assign(oVal, v)
      sourceListByLabel(oVal, this.urlParameter(this.grantPage)).then((res) => {
        if (res.code === 200) {
          this.grantData = res.rows;
          this.grantTotal = res.total;
        }
        this.grantLoading = false;
      });
    },
    handleChange(selection) {
      if (selection.length > 0) {
        let oArr = [];
        selection.forEach((i) => {
          oArr.push(i.sourceId);
        });
        this.sourceIds = oArr;
      }
    },
    handleGrantChange(selection) {
      if (selection.length > 0) {
        let oArr = [];
        selection.forEach((i) => {
          oArr.push(i.sourceId);
        });
        this.sourceGrantIds = oArr;
      }
    },
    sourceAdd() {
      if (this.sourceIds.length > 0) {
        let oVal = this.sourceIds;
        labelRelatioSource(oVal, this.labelHeadId).then((res) => {
          if (res.code === 200) {
            this.msgSuccess(res.msg);
            this.getLeftList();
            this.getRightList();
          }
        });
      } else {
        this.msgError("请选择企业");
      }
    },
    sourceUpdate() {
      if (this.sourceGrantIds.length > 0) {
        let oVal = this.sourceGrantIds;
        labelUnrelationSource(oVal, this.labelHeadId).then((res) => {
          if (res.code === 200) {
            this.msgSuccess(res.msg);
            this.getLeftList();
            this.getRightList();
          }
        });
      } else {
        this.msgError("请选择企业");
      }
    },
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
      height: 100%;

      /deep/ .el-input-group__prepend {
        width: 90px;
      }
    }

    .grant_source {
      border: 1px solid #ddd;
      margin-left: 5px;
      flex: 1;
      display: flex;
      flex-direction: column;
      overflow: hidden;
      padding: 5px;
      height: 100%;

      /deep/ .el-input-group__prepend {
        width: 90px;
      }
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
