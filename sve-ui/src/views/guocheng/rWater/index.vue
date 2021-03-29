<template>
<!--  企业回用水量统计-->
  <statis-tem tagHeadId="1002" chartTitle="回用水量" :statisRequest="gcEterpriseReuseWater"></statis-tem>
</template>

<script>
import statisTem from "@/views/components/statisTem";
import {gcEterpriseReuseWater} from "@/api/guocheng";

export default {
  components: {
    statisTem
  },
  data(){
    return{
      gcEterpriseReuseWater
    }
  }
}
</script>

<style lang='scss' scoped>

</style>
