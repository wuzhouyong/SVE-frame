<template>
  <div class="mapindex-style">
    <div class="gls_wk" ref="companyStyle">
      <!--      <div class="gls_header">-->
      <!--        <div class="gls_operation">-->
      <!--          <div class="gls_operation_btn">-->
      <!--            <router-link :to="$store.state.curos.path">-->
      <!--              <i class="el-icon-s-platform"></i>-->
      <!--              <span>返回平台</span>-->
      <!--            </router-link>-->
      <!--          </div>-->
      <!--        </div>-->
      <!--        <div class="gls_shadow">-->
      <!--          <div class="gls_bg">-->
      <!--            <h1>东莞市在线监控平台</h1>-->
      <!--          </div>-->
      <!--        </div>-->
      <!--      </div>-->
      <!-- 地图 -->
      <div class="mapContain">
        <MapChunk
          :sourceList="sourceList"
          :companyDetails="companyDetails"
          @cKey="showDetails"
          ref="mapTem"
        ></MapChunk>
      </div>
      <!-- 工具栏 -->
      <div class="toolbar">
        <span
          :title="isStatis?'关闭监控统计':'打开监控统计'"
          @click="()=>{isStatis = !isStatis;pollutionCountWidth();}"
        >
          <i class="el-icon-picture-outline"></i>
        </span>
        <p>在线监控污染源</p>
        <span :title="isCompany?'收起企业列表':'展开企业列表'" @click="()=>isCompany = !isCompany">
          <i :class="isCompany?'el-icon-arrow-up':'el-icon-arrow-down'"></i>
        </span>
      </div>
      <!-- 企业列表 -->
      <div class="home-company-list" :style="{top:(isCompany? '95px':'110%' )}">
        <SourceChunk
          :checkArr="checkArrs"
          :regions="regions"
          @showDetails="showDetails"
          @sendSourceList="sendSourceList"
        ></SourceChunk>
      </div>
      <!-- 平台选择 -->
      <transition name="el-zoom-in-top">
        <div
          class="home-platform-change"
          v-show="isPlatform"
          :style="{width:pollutionW,left:(isStatis?'302px':'2px')}"
        >
          <el-checkbox
            :indeterminate="isIndeterminate"
            v-model="checkAll"
            @change="handleCheckAllChange"
            class="allCheck"
          >全选
          </el-checkbox>
          <el-checkbox-group
            v-model="checkArrs"
            @change="handleCheckedCitiesChange"
            class="checkStyle"
          >
            <el-checkbox v-for="i in cities" :label="i.id" :key="i.id">{{ i.name }}</el-checkbox>
          </el-checkbox-group>
        </div>
      </transition>
      <!-- 企业详情 -->
      <div
        class="home-company-details"
        :style="{width:pollutionW,top:(!isPlatform? '65px':'110%' ),left:(isStatis?'302px':'2px')}"
      >
        <div class="mapPollutionHead">
          <p>{{ sourceDetails.sourceName }}</p>
          <i class="el-icon-close" title="关闭详情" @click="detailsClose"/>
        </div>
        <div class="mapPollutionWk">
          <transition name="el-fade-in-linear">
            <DetailsChunk :key="nKEY" :sData="sourceDetails" :oId="sourceDetails.sourceId"></DetailsChunk>
          </transition>
        </div>
      </div>
      <!-- 统计 -->
      <div class="home-company-census" :style="{top:(isStatis? '65px':'110%' )}">
        <StatisChunk @closediv="closediv" :checkArr="checkArrs"></StatisChunk>
      </div>
    </div>
  </div>
</template>
<script>
import MapChunk from "./component/map";
import SourceChunk from "./component/sourceList";
import StatisChunk from "./component/statis";
import DetailsChunk from "./component/details/index";
import nScrollWarn from "./component/scrollWarn";
import utils from "@/utils/ruoyi";
import {getSourceList, getTagList} from "@/api/composite/source";
import {task_statistics} from "@/config";

