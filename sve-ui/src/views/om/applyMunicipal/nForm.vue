<template>
  <div class="apply-lw">
    <el-form ref="form" label-width="140px">
      <el-row :gutter="15">
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="企业名称">
            <el-input v-model="detailsData.sourceName" size="mini" readonly></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="社会统一信用代码">
            <el-input v-model="detailsData.socialCreditCode" size="mini" readonly></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="行业">
            <el-input v-model="detailsData.industryName" size="mini" readonly></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="环保负责人">
            <el-input v-model="detailsData.envChargeMan" size="mini" readonly></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="负责人电话">
            <el-input v-model="detailsData.contactTel" size="mini" readonly></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="网络运营商">
            <el-input v-model="detailsData.networkOperator" size="mini" readonly></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="镇街">
            <el-input v-model="detailsData.townName" size="mini" readonly></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="详细地址">
            <el-input v-model="detailsData.address" size="mini" readonly></el-input>
          </el-form-item>
        </el-col>

        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="经度">
            <el-input v-model="detailsData.longitude" size="mini" readonly></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="纬度">
            <el-input v-model="detailsData.latitude" size="mini" readonly></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="申请人">
            <el-input v-model="detailsData.applyPerson" size="mini" readonly></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="申请人电话">
            <el-input v-model="detailsData.contactCellPhone" size="mini" readonly></el-input>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <el-divider content-position="left">
      设备管理
    </el-divider>
    <el-table
      :data="detailsData.collects?detailsData.collects:[]"
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
    <el-table
      :data="fileData"
      style="width: 100%"
      size="mini"
      border
      max-height="350">
      <el-table-column
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
    </el-table>
    <el-divider content-position="left">
      分局审核
    </el-divider>
    <el-form label-width="140px" style="height:160px">
      <el-col :lg="24" :md="24" :sm="24">
        <el-form-item label="分局经办人">
          <el-input v-model="detailsData.approvedBy" size="mini" readonly></el-input>
        </el-form-item>
      </el-col>
      <el-col :lg="24" :md="24" :sm="24">
        <el-form-item label="分局批复时间">
          <el-input v-model="detailsData.approvedDate" size="mini" readonly></el-input>
        </el-form-item>
      </el-col>
      <el-col :lg="24" :md="24" :sm="24">
        <el-form-item label="分局批复意见" prop="approvalDesc">
          <el-input v-model="detailsData.approvalDesc" size="mini" type="textarea" readonly></el-input>
        </el-form-item>
      </el-col>
    </el-form>
    <el-divider content-position="left">
      市局审核
    </el-divider>
    <el-form label-width="140px" style="height:160px" :model="approvedForm" ref="approvedForm" :rules="rules">
      <el-col :lg="24" :md="24" :sm="24">
        <el-form-item label="市局确认人" prop="muApprovedBy">
          <el-input v-model="approvedForm.muApprovedBy" size="mini"></el-input>
        </el-form-item>
      </el-col>
      <el-col :lg="24" :md="24" :sm="24">
        <el-form-item label="市局确认意见" prop="muApprovalDesc">
          <el-input size="mini" type="textarea" v-model="approvedForm.muApprovalDesc"></el-input>
        </el-form-item>
      </el-col>
    </el-form>
  </div>
</template>

<script>
import {dgomannexList, applyMunicipal} from "@/api/om";

export default {
  props: {
    detailsData: {
      type: Object,
      default: () => ({})
    },
  },
  data () {
    return {
      attachtype: "",
      fileData: [],
      approvedForm: {},
      rules: {
        muApprovedBy: [
          {required: true, message: "市局确认人必填", trigger: "blur"}
        ],
        muApprovalDesc: [
          {required: true, message: "市局确认意见必填", trigger: "blur"}
        ],
      }
    };
  },
  watch: {
    detailsData: {
      handler (v) {
        if (v) {
          this.getFileList(v.applyId);
        }
      },
      immediate: true
    }
  },
  created () {
  },
  methods: {
    getFileList (v) {
      dgomannexList({applyId: v}).then((res) => {
        if (res.code === 200) {
          this.fileData = res.data;
        }
      });
    },
    getlink (id) {
      return `${process.env.VUE_APP_BASE_API}/dgom/attach/download/${id}`;
    },
    submitForm (v) {
      this.$refs["approvedForm"].validate((valid) => {
        if (!valid) return;
        let {muApprovedBy, muApprovalDesc} = this.approvedForm;
        let oV = {
          muApprovedBy,
          muApprovalDesc,
          status: v,
          applyId: this.detailsData.applyId,
        };
        applyMunicipal(oV).then(res => {
          if (res.code === 200) {
            this.msgSuccess(res.message);
            this.$emit("complete");
          }
        });
      });
    }
  }
};
</script>

<style lang="scss" scoped>
.apply-lw {
  height: 100%;
  width: 100%;
  overflow-y: auto;
  overflow-x: hidden;

  /deep/ .el-form-item {
    margin-bottom: 12px;
  }
}

.collTableStyle {
  /deep/ .el-table__expanded-cell {
    padding: 5px 5px 5px 47px;
  }
}
</style>
