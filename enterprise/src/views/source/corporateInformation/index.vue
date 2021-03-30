<template>
  <div class="wrapper">
    <!-- <h4>企业信息</h4> -->
    <div class="divider_btn">
      <el-divider content-position="left" class="divider">基本信息</el-divider>
      <div class="btn_group">
        <el-button size="small" type="primary" @click="changeRequest">修改企业基本信息</el-button>
        <el-button size="small" type="info" @click="checkInformation">查看修改申请</el-button>
      </div>
    </div>
    <div class="info">
      <el-form>
        <el-col>
          <el-form-item label="企业名称:">{{ formData.fullName }}</el-form-item>
          <el-form-item label="排污许可证代码:">{{ formData.emissionPermitCode }}</el-form-item>
          <el-form-item label="经度:">{{ formData.longitude }}</el-form-item>
          <el-form-item label="企业地址:">{{ formData.address }}</el-form-item>
          <el-form-item label="行业类别:">{{ formData.industryName }}</el-form-item>
          <el-form-item label="法定代表人:">{{ formData.legalPersonName }}</el-form-item>

        </el-col>
        <el-col>
          <el-form-item label="社会统一信用码:">{{ formData.socialCreditCode }}</el-form-item>
          <el-form-item label="所属镇街:">{{ formData.townName }}</el-form-item>
          <el-form-item label="维度:">{{ formData.latitude }}</el-form-item>
          <el-form-item label="企业经营状态:">{{ getStatusName(formData.sourceStatus) }}</el-form-item>
          <el-form-item label="企业规模:">{{ getScaleName(formData.sourceScale) }}</el-form-item>
          <el-form-item label="法人联系电话:">{{ formData.legalPersonPhone }}</el-form-item>
        </el-col>
      </el-form>
    </div>
    <div class="divider_btn">
      <el-divider content-position="left" class="divider">环保信息</el-divider>
      <div class="btn_group">
        <el-button size="small" type="primary" @click="changeProtection">保存环保基本信息</el-button>
      </div>
    </div>
    <div class="form_group">
      <el-form size="medium" label-width="160px" label-position="top" :rules="rules" ref="source" :model="source">
        <el-row :gutter="15">
          <el-col :lg="6" :md="8" :sm="24">
            <el-form-item label="环保联系人" prop="envContactName">
              <el-input
                placeholder="请输入环保联系人"
                clearable
                v-model="source.envContactName"
                :style="{width: '100%'}"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :lg="6" :md="12" :sm="24">
            <el-form-item label="环保联系人电话" prop="envContactPhone">
              <el-input
                placeholder="请输入环保联系人电话"
                clearable
                v-model="source.envContactPhone"
                maxlength="11"
                show-word-limit
                :style="{width: '100%'}"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :lg="6" :md="12" :sm="24">
            <el-form-item label="涉及稀土等15类矿产" prop="hasRareEarth">
              <el-input
                placeholder="请输入涉及稀土等15类矿产"
                clearable
                v-model="source.hasRareEarth"
                maxlength="20"
                show-word-limit
                :style="{width: '100%'}"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :lg="6" :md="12" :sm="24">
            <el-form-item label="收纳水体名称" prop="inflowWaterName">
              <el-input
                placeholder="请输入收纳水体名称"
                clearable
                v-model="source.inflowWaterName"
                maxlength="50"
                show-word-limit
                :style="{width: '100%'}"
              ></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="15">
          <el-col :lg="6" :md="12" :sm="24">
            <el-form-item label="产生工业废水" prop="hasWasteWater">
              <el-input
                placeholder="请输入产生工业废水"
                clearable
                v-model="source.hasWasteWater"
                maxlength="50"
                show-word-limit
                :style="{width: '100%'}"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :lg="6" :md="12" :sm="24">
            <el-form-item label="产生工业废气" prop="hasWasteGas">
              <el-input
                placeholder="请输入产生工业废气"
                clearable
                v-model="source.hasWasteGas"
                maxlength="50"
                show-word-limit
                :style="{width: '100%'}"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :lg="6" :md="12" :sm="24">
            <el-form-item label="产生工业一般固体" prop="hasGeneralSolid">
              <el-input
                placeholder="请输入产生工业一般固体"
                clearable
                v-model="source.hasGeneralSolid"
                maxlength="50"
                show-word-limit
                :style="{width: '100%'}"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :lg="6" :md="12" :sm="24">
            <el-form-item label="产生工业危险物" prop="hasHazardousWaste">
              <el-input
                placeholder="请输入产生工业危险物"
                clearable
                v-model="source.hasHazardousWaste"
                maxlength="50"
                show-word-limit
                :style="{width: '100%'}"
              ></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :lg="24" :md="24" :sm="24">
            <el-form-item label="工业流程" prop="processFlow">
              <el-input
                placeholder="请输入工业流程"
                clearable
                v-model="source.processFlow"
                maxlength="500"
                show-word-limit
                type="textarea"
                :rows="2"
                :style="{width: '100%'}"
              ></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :lg="24" :md="24" :sm="24">
            <el-form-item label="备注" prop="memo">
              <el-input
                placeholder="请输入备注"
                clearable
                type="textarea"
                :rows="2"
                v-model="source.memo"
                maxlength="500"
                show-word-limit
                :style="{width: '100%'}"
              ></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </div>
    <div class="divider_btn" v-if="hasVoc">
      <el-divider content-position="left" class="divider">VOCs废气治理设施参数</el-divider>
      <div class="btn_group">
        <el-button size="small" type="primary" @click="changeVOC">保存VOCs废气治理设施参数</el-button>
        <el-link :href="getVOCslink(this.formData.sourceId)" type="primary" :underline="false" target="_blank"
                 class="download">导出VOCs废气治理设施参数表
        </el-link>
      </div>
    </div>
    <div v-if="hasVoc" class="form_group voc">
      <el-form size="medium" label-width="160px" label-position="top">
        <el-row :gutter="15">
          <el-col :lg="8" :md="12" :sm="24">
            <el-form-item label="治理投资 单位（万元）" prop="invsertTotal">
              <el-input
                placeholder="治理投资 单位（万元）"
                clearable
                v-model="voc.invsertTotal"
                maxlength="128"
                show-word-limit
                :style="{width: '100%'}"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :lg="8" :md="24" :sm="24">
            <el-form-item label="环保施工单位" prop="constructionUnit">
              <el-input
                placeholder="环保施工单位"
                clearable
                v-model="voc.constructionUnit"
                maxlength="128"
                show-word-limit
                :style="{width: '100%'}"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :lg="8" :md="24" :sm="24">
            <el-form-item label="生产工艺" prop="productFlow">
              <el-input
                placeholder="生产工艺"
                clearable
                v-model="voc.productFlow"
                maxlength="128"
                show-word-limit
                :style="{width: '100%'}"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :lg="8" :md="24" :sm="24">
            <el-form-item label="废气收集情况" prop="gasCollect">
              <el-input
                placeholder="废气收集情况"
                clearable
                v-model="voc.gasCollect"
                maxlength="128"
                show-word-limit
                :style="{width: '100%'}"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :lg="8" :md="12" :sm="24">
            <el-form-item label="治理工艺" prop="processFlow">
              <el-input
                placeholder="治理工艺"
                clearable
                v-model="voc.processFlow"
                maxlength="128"
                show-word-limit
                :style="{width: '100%'}"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :lg="8" :md="12" :sm="24">
            <el-form-item label="水泵功率和流量（注明台数、使用工艺阶段及各自功率）" prop="keyPump">
              <el-input
                placeholder="水泵功率和流量（注明台数、使用工艺阶段及各自功率）"
                clearable
                v-model="voc.keyPump"
                maxlength="128"
                show-word-limit
                :style="{width: '100%'}"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :lg="8" :md="12" :sm="24">
            <el-form-item label="风机功率和风量（注明台数、使用工艺阶段及各自功率）" prop="keyBlower">
              <el-input
                placeholder="风机功率和风量（注明台数、使用工艺阶段及各自功率）"
                clearable
                v-model="voc.keyBlower"
                maxlength="128"
                show-word-limit
                :style="{width: '100%'}"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :lg="8" :md="12" :sm="24">
            <el-form-item label="吸附材料材质及使用量（注明吸附塔数量及各自使用量）" prop="keySorbMaterial">
              <el-input
                placeholder="吸附材料材质及使用量（注明吸附塔数量及各自使用量）"
                clearable
                v-model="voc.keySorbMaterial"
                maxlength="128"
                show-word-limit
                :style="{width: '100%'}"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :lg="8" :md="12" :sm="24">
            <el-form-item label="吸附材料更换周期（注明吸附塔数量及各自周期）" prop="keySorbPeriod">
              <el-input
                placeholder="吸附材料更换周期（注明吸附塔数量及各自周期）"
                clearable
                v-model="voc.keySorbPeriod"
                maxlength="128"
                show-word-limit
                :style="{width: '100%'}"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :lg="8" :md="12" :sm="24">
            <el-form-item label="光催化处理设备功率（注明台数及各自功率）" prop="keyPhotoPower">
              <el-input
                placeholder="光催化处理设备功率（注明台数及各自功率）"
                clearable
                v-model="voc.keyPhotoPower"
                maxlength="128"
                show-word-limit
                :style="{width: '100%'}"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :lg="8" :md="12" :sm="24">
            <el-form-item label="等离子处理设备功率（注明台数及各自功率）" prop="keyPlasmaPower">
              <el-input
                placeholder="等离子处理设备功率（注明台数及各自功率）"
                clearable
                v-model="voc.keyPlasmaPower"
                maxlength="128"
                show-word-limit
                :style="{width: '100%'}"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :lg="8" :md="12" :sm="24">
            <el-form-item label="脱附回收效率（仅限于回收法）" prop="keyRecoveryRate">
              <el-input
                placeholder="脱附回收效率（仅限于回收法）"
                clearable
                v-model="voc.keyRecoveryRate"
                maxlength="128"
                show-word-limit
                :style="{width: '100%'}"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :lg="8" :md="12" :sm="24">
            <el-form-item label="一般燃烧温度（包括但不限于RCO/RTO等类似工艺）" prop="keyBurnTemp">
              <el-input
                placeholder="一般燃烧温度（包括但不限于RCO/RTO等类似工艺"
                clearable
                v-model="voc.keyBurnTemp"
                maxlength="128"
                show-word-limit
                :style="{width: '100%'}"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :lg="8" :md="12" :sm="24">
            <el-form-item label="吸附时间（包括但不限于RCO/RTO等类似工艺）" prop="keyRecoveryTime">
              <el-input
                placeholder="吸附时间（包括但不限于RCO/RTO等类似工艺）"
                clearable
                v-model="voc.keyRecoveryTime"
                maxlength="128"
                show-word-limit
                :style="{width: '100%'}"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :lg="8" :md="12" :sm="24">
            <el-form-item label="催化剂失效周期（采用含催化剂的工艺）" prop="keyLossPeriod">
              <el-input
                placeholder="催化剂失效周期（采用含催化剂的工艺"
                clearable
                v-model="voc.keyLossPeriod"
                maxlength="128"
                show-word-limit
                :style="{width: '100%'}"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :lg="8" :md="12" :sm="24">
            <el-form-item label="燃烧器装机量" prop="keyBurnerCount">
              <el-input
                placeholder="燃烧器装机量"
                clearable
                v-model="voc.keyBurnerCount"
                maxlength="128"
                show-word-limit
                :style="{width: '100%'}"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :lg="8" :md="12" :sm="24">
            <el-form-item label="加热时间（包括但不限于RCO/RTO等类似工艺）" prop="keyHeatTime">
              <el-input
                placeholder="加热时间（包括但不限于RCO/RTO等类似工艺"
                clearable
                v-model="voc.keyHeatTime"
                maxlength="128"
                show-word-limit
                :style="{width: '100%'}"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :lg="8" :md="12" :sm="24">
            <el-form-item label="换热器温度（如有）" prop="keyInterTemp">
              <el-input
                placeholder="换热器温度（如有）"
                clearable
                v-model="voc.keyInterTemp"
                maxlength="128"
                show-word-limit
                :style="{width: '100%'}"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :lg="8" :md="12" :sm="24">
            <el-form-item label="排气温度" prop="keyOutgasTemp">
              <el-input
                placeholder="排气温度"
                clearable
                v-model="voc.keyOutgasTemp"
                maxlength="128"
                show-word-limit
                :style="{width: '100%'}"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :lg="8" :md="12" :sm="24">
            <el-form-item label="生物滤池（或滴滤塔）有效体积及停留时间" prop="keyTowerTime">
              <el-input
                placeholder="生物滤池（或滴滤塔）有效体积及停留时间"
                clearable
                v-model="voc.keyTowerTime"
                maxlength="128"
                show-word-limit
                :style="{width: '100%'}"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :lg="8" :md="12" :sm="24">
            <el-form-item label="生物污泥处置周期" prop="keyMudPeriod">
              <el-input
                placeholder="生物污泥处置周期"
                clearable
                v-model="voc.keyMudPeriod"
                maxlength="128"
                show-word-limit
                :style="{width: '100%'}"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :lg="8" :md="12" :sm="24">
            <el-form-item label="吸收液添加（更换）周期" prop="keyLiquidPeriod">
              <el-input
                placeholder="吸收液添加（更换）周期"
                clearable
                v-model="voc.keyLiquidPeriod"
                maxlength="128"
                show-word-limit
                :style="{width: '100%'}"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :lg="8" :md="12" :sm="24">
            <el-form-item label="吸收液添加（更换）用量" prop="keyLiquidAmount">
              <el-input
                placeholder="吸收液添加（更换）用量"
                clearable
                v-model="voc.keyLiquidAmount"
                maxlength="128"
                show-word-limit
                :style="{width: '100%'}"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :lg="8" :md="12" :sm="24">
            <el-form-item label="旋转周期（仅限于旋转式蓄热燃烧工艺）" prop="keyCyclePeriod">
              <el-input
                placeholder="旋转周期（仅限于旋转式蓄热燃烧工艺）"
                clearable
                v-model="voc.keyCyclePeriod"
                maxlength="128"
                show-word-limit
                :style="{width: '100%'}"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :lg="8" :md="12" :sm="24">
            <el-form-item label="设备总体占地面积" prop="keyArea">
              <el-input
                placeholder="设备总体占地面积"
                clearable
                v-model="voc.keyArea"
                maxlength="128"
                show-word-limit
                :style="{width: '100%'}"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :lg="8" :md="12" :sm="24">
            <el-form-item label="一般企业开工时间" prop="keyWorkTime">
              <el-input
                placeholder="一般企业开工时间"
                clearable
                v-model="voc.keyWorkTime"
                maxlength="128"
                show-word-limit
                :style="{width: '100%'}"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :lg="24" :md="24" :sm="24">
            <el-form-item label="废气治理工艺说明" prop="processFlowMemo">
              <el-input
                placeholder="废气治理工艺说明"
                clearable
                v-model="voc.processFlowMemo"
                maxlength="256"
                show-word-limit
                :style="{width: '100%'}"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :lg="24" :md="24" :sm="24">
            <el-form-item label="其他说明 （企业认为必须补充的说明或解释，若不需补充则填写“无”）" prop="memo">
              <el-input
                placeholder="其他说明 （企业认为必须补充的说明或解释，若不需补充则填写“无”）"
                clearable
                v-model="voc.memo"
                maxlength="256"
                show-word-limit
                :style="{width: '100%'}"
              ></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </div>
    <form-dialog
      label-width="130px"
      :visible.sync="dialogVisible"
      :width="width"
      :title="'企业基本信息'"
      :values="modify"
      :rules="rules"
      @submit="handleSubmit"
    >
      <el-row>
        <el-col :span="12">
          <el-form-item label="企业名称" prop="fullName">
            <el-input v-model="modify.fullName" placeholder="企业名称"/>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="统一社会信用代码" prop="socialCreditCode">
            <el-input v-model="modify.socialCreditCode" placeholder="统一社会信用代码"/>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="排污许可证代码" prop="emissionPermitCode">
            <el-input v-model="modify.emissionPermitCode" placeholder="请输入排污许可证代码"/>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="所属镇街" prop="townName">
            <el-select
              v-model="modify.townCode"
              placeholder="请选择镇街名称"
              clearable
              :style="{width: '100%'}"
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
        <el-col :span="12">
          <el-form-item label="企业地址" prop="address">
            <el-input v-model="modify.address" placeholder="请输入企业地址"/>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="状态变更为" prop="sourceStatus">
            <el-select v-model="modify.sourceStatus" clearable :style="{width: '100%'}">
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
        <el-col :span="12">
          <el-form-item label="经度" prop="longitude">
            <el-input
              v-model="modify.longitude"
              placeholder="请填写经度(格式:113.516)"
              clearable
              :style="{width: '100%'}"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="纬度" prop="latitude">
            <el-input
              v-model="modify.latitude"
              placeholder="请填写纬度(格式:22.650)"
              clearable
              :style="{width: '100%'}"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="行业类别" prop="industryName">
            <Treeselect
              v-model="modify.industryCode"
              :options="industryCodeOptions"
              placeholder="请选择行业名称"
              class="treeselect"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="企业规模" prop="sourceScale">
            <el-select v-model="modify.sourceScale" clearable :style="{width: '100%'}">
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
        <el-col :span="12">
          <el-form-item label="法定代表人" prop="legalPersonName">
            <el-input v-model="modify.legalPersonName" placeholder="法定代表人"/>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="法人联系电话" prop="legalPersonPhone">
            <el-input v-model="modify.legalPersonPhone" placeholder="法人联系电话"/>
          </el-form-item>
        </el-col>
        <el-col>
          <upload :refId="modifyId" :ref_type="refType" ref="uploadTem"></upload>
        </el-col>
      </el-row>
    </form-dialog>
    <checkDialog :infor="infor" :sourceStatusOptions="sourceStatusOptions" :sourceScaleOptions="sourceScaleOptions"
                 ref="checkDialog"></checkDialog>
  </div>
