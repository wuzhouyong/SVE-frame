<template>
  <!--  企业异常情况统计-->
  <div class="task-wk">
    <el-tabs v-model="activeName" @tab-click="handleClick">
      <el-tab-pane :label="i.name" :name="i.key" v-for="i in systemList" :key="i.key"></el-tab-pane>
    </el-tabs>
    <!-- 搜索表单 -->
    <el-form :model="queryForm" ref="queryForm" :inline="true">
      <el-form-item label="企业名称" prop="fullName">
        <el-input
          v-model="queryForm.fullName"
          placeholder="请输入企业名称"
          clearable
          size="mini"
          style="width: 180px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="镇街" prop="townCode">
        <el-select
          v-model="queryForm.townCode"
          placeholder="镇街"
          clearable
          :style="{width: '160px'}"
        >
          <el-option
            v-for="(item, index) in townCodeOptions"
            :key="index"
            :label="item.regionName"
            :value="item.regionCode"
            :disabled="item.disabled"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="月份" prop="dtmon">
        <el-date-picker
          v-model="queryForm.dtmon"
          style="width: 180px"
          value-format="yyyy-MM"
          type="month"
          placeholder="选择月">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <el-table
      :data="tableData"
      v-loading="loading"
      element-loading-text="数据加载中,请稍后!"
      style="width: 100%;flex:1"
      size="mini"
      height="100%"
      border>
      <el-table-column
        prop="fullName"
        label="企业名称"
        show-overflow-tooltip
        min-width="220">
      </el-table-column>
      <el-table-column
        prop="socialCreditCode"
        label="税号"
        width="180">
      </el-table-column>
      <el-table-column
        prop="townName"
        label="镇街"
        width="90">
      </el-table-column>
      <el-table-column
        label="月份"
        width="80">
        <template slot-scope="scope">
          {{ scope.row.dtmon.substr(0, 7) }}
        </template>
      </el-table-column>
      <el-table-column
        label="一级" align="center">
        <el-table-column
          prop="totalcnt1"
          label="总异常" align="center">
        </el-table-column>
        <el-table-column
          prop="proccnt1"
          label="核减异常" align="center">
        </el-table-column>
        <el-table-column
          prop="factcnt1"
          label="实际异常" align="center">
        </el-table-column>
      </el-table-column>
      <el-table-column
        label="二级" align="center">
        <el-table-column
          prop="totalcnt2"
          label="总异常" align="center">
        </el-table-column>
        <el-table-column
          prop="proccnt2"
          label="核减异常" align="center">
        </el-table-column>
        <el-table-column
          prop="factcnt2"
          label="实际异常" align="center">
        </el-table-column>
      </el-table-column>
      <el-table-column
        label="三级" align="center">
        <el-table-column
          prop="totalcnt3"
          label="总异常" align="center">
        </el-table-column>
        <el-table-column
          prop="proccnt3"
          label="核减异常" align="center">
        </el-table-column>
        <el-table-column
          prop="factcnt3"
          label="实际异常" align="center">
        </el-table-column>
      </el-table-column>
      <el-table-column
        prop="othercnt"
        v-if="activeName==='outwater'"
        label="其他">
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <pagination
      :total="total"
      :page.sync="page.pageNum"
      :limit.sync="page.pageSize"
      @pagination="getList(queryForm)"
    />
  </div>
</template>

<script>
import {abSourceList} from '@/api/currency'
import {GetRegionPower} from "@/api/composite/source";
import {mapState} from "vuex";

export default {
  data() {
    return {
      queryForm: {
        fullName: null,
        townCode: null,
        dtmon: null,
      },
      tableData: [],
      activeName: 'outwater',
      // 分页
      page: {
        pageNum: 1,
        pageSize: 30
      },
      total: 0,
      townCodeOptions: [],
      loading: false,
      systemList: [
        {
          name: '排水户',
          key: 'outwater'
        },
        {
          name: '零星废水',
          key: 'lingxing'
        },
        {
          name: '四大流域',
          key: 'guocheng'
        },
        {
          name: '金属表面处理',
          key: 'jinshu'
        },
        {
          name: '造纸行业',
          key: 'zaozhi'
        },
        {
          name: 'VOCs',
          key: 'voc'
        },
        {
          name: '汽修行业',
          key: 'qixiu'
        },
        {
          name: '餐饮行业',
          key: 'youyan'
        },
        {
          name: '加油站',
          key: 'jiayou'
        }
      ]
    }
  },
  computed: {
    ...mapState("label", ["searchData"]),
    changeData() {
      const {activeName, searchData} = this;
      return {
        activeName,
        searchData
      }
    }
  },
  watch: {
    changeData: {
      handler() {
        this.getList();
      },
      immediate: true,
      deep: true
    }
  },
  created() {
    GetRegionPower().then((res) => {
      this.townCodeOptions = res.data;
    });
  },
  methods: {
    getList(v = {}) {
      this.loading = true;
      let oVal = Object.assign({labelIds: this.searchData}, v);
      abSourceList(this.activeName, oVal, this.urlParameter(this.page)).then(res => {
        if (res.code === 200) {
          this.tableData = res.rows;
          this.total = res.total;
        }
        this.loading = false;
      })
    },
    handleClick(tab) {
      this.activeName = tab.name;
    },
    handleQuery() {
      this.page.pageNum = 1;
      this.getList(this.queryForm);
    },
    resetQuery() {
      this.page.pageNum = 1;
      this.queryForm = {
        fullName: null,
        townCode: null,
        dtmon: null,
      }
      this.getList();
    }
  }
}
</script>

<style scoped>

</style>
