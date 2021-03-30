<template>
  <div class="data-search">
    <el-tabs v-model="active_sys">
      <el-tab-pane v-for="sys in systems" :key="sys.path" :label="sys.name" :name="sys.path"></el-tab-pane>
    </el-tabs>
    <el-radio-group
      v-if="data_types && data_types.length > 0"
      v-model="active_type"
      size="mini"
      @change="typeChange"
    >
      <el-radio-button v-for="dt in data_types" :key="dt.value" :label="dt.value">{{ dt.name }}</el-radio-button>
    </el-radio-group>

    <el-form :model="queryForm" ref="queryForm" :inline="true" class="search-form">
      <el-form-item v-for="item in items" :key="item.field + timestamp" :label="item.label">
        <remote-select
          v-if="item.type === 'select'"
          v-model="queryForm[item.field]"
          :placeholder="'请选择' + item.label"
          v-bind="item.data"
        />
      </el-form-item>
      <el-form-item v-for="item in date_items" :key="item.field + timestamp" :label="item.label">
        <el-time-select
          v-if="item.type === 'time'"
          placeholder="选择时间"
          v-model="queryForm[item.field]"
          style="width:120px"
          size="mini"
          :picker-options="{
                        start: '00:00',
                        step: '00:30',
                        end: '23:30'
                    }"
        ></el-time-select>
        <el-date-picker
          v-if="item.type === 'date'"
          type="date"
          size="mini"
          style="width:150px"
          v-model="queryForm[item.field]"
          :picker-options="pickerOptions"
          value-format="yyyy-MM-dd"
          placeholder="选择日期"
        ></el-date-picker>
        <el-date-picker
          v-if="item.type === 'daterange'"
          v-model="queryForm[item.field]"
          type="daterange"
          size="mini"
          style="width:280px"
          :picker-options="pickerOptions"
          value-format="yyyy-MM-dd"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <!-- <el-button
                    v-if="active_type > 1 && !system.no_summary"
                    type="primary"
                    icon="el-icon-search"
                    size="mini"
                    @click="handleSum"
        >搜索汇总</el-button>-->
      </el-form-item>
    </el-form>
    <el-table
      v-loading="loading"
      :data="data"
      row-key="date"
      style="width: 100%;flex:1;"
      class="searchStyle"
      ref="oTable"
      height="100%"
      size="mini"
      show-overflow-tooltip
      border
    >
      <template v-if="active_sys !== 'dgom'">
        <el-table-column
          :prop="i.dataIndex"
          :label="i.title"
          v-for="i in cols"
          :key="i.dataIndex"
          :width="i.width"
          :min-width="i.minWidth"
          align="center"
        >
          <template v-if="i.children">
            <el-table-column
              :prop="c.dataIndex"
              :label="c.title"
              v-for="c in i.children"
              :key="c.dataIndex"
              :width="c.minWidth ? null : (c.width || '150')"
              :min-width="c.minWidth"
            ></el-table-column>
          </template>
        </el-table-column>
      </template>
      <template v-else>
        <el-table-column prop="date" label="时间" min-width="140" align="center" size="mini"></el-table-column>
        <template v-for="i in cols">
          <!--            因子为100041的时候读取100040值,显示烟气排放量(m³)-->
          <el-table-column
            v-if="i.elementid===100041"
            label="烟气排放量(m³)"
            align="center"
            min-width="140"
            :key="i.elementid"
          >
            <template slot-scope="scope">
              <div v-html="returnHtml(scope.row,100041)"></div>
            </template>
          </el-table-column>
          <el-table-column
            v-else-if="i.elementid===100002"
            :label="i.elementdesc"
            align="center"
            min-width="140"
            :key="i.elementid"
          >
            <template slot-scope="scope">
              <div v-html="returnHtml(scope.row,100002)"></div>
            </template>
          </el-table-column>
          <!--                    有上限或限值-->
          <el-table-column v-else :label="i.elementdesc" align="center" :key="i.elementid">
            <!--            因子是ph的时候限值区间-->
            <el-table-column
              :label="'限值:' +i.minvalue+'-'+i.maxvalue"
              v-if="i.elementid===100035"
              align="center"
              :prop="'val_'+i.elementid"
              min-width="140"
            >
              <template slot-scope="scope">
                <div v-html="returnHtml(scope.row,i.elementid)"></div>
              </template>
            </el-table-column>
            <!--            有上限的因子-->
            <el-table-column
              v-else
              :label="'上限:' +i.maxvalue+i.unit"
              align="center"
              :prop="'val_'+i.elementid"
              min-width="140"
            >
              <template slot-scope="scope">
                <div v-html="returnHtml(scope.row,i.elementid)"></div>
              </template>
            </el-table-column>
          </el-table-column>
        </template>
      </template>
    </el-table>
  </div>
</template>

