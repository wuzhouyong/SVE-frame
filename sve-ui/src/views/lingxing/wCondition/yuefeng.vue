<template>
  <div class="flow-style">
    <div class="flow-canvas">
      <div class="flow-pipeline">
        <div class="flow-pipe-right water" xy="75,86" pwidth="150" tgroups="SW48,SW49"></div>
        <div class="flow-pipe-right water" xy="224,86" pwidth="100" groups="SW48"></div>
        <div class="flow-pipe-right water" xy="324,86" pwidth="150" tgroups="SW48,SW49"></div>
        <div class="flow-pipe-right water" xy="474,86" pwidth="480"></div>
        <div class="flow-pipe-right water" xy="960,86" pwidth="100" tgroups="SW64,SW65"></div>
        <div class="flow-pipe-bottom water" xy="215,96" pheight="100" groups="SW49"></div>
        <div class="flow-pipe-top water" xy="324,96" pheight="100" groups="SW49"></div>
        <div class="flow-pipe-right water" xy="225,186" pwidth="100" groups="SW49"></div>

        <div class="flow-pipe-right water" xy="1054,86" pwidth="90" groups="SW64"></div>
        <div class="flow-pipe-bottom water" xy="1044,96" pheight="100" groups="SW65"></div>
        <div class="flow-pipe-top water" xy="1144,96" pheight="100" groups="SW65"></div>
        <div class="flow-pipe-right water" xy="1044,186" pwidth="100" groups="SW65"></div>
        <div class="flow-pipe-right water" xy="1144,86" pwidth="80" tgroups="SW64,SW65"></div>

        <div class="flow-pipe-bottom water" xy="1214,95" pheight="240" tgroups="SW64,SW65"></div>
        <div class="flow-pipe-left water" xy="229,343" pwidth="955"></div>
        <div class="flow-pipe-bottom water" xy="194,378" pheight="80"></div>
        <div class="flow-pipe-left water" xy="153,458" pwidth="51"></div>
        <div class="flow-pipe-bottom water" xy="143,458" pheight="200"></div>
        <div class="flow-pipe-right water" xy="143,658" pwidth="680"></div>

        <div class="flow-pipe-top air" xy="42,153" pheight="30" groups="SW46"></div>
        <div class="flow-pipe-top air" xy="142,153" pheight="30" groups="SW47"></div>
        <div class="flow-pipe-top air" xy="42,113" pheight="40" tgroups="SW46,SW47"></div>
        <div class="flow-pipe-left air" xy="52,143" pwidth="100" groups="SW47"></div>
        <div class="flow-pipe-right air" xy="42,297" pwidth="80" groups="SW57"></div>
        <div class="flow-pipe-top air" xy="42,306" pheight="80" groups="SW57"></div>
        <div class="flow-pipe-right air" xy="42,473" pwidth="80" groups="SW58"></div>
        <div class="flow-pipe-top air" xy="42,483" pheight="30" groups="SW58"></div>
        <div class="flow-pipe-top air" xy="122,307" pheight="176" groups="SW58"></div>
        <div class="flow-pipe-right air" xy="122,297" pwidth="570" tgroups="SW57,SW58"></div>
        <div class="flow-pipe-bottom air" xy="195,307" pheight="40" tgroups="SW57,SW58"></div>
        <div class="flow-pipe-bottom air" xy="690,297" pheight="40" tgroups="SW57,SW58"></div>

        <div class="flow-pipe-top med" xy="475,87" pheight="50" tgroups="SW61,SW50"></div>
        <div class="flow-pipe-right med" xy="425,127" pwidth="50" groups="SW61"></div>
        <div class="flow-pipe-left med" xy="485,127" pwidth="50" groups="SW50"></div>
        <div class="flow-pipe-top med" xy="425,136" pheight="50" groups="SW61"></div>
        <div class="flow-pipe-top med" xy="525,116" pheight="70" groups="SW50"></div>
        <div class="flow-pipe-right med" xy="525,106" pwidth="180" groups="SW50"></div>

        <div class="flow-pipe-top med" xy="715,87" pheight="50" tgroups="SW63,SW62"></div>
        <div class="flow-pipe-right med" xy="665,127" pwidth="50" groups="SW63"></div>
        <div class="flow-pipe-left med" xy="725,127" pwidth="50" groups="SW62"></div>
        <div class="flow-pipe-top med" xy="665,136" pheight="50" groups="SW63"></div>
        <div class="flow-pipe-top med" xy="765,136" pheight="50" groups="SW62"></div>

        <div class="flow-pipe-left med" xy="855,204" pwidth="50" groups="SW56"></div>
        <div class="flow-pipe-bottom med" xy="846,204" pheight="150" groups="SW56"></div>
        <div class="flow-pipe-left med" xy="590,576" pwidth="100" groups="SW55"></div>
        <div class="flow-pipe-bottom med" xy="580,576" pheight="60" groups="SW55"></div>

        <div class="flow-pipe-bottom solid" xy="352,379" pheight="40" groups="SW45"></div>
        <div class="flow-pipe-bottom solid" xy="552,379" pheight="40" groups="SW60"></div>
        <div class="flow-pipe-left solid" xy="767,369" pwidth="60" groups="SW59"></div>
        <div class="flow-pipe-bottom solid" xy="767,379" pheight="40" groups="SW59"></div>
        <div class="flow-pipe-top solid" xy="195,581" pheight="60" groups="SW53"></div>
        <div class="flow-pipe-top solid" xy="376,582" pheight="60" groups="SW54"></div>

        <div class="flow-pipe-top solid" xy="195,493" pheight="60" groups="SW53"></div>
        <div class="flow-pipe-top solid" xy="376,502" pheight="60" groups="SW54"></div>
        <div class="flow-pipe-bottom solid" xy="328,453" pheight="40" groups="SW45"></div>
        <div class="flow-pipe-bottom solid" xy="528,453" pheight="40" groups="SW60"></div>
        <div class="flow-pipe-bottom solid" xy="744,453" pheight="40" groups="SW59"></div>

        <div class="flow-pipe-right solid" xy="205,493" pwidth="123" groups="SW53"></div>
        <div class="flow-pipe-right solid" xy="328,493" pwidth="48" tgroups="SW53,SW45"></div>
        <div class="flow-pipe-right solid" xy="376,493" pwidth="152" tgroups="SW53,SW45,SW54"></div>
        <div class="flow-pipe-right solid" xy="528,493" pwidth="215" tgroups="SW53,SW45,SW54,SW60"></div>
        <div
          class="flow-pipe-right solid"
          xy="743,493"
          pwidth="150"
          tgroups="SW53,SW45,SW54,SW60,SW59"
        ></div>
        <div
          class="flow-pipe-top solid"
          xy="893,443"
          pheight="60"
          tgroups="SW53,SW45,SW54,SW60,SW59"
        ></div>
        <div
          class="flow-pipe-right solid"
          xy="893,434"
          pwidth="123"
          tgroups="SW53,SW45,SW54,SW60,SW59"
        ></div>

        <div class="flow-pipe-right solid" xy="976,444" pwidth="96" tgroups="SW51,SW52"></div>
        <div class="flow-pipe-right solid" xy="1072,444" pwidth="30" groups="SW51"></div>
        <div class="flow-pipe-bottom solid" xy="1062,454" pheight="90" groups="SW52"></div>
        <div class="flow-pipe-right solid" xy="1062,543" pwidth="40" groups="SW52"></div>
      </div>
      <div class="flow-element">
        <div class="flow-pool-med top-name" ename="调节池" xy="40,60"></div>
        <div id="SW46" class="flow-machine-fan flow-start" ename="鼓风机1" xy="40,175" groups="SW46"></div>
        <div id="SW47" class="flow-machine-fan flow-start" ename="鼓风机2" xy="140,175" groups="SW47"></div>
        <div id="SW48" class="flow-pump-up flow-start" ename="提升泵1" xy="260,50" groups="SW48"></div>
        <div id="SW49" class="flow-pump-up flow-start" ename="提升泵2" xy="260,150" groups="SW49"></div>

        <div class="flow-pool-med top-name" ename="气浮池" xy="450,60"></div>
        <div id="SW61" class="flow-pump-med flow-start" ename="PAM加药泵" xy="400,160" groups="SW61"></div>
        <div id="SW50" class="flow-pump-med flow-start" ename="PAC加药泵" xy="500,160" groups="SW50"></div>

        <div class="flow-pool-med top-name" ename="混凝池" xy="690,60"></div>
        <div id="SW63" class="flow-pump-med flow-start" ename="NaOH加药泵1" xy="640,160" groups="SW63"></div>
        <div id="SW62" class="flow-pump-med flow-start" ename="PAM加药泵2" xy="740,160" groups="SW62"></div>

        <div class="flow-pool-med top-name" ename="中间水池" xy="910,60"></div>
        <div id="SW64" class="flow-pump-up flow-start" ename="提升泵3" xy="1080,50" groups="SW64"></div>
        <div id="SW65" class="flow-pump-up flow-start" ename="提升泵4" xy="1080,150" groups="SW65"></div>

        <div class="flow-pool-med" ename="水解酸化池" xy="1180,320"></div>
        <div class="flow-pool-med top-name" ename="EGSB" xy="1050,320"></div>

        <div class="flow-pool-med body-name" ename="A段<br />缺氧池" xy="810,320"></div>
        <div id="SW56" class="flow-pump-med flow-start" ename="NaOH加药泵2" xy="905,160" groups="SW56"></div>
        <div
          id="SW59"
          class="flow-pump-soil flow-start"
          ename="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;污泥泵1"
          xy="740,415"
          groups="SW59"
        ></div>

        <div class="flow-pool-med body-name" ename="A段<br />氧化池" xy="665,320"></div>

        <div class="flow-pool-med body-name" ename="初沉池" xy="520,320"></div>
        <div
          id="SW60"
          class="flow-pump-soil flow-start"
          ename="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;污泥泵2"
          xy="525,415"
          groups="SW60"
        ></div>

        <div class="flow-pool-med body-name" ename="B段<br />缺氧池" xy="320,320"></div>
        <div
          id="SW45"
          class="flow-pump-soil flow-start"
          ename="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;污泥泵3"
          xy="325,415"
          groups="SW45"
        ></div>
        <div id="SW57" class="flow-machine-fan flow-start" ename="鼓风机3" xy="40,360" groups="SW57"></div>
        <div id="SW58" class="flow-machine-fan flow-start" ename="鼓风机4" xy="40,500" groups="SW58"></div>

        <div class="flow-pool-med body-name" ename="B段<br />氧化池" xy="170,320"></div>

        <div class="flow-pool-med" ename="二沉池" xy="170,620"></div>
        <div
          id="SW53"
          class="flow-pump-soil flow-start top-name"
          ename="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;污泥泵4"
          xy="175,545"
          groups="SW53"
        ></div>

        <div class="flow-pool-med" ename="芬顿系统" xy="350,620"></div>
        <div
          id="SW54"
          class="flow-pump-soil flow-start top-name"
          ename="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;污泥泵5"
          xy="355,545"
          groups="SW54"
        ></div>

        <div class="flow-pool-med" ename="清水口" xy="550,620"></div>
        <div id="SW55" class="flow-pump-med flow-start" ename="漂白水加药泵" xy="685,535" groups="SW55"></div>

        <div class="flow-pool-med" ename="排放口" xy="810,620"></div>

        <div class="flow-pool-med body-name" ename="浓缩池" xy="965,415"></div>
        <div
          id="SW51"
          class="flow-machine-filter flow-start"
          ename="压滤机1"
          xy="1085,430"
          groups="SW51"
        ></div>
        <div
          id="SW52"
          class="flow-machine-filter flow-start"
          ename="压滤机2"
          xy="1085,530"
          groups="SW52"
        ></div>
      </div>
      <div class="flow-label">
        <div id="E46" class="flow-label-tag" format="{}A" xy="40,255"></div>
        <div id="E47" class="flow-label-tag" format="{}A" xy="140,255"></div>
        <div id="E57" class="flow-label-tag" format="{}A" xy="42,435"></div>
        <div id="E58" class="flow-label-tag" format="{}A" xy="42,578"></div>

        <div id="E48" class="flow-label-tag" format="{}A" xy="250,115"></div>
        <div id="E49" class="flow-label-tag" format="{}A" xy="250,215"></div>
        <div id="E64" class="flow-label-tag" format="{}A" xy="1070,115"></div>
        <div id="E65" class="flow-label-tag" format="{}A" xy="1070,215"></div>
        <div id class="flow-label-tag" format="流量计" xy="1160,50"></div>

        <div id="E61" class="flow-label-tag" format="{}A" xy="395,250"></div>
        <div id="E50" class="flow-label-tag" format="{}A" xy="505,250"></div>
        <div id="E63" class="flow-label-tag" format="{}A" xy="640,250"></div>
        <div id="E62" class="flow-label-tag" format="{}A" xy="740,250"></div>
        <div id="E56" class="flow-label-tag" format="{}A" xy="905,250"></div>
        <div id="E55" class="flow-label-tag" format="{}A" xy="740,567"></div>

        <div id="E59" class="flow-label-tag" format="{}A" xy="795,420"></div>
        <div id="E60" class="flow-label-tag" format="{}A" xy="580,420"></div>
        <div id="E45" class="flow-label-tag" format="{}A" xy="380,420"></div>
        <div id="E53" class="flow-label-tag" format="{}A" xy="230,555"></div>
        <div id="E54" class="flow-label-tag" format="{}A" xy="412,555"></div>

        <div id="E51" class="flow-label-tag" format="{}A" xy="1105,400"></div>
        <div id="E52" class="flow-label-tag" format="{}A" xy="1105,500"></div>
      </div>
      <!-- <div class="flow-other">
        <div class="flow-online-data" xy="885,535">
          <li id="E23" class="flow-label-li" format="用电量：{}KWH">用电量</li>
          <li id="val_100001" class="flow-label-li" format="COD：{}mg/L">COD排放量</li>
          <li id="val_100035" class="flow-label-li" format="PH：{}">PH值</li>
          <li id="val_100029" class="flow-label-li" format="污水流速：{}L/S">污水流速</li>
        </div>
      </div>-->
    </div>
    <el-dialog title="近6小时数据（间隔10分钟）" :visible.sync="visible" width="30%" custom-class="lxworking">
      <div v-loading="loading" v-if="visible">
        <h3>{{ title }}</h3>
        <ve-line :data="chartData" :legend-visible="false" :settings="chartSettings" :data-empty="dataEmpty"></ve-line>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import VeLine from "v-charts/lib/line.common";
import { flowResize, flowRefresh } from "./flow-js.js";
import { workingCondition, workingMinute } from "@/api/lingxing";
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
          this.chartData.rows = res.data.data;
          this.dataEmpty = res.data.data.length > 0 ? false : true;
        }
        this.loading = false;
      })
    },
    getData() {
      workingCondition(100009601).then((res) => {
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
