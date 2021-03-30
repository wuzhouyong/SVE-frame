<template>
  <div class="em-smoke-out flow-element" :class="{'stop':!status}">
    <span class="flow-tag">{{tag}}</span>
  </div>
</template>

<script>
  export default {
    name: "smokeOut",
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

  .em-smoke-out {
    margin-left: 15px;
    margin-top: 15px;
    height: 114px;
    width: 120px;
    -moz-background-size: 120px 230px;
    -webkit-background-size: 120px 230px;
    -o-background-size: 120px 230px;
    background-size: 120px 230px;
    background-image: url("../../../../assets/autoFlow/soot_out.gif");
    background-repeat: no-repeat;
    .flow-tag {
      margin-top: 120px;
    }
  }

  .em-smoke-out.stop {
    background-position: bottom left !important;
  }

</style>

