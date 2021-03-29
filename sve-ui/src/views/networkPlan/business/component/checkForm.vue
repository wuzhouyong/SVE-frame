<template>
  <div>
    <el-form ref="checkform" :model="form" :rules="rules" label-width="170px">
      <el-form-item label="排放口类型" prop="checkpointType">
        <el-select
          v-model="form.checkpointType"
          placeholder="排放口类型"
          style="width:100%"
          @change="onChange"
          :disabled="isDisable"
        >
          <el-option
            :label="i.dictLabel"
            :value="i.dictValue"
            v-for="i in portType"
            :key="i.dictValue"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="生产工艺/设备名称" prop="processFlow">
        <el-input v-model="form.processFlow" placeholder="生产工艺/设备名称" />
      </el-form-item>
      <el-form-item label="排污许可证排放口编号" prop="emissionCode">
        <el-input v-model="form.emissionCode" placeholder="排污许可证排放口编号" />
      </el-form-item>
      <el-form-item label="监控点名称" prop="checkpointDesc">
        <el-input v-model="form.checkpointDesc" placeholder="监控点名称" />
      </el-form-item>
      <el-form-item
        :label="checkList.length>0?'监控污染物':''"
        :rules="[{ required: true, message: '请输入监控污染物', trigger: 'blur' }]"
      >
        <vCard :checkList="checkList" v-model="form.elements"></vCard>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import vCard from "./vCard";
export default {
  components: { vCard },
  props: ["editRows"],
  watch: {
    editRows: {
      handler(v) {
        if (v) {
          this.isDisable = v.checkpointId ? true : false;
          this.form = Object.assign({}, v);
          this.getCheck(v.checkpointType);
        }
      },
      immediate: true
    }
  },
  data() {
    return {
      isDisable: false,
      form: {
        elements: []
      },
      rules: {
        checkpointType: [
          { required: true, message: "排放口类型不能为空", trigger: "blur" }
        ],
        processFlow: [
          {
            required: true,
            message: "生产工艺/设备名称不能为空",
            trigger: "blur"
          }
        ],
        emissionCode: [
          { required: true, message: "污染源名称不能为空", trigger: "blur" }
        ],
        checkpointDesc: [
          { required: true, message: "监控点名称不能为空", trigger: "blur" }
        ]
      },
      portType: [],
      checkList: []
    };
  },
  created() {
    this.getDicts("discharge_port_type").then(response => {
      this.portType = response.data;
    });
  },
  methods: {
    getCheck(v) {
      this.getDicts(v).then(res => {
        this.checkList = [];
        this.$nextTick(() => {
          this.checkList = res.data;
          this.form.elements = [];
        });
      });
    },
    onChange(v) {
      this.getCheck(v);
    },
    submitForm() {
      this.$refs["checkform"].validate(valid => {
        if (!valid) {
          this.msgError("请完整填写必填项!");
          return;
        }
        this.$emit("checkPoints", this.form);
      });
    }
  }
};
</script>
<style lang="scss">
.checkbox-card {
  margin-bottom: 5px;
  .el-card__body {
    padding: 5px;
    p.pointType {
      margin: 0px;
    }
  }
}
</style>