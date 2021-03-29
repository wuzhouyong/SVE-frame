<template>
  <div class="handle-style">
    <div style="height:100%;overflow-y:auto;padding-right: 2px;">
      <el-form ref="elForm" :model="formData" :rules="rules" label-width="110px">
        <el-form-item label="处理状态" prop="procResult">
          <el-radio-group v-model="formData.procResult" size="mini">
            <el-radio-button label="1">同意处理</el-radio-button>
            <el-radio-button label="0">驳回处理</el-radio-button>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="处理情况说明" prop="procMemo">
          <el-input type="textarea" v-model="formData.procMemo" :rows="3"></el-input>
        </el-form-item>
        <el-form-item label="处理人" prop="procUserName">
          <el-input v-model="formData.procUserName"></el-input>
        </el-form-item>
      </el-form>
      <UploadTem v-bind="$attrs" ref="uploadTem"></UploadTem>
      <!-- <el-divider content-position="left">已选择待处理(共 {{selectRows.length}} 条数据)</el-divider> -->
      <el-divider content-position="left">处理流程</el-divider>
      <div class="alarmCompany">
        <ul v-if="warnProList.length>0">
          <li v-for="i in warnProList" :key="i.key">
            <el-card size="small" shadow="never">
              <div slot="header" class="clearfix">
                <!-- <span style="font-weight:bold">{{(index+1)+'.'+i.sourceName}}</span> -->
                <span style="font-weight:bold">{{ oArr[i.procState] }}</span>
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
                  <p>异常原因描述：</p>
                  <span>{{ i.exceptionDesc || "-" }}</span>
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
    </div>
  </div>
</template>
<script>
import UploadTem from "./upload";
import {warnDutyHandle, warnProclist} from "@/api/warn_handle";
import {getUserProfile} from "@/api/system/user";

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
  components: {UploadTem},
  props: ["selectRows", "activeName"],
  data () {
    return {
      formData: {
        procState: "1",
        procUserName: null,
      },
      rules: {
        procMemo: [
          {required: true, message: "请输入处理情况说明", trigger: "blur"},
        ],
        procUserName: [
          {required: true, message: "请输入处理人", trigger: "blur"},
        ],
      },
      warnProList: [],
      oArr,
    };
  },
  watch: {
    selectRows: {
      handler (v) {
        if (v) {
          this.getWarnProcList(v);
        }
      },
      immediate: true,
    },
  },
  created () {
    getUserProfile().then((response) => {
      this.formData.procUserName = response.data.userName;
    });
  },
  methods: {
    getWarnProcList (v) {
      warnProclist(v[0].warnId, this.activeName).then((res) => {
        this.warnProList = res.data;
      });
    },
    submitForm () {
      this.$refs["elForm"].validate((valid) => {
        if (valid) {
          this.formData.warnId = this.selectRows[0].warnId;
          this.formData.warnLevel = this.selectRows[0].warnLevel;
          this.formData.procState = this.selectRows[0].procState;
          warnDutyHandle(this.activeName, this.formData).then((res) => {
            if (res.code === 200) {
              this.$emit("suc");
            }
          });
        } else {
          this.msgError("完整填写信息");
        }
      });
    },
    disabledDate (time) {
      return time.getTime() > Date.now() - 8.64e6;
    },
  },
};
</script>
<style lang="scss">
.alarmCompany {
  height: auto;

  ul {
    width: 100%;
    padding: 0;
    margin: 0;

    li {
      list-style-type: none;
      margin-bottom: 5px;

      .wpro_wk {
        .wpro_con {
          display: flex;
          margin-bottom: 5px;

          p {
            margin: 0;
            width: 100px;
            text-align: right;
          }

          span {
            margin: 0;
            flex: 1;
            padding-left: 5px;
          }
        }
      }
    }
  }
}

.handleWarnDialog {
  display: flex;
  flex-direction: column;
  width: 100%;
  height: calc(100% - 15vh);
  overflow: hidden;

  .el-dialog__body {
    flex: 1;
    overflow: hidden;
    height: 100%;
    padding: 15px;

    .handle-style {
      display: flex;
      flex-direction: column;
      height: 100%;
      overflow: hidden;
    }
  }

  .alarmCompany {
    .el-card__body {
      padding: 10px;
    }
  }
}
</style>
