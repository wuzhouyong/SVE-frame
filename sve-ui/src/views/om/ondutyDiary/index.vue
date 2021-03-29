<template>
  <div class="duty-diary">
    <!--    值守日记-->
    <el-form :model="queryForm" ref="queryForm" :inline="true" class="voc-serchForm">
      <el-form-item label="选择日期" prop="isDate">
        <el-date-picker
          v-model="queryForm.isDate"
          style="width:140px"
          value-format="yyyy-MM-dd"
          type="date"
        ></el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
      </el-form-item>
      <el-form-item>
        <el-button type="warning" icon="el-icon-upload2" size="mini" @click="exportTable">导出表格</el-button>
      </el-form-item>
    </el-form>
    <section class="page-main" v-loading="loading"
             element-loading-text="拼命加载中">
      <!-- 内容区 -->
      <div class="page-content" style="height: 100%;">
        <div class="on_duty">
          <div class="on_duty_top">
            <div class="on_duty_t_l">
              <div class="on_du_t_l1" style="text-align: center">
                自动监控数据<br/>超标情况
              </div>
              <div class="on_du_t_l2">
                <h3>数量(条)</h3>
                <div class="on_du_t_l21">
                  <div class="on_du_t_l21_l">
                    时均值
                  </div>
                  <div class="on_du_t_l21_r">
                    <div class="on_water" id="h_w_num">
                      {{ diaryData.h_w_num }}
                    </div>
                    <div class="on_gas" id="h_g_num">
                      {{ diaryData.h_g_num }}
                    </div>
                  </div>
                </div>
                <div class="on_du_t_l22">
                  <div class="on_du_t_l21_l">
                    日均值
                  </div>
                  <div class="on_du_t_l21_r">
                    <div class="on_water" id="d_w_num">
                      {{ diaryData.d_w_num }}
                    </div>
                    <div class="on_gas" id="d_g_num">
                      {{ diaryData.d_g_num }}
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="on_duty_t_m">
              <h3>企业数量（家）</h3>
              <div class="on_du_t_l21 num_style" id="h_src_num">
                {{ diaryData.h_src_num }}
              </div>
              <div class="on_du_t_l22 num_style" id="d_src_num">
                {{ diaryData.d_src_num }}
              </div>
            </div>
            <div class="on_duty_t_r">
              <h3>设备数量(台)</h3>
              <div class="equi_wk">
                <div class="equi">
                  <pre id="h_w_dev_info">{{ diaryData.h_w_dev_info }}</pre>
                </div>
                <div class="yqi" id="h_g_dev_info">
                  {{ diaryData.h_g_dev_info }}
                </div>
              </div>
              <div class="equi_wk borderNone">
                <div class="equi">
                  <pre id="d_w_dev_info">{{ diaryData.d_w_dev_info }}</pre>
                </div>
                <div class="yqi" id="d_g_dev_info">
                  {{ diaryData.d_g_dev_info }}
                </div>
              </div>
            </div>
          </div>
          <div class="on_duty_mid">
            <div class="on_duty_last">
              <p>自动监测数据缺失情况(传输率 < 90%)</p>
            </div>
            <div class="on_duty_last_num">
              <p id="trans_rate_num">{{ diaryData.trans_rate_num }}</p>
            </div>
            <div class="on_duty_last_list">
              <pre id="trans_rate_info">{{ diaryData.trans_rate_info }}</pre>
            </div>
          </div>
          <div class="on_duty_btm">
            <div class="on_duty_last">
              <p>自动监控数据异常情况</p>
            </div>
            <div class="on_duty_last_num">
              <p id="zero_record_num">{{ diaryData.zero_record_num }}</p>
            </div>
            <div class="on_duty_last_list">
              <pre id="zero_record_info">{{ diaryData.zero_record_info }}</pre>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import {dgomDutyDiary, dgomExportDiary} from '@/api/om'
import {fun_date} from "@/utils/ruoyi";

