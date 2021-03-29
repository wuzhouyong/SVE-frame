<template>
  <SearchTem :loading="loading" :tableData="tableData" :cols="cols">
    <template slot="checkType">
      <el-radio-group v-model="searchRadio" size="mini" @change="onChange" style="width:100%;">
        <el-radio-button label="real" v-if="platform!=='outwater'">实时数据</el-radio-button>
        <el-radio-button label="minute">分钟数据</el-radio-button>
        <el-radio-button label="hour">小时数据</el-radio-button>
        <el-radio-button label="day">日数据</el-radio-button>
      </el-radio-group>
    </template>
    <template slot="sourceLeft">
      <SourceLeft @selectSource="selectSource" :tagHeadId="tagHeadId" @resetQuery="resetQuery"></SourceLeft>
    </template>
    <template slot="contain">
      <el-form :model="queryForm" ref="queryForm" :inline="true" class="serchFormComit">
        <!--      <el-form-item label="污染源" prop="sourceId">-->
        <!--        <SelectSource-->
        <!--          v-model="queryForm.sourceId"-->
        <!--          :data="sourceList"-->
        <!--          :isPlatName="platform"-->
        <!--          placeholder="请输入污染源"-->
        <!--          style="width:300px"-->
        <!--        ></SelectSource>-->
        <!--      </el-form-item>-->
        <SearchForm :formItem="formItem" :formData="queryForm"></SearchForm>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="warning" icon="el-icon-upload2" size="mini" @click="exportTables" v-if="tableData.length>0">
            导出数据
          </el-button>
        </el-form-item>
      </el-form>
    </template>
  </SearchTem>
</template>
<script>
import SearchTem from "./template";
import SelectSource from "@/components/SelectSource";
import SearchForm from "./search-form";
import {mapActions} from "vuex";
import {searchData, sourceList, pointSelectList, searchDataExport} from "@/api/search";
import {outletList} from "@/api/youyan";
import {outletList as qxoutletList} from "@/api/qixiu";
import {jyOutletSelect} from "@/api/jiayou";
import SourceLeft from "@/components/searchLeft";
import {task_statistics} from "@/config";

