<template>
  <div class="base-list">
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="applyAdd"
        >新增联网申请
        </el-button>
      </el-col>
    </el-row>
    <el-table
      :data="tableData"
      border
      size="mini"
      height="100%"
      @row-dblclick="see"
      style="width: 100%"
      title="双击查看详情"
    >
      <el-table-column prop="townName" label="镇街" width="100">
      </el-table-column>
      <el-table-column prop="industryName" label="行业" width="120">
      </el-table-column>
      <el-table-column label="申请状态" width="100px">
        <template slot-scope="scope">
          {{ statusType[scope.row.status] }}
        </template>
      </el-table-column>
      <el-table-column prop="applyDate" label="申请时间" width="140px">
      </el-table-column>
      <el-table-column prop="applyPerson" label="申请人" width="120px">
      </el-table-column>
      <el-table-column prop="approvedBy" label="分局经办人" width="120px">
      </el-table-column>
      <el-table-column prop="approvedDate" label="分局批复时间" width="140px">
      </el-table-column>
      <el-table-column prop="approvalDesc" label="分局批复意见">
      </el-table-column>
      <el-table-column prop="muApprovedBy" label="市局确认人" width="120px">
      </el-table-column>
      <el-table-column prop="muApprovedDate" label="市局确认时间" width="140px">
      </el-table-column>
      <el-table-column
        prop="muApprovalDesc"
        label="市局确认意见"
        min-width="140px"
        show-overflow-tooltip>
      </el-table-column>
      <el-table-column label="操作" align="center" width="220px">
        <template v-if="scope.row.status === 0" slot-scope="scope">
          <a target="_blank" class="exportA" :href="getlink(scope.row.applyId)">
            导出申请表
          </a>
          <el-divider direction="vertical"></el-divider>
          <el-upload
            class="upload-demo"
            :on-change="handleChange"
            action="Fake Action"
            :data="{ attachtype: attachtype }"
            :show-file-list="false"
            :auto-upload="false"
            style="display: inline-block"
          >
            <el-link
              type="primary"
              :underline="false"
              style="font-size: 12px"
              @click="appIdSent(scope.row)"
            >提交申请表
            </el-link>
          </el-upload>
          <el-divider direction="vertical"></el-divider>
          <el-link
            type="danger"
            :underline="false"
            style="font-size: 12px"
            v-if="scope.row.status === 0"
            @click.stop="delData(scope.row)"
          >删除
          </el-link>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog
      title="新增联网申请"
      :visible.sync="visible"
      :close-on-click-modal="false"
      width="800px"
    >
      <formTem ref="addApply" @complete="complete"></formTem>
      <span slot="footer" class="dialog-footer">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="exportApply"
        >提交并导出申请表</el-button
        >
        <el-button size="mini" icon="el-icon-finished" @click="visible = false"
        >取消</el-button
        >
      </span>
    </el-dialog>
    <el-dialog
      title="联网申请详情"
      :visible.sync="detailsVisible"
      width="800px"
    >
      <detailsTem :detailsData="detailsData" v-if="detailsVisible"></detailsTem>
    </el-dialog>
  </div>
</template>

<script>
import formTem from "./form";
import detailsTem from "./details";
import {dgomNetworkList, dgomModel, dgomDelete, dgomUpload} from "@/api/dgom";

export default {
  components: {formTem, detailsTem},
  data: () => ({
    visible: false,
    tableData: [],
    statusType: ["草稿", "待审核", "分局同意", "分局拒绝", "市局同意", "市局退回", "已联网"],
    detailsData: {},
    detailsVisible: false,
    attachtype: "",
    applyId: ""
  }),
  created () {
    this.getList();
  },
  methods: {
    getList () {
      dgomNetworkList({}).then(res => {
        if (res.code === 200) {
          this.tableData = res.rows;
        }
      });
    },
    applyAdd () {
      this.visible = true;
    },
    complete () {
      this.visible = false;
      this.getList();
    },
    exportApply () {
      this.$refs.addApply.submit();
    },
    see (v) {
      dgomModel(v.applyId).then(res => {
        if (res.code === 200) {
          this.detailsData = res.data;
          this.detailsVisible = true;
        }
      });
    },
    delData (v) {
      this.$confirm("是否确认删除此条数据?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          return dgomDelete(v.applyId);
        })
        .then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
        .catch(() => {
        });
    },
    appIdSent (v) {
      this.applyId = v.applyId;
    },
    handleChange (file) {
      let reader = new FileReader();
      reader.readAsDataURL(file.raw);
      reader.onload = (e) => {
        let imgcode = e.target.result;
        const data = {
          fileName: file.name,
          fileData: imgcode,
          applyId: this.applyId,
          masterType: "Ent_ConnectApply_Approval"
        };
        dgomUpload(data).then((res) => {
          if (res.code === 200) {
            this.msgSuccess("附件上传成功");
            this.getList();
          }
        });
      };
    },
    getlink (id) {
      return `${process.env.VUE_APP_BASE_API}/dgom/networking/export/${id}`;
    },
  }
};
</script>

<style lang="scss">
.exportA {
  color: #1890ff;
  font-size: 12px;

  &:hover {
    color: #46a6ff;
  }

  &:active {
    color: #46a6ff;
  }
}
</style>
