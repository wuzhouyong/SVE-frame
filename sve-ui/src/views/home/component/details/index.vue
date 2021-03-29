<template>
  <div class="mapindex-source-details">
    <div class="source-basic">
      <el-row :gutter="15" v-if="JSON.stringify(sData) !== '{}'">
        <el-col :lg="8" :md="12" :sm="24">
          <p>环保联系人：</p>
          <span :title="sData.envContactName">{{ sData.envContactName || "-" }}</span>
        </el-col>
        <el-col :lg="8" :md="12" :sm="24">
          <p>环保联系人电话：</p>
          <span>{{ sData.envContactPhone || "-" }}</span>
        </el-col>
        <el-col :lg="8" :md="12" :sm="24">
          <p>所属行业：</p>
          <span :title="sData.industryName">{{ sData.industryName || "-" }}</span>
        </el-col>
        <el-col :lg="8" :md="12" :sm="24">
          <p>企业经营状态：</p>
          <span>{{ sData.dictLabel || "-" }}</span>
        </el-col>
        <el-col :lg="8" :md="12" :sm="24">
          <p>所属镇街：</p>
          <span>{{ sData.townName || "-" }}</span>
        </el-col>
        <el-col :lg="8" :md="12" :sm="24">
          <p>地址：</p>
          <span :title="sData.address">{{ sData.address || "-" }}</span>
        </el-col>
        <el-col :lg="8" :md="12" :sm="24">
          <p>排污许可证代码：</p>
          <span>{{ sData.emissionPermitCode || "-" }}</span>
        </el-col>
        <!-- <el-col :lg="8" :md="12" :sm="24">
          <p>企业验收状态：</p>
          <span>{{constructionStatus[sData.status]}}</span>
        </el-col>-->
        <el-col :lg="8" :md="12" :sm="24">
          <p>社会统一信用代码：</p>
          <span>{{ sData.socialCreditCode || "-" }}</span>
        </el-col>
      </el-row>
    </div>
    <div class="platform-list">
      <el-tabs v-model="platFormRadio" @tab-click="tabClick">
        <el-tab-pane :label="i.name" v-for="i in platform" :key="i.path" :name="i.path"></el-tab-pane>
      </el-tabs>
    </div>
    <div class="outwater-tab" v-show="platform.length>0">
      <el-tabs v-model="activeName" type="card" @tab-click="menuTab">
        <el-tab-pane label="施工进度" name="1">
          <ConstructChunk :platFormPath="platFormPath" v-bind="$attrs"></ConstructChunk>
        </el-tab-pane>
        <el-tab-pane label="异常查询" name="3">
          <WarnChunk
            :warnTypes="warnTypes"
            :procStates="procStates"
            :warnLevels="warnLevels"
            :platFormPath="platFormPath"
            :isMenu="activeName==='3'"
            v-bind="$attrs"
          ></WarnChunk>
        </el-tab-pane>
        <el-tab-pane label="实时数据" name="4" v-if="platFormPath!=='outwater'">
          <RealChunk v-bind="$attrs" :platFormPath="platFormPath" :aceName="activeName"></RealChunk>
        </el-tab-pane>
        <el-tab-pane label="分钟数据" name="5">
          <MinuteChunk v-bind="$attrs" :platFormPath="platFormPath" :aceName="activeName"></MinuteChunk>
        </el-tab-pane>
        <el-tab-pane label="小时数据" name="6">
          <HourChunk v-bind="$attrs" :platFormPath="platFormPath" :aceName="activeName"></HourChunk>
        </el-tab-pane>
        <el-tab-pane label="日数据" name="7">
          <DayChunk v-bind="$attrs" :platFormPath="platFormPath" :aceName="activeName"></DayChunk>
        </el-tab-pane>
      </el-tabs>
    </div>
  </div>
</template>
<script>
import {platformSource} from "@/api/home/index";
import {home_system_list} from "@/config";
import ConstructChunk from "./component/construct";
import WarnChunk from "./component/warn";
import RealChunk from "./component/real";
import DayChunk from "./component/day";
import MinuteChunk from "./component/minute";
import HourChunk from "./component/hour";

