<template>
  <div class="source-see-style">
    <el-divider content-position="left">基本信息</el-divider>
    <el-row :gutter="15">
      <el-col :lg="12" :md="12" :sm="24">
        <p>企业名称：</p>
        <span :title="seeData.sourceName">{{ seeData.sourceName }}</span>
      </el-col>
      <el-col :lg="12" :md="12" :sm="24">
        <p>统一社会信用代码：</p>
        <span>{{ seeData.socialCreditCode || "-" }}</span>
      </el-col>
      <el-col :lg="12" :md="12" :sm="24">
        <p>排污许可证代码：</p>
        <span>{{ seeData.emissionPermitCode || "-" }}</span>
      </el-col>
      <el-col :lg="12" :md="12" :sm="24">
        <p>所属镇街：</p>
        <span>{{ seeData.townName || "-" }}</span>
      </el-col>
      <el-col :lg="12" :md="12" :sm="24">
        <p>企业地址：</p>
        <span :title="seeData.address">{{ seeData.address || "-" }}</span>
      </el-col>
      <el-col :lg="12" :md="12" :sm="24">
        <p>企业经营状态：</p>
        <span>{{ seeData.statusName || "-" }}</span>
      </el-col>
      <el-col :lg="12" :md="12" :sm="24">
        <p>行业类别：</p>
        <span :title="seeData.industryName">{{ seeData.industryName || "-" }}</span>
      </el-col>
      <el-col :lg="12" :md="12" :sm="24">
        <p>企业规模：</p>
        <span>{{ source_scale[seeData.sourceScale] }}</span>
      </el-col>
      <el-col :lg="12" :md="12" :sm="24">
        <p>法定代表人：</p>
        <span>{{ seeData.legalPersonName || "-" }}</span>
      </el-col>
      <el-col :lg="12" :md="12" :sm="24">
        <p>法人联系电话：</p>
        <span>{{ seeData.legalPersonPhone || "-" }}</span>
      </el-col>
      <el-col :lg="12" :md="12" :sm="24">
        <p>经度：</p>
        <span>{{ seeData.longitude || "-" }}</span>
      </el-col>
      <el-col :lg="12" :md="12" :sm="24">
        <p>纬度：</p>
        <span>{{ seeData.latitude || "-" }}</span>
      </el-col>
    </el-row>
    <el-divider content-position="left">环保信息</el-divider>
    <el-row :gutter="15">
      <el-col :lg="24" :md="12" :sm="24">
        <p>监控方式：</p>
        <ul v-if="seeData.tagHeadList.length>0">
          <li v-for="(i,index) in seeData.tagHeadList" :key="index">
            <i class="el-icon-success"></i>
            {{ i.tagHeadName }}
          </li>
        </ul>
        <span v-else>-</span>
      </el-col>
      <el-col :lg="12" :md="12" :sm="24">
        <p>环保联系人：</p>
        <span>{{ seeData.envContactName || "-" }}</span>
      </el-col>
      <el-col :lg="12" :md="12" :sm="24">
        <p>环保联系人电话：</p>
        <span>{{ seeData.envContactPhone || "-" }}</span>
      </el-col>
      <!-- <el-col :lg="12" :md="12" :sm="24">
        <p>监控级别：</p>
        <span>{{seeData.envMonitorLevel || '-'}}</span>
      </el-col>-->
      <el-col :lg="12" :md="12" :sm="24">
        <p>收纳水体名称：</p>
        <span>{{ seeData.inflowWaterName || "-" }}</span>
      </el-col>
      <el-col :lg="12" :md="12" :sm="24">
        <p>产生工业废水：</p>
        <span>{{ seeData.hasWasteWater || "-" }}</span>
      </el-col>
      <el-col :lg="12" :md="12" :sm="24">
        <p>产生工业废气：</p>
        <span>{{ seeData.hasWasteGas || "-" }}</span>
      </el-col>
      <el-col :lg="12" :md="12" :sm="24">
        <p>产生一般工业固废：</p>
        <span>{{ seeData.hasGeneralSolid || "-" }}</span>
      </el-col>
      <el-col :lg="12" :md="12" :sm="24">
        <p>工业危险废物：</p>
        <span>{{ seeData.hasHazardousWaste || "-" }}</span>
      </el-col>
      <el-col :lg="24" :md="12" :sm="24">
        <p>涉及稀土等15类矿产：</p>
        <span>{{ seeData.hasRareEarth || "-" }}</span>
      </el-col>
      <el-col :lg="24" :md="12" :sm="24">
        <p>工业流程：</p>
        <span>{{ seeData.processFlow || "-" }}</span>
      </el-col>
      <el-col :lg="24" :md="12" :sm="24">
        <p>备注：</p>
        <span>{{ seeData.memo || "-" }}</span>
      </el-col>
    </el-row>
  </div>
</template>
<script>
import {getDict} from "@/api/composite/source";

export default {
  props: ["seeData"],
  data () {
    return {
      sourceScaleOptions: [],
      source_scale: ["", "微型", "小型", "中型", "大型"],
    };
  },
};
</script>
<style lang="scss">
.source-see-style {
  .el-col {
    display: flex;
    align-items: center;
    margin-bottom: 15px;

    ul {
      display: flex;
      padding: 0;
      align-items: center;
      margin: 0;
      flex-wrap: wrap;

      li {
        list-style-type: none;
        margin-right: 20px;
      }
    }

    p {
      margin: 0;
      width: 160px;
      text-align: right;
      font-size: 14px;
      letter-spacing: 1px;
    }

    span {
      font-size: 14px;
      flex: 1;
      overflow: hidden;
      white-space: nowrap;
      text-overflow: ellipsis;
    }
  }
}
</style>
