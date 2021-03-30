<template>
  <form-dialog
    :title="title"
    :values.sync="formData"
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
        <el-col :lg="24" :md="24" :sm="24" v-if="formData.checkpointdesc">
          <el-form-item label="监测点" prop="sourcename">
            <el-input
              :disabled="true"
              v-model="formData.checkpointdesc"
              placeholder="监测点"
              :style="{ width: '100%' }"
              size="mini"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="24" :md="24" :sm="24" v-else>
          <el-form-item label="监测点" prop="checkpointid">
            <el-select
              @change="monitorChange"
              v-model="formData.checkpointid"
              placeholder="请选择监测点"
              :style="{ width: '100%' }"
              size="mini"
            >
              <el-option
                v-for="item in Monitoring"
                :key="item.checkpointid"
                :label="item.checkpointname"
                :value="item.checkpointid"
              >
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="排污单位" prop="sourcename">
            <el-input
              v-model="formData.sourcename"
              placeholder="排污单位"
              :style="{ width: '100%' }"
              size="mini"
            ></el-input>
          </el-form-item>
        </el-col>

        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="环保负责人" prop="sourceenvhead">
            <el-input
              v-model="formData.sourceenvhead"
              placeholder="环保负责人"
              :style="{ width: '100%' }"
              size="mini"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="联系方式" prop="sourceenvheadinfo">
            <el-input
              v-model="formData.sourceenvheadinfo"
              placeholder="联系方式"
              :style="{ width: '100%' }"
              size="mini"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="运营单位" prop="opcompanyname">
            <el-input
              v-model="formData.opcompanyname"
              placeholder="运营单位"
              :style="{ width: '100%' }"
              size="mini"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="负责人" prop="opcompanyhead">
            <el-input
              v-model="formData.opcompanyhead"
              placeholder="负责人"
              :style="{ width: '100%' }"
              size="mini"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="联系方式" prop="opcompanyheadinfo">
            <el-input
              v-model="formData.opcompanyheadinfo"
              placeholder="联系方式"
              :style="{ width: '100%' }"
              size="mini"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="排污口名称" prop="checkpointname">
            <el-input
              v-model="formData.checkpointname"
              placeholder="排污口名称"
              size="mini"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="姓名" prop="oppersonname">
            <el-input
              v-model="formData.oppersonname"
              placeholder="姓名"
              :style="{ width: '100%' }"
              size="mini"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="联系电话" prop="oppersontel">
            <el-input
              v-model="formData.oppersontel"
              placeholder="联系电话"
              :style="{ width: '100%' }"
              size="mini"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="运维开始时间" prop="opstartdate">
            <el-date-picker
              value-format="yyyy-MM-dd"
              v-model="formData.opstartdate"
              type="date"
              placeholder="选择日期"
            >
            </el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="运维结束时间" prop="openddate">
            <el-date-picker
              value-format="yyyy-MM-dd"
              v-model="formData.openddate"
              type="date"
              placeholder="选择日期"
            >
            </el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="运营证编号" prop="oplicensenum">
            <el-input
              v-model="formData.oplicensenum"
              placeholder="运营证编号"
              :style="{ width: '100%' }"
              size="mini"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="发证单位" prop="oplicissueunit">
            <el-input
              v-model="formData.oplicissueunit"
              placeholder="发证单位"
              :style="{ width: '100%' }"
              size="mini"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="发证时间" prop="oplicissuedate">
            <el-date-picker
              value-format="yyyy-MM-dd"
              v-model="formData.oplicissuedate"
              type="date"
              placeholder="选择日期"
            >
            </el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="无证原因" prop="opnolicreason">
            <el-input
              v-model="formData.opnolicreason"
              placeholder="无证原因"
              :style="{ width: '100%' }"
              size="mini"
            ></el-input>
          </el-form-item>
        </el-col>

        <el-col
          :lg="24"
          :md="24"
          :sm="24"
          v-for="(item, index) in currentEquitment"
          :key="index"
        >
          <form-item
            :itemVal="item"
            :formVal="oarr.find((p) => p.datadevid === item.datadevid)"
            :ref="'formItem' + index"
            :propsKey="index"
          ></form-item>
        </el-col>
      </el-form>
    </el-row>
  </form-dialog>
</template>
<script>
// 验证规则
import rules from "./rules.js";
import formItem from "./formItem.vue";
import FormDialog from "@/views/components/form-dialog";
import {
  getMonitorDropList,
  getGasImportantList,
  getWaterImportantList,
  addWaterEquipment,
  addGasEquipment,
  editWaterEquipment,
  editGasEquipment,
  dgomDeviceList,
} from "@/api/dgom";
import { getmodule2 } from "@/api/corporateInformation";

