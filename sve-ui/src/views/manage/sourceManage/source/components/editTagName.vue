<template>
  <div class="editTag">
    <span @click="changeTag" :title="i.tagHeadName" v-show="!edit">{{ i.tagHeadName }}</span>
    <el-form ref="form" :model="form" label-width="80px" v-if="edit" label-position="top">
      <el-form-item label="任务名称">
        <el-input v-show="edit" v-model="form.tagName" placeholder="平台名称" style="width:100%"></el-input>
      </el-form-item>
      <el-form-item label="排序">
        <el-input v-show="edit" v-model="form.orderBy" placeholder="排序" style="width:100%"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" size="mini" @click="editTagName">提交修改</el-button>
        <el-button size="mini" @click="edit=false">取消修改</el-button>
      </el-form-item>
    </el-form>
    <!--    <i-->
    <!--      class="el-icon-check"-->
    <!--      v-if="edit"-->
    <!--      @click="editTagName"-->
    <!--      title="提交修改"-->
    <!--    ></i>-->
    <i
      class="el-icon-edit"
      v-if="!edit"
      @click="editTagName"
      title="修改信息"
      v-hasPermi="['edit:tag:name']"
    ></i>
  </div>
</template>
<script>
import { updateTagName } from "@/api/composite/source";

export default {
  props: {
    i: {
      type: Object,
      default: () => {}
    }
  },
  watch: {
    i: {
      handler(v) {
        if (v) {
          this.form.tagName = v.tagHeadName;
          this.form.orderBy = v.orderBy ? v.orderBy : 0;
        }
      },
      immediate: true
    }
  },
  data() {
    return {
      edit: false,
      form: {
        tagName: "",
        orderBy: ""
      }
    };
  },
  methods: {
    changeTag() {
      this.$emit("changeTagHead", this.i.tagHeadId);
    },
    editTagName() {
      this.edit = !this.edit;
      if (!this.edit) {
        if (this.form.tagName.trim() === "") {
          this.msgError("任务名称不能为空");
          return;
        } else {
          this.form.tagName = this.form.tagName.trim();
        }
        if (this.form.orderBy === "") {
          this.form.orderBy = "0";
        }
        updateTagName(this.i.tagHeadId, this.form).then(
          (res) => {
            if (res.code === 200) {
              this.$emit("editSuccess");
            }
          }
        );
      }
    }
  }
};
</script>
<style lang="scss">
.editTag {
  width: 100%;
  min-height: 30px;
  //line-height: 30px;
  height: auto;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0px 5px;

  .el-form {
    .el-form-item {
      margin-bottom: 3px;
    }

    label {
      font-size: 12px;
      line-height: 22px;
      padding: 0;
    }
  }

  span {
    height: 100%;
    width: 160px;
  }

  i {
    flex: 1;
    height: 100%;
    text-align: right;
    display: flex;
    align-items: center;
    font-size: 15px;
  }

  .el-input {
    margin-right: 5px;

    input {
      height: 20px;
      line-height: 20px;
    }
  }
}
</style>
