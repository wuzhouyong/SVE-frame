<template>
  <div class="monitor-wk" v-loading="loading">
    <el-form label-width="150px" class="show-form">
      <el-form-item label="施工进度：">{{ constructionStatus[model.constructionStatus] }}</el-form-item>
      <el-form-item label="施工单位名称：">{{ model.constructionUnit }}</el-form-item>
      <el-form-item label="施工单位简称：">{{ model.shortName }}</el-form-item>
      <el-form-item label="施工单位联系人：">{{ model.constructionPerson }}</el-form-item>
      <el-form-item label="施工单位联系电话：">{{ model.constructionPhone }}</el-form-item>
      <!-- <el-form-item label="分局确认日期：">{{model.auditDate||'-'}}</el-form-item> -->
      <el-form-item label="施工完成日期：">{{ model.completeDate || "-" }}</el-form-item>
      <el-form-item label="自主验收日期：">{{ model.acceptanceDate || "-" }}</el-form-item>
      <el-form-item label="验收确认日期：">{{ model.acceptDate || "-" }}</el-form-item>
    </el-form>
    <div style="text-align: right;width:100%;">
      <el-button type="primary" icon="el-icon-download"><a :href="allDown()" target="_blank">附件批量下载</a></el-button>
    </div>
    <el-table :data="fileList" stripe style="width: 100%" border>
      <el-table-column prop="showName" label="附件名称">
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
      <el-table-column prop="TypeName" label="附件类型" width="200" show-overflow-tooltip></el-table-column>
    </el-table>
  </div>
</template>
<script>
import { getConstructionModel } from "@/api/home/index";
import { fileList } from "@/api/upload";
import { contrast } from "@/config";

const file_list = [
  {
    id: 1002,
    ref_type: "gc_sysfile_type"
  },
  {
    id: 1003,
    ref_type: "lx_sysfile_type"
  },
  {
    id: 1004,
    ref_type: "voc_sysfile_type"
  },
  {
    id: 1006,
    ref_type: "yy_sysfile_type"
  },
  {
    id: 1012,
    ref_type: "psh_sysfile_type"
  },
  {
    id: 1013,
    ref_type: "qx_sysfile_type"
  },
  {
    id: 1014,
    ref_type: "jy_sysfile_type"
  },
  {
    id: 1015,
    ref_type: "js_sysfile_type"
  },
  {
    id: 1017,
    ref_type: "water_sysfile_type"
  },
  {
    id: 1019,
    ref_type: "zz_sysfile_type"
  },
  {
    id: 1021,
    ref_type: "gas_sysfile_type"
  },
  {
    id: 1022,
    ref_type: "gasv_sysfile_type"
  },
  {
    id: 1023,
    ref_type: "waterv_sysfile_type"
  }
]
export default {
  props: ["platFormPath", "oId", "refType"],
  data() {
    return {
      constructionStatus: [
        "未开展",
        "已施工",
        "已接入",
        "接入完成",
        "待验收",
        "",
        "已验收"
      ],
      model: {},
      fileList: [],
      loading: false,
      fileType: [],
      fileListType: file_list
    };
  },
  watch: {
    oId: {
      handler() {
        this.getData();
        this.getList();
      },
      immediate: true
    },
    platFormPath: {
      handler() {
        this.getData();
        this.getList();
      }
    }
  },
  methods: {
    getList() {
      let ref = {
        refId: this.oId,
        refType: this.refType
      };
      fileList(ref).then((res) => {
        if (res.code === 200) {
          this.fileList = res.data;
        }
      });
    },
    getData() {
      this.loading = true;
      getConstructionModel(this.platFormPath, this.oId).then((res) => {
        if (res.code === 200) {
          this.model = res.data;
          this.loading = false;
        } else {
          this.loading = false;
        }
      });
    },
    getType() {
      let oName = contrast[this.platFormPath];
      this.getDicts(oName + "_sysfile_type").then((response) => {
        this.fileType = response.data;
      });
    },
    cFileType(row) {
      return this.selectDictLabel(this.fileType, row.attachType);
    },
    getlink(id) {
      return `${process.env.VUE_APP_BASE_API}/com/getfile/${id}`;
    },
    allDown() {
      return `${process.env.VUE_APP_BASE_API}/com/ref_files?refType=${this.refType}&refId=${this.oId}`;
    }
  }
};
</script>
<style lang="scss">
.monitor-wk {
  .el-form-item__label {
    color: #000;
  }

  .el-form-item__content {
    color: #000;
  }
}
.show-form {
  /deep/ .el-form-item {
    margin-bottom: 5px;
  }
}
</style>
