<template>
  <div class="psh-kanban-wk">
    <el-row :gutter="10" style="width:100%">
      <el-col class="psh-el-col" :span="6">
        <div class="psh-kanban-left" v-if="isChange">
          <div class="kanban-left-top">
            <div class="psh-kanban-title">
              <i class="el-icon-s-data"></i>
              <p>全局总览(近7天)</p>
            </div>
            <div class="overall-data">
              <ul>
                <el-row :gutter="10" style="width:100%">
                  <el-col :xs="8" :sm="8" :lg="8" :xl="8">
                    <li class="color_1">
                      <p>{{ eq.total }}</p>
                      <h4>设备总数</h4>
                    </li>
                  </el-col>
                  <el-col :xs="8" :sm="8" :lg="8" :xl="8">
                    <li class="color_2">
                      <p>{{ eq.onLine }}</p>
                      <h4>在线设备数</h4>
                    </li>
                  </el-col>
                  <el-col :xs="8" :sm="8" :lg="8" :xl="8">
                    <li class="color_3">
                      <p>{{ eq.offLine }}</p>
                      <h4>离线设备数</h4>
                    </li>
                  </el-col>
                  <el-col :xs="8" :sm="8" :lg="8" :xl="8">
                    <li class="color_4">
                      <p>{{ wd.one }}</p>
                      <h4>一级预警数</h4>
                    </li>
                  </el-col>
                  <el-col :xs="8" :sm="8" :lg="8" :xl="8">
                    <li class="color_5">
                      <p>{{ wd.two }}</p>
                      <h4>二级预警数</h4>
                    </li>
                  </el-col>
                  <el-col :xs="8" :sm="8" :lg="8" :xl="8">
                    <li class="color_6">
                      <p>{{ wd.three }}</p>
                      <h4>三级预警数</h4>
                    </li>
                  </el-col>
                </el-row>
              </ul>
            </div>
          </div>
          <div class="kanban-left-mid">
            <div class="psh-kanban-title">
              <i class="el-icon-s-data"></i>
              <p>近30天预警总览</p>
            </div>
            <div class="mon-style">
              <ve-ring
                :data="warnMonthData"
                :legend-visible="false"
                height="100%"
                :extend="monthConfig.chartExtend"
                :legend="monthConfig.legend"
                :settings="monthConfig.settings"
                :colors="monthConfig.colors"
              ></ve-ring>
              <h3>{{ warnTotal }}</h3>
            </div>
          </div>
          <div class="kanban-left-btm">
            <div class="psh-kanban-title">
              <i class="el-icon-s-data"></i>
              <p>预警产生趋势</p>
            </div>
            <div class="eq-style">
              <ve-histogram
                :data="equipmentData"
                height="100%"
                :extend="equipmentConfig.extend"
                :settings="equipmentConfig.setting"
                :colors="equipmentConfig.colors"
                :legend="equipmentConfig.legend"
              ></ve-histogram>
            </div>
          </div>
        </div>
      </el-col>
      <el-col class="psh-el-col" :span="11">
        <div class="psh-kanban-mid">
          <div class="psh-mid-top">
            <div class="psh-kanban-title">
              <i class="el-icon-s-data"></i>
              <p>污染源程度分析</p>
            </div>
            <div class="psh-mid-top-charts">
              <el-row :gutter="10" style="width:100%">
                <el-col :xs="8" :lg="8" :xl="8">
                  <h5>近24小时</h5>
                  <!--                24小时-->
                  <ve-pie
                    :data="hourPieData"
                    :legend="polluteConfig.legend"
                    :extend="polluteConfig.chartExtend"
                    :tooltip="polluteConfig.tooltip"
                    :colors="polluteConfig.colors"
                    height="100%"
                  ></ve-pie>
                </el-col>
                <el-col :xs="8" :lg="8" :xl="8">
                  <h5>近7天</h5>
                  <ve-pie
                    :data="weekPieData"
                    :legend="polluteConfig.legend"
                    :extend="polluteConfig.chartExtend"
                    :tooltip="polluteConfig.tooltip"
                    :colors="polluteConfig.colors"
                    height="100%"
                  ></ve-pie>
                </el-col>
                <el-col :xs="8" :lg="8" :xl="8">
                  <h5>近30天</h5>
                  <ve-pie
                    :data="monthPieData"
                    :legend="polluteConfig.legend"
                    :extend="polluteConfig.chartExtend"
                    :tooltip="polluteConfig.tooltip"
                    :colors="polluteConfig.colors"
                    height="100%"
                  ></ve-pie>
                </el-col>
              </el-row>
            </div>
          </div>
          <div class="psh-mid-btm">
            <div class="psh-kanban-title">
              <i class="el-icon-s-data"></i>
              <p>污染程度排名(TOP20)</p>
            </div>
            <div class="townTop-style" v-loading="loading">
              <el-table :data="topTown" style="width: 100%;" height="100%" size="mini" border>
                <el-table-column label="排名" type="index" :index="1" align="center"></el-table-column>
                <el-table-column prop="townName" label="镇街" width="90"></el-table-column>
                <el-table-column
                  prop="sourceName"
                  label="污染源名称"
                  min-width="180"
                  show-overflow-tooltip
                ></el-table-column>
                <el-table-column prop="cpName" label="监测井" width="180" show-overflow-tooltip></el-table-column>
                <el-table-column prop="wqi" label="WQI" width="100"></el-table-column>
                <el-table-column label="污染程度" width="90" align="center">
                  <template slot-scope="scope">
                    <span v-if="scope.row.wqi>30">严重污染</span>
                    <span v-else-if="scope.row.wqi<30&&scope.row.wqi>20">重度污染</span>
                    <span v-else-if="scope.row.wqi<20&&scope.row.wqi>10">中度污染</span>
                    <span v-else-if="scope.row.wqi<10&&scope.row.wqi>5">轻度污染</span>
                  </template>
                </el-table-column>
              </el-table>
            </div>
          </div>
        </div>
      </el-col>
      <el-col class="psh-el-col" :span="7">
        <div class="psh-kanban-right">
          <div class="psh-kanban-title">
            <i class="el-icon-s-data"></i>
            <p>镇街报警排名</p>
          </div>
          <div class="vBar-style">
            <ve-bar
              :data="townRank"
              height="100%"
              :extend="townRangConfig.extend"
              :legend-visible="false"
              :settings="townRangConfig.setting"
            ></ve-bar>
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import {
  townWarnRank,
  warningMonth,
  warningQuantity,
  equipmentQuantity,
  warningPollution,
  warningTrend,
  pollutionAnalysis
} from "@/api/outwater";
import VeBar from "v-charts/lib/bar.common";
import VeRing from "v-charts/lib/ring.common";
import VeHistogram from "v-charts/lib/histogram.common";
import VePie from "v-charts/lib/pie.common";
import { mapState } from "vuex";

