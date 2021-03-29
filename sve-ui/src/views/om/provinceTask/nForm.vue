<template>
  <div>
    <el-form :model="form" :rules="rules" ref="form" label-width="100px">
      <el-row :gutter="15">
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="企业名称" prop="factoryName">
            <el-input
              v-model="form.factoryName"
              placeholder="企业名称"
              clearable
              style="width:100%"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="监控点" prop="checkpointName">
            <el-input
              v-model="form.checkpointName"
              placeholder="监控点"
              clearable
              style="width:100%"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="监测因子" prop="elementName">
            <el-input
              v-model="form.elementName"
              placeholder="监测因子"
              clearable
              style="width:100%"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="标记状态" prop="taskType">
            <el-select v-model="form.taskType" size="mini" placeholder="请选择督办类型" style="width:100%">
              <el-option label="月督办" value="月督办"></el-option>
              <el-option label="周督办" value="周督办"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="镇街" prop="omtownId">
            <el-select v-model="form.omtownId" size="mini" placeholder="请选择镇街" style="width:100%"
                       @change="v=>{townName = townList.filter(i => i.id === v)[0].name}">
              <el-option :label="i.name" :value="i.id" v-for="i in townList" :key="i.id"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="超标天数" prop="overDays">
            <el-input
              v-model.number="form.overDays"
              placeholder="超标天数"
              clearable
              style="width:100%"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="排放浓度" prop="amount">
            <el-input
              v-model="form.amount"
              placeholder="排放浓度"
              clearable
              style="width:100%"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="排放标准" prop="standardval">
            <el-input
              v-model="form.standardval"
              placeholder="排放标准"
              clearable
              style="width:100%"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="超标倍数" prop="overTimes">
            <el-input
              v-model="form.overTimes"
              placeholder="超标倍数"
              clearable
              style="width:100%"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="监测时间" prop="monitorTime">
            <el-date-picker
              type="date"
              placeholder="监测时间"
              v-model="form.monitorTime"
              value-format="yyyy-MM-dd"
              style="width:100%"
            ></el-date-picker>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
  </div>
</template>
<script>
import {dgomSuperviseAdd, dgomSuperviseEdit} from "@/api/om/task";

export default {
  props: {
    values: {
      type: Object,
      default: () => ({})
    },
    townList: {
      type: Array,
      default: () => []
    }
  },
  computed: {
    formChange () {
      const {standardval, amount} = this.form;
      return {
        standardval,
        amount
      };
    }
  },
  watch: {
    formChange: {
      handler (v) {
        if (v.standardval && v.amount && Number(v.standardval) && Number(v.amount) && !this.form.suptaskId) {
          this.$set(this.form, "overTimes", +(((v.amount - v.standardval) / v.standardval).toFixed(2)));
        }
      },
      deep: true
    },
    values: {
      handler (v) {
        if (v.suptaskId && v.townName) {
          this.townName = v.townName;
        }
        this.form = Object.assign({}, this.form, v);
      },
      immediate: true
    }
  },
  data () {
    const validateNumber = (rule, value, callback) => {
      if (!(Number(value) > 0)) {
        callback(new Error("只能输入大于0的数值"));
      } else {
        callback();
      }
    };
    return {
      form: {
        factoryName: null,
        checkpointName: null,
        elementName: null,
        omtownId: null,
        overDays: null,
        amount: null,
        standardval: null,
        overTimes: null,
        monitorTime: null,
        taskType: null
      },
      rules: {
        factoryName: [
          {required: true, message: "企业名称必填", trigger: "blur"}
        ],
        checkpointName: [
          {required: true, message: "监测点必填", trigger: "blur"}
        ],
        elementName: [
          {required: true, message: "监测因子必填", trigger: "blur"}
        ],
        taskType: [
          {required: true, message: "督办类型必填", trigger: "blur"}
        ],
        omtownId: [
          {required: true, message: "镇街必选", trigger: "blur"}
        ],
        overDays: [
          {required: true, message: "超标天数必填", trigger: "blur"},
          {type: "number", message: "超标天数为数字值"},
          {validator: validateNumber, trigger: "blur"}
        ],
        amount: [
          {required: true, message: "排放浓度必填", trigger: "blur"},
          {validator: validateNumber, trigger: "blur"}
        ],
        standardval: [
          {required: true, message: "排放标准必填", trigger: "blur"},
          {validator: validateNumber, trigger: "blur"}
        ],
        overTimes: [
          {required: true, message: "超标倍数必填", trigger: "blur"},
          {validator: validateNumber, trigger: "blur"}
        ],
        monitorTime: [
          {required: true, message: "监测时间必选", trigger: "blur"}
        ]
      },
      townName: null
    };
  },
  created () {
  },
  methods: {
    submit (v = 1) {
      this.$refs["form"].validate((valid) => {
        if (!valid) return false;
        let oV = {};
        oV.taskStatus = v;
        oV.townName = this.townName;
        oV = Object.assign({}, this.form, oV);
        if (this.form.suptaskId) {
          dgomSuperviseEdit(oV).then(res => {
            if (res.code === 200) {
              this.msgSuccess("修改成功");
              this.$emit("completed");
            } else {
              this.msgError(res.message);
            }
          });
        } else {
          dgomSuperviseAdd(oV).then(res => {
            if (res.code === 200) {
              this.msgSuccess("新增成功");
              this.$emit("completed");
            } else {
              this.msgError(res.message);
            }
          });
        }

      });

    }
  }
};
</script>

<style lang="scss" scoped>

</style>
