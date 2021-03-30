<template>
  <div>
    <el-row :gutter="15">
      <el-form :model="formData" :rules="rules" size="medium" label-width="160px" ref="TankForm">
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="废水收集池/桶" prop="storageName">
            <el-input
              v-model="formData.storageName"
              placeholder="请输入废水收集池/桶"
              :style="{width: '100%'}"
              size="mini"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="储罐类型" prop="storageType">
            <el-select
              v-model="formData.storageType"
              placeholder="请选择储罐类型"
              :style="{width: '100%'}"
              size="mini"
            >
              <el-option label="储存桶" value="cct"></el-option>
              <el-option label="自建池" value="zjc"></el-option>
              <el-option label="其他" value="qt"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="废水密度(g/cm³)" prop="density">
            <el-input
              v-model="formData.density"
              placeholder="范围为(0~9.999)"
              :style="{width: '100%'}"
              size="mini"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="数采仪" prop="collectId">
            <el-select
              v-model="formData.collectId"
              placeholder="请选择数采仪"
              :style="{width: '100%'}"
              size="mini"
              @change="collectChange"
            >
              <el-option
                v-for="item in collects"
                :key="item.collectId"
                :label="item.cpName"
                :value="item.collectId"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="容积(升)" prop="fullVolume">
            <el-input
              v-model="formData.fullVolume"
              placeholder="范围为(0~99999999.99)"
              :style="{width: '100%'}"
              size="mini"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="截面积(平方米)" prop="sectionArea">
            <el-input
              v-model="formData.sectionArea"
              placeholder="范围为(0~99999999.99)"
              :style="{width: '100%'}"
              size="mini"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="废水收集桶高度(米)" prop="maxHeight">
            <el-input
              v-model="formData.maxHeight"
              placeholder="范围为(0~99.99)"
              :style="{width: '100%'}"
              size="mini"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="水量预警线" prop="warnRate">
            <el-input
              v-model="formData.warnRate"
              placeholder="范围为(0~99.999)"
              :style="{width: '100%'}"
              size="mini"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="备注" prop="memo">
            <el-input type="textarea" v-model="formData.memo" placeholder="备注" size="mini"></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24" class="elBox-style">
          <el-card class="box-card" v-loading="loading">
            <el-form-item label="因子类型:" label-width="80px">水池液位高度(we11)</el-form-item>
            <el-form-item label="因子名称:" label-width="80px">液位高度</el-form-item>
            <el-form-item label="因子编码:" label-width="80px">we11{{ maxEl }}</el-form-item>
            <el-form-item label="数采仪:" label-width="80px">{{ collectName || "-" }}</el-form-item>
          </el-card>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24" class="elBox-style">
          <el-card class="box-card" v-loading="loading">
            <el-form-item label="因子类型:" label-width="80px">零星废水储量(we00)</el-form-item>
            <el-form-item label="因子名称:" label-width="80px">水量</el-form-item>
            <el-form-item label="因子编码:" label-width="80px">we00{{ maxEl }}</el-form-item>
            <el-form-item label="数采仪:" label-width="80px">{{ collectName || "-" }}</el-form-item>
          </el-card>
        </el-col>
      </el-form>
    </el-row>
  </div>
</template>
<script>
import {lxStorageAdd, lxStorageEdit, lxGetInfo} from "@/api/lingxing";
import {
  validateFullVolume,
  validateMaxHeight,
  validateWarnRate,
  validateDensity,
} from "@/utils/rules.js";

