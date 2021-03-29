<template>
  <div class="ondutySummary">
    <el-form :model="queryForm" ref="queryForm" :inline="true" class="voc-serchForm">
      <el-form-item label="选择日期" prop="startTime">
        <el-date-picker
          v-model="queryForm.isDate"
          style="width:280px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
      </el-form-item>
      <el-form-item>
        <el-button type="warning" icon="el-icon-upload2" size="mini" @click="exportTable">导出表格</el-button>
      </el-form-item>
    </el-form>
    <!--    监控异常汇总表-->
    <section class="page-main" v-loading="loading"
             element-loading-text="拼命加载中">
      <!-- 内容区 -->
      <div class="page-content" style="height: 100%;">
        <div class="exce_sum_wk">
          <div class="exce_sum_header">
            <div class="tb_height ex_head_1">企业分类</div>
            <div class="tb_height ex_head_2">严重异常线索</div>
            <div class="clear"></div>
          </div>
          <div class="exce_sum_sec_header">
            <div class="tb_height ex_sec_head_1">项目</div>
            <div class="tb_height ex_sec_head_2">日均值超标</div>
            <div class="tb_height ex_sec_head_2">连续2日日均值超标</div>
            <div class="tb_height ex_sec_head_2">累计4日日均值超标</div>
            <div class="tb_height ex_sec_head_3">传输率低于90%</div>
            <div class="clear"></div>
          </div>
          <div class="exce_sum_industry_wk">
            <div class="exce_sum_industry_title exceLeft">
              行<br/>业
            </div>
            <div class="exce_sum_industry_r exceRightht">
              <div class="exce_sum_industry_rT">
                <div class="exce_rT_t exceLeft">
                  国家考核排污单位
                </div>
                <ul class="exceRightht">
                  <li>
                    <h4>
                      垃圾焚烧发电厂
                    </h4>
                    <p>{{ rubbish.day_cnt }}</p>
                    <p>{{ rubbish.two_day_cnt }}</p>
                    <p>{{ rubbish.four_day_cnt }}</p>
                    <p class="border_r_none">{{ rubbish.rate_less_90 }}</p>
                  </li>
                  <li>
                    <h4>
                      污水处理厂
                    </h4>
                    <p>{{ sewage.day_cnt }}</p>
                    <p>{{ sewage.two_day_cnt }}</p>
                    <p>{{ sewage.four_day_cnt }}</p>
                    <p class="border_r_none">{{ sewage.rate_less_90 }}</p>
                  </li>
                  <li class="border_b_none">
                    <h4>
                      其它
                    </h4>
                    <p>{{ other.day_cnt }}</p>
                    <p>{{ other.two_day_cnt }}</p>
                    <p>{{ other.four_day_cnt }}</p>
                    <p class="border_r_none">{{ other.rate_less_90 }}</p>
                  </li>
                </ul>
                <div class="clear"></div>
              </div>
              <div class="exce_sum_industry_rB">
                <div class="rB_1 exceLeft">
                  其它重点排污单位
                </div>
                <p>{{ otherCompany.day_cnt }}</p>
                <p>{{ otherCompany.two_day_cnt }}</p>
                <p>{{ otherCompany.four_day_cnt }}</p>
                <p class="border_r_none">{{ otherCompany.rate_less_90 }}</p>
                <div class="clear"></div>
              </div>
            </div>
            <div class="clear"></div>
          </div>
          <div class="exce_territor_wk">
            <div class="exce_territor_title">
              属<br/>地
            </div>
            <div class="exce_territor_r">
              <ul>
                <li v-for="i in listData" :key="i.zsuperiorid">
                  <h4>{{ i.town_name }}</h4>
                  <p>{{ i.day_cnt }}</p>
                  <p>{{ i.two_day_cnt }}</p>
                  <p>{{ i.four_day_cnt }}</p>
                  <p class="border_r_none">{{ i.rate_less_90 }}</p>
                </li>
              </ul>
            </div>
          </div>

          <div class="exce_sum_sec_header">
            <div class="tb_height ex_sec_head_1">汇总</div>
            <div class="tb_height ex_sec_head_2">{{ summary.day_cnt }}</div>
            <div class="tb_height ex_sec_head_2">{{ summary.two_day_cnt }}</div>
            <div class="tb_height ex_sec_head_2">{{ summary.four_day_cnt }}</div>
            <div class="tb_height ex_sec_head_3">{{ summary.rate_less_90 }}</div>
            <div class="clear"></div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import {fun_date} from "@/utils/ruoyi";
import {dgomDutyAbnorSummary, dgomDutyAbnorSummaryExport} from '@/api/om'

export default {
  data() {
    return {
      queryForm: {},
      listData: {},
      loading: false,
      rubbish: {},
      sewage: {},
      other: {},
      otherCompany: {},
      summary: {}
    }
  },
  created() {
    let oDate = this.parseTime(new Date()).substr(0, 10);
    this.queryForm.isDate = [fun_date(-10), oDate];
    this.getData(this.queryForm)
  },
  methods: {
    getData(v) {
      this.loading = true;
      let oVal = {
        startDate: v.isDate[0],
        endDate: v.isDate[1]
      }
      dgomDutyAbnorSummary(oVal).then(res => {
        this.listData = res.data.towns;
        let cates = res.data.cates;
        cates.forEach(i => {
          // switch (i.zsubcategoryid) {
          //   case 5042:
          //     this.rubbish = i;
          //   case  201:
          //     this.sewage = i;
          //     case
          // }
          if (i.zsubcategoryid === 5042) {
            this.rubbish = i;
          } else if (i.zsubcategoryid === 201) {
            this.sewage = i;
          } else if (i.zsubcategoryid === 'other') {
            this.other = i
          } else if (i.zsubcategoryid === 'zhongdian_other') {
            this.otherCompany = i;
          } else {
            this.summary = i;
          }
        })
        this.loading = false;
      })
    },
    handleQuery() {
      this.getData(this.queryForm);
    },
    exportTable() {
      let _that = this;
      this.$confirm("是否导出数据?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(function () {
          let oVal = {
            startDate: _that.queryForm.isDate[0],
            endDate: _that.queryForm.isDate[1]
          }
          return dgomDutyAbnorSummaryExport(oVal);
        })
        .then((response) => {
          this.download(response.msg);
        })
        .catch(function () {
        });
    },
  }
}
</script>

