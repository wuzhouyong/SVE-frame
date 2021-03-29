<template>
  <el-dialog
    custom-class="video-dialog"
    :title="title"
    append-to-body
    :visible.sync="videoVisible"
    width="600px"
    @close="onClose"
  >
    <div class="historyVideo">
      <el-date-picker
        v-model="historyTime"
        value-format="yyyy-MM-dd HH:mm:ss"
        type="datetime"
        placeholder="选择日期时间">
      </el-date-picker>
      <el-button type="primary" icon="el-icon-time" @click="historyVideo">查看历史视频</el-button>
      <el-button type="warning" icon="el-icon-video-camera" @click="realVideo" v-if="!isReal">查看实时视频</el-button>
    </div>
    <div style="margin: 0 auto; width: 600px; height: 400px">
      <div id="ezviz" v-if="isCloud"></div>
      <video
        id="twAgent"
        v-else
        autoplay
        style="width: 100%; height: 100%; object-fit: fill"
      ></video>
    </div>
  </el-dialog>
</template>

<script>
import flvjs from "flv.js";

export default {
  name: "play-dialog",
  props: {
    title: {
      type: String,
    },
    url: {
      type: String,
      default: ""
    },
    token: {
      type: String,
    },
    videoType: {
      type: String,
    },
    visible: {
      type: Boolean,
      default: false,
    },
    isCloud: {
      type: Boolean,
      default: false,
    },
  },
  data () {
    return {
      plays: null,
      isZW: 0,
      historyTime: "",
      isReal: true,
    };
  },
  computed: {
    videoVisible: {
      get () {
        return !!this.visible;
      },
      set (v) {
        this.$emit("update:visible", v);
      },
    },
  },
  watch: {
    url: {
      handler (v) {
        if (v) {
          this.$nextTick(() => this.playVideo());
        }
      },
      immediate: true,
    }
  },
  methods: {
    playVideo () {
      this.isCloud ? this.ezvizPlay() : this.twAgentPlay();
    },
    ezvizPlay () {
      const option = {
        id: "ezviz",
        url: this.url,
        autoplay: true,
        accessToken: this.token,
        decoderPath: "/static",
        width: 600,
        height: 400,
      };
      this.player = new EZUIPlayer(option);
    },
    twAgentPlay () {
      let oHost = window.location.host;
      let oUrl = null;
      if (oHost === "172.31.0.9:6677") {
        oUrl = this.url;
      } else {
        oUrl = this.url.replace(/192.168.10.209/g, "19.106.165.139");
      }
      let option = {
        type: "flv",
        isLive: false,
        hasVideo: true,
        hasAudio: false,
        withCredentials: false,
        url: oUrl
      };
      this.flv_load_mds(option);
    },
    flv_load_mds (option) {
      this.flv_destroy();
      var element = document.getElementById("twAgent");
      this.plays = flvjs.createPlayer(option, {
        enableWorker: false,
        lazyLoadMaxDuration: 3 * 60,
        enableStashBuffer: false,
        lazyLoad: false,
        lazyLoadRecoverDuration: 1,
        deferLoadAfterSourceOpen: false,
        statisticsInfoReportInterval: 1,
        fixAudioTimestampGap: false,
        stashInitialSize: 128,
      });
      this.$nextTick(() => {
        this.plays.attachMediaElement(element);
        this.plays.load();
        // if (this.isZW !== 1) {
        //   setTimeout(() => {
        //     if (
        //       this.plays._transmuxer._controller._ioctl._loader._ws !==
        //       "undefined" &&
        //       this.plays._transmuxer._controller._ioctl._loader._ws
        //         .readyState != 1
        //     ) {
        //       this.isZW = 1;
        //       this.twAgentPlay();
        //     }
        //   }, 2000);
        // }
      });
      window.onerror = (errorMessage) => {
        this.flv_destroy();
        this.msgError("连接视频服务器失败");
        return true;
      };
    },
    flv_destroy () {
      if (this.plays) {
        this.plays.pause();
        this.plays.unload();
        this.plays.detachMediaElement();
        this.plays.destroy();
        this.plays = null;
      }
    },
    onClose () {
      this.isCloud ? this.player.stop() : this.flv_destroy();
    },
    historyVideo () {
      if (!this.historyTime) {
        this.msgError("请选择需要查看的时间!");
        return;
      }
      if (this.isCloud) {
        //.rec?begin=20210105153700
        let oldurl = JSON.parse(JSON.stringify(this.url.replace(/live/g, "rec")));
        let dateTime = this.historyTime.replace(/[^0-9]/ig, "");
        oldurl = oldurl + "?begin=" + dateTime;
        const option = {
          id: "ezviz",
          url: oldurl,
          autoplay: true,
          accessToken: this.token,
          decoderPath: "/static",
          width: 600,
          height: 400,
        };
        this.player = new EZUIPlayer(option);
      } else {
        this.isReal = false;
        this.flv_destroy();
        this.$emit("videoHistory", this.historyTime);
      }
    },
    realVideo () {
      if (this.isCloud) {
        this.ezvizPlay();
      } else {
        this.isReal = true;
        this.flv_destroy();
        this.historyTime = "";
        this.$emit("videoHistory", null);
      }
    }
  },
};
</script>

<style lang="scss">
.video-dialog {
  .el-dialog__body {
    padding: 0;
  }
}

.historyVideo {
  padding: 0 5px 5px;
}

</style>
