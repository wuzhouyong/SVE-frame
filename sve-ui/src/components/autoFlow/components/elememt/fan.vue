<template>
  <div class="em-fan flow-element" :class="{'stop':!status}">
    <span class="flow-tag">{{tag}}</span>
  </div>
</template>

<script>
  export default {
    name: "fan",
    props:["tag","status"],
    data() {
      return {
        // status: false
      }
    }
  }
</script>

<style lang="scss" scoped>
  @import "../../css/flow.scss";

  .em-fan {
    margin-left: 76px;
    margin-top: 35px;
    height: 69px;
    width: 60px;
    -moz-background-size: 60px 139px;
    -webkit-background-size: 60px 139px;
    -o-background-size: 60px 139px;
    background-size: 60px 139px;
    background-image: url("../../../../assets/autoFlow/fan.gif");
    background-repeat: no-repeat;
    .flow-tag {
      margin-top: 75px;
    }
  }

  .em-fan.stop {
    background-position: bottom left !important;
  }

</style>
