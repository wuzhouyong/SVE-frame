<template>
  <div>
    <el-table
      :data="details"
      style="width: 100%;flex:1;"
      highlight-current-row
      ref="oTable"
      size="mini"
      min-height="400px"
      border
    >
      <el-table-column prop="checkPointDesc" label="排放口" show-overflow-tooltip></el-table-column>
      <el-table-column prop="dataDevDesc" label="设备名称" show-overflow-tooltip></el-table-column>
      <el-table-column label="附件" width="80" align="center">
        <template slot-scope="scope">
          <el-dropdown trigger="hover" @visible-change="fileClick($event,scope.row)" placement="bottom-start">
            <span class="el-dropdown-link" style="font-size:12px;color:#1682e6">
              查看<i class="el-icon-arrow-down el-icon--right"></i>
            </span>
            <el-dropdown-menu slot="dropdown" style="width:400px;padding:10px" v-loading="fileLoading">
              <el-dropdown-item v-for="(item,index) in fileList" :key="item.Id">
                <el-link
                  type="primary"
                  :underline="false"
                  target="_blank"
                  :href="getlink(item.attach_id)"
                  style="font-size:12px;"
                >{{ index + 1 }}.{{ item.file_name }} ({{ fileType[item.attach_type] }})
                </el-link>
              </el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import {dgomAttachList} from "@/api/om/upload";

export default {
  props: {
    details: {
      type: Array,
      default: () => []
    },
    applyId: {
      type: String | Number
    }
  },
  data () {
    return {
      fileLoading: false,
      fileList: [],
      fileType: {
        device: "设备现场照片",
        monitoringStation: "监控站房照片",
        samplingPoint: "取样点照片"
      },
    };
  },
  methods: {
    fileClick (i, v) {
      if (i) {
        this.fileLoading = true;
        let ref = {
          refType: "Ent_AcceptApplyDetail"
        };
        dgomAttachList(v.detailId, ref).then((res) => {
          this.fileList = res.data;
          this.fileLoading = false;
        });
      } else {
        this.fileList = [];
        this.fileLoading = false;
      }
    },
    getlink (id) {
      return `${process.env.VUE_APP_BASE_API}/dgom/attach/download/${id}`;
    },
  }
};
</script>

<style lang='scss' scoped>

</style>
