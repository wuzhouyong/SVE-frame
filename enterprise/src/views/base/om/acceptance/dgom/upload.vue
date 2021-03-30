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
      <span v-if="refType==='Ent_AcceptApplyHead'">
          <el-button size="mini" type="primary" @click="uploadType('Add')">附件上传</el-button>
      </span>
      <span v-else>
         <el-button size="mini" type="primary" @click="uploadType('device')">设备现场照片</el-button>
         <el-button size="mini" type="primary" @click="uploadType('monitoringStation')">监控站房照片</el-button>
         <el-button size="mini" type="primary" @click="uploadType('samplingPoint')">取样点照片</el-button>
      </span>
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
        <el-table-column
          v-if="refType==='Ent_AcceptApplyDetail'"
          label="附件类型"
          show-overflow-tooltip
        >
          <template slot-scope="scope">
            {{ fileType[scope.row.attach_type] }}
          </template>
        </el-table-column>
        <el-table-column prop="handle" label="操作" width="100" align="center">
          <template slot-scope="scope">
            <el-popconfirm
              title="确定要删除吗？"
              @onConfirm="del(scope.row.attach_id)"
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
import {dgomAttachList, dgomAttachUpload, dgomAttachRemove} from "@/api/dgom/upload";

export default {
  props: {
    applyId: {
      type: String,
    },
    refType: {
      type: String
    }
  },
  data () {
    return {
      fileType: {
        device: "设备现场照片",
        monitoringStation: "监控站房照片",
        samplingPoint: "取样点照片"
      },
      attachtype: "",
      fileList: [],
      attachType: "'"
    };
  },
  watch: {
    applyId: {
      handler (v) {
        if (v) {
          this.getList();
        }
      },
      immediate: true,
    },
  },
  methods: {
    uploadType (v) {
      this.attachType = v;
    },
    getList () {
      dgomAttachList(this.applyId, {refType: this.refType}).then((res) => {
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
          refType: this.refType,
          attachType: this.attachType,
        };
        dgomAttachUpload(this.applyId, data).then((res) => {
          if (res.code === 200) {
            this.msgSuccess("附件上传成功");
            this.getList();
          }
        });
      };
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
<style lang="scss" scoped>
.upload {
  overflow: hidden;
  max-height: 400px;
  height: auto;
}
</style>
