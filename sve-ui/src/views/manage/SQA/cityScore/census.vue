<template>
  <div class="rank-table">
    <el-radio-group v-model="showType">
      <el-radio-button label="0">全部</el-radio-button>
      <el-radio-button label="1">涉水责考评分</el-radio-button>
      <el-radio-button label="2">涉气责考评分</el-radio-button>
    </el-radio-group>
    <div class="cTable">
      <allTem v-if="showType==='0'"></allTem>
      <waterTem v-else-if="showType==='1'"></waterTem>
      <gasTem v-else-if="showType==='2'"></gasTem>
    </div>
  </div>
</template>

<script>
import gasTem from "./gas";
import waterTem from "./water";
import allTem from "./all";

export default {
  components: { gasTem, waterTem, allTem },
  data() {
    return {
      showType: "0"
    }
  },
  created() {},
  methods: {}
}
</script>

<style lang="scss" scoped>
.rank-table {
  padding: 10px 0;
  flex: 1;
  display: flex;
  flex-direction: column;
  overflow: hidden;

  .cTable {
    flex: 1;
  }
}
</style>
