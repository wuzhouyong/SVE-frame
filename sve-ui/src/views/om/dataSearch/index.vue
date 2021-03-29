<template>
  <div class="task-wk">
    <el-form :model="queryForm" ref="queryForm" :inline="true" class="guocheng-serchForm">
      <el-form-item label="企业名称" prop="sourceId">
        <el-select v-model="queryForm.sourceId" placeholder="请选择企业名称" style="width:300px" filterable size="mini"
                   @change="sourceChange">
          <el-option :label="i.zdatasourname" :value="i.zdatasourid" v-for="i in sourceList"
                     :key="i.zdatasourid"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="排放口" prop="checkpointid">
        <el-select v-model="queryForm.checkpointid" placeholder="请选择排放口" style="width:200px" size="mini">
          <el-option :label="i.zcheckpointdesc" :value="i.zcheckpointid" v-for="i in pointList"
                     :key="i.zcheckpointid"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="日期范围" prop="dateTime" v-if="dataType==='day'">
        <el-date-picker
          v-model="queryForm.dateTime"
          :clearable="false"
          style="width:280px"
          :picker-options="pickerOptions"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="时间" prop="startDate" v-else>
        <el-date-picker
          type="date"
          placeholder="选择日期"
          v-model="queryForm.startDate"
          :picker-options="pickerOptions"
          value-format="yyyy-MM-dd"
          style="width:150px"
        ></el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-radio-group v-model="dataType" size="mini" @change="onChange">
          <el-radio-button label="minute">分钟均值</el-radio-button>
          <el-radio-button label="hour">小时均值</el-radio-button>
          <el-radio-button label="day">日均值</el-radio-button>
        </el-radio-group>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery" v-preventClick
                   :loading="loading">{{ loading ? "数据搜索中..." : "搜索" }}
        </el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <div style="flex:1;overflow: hidden" class="realTable-style">
      <el-table
        :data="tableData"
        style="width: 100%;"
        height="100%"
        v-loading="loading"
        border
        size="mini">
        <el-table-column
          prop="date"
          label="时间"
          min-width="140"
          align="center"
          size="mini">
        </el-table-column>
        <template v-for="i in tableHeader">
          <!--            因子为100041的时候读取100040值,显示烟气排放量(m³)-->
          <el-table-column v-if="i.elementid===100041" label="烟气排放量(m³)"
                           align="center" min-width="140">
            <template slot-scope="scope">
              <div v-html="returnHtml(scope.row,100041)"></div>
            </template>
          </el-table-column>
          <el-table-column v-else-if="i.elementid===100002" :label="i.elementdesc"
                           align="center" min-width="140">
            <template slot-scope="scope">
              <div v-html="returnHtml(scope.row,100002)"></div>
            </template>
          </el-table-column>
          <!--                    有上限或限值-->
          <el-table-column v-else :label="i.elementdesc" align="center">
            <!--            因子是ph的时候限值区间-->
            <el-table-column :label="'限值:' +i.minvalue+'-'+i.maxvalue" v-if="i.elementid===100035"
                             align="center" :prop="'val_'+i.elementid" min-width="140">
              <template slot-scope="scope">
                <div v-html="returnHtml(scope.row,i.elementid)"></div>
              </template>
            </el-table-column>
            <!--            有上限的因子-->
            <el-table-column v-else :label="'上限:' +i.maxvalue+i.unit"
                             align="center" :prop="'val_'+i.elementid" min-width="140">
              <template slot-scope="scope">
                <div v-html="returnHtml(scope.row,i.elementid)"></div>
              </template>
            </el-table-column>
          </el-table-column>
        </template>
      </el-table>
    </div>
  </div>
</template>

<script>
import {dgomEnterprise, dgomCheckPoint, dgomMonitorHearderHM, dgomMonitorHearderDay, dgomMonitorData} from "@/api/om";
import {fun_date} from "@/utils/ruoyi";

