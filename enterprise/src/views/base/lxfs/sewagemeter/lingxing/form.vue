<template>
  <div>
    <el-row :gutter="15">
      <el-form :model="formData" :rules="rules" size="medium" label-width="140px" ref="waterForm">
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="水表名称" prop="meterName" :disabled="isEdit">
            <el-input
              v-model="formData.meterName"
              placeholder="水表名称"
              :style="{width: '100%'}"
              size="mini"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="24" :sm="24">
          <el-form-item label="数据来源" prop="isOther">
            <el-radio-group v-model="formData.isOther" size="mini" @change="isOtherChange" :disabled="isEdit">
              <el-radio-button :label="false">新增因子</el-radio-button>
              <el-radio-button :label="true">四大流域</el-radio-button>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="24" :sm="24" v-if="isOther">
          <el-form-item label="数采关联" prop="srcCollectId">
            <el-select
              v-model="formData.srcCollectId"
              placeholder="数采关联"
              size="mini"
              style="width: 100%"
              @change="cIdChange"
              :disabled="isEdit"
            >
              <el-option
                :label="i.text"
                :value="i.value"
                v-for="i in miningArr"
                :key="i.value"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="数采仪" prop="collectId">
            <el-select
              v-model="formData.collectId"
              placeholder="请选择数采仪"
              :style="{width: '100%'}"
              size="mini"
              @change="collectChange"
              :disabled="isEdit"
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
        <el-col :lg="24" :md="24" :sm="24" v-if="!isEdit">
          <el-form-item label="因子编码(后两位)" prop="elInput">
            <el-input v-model="formData.elInput" placeholder="因子编码(后两位)" size="mini" maxlength="2"
                      show-word-limit @blur="elInputBlur"></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="备注" prop="memo">
            <el-input type="textarea" v-model="formData.memo" placeholder="备注"></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24" class="elBox-style">
          <el-card class="box-card">
            <el-form-item label="因子类型:" label-width="100px">工业污水排放流量(w900)</el-form-item>
            <el-form-item label="因子名称:" label-width="100px" :prop="'elements.'+0+'.elementDesc'"
                          :rules="{ required: true, message: '必填', trigger: 'blur' }">
              <el-input v-model="formData.elements[0].elementDesc" placeholder="因子名称" size="mini"></el-input>
            </el-form-item>
            <el-form-item label="因子关联:" label-width="100px" v-if="isOther" :prop="'elements.'+0+'.srcElemId'"
                          :rules="{ required: true, message: '必填', trigger: 'blur' }">
              <el-select
                v-model="formData.elements[0].srcElemId"
                placeholder="因子关联"
                style="width: 100%"
                size="mini"
                :disabled="isEdit"
              >
                <el-option
                  :label="i.text+'('+i.type+')'"
                  :value="i.value"
                  v-for="i in factorArr1"
                  :key="i.value"
                ></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="因子编码:" label-width="100px">{{ formData.elements[0].elementCode }}</el-form-item>
            <el-form-item label="数采仪:" label-width="100px">{{ collectName || "-" }}</el-form-item>
          </el-card>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24" class="elBox-style">
          <el-card class="box-card">
            <el-form-item label="因子类型:" label-width="100px">工业污水排放瞬时流量(w901)</el-form-item>
            <el-form-item label="因子名称:" label-width="100px" :prop="'elements.'+1+'.elementDesc'"
                          :rules="{ required: true, message: '必填', trigger: 'blur' }">
              <el-input v-model="formData.elements[1].elementDesc" placeholder="因子名称" size="mini"></el-input>
            </el-form-item>
            <el-form-item label="因子关联:" :prop="'elements.'+1+'.srcElemId'" label-width="100px" v-if="isOther"
                          :rules="{ required: true, message: '必填', trigger: 'blur' }">
              <el-select
                v-model="formData.elements[1].srcElemId"
                placeholder="因子关联"
                style="width: 100%"
                size="mini"
                :disabled="isEdit"
              >
                <el-option
                  :label="i.text+'('+i.type+')'"
                  :value="i.value"
                  v-for="i in factorArr2"
                  :key="i.value"
                ></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="因子编码:" label-width="100px">{{ formData.elements[1].elementCode }}</el-form-item>
            <el-form-item label="数采仪:" label-width="100px">{{ collectName || "-" }}</el-form-item>
          </el-card>
        </el-col>
      </el-form>
    </el-row>
  </div>
