<template>
  <div class="task-wk">
    <!-- 搜索表单 -->
    <el-form :model="queryForm" ref="queryForm" :inline="true" class="qixiu-serchForm">
      <el-form-item label="镇街" prop="townCodes">
        <el-select
          v-model="queryForm.townCodes"
          filterable
          multiple
          placeholder="请选择"
          style="width:250px;"
          :disabled="disabled"
          @change="onChange"
          collapse-tags
        >
          <el-checkbox v-model="checked" @change="selectAll" class="allSelect" v-if="fenju">全选</el-checkbox>
          <el-option
            v-for="(item, index) in townCodeOptions"
            :key="index"
            :label="item.regionName"
            :value="item.regionCode"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="监控任务" prop="tagHeadId">
        <el-select
          v-model="queryForm.tagHeadId"
          placeholder="请选择监控任务"
          clearable
          :style="{ width: '160px' }"
        >
          <el-option v-for="i in cities" :key="i.id" :label="i.name" :value="i.id"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item prop="listType">
        <el-radio-group v-model="queryForm.listType">
          <el-radio-button :label="-1">全部</el-radio-button>
          <el-radio-button :label="0">考核名单</el-radio-button>
          <el-radio-button :label="1">非考核名单</el-radio-button>
        </el-radio-group>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-upload2" size="mini" type="warning" @click="exportTables">导出表格</el-button>
      </el-form-item>
    </el-form>
    <el-table
      :data="tableData"
      style="width: 100%"
      height="100%"
      v-loading="loading"
      element-loading-text="数据查询中,请稍后!"
      :row-class-name="tableRowClassName"
    >
      <el-table-column prop="tagHeadName" label="监控任务" width="180"></el-table-column>
      <el-table-column prop="townName" label="镇街" width="120"></el-table-column>
      <el-table-column prop="factorycnt" label="监控企业数" width="120"></el-table-column>
      <el-table-column label="未开展" align="center">
        <el-table-column prop="accessQuantity" label="数量" align="center"></el-table-column>
        <el-table-column prop="accessRatio" label="比例" align="center">
          <template slot-scope="scope">
            <div>{{percent(scope.row.accessRatio)}}</div>
          </template>
        </el-table-column>
      </el-table-column>
      <el-table-column label="已施工" align="center">
        <el-table-column prop="constructionQuantity" label="数量" align="center"></el-table-column>
        <el-table-column prop="constructionRatio" label="比例" align="center">
          <template slot-scope="scope">
            <div>{{percent(scope.row.constructionRatio)}}</div>
          </template>
        </el-table-column>
      </el-table-column>
      <el-table-column label="已接入" align="center">
        <el-table-column prop="connectedQuantity" label="数量" align="center"></el-table-column>
        <el-table-column prop="connectedRatio" label="比例" align="center">
          <template slot-scope="scope">
            <div>{{percent(scope.row.connectedRatio)}}</div>
          </template>
        </el-table-column>
      </el-table-column>
      <el-table-column label="已验收" align="center">
        <el-table-column prop="acceptedQuantity" label="数量" align="center"></el-table-column>
        <el-table-column prop="acceptedratio" label="比例" align="center">
          <template slot-scope="scope">
            <div>{{percent(scope.row.acceptedratio)}}</div>
          </template>
        </el-table-column>
      </el-table-column>
      <el-table-column label="已暂缓" align="center">
        <el-table-column prop="suspendQuantity" label="数量" align="center"></el-table-column>
        <el-table-column prop="suspendRatio" label="比例" align="center">
          <template slot-scope="scope">
            <div>{{percent(scope.row.suspendRatio)}}</div>
          </template>
        </el-table-column>
      </el-table-column>
      <el-table-column label="取消安装" align="center">
        <el-table-column prop="cancelQuantity" label="数量" align="center"></el-table-column>
        <el-table-column prop="cancelRatio" label="比例" align="center">
          <template slot-scope="scope">
            <div>{{percent(scope.row.cancelRatio)}}</div>
          </template>
        </el-table-column>
      </el-table-column>
    </el-table>
  </div>