export default {
  data () {
    return {
      queryForm: {
        sourceId: null,
        checkpointid: null,
        dateTime: null,
        startDate: null
      },
      sourceList: [],
      pointList: [],
      loading: false,
      pickerOptions: {
        disabledDate: this.disabledDate
      },
      dataType: "minute",
      tableData: [],
      tableHeader: []
    };
  },
  created () {
    this.getEnterprise();
    let oDate = this.parseTime(new Date()).substr(0, 10);
    // this.queryForm.dateTime = [oDate, oDate];
    this.queryForm.startDate = oDate;
  },
  methods: {
    returnHtml (v, elId) {
      if (v["val_100040"] && elId === 100041) {
        let oClass = v["val_100040"].flag === -1 ? "" : "color_" + v["val_100040"].flag;
        return `<span class="${oClass}">${v["val_100040"].value}</span>`;
      } else if (v["val_" + elId]) {
        let oClass = v["val_" + elId].flag === -1 ? "" : "color_" + v["val_" + elId].flag;
        return (`<span class="${oClass}">${v["val_" + elId].value}</span>`);
      }
    },
    //获取企业列表
    getEnterprise () {
      dgomEnterprise().then(res => {
        if (res.code === 200) {
          this.sourceList = res.data;
        }
      });
    },
    //获取排放口列表
    getEmission (v) {
      dgomCheckPoint(v).then(res => {
        if (res.code === 200) {
          this.pointList = res.data;
          if (res.data.length > 0) {
            this.queryForm.checkpointid = this.pointList[0].zcheckpointid;
          }
        }
      });
    },
    //选中企业
    sourceChange (v) {
      this.queryForm.checkpointid = "";
      this.getEmission(v);
    },
    handleQuery () {
      this.tableData = [];
      this.tableHeader = [];
      this.loading = true;
      let {checkpointid, sourceId, dateTime, startDate} = this.queryForm;
      let oVal = {};
      if (this.dataType === "day") {
        if (checkpointid && sourceId && dateTime) {
          oVal = {
            startDate: dateTime[0],
            endDate: dateTime[1],
            checkpointid: checkpointid,
            sourceId: sourceId
          };
        } else {
          this.msgError("请完整选择条件.");
          this.loading = false;
          return;
        }
      } else {
        if (checkpointid && sourceId && startDate) {
          oVal = {
            checkpointid: checkpointid,
            sourceId: sourceId,
            startDate: startDate
          };
        } else {
          this.msgError("请完整选择条件.");
          this.loading = false;
          return;
        }
      }
      if (this.dataType === "day") {
        dgomMonitorHearderDay(oVal).then(res => {
          if (res.code === 200) {
            let oRes = res.data;
            oRes = oRes.filter(i => !new Set([100029, 100040, 100046, 100070]).has(i.elementid));
            this.tableHeader = oRes;
          }
        });
      } else {
        dgomMonitorHearderHM(oVal).then(res => {
          if (res.code === 200) {
            let oRes = res.data;
            oRes = oRes.filter(i => !new Set([100029, 100040, 100046, 100070]).has(i.elementid));
            this.tableHeader = oRes;
          }
        });
      }
      dgomMonitorData(oVal, this.dataType).then(res => {
        if (res.code === 200) {
          this.tableData = res.data;
          this.loading = false;
        } else {
          this.loading = false;
        }
      });

    },
    resetQuery () {
      let oDate = this.parseTime(new Date()).substr(0, 10);
      this.queryForm = {
        sourceId: null,
        checkpointid: null,
        dateTime: [fun_date(-6), oDate],
        startDate: oDate
      };
      this.dataType = "minute";
      this.tableData = [];
    },
    disabledDate (time) {
      return time.getTime() > Date.now() - 8.64e6;
    },
    onChange (v) {
      if (v === "day") {
        let oDate = this.parseTime(new Date()).substr(0, 10);
        this.queryForm.dateTime = [fun_date(-6), oDate];
      }
    }
  }
};
</script>

<style lang="scss">
.realTable-style {
  .el-table {
    display: flex;
    flex-direction: column;
    overflow: hidden;

    .el-table__body-wrapper {
      height: 100% !important;
    }
  }
}

.color_0 {
  color: #000000;
}

.color_1 {
  color: green;
}

.color_2 {
  color: #999;
}

.color_3 {
  color: red
}
</style>
