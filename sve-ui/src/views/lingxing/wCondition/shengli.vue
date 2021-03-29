<template>
  <div class="flow-style">
    <div class="flow-canvas">
      <div class="flow-pipeline">
        <div class="flow-pipe-right water" xy="75,86" pwidth="50" tgroups="SW14,SW12"></div>
        <div class="flow-pipe-right water" xy="125,86" pwidth="100" groups="E14"></div>
        <div class="flow-pipe-right water" xy="225,86" pwidth="100" tgroups="SW14,SW12"></div>
        <div class="flow-pipe-right water" xy="325,86" pwidth="830"></div>
        <div class="flow-pipe-bottom water" xy="115,96" pheight="100" groups="E12"></div>
        <div class="flow-pipe-top water" xy="225,96" pheight="100" groups="E12"></div>
        <div class="flow-pipe-right water" xy="125,186" pwidth="100" groups="E12"></div>
        <div class="flow-pipe-top med" xy="332,114" pheight="100" groups="E13"></div>
        <div class="flow-pipe-top med" xy="511,98" pheight="50" tgroups="SW7,SW6"></div>
        <div class="flow-pipe-right med" xy="462,138" pwidth="50" groups="E7"></div>
        <div class="flow-pipe-left med" xy="521,138" pwidth="50" groups="E6"></div>
        <div class="flow-pipe-top med" xy="462,148" pheight="50" groups="E7"></div>
        <div class="flow-pipe-top med" xy="561,147" pheight="50" groups="E6"></div>
        <div class="flow-pipe-top med" xy="683,112" pheight="100" groups="E8"></div>
        <div class="flow-pipe-top med" xy="884,98" pheight="50" tgroups="SW9,SW3,SW2"></div>
        <div class="flow-pipe-right med" xy="804,138" pwidth="80" groups="E9"></div>
        <div class="flow-pipe-left med" xy="894,138" pwidth="80" groups="E2"></div>
        <div class="flow-pipe-top med" xy="804,147" pheight="50" groups="E9"></div>
        <div class="flow-pipe-top med" xy="884,147" pheight="50" groups="E3"></div>
        <div class="flow-pipe-top med" xy="964,147" pheight="50" groups="E2"></div>
        <div class="flow-pipe-top med" xy="1083,110" pheight="100" groups="E4"></div>
        <div class="flow-pipe-right water" xy="1068,86" pwidth="100"></div>
        <div class="flow-pipe-bottom water" xy="1158,96" pheight="330"></div>
        <div class="flow-pipe-left water" xy="147,426" pwidth="1021"></div>
        <div class="flow-pipe-bottom water" xy="137,426" pheight="200"></div>
        <div class="flow-pipe-left water" xy="69,628" pwidth="50"></div>
        <div class="flow-pipe-top water" xy="64,458" pheight="180"></div>
        <div class="flow-pipe-right solid" xy="155,628" pwidth="54" tgroups="SW20,SW21"></div>
        <div class="flow-pipe-right solid" xy="209,628" pwidth="54" groups="E21"></div>
        <div class="flow-pipe-right solid" xy="297,628" pwidth="61" groups="E21"></div>
        <div class="flow-pipe-right solid" xy="357,628" pwidth="358" tgroups="SW20,SW21"></div>
        <div class="flow-pipe-top solid" xy="209,550" pheight="80" groups="E20"></div>
        <div class="flow-pipe-right solid" xy="209,540" pwidth="60" groups="E20"></div>
        <div class="flow-pipe-right solid" xy="298,540" pwidth="50" groups="E20"></div>
        <div class="flow-pipe-bottom solid" xy="348,540" pheight="90" groups="E20"></div>
        <div class="flow-pipe-left solid" xy="1089,538" pwidth="30" groups="E5"></div>
        <div class="flow-pipe-left solid" xy="999,538" pwidth="60" groups="E5"></div>
        <div class="flow-pipe-bottom solid" xy="989,538" pheight="98" groups="E5"></div>
        <div class="flow-pipe-left solid" xy="1089,626" pwidth="30" groups="E10"></div>
        <div class="flow-pipe-left solid" xy="824,626" pwidth="175" tgroups="SW5,SW10"></div>
        <div class="flow-pipe-left solid" xy="999,626" pwidth="45" groups="E10"></div>
        <div class="flow-pipe-bottom solid" xy="1109,432" pheight="116" tgroups="SW5,SW10"></div>
        <div class="flow-pipe-bottom solid" xy="1109,548" pheight="79" groups="E10"></div>
        <div class="flow-pipe-top air" xy="431,455" pheight="60" groups="E16"></div>
        <div class="flow-pipe-top air" xy="581,455" pheight="60" groups="E15"></div>
        <div class="flow-pipe-right air" xy="431,445" pwidth="60" groups="E16"></div>
        <div class="flow-pipe-left air" xy="531,445" pwidth="60" groups="E15"></div>
        <div class="flow-pipe-top med" xy="890,459" pheight="60" groups="E11"></div>
      </div>
      <div class="flow-element">
        <div class="flow-pool-med top-name" ename="调节池" xy="40,60"></div>
        <div id="SW14" class="flow-pump-up flow-start" ename="提升泵A" xy="160,50" groups="E14"></div>
        <div id="SW12" class="flow-pump-up flow-start" ename="提升泵B" xy="160,150" groups="E12"></div>
        <div class="flow-pool-med top-name" ename="PH调节池" xy="309,60"></div>
        <div id="SW13" class="flow-pump-med flow-start" ename="加硫酸药泵" xy="309,173" groups="E13"></div>
        <div class="flow-pool-med top-name" ename="芬顿反应池" xy="486,60"></div>
        <div id="SW7" class="flow-pump-med flow-start" ename="过氧化氢加药泵" xy="451,173" groups="E7"></div>
        <div id="SW6" class="flow-pump-med flow-start" ename="硫酸亚铁加药泵" xy="540,173" groups="E6"></div>
        <div class="flow-pool-med top-name" ename="PH回调池" xy="660,60"></div>
        <div id="SW8" class="flow-pump-med flow-start" ename="加氢氧化钠药泵" xy="660,173" groups="E8"></div>
        <div class="flow-pool-water top-name" ename="还原反应池" xy="860,60"></div>
        <div id="SW9" class="flow-pump-med flow-start" ename="还原剂加药泵" xy="780,173" groups="E9"></div>
        <div id="SW3" class="flow-pump-med flow-start" ename="PAC加药泵" xy="860,173" groups="E3"></div>
        <div id="SW2" class="flow-pump-med flow-start" ename="除磷剂加药泵" xy="940,173" groups="E2"></div>
        <div class="flow-pool-soil top-name" ename="慢混池" xy="1060,60"></div>
        <div id="SW4" class="flow-pump-med flow-start" ename="PAM药泵" xy="1060,173" groups="E4"></div>
        <div class="flow-pool-soil top-name" ename="沉淀池1" xy="1080,400"></div>
        <div id="SW5" class="flow-pump-soil flow-start" ename="1#污泥泵A" xy="1040,528" groups="E5"></div>
        <div id="SW10" class="flow-pump-soil flow-start" ename="1#污泥泵B" xy="1040,615" groups="E10"></div>
        <div id="SW11" class="flow-pump-med flow-start" ename="硫酸加药泵" xy="867,488" groups="E11"></div>
        <div class="flow-pool-med top-name" ename="PH中和池" xy="860,400"></div>
        <div class="flow-pool-med top-name" ename="水解酸化池" xy="760,400"></div>
        <div class="flow-pool-med top-name" ename="缺氧池" xy="660,400"></div>
        <div class="flow-pool-med top-name" ename="好氧池" xy="480,400"></div>
        <div id="SW16" class="flow-machine-fan flow-start" ename="生化风机A" xy="430,500" groups="E16"></div>
        <div id="SW15" class="flow-machine-fan flow-start" ename="生化风机B" xy="580,500" groups="E15"></div>
        <div class="flow-pool-med top-name" ename="脱氧池" xy="260,400"></div>
        <div class="flow-pool-soil" ename="沉淀池2" xy="100,600"></div>
        <div id="SW20" class="flow-pump-soil flow-start" ename="2#污泥泵A" xy="250,528" groups="E20"></div>
        <div id="SW21" class="flow-pump-soil flow-start" ename="2#污泥泵B" xy="250,615" groups="E21"></div>
        <div class="flow-pool-water top-name" ename="排放池" xy="40,440"></div>
        <div class="flow-pool-soil" ename="生化污泥池1" xy="600,600"></div>
        <div id="SW22" class="flow-machine-filter flow-start" ename="压滤机" xy="699,613" groups="E22"></div>
        <div class="flow-pool-soil" ename="生化污泥池2" xy="880,600"></div>
      </div>
      <div class="flow-label">
        <div id="E14" class="flow-label-tag" format="电流{}A" xy="148,116" groups="E14"></div>
        <div id="E12" class="flow-label-tag" format="电流{}A" xy="148,218" groups="E12"></div>
        <div id="E17" class="flow-label-tag" format="进水流量</br>{}M3" xy="35,124"></div>
        <div id="E18" class="flow-label-tag" format="PH{}" xy="313,79"></div>
        <div id="E19" class="flow-label-tag" format="PH{}" xy="664,79"></div>
        <div id="E13" class="flow-label-tag" format="电流{}A" xy="309,272" groups="E13"></div>
        <div id="E7" class="flow-label-tag" format="电流{}A" xy="451,272" groups="E7"></div>
        <div id="E6" class="flow-label-tag" format="电流{}A" xy="540,272" groups="E6"></div>
        <div id="E8" class="flow-label-tag" format="电流{}A" xy="660,272" groups="E8"></div>
        <div id="E9" class="flow-label-tag" format="电流{}A" xy="780,272" groups="E9"></div>
        <div id="E3" class="flow-label-tag" format="电流{}A" xy="860,272" groups="E3"></div>
        <div id="E2" class="flow-label-tag" format="电流{}A" xy="940,272" groups="E2"></div>
        <div id="E4" class="flow-label-tag" format="电流{}A" xy="1060,272" groups="E4"></div>
        <div id="E11" class="flow-label-tag" format="电流{}A" xy="796,493" groups="E11"></div>
        <div id="E5" class="flow-label-tag" format="电流{}A" xy="1121,532" groups="E5"></div>
        <div id="E10" class="flow-label-tag" format="电流{}A" xy="1121,619" groups="E10"></div>
        <div id="E20" class="flow-label-tag" format="电流{}A" xy="304,507" groups="E20"></div>
        <div id="E21" class="flow-label-tag" format="电流{}A" xy="304,642" groups="E21"></div>
        <div id="E16" class="flow-label-tag" format="电流{}A" xy="488,505" groups="E16"></div>
        <div id="E15" class="flow-label-tag" format="电流{}A" xy="639,505" groups="E15"></div>
        <div id="E22" class="flow-label-tag" format="电流{}A" xy="735,580" groups="E22"></div>
      </div>
      <!-- <div class="flow-other">
        <div class="flow-online-data" xy="40,260">
          <li id="E1" class="flow-label-li" format="用电量：{}KWH">用电量</li>
          <li id="val_100001" class="flow-label-li" format="COD：{}mg/L">COD排放量</li>
          <li id="val_100035" class="flow-label-li" format="PH：{}">PH值</li>
          <li id="val_100029" class="flow-label-li" format="污水流速：{}L/S">污水流速</li>
        </div>
      </div>-->
    </div>
    <el-dialog title="近6小时数据（间隔10分钟）" :visible.sync="visible" width="30%" custom-class="lxworking">
      <div v-loading="loading">
        <h3>{{ title }}</h3>
        <ve-line :data="chartData" :legend-visible="false" :settings="chartSettings" :data-empty="dataEmpty"></ve-line>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import VeLine from "v-charts/lib/line.common";
