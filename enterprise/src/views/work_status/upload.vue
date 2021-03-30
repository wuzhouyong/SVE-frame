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
      <el-table-column :prop="refId?'showName':'fileName'" label="附件名" show-overflow-tooltip></el-table-column>
      <el-table-column label="操作" width="100" align="center">
        <template slot-scope="scope">
          <el-link
            v-if="refId"
            style="font-size:12px;"
            type="primary"
            :underline="false"
            target="_blank"
            :href="getlink(scope.row.Id)"
          >下载
          </el-link>
          <el-divider v-if="refId" direction="vertical"></el-divider>
          <el-popconfirm title="确定要删除吗？" @onConfirm="del(scope.row.Id,true)" v-if="refId">
            <el-button type="text" size="small" slot="reference">删除</el-button>
          </el-popconfirm>
          <el-popconfirm title="确定要删除吗？" @onConfirm="del(scope.$index,false)" v-else>
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
    refId: {
      type: String | Number,
      default: null
    },
  },
  data () {
    return {
      attachtype: "",
      fileList: [],
      ref_type: "work_status_apply"
    };
  },
  watch: {
    refId: {
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
        refId: this.refId,
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
    del (v, b) {
      if (b) {
        delFile(v).then(res => {
          if (res.code === 200) {
            this.msgSuccess("附件删除成功");
            this.getList();
          }
        });
      } else {
        this.fileList.splice(v, 1);
      }

    },
    uploadSub (v) {
      this.fileList.forEach(i => {
        i.refId = v;
        uploadFile(i);
      });
    },
    handleChange (file) {
      let _that = this;
      var reader = new FileReader();
      reader.readAsDataURL(file.raw);
      reader.onload = e => {
        var imgcode = e.target.result;
        const data = {
          fileName: file.name,
          file: imgcode,
          refId: _that.refId,
          refType: _that.ref_type,
          attachType: "work_status_apply"
        };
        if (this.refId) {
          uploadFile(data).then(res => {
            if (res.code === 200) {
              this.msgSuccess("附件上传成功");
              this.getList();
            }
          });
        } else {
          this.fileList.push(data);
        }
      };
    },
    isUpload () {
      return this.fileList.length > 0;
    }
  }
};
</script>
