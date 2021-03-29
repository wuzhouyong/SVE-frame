<template>
  <div class="goback-wk">
    <!-- 搜索表单 -->
    <el-form :model="form" ref="form" label-width="98px" :rules="rules">
      <el-row :gutter="15">
        <el-col :lg="24">
          <el-form-item label="企业名称">
            {{ g.sourceName }}
          </el-form-item>
        </el-col>
        <el-col :lg="24">
          <el-form-item label="监控任务">
            {{ g.tagHeadName }}
          </el-form-item>
        </el-col>
        <el-col :lg="24">
          <el-form-item label="退回原因" prop="returnReason">
            <el-input
              v-model="form.returnReason"
              type="textarea"
              placeholder
              clearable
              size="mini"
              style="width: 100%"
            />
          </el-form-item>
        </el-col>
        <el-col :lg="24">
          <el-form-item label="审核人" prop="contactName">
            <el-input
              v-model="form.contactName"
              placeholder
              clearable
              size="mini"
              style="width: 100%"
            />
          </el-form-item>
        </el-col>
        <el-col :lg="24">
          <el-form-item label="联系方式" prop="contactPhone">
            <el-input
              v-model="form.contactPhone"
              placeholder
              clearable
              size="mini"
              style="width: 100%"
            />
          </el-form-item>
        </el-col>
        <el-col :lg="24">
          <el-form-item label="备注" prop="memo">
            <el-input
              v-model="form.memo"
              type="textarea"
              placeholder
              clearable
              size="mini"
              style="width: 100%"
            />
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <el-divider content-position="left">
      <el-upload
        class="upload-demo"
        :on-change="handleChange"
        action="Fake Action"
        :show-file-list="false"
        :auto-upload="false"
      >
        <el-link type="primary" icon="el-icon-upload" :underline="false">附件上传</el-link>
      </el-upload>
    </el-divider>
    <el-table :data="uploads" stripe style="width: 100%" max-height="160px" border size="mini">
      <el-table-column prop="fileName" label="附件名" show-overflow-tooltip></el-table-column>
      <el-table-column prop="address" label="操作" width="80" align="center">
        <template slot-scope="scope">
          <el-button
            type="text"
            size="small"
            @click="del(scope.$index)"
          >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <div class="goback-submit">
      <el-button type="primary" size="mini" icon="el-icon-thumb" @click="submitData" :loading="isSum">
        {{ isSum ? "数据提交中..." : "提交" }}
      </el-button>
    </div>
  </div>
</template>

<script>
import { uploadFile } from "@/api/upload";
import { monitorReject } from "@/api/composite/monitor";

export default {
  props: {
    g: {
      type: Object,
      default: () => {}
    }
  },
  data() {
    return {
      form: {},
      uploads: [],
      rules: {
        returnReason: [{ required: true, message: "必填", trigger: "blur" }],
        contactName: [{ required: true, message: "必填", trigger: "blur" }],
        contactPhone: [{ required: true, message: "必填", trigger: "blur" }]
      },
      isSum: false
    }
  },
  created() {},
  methods: {
    submitData() {
      this.isSum = true;
      this.$refs["form"].validate((v) => {
        if (!v) {
          this.isSum = false;
          return;
        }
        let oV = {
          sourceId: this.g.sourceId,
          tagHeadId: this.g.tagHeadId
        }
        oV = { ...oV, ...this.form };
        monitorReject(oV).then(res => {
          if (res.code === 200) {
            let oId = res.data;
            if (this.uploads.length > 0) {
              this.uploads.forEach(i => {
                i.refId = oId;
                uploadFile(i).then((res) => {
                  if (res.code === 200) {
                    this.msgSuccess("退回成功");
                    this.isSum = false;
                    this.$emit("gobackSuccess");
                  }
                });
              })
            } else {
              this.msgSuccess("退回成功");
              this.isSum = false;
              this.$emit("gobackSuccess");
            }
          }
        })
      })
    },
    del(index) {
      this.uploads.splice(index, 1);
      // this.uploads = this.uploads.filter((item) => {
      //   return item.file != data.file;
      // });
    },
    handleChange(file) {
      let _that = this;
      var reader = new FileReader();
      reader.readAsDataURL(file.raw);
      reader.onload = (e) => {
        var imgcode = e.target.result;
        const data = {
          fileName: file.name,
          file: imgcode,
          refId: null,
          refType: "goback_file",
          attachType: "goback_file"
        };
        this.uploads.push(data);
      };
    }
  }
}
</script>

<style lang="scss" scoped>
.goback-wk {
  /deep/ .el-form-item {
    margin-bottom: 20px;
  }

  .goback-submit {
    margin-top: 10px;
    text-align: right;
  }
}

</style>
