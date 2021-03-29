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
            <el-input v-model="form.industryname" size="mini" readonly></el-input>
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
            <el-input v-model="form.townname" size="mini" readonly></el-input>
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
      <el-upload
        class="upload-demo"
        :on-change="handleChange"
        action="Fake Action"
        :data="{attachtype:attachtype}"
        :show-file-list="false"
        :auto-upload="false"
      >
        <el-button type="primary" size="mini" icon="el-icon-plus">附件上传</el-button>
      </el-upload>
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
      </el-table-column>
      <el-table-column
        label="附件类型">
        <template slot-scope="scope">
          {{ scope.row.masterType === "Ent_ConnectApply_Approval" ? "分局上传" : "企业上传" }}
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-link type="danger" v-if="scope.row.masterType === 'Ent_ConnectApply_Approval'">删除</el-link>
          <el-link type="info" v-else>-</el-link>
        </template>
      </el-table-column>
    </el-table>
    <el-divider content-position="left">
      分局审核
    </el-divider>
    <el-form ref="examineForm" :model="examineForm" label-width="140px" :rules="rules">
      <el-col :lg="24" :md="24" :sm="24">
        <el-form-item label="审核意见">
          <el-radio-group v-model="examineForm.resource" size="mini">
            <el-radio border label="同意"></el-radio>
            <el-radio border label="拒绝"></el-radio>
          </el-radio-group>
        </el-form-item>
      </el-col>
      <el-col :lg="24" :md="24" :sm="24">
        <el-form-item label="分局经办人" prop="approvedBy">
          <el-input v-model="examineForm.approvedBy" size="mini"></el-input>
        </el-form-item>
      </el-col>
      <el-col :lg="24" :md="24" :sm="24">
        <el-form-item label="分局批复意见" prop="desc">
          <el-input v-model="examineForm.desc" size="mini" type="textarea" :rows="4"></el-input>
        </el-form-item>
      </el-col>
    </el-form>

  </div>
</template>

<script>
import { uploadFile } from "@/api/upload";

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
        if (v) this.form = Object.assign({}, v);
      },
      immediate: true
    }
  },
  data: () => ({
    form: {},
    attachtype: "",
    examineForm: {},
    rules: {},
    fileData: []
  }),
  created() {},
  methods: {
    handleChange(file) {
      let _that = this;
      let reader = new FileReader();
      reader.readAsDataURL(file.raw);
      reader.onload = (e) => {
        let imgcode = e.target.result;
        const data = {
          fileName: file.name,
          fileData: imgcode,
          applyId: _that.ids,
          masterType: "Ent_ConnectApply_Approval"
        };
        uploadFile(data).then((res) => {
          if (res.code === 200) {
            this.msgSuccess("附件上传成功");
          }
        });
      };
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
    margin-bottom: 8px;
  }
}

.collTableStyle {
  /deep/ .el-table__expanded-cell {
    padding: 5px 5px 5px 47px;
  }
}
</style>
