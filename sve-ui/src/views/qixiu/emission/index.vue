<template>

  <!--  排放量amt,最高浓度max_amt,平均浓度avg_amt-->
  <div class="task-swage-wk">
    <div class="selectMonth">
      <el-form label-width="80px">
        <el-form-item label="选择月份">
          <el-date-picker
            value-format="yyyy-MM"
            v-model="monthVal"
            style="width:120px"
            type="month"
            @change="dateChange">
          </el-date-picker>
        </el-form-item>
      </el-form>
      <el-radio-group v-model="oType" size="mini" @change="typeChange">
        <el-radio-button label="amt">排放量</el-radio-button>
        <el-radio-button label="max_amt">最高浓度</el-radio-button>
        <el-radio-button label="avg_amt">平均浓度</el-radio-button>
      </el-radio-group>
    </div>
    <div class="charts_wk">
      <el-row :gutter="10">
        <el-col :lg="12" :md="12" :sm="24">
          <div class="charts_left">
            <div class="psh-kanban-title">
              <i class="el-icon-s-data"></i>
              <p>各镇街排放统计</p>
            </div>
            <ve-bar
              :data="townData"
              height="100%"
              :legend-visible="false"
              :extend="townConfig.extend"
              :events="chartEvents"
              :settings="townConfig.setting"
            ></ve-bar>
          </div>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <div class="charts_right">
            <div class="charts_right_top">
              <div class="psh-kanban-title">
                <i class="el-icon-s-data"></i>
                <p>全市近半年排放量统计</p>
              </div>
              <ve-line
                :data="emissionseCity"
                height="100%"
                :extend="emissCity.extend"
                :settings="emissCity.setting"
                :colors="emissCity.colors"
                :legend="emissCity.legend"
              ></ve-line>
            </div>
            <div class="charts_right_btm">
              <div class="psh-kanban-title">
                <i class="el-icon-s-data"></i>
                <p>{{ townName ? townName : '镇街' }}近半年({{ oType === 'max_amt' ? '最高浓度' : '平均浓度' }})统计</p>
              </div>
              <ve-histogram
                :data="emissionseTown"
                height="100%"
                :extend="emissTown.extend"
                :settings="emissTown.setting"
                :colors="emissTown.colors"
                :legend="emissTown.legend"
              ></ve-histogram>
            </div>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import VeBar from "v-charts/lib/bar.common";
import VeLine from 'v-charts/lib/line'
import VeHistogram from "v-charts/lib/histogram.common";
import {qxTownEmission, qxEnterpriseEmission} from "@/api/qixiu";

