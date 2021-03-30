<template>
    <div class="source-edit">
		<el-divider content-position="right">
			<el-button type="primary" @click="submitForm">提交</el-button>
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
                    <el-form-item label="污染源名称" prop="fullName">
                        <el-input
                            v-model="formData.fullName"
                            placeholder="请输入污染源名称"
                            readonly
                            clearable
                            :style="{width: '100%'}"
                        ></el-input>
                    </el-form-item>
                </el-col>
                <el-col :lg="4" :md="6" :sm="12">
                    <el-form-item label="污染源代码" prop="sourceNo">
                        <el-input
                            v-model="formData.sourceNo"
                            placeholder="请输入污染源代码"
                            clearable
                            maxlength="20"
                            show-word-limit
                            :style="{width: '100%'}"
                        ></el-input>
                    </el-form-item>
                </el-col>
                <el-col :lg="4" :md="6" :sm="12">
                    <el-form-item label="社会统一信用代码" prop="socialCreditCode">
                        <el-input
                            v-model="formData.socialCreditCode"
                            placeholder="请输入社会统一信用代码"
                            clearable
                            maxlength="30"
                            show-word-limit
                            :style="{width: '100%'}"
                        ></el-input>
                    </el-form-item>
                </el-col>
                <el-col :lg="4" :md="6" :sm="12">
                    <el-form-item label="组织机构代码" prop="organizationCode">
                        <el-input
                            v-model="formData.organizationCode"
                            placeholder="请输入组织机构代码"
                            clearable
                            maxlength="30"
                            show-word-limit
                            :style="{width: '100%'}"
                        ></el-input>
                    </el-form-item>
                </el-col>
                <el-col :lg="4" :md="6" :sm="12">
                    <el-form-item label="排污许可证代码" prop="emissionPermitCode">
                        <el-input
                            v-model="formData.emissionPermitCode"
                            placeholder="请输入排污许可证代码"
                            clearable
                            maxlength="30"
                            show-word-limit
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
                            maxlength="40"
                            show-word-limit
                            :style="{width: '100%'}"
                        ></el-input>
                    </el-form-item>
                </el-col>
                <el-col :lg="4" :md="6" :sm="12">
                    <el-form-item label="经度" prop="longitude">
                        <el-input
                            v-model="formData.longitude"
                            placeholder="请输入经度 (113.516667~114.250000)"
                            clearable
                            onkeyup="value=value.replace(/[^\d^\.]+/g,'')"
                            @input="addLongitude(formData.longitude)"
                            :style="{width: '100%'}"
                        ></el-input>
                    </el-form-item>
                </el-col>
                <el-col :lg="4" :md="6" :sm="12">
                    <el-form-item label="纬度" prop="latitude">
                        <el-input
                            v-model="formData.latitude"
                            placeholder="请输入纬度 (22.650000 ~23.150000)"
                            clearable
                            onkeyup="value=value.replace(/[^\d^\.]+/g,'')"
                            @input="latitude(formData.latitude)"
                            :style="{width: '100%'}"
                        ></el-input>
                    </el-form-item>
                </el-col>
                <el-col :lg="4" :md="6" :sm="12">
                    <el-form-item label="污染源状态" prop="sourceStatus">
                        <el-select
                            v-model="formData.sourceStatus"
                            placeholder="请选择污染源状态"
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
                <el-col :lg="4" :md="6" :sm="12">
                    <el-form-item label="法人姓名" prop="legalPersonName">
                        <el-input
                            v-model="formData.legalPersonName"
                            placeholder="请输入法人姓名"
                            clearable
                            maxlength="12"
                            show-word-limit
                            :style="{width: '100%'}"
                        ></el-input>
                    </el-form-item>
                </el-col>
                <el-col :lg="4" :md="6" :sm="12">
                    <el-form-item label="法人联系电话" prop="legalPersonPhone">
                        <el-input
                            v-model="formData.legalPersonPhone"
                            placeholder="请输入法人联系电话"
                            clearable
                            maxlength="16"
                            show-word-limit
                            :style="{width: '100%'}"
                        ></el-input>
                    </el-form-item>
                </el-col>
                <el-col :lg="4" :md="6" :sm="12">
                    <el-form-item label="环保联系人" prop="envContactName">
                        <el-input
                            v-model="formData.envContactName"
                            placeholder="请输入环保联系人"
                            clearable
                            maxlength="12"
                            show-word-limit
                            :style="{width: '100%'}"
                        ></el-input>
                    </el-form-item>
                </el-col>
                <el-col :lg="4" :md="6" :sm="12">
                    <el-form-item label="环保联系人电话" prop="envContactPhone">
                        <el-input
                            v-model="formData.envContactPhone"
                            placeholder="请输入环保联系人电话"
                            clearable
                            maxlength="16"
                            show-word-limit
                            :style="{width: '100%'}"
                        ></el-input>
                    </el-form-item>
                </el-col>
                <el-col :lg="8" :md="12" :sm="24">
                    <el-form-item label="行业名称" prop="industryCode">
              			<Treeselect v-model="formData.industryCode" :options="industryCodeOptions" placeholder="请选择行业名称"/>
                    </el-form-item>
                </el-col>
                <el-col :lg="4" :md="6" :sm="12">
                    <el-form-item label="收纳水体名称" prop="inflowWaterName">
                        <el-input
                            v-model="formData.inflowWaterName"
                            placeholder="请输入收纳水体名称"
                            clearable
                            maxlength="20"
                            show-word-limit
                            :style="{width: '100%'}"
                        ></el-input>
                    </el-form-item>
                </el-col>
                <el-col :lg="4" :md="6" :sm="12">
                    <el-form-item label="产生工业废水" prop="hasWasteWater">
                        <el-input
                            v-model="formData.hasWasteWater"
                            placeholder="请输入产生工业废水"
                            clearable
                            maxlength="20"
                            show-word-limit
                            :style="{width: '100%'}"
                        ></el-input>
                    </el-form-item>
                </el-col>
                <el-col :lg="4" :md="6" :sm="12">
                    <el-form-item label="产生工业废气" prop="hasWasteGas">
                        <el-input
                            v-model="formData.hasWasteGas"
                            placeholder="请输入产生工业废气"
                            clearable
                            maxlength="20"
                            show-word-limit
                            :style="{width: '100%'}"
                        ></el-input>
                    </el-form-item>
                </el-col>
                <el-col :lg="4" :md="6" :sm="12">
                    <el-form-item label="产生一般工业固体废物" prop="hasGeneralSolid">
                        <el-input
                            v-model="formData.hasGeneralSolid"
                            placeholder="请输入产生一般工业固体废物"
                            clearable
                            maxlength="20"
                            show-word-limit
                            :style="{width: '100%'}"
                        ></el-input>
                    </el-form-item>
                </el-col>
                <el-col :lg="24">
                    <el-form-item label="工艺流程" prop="processFlow">
                        <el-input
                            v-model="formData.processFlow"
                            type="textarea"
                            placeholder="请输入工艺流程"
                            :autosize="{minRows: 4, maxRows: 4}"
                            :style="{width: '100%'}"
                        ></el-input>
                    </el-form-item>
                </el-col>
                <el-col :lg="12" :md="24">
                    <el-form-item label="工业危险废物" prop="hasHazardousWaste">
                        <el-input
                            v-model="formData.hasHazardousWaste"
                            placeholder="请输入工业危险废物"
                            clearable
                            maxlength="45"
                            show-word-limit
                            :style="{width: '100%'}"
                        ></el-input>
                    </el-form-item>
                </el-col>
                <el-col :lg="12" :md="24">
                    <el-form-item label="涉及稀土等15类矿产" prop="hasRareEarth">
                        <el-input
                            v-model="formData.hasRareEarth"
                            placeholder="请输入涉及稀土等15类矿产"
                            clearable
                            maxlength="45"
                            show-word-limit
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
                            :autosize="{minRows: 4, maxRows: 4}"
                            :style="{width: '100%'}"
                        ></el-input>
                    </el-form-item>
                </el-col>
            </el-form>
            <!-- <el-col :lg="24">
                <upload></upload>
            </el-col> -->
        </el-row>
    </div>
