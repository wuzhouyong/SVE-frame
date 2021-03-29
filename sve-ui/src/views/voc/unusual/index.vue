<template>
  <div class="task-wk">
    <!--    异常企业统计查询-->
    <div class="unusual_wk">
      <div class="unusual_left">
        <div class="psh-kanban-title">
          <i class="el-icon-s-data"></i>
          <p>近7天各镇街异常企业数</p>
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
      <div class="unusual_right">
        <el-radio-group v-model="warnLevel" size="mini" style="margin-bottom:5px;" @change="warnChange">
          <el-radio-button label="-1">全部</el-radio-button>
          <el-radio-button label="1">一级异常</el-radio-button>
          <el-radio-button label="2">二级异常</el-radio-button>
          <el-radio-button label="3">三级异常</el-radio-button>
        </el-radio-group>
        <el-table
          :data="tableData"
          v-loading="loading"
          style="width: 100%;flex:1"
          size="mini"
          border>
          <el-table-column
            label="镇街"
            width="90px">
            <template>
              {{ townName }}
            </template>
          </el-table-column>
          <el-table-column
            prop="full_name"
            label="企业名称">
          </el-table-column>
          <el-table-column
            prop="one"
            label="一级异常">
          </el-table-column>
          <el-table-column
            prop="two"
            label="二级异常">
          </el-table-column>
          <el-table-column
            prop="three"
            label="三级异常">
          </el-table-column>
        </el-table>
      </div>
    </div>
  </div>
</template>

<script>
import VeBar from "v-charts/lib/bar.common";
import {vocTownWarnAmt, vocTownWarnEnterprise} from '@/api/outwater'

export default {
  components: {
    VeBar
  },
  data() {
    this.townConfig = {
      setting: {
        labelMap: {
          town_name: '镇街',
          cnt: "企业数",
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
            // formatter: (v, i) => {
            //   return v + " ( " + this.numList[i] + " ) ";
            // }
          }
        },
        "grid.top": "10px",
        "grid.right": "60px",
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
    return {
      townData: {
        columns: ["town_name", "cnt"],
        rows: []
      },
      tableData: [],
      townName: null,
      warnLevel: "-1",
      loading: false,
    }
  },
  watch: {
    townName: {
      handler() {
        this.loading = true;
        this.getSourceList();
      }
    }
  },
  created() {
    this.getChartsData();
  },
  methods: {
    getSourceList() {
      vocTownWarnEnterprise(this.townName).then(res => {
        this.tableData = res.data;
        this.loading = false;
      })
    },
    getChartsData() {
      let oV = this.warnLevel !== '-1' ? {warnLevel: this.warnLevel} : '';
      vocTownWarnAmt(oV).then(res => {
        if (res.code === 200) {
          if (res.data.length > 0) {
            this.townName = res.data[res.data.length - 1].town_name;
          }
          this.townData.rows = res.data;
        }
      })
    },
    warnChange(v) {
      this.warnLevel = v;
      this.tableData = [];
      this.getChartsData();
    }
  }
}
</script>

<style lang='scss' scoped>
.unusual_wk {
  width: 100%;
  height: 100%;
  display: flex;
  overflow: hidden;

  .unusual_left {
    width: 500px;
    height: 100%;
    background: rgba(0, 21, 41, 0.8);
    box-shadow: 0 0px 0px rgba(0, 0, 0, 0.3), 0 0 4px rgba(0, 0, 0, 0.9);
  }

  .unusual_right {
    flex: 1;
    height: 100%;
    margin-left: 14px;
    display: flex;
    flex-direction: column;
    overflow: hidden;
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
</style>
