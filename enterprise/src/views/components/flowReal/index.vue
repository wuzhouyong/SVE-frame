<template>
  <el-dialog
    title="流程图"
    :visible.sync="dialogVisible"
    width="90%"
    height="90%"
    class="flow-page">
    <div class="flow-chart" :class="{noChart:chartList.length==0}">
      <flow-panel :type="type" class="panel-box" v-for="chart in chartList" :chart="chart"
                  :key="chart.id+'_flow'"></flow-panel>
    </div>
    <span slot="footer" class="dialog-footer">
    </span>
  </el-dialog>
</template>
<script>
  import FormDialog from "@/views/components/form-dialog";
  import FlowPanel from "../../../components/autoFlow/components/flowPanel";
  import {getFlowChartData} from "../../../api/flowReal/charData";

  export default {
    components: {FlowPanel, FormDialog},
    props: ["type", "visible"],
    name: 'flow-real',
    data() {
      return {
        sourceId: undefined,
        platform: "",
        chartList: [],

      };
    },
    computed: {
      dialogVisible: {
        get() {
          return this.visible || false;
        },
        set(v) {
          this.$emit("update:visible", v);
        }
      }
    },
    created() {
      this.platform = this.type;
      this.sourceId = this.$store.getters.sourceId;

      console.log(this.platform);
      console.log(this.sourceId);
      this.getChart();
    },
    methods: {
      getChart() {
        if (!this.sourceId)
          return;

        let data = {key: this.sourceId, platform: this.platform};
        getFlowChartData(data).then(res => {
          if (res) {
            this.chartList = res.data;
          }
        });
      }
    }
  };
</script>
<style lang="scss">
  $height: calc(100vh - 60px);
  $height2: calc(100vh - 200px);
  .flow-page {
    height: $height;

    .el-dialog__body {
      height: $height2 !important;
      overflow: auto !important;
    }

    .flow-chart {

      padding-left: 10px;
      /*background-color: red;*/

      .panel-box {
        margin-bottom: 10px;
      }
    }

    .flow-chart.noChart {
      background-image: url("../../../assets/autoFlow/no_view.png");
      background-repeat: no-repeat;
      background-position: center;
    }
  }
</style>