export default {
  components: {
    VeBar,
    VeHistogram,
    VeLine
  },
  created() {
    var time = new Array();
    for (let allinfo = this.lastYearMoth(), i = 0; i < allinfo.length; i++) {
      time[i] = allinfo[i];
    }
    this.monthVal = time[0];
    this.getTownEmission({dataDate: time[0]});
    this.getCityEmission();
  },
  watch: {
    townName: {
      handler(v) {
        qxEnterpriseEmission({townName: v}).then(res => {
          if (res.code === 200) {
            this.emissionseTown.rows = res.data;
          }
        })
      }
    },
  },
  data() {
    this.townConfig = {
      setting: {
        labelMap: {
          amt:'排放量',
          max_amt: "最高浓度",
          avg_amt: "平均浓度"
        }
      },
      extend: {
        xAxis: {
          axisLabel: {
            color: "#fff",
          }
        },
        yAxis: {
          axisLabel: {
            color: "#fff"
          }
        },
        "grid.top": "10px",
        "grid.bottom": "70px",
        series(v) {
          if (v.length > 0) {
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
      }
    };
    this.chartEvents = {
      click: (e) => {
        this.townName = e.name;
      }
    }
    //排放量镇街配置
    this.emissTown = {
      legend: {
        show: true,
        textStyle: {
          color: "#fff",
          fontSize: 12
        },
        y: 10
      },
      colors: ["#FF7575"],
      setting: {
        labelMap: {
          amt:'排放量',
          max_amt: "最高浓度",
          avg_amt: "平均浓度"
        }
      },
      extend: {
        series(v) {
          // 设置柱子的样式
          if (v.length > 0) {
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
          }
        },
        "grid.top": "50px",
        "grid.bottom": "60px",
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
            textStyle: {
              color: "#fff",
              fontSize: 12
            }
          }
        }
      }
    }
    //排放量全市配置
    this.emissCity = {
      legend: {
        show: true,
        textStyle: {
          color: "#fff",
          fontSize: 12
        },
        y: 10
      },
      colors: ["#2894FF"],
      setting: {
        labelMap: {
          amt:'排放量',
          max_amt: "最高浓度",
          avg_amt: "平均浓度"
        }
      },
      extend: {
        series(v) {
          // 设置柱子的样式
          if (v.length > 0) {
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
          }
        },
        "grid.top": "50px",
        "grid.bottom": "60px",
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
            textStyle: {
              color: "#fff",
              fontSize: 12
            }
          }
        }
      }
    }
    return {
      townData: {
        columns: ["town_name", "amt"],
        rows: []
      },
      emissionseCity: {
        columns: ['datadate', 'amt'],
        rows: []
      },
      emissionseTown: {
        columns: ['datadate', 'amt'],
        rows: []
      },
      monthVal: null,
      townName: null,
      oType: "amt"
    }
  },
  methods: {
    getTownEmission(v) {
      qxTownEmission(v).then(res => {
        if (res.code === 200) {
          this.townData.rows = res.data.sort((a, b) => a[this.oType] - b[this.oType]);
        }
      })
    },
    getCityEmission(v = {}) {
      qxEnterpriseEmission(v).then(res => {
        if (res.code === 200) {
          this.emissionseCity.rows = res.data;
        }
      })
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
    dateChange(v) {
      this.getTownEmission({dataDate: v});
    },
    typeChange(v) {
      this.$set(this.townData, 'columns', ['town_name', v]);
      this.townData.rows = this.townData.rows.sort((a, b) => a[v] - b[v]);
      this.$set(this.emissionseCity, 'columns', ['datadate', v]);
      this.$set(this.emissionseTown, 'columns', ['datadate', v]);
      // this.townData.columns[1] = v;
    }
  }
}
</script>

<style lang="scss" scoped>


.task-swage-wk {
  position: relative;
  width: 100%;
  height: 100%;
  overflow: hidden;
  padding: 5px 10px;
  display: flex;
  flex-direction: column;

  .selectMonth {
    padding: 5px;
    display: flex;
    align-items: center;

    /deep/ .el-form {
      margin: 0;
      padding: 0;
      margin-right: 10px;

      .el-form-item {
        margin: 0;
      }
    }

    //position: absolute;
    //right: 30px;
    //top: 0px;
  }

  /deep/ .el-tabs__item {
    height: 30px;
    line-height: 30px;
  }

  /deep/ .el-tabs {
    .el-tabs__header {
      margin-bottom: 5px;
    }
  }
}

.p_memo {
  color: #e6a23c;
  margin: 0;
  padding: 0;
  font-size: 12px;
  margin-bottom: 5px;
}

.charts_wk {
  width: 100%;
  flex: 1;
  overflow: hidden;

  /deep/ .el-form-item__label {
    font-size: 12px;
    color: #fff;
  }

  .el-row {
    width: 100%;
    height: 100%;

    .el-col {
      height: 100%;
    }

    .charts_left {
      width: 100%;
      height: 100%;
      background: rgba(0, 21, 41, 0.8);
      box-shadow: 0 0px 0px rgba(0, 0, 0, 0.3), 0 0 4px rgba(0, 0, 0, 0.9);
    }

    .charts_right {
      width: 100%;
      height: 100%;
      display: flex;
      overflow: hidden;
      flex-direction: column;

      .charts_right_top {
        width: 100%;
        height: 380px;
        background: rgba(0, 21, 41, 0.8);
        box-shadow: 0 0px 0px rgba(0, 0, 0, 0.3), 0 0 4px rgba(0, 0, 0, 0.9);
      }

      .charts_right_btm {
        margin-top: 10px;
        width: 100%;
        flex: 1;
        background: rgba(0, 21, 41, 0.8);
        box-shadow: 0 0px 0px rgba(0, 0, 0, 0.3), 0 0 4px rgba(0, 0, 0, 0.9);
        overflow: hidden;
      }
    }
  }

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
    height: 28px;
    line-height: 28px;
    border-left: 4px solid rgba(17, 217, 255, 0.8);
    padding-left: 10px;
    color: #fff;
    font-size: 12px;
    letter-spacing: 1px;
    align-items: center;

    p {
      padding: 0;
      margin: 0;
    }
  }
}
</style>
