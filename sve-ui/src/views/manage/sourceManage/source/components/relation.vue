<template>
  <div v-loading="loading">
    <el-form ref="reboxform" :model="form">
      <el-form-item>
        <rebox v-model="form.list" v-bind="$attrs"></rebox>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit" v-preventClick>提交</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import {
  getSourcetagList,
  sourcecorrelation,
  sourcetDList
} from "@/api/composite/source";
import rebox from "./rebox";
import {noticeOperaAdd} from "@/api/system/config";

export default {
  components: { rebox },
  props: {
    oSource: {
      type: String
    },
    fullName: {
      type: String
    }
  },
  data() {
    return {
      form: {
        list: []
      },
      // systemList: [],
      loading: false
    };
  },
  // watch: {
  //   checkboxGroup: {
  //     handler(v) {
  //       this.form.list = v;
  //     },
  //     immediate: true
  //   }
  // },
  created() {
    if (this.oSource) {
      this.loading = true;
      sourcetDList({ pfSourceId: this.oSource }).then((res) => {
        this.form.list = res.data;
        this.loading = false;
      });
    }
  },
  methods: {
    // getSourceTagList() {
    //   getSourcetagList().then((res) => {
    //     if (res.code === 200) {
    //       this.systemList = res.data;
    //     }
    //   });
    // },

    onSubmit() {
      this.$refs["reboxform"].validate((valid) => {
        if (!valid) {
          this.msgError("请完整填写必填项!");
          return;
        }
        sourcecorrelation(this.form).then((res) => {
          let journal = {
            module: "综合管理 → 污染源列表",
            action: `(${this.fullName})变更任务设置操作`,
            operaResult: res.code === 200 ? "操作成功" : "操作失败",
            request: "/enterprise/source/correlation"
          }
          noticeOperaAdd(journal);

          if (res.code === 200) {
            this.msgSuccess("平台关联配置成功!");
            this.$emit("corre");
          } else {
            this.msgError("平台关联失败!");
          }
        });
      });
    }
  }
};
</script>
