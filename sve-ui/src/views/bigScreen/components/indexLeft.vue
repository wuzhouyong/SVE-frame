<template>
  <div class="index-left">
    <char-box class="task-box" :title="'任务总览'" :height="300">
      <div class="num">180</div>
      <ve-ring
        class="task-pie-chart"
        :data="taskPieChart.chartData"
        :colors="config.colors"
        :legend="config.legend"
        :extend="config.chartExtend"
        :settings="config.settings"
      />
    </char-box>

    <char-box class="task-his-box" :title="'任务数量分析'" :height="360">
      <ve-histogram
        class="task-his-chart"
        :data="taskHisChart.chartData"
        :legend="{ show: false }"
        :extend="taskHisChart.extend"
        :theme-name="'dark'"
        :settings="taskHisChart.chartSettings"
      ></ve-histogram>
    </char-box>

    <char-box class="task-table" :title="'异常任务跟踪'" :height="335">
      <div class="task-table-head">
        <el-row class="task-head-row">
          <el-col :span="12"
          ><span style="font-size: 12px">异常任务总数</span>
            <span style="color: #f8ad60;font-weight: bold">17</span>
          </el-col>
          <el-col :span="12"
          ><span style="font-size: 12px">异常率</span>
            <span style="color: #FF7993;font-weight: bold">92%</span></el-col
          >
        </el-row>
      </div>
      <div class="table-box">
        <table>
          <tr v-for="(item, index) of taskTableData" :key="index">
            <td style="width: 20px; color: #5e88eb">{{ index + 1 }}</td>
            <td style="width: 60px">{{ item.type }}</td>
            <td>{{ item.title }}</td>
            <td style="width: 40px; color: #f8bb38">{{ item.reason }}</td>
          </tr>
        </table>
      </div>
    </char-box>
  </div>
</template>

<script>
import CharBox from "./charBox";
import VeRing from "v-charts/lib/ring.common";
import VeHistogram from "v-charts/lib/histogram.common";
import rand from "../../../utils/randomData";

export default {
  name: "indexLeft",
  components: {CharBox, VeRing, VeHistogram},
  data() {
    this.config = {
      colors: ["#1dd8c5", "#fa6e86", "#eeda84", "#d680ef"],
      settings: {
        label: {
          show: true,
          formatter: (v) => {
            return (
              "{color1|" +
              v.name +
              "}\n\n{color2|" +
              v.value +
              "}{color2|(" +
              v.percent +
              "%)}"
            );
          },
          rich: {
            color1: {
              color: "#fff"
            },
            color2: {
              color: "#03ccff"
            }
          }
        }
      },
      chartExtend: {
        series: {
          radius: ["35%", "48%"],
          center: ["50%", "55%"]
        }
      },
      legend: {
        show: true,
        right: 0,
        top: 10,
        textStyle: {
          color: "#ffffff",
          fontSize: 11
        }
      }
    };
    return {
      taskPieChart: {
        chartData: {
          columns: ["type", "count"],
          rows: [
            {type: "已完成", count: rand.number(1, 100)},
            {type: "处理中", count: rand.number(1, 100)},
            {type: "已超时", count: rand.number(1, 100)},
            {type: "待处理", count: rand.number(1, 100)},
          ],
        },
      },
      taskHisChart: {
        chartData: {
          columns: ["name", "已完成", "待完成", "已逾期"],
          rows: [
            {
              name: "大气环境",
              已完成: rand.number(1, 100),
              待完成: rand.number(1, 20),
              已逾期: rand.number(1, 10),
            },
            {
              name: "水环境",
              已完成: rand.number(1, 100),
              待完成: rand.number(1, 20),
              已逾期: rand.number(1, 10),
            },
            {
              name: "土壤与固废",
              已完成: rand.number(1, 100),
              待完成: rand.number(1, 20),
              已逾期: rand.number(1, 10),
            },
            {
              name: "环境信访",
              已完成: rand.number(1, 100),
              待完成: rand.number(1, 20),
              已逾期: rand.number(1, 10),
            },
            {
              name: "在线监控",
              已完成: rand.number(1, 100),
              待完成: rand.number(1, 20),
              已逾期: rand.number(1, 10),
            },
            {
              name: "双随机",
              已完成: rand.number(1, 100),
              待完成: rand.number(1, 20),
              已逾期: rand.number(1, 10),
            },
            {
              name: "手工任务",
              已完成: rand.number(1, 100),
              待完成: rand.number(1, 20),
              已逾期: rand.number(1, 10),
            },
          ],
        },
        chartSettings: {
          stack: {name: ["已完成", "待完成", "已逾期"]},
        },
        extend: {
          barWidth: "50%",
          backgroundColor: "rgba(1,1,1,0)",
          "xAxis.0.axisLabel.rotate": 30,
          grid: {
            left: "3%",
            right: "3%",
            bottom: "20%",
            containLabel: true,
          },
        },
      },
      taskTableData: [],
    };
  },
  mounted() {
    this.taskTableData = rand.taskTbaleData();
    //console.log(this.taskTableData)
  },
};
</script>

<style lang="scss" scoped>
.index-left {
  padding: 0 20px 0 20px;

  .task-box {
    margin-top: 10px;

    .task-pie-chart {
      top: -100px;
    }

    .num {
      color: #f8bb38;
      font-size: 50px;
      position: absolute;
      width: 100%;
      text-align: center;
      left: 0px;
      top: 48%;
    }
  }

  .task-his-box {
    margin-top: 20px;

    .task-his-chart {
      margin-top: -40px;
    }
  }

  .task-table {
    margin-top: 20px;

    .task-table-head {
      width: 90%;
      background-color: #0288d1;
      height: 35px;
      margin-left: 5%;
      border-radius: 5px;
      margin-bottom: 10px;

      .task-head-row {
        div {
          font-size: 18px;
          text-align: center;

          span {
            line-height: 35px;
          }
        }
      }
    }

    .table-box {
      height: 230px;
      overflow: hidden;
      position: relative;

      table {
        color: #80deea;
        position: absolute;
        animation: topMove 18s linear infinite;
        -webkit-animation: topMove 18s linear infinite;

        width: 100%;
        -moz-user-select: none;
        -ms-user-select: none;
        -webkit-user-select: none;
        -khtml-user-select: none;
        table-layout: fixed;
        line-height: 1.1;

        tr {
          font-size: 13px;

          td {
            padding-left: 5px;
            padding-bottom: 10px;
          }
        }
      }
    }
  }
}

@keyframes topMove {
  0% {
    top: 0%;
  }
  5% {
    top: 0%;
  }
  45% {
    top: -100%;
  }
  50% {
    top: -100%;
  }
  100% {
    top: 0%;
  }
}

@-webkit-keyframes topMove {
  0% {
    top: 0%;
  }
  5% {
    top: 0%;
  }
  45% {
    top: -100%;
  }
  50% {
    top: -100%;
  }
  100% {
    top: 0%;
  }
}
</style>
