<template>
  <form-dialog
    :title="title"
    :values.sync="formData"
    label-width="130px"
    :visible.sync="dialogVisible"
    @submit="submitForm('TankForm')"
    :width="'850px'"
  >
    <el-row :gutter="15">
      <el-form
        :model="formData"
        :rules="rules"
        size="medium"
        label-width="160px"
        ref="TankForm"
      >
        <el-col :lg="24" :md="24" :sm="24" v-if="!handleStatus">
          <el-form-item label="监测点" prop="checkpointid">
            <el-select
              @change="monitorChange"
              v-model="formData.checkpointid"
              placeholder="请选择监测点"
              :style="{ width: '100%' }"
              size="mini"
            >
              <el-option
                v-for="item in Monitoring"
                :key="item.checkpointid"
                :label="item.checkpointname"
                :value="item.checkpointid"
              >
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :lg="24" :md="24" :sm="24" v-if="handleStatus">
          <el-form-item label="监测点" prop="sourcename">
            <el-input
              :disabled="true"
              v-model="formData.checkpointdesc"
              placeholder="监测点"
              :style="{ width: '100%' }"
              size="mini"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="设备" prop="datadevid">
            <el-select
              v-model="formData.datadevid"
              placeholder="请选择设备"
              :style="{ width: '100%' }"
              size="mini"
            >
              <el-option
                v-for="item in currentEquitment"
                :key="item.datadevid"
                :label="item.datadevdesc"
                :value="item.datadevid"
              >
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="排污单位" prop="sourcename">
            <el-input
              v-model="formData.sourcename"
              placeholder="排污单位"
              :style="{ width: '100%' }"
              size="mini"
            ></el-input>
          </el-form-item>
        </el-col>

        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="环保负责人" prop="sourceenvhead">
            <el-input
              v-model="formData.sourceenvhead"
              placeholder="环保负责人"
              :style="{ width: '100%' }"
              size="mini"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="联系方式" prop="sourceenvheadinfo">
            <el-input
              v-model="formData.sourceenvheadinfo"
              placeholder="联系方式"
              :style="{ width: '100%' }"
              size="mini"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="运营单位" prop="opcompanyname">
            <el-input
              v-model="formData.opcompanyname"
              placeholder="运营单位"
              :style="{ width: '100%' }"
              size="mini"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="负责人" prop="opcompanyhead">
            <el-input
              v-model="formData.opcompanyhead"
              placeholder="负责人"
              :style="{ width: '100%' }"
              size="mini"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="联系方式" prop="opcompanyheadinfo">
            <el-input
              v-model="formData.opcompanyheadinfo"
              placeholder="联系方式"
              :style="{ width: '100%' }"
              size="mini"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="监测位置" prop="monitorposition">
            <el-input
              v-model="formData.monitorposition"
              placeholder="监测位置"
              size="mini"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="设备名称" prop="devicedesc">
            <el-input
              v-model="formData.devicedesc"
              placeholder="设备名称"
              :style="{ width: '100%' }"
              size="mini"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="设备品牌型号编号" prop="devicenumber">
            <el-input
              v-model="formData.devicenumber"
              placeholder="设备品牌型号编号"
              :style="{ width: '100%' }"
              size="mini"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="工作原理" prop="workingprinciple">
            <el-input
              v-model="formData.workingprinciple"
              placeholder="工作原理"
              :style="{ width: '100%' }"
              size="mini"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="工作量程" prop="workrange">
            <el-input
              v-model="formData.workrange"
              placeholder="工作量程"
              :style="{ width: '100%' }"
              size="mini"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="排放标准" prop="emissionstandard">
            <el-input
              v-model="formData.emissionstandard"
              placeholder="排放标准"
              :style="{ width: '100%' }"
              size="mini"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="皮托管系数" prop="tubecoefficient">
            <el-input
              v-model="formData.tubecoefficient"
              placeholder="皮托管系数"
              :style="{ width: '100%' }"
              size="mini"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="速度场系数" prop="velocitycoefficient">
            <el-input
              v-model="formData.velocitycoefficient"
              placeholder="速度场系数"
              :style="{ width: '100%' }"
              size="mini"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="过量空气系数" prop="aircoefficient">
            <el-input
              v-model="formData.aircoefficient"
              placeholder="过量空气系数"
              :style="{ width: '100%' }"
              size="mini"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="烟囱建面积" prop="chimneysectionarea">
            <el-input
              v-model="formData.chimneysectionarea"
              placeholder="烟囱建面积"
              :style="{ width: '100%' }"
              size="mini"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="检定时间" prop="verificationtime">
            <el-date-picker
              value-format="yyyy-MM-dd"
              v-model="formData.verificationtime"
              type="date"
              placeholder="检定时间"
            >
            </el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="验收时间" prop="acceptancetime">
            <el-date-picker
              value-format="yyyy-MM-dd"
              v-model="formData.acceptancetime"
              type="date"
              placeholder="验收时间"
            >
            </el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="姓名" prop="oppersonname">
            <el-input
              v-model="formData.oppersonname"
              placeholder="姓名"
              :style="{ width: '100%' }"
              size="mini"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="联系电话" prop="oppersontel">
            <el-input
              v-model="formData.oppersontel"
              placeholder="联系电话"
              :style="{ width: '100%' }"
              size="mini"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="运维开始时间" prop="opstartdate">
            <el-date-picker
              value-format="yyyy-MM-dd"
              v-model="formData.opstartdate"
              type="date"
              placeholder="选择日期"
            >
            </el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="运维结束时间" prop="openddate">
            <el-date-picker
              value-format="yyyy-MM-dd"
              v-model="formData.openddate"
              type="date"
              placeholder="选择日期"
            >
            </el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="运营证编号" prop="oplicensenum">
            <el-input
              v-model="formData.oplicensenum"
              placeholder="运营证编号"
              :style="{ width: '100%' }"
              size="mini"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="发证单位" prop="oplicissueunit">
            <el-input
              v-model="formData.oplicissueunit"
              placeholder="发证单位"
              :style="{ width: '100%' }"
              size="mini"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="发证时间" prop="oplicissuedate">
            <el-date-picker
              value-format="yyyy-MM-dd"
              v-model="formData.oplicissuedate"
              type="date"
              placeholder="选择日期"
            >
            </el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="无证原因" prop="opnolicreason">
            <el-input
              v-model="formData.opnolicreason"
              placeholder="无证原因"
              :style="{ width: '100%' }"
              size="mini"
            ></el-input>
          </el-form-item>
        </el-col>

      </el-form>
    </el-row>
  </form-dialog>
