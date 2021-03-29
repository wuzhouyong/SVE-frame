<template>
  <!--  企业生产用电量统计-->
  <statis-tem tagHeadId="1019" chartTitle="生产用电量" :statisRequest="eterpriseElect"></statis-tem>
</template>

<script>
import statisTem from '@/views/components/statisTem'
import {eterpriseElect} from '@/api/zaozhi'

export default {
  components: {
    statisTem
  },
  data(){
    return{
      eterpriseElect
    }
  }
}
</script>
