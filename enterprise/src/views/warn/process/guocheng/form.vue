<template>
  <div class="formUplo">
    <form-dialog
      :title="title"
      :values.sync="form"
      :rules="rules"
      label-width="130px"
      :visible.sync="dialogVisible"
      @submit="submitForm"
      :width="width"
    >
      <el-form-item label="处理人" prop="procUserName">
        <el-input v-model="form.procUserName" placeholder="请输入处理人" />
      </el-form-item>
      <el-form-item label="处理时间" prop="procDate">
        <el-input v-model="form.procDate" :disabled="true"></el-input>
      </el-form-item>
      <el-form-item label="异常原因描述" prop="exceptionDesc">
        <el-input
          type="textarea"
          v-model="form.exceptionDesc"
          placeholder="请输入异常原因描述"
        />
      </el-form-item>
      <el-form-item label="处理情况描述" prop="procMemo">
        <el-input
          type="textarea"
          v-model="form.procMemo"
          placeholder="请输入处理情况描述"
        />
      </el-form-item>
      <div class="alarmCompany">
        <ul v-if="warnProList.length > 0" class="warnProList">
          <li v-for="i in warnProList" :key="i.key">
            <el-card size="small" shadow="never">
              <div slot="header" class="clearfix">
                <span style="font-weight: bold">{{
                  i.procState ? oArr[i.procState] : "-"
                }}</span>
              </div>
              <div class="wpro_wk">
                <div class="wpro_con">
                  <span>处理人：</span>
                  <span>{{ i.procUserName || "-" }}</span>
                </div>
                <div class="wpro_con">
                  <span>处理时间：</span>
                  <span>{{ i.procDate || "-" }}</span>
                </div>
                <div class="wpro_con">
                  <p>处理情况说明：</p>
                  <span>{{ i.procMemo || "-" }}</span>
                </div>
              </div>
            </el-card>
          </li>
        </ul>
      </div>
      <upload
        :warnId="form.warnId"
        ref_type="gc_warn_file"
        class="upload"
      ></upload>
    </form-dialog>
  </div>
</template>
<script>
import { process, warnProclist } from "@/api/warn";
import { parseTime } from "@/utils/ruoyi";
import FormDialog from "@/views/components/form-dialog";
import Upload from "../upload";
const oArr = [
  "企业待处理",
  "退回处理",
  "逾期未处理",
  "值守待核实",
  "退回核实",
  "分局待处理",
  "已完成",
];
export default {
  name: "process-form",
  components: { FormDialog, Upload },
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
  data () {
    return {
      // 表单参数
      form: {
        warnId: null,
        procDate: null,
        procUserName: null,
      },
      // 表单校验
      rules: {
        exceptionDesc: [
          {
            required: true,
            message: "异常原因描述不能为空",
            trigger: "blur",
          },
        ],
      },
      warnProList: [],
      oArr,
      width: 40 + "%"
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
    username () {
      return this.$store.getters.name;
    },
  },
  watch: {
    values () {
      this.form.warnId = this.values.id;
      this.form.procDate = parseTime(new Date()).substr(0, 10);
      this.form.procUserName = this.username;
      this.getProclist();
    },
  },
  methods: {
    getProclist () {
      warnProclist(this.sys_name, this.form.warnId).then((res) => {
        this.warnProList = res.data || [];
      });
    },
    submitForm (v) {
      process(this.sys_name, this.form).then((res) => {
        console.log(this.form);
        if (res.code === 200) {
          this.msgSuccess(res.message || "异常处理成功");
          this.$emit("completed");
        } else {
          this.msgError(res.message || "异常处理失败");
        }
      });
    },
  },
};
</script>
<style scoped>
.formUplo >>> .el-dialog__wrapper .el-dialog {
  min-height: 80%;
  max-height: 80%;
  height: auto;
  overflow: hidden;
}
.formUplo >>> .el-dialog__wrapper .el-dialog .el-dialog__body {
  position: absolute;
  left: 0;
  top: 54px;
  bottom: 80px;
  right: 0;
  padding: 0 30px 0 0;
  z-index: 1;
  /* overflow: hidden; */
  overflow-y: auto;
}
.formUplo >>> .el-dialog__wrapper .el-dialog .el-dialog__footer {
  position: absolute;
  bottom: 0;
  right: 0;
}
.warnProList {
  list-style: none;
}
.upload {
  padding-left: 40px;
}
.el-card__body .wpro_conb {
  display: flex;
  flex-direction: column;
}

.wpro_con span {
  display: inline-block;
  line-height: 30px;
  height: 30px;
}
</style>