<script>
import {data_search_config} from "@/config";
import {getSourceTags} from "@/api/menu";
import {
  real,
  minute,
  hour,
  day,
  hour_summary,
  day_summary,
  dgomHearderReal,
  dgomHearderHM,
  dgomHearderDay
} from "@/api/data_search";
import remoteSelect from "@/views/components/remote-select";

export default {
  components: {remoteSelect},
  data () {
    return {
      loading: false,
      timestamp: null,
      active_sys: null,
      active_type: null,
      systems: [],
      system: null,
      data_types: [],
      data_type: null,
      date_items: [],
      queryForm: {},
      cols: [],
      data: [],
      pickerOptions: {
        disabledDate: (time) => time.getTime() > Date.now() - 8.64e6
      },
      search_apis: [real, minute, hour, day],
      summary_api: [null, null, hour_summary, day_summary]
    };
  },
  computed: {
    sys_name () {
      return this.$route.path.substr(this.$route.path.lastIndexOf("/") + 1);
    },
    items () {
      return this.system ? this.system.items : [];
    }
  },
  watch: {
    active_sys (v) {
      if (v && v !== "0") {
        this.timestamp = new Date().getMilliseconds();
        this.system = this.systems.find((f) => f.path === v);
        if (this.system) {
          this.data_types = this.system.data_types;
          // this.items = this.system.items;
          this.active_type = this.data_types[0].value;
          this.typeChange(this.active_type);
        }
      }
    }
  },
  created () {
    getSourceTags().then((res) => {

      this.systems = Object.keys(data_search_config)
        .filter((f) => res.data.some((s) => s === f))
        .map((m) => data_search_config[m]);

      this.active_sys = this.systems[0].path;
    });
  },
  methods: {
    returnHtml (v, elId) {
      let a = v["val_" + elId];
      if (elId === 100041 && !a) {
        elId = 100040;
        a = v["val_" + elId];
      }
      if (a && Object.keys(a).length > 0) {
        let oClass =
          v["val_" + elId].flag === -1 ? "" : "color_" + v["val_" + elId].flag;
        return `<span class="${oClass}">${v["val_" + elId].value}</span>`;
      } else {
        return `<span>${v["val_" + elId]}</span>`;
      }
    },
    typeChange (v) {
      this.queryForm = {};
      this.data_type = this.data_types.find((p) => p.value === v);
      this.date_items = this.data_type.items;
      this.date_items.forEach((e) =>
        this.$set(this.queryForm, e.field, e.value)
      );
      this.cols = [];
      this.data = [];
    },
    async getApiData (api) {
      const res = await api();
      if (res.data) {
        return res.data;
      }
      return [];
    },
    handleQuery () {
      // if (this.active_sys === "youyan") {
      //   if (!this.queryForm.outletId) {
      //     this.msgError("请选择需要查看的排放口 !");
      //     return;
      //   }
      // }
      if (this.active_sys === "dgom") {
        if (this.active_type === 0) {
          if (this.queryForm.checkpointid) {
            dgomHearderReal(this.queryForm).then(res => {
              if (res.code === 200) {
                this.cols = res.data;
              }
            });
            this.search_apis[this.active_type](this.active_sys, this.queryForm, false).then(res => {
              this.data = res.data;
            });
          } else {
            this.msgError("请选择排放口");
          }
        } else {
          if (this.queryForm.checkpointid) {
            if (this.active_type === 3) {
              dgomHearderDay(this.queryForm).then(res => {
                this.cols = res.data;
              });
            } else {
              dgomHearderHM(this.queryForm).then(res => {
                this.cols = res.data;
              });
            }
            let {startTime, date, time} = this.queryForm;
            if (startTime || (date && time)) {
              this.search_apis[this.active_type](this.active_sys, this.queryForm).then(res => {
                this.data = res.data;
              });
            } else {
              this.msgError("请完整填写查询条件");
            }
          } else {
            this.msgError("请选择排放口");
          }
        }
      } else {
        let {startTime, date, time} = this.queryForm;
        if (startTime || (date && time)) {
          this.search_apis[this.active_type](this.active_sys, this.queryForm).then(res => {
            if (res.code === 200) {
              this.cols = res.data.cols;
              this.data = res.data.data;
            }
          });
        } else {
          this.msgError("请完整填写查询条件");
        }
      }
    },
    async handleSum () {
      let {startTime} = this.queryForm;
      if (startTime) {
        const res = await this.summary_api[this.active_type](
          this.active_sys,
          this.queryForm
        );
        this.cols = res.data.cols;
        this.data = res.data.data;
        // this.getSumList(oVal);
      } else {
        this.msgError("请完整填写查询条件");
      }
    }
  }
};
</script>

<style lang="scss">
.data-search {
  width: 100%;
  height: calc(100% - 36px);
  overflow: hidden;
  padding: 10px;
  display: flex;
  flex-direction: column;

  .el-radio-group {
    margin-bottom: 15px;
  }
}

//.searchStyle {
//  .el-table__body-wrapper {
//    height: 100% !important;
//  }
//}
</style>
