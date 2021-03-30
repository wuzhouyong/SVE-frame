<template>
  <div class="base">
    <el-tabs v-model="active_sys" @tab-click="tabClick" v-show="sys_items.length>1">
      <el-tab-pane v-for="sys in sys_items" :key="sys.path" :label="sys.name" :name="sys.path"></el-tab-pane>
    </el-tabs>
    <router-view :key="timestamp"/>
  </div>
</template>

<script>
import { system_config, base_config } from '@/config'
import { getSourceTags } from '@/api/menu'

export default {
  data() {
    return {
      timestamp: null,
      active_sys: null,
      sys_items: [],
      sys_config: null,
      base_path: null,
      need_redirect: true
    }
  },
  watch: {
    '$route.path': {
      immediate: true,
      handler(v) {
        const base_path = Object.keys(base_config).find((p) => {
          return v.startsWith(p)
        })

        if (base_path === this.base_path) {
          if (v.length === base_path.length) {
            this.redirect()
          }
        } else {
          this.sys_config = base_config[base_path].systems
          this.base_path = base_path
          this.timestamp = new Date().getMilliseconds()
          this.load_systems()
        }
      }
    }
    // active_sys(v) {
    //     if (v && v !== "0") {
    //         this.redirect();
    //     }
    // }
  },
  methods: {
    load_systems() {
      getSourceTags().then((res) => {
        if (res.data) {
          this.sys_items = Object.keys(system_config)
            .filter((f) => res.data.some((s) => s === f))
            .filter((f) => this.sys_config.some((s) => s === f))
            .map((m) => system_config[m])
          this.active_sys = null

          if (this.sys_items.length > 0) {
            this.active_sys = this.sys_items[0].path
            this.redirect()
          }
        }
      })
    },
    tabClick(tab) {
      this.redirect()
    },
    redirect() {

      this.$router.push({
        path: `${this.base_path}/${this.active_sys}`
      })
    }
  }
}
</script>

<style lang="scss">
.base {
  width: 100%;
  height: calc(100% - 36px);
  overflow: hidden;
  padding: 10px;
  display: flex;
  flex-direction: column;
}
</style>