export default {
  components: {SearchTem, SelectSource, SearchForm, SourceLeft},
  data () {
    return {
      formTerm: {
        outwater: {
          real: [
            {
              type: "Select",
              prop: "checkPointId",
              label: "检测井",
              order: 1,
              list: []
            }
          ],
          minute: [
            {
              type: "Select",
              prop: "checkPointId",
              label: "检测井",
              order: 1,
              list: []
            }
          ],
          hour: [
            {
              type: "Select",
              prop: "checkPointId",
              label: "检测井",
              order: 1,
              list: []
            }
          ],
          day: [
            {
              type: "Select",
              prop: "checkPointId",
              label: "检测井",
              order: 1,
              list: []
            }
          ]
        },
        qixiu: {
          real: [
            {
              type: "Select",
              prop: "outletId",
              label: "排放口",
              order: 1,
              list: []
            }
          ],
          minute: [
            {
              type: "Select",
              prop: "outletId",
              label: "排放口",
              order: 1,
              list: []
            }
          ],
          hour: [
            {
              type: "Select",
              prop: "outletId",
              label: "排放口",
              order: 1,
              list: []
            }
          ],
          day: [
            {
              type: "Select",
              prop: "outletId",
              label: "排放口",
              order: 1,
              list: []
            }
          ]
        },
        jiayou: {
          real: [
            {
              type: "Select",
              prop: "outletId",
              label: "加油枪",
              order: 1,
              list: []
            }
          ],
          minute: [
            {
              type: "Select",
              prop: "outletId",
              label: "加油枪",
              order: 1,
              list: []
            }
          ],
          hour: [
            {
              type: "Select",
              prop: "outletId",
              label: "加油枪",
              order: 1,
              list: []
            }
          ],
          day: [
            {
              type: "Select",
              prop: "outletId",
              label: "加油枪",
              order: 1,
              list: []
            }
          ]
        },
        youyan: {
          real: [
            {
              type: "Select",
              prop: "outletId",
              label: "排放口",
              order: 1,
              list: []
            }
          ],
          minute: [
            {
              type: "Select",
              prop: "outletId",
              label: "排放口",
              order: 1,
              list: []
            }
          ],
          hour: [
            {
              type: "Select",
              prop: "outletId",
              label: "排放口",
              order: 1,
              list: []
            }
          ],
          day: [
            {
              type: "Select",
              prop: "outletId",
              label: "排放口",
              order: 1,
              list: []
            }
          ]
        }
      },
      formTermComit: {
        real: [
          {
            type: "Date",
            prop: "date",
            label: "选择日期",
            order: 5
          },
          {
            type: "Time",
            prop: "time",
            label: "选择时间",
            order: 6
          }
        ],
        minute: [
          {
            type: "Date",
            prop: "date",
            label: "选择日期",
            order: 5
          }
        ],
        hour: [
          {
            type: "Date",
            prop: "date",
            label: "选择日期",
            order: 5
          }
        ],
        day: [
          {
            type: "DateRange",
            prop: "startDate",
            label: "选择日期",
            order: 5
          }
        ]
      },
      platList: [
        "guocheng",
        "zaozhi",
        "lingxing",
        "lingxingp",
        "qixiu",
        "jinshu",
        "voc",
        "youyan",
        "jiayou"
      ],
      sourceList,
      queryForm: {
        sourceId: null,
        time: "00:00",
        checkPointId: null,
        date: null,
        startDate: null,
        outletId: null
      },
      tableData: [],
      cols: [],
      loading: false,
      searchRadio: "real",
      platform: "",
      formItem: [],
      page: {
        pageNum: 1,
        pageSize: 50
      },
      tagHeadId: null,
      filterData: {}
    };
  },
  watch: {
    "queryForm.sourceId": {
      handler (v) {
        if (v) {
          if (this.platform === "outwater") {
            this.queryForm.checkPointId = null;
            this.getPointList(v);
          } else if (this.platform === "youyan") {
            this.queryForm.outletId = null;
            this.getoutleList(v);
          } else if (this.platform === "qixiu") {
            this.queryForm.outletId = null;
            this.getqxoutleList(v);
          } else if (this.platform === "jiayou") {
            this.queryForm.outletId = null;
            this.getJYOutLet(v);
          } else {
            this.handleQuery();
          }
        }
      }
    }
  },
  created () {
    // this.platform = Cookies.get("system-name");
    let oRoute = this.$route.path;
    let oRouteArr = oRoute.split("/");
    this.platform = oRouteArr[1];
    task_statistics.forEach(i => {
      if (i.path === this.platform) {
        this.tagHeadId = i.id;
      }
    });
    this.concatForm();
    if (this.platform === "outwater") {
      this.searchRadio = "minute";
      this.formItem = this.formTermComit[this.searchRadio];
    } else {
      this.formItem = this.formTermComit.real;
    }

    //时间格式化
    const date = new Date();
    let oDate = this.parseTime(date).substr(0, 10);
    const hour = (date.getHours() - 1).toString().padStart(2, "0");
    this.queryForm.date = oDate;
    if (this.searchRadio === "real") {
      this.queryForm.time = `${hour}:00`;
    }
  },
  computed: {
    isSummary: function () {
      return this.platList.indexOf(this.platform) > -1 &&
      (this.searchRadio === "hour" || this.searchRadio === "day")
        ? true
        : false;
    }
  },
  methods: {
    ...mapActions("company", ["SaveSelectCompanyId"]),
    resetQuery () {
      this.queryForm = {
        sourceId: null,
        time: "00:00",
        checkPointId: null,
        date: null,
        startDate: null,
        outletId: null
      };
      const date = new Date();
      let oDate = this.parseTime(date).substr(0, 10);
      const hour = (date.getHours() - 1).toString().padStart(2, "0");
      this.queryForm.date = oDate;
      if (this.searchRadio === "real") {
        this.queryForm.time = `${hour}:00`;
      }
      this.tableData = [];
      this.cols = [];
    },
    getList (v = {}) {
      this.loading = true;
      let {platform, searchRadio} = this;
      searchData(v, platform, searchRadio).then((res) => {
        if (res.code === 200) {
          this.cols = res.data.cols;
          this.tableData = res.data.data;
        }
        this.loading = false;
      });
    },
    getJYOutLet (v) {
      jyOutletSelect(v).then(res => {
        let oArr = res.data;
        oArr.forEach((i) => {
          i.id = i.outletId;
          i.name = i.outletName;
        });
        Object.keys(this.formTermComit).forEach((e) => {
          this.formTermComit[e].forEach((v) => {
            if (v.prop === "outletId") {
              v.list = oArr;
            }
          });
        });
        this.formItem = this.formTermComit[this.searchRadio];
        if (res.data.length > 0) {
          this.queryForm.outletId = res.data[0].id;
          this.handleQuery();
        } else {
          this.queryForm.outletId = null;
          this.handleQuery();
        }
      });
    },
    getoutleList (v) {
      // let oVal = {
      //   sourceId: v
      // };
      outletList(v).then((res) => {
        let oArr = res.data;
        oArr.forEach((i) => {
          i.id = i.facilityId;
          i.name = i.facilityName;
        });

        Object.keys(this.formTermComit).forEach((e) => {
          this.formTermComit[e].forEach((v) => {
            if (v.prop === "outletId") {
              v.list = oArr;
            }
          });
        });
        this.formItem = this.formTermComit[this.searchRadio];
        if (res.data.length > 0) {
          this.queryForm.outletId = res.data[0].id;
          this.handleQuery();
        } else {
          this.queryForm.outletId = null;
          this.handleQuery();
        }
      });
    },
    getqxoutleList (v) {
      let oVal = {
        sourceId: v
      };
      qxoutletList(oVal, this.urlParameter(this.page)).then((res) => {
        let oArr = res.rows;
        oArr.forEach((i) => {
          i.id = i.outletId;
          i.name = i.outletName;
        });
        Object.keys(this.formTermComit).forEach((e) => {
          this.formTermComit[e].forEach((v) => {
            if (v.prop === "outletId") {
              v.list = oArr;
            }
          });
        });
        this.formItem = this.formTermComit[this.searchRadio];
        if (res.rows.length > 0) {
          this.queryForm.outletId = res.rows[0].id;
          this.handleQuery();
        } else {
          this.queryForm.outletId = null;
          this.handleQuery();
        }
      });
    },
    concatForm () {
      //判断是否有特殊表单
      if (this.formTerm[this.platform]) {
        Object.keys(this.formTermComit).forEach((e) => {
          if (this.formTerm[this.platform][e]) {
            this.formTermComit[e] = [
              ...new Set(
                this.formTermComit[e].concat(this.formTerm[this.platform][e])
              )
            ];
          }
        });
      }
      this.formTermComit = JSON.parse(
        JSON.stringify(this.fun_order(this.formTermComit))
      );
    },
    //获取检测井
    getPointList (v) {
      let oVal = {
        sourceId: v
      };
      pointSelectList(oVal).then((res) => {
        Object.keys(this.formTermComit).forEach((e) => {
          this.formTermComit[e].forEach((v) => {
            if (v.prop === "checkPointId") {
              v.list = res.rows;
            }
          });
        });
        this.formItem = this.formTermComit[this.searchRadio];
        if (res.rows.length > 0) {
          this.queryForm.checkPointId = res.rows[0].id;
          this.handleQuery();
        } else {
          this.tableData = [];
          this.msgError("该企业下无检测井,无法查询数据!");
        }

      });
    },
    getSummary (v = {}) {
      this.loading = true;
      let oSummary = this.searchRadio === "hour" ? "hoursummary" : "daysummary";
      let {platform} = this;
      searchData(v, platform, oSummary).then((res) => {
        if (res.code === 200) {
          this.cols = res.data.cols;
          this.tableData = res.data.data;
        }
        this.loading = false;
      });
    },
    handleQuery (v = true) {
      this.cols = [];
      this.tableData = [];
      if (this.queryForm.sourceId) {
        let oVal = {};
        if (this.searchRadio === "real") {
          let {sourceId, date, time} = this.queryForm;
          if (sourceId && date && time) {
            oVal = {
              sourceId: sourceId,
              startDate: date + " " + time
            };
          } else {
            this.msgError("查询条件不完整(污染源和时间不能为空)");
            return;
          }
        } else if (this.searchRadio === "day") {
          let {sourceId, startDate} = this.queryForm;
          if (sourceId && startDate) {
            oVal = {
              sourceId: sourceId,
              startDate: startDate[0],
              endDate: startDate[1]
            };
          } else {
            this.msgError("查询条件不完整(污染源和时间不能为空)");
            return;
          }
        } else {
          let {sourceId, date} = this.queryForm;
          if (sourceId && date) {
            oVal = {
              sourceId: sourceId,
              startDate: date
            };
          } else {
            this.msgError("查询条件不完整(污染源和时间不能为空)");
            return;
          }
        }
        if (this.platform === "outwater") {
          oVal.checkPointId = this.queryForm.checkPointId;
        }
        // if (this.platform === "youyan") {
        //   if (!this.queryForm.outletId) {
        //     this.msgError("请选择排放口");
        //     return;
        //   }
        // }
        if (new Set(["youyan", "qixiu", "jiayou"]).has(this.platform)) {
          oVal.outletId = this.queryForm.outletId;
        }
        this.filterData = oVal;
        v ? this.getList(oVal) : this.getSummary(oVal);
      } else {
        this.msgError("请选择需要查询污染源");
        return;
      }
    },
    onChange (v) {
      this.tableData = [];
      this.cols = [];
      this.searchRadio = v;
      if (v === "day") {
        let oDate = this.parseTime(new Date()).substr(0, 10);
        this.queryForm.startDate = [this.fun_date(-7), oDate];
      }
      this.formItem = this.formTermComit[v];
    },
    //排序
    fun_order (v) {
      Object.keys(v).forEach((e) => {
        v[e].sort(function (x, y) {
          return x.order > y.order ? 1 : -1;
        });
      });
      return v;
    },
    //获取7天前(num是正数表示之后的时间，num负数表示之前的时间，0表示今天)
    fun_date (num) {
      let date1 = new Date();
      let time1 =
        date1.getFullYear() +
        "-" +
        (date1.getMonth() + 1) +
        "-" +
        date1.getDate();
      let date2 = new Date(date1);
      date2.setDate(date1.getDate() + num);
      let oMonth = date2.getMonth() + 1;
      if (oMonth < 10) {
        oMonth = "0" + oMonth;
      }
      let time2 = date2.getFullYear() + "-" + oMonth + "-" + date2.getDate();
      return time2;
    },
    selectSource (v) {
      this.queryForm.sourceId = v.sourceId;
    },
    exportTables () {
      this.$confirm("是否导出数据?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          let {platform, searchRadio} = this;
          return searchDataExport(this.filterData, platform, searchRadio);
        })
        .then((response) => {
          this.download(response.msg);
        })
        .catch(function () {
        });
    }
  }
};
</script>
<style lang="scss">
.serchFormComit {
  margin: 5px 0;

  .el-form-item {
    margin-bottom: 0;
  }
}
</style>
