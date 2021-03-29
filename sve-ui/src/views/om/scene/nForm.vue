<template>
  <div>
    <el-form :model="sceneForm" ref="sceneForm" label-width="100px">
      <el-row :gutter="15">
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="企业名称" prop="sourceName">
            <el-input v-model="sceneForm.sourceName" size="mini" readonly></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="检查时间" prop="dateChecked">
            <el-date-picker
              type="date"
              size="mini"
              placeholder="选择检查时间"
              v-model="sceneForm.dateChecked"
              value-format="yyyy-MM-dd"
              style="width:100%"
            ></el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="状态" prop="status">
            <el-select v-model="sceneForm.status" size="mini" style="width:100%">
              <el-option label="未录入" :value="0"></el-option>
              <el-option label="已录入" :value="1"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="执法人员" prop="checkBy">
            <el-input v-model="sceneForm.checkBy" size="mini"></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="年月" prop="reportMonth">
            <el-input v-model="sceneForm.reportMonth" size="mini" readonly></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="责任方" prop="dutyCom">
            <el-input v-model="sceneForm.dutyCom" size="mini" readonly></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="存在问题" prop="issue">
            <el-input type="textarea" v-model="sceneForm.issue" size="mini"></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="整改要求" prop="request">
            <el-input type="textarea" v-model="sceneForm.request" size="mini"></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="处理处置结果" prop="procResult">
            <el-input type="textarea" v-model="sceneForm.procResult" size="mini"></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="备注" prop="备注">
            <el-input type="textarea" v-model="sceneForm.memo" size="mini"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
  </div>
</template>

<script>
import {dgomCheckEdit} from "@/api/om";

export default {
  props: {
    values: {
      type: Object,
      default: () => ({})
    }
  },
  watch: {
    values: {
      handler (v) {
        let oV = JSON.parse(JSON.stringify(v));
        this.sceneForm = Object.assign(oV, this.sceneForm);
      },
      immediate: true
    }
  },
  data () {
    return {
      sceneForm: {}
    };
  },
  methods: {
    submitMsg () {
      this.$refs["sceneForm"].validate((valid) => {
        if (!valid) return;
        dgomCheckEdit(this.sceneForm).then(res => {
          if (res.code === 200) {
            this.$emit("isSuccess");
            this.msgSuccess(res.message);
          }
        });
      });
    }
  }
};
</script>

<style lang='scss' scoped>

</style>


