<template>
  <div class="switch-center">
    <el-dropdown class="switch-center-drop hover-effect" @command="sysCommand">
      <div class="switch-center-title" :val="curSystem.id">
        <svg-icon :class-name="(curSystem.ico)+'ico'" :icon-class="curSystem.ico"/>
        {{curSystem.name}}<i class="el-icon-caret-bottom"/>
      </div>
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item class="switch-center-item" :command="item.id" v-for="item in system" :key="item.id">
        <span>
          <svg-icon :class-name="(item.ico)+'ico'" :icon-class="item.ico"/>
          {{item.name}}
        </span>
        </el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
    <span class="switch-split">|</span>
  </div>
</template>

<script>
  export default {
    name: "SwitchCenter",
    data() {
      return {
        system: [
          {id: 10, name: "大数据1", ico: "search"},
          {id: 1, name: "大数据2", ico: "search"},
          {id: 2, name: "大数据3", ico: "search"}
        ],
        curSystem: {
          id: 0,
          name: "当前系统",
          ico: "monitor"
        }
      }
    },
    created() {
      if (this.system.length > 0) {
        this.curSystem = this.system[0].id;
        this.curSystem = this.system[0];
      }
    },
    methods: {
      sysCommand(command) {
        this.$confirm('切换系统后当前打开的标签面将清空，您确定切换吗？').then(() => {
          for (let i = 0; i < this.system.length; i++) {
            if (this.system[i].id === command) {
              this.curSystem = this.system[i];

              //清空tag
              this.$store.dispatch('tagsView/delAllViews').then(({visitedViews}) => {
                this.$router.push({ path: "/index" });
              })
            }
          }
        });

      }
    }
  }
</script>
<style lang="scss" scoped>
  .switch-center {

    float: left;
    cursor: pointer;

    .switch-center-title {
      color: #42b983;

      svg {
        margin-right: 5px;
      }

      i {
        margin-left: 5px;
      }
    }

    .switch-split {
      padding-left: 10px;
      padding-right: 10px;
      color: #b5b5b5;
    }
  }

  .switch-center-item {
    font-size: 14px !important;
    padding: 5px 15px 5px 15px;
  }

</style>
