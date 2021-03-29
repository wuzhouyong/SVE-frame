<template>
  <div>
    <el-dialog
      :title="title"
      :visible="visible"
      width="30%"
      @close="$emit('update:visible', false)"
      :destroy-on-close="true"
      :close-on-click-modal="false"
    >
      <el-form ref="form" :model="form" label-width="90px" :rules="rules">
        <el-form-item label="污染源" prop="sourceId" v-if="platformName!=='dgom'">
          <SelectSource
            v-model="form.sourceId"
            :data="sourceList"
            placeholder="请输入污染源"
            v-bind="$attrs"
          ></SelectSource>
        </el-form-item>
        <el-form-item label="污染源" v-else prop="sourceId">
          <el-select
            v-model="form.sourceId"
            placeholder="请选择污染源"
            style="width:100%"
            filterable
            size="mini"
          >
            <el-option
              :label="i.zdatasourname"
              :value="i.zdatasourid"
              v-for="i in dgomSourceList"
              :key="i.zdatasourid"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="视频名称" prop="videoDesc">
          <el-input v-model="form.videoDesc" placeholder="请输入视频名称"></el-input>
        </el-form-item>
        <el-form-item label="视频类型" prop="videoType">
          <el-select
            v-model="form.videoType"
            placeholder="视频类型"
            style="width:100%"
            @change="onChange"
          >
            <el-option
              :label="i.dictLabel"
              :value="i.dictValue"
              v-for="i in videoTypes"
              :key="i.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="视频地址" prop="videoAddress">
          <el-input v-model="form.videoAddress" autocomplete="off" placeholder="视频地址"></el-input>
        </el-form-item>
        <el-form-item
          :label="videoTypeName==='FluorideCloud'?'AppKey':'账号'"
          prop="appkey"
          v-if="videoTypeName!==''&&videoTypeName!=='omAgent'"
        >
          <el-input
            v-model="form.appkey"
            autocomplete="off"
            :placeholder="videoTypeName==='FluorideCloud'?'AppKey':'账号'"
          ></el-input>
        </el-form-item>
        <el-form-item
          :label="videoTypeName==='FluorideCloud'?'AppSecret':'密码'"
          prop="appsecret"
          v-if="videoTypeName!==''&&videoTypeName!=='omAgent'"
        >
          <el-input
            v-model="form.appsecret"
            autocomplete="off"
            :placeholder="videoTypeName==='FluorideCloud'?'AppSecret':'密码'"
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="$emit('update:visible',false)">取消</el-button>
        <el-button type="primary" @click="submitForm">提交</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import { sourceList, videoAdd, videoEdit } from "@/api/video";
import { dgomEnterprise } from "@/api/om";
import SelectSource from "@/components/SelectSource";

export default {
  components: { SelectSource },
  props: {
    title: {
      type: String,
      default: "",
    },
    visible: {
      type: Boolean,
      default: false,
    },
    values: {
      type: Object,
      default: () => {},
    },
    platformName: {
      type: String,
      default: "",
    },
  },
  data() {
    return {
      sourceList,
      form: {
        sourceId: null,
        videoDesc: null,
        videoType: null,
        videoAddress: null,
        appkey: null,
        appsecret: null,
      },
      //视频类型
      videoTypes: [],
      rules: {
        sourceId: [
          {
            required: true,
            message: "污染源不能为空",
            trigger: "blur",
          },
        ],
        videoDesc: [
          {
            required: true,
            message: "视频名称不能为空",
            trigger: "blur",
          },
        ],
        videoType: [
          {
            required: true,
            message: "视频类型不能为空",
            trigger: "blur",
          },
        ],
      },
      videoTypeName: "",
      dgomSourceList: [],
    };
  },
  computed: {
    watchForm() {
      const { values, visible } = this;
      return {
        values,
        visible,
      };
    },
  },
  watch: {
    watchForm: {
      handler(v) {
        if (v) {
          this.videoTypeName = v.values.videoType;
          this.form =
            Object.keys(v.values).length === 0
              ? {
                  sourceId: null,
                  videoDesc: null,
                  videoType: null,
                  videoAddress: null,
                  appkey: null,
                  appsecret: null,
                }
              : Object.assign({}, this.form, v.values);
          if (this.platformName === "dgom" && this.form.sourceId) {
            this.form.sourceId = Number(this.form.sourceId);
          }
        }
      },
      immediate: true,
      deep: true,
    },
  },
  created() {
    //获取视频类型
    this.getVideoType();
    if (this.platformName === "dgom") this.getEnterprise();
  },
  methods: {
    getVideoType() {
      this.getDicts("ty_video_type").then((response) => {
        this.videoTypes = response.data;
      });
    },
    //获取企业列表
    getEnterprise() {
      dgomEnterprise().then((res) => {
        if (res.code === 200) this.dgomSourceList = res.data;
      });
    },
    //提交表单
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.values.videoId) {
            videoEdit(this.form, this.platformName).then((res) => {
              if (res.code === 200) {
                this.msgSuccess("视频设备修改成功");
                this.$emit("successRequest");
                this.$emit("update:visible", false);
              }
            });
          } else {
            videoAdd(this.form, this.platformName).then((res) => {
              if (res.code === 200) {
                this.msgSuccess("视频设备新增成功");
                this.$emit("successRequest");
                this.$emit("update:visible", false);
              }
            });
          }
        } else {
          this.msgError("请完整填写表单");
        }
      });
    },
    onChange(v) {
      this.videoTypeName = v;
    },
  },
};
</script>