<style lang='scss' scoped>
.ondutySummary {
  width: 100%;
  height: 100%;
  overflow-y: auto;
  padding: 10px;
  display: flex;
  flex-direction: column;
}

h4, p, ul, li {
  padding: 0;
  margin: 0;
}

.tb_height {
  height: 38px;
  line-height: 38px;
}

.clear {
  clear: both;
}

.border_b_none {
  border-bottom: none !important;
}

.border_r_none {
  border-right: none !important;
}

.exce_sum_wk {
  width: 819px;
  height: auto;
  border: 1px solid #ddd;
  margin: 10px;
  font-size: 12px;
  letter-spacing: 1px;
  border-bottom: none;
}

.exce_sum_wk .exceLeft {
  float: left;
}

.exce_sum_wk .exceRightht {
  float: right;
}

.exce_sum_header {
  font-weight: bold;
}

.exce_sum_header .ex_head_1 {
  float: left;
  width: 225px;
  border-bottom: 1px solid #ddd;
  border-right: 1px solid #ddd;
  text-align: center;
}

.exce_sum_header .ex_head_2 {
  float: left;
  width: 591px;
  border-bottom: 1px solid #ddd;
  text-align: center;
}

.exce_sum_sec_header .ex_sec_head_1 {
  float: left;
  width: 225px;
  border-bottom: 1px solid #ddd;
  border-right: 1px solid #ddd;
  text-align: center;
}

.exce_sum_sec_header .ex_sec_head_2 {
  float: left;
  width: 147px;
  border-bottom: 1px solid #ddd;
  border-right: 1px solid #ddd;
  text-align: center;
}

.exce_sum_sec_header .ex_sec_head_3 {
  float: left;
  width: 147px;
  border-bottom: 1px solid #ddd;
  text-align: center;
}

.exce_sum_industry_wk .exce_sum_industry_title {
  width: 43px;
  height: 171px;
  display: flex;
  align-items: center;
  justify-content: center;
  border-right: 1px solid #ddd;
  border-bottom: 1px solid #ddd;
}

.exce_sum_industry_wk .exce_sum_industry_r {
  width: 774px;
  height: auto;
}

.exce_sum_industry_wk .exce_sum_industry_r .exce_sum_industry_rT {
  border-bottom: 1px solid #ddd;
}

.exce_sum_industry_wk .exce_sum_industry_r .exce_sum_industry_rT .exce_rT_t {
  width: 24px;
  height: 137px;
  border-right: 1px solid #ddd;
  display: flex;
  align-items: center;
  justify-content: center;
  text-align: center;
}

.exce_sum_industry_wk .exce_sum_industry_r .exce_sum_industry_rT ul {
  width: 749px;
}

.exce_sum_industry_wk .exce_sum_industry_r .exce_sum_industry_rT ul li {
  width: 100%;
  height: 45px;
  line-height: 39px;
  border-bottom: 1px solid #ddd;
  list-style-type: none;
}

.exce_sum_industry_wk .exce_sum_industry_r .exce_sum_industry_rT ul li h4 {
  width: 157px;
  height: 45px;
  line-height: 45px;
  border-right: 1px solid #ddd;
  float: left;
  text-align: center;
  font-weight: normal;
}

.exce_sum_industry_wk .exce_sum_industry_r .exce_sum_industry_rT ul li p {
  float: left;
  width: 147px;
  height: 45px;
  line-height: 45px;
  border-right: 1px solid #ddd;
  text-align: center;
}

.exce_sum_industry_wk .exce_sum_industry_r .exce_sum_industry_rB .rB_1 {
  width: 182px;
  height: 33px;
  line-height: 33px;
  text-align: center;
  border-right: 1px solid #ddd;
  border-bottom: 1px solid #ddd;
}

.exce_sum_industry_wk .exce_sum_industry_r .exce_sum_industry_rB p {
  float: left;
  width: 147px;
  height: 33px;
  line-height: 33px;
  border-right: 1px solid #ddd;
  border-bottom: 1px solid #ddd;
  text-align: center;
}

.exce_territor_wk {
  display: flex;
  flex-direction: row;
}

.exce_territor_wk .exce_territor_title {
  width: 43px;
  display: flex;
  align-items: center;
  justify-content: center;
  border-right: 1px solid #ddd;
  border-bottom: 1px solid #ddd;
}

.exce_territor_wk .exce_territor_r {
  flex: 1 1;
}

.exce_territor_wk .exce_territor_r ul li {
  list-style-type: none;
  display: flex;
  flex-direction: row;
  border-bottom: 1px solid #ddd;
}

.exce_territor_wk .exce_territor_r ul li h4 {
  width: 182px;
  border-right: 1px solid #ddd;
  height: 39px;
  line-height: 39px;
  font-weight: normal;
  text-align: center;
}

.exce_territor_wk .exce_territor_r ul li p {
  //flex: 1 1;
  width: 147px;
  border-right: 1px solid #ddd;
  height: 39px;
  line-height: 39px;
  text-align: center;
}
</style>
