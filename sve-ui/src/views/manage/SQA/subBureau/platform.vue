<template>
  <div class="bureau-platform">
    <el-checkbox
      :indeterminate="isIndeterminate"
      v-model="checkAll"
      @change="handleCheckAllChange"
      class="allCheck"
    >全选</el-checkbox>
    <el-checkbox-group v-model="checkArrs" @change="handleCheckedCitiesChange" class="checkStyle">
      <el-checkbox v-for="i in cities" :label="i.id" :key="i.id">{{i.name}}</el-checkbox>
    </el-checkbox-group>
  </div>
</template>
<script>
import { task_statistics } from "@/config";
export default {
  data() {
    return {
      checkAll: true,
      cities: [],
      isIndeterminate: false,
      checkArrs: []
    };
  },
  created() {
    this.getPlatform();
  },
  methods: {
    getPlatform() {
      this.cities = task_statistics;
      let checkArr = [];
      this.cities.forEach(v => {
        checkArr.push(v.id);
      });
      this.checkArrs = checkArr;
      this.$emit("defaultData", this.checkArrs);
    },
    handleCheckAllChange(val) {
      if (val) {
        let oArr = this.cities;
        let checkArr = [];
        oArr.forEach((v, i, a) => {
          checkArr.push(v.id);
        });
        this.checkArrs = checkArr;
      } else {
        this.checkArrs = [];
      }
      this.isIndeterminate = false;
      this.$emit("isChange", this.checkArrs);
    },
    handleCheckedCitiesChange(value) {
      this.checkArrs = value;
      this.$emit("isChange", this.checkArrs);
      let checkedCount = value.length;
      this.checkAll = checkedCount === this.cities.length;
      this.isIndeterminate =
        checkedCount > 0 && checkedCount < this.cities.length;
    }
  }
};
</script>
<style lang="scss" scope>
//平台选择
.bureau-platform {
  height: auto;
  z-index: 70;
  display: flex;
  padding: 10px 10px 0;
  transition: 1s;
  .allCheck {
    margin-right: 20px;
    color: #fff;
    span {
      font-size: 13px;
    }
  }
  .checkStyle {
    .el-checkbox {
      color: #fff;
      margin-right: 20px;
      margin-bottom: 10px;
      span {
        font-size: 13px;
      }
    }
  }
}
</style>
