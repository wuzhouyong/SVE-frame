<template>
  <!--  排放趋势分析-->
  <div class="trends-wk">
    <div class="trends-left">
      <n-sys-source-left @haveSysName="haveSysName" @selectSource="selectSource"></n-sys-source-left>
    </div>
    <div class="trends-right" v-if="collectList.length>0">
      <el-form :model="queryForm" ref="queryForm" :inline="true">
        <el-form-item label="数采" prop="collectId">
          <el-select v-model="queryForm.collectId" size="mini" style="width:100%" @change="collectChange">
            <el-option :label="i.name" :value="i.id" v-for="i in collectList" :key="i.id"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="因子" prop="cpElemId">
          <el-select v-model="queryForm.cpElemId" size="mini" style="width:100%" @change="elChange">
            <el-option :label="i.element_desc" :value="i.cp_elem_id" v-for="i in elementList"
                       :key="i.cp_elem_id"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="月份" prop="datadate">
          <el-date-picker
            v-model="queryForm.datadate"
            style="width: 180px"
            value-format="yyyy-MM"
            type="month"
            @change="elChange"
            placeholder="选择月">
          </el-date-picker>
        </el-form-item>
      </el-form>
      <ve-histogram
        :data="trendData"
        height="100%"
        :extend="trendSource.extend"
        :settings="trendSource.setting"
        :colors="trendSource.colors"
        :legend="trendSource.legend"
      ></ve-histogram>
    </div>
  </div>
</template>

<script>
import nSysSourceLeft from '@/components/sysSourceLeft'
import {currencyCheckpoint, currencyElement, currencyTrend} from '@/api/analysis'
import VeHistogram from "v-charts/lib/histogram.common";

export default {
  components: {nSysSourceLeft, VeHistogram},
  data() {
    this.trendSource = {
      legend: {
        show: true,
        textStyle: {
          color: "#000",
          fontSize: 12
        },
        y: 10
      },
      colors: ["#2894FF"],
      setting: {
        dataOrder: true,
        labelMap: {
          amt: "排放量",
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
                color: "#000",
                show: true,
                position: "top"
              };
            });
            return v;
          }
        },
        "grid.top": "50px",
        "grid.bottom": "90px",
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
            rotate: 90, //倾斜
            textStyle: {
              color: "#000",
              fontSize: 12
            }
          },
        }
      }
    }
    return {
      trendData: {
        columns: ["datadate", "amt"],
        rows: []
      },
      queryForm: {
        collectId: null,
        cpElemId: null,
        datadate: null
      },
      collectList: [],
      elementList: [],
      sysName: "zaozhi",
      sysList: {
        1003: 'lingxing',
        1002: 'guocheng',
        1015: 'jinshu',
        1019: 'zaozhi',
        1004: 'voc',
        1006: 'youyan',
        1013: 'qixiu'
      }
    }
  },
  created() {
    var time = new Array();
    for (let allinfo = this.lastYearMoth(), i = 0; i < allinfo.length; i++) {
      time[i] = allinfo[i];
    }
    this.queryForm.datadate = time[0];
  },
  methods: {
    getCheckPoint(v) {
      currencyCheckpoint(this.sysName, v).then(res => {
        this.collectList = res.rows;
      })
    },
    haveSysName(v) {
      this.sysName = this.sysList[v];
    },
    selectSource(v) {
      this.collectList = [];
      this.elementList = [];
      this.trendData.rows = [];
      this.queryForm.collectId = null;
      this.queryForm.cpElemId = null;
      this.getCheckPoint(v.sourceId);
    },
    collectChange(v) {
      this.elementList = [];
      this.queryForm.cpElemId = null;
      this.trendData.rows = [];
      currencyElement(this.sysName, v).then(res => {
        this.elementList = res.data;
        if (this.elementList.length > 0) {
          this.queryForm.cpElemId = this.elementList[0].cp_elem_id;
          currencyTrend(this.sysName, this.queryForm).then(res => {
            this.trendData.rows = res.data;
          })
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
    elChange() {
      if (this.queryForm.cpElemId && this.queryForm.datadate) {
        currencyTrend(this.sysName, this.queryForm).then(res => {
          this.trendData.rows = res.data;
        })
      }
    }
  }
}
</script>

<style lang='scss' scoped>
.trends-wk {
  width: 100%;
  height: 100%;
  overflow: hidden;
  padding: 10px;
  display: flex;

  .trends-left {
    width: 300px;
    height: 100%;
  }

  .trends-right {
    flex: 1;
    height: 100%;
    margin-left: 15px;
  }
}

</style>
