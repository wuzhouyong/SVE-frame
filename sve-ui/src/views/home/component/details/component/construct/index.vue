<template>
  <div class="construct-wk">
    <el-form label-width="150px">
      <el-form-item label="施工进度：">{{constructionStatus[model.constructionStatus]}}</el-form-item>
      <el-form-item label="施工单位名称：">{{model.constructionUnit}}</el-form-item>
      <el-form-item label="施工单位联系人：">{{model.constructionPerson}}</el-form-item>
      <el-form-item label="施工单位联系电话：">{{model.constructionPhone}}</el-form-item>
      <el-form-item label="施工完成日期：">{{model.completeDate}}</el-form-item>
      <el-form-item label="验收日期：">{{model.acceptanceDate}}</el-form-item>
    </el-form>
  </div>
</template>
<script>
import { getConstructionModel } from "@/api/home/index";
export default {
  props: ["platFormPath", "oId"],
  data() {
    return {
      constructionStatus: [
        "未开展",
        "施工中",
        "已接入",
        "接入完成",
        "待验收",
        "",
        "已验收"
      ],
      model: {}
    };
  },
  watch: {
    platFormPath: {
      handler(v) {
        if (v) {
          this.getData();
        }
      }
    }
  },
  methods: {
    getData() {
      getConstructionModel(this.platFormPath, this.oId).then(res => {
        this.model = res.data;
      });
    }
  }
};
</script>
<style lang="scss">
.construct-wk {
  .el-form-item__label {
    color: #fff;
  }
  .el-form-item__content {
    color: #fff;
  }
}
</style>