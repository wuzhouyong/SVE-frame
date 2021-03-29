<template>
  <!--  企业污水排放流量统计-->
    <statis-tem tagHeadId="1002" chartTitle="污水排放量" :statisRequest="gcEterpriseSewageAmt"></statis-tem>
</template>

<script>
import statisTem from '@/views/components/statisTem'
import {gcEterpriseSewageAmt} from '@/api/guocheng'

export default {
  components: {
    statisTem
  },
  data(){
    return{
      gcEterpriseSewageAmt
    }
  }
}
</script>

