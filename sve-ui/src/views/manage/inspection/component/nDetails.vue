<template>
  <div class="inspection-details">
    <el-divider content-position="left">巡检情况详情</el-divider>
    <el-form ref="form" label-width="140px" class="show-details">
      <el-row :gutter="15">
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="企业名称：">
            {{ rectDatas.sourceName }}
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="镇街：">
            {{ rectDatas.townName }}
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="巡检时间：">
            {{ rectDatas.inspectDate }}
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="异常级别：">
            {{ rectDatas.warnLevel }}
          </el-form-item>
        </el-col>
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="异常原因：">
            <pre style="margin:0;overflow: hidden">{{ rectDatas.abnormalCause }}</pre>
          </el-form-item>
        </el-col>
        <el-col :lg="24" :md="24" :sm="24" v-if="rectDatas.rectification">
          <el-form-item label="整改情况：">
            <pre style="margin:0;">{{ rectDatas.rectification }}</pre>
          </el-form-item>
        </el-col>
        <el-col :lg="24" :md="24" :sm="24" v-if="rectDatas.expectFinishDate">
          <el-form-item label="预计整改完成时间：">
            {{ rectDatas.expectFinishDate }}
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <el-divider content-position="left">整改情况</el-divider>
    <el-form :model="rectificationForm" label-width="140px" style="height:140px" ref="rectificationForm" :rules="rules">
      <el-col :lg="24" :md="24" :sm="24">
        <el-form-item label="整改情况" prop="rectification">
          <el-input v-model="rectificationForm.rectification" type="textarea" size="mini"></el-input>
        </el-form-item>
      </el-col>
      <el-col :lg="24" :md="24" :sm="24">
        <el-form-item label="预计整改完成时间" prop="expectFinishDate">
          <el-date-picker
            v-model="rectificationForm.expectFinishDate"
            :clearable="false"
            style="width:100%"
            value-format="yyyy-MM-dd"
            type="date"/>
        </el-form-item>
      </el-col>
      <el-col :lg="24" :md="24" :sm="24">
        <el-form-item>
          <el-button type="primary" @click="submitRect">提 交</el-button>
        </el-form-item>
      </el-col>
    </el-form>
    <el-divider content-position="left">历史整改情况</el-divider>
    <el-table
      :data="historyData"
      ref="oTable"
      size="mini"
      max-height="500px"
      border
    >
      <el-table-column prop="rectification" label="整改情况" show-overflow-tooltip></el-table-column>
      <el-table-column prop="expectFinishDate" label="预计整改完成时间" width="150px"></el-table-column>
      <el-table-column prop="dateCreated" label="修改时间" width="100px"></el-table-column>
    </el-table>
  </div>
</template>

<script>
import {inspectEdit, inspectRecordList} from "@/api/inspection";

export default {
  props: {
    rectDatas: {
      type: Object
    }
  },
  watch: {
    rectDatas: {
      handler (v) {
        if (v && v.statId) {
          this.getTable(v.statId);
        }
      },
      immediate: true
    }
  },
  data () {
    return {
      rectificationForm: {},
      historyData: [],
      rules: {
        rectification: [
          {required: true, message: "分整改情况必填", trigger: "blur"}
        ],
        expectFinishDate: [
          {required: true, message: "预计整改完成时间必填", trigger: "blur"}
        ],
      }
    };
  },
  methods: {
    getTable (v) {
      inspectRecordList(v).then(res => {
        this.historyData = res.data;
      });
    },
    submitRect () {
      this.$refs["rectificationForm"].validate((valid) => {
        if (!valid) return;
        this.rectificationForm.statId = this.rectDatas.statId;
        inspectEdit(this.rectificationForm).then(res => {
          if (res.code === 200) {
            this.$emit("complete");
            this.msgSuccess("整改情况提交成功!");
          }
        });
      });
    }
  }
};
</script>

<style lang='scss' scoped>
.inspection-details {
  .show-details {
    /deep/ .el-form-item {
      margin-bottom: 5px;
    }
  }

  pre {
    font-size: 14px;
  }
}
</style>
