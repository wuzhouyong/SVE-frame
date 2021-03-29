<template>
  <div class="task-wk">
    <el-form :model="queryForm" ref="queryForm" :inline="true" class="task-serchForm">
      <el-form-item label="企业名称" prop="fullName">
        <el-input
          v-model="queryForm.fullName"
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
      <el-form-item prop="listType">
        <el-radio-group v-model="queryForm.listType">
          <el-radio-button :label="-1">全部</el-radio-button>
          <el-radio-button :label="0">考核名单</el-radio-button>
          <el-radio-button :label="1">非考核名单</el-radio-button>
        </el-radio-group>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        <el-button icon="el-icon-upload2" size="mini" type="warning" @click="exportTables">导出表格</el-button>
      </el-form-item>
      <el-form-item>
        <div class="instruWk">
          <!--          <p>-->
          <!--            <i class="show-0"></i>-->
          <!--            <span>取消安装</span>-->
          <!--          </p>-->
          <p>
            <i class="show-1"></i>
            <span>未开展</span>
          </p>
          <p>
            <i class="show-2"></i>
            <span>已施工</span>
          </p>
          <p>
            <i class="el-icon-success" style="color:#cc9900;font-size:16px;"></i>
            <span>已接入</span>
          </p>
          <p>
            <i class="el-icon-success" style="color:#89da06;font-size:16px;"></i>
            <span>已验收</span>
          </p>
        </div>
      </el-form-item>
    </el-form>
    <el-table
      :data="data"
      height="100%"
      border
      :row-class-name="tableRowClassName"
      v-loading="loading"
    >
      <el-table-column type="index" label="序号" align="center" width="50" fixed></el-table-column>
      <el-table-column prop="fullName" label="企业名称" width="260" fixed></el-table-column>
      <el-table-column prop="townName" label="所属镇街"></el-table-column>
      <el-table-column prop="industryName" label="所属行业" width="150" show-overflow-tooltip></el-table-column>
      <el-table-column prop="sourceStatus" label="经营状态" :formatter="cScaleType"></el-table-column>
      <el-table-column prop="envContactName" label="联系人"></el-table-column>
      <el-table-column prop="envContactPhone" label="联系电话" width="110"></el-table-column>
      <el-table-column
        v-for="(i,index) in platformList"
        :key="index"
        :prop="i.platProp"
        :label="i.platName"
        align="center"
      >
        <template slot-scope="scope">
          <div
            v-if="scope.row.fullName===' 合计'"
            style="font-weight: bold;"
          >{{ scope.row[i.platProp] }}
          </div>
          <div v-else>
            <i v-if="scope.row[i.platProp]===1" class="show-0" title="取消安装"></i>
            <i v-if="scope.row[i.platProp]===2" class="show-1" title="未开展"></i>
            <i v-else-if="scope.row[i.platProp]===3" class="show-2" title="已施工"></i>
            <i
              v-else-if="new Set([4,5,6]).has(scope.row[i.platProp])"
              class="el-icon-success"
              style="color:#cc9900;font-size:16px;"
              title="已接入"
            ></i>
            <i
              v-else-if="scope.row[i.platProp]===8"
              class="el-icon-success"
              style="color:#89da06;font-size:16px;"
              title="已验收"
            ></i>
          </div>
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
</template>

<script>
import utils from "@/utils/ruoyi";
import {GetRegionPower} from "@/api/composite/source";
import {sourceTask, exportTable} from "@/api/monitor_task";
import {mapState} from "vuex";

const platformList = [
  {platProp: "shuizhongdian", platName: "涉水重点排污单位"},
  {platProp: "qizhongdian", platName: "涉气重点排污单位"},
  {platProp: "paishui", platName: "重点排水户末端监控"},
  {platProp: "guocheng", platName: "四大流域过程监控"},
  {platProp: "lsprod", platName: "零散废水产生单位监控"},
  {platProp: "lsprocess", platName: "零散废水处理单位监控"},
  {platProp: "jinshu", platName: "金属表面处理行业过程监控"},
  {platProp: "zaozhi", platName: "造纸行业过程监控"},
  {platProp: "yitihua", platName: "一体化污水处理设施在线监控"},
  {platProp: "voc", platName: "市级以上VOCs重点企业过程监控"},
  {platProp: "qixiu", platName: "汽修行业过程监控"},
  {platProp: "youyan", platName: "餐饮行业过程监控"},
  {platProp: "jiayou", platName: "加油站油气回收装置过程监控"},
  {platProp: 'xinfang', platName: '重点信访企业'}
];
export default {
  data() {
    return {
      data: [],
      queryForm: {townCode: undefined, listType: 0},
      page: {
        pageNum: 1,
        pageSize: 30,
      },
      loading: false,
      total: 0,
      townCodeOptions: [],
      platformList,
      sourceStatusOptions: [],
    };
  },
  computed: {
    ...mapState("label", ["searchData"])
  },
  watch: {
    searchData: {
      handler() {
        this.handleQuery();
      },
      immediate: true
    },
  },
  created() {
    GetRegionPower().then((res) => {
      this.townCodeOptions = res.data;
    });
    if (this.$route.query.townCode) {
      this.queryForm.townCode = this.$route.query.townCode;
    }
    if (this.$route.query.listType) {
      this.queryForm.listType = this.$route.query.listType;
    }
    this.getDicts("source_status").then(
      (res) => (this.sourceStatusOptions = res.data)
    );
    this.getList(this.queryForm);
  },
  methods: {
    getList(v = {}) {
      this.loading = true;
      // v = utils.filterData(v);
      let oVal = Object.assign({labelIds: this.searchData}, v);
      sourceTask(oVal, this.urlParameter(this.page)).then((res) => {
        if (res.code === 200) {
          this.data = res.rows;
          this.total = res.total;
          this.loading = false;
        }
      });
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.page.pageNum = 1;
      this.getList(this.queryForm);
    },
    //重置表单
    resetQuery() {
      this.resetForm("queryForm");
      this.queryForm.listType = 0;
      this.handleQuery();
    },
    //经营状态
    cScaleType(row) {
      return this.selectDictLabel(this.sourceStatusOptions, row.sourceStatus);
    },
    //表格样式
    tableRowClassName({row, rowIndex}) {
      if (row.fullName === " 合计") {
        return "warning-row";
      }
      return "";
    },
    exportTables() {
      this.$confirm("是否导出数据?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          return exportTable(this.queryForm);
        })
        .then((response) => {
          this.download(response.msg);
        })
        .catch(function () {
        });
    },
  },
};
</script>

<style lang="scss">
.show-0 {
  width: 14px;
  height: 14px;
  border-radius: 50%;
  background: #999;
  display: inline-block;
}

.show-1 {
  width: 14px;
  height: 14px;
  border-radius: 50%;
  background: #ff0000;
  display: inline-block;
}

.show-2 {
  width: 14px;
  height: 14px;
  border-radius: 50%;
  background: #cc9900;
  display: inline-block;
}

.instruWk {
  display: flex;
  align-items: center;

  p {
    margin: 0;
    margin-left: 20px;
    display: flex;
    align-items: center;

    span {
      font-size: 13px;
      margin-left: 5px;
    }
  }
}

.el-table .warning-row {
  background: oldlace;
  font-weight: bold;
}
</style>