export default {
  name: "MapIndex",
  components: {MapChunk, SourceChunk, StatisChunk, DetailsChunk, nScrollWarn},
  data () {
    return {
      sourceList: [],
      companyDetails: [],
      pollutionW: "",
      isPlatform: true,
      isStatis: true,
      isCompany: true,
      //全选
      checkAll: true,
      checkedCities: [],
      cities: [],
      isIndeterminate: false,
      checkArrs: [],
      //查询
      queryForm: {
        pageNum: 1,
        pageSize: 20,
        fullName: undefined,
        tagHeadId: undefined,
        townCode: undefined,
        industryCode: undefined
      },
      //企业详情
      sourceDetails: {},
      // user: {},
      //是否镇街
      regions: "",
      nKEY: ""
    };
  },
  created () {
    this.pollutionCountWidth();
    this.getPlatform();
    // this.getUser();
  },
  mounted () {
    //浏览器宽度改变重新计算污染源详情宽度
    window.onresize = () => {
      this.pollutionCountWidth();
    };
  },
  methods: {
    //平台数据
    getPlatform () {
      // this.cities = task_statistics;
      // let checkArr = [];
      // this.cities.forEach((v) => {
      //   checkArr.push(v.id);
      // });
      // this.checkArrs = checkArr;

      getTagList().then((res) => {
        if (res.code === 200) {
          let oArr = res.rows;
          this.cities = task_statistics.filter((p) =>
            oArr.some((s) => s.tagHeadId === p.id)
          );
          let checkArr = [];
          this.cities.forEach((v) => {
            checkArr.push(v.id);
          });
          this.checkArrs = checkArr;
        }
      });
    },
    // getSourceTagList() {
    //   getSourcetagList().then(res => {
    //     if (res.code === 200) {
    //       let oArr = res.data;

    //       this.cities = platform_list.filter(p =>
    //         oArr.some(s => s.id === p.id)
    //       );
    //       let checkArr = [];
    //       this.cities.forEach((v, i, a) => {
    //         checkArr.push(v.id);
    //       });
    //       this.checkedCities = checkArr;
    //       this.checkArrs = this.checkedCities;
    //     }
    //   });
    // },
    //企业列表
    getList (v = {}) {
      this.loading = true;
      v = utils.filterData(v);
      getSourceList(this.urlParameter(v)).then((res) => {
        if (res.code === 200) {
          this.tableData = res.rows;
          this.total = res.total;
          this.loading = false;
        }
      });
    },
    //计算污染源详情宽度
    pollutionCountWidth () {
      let oWidth = window.innerWidth;
      if (this.isStatis) {
        this.pollutionW = oWidth - 400 - 300 - 6 + "px";
      } else {
        this.pollutionW = oWidth - 400 - 6 + "px";
      }
    },
    //平台选择
    handleCheckAllChange (val) {
      if (val) {
        let oArr = this.cities;
        let checkArr = [];
        oArr.forEach((v, i, a) => {
          checkArr.push(v.id);
        });
        this.checkedCities = checkArr;
      } else {
        this.checkedCities = [];
      }
      this.checkArrs = this.checkedCities;
      this.isIndeterminate = false;
    },
    handleCheckedCitiesChange (value) {
      this.checkArrs = value;
      let checkedCount = value.length;
      this.checkAll = checkedCount === this.cities.length;
      this.isIndeterminate =
        checkedCount > 0 && checkedCount < this.cities.length;
    },
    //地图展示企业列表
    sendSourceList (v) {
      this.isPlatform = true;
      this.sourceList = v;
    },
    //点击显示企业详情
    showDetails (v) {
      this.sourceDetails = v;
      this.nKEY = Date.parse(new Date());
      this.isPlatform = false;
    },
    //关闭详情
    detailsClose () {
      this.isPlatform = true;
    },
    //退出登录
    // async logout() {
    //   this.$confirm("确定注销并退出系统吗？", "提示", {
    //     confirmButtonText: "确定",
    //     cancelButtonText: "取消",
    //     type: "warning"
    //   }).then(() => {
    //     this.$store.dispatch("LogOut").then(() => {
    //       Cookies.remove("system-key");
    //       location.reload();
    //     });
    //   });
    // },
    //关闭统计
    closediv () {
      this.isStatis = false;
      this.pollutionCountWidth();
    }
  }
};
</script>
<style lang="scss">
.mapindex-style {
  width: 100%;
  height: 100%;

  .gls_wk {
    width: 100%;
    height: 100%;
    position: relative;
    //头部
    .gls_header {
      width: 100%;
      height: 60px;
      position: absolute;
      right: 0px;
      top: 0px;
      background: rgba(23, 32, 47, 0.8);
      z-index: 90;

      .gls_operation {
        position: absolute;
        right: 14px;
        top: 24px;
        display: flex;

        .gls_operation_btn {
          margin-right: 20px;

          &:hover {
            cursor: pointer;

            i {
              color: #f3961c;
            }

            span {
              color: #f3961c;
            }
          }

          i {
            color: #fff;
          }

          span {
            color: #fff;
            font-size: 13px;
            padding-left: 5px;
          }
        }
      }

      //.gls_bg {
      //  width: 100%;
      //  height: 100%;
      //  max-width: 1200px;
      //  margin: 0 auto;
      //  background: url(/static/img/h_x.4e3ffb94.png) no-repeat center -42px;
      //  background-size: cover;
      //}

      .gls_shadow {
        width: 100%;
        height: 100%;
        max-width: 1200px;
        margin: 0 auto;
        background: url("./yy.png") no-repeat top center;
      }

      h1 {
        width: 100%;
        color: #fff;
        text-align: center;
        padding-top: 7px;
        font-size: 30px;
        letter-spacing: 10px;
        font-family: "Microsoft YaHei";
        margin: 0;
      }

      .nav_wk {
        width: 100%;
        height: 40px;
        position: absolute;
        bottom: 18px;
        left: 0px;

        .nav_con {
          width: 100%;
          max-width: 1200px;
          height: 40px;
          margin: 0 auto;
          display: flex;
          align-items: center;

          .seizeD {
            flex: 1;
            height: 100%;
          }

          .nav_c {
            width: 280px;
          }

          .nav_c_left {
            display: flex;
            justify-content: space-between;

            a:nth-child(1) {
              margin-left: 54px;
            }
          }

          a {
            position: relative;
            font-size: 14px;
            color: #fff;
            letter-spacing: 1px;

            span {
              padding-left: 5px;
            }

            &:hover {
              color: #f3961c;
              font-weight: bold;

              &::after {
                position: absolute;
                content: "";
                width: 30px;
                height: 3px;
                background: #f3961c;
                left: 50%;
                top: 26px;
                margin-left: -15px;
                transform: translateY(-50%);
                animation: blink 1.2s infinite steps(1, start);
                border-radius: 5px;
              }
            }

            &.hoveA {
              color: #f3961c;
              font-weight: bold;

              &::after {
                position: absolute;
                content: "";
                width: 30px;
                height: 3px;
                background: #f3961c;
                left: 50%;
                top: 26px;
                margin-left: -15px;
                transform: translateY(-50%);
                animation: blink 1.2s infinite steps(1, start);
                border-radius: 5px;
              }
            }
          }
        }
      }
    }

    //右侧企业列表
    .home-company-list {
      position: fixed;
      width: 400px;
      height: calc(100% - 99px);
      z-index: 70;
      user-select: none;
      right: 2px;
      background: rgba(23, 32, 47, 0.9);
      border: 1px solid #797979;
      border-top: none;
      transition: 1s;
      -webkit-transition: 1s;
    }

    //平台选择
    .home-platform-change {
      height: auto;
      position: fixed;
      top: 65px;
      left: 302px;
      background: rgba(23, 32, 47, 0.9);
      border: 1px solid #797979;
      z-index: 70;
      display: flex;
      padding: 10px 10px 0;
      transition: 1s;
      -webkit-transition: 1s;

      .allCheck {
        margin-right: 20px;
        color: #fff;

        span {
          font-size: 13px;
        }
      }

      .checkStyle {
        .el-checkbox {
          color: #fff;
          margin-right: 20px;
          margin-bottom: 10px;

          span {
            font-size: 13px;
          }
        }
      }
    }

    //工具栏
    .toolbar {
      width: 400px;
      height: 30px;
      display: flex;
      align-items: center;
      position: absolute;
      right: 2px;
      top: 65px;
      border: 1px solid #797979;
      background-color: rgba(23, 32, 47, 0.8);

      span {
        width: 30px;
        line-height: 30px;
        text-align: center;

        i {
          color: #fff;
          font-size: 16px;
        }

        &:hover {
          cursor: pointer;

          i {
            color: rgba(243, 150, 28, 0.7);
          }
        }
      }

      p {
        flex: 1;
        margin: 0 5px;
        color: #fff;
        text-align: center;
        font-size: 13px;
        letter-spacing: 1px;
      }
    }

    //中间企业详情
    .home-company-details {
      height: calc(100% - 69px);
      position: fixed;
      left: 302px;
      background: rgba(23, 32, 47, 0.9);
      border: 1px solid #797979;
      z-index: 70;
      display: flex;
      flex-direction: column;
      transition: 1s;
      -webkit-transition: 1s;

      .mapPollutionHead {
        height: 30px;
        border-bottom: 1px solid #797979;
        width: 100%;
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding: 0 10px;

        p {
          color: #fff;
          font-size: 14px;
          letter-spacing: 1px;
        }

        i {
          color: #fff;
          font-size: 16px;
          cursor: pointer;
        }
      }
    }

    //左侧数据统计
    .home-company-census {
      position: fixed;
      top: 65px;
      left: 0;
      height: calc(100% - 69px);
      z-index: 70;
      width: 300px;
      background: rgba(23, 32, 47, 0.9);
      border: 1px solid #797979;
      overflow: hidden;
      transition: 1s;
      -webkit-transition: 1s;
    }
  }

  //内容
  .mapContain {
    width: 100%;
    height: 100%;

    .map {
      width: 100%;
      height: 100%;
    }
  }

  .mapPollutionWk {
    flex: 1;
    overflow: hidden;
  }
}
</style>
