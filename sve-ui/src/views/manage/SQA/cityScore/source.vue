<template>
  <div class="company-list-box" :style="{top:(isBoxShow? '74px':'110%' )}">
    <div class="currency-title-style">
      <span>{{ showTitle }} (共{{ total }})</span>
      <i class="el-icon-close" title="关闭"></i>
    </div>
    <div class="home-company-list" ref="homeCompany">
      <div class="company-con">
        <ul ref="scrollTopUl" v-loading="loading" element-loading-text="拼命加载中">
          <li
            v-for="i in sourceList"
            :key="i.id"
            :title="i.fullName"
          >
            <p class="sourceP1">{{ i.tagHeadName }}</p>
            <el-divider direction="vertical"></el-divider>
            <p style="width:50px;text-align: center">{{ i.townName }}</p>
            <el-divider direction="vertical"></el-divider>
            <p class="sourceP2">{{ i.fullName }}</p>
          </li>
        </ul>
        <!-- 分页 -->
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
    </div>
  </div>
</template>

<script>
import { taskAggregate } from "@/api/analysis";

export default {
  props: {
    isBoxShow: {
      type: Boolean,
      default: false
    },
    values: {
      type: Object,
      default: () => {}
    },
    showTitle: {
      type: String,
      default: "企业列表"
    }
  },
  data() {
    return {
      loading: false,
      sourceList: [],
      // 分页
      page: {
        pageNum: 1,
        pageSize: 30
      },
      total: 0,
      pagerCount: 5
    }
  },
  mounted() {
    window.addEventListener("click", this.closeList);
  },
  destroyed() {
    window.removeEventListener("click", this.closeList);
  },
  watch: {
    values: {
      handler(v) {
        if (v) {
          this.page.pageNum = 1;
          this.getList(v);
        }
      },
      immediate: true,
      deep: true
    }
  },
  methods: {
    getList(v = {}) {
      this.loading = true;
      taskAggregate(v, this.urlParameter(this.page)).then(res => {
        if (res.code === 200) {
          this.total = res.total;
          this.sourceList = res.rows;
        }
        this.loading = false;
      })
    },
    cChange(v) {
      this.page.pageNum = v;
      this.$refs.scrollTopUl.scrollTop = 0;
      this.getList(this.values);
    },
    closeList(e) {
      if (!this.$refs.homeCompany.contains(e.target) && this.isBoxShow && e.target.className !== "el-link--inner") {
        this.total = 0;
        this.sourceList = [];
        this.page.pageNum = 1;
        this.$emit("closeBox");
      }
    }
  }
}
</script>

<style lang="scss" scoped>
.company-list-box {
  width: 550px;
  height: calc(100% - 76px);
  background-color: rgba(0, 21, 41, 0.9);
  position: fixed;
  right: 0px;
  box-shadow: 0 2px 4px rgba(255, 255, 255, 0.1),
  0 0 6px rgba(255, 255, 255, 0.3);
  padding: 5px 0;
  transition: 1s;
  -webkit-transition: 1s;
  display: flex;
  flex-direction: column;
  overflow: hidden;
  z-index: 999
}

.currency-title-style {
  margin-left: 6px;
  background: #0c354a;
  background: linear-gradient(
      to right,
      rgba(22, 53, 74, 1),
      rgba(22, 53, 74, 0)
  );
  height: 30px;
  line-height: 30px;
  border-left: 4px solid rgba(17, 217, 255, 0.8);
  padding-left: 10px;
  color: #fff;
  font-size: 12px;
  letter-spacing: 1px;
  position: relative;

  span {
    font-size: 12px;
  }

  i {
    position: absolute;
    right: 12px;
    top: 10px;
    color: #ddd;

    &:hover {
      color: #fff;
      cursor: pointer;
    }
  }
}

.home-company-list {
  width: 100%;
  flex: 1;
  overflow-y: hidden;
  display: flex;
  flex-direction: column;

  h3 {
    color: #eee;
    font-size: 13px;
    padding-left: 15px;
    margin: 0;
    height: 25px;
    line-height: 30px;
    width: 100%;
    overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;
    letter-spacing: 1px;
    text-align: left;
  }

  .company-con {
    flex: 1;
    width: 100%;
    overflow: hidden;
    display: flex;
    flex-direction: column;

    /deep/ .el-loading-mask {
      background: rgba(0, 0, 0, 0.4);
    }
  }

  ul {
    padding: 5px;
    flex: 1;
    margin: 0;
    overflow-y: auto;

    li {
      color: #fff;
      font-size: 12px;
      width: 100%;
      height: 30px;
      line-height: 30px;
      padding-left: 5px;
      padding-right: 5px;
      display: flex;
      align-items: center;
      border: 1px solid rgba(255, 255, 255, 0.3);
      margin-bottom: 5px;
      // border-radius: 3px;
      box-shadow: 0 2px 2px rgba(255, 255, 255, 0.1),
      0 0 6px rgba(255, 255, 255, 0.2);

      p {
        margin: 0;
        padding: 0;
        overflow: hidden;
        white-space: nowrap;
        text-overflow: ellipsis;
      }

      p.sourceP1 {
        width: 135px;
        text-align: right;
      }

      p.sourceP2 {
        flex: 1;
        text-align: left;
      }

      &:hover {
        cursor: pointer;
        background: rgba(255, 255, 255, 0.3);
      }
    }
  }
}

.mapindex-page {
  /deep/ .el-pagination {
    text-align: center;
  }

  /deep/ .el-pagination__total {
    color: #eee;
  }

  /deep/ .el-pagination button:disabled {
    background-color: transparent !important;
    color: #eee;

    &:hover {
      color: #eee;
    }
  }

  /deep/ .el-pagination .btn-prev,
  /deep/ .el-pagination .btn-next {
    background-color: transparent !important;
    color: #eee;

    &:hover {
      color: #1890ff;
    }
  }

  /deep/ .el-pager {
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
</style>
