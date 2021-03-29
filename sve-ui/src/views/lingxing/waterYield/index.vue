<template>
  <div class="lx-wk">
    <div class="lx-left">
      <SourceLeft @selectSource="selectSource" tagHeadId="1003"></SourceLeft>
    </div>
    <div class="lx-mid">
      <div v-if="setSourceId" style="width:100%;height: 100%; overflow-y: auto;">
        <el-form ref="form" :model="form" label-width="60px" :inline="true">
          <el-form-item label="日期">
            <el-date-picker
              v-model="form.dataTime"
              type="daterange"
              value-format="yyyy-MM-dd"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
              :picker-options="pickerOptions"
              :clearable="false"
            ></el-date-picker>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
          </el-form-item>
        </el-form>
        <div class="waterMeter" v-if="meterData.rows.length>0">
          <h3 class="tb-title">水表流量 ( m³ )</h3>
          <ve-line
            :data="meterData"
            height="100%"
            :legend="commit.legend"
            :grid="commit.grid"
            :settings="commit.tankSetting"
          ></ve-line>
        </div>
        <!-- 水池 -->
        <el-radio-group v-model="dataType" size="mini" v-if="setSourceId" @change="typeChage">
          <el-radio-button label="hour">1/小时</el-radio-button>
          <el-radio-button label="minute">10/分钟</el-radio-button>
        </el-radio-group>
        <div class="waterTank" v-for="(i,index) in waterPoll" :key="index">
          <h3 class="tb-title">{{ i.name }}</h3>
          <ve-line
            :data="i"
            height="100%"
            :legend="commit.legend"
            :grid="commit.grid"
            :extend="commit.extend"
          ></ve-line>
        </div>
        <div class="waterTank" v-if="waterPollData.rows.length>0">
          <h3 class="tb-title">水池总水量</h3>
          <ve-line
            :data="waterPollData"
            height="100%"
            :tooltip="commit.tooltip"
            :legend="commit.legend"
            :grid="commit.grid"
            :extend="commit.extends"
            :settings="commit.tankSetting"
          ></ve-line>
        </div>
      </div>
      <div v-else class="noData">请选择需要查看的企业.</div>
    </div>
    <el-card class="box-card lx-right" v-if="setSourceId">
      <el-card class="box-card lx-ld-card" v-for="i in jobData" :key="i.jobDetailid">
        <el-form label-width="100px" label-position="top" class="ldStyle">
          <el-form-item label="时间:">{{ i.jobDate }}</el-form-item>
          <el-form-item label="工作单号:">{{ i.jobHeadid }}</el-form-item>
          <el-form-item label="联单号:">{{ i.transbillid }}</el-form-item>
          <el-form-item label="计算数量(吨):">{{ i.flowinamt }}</el-form-item>
          <el-form-item label="实际数量(吨):">{{ i.factinamt }}</el-form-item>
        </el-form>
      </el-card>
    </el-card>
  </div>
</template>
<script>
import SourceLeft from "@/components/sourceLeft";
import VeLine from "v-charts/lib/line.common";
import {
  meterinfoDay,
  storageHour,
  storageTotalHour,
  storageMinute,
  storageTotalMinute,
  transjobList,
} from "@/api/lingxing";

