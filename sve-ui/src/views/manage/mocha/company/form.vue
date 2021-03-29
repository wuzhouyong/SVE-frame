<template>
  <form-dialog
    :title="title"
    :values.sync="form"
    :rules="rules"
    label-width="130px"
    :visible.sync="dialogVisible"
    @submit="submitForm"
  >
    <el-form-item label="运维单位" prop="fullName">
      <el-input v-model="form.fullName" placeholder="运维单位"/>
    </el-form-item>
    <el-form-item label="简称" prop="shortName">
      <el-input v-model="form.shortName" placeholder="简称"/>
    </el-form-item>
    <el-form-item label="运维类型" prop="opSourceType">
      <el-radio-group v-model="form.opSourceType" size="mini">
        <el-radio-button border label="0">施工单位</el-radio-button>
        <el-radio-button border label="1">施工、运维单位</el-radio-button>
        <el-radio-button border label="2">运维单位</el-radio-button>
      </el-radio-group>
    </el-form-item>
    <el-form-item label="联系人" prop="contactName">
      <el-input v-model="form.contactName" placeholder="联系人"/>
    </el-form-item>
    <el-form-item label="联系电话" prop="contactPhone">
      <el-input v-model="form.contactPhone" placeholder="联系电话"/>
    </el-form-item>
  </form-dialog>
</template>
<script>
import {mochaDevopsAdd, mochaDevopsEdit} from "@/api/mocha";
import FormDialog from "@/views/components/form-dialog";

export default {
  components: {FormDialog},
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
    var checkphone = (rule, value, callback) => {
      if (value == '') {
        callback(new Error('请输入手机号'))
      } else if (!this.isCellPhone(value)) {
        //引入methods中封装的检查手机格式的方法
        callback(new Error('请输入正确的手机号!'))
      } else {
        callback()
      }
    }
    return {
      // 表单参数
      form: {
        fullName: null,
        shortName:null,
        opSourceType: '2',
        contactName: null,
        contactPhone: null,
      },
      // 表单校验
      rules: {
        fullName: [
          {
            required: true,
            message: "运维单位必填",
            trigger: "blur",
          },
        ],
        shortName: [
          {
            required: true,
            message: "简称必填",
            trigger: "blur",
          },
        ],
        opSourceType: [
          {
            required: true,
            message: "运维类型必填",
            trigger: "blur",
          },
        ],
      },
    };
  },
  computed: {
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
      this.form = Object.assign({opSourceType: '2'}, this.values);
    },
  },
  methods: {
    submitForm() {
      if (!this.form.opSourceId) {
        mochaDevopsAdd(this.form).then((res) => {
          if (res.code === 200) {
            this.msgSuccess("添加成功");
            this.$emit("completed");
          } else {
            this.msgError(res.message);
          }
        });
      } else {
        mochaDevopsEdit(this.form).then((res) => {
          if (res.code === 200) {
            this.msgSuccess("修改成功");
            this.$emit("completed");
          } else {
            this.msgError(res.message);
          }
        });
      }
    },
    isCellPhone(val) {
      if (!/^1(3|4|5|6|7|8)\d{9}$/.test(val)) {
        return false
      } else {
        return true
      }
    }
  },
};
</script>