</template>
<script>
import FormDialog from "@/views/components/form-dialog";
import {
  getSource,
  sourceEdit,
  getDict,
  getRegion,
  getIndustry,
} from "@/api/source";
import {getSourceTags} from "@/api/menu";
import {fileList} from "@/api/upload";
import checkDialog from "./dialog/check_dialog";
import {
  modify,
  history,
  getVoc,
  getmodule1,
  getmodule2,
  changeEnProtection,
  changeVoc,
  getModifyId,
  exportVOCsWord
} from "@/api/corporateInformation";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
import Upload from "../upload";
// import {phoneRules} from './phoneRules'
export default {
  name: "corporateInformation",
  components: {
    FormDialog,
    Treeselect,
    Upload,
    checkDialog
  },
  data () {
    const phoneRules = (rule, value, callback) => {
      if (!value) {
        callback(new Error("请输入联系方式"));
      } else {
        const reg = /^1[3|4|5|6|7|8][0-9]\d{8}$/;
        if (reg.test(value)) {
          callback();
        } else {
          return callback(new Error("请输入正确的电话"));
        }
      }
    };
    return {
      dialogVisible: false,
      dialogVisible1: false,
      checkInfo: false,
      title: "数据变更",
      formData: {},
      width: "1200px",
      active_sys: null,
      systems: [],
      modify: {},
      source: {},
      voc: {},
      hasVoc: false,
      sourceStatusOptions: [],
      sourceScaleOptions: [],
      industryCodeOptions: [],
      villageCodeOptions: [],
      townCodeOptions: [],
      infor: [],
      modifyId: "",
      refType: "source_change_prove",
      fileList: [],
      rules: {
        fullName: [
          {
            required: true,
            message: "请输入企业名称",
            trigger: "blur"
          }
        ],
        envContactPhone: [
          {
            trigger: "blur",
            validator: phoneRules
          },
        ]
      },
    };
  },
  watch: {
    active_sys (v) {
      if (v && v !== "0") {
        this.timestamp = new Date().getMilliseconds();
        this.system = this.systems.find(f => f.path === v);
      }
    },
    infor: {
      handler (newVal, oldVal) {
        this.infor = newVal;
      },
      deep: true
    }
  },
  created () {
    this.getStatusList();
    this.getScaleList();
    getRegion(4419).then(
      res => (this.townCodeOptions = res.rows || res.data)
    );
    getIndustry().then(
      res => (this.industryCodeOptions = res.rows || res.data)
    );
    getmodule1().then(res => {
      if (res.data.isHasVOCs === 1) {
        this.hasVoc = true;
        getVoc().then(res => {
          if (res.code == 200) {
            this.voc = res.rows || res.data;
            if (JSON.stringify(this.voc) === "{}") {
              this.$message.msgError("没有voc数据");
            }
          }
        });
      }
      if (res.data.isHasVOCs === 0) {
        this.hasVoc = false;
      }
      this.formData = res.rows || res.data;
    });
    getmodule2().then(res => {
      this.source = res.rows || res.data;
    });
  },
  methods: {
    getStatusName (code) {//筛选企业状态
      const dict = this.sourceStatusOptions.find(
        p => p.dictValue === code
      );
      return dict ? dict.dictLabel : null;
    },
    getScaleName (code) {// 筛选企业规模
      const dict = this.sourceScaleOptions.find(
        p => p.dictValue === code
      );
      return dict ? dict.dictLabel : null;
    },
    getStatusList () {// 获取企业状态数据
      getDict("source_status").then(res => {
        this.sourceStatusOptions = res.rows || res.data;
      });
    },
    getScaleList () {// 获取企业规模数据
      getDict("source_scale").then(res => {
        this.sourceScaleOptions = res.rows || res.data;
      });
    },

    // ------------------------------------------
    changeRequest () {
      // 修改企业信息
      getModifyId().then(res => {
        this.modifyId = res.data;
        let queryData = {
          refId: this.modifyId,
          refType: this.refType
        };
        this.$refs.uploadTem.getFile(queryData);
      });

      this.modify = JSON.parse(JSON.stringify(this.formData));
      this.dialogVisible = true;
    },
    handleSubmit () {// 提交修改
      modify(this.modify).then(res => {
        if (res.code == 200) {
          this.modifyId = res.data;
          this.$message({
            showClose: true,
            message: res.msg,
            type: "success"
          });
          this.$refs.uploadTem.sendFile(this.modifyId);
          this.$nextTick(() => {
            this.dialogVisible = false;
            getmodule1().then(res => {
              this.formData = res.rows || res.data;
            });
          });
        } else {
          this.$message.info(res.msg || res.message);
        }
      });
    },
    townChange (townCode) {// 通过城镇编码获取所属街镇
      this.formData.villageCode = null;
      getRegion(townCode).then(
        res => (this.villageCodeOptions = res.rows || res.data)
      );
    },

    checkInformation () {// 查看修改企业信息记录
      history().then(res => {
        if (res.code == 200) {
          const temp = res.rows || res.data;
          if (temp.length == 0) {
            this.$message.info("暂无修改记录");
            return;
          }
          const xxx = JSON.parse(JSON.stringify(temp));
          for (const iterator of xxx) {
            async function getIterator () {
              try {
                return await fileList({refId: iterator.modifyId, refType: "source_change_prove"});
              } catch (error) {
                console.log(error);
              }
            }

            getIterator().then(res => {
              // console.log(res)
              this.$set(iterator, "file", res.data);
            });
          }
          this.infor = xxx;
        } else {
          this.$message.msgError(res.msg || res.message);
        }
        this.$refs.checkDialog.checkInfo = true;
      });
    },

    getlink (id) {
      return `${process.env.VUE_APP_BASE_API}/com/getfile/${id}`;
    },
    changeProtection () {
      // console.log(this.$refs.source)
      this.$refs.source.validate(async (valid) => {
        if (valid) {
          changeEnProtection(this.source).then(res => {
            this.$message.info(res.msg || res.message);
          });
        }
      });
    },
    changeVOC () {// 保存VOC修改
      changeVoc(this.voc).then(res => {
        if (res.code == 200) {
          this.$message.info(res.msg || res.message);
        } else {
          this.$message.info(res.msg || res.message);
        }
      });
    },
    getVOCslink (id) {
      return `${process.env.VUE_APP_BASE_API}/enterprise/modify/word?sourceId=${id}`;
    }
  }
};
</script>
<style scoped>
.wrapper {
  width: 100%;
  height: 100%;
  overflow: auto;
}

