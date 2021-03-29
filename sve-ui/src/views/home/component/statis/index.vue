<template>
  <div class="statisticsCon">
    <h3>
      监控统计
<!--      <i class="el-icon-close" @click="closeDiv"/>-->
    </h3>
    <div class="home-statis-con" v-loading="loading" element-loading-text="数据加载中">
      <ul>
<!--        <li class="pCalendar-wk">-->
<!--          <p class="pCalendar" style="letter-spacing: 1px"><i class="el-icon-s-grid" style="padding-right:5px;"></i>数据正常率日历</p>-->
<!--          <i class="el-icon-right"></i>-->
<!--        </li>-->
        <li>
          <div class="statis-title">
            <p class="statis-title-p1">企业总数</p>
            <p class="statis-title-p2">{{ stDataTotal }}</p>
            <p class="statis-title-p3"></p>
          </div>
          <div v-for="i in stData" :key="i.type">
            <div class="statis-list">
              <p class="statis-title-p1">{{ i.name }}</p>
              <p class="statis-title-p2">{{ i.total }}</p>
              <p class="statis-title-p3">{{ i.rate }}%</p>
            </div>
          </div>
        </li>
        <li>
          <div>
            <div class="statis-list">
              <p class="statis-title-p1">异常总数</p>
              <p class="statis-title-p2">{{ warnTotal }}</p>
              <p class="statis-title-p3"></p>
            </div>
          </div>
        </li>
        <li>
          <div class="block">
            <el-date-picker
              style="width:100%"
              size="mini"
              v-model="value2"
              type="daterange"
              align="right"
              unlink-panels
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
              value-format="yyyy-MM-dd"
              @change="onChange"
              :picker-options="pickerOptions">
            </el-date-picker>
          </div>
        </li>
        <li>
          <div class="statis-title statis-one">
            <p class="statis-title-p1">一级异常</p>
            <p class="statis-title-p2">{{ warnOne.total }}</p>
            <p class="statis-title-p3"></p>
          </div>
          <div class="statis-list" v-for="i in warnOne.list" :key="i.type">
            <p class="statis-title-p1" :title="i.name">{{ i.name }}</p>
            <p class="statis-title-p2">{{ i.total }}</p>
            <p class="statis-title-p3">{{ i.rate }}%</p>
          </div>
        </li>
        <li>
          <div class="statis-title statis-two">
            <p class="statis-title-p1">二级异常</p>
            <p class="statis-title-p2">{{ warnTwo.total }}</p>
            <p class="statis-title-p3"></p>
          </div>
          <div class="statis-list" v-for="i in warnTwo.list" :key="i.type">
            <p class="statis-title-p1" :title="i.name">{{ i.name }}</p>
            <p class="statis-title-p2">{{ i.total }}</p>
            <p class="statis-title-p3">{{ i.rate }}%</p>
          </div>
        </li>
        <li>
          <div class="statis-title statis-three">
            <p class="statis-title-p1">三级异常</p>
            <p class="statis-title-p2">{{ warnThree.total }}</p>
            <p class="statis-title-p3"></p>
          </div>
          <div class="statis-list" v-for="i in warnThree.list" :key="i.type">
            <p class="statis-title-p1" :title="i.type">{{ i.name }}</p>
            <p class="statis-title-p2">{{ i.total }}</p>
            <p class="statis-title-p3">{{ i.rate }}%</p>
          </div>
        </li>
        <li>
          <div class="statis-title statis-four">
            <p class="statis-title-p1">四级异常</p>
            <p class="statis-title-p2">{{ warnFour.total }}</p>
            <p class="statis-title-p3"></p>
          </div>
          <div class="statis-list" v-for="i in warnFour.list" :key="i.type">
            <p class="statis-title-p1" :title="i.type">{{ i.name }}</p>
            <p class="statis-title-p2">{{ i.total }}</p>
            <p class="statis-title-p3">{{ i.rate }}%</p>
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>
<script>
import {getStatis, getStatisWarn} from "@/api/home/index";