export default {
  name: "element-form",
  components: { FormDialog, formItem },
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
    equipType: {
      type: Number,
      default: 1,
    },
  },
  data() {
    return {
      // 监测点下拉
      Monitoring: [],
      formData: {
        sourcename: "",
      },
      // 当前设施的重点设备列表
      currentEquitment: [],
      oarr: [],
      // 表单校验
      rules: rules,

      //新增状态false还是编辑状态true
      handleStatus: false,

      // 设备列表数据
      equitmentList: [],

      // opw: {
      //   checkpointid: "",
      //   sourcename: "",
      //   sourceenvhead: "",
      //   checkpointtype: "",
      //   checkpointname: "",

      //   sourceenvheadinfo: "",
      //   opcompanyname: "",
      //   opcompanytype: "",
      //   opcompanyhead: "",
      //   opcompanyheadinfo: "",
      //   oppersonname: "",
      //   oppersontel: "",
      //   opstartdate: "",
      //   openddate: "",
      //   oplicensenum: "",
      //   oplicissueunit: "",
      //   oplicissuedate: "",
      //   opnolicreason: "",
      //   opw: [],
      // },
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
    values: {
      handler(v) {
        this.formData = Object.assign(this.formData, v);
        if (v.checkpointtype === 1) {
          getWaterImportantList(v.checkpointid).then((res) => {
            this.oarr = res.data;
          });
        }
      },
      immediate: true,
    },
  },
  methods: {
    getCollects() {
      dropdown(this.sys_name).then((res) => {
        if (res.code === 200) this.collects = res.rows || res.data;
      });
    },

    async submitForm(formName) {
      // 重点设备表单验证状态
      let formItemStauts = await this.judgequipType();
      // console.log("formItemStauts", formItemStauts);
      let _that = this;
      this.$refs[formName].validate((valid) => {
        if (valid && formItemStauts) {
          let { equitmentList, formData, handleStatus, equipType } = _that;
          console.log(_that.formData);

          if (!handleStatus) {
            addWaterEquipment(formData).then((res) => {
              if (res.code === 200) {
                this.msgSuccess("添加成功");
                this.$emit("completed");
              } else {
                this.msgError(res.message);
              }
            });
          } else {
            editWaterEquipment(formData).then((res) => {
              if (res.code === 200) {
                this.msgSuccess("编辑成功");
                this.$emit("completed");
              } else {
                this.msgError(res.message);
              }
            });
          }
          // if (!handleStatus) {
          //   if (equipType == "1") {
          //     addWaterEquipment(formData).then((res) => {
          //       if (res.code === 200) {
          //         this.msgSuccess("添加成功");
          //         this.$emit("completed");
          //       } else {
          //         this.msgError(res.message);
          //       }
          //     });
          //   } else {
          //     addGasEquipment(formData).then((res) => {
          //       if (res.code === 200) {
          //         this.msgSuccess("添加成功");
          //         this.$emit("completed");
          //       } else {
          //         this.msgError(res.message);
          //       }
          //     });
          //   }
          // } else {
          //   if (equipType == "1") {
          //     addWaterEquipment(formData).then((res) => {
          //       if (res.code === 200) {
          //         this.msgSuccess("添加成功");
          //         this.$emit("completed");
          //       } else {
          //         this.msgError(res.message);
          //       }
          //     });
          //   } else {
          //     addGasEquipment(formData).then((res) => {
          //       if (res.code === 200) {
          //         this.msgSuccess("添加成功");
          //         this.$emit("completed");
          //       } else {
          //         this.msgError(res.message);
          //       }
          //     });
          //   }
          // }
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    enterpriseChange(value) {
      console.log(value);
    },
    init() {
      let { values, equipType } = this;

      getMonitorDropList(equipType).then((res) => {
        // console.log(res)
        this.Monitoring = res.data;

        // this.Monitoring = [
        //   {
        //     checkpointid: "434",
        //     checkpointname: "3333",
        //   },
        // ];
      });
      // 如果是编辑的话,将编辑的数据合并到formData
      if (Object.keys(values).length != 0 && values.checkpointid) {
        // 设置操作状态为编辑
        this.handleStatus = true;
        // 获取监测点下拉
        this.formData = Object.assign({}, values);
        // this.monitorChange(this.formData.checkpointid);
        this.monitorChange(this.formData.checkpointid);
      } else {
        getmodule2().then((res) => {
          this.formData.sourcename = res.data.fullName;
        });
      }

      this.formData.checkpointtype = equipType;
    },
    monitorChange(checkpointid) {
      // getWaterImportantList(checkpointid).then((res) => {
      dgomDeviceList(checkpointid).then((res) => {
        // this.Monitoring = res.data;
        this.currentEquitment = res.data;
      });
    },
    getFormPromise(form) {
      // console.log("form", form);
      return new Promise((resolve) => {
        form.validate((res) => {
          resolve(res);
        });
      });
    },
    judgequipType() {
      // 先判断表单是否验证完毕
      let _that = this;
      let { equipType } = this;
      let Arrayrefs = [];
      let ArrayformItem = [];
      Object.getOwnPropertyNames(_that.$refs).forEach(function (key) {
        Arrayrefs.push(_that.$refs[key][0]);
      });
      Arrayrefs.shift();
      for (let i = 0; i < Arrayrefs.length; i++) {
        ArrayformItem.push(Arrayrefs[i].$refs[`formItem${i}`]);
      }

      return Promise.all(ArrayformItem.map(this.getFormPromise))
        .then((res) => {
          // console.log("res", res);
          const validateResult = res.every((item) => !!item);
          if (validateResult) {
            // let type = equipType == "1" ? "opw" : "opg";
            _that.formData["opw"] = ArrayformItem.map((item) => {
              return item.model;
            });
            // console.log("_that.formData.list", _that.formData.list);
            // console.log("两个表单都校验通过");
            return _that.currentEquitment.length != 0 && true;
          } else {
            console.log("两个表单未校验通过");
            return false;
          }
        })
        .catch((err) => {
          console.log(123);
          console.log(err);
        });
    },
  },
  mounted() {
    // console.log("equipType", this.equipType);

    // console.log("rules", this.rules);
    this.init();
  },
};
</script>