export default {
  components: { VeBar, VeRing, VeHistogram, VePie },
  data() {
    //镇街排名
    this.townRangConfig = {
      setting: {
        labelMap: {
          name: "镇街",
          amount: "报警数"
        }
      },
      extend: {
        xAxis: {
          axisLabel: {
            color: "#fff"
          }
        },
        yAxis: {
          axisLabel: {
            color: "#fff",
            formatter: (v, i) => {
              return v + " ( " + this.numList[i] + " ) ";
            }
          }
        },
        "grid.top": "20px",
        "grid.bottom": "30px",
        series(v) {
          v.forEach((i) => {
            let oArr = JSON.parse(JSON.stringify(i.data));
            if (oArr.length > 0) {
              oArr = oArr.sort((a, b) => b - a);
            }
            i.barWidth = 10;
            i.itemStyle = {
              color: (params) => {
                let colorList = [
                  "rgb(242,107,107)",
                  "rgb(229,206,77)",
                  "rgb(31,208,127)",
                  "rgb(0,146,254)"
                ];
                let d = params.data;
                if (d === oArr[0]) {
                  return colorList[0];
                } else if (d === oArr[1]) {
                  return colorList[1];
                } else if (d === oArr[2]) {
                  return colorList[2];
                } else {
                  return colorList[3];
                }
              },
              borderWidth: 1
            };
            i.label = {
              color: "#fff",
              show: true,
              position: "right"
            };
          });
          return v;
        }
      }
    };
    //近30天
    this.monthConfig = {
      colors: ["#EDC673", "#f29a44", "#FF7575"],
      chartExtend: {
        series: {
          radius: ["60%", "80%"],
          center: ["50%", "50%"]
        }
      },
      legend: {
        show: false
      }
    };
    //预警产生趋势
    this.equipmentConfig = {
      legend: {
        show: true,
        textStyle: {
          color: "#fff",
          fontSize: 12
        },
        y: 10
      },
      colors: ["#FF7575", "#f29a44", "#EDC673"],
      setting: {
        labelMap: {
          one: "一级预警",
          two: "二级预警",
          three: "三级预警"
        }
      },
      extend: {
        series(v) {
          // 设置柱子的样式
          v.forEach((i) => {
            i.barWidth = 20;
            i.barGap = "20%"
            i.label = {
              color: "#fff",
              show: true,
              position: "top"
            };
          });
          return v;
        },
        "grid.top": "50px",
        "grid.bottom": "10px",
        yAxis: {
          splitNumber: 5,
          axisTick: {
            alignWithLabel: 2
          },
          axisLabel: {
            show: true,
            textStyle: {
              color: "white",
              fontSize: 12
            }
          }
        },
        xAxis: {
          axisLabel: {
            show: true,
            textStyle: {
              color: "white",
              fontSize: 12
            }
          }
        }
      }
    };
    //污染
    this.polluteConfig = {
      colors: [
        "#5FC648",
        "#25B8D2",
        "#EDC673",
        "#B37EDD",
        "#E772CF",
        "#E3537F"
      ],
      tooltip: {
        formatter: (v) => {
          return `${v.marker}${this.wrType[v.name]} : ${v.value}`;
        }
      },
      legend: {
        show: true,
        orient: "horizontal",
        x: "center",
        y: "10",
        itemWidth: 12,
        itemHeight: 12,
        textStyle: {
          color: "#fff"
        },
        formatter: (v) => {
          return this.wrType[v];
        }
      },
      chartExtend: {
        series: {
          radius: "66%",
          center: ["50%", "55%"],
          labelLine: {
            normal: {
              show: false
            }
          },
          itemStyle: {
            emphasis: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: "rgba(0,0,0,0.5)"
            }
          },
          label: {
            normal: {
              show: false
            }
          }
        }
      }
    };
    return {
      townRank: {
        columns: ["name", "amount"],
        rows: []
      },
      numList: [],
      warnTotal: 0,
      eq: {},
      wd: {},
      warnMonthData: {
        columns: ["name", "count"],
        rows: []
      },
      equipmentData: {
        columns: ["datadate", "one", "two", "three"],
        rows: []
      },
      //TOP10
      topTown: [],
      loading: true,
      //24小时
      hourPieData: {
        columns: ["type", "count"],
        rows: []
      },
      //24小时
      weekPieData: {
        columns: ["type", "count"],
        rows: [
          { type: "superior", count: 0 },
          { type: "good", count: 0 },
          { type: "mild", count: 2 },
          { type: "medium", count: 33 },
          { type: "severe", count: 42 },
          { type: "serious", count: 110 }
        ]
      },
      //24小时
      monthPieData: {
        columns: ["type", "count"],
        rows: [
          { type: "superior", count: 0 },
          { type: "good", count: 0 },
          { type: "mild", count: 2 },
          { type: "medium", count: 33 },
          { type: "severe", count: 42 },
          { type: "serious", count: 110 }
        ]
      },
      //污染type
      wrType: {
        superior: "优",
        good: "良",
        mild: "轻度污染",
        medium: "中度污染",
        severe: "重度污染",
        serious: "严重污染"
      },
      isChange: true
    };
  },
  created() {
    this.getTownWarnRank();
    this.getWarnMonth();
    this.getWarningQuantity();
    this.getEquipmentQuantity();
    this.getWarningTrend();
    this.getWarningPollution();
    this.getPollutionAnalysis();
  },
  computed: {
    ...mapState({
      sidebar: (state) => state.app.sidebar.opened
    })
  },
  watch: {
    sidebar: {
      handler() {
        this.isChange = false;
        this.$nextTick(() => {
          this.isChange = true;
        });
      }
    }
  },
  methods: {
    //镇街排名
    getTownWarnRank() {
      townWarnRank().then((res) => {
        if (res.code === 200) {
          let oRes = res.data;
          oRes = oRes.sort((a, b) => a.amount - b.amount);
          this.numList = this.generateArray(1, oRes.length);
          this.townRank.rows = oRes;
        }
      });
    },
    //近30天报警
    getWarnMonth() {
      warningMonth().then((res) => {
        if (res.code === 200) {
          let newRes = [];
          res.data.rows.forEach((i) => {
            if (i.name === "三级预警") {
              newRes[0] = i;
            } else if (i.name === "二级预警") {
              newRes[1] = i;
            } else if (i.name === "一级预警") {
              newRes[2] = i;
            }
          });
          this.warnTotal = res.data.total;
          this.warnMonthData.rows = newRes;
        }
      });
    },
    //报警数
    getWarningQuantity() {
      warningQuantity().then((res) => {
        if (res.code === 200) {
          this.wd = res.data;
        }
      });
    },
    //设备数量
    getEquipmentQuantity() {
      equipmentQuantity().then((res) => {
        if (res.code === 200) {
          this.eq = res.data;
        }
      });
    },
    //预警产生趋势
    getWarningTrend() {
      warningTrend().then((res) => {
        if (res.code === 200) {
          this.equipmentData.rows = res.data;
        }
      });
    },
    //企业top10
    getWarningPollution() {
      warningPollution().then((res) => {
        if (res.code === 200) {
          this.topTown = res.data;
        }
        this.loading = false;
      });
    },
    //近24小时
    getPollutionAnalysis() {
      pollutionAnalysis().then((res) => {
        if (res.code === 200) {
          this.hourPieData.rows = this.sortPollute(res.data.hour);
          this.weekPieData.rows = this.sortPollute(res.data.week);
          this.monthPieData.rows = this.sortPollute(res.data.month);
        }
      });
    },
    //生成顺序数字数组
    generateArray(start, end) {
      let newArr = Array.from(new Array(end + 1).keys()).slice(start);
      newArr = newArr.sort((a, b) => b - a);
      return newArr;
    },
    //污染度排序
    sortPollute(v) {
      let newRes = [];
      v.forEach((i) => {
        if (i.type === "superior") {
          newRes[0] = i;
        } else if (i.type === "good") {
          newRes[1] = i;
        } else if (i.type === "mild") {
          newRes[2] = i;
        } else if (i.type === "medium") {
          newRes[3] = i;
        } else if (i.type === "severe") {
          newRes[4] = i;
        } else if (i.type === "serious") {
          newRes[5] = i;
        }
      });
      return newRes;
    }
  }
};
</script>

