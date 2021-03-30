<template>
    <section class="app-main">
        <transition name="fade-transform" mode="out-in">
            <keep-alive :include="cachedViews">
                <router-view />
            </keep-alive>
        </transition>
    </section>
</template>

<script>
import { getToken } from "@/utils/auth";

export default {
    name: "AppMain",
    computed: {
        cachedViews() {
            return this.$store.state.tagsView.cachedViews;
        },
        path() {
            return this.$route.path;
        }
    },
    data() {
        return {
            iFrameWin: {},
            inPath: ""
        };
    }
};
</script>

<style lang="scss" scoped>
.app-main {
    /* 50= navbar  50  */
    // min-height: calc(100vh - 50px);
    height: 100%;
    width: 100%;
    position: relative;
    overflow: hidden;
    box-sizing: border-box;
}

.fixed-header + .app-main {
    padding-top: 50px;
}

.hasTagsView {
    .app-main {
        /* 84 = navbar + tags-view = 50 + 34 */
        // min-height: calc(100vh - 84px);
        height: 100%;
        box-sizing: border-box;
    }

    .fixed-header + .app-main {
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
</style>
