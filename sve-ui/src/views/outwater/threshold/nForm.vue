<template>
  <div>
    <el-form>
      <el-form-item label="异常等级 :">
        {{ warnType[newData.warnLevel] }}
      </el-form-item>
      <el-form-item label="异常类型 :">
        {{ newData.warnDesc }}
      </el-form-item>
      <el-form-item label="判定条件 :">
        <span v-html="newData.newTemp"></span>
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
        let newTemp = this.newData.termsTemp.replace("{minValue}", `<input type='text' value="${this.newData.minValue}" class="inputStyle" autocomplete="off" id="minRef"></input>`)
          .replace("{maxValue}", `<input type='text' value="${this.newData.maxValue}" class="inputStyle" autocomplete="off" id="maxRef"></input>`)
        this.$set(this.newData, "newTemp", newTemp);
        this.minValue = this.newData.minValue;
        this.maxValue = this.newData.maxValue;
      },
      immediate: true
    }
  },

  data() {
    return {
      newData: {},
      formList: [],
      minValue: "",
      maxValue: "",
      warnType: ["", "一级异常", "二级异常", "三级异常"]
    }
  },
  created() {},
  methods: {
    submit() {
      if (this.newData.minValue && document.getElementById("minRef")) {
        if (isNaN(Number(document.getElementById("minRef").value))) {
          this.msgError("只能输入数字");
          return;
        }
        if (Number(document.getElementById("minRef").value) < 0) {
          this.msgError("只能输入≥0的数字");
          return;
        }
        this.newData.minValue = Number(document.getElementById("minRef").value);
      }

      if (this.newData.maxValue && document.getElementById("maxRef")) {
        if (isNaN(Number(document.getElementById("maxRef").value))) {
          this.msgError("只能输入数字");
          return;
        }
        if (Number(document.getElementById("maxRef").value) < 0) {
          this.msgError("只能输入≥0的数字");
          return;
        }
        this.newData.maxValue = Number(document.getElementById("maxRef").value);
      }

      pshBoundaryEdit([this.newData]).then(res => {
        if (res.code === 200) {
          this.$emit("successMes");
          this.msgSuccess("修改成功");
        }
      })
    }
  }
}
</script>

<style lang="scss">
.inputStyle {
  width: 60px;
  text-align: center;
  margin: 0 5px;
}
</style>
