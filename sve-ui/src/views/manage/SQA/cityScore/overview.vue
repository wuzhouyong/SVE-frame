<template>
  <div class="task-overview">
    <div class="task-overview-left">
      <ul>
        <li
          v-for="i in oList"
          :key="i.code"
          @click="viewClick(i.tagHeadId)"
          :class="{'checked':checkTagList.indexOf(i.tagHeadId)>-1}"
        >
          <h4>
            <i class="el-icon-s-ticket"></i>
            <span>{{ i.name }} ({{ i.total }})</span>
          </h4>
          <div class="task-con">
            <div class="task-con-list" title="已完成">
              <i class="finish"></i>
              <span>{{ i.finish }}</span>
              <p>{{ getPencent(i.finish, i.total) }}</p>
            </div>
            <div class="task-con-list" title="未完成">
              <i class="notFinish"></i>
              <span>{{ i.notFinish }}</span>
              <p>{{ getPencent(i.notFinish, i.total) }}</p>
            </div>
          </div>
        </li>
      </ul>
    </div>
    <div class="task-overview-right">
      <div class="right-type">
        <el-radio-group v-model="showType" size="mini" @change="onChange">
          <el-radio-button label="0">总计</el-radio-button>
          <el-radio-button label="1">已完成</el-radio-button>
          <el-radio-button label="2">未完成</el-radio-button>
        </el-radio-group>
      </div>
      <div class="right-show-circle">
        <div class="task-count" v-if="chartData.rows.length>0">
          <p>{{ count }}</p>
          <span>任务总数</span>
        </div>
        <ve-ring
          :data="chartData"
          :colors="config.colors"
          :legend="config.legend"
          :extend="config.chartExtend"
          :settings="config.settings"
          :events="chartEvents"
          class="ringClass"
        ></ve-ring>
      </div>
    </div>
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
  </div>
</template>

<script>
import {
  taskCountMissionBlame,
  taskCountAccepted,
  taskCountAddition,
  taskCountAggregate,
  taskAggregate
} from "@/api/analysis";
import VeRing from "v-charts/lib/ring.common.js";
import {task_statistics} from "@/config";

