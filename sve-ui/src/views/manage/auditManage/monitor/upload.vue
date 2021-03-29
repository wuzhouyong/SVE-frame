<template>
  <div>
    <el-upload
      style="margin-bottom: 10px;"
      class="upload-demo"
      :on-change="handleChange"
      action="Fake Action"
      :show-file-list="false"
      :auto-upload="false"
    >
      <el-button size="mini" type="danger" @click="fileType(3)">
        {{ trimType === "1" ? "*任务调整说明（加盖公章）" : "*分局初审意见（加盖公章）" }}
      </el-button>
      <el-button size="mini" type="primary" @click="fileType(4)">附件上传</el-button>
    </el-upload>
    <el-table :data="oFileList" stripe style="width: 100%" border size="mini">
      <el-table-column prop="showName" label="附件名" show-overflow-tooltip></el-table-column>
      <el-table-column label="类型">
        <template slot-scope="scope">
          <div>{{
              scope.row.attachType === "task_branch" ? "分局初审意见" : scope.row.attachType === "city_task_branch" ? "市局任务调整说明" : "普通附件"
            }}
          </div>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="100" align="center">
        <template slot-scope="scope">
          <el-popconfirm title="确定要删除吗？" @onConfirm="del(scope.row.Id)">
            <el-button type="text" size="small" slot="reference">删除</el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>
<script>
import { uploadFile, fileList, delFile } from "@/api/upload";

export default {
  name: "upload",
  props: {
    refId: {
      type: String
    },
    trimType: {
      type: String,
      default: "0"
    }
  },
  data() {
    return {
      attachtype: "",
      oFileList: [],
      uploads: []
    };
  },
  methods: {
    del(v) {
      delFile(v).then((res) => {
        if (res.code === 200) {
          this.msgSuccess("附件删除成功");
          this.getFile(this.refId);
        }
      });
    },
    fileType(v) {
      if (v === 3) {
        this.attachtype = this.trimType === "1" ? "city_task_branch" : "task_branch";
      } else {
        this.attachtype = "other_task"
      }

    },
    getFile(v) {
      let ref = {
        refId: v,
        refType: "task_apply"
      };
      fileList(ref).then((res) => {
        this.oFileList = res.data;
      });
    },
    handleChange(file) {
      if (file.size / (1024 * 1024) > 100) {
        this.$notification["warning"]({
          message: "提示",
          description: "附件大小不超过100Mb！",
          duration: 2
        });
        return false;
      } else {
        let _that = this;
        var reader = new FileReader();
        reader.readAsDataURL(file.raw);
        reader.onload = (e) => {
          var imgcode = e.target.result;
          const data = {
            fileName: file.name,
            file: imgcode,
            refId: _that.refId,
            refType: "task_apply",
            attachType: _that.attachtype
          };
          uploadFile(data).then((res) => {
            if (res.code === 200) {
              this.msgSuccess("附件上传成功");
              this.getFile(this.refId);
            } else {
              this.msgError(file.name + "附件上传失败!");
            }
          });
        };
      }
    },
    hasFile() {
      if (this.oFileList.length > 0) {
        if (this.oFileList.some((i) => i.attachType === "task_branch" || i.attachType === "city_task_branch")) {
          return true;
        } else {
          this.trimType === "1" ? this.msgError("任务调整说明必须上传") : this.msgError("分局初审意见必须上传");
          return false;
        }
      } else {
        this.trimType === "1" ? this.msgError("请上传附件 ( 注:任务调整说明必须上传 )") : this.msgError("请上传附件 ( 注:分局初审意见必须上传 )");
        return false;
      }
    }
  }
};
</script>
