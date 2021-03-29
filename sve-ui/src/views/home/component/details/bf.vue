<template>
  <div class="mapindex-source-details">
    <div class="outwater-tab">
      <el-tabs v-model="activeName" type="card" @tab-click="menuTab">
        <el-tab-pane label="企业信息" name="1">
          <n-source-msg :sData="sData" :platform="platform"></n-source-msg>
        </el-tab-pane>
        <el-tab-pane label="异常查询" name="3">
        </el-tab-pane>
        <el-tab-pane label="数据查询" name="4">
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

import nSourceMsg from "./component/nSourceMsg";

export default {
  components: {
    ConstructChunk,
    WarnChunk,
    RealChunk,
    DayChunk,
    MinuteChunk,
    HourChunk,
    nSourceMsg
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
