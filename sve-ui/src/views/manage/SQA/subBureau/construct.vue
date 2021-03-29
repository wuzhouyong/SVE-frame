<template>
  <div>
    <ve-histogram
      :data="workData"
      ref="histoRef"
      :settings="config.settings"
      :extend="config.extend"
      :events="config.events"
      :colors="config.colors"
      :legend="config.legend"
      :data-zoom="config.dataZoom"
      :after-set-option="afterSetOption"
      height="300px"
    ></ve-histogram>
  </div>
</template>
<script>
import "echarts/lib/component/dataZoom";
import VeHistogram from "v-charts/lib/histogram.common";
import { countConstruct } from "@/api/analysis";
export default {
  components: { VeHistogram },
  props: {
    isWorkHeadId: {
      type: Array,
      default: () => []
    }
  },
  watch: {
    isWorkHeadId: {
      handler(v) {
        this.getWorkData(v);
      }
    },
    immediate: true
  },
  data() {
    //配置
    this.config = {
      colors: ["#D680EF", "#EEDA84", "#39C7FF", "#1DD8C5"],
      settings: {
        dataOrder: true,
        yAxisType: ["normal"],
        labelMap: {
          constructed: "施工中",
          completed: "已接入",
          accepted: "已验收"
        },
        stack: {
          施工状态: ["noconnected", "constructed", "completed", "accepted"]
        }
      },
      legend: {
        show: true,
        top: 10,
        textStyle: {
          color: "#ffffff",
          fontSize: 11
        },
        selected: {
          施工中: true,
          已接入: true,
          已验收: true
        }
      },
      extend: {
        series(v) {
          // 设置柱子的样式
          v.forEach(i => {
            i.barWidth = 26;
            i.itemStyle = {
              borderWidth: 1
            };
            i.label = {
              color: "#fff",
              show: false,
              position: "inside",
              fontSize: 10
            };
          });
          return v;
        },
        "grid.bottom": "20px",
        "grid.top": "50px",
        yAxis: {
          splitNumber: 5,
          axisTick: {
            alignWithLabel: 2
          },
          axisLabel: {
            show: true,
            textStyle: {
              color: "#fff",
              fontSize: 12
            }
          }
        },
        xAxis: {
          axisLabel: {
            show: true,
            rotate: 15, //倾斜
            textStyle: {
              color: "#fff",
              fontSize: 12
            }
          }
        }
      },
      dataZoom: [
        {
          type: "slider", //有单独的滑动条，用户在滑动条上进行缩放或漫游。inside是直接可以是在内部拖动显示
          show: true, //是否显示 组件。如果设置为 false，不会显示，但是数据过滤的功能还存在。
          start: 0, //数据窗口范围的起始百分比0-100
          end: 0, //数据窗口范围的结束百分比0-100
          bottom: -10,
          textStyle: {
            color: "#fff"
          }
        }
      ],
      events: {
        legendselectchanged: item => {
          const oMap = {
            施工中: "constructed",
            已接入: "completed",
            已验收: "accepted"
          };
          let selectList = item.selected;
          const selected = Object.keys(selectList).filter(p => selectList[p]);
          this.workData.rows = this.workData.rows
            .map(m => {
              m.currentTotal = selected
                .map(x => m[oMap[x]])
                .reduce((total, cur) => (total += cur));
              return m;
            })
            .sort((x, y) => y.currentTotal - x.currentTotal);
          this.config.legend.selected = selectList;
        }
      }
    };
    return {
      workData: {
        columns: ["name", "constructed", "completed", "accepted"],
        rows: []
      }
    };
  },
  methods: {
    getWorkData(v) {
      let oVal = v.join(",");
      countConstruct({ tagHeadIds: oVal }).then(res => {
        if (res.code === 200) {
          this.orderBy(this.config.legend.selected, res.data);
        }
      });
    },
    //处理图标滚动条
    afterSetOption(echarts) {
      let _that = this;
      let oLen = this.workData.rows.length;
      let oEnd = 0;
      if (oLen > 10) {
        oEnd = (10 / oLen) * 100;
      } else {
        oEnd = 100;
      }
      echarts.setOption({
        dataZoom: [
          {
            end: oEnd
          }
        ]
      });
      this.$refs.histoRef.echarts.resize();
    },
    //排序
    orderBy(selectList, v) {
      const oMap = {
        施工中: "constructed",
        已接入: "completed",
        已验收: "accepted"
      };
      const selected = Object.keys(selectList).filter(p => selectList[p]);
      this.workData.rows = v
        .map(m => {
          m.currentTotal = selected
            .map(x => m[oMap[x]])
            .reduce((total, cur) => (total += cur));
          return m;
        })
        .sort((x, y) => y.currentTotal - x.currentTotal);
    }
  }
};
</script>