<template>
  <form-dialog
    :title="title"
    :values.sync="form"
    :rules="rules"
    :width="'700px'"
    label-width="150px"
    :visible.sync="dialogVisible"
    @submit="submitForm"
  >
    <el-form-item label="排污单位名称" prop="collectId">
      <!-- <SearchSelect v-model="form.collectId" place="请输入数采名称" :isType="isType"></SearchSelect> -->
      <el-input v-model="input" placeholder="请输入内容"></el-input>
    </el-form-item>
    <el-form-item label="在线监控服务机构" prop="eleTypeId">
      <el-input v-model="input" placeholder="请输入内容"></el-input>
    </el-form-item>
    <el-form-item label="服务内容" prop="eleCode">
      <el-checkbox v-model="checked"
        >建设 <el-input v-model="input" placeholder="请输入内容"></el-input
      ></el-checkbox>
      <el-checkbox v-model="checked">运营维护</el-checkbox>
    </el-form-item>
    <el-table :data="tableData" stripe style="width: 100%" show-summary>
      <el-table-column type="index" width="50"> </el-table-column>
      <el-table-column prop="assessment" label="考核内容" width="180">
      </el-table-column>
      <el-table-column prop="fullmark" label="满分" width="180">
      </el-table-column>
      <el-table-column label="评分" prop="score">
        <template slot-scope="scope">
          <el-input
            type="number"
            v-model="scope.row.score"
            oninput="if(value>20)value=20"
            onkeypress="return( /[\d]/.test(String.fromCharCode(event.keyCode)))"
            placeholder="请输入排序"
          >
          </el-input>
        </template>
      </el-table-column>
    </el-table>

    <div>
      最终得分(合计分数乘以0.2)： <span style="color: red">{{ total }}</span>
    </div>
  </form-dialog>
</template>
<script>
import { dropdown, elementType, add, edit } from "@/api/element";
import FormDialog from "@/views/components/form-dialog";
import { parse } from "path-to-regexp";

export default {
  name: "element-form",
  components: { FormDialog },
  props: {
    title: {
      type: String,
    },
    values: {
      type: Object,
      default: () => ({}),
    },
    visible: {
      type: Boolean,
      default: false,
    },
  },
  data() {
    const validateElement = (rule, value, callback) => {
      if (value.substring(0, 4) !== this.form.eleTypeId) {
        callback(new Error("因子编码前四位必须和因子类型对应!"));
      } else if (value.length !== 6) {
        callback(new Error("因子编码长度为6位!"));
      } else if (isNaN(Number(value.substring(value.length - 2)))) {
        callback(new Error("因子编码后两位必须为数字!"));
      } else {
        callback();
      }
    };
    return {
      collects: [],
      //因子类型
      elemType: [],
      form: Object.assign({}, this.values),
      total: 20,
      // 表单校验
      rules: {
        // collectId: [
        //   {
        //     required: true,
        //     message: "数采名称不能为空",
        //     trigger: "blur",
        //   },
        // ],
        // eleDesc: [
        //   {
        //     required: true,
        //     message: "因子名称不能为空",
        //     trigger: "blur",
        //   },
        // ],
        // eleTypeId: [
        //   {
        //     required: true,
        //     message: "因子类型不能为空",
        //     trigger: "blur",
        //   },
        // ],
        // eleCode: [
        //   {
        //     required: true,
        //     message: "因子代码不能为空",
        //     trigger: "blur",
        //   },
        //   { validator: validateElement },
        // ],
      },
      tableData: [
        {
          assessment: "评价机构服务频次是否达到有关技术的规范要求",
          fullmark: "20",
        },
        {
          assessment: "评价机构对设备故障或应急响应及时性",
          fullmark: "20",
        },
        {
          assessment: "评价机构服务态度及服务质量",
          fullmark: "20",
        },
        {
          assessment: "评价机构收费标准合理性",
          fullmark: "20",
        },
        {
          assessment: "总体满意度评价",
          fullmark: "20",
        },
      ],
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
      },
    },
  },
  watch: {
    values() {
      this.form = Object.assign({}, this.values);
      this.getCollects();
      elementType(this.sys_name).then((res) => {
        if (res.code === 200) this.elemType = res.rows || res.data;
      });
    },
    tableData: {
      handler(v) {
        let total = 0;
        console.log("v", v);
        for (var i = 0; i < v.length; i++) {
          if (v[i].score) {
            total += parseInt(v[i].score) * 100;
          }
        }
        this.total = (total * 0.2) / 100;
      },
      immediate: true,
    },
  },
  methods: {
    getCollects() {
      dropdown(this.sys_name).then((res) => {
        if (res.code === 200) this.collects = res.rows || res.data;
      });
    },
    eleTypeChange(v) {
      this.$set(this.form, "eleCode", v);
    },
    submitForm(v) {
      // if (!this.form.cpElemId) {
      //   add(this.sys_name, this.form).then((res) => {
      //     if (res.code === 200) {
      //       this.msgSuccess("添加成功");
      //       this.$emit("completed");
      //     } else {
      //       this.msgError(res.message);
      //     }
      //   });
      // } else {
      //   edit(this.sys_name, this.form).then((res) => {
      //     if (res.code === 200) {
      //       this.msgSuccess("修改成功");
      //       this.$emit("completed");
      //     } else {
      //       this.msgError(res.message);
      //     }
      //   });
      // }
    },
  },
};
</script>
