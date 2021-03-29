<template>
  <div>
    <el-divider content-position="left" v-if="sData.length===1">严重超标情况明细</el-divider>
    <el-form label-width="110px" class="show-form" v-if="sData.length===1">
      <el-row :gutter="15">
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="企业名称：">
            <p class="oTextHidden" :title="sData[0].factoryName">{{ sData[0].factoryName }}</p>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="监控点：">
            <p class="oTextHidden" :title="sData[0].checkpointName">{{ sData[0].checkpointName }}</p>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="监测因子：">
            <p class="oTextHidden" :title="sData[0].elementName">{{ sData[0].elementName }}</p>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="镇街：">
            {{ sData[0].townName }}
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="超标天数：">
            {{ sData[0].overDays }}
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="排放浓度：">
            {{ sData[0].amount }}
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="排放标准：">
            {{ sData[0].standardval }}
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="超标倍数：">
            {{ sData[0].overTimes }}
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="监测时间：">
            {{ sData[0].monitorTime }}
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="任务状态：">
            {{ statusType[sData[0].taskStatus] }}
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="开始时间：">
            {{ sData[0].startDate }}
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="结束时间：">
            {{ sData[0].endDate }}
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <el-divider content-position="left">超标核实反馈</el-divider>
    <el-form :model="form" :rules="rules" ref="form" label-width="110px">
      <el-row :gutter="15">
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="处理人" prop="handleby">
            <el-input
              v-model="form.handleby"
              placeholder="处理人"
              clearable
              style="width:100%"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="联系方式" prop="contactPhone">
            <el-input
              v-model="form.contactPhone"
              placeholder="联系方式"
              clearable
              style="width:100%"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="处理时间" prop="handleTime">
            <el-date-picker
              type="date"
              placeholder="处理时间"
              v-model="form.handleTime"
              value-format="yyyy-MM-dd"
              style="width:100%"
            ></el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="超标是否属实" prop="istrue">
            <el-radio-group v-model="form.istrue" size="mini">
              <el-radio-button label="1">是</el-radio-button>
              <el-radio-button label="0">否</el-radio-button>
            </el-radio-group>
          </el-form-item>
        </el-col>
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="超标原因" prop="reason">
            <el-input
              type="textarea"
              v-model="form.reason"
              placeholder="超标原因"
              clearable
              style="width:100%"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="整改情况" prop="recityinfo">
            <el-input
              type="textarea"
              v-model="form.recityinfo"
              placeholder="整改情况"
              clearable
              style="width:100%"
            >
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="查处情况" prop="situation">
            <el-input
              type="textarea"
              v-model="form.situation"
              placeholder="查处情况"
              clearable
              style="width:100%"
            >
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <el-divider content-position="left">
      <el-upload
        class="upload-demo"
        :on-change="handleChange"
        action="Fake Action"
        :data="{attachtype:attachtype}"
        :show-file-list="false"
        :auto-upload="false"
      >
        <el-button type="primary" icon="el-icon-upload">附件上传</el-button>
      </el-upload>
    </el-divider>
    <el-table
      :data="fileData"
      size="mini"
      border
      max-height="200px"
      style="width: 100%">
      <el-table-column
        prop="fileName"
        label="附件名"
        show-overflow-tooltip>
      </el-table-column>
      <el-table-column label="操作" width="100" align="center">
        <template slot-scope="scope">
          <el-link type="danger" style="font-size:12px" @click="delFile(scope.$index)">删除</el-link>
        </template>
      </el-table-column>
    </el-table>
    <el-divider content-position="left" v-if="sData.length>1">已选择处理企业</el-divider>
    <el-table
      :data="sData"
      size="mini"
      border
      height="100%"
      v-if="sData.length>1"
      style="width: 100%">
      <el-table-column
        prop="factoryName"
        label="企业名称"
        show-overflow-tooltip>
      </el-table-column>
      <el-table-column
        prop="checkpointName"
        label="监控点名称"
        show-overflow-tooltip>
      </el-table-column>
      <el-table-column
        prop="elementName"
        show-overflow-tooltip
        label="监控因子">
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import { dgomDivideHandle } from "@/api/om/task"
import { uploadFile } from "@/api/upload";

export default {
  props: {
    sData: {
      type: Array,
      default: () => []
    }
  },
  data() {
    return {
      attachtype: "",
      form: {
        handleby: null,
        contactPhone: null,
        handleTime: null,
        istrue: "1",
        reason: null,
        recityinfo: null,
        situation: null
      },
      rules: {
        handleby: [
          { required: true, message: "处理人必填", trigger: "blur" }
        ],
        contactPhone: [
          { required: true, message: "联系方式必填", trigger: "blur" }
        ],
        handleTime: [
          { required: true, message: "处理时间必填", trigger: "blur" }
        ],
        istrue: [
          { required: true, message: "超标是否属实必选", trigger: "blur" }
        ],
        reason: [
          { required: true, message: "超标原因必填", trigger: "blur" }
        ],
        recityinfo: [
          { required: true, message: "整改情况必填", trigger: "blur" }
        ],
        situation: [
          { required: true, message: "查处情况必填", trigger: "blur" }
        ]
      },
      statusType: ["", "草稿", "分局待处理", "超时未处理", "市局待核实", "市局退回", "已完成"],
      fileData: []
    }
  },
  created() {},
  methods: {
    handleTask() {
      this.$refs["form"].validate((valid) => {
        if (!valid) return false;
        if (this.fileData.length > 0) {
          let ids = [];
          this.sData.forEach(i => {
            ids.push(i.suptaskId);
          })
          this.fileData.forEach(j => {
            j.refId = ids.join(",");
            uploadFile(j).then((res) => {
              if (res.code === 200) {
                this.msgSuccess("附件上传成功");
              }
            });
          })
          this.$nextTick(() => {
            let newData = JSON.parse(JSON.stringify(this.sData))
            newData.forEach(i => {
              i = Object.assign(i, this.form);
            })
            this.hadnleSendMes(newData);
          })
        } else {
          this.msgError("附件必须上传!");
        }
      })
    },
    hadnleSendMes(v) {
      dgomDivideHandle(v).then(res => {
        if (res.code === 200) {
          this.msgSuccess("任务处理成功");
          this.$emit("completed");
        }
      })
    },
    handleChange(file) {
      var reader = new FileReader();
      reader.readAsDataURL(file.raw);
      reader.onload = (e) => {
        var imgcode = e.target.result;
        const data = {
          fileName: file.name,
          file: imgcode,
          refId: null,
          refType: "dgom_branch_task",
          attachType: "dgom_branch_task"
        };
        this.fileData.push(data);

      };
    },
    delFile(v) {
      this.fileData.splice(v, 1);
    }
  }
}
</script>

<style lang="scss" scoped>
p.oTextHidden {
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  margin: 0;
  padding: 0;
}

.show-form {
  /deep/ .el-form-item {
    margin-bottom: 5px;
  }
}
</style>
