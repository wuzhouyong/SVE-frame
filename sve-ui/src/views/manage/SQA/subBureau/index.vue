<template>
  <div
    class="city-bureau"
    :style="{padding:(path==='/analysis/subBureau'?'10px 4px 10px 10px':'0px')}"
  >
    <div class="city-bureau-con">
      <!-- 监控任务总览 -->
      <div class="monitor-task-overview">
        <div class="bureau-title">
          <i class="el-icon-s-data"></i>
          <span>监控任务总览</span>
          <p class="task-p">
            <router-link
              to="/HomeAnalysisSub"
              style="font-size:12px;"
              v-if="path==='/kanban/subBureau'"
            >
              <i class="el-icon-monitor"></i>
              <span style="padding-left:5px;">全屏显示</span>
            </router-link>
            <router-link to="/kanban/subBureau" style="font-size:12px;" v-else>
              <i class="el-icon-monitor"></i>
              <span style="padding-left:5px;">返回平台</span>
            </router-link>
            <router-link to="/inc/monitortask/taskStatis" style="font-size:12px;">
              <i class="el-icon-link"></i>
              <span style="padding-left:5px;">任务进度统计表</span>
            </router-link>
          </p>
        </div>
        <OverViewTem></OverViewTem>
      </div>
      <!-- 镇街任务进度排名 -->
      <div class="mon-task-schedule">
        <div class="bureau-title" style="display:flex;">
          <i class="el-icon-s-data"></i>
          <span>我的排名</span>
          <div class="allStyle">
            <p>总任务数:{{ all.totalcnt }}</p>
            <p>联网完成量:{{ all.cnt }}</p>
            <p>联网完成量排名:NO.{{ all.countrank }}</p>
            <p>联网完成率:{{ all.rate }}%</p>
            <p>联网完成率排名:NO.{{ all.raterank }}</p>
          </div>
        </div>
        <Completed></Completed>
      </div>
      <!-- 监控任务进度排名 -->
      <div class="town-task-schedule">
        <div class="bureau-title">
          <i class="el-icon-s-data"></i>
          <span>监控任务进度排名</span>
        </div>
        <monitorTem></monitorTem>
      </div>
      <!-- 施工任务排名 -->
      <!-- <div class="construction-task-rank">
        <div class="bureau-title">
          <i class="el-icon-s-data"></i>
          <span>施工单位排名</span>
        </div>
        <PlatformTem @isChange="isChange" temName="work" @defaultData="defaultData"></PlatformTem>
        <ConstructTem :isWorkHeadId="isWorkHeadId"></ConstructTem>
      </div> -->
    </div>
  </div>
</template>
<script>
import { getSourcetagList } from "@/api/composite/source";
import { countLeaderboard } from "@/api/analysis";
import PlatformTem from "./platform";
import ConstructTem from "./construct";
import monitorTem from "./monitor";
import OverViewTem from "./overview";
import Completed from "./completed";

export default {
  components: { PlatformTem, ConstructTem, monitorTem, OverViewTem, Completed },
  data() {
    return {
      isTownHeadId: [],
      isWorkHeadId: [],
      path: "",
      all: {}
    };
  },
  created() {
    this.getLeaderboard();
  },
  watch: {
    $route: {
      handler(v) {
        this.path = v.path;
      },
      immediate: true
    }
  },
  methods: {
    isChange(v) {
      if (v.length === 0) {
        this.isWorkHeadId = [];
      } else {
        this.isWorkHeadId = v;
      }
    },
    //默认值
    defaultData(v) {
      this.isWorkHeadId = v;
    },
    getLeaderboard() {
      countLeaderboard().then(res => {
        if (res.code === 200) {
          this.all = res.data[0];
        }
      });
    }
  }
};
</script>
<style lang="scss" scope>
.city-bureau {
  width: 100%;
  height: 100%;
  overflow: hidden;

  .city-bureau-con {
    width: 100%;
    height: 100%;
    overflow-y: auto;
  }

  .town-task-schedule,
  .mon-task-schedule,
  .construction-task-rank,
  .monitor-task-overview,
  .monitoring-task-rank {
    padding: 0 8px;
    background: rgba(0, 21, 41, 0.8);
    box-shadow: 0 0px 0px rgba(0, 0, 0, 0.3), 0 0 4px rgba(0, 0, 0, 0.9);
    margin-bottom: 10px;
    position: relative;
  }

  .monitor-task-overview {
    height: auto;
    min-height: 300px;
    position: relative;

    .task-p {
      margin: 0;
      position: absolute;
      right: 20px;
      top: 0px;

      a {
        font-size: 12px;
        margin-left: 10px;
      }
    }
  }

  .town-task-schedule {
    height: 400px;

    .townChange {
      position: absolute;
      right: 16px;
      top: 74px;
      z-index: 99;
    }
  }

  .mon-task-schedule {
    height: 556px;
    padding-bottom: 5px;
  }

  .construction-task-rank {
    height: 400px;
  }

  .monitoring-task-rank {
    height: 450px;
  }
}

.bureau-title {
  width: 100%;
  height: 35px;
  line-height: 35px;
  color: #fff;
  border-bottom: 1px solid #ebeef5;
  font-size: 13px;
  display: flex;
  align-items: center;
  padding-left: 5px;

  span {
    padding-left: 5px;
  }
}

.allStyle {
  display: flex;
  margin-left: 30px;

  p {
    margin: 0;
    margin-left: 30px;
    letter-spacing: 1px;
    font-weight: bold;
    color: #e4d14e;
  }
}
</style>
