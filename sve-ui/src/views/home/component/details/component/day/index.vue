<template>
  <div class="mapindex-checkpoint">
    <!-- 搜索表单 -->
    <el-form :model="queryForm" ref="queryForm" :inline="true" class="home-serchForm">
      <el-form-item label="检测井" prop="checkPointId" v-if="platFormPath==='outwater'">
        <el-select v-model="queryForm.checkPointId" placeholder="请选择检测井" size="mini">
          <el-option :label="i.name" :value="i.id" v-for="i in pointList" :key="i.id"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item :label="platFormPath==='jiayou'?'加油枪':'排放口'" prop="outletId"
                    v-if="new Set(['youyan','qixiu','jiayou']).has(platFormPath)">
        <el-select v-model="queryForm.outletId" size="mini">
          <el-option
            :label="i.facilityName"
            :value="i.facilityId"
            v-for="i in outletData"
            :key="i.facilityId"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="选择日期" prop="dayTime">
        <el-date-picker
          v-model="queryForm.dayTime"
          style="width:280px"
          :picker-options="pickerOptions"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button
          type="success"
          icon="el-icon-refresh"
          size="mini"
          @click="isCut=!isCut"
        >{{ isCut ? "切换图表" : "切换表格" }}
        </el-button>
      </el-form-item>
    </el-form>
    <template v-if="isCut">
      <transition name="el-fade-in-linear">
        <el-table
          v-loading="loading"
          :data="tableData"
          style="width: 100%;flex:1;"
          ref="oTable"
          height="100%"
          border
        >
          <el-table-column
            :prop="i.dataIndex"
            :label="i.title"
            v-for="i in cols"
            :key="i.dataIndex"
            :width="i.dataIndex==='date'?'130':null"
          >
            <template v-if="i.children">
              <el-table-column
                :prop="c.dataIndex"
                :label="c.title"
                v-for="c in i.children"
                :key="c.dataIndex"
                :width="c.minWidth ? null : (c.width || '150')"
                :min-width="c.minWidth"
              ></el-table-column>
            </template>
          </el-table-column>
        </el-table>
      </transition>
    </template>
    <template v-else>
      <transition name="el-fade-in-linear">
        <ve-line
          :data="chartData"
          :settings="chartsSetting.settings"
          :legend="chartsSetting.legend"
          :extend="chartsSetting.extend"
          height="100%"
        ></ve-line>
      </transition>
    </template>
  </div>
</template>
<script>
import {pointSelectList} from "@/api/outwater/index";
import {searchDay} from "@/api/home";
import VeLine from "v-charts/lib/line.common";
import {outletList} from "@/api/youyan";
import {outletList as qxoutletList} from "@/api/qixiu";
import {jyOutletSelect} from "@/api/jiayou";

