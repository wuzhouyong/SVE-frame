<template>
  <div class="maphomeindex-style">
    <div class="gls_wk" ref="companyStyle">
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
          @click="()=>{isStatis = !isStatis;pollutionCountWidth()}"
        >
          <i class="el-icon-picture-outline"></i>
        </span>
        <p>在线监控污染源</p>
        <span :title="isCompany?'收起企业列表':'展开企业列表'" @click="()=>isCompany = !isCompany">
          <i :class="isCompany?'el-icon-arrow-up':'el-icon-arrow-down'"></i>
        </span>
      </div>
      <!-- 企业列表 -->
      <div class="home-company-list" :style="{top:(isCompany? '35px':'110%' )}">
        <SourceChunk
          :checkArr="checkArrs"
          :regions="regions"
          @showDetails="showDetails"
          @sendSourceList="sendSourceList"
          @townCodeSave="townCodeSave"
        ></SourceChunk>
      </div>
      <!-- 平台选择 -->
      <transition name="el-zoom-in-top" v-if="isHome">
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
        :style="{width:pollutionW,top:(!isPlatform? '5px':'110%' ),left:(isStatis?'302px':'2px')}"
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
      <!---按钮-->
<!--      <div class="home-index-btn">-->
<!--        <el-button size="mini" type="primary" plain>监控统计</el-button>-->
<!--        <el-button size="mini" type="primary" plain>异常任务</el-button>-->
<!--        <el-button size="mini" type="primary" plain>异常处理</el-button>-->
<!--        <el-button size="mini" type="primary" plain>企业列表</el-button>-->
<!--      </div>-->
      <!-- 统计 -->
      <div class="home-company-census" :style="{top:(isStatis? '5px':'110%' )}">
        <StatisChunk @closediv="closediv" :tagHeadIds="checkArrs" :townCode="townCodeData"></StatisChunk>
      </div>
    </div>
  </div>
</template>
<script>
import MapChunk from "@/views/home/component/map";
import SourceChunk from "@/views/home/component/sourceList";
import StatisChunk from "@/views/home/component/statis";
import DetailsChunk from "@/views/home/component/details";
import utils from "@/utils/ruoyi";
import {getSourceList, getTagList} from "@/api/composite/source";
import {task_statistics} from "@/config";

export default {
  name: "MapIndex",
  components: {MapChunk, SourceChunk, StatisChunk, DetailsChunk},
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
      nKEY: "",
      isHome: false,
      townCodeData: ""
    };
  },
  created () {
    this.$nextTick(() => {
      this.pollutionCountWidth();
    });

    this.getPlatform();
  },
  watch: {
    $route: {
      handler () {
        this.getPlatform();
        this.isPlatform = true;
        this.isStatis = true;
        this.isCompany = true;
      }
    }
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
      getTagList().then((res) => {
        let oArr = res.rows;
        this.cities = task_statistics.filter((p) =>
          oArr.some((s) => s.tagHeadId === p.id)
        );
        let oPath = this.$route.path;
        let checkArr = [];
        if (oPath === "/" || oPath === "/comprehensive") {
          this.cities.forEach((v) => {
            checkArr.push(v.id);
          });
          this.isHome = true;
          this.checkAll = true;
        } else {
          if (oPath === "/lingxing") {
            checkArr = ["1003", "1018"];
            this.cities = [{
              name: "零星废水产生企业",
              path: "lingxing",
              id: "1003",
              ref_type: "lx_sysfile_type"
            },
              {
                name: "零星废水处置企业",
                path: "lingxingp",
                id: "1018",
                ref_type: "lxp_sysfile_type"
              }];
            this.isHome = true;
            this.checkAll = true;
          } else {
            this.cities.forEach((v) => {
              if (oPath.replace("/", "") === v.path) {
                checkArr.push(v.id);
              }
            });
            this.isHome = false;
            this.checkAll = false;
          }
        }
        this.checkArrs = checkArr;
      });
    },
    townCodeSave (v) {
      this.townCodeData = v;
    },
    //平台数据
    // getSourceTagList() {
    //   getSourcetagList().then((res) => {
    //     if (res.code === 200) {
    //       let oArr = res.data;

    //       this.cities = platform_list.filter((p) =>
    //         oArr.some((s) => s.id === p.id)
    //       );
    //       let oPath = this.$route.path;
    //       let checkArr = [];
    //       if (oPath === "/" || oPath === "/comprehensive") {
    //         this.cities.forEach((v, i, a) => {
    //           checkArr.push(v.id);
    //         });
    //         this.isHome = true;
    //         this.checkAll = true;
    //       } else {
    //         this.cities.forEach((v, i, a) => {
    //           if (oPath.indexOf(v.path) !== -1) {
    //             checkArr.push(v.id);
    //           }
    //         });
    //         this.isHome = false;
    //         this.checkAll = false;
    //       }
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
      if (this.$refs.companyStyle) {
        this.$nextTick(() => {
          let oWidth = this.$refs.companyStyle.clientWidth;
          if (this.isStatis) {
            this.pollutionW = oWidth - 400 - 300 - 6 + "px";
          } else {
            this.pollutionW = oWidth - 400 - 6 + "px";
          }
        });
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
    //关闭统计
    closediv () {
      this.isStatis = false;
      this.pollutionCountWidth();
    }
  }
};
</script>
<style lang="scss">
.maphomeindex-style {
  width: 100%;
  height: 100%;
  position: relative;

  .gls_wk {
    width: 100%;
    height: 100%;
    position: relative;
    //右侧企业列表
    .home-company-list {
      position: absolute;
      width: 400px;
      height: calc(100% - 40px);
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
      position: absolute;
      top: 5px;
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
      top: 5px;
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
      height: calc(100% - 10px);
      position: absolute;
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

    //首页按钮
    .home-index-btn {
      width: 300px;
      height: 35px;
      background: rgba(23, 32, 47, 0.9);
      border: 1px solid #797979;
      position: absolute;
      left: 0;
      top: 5px;
      display: flex;
      align-items: center;
      justify-content: space-around;

      button {
        margin-left: 0px;
      }

      //p {
      //  color: #2b92d4;
      //  font-size: 12px;
      //  height: 28px;
      //  line-height: 28px;
      //  border: 1px solid #00a1ff;
      //  border-radius: 3px;
      //  flex: 1 1;
      //  text-align: center;
      //  cursor: pointer;
      //  margin: 0px 2px;
      //  overflow: hidden;
      //}
    }

    //左侧数据统计
    .home-company-census {
      position: absolute;
      left: 0;
      height: calc(100% - 10px);
      z-index: 70;
      width: 300px;
      background: rgba(23, 32, 47, 0.9);
      border: 1px solid #797979;
      overflow: hidden;
      transition: 1s;
      -webkit-transition: 1s;

      p.oIndex-btn {
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
