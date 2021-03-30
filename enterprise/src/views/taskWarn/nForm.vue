<template>
  <div class="warn_handle_dialog">
    <el-form ref="elForm" :model="formData" :rules="rules" label-width="110px">
      <el-form-item label="异常原因" prop="warnReason">
        <el-input type="textarea" v-model="formData.warnReason" :rows="3" size="mini"></el-input>
      </el-form-item>
      <el-form-item label="处理情况说明" prop="procDesc">
        <el-input type="textarea" v-model="formData.procDesc" :rows="3" size="mini"></el-input>
      </el-form-item>
      <el-form-item label="是否恢复正常" prop="isNormal">
        <el-radio-group v-model="formData.isNormal" size="mini">
          <el-radio-button :label="true">是</el-radio-button>
          <el-radio-button :label="false">否</el-radio-button>
        </el-radio-group>
      </el-form-item>
    </el-form>
    <n-upload :ids="warnIds" :oPath="oPath" attachType="enterprise" ref="nUpload"></n-upload>
  </div>
</template>

<script>
import nUpload from "./upload";
import {platform_list} from "@/config";
import {warnProc} from "@/api/warn";

export default {
  components: {
    nUpload
  },
  props: {
    selectRows: {
      type: Array,
      Object: () => ([])
    }
  },
  watch: {
    selectRows: {
      handler (v) {
        if (v.length > 0) {
          let warnIds = v.map(i => {
            return i.warnId;
          });
          this.warnIds = warnIds;
          let {warnLevel, procStatus} = v[0];
          this.defaultForm = {
            warnIds: warnIds,
            warnLevel: warnLevel,
            procStatus: procStatus,
            userType: "enterprise"
          };
          this.oPath = platform_list.find(i => i.id === v[0].tagHeadId.toString()).path;
        }
      },
      immediate: true
    }
  },
  data () {
    return {
      oPath: null,
      defaultForm: {},
      formData: {
        isNormal: []
      },
      warnIds: [],
      rules: {
        warnReason: [
          {required: true, message: "异常原因必填", trigger: "blur"},
        ],
        procDesc: [
          {required: true, message: "处理情况说明必填", trigger: "blur"},
        ],
        isNormal: [
          {required: true, message: "是否恢复正常必填", trigger: "blur"},
        ],
      }
    };
  },
  methods: {
    handlerWarn () {
      if (this.$refs.nUpload.isUpload()) {
        this.$refs["elForm"].validate((valid) => {
          if (!valid) return;
          let oVal = Object.assign(this.defaultForm, this.formData);
          warnProc(oVal).then(res => {
            if (res.code === 200) {
              this.msgSuccess("异常处理成功");
              this.$emit("complete");
            }
          });
        });
      } else {
        this.msgError("附件必须上传!");
      }
    }
  }
};
</script>

<style lang='scss' scoped>

</style>
