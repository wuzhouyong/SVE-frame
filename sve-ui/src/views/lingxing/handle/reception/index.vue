<template>
  <!--  零废接收量统计-->
  <div class="lx-wk">
    <div class="lx-left">
      <n-sourceLeft @selectSource="selectSource" tagHeadId="1018"></n-sourceLeft>
    </div>
    <div class="lx-right" v-if="sourceId">
      <div class="consump-date">
        <h4>{{ sourceName }}</h4>
        <p>选择日期：</p>
        <div>
          <el-date-picker type="month"
                          :clearable="false"
                          size="mini"
                          placeholder="选择月份"
                          value-format="yyyy-MM"
                          @change="dateChange"
                          v-model="dataDate"
                          style="width: 100%;">
          </el-date-picker>
        </div>
      </div>
      <div class="consump-charts">
        <h3 class="tb-title">零废接收量 ( m³ )</h3>
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
            align="center"
            prop="datadate"
            label="日期">
          </el-table-column>
          <el-table-column
            align="center"
            prop="inamt"
            label="流量（当天新增水量）">
          </el-table-column>
          <el-table-column
            align="center"
            label="联单登记水量">
            <template slot-scope="scope">
              {{ scope.caramt ? scope.caramt : 0 }}
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
  </div>
</template>

<script>
import {lxTransBillReceive} from '@/api/lingxing'
import nSourceLeft from "@/components/sourceLeft";
import VeLine from "v-charts/lib/line.common";

export default {
  components: {nSourceLeft, VeLine},
  data() {
    this.chartsSetting = {
      settings: {
        labelMap: {
          datadate:'时间',
          inamt:'流量（当天新增水量）',
          caramt:'联单登记水量'
        }
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
        columns: ['datadate','inamt','caramt'],
        rows: [],
      }
    }
  },
  created() {
    var time = new Array();
    for (let allinfo = this.lastYearMoth(), i = 0; i < allinfo.length; i++) {
      time[i] = allinfo[i];
    }
    this.dataDate = time[0];
  },
  methods: {
    selectSource(v) {
      this.sourceId = v.sourceId;
      this.sourceName = v.fullName;
      this.chartData.rows = [];
      this.tableData = [];
      let oVal = {
        sourceId: this.sourceId,
        dataDate: this.dataDate
      }
      this.getRightData(oVal);
    },
    getRightData(v = {}) {
      lxTransBillReceive(v).then(res => {
        let oRes = res.data;
        if (oRes.length > 0) {
          this.chartData.rows = oRes;
          this.tableData = oRes;
        }
      })
    },
    dateChange(v) {
      this.dataDate = v;
      let oVal = {
        sourceId: this.sourceId,
        dataDate: this.dataDate
      }
      this.getRightData(oVal);
    },
    lastYearMoth() {
      let result = [];
      for (let i = 0; i < 6; i++) {
        let d = new Date();
        d.setDate(1);
        d.setMonth(d.getMonth() - i);
        let m = d.getMonth() + 1;

        m = m < 10 ? "0" + m : m;
        //在这里可以自定义输出的日期格式
        result.push(d.getFullYear() + "-" + m);
        //result.push(d.getFullYear() + "年" + m + '月');
      }
      return result;
    },
  }
}
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
