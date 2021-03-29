<template>
  <!--  企业生产用水统计-->
    <statis-tem tagHeadId="1015" chartTitle="生产用水量 ( m³ )" :statisRequest="eterpriseWater"></statis-tem>
</template>

<script>
import statisTem from '@/views/components/statisTem'
import {eterpriseWater} from '@/api/jinshu'

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

