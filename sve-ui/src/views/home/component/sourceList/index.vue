<template>
  <div class="mapindex-sourcelist">
    <div class="mapindex-search">
      <el-form :inline="true" :model="sourceForm">
        <el-input placeholder="请输入企业名称" v-model="sourceForm.sourceName" class="input-with-select" style="width:100%">
          <el-select
            v-model="sourceForm.townCode"
            slot="prepend"
            placeholder="请选择镇街"
          >
            <el-option
              v-for="(item, index) in townCodeOptions"
              :key="index"
              :label="item.regionName"
              :value="item.regionCode"
              :disabled="item.disabled"
            ></el-option>
          </el-select>
          <el-button slot="append" type="primary" @click="handleSearch" :loading="btnLoading">查询</el-button>
          <el-button slot="append" type="primary" @click="resetQuery" :loading="btnLoading">重置</el-button>
        </el-input>
      </el-form>
    </div>
    <div>
      <p
        style="width:100%;margin:3px 0;padding:0;color:#fff;text-align: center;font-size:12px;padding:5px 0">
        异常信息展示</p>
      <n-scroll-warn @ejectMsg="ejectMsg"></n-scroll-warn>
    </div>
    <div class="stautsType_home" style="margin-top:10px;">
      <div class="map-company-grade">
        <p class="bg-color-1" :class="constStatus===0?'bg-ground-1':''" @click="constChoose(0)">未开展</p>
        <p class="bg-color-2" :class="constStatus===1?'bg-ground-2':''" @click="constChoose(1)">施工中</p>
        <p class="bg-color-3" :class="constStatus===2?'bg-ground-3':''" @click="constChoose(2)">已接入</p>
        <p class="bg-color-0" :class="constStatus===6?'bg-ground-0':''" @click="constChoose(6)">已验收</p>
      </div>
      <!-- <p style="width:46px;height:24px;margin-left: 16px;margin-bottom:0px;margin-top:0px;"></p> -->
    </div>
    <div class="stautsType_home" style="margin:10px 0 6px;">
      <div class="map-company-grade">
        <p class="bg-color-1" :class="warnStatus===1?'bg-ground-1':''" @click="warnChoose(1)">一级异常</p>
        <p class="bg-color-2" :class="warnStatus===2?'bg-ground-2':''" @click="warnChoose(2)">二级异常</p>
        <p class="bg-color-3" :class="warnStatus===3?'bg-ground-3':''" @click="warnChoose(3)">三级异常</p>
        <p class="bg-color-4" :class="warnStatus===4?'bg-ground-4':''" @click="warnChoose(4)">四级异常</p>
        <p class="bg-color-0" :class="warnStatus===0?'bg-ground-0':''" @click="warnChoose(0)">无异常</p>
      </div>
      <!-- <p class="cz" @click="resetQuery">重置</p> -->
    </div>
    <div class="mapindex-table">
      <el-table
        :data="tableData"
        border
        height="100%"
        ref="oTable"
        v-loading="loading"
        element-loading-text="数据获取中."
        @row-click="rowClick"
      >
        <el-table-column prop="sourceName" label="企业名称" show-overflow-tooltip></el-table-column>
        <el-table-column prop="warnLevel" label="状态" width="70px" align="center">
          <template slot-scope="scope">
            <span v-if="scope.row.warnLevel===0" style="color:#63d59d">无异常</span>
            <span v-else-if="scope.row.warnLevel===1" style="color:#db1f35">一级异常</span>
            <span v-else-if="scope.row.warnLevel===2" style="color:#f63">二级异常</span>
            <span v-else-if="scope.row.warnLevel===3" style="color:#edc414">三级异常</span>
            <span v-else-if="scope.row.warnLevel===4" style="color:#48D1CC">四级异常</span>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div class="mapindex-page">
      <el-pagination
        :current-page.sync="page.pageNum"
        :page-size="page.pageSize"
        :pager-count="pagerCount"
        layout="total, prev, pager, next"
        :total="total"
        @current-change="cChange"
      ></el-pagination>
    </div>
  </div>
