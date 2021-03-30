<template>
  <form-dialog
    :title="title"
    :values.sync="form"
    :rules="rules"
    label-width="150px"
    :visible.sync="dialogVisible"
    @submit="submitForm"
  >
    <el-form-item label="施工单位名称" prop="constructionUnit">
      <el-input
        v-model="form.constructionUnit"
        placeholder="请输入施工单位名称"
      ></el-input>
    </el-form-item>
    <el-form-item label="施工单位简称" prop="shortName">
      <el-input
        v-model="form.shortName"
        minlength="2"
        maxlength="8"
        placeholder="请输入施工单位名称"
      ></el-input>
    </el-form-item>
    <el-form-item label="施工单位联系人" prop="constructionPerson">
      <el-input
        v-model="form.constructionPerson"
        placeholder="请输入施工单位联系人"
      />
    </el-form-item>
    <el-form-item label="施工单位联系电话" prop="constructionPhone">
      <el-input
        v-model="form.constructionPhone"
        placeholder="请输入施工单位联系电话"
      />
    </el-form-item>
    <el-form-item label="统一社会信用代码" prop="socialCreditCode">
      <el-input
        v-model="form.socialCreditCode"
        placeholder="施工单位统一社会信用代码"
      />
    </el-form-item>
    <!--    <el-form-item label="备注" prop="completeMemo">-->
    <!--      <el-input-->
    <!--        type="textarea"-->
    <!--        :rows="4"-->
    <!--        v-model="form.completeMemo"-->
    <!--        placeholder="请输入备注"-->
    <!--      />-->
    <!--    </el-form-item>-->
    <!--    <div style="font-size:12px;color:indianred">( 注:修改施工单位登记相关信息后.施工状态将重新变更为施工中.请谨慎操作! )</div>-->
  </form-dialog>
</template>
<script>
import {constructionRegister, constructionUpdate} from "@/api/construction";
import FormDialog from "@/views/components/form-dialog";
import {remove} from "@/api/outlet";

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
    active_sys: {
      type: String,
    },
    visible: {
      type: Boolean,
      default: false,
    },
    registerType: {
      type: Number,
      default: 1,
    },
  },
  mounted () {

  },
  data () {
    return {
      form: Object.assign({}, this.values),
      rules: {
        constructionUnit: [
          {
            required: true,
            message: "施工单位名称不能为空",
            trigger: "blur",
          },
        ],
        shortName: [
          {
            required: true,
            message: "施工单位简称不能为空",
            trigger: "blur",
          },
          {
            min: 2,
            max: 8,
            message: "长度在 2 到 8 个字符",
            trigger: "blur",
          },
        ],
        constructionPerson: [
          {
            required: true,
            message: "施工单位联系人不能为空",
            trigger: "blur",
          },
        ],
        constructionPhone: [
          {
            required: true,
            message: "施工单位联系电话不能为空",
            trigger: "blur",
          },
        ],
        socialCreditCode: [
          {
            required: true,
            message: "统一社会信用代码不能为空",
            trigger: "blur",
          },
        ],
      },
    };
  },
  computed: {
    sys_name () {
      return this.$route.path.substr(this.$route.path.lastIndexOf("/") + 1);
    },
    dialogVisible: {
      get () {
        return this.visible || false;
      },
      set (v) {
        this.$emit("update:visible", v);
      },
    },
  },
  watch: {
    values () {
      this.form = Object.assign({}, this.values);
    },
  },
  methods: {
    submitForm () {
      const {registerType} = this;

      // 判断是修改还是新增施工登记
      if (registerType == 1) {
        this.$confirm("提交成功后,状态将变更为施工中,请确认是否提交?", "提醒", {
          confirmButtonText: "确认",
          cancelButtonText: "取消",
          type: "warning",
        })
          .then(() => {
            this.form.constructionStatus = 1;
            return constructionUpdate(this.active_sys, this.form);
          })
          .then((res) => {
            if (res.code === 200) {
              this.msgSuccess(res.message || "施工单位修改成功");
              this.dialogVisible = false;
              this.$emit("completed");
            } else {
              this.msgError(res.message || "施工单位修改失败");
            }
          })
          .catch(() => {
          });
      } else {
        constructionUpdate(this.active_sys, this.form).then((res) => {
          if (res.code === 200) {
            this.msgSuccess(res.message || "施工单位修改成功");
            this.dialogVisible = false;
            this.$emit("completed");
          } else {
            this.msgError(res.message || "施工单位修改失败");
          }
        });
      }
    },
  },
};
</script>
