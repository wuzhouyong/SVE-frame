<template>
  <!--  进水量统计-->
  <div class="lx-wk">
    <div class="lx-left">
      <n-sourceLeft @selectSource="selectSource" tagHeadId="1018"></n-sourceLeft>
    </div>
    <div class="lx-right" v-if="sourceId">
      <div class="consump-date">
        <h4>{{ sourceName }}</h4>
        <p>选择日期：</p>
        <div>
          <el-date-picker type="date"
                          :clearable="false"
                          size="mini"
                          placeholder="选择日期"
                          value-format="yyyy-MM-dd"
                          @change="dateChange"
                          v-model="dataDate"
                          style="width: 100%;">
          </el-date-picker>
        </div>
      </div>
      <div class="consump-charts">
        <h3 class="tb-title">水质统计</h3>
        <ve-line
          :data="chartData"
          :settings="chartsSetting.settings"
          :legend="chartsSetting.legend"
          :extend="chartsSetting.extend"
          height="100%"
        ></ve-line>
      </div>
      <div class="consump-table">
        <el-table
          :data="tableData"
          border
          size="mini"
          style="width: 100%"
          height="100%">
          <el-table-column
            v-for="(i,index) in chartData.columns"
            align="center"
            :prop="i"
            :label="i"
            :key="index">
          </el-table-column>
        </el-table>
      </div>
    </div>
  </div>
</template>

<script>
import {lxpWaterQualityAmt} from "@/api/lingxing";
import nSourceLeft from "@/components/sourceLeft";
import {fun_date} from "@/utils/ruoyi";
import VeLine from "v-charts/lib/line.common";

export default {
  components: {nSourceLeft, VeLine},
  data () {
    this.chartsSetting = {
      settings: {
        labelMap: {}
      },
      legend: {
        show: true,
        top: 10,
        textStyle: {
          color: "#000",
          fontSize: 11
        }
      },
      extend: {
        "grid.bottom": "20px",
        "grid.top": "60px",
        yAxis: {
          splitNumber: 5,
          axisTick: {
            alignWithLabel: 2
          },
          axisLabel: {
            show: true,
            textStyle: {
              color: "#000",
              fontSize: 12
            }
          }
        },
        xAxis: {
          axisLabel: {
            show: true,
            // rotate: 15, //倾斜
            textStyle: {
              color: "#000",
              fontSize: 12
            }
          }
        }
      }
    };
    return {
      dataDate: "",
      sourceName: null,
      sourceId: null,
      tableData: [],
      chartData: {
        columns: [],
        rows: [],
      }
    };
  },
  created () {
    this.dataDate = fun_date(-1);
  },
  methods: {
    selectSource (v) {
      this.sourceId = v.sourceId;
      this.sourceName = v.fullName;
      this.chartData.columns = [];
      this.chartData.rows = [];
      this.tableData = [];
      let oVal = {
        sourceId: this.sourceId,
        dataDate: this.dataDate
      };
      this.getRightData(oVal);
    },
    getRightData (v = {}) {
      lxpWaterQualityAmt(v).then(res => {
        let oRes = res.data;
        if (oRes.length > 0) {
          this.chartData.columns = Object.keys(oRes[0]);
          this.chartData.rows = res.data;
          this.tableData = res.data;
        }
        console.log(this.chartData)
      });
    },
    dateChange (v) {
      this.dataDate = v;
      let oVal = {
        sourceId: this.sourceId,
        dataDate: this.dataDate
      };
      this.getRightData(oVal);
    }
  }
};
</script>

<style lang="scss" scoped>
.lx-wk {
  width: 100%;
  height: 100%;
  display: flex;
  overflow: hidden;

  .lx-left {
    width: 300px;
    height: 100%;
  }

  .lx-right {
    flex: 1;
    height: 100%;
    padding: 10px;
    display: flex;
    flex-direction: column;
    overflow: hidden;

    .consump-date {
      display: flex;
      overflow: hidden;
      align-items: center;

      p {
        width: auto;
        font-size: 14px;
        margin: 0;
        padding: 0;
        margin: 0 5px;
      }

      h4 {
        margin: 0;
        margin-right: 10px;
        padding-left: 10px;
      }
    }

    .consump-charts {
      width: 100%;
      height: 400px;
      display: flex;
      flex-direction: column;
      overflow: hidden;
      margin: 5px 0;
    }

    .consump-table {
      flex: 1;
      overflow: hidden;
    }
  }

  .tb-title {
    height: 30px;
    line-height: 30px;
    border-bottom: 1px solid #dcdfe6;
    font-size: 14px;
    padding-left: 10px;
    margin: 0;
    letter-spacing: 1px;
  }
}
</style>
