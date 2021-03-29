<template>
  <div>
    <el-row :gutter="15">
      <el-form size="mini" label-width="120px">
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="联单编号">{{ldmx.billNo}}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="联单状态">{{status[ldmx.billStatus]}}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="产生单位">{{ldmx.sourceName}}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="联系方式">{{ldmx.contactInfo}}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="通讯地址">{{ldmx.address}}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="运输单位">{{ldmx.transSourceName}}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="联系方式">{{ldmx.transContectInfo}}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="通讯地址">{{ldmx.transAddress}}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="邮编">{{ldmx.transPostcode}}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="废水名称">{{ldmx.wastewaterName}}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="废水类别">{{ldmx.wastewaterType}}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="计算数量(吨)">{{ldmx.flowinamt}}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="确定数量(吨)">{{ldmx.factinamt}}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="形态">{{ldmx.wastewaterShape}}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="主要成分">{{ldmx.mainComponents}}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="外运目的">{{purpose[ldmx.transPurpose]}}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="承运人">{{ldmx.carrier1Name}}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="禁忌与应急措施">{{ldmx.emergencyMeasure}}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="运输方式">{{ldmx.transType}}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="运达地点">{{ldmx.endPlace}}</el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="运输时间">{{ldmx.transDate}}</el-form-item>
        </el-col>
      </el-form>
    </el-row>
  </div>
</template>
<script>
import { transBillinfo } from "@/api/lingxing";
export default {
  props: {
    transbillid: {
      type: String,
    },
  },
  watch: {
    transbillid: {
      handler(v) {
        if (v) this.getList(v);
      },
      immediate: true,
    },
  },
  data() {
    return {
      ldmx: {},
      purpose: ["", "中转贮存", "利用", "集中处理", "处置，默认集中处理"],
      status: ["预联单", "进行中", "已完成", "已作废"],
    };
  },
  methods: {
    getList(v) {
      transBillinfo(v).then((res) => {
        if (res.code === 200) {
          this.ldmx = res.data[0];
        }
      });
    },
  },
};
</script>