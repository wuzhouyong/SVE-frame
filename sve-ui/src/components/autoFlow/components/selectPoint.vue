<template>
  <div class="element-box">
    <!--    烟筒-->
    <soot-source v-if="type =='yt' " :tag="name" :status="status"/>
    <!--    净化器-->
    <purifier v-if="type =='jhq'" :tag="name" :status="status"/>
    <!--    风机-->
    <fan v-if="type=='fj' || type=='sjfj'||type =='pffj'" :tag="name" :status="status"/>
    <!--    烟囱,排放口-->
    <smoke-out v-if="type=='yc' || type=='pfk'" :tag="name" :status="status"/>
    <!--    voc-->
    <plant v-if="type=='vo' || type=='scss'" :tag="name" :status="status"/>

    <facility v-if="type=='clss'" :tag="name" :point="point" :status="status"></facility>
<!--    <span class="show-info">{{ point.type }}</span>-->
  </div>
</template>

<script>
import SootSource from "./elememt/sootSource";
import Purifier from "./elememt/purifier";
import Fan from "./elememt/fan";
import SmokeOut from "./elememt/smokeOut";
import Plant from "./elememt/plant";
import Facility from "./elememt/facility";

export default {
  name: "selectPoint",
  components: {Facility, Plant, SmokeOut, Fan, Purifier, SootSource},
  props: ["point", "status"],
  data () {
    return {
      type: "",
      name: ""
    };
  },
  mounted () {
    // console.log(this.point);
    // console.log(this.status);
    let types = this.point.type.split("_");
    this.name = this.point.name;
    if (types.length == 3) {
      this.type = types[1];
    }

  }
};
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
