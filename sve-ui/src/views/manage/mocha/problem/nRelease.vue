<template>
  <div>
    <el-form :model="dateForm" ref="dateForm" label-width="80px" :rules="rules">
      <el-form-item label="问卷主题" prop="questionnaireName">
        <el-input v-model="dateForm.questionnaireName" placeholder="问卷主题"/>
      </el-form-item>
      <el-form-item label="选择时间" prop="dateTime">
        <el-date-picker
          v-model="dateForm.dateTime"
          style="width:100%"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="系数" prop="coefficient">
        <el-input v-model="dateForm.coefficient" placeholder="系数"/>
      </el-form-item>
      <template v-if="dateForm.details.length>0">
        <el-divider content-position="left">问题列表</el-divider>
        <template v-for="(item,index) in nQuestion">
          <p style="font-size:12px;font-weight: bold">{{ index + 1 }}.{{ item.questionDesc }}</p>
          <el-form-item label="分值" :prop="dateForm.details[index].score" :key="index"
                        label-width="50px">
            <el-input v-model="dateForm.details[index].score" style="width:70px"
                      onKeypress="return(/[\d]/.test(String.fromCharCode(event.keyCode)))"
                      size="mini"/>
          </el-form-item>
        </template>
      </template>
    </el-form>
    <p style="margi-bottom:0;font-size:12px;color:#db1f35">注:发布评分问卷15天后关闭此问卷!</p>
  </div>
</template>

<script>
import {mochaQuestionaireAdd} from '@/api/mocha'

export default {
  props: {
    nQuestion: {
      type: Array,
      default: () => []
    }
  },
  watch: {
    nQuestion: {
      handler(v) {
        let oArr = [];
        v.forEach((o, i) => {
          let nObj = {
            questionId: o.questionId,
            score: o.fullMarks.toString(),
            serialNo: i + 1
          };
          oArr.push(nObj)
        })
        this.dateForm.details = oArr;
      },
      immediate: true
    }
  },
  data() {
    var checkCoe = (rule, value, callback) => {
      if (Number(value) <= 0 || Number(value) > 1) {
        callback(new Error('系数只能为0-1之间的小数'))
      } else {
        callback()
      }
    }
    return {
      dateForm: {
        questionnaireName: null,
        dateTime: null,
        coefficient: 0.15,
        details: []
      },
      rules: {
        questionnaireName: [
          {
            required: true,
            message: "问卷主题必填",
            trigger: "blur",
          },
        ],
        dateTime: [
          {
            required: true,
            message: "时间必选",
            trigger: "blur",
          },
        ],
        coefficient: [
          {
            required: true,
            message: "系数必填",
            trigger: "blur",
          },
          {validator: checkCoe, trigger: 'blur'}
        ]
      },
    }
  },
  methods: {
    subMit() {
      this.$refs['dateForm'].validate((valid) => {
        if (!valid) return;
        this.dateForm.startDate = this.dateForm.dateTime[0];
        this.dateForm.endDate = this.dateForm.dateTime[1];
        let oDetails = this.dateForm.details;

        let o = oDetails.every(i => {
          return !isNaN(i.score) && !(Number(i.score) <= 0) && !(Number(i.score) > 100);
        })
        if (o) {
          let oRes = 0;
          oDetails.forEach(i => {
            oRes += Number(i.score);
          })
          if (oRes > 100) {
            this.msgError('问题总分数大于100分');
            return;
          } else if (oRes < 100) {
            this.msgError('问题总分数小于100分');
            return;
          } else {
            mochaQuestionaireAdd(this.dateForm).then(res => {
             if(res.code===200){
               this.msgSuccess("问卷发布成功!");
               this.$emit('isSuccess');
             }
            })
          }
        } else {
          this.msgError('分值格式不符合要求请输入大于0且小于等于100的分值')
        }

      })
    },
  }
}
</script>

<style lang='scss' scoped>

</style>
