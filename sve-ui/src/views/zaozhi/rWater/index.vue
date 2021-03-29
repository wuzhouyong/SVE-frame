<template>
  <!--  企业回用水量统计-->
  <statis-tem tagHeadId="1019" chartTitle="回用水量" :statisRequest="eterpriseReuseWater"></statis-tem>
</template>

<script>
import statisTem from "@/views/components/statisTem";
import {eterpriseReuseWater} from "@/api/zaozhi";

export default {
  components: {
    statisTem
  },
  data(){
    return{
      eterpriseReuseWater
    }
  }
}
</script>

<style lang='scss' scoped>

</style>
