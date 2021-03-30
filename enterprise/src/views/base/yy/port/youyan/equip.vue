<template>
  <div class="container">
    <form-dialog
      :title="title"
      :values.sync="form"
      :rules="rules"
      label-width="110px"
      :visible.sync="dialogVisible"
      :width="width"
      @submit="submitForm"
    >
      <el-form-item label="净化设施名称" prop="facilityName">
        <el-input v-model="form.facilityName" placeholder="净化设施名称" size="mini"/>
      </el-form-item>
      <el-form-item label="电流阈值" prop="threshold">
        <el-input v-model="form.threshold" placeholder="电流阈值" size="mini"/>
      </el-form-item>
      <el-form-item label="备注" prop="memo">
        <el-input v-model="form.memo" placeholder="备注" type="textarea" size="mini"/>
      </el-form-item>
      <el-card class="box-card el-box-style">
        <el-form-item label="因子类型" label-width="90px">
          <el-select v-model="form.entity[0].eleTypeId" placeholder="因子类型" size="mini" style="width:100%" disabled>
            <el-option label="净化器电流" value="e902"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="因子名称" label-width="90px" prop="entity[0].eleDesc">
          <el-input v-model="form.entity[0].eleDesc" placeholder="因子名称" size="mini"/>
        </el-form-item>
        <el-form-item label="因子编码" label-width="90px" prop="entity[0].eleCode">
          <el-input placeholder="因子编码" v-model="form.entity[0].eleCode" size="mini" maxlength="2" show-word-limit
                    oninput="value=value.replace(/[^\d]/g,'')">
            <template slot="prepend">e902</template>
          </el-input>
        </el-form-item>
        <el-form-item label="数采名称" label-width="90px" prop="entity[0].collectId">
          <el-select v-model="form.entity[0].collectId" placeholder="数采名称" size="mini" style="width:100%">
            <el-option v-for="i in collectList" :label="i.cpName" :value="i.collectId" :key="i.collectId"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="安装位置" label-width="90px">
          <el-input v-model="form.entity[0].installLocation" placeholder="安装位置" size="mini"/>
        </el-form-item>
      </el-card>
    </form-dialog>
  </div>
</template>

<script>
import FormDialog from "@/views/components/form-dialog";
import { yyFacilityAdd, yyFacilityEdit } from "@/api/youyan";

export default {
  components: { FormDialog },
  props: {
    title: {
      type: String
    },
    values: {
      type: Object,
      default: () => ({})
    },
    visible: {
      type: Boolean,
      default: false
    },
    collectList: {
      type: Array,
      default: () => []
    },
    level: {
      type: Number
    },
    pid: {
      type: Number | String
    }
  },
  computed: {
    dialogVisible: {
      get() {
        return this.visible || false;
      },
      set(v) {
        this.$emit("update:visible", v);
      }
    }
  },
  data() {
    const validatorNum = (rule, value, callback) => {
      const reg = /^(?=[\d.]{1,7})([1-9]\d{1,6}|\d)(\.\d\d)?$/;
      if (!value) {
        callback(new Error("请输入电流阈值"));
      } else if (!reg.test(value)) {
        callback(new Error("请正确输入阈值区间为(0~9999999.99)"));
      } else {
        callback();
      }
    };
    return {
      width: "600px",
      form: {
        entity: [{
          eleTypeId: "e902",
          eleDesc: "",
          eleCode: "",
          installLocation: "",
          collectId: ""
        }]
      },
      rules: {
        facilityName: [
          { required: true, message: "请输入净化设施名称", trigger: "blur" }
        ],
        threshold: [
          { required: true, validator: validatorNum, trigger: "blur" }
        ],
        "entity[0].eleDesc": [
          { required: true, message: "请输入因子名称", trigger: "blur" }
        ],
        "entity[0].eleCode": [
          { required: true, message: "请输入因子编码", trigger: "blur" }
        ],
        "entity[0].collectId": [
          { required: true, message: "请选择数采", trigger: "blur" }
        ]
      }
    };
  },
  watch: {
    values: {
      handler(v) {
        if (Object.keys(v).length > 0) {
          let oV = JSON.parse(JSON.stringify(v));
          oV.entity.forEach(i => {
            i.eleCode = i.eleCode.substr(4, 2);
          });
          this.form = Object.assign({}, oV);
        }
      },
      immediate: true
    }
  },
  created() {},
  methods: {
    submitForm() {
      let oVal = JSON.parse(JSON.stringify(this.form));
      oVal.entity[0].eleCode = "e902" + oVal.entity[0].eleCode;
      if (this.form.facilityId) {
        yyFacilityEdit(oVal).then(res => {
          if (res.code === 200) {
            this.msgSuccess("修改排放口成功!");
            this.$emit("successReq", 3);
          }
        });
      } else {
        oVal.facilityType = "yy_jhq";
        oVal.depthLevel = this.level;
        oVal.facilityPid = this.pid;
        yyFacilityAdd(oVal).then(res => {
          if (res.code === 200) {
            this.msgSuccess("新增排放口成功!");
            this.$emit("successReq", 3);
          }
        });
      }

    }
  }
};
</script>

<style lang="scss">
.el-box-style {
  .el-card__body {
    padding-bottom: 0px;
  }

  .el-form-item {
    margin-bottom: 15px;
  }

  .el-form-item__error {
    padding-top: 0;
  }
}
</style>
