<template>
  <div class="filing-wk">
    <el-form label-width="130px">
      <el-row :gutter="15">
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="环保负责人">{{ rows.sourceenvhead || '-' }}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="联系方式">{{ rows.sourceenvheadinfo || '-' }}</el-form-item>
        </el-col>
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="运营单位">{{ rows.opcompanyname || '-' }}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="负责人">{{ rows.opcompanyhead || '-' }}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="联系方式">{{ rows.opcompanyheadinfo || '-' }}</el-form-item>
        </el-col>
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="排污口名称">{{ rows.checkpointname || '-' }}</el-form-item>
        </el-col>
      </el-row>

      <template v-for="i in waterMes">
        <el-divider content-position="left">{{ i.zdatadevdesc || '-' }}</el-divider>
        <el-row :gutter="15">
          <el-col :lg="12" :md="12" :sm="24">
            <el-form-item label="设备品牌">{{ i.devicebrand || '-' }}</el-form-item>
          </el-col>
          <el-col :lg="12" :md="12" :sm="24">
            <el-form-item label="设备出厂型号编号">{{ i.devicenumber || '-' }}</el-form-item>
          </el-col>
          <el-col :lg="12" :md="12" :sm="24">
            <el-form-item label="测量方法">{{ i.measuringmethod || '-' }}</el-form-item>
          </el-col>
          <el-col :lg="12" :md="12" :sm="24">
            <el-form-item label="加热消解温度">{{ i.heatingtemp || '-' }}</el-form-item>
          </el-col>
          <el-col :lg="12" :md="12" :sm="24">
            <el-form-item label="加热消解时间">{{ i.heatingtime ? i.heatingtime.substr(0, 10) : '-' }}</el-form-item>
          </el-col>
          <el-col :lg="12" :md="12" :sm="24">
            <el-form-item label="工作量程">{{ i.workrange || '-' }}</el-form-item>
          </el-col>
          <el-col :lg="12" :md="12" :sm="24">
            <el-form-item label="相关系数">{{ i.refcoefficient || '-' }}</el-form-item>
          </el-col>
          <el-col :lg="12" :md="12" :sm="24">
            <el-form-item label="检定时间">{{ i.verificationtime ? i.verificationtime.substr(0, 10) : '-' }}</el-form-item>
          </el-col>
          <el-col :lg="12" :md="12" :sm="24">
            <el-form-item label="验收时间">{{ i.acceptancetime ? i.acceptancetime.substr(0, 10) : '-' }}</el-form-item>
          </el-col>
          <el-col :lg="12" :md="12" :sm="24">
            <el-form-item label="排放标准">{{ i.emissionstandard || '-' }}</el-form-item>
          </el-col>
        </el-row>
      </template>

      <el-divider content-position="left">运维资料</el-divider>
      <el-row :gutter="15">
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="运维人员">{{ rows.oppersonname || '-' }}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="联系方式">{{ rows.oppersontel || '-' }}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="运维开始时间">{{ rows.opstartdate ? rows.opstartdate.substr(0, 10) : '-' }}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="运维结束时间">{{ rows.openddate ? rows.openddate.substr(0, 10) : '-' }}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="运维证编号">{{ rows.oplicensenum || '-' }}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="发证单位">{{ rows.oplicissueunit || '-' }}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="发证时间">{{ rows.oplicissuedate ? rows.oplicissuedate.substr(0, 10) : '-' }}</el-form-item>
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
    waterMes: {
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
.filing-wk {
  /deep/ .el-form {
    .el-form-item {
      margin-bottom: 2px;
    }
  }
}
</style>
