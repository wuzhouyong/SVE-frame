<template>
  <div style="width: 100%; height: 100%; overflow: hidden">
    <el-divider content-position="left" class="divider">报告列表</el-divider>
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-upload
          class="upload-demo"
          :on-change="handleChange"
          action="Fake Action"
          :data="{ attachtype: attachtype }"
          :show-file-list="false"
          :auto-upload="false"
        >
          <el-button
            type="primary"
            icon="el-icon-plus"
            size="mini"
            @click="uploadReport"
            >上传报告
          </el-button>
        </el-upload>
      </el-col>
    </el-row>
    <el-table
      ref="filterTable"
      height="100%"
      :data="tableData"
      border
      size="mini"
      style="width: 100%"
    >
      <el-table-column prop="showName" label="附件名" width="250">
      </el-table-column>
      <el-table-column prop="auditStatus" label="报告状态" width="250">
        <template slot-scope="scope">
          {{
            scope.row.auditStatus == 0
              ? "待审核"
              : scope.row.auditStatus == 1
              ? "退回"
              : "通过"
          }}
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <pagination
      :total="total"
      :page.sync="page.pageNum"
      :limit.sync="page.pageSize"
      @pagination="getList"
    />

    <!-- <element-form
      :title="title"
      :values="editValues"
      :visible.sync="editVisible"
      @completed="successRequest()"
    ></element-form> -->
  </div>
</template>

<script>
import { getReportId, getReportList } from "@/api/dgom";
import { uploadFile } from "@/api/upload";
import ElementForm from "./form";
export default {
   components: { ElementForm },
  data: () => ({
    tableData: [],
    page: {
      pageNum: 1,
      pageSize: 30,
    },
    total: 0,
    attachtype: "report_sysfile_type",
    refType: "report_sysfile_type",

    editVisible: true,
    editValues: {},
    title:"scdd"
  }),
  created() {
    this.getList();
  },
  methods: {
    getList(v = {}) {
      getReportList(v, this.urlParameter(this.page)).then((res) => {
        if (res.code === 200) {
          this.tableData = res.rows;
          this.total = res.total;
        }
      });
    },
    // clearFilter() {
    //   this.$refs.filterTable.clearFilter();
    // },
    // filterTag(value, row) {
    //   return row.checkpointtype === value;
    // }
    uploadReport(v) {
      // console.log(123);
    },
    handleChange(file) {
      //   return;
      if (file.size / (1024 * 1024) > 100) {
        this.$notification["warning"]({
          message: "提示",
          description: "附件大小不超过100Mb！",
          duration: 2,
        });
        return false;
      } else {
        getReportId().then((res) => {
          console.log(res);
          let _that = this;
          var reader = new FileReader();
          reader.readAsDataURL(file.raw);
          reader.onload = (e) => {
            var imgcode = e.target.result;
            const data = {
              fileName: file.name,
              file: imgcode,
              refId: res.data,
              refType: _that.refType,
              attachType: _that.attachtype,
            };
            uploadFile(data).then((res) => {
              if (res.code === 200) {
                this.msgSuccess("附件上传成功");
                this.getList();
              }
            });
          };
        });
      }
    },

    //操作成功
    successRequest() {
      // this.getList();
      this.editVisible = false;
    },
  },
};
</script>

<style lang="scss">
</style>
