<template>
  <div>
    <el-form label-width="130px">
      <el-row :gutter="15">
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="环保负责人">{{ rows.sourceenvhead }}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="联系方式">{{ rows.sourceenvheadinfo }}</el-form-item>
        </el-col>
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="运营单位">{{ rows.opcompanyname }}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="负责人">{{ rows.opcompanyhead }}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="联系方式">{{ rows.opcompanyheadinfo }}</el-form-item>
        </el-col>

        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="监测位置">{{ gasMes[0].monitorposition }}</el-form-item>
        </el-col>
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="设备名称">{{ gasMes[0].zdatadevdesc }}</el-form-item>
        </el-col>
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="设备品牌型号编号">{{ gasMes[0].devicebrand }}</el-form-item>
        </el-col>
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="工作原理">{{ gasMes[0].workingprinciple }}</el-form-item>
        </el-col>
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="工作量程">{{ gasMes[0].workrange }}</el-form-item>
        </el-col>
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="排放标准">{{ gasMes[0].emissionstandard }}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="皮托管系数">{{ gasMes[0].tubecoefficient }}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="速度场系数">{{ gasMes[0].velocitycoefficient }}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="过量空气系数">{{ gasMes[0].aircoefficient }}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="烟囱建面积">{{ gasMes[0].chimneysectionarea }}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="验收时间">{{
              gasMes[0].verificationtime ? gasMes[0].verificationtime.substr(0, 10) : '-'
            }}
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="检定时间">{{
              gasMes[0].acceptancetime ? gasMes[0].acceptancetime.substr(0, 10) : '-'
            }}
          </el-form-item>
        </el-col>
      </el-row>
      <el-divider content-position="left">运维资料</el-divider>
      <el-row :gutter="15">
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="运维人员">{{ rows.oppersonname }}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="联系方式">{{ rows.oppersontel }}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="运维开始时间">{{ rows.opstartdate }}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="运维结束时间">{{ rows.openddate }}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="运维证编号">{{ rows.oplicensenum }}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="发证单位">{{ rows.oplicissueunit }}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="发证时间">{{ rows.oplicissuedate }}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="无证明原因">{{ rows.opnolicreason || '-' }}</el-form-item>
        </el-col>
      </el-row>
    </el-form>
  </div>
</template>

<script>
export default {
  props: {
    gasMes: {
      type: Array,
      default: () => []
    },
    rows: {
      type: Object,
      default: () => ({})
    }
  }
}
</script>

<style lang='scss' scoped>

</style>
