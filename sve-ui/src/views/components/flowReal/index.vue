<template>
  <div class="flow-page">
    <!--    <el-form :model="queryForm" ref="queryForm" :inline="true">-->
    <!--      <el-form-item label="污染源" prop="sourceId">-->
    <!--        <SelectSource-->
    <!--          v-model="queryForm.sourceId"-->
    <!--          :data="sourceList"-->
    <!--          :isPlatName="platform"-->
    <!--          placeholder="请输入污染源"-->
    <!--          style="width:300px"-->
    <!--        ></SelectSource>-->
    <!--      </el-form-item>-->
    <!--    </el-form>-->
    <SourceLeft class="select-source" @selectSource="selectSource" :tagHeadId="tagID" v-if="tagID != 0"></SourceLeft>
    <div style="height:100%;overflow: hidden">
      <div class="flow-chart" :class="{noChart:chartList.length==0}">
        <flow-panel class="panel-box" v-for="chart in chartList" :chart="chart" :key="chart.id+'_flow'"></flow-panel>
      </div>
    </div>
  </div>
</template>
<script>
import SourceLeft from "@/components/sourceLeft";
import {platform_list} from "@/config";
import {mapActions} from "vuex";
import {sourceList} from "@/api/search";
import Cookies from "js-cookie";
import FlowPanel from "../../../components/autoFlow/components/flowPanel";
import {getFlowChartData} from "../../../api/flowReal/charData";

export default {
  components: {FlowPanel, SourceLeft},
  data () {
    return {
      sourceList,
      queryForm: {
        sourceId: undefined
      },
      platform: "",
      chartList: [],
      tagID: 0
    };
  },
  watch: {
    // "queryForm.sourceId"() {
    //   //console.log(this.queryForm.sourceId);
    //   this.getChart();
    // }
  },
  created () {
    this.platform = Cookies.get("system-name");
    let v = platform_list.filter(p => {
      return p.path === this.platform;
    });
    if (v)
      this.tagID = v[0].id;

    //console.log(this.tagID);
    //this.getChart();
  },
  methods: {
    ...mapActions("company", ["SaveSelectCompanyId"]),
    getChart () {
      if (!this.queryForm.sourceId)
        return;

      let data = {key: this.queryForm.sourceId, platform: this.platform};
      getFlowChartData(data).then(res => {
        if (res) {
          this.chartList = res.data;
        }
      });
    },
    selectSource (v) {
      this.queryForm.sourceId = v.sourceId;
      this.getChart();
    }
  }
};
</script>
<style lang="scss" scoped>

$height: calc(100vh - 90px);
.flow-page {
  padding: 10px;
  height: 100%;
  overflow: hidden;

  .select-source {
    width: 300px;
    //height: $height;
    height: 100%;
    float: left;
    overflow: hidden;
  }

  .flow-chart {
    height: $height;
    overflow-y: auto;
    padding-left: 10px;
    /*background-color: red;*/

    .panel-box {
      margin-bottom: 10px;
    }
  }

  .flow-chart.noChart {
    background-image: url("../../../assets/autoFlow/no_view.png");
    background-repeat: no-repeat;
    background-position: center;
  }
}
</style>