export default {
  components: {VeRing},
  props: {
    chooseTownCode: {
      type: String | null,
      default: null
    }
  },
  watch: {
    chooseTownCode: {
      handler (v) {
        if (v) {
          let oV = {};
          if (this.checkTagList.length > 0) {
            oV = {
              tagHeadIds: this.checkTagList
            };
          } else {
            oV = {};
          }
          if (this.showType === "1") {
            this.chartData.rows = [];
            this.getAccept(oV);
          } else if (this.showType === "2") {
            this.chartData.rows = [];
            this.getMission(oV);
          } else if (this.showType === "0") {
            this.chartData.rows = [];
            this.getAggregate(oV);
          }
          this.getAddition();
        }

      },
      immediate: true
    }
  },
  data () {
    this.config = {
      colors: ["#EEDA84", "#FA6E86", "#1BE9BF", "#D680EF", "#FFCB8C", "#BAABD9", "#34B5F6", "#1DD8C5"],
      settings: {
        label: {
          show: true,
          formatter: (v) => {
            return (
              "{color1|" +
              v.name +
              "}\n\n{color2|" +
              v.value +
              "}{color2|(" +
              v.percent +
              "%)}"
            );
          },
          rich: {
            color1: {
              color: "#fff"
            },
            color2: {
              color: "#03ccff"
            }
          }
        }
      },
      chartExtend: {
        series: {
          radius: ["55%", "75%"],
          center: ["50%", "50%"]
        }
      },
      legend: {
        show: false,
        right: 0,
        top: 10,
        textStyle: {
          color: "#ffffff",
          fontSize: 11
        }
      }
    };
    this.chartEvents = {
      click: e => {
        let oName = e.name;
        this.showTitle = oName;
        this.typeNum.forEach((v, i) => {
          if (v === oName) {
            this.iIndex = i;
            let oV = {
              tagHeadIds: this.checkTagList.length > 0 ? this.checkTagList : null,
              listType: i
            };
            this.getList(oV);
          }
        });
        this.isBoxShow = true;
      }
    };
    return {
      typeNum: ["未完成", "已完成", "不安装", "已接入", "联网完成", "待验收", "已验收", "未开展", "已登录", "施工中", "暂缓", "申请暂缓", "申请不安装"],
      oList: [],
      showType: "0",
      count: "",
      isBoxShow: false,
      showTitle: null,
      loading: false,
      sourceList: [],
      iIndex: null,
      checkTagList: [],
      chartData: {
        columns: ["name", "num"],
        rows: []
      },
      oType: {
        notLoggedIn: "未开展",
        loggedIn: "已登录",
        construction: "施工中",
        defer: "暂缓",
        applyDefer: "申请暂缓",
        applyNotInstall: "申请不安装"
      },
      // 分页
      page: {
        pageNum: 1,
        pageSize: 30
      },
      total: 0,
      pagerCount: 5
    };
  },
  created () {
    this.getAggregate();
    this.getAddition();
  },
  mounted () {
    window.addEventListener("click", this.closeList);
  },
  destroyed () {
    window.removeEventListener("click", this.closeList);
  },
  methods: {
    getList (v = {}) {
      if (this.chooseTownCode) {
        v.townCode = this.chooseTownCode;
      }
      this.loading = true;
      taskAggregate(v, this.urlParameter(this.page)).then(res => {
        if (res.code === 200) {
          this.total = res.total;
          this.sourceList = res.rows;
        }
        this.loading = false;
      });
    },
    getAccept (v = {}) {
      if (this.chooseTownCode) {
        v.townCode = this.chooseTownCode;
      }
      taskCountAccepted(v).then(res => {
        if (res.code === 200) {
          this.count = res.data.total;
          let oRes = res.data;
          let oArr = [{
            name: "不安装",
            num: oRes.notInstall
          }, {
            name: "已接入",
            num: oRes.connected
          }, {
            name: "联网完成",
            num: oRes.networked
          }, {
            name: "待验收",
            num: oRes.accepting
          }, {
            name: "已验收",
            num: oRes.accepted
          }];
          this.chartData.rows = oArr;
        }
      });
    },
    getMission (v = {}) {
      if (this.chooseTownCode) {
        v.townCode = this.chooseTownCode;
      }
      taskCountMissionBlame(v).then(res => {
        if (res.code === 200) {
          this.count = res.data.total;
          let oRes = res.data;
          let oArr = [{
            name: "未开展",
            num: oRes.notLoggedIn
          }, {
            name: "已登录",
            num: oRes.loggedIn
          }, {
            name: "施工中",
            num: oRes.construction
          }, {
            name: "暂缓",
            num: oRes.defer
          }, {
            name: "申请暂缓",
            num: oRes.applyDefer
          }, {
            name: "申请不安装",
            num: oRes.applyNotInstall
          }];
          this.chartData.rows = oArr;
        }
      });
    },
    getAggregate (v = {}) {
      if (this.chooseTownCode) {
        v.townCode = this.chooseTownCode;
      }
      taskCountAggregate(v).then(res => {
        if (res.code === 200) {
          let oRes = res.data[0];
          this.count = oRes.total;
          let oArr = [{
            name: "已完成",
            num: oRes.finish
          }, {
            name: "未完成",
            num: oRes.notFinish
          }];
          this.chartData.rows = oArr;

        }
      });
    },
    getAddition () {
      let oV = {};
      if (this.chooseTownCode) {
        oV.townCode = this.chooseTownCode;
      }
      taskCountAddition(oV).then(res => {
        let oRes = res.data;
        oRes.forEach(i => {
          task_statistics.forEach(j => {
            if (i.tagHeadId === j.id) {
              i.name = j.name;
            }
          });
        });
        this.oList = oRes;
      });
    },
    getPencent (num, total) {
      num = parseFloat(num);
      total = parseFloat(total);
      if (isNaN(num) || isNaN(total)) {
        return "-";
      }
      return total <= 0
        ? "0%"
        : Math.round((num / total) * 10000) / 100.0 + "%";
    },
    onChange (v) {
      let oV = {};
      if (this.checkTagList.length > 0) {
        oV = {
          tagHeadIds: this.checkTagList
        };
      } else {
        oV = {};
      }
      if (v === "1") {
        this.chartData.rows = [];
        this.getAccept(oV);
      } else if (v === "2") {
        this.chartData.rows = [];
        this.getMission(oV);
      } else if (v === "0") {
        this.chartData.rows = [];
        this.getAggregate(oV);
      }
    },
    viewClick (v) {
      if (this.checkTagList.indexOf(v) > -1) {
        this.checkTagList.splice(
          this.checkTagList.findIndex((item) => item === v),
          1
        );
      } else {
        this.checkTagList.push(v);
      }
      let oV = {};
      if (this.checkTagList.length > 0) {
        oV = {
          tagHeadIds: this.checkTagList
        };
      } else {
        oV = {};
      }
      if (this.showType === "1") {
        this.chartData.rows = [];
        this.getAccept(oV);
      } else if (this.showType === "2") {
        this.chartData.rows = [];
        this.getMission(oV);
      } else if (this.showType === "0") {
        this.chartData.rows = [];
        this.getAggregate(oV);
      }
    },
    cChange (v) {
      this.page.pageNum = v;
      this.$refs.scrollTopUl.scrollTop = 0;
      let oV = {
        tagHeadIds: this.checkTagList.length > 0 ? this.checkTagList : null,
        listType: this.iIndex
      };
      this.getList(oV);
    },
    closeList (e) {
      if (!this.$refs.homeCompany.contains(e.target) && this.isBoxShow && e.target.tagName !== "CANVAS") {
        this.total = 0;
        this.sourceList = [];
        this.page.pageNum = 1;
        this.isBoxShow = false;
      }
    }
  }
};
</script>

