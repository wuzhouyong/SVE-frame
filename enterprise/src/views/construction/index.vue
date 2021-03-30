<template>
  <div class="progress">
    <el-tabs v-model="active_sys" type="border-card">
      <el-tab-pane
        v-for="sys in systems"
        :key="sys.path"
        :label="sys.name"
        :name="sys.path"
      ></el-tab-pane>
    </el-tabs>

    <!-- 操作按钮 -->
    <el-row :gutter="10" class="mb8">
      <el-col>
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="checkContract"
          :disabled="model.constructionStatus > 1"
        >施工单位登记
        </el-button>
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          @click="completeVisable = true"
          :disabled="
            model.constructionStatus < 2 || model.constructionStatus > 3
          "
        >联网完成登记
        </el-button>
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          @click="acceptVisable = true"
          :disabled="model.constructionStatus !== 4"
        >自主验收登记
        </el-button>
        <el-link
          icon="el-icon-download"
          style="font-size:12px;"
          v-if="template_url"
          :href="template_url"
        >验收报告模板下载
        </el-link>
        <el-button
          style="float: right"
          type="warning"
          icon="el-icon-tickets"
          size="mini"
          @click="dissmissHis"
          v-if="model.flag"
        >验收审核历史
        </el-button>
      </el-col>
      <el-col>
        <el-row :gutter="15">
          <el-form label-width="140px">
            <el-col :lg="24" :md="24" :sm="24">
              <el-form-item label="施工进度:">{{ constructionStatus[model.constructionStatus] }}</el-form-item>
            </el-col>
            <el-col :lg="12" :md="12" :sm="24" style="height:36px">
              <el-form-item label="施工单位名称:">{{ model.constructionUnit }}
                <span style="padding-left:10px;" v-if="! (model.constructionStatus <= 0)">
                  <el-link
                    type="warning"
                    icon="el-icon-edit"
                    :underline="false"
                    style="font-size:12px;"
                    size="mini"
                    @click="constructionUpdate">(施工单位信息修改)
                  </el-link>
                </span>
              </el-form-item>
            </el-col>
            <el-col :lg="12" :md="12" :sm="24">
              <el-form-item label="施工单位简称:">{{ model.shortName }}
              </el-form-item>
            </el-col>
            <el-col :lg="12" :md="12" :sm="24">
              <el-form-item label="施工单位联系人:">{{ model.constructionPerson }}
              </el-form-item>
            </el-col>
            <el-col :lg="12" :md="12" :sm="24">
              <el-form-item label="施工单位联系电话:">{{ model.constructionPhone }}
              </el-form-item>
            </el-col>
            <el-col :lg="12" :md="12" :sm="24">
              <el-form-item label="统一社会信用代码:">{{ model.socialCreditCode }}
              </el-form-item>
            </el-col>
            <el-col :lg="12" :md="12" :sm="24">
              <el-form-item label="施工完成日期:">{{ model.completeDate || "-" }}
              </el-form-item>
            </el-col>
            <el-col :lg="12" :md="12" :sm="24">
              <el-form-item label="自主验收日期:">{{ model.acceptanceDate || "-" }}
              </el-form-item>
            </el-col>
            <el-col :lg="12" :md="12" :sm="24">
              <el-form-item label="验收确认人:"
              >{{ model.acceptBy || "-" }}
              </el-form-item>
            </el-col>
            <el-col :lg="12" :md="12" :sm="24">
              <el-form-item label="验收确认日期:">
                {{ model.acceptDate || "-" }}
              </el-form-item>
            </el-col>
<!--            <el-col :lg="12" :md="12" :sm="24">-->
<!--              <el-form-item label="验收确认结果:"-->
<!--              >{{ model.acceptResult !== null ? model.acceptResult === 0 ? "验收退回" : "验收通过" : "-" }}-->
<!--              </el-form-item>-->
<!--            </el-col>-->
          </el-form>
        </el-row>
      </el-col>
      <el-col :lg="24" style="flex: 1; min-height: 300px">
        <upload
          v-if="ref_type"
          :dict_code="ref_type"
          :ref_type="ref_type"
          :isDel="isDel"
        ></upload>
      </el-col>
    </el-row>
    <register
      title="施工单位登记"
      :values="model"
      :active_sys="active_sys"
      :visible.sync="regVisable"
      :registerType="registerType"
      @completed="getConstruction()"
    ></register>

    <complete
      title="联网完成登记"
      :values="model"
      :active_sys="active_sys"
      :visible.sync="completeVisable"
      @completed="getConstruction()"
    ></complete>

    <acceptance
      title="自主验收登记"
      :values="model"
      :active_sys="active_sys"
      :visible.sync="acceptVisable"
      @completed="getConstruction()"
    ></acceptance>

    <el-dialog
      title="验收审核历史"
      :visible.sync="dissmissable"
      width="30%">
      <dimiss-history
        :values="queryTypes"
        :active_sys="active_sys"
        @completed="getConstruction()"
      ></dimiss-history>
    </el-dialog>

  </div>
