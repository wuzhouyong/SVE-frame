<template>
  <div class="score-style">
    <div class="construction-task">
      <div class="bureau-title" style="display:flex;">
        <i class="el-icon-s-data"></i>
        <span>监控任务总览</span>
        <p class="task-p">
          <router-link to="/inc/monitortask/taskStatis" style="font-size:12px;">
            <i class="el-icon-link"></i>
            <span style="padding-left:5px;">任务进度统计表</span>
          </router-link>
        </p>
      </div>
      <overviewTem></overviewTem>
    </div>
    <div class="construction-task">
      <div class="bureau-title" style="display:flex;">
        <i class="el-icon-s-data"></i>
        <span>建设任务</span>
        <p class="task-p">
          <router-link to="/inc/monitortask/monitorSource" style="font-size:12px;">
            <i class="el-icon-link"></i>
            <span style="padding-left:5px;">监控任务清单</span>
          </router-link>
        </p>
      </div>
      <missionTem></missionTem>
    </div>
  </div>
</template>

<script>
import missionTem from "./mission"
import overviewTem from "./overview"

export default {
  components: { missionTem, overviewTem },
  data() {
    return {}
  },
  created() {}
}
</script>

<style lang="scss" scoped>
.score-style {
  width: 100%;
  height: 100%;
  overflow: hidden;
  overflow-y: auto;
  padding: 10px 4px 10px 10px;

  .construction-task {
    padding: 0 8px;
    background: rgba(0, 21, 41, 0.8);
    box-shadow: 0 0px 0px rgba(0, 0, 0, 0.3), 0 0 4px rgba(0, 0, 0, 0.9);
    margin-bottom: 10px;
    position: relative;
    height: auto;
    min-height: 350px;
    overflow: hidden;
    display: flex;
    flex-direction: column;


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
</style>
