<template>
  <div class="index-center">
    <div class="map-chart">
      <ve-map width="950px" height="660px" :data="mapChart.chartData"
              :settings="mapChart.chartSettings"
              :extend="mapChart.chartExtend" :legend-visible="false" :events="mapChart.mapEvent"/>
    </div>
    <div class="task-table" :title="'任务类型统计'" :height="600">
      <div class="task-table-head">
        <el-row class="task-head-row">
          <el-col :span="12"><span style="font-size: 12px">在线监控任务总数</span> <span style="color: #F8AD60;">223</span>
          </el-col>
          <el-col :span="12"><span style="font-size: 12px">总完成率</span> <span style="color: #07BFF8;">85%</span></el-col>
        </el-row>
      </div>
      <div class="table-box">
        <table>
          <tr style="color: white;">
            <td>任务小类</td>
            <td style="width: 120px;">任务总数</td>
            <td style="width: 120px;">已处理数</td>
            <td style="width: 120px;">未处理数</td>
            <td style="width: 120px;">逾期没处理</td>
            <td style="width: 120px;">完成率</td>
          </tr>
          <tr v-for="(item,index) of taskTableData" :key="index">
            <td>{{item.type}}</td>
            <td style="">{{item.all}}</td>
            <td style="">{{item.pro}}</td>
            <td style="">{{item.no}}</td>
            <td style=" color: #F8BB38;">{{item.out}}</td>
            <td style=" color: #00BCD4;">{{(item.pro/item.all).toFixed(2)+'%'}}</td>
          </tr>
        </table>
      </div>
    </div>
  </div>
</template>

<script>
  import VeMap from 'v-charts/lib/map.common'
  import mapJson from '@/utils/dongguan.json'
  import 'echarts/lib/component/visualMap'
  import rand from '../../../utils/randomData'
  import Clock from './clock'
  // import 'echarts-gl'

  export default {
    name: 'indexCenter',
    components: {Clock, VeMap},
    data() {
      return {
        mapChart: {
          chartSettings: {},
          chartExtend: {},
          chartData: {
            columns: ['regionName', 'num'],
            rows: [{regionName: '大岭山镇', 'num': 123},
              {regionName: '长安镇', 'num': 323},
              {regionName: '沙田镇', 'num': 523}]
          },
          mapEvent: {}
        },
        taskTableData: []
      }
    },
    beforeMount() {
      this.mapChart.chartSettings = {
        position: 'dg',
        mapOrigin: mapJson,
        labelMap: {
          regionName: '镇街',
          num: '评分'
        },
        itemStyle: {
          normal: {
            areaColor: 'transparent',
            borderColor: '#21f7ff',
            borderWidth: 1,
            shadowColor: 'rgba(63, 218, 255, 0.5)',
            shadowBlur: 30
          },
          emphasis: {
            areaColor: '#27c32a'
          }
        },
        label: {
          show: true,
          formatter: (res => {
            //console.log(res)
            return '{b|' + res.name.substring(0, 3) + '}\n{c|' + res.value + '}'
          }),
          rich: {
            b: {
              fontSize: 12,
              lineHeight: 15,
              color: 'white'
            },
            c: {
              fontSize: 16,
              fontWeight: 'bold',
              lineHeight: 33,
              color: '#f6ea83'
            }
          }
        }
      }
      this.mapChart.chartExtend = {
        visualMap: {
          min: 0,
          max: 100,
          text: ['最高', '最小'],
          realtime: false,
          calculable: true,
          inRange: {
            color: ['#16e9f9', '#1871b9']
          },
          textStyle: {
            color: '#fff'
          },
          right: '20px',
          bottom: '20px'
        },
        series: {
          top: '30px',
          bottom: '30px',
          left: '50px',
          right: '120px',
          nameMap: {},
          selectedMode: 'single'
        }

      }
      this.mapChart.mapEvent = {
        click: function (item) {
          console.log(item.name)
        }
      }
      this.mapChart.chartData.rows = rand.getTownCount()
      //console.log(this.mapChart.chartData.rows)
    },
    mounted() {
      this.taskTableData = rand.getSubclassData()
    }
  }
</script>

<style lang="scss" scoped>
  .index-center {

    .map-chart {
      margin-left: auto;
      margin-right: auto;
    }

    .task-table {
      margin-top: 10px;
      display: block;

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
        height: 350px;
        overflow: hidden;
        position: relative;
        padding: 0 40px 0 40px;

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
              padding-left: 5px;
              padding-bottom: 10px;
            }
          }
        }
      }
    }

  }
</style>
