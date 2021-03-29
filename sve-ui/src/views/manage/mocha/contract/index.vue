<template>
  <div class="task-wk">
    <!-- 搜索表单 -->
    <el-form :model="queryForm" ref="queryForm" :inline="true">
      <el-form-item label="企业名称" prop="sourceName">
        <el-input
          v-model="queryForm.sourceName"
          placeholder="请输入企业名称"
          clearable
          size="mini"
          style="width: 180px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="运维单位" prop="fullName">
        <el-input
          v-model="queryForm.fullName"
          placeholder="请输入运维单位"
          clearable
          size="mini"
          style="width: 180px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="时间区间" prop="oTime">
        <el-date-picker
          v-model="queryForm.oTime"
          type="daterange"
          value-format="yyyy-MM-dd"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <!-- 表格 -->
    <el-table
      v-loading="loading"
      :data="tableData"
      style="width: 100%;flex:1;"
      ref="oTable"
      height="100%"
      border
    >
      <el-table-column prop="sourceName" label="企业名称" show-overflow-tooltip width="220"></el-table-column>
      <el-table-column prop="fullName" label="运维单位" width="220" show-overflow-tooltip></el-table-column>
      <el-table-column prop="tagHeadName" label="监控任务" width="170"></el-table-column>
      <el-table-column prop="projectTitle" label="合同名称" width="240" show-overflow-tooltip></el-table-column>
      <el-table-column label="开始时间" width="90">
        <template slot-scope="scope">
          {{ scope.row.startDate.substr(0, 10) }}
        </template>
      </el-table-column>
      <el-table-column label="结束时间" width="90">
        <template slot-scope="scope">
          {{ scope.row.endDate.substr(0, 10) }}
        </template>
      </el-table-column>
      <el-table-column prop="memo" label="备注" min-width="180" show-overflow-tooltip></el-table-column>
<!--      <el-table-column label="因子数" width="80" align="center">-->
<!--        <template slot-scope="scope">-->
<!--          <el-link slot="reference" type="primary" :underline="false" style="font-size:12px;"-->
<!--                   @click="showElement(scope.row)"> {{ scope.row.elements.length }}-->
<!--          </el-link>-->
<!--        </template>-->
<!--      </el-table-column>-->
      <el-table-column label="附件" width="80" align="center">
        <template slot-scope="scope">
          <el-dropdown trigger="hover" @visible-change="fileClick($event,scope.row)" placement="bottom-start">
            <span class="el-dropdown-link" style="font-size:12px;">
              查看<i class="el-icon-arrow-down el-icon--right"></i>
            </span>
            <el-dropdown-menu slot="dropdown" style="width:400px;padding:10px" v-loading="fileLoading">
              <el-dropdown-item v-for="(item,index) in fileList" :key="item.Id">
                <el-link
                  type="primary"
                  :underline="false"
                  target="_blank"
                  :href="getlink(item.Id)"
                  style="font-size:12px;"
                >{{ index + 1 }}.{{ item.showName }}
                </el-link>
              </el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <pagination
      :total="total"
      :page.sync="page.pageNum"
      :limit.sync="page.pageSize"
      @pagination="getList(queryForm)"
    />
    <el-dialog
      title="运维因子列表"
      :visible.sync="elVisible"
      custom-class="el-contract-dialog"
      width="500px">
      <ul>
        <li v-for="(i,index) in elementList" :key="i.cp_elem_id">
          <span>{{ index + 1 }}.</span>
          <p :title="`【 ${ i.collect_name } 】 ${ i.element_desc } (${ i.element_code })`">【 {{ i.collect_name }} 】
            {{ i.element_desc }} ({{ i.element_code }})</p>
        </li>
      </ul>
    </el-dialog>
  </div>
</template>

<script>
import {operateUnitList} from "@/api/mocha";
import {fileList} from "@/api/upload";

export default {
  data () {
    return {
      isShow: false,
      loading: false,
      elVisible: false,
      fileLoading: false,
      queryForm: {
        sourceName: null,
        fullName: null,
        oTime: null
      },
      // 分页
      page: {
        pageNum: 1,
        pageSize: 30
      },
      total: 0,
      tableData: [],
      elementList: [],
      fileList: [],
    };
  },
  created () {
    this.getList();
  },
  methods: {
    getList (v = {}) {
      this.loading = true;
      operateUnitList(v, this.urlParameter(this.page)).then(res => {
        this.tableData = res.rows;
        this.total = res.total;
        this.loading = false;
      });
      this.$nextTick(() => {
        this.$refs.oTable.bodyWrapper.scrollTop = 0;
      });
    },
    handleQuery () {
      this.page.pageNum = 1;
      let oV = {
        sourceName: this.queryForm.sourceName,
        fullName: this.queryForm.fullName
      };
      if (this.queryForm.oTime) {
        oV.startDate = this.queryForm.oTime[0];
        oV.endDate = this.queryForm.oTime[1];
      }
      this.getList(oV);
    },
    resetQuery () {
      this.page.pageNum = 1;
      this.queryForm = {
        sourceName: null,
        fullName: null,
        oTime: null
      };
      this.getList({});
    },
    // showElement (v) {
    //   devopsEleList(v.opProjectId).then(res => {
    //     this.elementList = res.data;
    //     this.elVisible = true;
    //   });
    // },
    fileClick (i, v) {
      if (i) {
        this.fileLoading = true;
        let ref = {
          refId: v.opProjectId,
          refType: "pf_bas_devops"
        };
        fileList(ref).then((res) => {
          if (res.code === 200) {
            this.fileList = res.rows || res.data;
            this.fileLoading = false;
          }
        });
      } else {
        this.fileList = [];
        this.fileLoading = false;
      }
    },
    getlink (id) {
      return `${process.env.VUE_APP_BASE_API}/com/getfile/${id}`;
    }
  }
};
</script>

<style lang='scss' scoped>
.task-wk {
  .el-dropdown-link {
    cursor: pointer;
    color: #409EFF;
  }

  .el-icon-arrow-down {
    font-size: 12px;
  }

  /deep/ .el-contract-dialog {
    max-height: calc(100% - 12vh);
    height: auto;
    overflow: hidden;
    display: flex;
    flex-direction: column;

    .el-dialog__body {
      flex: 1;
      overflow-y: auto;
      padding: 10px;
    }

    ul {
      padding: 0;
      margin: 0;

      li {
        list-style-type: none;
        padding: 0;
        margin: 0;
        margin-bottom: 10px;
        display: flex;
        align-items: center;

        span {
          width: 36px;
          text-align: right;
        }

        p {
          flex: 1;
          margin: 0;
          margin-left: 5px;
          text-align: left;
          overflow: hidden;
          white-space: nowrap;
          text-overflow: ellipsis;
        }
      }
    }
  }
}

</style>
