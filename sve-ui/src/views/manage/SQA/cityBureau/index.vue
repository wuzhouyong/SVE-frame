<template>
  <div
    class="city-bureau"
    :style="{padding:(path==='/analysis/cityBureau'?'10px 4px 10px 10px':'0px')}"
  >
    <div class="city-bureau-con">
      <!-- 监控任务总览 -->
      <div class="monitor-task-overview">
        <div class="bureau-title">
          <i class="el-icon-s-data"></i>
          <span>监控任务总览</span>
          <p class="task-p">
            <el-select v-model="cityValues" placeholder="请选择镇街" size="mini" class="townSelect" style="width:120px"
                       clearable popper-class="selectName" @change="onChange">
              <el-option
                v-for="(item, index) in townCodeOptions"
                :key="index"
                :label="item.regionName"
                :value="item.regionCode"
              ></el-option>
            </el-select>
            <router-link
              to="/HomeAnalysis"
              style="font-size:12px;"
              v-if="path==='/kanban/cityBureau'"
            >
              <i class="el-icon-monitor"></i>
              <span style="padding-left:5px;">全屏显示</span>
            </router-link>
            <router-link to="/kanban/cityBureau" style="font-size:12px;" v-else>
              <i class="el-icon-monitor"></i>
              <span style="padding-left:5px;">返回平台</span>
            </router-link>
            <router-link to="/inc/monitortask/taskStatis" style="font-size:12px;">
              <i class="el-icon-link"></i>
              <span style="padding-left:5px;">任务进度统计表</span>
            </router-link>
          </p>
        </div>
        <OverViewTem :chooseTownCode="chooseTownCode"></OverViewTem>
      </div>
      <!-- 镇街任务进度排名 -->
      <div class="town-task-schedule">
        <div class="bureau-title">
          <i class="el-icon-s-data"></i>
          <span>镇街任务进度排名</span>
        </div>
        <PlatformTem @isChange="isChange" temName="town" @defaultData="defaultData"></PlatformTem>
        <townTem :isTownHeadId="isTownHeadId"></townTem>
      </div>
      <!-- 监控任务进度排名 -->
      <div class="town-task-schedule">
        <div class="bureau-title">
          <i class="el-icon-s-data"></i>
          <span>监控任务进度排名</span>
        </div>
        <monitorTem :chooseTownCode="chooseTownCode"></monitorTem>
      </div>
      <!-- 施工任务排名 -->
      <div class="construction-task-rank">
        <div class="bureau-title">
          <i class="el-icon-s-data"></i>
          <span>施工单位排名</span>
        </div>
        <PlatformTem @isChange="isChange" temName="work"></PlatformTem>
        <ConstructTem :isWorkHeadId="isWorkHeadId" :chooseTownCode="chooseTownCode"></ConstructTem>
      </div>
    </div>
  </div>
</template>
<script>
import PlatformTem from "./platform";
import townTem from "./town";
import ConstructTem from "./construct";
import monitorTem from "./monitor";
import OverViewTem from "./overview";
import {GetRegionPower} from "@/api/composite/source";

export default {
  components: {PlatformTem, townTem, ConstructTem, monitorTem, OverViewTem},
  data () {
    return {
      isTownHeadId: [],
      isWorkHeadId: [],
      path: "",
      cityValues: "",
      townCodeOptions: [],
      chooseTownCode: null
    };
  },
  created () {
    GetRegionPower().then((res) => {
      this.townCodeOptions = res.data;
    });
  },
  watch: {
    $route: {
      handler (v) {
        this.path = v.path;
      },
      immediate: true
    }
  },
  methods: {
    isChange (v, i) {
      v.length === 0
        ? i === "town"
        ? (this.isTownHeadId = [])
        : (this.isWorkHeadId = [])
        : i === "town"
        ? (this.isTownHeadId = v)
        : (this.isWorkHeadId = v);
    },
    //获取施工单位排名
    // getWorkData(v) {
    //   console.log("work", v);
    // },
    //默认值
    defaultData (v) {
      this.isTownHeadId = v;
      this.isWorkHeadId = v;
    },
    onChange (v) {
      this.chooseTownCode = v;
    }
  }
};
</script>
<style lang="scss" scope>
.selectName {
  background: #334454;

  .el-select-dropdown__item {
    color: #999;

    &.hover {
      background-color: rgba(0, 21, 41, 0.8);
      color: #fff;
    }

    &:hover {
      background-color: rgba(0, 21, 41, 0.8);
      color: #fff;
    }
  }
}

.townSelect {
  .el-input__suffix {
    height: 87%;
  }

  input {
    background: #334454;
    color: #fff;
  }
}

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
      top: 3px;
      z-index: 99;
    }
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
</style>
