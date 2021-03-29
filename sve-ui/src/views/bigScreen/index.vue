<template>
  <div
    class="dis-body"
    :style="{
      transform: 'scale(' + screenSize.scale + ')',
      left: screenSize.left + 'px',
      top: screenSize.top + 'px',
    }"
  >
    <div class="dis-header">
      <div class="title">
        <div class="title-name">东莞市污染源在线监控平台</div>
      </div>
      <div class="head-left-button">
        <div class="box-left">
          <div class="dis-btn">全市总览</div>
          <div class="dis-btn">项目监控</div>
          <div class="dis-btn">责考评分</div>
        </div>
        <div>
          <clock class="head-left-clock"></clock>
        </div>
      </div>
      <div class="head-right-button">
        <div class="box-right">
          <div class="dis-btn dis-admin">数据监控</div>
          <div class="dis-btn">预警监控</div>
          <div class="dis-btn">企业评价</div>
        </div>
      </div>
      <div class="button-back">
        <router-link to="/">
          <span>返回后台</span>
          <svg-icon :icon-class="'exit-fullscreen'"/>
        </router-link>
      </div>
    </div>
    <div>
      <div class="dis-box">
        <el-row class="dis-box-row">
          <el-col :span="6" class="box-left">
            <index-left/>
          </el-col>
          <el-col :span="12" class="box-center">
            <index-center/>
          </el-col>
          <el-col :span="6" class="box-right">
            <index-right/>
          </el-col>
        </el-row>
      </div>
    </div>
  </div>
</template>

<script>
  import IndexLeft from "./components/indexLeft";
  import IndexRight from "./components/indexRight";
  import IndexCenter from "./components/indexCenter";
  import Clock from "./components/clock";

  export default {
    name: "index",
    components: {IndexCenter, IndexRight, IndexLeft, Clock},
    data() {
      return {
        screenSize: {scale: 0.5, left: 0, top: 0},
        screenWidth: 1920,
        screenHeight: 1080,
        defaultSize: {
          width: 1920,
          height: 1080,
        },
      };
    },
    mounted() {
      const that = this;
      window.onresize = function () {
        that.reSizeScreen();
      };

      this.reSizeScreen();
    },
    beforeCreate() {
      document
        .querySelector("body")
        .setAttribute("style", "background-color:#0d193c");
    },
    beforeDestroy() {
      document.querySelector("body").removeAttribute("style");
    },
    methods: {
      reSizeScreen() {
        this.screenWidth = document.documentElement.clientWidth;
        this.screenHeight = document.documentElement.clientHeight;
        // console.log('H' + this.screenWidth)
        // console.log('H' + this.screenHeight)
        let xScale = 1,
          yScale = 1;
        xScale = this.screenWidth / this.defaultSize.width;
        yScale = this.screenHeight / this.defaultSize.height;

        this.screenSize.scale = xScale > yScale ? yScale : xScale;

        this.screenSize.left = -(this.defaultSize.width - this.screenWidth) / 2;
        this.screenSize.top = -(this.defaultSize.height - this.screenHeight) / 2;
        //console.log(this.screenSize.left)
      },
      reBack() {
        this.$router.push({path: "/index"});
      },
    },
  };
</script>

<style lang="scss" scoped>
  .dis-body {
    position: fixed;
    color: white;
    // color:#172343;
    font-size: 14px;
    // background-color:rgba(6,17,52,0.2);
    // background-color: rgb(6, 17, 52,0.1);
    width: 1920px;
    height: 1080px;

    background-image: url("../../assets/bigScreen/bk.png");
    background-size: cover;
    background-position: center center;

    .dis-header {
      height: 83px;
      padding-bottom: 20px;
      background-image: url("../../assets/bigScreen/top_bottom.png");
      background-size: cover;
      background-position: center center;

      .head-left-clock {
        margin-top: 13px;
      }

      .head-left-button {
        position: absolute;
        width: 50%;
        top: 0px;
        left: 0px;
        padding-right: 280px;

        .dis-btn {
          float: right;
          margin-right: 15px;

          &:before {
            // transform: skewX(25deg);
          }
        }
      }

      .head-right-button {
        position: absolute;
        width: 50%;
        top: 0px;
        right: 0px;
        padding-left: 280px;

        .dis-btn {
          float: left;
          margin-left: 10px;

          &:before {
            // transform: skewX(-25deg);
          }
        }
      }

      .title {
        width: 100%;
        height: 98px;
        background-image: url("../../assets/bigScreen/title_bg.png");
        background-size: 447px 98px;
        background-repeat: no-repeat;
        background-position: center;
        text-align: center;

        .title-name {
          font-family: YouYuan;
          font-size: 30px;
          line-height: 50px;
          color: rgb(255, 255, 255);
          cursor: pointer;
          font-weight: bolder;
          text-overflow: ellipsis;
          white-space: nowrap;
          font-style: normal;
        }
      }

      .button-back {
        cursor: pointer;
        color: #39ddea;
        position: absolute;
        top: 20px;
        right: 20px;

        span {
          line-height: 25px;
          font-size: 16px;
        }

        svg {
          height: 25px;
          width: 25px;

          opacity: 0.8;
        }

        &:hover {
          color: #239cb9;
        }
      }
    }

    .dis-box {
      height: calc(100% - 90px);
      position: absolute;
      width: 100%;

      .dis-box-row {
        height: 100%;

        .el-col {
          height: 100%;
          overflow-y: auto;

          &::-webkit-scrollbar {
            width: 1px;
          }

          &::-webkit-scrollbar-thumb {
            border-radius: 10px;
            -webkit-box-shadow: inset 0 0 5px rgba(66, 195, 141, 0.2);
            background: rgba(22, 233, 249, 0.1);
          }

          &::-webkit-scrollbar-track {
            -webkit-box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.2);
            border-radius: 0;
            background: rgba(0, 0, 0, 0.1);
          }
        }
      }
    }
  }

  .dis-header {
    .dis-btn {
      color: #c9f9ff;
      position: relative;
      width: 150px;
      height: 50px;
      text-align: center;
      font-size: 16px;
      letter-spacing: 0.1vw;
      line-height: 46px;
      cursor: pointer;
      top: 15px;

      &:hover {
        // color: red;
        color: #fff;
        z-index: 100000;
      }

      &:before {
        content: "";
        position: absolute;
        width: 150px;
        top: 0;
        right: 0;
        bottom: 0;
        left: 0;

        background-size: contain;
        height: 50px;
        // border-radius: 50%;

        // border: 1px solid #03A9F4;
        /*transform: skewX(-25deg);*/
        // box-shadow: 1px 1px 5px 1px #00bcd485 inset;;
      }
    }

    .box-left {
      .dis-btn {

        &:hover::before {
          z-index: -1;
          background: url("../../assets/bigScreen/opacityNone.png") no-repeat;
        }

        &:before {
          background: url("../../assets/bigScreen/opcaty.png") no-repeat;
        }
      }
    }

    .box-right {
      .dis-btn {
        &:hover::before {
          z-index: -1;
          background: url("../../assets/bigScreen/right-opcatyNone.png") no-repeat;
        }

        &:before {
          background: url("../../assets/bigScreen/opacity-right.png") no-repeat;
        }

      }
    }
  }
</style>
