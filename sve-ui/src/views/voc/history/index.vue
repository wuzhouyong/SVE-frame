<template>
  <div class="workCondit-wk">
    <div class="workCondit-left">
      <SourceLeft @selectSource="selectSource" tagHeadId="1004"></SourceLeft>
    </div>
    <div class="workCondit-right">
      <div class="workCondit-right-con" v-if="queryForm.sourceId">
        <!-- 搜索表单 -->
        <el-form :model="queryForm" ref="queryForm" :inline="true" class="youyan-serchForm">
          <!-- <el-form-item label="排放口">
            <el-select v-model="queryForm.port" placeholder="请选择排放口">
              <el-option label="排放口1#" value="1"></el-option>
              <el-option label="排放口2#" value="2"></el-option>
            </el-select>
          </el-form-item>-->
          <el-form-item label="日期">
            <el-date-picker
              type="date"
              placeholder="选择日期"
              v-model="queryForm.time"
              :picker-options="pickerOptions"
              value-format="yyyy-MM-dd"
              style="width: 100%;"
            ></el-date-picker>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
          </el-form-item>
        </el-form>
        <!-- 生产线用电功率 -->
        <div class="electric">
          <h3 class="tb-title">生产线用电功率</h3>
          <ve-line :data="electricData" height="100%" :legend="commit.legend" :grid="commit.grid"></ve-line>
        </div>
        <!-- 收集、排放风机开关状态 -->
        <div class="exhaustfan">
          <h3 class="tb-title">收集、排放风机开关状态</h3>
          <ve-line
            :data="exhaustfanData"
            height="100%"
            :legend="commit.legend"
            :grid="commit.grid"
            :extend="commit.extend"
          ></ve-line>
        </div>
        <!-- 治理设施工况-治理设施1# -->
        <div class="facilities" v-for="(i,index) in cols" :key="index">
          <h3 class="tb-title">{{ i.name }}</h3>
          <div class="ex-con" v-if="switch_w.some(o=>o.id===i.id)">
            <ve-line
              :data="switch_w[index]"
              height="200px"
              :legend="commit.legend"
              :grid="commit.grid"
              :extend="commit.extend"
              :settings="chartSettings"
            ></ve-line>
          </div>
          <div class="ex-con" v-if="el_w.some(o=>o.id===i.id)">
            <ve-line :data="el_w[index]" height="200px" :legend="commit.legend" :grid="commit.grid"></ve-line>
          </div>
        </div>
        <!-- voc -->
        <div class="voc">
          <h3 class="tb-title">排放口</h3>
          <ve-line :data="vocData" height="100%" :legend="commit.legend" :grid="commit.grid"></ve-line>
        </div>
      </div>
      <div class="noneData">请选择查看企业.</div>
    </div>
  </div>
</template>
<script>
import SourceLeft from "@/components/sourceLeft";
import VeLine from "v-charts/lib/line.common";
import { workingGetinfo } from "@/api/voc";

