<template>
  <div class="center-menu" v-if="systems.length>0">
    <ul>
      <li @click="" style="background-color: #37c4d0;">
        <router-link to="/bigScreen" style="font-size:12px;">
          <svg-icon :class-name="'dashboardico'" :icon-class="'dashboard'"/>
          <span>一张图</span>
        </router-link>
      </li>

      <li v-for="item in systems" :key="item.menuId" @click="sysCommand(item.menuId)"
          :class="{'curActive':curSystem.menuId == item.menuId}" :title="item.menuName">
        <svg-icon :class-name="(item.icon)+'ico'" :icon-class="item.icon"/>
        <span>{{ item.menuName.substring(0, 4) }}</span>
      </li>

    </ul>
  </div>
</template>

<script>
import Cookies from "js-cookie";
import {getRoutersSystem} from "../../api/menu";
import Layout from "@/layout/index";

export default {
  name: "centerMenu",
  data() {
    return {
      systems: [],
      curSystem: null
    };
  },
  created() {
    getRoutersSystem().then(res => {
      let systemKey = Cookies.get("system-key");
      this.systems = res.data;
      this.systems.forEach(item => {
        if (item.menuId.toString() === systemKey) {
          this.curSystem = item;
        }
      });
      this.switchRouter(true);
    });
  },
  methods: {
    sysCommand(command) {
      if (this.$store.state.curos.key === command) return;

      this.systems.forEach(async item => {
        if (item.menuId.toString() === command.toString()) {
          this.curSystem = item;
          Cookies.set("system-key", item.menuId);
          Cookies.set("system-name", item.path);
          this.$store.state.curos.key = item.menuId;
          this.$store.state.curos.path =
            item.path === null ? "index" : item.path;

          const accessRoutes = await this.$store.dispatch(
            "GenerateRoutes",
            {}
          );
          // console.log(accessRoutes);
          if (!this.$store.state.curos.systems.includes(command)) {
            // console.log("add routes:" + command);
            this.$store.state.curos.systems.push(command);
            this.$router.addRoutes(accessRoutes); // 动态添加可访问路由表
          }

          //清空tag数据
          //if(!this.isFirst && this.$store.state.curos.path === "index") {
          await this.$store.dispatch("tagsView/delAllViews");
          this.switchRouter(false);
        }
      });
    },
    switchRouter(is_init) {
      let curPath = this.$store.state.curos.path;
      let pat = curPath === null || curPath.indexOf(":") > 0 ? "" : curPath;
      // this.$router.push({ path: pat });
      if (pat.startsWith("/")) {
        pat = "";
      }
      if (!is_init) {
        this.$router.push({path: `/${pat}`});
      }
    }
  }
};
</script>
<style lang="scss" scoped>
.center-menu {
  ul {
    margin: 0px;
    padding: 0px;

    li {
      cursor: pointer;
      padding: 10px 10px;
      margin: 0px;
      list-style: none;
      float: right;
      line-height: initial;
      height: 60px;
      text-align: center;

      &:hover {
        background: rgba(98, 168, 209, 0.5);
        box-shadow: inset 0 0 4px rgba(255, 255, 255, 1), 0 0 1px rgba(255, 255, 255, 0);

      }

      .svg-icon {
        font-size: 20px;
      }

      span {
        display: block;
        line-height: 22px;
        font-size: 13px;
      }
    }
  }
}

.curActive {
  background: #62A8D1;
  border-bottom: 4px solid #FFBD04;
}
</style>
