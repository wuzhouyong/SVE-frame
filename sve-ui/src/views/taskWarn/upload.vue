<template>
  <div style="height:233px;overflow:hidden;">
    <el-upload
      class="upload-demo"
      :on-change="handleChange"
      action="Fake Action"
      :show-file-list="false"
      :auto-upload="false"
    >
      <el-button size="mini" type="primary" style="margin-bottom:5px;">附件上传</el-button>
    </el-upload>
    <el-table :data="fileList" border size="mini" style="width: 100%" height="100%" max-height="200px">
      <el-table-column prop="showName" label="附件名" show-overflow-tooltip></el-table-column>
      <el-table-column label="上传人类型" show-overflow-tooltip>
        <template slot-scope="scope">
          {{ fileType[scope.row.attachType] }}
        </template>
      </el-table-column>
      <el-table-column prop="address" label="操作" width="100" align="center">
        <template slot-scope="scope">
          <el-link
            type="primary"
            :underline="false"
            target="_blank"
            :href="getlink(scope.row.Id)"
            style="font-size:12px;"
          >下载
          </el-link>
          <el-divider direction="vertical"></el-divider>
          <el-button
            type="text"
            size="small"
            @click="del(scope.row.Id)"
            :disabled="scope.row.attachType!==attachType"
          >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>
<script>
import {uploadFile, fileList, delFile} from "@/api/upload";
import {contrast} from "@/config";

export default {
  props: {
    ids: {
      type: Array,
      default: () => ([])
    },
    oPath: {
      type: String | Number,
      default: ""
    },
    attachType: {
      type: String,
      default: ""
    }
  },
  data () {
    return {
      attachtype: "",
      fileList: [],
      fileType: {
        enterprise: "企业上传",
        duty: "专管员上传",
        sub: "分局上传",
        city: "市局上传"
      }
    };
  },
  created () {
    this.getList();
  },
  methods: {
    getList () {
      let ref = {
        refId: this.ids.join(","),
        refType: this.oPath+"_warn_attach",
      };
      fileList(ref).then((res) => {
        if (res.code === 200) {
          this.fileList = res.data;
        }
      });
    },
    handleChange (file) {
      let _that = this;
      var reader = new FileReader();
      reader.readAsDataURL(file.raw);
      reader.onload = (e) => {
        var imgcode = e.target.result;
        const data = {
          fileName: file.name,
          file: imgcode,
          refId: _that.ids.join(","),
          refType: _that.oPath+"_warn_attach",
          attachType: _that.attachType,
        };
        uploadFile(data).then((res) => {
          if (res.code === 200) {
            this.msgSuccess("附件上传成功");
            this.getList();
          }
        });
      };
    },
    del (v) {
      delFile(v).then((res) => {
        if (res.code === 200) {
          this.msgSuccess("附件删除成功");
          this.getList();
        }
      });
    },
    getlink (id) {
      return `${process.env.VUE_APP_BASE_API}/com/getfile/${id}`;
    },
    isUpload () {
      return this.fileList.some(i => i.attachType === this.attachType);
    },
  },
};
</script>