export default {
  props: {
    values: {
      type: Object,
      default: () => {
      },
    },
    collects: {
      type: Array,
      default: () => [],
    },
  },
  data () {
    const validatorGreater = (rule, value, callback) => {
      if (this.formData.warnRate) {
        if (Number(value) >= Number(this.formData.warnRate)) {
          callback();
        } else {
          callback(new Error("废水收集桶高度应大于水量预警线"));
        }
      } else {
        callback();
      }
    };
    const validatorLess = (rule, value, callback) => {
      if (this.formData.maxHeight) {
        if (Number(value) <= Number(this.formData.maxHeight)) {
          callback();
        } else {
          callback(new Error("水量预警线应小于废水收集桶高度"));
        }
      } else {
        callback();
      }
    };
    return {
      loading: false,
      formData: {},
      rules: {
        storageName: [
          {required: true, message: "废水收集池/桶必填", trigger: "blur"},
        ],
        storageType: [
          {required: true, message: "储罐类型必填", trigger: "blur"},
        ],
        fullVolume: [
          {required: true, message: "容积必填", trigger: "blur"},
          {
            validator: validateFullVolume,
            trigger: "blur",
          },
        ],
        sectionArea: [
          {required: true, message: "截面积必填", trigger: "blur"},
          {
            validator: validateFullVolume,
            trigger: "blur",
          },
        ],
        maxHeight: [
          {required: true, message: "废水收集桶高度必填", trigger: "blur"},
          {
            validator: validateMaxHeight,
            trigger: "blur",
          },
          {
            validator: validatorGreater,
            trigger: "blur",
          },
        ],
        warnRate: [
          {required: true, message: "水量预警线必填", trigger: "blur"},
          {
            validator: validateWarnRate,
            trigger: "blur",
          },
          {
            validator: validatorLess,
            trigger: "blur",
          },
        ],
        density: [
          {required: true, message: "废水密度必填", trigger: "blur"},
          {
            validator: validateDensity,
            trigger: "blur",
          },
        ],
        collectId: [{required: true, message: "数采仪必填", trigger: "blur"}],
      },
      eloptions: [
        {value: "we11", label: "水池液位高度"},
        {value: "we00", label: "零星废水储量"},
      ],
      entity: [
        {
          eleTypeId: "we11",
          eleDesc: "液位高度",
          eleCode: "we11",
          collectId: "",
          installLocation: "",
        },
        {
          eleTypeId: "we00",
          eleDesc: "水量",
          eleCode: "we00",
          collectId: "",
          installLocation: "",
        },
      ],
      collectName: "",
      maxEl: "",
      defaultData: "",
    };
  },
  watch: {
    values: {
      handler (v) {
        if (v) {
          this.defaultData = JSON.parse(JSON.stringify(v));
          this.formData = Object.assign({}, this.formData, v);
          if (this.formData.entity && this.formData.entity.length > 0) {
            this.$set(
              this.formData,
              "collectId",
              this.formData.entity[0].collectId
            );
            this.collectChange(this.formData.entity[0].collectId);
          }
        }
      },
      immediate: true,
    },
  },
  methods: {
    submit () {
      this.$refs["TankForm"].validate((valid) => {
        if (valid) {
          this.entity.forEach((i) => {
            i.collectId = this.formData.collectId;
            i.eleCode = i.eleCode + this.maxEl;
          });

          this.formData.entity = this.entity;
          if (this.formData.storageId) {
            lxStorageEdit(this.formData).then((res) => {
              if (res.code === 200) {
                this.msgSuccess("修改成功");
                this.$emit("completed");
              } else {
                this.msgError(res.message);
              }
            });
          } else {
            lxStorageAdd(this.formData).then((res) => {
              if (res.code === 200) {
                this.msgSuccess("添加成功");
                this.$emit("completed");
              } else {
                this.msgError(res.message);
              }
            });
          }
        }
      });
    },
    collectChange (v) {
      if (this.defaultData.collectId === v) {
        let defaultCode = this.defaultData.entity[0].eleCode.substring(4, 6);
        this.maxEl = defaultCode;
      } else {
        this.getInfoEl(v);
      }
      this.collects.forEach((i) => {
        if (i.collectId === v) {
          this.collectName = i.cpName;
        }
      });
    },
    getInfoEl (v) {
      this.loading = true;
      lxGetInfo({collectId: v, eleTypeId: "we11"}).then((res) => {
        if (res.code === 200) {
          this.maxEl = res.data || res.message || "01";
        }
        this.loading = false;
      });
    },
  },
};
</script>
<style lang="scss">
.elBox-style {
  .el-card__body {
    padding: 10px;

    .el-form-item {
      margin-bottom: 5px;
    }
  }
}
</style>