export default {
  components: {SourceLeft, VeLine},
  data () {
    this.commit = {
      legend: {
        show: true,
        top: 10,
        textStyle: {
          color: "#000",
          fontSize: 11,
        },
      },
      grid: {
        top: "50px",
        left: "0",
        right: "0",
        bottom: "10px",
        containLabel: true,
      },
      extend: {
        series: {
          smooth: false,
          // type: "line",
          // step: "start",
        },
      },
      extends: {
        series: {
          smooth: false,
          // type: "line",
          // step: "start",
        },
        // yAxis: {
        //   axisLabel: {
        //     formatter: function (value) {
        //       return value.toFixed(3);
        //     }
        //   }
        // }
      },
      tooltip: {
        formatter: (v, i) => {
          for (var i = 0; i < v.length; i++) {
            return `${v[i].name}<br />${v[i].marker}${v[i].seriesName} : ${v[i].value[1]}`;
          }
        },
        trigger: "axis",
      },
      tankSetting: {
        labelMap: {
          count: "总量",
        },
      }
    };

    return {
      form: {dataTime: ""},
      timeOptionRange: "",
      pickerOptions: {
        disabledDate: (time) => {
          return time.getTime() > Date.now() - 8.64e7;
          // let timeOptionRange = this.timeOptionRange;
          // let secondNum = 60 * 60 * 24 * 7 * 1000;
          // if (timeOptionRange) {
          //   return (
          //     time.getTime() > timeOptionRange.getTime() + secondNum ||
          //     time.getTime() < timeOptionRange.getTime() - secondNum
          //   );
          // } else {
          //   return time.getTime() > Date.now() - 8.64e6;
          // }
        },
        // onPick: (time) => {
        //   //当第一时间选中才设置禁用
        //   if (time.minDate && !time.maxDate) {
        //     this.timeOptionRange = time.minDate;
        //   }
        //   if (time.maxDate) {
        //     this.timeOptionRange = null;
        //   }
        // },
      },
      setSourceId: null,
      //水表数据
      meterData: {
        columns: [],
        rows: [],
      },
      //废水收集池
      tankData: {
        columns: ["date", "水量", "液位高度"],
        rows: [],
      },
      waterPoll: [],
      waterPollData: {columns: ["date", "count"], rows: []},
      dataType: "hour",
      //联单
      jobData: [],
    };
  },
  created () {
    let oDate = this.parseTime(new Date()).substr(0, 10);
    this.form.dataTime = [this.fun_date(-7), this.fun_date(-1)];
  },
  methods: {
    getLdList (v = {}) {
      transjobList(v).then((res) => {
        if (res.code === 200) {
          this.jobData = res.data;
        }
      });
    },
    getStoragHour (v) {
      storageHour(v).then((res) => {
        if (res.code === 200) {
          let oRes = res.data;
          oRes.forEach((i) => {
            i.columns = ["date", "水量", "液位高度"];
            i.rows = i.data;
          });
          this.waterPoll = res.data;
        }
      });
    },
    getTotalStoragHour (v) {
      storageTotalHour(v).then((res) => {
        if (res.code === 200) {
          this.waterPollData.rows = res.data;
        }
      });
    },
    storageMinute (v) {
      storageMinute(v).then((res) => {
        if (res.code === 200) {
          let oRes = res.data;
          oRes.forEach((i) => {
            i.columns = ["date", "水量", "液位高度"];
            i.rows = i.data;
          });
          this.waterPoll = res.data;
        }
      });
    },
    getTotalStoragMinute (v) {
      storageTotalMinute(v).then((res) => {
        if (res.code === 200) {
          this.waterPollData.rows = res.data;
        }
      });
    },
    getMeterDay (v) {
      meterinfoDay(v).then((res) => {
        if (res.code === 200) {
          let oRes = res.data;
          let oArr = ["", ""];
          if (oRes.length > 0) {
            Object.keys(oRes[0]).forEach((i) => {
              if (i === "date") {
                oArr[0] = i;
              } else if (i === "count") {
                oArr[1] = i;
              } else {
                oArr.push(i);
              }
            });
          }
          this.meterData.columns = oArr;
          this.meterData.rows = oRes;
        }
      });
    },
    selectSource (v) {
      this.setSourceId = v.sourceId;
      let oVal = {
        sourceId: this.setSourceId,
        beginTime: this.form.dataTime[0],
        endTime: this.form.dataTime[1],
      };
      this.dataType = "hour";
      this.getMeterDay(oVal);
      this.getStoragHour(oVal);
      this.getTotalStoragHour(oVal);
      this.getLdList(oVal);
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
    },
    handleQuery () {
      let oVal = {
        sourceId: this.setSourceId,
        beginTime: this.form.dataTime[0],
        endTime: this.form.dataTime[1],
      };
      this.dataType = "hour";
      this.getMeterDay(oVal);
      this.getStoragHour(oVal);
      this.getTotalStoragHour(oVal);
      this.getLdList(oVal);
    },
    typeChage (v) {
      this.dataType = v;
      let oVal = {
        sourceId: this.setSourceId,
        beginTime: this.form.dataTime[0],
        endTime: this.form.dataTime[1],
      };
      if (this.dataType === "hour") {
        this.getStoragHour(oVal);
        this.getTotalStoragHour(oVal);
      } else {
        this.storageMinute(oVal);
        this.getTotalStoragMinute(oVal);
      }
    },
  },
};
</script>
<style lang="scss" scope>
.lx-wk {
  width: 100%;
  height: 100%;
  display: flex;
  overflow: hidden;

  .lx-left {
    width: 300px;
    height: 100%;
  }

  .lx-mid {
    flex: 1;
    height: 100%;
    padding: 10px;

    .waterMeter {
      height: 250px;
      display: flex;
      flex-direction: column;
      overflow: hidden;
    }

    .waterTank {
      height: 250px;
      display: flex;
      flex-direction: column;
      overflow: hidden;
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

  .lx-right {
    width: 230px;
    height: 100%;
    overflow-y: auto;

    .el-card__body {
      padding: 10px;
    }

    .lx-ld-card {
      margin-bottom: 10px;

      .el-card__body {
        padding: 10px;

        .el-form-item {
          margin-bottom: 5px;
        }
      }
    }

    .ldStyle {
      .el-form-item__label,
      .el-form-item__content {
        padding: 0;
        line-height: 26px;
      }
    }
  }

  .noData {
    width: 100%;
    height: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 13px;
    color: #999;
    letter-spacing: 2px;
  }
}
</style>
