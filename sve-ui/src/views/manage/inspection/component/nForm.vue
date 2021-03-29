<template>
  <div>
    <el-form ref="form" :model="form" :rules="rules" label-width="145px" label-position="right">
      <el-row :gutter="15">
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="企业名称" prop="sourceName">
            <el-input
              v-model="form.sourceName"
              placeholder="请输入企业名称"
              clearable
              size="mini"
              style="width: 100%"
            />
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="镇街" prop="townCode">
            <el-select
              v-model="form.townCode"
              placeholder="请选择"
              clearable
            >
              <el-option
                v-for="(item, index) in townCodeOptions"
                :key="index"
                :label="item.regionName"
                :value="item.regionCode"
                :disabled="item.disabled"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="巡检日期" prop="inspectDate">
            <el-date-picker
              v-model="form.inspectDate"
              value-format="yyyy-MM-dd"
              type="date"
              style="width:100%"
              :clearable="false">
            </el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="预警级别" prop="warnLevel">
            <el-select
              v-model="form.warnLevel"
              clearable
            >
              <el-option label="白" value="白"></el-option>
              <el-option label="黄" value="黄"></el-option>
              <el-option label="红" value="红"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="排污单位类型" prop="keyPoint">
            <el-select
              v-model="form.keyPoint"
              clearable
            >
              <el-option label="重点排污单位" value="重点排污单位"></el-option>
              <el-option label="非重点排污单位" value="非重点排污单位"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="存在问题" prop="abnormalCause">
            <el-input
              v-model="form.abnormalCause"
              placeholder="存在问题"
              type="textarea"
              rows="4"
              size="mini"
              style="width: 100%"
            />
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
  </div>
</template>

<script>
import {getRegion} from "@/api/composite/source";
import {inspectAdd, inspectTaskEdit} from "@/api/inspection";

export default {
  created () {
    //镇街列表
    getRegion(4419).then((res) => (this.townCodeOptions = res.data));
  },
  props: {
    values: {
      type: Object,
      default: () => ({})
    }
  },
  data () {
    return {
      rules: {
        sourceName: [
          {
            required: true,
            message: "企业名称不能为空",
            trigger: "blur",
          },
        ],
        townCode: [
          {
            required: true,
            message: "镇街不能为空",
            trigger: "blur",
          },
        ],
        inspectDate: [
          {
            required: true,
            message: "巡检日期不能为空",
            trigger: "blur",
          },
        ],
        warnLevel: [
          {
            required: true,
            message: "预警级别不能为空",
            trigger: "blur",
          },
        ],
        keyPoint: [
          {
            required: true,
            message: "排污单位类型不能为空",
            trigger: "blur",
          },
        ],
        abnormalCause: [
          {
            required: true,
            message: "存在问题不能为空",
            trigger: "blur",
          },
        ],
      },
      form: {},
      townCodeOptions: []
    };
  },
  watch: {
    values: {
      handler (v) {
        if (v) {
          this.form = Object.assign({}, this.form, v);
        }
      },
      immediate: true
    }
  },
  methods: {
    sumitMes () {
      this.$refs["form"].validate((valid) => {
        if (!valid) return;
        if (this.form.statId) {
          inspectTaskEdit(this.form).then(res => {
            if (res.code === 200) {
              this.$emit("complete");
              this.msgSuccess("数据提交成功");
            }
          });
        } else {
          inspectAdd(this.form).then(res => {
            if (res.code === 200) {
              this.$emit("complete");
              this.msgSuccess("数据提交成功");
            }
          });
        }

      });
    }
  }
};
</script>

<style lang='scss' scoped>

</style>
