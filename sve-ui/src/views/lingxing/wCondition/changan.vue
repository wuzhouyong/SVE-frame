<template>
  <div class="flow-style">
    <div class="flow-canvas">
      <div class="flow-pipeline">
        <div class="flow-pipe-top med" xy="62,104" pheight="80" groups="SW35"></div>
        <div class="flow-pipe-right water" xy="75,86" pwidth="100" tgroups="SW31,SW32"></div>
        <div class="flow-pipe-right water" xy="175,86" pwidth="100" groups="SW31"></div>
        <div class="flow-pipe-right water" xy="274,86" pwidth="180" tgroups="SW31,SW32"></div>
        <div class="flow-pipe-right water" xy="400,86" pwidth="600"></div>
        <div class="flow-pipe-bottom water" xy="165,96" pheight="100" groups="SW32"></div>
        <div class="flow-pipe-top water" xy="275,96" pheight="100" groups="SW32"></div>
        <div class="flow-pipe-right water" xy="175,186" pwidth="100" groups="SW32"></div>
        <div class="flow-pipe-top med" xy="424,87" pheight="50" tgroups="SW36,SW37"></div>
        <div class="flow-pipe-right med" xy="374,127" pwidth="50" groups="SW36"></div>
        <div class="flow-pipe-left med" xy="434,127" pwidth="50" groups="SW37"></div>
        <div class="flow-pipe-top med" xy="374,136" pheight="50" groups="SW36"></div>
        <div class="flow-pipe-top med" xy="474,136" pheight="50" groups="SW37"></div>
        <div class="flow-pipe-top med" xy="675,87" pheight="50" tgroups="SW38,SW34"></div>
        <div class="flow-pipe-right med" xy="625,127" pwidth="50" groups="SW38"></div>
        <div class="flow-pipe-left med" xy="685,127" pwidth="50" groups="SW34"></div>
        <div class="flow-pipe-top med" xy="625,136" pheight="50" groups="SW38"></div>
        <div class="flow-pipe-top med" xy="725,136" pheight="50" groups="SW34"></div>
        <div class="flow-pipe-top med" xy="865,87" pheight="100" groups="SW39"></div>
        <div class="flow-pipe-bottom solid" xy="1039,100" pheight="80" tgroups="SW33,SW28"></div>
        <div class="flow-pipe-right solid" xy="1049,170" pwidth="140" groups="SW33"></div>
        <div class="flow-pipe-right solid" xy="1049,251" pwidth="140" groups="SW28"></div>
        <div class="flow-pipe-bottom solid" xy="1039,180" pheight="82" groups="SW28"></div>
        <div class="flow-pipe-bottom solid" xy="1189,170" pheight="82" groups="SW33"></div>
        <div class="flow-pipe-bottom solid" xy="1189,251" pheight="330" tgroups="SW33,SW28"></div>
        <div class="flow-pipe-left solid" xy="808,581" pwidth="391" tgroups="SW33,SW28"></div>
        <div class="flow-pipe-bottom water" xy="1011,108" pheight="250"></div>
        <div class="flow-pipe-bottom solid" xy="774,581" pheight="60" tgroups="SW29,SW30"></div>
        <div class="flow-pipe-bottom solid" xy="798,581" pheight="60" tgroups="SW33,SW28"></div>
        <div class="flow-pipe-left water" xy="98,364" pwidth="920"></div>
        <div class="flow-pipe-bottom water" xy="64,388" pheight="200"></div>
        <div class="flow-pipe-top med" xy="1017,388" pheight="80" groups="SW41"></div>
        <div class="flow-pipe-top med" xy="886,387" pheight="80" groups="SW40"></div>
        <div class="flow-pipe-bottom solid" xy="314,381" pheight="100" tgroups="SW29,SW30"></div>
        <div class="flow-pipe-right solid" xy="324,471" pwidth="140" groups="SW29"></div>
        <div class="flow-pipe-right solid" xy="324,581" pwidth="140" groups="SW30"></div>
        <div class="flow-pipe-bottom solid" xy="314,481" pheight="110" groups="SW30"></div>
        <div class="flow-pipe-bottom solid" xy="464,471" pheight="110" groups="SW29"></div>
        <!--         <div class="flow-pipe-bottom solid" xy="1189,251" pheight="330"></div> -->
        <div class="flow-pipe-right solid" xy="464,581" pwidth="320" tgroups="SW29,SW30"></div>
        <div class="flow-pipe-right pipe-stop solid" xy="782,642" pwidth="140"></div>
        <div class="flow-pipe-left solid" xy="660,642" pwidth="140" groups="SW27"></div>
        <div class="flow-pipe-top air" xy="622,356" pheight="60" tgroups="SW24,SW25,SW26"></div>
        <div class="flow-pipe-top air" xy="532,416" pheight="60" groups="SW24"></div>
        <div class="flow-pipe-top air" xy="622,416" pheight="60" groups="SW25"></div>
        <div class="flow-pipe-top air" xy="712,416" pheight="60" groups="SW26"></div>
        <div class="flow-pipe-right air" xy="532,406" pwidth="90" groups="SW24"></div>
        <div class="flow-pipe-left air" xy="632,406" pwidth="90" groups="SW26"></div>
      </div>
      <div class="flow-element">
        <div class="flow-pool-med top-name" ename="中转池1" xy="39,50"></div>
        <div id="SW35" class="flow-pump-med flow-start" ename="1#硫酸加药泵" xy="44,160" groups="SW35"></div>
        <div id="SW31" class="flow-pump-up flow-start" ename="1#提升泵" xy="210,50" groups="SW31"></div>
        <div id="SW32" class="flow-pump-up flow-start" ename="2#提升泵" xy="210,180" groups="SW32"></div>
        <div class="flow-pool-med top-name" ename="氧化池" xy="400,50"></div>
        <div id="SW36" class="flow-pump-med flow-start" ename="硫酸亚铁加药泵" xy="350,160" groups="SW36"></div>
        <div id="SW37" class="flow-pump-med flow-start" ename="过氧化氢加药泵" xy="460,160" groups="SW37"></div>
        <div class="flow-pool-med top-name" ename="混凝池" xy="650,50"></div>
        <div id="SW38" class="flow-pump-med flow-start" ename="氢氧化钙加药泵" xy="600,160" groups="SW38"></div>
        <div id="SW34" class="flow-pump-med flow-start" ename="PAC加药泵" xy="710,160" groups="SW34"></div>
        <div class="flow-pool-med top-name" ename="絮凝池" xy="840,50"></div>
        <div id="SW39" class="flow-pump-med flow-start" ename="PAM加药泵" xy="847,160" groups="SW39"></div>
        <div class="flow-pool-soil top-name" ename="物化沉淀池" xy="1000,50"></div>
        <div
          id="SW33"
          class="flow-pump-soil flow-start"
          ename="物化池污泥泵A"
          xy="1089,160"
          groups="SW33"
        ></div>
        <div
          id="SW28"
          class="flow-pump-soil flow-start"
          ename="物化池污泥泵B"
          xy="1089,240"
          groups="SW28"
        ></div>
        <div
          class="flow-pool-med"
          ename="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;PH回调池"
          xy="1000,330"
        ></div>
        <div id="SW41" class="flow-pump-med flow-start" ename="2#硫酸加药泵" xy="1010,435" groups="SW41"></div>

        <div class="flow-pool-med top-name" ename="水解酸化池" xy="860,330"></div>
        <div id="SW40" class="flow-pump-med flow-start" ename="营养剂加药泵" xy="870,435" groups="SW40"></div>

        <div class="flow-pool-med top-name" ename="中转池2" xy="735,330"></div>

        <div class="flow-pool-med top-name" ename="好氧池" xy="600,330"></div>
        <div
          id="SW24"
          class="flow-machine-fan flow-start"
          ename="好氧池1#风机"
          xy="530,435"
          groups="SW24"
        ></div>
        <div
          id="SW25"
          class="flow-machine-fan flow-start"
          ename="好氧池2#风机"
          xy="620,435"
          groups="SW25"
        >>
        </div>
        <div
          id="SW26"
          class="flow-machine-fan flow-start"
          ename="好氧池3#风机"
          xy="710,435"
          groups="SW26"
        >>
        </div>

        <div class="flow-pool-soil top-name" ename="生化沉淀池" xy="303,330"></div>
        <div id="SW29" class="flow-pump-soil flow-start" ename="生化池污泥泵A" xy="370,460" groups="SW29"></div>
        <div id="SW30" class="flow-pump-soil flow-start" ename="生化池污泥泵B" xy="370,571" groups="SW30"></div>

        <div class="flow-pool-soil" ename="浓缩池" xy="762,617"></div>
        <div
          id="SW27"
          class="flow-machine-filter flow-start"
          ename="1#压滤机"
          xy="522,629"
          groups="SW27"
        ></div>
        <div class="flow-machine-filter flow-stop" ename="2#压滤机" xy="908,629"></div>

        <div class="flow-pool-water top-name" ename="清水池" xy="39,330"></div>

        <div
          class="flow-pool-med"
          ename="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;BAF池"
          xy="39,433"
        ></div>
        <div class="flow-pool-med" ename="排放池" xy="39,570"></div>
      </div>
      <div class="flow-label">
        <div id="E35" class="flow-label-tag" format="{}A" xy="44,255"></div>
        <div id="E36" class="flow-label-tag" format="{}A" xy="350,255"></div>
        <div id="E37" class="flow-label-tag" format="{}A" xy="460,255"></div>
        <div id="E38" class="flow-label-tag" format="{}A" xy="600,255"></div>
        <div id="E34" class="flow-label-tag" format="{}A" xy="710,255"></div>
        <div id="E39" class="flow-label-tag" format="{}A" xy="847,255"></div>
        <div id="E40" class="flow-label-tag" format="{}A" xy="871,527"></div>
        <div id="E41" class="flow-label-tag" format="{}A" xy="1010,527"></div>
        <div id="E31" class="flow-label-tag" format="{}A" xy="206,114"></div>
        <div id="E32" class="flow-label-tag" format="{}A" xy="206,246"></div>
        <div id="E42" class="flow-label-tag" format="进水流量计</br>{}M3" xy="290,40"></div>
        <div id="E43" class="flow-label-tag" format="{}" xy="47,75"></div>
        <div id="E44" class="flow-label-tag" format="{}" xy="657,73"></div>
        <div id="E33" class="flow-label-tag" format="{}A" xy="1089,127"></div>
        <div id="E28" class="flow-label-tag" format="{}A" xy="1089,299"></div>
        <div id="E29" class="flow-label-tag" format="{}A" xy="371,426"></div>
        <div id="E30" class="flow-label-tag" format="{}A" xy="371,629"></div>
        <div id="E24" class="flow-label-tag" format="{}A" xy="530,516"></div>
        <div id="E25" class="flow-label-tag" format="{}A" xy="620,516"></div>
        <div id="E26" class="flow-label-tag" format="{}A" xy="710,516"></div>
        <div id="E27" class="flow-label-tag" format="{}A" xy="536,597"></div>
        <div class="flow-label-tag" format="*A" xy="923,597"></div>
      </div>
      <!-- <div class="flow-other">
        <div class="flow-online-data" xy="115,440">
          <li id="E23" class="flow-label-li" format="用电量：{}KWH">用电量</li>
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
import VeLine from "v-charts/lib/line.common"
import { flowResize, flowRefresh } from "./flow-js.js"
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
    this.getData()
    this.get()
    this.isDefault()
  },
  methods: {
    getMinute() {
      workingMinute(this.oId).then(res => {
        if (res.code === 200) {
          this.title = res.data.elementDesc;
          this.chartData.rows = res.data.data;
          this.dataEmpty = res.data.data.length > 0 ? false : true;
        }
        this.loading = false;
      })
    },
    getData() {
      workingCondition(100009463).then((res) => {
        if (res.code === 200) {
          this.gkData = res.data;
          flowRefresh(res.data);
        }
      })
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
      let _that = this
      const sizePercent = $(document.body).height() / $(".flow-canvas").height()
      flowResize(sizePercent);
      $(window).resize(function() {
        flowResize(sizePercent);
      })
      $(".flow-label [id]").click(function() {
        _that.oId = $(this).attr("id").replace("E", "");
        _that.visible = true;
        _that.getMinute();
      })
    }
  }
}
</script>
<style>
@import "./flow-base.css";
</style>

