<template>
  <div class="em-purifier flow-element" :class="{'stop':!status}">
    <span class="flow-tag">{{tag}}</span>
  </div>
</template>

<script>
  export default {
    name: "purifier",
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

  .em-purifier {
    margin-left: 35px;
    margin-top: 35px;
    height: 80px;
    width: 80px;
    -moz-background-size: 80px 162px;
    -webkit-background-size: 80px 162px;
    -o-background-size: 80px 162px;
    background-size: 80px 162px;
    background-image: url("../../../../assets/autoFlow/purifier.gif");
    background-repeat: no-repeat;

    .flow-tag {
      margin-top: 90px;
    }
  }

  .em-purifier.stop {
    background-position: bottom left !important;
  }

</style>
