<template>
  <div class="upload">
    <el-divider content-position="left">附件列表</el-divider>
    <el-upload
      style="margin-bottom: 10px"
      class="upload-demo"
      :on-change="handleChange"
      action="Fake Action"
      :data="{ attachtype: attachtype }"
      :show-file-list="false"
      :auto-upload="false"
    >
      <el-button size="mini" type="primary" v-if="submitSatus" >附件上传</el-button>
    </el-upload>
    <div style="height: calc(100% - 110px)">
      <el-table
        :data="fileList"
        stripe
        style="width: 100%"
        height="100%"
        border
      >
        <el-table-column
          prop="fileName"
          label="附件名"
          show-overflow-tooltip
        ></el-table-column>
        <el-table-column v-if="submitSatus" prop="handle" label="操作" width="100" align="center">
          <template slot-scope="scope">
            <el-popconfirm
              title="确定要删除吗？"
              @onConfirm="del(scope.row.attachfileid)"
            >
              <el-button  type="text" size="small" slot="reference"
                >删除</el-button
              >
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>
<script>
import { uploadFile, fileList, delFile } from "@/api/upload";
import { dgomUpload, dgomUploadList, dgomDel } from "@/api/dgom";
export default {
  props: {
    zoperationid: {
      type: String,
      default: "",
    },
    submitSatus: {
      type: Boolean,
      default: true,
    },
  },
  data() {
    return {
      attachtype: "",
      fileList: [],
      ref_type: "Ent_DevChangeApplyHead",
    };
  },
  watch: {
    contractId: {
      handler() {
        this.getList();
      },
      immediate: true,
    },
  },
  methods: {
    getList() {
      let ref = {
        applyId: this.zoperationid,
        masterType: this.ref_type,
      };
      dgomUploadList(ref).then((res) => {
        if (res.code === 200) {
          this.fileList = res.rows || res.data;
        }
      });
    },
    del(id) {
      dgomDel(id).then((res) => {
        if (res.code === 200) {
          this.msgSuccess("附件删除成功");
          this.getList();
        }
      });
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
        reader.readAsDataURL(file.raw);
        reader.onload = (e) => {
          var imgcode = e.target.result;
          // const data = {
          //   fileName: file.name,
          //   file: imgcode,
          //   refId: _that.contractId,
          //   refType: _that.ref_type,
          //   attachType: _that.attachtype
          // }
          const data = {
            fileName: file.name,
            fileData: imgcode,
            applyId: this.zoperationid,
            masterType: "Ent_DevChangeApplyHead",
          };
          dgomUpload(data).then((res) => {
            if (res.code === 200) {
              this.msgSuccess("附件上传成功");
              this.getList();
            }
          });
        };
      }
    },
    isFile() {
      return this.fileList.length > 0;
    },
  },
};
</script>
<style lang="scss">
.upload {
  // height: 100%;
  overflow: hidden;
  height: 400px;
}
</style>
