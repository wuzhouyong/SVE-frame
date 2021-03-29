<template>
  <el-form ref="branchform" :model="form">
    <el-form-item>
      <BranchBox v-model="form.list" v-bind="$attrs"></BranchBox>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onSubmit" v-preventClick>提交</el-button>
    </el-form-item>
  </el-form>
</template>
<script>
import {
  getSourcetagList,
  sourcecorrelationfj,
  sourcetDList
} from "@/api/composite/source";
import BranchBox from "./branchbox";
import {noticeOperaAdd} from "@/api/system/config";

export default {
  components: {BranchBox},
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
      }
    };
  },
  created() {
    // this.getSourceTagList();
  },
  watch: {
    oSource: {
      handler(v) {
        if (v) this.getDList();
      },
      immediate: true
    }
  },
  methods: {
    onSubmit() {
      sourcecorrelationfj(this.form).then((res) => {
        let journal = {
          module: "综合管理 → 污染源列表",
          action: `(${this.fullName})变更任务设置(分局)操作`,
          operaResult: res.code === 200 ? "操作成功" : "操作失败",
          request: "/enterprise/source/correlationfj"
        }
        noticeOperaAdd(journal);
        if (res.code === 200) {
          this.msgSuccess("平台关联配置成功!");
          this.$emit("corres");
        } else {
          this.msgError("平台关联失败!");
        }
      });
    },
    // //平台列表数据
    // getSourceTagList() {
    //   getSourcetagList().then((res) => {
    //     if (res.code === 200) {
    //       this.systemList = res.data;
    //     }
    //   });
    // },
    //企业与平台关联
    getDList() {
      sourcetDList({pfSourceId: this.oSource}).then((res) => {
        this.form.list = res.data;
      });
    }
  }
};
</script>
