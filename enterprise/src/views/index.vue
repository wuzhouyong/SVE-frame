<template>
  <div class="home" v-loading="loading">
    <el-divider content-position="left">监控任务进度</el-divider>
    <div class="task_container">
      <div class="task" v-for="sys in systems" :key="sys.path">
        <el-row>
          <el-col :span="3">
            <div class="title">{{ sys.name }}:</div>
          </el-col>
          <el-col :span="21">
            <div class="step">
              <el-steps
                :active="sys.status"
                finish-status="success"
                align-left
                simple
              >
                <el-step
                  v-for="(item, index) in stepsData"
                  :key="index"
                  :title="item"
                ></el-step>
              </el-steps>
            </div>
          </el-col>
        </el-row>
      </div>
    </div>
    <div class="tagData">
      <el-radio-group v-model="activeName" size="mini">
        <el-radio-button
          :label="sys.name"
          :name="sys.name"
          v-for="sys in systems"
          :key="sys.path"
        ></el-radio-button>
      </el-radio-group>
      <div v-for="sys in systems" :key="sys.path">
        <div class="charts" v-if="sys.name === activeName">
          <el-divider content-position="right"
          >{{ sys.name }} - 待处理异常数量：{{ sys.warn_count }}
          </el-divider
          >
          <ve-line
            :extend="extend"
            :data="sys.chart.data"
            :settings="sys.chart.settings"
            height="400px"
          ></ve-line>
        </div>
      </div>
    </div>

    <el-dialog
      title="公告"
      :visible.sync="centerDialogVisible"
      width="30%"
      :show-close="false"
      center
    >
      <div class="wrap">
        <p class="notice">
          根据东莞市生态环境局关于加强企业用户帐号管理的相关要求，为不影响您的功能使用，请东莞市污染源在线监控平台企业尽快完成手机号绑定，如在绑定过程中遇到任何问题，请联系官方微信客服。绑定手机后可通过手机号登陆、手机号短信验证码修改密码和找回密码等与帐户安全性相关的操作。
        </p>
      </div>

      <span slot="footer" class="dialog-footer">
        <el-button :disabled="disabled" type="primary" @click="confirm">{{
            btntxt
          }}</el-button>
      </span>
    </el-dialog>


  </div>
</template>
<script>
import { progressModel } from '@/api/construction'
import { getSourceTags } from '@/api/menu'
import { transfer_config } from '@/config'

import VeLine from 'v-charts/lib/line.common'
import Cookies from 'js-cookie'

export default {
  components: { VeLine },
  data() {
    return {
      activeName: '',
      systems: [],
      transferStatus: { 1: 1, 2: 2, 3: 2, 4: 3, 6: 4 },
      stepsData: ['施工中', '已接入', '待验收', '已验收'],
      extend: {
        grid: {
          bottom: '10px'
        }
      },
      loading: true,
      centerDialogVisible: false,
      time: 5,
      btntxt: '确定',
      disabled: Cookies.get('notice'),

    }
  },
  computed: {
    userMessage() {
      return this.$store.getters
    }
  },
  created() {
    this.getSystems()
    this.timer()
    this.centerDialogVisible = this.userMessage.isBinding != 1 && !this.userMessage.clickStatus
  },
  mounted() {
  },
  methods: {
    async getSystems() {
      try {
        const tags = await getSourceTags()
        this.systems = await Promise.all(
          Object.keys(transfer_config)
            .filter((f) => tags.data.some((s) => s === f))
            .map(async(m) => {
              let sys = Object.assign({}, transfer_config[m])
              sys.status = await this.getConstruction(sys.path)
              sys.warn_count = (await sys.procstate()).data
              sys
                .chart()
                .then((res) => {
                  let chart = res.data
                  const columns = [
                    'date',
                    ...chart.cols.map((c) => c.dataIndex)
                  ]
                  const labelMap = chart.cols.reduce((obj, cur) => {
                    obj[cur.dataIndex] = cur.title
                    return obj
                  }, {})
                  sys.chart = {
                    data: { rows: chart.data, columns },
                    settings: { labelMap }
                  }
                })
                .catch((error) => {
                  console.log(error)
                })
              return sys
            })
        )
        this.path = this.systems[0].path
        this.activeName = this.systems[0].name
        this.loading = false
      } catch (error) {
        this.loading = false
      }
    },
    async getConstruction(sys) {
      try {
        const model = await progressModel(sys)
        return this.transferStatus[model.data.constructionStatus]
      } catch (error) {
      }
      return null
    },
    timer() {
      if (this.time > 0) {
        this.disabled = true
        this.time--
        this.btntxt = '我已阅读(' + this.time + ')'
        setTimeout(this.timer, 1000)
      } else {
        this.time = 0
        this.btntxt = '确定'
        this.disabled = false
      }
    },
    confirm() {

      this.$store.dispatch('SetClcikStauts')
      this.centerDialogVisible = false

    }
  }
}
</script>
<style lang="scss" scope>
.home {
  width: 100%;
  height: calc(100% - 40px);
  padding: 20px;
  overflow-y: auto;

  .T_progress {
    font-size: 18px;
    font-weight: bold;
    margin-top: 30px;
  }

  .el-divider {
    margin-top: 5px;

    .el-divider__text {
      font-size: 18px;
      font-weight: bold;
    }
  }

  .task_container {
    margin-top: 20px;
    display: flex;
    flex-wrap: wrap;

    .task {
      width: 100%;

      .el-row {
        margin: 10px 0;

        .el-col {
          .title {
            padding: 0;
            height: 30px;
            line-height: 30px;
            text-align: right;
            margin: 0;
            font-size: 14px;
            padding-right: 20px;
            font-weight: bold;
          }

          .step {
            border: 1px solid #e4e4e4;

            .el-step__title {
              font-size: 14px;
            }
          }
        }
      }
    }
  }

  .tagData {
    margin: 50px 0 0 0;

    .charts {
      width: 100%;
      padding-top: 10px;
      margin-top: 50px;

      .el-divider {
        margin-top: -61px;
        margin-bottom: 30px;
      }

      .ve-line {
        background-color: #f5f5f5;
      }
    }

    .el-divider {
      margin: -1px 0 0 0;
    }
  }
}

.el-steps--simple {
  height: 30px;
  padding: 0;
  background: #e4e4e4;

  .is-simple {
    padding-left: 10px;

    .el-step__main {
      width: 164.9px;
    }
  }
}

p {
  line-height: 30px;
  text-align: center;
  // width: 300px;
}
</style>
