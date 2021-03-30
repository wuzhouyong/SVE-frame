<template>
  <div>
    <el-upload
      v-if="!isView"
      class="upload-demo"
      :on-change="handleChange"
      action="Fake Action"
     
      :show-file-list="false"
      :auto-upload="false"
    >
      <el-divider class="edit-model" content-position="left"
        >附件列表
        <el-button
          class="asset-up"
          size="mini"
          type="primary"
          plain
          @click="uploadFile"
        >
          上传附件
        </el-button>
        <span class="asset-msg"
          >{{ fileList.length > 0 ? "" : "（该合同无附件）" }}
        </span>
      </el-divider>
    </el-upload>
    <el-divider class="view-model" content-position="left" v-if="isView"
      >附件列表
      <span class="asset-msg"
        >{{ fileList.length > 0 ? "" : "（该合同无附件）" }}
      </span>
    </el-divider>

    <el-table :data="fileList" stripe style="width: 100%" height="300px">
      <el-table-column
        prop="showName"
        label="附件名"
        show-overflow-tooltip
      ></el-table-column>
      <!-- <el-table-column prop="TYPENAME" label="附件类型"></el-table-column> -->
      <el-table-column prop="address" label="操作" width="100" align="center">
        <template slot-scope="scope">
          <!-- <el-link
            type="primary"
            :underline="false"
            target="_blank"
            :href="getlink(scope.row.ID)"
          >下载
          </el-link> -->
          <el-divider direction="vertical"></el-divider>
          <el-button
            type="text"
       
            size="small"
            @click="del(scope.row.Id)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>
<script>
import { uploadFile, fileList, delFile } from "@/api/upload";

export default {
  name: "UploadTool",
  // props: ["ids", "refType", "recordType", "status",],
  props: {
    refId: {
      type: Number,
      default: 0,
    },
    refType: {
        type: String,
        required: true
    },
    recordType: {
      type: String,
      default: "标题",
    },
    attachType:{
      type: String
    }
  },
  data() {
    return {
      fileType: [{ dictLabel: "文件", dictValue: 1 }],
      // attachtype: "",
      fileList: [],
      isView: "",
    };
  },
  created() {
    // this.getDicts("sys_file_type").then(res => {
    //   if (res.code) this.fileType = res.data;
    // });
    // if (this.status == "view") {
    //   this.isView = true;
    // } else {
    //   this.isView = false;
    // }
    // this.isView = this.status===null || this.status === 'view';
    // // this.isView  = true
    // console.log("this.isView",this.isView);
    this.ids = this.refId;
    this.ids && this.getList(this.ids);
  },
  methods: {
    getList(fileId) {
      let ref = {
        refId: fileId,
        refType: this.refType,
      };
      fileList(ref).then((res) => {
        if (res.code === 200) {
          this.fileList = res.data;
        }
      });
    },
    //附件类型
    uploadFile(v) {
      this.attachtype = v;
    },
    handleChange(file) {
      if (file.size / (1024 * 1024) > 100) {
        this.$notification["warning"]({
          message: "提示",
          description: "附件大小不超过100Mb！",
          duration: 2,
        });
        return false;
      } else {
        let _that = this;
        var reader = new FileReader();
        !_that.ids &&  this.guid();
        reader.readAsDataURL(file.raw);
        reader.onload = (e) => {
          var imgcode = e.target.result;

          const data = {
            fileName: file.name,
            file: imgcode,
            refId: _that.ids,
            refType: _that.refType,
            attachType: _that.attachType,
          };
          uploadFile(data).then((res) => {
            if (res.code === 200) {
              this.$emit('contractId', _that.ids);
              this.msgSuccess("附件上传成功");
              this.getList(_that.ids);
            }
          });
        };
      }
    },
    del(v) {
      delFile({ refId: v, refType: this.refType }).then((res) => {
        if (res.code === 200) {
          this.msgSuccess("附件删除成功");
          this.getList(this.ids);
        }
      });
    },
    getlink(id) {
      //console.log(process.env.VUE_APP_BASE_API);
      return `${process.env.VUE_APP_BASE_API}/com/file/getfile?refId=${id}&refType=${this.refType}&zsourceid=${this.zsourceid}`;
      // getFile({refId:id,refType:this.refType}).then(res=>{
      //   this.download(res);
      // });
    },
    guid() {
      let s = [];
      let hexDigits = "0123456789abcdef";
      for (var i = 0; i < 36; i++) {
        s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
      }
      s[14] = "4"; // bits 12-15 of the time_hi_and_version field to 0010
      s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1); // bits 6-7 of the clock_seq_hi_and_reserved to 01
      s[8] = s[13] = s[18] = s[23] = "-";
      this.ids = s.join("");
    },
  },
};
</script>

<style lang="scss">
.upload-demo {
  .el-upload {
    width: 100%;
  }

  .asset-msg {
    margin-left: 20px;
    color: #ffc107;
  }

  .asset-up {
    margin-left: 20px;
  }
}
</style>
