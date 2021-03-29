<template>
  <div class="task-overview">
    <div class="task-overview-left">
      <ul>
        <li
          v-for="i in oList"
          :key="i.code"
          @click="viewClick(i.code)"
          :class="{'checked':checkTagList.indexOf(i.code)>-1}"
        >
          <h4>
            <i class="el-icon-s-ticket"></i>
            <span>{{ i.name }} ({{ i.count }})</span>
          </h4>
          <div class="task-con">
            <div class="task-con-list">
              <i class="nocon"></i>
              <span>{{ i.noconnected }}</span>
              <p>{{ getPencent(i.noconnected, i.count) }}</p>
            </div>
            <div class="task-con-list">
              <i class="constr"></i>
              <span>{{ i.constructed }}</span>
              <p>{{ getPencent(i.constructed, i.count) }}</p>
            </div>
            <div class="task-con-list">
              <i class="comple"></i>
              <span>{{ i.completed }}</span>
              <p>{{ getPencent(i.completed, i.count) }}</p>
            </div>
            <div class="task-con-list">
              <i class="accept"></i>
              <span>{{ i.accepted }}</span>
              <p>{{ getPencent(i.accepted, i.count) }}</p>
            </div>
          </div>
        </li>
      </ul>
    </div>
    <div class="task-overview-right">
      <div class="task-count">
        <p>{{ count }}</p>
        <span>任务数</span>
      </div>
      <ve-ring
        height="500px"
        :data="chartData"
        :colors="config.colors"
        :legend="config.legend"
        :extend="config.chartExtend"
        :settings="config.settings"
      ></ve-ring>
      <!--      <el-radio-group v-model="rwType" size="mini" class="rwType" @change="typeChange">-->
      <!--        <el-radio-button label="1">已完成</el-radio-button>-->
      <!--        <el-radio-button label="2">未完成</el-radio-button>-->
      <!--      </el-radio-group>-->
      <!--      <ve-histogram :data="chartData" :extend="hisConfig.extend" :legend="hisConfig.legend" :events="chartEvents"-->
      <!--                    :settings="hisConfig.settings"></ve-histogram>-->
    </div>
  </div>
</template>
<script>
import { countSchedule, countSummary, taskCountStatusCount } from "@/api/analysis";
import { boardSystem } from "@/config";
import VeRing from "v-charts/lib/ring.common.js";
// import VeHistogram from "v-charts/lib/histogram.common.js";

