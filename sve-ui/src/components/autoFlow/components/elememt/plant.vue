<template>
<!--  喷漆房-->
  <div class="em-plant flow-element" :class="{'stop':!status}">
    <span class="flow-tag">{{tag}}</span>
  </div>
</template>

<script>
  export default {
    name: "plant",
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

  .em-plant {
    margin-left: 0px;
    margin-top: 20px;
    height: 110px;
    width:135px;
    -moz-background-size:135px 110px;
    -webkit-background-size:135px 110px;
    -o-background-size:135px 110px;
    background-size:135px 110px;
    background-image: url("../../../../assets/autoFlow/plant.gif");
    background-repeat: no-repeat;
    .flow-tag {
      margin-top: 120px;
    }
  }

  .em-plant.stop {
    background-position: bottom left !important;
  }

</style>
