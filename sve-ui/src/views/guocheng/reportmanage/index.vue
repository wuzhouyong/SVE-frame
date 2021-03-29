<template>
  <div class="task-wk">
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
        icon="el-icon-upload"
      >附件上传
      </el-button>
    </el-upload>
    <!-- 表格 -->
    <el-table
      v-loading="loading"
      :data="fileList"
      style="width: 100%;flex:1;"
      ref="oTable"
      height="100%"
      size="mini"
      border
    >
      <el-table-column prop="showName" label="附件名" width="240"></el-table-column>
      <el-table-column prop="createdBy" label="上传人" width="180"></el-table-column>
      <el-table-column prop="dateCreated" label="上传时间" width="180"></el-table-column>
      <el-table-column label="操作" width="120" align="center">
        <template slot-scope="scope">
          <el-link
            type="primary"
            :underline="false"
            target="_blank"
            style="font-size:12px;"
            :href="getlink(scope.row.Id)"
          >下载
          </el-link>
          <el-divider direction="vertical"></el-divider>
          <el-button type="text" size="small" @click="del(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import { fileList, uploadFile, delFile } from "@/api/upload";

export default {
  data() {
    return {
      loading: false,
      attachtype: "",
      fileList: []
    }
  },
  created() {
    this.getList();
  },
  methods: {
    getList() {
      let ref = {
        refId: "11",
        refType: "guocheng_onduty_report"
      };
      fileList(ref).then((res) => {
        if (res.code === 200) {
          this.fileList = res.data;
        }
      });
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
          refId: "11",
          refType: "guocheng_onduty_report",
          attachType: "guocheng_onduty_report"
        };
        uploadFile(data).then((res) => {
          if (res.code === 200) {
            this.msgSuccess("附件上传成功");
            this.getList();
          }
        });
      };
    },
    del(v) {
      this.$confirm("是否确认删除数据?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(function() {
          return delFile(v.Id);
        })
        .then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
        .catch(function() {});
    },
    getlink(id) {
      return `${process.env.VUE_APP_BASE_API}/com/getfile/${id}`;
    }
  }
}
</script>

<style lang="scss" scoped>

</style>