<style lang="scss">
.psh-kanban-wk {
  padding: 10px 4px 10px 10px;
  width: 100%;
  height: 100%;
  overflow-y: auto;
  display: flex;

  .psh-kanban-title {
    display: flex;
    width: 100%;
    margin-left: 6px;
    background: -webkit-gradient(
        linear,
        left top,
        right top,
        from(#454647),
        to(rgba(69, 70, 71, 0))
    );
    background: linear-gradient(90deg, #454647, rgba(69, 70, 71, 0));
    height: 30px;
    line-height: 30px;
    border-left: 4px solid rgba(17, 217, 255, 0.8);
    padding-left: 10px;
    color: #fff;
    font-size: 14px;
    letter-spacing: 1px;
    align-items: center;

    p {
      padding: 0;
      margin: 0;
    }
  }

  .psh-el-col {
    height: 100%;
  }

  .psh-kanban-left {
    width: 100%;
    height: 100%;
    background: rgba(0, 21, 41, 0.8);
    box-shadow: 0 0px 0px rgba(0, 0, 0, 0.3), 0 0 4px rgba(0, 0, 0, 0.9);
    padding-top: 6px;
    display: flex;
    flex-direction: column;
    overflow: hidden;
    overflow-y: auto;

    .kanban-left-mid {
      height: 260px;

      .mon-style {
        height: calc(100% - 30px);
        position: relative;

        h3 {
          //position: absolute;
          //width: 100%;
          //height: 30px;
          //left: 0px;
          //top: 50%;
          //margin-top: -15px;
          //line-height: 30px;
          //text-align: center;
          //font-size: 40px;
          //color: #fff;

          position: absolute;
          width: 100%;
          height: 100%;
          left: 0px;
          top: 0;
          /* margin-top: 0; */
          /* line-height: 30px; */
          text-align: center;
          font-size: 40px;
          color: #fff;
          margin: 0;
          display: flex;
          align-items: center;
          justify-content: center;
        }
      }
    }

    .kanban-left-btm {
      flex: 1;
      min-height: 300px;

      .eq-style {
        height: calc(100% - 30px);
      }
    }
  }

  .psh-kanban-mid {
    width: 100%;
    height: 100%;
    background: rgba(0, 21, 41, 0.8);
    box-shadow: 0 0px 0px rgba(0, 0, 0, 0.3), 0 0 4px rgba(0, 0, 0, 0.9);
    display: flex;
    flex-direction: column;
    overflow: hidden;
    overflow-y: auto;

    .psh-mid-top {
      height: 300px;
      display: flex;
      min-height: 300px;
      flex-direction: column;
      overflow: hidden;

      .psh-mid-top-charts {
        flex: 1;

        .el-row {
          height: 100%;
          margin: 0 !important;

          .el-col {
            height: 100%;
            position: relative;
            padding: 0 !important;

            h5 {
              position: absolute;
              bottom: 6px;
              left: 0px;
              width: 100%;
              margin: 0;
              color: #fff;
              font-size: 16px;
              text-align: center;
            }
          }
        }
      }
    }

    .psh-mid-btm {
      display: flex;
      flex-direction: column;
      flex: 1;
    }
  }

  .psh-kanban-right {
    width: 100%;
    height: 100%;
    background: rgba(0, 21, 41, 0.8);
    box-shadow: 0 0px 0px rgba(0, 0, 0, 0.3), 0 0 4px rgba(0, 0, 0, 0.9);
    display: flex;
    overflow: hidden;
    flex-direction: column;
    overflow-y: auto;
  }

  .vBar-style {
    width: 100%;
    flex: 1;
    padding-right: 20px;
    overflow: hidden;
    min-height: 780px;
  }

  .overall-data {
    ul {
      display: flex;
      flex-wrap: wrap;
      justify-content: space-between;
      padding: 0 8px;
      margin: 0;
      margin-top: 10px;

      .el-row {
        margin: 0 !important;
      }

      li {
        flex: 0 0 130px;
        list-style-type: none;
        display: flex;
        flex-direction: column;
        margin-bottom: 20px;
        border-radius: 5px;
        border: 1px solid transparent;
        margin-bottom: 10px;

        &.color_1 {
          border-color: #00a0ff;

          p {
            color: #00a0ff;
            border-color: #00a0ff;
          }

          h4 {
            background-color: #00a0ff;
          }
        }

        &.color_2 {
          border-color: #22e48b;

          p {
            color: #22e48b;
            border-color: #22e48b;
          }

          h4 {
            background-color: #22e48b;
          }
        }

        &.color_3 {
          border-color: #a2a1a3;

          p {
            color: #a2a1a3;
            border-color: #a2a1a3;
          }

          h4 {
            background-color: #a2a1a3;
          }
        }

        &.color_4 {
          border-color: #ff7575;

          p {
            color: #ff7575;
            border-color: #ff7575;
          }

          h4 {
            background-color: #ff7575;
          }
        }

        &.color_5 {
          border-color: #f29a44;

          p {
            color: #f29a44;
            border-color: #f29a44;
          }

          h4 {
            background-color: #f29a44;
          }
        }

        &.color_6 {
          border-color: #edc673;

          p {
            color: #edc673;
            border-color: #edc673;
          }

          h4 {
            background-color: #edc673;
          }
        }

        p,
        h4 {
          margin: 0;
          text-align: center;
        }

        p {
          font-size: 28px;
          height: 36px;
          line-height: 36px;
          border-bottom: 1px solid transparent;
          font-weight: bold;
        }

        h4 {
          font-size: 13px;
          height: 26px;
          line-height: 26px;
          color: #fff;
          font-weight: normal;
          overflow: hidden;
          white-space: nowrap;
          text-overflow: ellipsis;
        }
      }
    }
  }
}

.townTop-style {
  padding: 6px;
  height: 100%;

  .el-table {
    color: #fff;
    background-color: transparent;

    th {
      background-color: transparent !important;

      div {
        color: #fff;
      }
    }

    tr {
      background-color: transparent !important;

      &:hover {
        td {
          background: rgba(0, 0, 0, 0.3) !important;
        }
      }
    }
  }
}
</style>
