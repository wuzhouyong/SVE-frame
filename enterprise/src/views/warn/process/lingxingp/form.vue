<template>
  <form-dialog
    :title="title"
    :values.sync="form"
    :rules="rules"
    label-width="130px"
    :visible.sync="dialogVisible"
    @submit="submitForm"
  >
    <el-form-item label="处理人" prop="procUserName">
      <el-input v-model="form.procUserName" placeholder="请输入处理人" />
    </el-form-item>
    <el-form-item label="处理时间" prop="procDate">
      <el-input v-model="form.procDate" :disabled="true"></el-input>
    </el-form-item>
    <el-form-item label="异常原因描述" prop="exceptionDesc">
      <el-input type="textarea" v-model="form.exceptionDesc" placeholder="请输入异常原因描述" />
    </el-form-item>
    <el-form-item label="处理情况描述" prop="procMemo">
      <el-input type="textarea" v-model="form.procMemo" placeholder="请输入处理情况描述" />
    </el-form-item>
    <div class="alarmCompany">
      <ul v-if="warnProList.length>0">
        <li v-for="i in warnProList" :key="i.key">
          <el-card size="small" shadow="never">
            <div slot="header" class="clearfix">
              <span style="font-weight:bold">{{i.procState?oArr[i.procState]:'-'}}</span>
            </div>
            <div class="wpro_wk">
              <div class="wpro_con">
                <p>值守核实人：</p>
                <span>{{i.procUserName||'-'}}</span>
              </div>
              <div class="wpro_con">
                <p>处理时间：</p>
                <span>{{i.procDate||'-'}}</span>
              </div>
              <div class="wpro_con">
                <p>处理情况说明：</p>
                <span>{{i.procMemo||'-'}}</span>
              </div>
            </div>
          </el-card>
        </li>
      </ul>
    </div>
    <upload :warnId="form.warnId" ref_type="lx_warn_file"></upload>
  </form-dialog>
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
  data() {
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
    username() {
      return this.$store.getters.name;
    },
  },
  watch: {
    values() {
      this.form.warnId = this.values.id;
      this.form.procDate = parseTime(new Date()).substr(0, 10);
      this.form.procUserName = this.username;
      this.getProclist();
    },
  },
  methods: {
    getProclist() {
      warnProclist(this.sys_name, this.form.warnId).then((res) => {
        this.warnProList = res.data || [];
      });
    },
    submitForm(v) {
      process(this.sys_name, this.form).then((res) => {
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
<style lang="scss">
.alarmCompany  {
  height: auto;
  ul  {
    width: 100%;
    padding: 0;
    margin: 0;
    li  {
      list-style-type: none;
      margin-bottom: 5px;
      .wpro_wk  {
        .wpro_con  {
          display: flex;
          p  {
            margin: 0;
            width: 100px;
            text-align: right;
          }
          span  {
            margin: 0;
            flex: 1;
            padding-left: 5px;
          }
        }
      }
    }
  }
}
</style>