<template>
  <div class="upload">
    <el-upload
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
          prop="file_name"
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
        <el-table-column prop="file_name"
                         label="类型"
                         show-overflow-tooltip>
          <template slot-scope="scope">
            {{ scope.row.attach_type === null ? "-" : scope.row.attach_type === "Add" ? "企业上传" : "审批上传" }}
          </template>

        </el-table-column>
        <el-table-column prop="handle" label="操作" width="100" align="center">
          <template slot-scope="scope">
            <el-popconfirm
              title="确定要删除吗？"
              v-if="scope.row.attach_type==='Approval'"
              @onConfirm="del(scope.row.attach_id)"
            >
              <el-button type="text" size="small" slot="reference">删除</el-button>
            </el-popconfirm>
            <span v-else>-</span>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>
<script>
import {dgomAttachList, dgomAttachUpload, dgomAttachRemove} from "@/api/om/upload";

export default {
  props: {
    oId: {
      type: String | Number,
    },
  },
  data () {
    return {
      attachtype: "",
      fileList: [],
      ref_type: "Ent_DevChangeApplyHead",
    };
  },
  watch: {
    oId: {
      handler () {
        this.getList();
      },
      immediate: true,
    },
  },
  methods: {
    getList () {
      dgomAttachList(this.oId, {refType: "Ent_DevChangeApplyHead"}).then((res) => {
        if (res.code === 200) {
          this.fileList = res.data;
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
      var reader = new FileReader();
      reader.readAsDataURL(file.raw);
      reader.onload = (e) => {
        var imgcode = e.target.result;
        const data = {
          fileName: file.name,
          fileData: imgcode,
          refType: "Ent_DevChangeApplyHead",
          attachType: "Approval"
        };
        dgomAttachUpload(this.oId, data).then((res) => {
          if (res.code === 200) {
            this.msgSuccess("附件上传成功");
            this.getList();
          }
        });
      };
    },
    isFile () {
      let isUpload = this.fileList.some(i => i.attach_type === "Approval");
      return isUpload;
    },
    getlink (id) {
      return `${process.env.VUE_APP_BASE_API}/dgom/attach/download/${id}`;
    },
  },
};
</script>
<style lang="scss" scoped>
.upload {
  overflow: hidden;
  max-height: 400px;
  height: auto;
}
</style>
