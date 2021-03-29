<template>
  <div>
    <el-radio-group
      v-model="radio1"
      fill="#FFCC00"
      text-color="#333333"
      class="townChange"
      size="mini"
      @change="onChange"
    >
      <el-radio-button label="1">完成量</el-radio-button>
      <el-radio-button label="2">完成率</el-radio-button>
    </el-radio-group>
    <ve-histogram
      :data="townData"
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
import { countTown } from "@/api/analysis";
export default {
  components: { VeHistogram },
  props: {
    isTownHeadId: {
      type: Array,
      default: () => [],
    },
  },
  watch: {
    isTownHeadId: {
      handler(v) {
        this.getTownData(v);
      },
    },
    immediate: true,
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
          accepted: "已验收",
        },
        stack: {
          施工状态: ["noconnected", "constructed", "completed", "accepted"],
        },
      },
      legend: {
        show: true,
        top: 10,
        textStyle: {
          color: "#ffffff",
          fontSize: 11,
        },
        selected: {
          施工中: true,
          已接入: true,
          已验收: true,
        },
      },
      extend: {
        series(v) {
          // 设置柱子的样式
          v.forEach((i) => {
            i.barWidth = 26;
            i.itemStyle = {
              borderWidth: 1,
            };
            i.label = {
              color: "#fff",
              show: false,
              position: "inside",
              fontSize: 10,
            };
          });
          return v;
        },
        "grid.bottom": "20px",
        "grid.top": "50px",
        "yAxis.0.axisLabel.color": "#fff",
        // yAxis: {
        //   axisLabel: {
        //     textStyle: {
        //       color: "#fff",
        //       fontSize: 12
        //     }
        //   }
        // },
        xAxis: {
          axisLabel: {
            show: true,
            // rotate: 20, //倾斜
            textStyle: {
              color: "#fff",
              fontSize: 12,
            },
            formatter: function (value) {
              return value.split("").join("\n");
            },
            rich: {
              labelText: {
                lineHeight: 18,
              },
            },
          },
        },
      },
      dataZoom: [
        {
          type: "slider", //有单独的滑动条，用户在滑动条上进行缩放或漫游。inside是直接可以是在内部拖动显示
          show: true, //是否显示 组件。如果设置为 false，不会显示，但是数据过滤的功能还存在。
          start: 0, //数据窗口范围的起始百分比0-100
          end: 0, //数据窗口范围的结束百分比0-100
          bottom: -10,
          textStyle: {
            color: "#fff",
          },
        },
      ],
      events: {
        legendselectchanged: (item) => {
          const oMap = {
            施工中: "constructed",
            已接入: "completed",
            已验收: "accepted",
          };
          let selectList = item.selected;
          const selected = Object.keys(selectList).filter((p) => selectList[p]);
          this.townData.rows = this.townData.rows
            .map((m) => {
              m.currentTotal = selected
                .map((x) => m[oMap[x]])
                .reduce((total, cur) => (total += cur));
              return m;
            })
            .sort((x, y) => y.currentTotal - x.currentTotal);
          this.config.legend.selected = selectList;
        },
      },
    };
    return {
      townPercen: [],
      defaultData: [],
      radio1: "1",
      townData: {
        columns: ["name", "constructed", "completed", "accepted"],
        rows: [],
      },
    };
  },
  methods: {
    //获取镇街任务进度排名
    getTownData(v) {
      let oVal = v.join(",");
      countTown({ tagHeadIds: oVal }).then((res) => {
        if (res.code === 200) {
          this.defaultData = res.data;
          this.orderBy(this.config.legend.selected, res.data);
        }
      });
    },
    //处理图标滚动条
    afterSetOption(echarts) {
      let _that = this;
      let oLen = this.townData.rows.length;
      let oEnd = 0;
      if (oLen > 26) {
        oEnd = (26 / oLen) * 100;
      } else {
        oEnd = 100;
      }
      echarts.setOption({
        dataZoom: [
          {
            end: oEnd,
          },
        ],
      });
      this.$refs.histoRef.echarts.resize();
    },
    //计算百分率
    countPercen(v = []) {
      let list = ["constructed", "completed", "accepted"];
      let oArr = [];
      v.forEach((i) => {
        let obj = {};
        for (let j in i) {
          if (list.indexOf(j) !== -1) {
            obj[j] = parseFloat((i[j] / i["count"]).toFixed(3));
          } else if (j === "name") {
            obj["name"] = i["name"];
          }
        }
        oArr.push(obj);
      });
      this.orderBy(this.config.legend.selected, oArr);
    },
    //切换
    onChange(v) {
      if (v === "1") {
        this.config.settings.yAxisType = ["normal"];
        this.orderBy(this.config.legend.selected, this.defaultData);
      } else if (v === "2") {
        this.config.settings.yAxisType = ["percent"];
        this.countPercen(this.defaultData);
      }
    },
    //排序
    orderBy(selectList, v) {
      const oMap = {
        施工中: "constructed",
        已接入: "completed",
        已验收: "accepted",
      };
      const selected = Object.keys(selectList).filter((p) => selectList[p]);
      this.townData.rows = v
        .map((m) => {
          m.currentTotal = selected
            .map((x) => m[oMap[x]])
            .reduce((total, cur) => (total += cur));
          return m;
        })
        .sort((x, y) => y.currentTotal - x.currentTotal);
    },
  },
};
</script>
