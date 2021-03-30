<template>
  <div>
    <el-upload
      style="margin-bottom: 10px;"
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
      >附件上传
      </el-button>
    </el-upload>
    <el-table :data="fileList" stripe style="width: 100%" border size="mini">
      <el-table-column prop="showName" label="附件名" show-overflow-tooltip></el-table-column>
      <el-table-column prop="TypeName" label="附件类型" width="120">
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
          >下载
          </el-link>
          <el-divider direction="vertical"></el-divider>
          <el-popconfirm title="确定要删除吗？" @onConfirm="del(scope.row.Id)">
            <el-button type="text" size="small" slot="reference">删除</el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>
<script>
import {getDict} from "@/api/source";
import {uploadFile, fileList, delFile} from "@/api/upload";

export default {
  props: {
    warnId: {
      type: String
    },
    ref_type: {
      type: String,
      required: true
    }
  },
  data () {
    return {
      attachtype: "",
      fileList: []
    };
  },
  watch: {
    warnId: {
      immediate: true,
      handler (v) {
        if (v) {
          this.getList();
        }
      }
    },
  },
  methods: {
    getList () {
      let ref = {
        refId: this.warnId,
        refType: this.ref_type
      };
      fileList(ref).then(res => {
        if (res.code === 200) {
          this.fileList = res.rows || res.data;
        }
      });
    },
    getlink (id) {
      return `${process.env.VUE_APP_BASE_API}/com/getfile/${id}`;
    },
    //附件类型
    uploadFile () {
      // this.attachtype = v;
      console.log("aaaa");
    },
    del (id) {
      delFile(id).then(res => {
        if (res.code === 200) {
          this.msgSuccess("附件删除成功");
          this.getList();
        }
      });
    },
    handleChange (file) {
      // debugger
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
        reader.onload = e => {
          var imgcode = e.target.result;
          const data = {
            fileName: file.name,
            file: imgcode,
            refId: _that.warnId,
            refType: this.ref_type,
            attachType: "factory"
          };
          uploadFile(data).then(res => {
            if (res.code === 200) {
              this.msgSuccess("附件上传成功");
              this.getList();
            }
          });
        };
      }
    }
  }
};
</script>
