<template>
  <!--  企业生产用水统计-->
    <statis-tem tagHeadId="1019" chartTitle="生产用水量 ( m³ )" :statisRequest="eterpriseWater"></statis-tem>
</template>

<script>
import statisTem from '@/views/components/statisTem'
import {eterpriseWater} from '@/api/zaozhi'

export default {
  components: {
    statisTem
  },
  data(){
    return{
      eterpriseWater
    }
  }
}
</script>

