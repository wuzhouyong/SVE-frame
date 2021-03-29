<template>
  <div style="height:233px;overflow:hidden;">
    <el-upload
      class="upload-demo"
      :on-change="handleChange"
      action="Fake Action"
      :show-file-list="false"
      :auto-upload="false"
    >
      <el-button size="mini" type="primary">附件上传</el-button>
    </el-upload>
    <el-divider content-position="left">附件列表</el-divider>
    <el-table :data="fileList" stripe style="width: 100%" height="160px">
      <el-table-column prop="showName" label="附件名" show-overflow-tooltip></el-table-column>
      <el-table-column prop="attachType" label="附件类型" width="120">
        <template slot-scope="scope">
          <div>
            <span v-if="scope.row.attachType==='duty_warn_type'">值守核实</span>
            <span v-else-if="scope.row.attachType==='substation_warn_type'">分局处理</span>
            <span v-else-if="scope.row.attachType==='factory'">企业处理</span>
          </div>
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
          >下载</el-link>
          <el-divider direction="vertical" v-if="scope.row.attachType!=='factory'"></el-divider>
          <el-button
            type="text"
            size="small"
            @click="del(scope.row.Id)"
            v-if="scope.row.attachType!=='factory'"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>
<script>
import { uploadFile, fileList, delFile } from "@/api/upload";
import { contrast } from "@/config";
export default {
  props: ["ids", "upName"],
  data() {
    return {
      attachtype: "",
      fileList: [],
    };
  },
  created() {
    this.getList();
  },
  methods: {
    getList() {
      let ref = {
        refId: this.ids.join(","),
        refType: contrast[this.upName] + "_warn_file",
      };
      fileList(ref).then((res) => {
        if (res.code === 200) {
          this.fileList = res.data;
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
          const data = {
            fileName: file.name,
            file: imgcode,
            refId: _that.ids.join(","),
            refType: contrast[this.upName] + "_warn_file",
            attachType: "substation_warn_type",
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