export default {
  created() {
    let oDate = this.parseTime(new Date()).substr(0, 10);
    this.queryForm.isDate = fun_date(-1)
    // this.queryForm.isDate = oDate;
    this.getData(this.queryForm);
  },
  data() {
    return {
      queryForm: {
        isDate: null
      },
      diaryData: {},
      loading: false
    }
  },
  methods: {
    getData(v) {
      this.loading = true;
      let oVal = {
        startDate: v.isDate,
        endDate: v.isDate
      }
      dgomDutyDiary(oVal).then(res => {
        this.diaryData = res.data;
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
            startDate: _that.queryForm.isDate,
            endDate: _that.queryForm.isDate
          }
          return dgomExportDiary(oVal);
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
.duty-diary {
  width: 100%;
  height: 100%;
  overflow: auto;
  padding: 10px;

  /deep/ .el-form-item {
    margin-bottom: 5px;
  }
}

h3, p {
  margin: 0;
  padding: 0;
}

.on_duty {
  width: 670px;
  height: auto;
  margin: 10px 0;
  font-size: 12px;

}

.on_duty_top {
  width: 670px;
  border: 1px solid #ddd;
  border-bottom: none;
  height: auto;
  min-height: 400px;
  display: flex;
  flex-direction: row
}

.on_duty_t_l {
  width: 300px;
  height: auto;
  border-right: 1px solid #ddd;
  display: flex;
  flex-direction: row
}

.on_du_t_l1 {
  width: 120px;
  padding: 0 8px;
  height: 100%;
  border-right: 1px solid #ddd;
  display: flex;
  align-items: center;
  justify-content: center;
}

.on_du_t_l2 {
  flex: 1 1;
}

.on_du_t_l2 h3 {
  font-weight: normal;
  font-size: 12px;
  height: 25px;
  line-height: 25px;
  text-align: center;
  border-bottom: 1px solid #ddd;
  width: 100%;
  margin: 0;
}

.on_du_t_l21 {
  width: 100%;
  height: 187px;
  border-bottom: 1px solid #ddd;
  display: flex;
  flex-direction: row;
}

.on_du_t_l21_l {
  width: 60px;
  display: flex;
  align-items: center;
  justify-content: center;
  border-right: 1px solid #ddd;
}

.on_du_t_l21_r {
  flex: 1 1;
}

.on_du_t_l22 {
  width: 100%;
  height: 187px;
  display: flex;
  flex-direction: row;
}

.on_water {
  width: 100%;
  height: 150px;
  line-height: 150px;
  text-align: center;
  border-bottom: 1px solid #ddd;
}

.on_gas {
  width: 100%;
  height: 36px;
  line-height: 36px;
  text-align: center;
}

.on_duty_t_m {
  width: 150px;
  border-right: 1px solid #ddd;
}

.on_duty_t_m .num_style {
  line-height: 188px;
  align-items: center;
  justify-content: center;
}

.on_duty_t_m h3 {
  font-weight: normal;
  font-size: 12px;
  height: 25px;
  line-height: 25px;
  text-align: center;
  border-bottom: 1px solid #ddd;
  width: 100%;
}

.on_duty_t_r {
  flex: 1 1;
}

.on_duty_t_r h3 {
  font-weight: normal;
  font-size: 12px;
  height: 25px;
  line-height: 25px;
  text-align: center;
  border-bottom: 1px solid #ddd;
  width: 100%;
}

.equi_wk {
  height: 187px;
  width: 100%;
  border-bottom: 1px solid #ddd;
}

.borderNone {
  border-bottom: none;
}

.equi_wk .equi {
  width: 100%;
  height: 150px;
  border-bottom: 1px solid #ddd;
  display: flex;
  align-items: center;
  justify-content: center;
  line-height: 20px;
}

.equi_wk .yqi {
  width: 100%;
  height: 36px;
  line-height: 36px;
  text-align: center;
}

/*中*/
.on_duty_mid {
  width: 670px;
  border: 1px solid #ddd;
  border-bottom: none;
  height: auto;
  min-height: 200px;
  display: flex;
  flex-direction: row
}

.on_duty_last {
  width: 300px;
  height: auto;
  border-right: 1px solid #ddd;
  text-align: center;
  position: relative;
}

.on_duty_last p, .on_duty_last_num p {
  width: 260px;
  height: 16px;
  line-height: 16px;
  position: absolute;
  left: 50%;
  margin-left: -130px;
  top: 50%;
  margin-top: -8px;
}

.on_duty_last_num {
  width: 150px;
  height: auto;
  border-right: 1px solid #ddd;
  text-align: center;
  position: relative;
}

.on_duty_last_list {
  flex: 1 1;
  padding: 10px 0;
  padding-left: 15px;
  display: flex;
  align-items: center;
  justify-content: center;
  line-height: 20px;
}

/*下*/
.on_duty_btm {
  width: 670px;
  border: 1px solid #ddd;
  height: auto;
  min-height: 200px;
  display: flex;
  flex-direction: row;
}
</style>