</template>
<script>
import {homeSourceList} from "@/api/home/index";
import {getRegion} from "@/api/composite/source";
import utils from "@/utils/ruoyi";
import nScrollWarn from "../scrollWarn";

export default {
  components: {nScrollWarn},
  props: ["checkArr", "regions"],
  data () {
    return {
      pagerCount: 5,
      // 分页
      page: {
        pageNum: 1,
        pageSize: 50,
      },
      total: 0,
      sourceForm: {
        sourceName: null,
        townCode: null,
        statuses: null,
        tagHeadIds: [],
        warnLevel: null,
      },
      tableData: [],
      loading: false,
      constructionStatus: ["未开展", "施工中", "施工完毕", "验收完毕"],
      townCodeOptions: [],
      warnStatus: -1,
      constStatus: -1,
      btnLoading: false,
      isTown: false,
    };
  },
  created () {
    getRegion(4419).then((res) => {
      this.townCodeOptions = res.data;
    });
  },
  watch: {
    checkArr: {
      handler (v) {
        this.sourceForm.tagHeadIds = v;
        this.getList(this.sourceForm);
      },
      immediate: true,
    },
    regions: {
      handler (v) {
        if (v) {
          let oTownCode = v.replace(new RegExp("'", "gm"), "");
          if (oTownCode !== "0") {
            this.sourceForm.townCode = oTownCode;
            this.isTown = true;
          } else {
            this.isTown = false;
          }
        }
      },
    },
  },
  methods: {
    getList (v = {}) {
      this.loading = true;
      v = utils.filterData(v);
      homeSourceList(v, this.urlParameter(this.page)).then((res) => {
        if (res.code === 200) {
          this.tableData = res.rows;
          this.total = res.total;
          this.$nextTick(() => {
            if (this.$refs.oTable.bodyWrapper) {
              this.$refs.oTable.bodyWrapper.scrollTop = 0;
            }
          });
          this.$emit("sendSourceList", res.rows);
        }
        this.loading = false;
      });
    },
    cChange (v) {
      this.page.pageNum = v;
      this.getList(this.sourceForm);
    },
    rowClick (r, c) {
      this.$emit("showDetails", r);
    },
    handleSearch () {
      this.btnLoading = true;
      this.page.pageNum = 1;
      this.getList(this.sourceForm);
      this.$emit("townCodeSave", this.sourceForm.townCode);
      setInterval(() => {
        this.btnLoading = false;
      }, 1000);
    },
    cStatus (v) {
      return this.constructionStatus[v.status];
    },
    warnChoose (v) {
      this.warnStatus = v;
      this.sourceForm.warnLevel = v;
      this.getList(this.sourceForm);
    },
    constChoose (v) {
      this.constStatus = v;
      this.sourceForm.statuses = [v];
      if (v === 2) {
        this.sourceForm.statuses = [2, 3, 4];
      }
      this.getList(this.sourceForm);
    },
    resetQuery () {
      this.page = {
        pageNum: 1,
        pageSize: 30,
      };
      this.sourceForm = {
        sourceName: null,
        townCode: null,
        statuses: null,
        tagHeadIds: this.checkArr,
      };
      this.warnStatus = -1;
      this.constStatus = -1;
      this.handleSearch();
    },
    ejectMsg (v) {
      homeSourceList({sourceId: v}).then(res => {
        let oRes = res.rows[0];
        this.$emit("showDetails", oRes);
      });
    }
  },
};
</script>
<style lang="scss">
$statusColor0: #63d59d;
$statusColor1: #db1f35;
$statusColor2: #f63;
$statusColor3: #edc414;
$statusColor4: #48D1CC;
$statusColor6: #1890ff;
.mapindex-sourcelist {
  padding: 5px;
  display: flex;
  flex-direction: column;
  overflow: hidden;
  width: 100%;
  height: 100%;

  .mapindex-table {
    margin: 5px 0;
    flex: 1;
    width: 100%;
    overflow: hidden;

    .el-loading-mask {
      background-color: rgba(0, 0, 0, 0.5);
    }

    .el-table {
      background-color: transparent !important;

      .el-table__header-wrapper th {
        background-color: transparent !important;
        color: #eee;
        height: 30px;
        padding: 0;
      }

      tr {
        background-color: transparent !important;
        color: #eee;

        &:hover {
          td {
            background-color: rgba(69, 76, 89, 0.6);
          }
        }
      }
    }
  }

  .mapindex-page {
    .el-pagination {
      text-align: center;
    }

    .el-pagination__total {
      color: #eee;
    }

    .el-pagination button:disabled {
      background-color: transparent !important;
      color: #eee;

      &:hover {
        color: #eee;
      }
    }

    .el-pagination .btn-prev,
    .el-pagination .btn-next {
      background-color: transparent !important;
      color: #eee;

      &:hover {
        color: #1890ff;
      }
    }

    .el-pager {
      li {
        background-color: transparent !important;
        color: #eee;

        &:hover {
          color: #1890ff;
        }

        &.active {
          color: #1890ff;
        }
      }
    }
  }

  .mapindex-search {
    .el-select .el-input {
      width: 130px;
    }

    .input-with-select .el-input-group__prepend {
      background-color: #fff;
    }

    .el-button {
      span {
        color: #666;
      }
    }
  }

  .el-form-item {
    margin-bottom: 0px;
    margin-right: 0px;
  }
}

