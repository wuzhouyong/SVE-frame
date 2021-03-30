<template>
  <div>
    <el-row :gutter="15">
      <el-form :model="formData" :rules="rules" size="medium" label-width="140px" ref="waterForm">
        <el-col :lg="12" :md="24" :sm="24">
          <el-form-item label="水表名称" prop="meterName">
            <el-input
              v-model="formData.meterName"
              placeholder="水表名称"
              :style="{width: '100%'}"
              size="mini"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="24" :sm="24">
          <el-form-item label="数采仪" prop="collectId">
            <el-select
              v-model="formData.collectId"
              placeholder="请选择数采仪"
              :style="{width: '100%'}"
              size="mini"
              @change="collectChange"
            >
              <el-option
                v-for="item in collects"
                :key="item.collectId"
                :label="item.cpName"
                :value="item.collectId"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="备注" prop="memo">
            <el-input type="textarea" v-model="formData.memo" placeholder="备注"></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24" class="elBox-style">
          <el-card class="box-card">
            <el-form-item label="因子类型:" label-width="80px">工业总用水进水流量(w100)</el-form-item>
            <el-form-item label="因子名称:" label-width="80px">流量</el-form-item>
            <el-form-item label="因子编码:" label-width="80px">w100{{ maxEl }}</el-form-item>
            <el-form-item label="数采仪:" label-width="80px">{{ collectName || "-" }}</el-form-item>
          </el-card>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24" class="elBox-style">
          <el-card class="box-card">
            <el-form-item label="因子类型:" label-width="80px">工业总用水进水瞬时流量(w101)</el-form-item>
            <el-form-item label="因子名称:" label-width="80px">瞬时流量</el-form-item>
            <el-form-item label="因子编码:" label-width="80px">w101{{ maxEl }}</el-form-item>
            <el-form-item label="数采仪:" label-width="80px">{{ collectName || "-" }}</el-form-item>
          </el-card>
        </el-col>
      </el-form>
    </el-row>
  </div>
</template>
<script>
import {lxMeterinfoAdd, lxMeterinfoEdit, lxGetInfo} from "@/api/lingxing";

export default {
  props: {
    values: {
      type: Object,
      default: () => {
      },
    },
    collects: {
      type: Array,
      default: () => [],
    },
  },
  data () {
    return {
      formData: {},
      defaultData: {},
      rules: {
        meterName: [{required: true, message: "水表必填", trigger: "blur"}],
        collectId: [{required: true, message: "数采仪必填", trigger: "blur"}],
      },
      eloptions: [
        {value: "w100", label: "自来水工业用水流量"},
        {value: "w101", label: "自来水工业用水瞬时流量"},
      ],
      entity: [
        {
          eleTypeId: "w100",
          eleDesc: "自来水工业用水流量",
          eleCode: "w100",
          collectId: "",
          installLocation: "",
        },
        {
          eleTypeId: "w101",
          eleDesc: "自来水工业用水瞬时流量",
          eleCode: "w101",
          collectId: "",
          installLocation: "",
        },
      ],
      collectName: "",
      maxEl: "",
    };
  },
  watch: {
    values: {
      handler (v) {
        if (v) {
          this.defaultData = JSON.parse(JSON.stringify(v));
          this.formData = Object.assign({}, this.formData, v);
          if (this.formData.entity && this.formData.entity.length > 0) {
            this.$set(
              this.formData,
              "collectId",
              this.formData.entity[0].collectId
            );
            this.collectChange(this.formData.entity[0].collectId);
          }
        }
      },
      immediate: true,
    },
  },
  methods: {
    submit () {
      this.$refs["waterForm"].validate((valid) => {
        if (!valid) return;
        this.entity.forEach((i) => {
          i.collectId = this.formData.collectId;
          i.eleCode = i.eleCode + this.maxEl;
        });
        this.formData.entity = this.entity;
        this.formData.meterType = 0;
        if (this.formData.meterid) {
          lxMeterinfoEdit(this.formData).then((res) => {
            if (res.code === 200) {
              this.msgSuccess("修改成功");
              this.$emit("completed");
            } else {
              this.msgError(res.message);
            }
          });
        } else {
          lxMeterinfoAdd(this.formData).then((res) => {
            if (res.code === 200) {
              this.msgSuccess("添加成功");
              this.$emit("completed");
            } else {
              this.msgError(res.message);
            }
          });
        }
      });
    },
    collectChange (v) {
      if (this.defaultData.collectId === v) {
        let defaultCode = this.defaultData.entity[0].eleCode.substring(4, 6);
        this.maxEl = defaultCode;
      } else {
        this.getInfoEl(v);
      }
      this.collects.forEach((i) => {
        if (i.collectId === v) {
          this.collectName = i.cpName;
        }
      });
    },
    getInfoEl (v) {
      this.loading = true;
      lxGetInfo({collectId: v, eleTypeId: "w100"}).then((res) => {
        if (res.code === 200) {
          this.maxEl = res.data || "01";
        }
        this.loading = false;
      });
    },
  },
};
</script>
<style lang="scss">
.elBox-style {
  .el-card__body {
    padding: 10px;

    .el-form-item {
      margin-bottom: 5px;
    }
  }
}
</style>
