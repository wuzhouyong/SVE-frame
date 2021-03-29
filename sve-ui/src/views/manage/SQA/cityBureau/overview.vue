<template>
  <div class="task-overview">
    <div class="task-overview-left">
      <ul>
        <li
          v-for="i in oList"
          :key="i.code"
          :class="{'checked':checkTagList.indexOf(i.code)>-1}"
        >
          <!--          @click="viewClick(i.code)"-->
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
      <!--      <div class="task-count">-->
      <!--        <p>{{ count }}</p>-->
      <!--        <span>任务数</span>-->
      <!--      </div>-->
      <ve-histogram
        height="500px"
        :data="chartData"
        :extend="hisConfig.extend"
        :legend="hisConfig.legend"
        :settings="hisConfig.settings">
      </ve-histogram>
    </div>
  </div>
</template>
<script>
import {countSchedule, countSummary, notlogin} from "@/api/analysis";
import {boardSystem} from "@/config";
// import VeRing from "v-charts/lib/ring.common.js";
import VeHistogram from "v-charts/lib/histogram.common.js";

export default {
  props: {
    chooseTownCode: {
      type: String | null,
      default: null
    }
  },
  components: {VeHistogram},
  data () {
    this.hisConfig = {
      settings: {
        labelMap: {
          "num": "数量"
        }
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
        series (v) {
          // 设置柱子的样式
          v.forEach((i, index) => {
            i.barWidth = 26;
            i.itemStyle = {
              borderWidth: 1,
              normal: {
                color: function (params) {
                  let colorList = ["#fa6e86", "#d680ef", "#eeda84", "#1dd8c5"];
                  return colorList[params.dataIndex];
                }
              }
            };
            i.label = {
              color: "#fff",
              show: true,
              position: "top",
              fontSize: 11
            };
          });
          return v;
        },
        "grid.bottom": "20px",
        "grid.top": "50px",
        "yAxis.0.axisLabel.color": "#fff",
        xAxis: {
          axisLabel: {
            show: true,
            // rotate: 20, //倾斜
            textStyle: {
              color: "#fff",
              fontSize: 12
            },
            rich: {
              labelText: {
                lineHeight: 18
              }
            }
          }
        }
      }
    };
    return {
      oList: [],
      chartData: {
        columns: ["name", "num"],
        rows: []
      },
      count: 0,
      checkTagList: []
    };
  },
  watch: {
    chooseTownCode: {
      handler (v) {
        if (v !== null && v !== "") {
          this.getCon({townCode: v});
          this.getSummary({townCode: v});
        } else {
          this.getCon();
          this.getSummary();
        }
      },
      immediate: true
    }
  },
  methods: {
    getCon (v = {}) {
      countSchedule(v).then(res => {
        if (res.code === 200) {
          let oRes = res.data;
          let oArr = [];
          boardSystem.forEach(i => {
            oArr.push(oRes.find(j => j.code.toString() === i.id));
          });
          this.oList = oArr;
          console.log(this.oList);
        }
      });
    },
    getPencent (num, total) {
      num = parseFloat(num);
      total = parseFloat(total);
      if (isNaN(num) || isNaN(total)) {
        return "-";
      }
      return total <= 0
        ? "0%"
        : Math.round((num / total) * 10000) / 100.0 + "%";
    },
    getSummary (v = {}) {
      let oMap = {
        noconnected: "未开展",
        constructed: "施工中",
        completed: "已接入",
        accepted: "已验收"
      };

      countSummary(v).then(res => {
        let oRes = res.data;
        this.count = res.data.count;
        let oArr = [
          {name: "未登录", num: 0},
          {name: "施工中", num: oRes.constructed},
          {name: "已接入", num: oRes.completed},
          {name: "已验收", num: oRes.accepted}];
        notlogin(v).then(res => {
          if (res.code === 200) {
            oArr[0].num = res.data;
          }
        });
        // Object.keys(oRes).forEach(i => {
        //   let obj = {};
        //   if (i !== "count" && i !== "noconnected") {
        //     obj.name = oMap[i];
        //     obj.num = oRes[i];
        //     oArr.push(obj);
        //   }
        // });

        this.chartData.rows = oArr;
      });
    },
    viewClick (v) {
      if (this.checkTagList.indexOf(v) > -1) {
        this.checkTagList.splice(
          this.checkTagList.findIndex(item => item === v),
          1
        );
      } else {
        this.checkTagList.push(v);
      }
      let oVal = {
        tagHeadIds: this.checkTagList.join(",")
      };
      if (this.chooseTownCode !== null && this.chooseTownCode !== "") {
        oVal.townCode = this.chooseTownCode;
      }
      this.getSummary(oVal);
    }
  }
};
</script>
<style lang="scss">
.task-overview {
  width: 100%;
  height: auto;
  display: flex;
  overflow: hidden;

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
          overflow: hidden;

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
    //height: 100%;
    overflow: hidden;

    .task-count {
      position: absolute;
      width: 100%;
      height: 100%;
      display: flex;
      align-items: center;
      justify-content: center;
      flex-direction: column;
      padding-top: 24px;

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
