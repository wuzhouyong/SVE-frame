<template>
  <div class="apply-lw">
    <el-form ref="form" :model="form" :rules="rules" label-width="145px" label-position="right">
      <el-row :gutter="15">
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="企业名称" prop="sourceId">
            <el-select v-model="form.sourceId" placeholder="" size="mini" style="width:100%">
              <el-option :label="i.zdatasourname" :value="i.zdatasourid" v-for="i in sourceList"
                         :key="i.zdatasourid"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="社会统一信用代码" prop="socialCreditCode">
            <el-input v-model="form.socialCreditCode" size="mini"></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="行业" prop="industryId" style="margin-bottom:16px">
            <el-select v-model="form.industryId" placeholder="" size="mini" style="width:100%">
              <el-option :label="i.name" :value="i.id" v-for="i in industrysList" :key="i.id"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="环保负责人" prop="envChargeMan">
            <el-input v-model="form.envChargeMan" size="mini"></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="负责人电话" prop="contactTel">
            <el-input v-model="form.contactTel" size="mini"></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="网络运营商" prop="networkOperator">
            <el-select v-model="form.networkOperator" size="mini" placeholder="" style="width:100%">
              <el-option label="电信" value="电信"></el-option>
              <el-option label="联通" value="联通"></el-option>
              <el-option label="移动" value="移动"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="镇街" prop="townId">
            <el-select v-model="form.townId" size="mini" placeholder="" style="width:100%">
              <el-option :label="i.name" :value="i.id" v-for="i in townList" :key="i.id"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="详细地址" prop="address">
            <el-input v-model="form.address" size="mini"></el-input>
          </el-form-item>
        </el-col>

        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="经度" prop="longitude">
            <el-input v-model="form.longitude" size="mini"></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="纬度" prop="latitude">
            <el-input v-model="form.latitude" size="mini"></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="申请人" prop="applyPerson">
            <el-input v-model="form.applyPerson" size="mini"></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="申请人电话" prop="contactCellPhone">
            <el-input v-model="form.contactCellPhone" size="mini"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <el-divider content-position="left">
      <el-button type="primary" size="mini" icon="el-icon-plus" @click="addCollect">新增数采</el-button>
    </el-divider>
    <el-table
      :data="tableData"
      style="width: 100%"
      class="collTableStyle"
      row-key="guid"
      size="mini"
      border>
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-table
            :data="props.row.devices"
            style="width: 100%"
            border
            size="mini">
            <el-table-column
              label="监控因子"
              prop="element"
              show-overflow-tooltip>
            </el-table-column>
            <el-table-column
              label="设备品牌"
              prop="brand"
              show-overflow-tooltip>
            </el-table-column>
            <el-table-column
              label="设备型号"
              prop="type"
              show-overflow-tooltip>
            </el-table-column>
            <el-table-column
              label="限制下限"
              prop="alarmMin"
              show-overflow-tooltip>
            </el-table-column>
            <el-table-column
              label="限制上限"
              prop="alarmMax"
              show-overflow-tooltip>
            </el-table-column>
            <el-table-column
              label="操作" align="center">
              <template slot-scope="scope">
                <el-link type="danger" style="font-size:12px" @click="delDevice(scope.row)">
                  删除设备
                </el-link>
              </template>
            </el-table-column>
          </el-table>
        </template>
      </el-table-column>
      <el-table-column
        label="监控点名称"
        prop="checkpointDesc"
        show-overflow-tooltip>
      </el-table-column>
      <el-table-column
        label="数采品牌"
        prop="collectDevDesc"
        show-overflow-tooltip>
      </el-table-column>
      <el-table-column
        label="数采型号"
        prop="collectDevSpec"
        show-overflow-tooltip>
      </el-table-column>
      <el-table-column
        label="MN码"
        prop="mn"
        show-overflow-tooltip>
      </el-table-column>
      <el-table-column
        label="操作" align="center" width="180px">
        <template slot-scope="scope">
          <el-link type="primary" style="font-size:12px" @click="addDevice(scope.row)">
            新增设备
          </el-link>
          <el-divider direction="vertical"></el-divider>
          <el-link type="danger" style="font-size:12px" @click="delCollect(scope.row)">删除数采</el-link>
        </template>
      </el-table-column>
    </el-table>
    <collectTem :visible.sync="collectVisible" @addCollectSuccess="addCollectSuccess"
                v-if="collectVisible"></collectTem>
    <deviceTem :visible.sync="deviceVisible" :guid="guid" @successDevice="successDevice"
               v-if="deviceVisible"></deviceTem>
  </div>
