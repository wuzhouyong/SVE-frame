<template>
  <div class="source-edit">
    <el-divider content-position="right">
      <el-button type="primary" @click="submitForm" v-preventClick>提交</el-button>
    </el-divider>
    <el-row :gutter="15">
      <el-form
        ref="elForm"
        :model="formData"
        :rules="rules"
        size="medium"
        label-width="160px"
        label-position="top"
      >
        <el-col :lg="8" :md="12" :sm="24">
          <el-form-item label="企业名称" prop="fullName">
            <el-input
              v-model="formData.fullName"
              placeholder="请输入企业名称"
              clearable
              :style="{width: '100%'}"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="8" :md="6" :sm="12">
          <el-form-item label="污染源代码" prop="sourceNo">
            <el-input
              v-model="formData.sourceNo"
              placeholder="请输入污染源代码"
              clearable
              :style="{width: '100%'}"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="8" :md="6" :sm="12">
          <el-form-item label="社会统一信用代码" prop="socialCreditCode">
            <el-input
              v-model="formData.socialCreditCode"
              placeholder="请输入社会统一信用代码"
              clearable
              :style="{width: '100%'}"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="8" :md="6" :sm="12">
          <el-form-item label="组织机构代码" prop="organizationCode">
            <el-input
              v-model="formData.organizationCode"
              placeholder="请输入组织机构代码"
              clearable
              :style="{width: '100%'}"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="8" :md="6" :sm="12">
          <el-form-item label="排污许可证代码" prop="emissionPermitCode">
            <el-input
              v-model="formData.emissionPermitCode"
              placeholder="请输入排污许可证代码"
              clearable
              :style="{width: '100%'}"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="4" :md="6" :sm="12">
          <el-form-item label="镇街名称" prop="townCode">
            <el-select
              v-model="formData.townCode"
              placeholder="请选择镇街名称"
              clearable
              :style="{width: '100%'}"
              @change="townChange"
            >
              <el-option
                v-for="(item, index) in townCodeOptions"
                :key="index"
                :label="item.regionName"
                :value="item.regionCode"
                :disabled="item.disabled"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :lg="4" :md="6" :sm="12">
          <el-form-item label="社区名称" prop="villageCode">
            <el-select
              v-model="formData.villageCode"
              placeholder="请选择社区名称"
              clearable
              :style="{width: '100%'}"
            >
              <el-option
                v-for="(item, index) in villageCodeOptions"
                :key="index"
                :label="item.regionName"
                :value="item.regionCode"
                :disabled="item.disabled"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :lg="8" :md="12" :sm="24">
          <el-form-item label="地址" prop="address">
            <el-input
              v-model="formData.address"
              placeholder="请输入地址"
              clearable
              :style="{width: '100%'}"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="4" :md="6" :sm="12">
          <el-form-item label="经度" prop="longitude">
            <el-input
              v-model="formData.longitude"
              placeholder="请填写经度(格式:113.516)"
              clearable
              :style="{width: '100%'}"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="4" :md="6" :sm="12">
          <el-form-item label="纬度" prop="latitude">
            <el-input
              v-model="formData.latitude"
              placeholder="请填写纬度(格式:22.650)"
              clearable
              :style="{width: '100%'}"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="4" :md="6" :sm="12">
          <el-form-item label="企业经营状态" prop="sourceStatus">
            <el-select
              v-model="formData.sourceStatus"
              placeholder="请选择企业经营状态"
              clearable
              :style="{width: '100%'}"
            >
              <el-option
                v-for="(item, index) in sourceStatusOptions"
                :key="index"
                :label="item.dictLabel"
                :value="item.dictValue"
                :disabled="item.disabled"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :lg="4" :md="6" :sm="12">
          <el-form-item label="企业规模" prop="sourceScale">
            <el-select
              v-model="formData.sourceScale"
              placeholder="请选择企业规模"
              clearable
              :style="{width: '100%'}"
            >
              <el-option
                v-for="(item, index) in sourceScaleOptions"
                :key="index"
                :label="item.dictLabel"
                :value="item.dictValue"
                :disabled="item.disabled"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :lg="8" :md="6" :sm="12">
          <el-form-item label="行业名称" prop="industryCode">
            <Treeselect
              v-model="formData.industryCode"
              :options="industryCode1Options"
              placeholder="请选择行业名称"
            />
          </el-form-item>
        </el-col>
        <el-col :lg="4" :md="6" :sm="12" style="margin-bottom: 2px;">
          <el-form-item label="法人姓名" prop="legalPersonName">
            <el-input
              v-model="formData.legalPersonName"
              placeholder="请输入法人姓名"
              maxlength="10"
              show-word-limit
              clearable
              :style="{width: '100%'}"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="8" :md="6" :sm="12" style="margin-bottom: 2px;">
          <el-form-item label="法人联系电话" prop="legalPersonPhone">
            <el-input
              v-model="formData.legalPersonPhone"
              placeholder="请输入法人联系电话"
              maxlength="20"
              show-word-limit
              clearable
              :style="{width: '100%'}"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="4" :md="6" :sm="12">
          <el-form-item label="环保联系人" prop="envContactName">
            <el-input
              v-model="formData.envContactName"
              placeholder="请输入环保联系人"
              maxlength="10"
              show-word-limit
              clearable
              :style="{width: '100%'}"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="8" :md="6" :sm="12">
          <el-form-item label="环保联系人电话" prop="envContactPhone">
            <el-input
              v-model="formData.envContactPhone"
              placeholder="请输入环保联系人电话"
              maxlength="20"
              show-word-limit
              clearable
              :style="{width: '100%'}"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="16" :md="6" :sm="12">
          <el-form-item label="收纳水体名称" prop="inflowWaterName">
            <el-input
              v-model="formData.inflowWaterName"
              placeholder="请输入收纳水体名称"
              maxlength="50"
              show-word-limit
              clearable
              :style="{width: '100%'}"
            ></el-input>
          </el-form-item>
        </el-col>
        <!-- <el-col :lg="4" :md="6" :sm="12">
          <el-form-item label="监控级别" prop="envMonitorLevel">
            <el-select
              v-model="formData.envMonitorLevel"
              placeholder="请选择监控级别"
              clearable
              :style="{width: '100%'}"
            >
              <el-option
                v-for="(item, index) in envMonitorLevelOptions"
                :key="index"
                :label="item.dictLabel"
                :value="item.dictValue"
                :disabled="item.disabled"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>-->
        <el-col :lg="12" :md="6" :sm="12">
          <el-form-item label="产生工业废水" prop="hasWasteWater">
            <el-input
              v-model="formData.hasWasteWater"
              placeholder="请输入产生工业废水"
              maxlength="50"
              show-word-limit
              clearable
              :style="{width: '100%'}"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="6" :sm="12">
          <el-form-item label="产生工业废气" prop="hasWasteGas">
            <el-input
              v-model="formData.hasWasteGas"
              placeholder="请输入产生工业废气"
              maxlength="50"
              show-word-limit
              clearable
              :style="{width: '100%'}"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="24" :md="6" :sm="12">
          <el-form-item label="产生一般工业固体废物" prop="hasGeneralSolid">
            <el-input
              v-model="formData.hasGeneralSolid"
              placeholder="请输入产生一般工业固体废物"
              maxlength="50"
              show-word-limit
              clearable
              :style="{width: '100%'}"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="24">
          <el-form-item label="工业流程" prop="processFlow">
            <el-input
              v-model="formData.processFlow"
              type="textarea"
              placeholder="请输入工业流程"
              :autosize="{minRows: 4, maxRows: 4}"
              maxlength="500"
              show-word-limit
              :style="{width: '100%'}"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="24">
          <el-form-item label="工业危险废物" prop="hasHazardousWaste">
            <el-input
              v-model="formData.hasHazardousWaste"
              placeholder="请输入工业危险废物"
              maxlength="50"
              show-word-limit
              clearable
              :style="{width: '100%'}"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="24">
          <el-form-item label="涉及稀土等15类矿产" prop="hasRareEarth">
            <el-input
              v-model="formData.hasRareEarth"
              placeholder="请输入涉及稀土等15类矿产"
              maxlength="50"
              show-word-limit
              clearable
              :style="{width: '100%'}"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="24">
          <el-form-item label="备注" prop="memo">
            <el-input
              v-model="formData.memo"
              type="textarea"
              placeholder="请输入备注"
              maxlength="500"
              show-word-limit
              :autosize="{minRows: 4, maxRows: 4}"
              :style="{width: '100%'}"
            ></el-input>
          </el-form-item>
        </el-col>
      </el-form>
    </el-row>
  </div>
