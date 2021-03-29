<template>
  <div class="score-style">
    <div class="construction-task">
      <div class="bureau-title" style="display:flex;">
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
          <router-link to="/inc/monitortask/taskStatis" style="font-size:12px;">
            <i class="el-icon-link"></i>
            <span style="padding-left:5px;">任务进度统计表</span>
          </router-link>
        </p>
      </div>
      <overviewTem :chooseTownCode="chooseTownCode"></overviewTem>
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
      <censusTem></censusTem>
    </div>
  </div>
</template>

<script>
import overviewTem from "./overview";
import censusTem from "./census";
import {GetRegionPower} from "@/api/composite/source";

export default {
  components: {overviewTem, censusTem},
  data () {
    return {
      townCodeOptions: [],
      cityValues: "",
      chooseTownCode: null,
      typeNum: ["未完成", "已完成", "不安装", "已接入", "联网完成", "待验收", "已验收", "未开展", "已登录", "施工中", "暂缓", "申请暂缓", "申请不安装"]
    };
  },
  created () {
    GetRegionPower().then((res) => {
      this.townCodeOptions = res.data;
    });
  },
  methods: {
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
