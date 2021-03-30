<template>
  <form-dialog
    :title="title"
    :values.sync="form"
    :rules="rules"
    label-width="130px"
    :visible.sync="dialogVisible"
    @submit="submitForm"
  >
    <el-form-item label="数采名称" prop="collectId">
      <!-- <SearchSelect v-model="form.collectId" place="请输入数采名称" :isType="isType"></SearchSelect> -->
      <el-select v-model="form.collectId" placeholder="请输入数采名称" style="width:100%">
        <el-option :label="i.name" :value="i.id" v-for="i in collects" :key="i.id"></el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="排放口" prop="outletId">
      <el-select
        v-model="form.outletId"
        placeholder="请选择排放口"
        style="width:100%"
      >
        <el-option
          :label="`${i.outletName} ( ${i.outletId} )`"
          :value="i.outletId"
          v-for="i in outlets"
          :key="i.outletId"
        ></el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="因子类型" prop="eleTypeId">
      <el-select
        v-model="form.eleTypeId"
        placeholder="请选择因子类型"
        style="width:100%"
        @change="eleTypeChange"
      >
        <el-option
          :label="i.name"
          :value="i.id"
          v-for="i in elemType"
          :key="i.id"
        ></el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="因子代码" prop="eleCode">
      <el-input v-model="form.eleCode" placeholder="请输入因子代码">
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
  dropdown,
  elementType,
  add,
  edit
} from "@/api/element";
import { list as outletList } from "@/api/outlet";
import FormDialog from "@/views/components/form-dialog";

export default {
  name: "element-form",
  components: { FormDialog },
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
      outlets: [],
      collects: [],
      //因子类型
      elemType: [],
      form: Object.assign({}, this.values),
      // 表单校验
      rules: {
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
        outletId: [{
          required: true,
          message: "排放口必选",
          trigger: "blur"
        }],
        eleCode: [
          {
            required: true,
            message: "因子代码不能为空",
            trigger: "blur"
          },
          { validator: validateElement }
        ]
      }
    };
  },
  computed: {
    sys_name() {
      return this.$route.path.substr(this.$route.path.lastIndexOf("/") + 1);
    },
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
      this.getOutlets();
      this.getCollects();
      elementType(this.sys_name, {}, this.urlParameter({ pageNum: 1, pageSize: 50 })).then(res => {
        if (res.code === 200) this.elemType = res.rows || res.data;
      });
    }
  },
  methods: {
    getOutlets() {
      outletList(this.sys_name, {}, this.urlParameter({ pageNum: 1, pageSize: 50 })).then(res => {
        if (res.code === 200) this.outlets = res.rows || res.data;
      });
    },
    getCollects() {
      dropdown(this.sys_name).then(res => {
        if (res.code === 200) this.collects = res.rows || res.data;
      });
    },
    eleTypeChange(v) {
      this.$set(this.form, "eleCode", v);
    },
    submitForm(v) {
      if (!this.form.cpElemId) {
        add(this.sys_name, this.form).then(res => {
          if (res.code === 200) {
            this.msgSuccess("添加成功");
            this.$emit("completed");
          } else {
            this.msgError(res.message);
          }
        });
      } else {
        edit(this.sys_name, this.form).then(res => {
          if (res.code === 200) {
            this.msgSuccess("修改成功");
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
