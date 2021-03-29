<template>
  <!--  治理设备状态统计-->
  <statis-tem tagHeadId="1019" chartTitle="设备状态开关状态" :statisRequest="eterpriseRealElect"></statis-tem>
</template>

<script>
import statisTem from '@/views/components/statisTem'
import {eterpriseRealElect} from '@/api/zaozhi'

export default {
  components: {
    statisTem
  },
  data(){
    return{
      eterpriseRealElect
    }
  }
}
</script>

