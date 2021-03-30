<template>
  <div class="element-box">
    <soot-source v-if="type =='yt' " :tag="name" :status="status"/>
    <purifier v-if="type =='jhq' " :tag="name" :status="status"/>
    <fan v-if="type=='fj'" :tag="name" :status="status"/>
    <smoke-out v-if="type=='yc'" :tag="name" :status="status"/>
    <plant v-if="type=='vo'" :tag="name" :status="status"/>
    <!--    <span class="show-info">{{point.type}}</span>-->
  </div>
</template>

<script>
  import SootSource from "./elememt/sootSource";
  import Purifier from "./elememt/purifier";
  import Fan from "./elememt/fan";
  import SmokeOut from "./elememt/smokeOut";
  import Plant from "./elememt/plant";

  export default {
    name: "selectPoint",
    components: {Plant, SmokeOut, Fan, Purifier, SootSource},
    props: ["point", "status"],
    data() {
      return {
        type: "",
        name: ""
      }
    },
    mounted() {
      //console.log(this.point);
      let types = this.point.type.split("_");
      this.name = this.point.name;
      if (types.length == 3) {
        this.type = types[1];
      }

    }
  }
</script>

<style lang="scss" scoped>
  .element-box {
    position: relative;
    z-index: 99;

    .show-info {
      position: absolute;
      top: 0px;
      right: 0px;
      font-size: 10px;
    }
  }
</style>