</template>

<script>
import {dgomTowns, dgomIndustrys, dgomEnterprise, dgomRegister} from "@/api/dgom";
import collectTem from "./collect";
import deviceTem from "./device";

export default {
  components: {collectTem, deviceTem},
  data: () => ({
    form: {},
    townList: [],
    industrysList: [],
    sourceList: [],
    rules: {
      sourceId: [
        {required: true, message: "企业名称必填", trigger: "blur"}
      ],
      socialCreditCode: [
        {required: true, message: "社会统一信用代码必填", trigger: "blur"}
      ],
      industryId: [
        {required: true, message: "行业必填"}
      ],
      envChargeMan: [
        {required: true, message: "环保负责人必填", trigger: "blur"}
      ],
      contactTel: [
        {required: true, message: "负责人电话必填", trigger: "blur"}
      ],
      townId: [
        {required: true, message: "镇街必填"}
      ],
      address: [
        {required: true, message: "详细地址必填", trigger: "blur"}
      ],
      networkOperator: [
        {required: true, message: "网络运营商必填"}
      ],
      longitude: [
        {required: true, message: "经度必填", trigger: "blur"}
      ],
      latitude: [
        {required: true, message: "维度必填", trigger: "blur"}
      ],
      applyPerson: [
        {required: true, message: "申请人必填", trigger: "blur"}
      ],
      contactCellPhone: [
        {required: true, message: "申请人电话必填", trigger: "blur"}
      ]
    },
    tableData: [],
    collectVisible: false,
    deviceVisible: false,
    guid: null,
    dataId: null
  }),
  created () {
    dgomTowns().then(res => {
      if (res.code === 200) {
        this.townList = res.data;
      }
    });
    dgomIndustrys().then(res => {
      if (res.code === 200) {
        this.industrysList = res.data;
      }
    });
    dgomEnterprise().then(res => {
      if (res.code === 200) {
        this.sourceList = res.data;
        if (this.sourceList.length === 1) {
          this.$set(this.form, "sourceId", this.sourceList[0].zdatasourid);
        }
      }
    });
  },
  methods: {
    addCollect () {
      this.collectVisible = true;
    },
    addDevice (v) {
      this.guid = v.guid;
      this.deviceVisible = true;
    },
    addCollectSuccess (v) {
      this.tableData.push(v);
      this.collectVisible = false;
    },
    successDevice (v) {
      this.tableData.forEach(i => {
        if (i.guid === v.parentId) {
          i.devices.push(v);
        }
      });
      this.deviceVisible = false;
    },
    submit () {
      this.$refs["form"].validate((valid) => {
        if (!valid) {
          return;
        }
        if (this.tableData.length < 1) {
          this.msgError("请至少添加一个数采");
          return;
        }

        if (!this.tableData.every(i => i.devices.length > 0)) {
          this.msgError("每个数采下面至少添加一个设备");
          return;
        }

        this.form.collects = this.tableData;
        dgomRegister(this.form).then(res => {
          if (res.code === 200) {
            this.dataId = res.data;
            let elA = document.createElement("a");
            elA.target = "_new";
            elA.href = `/dev-api/dgom/networking/export/${res.data}`;
            elA.click();
            this.msgSuccess("联网申请提交成功");
            this.$emit('complete')
          }
        });
      });

    },
    //删除设备
    delDevice (v) {
      let newData = JSON.parse(JSON.stringify(this.tableData));
      newData.forEach(i => {
        i.devices = i.devices.filter(o => o.deviceId !== v.deviceId);
      });
      this.tableData = newData;
    },
    delCollect (v) {
      this.$confirm("是否确认删除该数采?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.tableData = this.tableData.filter(i => i.guid !== v.guid);
          this.msgSuccess("删除成功");
        })
        .catch(() => {
        });

    }

  }
};
</script>

<style lang="scss">
.apply-lw {
  .el-form-item {
    margin-bottom: 15px;
  }
}

.applyUpload {
  margin-top: 10px;
}

.collTableStyle {
  .el-table__expanded-cell {
    padding: 5px 5px 5px 47px;
  }
}

.demo-table-expand {
  font-size: 0;
}

.demo-table-expand label {
  width: 90px;
  color: #99a9bf;
}

.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 50%;
}
</style>
