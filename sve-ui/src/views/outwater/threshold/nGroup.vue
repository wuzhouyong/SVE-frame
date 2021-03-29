<template>
  <div class="group-style">
    <el-form>
      <el-form-item label="异常等级 :">
        {{ warnType[newData.warnLevel] }}
      </el-form-item>
      <el-form-item label="异常类型 :">
        {{ newData.warnDesc }}
      </el-form-item>
      <el-form-item label="判定条件 :">
        <template v-for="(i,index) in newData.list">
          <span v-html="i.newTemp" :key="i.index"></span><br/>
        </template>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { pshBoundaryEdit } from "@/api/outwater"

export default {
  props: {
    oData: {
      type: Object,
      default: () => {}
    }
  },
  watch: {
    oData: {
      handler(v) {
        this.newData = JSON.parse(JSON.stringify(v));
        let oList = this.newData.list;
        oList.forEach(i => {
          let newTemp = i.termsTemp.replace("{minValue}", `<input type='text' value="${i.minValue}" class="inputStyle" autocomplete="off" id="min_${i.formulaId}"></input>`)
            .replace("{maxValue}", `<input type='text' value="${i.maxValue}" class="inputStyle" autocomplete="off" id="max_${i.formulaId}"></input>`)
          this.$set(i, "newTemp", newTemp);
        });
      },
      immediate: true
    }
  },
  data() {
    return {
      warnType: ["", "一级异常", "二级异常", "三级异常"],
      newData: []
    }
  },
  created() {},
  methods: {
    submit() {
      let iCheck = true;
      this.newData.list.forEach(i => {
        if (i.minValue && document.getElementById(`min_${i.formulaId}`)) {
          if (isNaN(Number(document.getElementById(`min_${i.formulaId}`).value))) {
            this.msgError("只能输入数字");
            iCheck = false;
            return;``
          }
          if (Number(document.getElementById(`min_${i.formulaId}`).value) < 0) {
            this.msgError("只能输入≥0的数字");
            iCheck = false;
            return;
          }
          i.minValue = Number(document.getElementById(`min_${i.formulaId}`).value);
        }

        if (i.maxValue && document.getElementById(`max_${i.formulaId}`)) {
          if (isNaN(Number(document.getElementById(`max_${i.formulaId}`).value))) {
            this.msgError("只能输入数字");
            iCheck = false;
            return;
          }
          if (Number(document.getElementById(`max_${i.formulaId}`).value) < 0) {
            this.msgError("只能输入≥0的数字");
            iCheck = false;
            return;
          }
          i.maxValue = Number(document.getElementById(`max_${i.formulaId}`).value);
        }
      })
      if (iCheck) {
        pshBoundaryEdit(this.newData.list).then(res => {
          if (res.code === 200) {
            this.$emit("successMes");
            this.msgSuccess("修改成功");
          }
        })
      }
    }
  }
}
</script>

<style lang="scss" scoped>
.group-style {
  /deep/ .el-form-item {
    display: flex;
  }
}
</style>
