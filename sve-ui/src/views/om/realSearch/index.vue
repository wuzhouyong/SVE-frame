<template>
  <div class="task-wk">
    <el-form :model="queryForm" ref="queryForm" :inline="true" class="guocheng-serchForm">
      <el-form-item label="企业名称" prop="sourceName">
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
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery" v-preventClick
                   :loading="loading">{{ loading ? "数据搜索中..." : "搜索" }}
        </el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <div style="height:300px;">
      <ve-line :data="chartData" height="300px" :settings="lineConfig.settings" :extend="lineConfig.extend"
      ></ve-line>
    </div>
    <div style="flex:1;overflow: hidden" class="realTable-style">
      <el-table
        :data="tableData"
        style="width: 100%;"
        height="100%"
        border>
        <el-table-column
          prop="date"
          label="时间"
          min-width="140"
          align="center"
          size="mini">
        </el-table-column>
        <template v-for="i in tableHeader">
          <el-table-column :label="i.elementdesc"
                           :key="i.elementid" align="center" v-if="i.elementid!==100041">
            <el-table-column :label="'上限:' +i.maxvalue+i.unit" v-if="i.elementid!==100035"
                             align="center" :prop="'val_'+i.elementid" min-width="140"></el-table-column>
            <el-table-column :label="'限值:' +i.minvalue+'-'+i.maxvalue" v-if="i.elementid===100035"
                             align="center" :prop="'val_'+i.elementid" min-width="140"></el-table-column>
            <el-table-column :label="'下限:' +i.minvalue+i.unit"
                             v-if="i.elementid===100003||i.elementid===100004"
                             align="center" :prop="'val_'+i.elementid" min-width="140"></el-table-column>
          </el-table-column>
          <el-table-column :label="i.elementdesc+'\n'+'('+i.unit+')'"
                           :key="i.elementid" align="center" v-else :prop="'val_'+i.elementid" min-width="140">
          </el-table-column>
        </template>
      </el-table>
    </div>
  </div>
</template>

<script>
import { dgomEnterprise, dgomCheckPoint, dgomMonitorHearderReal, dgomMonitorReal } from "@/api/om"
import VeLine from "v-charts/lib/line.common";

export default {
  data() {
    this.lineConfig = {
      settings: {
        labelMap: {}
      },
      extend: {
        "grid.bottom": "20px",
        "grid.top": "60px",
        legend: {
          right: "center",
          top: "0",
          itemWidth: 12,
          itemHeight: 12
        }
      }
    }
    return {
      queryForm: {
        sourceId: "",
        checkpointid: ""
      },
      loading: false,
      sourceList: [],
      pointList: [],
      tableHeader: [],
      tableData: [],
      chartData: {
        columns: [],
        rows: []
      },
      isOne: false
    }
  },
  components: { VeLine },
  created() {
    this.getEnterprise();
  },
  beforeDestroy() {
    clearInterval(this.polling);
  },
  methods: {
    //获取企业列表
    getEnterprise() {
      dgomEnterprise().then(res => {
        if (res.code === 200) {
          this.sourceList = res.data;
        }
      })
    },
    //获取排放口列表
    getEmission(v) {
      dgomCheckPoint(v).then(res => {
        if (res.code === 200) {
          this.pointList = res.data;
          if (res.data.length > 0) {
            this.queryForm.checkpointid = this.pointList[0].zcheckpointid;
          }
        }
      })
    },
    //选中企业
    sourceChange(v) {
      this.queryForm.checkpointid = "";
      this.getEmission(v);
    },
    handleQuery() {
      if (this.queryForm.sourceId && this.queryForm.checkpointid) {
        this.loading = true;
        this.isOne = true;
        dgomMonitorHearderReal(this.queryForm).then(res => {
          if (res.code === 200) {
            this.tableHeader = res.data;
            let oRes = res.data;
            oRes = oRes.filter(i => !new Set(["100002", "100040", "100046"]).has(i.elementid));
            let newObj = {};
            let newArr = ["date"];
            oRes.forEach(i => {
              newArr.push("val_" + i.elementid);
              newObj["val_" + i.elementid] = i.elementdesc;
            })
            this.chartData.columns = newArr;
            this.lineConfig.settings.labelMap = newObj;
          }

        })
        this.$nextTick(() => {
          dgomMonitorReal(this.queryForm).then(res => {
            if (res.code === 200) {
              this.chartData.rows = res.data;
              this.tableData = res.data;
            }
          })
        })
        let loadTime = setTimeout(() => {
          this.loading = false;
          clearTimeout(loadTime);
        }, 500);
        clearInterval(this.polling);
        this.polling = null;
        this.polling = setInterval(this.handleQuery, 30000);
      } else {
        this.msgError("企业名称和排放口不能为空.");
      }

    },
    resetQuery() {
      this.isOne = false;
      this.queryForm = {
        sourceId: "",
        checkpointid: ""
      }
    }
  }
}
</script>

<style lang="scss">
.realTable-style {
  .el-table {
    display: flex;
    flex-direction: column;
    overflow: hidden;

    .el-table__body-wrapper {
      flex: 1;
    }
  }
}

</style>
