<template>
  <div>
    <el-form ref="form" :model="form" label-width="80px" :rules="rules">
      <el-form-item label="处置企业" prop="procSourceId">
        <el-select
          v-model="form.procSourceId"
          placeholder="请选择处置企业"
          style="width:100%"
          size="mini"
        >
          <el-option
            :label="i.fullName"
            :value="i.sourceId"
            v-for="i in sourceList"
            :key="i.sourceId"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="开始时间" prop="startDate">
        <el-date-picker type="date" placeholder="开始时间" value-format="yyyy-MM-dd" v-model="form.startDate"
                        style="width: 100%;" size="mini"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="结束时间" prop="endDate">
        <el-date-picker type="date" placeholder="结束时间" value-format="yyyy-MM-dd" v-model="form.endDate"
                        style="width: 100%;" size="mini"
        ></el-date-picker>
      </el-form-item>
    </el-form>
    <n-upload :contractId="id" ref="nUpLoad"></n-upload>
  </div>
</template>
<script>
import { getHandleUnitList, addTransUnit, editTransUnit } from '@/api/lingxing'
import nUpload from './upload'

export default {
  components: { nUpload },
  props: {
    values: {
      type: Object,
      default: () => ({})
    }
  },
  data() {
    return {
      form: {},
      sourceList: [],
      id: null,
      rules: {
        procSourceId: [
          {
            required: true,
            message: '处置企业必选',
            trigger: 'blur'
          }
        ],
        startDate: [
          {
            required: true,
            message: '开始时间',
            trigger: 'blur'
          }
        ],
        endDate: [
          {
            required: true,
            message: '结束时间',
            trigger: 'blur'
          }
        ]
      }
    }
  },
  created() {
    this.getProcList()
    if (this.values && !this.values.contractId) {
      this.guid()
    } else {
      this.id = this.values.contractId
    }
  },
  watch: {
    values: {
      handler(v) {
        console.log(v)
        this.form = Object.assign({}, v)
      },
      immediate: true,
      deep: true
    }
  },
  methods: {
    getProcList() {
      getHandleUnitList().then(res => {
        if (res.code === 200) {
          this.sourceList = res.data
        }
      })
    },
    submitForm() {
      if (this.$refs.nUpLoad.isFile()) {
        this.$refs['form'].validate((valid) => {
          if (!valid) return
          if (this.values.contractId) {
            let oV = Object.assign({}, this.values, this.form)
            console.log(oV)
            editTransUnit(oV).then(res => {
              if (res.code === 200) {
                this.msgSuccess('修改成功')
                this.$emit('completed')
              } else {
                this.msgError(res.message)
              }
            })
          } else {
            let oV = JSON.parse(JSON.stringify(this.form))
            oV.contractId = this.id
            addTransUnit(oV).then(res => {
              if (res.code === 200) {
                this.msgSuccess('添加成功')
                this.$emit('completed')
              } else {
                this.msgError(res.message)
              }
            })
          }
        })
      } else {
        this.msgError('附件必选上传!')
      }
    },
    guid() {
      let s = []
      let hexDigits = '0123456789abcdef'
      for (var i = 0; i < 36; i++) {
        s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1)
      }
      s[14] = '4' // bits 12-15 of the time_hi_and_version field to 0010
      s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1) // bits 6-7 of the clock_seq_hi_and_reserved to 01
      s[8] = s[13] = s[18] = s[23] = '-'
      this.id = s.join('')
    }
  }
}
</script>
