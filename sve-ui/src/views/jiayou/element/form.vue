<template>
  <form-dialog
    :title="title"
    :values.sync="form"
    :rules="rules"
    label-width="130px"
    :visible.sync="dialogVisible"
    @submit="submitForm"
  >
    <el-form-item label="污染源" prop="sourceId">
      <SelectSource
        v-model="form.sourceId"
        :data="sourceList"
        @input="watchSource"
        placeholder="请输入污染源"
        isPlatName="jiayou"
      ></SelectSource>
    </el-form-item>
    <el-form-item label="数采名称" prop="collectId">
      <!-- <SearchSelect v-model="form.collectId" place="请输入数采名称" :isType="isType"></SearchSelect> -->
      <el-select v-model="form.collectId" placeholder="请输入数采名称" style="width:100%">
        <el-option :label="i.name" :value="i.id" v-for="i in checkpoints" :key="i.id"></el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="因子类型" prop="eleTypeId">
      <el-select
        v-model="form.eleTypeId"
        placeholder="请选择因子类型"
        style="width:100%"
        @change="eleTypeChange"
      >
        <el-option :label="`${i.name} ( ${i.id} )`" :value="i.id" v-for="i in elemType" :key="i.id"></el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="因子编码" prop="eleCode">
      <el-input v-model="form.eleCode" placeholder="请输入因子编码" maxlength="6" show-word-limit>
        <!-- <template slot="prepend">{{form.eleTypeId}}</template> -->
      </el-input>
    </el-form-item>
    <el-form-item label="因子名称" prop="eleDesc">
      <el-input v-model="form.eleDesc" placeholder="请输入因子名称"/>
    </el-form-item>
    <el-form-item label="安装位置" prop="installLocation">
      <el-input v-model="form.installLocation" autocomplete="off" placeholder="安装位置"></el-input>
    </el-form-item>
  </form-dialog>
</template>
<script>
import {
  sourceList,
  checkpointSelect,
  elementType,
  elementAdd,
  elementEdit
} from "@/api/jiayou";
import FormDialog from "@/views/components/form-dialog";
import SelectSource from "@/components/SelectSource";

export default {
  name: "element-form",
  components: { FormDialog, SelectSource },
  props: {
    title: {
      type: String
    },
    values: {
      type: Object,
      default: () => ({})
    },
    visible: {
      type: Boolean,
      default: false
    }
  },
  data() {
    const validateElement = (rule, value, callback) => {
      if (value.substring(0, 4) !== this.form.eleTypeId) {
        callback(new Error("因子编码前四位必须和因子类型对应!"));
      } else if (value.length !== 6) {
        callback(new Error("因子编码长度为6位!"));
      } else {
        callback();
      }
    };
    return {
      sourceList,
      checkpoints: [],
      //因子类型
      elemType: [],
      form: Object.assign({}, this.values),
      // 表单校验
      rules: {
        sourceId: [
          {
            required: true,
            message: "污染源不能为空",
            trigger: "blur"
          }
        ],
        collectId: [
          {
            required: true,
            message: "数采名称不能为空",
            trigger: "blur"
          }
        ],
        eleDesc: [
          {
            required: true,
            message: "因子名称不能为空",
            trigger: "blur"
          }
        ],
        eleTypeId: [
          {
            required: true,
            message: "因子类型不能为空",
            trigger: "blur"
          }
        ],
        eleCode: [
          {
            required: true,
            message: "因子编码不能为空",
            trigger: "blur"
          },
          { validator: validateElement }
        ]
      }
    };
  },
  computed: {
    dialogVisible: {
      get() {
        return this.visible || false;
      },
      set(v) {
        this.$emit("update:visible", v);
      }
    }
  },
  watch: {
    values() {
      this.form = Object.assign({}, this.values);
      if (this.form.sourceId) {
        this.getCheckpoints(this.form.sourceId);
      }
      elementType().then((res) => {
        if (res.code === 200) this.elemType = res.rows;
      });
    }
  },
  methods: {
    watchSource(v) {
      if (this.form.cpElemId) {
        this.getCheckpoints(v);
        this.form.collectId = null;
      } else {
        this.getCheckpoints(v);
      }
    },
    getCheckpoints(v) {
      checkpointSelect(v).then((res) => {
        if (res.code === 200) this.checkpoints = res.rows;
      });
    },
    eleTypeChange(v) {
      //    this.$set(this.form, "eleCode", v);;
      this.$set(this.form, "eleCode", v);
    },
    submitForm(v) {
      if (this.form.cpElemId != undefined) {
        elementEdit(this.form).then((res) => {
          if (res.code === 200) {
            this.msgSuccess("修改成功");
            this.$emit("completed");
          } else {
            this.msgError(res.message);
          }
        });
      } else {
        elementAdd(this.form).then((res) => {
          if (res.code === 200) {
            this.msgSuccess("添加成功");
            this.$emit("completed");
          } else {
            this.msgError(res.message);
          }
        });
      }
    }
  }
};
</script>
