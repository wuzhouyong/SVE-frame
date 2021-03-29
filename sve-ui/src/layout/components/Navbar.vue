<template>
  <div class="navbar">

    <div class="logo-title">
      <img src="../../assets/logo/logo3.png" height="38" width="38"/>
      <span>在线监控平台</span>
    </div>


    <!--    <hamburger-->
    <!--      id="hamburger-container"-->
    <!--      :is-active="sidebar.opened"-->
    <!--      class="hamburger-container"-->
    <!--      @toggleClick="toggleSideBar"-->
    <!--    />-->
    <center-menu/>
    <!-- <breadcrumb id="breadcrumb-container" class="breadcrumb-container" /> -->
<!--    <div class="right-menu">-->
<!--      <div class="home-index">-->
<!--        <span class="downLoadSC" title="下载操作手册">-->
<!--          <el-link icon="el-icon-notebook-2" href="/static/操作手册.pdf" target="_blank">操作手册</el-link>-->
<!--        </span>-->
<!--      </div>-->
<!--      <div class="home-index" v-if="curos.key===0 || curos.key===2305 || curos.key===2298">-->
<!--        <router-link to="/homeIndex" style="font-size:12px;">-->
<!--          <i class="el-icon-data-board"></i>-->
<!--          <span style="padding-left:5px;">全屏展示首页</span>-->
<!--        </router-link>-->
<!--      </div>-->
<!--      <div class="home-index" title="个人中心">-->
<!--        <router-link to="/user/profile" style="font-size:12px;">-->
<!--          <i class="el-icon-user"></i>-->
<!--          <span style="padding-left:5px;">{{user.userName}}</span>-->
<!--        </router-link>-->
<!--      </div>-->
<!--      <span class="logout-style" @click="logout" title="退出系统">-->
<!--        <i class="el-icon-s-promotion"></i>退出-->
<!--      </span>-->
<!--      <template v-if="device!=='mobile'">-->
<!--        <search id="header-search" class="right-menu-item"/>-->
<!--        <screenfull id="screenfull" class="right-menu-item hover-effect"/>-->
<!--        <el-tooltip content="布局大小" effect="dark" placement="bottom">-->
<!--          <size-select id="size-select" class="right-menu-item hover-effect"/>-->
<!--        </el-tooltip>-->
<!--      </template>-->
<!--    </div>-->
  </div>
</template>

<script>
  import {mapGetters, mapState} from "vuex";
  import Breadcrumb from "@/components/Breadcrumb";
  import Hamburger from "@/components/Hamburger";
  // import Screenfull from "@/components/Screenfull";
  import SizeSelect from "@/components/SizeSelect";
  // import Search from "@/components/HeaderSearch";
  import RuoYiGit from "@/components/RuoYi/Git";
  import RuoYiDoc from "@/components/RuoYi/Doc";
  import SwitchCenter from "../../components/SwitchCenter/index";
  import Cookies from "js-cookie";
  import {getUserProfile} from "@/api/system/user";
  import CenterMenu from "@/components/centerMenu/index";

  export default {
    components: {
      CenterMenu,
      SwitchCenter,
      Breadcrumb,
      Hamburger,
      // Screenfull,
      SizeSelect,
      // Search,
      RuoYiGit,
      RuoYiDoc
    },
    computed: {
      ...mapGetters(["sidebar", "device"]),
      ...mapState(["curos"]),
      setting: {
        get() {
          return this.$store.state.settings.showSettings;
        },
        set(val) {
          this.$store.dispatch("settings/changeSetting", {
            key: "showSettings",
            value: val
          });
        }
      },
      showLogo() {
        return this.$store.state.settings.sidebarLogo
      },
    },

    data() {
      return {
        user: {},
        sysKey: ""
      };
    },
    created() {
      //this.getUser();
    },
    methods: {
      getUser() {
        getUserProfile().then(response => {
          this.user = response.data;
          this.roleGroup = response.roleGroup;
          this.postGroup = response.postGroup;
        });
      },
      toggleSideBar() {
        this.$store.dispatch("app/toggleSideBar");
      },
      async logout() {
        this.$confirm("确定注销并退出系统吗？", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(() => {
          this.$store.dispatch("LogOut").then(() => {
            Cookies.remove("system-key");
            location.reload();
          });
        });
      }
    }
  };
</script>

<style lang="scss" scoped>
  .navbar {
    height: 50px;
    overflow: hidden;
    position: relative;
    background: #fff;
    box-shadow: 0 1px 4px rgba(0, 21, 41, 0.08);

    .logo-title {
      position: absolute;
      left: 20px;
      top: 10px;
      display: inline-flex;

      span {
        margin-left: 15px;
        color: #FFF;
        font-size: 24px;
      }

    }

    .hamburger-container {
      line-height: 46px;
      height: 100%;
      float: left;
      cursor: pointer;
      transition: background 0.3s;
      -webkit-tap-highlight-color: transparent;

      &:hover {
        background: rgba(0, 0, 0, 0.025);
      }
    }

    .breadcrumb-container {
      float: left;
    }

    .errLog-container {
      display: inline-block;
      vertical-align: top;
    }

    .right-menu {
      float: right;
      height: 100%;
      line-height: 50px;
      display: flex;

      &:focus {
        outline: none;
      }

      .home-index {
        height: 100%;
        font-size: 12px;
        margin-right: 20px;

        &:hover {
          a,
          i {
            color: #42b983;
          }
        }
      }

      .logout-style {
        cursor: pointer;
        font-size: 12px;
        margin-right: 20px;

        &:hover {
          color: #42b983;

          i {
            color: #42b983;
          }
        }
      }

      .right-menu-item {
        display: inline-block;
        padding: 0 8px;
        height: 100%;
        font-size: 18px;
        color: #5a5e66;
        vertical-align: text-bottom;

        &.hover-effect {
          cursor: pointer;
          transition: background 0.3s;

          &:hover {
            background: rgba(0, 0, 0, 0.025);
          }
        }
      }

      .avatar-container {
        margin-right: 30px;

        .avatar-wrapper {
          margin-top: 5px;
          position: relative;

          .user-avatar {
            cursor: pointer;
            width: 40px;
            height: 40px;
            border-radius: 10px;
          }

          .el-icon-caret-bottom {
            cursor: pointer;
            position: absolute;
            right: -20px;
            top: 25px;
            font-size: 12px;
          }
        }
      }
    }
  }

  .downLoadSC {
    a {
      font-size: 12px;
    }
  }
</style>
