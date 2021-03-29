<template>
  <div>
    <template>
      <el-form label-width="80px">
        <el-row :gutter="15">
          <el-col :lg="24" :md="24" :sm="24">
            <el-form-item label="企业名称">
              {{ rowData.sourceName }}
            </el-form-item>
          </el-col>
          <el-col :lg="12" :md="12" :sm="24">
            <el-form-item label="申请时间">
              {{ rowData.applyDate }}
            </el-form-item>
          </el-col>
          <el-col :lg="12" :md="12" :sm="24">
            <el-form-item label="申请类型">
              {{ rowData.applyType === 1 ? "维修(或更换)" : "停用(或拆除)" }}
            </el-form-item>
          </el-col>
          <el-col :lg="12" :md="12" :sm="24">
            <el-form-item label="联系人">
              {{ rowData.contactMan }}
            </el-form-item>
          </el-col>
          <el-col :lg="12" :md="12" :sm="24">
            <el-form-item label="联系电话">
              {{ rowData.contactTel }}
            </el-form-item>
          </el-col>
          <el-col :lg="24" :md="24" :sm="24">
            <el-form-item label="更换原因">
              {{ rowData.changeReason }}
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <el-divider content-position="left">排放口/设备</el-divider>
      <el-table
        :data="tableData"
        size="mini"
        border
        style="width: 100%">
        <el-table-column
          prop="checkpointDesc"
          label="排放口名称">
        </el-table-column>
        <el-table-column
          prop="dataDevDesc"
          label="设备名称">
        </el-table-column>
        <el-table-column
          prop="other"
          label="其他">
        </el-table-column>
      </el-table>
      <el-divider content-position="left">附件列表</el-divider>
      <el-table
        :data="fileData"
        size="mini"
        border
        style="width: 100%;margin-bottom:5px">
        <el-table-column
          prop="file_name"
          label="附件名">
        </el-table-column>
        <el-table-column
          label="操作"
          width="80"
          align="center">
          <template slot-scope="scope">
            <el-link type="primary" style="font-size:12px;" :href="getlink(scope.row.attach_id)">下载</el-link>
          </template>
        </el-table-column>
      </el-table>
    </template>
    <el-divider content-position="left">审批</el-divider>
    <template>
      <el-form label-width="80px">
        <el-form-item
          label="经办人"
        >
          {{ rowData.approvedBy || "-" }}
        </el-form-item>
        <el-form-item
          label="批复时间"
        >
          {{ rowData.approvedDate || "-" }}
        </el-form-item>
        <el-form-item
          label="批复意见"
        >
          {{ rowData.approvalDesc || "-" }}
        </el-form-item>
      </el-form>
    </template>
  </div>
</template>

<script>
import {dgomDeviceDetail} from "@/api/om";
import {dgomAttachList} from "@/api/om/upload";

export default {
  props: {
    rowData: {
      type: Object,
      default: () => ({})
    }
  },
  data () {
    return {
      tableData: [],
      fileData: [],
      approvalForm: {},
      dutyForm: {}
    };
  },
  watch: {
    rowData: {
      handler (v) {
        if (v.applyId) {
          this.getTableData(v.applyId);
          this.getFileList(v.applyId);
        }
      },
      immediate: true
    }
  },
  methods: {
    getFileList (v) {
      dgomAttachList(v, {refType: "Ent_DevChangeApplyHead"}).then((res) => {
        if (res.code === 200) {
          this.fileData = res.data;
        }
      });
    },
    getTableData (v) {
      dgomDeviceDetail(v).then(res => {
        this.tableData = res.data;
      });
    },
    getlink (id) {
      return `${process.env.VUE_APP_BASE_API}/dgom/attach/download/${id}`;
    },
  }
};
</script>

<style lang='scss' scoped>

</style>
