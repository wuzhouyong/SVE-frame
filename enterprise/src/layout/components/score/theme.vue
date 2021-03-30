<template>
  <div>
    <div v-for="(i,index) in newQuestion" :key="i.questionnaireId" class="question-wk">
      <h3>
        问卷{{ index + 1 }}.{{ i.questionnaireName }}
      </h3>
      <div style="padding-left:10px;">
        <div v-for="j in i.list" :key="i.questionnaireId+j.opSourceId" class="question-source">
          <i class="el-icon-star-off" v-if="j.isFinish===0" style="color:orangered"></i>
          <i class="el-icon-star-on" v-else style="color:green"></i>
          <p v-if="j.isFinish===0" class="noFinish" @click="openScore(i.questionnaireId,j)">{{ j.fullName }}</p>
          <p v-else>{{ j.fullName }}</p>
        </div>
      </div>
    </div>

    <p class="question-bz">
      注:<i class="el-icon-star-off" style="color:orangered"></i>为未完成问卷,
      <i class="el-icon-star-on" style="color:green"></i>为已完成问卷,请在结束时间内完成评分。
    </p>
    <el-dialog
      title="评分问卷"
      :visible.sync="scoreVisible"
      :close-on-click-modal="false"
      custom-class="question-dialog"
      append-to-body
      width="600px"
    >
      <n-source v-if="scoreVisible" :sData="sData" :qId="qId" ref="nsource" @isSendSuccess="isSendSuccess"></n-source>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" size="mini" @click="subScore">提 交</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import nSource from './nScore'

export default {
  components: { nSource },
  props: {
    themeData: {
      type: Array,
      default: () => []
    }
  },
  data() {
    return {
      newQuestion: [],
      scoreVisible: false,
      sData: {},
      qId: null
    }
  },
  watch: {
    themeData: {
      handler(v) {
        let oArr = []
        v.forEach(i => {
          let oD = oArr.find(o => o.questionnaireId === i.questionnaireId)
          if (!oD) {
            oD = {
              questionnaireName: i.questionnaireName,
              questionnaireId: i.questionnaireId,
              list: [
                {
                  fullName: i.fullName,
                  opSourceId: i.opSourceId,
                  isFinish: i.isFinish
                }
              ]
            }
            oArr.push(oD)
          } else {
            oD.list.push({
              fullName: i.fullName,
              opSourceId: i.opSourceId,
              isFinish: i.isFinish
            })
          }
        })
        oArr.sort((a, b) => a['questionnaireId'] < b['questionnaireId'] ? 1 : -1)
        this.newQuestion = oArr
      },
      immediate: true
    }
  },
  methods: {
    openScore(i, v) {
      this.qId = i
      this.sData = v
      this.scoreVisible = true
    },
    subScore() {
      this.$refs.nsource.sub()
    },
    isSendSuccess() {
      this.scoreVisible = false
      this.$emit('getNewList')
    }
  }
}
</script>

<style lang="scss" scoped>
.question-wk {
  h3 {
    margin: 0 0 5px;
    font-size: 14px;
    line-height: 28px;
  }
}

p.question-bz {
  font-size: 12px;
}

.question-source {
  width: 100%;
  height: 35px;
  line-height: 35px;
  display: flex;
  align-items: center;
  overflow: hidden;

  i {
    padding-right: 5px;
  }

  p {
    padding: 0;
    margin: 0;
    color: #666;
  }

  p.noFinish {
    color: rgba(64, 158, 255, 0.9);

    &:hover {
      color: rgba(64, 158, 255, 1);
      cursor: pointer;
    }
  }


}
</style>