.divider_btn {
  height: 45px;
  position: relative;
}

.btn_group {
  padding-right: 100px;
}

.info {
  width: 100%;
  padding: 0 10%;
  margin: 0 auto;
  justify-content: space-between;
}

.info form {
  width: 100%;
  display: flex;
  justify-content: space-between;
}

.info form col,
.check form div {
  width: 50%;
}

.info form >>> .el-col .el-form-item__label {
  width: 120px;
}

.btn_group {
  position: absolute;
  top: -15px;
  right: 0;
  margin-right: 65px;
  padding: 0 20px;
  background: #fff;
  display: flex;
}

.form_group,
.dialog {
  width: 90%;
  margin: 0 auto;
}

.check {
  display: flex;
  flex-direction: column;
  width: 100%;
  padding: 0;
  margin-bottom: 30px;
  border: 1px solid rgb(214, 214, 214);
  box-shadow: 0 0 5px rgb(153, 153, 153);
  /* border-radius: 10px; */
}

.check form {
  width: 90%;
  margin: 0 auto;
  display: flex;
  justify-content: space-around;
}

.check .auditState {
  text-align: center;
  line-height: 64px;
  height: 64px;
  font-weight: 600;
  border-top: 2px solid rgb(214, 214, 214);
}

.check form >>> .el-form-item {
  margin-right: 0;
  margin-bottom: 8px;
  width: 100%;
}