</template>
<script>
import { getSource, sourceEdit, getDict, getRegion, getIndustry } from "../api/source";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
import Upload from "./upload";

export default {
    components: {Treeselect, Upload},
    props: [],
    data() {
        return {
            formData: {
                fullName: undefined,
                sourceNo: undefined,
                socialCreditCode: undefined,
                organizationCode: undefined,
                emissionPermitCode: undefined,
                townCode: undefined,
                villageCode: undefined,
                address: undefined,
                longitude: undefined,
                latitude: undefined,
                sourceStatus: undefined,
                sourceScale: undefined,
                legalPersonName: undefined,
                legalPersonPhone: undefined,
                envContactName: undefined,
                envContactPhone: undefined,
                industryCode: undefined,
                inflowWaterName: undefined,
                envMonitorLevel: undefined,
                hasWasteWater: undefined,
                hasWasteGas: undefined,
                hasGeneralSolid: undefined,
                processFlow: undefined,
                hasHazardousWaste: undefined,
                hasRareEarth: undefined,
                memo: undefined
            },
            rules: {
                fullName: [
                    {
                        required: true,
                        message: "请输入污染源名称",
                        trigger: "blur"
                    }
                ],
                sourceNo: [],
                socialCreditCode: [],
                organizationCode: [],
                emissionPermitCode: [],
                townCode: [],
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
                industryCode: [],
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
            industryCodeOptions: [],
            envMonitorLevelOptions: []
        };
    },
    computed: {},
    watch: {},
    created() {
        getSource(this.$store.getters.sourceId).then(
            res => {
				this.formData = res.rows || res.data;
				getRegion(this.formData.townCode).then(res => (this.villageCodeOptions = res.rows || res.data));
			}
        );
        getDict("source_status").then(
            res => (this.sourceStatusOptions = res.rows || res.data)
        );
        getDict("source_scale").then(
            res => (this.sourceScaleOptions = res.rows || res.data)
        );
        getDict("env_monitor_level").then(
            res => (this.envMonitorLevelOptions = res.rows || res.data)
        );
		getRegion(4419).then(res => (this.townCodeOptions = res.rows || res.data));
		getIndustry().then(res => this.industryCodeOptions = res.rows || res.data);
    },
    mounted() {},
    methods: {
        townChange(townCode) {
            this.formData.villageCode = null;
            getRegion(townCode).then(
                res => (this.villageCodeOptions = res.rows || res.data)
            );
        },
        uploadFile() {

        },
        submitForm() {
            this.$refs["elForm"].validate(valid => {
                if (!valid) return;
                // TODO 提交表单
                sourceEdit(this.formData).then(res => {
                    this.msgSuccess("企业资料修改成功");
                });
            });
        },
        addLongitude(val){
            if(val){
                let reg = /^(\-|\+)?(((\d|[1-9]\d|1[0-7]\d|0{1,3})\.\d{0,6})|(\d|[1-9]\d|1[0-7]\d|0{1,3})|180\.0{0,6}|180)$/
                if(reg.test(val)){
                return
                }else{
                this.formData.longitude = ''
                this.msgError("您的输入有误,请从新输入!")
            }
            }
        },
        latitude(val){
            if(val){
                let reg=/^(\-|\+)?([0-8]?\d{1}\.\d{0,6}|90\.0{0,6}|[0-8]?\d{1}|90)$/;
                if(reg.test(val)){
                    return
                }else{
                    // val = "";
                    this.formData.latitude='';
                    this.msgError("您的输入有误,请从新输入!")
                }
            }
        }
    }
};
</script>
<style>
.source-edit {
    height: calc(100% - 74px);
    padding: 10px;
    overflow: auto;
}
</style>
