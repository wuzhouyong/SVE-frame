<template>
  <form-dialog
    :title="title"
    :values.sync="form"
    :rules="rules"
    label-width="130px"
    :visible.sync="dialogVisible"
    @submit="submitForm"
  >
    <el-form-item label="检测井" prop="checkPointName">
      <el-input v-model="form.checkPointName" placeholder="请输入检测井名称" />
    </el-form-item>
    <el-form-item label="检测井类型" prop="checkPointType">
      <el-select v-model="form.checkPointType" placeholder="请选择检测井类型" style="width:100%">
        <el-option
          :label="i.dictLabel"
          :value="i.dictValue"
          v-for="i in checkPointTypeList"
          :key="i.dictValue"
        ></el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="检测井密码" prop="collectPass" v-if="!form.collectId">
      <el-input v-model="form.collectPass" placeholder="请输入检测井密码" />
    </el-form-item>
    <el-form-item label="设备品牌" prop="brandName">
      <el-input v-model="form.brandName" placeholder="请输入设备品牌" />
    </el-form-item>
    <!-- <el-form-item label="MN码" prop="mn">
            <el-input v-model="form.mn" placeholder="请输入MN码" />
    </el-form-item>-->
    <el-form-item label="经度" prop="longitude">
      <el-input v-model="form.longitude" placeholder="请输入经度 (113.516667~114.250000)" />
    </el-form-item>
    <el-form-item label="纬度" prop="latitude">
      <el-input v-model="form.latitude" placeholder="请输入纬度 (22.650000 ~23.150000)" />
    </el-form-item>
    <el-form-item label="Ip地址" prop="ipAddress">
      <el-input v-model="form.ipAddress" placeholder="请输入Ip地址" />
    </el-form-item>
  </form-dialog>
</template>
<script>
import { add, edit, getMN } from "@/api/collect";
import FormDialog from "@/views/components/form-dialog";
export default {
  name: "checkpoint-form",
  components: { FormDialog },
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
    checkPointTypeList: {
      type: Array,
      default: () => [],
    },
  },
  data() {
    let checkLon = (rule, value, callback) => {
      if (parseFloat(value) >= 113.516667 && parseFloat(value) <= 114.25) {
        callback();
      } else {
        callback(new Error("请输入正确的经度"));
      }
    };
    let checkLat = (rule, value, callback) => {
      if (parseFloat(value) >= 22.65 && parseFloat(value) <= 23.15) {
        callback();
      } else {
        return callback(new Error("请输入正确的纬度"));
      }
    };

    return {
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        ipAddress: [],
        brandName: [
          {
            required: true,
            message: "设备品牌不能为空",
            trigger: "blur",
          },
        ],
        collectPass: [
          {
            required: true,
            message: "数采密码不能为空",
            trigger: "blur",
          },
          {
            pattern: /^\d{6}$/,
            message: "密码只能为6位数字",
          },
        ],
        checkPointName: [
          {
            required: true,
            message: "检测井不能为空",
            trigger: "blur",
          },
        ],
        checkPointType: [
          {
            required: true,
            message: "检测井类型不能为空",
            trigger: "blur",
          },
        ],
        longitude: [
          {
            required: true,
            message: "经度不能为空",
            trigger: "blur",
          },
          { validator: checkLon, trigger: "blur" },
        ],
        latitude: [
          {
            required: true,
            message: "纬度不能为空",
            trigger: "blur",
          },
          { validator: checkLat, trigger: "blur" },
        ],
      },
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
  watch: {
    values() {
      this.form = Object.assign({}, this.values);
    },
  },
  methods: {
    // getMN() {
    //     getMN().then(res => {

    //     });
    // },
    submitForm(v) {
      if (!this.form.checkPointId) {
        add(this.sys_name, this.form).then((res) => {
          if (res.code === 200) {
            this.msgSuccess("添加成功");
            this.$emit("completed");
          } else {
            this.msgError(res.message);
          }
        });
      } else {
        edit(this.sys_name, this.form).then((res) => {
          if (res.code === 200) {
            this.msgSuccess("修改成功");
            this.$emit("completed");
          } else {
            this.msgError(res.message);
          }
        });
      }
    },
  },
};
</script>