export default {
  components: { SourceLeft, VeLine },
  data() {
    this.commit = {
      legend: {
        show: true,
        top: 10,
        textStyle: {
          color: "#000",
          fontSize: 11
        }
      },
      grid: {
        top: "50px",
        left: "0",
        right: "0",
        bottom: "10px",
        containLabel: true
      },
      extend: {
        series: {
          smooth: false,
          type: "line",
          step: "start"
        }
      }
    };
    this.chartSettings = {
      labelMap: {
        voc_uvgj: "UV光解",
        voc_hxt: "活性炭吸附",
        voc_pl: "喷淋",
        voc_chrs: "催化燃烧"
      }
    };
    return {
      queryForm: { time: "", sourceId: "" },
      //生产线用电功率数据
      electricData: {
        columns: [],
        rows: []
      },
      //收集、排放风机开关数据
      exhaustfanData: {
        columns: [],
        rows: []
      },
      //治理设施
      facilOneData: {
        columns: [],
        rows: []
      },
      facilTwoData: {
        columns: [],
        rows: []
      },
      //voc数据
      vocData: {
        columns: [],
        rows: []
      },
      pickerOptions: {
        disabledDate(time) {
          return time.getTime() > Date.now() - 8.64e7;
        }
      },
      cols: [],
      switch_w: [],
      el_w: []
    };
  },
  created() {
    this.queryForm.time = this.fun_date(-1);
  },
  methods: {
    selectSource(v) {
      this.queryForm.sourceId = v.sourceId;
      this.getData();
    },
    getData() {
      workingGetinfo(this.queryForm).then((res) => {
        if (res.code === 200) {
          let line = res.data.line_meter;
          this.electricData.columns = Array.from([
            "时间",
            ...new Set(line.map((m) => m.facilityName))
          ]);
          this.electricData.rows = line.reduce((arr, cur) => {
            var obj = arr.find((s) => s.时间 === cur.datadate);
            if (!obj) {
              obj = { 时间: cur.datadate };
              arr.push(obj);
            }
            obj[cur.facilityName] = cur.avgAmt;
            return arr;
          }, []);

          let gather = res.data.gather_fan;
          this.exhaustfanData.columns = Array.from([
            "时间",
            ...new Set(gather.map((m) => m.facilityName))
          ]);
          this.exhaustfanData.rows = gather.reduce((arr, cur) => {
            var obj = arr.find((s) => s.时间 === cur.datadate);
            if (!obj) {
              obj = { 时间: cur.datadate };
              arr.push(obj);
            }
            obj[cur.facilityName] = cur.switch;
            return arr;
          }, []);

          let discharge = res.data.discharge;
          this.vocData.columns = Array.from([
            "时间",
            ...new Set(discharge.map((m) => m.elementDesc))
          ]);
          this.vocData.rows = discharge.reduce((arr, cur) => {
            var obj = arr.find((s) => s.时间 === cur.datadate);
            if (!obj) {
              obj = { 时间: cur.datadate };
              arr.push(obj);
            }
            obj[cur.elementDesc] = cur.avgAmt;
            return arr;
          }, []);
          let techno = res.data.techno;
          let facility = res.data.facility;
          var cols = [];
          var data = techno.reduce((arr, cur) => {
            var obj = arr.find((s) => s.id === cur.facilityId);
            if (!obj) {
              obj = { id: cur.facilityId, name: cur.elementDesc, rows: [] };
              obj.columns = Array.from([
                "时间",
                ...new Set(techno.map((m) => m.elementDesc))
              ]);
              cols.push({ id: cur.facilityId, name: cur.facilityName });
              arr.push(obj);
            }
            var obj1 = obj.rows.find((s) => s.时间 === cur.datadate);
            if (!obj1) {
              obj1 = { 时间: cur.datadate };
              obj.rows.push(obj1);
            }
            obj1[cur.elementDesc] = cur.avgAmt;
            return arr;
          }, []);

          var data1 = facility.reduce((arr, cur) => {
            var obj = arr.find((s) => s.id === cur.facilityId);
            if (!obj) {
              obj = {
                id: cur.facilityId,
                name: cur.facilityName,
                rows: []
              };
              obj.columns = Array.from([
                "时间",
                ...new Set(
                  facility
                    .filter((p) => p.facilityId === cur.facilityId)
                    .map((m) => m.techType)
                )
              ]);
              cols.push({ id: cur.facilityId, name: cur.facilityName });
              arr.push(obj);
            }
            var obj1 = obj.rows.find((s) => s.时间 === cur.datadate);
            if (!obj1) {
              obj1 = { 时间: cur.datadate };
              obj.rows.push(obj1);
            }
            obj1[cur.techType] = cur.switch;
            return arr;
          }, []);
          this.cols = cols.reduce((arr, cur) => {
            if (!arr.some((s) => s.id === cur.id)) {
              arr.push(cur);
            }
            return arr;
          }, []);
          this.switch_w = data1;
          this.el_w = data;
          console.log(this.el_w);
        }
      });
    },
    handleQuery() {
      this.getData();
    },
    fun_date(num) {
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
    }
  }
};
</script>
<style lang="scss" scope>
.workCondit-wk {
  width: 100%;
  height: 100%;
  overflow: hidden;
  display: flex;

  .workCondit-left {
    width: 300px;
    height: 100%;
  }

  .workCondit-right {
    flex: 1;
    padding: 10px;
    height: 100%;
    overflow: hidden;

    .noneData {
      width: 100%;
      height: 100%;
      display: flex;
      align-items: center;
      justify-content: center;
      font-size: 13px;
      color: #999;
      letter-spacing: 3px;
    }

    .workCondit-right-con {
      width: 100%;
      height: 100%;
      overflow-y: auto;
      overflow-x: hidden;
      padding-right: 8px;
      box-sizing: border-box;
    }

    .tb-title {
      height: 30px;
      line-height: 30px;
      border-bottom: 1px solid #dcdfe6;
      font-size: 14px;
      padding-left: 10px;
      margin: 0;
    }

    .el-divider--horizontal {
      margin: 10px;
    }

    .electric {
      height: 250px;
      display: flex;
      flex-direction: column;
      overflow: hidden;
    }

    .exhaustfan {
      height: 250px;
      display: flex;
      flex-direction: column;
      overflow: hidden;
    }

    .facilities {
      height: auto;
      display: flex;
      flex-direction: column;
      overflow: hidden;

      .ex-con {
        flex: 1;
        overflow: hidden;
        width: 100%;
      }
    }

    .voc {
      height: 250px;
      display: flex;
      flex-direction: column;
      overflow: hidden;
    }
  }
}
</style>