export default {
  props: {
    oId: {
      type: String
    },
    platFormPath: {
      type: String
    },
    aceName: {
      type: String
    }
  },
  components: {VeLine},
  data () {
    this.chartsSetting = {
      settings: {
        labelMap: {}
      },
      legend: {
        show: false,
        top: 10,
        textStyle: {
          color: "#ffffff",
          fontSize: 11
        }
      },
      extend: {
        "grid.bottom": "20px",
        "grid.top": "150px",
        yAxis: {
          splitNumber: 5,
          axisTick: {
            alignWithLabel: 2
          },
          axisLabel: {
            show: true,
            textStyle: {
              color: "#fff",
              fontSize: 12
            }
          }
        },
        xAxis: {
          axisLabel: {
            show: true,
            // rotate: 15, //倾斜
            textStyle: {
              color: "#fff",
              fontSize: 12
            }
          }
        }
      }
    };
    return {
      isCut: true,
      queryForm: {
        sourceId: undefined,
        dayTime: undefined,
        checkPointId: undefined
      },
      pickerOptions: {
        disabledDate: this.disabledDate
      },
      pointList: [],
      cols: [],
      tableData: [],
      loading: false,
      chartData: {
        columns: [],
        rows: []
      },
      outletData: [],
      // 分页
      page: {
        pageNum: 1,
        pageSize: 50
      }
    };
  },
  computed: {
    multipleProp () {
      const {oId, platFormPath, aceName} = this;
      return {
        oId,
        platFormPath,
        aceName
      };
    }
  },
  watch: {
    multipleProp: {
      handler (v) {
        if (v && v.aceName === "7") {
          this.queryForm.sourceId = v.oId;
          let n = v.oId;
          this.reset();
          if (v.platFormPath === "youyan") {
            this.getoutletList(n);
          } else if (v.platFormPath === "outwater") {
            this.getPointList(n);
          } else if (v.platFormPath === "jiayou") {
            this.getJYOutLet(n);
          }
          if (v.aceName === "7") {
            let oDate = this.parseTime(new Date()).substr(0, 10);
            this.queryForm.dayTime = [this.fun_date(-7), oDate];
          }
        }
      },
      immediate: true,
      deep: true
    }
  },
  methods: {
    getJYOutLet (v) {
      jyOutletSelect(v).then(res => {
        if (res.code === 200) {
          let oArr = res.data;
          oArr.forEach((i) => {
            i.facilityId = i.outletId;
            i.facilityName = i.outletName;
          });
          this.outletData = oArr;
        }
      });
    },
    //重置
    reset () {
      this.queryForm.startDate = undefined;
      this.queryForm.checkPointId = undefined;
      this.tableData = [];
      this.cols = [];
      this.chartData.rows = [];
      this.chartData.columns = [];
      this.isCut = true;
    },
    getoutletList (v) {
      outletList(v).then((res) => {
        this.outletData = res.data;
      });
    },
    //获取列表
    getList (v = {}) {
      this.loading = true;
      searchDay(v, this.platFormPath).then((res) => {
        if (res.code === 200) {
          let oCols = res.data.cols;
          oCols = this.funCol(oCols);
          this.chartsSetting.settings.labelMap = oCols
            .filter((p) => p.dataIndex !== "date")
            .reduce((obj, cur) => {
              obj[cur.dataIndex] = cur.title;
              return obj;
            }, {});
          let oArr = [];
          oCols.forEach((i) => {
            oArr.push(i.dataIndex);
          });
          this.cols = res.data.cols;
          this.chartData.columns = oArr;
          this.tableData = res.data.data;
          this.chartData.rows = res.data.data;
        }
        this.loading = false;
      });
    },
    funCol (cols) {
      var res = [];
      cols.forEach((a) => {
        if (a.dataIndex) {
          res = res.concat(a);
        }
        if (a.children && a.children.length > 0) {
          a.children.map((value) => {
            res = res.concat(value);
          });
        }
      });
      return res;
    },
    //获取检测井
    getPointList (v) {
      let oVal = {
        sourceId: v
      };
      pointSelectList(oVal).then((res) => {
        this.pointList = res.rows;
        if (this.pointList.length > 0) {
          this.queryForm.checkPointId = this.pointList[0].id;
        }
      });
    },
    getqxoutletList (v) {
      let oVal = {
        sourceId: v
      };
      qxoutletList(oVal, this.urlParameter(this.page)).then((res) => {
        this.outletData = res.rows;
      });
    },
    handleQuery () {
      this.queryForm.startDate = this.queryForm.dayTime[0];
      this.queryForm.endDate = this.queryForm.dayTime[1];
      this.queryForm.sourceId = this.oId;
      let oVal = JSON.parse(JSON.stringify(this.queryForm));
      if (this.platFormPath === "outwater") {
        if (!this.queryForm.checkPointId) {
          this.msgError("请完整填写查询条件");
          return;
        }
      } else {
        delete oVal.checkPointId;
      }
      let {startDate, endDate} = this.queryForm;
      if (startDate && endDate) {
        this.getList(oVal);
      } else {
        this.msgError("请完整填写查询条件");
      }
    },
    //获取7天前(num是正数表示之后的时间，num负数表示之前的时间，0表示今天)
    fun_date (num) {
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
    }
  }
};
</script>
<style lang="scss">
.home-serchForm {
  .el-form-item {
    margin-bottom: 5px;
  }

  label {
    color: #cfd0d2;
  }
}
</style>
