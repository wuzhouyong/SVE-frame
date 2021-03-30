<template>
  <div>
    <el-form :model="nForm" ref="nForm" label-width="110px" :rules="nRules">
      <el-row :gutter="15">
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="申请类型" prop="applyType">
            <el-select
              v-model="nForm.applyType"
              placeholder="请选择申请类型"
              :style="{ width: '100%' }"
              size="mini"
              :disabled="!!values.applyId"
            >
              <el-option
                :key="1"
                label="维修(或更换)"
                :value="1"
              >
              </el-option>
              <el-option
                :key="2"
                label="停用(或拆除)"
                :value="2"
              >
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="联系电话" prop="contactTel">
            <el-input
              :readonly="!!values.applyId"
              v-model="nForm.contactTel"
              placeholder="联系电话"
              :style="{ width: '100%' }"
              size="mini"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="联系人" prop="contactMan">
            <el-input
              :readonly="!!values.applyId"
              v-model="nForm.contactMan"
              placeholder="联系人"
              :style="{ width: '100%' }"
              size="mini"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="" prop="changerReason">
            <el-input
              :readonly="!!values.applyId"
              type="textarea"
              :autosize="{ minRows: 2, maxRows: 4 }"
              placeholder="请输入内容"
              v-model="nForm.changeReason"
            >
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <el-divider content-position="left">排放口列表</el-divider>
    <el-row :gutter="10" class="mb8" v-if="!values.applyId">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="addCheckpoint"
        >添加排放口
        </el-button>
      </el-col>
    </el-row>
    <el-table
      :data="checkpointData"
      style="width: 100%; flex: 1"
      ref="oTable"
      size="mini"
      max-height="300px"
      border
    >
      <el-table-column
        prop="checkpointDesc"
        label="排放口名称"
        show-overflow-tooltip
      ></el-table-column>
      <el-table-column
        prop="dataDevDesc"
        label="设备名称"
        show-overflow-tooltip
      ></el-table-column>
      <el-table-column prop="other" label="其他" width="120px"></el-table-column>
      <el-table-column label="操作" width="80px" align="center" v-if="!values.applyId">
        <template slot-scope="scope">
          <el-popconfirm
            title="确定要删除吗？"
            @onConfirm="del(scope)"
          >
            <el-button type="text" size="small" slot="reference"
            >删除
            </el-button
            >
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <n-upload
      :zoperationid="applyid"
      :isRead="!!values.applyId"
      ref="nUpLoad"
    ></n-upload>
    <!--    排放口新增-->
    <el-dialog
      width="30%"
      title="排放口信息"
      :visible.sync="innerVisible"
      :destroy-on-close="true"
      append-to-body
    >
      <el-form
        :rules="rulesOther"
        :model="formMsg"
        v-if="innerVisible"
        size="medium"
        label-width="160px"
        ref="formMsg"
      >
        <el-form-item label="排放口名称" prop="checkpointId">
          <el-select
            @change="checkpointidChange"
            v-model="formMsg.checkpointId"
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
        <el-form-item label="设备名称" prop="dataDevId">
          <el-select
            v-model="formMsg.dataDevId"
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
        <el-form-item label="其他" prop="other">
          <el-input
            v-model="formMsg.other"
            placeholder="其他"
            :style="{ width: '100%' }"
            size="mini"
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button size="mini" @click="closeInner">关 闭</el-button>
        <el-button size="mini" type="primary" @click="selectEquiemnt">添 加</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {device_detail_list, device_replaceAdd, getEmissions, getEquipmentByEmissions} from "@/api/dgom";
import nUpload from "./upload";

export default {
  components: {nUpload},
  props: {
    values: {
      type: Object,
      default: () => ({})
    }
  },
  watch: {
    values: {
      handler (v) {
        if (v.applyId) {
          this.nForm = v;
          this.applyid = v.applyId;
          device_detail_list(v.applyId).then((res) => {
            this.checkpointData = res.data;
          });
        } else {
          this.guid();
        }
      },
      immediate: true
    }
  },
  data () {
    var checkphone = (rule, value, callback) => {
      if (value == "") {
        callback(new Error("请输入手机号"));
      } else if (!/^1(3|4|5|6|7|8)\d{9}$/.test(value)) {
        //引入methods中封装的检查手机格式的方法
        callback(new Error("请输入正确的手机号!"));
      } else {
        callback();
      }
    };
    return {
      nForm: {},
      nRules: {
        applytype: [
          {
            required: true,
            message: "申请类型不能为空",
            trigger: "change",
          },
        ],
        changereason: [
          {
            required: true,
            message: "更换原因不能为空",
            trigger: "blur",
          },
        ],
        contactman: [
          {
            required: true,
            message: "联系人不能为空",
            trigger: "blur",
          },
        ],
        contacttel: [
          {
            required: true,
            message: "联系电话不能为空",
            trigger: "blur",
          },
          {validator: checkphone, trigger: "blur"},
        ],
      },
      rulesOther: {
        checkpointId: [
          {
            required: true,
            message: "排放口名称不能为空",
          },
        ],
        dataDevId: [
          {
            required: true,
            message: "设备名称不能为空",
          },
        ],
      },
      formMsg: {},
      innerVisible: false,
      applyid: null,
      emissions: [],
      equipmentEmisson: [],
      checkpointData: []
    };
  },
  created () {
    this.init();
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
      this.applyid = s.join("");
    },
    init () {
      getEmissions().then((res) => {
        this.emissions = res.data;
      });
    },
    submitForm () {
      this.$refs["nForm"].validate((valid) => {
        if (!valid) return;
        if (this.checkpointData.length < 1) {
          this.msgError("申请时必须添加一个排放口!");
          return;
        }
        if (!this.$refs.nUpLoad.isFile()) {
          this.msgError("附件必须上传!");
          return;
        }
        this.nForm.detail = this.checkpointData;
        this.nForm.applyId = this.applyid;
        this.nForm.status = 2;
        this.$confirm("提交后则不能更改,请确认后提交", "提示", {
          confirmButtonText: "提交",
          cancelButtonText: "保存",
          type: "info",
        })
          .then(() => {
            device_replaceAdd(this.nForm).then((res) => {
              if (res.code === 200) {
                this.$emit("completed");
              }
            });
          });
      });
    },
    addCheckpoint () {
      this.innerVisible = true;
    },
    checkpointidChange (value) {
      getEquipmentByEmissions(value).then((res) => {
        this.equipmentEmisson = res.data;
      });
    },
    del (v) {
      this.checkpointData.splice(v.$index, 1);
      this.msgSuccess("删除成功!");
    },
    selectEquiemnt () {
      this.$refs["formMsg"].validate((valid) => {
        if (!valid) return;
        let newFormMsg = JSON.parse(JSON.stringify(this.formMsg));
        let {checkpointId, dataDevId} = newFormMsg;
        let newData = JSON.parse(JSON.stringify(this.checkpointData));
        if (newData.every(o => o.datadevid !== dataDevId)) {
          newFormMsg.checkpointDesc = this.emissions.filter(i => i.checkpointid === checkpointId)[0].checkpointdesc;
          newFormMsg.dataDevDesc = this.equipmentEmisson.filter(i => i.datadevid === dataDevId)[0].datadevdesc;
          this.checkpointData.push(newFormMsg);
          this.msgSuccess("添加成功!");
        } else {
          this.msgError("此设备已存在列表中,请勿重复添加!");
        }
      });
    },
    closeInner () {
      this.innerVisible = false;
      this.formMsg = {
        checkpointId: null,
        dataDevId: null,
        other: null
      };
    }
  }
};
</script>

<style lang='scss' scoped>

</style>
