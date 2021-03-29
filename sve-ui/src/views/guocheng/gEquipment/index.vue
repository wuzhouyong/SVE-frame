<template>
<!--  治理设备状态统计-->
  <statis-tem tagHeadId="1002" chartTitle="设备状态开关状态" :statisRequest="gcEterpriseRealElect"></statis-tem>
</template>

<script>
import statisTem from '@/views/components/statisTem'
import {gcEterpriseRealElect} from '@/api/guocheng'

export default {
  components: {
    statisTem
  },
  data(){
    return{
      gcEterpriseRealElect
    }
  }
}
</script>

