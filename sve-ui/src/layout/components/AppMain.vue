<template>
  <!-- <section class="app-main" :class="{'app-main-iframe':isFrame}">
    <transition v-if="isFrame||isCurSystem" name="fade-transform" mode="out-in">
      <keep-alive :include="cachedViews">
        <router-view :key="key" />
      </keep-alive>
    </transition>
    <iframe v-if="!isFrame&&!isCurSystem" :src="routeView" height="100%" width="100%" ref="iframe"></iframe>
  </section>-->
  <section class="app-main">
    <transition name="fade-transform" mode="out-in">
      <keep-alive :include="cachedViews">
        <router-view :key="key" />
      </keep-alive>
    </transition>
  </section>
</template>

<script>
// import { isFrame, checkURL } from "@/utils/system";
// import { getToken } from "@/utils/auth";
import { mapState } from "vuex";
export default {
  name: "AppMain",
  computed: {
    ...mapState("tagsView", ["cachedViews"]),
    key() {
      return this.$route.path;
      // let cur = this.$store.state.curos;
      // this.isCurSystem = cur.key === 0;
      // if(this.isCurSystem) {
      //   return (cur.key === 0 ? "" : cur.path) + this.$route.path + (cur.key === 0 ? "" : "?k=" + getToken());
      // }
    },
    // path() {
    //   return this.$route.path;
    // },
    // sys() {
    //   return this.$store.state.curos.key;
    // },
  },
  data() {
    return {
      // iFrameWin: {},
      // inPath: "",
      // isFrame: false,
      // isCurSystem: true,
      // routeView: "",
    };
  },
  created() {
    // this.isFrame = isFrame();
  },
  mounted() {
    // if (this.isFrame) {
    //   // console.log("add listen");
    //   window.addEventListener("message", (event) => {
    //     const data = event.data;
    //     switch (data.cmd) {
    //       case "changeMemu":
    //         //console.log(data.params);
    //         this.$router.push({ path: data.params.key });
    //         break;
    //     }
    //   });
    // }
  },
  watch: {
    // sys() {
    //   //console.log("sys");
    //   let cur = this.$store.state.curos;
    //   this.isCurSystem = cur.key === 0 || !checkURL(cur.path);
    //   //this.routeView = (cur.key === 0 ? "" : cur.path) + this.$route.path + (cur.key === 0 ? "" : "?k=" + getToken());
    //   this.routeView =
    //     (cur.key === 0 ? "" : cur.path) +
    //     (cur.key === 0 ? "" : "?k=" + getToken());
    // },
    // path() {
    //   if (!this.isFrame && !this.isCurSystem) {
    //     this.sendMessage(this.$route.path);
    //   } else this.routeView = this.$route.path;
    // },
  },
  methods: {
    // sendMessage(res) {
    //   // console.log(res);
    //   if (this.$refs.iframe !== undefined) {
    //     this.$refs.iframe.contentWindow.postMessage(
    //       {
    //         cmd: "changeMemu",
    //         params: { key: res },
    //       },
    //       "*"
    //     );
    //   }
    // },
  },
};
</script>

<style lang="scss" scoped>
.app-main {
  /* 50= navbar  50  */
  // min-height: calc(100vh - 70px);
  height: 100%;
  width: 100%;
  position: relative;
  overflow: hidden;

  iframe {
    height: calc(100vh - 40px);
    border: 0px;
  }
}

.fixed-header + .app-main {
  padding-top: 50px;
}

.hasTagsView {
  .app-main {
    /* 84 = navbar + tags-view = 50 + 34 */
    // min-height: calc(100vh - 84px);
    height: 100%;
  }

  .fixed-header + .app-main {
    // padding-top: 84px;
    padding-top: 74px;
  }
}
</style>

<style lang="scss">
// fix css style bug in open el-dialog
.el-popup-parent--hidden {
  .fixed-header {
    padding-right: 15px;
  }
}

// .app-main-iframe {
//   position: fixed !important;
//   height: 100%;
//   width: 100%;
//   left: 0;
//   top: 0;
//   overflow: auto !important;
// }
</style>
