<template>
  <div class="task-wk">
    <el-tabs v-model="activeName" @tab-click="handleClick">
      <el-tab-pane :label="i.name" :name="i.path" v-for="i in cities" :key="i.path"></el-tab-pane>
    </el-tabs>
    <!-- 搜索表单 -->
    <el-form :model="queryForm" ref="queryForm" :inline="true" class="outwater-serchForm">
      <el-form-item label="污染源" prop="sourceName">
        <el-input
          v-model="queryForm.sourceName"
          placeholder="请输入污染源"
          clearable
          size="mini"
          style="width: 180px"
        />
      </el-form-item>
      <el-form-item label="镇街" prop="townCode">
        <el-select
          v-model="queryForm.townCode"
          placeholder="镇街"
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
      <el-form-item label="品牌" prop="acquisitionBrand">
        <el-input
          v-model="queryForm.acquisitionBrand"
          placeholder="品牌"
          size="mini"
          style="width: 180px"
        />
      </el-form-item>
      <el-form-item label="施工单位" prop="constructionUnit">
        <el-input
          v-model="queryForm.constructionUnit"
          placeholder="施工单位"
          size="mini"
          style="width: 180px"
        />
      </el-form-item>
      <el-form-item label="选择日期" prop="startTime">
        <el-date-picker
          v-model="queryForm.startTime"
          style="width:280px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="处理状态" prop="procState">
        <el-select
          v-model="queryForm.procState"
          placeholder="请选择处理状态"
          style="width:100%"
        >
          <el-option
            :label="i.dictLabel"
            :value="i.dictValue"
            v-for="i in procStates"
            :key="i.dictValue"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="异常级别" prop="warnLevel">
        <el-select v-model="queryForm.warnLevel" clearable placeholder="请选择异常级别" style="width:100%">
          <el-option
            :label="i.dictLabel"
            :value="i.dictValue"
            v-for="i in warnLevels"
            :key="i.dictValue"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="异常类型" prop="warnType">
        <el-select v-model="queryForm.warnType" clearable placeholder="请选择异常类型" style="width:100%">
          <el-option
            :label="i.dictLabel"
            :value="i.dictValue"
            v-for="i in warnTypes"
            :key="i.dictValue"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        <el-button type="warning" icon="el-icon-download" size="mini" @click="exportTable">导出表格
        </el-button>
      </el-form-item>
    </el-form>
    <!-- 操作按钮 -->
    <el-row :gutter="10" class="mb8"></el-row>
    <!-- 表格 -->
    <el-table
      v-loading="loading"
      element-loading-text="数据查询中,请稍后!"
      :data="tableData"
      style="width: 100%;flex:1"
      ref="oTable"
      height="100%"
      border
    >
      <!-- <el-table-column type="selection" width="55" align="center"></el-table-column> -->
      <el-table-column prop="sourceName" label="污染源" width="220" show-overflow-tooltip></el-table-column>
      <el-table-column prop="townName" label="镇街" width="100"></el-table-column>
      <el-table-column prop="acquisitionBrand" label="品牌" width="100" show-overflow-tooltip></el-table-column>
      <el-table-column prop="constructionUnit" label="施工单位" width="180" show-overflow-tooltip></el-table-column>
      <el-table-column prop="warnLevelName" label="异常级别" width="100"></el-table-column>
      <el-table-column prop="procStateName" label="处理状态" width="100"></el-table-column>
      <el-table-column label="异常时间" width="170">
        <template slot-scope="scope">{{ scope.row.startDate }} ~ {{ scope.row.endDate }}</template>
      </el-table-column>
      <el-table-column prop="warnTypeName" label="异常类型" width="160" show-overflow-tooltip></el-table-column>
      <el-table-column prop="warnDesc" label="异常描述" show-overflow-tooltip></el-table-column>
      <el-table-column label="操作" width="100" align="center">
        <template slot-scope="scope">
          <el-button type="text" slot="reference" @click="getDetails(scope.row.warnId)">处理详情</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="page.pageNum"
      :limit.sync="page.pageSize"
      @pagination="handleQuery()"
    />
    <el-dialog title="处理详情" :visible.sync="visible" width="30%" custom-class="warn_details_dialog">
      <div class="warn_details">
        <ul v-for="i in details" :key="i.id">
          <li>
            <p>处理状态：</p>
            <span>{{ oArr[i.procState] }}</span>
          </li>
          <li>
            <p>处理人：</p>
            <span>{{ i.procUserName || "-" }}</span>
          </li>
          <li>
            <p>处理时间：</p>
            <span>{{ i.procDate.substr(0, 10) || "-" }}</span>
          </li>
          <li>
            <p>处理情况说明：</p>
            <span>{{ i.procMemo || "-" }}</span>
          </li>
        </ul>
        <el-table :data="fileList" stripe style="width: 100%" height="160px">
          <el-table-column prop="showName" label="附件名" show-overflow-tooltip></el-table-column>
          <el-table-column prop="attachType" label="附件类型" width="120">
            <template slot-scope="scope">
              <div>
                <span v-if="scope.row.attachType==='duty_warn_type'">值守核实</span>
                <span v-else-if="scope.row.attachType==='substation_warn_type'">分局处理</span>
                <span v-else-if="scope.row.attachType==='factory'">企业处理</span>
              </div>
            </template>
          </el-table-column>
          <el-table-column prop="address" label="操作" width="100" align="center">
            <template slot-scope="scope">
              <el-link
                type="primary"
                :underline="false"
                target="_blank"
                :href="getlink(scope.row.Id)"
                style="font-size:12px;"
              >下载
              </el-link>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import {warnList, warnProclist, exportWarn} from "@/api/warn_handle";
