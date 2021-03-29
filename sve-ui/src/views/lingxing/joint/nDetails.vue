<template>
  <div class="joint-details">
    <el-divider content-position="left">第一部分:废物产生单位填写</el-divider>
    <el-row :gutter="15">
      <el-form size="mini" label-width="120px">
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="产生单位">{{ clickData.source_name || '-' }}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="电话">{{ clickData.contact_info || '-' }}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="通讯地址" :title="clickData.address">{{ clickData.address || '-' }}</el-form-item>
        </el-col>
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="运输单位">{{ clickData.trans_source_name || '-' }}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="电话">{{ clickData.trans_contect_info || '-' }}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="通讯地址" :title="clickData.trans_address">{{
              clickData.trans_address || '-'
            }}
          </el-form-item>
        </el-col>
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="接收单位">{{ clickData.proc_source_name || '-' }}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="电话">{{ clickData.proc_contact_info || '-' }}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="通讯地址" :title="clickData.proc_address">{{ clickData.proc_address || '-' }}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="废水名称">{{ clickData.wastewater_name || '-' }}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="废水类别">{{ clickData.wastewater_type || '-' }}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="确定数量(吨)">{{ clickData.wastewater_amt || '-' }}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="计算数量(吨)">{{ clickData.factinamt || '-' }}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="形态">{{ clickData.wastewater_shape || '-' }}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="运输方式">{{ clickData.trans_type || '-' }}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="外运目的">{{ purpose[clickData.trans_purpose] || '-' }}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="主要成分">{{ clickData.main_components || '-' }}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="禁忌与应急措施">{{ clickData.emergency_measure || '-' }}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="发运人">{{ clickData.ship_persign || '-' }}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="运达地点">{{ clickData.trans_destination || '-' }}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="转移时间">{{ clickData.trans_date || '-' }}</el-form-item>
        </el-col>
      </el-form>
    </el-row>
    <el-divider content-position="left">第二部分:废物运输单位填写</el-divider>
    <el-row :gutter="15">
      <el-form size="mini" label-width="120px">
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="承运人">{{ clickData.arrier1_name || '-' }}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="运输日期">{{ clickData.carrier1_date || '-' }}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="车(船)型">{{ clickData.carrier1_type || '-' }}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="车牌号">{{ clickData.carrier1_platenum || '-' }}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="道路运输证号">{{ clickData.carrier1_transcert || '-' }}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="运输起点">{{ clickData.start_place || '-' }}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="经由地">{{ clickData.via_place || '-' }}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="运输终点">{{ clickData.end_place || '-' }}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="第二承运人">{{ clickData.carrie_r2name || '-' }}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="运输日期">{{ clickData.carrier2_date || '-' }}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="车(船)型">{{ clickData.carrier2_type || '-' }}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="车牌号">{{ clickData.carrier2_platenum || '-' }}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="道路运输证号">{{ clickData.carrier2_transcert || '-' }}</el-form-item>
        </el-col>
      </el-form>
    </el-row>
    <el-divider content-position="left">第三部分:废物接收单位填写</el-divider>
    <el-row :gutter="15">
      <el-form size="mini" label-width="120px">
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="接收人">{{ clickData.receive_personname || '-' }}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="接收日期">{{ clickData.received_date || '-' }}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="处置方法">{{ clickData.proc_type || '-' }}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="单位负责人">{{ clickData.receive_headsign || '-' }}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="签字日期">{{ clickData.receive_headsigndate || '-' }}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="作废原因">{{ clickData.invalid_reason || '-' }}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="作废时间">{{ clickData.invalid_date || '-' }}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="作废用户">{{ clickData.invalid_user || '-' }}</el-form-item>
        </el-col>
      </el-form>
    </el-row>
  </div>
</template>

<script>
export default {
  props: {
    clickData: {
      type: Object
    }
  },
  data() {
    return {
      purpose: ["", "中转贮存", "利用", "集中处理", "默认集中处理"]
    }
  }
}
</script>

<style lang="scss" scoped>
.joint-details {
  /deep/ .el-form-item {
    margin-bottom: 2px;

    .el-form-item__content {
      overflow: hidden;
      white-space: nowrap;
      text-overflow: ellipsis;
    }
  }
}
</style>