</template>
<script>
import { getTask, exportTable } from "@/api/composite/task.js";
import { GetRegionPower, getTagList } from "@/api/composite/source";
import { analysis } from "@/config";
export default {
  data() {
    return {
      queryForm: {
        townCodes: null,
        listType: 0,
      },
      townCodeOptions: [],
      tableData: [],
      disabled: false,
      checked: true,
      selectedArray: [],
      cities: [],
      fenju: true,
      loading: false,
    };
  },
  created() {
    this.getTown();
    this.getSourceTag();
  },
  watch: {
    townCodeOptions: {
      handler(v) {
        if (v.length === 1) {
          this.$set(this.queryForm, "townCodes", [v[0].regionCode]);
          this.fenju = false;
          this.disabled = true;
        } else {
          this.townCodeOptions.map((item) => {
            this.selectedArray.push(item.regionCode);
          });
          this.queryForm.townCodes = this.selectedArray;
          this.fenju = true;
          this.disabled = false;
        }
        this.handleQuery();
      },
    },
  },
  methods: {
    getTown() {
      GetRegionPower().then((res) => {
        let oRes = res.data;
        this.townCodeOptions = oRes;
      });
    },
    onChange(v) {
      if (v.length === this.townCodeOptions.length) {
        this.checked = true;
      } else {
        this.checked = false;
      }
    },
    selectAll() {
      this.selectedArray = [];
      this.queryForm.townCodes = [];
      if (this.checked) {
        this.townCodeOptions.map((item) => {
          this.selectedArray.push(item.regionCode);
        });
        this.queryForm.townCodes = this.selectedArray;
      } else {
        this.selectedArray = [];
        this.queryForm.townCodes = [];
      }
    },
    handleQuery() {
      this.loading = true;
      let oVal = {};
      if (this.queryForm.townCodes.join(",") === "-1") {
        oVal.townCodes = this.allTownCode.join(",");
      } else {
        oVal.townCodes = this.queryForm.townCodes.join(",");
      }
      oVal.listType = this.queryForm.listType;
      oVal.tagHeadId = this.queryForm.tagHeadId;
      // let townCodes = this.queryForm.townCodes.join(",");
      if (this.queryForm.townCodes) {
        getTask(oVal).then((res) => {
          if (res.code === 200) {
            this.tableData = res.data;
          }
          this.loading = false;
        });
      } else {
        this.msgSuccess("请选择查看镇街");
        this.loading = false;
      }
    },
    exportTables() {
      let oVal = {};
      oVal.townCodes = this.queryForm.townCodes.join(",");
      oVal.listType = this.queryForm.listType;
      if (this.queryForm.tagHeadId) oVal.tagHeadId = this.queryForm.tagHeadId;
      this.$confirm("是否导出数据?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(function () {
          return exportTable(oVal);
        })
        .then((response) => {
          this.download(response.msg);
        })
        .catch(function () {});
    },
    // 监控任务
    getSourceTag() {
      getTagList().then((res) => {
        if (res.code === 200) {
          let oArr = res.rows;
          this.cities = analysis.filter((p) =>
            oArr.some((s) => s.tagHeadId === p.id)
          );

          let checkArr = [];
          this.cities.forEach((v, i, a) => {
            checkArr.push(v.id);
          });
          this.platArr = checkArr;
          this.queryForm.tagHeadIds = this.platArr;
        }
      });
    },
    //表格样式
    tableRowClassName({ row, rowIndex }) {
      if (row.townName === " 合计") {
        return "warning-row";
      }
      return "";
    },
    percent(v) {
      let oV = v.split(".");
      if (oV.indexOf("00%") > -1) {
        return oV[0] + "%";
      } else {
        return v;
      }
    },
  },
};
</script>
<style lang="scss">
.allSelect {
  text-align: right;
  width: 100%;
  padding-right: 10px;
}
.el-table .warning-row {
  background: oldlace;
  font-weight: bold;
}
</style>
