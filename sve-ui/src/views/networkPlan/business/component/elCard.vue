<template>
  <el-card class="checkbox-card" shadow="never">
    <el-row>
      <el-col :xl="3" :md="3" :xs="24">
        <p class="pointType">{{isIds?isIds.dictLabel:''}}</p>
      </el-col>
      <el-col :xl="4" :md="4" :xs="24">
        <p class="c-title">监控执行标准</p>
      </el-col>
      <el-col :xl="8" :md="8" :xs="24">
        <el-input v-model="lawStandard" placeholder="请输入内容" @change="onChange"></el-input>
      </el-col>
      <el-col :xl="3" :md="3" :xs="24">
        <p class="c-title">接入时间</p>
      </el-col>
      <el-col :xl="6" :md="6" :xs="24">
        <el-select v-model="accessDate" @change="onChange">
          <el-option label="2020" value="2020"></el-option>
          <el-option label="2019" value="2019"></el-option>
          <el-option label="2018" value="2018"></el-option>
          <el-option label="2017" value="2017"></el-option>
          <el-option label="2016" value="2016"></el-option>
        </el-select>
      </el-col>
    </el-row>
  </el-card>
</template>
<script>
export default {
  props: {
    value: {
      type: Object
    },
    isIds: {
      type: Object
    }
  },
  data() {
    return {
      lawStandard: "",
      accessDate: "2020"
    };
  },
  created() {
    if (this.value !== undefined) {
      if (this.value.lawStandard) {
        this.lawStandard = this.value.lawStandard;
      }
      if (this.value.accessDate) {
        this.accessDate = this.value.accessDate;
      }
    }
  },
  methods: {
    onChange() {
      let obj = {
        elementName: this.isIds.dictLabel,
        elementId: this.isIds.dictValue,
        lawStandard: this.lawStandard,
        accessDate: this.accessDate + "-01-01"
      };
      this.$emit("change", obj);
      this.$parent.$emit("validate", obj);
    }
  }
};
</script>
<style lang="scss">
.checkbox-card {
  p.pointType {
    font-size: 12px;
  }
  p.c-title {
    font-size: 12px;
    margin: 0;
    padding: 0;
    text-align: center;
  }
}
</style>