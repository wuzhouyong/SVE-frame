<template>
  <form-dialog
    :title="title"
    :values.sync="form"
    :rules="rules"
    label-width="130px"
    :visible.sync="dialogVisible"
    @submit="submitForm"
  >
    <el-form-item label="问题内容" prop="questionDesc">
      <el-input type="textarea" v-model="form.questionDesc" placeholder="请输入问题内容"/>
    </el-form-item>
    <el-form-item label="问题满分" prop="fullMarks">
      <el-input v-model.number="form.fullMarks" placeholder="请输入问题满分"/>
    </el-form-item>
  </form-dialog>
</template>
<script>
import FormDialog from "@/views/components/form-dialog";
import {mochaQuestionDevopsAdd, mochaQuestionDevopsEdit} from '@/api/mocha'

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
    var checkNum = (rule, value, callback) => {
      if (!Number.isInteger(value)) {
        callback(new Error('请输入数字值'));
      } else {
        if (value < 0) {
          callback(new Error('分数不能小于0'));
        } else if (value > 100) {
          callback(new Error('分数不能大于100'));
        } else {
          callback();
        }
      }
    };
    return {
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        questionDesc: [
          {
            required: true,
            message: "问题内容不能为空",
            trigger: "blur",
          },
        ],
        fullMarks: [
          {
            required: true,
            message: "问题满分不能为空",
            trigger: "blur",
          },
          {validator: checkNum, trigger: 'blur'}
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
    values: {
      handler() {
        this.form = Object.assign({}, this.values);
      },
      immediate: true
    }
  },
  methods: {
    submitForm() {
      if (!this.form.questionId) {
        mochaQuestionDevopsAdd(this.form).then((res) => {
          if (res.code === 200) {
            this.msgSuccess("添加成功");
            this.$emit("completed");
          } else {
            this.msgError(res.message);
          }
        });
      } else {
        mochaQuestionDevopsEdit(this.form).then((res) => {
          if (res.code === 200) {
            this.msgSuccess("修改成功");
            this.$emit("completed");
          } else {
            this.msgError(res.message);
          }
        });
      }
    },
  },
};
</script>