export default {
  components: { VeRing },
  data() {
    this.config = {
      colors: ["#fa6e86", "#d680ef", "#eeda84", "#1dd8c5"],
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
          radius: ["55%", "75%"],
          center: ["50%", "58%"]
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
    //   this.chartEvents = {
    //     click: e => {
    //       this.$router.push({ path: "/monitor_task/monitorSource" });
    //     }
    //   }
    //   this.hisConfig = {
    //     settings: {
    //       labelMap: {
    //         "num": "数量"
    //       }
    //     },
    //     legend: {
    //       show: false,
    //       top: 10,
    //       textStyle: {
    //         color: "#ffffff",
    //         fontSize: 11
    //       }
    //     },
    //     extend: {
    //       series(v) {
    //         // 设置柱子的样式
    //         v.forEach((i, index) => {
    //           i.barWidth = 26;
    //           i.itemStyle = {
    //             borderWidth: 1,
    //             normal: {
    //               color: function(params) {
    //                 let colorList = ["#82c8a0", "#eeda84", "#d680ef", "#fa6e86", "#6698cb", "#82c8a0"];
    //                 return colorList[params.dataIndex];
    //               }
    //             }
    //           };
    //           i.label = {
    //             color: "#fff",
    //             show: true,
    //             position: "top",
    //             fontSize: 11
    //           };
    //         });
    //         return v;
    //       },
    //       "grid.bottom": "10px",
    //       "grid.top": "70px",
    //       "yAxis.0.axisLabel.color": "#fff",
    //       // yAxis: {
    //       //   axisLabel: {
    //       //     textStyle: {
    //       //       color: "#fff",
    //       //       fontSize: 12
    //       //     }
    //       //   }
    //       // },
    //       xAxis: {
    //         axisLabel: {
    //           show: true,
    //           // rotate: 20, //倾斜
    //           textStyle: {
    //             color: "#fff",
    //             fontSize: 12
    //           },
    //           rich: {
    //             labelText: {
    //               lineHeight: 18
    //             }
    //           }
    //         }
    //       }
    //     }
    //   }
    return {
      oList: [],
      chartData: {
        columns: ["name", "num"],
        rows: []
      },
      count: 0,
      checkTagList: [],
      isFinish: [],
      undone: [],
      rwType: "1"
    };
  },
  created() {
    countSchedule().then((res) => {
      if (res.code === 200) {
        let oRes = res.data;
        let oArr = [];
        boardSystem.forEach((i) => {
          oArr.push(oRes.find((j) => j.code.toString() === i.id));
        });
        this.oList = oArr;
      }
    });
    this.getSummary();
  }
  ,
  methods: {
    getPencent(num, total) {
      num = parseFloat(num);
      total = parseFloat(total);
      if (isNaN(num) || isNaN(total)) {
        return "-";
      }
      return total <= 0
        ? "0%"
        : Math.round((num / total) * 10000) / 100.0 + "%";
    }
    ,
    getSummary(v = {}) {
      // let oMap = {
      //   accepted: "已验收",
      //   applying: "申请中",
      //   completed: "已接入",
      //   constructed: "已施工",
      //   loggedin: "已登录",
      //   noconnected: "未开展",
      //   notinstalled: "不安装",
      //   suspend: "暂缓"
      // };
      // taskCountStatusCount().then(res => {
      //   if (res.code === 200) {
      //     let oS = res.data;
      //     this.isFinish = [
      //       { name: "已登录", num: oS.loggedin },
      //       { name: "已接入", num: oS.completed },
      //       { name: "已验收", num: oS.accepted },
      //       { name: "不安装", num: oS.notinstalled }];
      //     this.undone = [
      //       { name: "已登录", num: oS.loggedin },
      //       { name: "未开展", num: oS.noconnected },
      //       { name: "施工中", num: oS.constructed },
      //       { name: "暂缓", num: oS.suspend },
      //       { name: "申请中", num: oS.applying }]
      //     this.chartData.rows = this.isFinish;
      //   }
      // })
      let oMap = {
        noconnected: "未开展",
        constructed: "施工中",
        completed: "已接入",
        accepted: "已验收"
      };
      countSummary(v).then((res) => {
        let oRes = res.data;
        this.count = res.data.count;
        let oArr = [];
        Object.keys(oRes).forEach((i) => {
          let obj = {};
          if (i !== "count") {
            obj.name = oMap[i];
            obj.num = oRes[i];
            oArr.push(obj);
          }
        });
        this.chartData.rows = oArr;
      });
    }
    ,
    viewClick(v) {
      if (this.checkTagList.indexOf(v) > -1) {
        this.checkTagList.splice(
          this.checkTagList.findIndex((item) => item === v),
          1
        );
      } else {
        this.checkTagList.push(v);
      }
      let oVal = {
        tagHeadIds: this.checkTagList.join(",")
      };
      this.getSummary(oVal);
    },
    typeChange(v) {
      if (v === "1") {
        this.chartData.rows = this.isFinish;
      }
      if (v === "2") {
        this.chartData.rows = this.undone;
      }
    }
  }
};
</script>
<style lang="scss">
.task-overview {
  width: 100%;
  height: auto;
  display: flex;

  .task-overview-left {
    width: 60%;
    padding-bottom: 5px;

    ul {
      display: flex;
      flex-flow: row wrap;
      justify-content: space-between;
      margin: 0;
      padding: 0;

      .checked {
        background-color: rgba(102, 153, 255, 0.6);

        h4 {
          span {
            color: #fff;
            font-weight: bold;
          }

          border-bottom: 1px solid #fff;
        }

        .task-con {
          .task-con-list {
            border-right: 1px solid #fff;

            p,
            span {
              color: #fff;
            }
          }
        }
      }

      li {
        margin-top: 5px;
        display: flex;
        flex-direction: column;
        box-sizing: border-box;
        flex: 0 0 33%;
        list-style-type: none;
        border: 1px solid #ebeef5;
        cursor: pointer;
        transition-property: background-color;
        transition-duration: 0.3s;
        transition-timing-function: ease;

        &:hover {
          background-color: rgba(102, 153, 255, 0.6);

          h4 {
            span {
              color: #fff;
              font-weight: bold;
            }

            border-bottom: 1px solid #fff;
          }

          .task-con {
            .task-con-list {
              border-right: 1px solid #fff;

              p,
              span {
                color: #fff;
              }
            }
          }
        }

        h4 {
          color: #e4d14e;
          margin: 0px;
          height: 30px;
          line-height: 30px;
          padding-left: 10px;
          font-size: 14px;
          font-weight: normal;
          border-bottom: 1px solid #ebeef5;
          display: flex;
          align-items: center;

          span {
            color: #e4d14e;
            font-size: 14px;
            font-weight: normal;
            padding-left: 5px;
          }
        }

        .task-con {
          display: flex;
          justify-content: space-between;
          margin: 5px 0;

          .task-con-list {
            flex: 0 0 25%;
            text-align: center;
            position: relative;
            margin-top: 5px;
            padding-top: 12px;
            border-right: 1px solid #ebeef5;

            &:last-child {
              border-right: none;
            }

            i {
              width: 25px;
              height: 8px;
              border-radius: 2px;
              position: absolute;
              right: 5px;
              top: 0px;
            }

            .nocon {
              background: #fa6e86;
            }

            .constr {
              background: #d680ef;
            }

            .comple {
              background: #eeda84;
            }

            .accept {
              background: #1dd8c5;
            }

            p {
              margin: 0;
              color: #00ccff;
              font-size: 15px;
              margin: 3px 0;
              font-weight: bold;
            }

            span {
              color: #00ccff;
              font-size: 15px;
              margin: 3px 0;
              font-weight: bold;
            }
          }
        }
      }
    }
  }

  .task-overview-right {
    border-left: 1px solid #ebeef5;
    margin-left: 5px;
    flex: 1;
    position: relative;

    .rwType {
      position: absolute;
      left: 10px;
      top: 10px;
      z-index: 99;
    }

    .task-count {
      position: absolute;
      width: 100%;
      height: 100%;
      display: flex;
      align-items: center;
      justify-content: center;
      flex-direction: column;
      padding-top: 42px;

      p {
        font-size: 50px;
        color: #00ccff;
        margin: 0;
      }

      span {
        font-size: 30px;
        color: #cccccc;
        letter-spacing: 2px;
      }
    }
  }
}
</style>
