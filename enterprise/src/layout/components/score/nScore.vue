<template>
  <div class="n-score">
    <p style="font-size:16px;margin:0;text-align: left;font-weight: bold">运维单位：{{ sData.fullName }}</p>
    <el-form ref="form" :model="form">
      <el-form-item :label="`${i.questionDesc}(满分:${i.score})`" v-for="(i,index) in questionList">
        <n-form v-model="form.details[index]" :fullMarks="i.score" :questions="i"></n-form>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { mochaQuestionnaireList, mochaScoreAdd } from '@/api/mocha'
import nForm from './nForm'

export default {
  components: {
    nForm
  },
  props: {
    sData: {
      type: Object,
      default: () => ({})
    },
    qId: {
      type: Number | String
    }
  },
  data() {
    return {
      form: {
        details: []
      },
      questionList: []
    }
  },
  watch: {
    qId: {
      handler(v) {
        this.getList(v)
      },
      immediate: true
    }
  },
  methods: {
    getList(v) {
      mochaQuestionnaireList(v).then(res => {
        this.questionList = res.data
        let ov = {
          questionId: '',
          score: '',
          isError: false
        }
        for (let i = 0; i < this.questionList.length; i++) {
          this.form.details.push(ov)
        }
      })
    },
    sub() {
      let o = this.form.details.every(i => i.isError === false)
      if (o) {
        let oRes = 0
        this.form.details.forEach(i => {
          oRes += Number(i.score)
        })
        let oNewObj = {
          opSourceId: this.sData.opSourceId,
          questionnaireId: this.qId,
          totalScore: oRes,
          details: this.form.details
        }
        mochaScoreAdd(oNewObj).then(res => {
          if(res.code===200){
            this.msgSuccess("问卷提交成功!");
            this.$emit('isSendSuccess')
          }
        })
      }
    }
  }
}
</script>

<style lang="scss" scoped>
.n-score {
  /deep/ .el-form-item {
    margin-bottom: 5px;
  }
}
</style>