.stautsType_home {
  display: flex;
  justify-content: space-around;
  align-items: center;

  .cz {
    background: #fff;
    border: 1px solid #dcdfe6;
    color: #606266;
    padding: 0px 10px;
    margin: 0;
    font-size: 12px;
    height: 24px;
    line-height: 24px;
    white-space: nowrap;
    cursor: pointer;
    text-align: center;
    box-sizing: border-box;
    outline: none;
    margin: 0;
    transition: 0.1s;
    font-weight: 400;
    border-radius: 3px;
    margin-left: 10px;
  }

  .map-company-grade {
    display: flex;
    margin: 0px auto;
    width: 100%;

    p {
      flex: 1;
      margin-right: 5px;
      margin-bottom: 0px;
      margin-top: 0px;
      height: 26px;
      line-height: 24px;
      font-size: 12px;
      text-align: center;
      opacity: 1;
      font-weight: bold;
      letter-spacing: 2px;
      border-radius: 4px;

      &:last-child {
        margin-right: 0;
      }
    }
  }

  .bg-color-0 {
    border: 1px solid $statusColor0;
    color: $statusColor0;

    &:hover {
      cursor: pointer;
      color: #fff;
      transition: all 0.3s;
      background-color: $statusColor0;
    }
  }

  .bg-color-1 {
    border: 1px solid $statusColor1;
    color: $statusColor1;

    &:hover {
      cursor: pointer;
      color: #fff;
      transition: all 0.3s;
      background-color: $statusColor1;
    }
  }

  .bg-color-2 {
    border: 1px solid $statusColor2;
    color: $statusColor2;

    &:hover {
      cursor: pointer;
      color: #fff;
      transition: all 0.3s;
      background-color: $statusColor2;
    }
  }

  .bg-color-3 {
    border: 1px solid $statusColor3;
    color: $statusColor3;

    &:hover {
      cursor: pointer;
      color: #fff;
      transition: all 0.3s;
      background-color: $statusColor3;
    }
  }

  .bg-color-4 {
    border: 1px solid $statusColor4;
    color: $statusColor4;

    &:hover {
      cursor: pointer;
      color: #fff;
      transition: all 0.3s;
      background-color: $statusColor4;
    }
  }

  .bg-color-6 {
    border: 1px solid $statusColor6;
    color: $statusColor6;

    &:hover {
      cursor: pointer;
      color: #fff;
      transition: all 0.3s;
      background-color: $statusColor6;
    }
  }

  .bg-ground-0 {
    background-color: $statusColor0;
    color: #fff;
  }

  .bg-ground-1 {
    background-color: $statusColor1;
    color: #fff;
  }

  .bg-ground-2 {
    background-color: $statusColor2;
    color: #fff;
  }

  .bg-ground-3 {
    background-color: $statusColor3;
    color: #fff;
  }

  .bg-ground-4 {
    background-color: $statusColor4;
    color: #fff;
  }

  .bg-ground-6 {
    background-color: $statusColor6;
    color: #fff;
  }
}
</style>
