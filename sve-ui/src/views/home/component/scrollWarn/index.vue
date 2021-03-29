<template>
  <div class="">
    <div class="page-example3" style="">
      <vue-seamless-scroll :data="listData" :class-option="defaultOption">
        <ul class="ul-scoll">
          <li v-for="(item, index) in listData" :key='index'>
            <span :title="item.tagHead" class="qian"> {{ item.tagHead }}</span>
            <span :title="item.sourceName+'(点击查看详情)'" class="mid" @click="sourceSelect(item.sourceId)"> {{
                item.sourceName
              }}</span>
            <span :title="item.warnType" class="hou">{{ item.warnType }}</span>
          </li>
        </ul>
      </vue-seamless-scroll>
    </div>
  </div>
</template>

<script>
import vueSeamlessScroll from "vue-seamless-scroll";

export default {
  components: {
    vueSeamlessScroll
  },
  computed: {
    defaultOption () {
      return {
        step: 0.2, // 数值越大速度滚动越快
        limitMoveNum: 2, // 开始无缝滚动的数据量 this.dataList.length
        hoverStop: true, // 是否开启鼠标悬停stop
        direction: 1, // 0向下 1向上 2向左 3向右
        openWatch: true, // 开启数据实时监控刷新dom
        singleHeight: 0, // 单步运动停止的高度(默认值0是无缝不停止的滚动) direction => 0/1
        singleWidth: 0, // 单步运动停止的宽度(默认值0是无缝不停止的滚动) direction => 2/3
        waitTime: 1000 // 单步运动停止的时间(默认值1000ms)
      };
    }
  },
  data () {
    // 这里存放数据
    return {
      listData: [
        {
          tagHead: "VOCs",
          sourceName: "东莞裕祥鞋材有限公司",
          warnType: "排放浓度超标",
          sourceId: "67148"
        },
        {
          tagHead: "VOCs",
          sourceName: "东莞市亚力山大家具有限公司",
          warnType: "周传输率异常",
          sourceId: "67148"
        },
        {
          tagHead: "VOCs",
          sourceName: "东莞宝建高分子材料有限公司",
          warnType: "收集风机开启时间不足",
          sourceId: "67148"
        },
        {
          tagHead: "重点排水户",
          sourceName: "爱沛精密模塑（东莞）有限公司",
          warnType: "设备运行异常",
          sourceId: "67148"
        },
        {
          tagHead: "重点排水户",
          sourceName: "大量(东莞)五金制品有限公司",
          warnType: "数据缺失",
          sourceId: "67148"
        },
        {
          tagHead: "重点排水户",
          sourceName: "东莞爱里富科技有限公司",
          warnType: "水质严重异常",
          sourceId: "67148"
        },
        {
          tagHead: "零星废水",
          sourceName: "博克精密部件（广东）有限公司",
          warnType: "进水流量计数据异常",
          sourceId: "67148"
        },
        {
          tagHead: "零星废水",
          sourceName: "东莞市力通玩具制品有限公司",
          warnType: "收集风机开启时间不足",
          sourceId: "67148"
        },
        {
          tagHead: "零星废水",
          sourceName: "东莞市金叶电子有限公司",
          warnType: "周传输率异常",
          sourceId: "67148"
        },
        {
          tagHead: "零星废水",
          sourceName: "东莞市星翼化妆用具有限公司",
          warnType: "月水平衡异常",
          sourceId: "67148"
        },
        {
          tagHead: "过程监控",
          sourceName: "东莞市亮成电子有限公司西南分公司",
          warnType: "进出水20日水平衡异常",
          sourceId: "67148"
        },
        {
          tagHead: "过程监控",
          sourceName: "东莞市明兴食品有限公司",
          warnType: "进出水30日水平衡异常",
          sourceId: "67148"
        },
        {
          tagHead: "过程监控",
          sourceName: "广东众生药业股份有限公司",
          warnType: "进出水30日水平衡异常",
          sourceId: "67148"
        },
        {
          tagHead: "餐饮行业",
          sourceName: "东莞市西关茶楼有限公司石龙分公司",
          warnType: "周传输率异常",
          sourceId: "67148"
        },
        {
          tagHead: "餐饮行业",
          sourceName: "东莞市金怡海鲜酒楼有限公司",
          warnType: "周传输率异常",
          sourceId: "67148"
        },
        {
          tagHead: "餐饮行业",
          sourceName: "卡士莫实业(东莞)有限公司第三分公司",
          warnType: "周传输率异常",
          sourceId: "67148"
        },
        {
          tagHead: "汽修行业",
          sourceName: "东莞市大岭山胜辉汽车车身维修店",
          warnType: "排放浓度超标",
          sourceId: "67148"
        },
        {
          tagHead: "汽修行业",
          sourceName: "东莞市厚街鸿鹏汽车砂板修理站",
          warnType: "周传输率异常",
          sourceId: "67148"
        },
        {
          tagHead: "汽修行业",
          sourceName: "东莞市渝庆汽车销售服务有限公司",
          warnType: "月传输率异常",
          sourceId: "67148"
        }
      ]
    };
  },
  methods: {
    sourceSelect (v) {
      this.$emit("ejectMsg", v);
    }
  },
  watch: {},
  created () {
  },

};
</script>

<style lang='scss' scoped>
.page-example3 {
  width: 100%;
  height: 200px;
  overflow: hidden;
  border: 1px solid #797979;

  .ul-scoll {
    padding: 0;
    margin: 0;

    li {
      list-style-type: none;
      display: flex;
      padding-right: .5%;
      width: 100%;
      height: auto;
      line-height: 20px;
      //border: 1px solid #db1f35;
      background-size: 100% !important;
      color: #BDF3F6;
      font-size: 12px;
      border-radius: 4px;
      overflow: hidden;
      transition: .3s;
      margin: 2px 0;
      border: none;

      //&:nth-of-type(odd) {
      //  background-color: #FF6666;
      //  color: #fff;
      //}
      //
      //&:nth-of-type(even) {
      //  background-color: #FFCCCC;
      //  color: #000
      //}


      span.qian {
        width: 70px;
        text-align: left;
        margin-right: 5px;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
        padding-left:5px;
        transform: scale(0.95);
      }

      span.mid {
        flex: 1;
        padding-right: 5px;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
        color: #1890ff;
        transform: scale(0.95);
        &:hover {
          cursor: pointer;
          color: #66b1ff;
        }
      }

      span.hou {
        width: 120px;
        padding-right: 5px;
        text-align: left;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
        transform: scale(0.95);
      }

      .title {
        padding: 0 1% 0 4%;
        width: 21%;

      }

      .ovr_h {
        width: 51%;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
      }
    }
  }
}

/*一级异常*/
.pro_1 {
  background: #F89D2E;

  .title {
    color: #F89D2E;
  }
}

/*二级异常*/
.pro_2 {
  background: #00FCD8;

  .title {
    color: #00FCD8;
  }
}

/*三级异常*/
.pro_3 {
  background: #3CFF45;

  .title {
    color: #3CFF45;
  }
}

</style>
