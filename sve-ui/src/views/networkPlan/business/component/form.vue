<template>
  <div>
    <div class="sourceMes">
      <el-row :gutter="24">
        <el-col :xl="12" :md="12" :xs="24">
          <span style="width:140px">企业名称</span>
          <el-select
            v-model="value"
            filterable
            placeholder="请选择"
            style="flex:1"
            @change="sourceChange"
            v-if="!formData.sourceId"
          >
            <el-option v-for="item in options" :key="item.id" :label="item.name" :value="item.id"></el-option>
          </el-select>
          <p v-else>{{sourceData.fullName || '-'}}</p>
        </el-col>
        <el-col :xl="12" :md="12" :xs="24" v-if="sourceIds!==''">
          <span>企业地址</span>
          <p>{{sourceData.address || '-'}}</p>
        </el-col>
        <el-col :xl="12" :md="12" :xs="24" v-if="sourceIds!==''">
          <span>所属镇街</span>
          <p>{{sourceData.townName || '-'}}</p>
        </el-col>
        <el-col :xl="12" :md="12" :xs="24" v-if="sourceIds!==''">
          <span>统一社会信用代码</span>
          <p>{{sourceData.socialCreditCode || '-'}}</p>
        </el-col>
        <el-col :xl="12" :md="12" :xs="24" v-if="sourceIds!==''">
          <span>国家排污许可证编号</span>
          <p>{{sourceData.emissionPermitCode || '-'}}</p>
        </el-col>
        <el-col :xl="12" :md="12" :xs="24" v-if="sourceIds!==''">
          <span>行业名称</span>
          <p>{{sourceData.industryName || '-'}}</p>
        </el-col>
        <el-col :xl="12" :md="12" :xs="24" v-if="sourceIds!==''">
          <span>环保联系人</span>
          <p>{{sourceData.envContactName || '-'}}</p>
        </el-col>
        <el-col :xl="12" :md="12" :xs="24" v-if="sourceIds!==''">
          <span>联系电话</span>
          <p>{{sourceData.envContactPhone || '-'}}</p>
        </el-col>
      </el-row>
    </div>
    <el-form ref="ruleform" label-width="150px" :model="formData" :rules="rules">
      <el-row :gutter="24">
        <el-col :xl="24" :md="24" :xs="24">
          <el-form-item label="重点排污单位类型" prop="polluteTypes">
            <el-checkbox-group v-model="formData.polluteTypes">
              <el-checkbox label="1">水环境重点排污单位</el-checkbox>
              <el-checkbox label="2">大气环境重点排污单位</el-checkbox>
              <el-checkbox label="4">土壤环境污染重点监管单位</el-checkbox>
              <el-checkbox label="8">声环境重点排污单位</el-checkbox>
              <el-checkbox label="16">其他重点排污单位</el-checkbox>
            </el-checkbox-group>
          </el-form-item>
        </el-col>
        <el-col :xl="24" :md="24" :xs="24">
          <el-form-item label="备注" prop="memo">
            <el-input type="textarea" v-model="formData.memo" :rows="3" resize="none"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-divider content-position="left" style="font-weight: bold;">监控要求</el-divider>
      <div class="intake-wk">
        <el-form-item prop="hasVideo" label-width="0px">
          <el-checkbox-group v-model="formData.hasVideo">
            <el-checkbox label="1" name="type">站房和排放口视频监控</el-checkbox>
          </el-checkbox-group>
        </el-form-item>
      </div>
    </el-form>
    <div class="checkPoint-pollutant">
      <div class="c-p-header">
        <h4>监控点及监控污染物</h4>
        <el-button type="primary" icon="el-icon-plus" size="mini" @click="checkAdd">新增监控点</el-button>
      </div>
      <div class="check-point-table">
        <el-table
          :data="tableData"
          :loading="tLoading"
          ref="refTable"
          style="width: 100%"
          class="check-table"
          border
          @row-click="(row)=>$refs.refTable.toggleRowExpansion(row)"
        >
          <el-table-column type="expand">
            <template slot-scope="props">
              <div class="commit-header" v-if="props.row.elements.length>0">
                <p>监控污染物</p>
                <p>执行标准</p>
                <p>接入时间</p>
              </div>
              <div v-for="(i,index) in props.row.elements" :key="index" class="check-pollutant">
                <span class="span-name">{{i.elementName}}</span>
                <span class="span-law">{{ i.lawStandard }}</span>
                <span class="span-access" v-if="i.lawStandard">{{ i.accessDate }}</span>
              </div>
            </template>
          </el-table-column>
          <el-table-column prop="checkpointDesc" label="监控点名称"></el-table-column>
          <el-table-column prop="checkpointType" label="排放口类型" :formatter="cPointType" width="180"></el-table-column>
          <el-table-column prop="processFlow" label="生产工艺/设备名称" width="180"></el-table-column>
          <el-table-column prop="emissionCode" label="排污许可证排放口编号"></el-table-column>
          <el-table-column label="操作" align="center" width="120">
            <template slot-scope="scope">
              <el-button type="text" size="small" @click.native.stop="editPoint(scope.row)">编辑</el-button>
              <el-divider direction="vertical"></el-divider>
              <el-button type="text" size="small" @click.native.stop="delPoint(scope)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
    <el-dialog
      :title="title"
      :visible.sync="cVisible"
      width="40%"
      append-to-body
      v-if="cVisible"
      :destroy-on-close="true"
    >
      <CheckForm ref="checkForm" @checkPoints="checkPoints" :editRows="editRows" v-if="cVisible"></CheckForm>
      <span slot="footer" class="dialog-footer">
        <el-button @click="cVisible = false">取 消</el-button>
        <el-button type="primary" @click="checkPointSub">提 交</el-button>
      </span>
    </el-dialog>
  </div>