</template>
<script>
import {
  progressModel,
  hasContract,
} from "@/api/construction";
import {getSourceTags} from "@/api/menu";
import {construction_config} from "@/config";
import register from "./register";
import complete from "./complete";
import acceptance from "./accept";
import DimissHistory from "./DimissHistory";
import Upload from "./upload";
import FormDialog from "@/views/components/form-dialog";

export default {
  components: {
    DimissHistory,
    Upload,
    register,
    complete,
    acceptance,
    FormDialog,
  },
  data () {
    return {
      timestamp: null,
      active_sys: null,
      //编辑施工单位登记信息2 或 施工单位信息登记1
      registerType: null,
      systems: [],
      system: null,
      template_url: null,
      ref_type: null,
      model: {
        constructionStatus: null,
        constructionUnit: null,
        constructionPerson: null,
        constructionPhone: null,
        completeDate: null,
        acceptanceDate: null,
        completeMemo: null,
        acceptanceMemo: null,
      },
      constructionStatus: [
        "未开展",
        "施工中",
        "已接入",
        "联网完成",
        "待验收",
        null,
        "已验收",
      ],
      regVisable: false,
      dissmissable: false,
      completeVisable: false,
      attachVisable: false,
      acceptVisable: false,
      isDel: false,
      queryTypes: {
        sourceId: "",
        tagHeadId: "",
      },
    };
  },
  watch: {
    active_sys (v) {
      if (v && v !== "0") {
        this.timestamp = new Date().getMilliseconds();
        this.system = this.systems.find((f) => f.path === v);
        this.ref_type = this.system.ref_type;
        this.template_url = this.system.template_url;
        this.getConstruction();
      }
    },
  },
  created () {
    getSourceTags().then((res) => {
      this.systems = Object.keys(construction_config)
        .filter((f) => res.data.some((s) => s === f))
        .map((m) => construction_config[m]);
      // console.log(" this.systems", this.systems);
      this.active_sys = this.systems[0].path;
    });
  },
  methods: {
    getConstruction () {
      progressModel(this.active_sys).then((res) => {
        this.model = Object.assign({}, this.model, res.data);
        this.isDel = res.data.constructionStatus === 6 ? true : false;
      });
    },
    checkContract () {
      hasContract(this.active_sys).then((res) => {
        if (res.data === true) {
          this.registerType = 1;
          this.regVisable = true;
        } else {
          this.msgError("请先上传施工合同或设备采购合同");
        }
      });
    },
    onRegister () {
      this.getConstruction();
    },
    onComplete () {
      this.completeVisable = false;
    },
    onAcceptance () {
      this.acceptVisable = false;
    },
    constructionUpdate () {
      this.registerType = 2;
      this.regVisable = true;
    },
    dissmissHis () {
      const {sourceId, tagHeadId} = this.model;
      this.queryTypes.sourceId = sourceId;
      this.queryTypes.tagHeadId = tagHeadId;
      this.dissmissable = true;
    },
  },
};
</script>
<style lang="scss">
.progress {
  height: calc(100% - 20px);
  padding: 10px;
  display: flex;
  flex-flow: column;
  overflow: hidden;

  .el-tabs {
    margin-bottom: 10px;

    .el-tabs__content {
      padding: 0;
    }

    .el-tabs__item.is-active {
      color: #fff !important;
      background-color: #1890ff;
      border-right-color: #dcdfe6;
      border-left-color: #dcdfe6;
    }
  }

  .el-form {
    .el-form-item {
      margin-bottom: 0;
    }
  }

  .mb8 {
    flex: 1;
    display: flex;
    flex-flow: column;
    overflow-y: auto;
    overflow-x: hidden;
  }
}
</style>