import {platform_list, contrast} from "@/config";
import {GetRegionPower, getTagList} from "@/api/composite/source";
import {fileList} from "@/api/upload";
import {mapState} from "vuex";

const oArr = [
  "企业已处理",
  "值守退回",
  "逾期未处理",
  "值守已处理",
  "分局退回",
  "分局已处理",
  "已完成"
];
export default {
  data () {
    return {
      oArr,
      // isExport: false,
      // 查询参数
      page: {
        pageNum: 1,
        pageSize: 30
      },
      dateRange: {
        startDate: null,
        endDate: null
      },
      total: 0,
      //查询表单
      queryForm: {
        sourceName: null,
        startTime: null,
        procState: null,
        warnLevel: null,
        warnType: null,
        townCode: null,
        constructionUnit: null,
        acquisitionBrand: null
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
      procStates: [],
      warnLevels: [],
      warnTypes: [],
      cities: [],
      activeName: "",
      details: [],
      visible: false,
      fileList: [],
      townCodeOptions: []
    };
  },
  created () {
    this.getDicts("ty_proc_state").then((response) => {
      this.procStates = response.data;
    });
    this.getDicts("ty_warn_level").then((response) => {
      this.warnLevels = response.data;
    });
    GetRegionPower().then((res) => {
      this.townCodeOptions = res.data;
    });
    this.getSourceTagList();
  },
  computed: {
    ...mapState("label", ["searchData"])
  },
  watch: {
    // activeName: {
    //   handler (v) {
    //     if (v === "outwater") {
    //       this.isExport = true;
    //     } else {
    //       this.isExport = false;
    //     }
    //   },
    //   immediate: true
    // },
    searchData: {
      handler () {
        this.getSourceTagList();
      },
      immediate: true
    },
  },
  methods: {
    getfileList (v) {
      let ref = {
        refId: v,
        refType: contrast[this.activeName] + "_warn_file"
      };
      fileList(ref).then((res) => {
        if (res.code === 200) {
          this.fileList = res.data;
        }
      });
    },
    getDetails (v) {
      this.visible = true;
      this.getfileList(v);
      warnProclist(v, this.activeName).then((res) => {
        if (res.code === 200) {
          this.details = res.data;
        }
      });
    },
    /** 获取列表 **/
    getList (v = {}) {
      this.loading = true;
      let oVal = Object.assign({labelIds: this.searchData}, v);
      warnList(oVal, this.activeName, this.urlParameter(this.page)).then((res) => {
        if (res.code === 200) {
          this.tableData = res.rows;
          this.total = res.total;
        }
        this.loading = false;
      });
    },
    /** 搜索按钮操作 */
    handleQuery () {
      let {startTime} = this.queryForm;
      let query = Object.assign({}, this.queryForm, this.dateRange);
      if (startTime) {
        let oVal = {
          startDate: startTime[0],
          endDate: startTime[1]
        };
        query = Object.assign({}, query, oVal);
      }
      delete query.startTime;
      let pam = {};
      for (let i in query) {
        if (query[i]) {
          pam[i] = query[i];
        }
      }
      this.getList(pam);
    },
    /** 重置按钮操作 */
    resetQuery () {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange (selection) {
      this.selectRows = selection;
      this.ids = selection.map((item) => item.warnId);
      this.single = selection.length != 1;
      this.multiple = !selection.length;
    },
    handleClick (tab) {
      this.activeName = tab.name;
      this.queryForm = {
        sourceName: null,
        startTime: null,
        procState: null,
        warnLevel: null,
        warnType: null,
        townCode: null,
        constructionUnit: null,
        acquisitionBrand: null
      };
      this.tableData = [];
      this.page = {
        pageNum: 1,
        pageSize: 30
      };
      this.total = 0;
      this.getDicts(contrast[this.activeName] + "_warn_type").then(
        (response) => {
          this.warnTypes = response.data;
        }
      );
      this.handleQuery();
    },
    //平台数据
    getSourceTagList () {
      getTagList().then((res) => {
        if (res.code === 200) {
          let oArr = res.rows;
          this.cities = platform_list.filter((p) =>
            oArr.some((s) => s.tagHeadId === p.id)
          );
          this.activeName = this.cities[0].path;
          this.handleQuery();
          this.getDicts(contrast[this.activeName] + "_warn_type").then(
            (response) => {
              this.warnTypes = response.data;
            }
          );
        }
      });
    },
    getlink (id) {
      return `${process.env.VUE_APP_BASE_API}/com/getfile/${id}`;
    },
    exportTable () {
      let _that = this;
      this.$confirm("是否导出数据?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(function () {
          let {startTime} = _that.queryForm;
          let query = Object.assign({}, _that.queryForm, _that.dateRange);
          if (startTime) {
            let oVal = {
              startDate: startTime[0],
              endDate: startTime[1]
            };
            query = Object.assign({}, query, oVal);
          }
          delete query.startTime;
          return exportWarn(_that.activeName, query);
        })
        .then((response) => {
          this.download(response.msg);
        })
        .catch(function () {
        });
    }
  }
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
</style>