import { flowResize, flowRefresh } from "./flow-js.js";
import { workingCondition, workingMinute } from "@/api/lingxing"
import "v-charts/lib/style.css"

export default {
  components: { VeLine },
  data() {
    this.chartSettings = {
      labelMap: {
        "avgAmt": ""
      }
    }
    return {
      gkData: {},
      visible: false,
      title: "",
      oId: "",
      chartData: {
        columns: ["datadate", "avgAmt"],
        rows: []
      },
      dataEmpty: false,
      loading: true
    }
  },
  mounted() {
    this.getData();
    this.get();
    this.isDefault();
  },
  methods: {
    getMinute() {
      workingMinute(this.oId).then(res => {
        if (res.code === 200) {
          this.title = res.data.elementDesc;
          if (this.oId === "17") {
            this.chartData.columns = ["datadate", "couAmt"];
            this.chartSettings = {
              labelMap: {
                "couAmt": ""
              }
            }
          } else {
            this.chartData.columns = ["datadate", "avgAmt"];
            this.chartSettings = {
              labelMap: {
                "avgAmt": ""
              }
            }
          }
          this.chartData.rows = res.data.data;
          this.dataEmpty = res.data.data.length > 0 ? false : true;
        }
        this.loading = false;
      })
    },
    getData() {
      workingCondition(100009540).then((res) => {
        if (res.code === 200) {
          this.gkData = res.data;
          flowRefresh(res.data);
        }
      });
    },
    isDefault() {
      $(".flow-canvas div").each(function(index, el) {
        let xy = $(this).attr("xy");
        if (xy) {
          let point = xy.split(",");
          if (point.length == 2) {
            $(this).animate({ top: point[1] + "px", left: point[0] + "px" });
          }
        }
        let ename = $(this).attr("ename");
        // console.log(ename);
        if (ename) {
          $(this).append("<span class=\"flow-name\">" + ename + "</span>");
        }
        let pwidth = $(this).attr("pwidth");
        if (pwidth) {
          $(this).css({
            width: pwidth + "px"
          })
        }
        let pheight = $(this).attr("pheight");
        if (pheight) {
          $(this).css({
            height: pheight + "px"
          })
        }
        let format = $(this).attr("format")
        if (format) {
          $(this).html(format);
        }
      })
      $("[tgroups]").each(function(index, el) {
        let ids = $(el).attr("tgroups").split(",")
        let isgo = false;
        $.each(ids, function(index, val) {
          if ($("#" + val).attr("class").indexOf("-start") >= 0) {
            isgo = true;
            return
          }
        })
        if ($(el).prop("class").indexOf("flow-pipe") >= 0) {
          if (isgo) {
            $(el).addClass("pipe-start");
            $(el).removeClass("pipe-stop");
          } else {
            $(el).addClass("pipe-stop");
            $(el).removeClass("pipe-start");
          }
        }
      })
    },
    get() {
      let _that = this;
      var sizePercent = $(document.body).height() / $(".flow-canvas").height();
      flowResize(sizePercent);
      $(window).resize(function() {
        var sizePercent =
          $(document.body).height() / $(".flow-canvas").height();
        flowResize(sizePercent);
      });

      $(".flow-label [id]").click(function() {
        _that.oId = $(this).attr("id").replace("E", "");
        _that.visible = true;
        _that.getMinute();
      });
    }
  }
};
</script>
<style>
@import "./flow-base.css";
</style>
<style lang="scss">
.flow-style {
  width: 100%;
  height: 100%;
  overflow: hidden;
}

.flow-label [id] {
  cursor: pointer;
}
</style>