</template>
<script>
import {lxGetInfo, lxMeterinfoAdd, lxMeterinfoEdit} from "@/api/lingxing";
import {Minninglist} from "@/api/collect";
import {factorList} from "@/api/element";

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
    return {
      formData: {
        isOther: false,
        elInput: null,
        elements: [
          {
            eleTypeId: "w900",
            elementDesc: "流量",
            elementCode: "w900",
            collectId: "",
            srcElemId: "",
            srcCollectId: "",
          },
          {
            eleTypeId: "w901",
            elementDesc: "瞬时流量",
            elementCode: "w901",
            collectId: "",
            srcElemId: "",
            srcCollectId: "",
          },
        ],
      },
      isOther: false,
      miningArr: [],
      factorArr1: [],
      factorArr2: [],
      defaultData: {},
      rules: {
        meterName: [{required: true, message: "水表必填", trigger: "blur"}],
        collectId: [{required: true, message: "数采仪必填", trigger: "blur"}],
        isOther: [{required: true, message: "数据来源必填", trigger: "blur"}],
        srcCollectId: [{required: true, message: "关联数采必选", trigger: "blur"}],
        elInput: [{required: true, message: "因子编码必填", trigger: "blur"}],
      },
      elements: [
        {
          eleTypeId: "w900",
          elementDesc: "流量",
          elementCode: "w900",
          collectId: "",
          srcElemId: "",
          installLocation: "",
        },
        {
          eleTypeId: "w901",
          elementDesc: "瞬时流量",
          elementCode: "w901",
          collectId: "",
          srcElemId: "",
          installLocation: "",
        },
      ],
      collectName: "",
      maxEl: "",
      isEdit: false
    };
  },
  watch: {
    values: {
      handler (v) {
        if (v.meterId) {
          this.isEdit = true;
          this.isOther = v.elements[0].isOther;
          this.$set(this.formData, "isOther", this.isOther);
          if (this.isOther) {
            this.$set(this.formData, "srcCollectId", v.elements[0].srcCollectId);
            v.elements.forEach(j => {
              j.srcElemId = j.srcElemId.toString();
            });
            factorList("guocheng", {collectId: v.elements[0].srcCollectId}).then((res) => {
              if (res.code === 200) {
                let oRes = res.data;
                oRes.forEach(i => {
                  if (i.type === "ea05") {
                    this.factorArr1.push(i);
                  } else if (i.type === "ea06") {
                    this.factorArr2.push(i);
                  }
                });
              }
            });
          }
          this.$set(this.formData, "collectId", v.elements[0].collectId);
          this.collectName = v.elements[0].collectName;
          Minninglist("guocheng", {}).then((res) => {
            this.miningArr = res.data;
          });
          this.formData = Object.assign({}, this.formData, v);
        } else {
          this.isEdit = false;
        }
      },
      immediate: true,
    },
  },
  methods: {
    submit () {
      this.$refs["waterForm"].validate((valid) => {
        if (!valid) return;
        this.formData.meterType = 1;
        if (this.formData.meterId) {
          lxMeterinfoEdit(this.formData).then((res) => {
            if (res.code === 200) {
              this.msgSuccess("编辑成功");
              this.$emit("completed");
            } else {
              this.msgError(res.message);
            }
          });
        } else {
          lxMeterinfoAdd(this.formData).then((res) => {
            if (res.code === 200) {
              this.msgSuccess("添加成功");
              this.$emit("completed");
            } else {
              this.msgError(res.message);
            }
          });
        }
      });
    },
    collectChange (v) {
      this.formData.elements.forEach(i => {
        i.collectId = v;
      });
      this.collects.forEach((i) => {
        if (i.collectId === v) {
          this.collectName = i.cpName;
        }
      });
    },
    isOtherChange (v) {
      if (v) {
        this.isOther = v;
        Minninglist("guocheng", {}).then((res) => {
          if (res.code === 200) {
            this.miningArr = res.rows || res.data;
          }
        });
      } else {
        this.isOther = v;
      }
    },
    cIdChange (v) {
      this.factorArr1 = [];
      this.factorArr2 = [];
      this.formData.elements.forEach(i => {
        i.srcElemId = null;
        i.srcCollectId = v;
      });
      factorList("guocheng", {collectId: v}).then((res) => {
        if (res.code === 200) {
          let oRes = res.data;

          oRes.forEach(i => {
            if (i.type === "ea05") {
              this.factorArr1.push(i);
            } else if (i.type === "ea06") {
              this.factorArr2.push(i);
            }
          });
          // this.factorArr = res.data;
        }
      });
    },
    elInputBlur (e) {
      this.formData.elements.forEach(i => {
        if (i.elementCode.length > 4) {
          let prefixCode = i.elementCode.substring(0, 4);
          i.elementCode = prefixCode + e.target.value;
        } else {
          i.elementCode = i.elementCode + e.target.value;
        }
      });
    }
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
