<template>
  <!--  水池占用比统计-->
  <div class="lx-wk">
    <div class="lx-left">
      <n-sourceLeft @selectSource="selectSource" tagHeadId="1003"></n-sourceLeft>
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
        <h3 class="tb-title">水池液位占比(%)</h3>
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
            v-for="(i,index) in tableHead"
            :key="index"
            :label="i.storage_name">
            <el-table-column
              align="center"
              :prop="i.max_height"
              label="最大高度(m)">
            </el-table-column>
            <el-table-column
              align="center"
              :prop="i.avg_amt"
              label="液位高度(cm)">
            </el-table-column>
            <el-table-column
              align="center"
              :prop="i.rate"
              label="液位占比(%)">
            </el-table-column>
          </el-table-column>

        </el-table>
      </div>
    </div>
  </div>
</template>

<script>
import {lxStorageRate} from "@/api/lingxing";
import nSourceLeft from "@/components/sourceLeft";
import {fun_date} from "@/utils/ruoyi";
import VeLine from "v-charts/lib/line.common";

export default {
  components: {nSourceLeft, VeLine},
  data () {
    this.chartsSetting = {
      settings: {
        labelMap: {
          datadate: "日期",
          max_height: "最大高度",
          avg_amt: "液位高度",
          rate: "液位占比"
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
      tableHead: [],
      chartsTitle: [],
      chartData: {
        columns: ["datadate"],
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
      this.chartData.columns = ["datadate"];
      this.chartsTitle = [];
      this.chartData.rows = [];
      this.tableData = [];
      let oVal = {
        sourceId: this.sourceId,
        dataDate: this.dataDate
      };
      this.getRightData(oVal);
    },
    getRightData (v = {}) {
      lxStorageRate(v).then(res => {
        let oRes = res.data;
        let newArray = [];
        this.tableHead = [];
        this.chartsTitle = [];
        oRes.map(item => {
          return [item];
        }).forEach(([{...item}]) => {
          const flag = newArray.find(([{...o}]) => o.storage_id === item.storage_id);
          if (!flag) {
            newArray.push([{...item}]);
            this.chartsTitle.push(item.storage_name);
            this.tableHead.push({
              storage_name: item.storage_name,
              max_height: "max_height_" + item.storage_id,
              avg_amt: "avg_amt_" + item.storage_id,
              rate: "rate_" + item.storage_id
            });
          } else {
            newArray.forEach(([{...y}], index) => {
              if (y.storage_name === item.storage_name) {
                newArray[index].push(item);
              }
            });
          }
        });

        /**
         * [{dataDate:2020,'xxx':222,'yyy':3333}]
         *
         *
         *
         *
         * **/
        let oArr = [];
        newArray.forEach(i => {
          i.forEach(j => {
            let oD = oArr.find(o => o.datadate === j.datadate);
            if (!oD) {
              oD = {datadate: j.datadate};
              oArr.push(oD);
            }
            oD["max_height_" + j.storage_id] = j.max_height;
            oD["avg_amt_" + j.storage_id] = j.avg_amt;
            oD["rate_" + j.storage_id] = j.rate;
            oD[j.storage_name] = j.rate;
          });
        });
        this.tableData = oArr;
        this.chartData.columns = ["datadate"];
        this.chartData.columns.push(...this.chartsTitle);
        console.log(this.chartData);
        this.chartData.rows = oArr;
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