export default {
  props: {
    tagHeadIds: {
      type: Array
    },
    townCode: {
      type: String
    }
  },
  data () {
    return {
      stData: [],
      stDataTotal: 0,
      warnOne: {
        total: 0,
        list: []
      },
      warnTwo: {
        total: 0,
        list: []
      },
      warnThree: {
        total: 0,
        list: []
      },
      warnFour: {
        total: 0,
        list: []
      },
      loading: true,
      pickerOptions: {
        shortcuts: [{
          text: "最近一周",
          onClick (picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
            picker.$emit("pick", [start, end]);
          }
        }, {
          text: "最近一个月",
          onClick (picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
            picker.$emit("pick", [start, end]);
          }
        }, {
          text: "最近三个月",
          onClick (picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
            picker.$emit("pick", [start, end]);
          }
        }]
      },
      value2: "",
      warnTotal: 0,
      queryForm: {
        startDate: null,
        endDate: null
      }
    };
  },
  computed: {
    isChange () {
      const {townCode, tagHeadIds} = this;
      return {
        townCode,
        tagHeadIds
      };
    },
  },
  watch: {
    isChange: {
      handler (v) {
        this.getData(v);
        this.getWarn(v);
      },
    }
  },
  methods: {
    getWarn (v = {}) {
      this.loading = true;
      let oVal = {};
      if (this.queryForm.startDate && this.queryForm.endDate) {
        oVal.startDate = this.queryForm.startDate;
        oVal.endDate = this.queryForm.endDate;
      }
      if (v.tagHeadIds.length > 0) {
        oVal.tagHeadIds = v.tagHeadIds;
      }
      if (v.townCode) {
        oVal.townCode = v.townCode;
      }
      getStatisWarn(oVal).then(res => {
        if (res.code === 200) {
          let nOb = {total: 0, list: []};
          this.warnOne = res.data[1] ? res.data[1] : nOb;
          this.warnTwo = res.data[2] ? res.data[2] : nOb;
          this.warnThree = res.data[3] ? res.data[3] : nOb;
          this.warnFour = res.data[4] ? res.data[4] : nOb;
          this.warnTotal = res.data.total;
        }
        this.loading = false;
      });
    },
    getData (v = {}) {
      this.loading = true;
      let oVal = {};
      if (v.tagHeadIds.length > 0) {
        oVal.tagHeadIds = v.tagHeadIds.join(",");
      }
      if (v.townCode) {
        oVal.townCode = v.townCode;
      }
      getStatis(oVal).then((res) => {
        if (res.code === 200) {
          this.stData = res.data.list;
          this.stDataTotal = res.data.total;
        }
      });
      this.loading = false;
    },
    closeDiv () {
      this.$emit("closediv");
    },
    onChange (v) {
      if (v) {
        this.queryForm.startDate = v[0];
        this.queryForm.endDate = v[1];
      } else {
        this.queryForm.startDate = null;
        this.queryForm.endDate = null;
      }
      this.getWarn(this.isChange);
    }
  }
};
</script>
<style lang="scss">
.statisticsCon {
  width: 100%;
  height: 100%;
  position: relative;
  display: flex;
  flex-direction: column;
  /*定义滚动条高宽及背景 高宽分别对应横竖滚动条的尺寸*/
  ::-webkit-scrollbar {
    width: 0px; /*滚动条宽度*/
    height: 0px; /*滚动条高度*/
  }

  /*定义滚动条轨道 内阴影+圆角*/
  ::-webkit-scrollbar-track {
    -webkit-box-shadow: inset 0 0 2px rgba(0, 0, 0, 0.2);
    border-radius: 4px; /*滚动条的背景区域的圆角*/
    background-color: #ededed; /*滚动条的背景颜色*/
  }

  /*定义滑块 内阴影+圆角*/
  ::-webkit-scrollbar-thumb {
    border-radius: 4px; /*滚动条的圆角*/
    -webkit-box-shadow: inset 0 0 2px rgba(0, 0, 0, .2);
    background-color: #535353; /*滚动条的背景颜色*/
  }

  .el-loading-mask {
    background-color: rgba(0, 0, 0, 0.4);
  }

  h3 {
    height: 30px;
    width: 100%;
    line-height: 30px;
    text-align: center;
    color: #fff;
    letter-spacing: 2px;
    border-bottom: 1px solid #797979;
    margin: 0;
    font-size: 13px;
    position: relative;

    i {
      color: rgba(255, 255, 255, 0.7);
      position: absolute;
      right: 8px;
      top: 8px;

      &:hover {
        color: rgba(255, 255, 255, 1);
        cursor: pointer;
      }
    }
  }

  .home-statis-con {
    width: 100%;
    flex: 1;
    overflow: hidden;
    padding: 5px;

    ul {
      width: 100%;
      height: 100%;
      display: flex;
      flex-direction: column;
      margin-bottom: 0;
      padding: 0;
      overflow-y: auto;
      overflow-x: hidden;
      margin: 0;

      li {
        list-style-type: none;
        width: 100%;
        height: auto;
        background-color: rgba(69, 76, 89, 0.2);
        margin-bottom: 5px;
        // border-radius: 5px;
        border: 1px solid #797979;
        padding: 2px;
        position: relative;

        &.pCalendar-wk {
          display: flex;
          justify-content: space-between;
          color: #fff;
          font-size: 12px;
          align-items: center;
          padding: 2px 5px;
          cursor: pointer;

          &:hover {
            background-color: rgba(69, 76, 89, 0.6);
            color: #EDC414;
          }

          p.pCalendar {
            margin: 0;
            text-align: center;
            padding: 3px 0;
          }
        }


        .statis-one {
          background-color: #db1f35;
        }

        .statis-two {
          background-color: #f63;
        }

        .statis-three {
          background-color: #edc414;
        }

        .statis-four {
          background-color: rgb(72, 209, 204);
        }

        .statis-title {
          padding: 3px 0;

          p {
            color: #fff;
            margin: 4px 0px;
          }
        }

        .statis-list {
          p {
            margin: 4px 0px;
          }
        }

        .statis-title,
        .statis-list {
          display: flex;
          font-weight: bold;
          font-size: 12px;

          p {
            text-align: center;
          }

          .statis-title-p1 {
            text-align: center;
            flex: 1;
            overflow: hidden;
            white-space: nowrap;
            text-overflow: ellipsis;
          }

          .statis-title-p2 {
            width: 60px;
          }

          .statis-title-p3 {
            width: 60px;
          }
        }

        .statis-list {
          margin: 2px 0;
          color: #fff;
          font-weight: normal;
        }
      }

      li:last-child {
        margin-bottom: 0px;
      }
    }
  }
}
</style>
