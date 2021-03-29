//生成测试随机数据
import { number } from 'echarts/src/export'

export default {
  number: function(min, max) {
    return Math.floor(Math.random() * (max - min)) + min
  },

  taskTbaleData: function() {
    let data = [
      { type: '在线监控', title: '理文造纸-排水户末端水质超标', reason: '逾期' },
      { type: '在线监控', title: '世丽纺织-零散工业废水水损率异常', reason: '逾期' },
      { type: '在线监控', title: '华星食品-重点污染源数据超标', reason: '逾期' },
      { type: '在线监控', title: '东莞市丰悦针织厂-排水户末端水质超标', reason: '逾期' },
      { type: '在线监控', title: '京朗金属制品-金属表面处理水损率异常', reason: '逾期' },
      { type: '在线监控', title: '富力达印花-排水户末端水质超标', reason: '逾期' },
      { type: '在线监控', title: '新城区分散式-重点污染源数据超标', reason: '逾期' },
      { type: '在线监控', title: '新城区分散式-排水户末端水质超标', reason: '逾期' },
      { type: '在线监控', title: '世丽纺织-重点污染源数据超标', reason: '逾期' },
      { type: '在线监控', title: '林丰五金制品-金属表面处理水损率异常', reason: '逾期' },
      { type: '在线监控', title: '东莞市丰悦针织厂-零散工业废水水损率异常', reason: '逾期' },
      { type: '在线监控', title: '世丽纺织-重点污染源数据缺失', reason: '逾期' },
      { type: '在线监控', title: '东莞市丰悦针织厂-重点污染源数据缺失', reason: '逾期' },
      { type: '在线监控', title: '晋亿服装-排水户末端水质超标', reason: '逾期' },
      { type: '在线监控', title: '世丽纺织-VoCs未端排放超标', reason: '逾期' },
      { type: '在线监控', title: '世丽纺织-排水户末端水质超标', reason: '逾期' },
      { type: '在线监控', title: '和合- VoCs未端排放超标', reason: '逾期' }
    ]
    return data

  },

  taskSumData: function() {
    let data = [
      { type: '大气环境', no: this.number(1, 60), out: this.number(1, 9) },
      { type: '在线监控', no: this.number(1, 60), out: this.number(1, 9) },
      { type: '水环境', no: this.number(1, 60), out: this.number(1, 9) },
      { type: '环境信访', no: this.number(1, 60), out: this.number(1, 9) },
      { type: '省一体化执法平台', no: this.number(1, 60), out: this.number(1, 9) },
      { type: '监测任务管理平台', no: this.number(1, 60), out: this.number(1, 9) }
    ]
    return data
  },

  townTaskData: function() {
    let data = [{ name: '东城街道办事处', finish: this.number(1, 100), out: this.number(1, 20) },
      { name: '南城街道办事处', finish: this.number(1, 100), out: this.number(1, 20) },
      { name: '万江街道办事处', finish: this.number(1, 100), out: this.number(1, 20) },
      { name: '莞城街道办事处', finish: this.number(1, 100), out: this.number(1, 20) },
      { name: '石碣镇', finish: this.number(1, 100), out: this.number(1, 20) },
      { name: '石龙镇', finish: this.number(1, 100), out: this.number(1, 20) },
      { name: '茶山镇', finish: this.number(1, 100), out: this.number(1, 20) },
      { name: '石排镇', finish: this.number(1, 100), out: this.number(1, 20) },
      { name: '企石镇', finish: this.number(1, 100), out: this.number(1, 20) },
      { name: '横沥镇', finish: this.number(1, 100), out: this.number(1, 20) },
      { name: '桥头镇', finish: this.number(1, 100), out: this.number(1, 20) },
      { name: '谢岗镇', finish: this.number(1, 100), out: this.number(1, 20) },
      { name: '东坑镇', finish: this.number(1, 100), out: this.number(1, 20) },
      { name: '常平镇', finish: this.number(1, 100), out: this.number(1, 20) },
      { name: '寮步镇', finish: this.number(1, 100), out: this.number(1, 20) },
      { name: '樟木头镇', finish: this.number(1, 100), out: this.number(1, 20) },
      { name: '大朗镇', finish: this.number(1, 100), out: this.number(1, 20) },
      { name: '黄江镇', finish: this.number(1, 100), out: this.number(1, 20) },
      { name: '清溪镇', finish: this.number(1, 100), out: this.number(1, 20) },
      { name: '塘厦镇', finish: this.number(1, 100), out: this.number(1, 20) },
      { name: '凤岗镇', finish: this.number(1, 100), out: this.number(1, 20) },
      { name: '大岭山镇', finish: this.number(1, 100), out: this.number(1, 20) },
      { name: '长安镇', finish: this.number(1, 100), out: this.number(1, 20) },
      { name: '虎门镇', finish: this.number(1, 100), out: this.number(1, 20) },
      { name: '厚街镇', finish: this.number(1, 100), out: this.number(1, 20) },
      { name: '沙田镇', finish: this.number(1, 100), out: this.number(1, 20) },
      { name: '道滘镇', finish: this.number(1, 100), out: this.number(1, 20) },
      { name: '洪梅镇', finish: this.number(1, 100), out: this.number(1, 20) },
      { name: '麻涌镇', finish: this.number(1, 100), out: this.number(1, 20) },
      { name: '望牛墩镇', finish: this.number(1, 100), out: this.number(1, 20) },
      { name: '中堂镇', finish: this.number(1, 100), out: this.number(1, 20) },
      { name: '高埗镇', finish: this.number(1, 100), out: this.number(1, 20) },
      { name: '松山湖管委会', finish: this.number(1, 100), out: this.number(1, 20) },
      { name: '虎门港', finish: this.number(1, 100), out: this.number(1, 20) },
      { name: '生态园', finish: this.number(1, 100), out: this.number(1, 20) },
      { name: '滨海湾新区', finish: this.number(1, 100), out: this.number(1, 20) }]

    data.sort(this.sortData)
    return data
  },
  sortData: function(a, b) {
    return (a.finish + a.out) - (b.finish + b.out)
  },
  getTownCount: function() {
    let data = [{ regionName: '东城街道办事处', num: this.number(1, 100) },
      { regionName: '南城街道办事处', num: this.number(1, 100) },
      { regionName: '万江街道办事处', num: this.number(1, 100) },
      { regionName: '莞城街道办事处', num: this.number(1, 100) },
      { regionName: '石碣镇', num: this.number(1, 100) },
      { regionName: '石龙镇', num: this.number(1, 100) },
      { regionName: '茶山镇', num: this.number(1, 100) },
      { regionName: '石排镇', num: this.number(1, 100) },
      { regionName: '企石镇', num: this.number(1, 100) },
      { regionName: '横沥镇', num: this.number(1, 100) },
      { regionName: '桥头镇', num: this.number(1, 100) },
      { regionName: '谢岗镇', num: this.number(1, 100) },
      { regionName: '东坑镇', num: this.number(1, 100) },
      { regionName: '常平镇', num: this.number(1, 100) },
      { regionName: '寮步镇', num: this.number(1, 100) },
      { regionName: '樟木头镇', num: this.number(1, 100) },
      { regionName: '大朗镇', num: this.number(1, 100) },
      { regionName: '黄江镇', num: this.number(1, 100) },
      { regionName: '清溪镇', num: this.number(1, 100) },
      { regionName: '塘厦镇', num: this.number(1, 100) },
      { regionName: '凤岗镇', num: this.number(1, 100) },
      { regionName: '大岭山镇', num: this.number(1, 100) },
      { regionName: '长安镇', num: this.number(1, 100) },
      { regionName: '虎门镇', num: this.number(1, 100) },
      { regionName: '厚街镇', num: this.number(1, 100) },
      { regionName: '沙田镇', num: this.number(1, 100) },
      { regionName: '道滘镇', num: this.number(1, 100) },
      { regionName: '洪梅镇', num: this.number(1, 100) },
      { regionName: '麻涌镇', num: this.number(1, 100) },
      { regionName: '望牛墩镇', num: this.number(1, 100) },
      { regionName: '中堂镇', num: this.number(1, 100) },
      { regionName: '高埗镇', num: this.number(1, 100) },
      { regionName: '松山湖管委会', num: this.number(1, 100) },
      { regionName: '虎门港', num: this.number(1, 100) },
      { regionName: '生态园', num: this.number(1, 100) },
      { regionName: '滨海湾新区', num: this.number(1, 100) }]
    return data

  },

  getSubclassData: function() {
    let data = [
      {
        type: '重点排水户末端监控',
        all: this.number(1, 100),
        pro: this.number(1, 100),
        no: this.number(1, 100),
        out: this.number(1, 100)
      },
      {
        type: '重点污染源在线监控',
        all: this.number(1, 100),
        pro: this.number(1, 100),
        no: this.number(1, 100),
        out: this.number(1, 100)
      },
      {
        type: '零散工业废水产生单位过程监控',
        all: this.number(1, 100),
        pro: this.number(1, 100),
        no: this.number(1, 100),
        out: this.number(1, 100)
      },
      {
        type: '市级以上工业VOCs过程监控',
        all: this.number(1, 100),
        pro: this.number(1, 100),
        no: this.number(1, 100),
        out: this.number(1, 100)
      },
      {
        type: '汽修行业VOCs过程监控',
        all: this.number(1, 100),
        pro: this.number(1, 100),
        no: this.number(1, 100),
        out: this.number(1, 100)
      },
      {
        type: '餐饮行业',
        all: this.number(1, 100),
        pro: this.number(1, 100),
        no: this.number(1, 100),
        out: this.number(1, 100)
      },
      {
        type: '造纸行业过程监控',
        all: this.number(1, 100),
        pro: this.number(1, 100),
        no: this.number(1, 100),
        out: this.number(1, 100)
      },
      {
        type: '金属表面处理行业过程监控',
        all: this.number(1, 100),
        pro: this.number(1, 100),
        no: this.number(1, 100),
        out: this.number(1, 100)
      },
      {
        type: '四大流域涉水企业过程监控',
        all: this.number(1, 100),
        pro: this.number(1, 100),
        no: this.number(1, 100),
        out: this.number(1, 100)
      }
    ]

    return data
  }
}