</template>
<script>
// 验证规则
import FormDialog from '@/views/components/form-dialog'
import {
  getMonitorDropList,
  addGasEquipment,
  editGasEquipment,
  dgomDeviceList,
  getGasImportantDetail
} from '@/api/dgom'
import { getmodule2 } from '@/api/corporateInformation'

export default {
  name: 'element-form',
  components: { FormDialog },
  props: {
    title: {
      type: String
    },
    values: {
      type: Object,
      default: () => ({})
    },
    visible: {
      type: Boolean,
      default: false
    },
    equipType: {
      type: Number,
      default: 1
    }
  },
  data() {
    return {
      // 监测点下拉
      Monitoring: [
        {
          zcheckpointid: '434',
          zcheckpointdesc: '3333'
        }
      ],
      formData: {
        sourcename: '',
        datadevid: ''
      },
      // 当前设施的重点设备列表
      currentEquitment: [],

      // 表单校验
      rules: {
        checkpointid: [
          {
            required: true,
            message: '监测点不能为空',
            trigger: 'change'
          }
        ],
        sourcename: [
          {
            required: true,
            message: '排污单位不能为空',
            trigger: 'blur'
          }
        ],
        datadevid: [
          {
            required: true,
            message: '设备不能为空',
            trigger: 'change'
          }
        ]
      },
      //新增状态false还是编辑状态true
      handleStatus: false,
      // 设备列表数据
      equitmentList: []

    }
  },
  computed: {
    sys_name() {
      return this.$route.path.substr(this.$route.path.lastIndexOf('/') + 1)
    },
    dialogVisible: {
      get() {
        return this.visible || false
      },
      set(v) {
        this.$emit('update:visible', v)
      }
    }
  },
  watch: {
    values() {
      console.log('222values', this.values)
    }
  },
  methods: {
    submitForm(formName) {
      let _that = this
      this.$refs[formName].validate((valid) => {
        if (valid) {
          let { equitmentList, formData, handleStatus, equipType } = _that
          // console.log(_that.formData);
          if (!handleStatus) {
            addGasEquipment(formData).then((res) => {
              if (res.code === 200) {
                this.msgSuccess('添加成功')
                this.$emit('completed')
              } else {
                this.msgError(res.message)
              }
            })
          } else {
            editGasEquipment(formData).then((res) => {
              if (res.code === 200) {
                this.msgSuccess('编辑成功')
                this.$emit('completed')
              } else {
                this.msgError(res.message)
              }
            })
          }
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    enterpriseChange(value) {
      console.log(value)
    },
    init() {
      let { values, equipType } = this
      getMonitorDropList(equipType).then((res) => {
        this.Monitoring = res.data
      })
      // 如果是编辑的话,将编辑的数据合并到formData
      if (Object.keys(values).length !== 0 && values.checkpointid) {
        // 设置操作状态为编辑
        this.handleStatus = true
        // 获取监测点下拉
        getGasImportantDetail(values.checkpointid).then((res) => {
          let obj = res.data[0]
          console.log('22', values)
          console.log('333', obj)
          obj.datadevid = res.data[0].datadevid
          this.formData = Object.assign({}, obj, values)
          console.log('this', this.formData)
          this.monitorChange(values.checkpointid)

        })

      } else {
        getmodule2().then((res) => {
          this.formData.sourcename = res.data.fullName
        })
      }

      this.formData.checkpointtype = equipType
    },
    monitorChange(checkpointid) {
      dgomDeviceList(checkpointid).then((res) => {
        this.currentEquitment = res.data

      })
    }
  },
  mounted() {
    // console.log("equipType", this.equipType);

    // console.log("rules", this.rules);
    this.init()
  }
}
</script>
