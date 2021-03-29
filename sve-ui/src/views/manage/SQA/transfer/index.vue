<template>
  <div class="task-wk">
    <el-tabs v-model="activeName" @tab-click="handleClick">
      <el-tab-pane :label="i.name" :name="i.path" v-for="i in cities" :key="i.path"></el-tab-pane>
    </el-tabs>
    <div style="display:flex">
      <el-radio-group
        v-model="isDay"
        size="mini"
        @change="isChangeSearch"
        style="margin-right:10px;"
      >
        <el-radio-button label="1">日传输率</el-radio-button>
        <el-radio-button label="2">月传输率</el-radio-button>
      </el-radio-group>
      <!-- 搜索表单 -->
      <el-form :model="queryForm" ref="queryForm" :inline="true" class="outwater-serchForm">
        <el-form-item label="污染源">
          <SelectSource
            v-model="queryForm.sourceId"
            :data="sourceList"
            :isPlatName="activeName"
            placeholder="请输入污染源"
            style="width:200px"
          ></SelectSource>
        </el-form-item>
        <el-form-item label="施工单位">
          <el-input v-model="queryForm.shortName" placeholder="请输入施工单位"></el-input>
        </el-form-item>
        <el-form-item label="品牌">
          <el-input v-model="queryForm.brandName" placeholder="请输入品牌"></el-input>
        </el-form-item>
        <el-form-item label="选择日期" v-if="isDay==='1'">
          <el-date-picker
            v-model="queryForm.oTime"
            style="width:280px"
            value-format="yyyy-MM-dd"
            type="daterange"
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
            :clearable="false"
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="选择月份" v-else>
          <el-date-picker
            v-model="queryForm.oMonth"
            style="width:280px"
            type="monthrange"
            value-format="yyyy-MM"
            range-separator="至"
            start-placeholder="开始月份"
            end-placeholder="结束月份"
          ></el-date-picker>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        </el-form-item>
      </el-form>
    </div>

    <!-- 表格 -->
    <el-table
      v-loading="loading"
      :data="tableData"
      style="width: 100%;flex:1"
      ref="oTable"
      row-key="uuid"
      height="100%"
      border
      :tree-props="{children: 'children'}"
      @row-click="(row)=>$refs.oTable.toggleRowExpansion(row)"
      :cell-style="yellowBg"
    >
      <el-table-column prop="checkpointName" label="企业名称" show-overflow-tooltip></el-table-column>
      <el-table-column prop="dataDate" label="日期" width="120">
        <template
          slot-scope="scope"
        >{{
            isDay === '1' ? scope.row.dataDate ? scope.row.dataDate.substr(0, 10) : '-' : scope.row.dataDate ? scope.row.dataDate.substr(0, 7) : '-'
          }}
        </template>
      </el-table-column>
      <el-table-column prop="shortName" label="施工单位"></el-table-column>
      <el-table-column prop="real" label="实时数据%" v-if="activeName!=='outwater'">
        <template slot-scope="scope">
          {{ (scope.row.real >= 0 && scope.row.real !== null) ? scope.row.real : '-' }}
        </template>
      </el-table-column>
      <el-table-column prop="minute" label="分钟数据%">
        <template slot-scope="scope">
          {{ (scope.row.minute >= 0 && scope.row.minute !== null) ? scope.row.minute : '-' }}
        </template>
      </el-table-column>
      <el-table-column prop="hour" label="小时数据%">
        <template slot-scope="scope">
          {{ (scope.row.hour >= 0 && scope.row.hour !== null) ? scope.row.hour : '-' }}
        </template>
      </el-table-column>
      <el-table-column prop="day" label="日数据%">
        <template slot-scope="scope">
          {{ (scope.row.day >= 0 && scope.row.day !== null) ? scope.row.day : '-' }}
        </template>
      </el-table-column>
      <el-table-column prop="brandName" label="品牌" width="100" show-overflow-tooltip></el-table-column>
    </el-table>
    <!-- 分页 -->
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="page.pageNum"
      :limit.sync="page.pageSize"
      @pagination="handleQuery()"
    />
  </div>
