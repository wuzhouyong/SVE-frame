<template>
  <div
    class="appLogin"
    v-loading="loading"
    element-loading-text="平台跳转中,请稍后"
    element-loading-spinner="el-icon-loading"
    element-loading-background="rgba(0, 0, 0, 0.8)"
  ></div>
</template>
<script>
import Cookies from "js-cookie";
export default {
  data() {
    return {
      loading: true
    };
  },
  created() {
    this.logOut();
  },
  methods: {
    logOut() {
      this.$store.dispatch("LogOut").then(() => {
        Cookies.remove("system-key");
        Cookies.remove("system-name");
        Cookies.remove("Admin-Token");
        this.$nextTick(() => {
          this.setApi();
        });
      });
    },
    setApi() {
      this.$store.dispatch("AppLogin", this.$route.query.authCode).then(() => {
        this.$router.push({ path: "/" });
      });
    }
  }
};
</script>
<style lang="scss">
.appLogin {
  width: 100%;
  height: 100%;
  overflow: hidden;
}
</style>
