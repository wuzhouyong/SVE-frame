<template>
  <div class="em-soot-source flow-element">
    <span class="flow-tag">{{tag}}</span>
  </div>
</template>

<script>
  export default {
    name: "sootSource",
    props: ["tag", "status"]
  }
</script>


<style lang="scss" scoped>
  @import "../../css/flow.scss";

  .em-soot-source {
    margin-left: 10px;
    margin-top: 10px;
    height: 130px;
    width: 130px;
    -moz-background-size: 130px 130px !important;
    -webkit-background-size: 130px 130px !important;
    -o-background-size: 130px 130px !important;
    background-size: 130px 130px !important;

    background-image: url("../../../../assets/autoFlow/soot_source.gif");
    background-repeat: no-repeat;
    background-color: #f3f3f3;
    border-radius: 10px;

    .flow-tag {
      margin-top: 140px;
    }
  }
</style>
