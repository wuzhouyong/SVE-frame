<template>
    <div class="switch-center" v-if="systems.length>0">
        <el-dropdown class="switch-center-drop hover-effect" @command="sysCommand">
            <div class="switch-center-title" :val="curSystem.menuId" style="font-size:13px;">
                <svg-icon :class-name="(curSystem.icon)+'ico'" :icon-class="curSystem.icon" />
                {{curSystem.menuName}}
                <i class="el-icon-caret-bottom" />
            </div>
            <el-dropdown-menu slot="dropdown">
                <el-dropdown-item
                    class="switch-center-item"
                    :command="item.menuId"
                    v-for="item in systems"
                    :key="item.menuId"
                >
                    <span>
                        <svg-icon :class-name="(item.icon)+'ico'" :icon-class="item.icon" />
                        {{item.menuName}}
                    </span>
                </el-dropdown-item>
            </el-dropdown-menu>
        </el-dropdown>
        <!-- <span class="switch-split">|</span> -->
    </div>
</template>

<script>
import Cookies from "js-cookie";
import { getRoutersSystem } from "../../api/menu";
import Layout from "@/layout/index";

export default {
    name: "SwitchCenter",
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
            // if (this.curSystem === null)
            //   this.curSystem = res.data[0];
            //console.log("SwitchPath:" + this.$store.state.curos.path);
            //this.$router.push({path: this.$store.state.curos.path});
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

                    //let pat = item.path === null || item.path.indexOf(":") > 0 ? "index" : item.path;
                    //this.$router.push({path: pat});

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
              this.$router.push({ path: `/${pat}` });
            }
            //延时切换路由（？？？）
            // setTimeout(() => {
            //   //console.log(this.$route.path);
            //   // console.log("SwitchPath:" + pat);
            //   //console.log(this.$route.path);
            // }, 100);
        }
    }
};
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
