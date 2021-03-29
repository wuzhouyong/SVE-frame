<template>
  <div class="index-right">
    <char-box class="task-table" :title="'任务类型统计'" :height="335">
      <div class="task-table-head">
        <el-row class="task-head-row">
          <el-col :span="12"><span style="font-size: 12px">任务总数</span> <span
            style="color: #F8AD60;">223</span></el-col>
          <el-col :span="12"><span style="font-size: 12px">完成率</span> <span style="color: #1FEDD8;">85%</span>
          </el-col>
        </el-row>
      </div>
      <div class="table-box">
        <table>
          <tr>
            <td style="width: 20px;"></td>
            <td style="text-align: left;color: #5E88EB;font-weight: bold;">任务来源</td>
            <td style="width: 60px;color: #5E88EB;font-weight: bold;">未处理</td>
            <td style="width: 80px;color: #5E88EB;font-weight: bold;">超时未处理</td>
          </tr>
          <tr v-for="(item,index) of taskTableData" :key="index">
            <td style="width: 20px;color: #5E88EB;">{{ index + 1 }}</td>
            <td style="text-align: left">{{ item.type }}</td>
            <td style="width: 60px;">{{ item.no }}</td>
            <td style="width: 80px; color: #F8BB38;">{{ item.out }}</td>
          </tr>
        </table>
      </div>
    </char-box>
    <char-box class="task-town-box" :title="'镇街任务完成率'" :height="900">
      <ve-bar class="task-town-bar" height="800px" :data="townBarData.chartData" :legend="{show: false}"
              :theme-name="'dark'"
              :settings="townBarData.chartSettings" :extend="townBarData.extend"></ve-bar>
    </char-box>
  </div>
</template>

<script>
import CharBox from './charBox'
import rand from '../../../utils/randomData'
import VeBar from 'v-charts/lib/bar.common'

export default {
  name: 'indexRight',
  components: {CharBox, VeBar},
  data() {
    return {
      taskTableData: [],
      townBarData: {
        chartData: {
          columns: ['name', 'finish', 'out'],
          rows: [
            {name: '1/1', finish: 1093, out: 0.32}
          ]
        },
        chartSettings: {
          label: {
            normal: {
              show: true,
              textBorderColor: '#ED452E',
              textBorderWidth: 1,
              position: 'right',
              formatter: (res => {
                //console.log(res)
                if (res.seriesIndex === 1) {
                  let now = this.townBarData.chartData.rows[res.dataIndex]
                  return (now.out / (now.out + now.finish) * 100).toFixed(1) + '%'
                } else {
                  return ''
                }
              })
            }
          },
          stack: {
            'xxx': ['out', 'finish']
          },
          labelMap: {
            finish: '已完成',
            out: '逾期'
          }
        },
        extend: {
          backgroundColor: 'rgba(1,1,1,0)',
          grid: {
            left: '0%',
            right: '8%',
            bottom: '0%',
            containLabel: true
          }
        }
      }
    }
  },
  mounted() {
    this.taskTableData = rand.taskSumData()
    this.townBarData.chartData.rows = rand.townTaskData()
  },
  methods: {
    // getTownPre(index) {
    //   let now = this.townBarData.rows[value.dataIndex]
    //   //console.log(now)
    // }
  }
}
</script>

<style lang="scss" scoped>
.index-right {
  padding: 0 15px 0 15px;

  .task-table {
    margin-top: 10px;

    .task-table-head {
      width: 90%;
      background-color: #0288D1;
      height: 35px;
      margin-left: 5%;
      border-radius: 5px;
      margin-bottom: 10px;

      .task-head-row {
        div {
          font-size: 18px;
          text-align: center;

          span {
            line-height: 35px;
          }
        }
      }
    }

    .table-box {
      height: 230px;
      overflow: hidden;
      position: relative;

      table {
        color: #80DEEA;
        position: absolute;
        /*animation: topMove 18s linear infinite;*/
        /*-webkit-animation: topMove 18s linear infinite;*/

        width: 100%;
        -moz-user-select: none;
        -ms-user-select: none;
        -webkit-user-select: none;
        -khtml-user-select: none;
        table-layout: fixed;
        line-height: 1.1;

        tr {
          font-size: 13px;

          td {
            //padding-left: 5px;
            padding-bottom: 10px;
            text-align: center;
          }
        }
      }
    }
  }

  .task-town-box {
    margin-top: 20px;

    .task-town-bar {
      margin-top: -50px;
    }

    .task-town-pre {
      height: 740px;
      background-color: red;
      position: absolute;
      width: 100px;
      right: 0px;
      top: 30px;
      font-size: 14px;

      span {
        display: block;
        line-height: 20px;
      }

    }
  }

}

@keyframes topMove {
  0% {
    top: 0%;
  }
  5% {
    top: 0%;
  }
  45% {
    top: -100%;
  }
  50% {
    top: -100%;
  }
  100% {
    top: 0%;
  }
}

@-webkit-keyframes topMove {
  0% {
    top: 0%;
  }
  5% {
    top: 0%;
  }
  45% {
    top: -100%;
  }
  50% {
    top: -100%;
  }
  100% {
    top: 0%;
  }
}
</style>