</template>
<script>
import {
  getSource,
  sourceEdit,
  sourceAdd,
  getDict,
  getRegion,
  getIndustry
} from "@/api/composite/source";
import Treeselect from "@riophae/vue-treeselect";
import {noticeOperaAdd} from "@/api/system/config";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  components: {Treeselect},
  props: {
    sData: {
      type: Object,
      default: {}
    }
  },
  data () {
    return {
      formData: {
        fullName: null,
        sourceNo: null,
        socialCreditCode: null,
        organizationCode: null,
        emissionPermitCode: null,
        townCode: null,
        villageCode: null,
        address: null,
        longitude: null,
        latitude: null,
        sourceStatus: null,
        sourceScale: null,
        legalPersonName: null,
        legalPersonPhone: null,
        envContactName: null,
        envContactPhone: null,
        industryCode: null,
        inflowWaterName: null,
        envMonitorLevel: null,
        hasWasteWater: null,
        hasWasteGas: null,
        hasGeneralSolid: null,
        processFlow: null,
        hasHazardousWaste: null,
        hasRareEarth: null,
        memo: null
      },
      rules: {
        fullName: [
          {
            required: true,
            message: "请输入污染源名称",
            trigger: "blur"
          }
        ],
        socialCreditCode: [
          {max: 50, message: "长度不超过50", trigger: "blur"}
        ],
        organizationCode: [
          {max: 50, message: "长度不超过50", trigger: "blur"}
        ],
        emissionPermitCode: [
          {max: 50, message: "长度不超过50", trigger: "blur"}
        ],
        townCode: [{
          required: true,
          message: "镇街必选",
          trigger: "blur"
        }],
        villageCode: [],
        address: [],
        longitude: [],
        latitude: [],
        sourceStatus: [],
        sourceScale: [],
        legalPersonName: [],
        legalPersonPhone: [],
        envContactName: [],
        envContactPhone: [],
        industryCode1: [],
        inflowWaterName: [],
        envMonitorLevel: [],
        hasWasteWater: [],
        hasWasteGas: [],
        hasGeneralSolid: [],
        processFlow: [],
        hasHazardousWaste: [],
        hasRareEarth: [],
        memo: []
      },
      townCodeOptions: [],
      villageCodeOptions: [],
      sourceStatusOptions: [],
      sourceScaleOptions: [],
      industryCode1Options: [],
      envMonitorLevelOptions: []
    };
  },
  computed: {},
  watch: {
    sData: {
      handler (v) {
        if (v.sourceId) {
          this.formData = JSON.parse(JSON.stringify(this.sData));
          this.formData.fullName = this.sData.sourceName;
          getRegion(this.sData.townCode).then(
            (res) => (this.villageCodeOptions = res.data)
          );
        }
      },
      immediate: true
    }
  },
  created () {
    getDict("source_status").then(
      (res) => (this.sourceStatusOptions = res.data)
    );
    getDict("source_scale").then((res) => (this.sourceScaleOptions = res.data));
    getDict("env_monitor_level").then(
      (res) => (this.envMonitorLevelOptions = res.data)
    );
    getRegion(4419).then((res) => (this.townCodeOptions = res.data));
    getIndustry().then((res) => (this.industryCode1Options = res.data));
  },
  mounted () {
  },
  methods: {
    townChange (townCode) {
      this.formData.villageCode = null;
      getRegion(townCode).then((res) => (this.villageCodeOptions = res.data));
    },
    submitForm () {
      this.$refs["elForm"].validate((valid) => {
        if (!valid) return;
        if (this.formData.sourceId) {
          sourceEdit(this.formData).then((res) => {
            let journal = {
              module: "综合管理 → 污染源管理 → 污染源列表",
              action: `${this.formData.fullName} → 编辑企业操作`,
              operaResult: res.code === 200 ? "操作成功" : "操作失败",
              request: "/enterprise/source/edit"
            };
            noticeOperaAdd(journal);
            if (res.code === 200) {
              this.msgSuccess("企业修改成功");
              this.$emit("successRequest");
            } else {
              this.msgError(res.message);
            }
          });
        } else {
          sourceAdd(this.formData).then((res) => {
            if (res.code === 200) {
              this.msgSuccess("企业新增成功");
              this.$emit("successRequest");
            } else {
              this.msgError(res.message);
            }
          });
        }
      });
    }
  }
};
</script>
<style>
.source-edit {
  padding: 10px;
}
</style>
