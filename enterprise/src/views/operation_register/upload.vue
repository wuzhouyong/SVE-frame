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
        prop="fileName"
        label="附件名"
        show-overflow-tooltip
      ></el-table-column>
      <el-table-column
        prop="handle"
        label="操作"
        width="100"
        align="center"
      >
        <template slot-scope="scope">
          <el-popconfirm
            title="确定要删除吗？"
            @onConfirm="del(scope)"
          >
            <el-button type="text" size="small" slot="reference">删除</el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>
<script>
import { uploadFile } from '@/api/upload'

export default {
  data() {
    return {
      attachtype: '',
      fileList: []
    }
  },
  methods: {
    del(v) {
      this.fileList.splice(v.$index, 1)
    },
    handleChange(file) {
      var reader = new FileReader()
      reader.readAsDataURL(file.raw)
      reader.onload = (e) => {
        var imgcode = e.target.result
        const data = {
          fileName: file.name,
          file: imgcode,
          refType: 'pf_bas_devops',
          attachType: '运维合同'
        }
        this.fileList.push(data)
      }
    },
    isFile() {
      return this.fileList.length > 0
    },
    isUpload(id) {
      this.fileList.forEach(i => {
        i.refId = id
        uploadFile(i).then(res => {
          if (res.code === 200) {
            this.msgSuccess('附件上传成功')
          } else {
            this.msgError(res.message+',请在附件列表处重新上传')
          }
        })
      })
    }
  }
}
</script>
<style lang="scss">
.upload {
  overflow: hidden;
  max-height: 400px;
}
</style>
