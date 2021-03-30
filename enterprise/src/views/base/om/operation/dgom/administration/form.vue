<template>
  <form-dialog
    :title="title"
    :values.sync="formData"
    :rules="rules"
    label-width="130px"
    :visible.sync="dialogVisible"
    @submit="submitForm('TankForm')"
    :width="'800px'"
  >
    <el-row :gutter="15">
      <el-form
        :model="formData"
        :rules="rules"
        size="medium"
        label-width="160px"
        ref="TankForm"
      >
        <el-col :lg="24" :md="24" :sm="24">
          <!-- <el-form-item label="企业名称" prop="storageName">
            <el-select
              v-model="formData.enterprise"
              size="mini"
              placeholder="请选择"
              @change="enterpriseChange"
            >
              <el-option
                v-for="item in enterprise"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select>
          </el-form-item> -->
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="台账类型" prop="zoperationtype">
            <el-select
              @change="zoperationtypeChange"
              v-model="formData.zoperationtype"
              placeholder="请选择台账类型"
              :style="{ width: '100%' }"
              size="mini"
            >
              <el-option
                v-for="item in operationtype"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="设备名称" prop="zdatadevid">
            <el-select
              v-model="formData.zdatadevid"
              placeholder="请选择设备名称"
              :style="{ width: '100%' }"
              size="mini"
            >
              <el-option
                v-for="item in Monitoring"
                :key="item.zdatadevid"
                :label="item.zdatadevdesc"
                :value="item.zdatadevid"
              >
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <!-- <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="排放口" prop="collectId">
            <el-input
              v-model="formData.density"
              placeholder="排放口"
              :style="{ width: '100%' }"
              size="mini"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="规格型号" prop="fullVolume">
            <el-input
              v-model="formData.density"
              placeholder="规格型号"
              :style="{ width: '100%' }"
              size="mini"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="设备编号" prop="fullVolume">
            <el-input
              v-model="formData.density"
              placeholder="设备编号"
              :style="{ width: '100%' }"
              size="mini"
            ></el-input>
          </el-form-item>
        </el-col> -->
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="维护单位" prop="zmaintenancecorp">
            <el-input
              v-model="formData.zmaintenancecorp"
              placeholder="维护单位"
              :style="{ width: '100%' }"
              size="mini"
            ></el-input>
          </el-form-item>
        </el-col>

        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="维护人员" prop="zmaintenancestaff">
            <el-input
              v-model="formData.zmaintenancestaff"
              placeholder="维护人员"
              :style="{ width: '100%' }"
              size="mini"
            ></el-input>
          </el-form-item>
        </el-col>

        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="维护日期" prop="zoperationdate">
            <el-date-picker
              value-format="yyyy-MM-dd"
              v-model="formData.zoperationdate"
              type="date"
              placeholder="选择日期"
            >
            </el-date-picker>
          </el-form-item>
        </el-col>
      </el-form>
    </el-row>
    <n-upload
      v-if="uploadStatus"
      :zoperationid="formData.zoperationid"
      ref="nUpLoad"
    ></n-upload>
  </form-dialog>
</template>
<script>
import { dropdown, elementType, add, edit } from "@/api/element";
import FormDialog from "@/views/components/form-dialog";
import nUpload from "./upload";
import { addOperation, editOperation, getEuqiumentList } from "@/api/dgom";
export default {
  name: "element-form",
  components: { FormDialog, nUpload },
  props: {
    title: {
      type: String,
    },
    handleType: {
      type: Boolean,
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
      // 文件id
      id: null,
      // 企业名称下拉
      enterprise: [],
      // 台账类型,
      operationtype: [
        {
          value: 1,
          label: "烟气日常巡检维护记录",
        },
        {
          value: 2,
          label: "烟气零漂（跨漂）校准记录",
        },
        {
          value: 3,
          label: "烟气校验测试记录",
        },
        {
          value: 4,
          label: "污水日常巡检维护记录",
        },
     
        {
          value: 5,
          label: "污水校验测试记录",
        },
        {
          value: 6,
          label: "易耗品",
        },
        {
          value: 7,
          label: "流量计强检",
        },
      ],
      formData: {
        zoperationtype: "",
        zdatadevid: "",
        zmaintenancecorp: "",
        zmaintenancestaff: "",
        zoperationdate: "",
      },

      // 表单校验
      rules: {
        zoperationtype: [
          {
            required: true,
            message: "台账类型不能为空",
            trigger: "change",
          },
        ],
        zdatadevid: [
          {
            required: true,
            message: "设备名称不能为空",
            trigger: "change",
          },
        ],
        zmaintenancecorp: [
          {
            required: true,
            message: "维护单位不能为空",
            trigger: "blur",
          },
        ],
        zmaintenancestaff: [
          {
            required: true,
            message: "维护人员不能为空",
            trigger: "blur",
          },
          // { validator: validateElement },
        ],
        zoperationdate: [
          {
            required: true,
            message: "维护日期不能为空",
            trigger: "change",
          },
          // { validator: validateElement },
        ],
      },
      uploadStatus: false,
      Monitoring: [],
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
  },

  mounted() {
    this.init();
  },
  methods: {
    init() {
      console.log(this.handleType);
      if (!this.handleType) {
        this.guid();
      } else {
        // this.id = this.values.contractId;

        this.formData = Object.assign(this.formData, this.values);
        console.log(this.formData);
        // this.zoperationtypeChange(this.formData.zoperationtype);
        getEuqiumentList(this.formData.zoperationtype).then((res) => {
          console.log(res);
          this.Monitoring = res.data;
        });
      }
      this.uploadStatus = true;
    },

    submitForm(formName) {
      if (this.$refs.nUpLoad.isFile()) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            if (!this.handleType) {
              addOperation(this.formData).then((res) => {
                if (res.code === 200) {
                  this.msgSuccess("新增成功");
                  this.$emit("completed");
                } else {
                  this.msgError(res.message);
                }
              });
            } else {
              editOperation(this.formData).then((res) => {
                if (res.code === 200) {
                  this.msgSuccess("编辑成功");
                  this.$emit("completed");
                } else {
                  this.msgError(res.message);
                }
              });
            }
          }
        });
      } else {
        this.msgError("附件必选上传!");
      }

      console.log(this.formData);
      // if (!this.form.cpElemId) {
      //   add(this.sys_name, this.form).then((res) => {
      //     if (res.code === 200) {
      //       this.msgSuccess("添加成功");
      //       this.$emit("completed");
      //     } else {
      //       this.msgError(res.message);
      //     }
      //   });
      // } else {
      //   edit(this.sys_name, this.form).then((res) => {
      //     if (res.code === 200) {
      //       this.msgSuccess("修改成功");
      //       this.$emit("completed");
      //     } else {
      //       this.msgError(res.message);
      //     }
      //   });
      // }
      console.log(this.formData);
    },
    enterpriseChange(value) {
      console.log(value);
    },
    guid() {
      let s = [];
      let hexDigits = "0123456789abcdef";
      for (var i = 0; i < 36; i++) {
        s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
      }
      s[14] = "4"; // bits 12-15 of the time_hi_and_version field to 0010
      s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1); // bits 6-7 of the clock_seq_hi_and_reserved to 01
      s[8] = s[13] = s[18] = s[23] = "-";
      this.formData.zoperationid = s.join("");
    },
    zoperationtypeChange(val) {
      this.formData.zdatadevid = ''
      getEuqiumentList(val).then((res) => {
        console.log(res);
        this.Monitoring = res.data;
      });
    },
  },
};
</script>
