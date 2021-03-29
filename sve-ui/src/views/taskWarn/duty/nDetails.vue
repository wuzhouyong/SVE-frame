<template>
  <div>
    <el-tabs v-model="detailsName" @tab-click="tabClick">
      <el-tab-pane label="异常详情" name="details"></el-tab-pane>
      <el-tab-pane label="处理历史" name="history"></el-tab-pane>
    </el-tabs>
    <div class="details_context" v-if="detailsName==='details'">
      <el-form label-width="130px">
        <el-row :gutter="15">
          <el-col :lg="24" :md="24" :sm="24">
            <el-form-item label="企业名称：">
              {{ values.sourceName }}
            </el-form-item>
          </el-col>
          <el-col :lg="24" :md="24" :sm="24">
            <el-form-item label="监控任务：">
              {{ values.tagHeadName }}
            </el-form-item>
          </el-col>
          <el-col :lg="12" :md="12" :sm="24">
            <el-form-item label="镇街：">
              {{ values.townName }}
            </el-form-item>
          </el-col>
          <el-col :lg="12" :md="12" :sm="24">
            <el-form-item label="异常级别：">
              {{ values.warnLevelName }}
            </el-form-item>
          </el-col>
          <el-col :lg="12" :md="12" :sm="24">
            <el-form-item label="异常时间：">
              {{ values.startDate }}~{{ values.endDate }}
            </el-form-item>
          </el-col>
          <el-col :lg="12" :md="12" :sm="24">
            <el-form-item label="异常类型：">
              {{ values.warnTypeName }}
            </el-form-item>
          </el-col>
          <el-col :lg="24" :md="24" :sm="24">
            <el-form-item label="异常描述：">
              {{ values.warnDesc }}
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <el-divider content-position="left" v-if="warnDetailsMsg.length>0">处理详情</el-divider>
      <el-form label-width="130px">
        <el-row :gutter="15" v-for="(i,index) in warnDetailsMsg" :key="1+index">
          <el-col :lg="24" :md="24" :sm="24">
            <el-form-item label="异常原因：">
              {{ i.warnReason }}
            </el-form-item>
          </el-col>
          <el-col :lg="24" :md="24" :sm="24">
            <el-form-item label="处理情况说明：">
              {{ i.procDesc }}
            </el-form-item>
          </el-col>
          <el-col :lg="24" :md="24" :sm="24">
            <el-form-item label="是否恢复正常：">
              {{ i.isNormal ? "是" : "否" }}
            </el-form-item>
          </el-col>
          <el-col :lg="24" :md="24" :sm="24">
            <el-form-item label="处理人类型：">
              {{ fileType[i.userType] }}
            </el-form-item>
          </el-col>
          <el-col :lg="24" :md="24" :sm="24">
            <el-form-item label="处理人：">
              {{ i.procUser }}
            </el-form-item>
          </el-col>
          <el-col :lg="24" :md="24" :sm="24">
            <el-form-item label="处理时间：">
              {{ i.procDate }}
            </el-form-item>
          </el-col>
          <el-col :lg="24" :md="24" :sm="24">
            <el-form-item label="附件：">
              <el-link type="primary" title="下载附件" :underline="false" v-for="j in i.attaches"
                       :href="getlink(j.attachmentId)"
                       style="margin-right:15px;"
                       :key="j.attachmentId">{{ j.showName }},
              </el-link>
            </el-form-item>
          </el-col>
          <el-col :lg="24" :md="24" :sm="24">
            <p style="width:100%;height:1px;border:1px solid #ddd"></p>
          </el-col>
        </el-row>
      </el-form>
    </div>
    <div class="history_context" v-else>
      <div v-if="warnDefaultHistory.length===0"
           style="width:100%;height:230px;line-height:230px;text-align: center;color:#999">
        无历史数据
      </div>
      <div v-else>
        <div class="select-type">
          <el-button :type="activeType==='all'?'primary':''" icon="el-icon-document" :autofocus="true"
                     @click="selectType('all')">全部
          </el-button>
          <el-button :type="activeType==='enterprise'?'primary':''" icon="el-icon-edit-outline"
                     @click="selectType('enterprise')">企业处理
          </el-button>
          <el-button :type="activeType==='duty'?'primary':''" icon="el-icon-edit-outline" @click="selectType('duty')">
            专管员处理
          </el-button>
          <el-button :type="activeType==='sub'?'primary':''" icon="el-icon-edit-outline" @click="selectType('sub')">
            分局处理
          </el-button>
          <el-button :type="activeType==='city'?'primary':''" icon="el-icon-edit-outline" @click="selectType('city')">
            市局处理
          </el-button>
        </div>
        <el-card class="box-card" v-for="(i,index) in warnHistoryMsg" style="margin-bottom:5px" :key="index">
          <el-form label-width="130px">
            <el-row :gutter="15">
              <el-col :lg="24" :md="24" :sm="24">
                <el-form-item label="异常原因：">
                  {{ i.warnReason }}
                </el-form-item>
              </el-col>
              <el-col :lg="24" :md="24" :sm="24">
                <el-form-item label="处理情况说明：">
                  {{ i.procDesc }}
                </el-form-item>
              </el-col>
              <el-col :lg="24" :md="24" :sm="24">
                <el-form-item label="是否恢复正常：">
                  {{ i.isNormal ? "是" : "否" }}
                </el-form-item>
              </el-col>
              <el-col :lg="24" :md="24" :sm="24">
                <el-form-item label="处理人类型：">
                  {{ fileType[i.userType] }}
                </el-form-item>
              </el-col>
              <el-col :lg="24" :md="24" :sm="24">
                <el-form-item label="处理人：">
                  {{ i.procUser }}
                </el-form-item>
              </el-col>
              <el-col :lg="24" :md="24" :sm="24">
                <el-form-item label="处理时间：">
                  {{ i.procDate }}
                </el-form-item>
              </el-col>
            </el-row>
          </el-form>
        </el-card>
      </div>
    </div>
  </div>