export default {
  components: {
    ConstructChunk,
    WarnChunk,
    RealChunk,
    DayChunk,
    MinuteChunk,
    HourChunk
  },
  props: {
    sData: {
      type: Object
    }
  },
  created () {
    if (this.sData.sourceId) {
      this.getPlat(this.sData.sourceId);
    }
  },
  watch: {
    sData: {
      handler (v) {
        if (v) {
          this.getPlat(v.sourceId);
        }
      }
    }
  },
  data () {
    return {
      constructionStatus: [
        "未开展",
        "施工中",
        "已接入",
        "已接入",
        "已接入",
        "已接入",
        "已验收"
      ],
      platform: [],
      platFormRadio: "",
      platFormPath: "",
      loading: false,
      activeName: "1",
      menuId: "",
      procStates: [],
      warnLevels: [],
      warnTypes: []
    };
  },
  methods: {
    //包含平台
    getPlat (v) {
      this.loading = true;
      platformSource(v).then(res => {
        if (res.code === 200) {
          let oArr = res.rows;
          // let newArr = res.rows;
          // let isOm = newArr.some(i => i.tagHeadId === "1017" || i.tagHeadId === "1021");
          // let oArr = JSON.parse(JSON.stringify(newArr));
          // if (isOm) {
          //   oArr.push({
          //     tagHeadId: "1001",
          //     tagHeadName: "在线监控"
          //   })
          // }
          // console.log(oArr);
          this.platform = home_system_list.filter(p =>
            oArr.some(s => s.tagHeadId === p.id)
          );
          if (this.platform.length > 0) {
            this.platFormRadio = this.platform[0].path;
            this.platFormPath = this.platFormRadio;
            setInterval(() => {
              this.loading = false;
            }, 1000);
          }
        }
      });
    },
    //平台切换
    tabClick (v) {
      this.platFormPath = v.name;
      this.activeName = "1";
    },
    menuTab (v) {
      this.activeName = v.name;
      // console.log(this.activeName);
      if (v.name === "3") {
        const p = this.platform.find(p => p.path === this.platFormPath);
        p.warn_type().then(res => {
          this.warnTypes = res.data;
        });
        p.warn_levels().then(res => {
          this.warnLevels = res.data;
        });
        p.proc_state().then(res => {
          this.procStates = res.data;
        });
      }
    }
  }
};
</script>
<style lang="scss">
.mapindex-source-details {
  width: 100%;
  height: 100%;
  overflow: hidden;
  padding: 5px;
  display: flex;
  flex-direction: column;

  .el-loading-mask {
    background-color: rgba(0, 0, 0, 0.4);
  }

  .source-basic {
    width: 100%;
    height: auto;
    color: #fff;
    padding: 5px;
    max-height: 200px;
    overflow-y: auto;
    overflow-x: hidden;

    .el-col {
      display: flex;
      align-items: center;
      margin-bottom: 8px;
      overflow: hidden;

      p {
        font-size: 13px;
        margin: 0;
      }

      span {
        font-size: 13px;
        margin-left: 5px;
        color: #0cf;
        flex: 1;
        overflow: hidden;
        white-space: nowrap;
        text-overflow: ellipsis;
      }
    }
  }

  .platform-list {
    padding: 0 10px;

    .el-tabs__nav {
      .el-tabs__item {
        color: #fff;

        &:hover {
          color: #1890ff;
        }
      }

      .el-tabs__item.is-active {
        color: #1890ff;
      }
    }
  }

  .outwater-tab {
    width: 100%;
    flex: 1;
    padding: 0 5px;
    overflow: hidden;

    .el-tabs {
      width: 100%;
      height: 100%;
      overflow: hidden;
      display: flex;
      flex-direction: column;

      .el-tab-pane {
        height: 100%;
        overflow: hidden;
      }
    }

    .el-table {
      background-color: rgba(23, 32, 47, 0.9) !important;

      thead.is-group th.is-leaf {
        background-color: rgba(23, 32, 47, 0.9) !important;
      }

      .el-table__fixed {
        background-color: rgba(23, 32, 47, 0.9) !important;
      }

      .el-table__header-wrapper th {
        background-color: rgba(23, 32, 47, 0.9) !important;
        color: #eee;
        //height: 30px;
        padding: 0;

      }

      tr {
        background-color: rgba(23, 32, 47, 0.9) !important;
        color: #eee;

        &.hover-row > td {
          background-color: rgba(69, 76, 89, 0.6);
        }

        &:hover {
          td {
            background-color: rgba(69, 76, 89, 0.6);
          }
        }
      }
    }

    .el-tabs__header {
      border-bottom: 1px solid #797979;
    }

    .el-tabs__nav {
      border: 1px solid #797979;
    }

    .el-tabs__item {
      color: #fff;
      height: 30px;
      line-height: 30px;
      font-size: 12px;
      letter-spacing: 1px;
      background: #2f3235 !important;
      border-left: 1px solid #797979;

      &.is-active {
        color: #1890ff;
        background: #454c59 !important;
        font-weight: bold;
        border-bottom-color: #797979;
      }

      &:hover {
        color: #1890ff;
        background: #454c59 !important;
      }
    }

    .el-tabs__content {
      height: 100%;
    }
  }
}
</style>
