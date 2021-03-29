<template>
  <div>
    <div class="source-basic">
      <div class="source-msg">
        <el-form label-width="140px">
          <el-row :gutter="15">
            <el-col :lg="12" :md="24" :sm="24">
              <el-form-item label="环保联系人：">
                {{ sData.envContactName || "-" }}
              </el-form-item>
            </el-col>
            <el-col :lg="12" :md="24" :sm="24">
              <el-form-item label="环保联系人电话：">
                {{ sData.envContactPhone || "-" }}
              </el-form-item>
            </el-col>
            <el-col :lg="12" :md="24" :sm="24">
              <el-form-item label="所属行业：">
                {{ sData.industryName || "-" }}
              </el-form-item>
            </el-col>
            <el-col :lg="12" :md="24" :sm="24">
              <el-form-item label="企业经营状态：">
                {{ sData.dictLabel || "-" }}
              </el-form-item>
            </el-col>
            <el-col :lg="12" :md="24" :sm="24">
              <el-form-item label="经度：">
                {{ sData.longitude || "-" }}
              </el-form-item>
            </el-col>
            <el-col :lg="12" :md="24" :sm="24">
              <el-form-item label="纬度：">
                {{ sData.latitude || "-" }}
              </el-form-item>
            </el-col>
            <el-col :lg="12" :md="24" :sm="24">
              <el-form-item label="所属镇街：">
                {{ sData.townName || "-" }}
              </el-form-item>
            </el-col>
            <el-col :lg="12" :md="24" :sm="24">
              <el-form-item label="地址：" :title="sData.address">
                {{ sData.address || "-" }}
              </el-form-item>
            </el-col>
            <el-col :lg="24" :md="24" :sm="24">
              <el-form-item label="排污许可证代码：">
                {{ sData.emissionPermitCode || "-" }}
              </el-form-item>
            </el-col>
            <el-col :lg="24" :md="24" :sm="24">
              <el-form-item label="社会统一信用代码：">
                {{ sData.socialCreditCode || "-" }}
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
      </div>
      <el-divider content-position="left">施工进度</el-divider>
      <div class="task_tag">
        <el-tag size="small" v-for="i in platform" :key="i.id" :class="{active:activeTask===i.path}"
                @click="clickTaskTag(i.path)">{{ i.name }}
        </el-tag>
        <div class="construct-wk" v-loading="constructLoading">
          <el-form label-width="150px">
            <el-row :gutter="15">
              <el-col :lg="12" :md="24" :sm="24">
                <el-form-item label="施工进度：">{{ constructionStatus[model.constructionStatus] }}</el-form-item>
              </el-col>
              <el-col :lg="12" :md="24" :sm="24">
                <el-form-item label="施工单位名称：">{{ model.constructionUnit || "-" }}</el-form-item>
              </el-col>
              <el-col :lg="12" :md="24" :sm="24">
                <el-form-item label="施工单位联系人：">{{ model.constructionPerson || "-" }}</el-form-item>
              </el-col>
              <el-col :lg="12" :md="24" :sm="24">
                <el-form-item label="施工单位联系电话：">{{ model.constructionPhone || "-" }}</el-form-item>
              </el-col>
              <el-col :lg="12" :md="24" :sm="24">
                <el-form-item label="施工完成日期：">{{ model.completeDate || "-" }}</el-form-item>
              </el-col>
              <el-col :lg="12" :md="24" :sm="24">
                <el-form-item label="验收日期：">{{ model.acceptanceDate || "-" }}</el-form-item>
              </el-col>
            </el-row>
          </el-form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {getConstructionModel} from "@/api/home/index";

export default {
  props: {
    sData: {
      type: Object,
      default: () => ({})
    },
    platform: {
      type: Array,
      default: () => ([])
    }
  },
  watch: {
    platform: {
      handler (v) {
        if (v.length > 0) {
          this.activeTask = v[0].path;
          this.getData();
        }
      }
    }
  },
  data () {
    return {
      activeTask: null,
      constructionStatus: [
        "未开展",
        "施工中",
        "已接入",
        "联网完成",
        "待验收",
        "",
        "已验收"
      ],
      model: {},
      constructLoading: false,
    };
  },
  created () {
    console.log(this.sData);
  },
  methods: {
    clickTaskTag (v) {
      this.activeTask = v;
      this.getData();
    },
    getData () {
      this.constructLoading = true;
      getConstructionModel(this.activeTask, this.sData.sourceId).then(res => {
        if (res.code === 200) {
          this.model = res.data;
        }
        this.constructLoading = false;
      });
    }
  }
};
</script>

<style lang='scss' scoped>
.source-basic {
  width: 100%;
  height: auto;
  color: #fff;
  padding: 5px;
  overflow-y: auto;
  overflow-x: hidden;

  /deep/ .el-divider__text {
    background-color: rgba(23, 32, 47, 0.9);
    color: #fff;
  }

  .source-msg {
    /deep/ .el-form-item__label {
      color: #fff;
      font-size: 13px;
    }

    /deep/ .el-form-item {
      margin-bottom: 0;
      font-size: 13px;

      .el-form-item__content {
        width: calc(100% - 140px);
        margin-left: 0 !important;
        color: #0cf;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
      }
    }
  }


  .el-col {
    display: flex;
    align-items: center;
    margin-bottom: 4px;
    overflow: hidden;
  }

  .task_tag {
    .el-tag {
      margin-right: 10px;

      &.active {
        background-color: #1890ff;
        color: #e8f4ff;
      }

      &:hover {
        background-color: #1890ff;
        color: #e8f4ff;
        cursor: pointer;
      }
    }
  }

  .construct-wk {
    margin-top: 10px;

    .el-form-item {
      margin-bottom: 0;
      font-size: 13px;

      .el-form-item__content {
        width: calc(100% - 140px);
        margin-left: 0 !important;
        color: #0cf;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
      }
    }
  }
}
</style>
