<template>
  <div class="score-form">
    <el-input v-model.number="score" oninput="value=value.replace(/[^\d]/g,'')" size="mini" @blur="onBlur"></el-input>
    <p class="errorMes" v-if="isError">{{ errorMes }}</p>
  </div>

</template>

<script>
export default {
  props: ['fullMarks', 'questions'],
  model: {
    prop: 'value',
    event: 'change'
  },
  data() {
    return {
      score: '',
      isError: false,
      errorMes: ''
    }
  },
  methods: {
    onBlur() {
      if (this.score !== '') {
        if (Number(this.score) > this.fullMarks) {
          this.isError = true
          this.errorMes = '分数不能大于该题的满分!'
        } else {
          this.isError = false
          this.errorMes = ''
        }
      } else {
        this.isError = true
        this.errorMes = '分数为必填项!'
      }
      let oVal = {
        questionId: this.questions.questionId,
        score: this.score,
        isError: this.isError
      }
      this.$emit('change', oVal)
    }
  }
}
</script>

<style lang="scss" scoped>
.score-form {
  p.errorMes {
    padding: 0;
    margin: 0;
    color: red;
    font-size: 12px;
    line-height: 20px;
    text-align: left;
  }
}
</style>