</template>
<script>
import Treeselect from "@riophae/vue-treeselect";
import { getIndustry } from "@/api/composite/source";
import {
  lwjhPointList,
  lwjhPointAdd,
  lwjhPointDel,
  lwjhPointEdit,
  lwjhSourceInfo,
  lwjhSelectSource
} from "@/api/networkPlan";
import SelectSource from "@/components/SelectSource";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
import CheckForm from "./checkForm";
export default {
  components: { Treeselect, CheckForm, SelectSource },
  props: {
    formData: {
      type: Object
    },
    townCodeOptions: {
      type: Array,
      default: []
    }
  },
  watch: {
    formData: {
      handler(v) {
        if (v.sourceId) {
          console.log(v);
          this.sourceData = v;
          this.sourceIds = v.sourceId;
          this.bizSourceId = v.bizSourceId;
          let oV = { sourceId: v.bizSourceId };
          this.getPointList(oV);
        }
      },
      deep: true,
      immediate: true
    }
  },
  data() {
    return {
      title: "排放口监控",
      cVisible: false,
      intake: [],
      tLoading: false,
      sourceIds: "",
      bizSourceId: "",
      rules: {
        fullName: [
          { required: true, message: "企业名称不能为空", trigger: "blur" }
        ],
        address: [
          { required: true, message: "企业地址不能为空", trigger: "blur" }
        ],
        townCode: [
          { required: true, message: "所属镇街不能为空", trigger: "blur" }
        ],
        industryCode: [
          { required: true, message: "所属行业不能为空", trigger: "blur" }
        ],
        socialCreditCode: [
          {
            required: true,
            message: "统一社会信用代码不能为空",
            trigger: "blur"
          }
        ],
        emissionPermitCode: [
          {
            required: true,
            message: "国家排污许可证编号不能为空",
            trigger: "blur"
          }
        ],
        envContactName: [
          { required: true, message: "环保联系人不能为空", trigger: "blur" }
        ],
        envContactPhone: [
          { required: true, message: "联系电话不能为空", trigger: "blur" }
        ],
        polluteTypes: [
          {
            required: true,
            message: "重点排污单位类型不能为空",
            trigger: "blur"
          }
        ]
      },
      industryCode1Options: [],
      tableData: [],
      industryName: "",
      town: "",
      portType: [],
      editRows: {},
      options: [],
      value: "",
      sourceData: {}
    };
  },
  created() {
    getIndustry().then(res => (this.industryCode1Options = res.data));
    this.getDicts("discharge_port_type").then(response => {
      this.portType = response.data;
    });
    lwjhSelectSource({}).then(res => {
      if (res.code === 200) {
        this.options = res.data;
      }
    });
  },
  methods: {
    getSource(v) {
      lwjhSourceInfo(v).then(res => {
        if (res.code === 200 && res.data.length > 0) {
          this.bizSourceId = res.data[0].bizSourceId;
          this.sourceData = res.data[0];
        }
      });
    },
    getPointList(v = {}) {
      this.tLoading = true;
      lwjhPointList(v).then(res => {
        if (res.code === 200) {
          this.tableData = res.data;
          this.tLoading = false;
        }
        this.tLoading = false;
      });
    },
    submitForm(v) {
      this.$refs["ruleform"].validate(valid => {
        if (!valid) {
          this.msgError("请完整填写必填项!");
          return;
        }
        if (this.bizSourceId === "") {
          this.msgError("请选择需要添加的企业!");
          return;
        }
        if (this.formData.hasVideo.length > 0) {
          this.formData.hasVideo = "1";
        } else {
          this.formData.hasVideo = "0";
        }
        const arr = this.formData.polluteTypes;
        for (const i in arr) this.formData.polluteType |= arr[i];
        this.formData.bizSourceId = this.bizSourceId;
        this.$emit("sendForm", this.formData);
      });
    },
    checkAdd() {
      this.editRows = [];
      this.cVisible = true;
    },
    checkPoints(v) {
      if (this.bizSourceId !== "") {
        if (v.checkpointId) {
          this.pointEdit(v);
        } else {
          if (v.elements.length === 0) {
            this.msgError("请填写监控污染物");
            return;
          }
          v.sourceId = this.bizSourceId;
          this.pointAdd(v);
        }
        this.cVisible = false;
      }
    },
    pointAdd(v) {
      lwjhPointAdd(v).then(res => {
        if (res.code === 200) {
          this.msgSuccess("插入监控点成功");
          this.getPointList(v);
        }
      });
    },
    pointEdit(v) {
      lwjhPointEdit(v).then(res => {
        if (res.code === 200) {
          this.msgSuccess("监控点修改成功");
          this.getPointList(v);
        }
      });
    },
    //排放口类型
    cPointType(row) {
      return this.selectDictLabel(this.portType, row.checkpointType);
    },
    //提交
    checkPointSub() {
      this.$refs.checkForm.submitForm();
    },
    editPoint(v) {
      this.cVisible = true;
      this.editRows = JSON.parse(JSON.stringify(v));
    },
    //删除
    delPoint(v) {
      if (this.formData.sourceId) {
        lwjhPointDel({ checkpointId: v.row.checkpointId }).then(res => {
          if (res.code === 200) {
            this.getPointList({ sourceId: this.formData.sourceId });
            this.msgSuccess("监测点删除成功");
          } else {
            this.msgError(res.message);
          }
        });
      } else {
        let oIndex = v.$index;
        this.tableData.splice(oIndex, 1);
      }
    },
    sourceChange(v) {
      this.sourceIds = v;
      let oV = { sourceId: v };
      this.getSource(oV);
    }
  }
};
</script>
<style lang="scss">
.intake-wk {
  width: 100%;
  display: flex;
  .el-form-item--mini.el-form-item {
    margin-bottom: 5px;
  }
}
.checkPoint-pollutant {
  .c-p-header {
    h4 {
      margin: 3px 10px 3px 0;
    }
    display: flex;
  }
  .check-pollutant {
    width: 500px;
    height: 28px;
    line-height: 28px;
    display: flex;
    span {
      height: 28px;
      line-height: 28px;
      margin: 0;
      flex: 1;
      font-size: 14px;
      text-align: center;
    }
  }
}
.check-point-table {
  margin-top: 15px;
}
.check-table {
  td.el-table__expanded-cell {
    padding: 10px 50px;
  }
}
.commit-header {
  width: 500px;
  height: 28px;
  line-height: 28px;
  display: flex;
  p {
    height: 28px;
    line-height: 28px;
    margin: 0;
    flex: 1;
    font-size: 14px;
    font-weight: bold;
    text-align: center;
  }
}
.sourceMes {
  margin-bottom: 10px;
  .el-col {
    display: flex;
    align-items: center;
    margin-bottom: 5px;
    height: 28px;
    line-height: 28px;
    span {
      width: 140px;
      text-align: right;
      margin-right: 15px;
    }
    span.el-input__suffix-inner {
      margin-right: 0px;
    }
    p {
      flex: 1;
      margin: 0;
    }
  }
}
</style>

