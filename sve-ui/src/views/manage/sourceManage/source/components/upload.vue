<template>
  <div>
    <el-upload
      class="upload-demo"
      :on-change="handleChange"
      action="Fake Action"
      :data="{attachtype:attachtype}"
      :show-file-list="false"
      :auto-upload="false"
    >
      <el-button
        size="mini"
        type="primary"
        v-for="i in fileType"
        :key="i.dictValue"
        @click="uploadFile(i.dictValue)"
      >{{i.dictLabel}}</el-button>
    </el-upload>
    <el-divider content-position="left">附件列表</el-divider>
    <el-table :data="fileList" stripe style="width: 100%" height="300px">
      <el-table-column prop="showName" label="附件名" show-overflow-tooltip></el-table-column>
      <el-table-column prop="attachType" label="附件类型" width="120" :formatter="statusFormat"></el-table-column>
      <el-table-column prop="address" label="操作" width="100" align="center">
        <template slot-scope="scope">
          <el-link
            type="primary"
            :underline="false"
            target="_blank"
            :href="getlink(scope.row.Id)"
          >下载</el-link>
          <el-divider direction="vertical"></el-divider>
          <el-button type="text" size="small" @click="del(scope.row.Id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>
<script>
import { uploadFile, fileList, delFile } from "@/api/upload";
export default {
  props: ["ids"],
  data() {
    return {
      fileType: [],
      attachtype: "",
      fileList: [],
    };
  },
  created() {
    this.getDicts("sys_file_type").then((res) => {
      if (res.code) this.fileType = res.data;
    });
    this.getList();
  },
  watch: {
    ids: {
      handler(v) {
        if (v) this.getList();
      },
    },
  },
  methods: {
    getList() {
      let ref = {
        refId: this.ids,
        refType: "bas_pollutingsource",
      };
      fileList(ref).then((res) => {
        if (res.code === 200) {
          this.fileList = res.data;
        }
      });
    },
    //附件类型
    statusFormat(row) {
      return this.selectDictLabel(this.fileType, row.attachType);
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
        reader.readAsDataURL(file.raw);
        reader.onload = (e) => {
          var imgcode = e.target.result;
          const data = {
            fileName: file.name,
            file: imgcode,
            refId: _that.ids,
            refType: "bas_pollutingsource",
            attachType: _that.attachtype,
          };
          uploadFile(data).then((res) => {
            if (res.code === 200) {
              this.msgSuccess("附件上传成功");
              this.getList();
            }
          });
        };
      }
    },
    del(v) {
      delFile(v).then((res) => {
        if (res.code === 200) {
          this.msgSuccess("附件删除成功");
          this.getList();
        }
      });
    },
    getlink(id) {
      return `${process.env.VUE_APP_BASE_API}/com/getfile/${id}`;
    },
  },
};
</script>
