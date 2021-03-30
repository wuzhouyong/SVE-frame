<template>
  <div style="width: 100%; height: 100%; overflow: hidden" class="operatio-style">
    <!--    <el-divider content-position="left" class="divider">运维管理</el-divider>-->
    <el-tabs type="border-card" v-model="activeName" @tab-click="handleClick" style="height:100%">
      <el-tab-pane label="运维管理" name="administration">
        <admin-stration v-if="activeName =='administration'"></admin-stration>
      </el-tab-pane>
      <el-tab-pane label="信息备案" name="information">
        <in-formation v-if="activeName =='information'"></in-formation>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import { getReportId, getReportList } from '@/api/dgom'
// import { uploadFile } from "@/api/upload";
import inFormation from './information/index.vue'
import adminStration from './administration/index.vue'

export default {
  components: { inFormation, adminStration },
  data: () => ({
    activeName: 'administration'
    // tableData: [],
    // page: {
    //   pageNum: 1,
    //   pageSize: 30,
    // },
    // total: 0,
    // attachtype: "report_sysfile_type",
    // refType: "report_sysfile_type",
  }),
  created() {
    // this.getList();
  },
  methods: {
    handleClick(tab, event) {
      // console.log(tab, event);
      // this.activeName = tab.name
    }
    // getList(v = {}) {
    //   getReportList(v, this.urlParameter(this.page)).then((res) => {
    //     if (res.code === 200) {
    //       this.tableData = res.rows;
    //       this.total = res.total;
    //     }
    //   });
    // },
    // clearFilter() {
    //   this.$refs.filterTable.clearFilter();
    // },
    // filterTag(value, row) {
    //   return row.checkpointtype === value;
    // }
    // uploadReport(v) {
    //   // console.log(123);
    // },
    // handleChange(file) {
    //   console.log(file);
    //   //   return;
    //   if (file.size / (1024 * 1024) > 100) {
    //     this.$notification["warning"]({
    //       message: "提示",
    //       description: "附件大小不超过100Mb！",
    //       duration: 2,
    //     });
    //     return false;
    //   } else {
    //     getReportId().then((res) => {
    //       console.log(res);
    //       let _that = this;
    //       var reader = new FileReader();
    //       reader.readAsDataURL(file.raw);
    //       reader.onload = (e) => {
    //         var imgcode = e.target.result;
    //         const data = {
    //           fileName: file.name,
    //           file: imgcode,
    //           refId: res.data,
    //           refType: _that.refType,
    //           attachType: _that.attachtype,
    //         };
    //         uploadFile(data).then((res) => {
    //           if (res.code === 200) {
    //             this.msgSuccess("附件上传成功");
    //             this.getList();
    //           }
    //         });
    //       };
    //     });
    //   }
    // },
  }
}
</script>

<style lang="scss" scoped>
.operatio-style {
  /deep/ .el-tabs {
    display: flex;
    flex-direction: column;
    overflow: hidden;

    .el-tabs__content {
      flex: 1;
      overflow: hidden;

      .el-tab-pane {
        height: 100%;

        .base-list {
          height: 100%;
        }
      }
    }
  }
}

</style>
