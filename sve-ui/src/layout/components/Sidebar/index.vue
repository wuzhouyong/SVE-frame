<template>
  <div :class="{'has-logo':showLogo}">
    <!--    <logo v-if="showLogo" :collapse="isCollapse" />-->
    <el-scrollbar wrap-class="scrollbar-wrapper">
      <el-menu
        :default-active="activeMenu"
        :collapse="isCollapse"
        :background-color="variables.menuBg"
        :text-color="variables.menuText"
        :unique-opened="true"
        :active-text-color="variables.menuActiveText"
        :collapse-transition="false"
        mode="vertical"
      >
        <sidebar-item v-for="route in permission_routes" :key="route.path" :item="route" :base-path="route.path"/>
      </el-menu>


      <el-divider>
        <hamburger
          id="hamburger-container"
          :is-active="sidebar.opened"
          class="hamburger-container"
          @toggleClick="toggleSideBar"
        />
      </el-divider>
    </el-scrollbar>


  </div>
</template>

<script>
  import {mapGetters} from 'vuex'
  import Logo from './Logo'
  import SidebarItem from './SidebarItem'
  import variables from '@/assets/styles/variables.scss'
  import Hamburger from "@/components/Hamburger";

  export default {
    components: {SidebarItem, Logo, Hamburger},
    computed: {
      ...mapGetters([
        'permission_routes',
        'sidebar'
      ]),
      activeMenu() {
        const route = this.$route
        const {meta, path} = route
        // if set path, the sidebar will highlight the path you set
        if (meta.activeMenu) {
          return meta.activeMenu
        }
        //console.log("activeMenu:"+path);
        return path
      },
      showLogo() {
        return this.$store.state.settings.sidebarLogo
      },
      variables() {
        return variables
      },
      isCollapse() {
        return !this.sidebar.opened
      }
    },
    methods: {
      toggleSideBar() {
        this.$store.dispatch("app/toggleSideBar");
      }
    }
  }
</script>

<style lang="scss">
  .el-divider {
    .el-divider__text {
      padding: 0px;
    }

    .hamburger-container {
      line-height: 40px;
      /*float: left;*/
      transition: background 0.3s;
      -webkit-tap-highlight-color: transparent;
      text-align: center;

      svg {
        cursor: pointer;
        opacity: 0.5;
      }

      /*&:hover {*/
      /*  background: rgba(0, 0, 0, 0.0);*/
      /*}*/

    }
  }

  .sidebar-container {
    top: 60px !important;
    height: calc(100% - 60px) !important;
    background-color: white !important;
    border-right: 3px solid #a7c8dc;

    .el-scrollbar {
      .scrollbar-wrapper {
        .el-scrollbar__view {
          li.el-submenu, .router-link-active {
            border-bottom: 1px solid #EEEEEE !important;
          }
        }
      }
    }
  }
</style>