</template>

<script>
import {warnDetails, warnHistory} from "@/api/warnType";

export default {
  props: {
    oPath: {
      type: String,
      default: ""
    },
    values: {
      type: Object,
      default: () => ({})
    }
  },
  data () {
    return {
      detailsName: "details",
      warnDetailsMsg: [],
      warnHistoryMsg: [],
      warnDefaultHistory: [],
      activeType: "all",
      fileType: {
        enterprise: "企业处理",
        duty: "专管员处理",
        sub: "分局处理",
        city: "市局处理"
      }
    };
  },
  watch: {
    values: {
      handler (v) {
        if (v && v.warnId && this.oPath) {
          this.getDetails(v);
        }
      },
      immediate: true
    }
  },
  methods: {
    getDetails (v) {
      warnDetails(v.warnId).then(res => {
        if (res.code === 200) {
          this.warnDetailsMsg = res.data;
        }
      });
    },
    getHistory (v) {
      warnHistory(v.warnId).then(res => {
        if (res.code === 200) {
          this.warnDefaultHistory = res.data;
          this.warnHistoryMsg = JSON.parse(JSON.stringify(this.warnDefaultHistory));
        }
      });
    },
    tabClick (v) {
      if (v.name === "details") {
        this.getDetails(this.values);
      } else {
        this.getHistory(this.values);
      }
    },
    getlink (id) {
      return `${process.env.VUE_APP_BASE_API}/com/getfile/${id}`;
    },
    selectType (v) {
      this.activeType = v;
      if (v === "all") {
        this.warnHistoryMsg = JSON.parse(JSON.stringify(this.warnDefaultHistory));
      } else {
        this.warnHistoryMsg = this.warnDefaultHistory.filter(i => i.userType === v);
      }
    }
  }
};
</script>

<style lang='scss' scoped>
.select-type {
  width: 100%;
  margin-bottom: 6px;
}
</style>
