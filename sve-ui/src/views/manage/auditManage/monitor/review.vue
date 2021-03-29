<template>
  <div v-loading="loading" class="re_wk">
    <div
      v-if="oData.length<1"
      style="width: 100%;
    height: 100%;
    display: flex;
    align-items: center;
    justify-content: center;"
    >无申请历史明细..
    </div>
    <el-card class="box-card" v-for="(item,index) in oData" :key="index" shadow="always">
      <div class="reviewDiv">
        <el-form label-width="100px" class="review_details">
          <el-form-item label="审核结果：">{{ status[item.aduitStatus] }}</el-form-item>
          <el-form-item label="调整事项：">{{ item.adjustItem === "0" ? "任务取消申请" : "任务暂缓申请" }}</el-form-item>
          <el-form-item :label="item.adjustItem==='0'?'取消原因：':'暂缓原因：'">{{ item.reasonTran }}</el-form-item>
          <el-form-item v-if="item.adjustItem==='1'" label="暂缓至：">{{ item.suspendDate }}</el-form-item>
          <el-form-item label="其他原因：">{{ item.otherReason }}</el-form-item>
          <el-form-item label="备注：">{{ item.memo }}</el-form-item>
          <template v-if="isLingxing">
            <div style="width:100%;display:flex;justify-content:space-between">
              <div style="width:49.5%" v-for="i in item.details">
                <el-card class="box-card">
                  <el-form-item label="审核人：">{{ i.userName }}({{ i.roleName }})</el-form-item>
                  <el-form-item label="审核结果：">{{ i.aduitStatus === "1" ? "同意" : "不同意" }}</el-form-item>
                  <el-form-item label="审核意见：">{{ i.aduitReason }}</el-form-item>
                </el-card>
              </div>
            </div>
          </template>
          <el-form-item label="审核意见：" v-else>{{ item.aduitReason || "-" }}</el-form-item>
          <el-form-item label="附件：" v-if="item.file&&item.file.length>0">
            <div v-for="i of item.file" :key="i.id" class="fileList">
              <el-link
                :href="getlink(i.Id)"
                type="primary"
                :underline="false"
                target="_blank"
              >{{ i.showName }}
                {{
                  i.attachType === "task_branch" ? " ( 分局初审意见 ) " : i.attachType === "city_task_branch" ? "( 市局任务调整说明 )" : ""
                }}
              </el-link>
            </div>
          </el-form-item>
        </el-form>
      </div>
    </el-card>
  </div>
</template>
<script>
import { adjustList, LXadjustDetail } from "@/api/composite/monitor";
import { fileList } from "@/api/upload";

export default {
  props: {
    reviewData: {
      type: Object,
      default: () => {}
    },
    isLingxing: {
      type: Boolean,
      default: false
    }
  },
  watch: {
    reviewData: {
      handler(v) {
        if (v) {
          this.getJustList();
        }
      },
      immediate: true,
      deep: true
    }
  },
  data() {
    return {
      oData: [],
      status: ["待审核", "同意", "不同意"],
      loading: false
    };
  },
  methods: {
    getJustList() {
      this.loading = true;
      let { sourceId, tagHeadId } = this.reviewData;
      if (this.isLingxing) {
        LXadjustDetail(sourceId, tagHeadId).then(res => {
          if (res.code === 200) {
            this.oData = res.data;
            for (const i of this.oData) {
              if (i.adjustReason === "-1") {
                i.reasonTran = "其他";
              } else {
                if (i.adjustItem === "0") {
                  this.getDicts("task_cancel_reason").then((res) => {
                    i.reasonTran = this.selectDictLabel(res.data, i.adjustReason);
                  });
                } else if (i.adjustItem === "1") {
                  this.getDicts("task_postpone_reason").then((res) => {
                    i.reasonTran = this.selectDictLabel(res.data, i.adjustReason);
                  });
                }
              }
              fileList({
                refId: i.taskAdjustId,
                refType: "task_apply"
              }).then((res) => {
                this.$set(i, "file", res.data);
              });
            }
          }
          setTimeout(() => {
            this.loading = false;
          }, 800);
        })
      } else {
        adjustList({ pfSourceId: sourceId, tagHeadId: tagHeadId }).then((res) => {
          if (res.code === 200) {
            this.oData = res.data;
            for (const i of this.oData) {
              if (i.adjustReason === "-1") {
                i.reasonTran = "其他";
              } else {
                if (i.adjustItem === "0") {
                  this.getDicts("task_cancel_reason").then((res) => {
                    i.reasonTran = this.selectDictLabel(res.data, i.adjustReason);
                  });
                } else if (i.adjustItem === "1") {
                  this.getDicts("task_postpone_reason").then((res) => {
                    i.reasonTran = this.selectDictLabel(res.data, i.adjustReason);
                  });
                }
              }
              fileList({
                refId: i.taskAdjustId,
                refType: "task_apply"
              }).then((res) => {
                this.$set(i, "file", res.data);
              });
            }
          }
          setTimeout(() => {
            this.loading = false;
          }, 800);
        });
      }
    },
    getlink(id) {
      return `${process.env.VUE_APP_BASE_API}/com/getfile/${id}`;
    }
  }
};
</script>
<style lang="scss">
.reviewDiv {
  padding-top: 3px;
  border-bottom: 1px solid #ddd;

  &:last-child {
    border-bottom: none;
  }

  .review_details {
    .el-form-item {
      margin-bottom: 5px;
    }
  }
}

.reviewDialog {
  display: flex;
  flex-direction: column;
  width: 100%;
  height: calc(100% - 15vh);
  overflow: hidden;

  .re_wk {
    height: 100%;
    overflow-y: auto;
    padding-right: 5px;

    .box-card {
      margin-top: 10px;

      &:nth-child(1) {
        margin-top: 0;
      }

      .el-card__body {
        padding: 5px;
      }
    }
  }

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
