<template>
  <div class="task-wk">
    <el-form :model="queryForm" ref="queryForm" :inline="true">
      <el-form-item label="日期范围" prop="dateTime">
        <el-date-picker
          v-model="queryForm.dateTime"
          :clearable="false"
          style="width:280px"
          :picker-options="pickerOptions"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery" v-preventClick>搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <ve-histogram :data="chartData"></ve-histogram>
  </div>
</template>

<script>
import { fun_date } from "@/utils/ruoyi"
import { dgomSuperviseSearch } from "@/api/om"
import VeHistogram from "v-charts/lib/histogram.common";

export default {
  components: { VeHistogram },
  data() {
    return {
      queryForm: {},
      pickerOptions: {
        disabledDate: this.disabledDate
      },
      chartData: {
        columns: ["industryType", "count"],
        rows: []
      },
      type: {
        other: "其他行业",
        dianli: "电力行业",
        zaozhi: "造纸行业",
        diandu: "电镀行业",
        piaoran: "漂染行业"
      }
    }
  },
  created() {
    let oDate = this.parseTime(new Date()).substr(0, 10);
    this.queryForm.dateTime = [fun_date(-6), oDate];
  },
  methods: {
    getData() {
      let oVal = {
        startDate: this.queryForm.dateTime[0],
        endDate: this.queryForm.dateTime[1]
      }
      dgomSuperviseSearch(oVal).then(res => {
        if (res.code === 200) {
          let oRes = JSON.parse(JSON.stringify(res.data));
          let oArr = [];
          for (let i in oRes) {
            let a = {
              industryType: this.type[i],
              count: oRes[i]
            }
            oArr.push(a);
          }
          this.chartData.rows = oArr;
        }
      })
    },
    disabledDate(time) {
      return time.getTime() > Date.now() - 8.64e6;
    },
    handleQuery() {
      this.getData();
    },
    resetQuery() {}
  }
}
</script>

<style lang="scss">

</style>
