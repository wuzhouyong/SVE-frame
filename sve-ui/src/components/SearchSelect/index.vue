<template>
  <el-select
    :value="value"
    filterable
    remote
    reserve-keyword
    :placeholder="place"
    :remote-method="remoteMethod"
    size="mini"
    style="width:100%"
    :loading="loading"
    @change="onChange"
  >
    <el-option v-for="item in options" :key="item.id" :label="item.name" :value="item.id"></el-option>
  </el-select>
</template>
<script>
import { collectSelectList, pointSelectList } from "@/api/outwater";
export default {
  props: {
    value: {
      type: String,
      default: ""
    },
    place: {
      type: String,
      default: ""
    },
    isType: {
      type: Number,
      default: 0
    }
  },
  data() {
    return {
      options: [],
      list: [],
      loading: false,
      states: []
    };
  },
  watch: {
    value: {
      handler(v) {
        if (v) {
          let val;
          if (this.isType === 0) {
            val = { collectId: val };
          } else if (this.isType === 0) {
            val = { checkPointId: v };
          }
          this.getList(val);
        }
      },
      immediate: true
    }
  },
  methods: {
    getList(v = {}) {
      if (this.isType === 0) {
        collectSelectList(v).then(res => {
          if (res.code === 200) {
            this.options = res.data;
          }
        });
      } else if (this.isType === 1) {
        pointSelectList(v).then(res => {
          if (res.code === 200) {
            this.options = res.data;
          }
        });
      }
    },
    remoteMethod(query) {
      if (query !== "") {
        var oVal;
        if (this.isType === 0) {
          oVal = { collectName: query };
        } else if (this.isType === 1) {
          oVal = { checkPointName: query };
        }
        this.getList(oVal);
      }
    },
    onChange(v) {
      this.$emit("input", v);
      this.$parent.$emit("validate", v);
    }
  }
};
</script>