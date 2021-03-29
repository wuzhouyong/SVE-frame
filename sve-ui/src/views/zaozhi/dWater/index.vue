<template>
  <!--  企业污水排放流量统计-->
  <statis-tem tagHeadId="1019" chartTitle="污水排放量" :statisRequest="eterpriseSewageAmt"></statis-tem>
</template>

<script>
import statisTem from '@/views/components/statisTem'
import {eterpriseSewageAmt} from '@/api/zaozhi'

export default {
  components: {
    statisTem
  },
  data(){
    return{
      eterpriseSewageAmt
    }
  }
}
</script>

