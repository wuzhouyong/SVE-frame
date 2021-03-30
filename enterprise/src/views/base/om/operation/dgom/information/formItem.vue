<template>
  <el-form
    :model="ruleForm"
    :rules="rules"
    :ref="'formItem' + propsKey"
    label-width="100px"
    class="demo-ruleForm"
  >
    <div class="equipment_list">
      <el-divider content-position="left" class="divider">{{
          itemVal.datadevdesc
        }}
      </el-divider>
      <el-row>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="设备品牌" prop="devicebrand">
            <el-input
              v-model="ruleForm.devicebrand"
              placeholder="设备品牌"
              :style="{ width: '100%' }"
              size="mini"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="设备编号" prop="devicenumber">
            <el-input
              v-model="ruleForm.devicenumber"
              placeholder="设备出厂编号"
              :style="{ width: '100%' }"
              size="mini"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="测量方法" prop="measuringmethod">
            <el-input
              v-model="ruleForm.measuringmethod"
              placeholder="测量方法"
              :style="{ width: '100%' }"
              size="mini"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="加热消解温度" prop="heatingtemp">
            <el-input
              v-model="ruleForm.heatingtemp"
              placeholder="加热消解温度"
              :style="{ width: '100%' }"
              size="mini"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="加热消解时间" prop="heatingtime">
            <el-input
              v-model="ruleForm.heatingtime"
              placeholder="加热消解时间"
              :style="{ width: '100%' }"
              size="mini"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="工作量程" prop="workrange">
            <el-input
              v-model="ruleForm.workrange"
              placeholder="工作量程"
              :style="{ width: '100%' }"
              size="mini"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="相关系数" prop="refcoefficient">
            <el-input
              v-model="ruleForm.refcoefficient"
              placeholder="相关系数"
              :style="{ width: '100%' }"
              size="mini"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="检定时间" prop="verificationtime">
            <el-date-picker
              value-format="yyyy-MM-dd"
              v-model="ruleForm.verificationtime"
              type="date"
              placeholder="选择日期"
              style="width:100%"
              size="mini"
            >
            </el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="验收时间" prop="acceptancetime">
            <el-date-picker
              value-format="yyyy-MM-dd"
              v-model="ruleForm.acceptancetime"
              type="date"
              placeholder="选择日期"
              size="mini"
              style="width:100%"
            >
            </el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="排放标准" prop="emissionstandard">
            <el-input
              v-model="ruleForm.emissionstandard"
              placeholder="排放标准"
              :style="{ width: '100%' }"
              size="mini"
            ></el-input>
          </el-form-item>
        </el-col>
      </el-row>
    </div>
  </el-form>
</template>
<script>
// import FactorType from "@/api/voc/discharge";

export default {
  props: {
    propsKey: {
      type: Number,
      default: true
    },
    formVal: {
      type: Object,
      default: () => ({})
    },
    itemVal: {
      default: () => ({}),
      type: Object
    }
    //       type: Boolean,
    //       default: true,
    //     isAdd: {
    //     },
    //     value: {
    //       type: Array,
    //     },
    //     eloptions: {
    //       type: Array,
    //       default: () => {
    //         [];
    //       },
    //     },
    //     collects: {
    //       default: () => {
    //         [];
    //       },
    //     },
  },
  data() {
    return {
      eloption: [],
      newData: [],
      obj: {},
      radio: '',

      ruleForm: {
        devicebrand: '',
        devicenumber: '',
        measuringmethod: '',
        heatingtemp: '',
        heatingtime: '',
        workrange: '',
        refcoefficient: '',
        verificationtime: '',
        acceptancetime: '',
        emissionstandard: ''
      },
      rules: {
        devicebrand: [
          { required: true, message: '请填写设备品牌', trigger: 'blur' }
        ],

        devicenumber: [
          { required: true, message: '请填写设备出厂编号', trigger: 'blur' }
        ],
        measuringmethod: [
          { required: true, message: '请填写测量方法', trigger: 'blur' }
        ]
      }
    }
  },
  //   watch: {
  //     eloptions: {
  //       handler(v) {
  //         this.eloption = v;
  //         this.newData = JSON.parse(JSON.stringify(this.eloption));
  //         this.obj.eleTypeId = this.newData[0].value;
  //       },
  //       immediate: true,
  //       deep: true,
  //     },
  //   },
  methods: {},
  mounted() {
    let propsKey = this.propsKey
    if (this.formVal) {
      this.ruleForm = this.formVal
    } else {
      this.ruleForm.datadevid = this.itemVal.datadevid
      this.ruleForm = Object.assign({}, this.itemVal)
    }

  },
  created() {
    //   let prosName = this.propsKey + 'ruleForm';
    //   this[prosName] = {}
  }
}
</script>