<style lang="scss" scoped>
.task-overview {
  width: 100%;
  height: auto;
  display: flex;

  .task-overview-left {
    width: 60%;
    padding-bottom: 5px;

    .finish {
      background: #EEDA84;
    }

    .notFinish {
      background: #FA6E86;
    }

    ul {
      display: flex;
      flex-flow: row wrap;
      justify-content: flex-start;
      margin: 0;
      padding: 0;

      .checked {
        background-color: rgba(102, 153, 255, 0.6);

        h4 {
          span {
            color: #fff;
            font-weight: bold;
          }

          border-bottom: 1px solid #fff;
        }

        .task-con {
          .task-con-list {
            border-right: 1px solid #fff;

            p,
            span {
              color: #fff;
            }
          }
        }
      }

      li {
        margin-left: 1px;
        margin-right: 1px;
        margin-top: 5px;
        display: flex;
        flex-direction: column;
        box-sizing: border-box;
        flex: 0 0 33%;
        list-style-type: none;
        border: 1px solid #ebeef5;
        cursor: pointer;
        transition-property: background-color;
        transition-duration: 0.3s;
        transition-timing-function: ease;

        &:hover {
          background-color: rgba(102, 153, 255, 0.6);

          h4 {
            span {
              color: #fff;
              font-weight: bold;
            }

            border-bottom: 1px solid #fff;
          }

          .task-con {
            .task-con-list {
              border-right: 1px solid #fff;

              p,
              span {
                color: #fff;
              }
            }
          }
        }

        h4 {
          color: #e4d14e;
          margin: 0px;
          height: 30px;
          line-height: 30px;
          padding-left: 10px;
          font-size: 14px;
          font-weight: normal;
          border-bottom: 1px solid #ebeef5;
          display: flex;
          align-items: center;

          span {
            color: #e4d14e;
            font-size: 14px;
            font-weight: normal;
            padding-left: 5px;
          }
        }

        .task-con {
          display: flex;
          justify-content: space-between;
          margin: 5px 0;

          .task-con-list {
            flex: 0 0 50%;
            text-align: center;
            position: relative;
            margin-top: 5px;
            padding-top: 12px;
            border-right: 1px solid #ebeef5;

            &:last-child {
              border-right: none;
            }

            i {
              width: 25px;
              height: 8px;
              border-radius: 2px;
              position: absolute;
              right: 5px;
              top: 0px;
            }

            .nocon {
              background: #fa6e86;
            }

            .constr {
              background: #d680ef;
            }

            .comple {
              background: #eeda84;
            }

            .accept {
              background: #1dd8c5;
            }

            p {
              margin: 0;
              color: #00ccff;
              font-size: 15px;
              margin: 3px 0;
              font-weight: bold;
            }

            span {
              color: #00ccff;
              font-size: 15px;
              margin: 3px 0;
              font-weight: bold;
            }
          }
        }
      }
    }
  }

  .task-overview-right {
    border-left: 1px solid #ebeef5;
    margin-left: 5px;
    flex: 1;
    position: relative;
    padding: 5px;

    .right-show-circle {
      position: relative;

      .task-count {
        position: absolute;
        width: 100%;
        height: 100%;
        display: flex;
        align-items: center;
        justify-content: center;
        flex-direction: column;
        padding-top: 0px;

        p {
          font-size: 50px;
          color: #00ccff;
          margin: 0;
        }

        span {
          font-size: 22px;
          color: #cccccc;
          letter-spacing: 2px;
          text-align: center;
          line-height: 30px;
        }
      }
    }
  }
}

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
