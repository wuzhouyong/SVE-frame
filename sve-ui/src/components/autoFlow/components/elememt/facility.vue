<template>
  <div class="em-fac flow-element" :style="{marginTop:top}">
    <div class="em-fac-head"></div>
    <div class="em-fac-body" :style="{height:bodyHeight}"></div>
    <div class="em-fac-bottom"></div>

    <div class="fac-teach">
      <div v-for="item in teach" :key="item.techId" class="teach-box">
        <div :info="teachStatus['T'+item.techId]" :class="[item.techType,{'teach-on':teachStatus['T'+item.techId]}]">
        </div>
        <span class="teach-tag">{{ item.techName }}</span>
      </div>
    </div>
    <span class="flow-tag">{{ tag }}</span>
  </div>
</template>

<script>
export default {
  name: "facility",
  props: ["tag", "status", "point"],
  data () {
    return {
      top: "-68px",
      bodyHeight: "0px",
      teach: [],
      teachStatus: []
      // status: false
    };
  },
  watch: {
    status () {
      for (let t of this.status) {
        //this.teachStatus["T" + t.id] = t.run;
        this.$set(this.teachStatus, "T" + t.id, t.run);
      }
      console.log(this.teachStatus);
    }
  },
  created () {
    //console.log(this.point.other);
    this.teach = this.point.other;
    if (this.teach.length > 0) {
      this.bodyHeight = (this.teach.length - 1) * 60 + "px";
      // this.top = -(this.teach.length - 1) * 60 - 30 + "px";
    }
  },
  methods: {
    getStatus (v) {

    }
  }
};
</script>

<style lang="scss" scoped>
@import "../../css/flow.scss";

.em-fac {
  position: relative;
  margin-left: 30px;
  /*margin-left: 76px;*/
  /*margin-top: 35px;*/
  /*height: 69px;*/
  /*width: 60px;*/
  /*-moz-background-size: 60px 139px;*/
  /*-webkit-background-size: 60px 139px;*/
  /*-o-background-size: 60px 139px;*/
  /*background-size: 60px 139px;*/
  /*background-image: url("../../../../assets/autoFlow/fan.gif");*/
  /*background-repeat: no-repeat;*/

  .em-fac-head {
    height: 80px;
    width: 160px;
    -moz-background-size: 160px 80px;
    -webkit-background-size: 160px 80px;
    -o-background-size: 160px 80px;
    //background-image: url("../../../../assets/autoFlow/fac_top.gif");
    background-repeat: no-repeat;
  }

  .em-fac-body {
    height: 0px;
    width: 160px;
    background-size: 160px 32px;
    //-moz-background-size: 160px 64px;
    //-webkit-background-size: 160px 64px;
    //-o-background-size: 160px 64px;
    background-image: url("../../../../assets/autoFlow/fac_body.gif");
    background-repeat: repeat-y;
  }

  .em-fac-bottom {
    height: 80px;
    width: 160px;
    -moz-background-size: 160px 80px;
    -webkit-background-size: 160px 80px;
    -o-background-size: 160px 80px;
    background-image: url("../../../../assets/autoFlow/fac_bottom.gif");
    background-repeat: no-repeat;
  }

  .flow-tag {
    margin-top: 10px;
  }

  .fac-teach {
    position: absolute;
    top: 90px;
    left: 10px;

    .teach-box {
      border-bottom: 2px solid #1ab394;
      padding-bottom: 2px;
      margin-bottom: 10px;
      width: 88px;
      position: relative;

      .teach-on.voc_uvgj {
        background: url("../../../../assets/autoFlow/voc_uvgj_on.png") no-repeat center center;
        background-size: 50% 50%;
      }

      .voc_uvgj {
        background-image: url("../../../../assets/autoFlow/voc_uvgj.png") no-repeat center center;
        background-repeat: no-repeat;
        background-size: 50% 50%;
      }

      .teach-on.voc_hxt {
        background-image: url("../../../../assets/autoFlow/voc_hxt_on.png") no-repeat center center;
      }

      .voc_hxt {
        background-image: url("../../../../assets/autoFlow/voc_hxt.png") no-repeat center center;
        background-repeat: no-repeat;
      }

      .teach-on.voc_pl {
        background-image: url("../../../../assets/autoFlow/voc_pl_on.png") no-repeat center center;
      }

      .voc_pl {
        background-image: url("../../../../assets/autoFlow/voc_pl.png") no-repeat center center;
        background-repeat: no-repeat;
      }

      .teach-on.voc_chrs {
        background-image: url("../../../../assets/autoFlow/voc_chrs_on.png") no-repeat center center;
      }

      .voc_chrs {
        background-image: url("../../../../assets/autoFlow/voc_chrs.png") no-repeat center center;
        background-repeat: no-repeat;
      }

      .teach-on.voc_dlz {
        background-image: url("../../../../assets/autoFlow/voc_dlz_on.png") no-repeat center center;
      }

      .voc_dlz {
        background-image: url("../../../../assets/autoFlow/voc_dlz.png") no-repeat center center;
        background-repeat: no-repeat;
      }

      .teach-on.voc_lnhs {
        background-image: url("../../../../assets/autoFlow/voc_lnhs_on.png") no-repeat center center;
      }

      .voc_lnhs {
        background-image: url("../../../../assets/autoFlow/voc_lnhs.png") no-repeat center center;
        background-repeat: no-repeat;
      }

      .teach-on.voc_jdxf {
        background: url("../../../../assets/autoFlow/voc_jdxf_on.png") no-repeat center center;
      }

      .voc_jdxf {
        background: url("../../../../assets/autoFlow/voc_jdxf.png") no-repeat center center;
        background-repeat: no-repeat;
      }

      .teach-on {
        animation: fade 1s infinite;
        -webkit-animation: fade 1s infinite;
      }

      [class*="voc_"] {
        height: 48px;
        width: 48px;
        -moz-background-size: 45px 45px;
        -webkit-background-size: 45px 45px;
        -o-background-size: 45px 45px;
      }

      .teach-tag {
        font-size: 10px;
        color: darkgreen;
        bottom: 3px;
        width: 45px;
        position: absolute;
        left: 50px;
      }
    }
  }
}

@keyframes fade {
  from {
    opacity: 1.0;
  }
  50% {
    opacity: 0.6;
  }
  to {
    opacity: 1.0;
  }
}

@-webkit-keyframes fade {
  from {
    opacity: 1.0;
  }
  50% {
    opacity: 0.6;
  }
  to {
    opacity: 1.0;
  }
}

.em-fan.stop {
  background-position: bottom left !important;
}

</style>