.check form >>> .el-form-item .el-form-item__label {
  width: 120px;
}

.check form >>> .el-form-item,
.info form >>> .el-col .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
}

.pub_dialog {
  overflow: hidden;
}

.wrapper >>> .pub_dialog .el-dialog {
  min-height: 80%;
  max-height: 80%;
  height: auto;
  overflow: hidden;
}

.wrapper >>> .pub_dialog .el-dialog .el-dialog__body {
  position: absolute;
  left: 0;
  top: 54px;
  bottom: 0;
  right: 0;
  padding: 0;
  z-index: 1;
  overflow: hidden;
  overflow-y: auto;
}

.voc {
  margin-bottom: 40px;
}

.download {
  display: inline-block;
  height: 32px;
  background: rgba(119, 226, 124, .9);
  line-height: 32px;
  border-radius: 3px;
  padding: 0 15px;
  margin-left: 10px;
  color: rgba(255, 255, 255, 0.918);
  text-decoration: none;
}

.download:hover {
  background: rgba(119, 226, 124, .8);
  color: rgba(255, 255, 255, 0.918);
}

.fileList {
  display: flex;
  justify-content: space-between;
  padding: 0 30px;
  border-top: 1px solid rgb(214, 214, 214)
}

.fileList span, .fileList a {
  display: inline-block;
  height: 40px;
  line-height: 40px;
}

.fileList span {
  color: rgb(135, 172, 221);
}
</style>
<style lang="scss">
.el-form {
  .treeselect {
    .vue-treeselect__control {
      height: 34px;
      line-height: 34px;
    }
  }
}
</style>
