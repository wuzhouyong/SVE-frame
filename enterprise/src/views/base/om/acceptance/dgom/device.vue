<template>
  <div>
    <el-row :gutter="10" class="mb8" v-if="headStatus===1">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="deviceAdd"
        >新增备案设备
        </el-button>
      </el-col>
    </el-row>
    <el-table
      :data="deviceData"
      style="width: 100%;flex:1;"
      highlight-current-row
      ref="oTable"
      size="mini"
      min-height="400px"
      border
    >
      <el-table-column prop="checkPointDesc" label="排放口" show-overflow-tooltip></el-table-column>
      <el-table-column prop="dataDevDesc" label="设备名称" show-overflow-tooltip></el-table-column>
      <el-table-column label="操作" align="center" width="120">
        <template slot-scope="scope">
          <el-link type="primary" :underline="false" style="font-size:12px;" v-if="headStatus===1"
                   @click="deviceEdit(scope.row)">
            编辑
          </el-link>
          <el-divider direction="vertical" v-if="headStatus===1"></el-divider>
          <el-link type="danger" :underline="false" style="font-size:12px;" v-if="headStatus===1"
                   @click="deviceDelete(scope.row.detailId)">
            删除
          </el-link>
          <el-dropdown trigger="hover" @visible-change="fileClick($event,scope.row)" placement="bottom-start"
                       v-if="headStatus!==1">
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
    <el-dialog
      width="700px"
      :title="oTitle"
      :visible.sync="innerVisible"
      append-to-body>
      <div>
        <el-form :model="deviceForm" ref="deviceForm" label-width="110px" :rules="rules">
          <el-row :gutter="15">
            <el-col :lg="24" :md="24" :sm="24">
              <el-form-item label="排放口名称" prop="checkPointId">
                <el-select
                  @change="checkpointidChange"
                  v-model="deviceForm.checkPointId"
                  placeholder="请选择排放口名称"
                  :style="{ width: '100%' }"
                  size="mini"
                >
                  <el-option
                    v-for="item in emissions"
                    :key="item.checkpointid"
                    :label="item.checkpointdesc"
                    :value="item.checkpointid"
                  >
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :lg="24" :md="24" :sm="24">
              <el-form-item label="设备名称" prop="dataDevId">
                <el-select
                  v-model="deviceForm.dataDevId"
                  placeholder="请选择设备名称"
                  :style="{ width: '100%' }"
                  size="mini"
                >
                  <el-option
                    v-for="item in equipmentEmisson"
                    :key="item.datadevid"
                    :label="item.datadevdesc"
                    :value="item.datadevid"
                  >
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
        <n-upload :applyId="detailId" ref="deviceUpload" refType="Ent_AcceptApplyDetail" v-if="innerVisible"></n-upload>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button size="mini" type="primary" @click="submitDevice">保存</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import nUpload from "./upload";
import {getEmissions, getEquipmentByEmissions} from "@/api/dgom";
import {
  dgomEntAcceptAddDevice,
  dgomEntAcceptEditDevice,
  dgomDevicesList,
  dgomEntAcceptDeleteDevice
} from "@/api/dgom/accept";
import {dgomAttachList} from "@/api/dgom/upload";
export default {
  components: {nUpload},
  props: {
    headDataId: {
      type: String
    },
    headStatus: {
      type: String | Number
    }
  },
  created () {
    getEmissions().then((res) => {
      this.emissions = res.data;
    });
  },
  watch: {
    headDataId: {
      handler (v) {
        if (v) {
          dgomDevicesList(v).then(res => {
            this.deviceData = res.data;
          });
        }
      },
      immediate: true
    }
  },
  data () {
    return {
      deviceData: [],
      emissions: [],
      equipmentEmisson: [],
      deviceForm: {},
      eValues: {},
      detailId: null,
      rules: {
        checkPointId: [
          {
            required: true,
            message: "排放口名称必填",
            trigger: "blur",
          },
        ],
        dataDevId: [
          {
            required: true,
            message: "设备名称必填",
            trigger: "blur",
          },
        ],
      },
      innerVisible: false,
      oTitle: null,
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
    guid () {
      let s = [];
      let hexDigits = "0123456789abcdef";
      for (var i = 0; i < 36; i++) {
        s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
      }
      s[14] = "4"; // bits 12-15 of the time_hi_and_version field to 0010
      s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1); // bits 6-7 of the clock_seq_hi_and_reserved to 01
      s[8] = s[13] = s[18] = s[23] = "-";
      this.detailId = s.join("");
    },
    deviceAdd () {
      this.oTitle = "新增验收设备";
      this.innerVisible = true;
      this.eValues = {};
      this.guid();
    },
    deviceEdit (v) {
      this.oTitle = "编辑验收设备";
      this.eValues = v;
      this.deviceForm = Object.assign(this.deviceForm, v);
      getEquipmentByEmissions(v.checkPointId).then((res) => {
        this.equipmentEmisson = res.data;
      });
      this.detailId = v.detailId;
      this.innerVisible = true;
    },
    checkpointidChange (value) {
      getEquipmentByEmissions(value).then((res) => {
        this.equipmentEmisson = res.data;
      });
    },
    submitDevice () {
      this.$refs["deviceForm"].validate((valid) => {
        if (!valid) return;
        if (this.$refs.deviceUpload.isFile()) {
          if (this.eValues && this.eValues.detailId) {
            this.deviceForm = Object.assign(this.eValues, this.deviceForm);
            dgomEntAcceptEditDevice(this.deviceForm).then(res => {
              if (res.code === 200) {
                this.msgSuccess("编辑验收设备成功");
                this.innerVisible = false;
                dgomDevicesList(this.headDataId).then(res => {
                  this.deviceData = res.data;
                });
              }
            });
          } else {
            this.deviceForm.detailId = this.detailId;
            this.deviceForm.applyId = this.headDataId;
            dgomEntAcceptAddDevice(this.deviceForm).then(res => {
              if (res.code === 200) {
                this.msgSuccess("新增验收设备成功");
                this.innerVisible = false;
                dgomDevicesList(this.headDataId).then(res => {
                  this.deviceData = res.data;
                });
              }
            });
          }
        } else {
          this.msgError("附件必须上传");
        }
      });
    },
    deviceDelete (v) {
      this.$confirm("是否确认删除选中数据数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          return dgomEntAcceptDeleteDevice(v);
        })
        .then(() => {
          dgomDevicesList(this.headDataId).then(res => {
            this.deviceData = res.data;
          });
          this.msgSuccess("删除成功");
        })
        .catch(() => {
        });
    },
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
