<template>
  <div>
    <el-table :data="joinData" style="width: 100%" border>
      <el-table-column prop="billNo" label="联单号" width="140"></el-table-column>
      <el-table-column prop="fullName" label="产污企业" show-overflow-tooltip></el-table-column>
      <el-table-column prop="startflowintime" label="开始收水" width="140"></el-table-column>
      <el-table-column prop="endflowintime" label="完成收水" width="140"></el-table-column>
      <el-table-column prop="factinamt" label="实际数量(吨)" width="100"></el-table-column>
      <el-table-column prop="flowinamt" label="计算数量(吨)" width="100"></el-table-column>
      <el-table-column label="操作" width="60" align="center">
        <template slot-scope="scope">
          <el-button type="text" size="small" @click="mxClick(scope.row)">明细</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog title="联单明细" :visible.sync="mxvisible" width="800px" append-to-body>
      <mxTem :transbillid="transbillid"></mxTem>
    </el-dialog>
  </div>
</template>
<script>
import { getJobdetailList } from "@/api/lingxing";
import mxTem from "./mx";
export default {
  components: { mxTem },
  props: {
    jobHeadid: {
      type: String,
    },
  },
  data() {
    return {
      joinData: [],
      mxvisible: false,
      transbillid: "",
    };
  },
  watch: {
    jobHeadid: {
      handler(v) {
        if (v) this.getJobdetail(v);
      },
      immediate: true,
    },
  },
  methods: {
    getJobdetail(v) {
      getJobdetailList(v).then((res) => {
        if (res.code === 200) {
          this.joinData = res.data;
        }
      });
    },
    mxClick(v) {
      this.mxvisible = true;
      this.transbillid = v.transbillid;
    },
  },
};
</script>