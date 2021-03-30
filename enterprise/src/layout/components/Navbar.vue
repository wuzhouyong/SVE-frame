<template>
  <div class="navbar">
    <hamburger
      id="hamburger-container"
      :is-active="sidebar.opened"
      class="hamburger-container"
      @toggleClick="toggleSideBar"
    />
    <!-- <switch-center /> -->
    <breadcrumb id="breadcrumb-container" class="breadcrumb-container"/>
    <div class="right-menu">

      <div class="right-menu-container">
        <span style="color: #606266; font-size: 14px">{{ username }}</span>
        <div style="line-height:26px;margin-top:5px;margin-right:4px;" v-if="!isThemeFinish">
          <el-badge is-dot class="item" style="line-height:26px;">
            <el-link icon="el-icon-edit-outline" :underline="false" @click="isTheme">评分问卷</el-link>
          </el-badge>
        </div>
        <span><el-link
          icon="el-icon-notebook-2"
          href="/static/东莞市在线监控平台企业端操作手册.pdf"
        >操作手册</el-link
        ></span>
        <changePwd></changePwd>
        <bindPhone :isBinds="isBinds"></bindPhone>
        <queryScores></queryScores>
        <span
        ><el-link
          icon="el-icon-lock"
          :underline="false"
          @click.native="logout"
        >退出登录</el-link
        ></span
        >
      </div>
    </div>
    <el-dialog
      title="问卷列表"
      :visible.sync="themeVisible"
      :close-on-click-modal="false"
      custom-class="question-dialog"
      width="600px"
    >
      <nTheme :themeData="themeData" @getNewList="getNewList"></nTheme>
    </el-dialog>
  </div>
</template>

<script>
import {mapGetters} from "vuex";
import Breadcrumb from "@/components/Breadcrumb";
import Hamburger from "@/components/Hamburger";
import Screenfull from "@/components/Screenfull";
import SizeSelect from "@/components/SizeSelect";
import Search from "@/components/HeaderSearch";
import {mochaQuestionAireTheme} from "@/api/mocha";
import nTheme from "./score/theme";

export default {
  components: {
    Breadcrumb,
    Hamburger,
    Screenfull,
    SizeSelect,
    Search,
    nTheme,
    changePwd: () => import("./changePwd"),
    //绑定或更改绑定手机
    bindPhone: () => import("./bindPhone/index.vue"),
    // 打分历史查看
    queryScores: () => import("./queryScores/index.vue")
  },
  data () {
    return {
      themeVisible: false,
      themeData: [],
      isThemeFinish: false
    };
  },
  created () {
    this.getAireTheme();
    // console.log("this.$store.getters", this.$store.getters);
  },
  computed: {
    ...mapGetters(["sidebar"]),
    username () {
      return this.$store.getters.name;
    },
    isBinds () {
      return this.$store.getters.isBinding;
    },
    setting: {
      get () {
        return this.$store.state.settings.showSettings;
      },
      set (val) {
        this.$store.dispatch("settings/changeSetting", {
          key: "showSettings",
          value: val
        });
      }
    },
  },
  methods: {
    getNewList () {
      this.getAireTheme();
    },
    getAireTheme () {
      mochaQuestionAireTheme().then(res => {
        this.themeVisible = true;
        this.themeData = res.data;
        this.isThemeFinish = this.themeData.every(i => i.isFinish === 1);
        if (this.isThemeFinish) {
          this.themeVisible = false;
        } else {
          this.themeVisible = true;
        }
      });
    },
    toggleSideBar () {
      this.$store.dispatch("app/toggleSideBar");
    },
    async logout () {
      this.$confirm("确定注销并退出系统吗？", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        this.$store.dispatch("LogOut").then(() => {
          location.reload();
        });
      });
    },
    isTheme () {
      if (this.themeData.length > 0) {
        this.themeVisible = true;
      } else {
        this.msgError("当前没有评分问卷!");
      }
    }
  }
};
</script>
<style lang="scss">
.question-dialog {
  /deep/ .el-dialog__body {
    padding: 5px 20px;
  }
}

</style>
<style lang="scss" scoped>

.navbar {
  height: 50px;
  overflow: hidden;
  position: relative;
  background: #fff;
  box-shadow: 0 1px 4px rgba(0, 21, 41, 0.08);

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

    &:focus {
      outline: none;
    }

    .right-menu-container {
      display: flex;
      flex-flow: row;
      justify-content: space-between;

      span {
        padding: 0 10px;
      }
    }

    // .right-menu-item {
    //     display: inline-block;
    //     padding: 0 8px;
    //     height: 100%;
    //     font-size: 18px;
    //     color: #5a5e66;
    //     vertical-align: text-bottom;

    //     &.hover-effect {
    //         cursor: pointer;
    //         transition: background 0.3s;

    //         &:hover {
    //             background: rgba(0, 0, 0, 0.025);
    //         }
    //     }
    // }

    // .avatar-container {
    //     margin-right: 30px;

    //     .avatar-wrapper {
    //         margin-top: 5px;
    //         position: relative;

    //         .user-avatar {
    //             cursor: pointer;
    //             width: 40px;
    //             height: 40px;
    //             border-radius: 10px;
    //         }

    //         .el-icon-caret-bottom {
    //             cursor: pointer;
    //             position: absolute;
    //             right: -20px;
    //             top: 25px;
    //             font-size: 12px;
    //         }
    //     }
    // }
  }
}
</style>