</template>
<script>
import {getTransfer, getTransferMonth} from "@/api/composite/transfer";
import {platform_list, contrast} from "@/config";
import {getTagList} from "@/api/composite/source";
import {sourceList} from "@/api/search";
import SelectSource from "@/components/SelectSource";
import {mapState} from "vuex";

const oArr = [
  "待处理",
  "退回处理",
  "逾期未处理",
  "企业已处理",
  "退回核实",
  "值守已核实",
  "已完成",
];
export default {
  components: {SelectSource},
  data() {
    return {
      oArr,
      sourceList,
      // 查询参数
      page: {
        pageNum: 1,
        pageSize: 30,
      },
      total: 0,
      //查询表单
      queryForm: {
        oTime: null,
        oMonth: null,
      },
      // 是否显示弹出层
      open: false,
      // 选中数组
      ids: [],
      //选中详情
      selectRows: [],
      sSelectRows: {},
      // 弹出层标题
      title: "",
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      //表格数据
      loading: false,
      tableData: [],
      cities: [],
      activeName: "",
      visible: false,
      isDay: "1",
    };
  },
  computed: {
    ...mapState("label", ["searchData"])
  },
  watch: {
    searchData: {
      handler() {
        this.getSourceTagList();
      },
      immediate: true
    },
  },
  created() {
    this.getSourceTagList();
    let oDate = this.parseTime(new Date()).substr(0, 10);
    this.queryForm.oTime = [this.fun_date(-2), oDate];
  },
  methods: {
    /** 获取列表 **/
    getList(v = {}) {
      this.loading = true;
      let oVal = Object.assign({labelIds: this.searchData}, v);
      getTransfer(oVal, this.activeName, this.urlParameter(this.page)).then(
        (res) => {
          if (res.code === 200) {
            this.tableData = res.rows;
            this.total = res.total;
          }
          this.loading = false;
        }
      );
    },
    getMonth(v = {}) {
      this.loading = true;
      let oVal = Object.assign({labelIds: this.searchData}, v);
      getTransferMonth(oVal, this.activeName, this.urlParameter(this.page)).then(
        (res) => {
          if (res.code === 200) {
            this.tableData = res.rows;
            this.total = res.total;
          }
          this.loading = false;
        }
      );
    },
    /** 搜索按钮操作 */
    handleQuery() {
      let oVal = {};
      let {oTime, sourceId, shortName, brandName, oMonth} = this.queryForm;
      if (this.isDay === "1") {
        if (oTime) {
          oVal.startDate = oTime[0];
          oVal.endDate = oTime[1];
        }
      } else {
        if (oMonth) {
          oVal.startDate = oMonth[0];
          oVal.endDate = oMonth[1];
        }
      }
      if (sourceId) oVal.sourceId = sourceId;
      if (shortName) oVal.shortName = shortName;
      if (brandName) oVal.brandName = brandName;
      this.isDay === "1" ? this.getList(oVal) : this.getMonth(oVal);
    },
    /** 重置按钮操作 */
    resetQuery() {
      let oDate = this.parseTime(new Date()).substr(0, 10);
      this.queryForm = {
        sourceId: null,
        oTime: [this.fun_date(-2), oDate],
      };
      this.resetMonth();
      this.handleQuery();
    },
    resetMonth() {
      const end = new Date();
      const start = new Date();
      start.setMonth(start.getMonth() - 1);
      this.queryForm.oMonth = [this.tran(start), this.tran(end)];
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.selectRows = selection;
      this.ids = selection.map((item) => item.videoId);
      this.single = selection.length != 1;
      this.multiple = !selection.length;
    },
    handleClick(tab, event) {
      this.activeName = tab.name;
      let oDate = this.parseTime(new Date()).substr(0, 10);
      this.queryForm = {
        sourceId: null,
        oTime: [this.fun_date(-2), oDate],
      };
      this.resetMonth();
      this.tableData = [];
      this.handleQuery();
    },
    //平台数据
    getSourceTagList() {
      getTagList().then((res) => {
        if (res.code === 200) {
          let oArr = res.rows;
          this.cities = platform_list.filter((p) =>
            oArr.some((s) => s.tagHeadId === p.id && s.tagHeadId !== '1018')
          );
          this.activeName = this.cities[0].path;
          this.handleQuery();
        }
      });
    },
    //数据查询类型切换
    isChangeSearch(v) {
      this.isDay = v;
      if (this.isDay === "1") {
        this.queryForm.oMonth = null;
        let oDate = this.parseTime(new Date()).substr(0, 10);
        this.queryForm.oTime = [this.fun_date(-2), oDate];
      } else {
        this.resetMonth();
      }
      this.handleQuery();
    },
    tran(today) {
      return today.toISOString().substring(0, 7);
    },
    fun_date(num) {
      let date1 = new Date();
      let time1 =
        date1.getFullYear() +
        "-" +
        (date1.getMonth() + 1) +
        "-" +
        date1.getDate();
      let date2 = new Date(date1);
      date2.setDate(date1.getDate() + num);
      let oMonth = date2.getMonth() + 1;
      if (oMonth < 10) {
        oMonth = "0" + oMonth;
      }
      let time2 = date2.getFullYear() + "-" + oMonth + "-" + date2.getDate();
      return time2;
    },
    yellowBg({row, columnIndex}) {
      if (columnIndex === 0) {
        if (row.children && row.children.length > 0) {
          return {
            background: "oldlace",
          };
        }
      }
      if (this.activeName === "outwater") {
        if (
          (columnIndex === 3 && row.minute !== null && row.minute >= 0 && row.minute < 80) ||
          (columnIndex === 4 && row.hour !== null && row.hour >= 0 && row.hour < 80) ||
          (columnIndex === 5 && row.day !== null && row.day >= 0 && row.day < 80)
        ) {
          return {
            background: "rgba(241,74,74,0.5)",
            color: "#fff",
          };
        }
      } else {
        if (
          (columnIndex === 3 && row.real !== null && row.real >= 0 && row.real < 50) ||
          (columnIndex === 4 && row.minute !== null && row.minute >= 0 && row.minute < 90) ||
          (columnIndex === 5 && row.hour !== null && row.hour >= 0 && row.hour < 90) ||
          (columnIndex === 6 && row.day !== null && row.day >= 0 && row.day < 90)
        ) {
          return {
            background: "rgba(241,74,74,0.5)",
            color: "#fff",
          };
        } else if (
          (columnIndex === 3 && !row.real) ||
          (columnIndex === 4 && !row.minute) ||
          (columnIndex === 5 && !row.hour) ||
          (columnIndex === 6 && !row.day)) {
          return {
            background: "#fff",
            color: "#000",
          };
        }
      }
    },
  },
};
</script>
<style lang="scss">
.warn_details {
  width: 100%;
  height: 100%;
  overflow-y: auto;

  ul {
    width: 100%;
    padding-left: 0;
    border-bottom: 1px solid #e6ebf5;
    padding-bottom: 10px;

    li {
      list-style-type: none;
      display: flex;
      margin-bottom: 5px;

      p {
        margin: 0;
        width: 100px;
        text-align: right;
      }

      span {
        margin: 0;
        flex: 1;
        padding-left: 5px;
      }
    }
  }
}

.warn_details_dialog {
  height: calc(100% - 12vh);
  overflow: hidden;
  display: flex;
  flex-direction: column;

  .el-dialog__body {
    overflow-y: auto;
    flex: 1;
    padding: 10px;
  }
}

.BelowEighty {
  color: red;
}
</style>
