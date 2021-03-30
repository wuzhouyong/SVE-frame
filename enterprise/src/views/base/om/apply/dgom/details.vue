<template>
  <div class="apply-lw">
    <el-form ref="form" :model="form" label-width="140px">
      <el-row :gutter="15">
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="企业名称" prop="sourceId">
            <el-input v-model="form.sourceName" size="mini" readonly></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="社会统一信用代码" prop="socialCreditCode">
            <el-input v-model="form.socialCreditCode" size="mini" readonly></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="行业" prop="industryId">
            <el-input v-model="form.industryName" size="mini" readonly></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="环保负责人" prop="envChargeMan">
            <el-input v-model="form.envChargeMan" size="mini" readonly></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="负责人电话" prop="contactTel">
            <el-input v-model="form.contactTel" size="mini" readonly></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="网络运营商" prop="networkOperator">
            <el-input v-model="form.networkOperator" size="mini" readonly></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="镇街" prop="townId">
            <el-input v-model="form.townName" size="mini" readonly></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="详细地址" prop="address">
            <el-input v-model="form.address" size="mini" readonly></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="经度" prop="longitude">
            <el-input v-model="form.longitude" size="mini" readonly></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="纬度" prop="latitude">
            <el-input v-model="form.latitude" size="mini" readonly></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="申请人" prop="applyPerson">
            <el-input v-model="form.applyPerson" size="mini" readonly></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="申请人电话" prop="contactCellPhone">
            <el-input v-model="form.contactCellPhone" size="mini" readonly></el-input>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <el-divider content-position="left">
      设备管理
    </el-divider>
    <el-table
      :data="form.collects"
      style="width: 100%"
      class="collTableStyle"
      row-key="detailId"
      size="mini"
      border>
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-table
            :data="props.row.devices"
            style="width: 100%"
            border
            size="mini">
            <el-table-column
              label="监控因子"
              prop="element"
              show-overflow-tooltip>
            </el-table-column>
            <el-table-column
              label="设备品牌"
              prop="brand"
              show-overflow-tooltip>
            </el-table-column>
            <el-table-column
              label="设备型号"
              prop="type"
              show-overflow-tooltip>
            </el-table-column>
            <el-table-column
              label="限制下限"
              prop="alarmMin"
              show-overflow-tooltip>
            </el-table-column>
            <el-table-column
              label="限制上限"
              prop="alarmMax"
              show-overflow-tooltip>
            </el-table-column>
          </el-table>
        </template>
      </el-table-column>
      <el-table-column
        label="监控点名称"
        prop="checkpointDesc"
        show-overflow-tooltip>
      </el-table-column>
      <el-table-column
        label="数采品牌"
        prop="collectDevDesc"
        show-overflow-tooltip>
      </el-table-column>
      <el-table-column
        label="数采型号"
        prop="collectDevSpec"
        show-overflow-tooltip>
      </el-table-column>
      <el-table-column
        label="MN码"
        prop="mn"
        show-overflow-tooltip>
      </el-table-column>
    </el-table>
    <el-divider content-position="left">
      附件列表
    </el-divider>
    <el-table
      :data="fileData"
      style="width: 100%"
      size="mini"
      border
      height="150">
      <el-table-column
        prop="fileName"
        label="附件名">
        <template slot-scope="scope">
          <el-link
            type="primary"
            :underline="false"
            target="_blank"
            :href="getlink(scope.row.attachfileid)"
          >{{ scope.row.fileName }}
          </el-link>
        </template>
      </el-table-column>
<!--      <el-table-column-->
<!--        label="附件类型">-->
<!--        <template slot-scope="scope">-->
<!--          {{ scope.row.masterType === "Ent_ConnectApply_Approval" ? "企业上传" : "分局上传" }}-->
<!--        </template>-->
<!--      </el-table-column>-->
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-link type="danger" v-if="scope.row.masterType === 'Ent_ConnectApply'">删除</el-link>
          <el-link type="info" v-else>-</el-link>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import { dgomannexList } from "@/api/dgom";

export default {
  props: {
    detailsData: {
      type: Object,
      default: () => ({})
    }
  },
  watch: {
    detailsData: {
      handler(v) {
        if (v) {
          this.form = Object.assign({}, v);
          dgomannexList({ applyId: v.applyId }).then(res => {
            if (res.code === 200) {
              this.fileData = res.data;
            }
          });
        }
      },
      immediate: true
    }
  },
  data: () => ({
    form: {},
    fileData: []
  }),
  created() {},
  methods: {
    getlink (id) {
      return `${process.env.VUE_APP_BASE_API}/dgom/attach/download/${id}`;
    },
  }
};
</script>

<style lang="scss" scoped>
.apply-lw {
  /deep/ .el-form-item {
    margin-bottom: 8px;
  }
}

</style>
