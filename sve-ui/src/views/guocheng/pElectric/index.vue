<template>
<!--  企业生产用电量统计-->
  <statis-tem tagHeadId="1002" chartTitle="生产用电量" :statisRequest="gcEterpriseElect"></statis-tem>
</template>

<script>
import statisTem from '@/views/components/statisTem'
import {gcEterpriseElect} from '@/api/guocheng'

export default {
  components: {
    statisTem
  },
  data(){
    return{
      gcEterpriseElect
    }
  }
}
</script>
