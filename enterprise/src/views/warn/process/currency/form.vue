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
        <el-input v-model="form.procUserName" placeholder="请输入处理人" size="mini" maxlength="10"
                  show-word-limit/>
      </el-form-item>
      <!--      <el-form-item label="处理时间" prop="procDate">-->
      <!--        <el-input v-model="form.procDate" :disabled="true" size="mini"></el-input>-->
      <!--      </el-form-item>-->
      <el-form-item label="异常原因描述" prop="exceptionDesc">
        <el-input
          size="mini"
          type="textarea"
          v-model="form.exceptionDesc"
          placeholder="请输入异常原因描述"
        />
      </el-form-item>
      <el-form-item label="处理情况描述" prop="procMemo">
        <el-input
          size="mini"
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
                <span style="font-weight: bold">{{ oArr[i.procState] }}</span>
              </div>
              <div class="wpro_wk">
                <div class="wpro_con">
                  <p>处理人：</p>
                  <span>{{ i.procUserName || "-" }}</span>
                </div>
                <div class="wpro_con">
                  <p>处理时间：</p>
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
        :ref_type="fileType[sysName]"
        class="upload"
      ></upload>
    </form-dialog>
  </div>
</template>
<script>
import {process, warnProclist} from "@/api/warn";
import FormDialog from "@/views/components/form-dialog";
import Upload from "../upload";

// const oArr = [
//   "企业待处理",
//   "退回处理",
//   "逾期未处理",
//   "值守待核实",
//   "退回核实",
//   "分局待处理",
//   "已完成",
// ];

const oArr = [
  "企业已处理",
  "值守退回",
  "逾期未处理",
  "值守已处理",
  "分局退回",
  "分局已处理",
  "已完成"
];
export default {
  name: "process-form",
  components: {FormDialog, Upload},
  props: {
    sysName: {
      type: String
    },
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
      fileType: {
        "guocheng": "gc_warn_file",
        "zaozhi": "zz_warn_file",
        "lingxing": "lx_warn_file",
        "lingxingp": "lxp_warn_file",
        "voc": "voc_warn_file",
        "outwater": "psh_warn_file",
        "qixiu": "qx_warn_file",
        "jiayou": "jy_warn_file",
        "jinshu": "js_warn_file",
        "youyan": "yy_warn_file"
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        procUserName: [{
          required: true,
          message: "处理人必填",
          trigger: "blur",
        },],
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
    values: {
      handler (v) {
        this.form.warnId = v.warnId;
        this.form.procState = v.procState;
        this.getProclist();
      }
    }
  },
  methods: {
    getProclist () {
      warnProclist(this.sysName, this.form.warnId).then((res) => {
        this.warnProList = res.data || [];
      });
    },
    submitForm () {
      process(this.sysName, this.form).then((res) => {
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
<style lang="scss" scoped>
.warnProList {
  /deep/ .el-card__body {
    padding: 10px;
  }

  li {
    margin-bottom: 5px;
  }

  .wpro_con {
    display: flex;
    align-items: center;

    p {
      width: 100px;
      text-align: right;
      margin: 0;
    }

    span {
      flex: 1;
    }
  }
}
</style>
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
