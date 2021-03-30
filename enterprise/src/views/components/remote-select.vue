<template>
  <el-select
    v-model="current"
    :placeholder="placeholder"
    size="mini"
    clearable
    @change="select_change"
  >
    <el-option :label="i[name]" :key="i[id]" :value="i[id]" v-for="i in data"></el-option>
  </el-select>
</template>

<script>
export default {
  name: "remote-select",
  props: {
    api: {
      type: Function,
    },
    id: {
      type: String,
    },
    name: {
      type: String,
    },
    value: {
      type: String | Number,
    },
    placeholder: {
      type: String,
    },
  },
  data() {
    return {
      data: [],
      current: null,
    };
  },
  watch: {
    value: {
      immediate: true,
      handler() {
        this.current = this.value;
        if (!this.value) {
          this.api().then((res) => {
            // console.log("res",res)
            this.data = res.rows || res.data;
            if (this.data && this.data.length > 0) {
              this.$emit("input", this.data[0].id);
            }
          });
        }
      },
    },
  },
  methods: {
    select_change(v) {
      this.$emit("input", v);
    },
  },
};
</script>

<style>
</style>