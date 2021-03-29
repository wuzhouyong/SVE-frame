<template>
  <div class="upload">
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
import {dgomAttachList} from "@/api/om/upload";

export default {
  props: {
    taskHeadId: {
      type: String
    },
  },
  data () {
    return {
      attachtype: "",
      fileList: [],
      ref_type: "zAbnorTaskHead",
    };
  },
  watch: {
    taskHeadId: {
      handler () {
        this.getList();
      },
      immediate: true,
    },
  },
  methods: {
    getList () {
      dgomAttachList(this.taskHeadId, {refType: "zAbnorTaskHead"}).then((res) => {
        if (res.code === 200) {
          this.fileList = res.data;
        }
      });
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
