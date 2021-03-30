<template>
  <div class="warn">
    <el-tabs v-model="active_sys">
      <el-tab-pane
        v-for="sys in systems"
        :key="sys.path"
        :label="sys.name"
        :name="sys.path"
      ></el-tab-pane>
    </el-tabs>

    <el-form
      :model="queryForm"
      ref="queryForm"
      :inline="true"
      class="search-form"
    >
      <el-form-item
        v-for="item in items"
        :key="item.field + timestamp"
        :label="item.label"
      >
        <el-input
          v-if="item.type === 'input'"
          v-model="queryForm[item.field]"
          :placeholder="'请选择' + item.label"
          size="mini"
          style="width: 180px"
          @keyup.enter.native="handleQuery"
        />
        <remote-select
          v-if="item.type === 'select'"
          v-model="queryForm[item.field]"
          :placeholder="'请选择' + item.label"
          v-bind="item.data"
        />
        <el-date-picker
          v-if="item.type === 'daterange'"
          v-model="queryForm[item.field]"
          type="daterange"
          size="mini"
          style="width: 220px"
          value-format="yyyy-MM-dd"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
        >搜索
        </el-button
        >
      </el-form-item>
    </el-form>
    <el-table
      v-loading="loading"
      :data="data"
      row-key="date"
      style="width: 100%; flex: 1"
      ref="oTable"
      height="100%"
      size="mini"
      border
    >
      <el-table-column
        :prop="i.dataIndex"
        :label="i.title"
        :width="i.width"
        v-for="i in cols"
        :key="i.dataIndex"
      ></el-table-column>
    </el-table>
    <pagination
      :total="total"
      :page.sync="page.pageNum"
      :limit.sync="page.pageSize"
      @pagination="getList(queryForm)"
    />
  </div>
</template>

<script>
import {warnLists} from "@/api/warn";
import {warn_config} from "@/config";
import {getSourceTags} from "@/api/menu";
import remoteSelect from "@/views/components/remote-select";

export default {
  components: {remoteSelect},
  data () {
    return {
      loading: false,
      timestamp: null,
      active_sys: null,
      systems: [],
      system: null,
      queryForm: {},
      data: [],
      total: 0,
      page: {
        pageNum: 1,
        pageSize: 20,
      },
    };
  },
  computed: {
    sys_name () {
      return this.$route.path.substr(this.$route.path.lastIndexOf("/") + 1);
    },
    items () {
      return this.system ? this.system.items : [];
    },
    cols () {
      return this.system ? this.system.cols : [];
    },
  },
  watch: {
    active_sys (v) {
      if (v && v !== "0") {
        this.timestamp = new Date().getMilliseconds();
        this.system = this.systems.find((f) => f.path === v);
        this.queryForm = {};
        this.data = [];
        this.getList();
      }
    },
  },
  created () {
    getSourceTags().then((res) => {
      this.systems = Object.keys(warn_config)
        .filter((f) => res.data.some((s) => s === f))
        .map((m) => warn_config[m]);
      this.systems = this.systems.filter(i => i.path !== "yitihua");
      this.active_sys = this.systems[0].path;
      this.getList();

    });

  },
  methods: {
    getList (v = {}) {
      warnLists(this.active_sys, v, this.urlParameter(this.page)).then((res) => {
        if (res.code === 200) {
          this.data = res.rows || res.data;
          this.total = res.total;
        }
      });
      this.loading = false;
    },
    handleQuery () {
      let {startTime} = this.queryForm;
      let query = Object.assign({}, this.queryForm, this.dateRange);
      if (startTime) {
        let oVal = {
          startDate: startTime[0],
          endDate: startTime[1],
        };
        query = Object.assign({}, query, oVal);
      }
      delete query.startTime;
      this.getList(query);
    },
  },
};
</script>

<style lang="scss">
.warn {
  width: 100%;
  height: calc(100% - 36px);
  overflow: hidden;
  padding: 10px;
  display: flex;
  flex-direction: column;
  box-sizing: border-box;
  overflow: hidden;
}
</style>
