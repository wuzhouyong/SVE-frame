<template>
  <div class="upload">
    <el-divider content-position="left">附件列表</el-divider>
    <el-upload
      v-if="!isRead"
      style="margin-bottom: 10px"
      class="upload-demo"
      :on-change="handleChange"
      action="Fake Action"
      :data="{ attachtype: attachtype }"
      :show-file-list="false"
      :auto-upload="false"
    >
      <el-button size="mini" type="primary">附件上传</el-button>
    </el-upload>
    <div style="height: calc(100% - 110px)">
      <el-table
        :data="fileList"
        stripe
        size="mini"
        style="width: 100%"
        max-height="300px"
        border
      >
        <el-table-column
          label="附件名"
          show-overflow-tooltip
        >
          <template slot-scope="scope">
            <el-link
              type="primary"
              :underline="false"
              target="_blank"
              :href="getlink(scope.row.attach_id)"
            >{{ scope.row.file_name }}
            </el-link>
          </template>
        </el-table-column>
        <el-table-column prop="handle" label="操作" width="100" align="center" v-if="!isRead">
          <template slot-scope="scope">
            <el-popconfirm
              title="确定要删除吗？"
              @onConfirm="del(scope.row.attachfileid)"
            >
              <el-button type="text" size="small" slot="reference"
              >删除
              </el-button
              >
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>
<script>
import {dgomAttachList, dgomAttachRemove, dgomAttachUpload} from "@/api/dgom/upload";

export default {
  props: {
    zoperationid: {
      type: String,
      default: "",
    },
    isRead: {
      type: Boolean,
      default: false
    }
  },
  data () {
    return {
      attachtype: "",
      fileList: [],
      ref_type: "Ent_DevChangeApplyHead",
    };
  },
  watch: {
    zoperationid: {
      handler (v) {
        if (v) {
          this.getList();
        }
      },
      immediate: true,
    },
  },
  methods: {
    getList () {
      dgomAttachList(this.zoperationid, {refType: "Ent_DevChangeApplyHead"}).then((res) => {
        if (res.code === 200) {
          this.fileList = res.rows || res.data;
        }
      });
    },
    del (id) {
      dgomAttachRemove(id).then((res) => {
        if (res.code === 200) {
          this.msgSuccess("附件删除成功");
          this.getList();
        }
      });
    },
    handleChange (file) {
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
          const data = {
            fileName: file.name,
            fileData: imgcode,
            attachType:'Add',
            refType: "Ent_DevChangeApplyHead",
          };
          dgomAttachUpload(this.zoperationid, data).then((res) => {
            if (res.code === 200) {
              this.msgSuccess("附件上传成功");
              this.getList();
            }
          });
        };
      }
    },
    isFile () {
      return this.fileList.length > 0;
    },
    getlink (id) {
      return `${process.env.VUE_APP_BASE_API}/dgom/attach/download/${id}`;
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
