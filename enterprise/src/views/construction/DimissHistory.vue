<template>
  <!--  <table-dialog :title="title" :visible.sync="dialogVisible">-->
  <!--    <el-table :data="gridData">-->
  <!--      <el-table-column-->
  <!--        property="returnReason"-->
  <!--        label="审核结果"-->
  <!--        width="150"-->
  <!--      ></el-table-column>-->
  <!--      <el-table-column-->
  <!--        property="contactName"-->
  <!--        label="审核人"-->
  <!--        width="200"-->
  <!--      ></el-table-column>-->
  <!--      <el-table-column-->
  <!--        property="contactPhone"-->
  <!--        label="联系方式"-->
  <!--      ></el-table-column>-->
  <!--      <el-table-column property="memo" label="备注"></el-table-column>-->
  <!--      <el-table-column property="address" label="附件">-->
  <!--        <template slot-scope="scope">-->
  <!--          <el-link-->
  <!--            v-for="item in scope.row.fileList"-->
  <!--            :key="item.Id"-->
  <!--            type="primary"-->
  <!--            :underline="false"-->
  <!--            target="_blank"-->
  <!--            :href="getlink(item.Id)"-->
  <!--            >{{ item.showName }}</el-link-->
  <!--          >-->
  <!--        </template>-->
  <!--      </el-table-column>-->
  <!--    </el-table>-->
  <!--    &lt;!&ndash;    <div style="font-size:12px;color:indianred">( 注:修改施工单位登记相关信息后.施工状态将重新变更为施工中.请谨慎操作! )</div>&ndash;&gt;-->
  <!--  </table-dialog>-->

  <div>
    <el-card class="box-card" v-for="i in gridData" :key="i.return_id" style="margin-bottom:5px;">
      <el-form label-width="80px">
        <el-form-item label="审核结果">
          {{ i.return_result === 1 ? "验收通过" : "验收退回" }}
        </el-form-item>
        <template v-if="i.return_result===0">
          <el-form-item label="审核人">
            {{ i.contact_name }}
          </el-form-item>
          <el-form-item label="联系电话">
            {{ i.contact_phone }}
          </el-form-item>
          <el-form-item label="退回原因">
            {{ i.return_reason }}
          </el-form-item>
          <el-form-item label="备注">
            {{ i.memo }}
          </el-form-item>
        </template>
        <template v-else>
          <el-form-item label="操作人">
            {{ i.created_by }}
          </el-form-item>
          <el-form-item label="操作时间">
            {{ i.date_created }}
          </el-form-item>
        </template>
      </el-form>
    </el-card>
  </div>
</template>
<script>
import {monitorRejectList} from "@/api/construction";
import {fileList} from "@/api/upload";

export default {
  props: {
    active_sys: {
      type: String,
    },
    values: {
      type: Object,
      default: () => ({}),
    },
  },
  data () {
    return {
      gridData: [],
    };
  },
  // computed: {
  //   dialogVisible: {
  //     get () {
  //       return this.visible || false;
  //     },
  //     set (v) {
  //       this.$emit("update:visible", v);
  //     },
  //   },
  // },
  watch: {
    values: {
      handler (v) {
        if (v) {
          this.getList(v.tagHeadId, v.sourceId);
          // this.getFileList
        }
      },
      immediate: true,
      deep: true,
    },
  },
  methods: {
    getList (t, s) {
      monitorRejectList(t, s).then((res) => {
        this.gridData = res.data;
      });
    },
    // getFileList (refId) {
    //   return new Promise((resolve, reject) => {
    //     let ref = {
    //       refId: this.gridData,
    //       refType: "goback_file",
    //     };
    //     fileList(ref).then((res) => {
    //       if (res.code === 200) {
    //         let fileList = res.rows || res.data;
    //         resolve(fileList);
    //       }
    //     });
    //   });
    // },
    // getlink (id) {
    //   return `${process.env.VUE_APP_BASE_API}/com/getfile/${id}`;
    // },
  },
};
</script>
<style lang="scss" scoped>
.history-style {
  /deep/ .el-form-item {
    margin-bottom: 5px;
  }

  /deep/ .el-card__body {
    padding: 5px 10px 0;
  }
}

</style>
