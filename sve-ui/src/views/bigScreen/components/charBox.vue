<template>
  <div class="char-box" :style="{ height: boxHeight }">
    <!-- 四个边角 -->
    <div class="image-border image-border1"></div>
    <div class="image-border image-border2"></div>
    <div class="image-border image-border3"></div>
    <div class="image-border image-border4"></div>

    <!-- <div class="box-bg"></div> -->
    <!-- <div class="box-title">{{ title }}</div> -->
    <div class="box-title">{{ title }}</div>
    <div class="border-bottom-circle"></div>
    <div class="box-body">
      <slot></slot>
    </div>
  </div>
</template>

<script>
export default {
  name: "charBox",
  props: ["title", "height"],
  data() {
    let height = (height ? 100 : this.height) + "px";
    return {
      boxHeight: height,
    };
  },
};
</script>

<style lang="scss" scoped>
.char-box {
  position: relative;
  min-height: 200px;
  min-width: 400px;

  .box-bg {
    width: 100%;
    height: 100%;
    border-style: solid;
    border-width: 100px 150px;
    // border-image-source: url("../../../assets/bigScreen/border2.png");
    border-image-slice: 100 150;
    border-image-width: initial;
    border-image-outset: initial;
    border-image-repeat: initial;
    position: absolute;
  }
     .border-bottom-circle {
      width: 100%;
      height:12px;
      background: url("../../../assets/bigScreen/gundong.gif")  0% 0% / 100% 100% no-repeat rgba(226, 23, 23, 0);

      // background-size: cover;
      // background-position: center center;
  
    }
  .box-title {
    color: #00e2ff;
    font-size: 15px;
    line-height: 30px;
    width: 100%;
    padding-left: 30px;
 
    // border-bottom-style: solid;
    // border-bottom-width: 15px;
    // border-bottom-width 10px 15px;
    // text-align: center;
    // border-image-slice: 10 15;
    // border-image-repeat:stretch;
    // border-image-source: url("../../../assets/bigScreen/gundong.gif");
  }
  .box-title::after {
    position: absolute;
    left: 8px;
    top: 10px;
    width: 15px;
    height: 15px;
    background: black;
    background: url("../../../assets/bigScreen/circle.gif") no-repeat;
    background-size: contain;
    border-radius: 50%;
    // transition: background 0.3s ease-in;
    content: "";
  }

  .box-body {
    // color: white;
    padding: 10px 20px 0 20px;
  }

  .image-border {
    position: absolute;
    width: 20px;
    height: 20px;
  }

  .image-border1 {
    top: 0;
    left: 0;
    border-left: 2px solid #39ddea;
    border-top: 2px solid #39ddea;
  }

  .image-border2 {
    top: 0;
    right: -2px;
    border-right: 2px solid #39ddea;
    border-top: 2px solid #39ddea;
  }

  .image-border3 {
    bottom: 0;
    left: 0;
    border-bottom: 2px solid #39ddea;
    border-left: 2px solid #39ddea;
  }

  .image-border4 {
    bottom: 0;
    right: -2px;
    border-right: 2px solid #39ddea;
    border-bottom: 2px solid #39ddea;
  }
}
</style>
