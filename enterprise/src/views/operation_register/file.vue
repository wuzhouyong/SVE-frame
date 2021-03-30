<template>
  <div>
    <el-upload
      style="margin-bottom: 10px"
      class="upload-demo"
      :on-change="handleChange"
      action="Fake Action"
      :data="{ attachtype: attachtype }"
      :show-file-list="false"
      :auto-upload="false"
    >
      <el-button size="mini" type="primary">上传合同</el-button>
    </el-upload>
    <el-table
      :data="fileList"
      size="mini"
      style="width: 100%"
      max-height="300px"
      border
    >
      <el-table-column
        prop="showName"
        label="附件名"
        show-overflow-tooltip
      >
        <template slot-scope="scope">
          <el-link
            type="primary"
            :underline="false"
            target="_blank"
            :href="getlink(scope.row.Id)"
            style="font-size:12px;"
          >{{ scope.row.showName }}
          </el-link>
        </template>
      </el-table-column>
      <el-table-column
        prop="attachType"
        label="类型"
      ></el-table-column>
    </el-table>
  </div>
</template>

<script>
import {fileList, uploadFile} from "@/api/upload";

export default {
  props: {
    fId: {
      type: String | null
    }
  },
  data () {
    return {
      fileList: [],
      attachtype: ""
    };
  },
  watch: {
    fId: {
      handler (v) {
        if (v) {
          console.log(v);
          this.getList(v);
        }
      },
      immediate: true
    }
  },
  methods: {
    getList (v) {
      let ref = {
        refId: v,
        refType: "pf_bas_devops"
      };
      fileList(ref).then((res) => {
        if (res.code === 200) {
          this.fileList = res.rows || res.data;
        }
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
          refId: _that.fId,
          refType: "pf_bas_devops",
          attachType: "运维合同"
        };
        uploadFile(data).then(res => {
          if (res.code === 200) {
            this.msgSuccess("附件上传成功");
            this.getList(_that.fId);
          }
        });
      };
    },
    getlink (id) {
      return `${process.env.VUE_APP_BASE_API}/com/getfile/${id}`;
    }
  }
};
</script>

<style lang="scss" scoped>

</style>
