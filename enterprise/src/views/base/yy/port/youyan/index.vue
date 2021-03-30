<template>
  <div class="yy-style">
    <!-- 操作按钮 -->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAdd">新增排放口</el-button>
        <el-button type="success" icon="el-icon-video-camera-solid" size="mini" @click="showFlowReal">查看流程图</el-button>
      </el-col>
      <!--      <el-col :span="1.5">-->
      <!--        <el-button-->
      <!--          type="success"-->
      <!--          icon="el-icon-edit"-->
      <!--          size="mini"-->
      <!--          :disabled="!single"-->
      <!--          @click="handleUpdate"-->
      <!--        >修改-->
      <!--        </el-button>-->
      <!--      </el-col>-->
      <!--      <el-col :span="1.5">-->
      <!--        <el-button-->
      <!--          type="danger"-->
      <!--          icon="el-icon-delete"-->
      <!--          size="mini"-->
      <!--          :disabled="multiple"-->
      <!--          @click="handleDelete"-->
      <!--        >删除-->
      <!--        </el-button>-->
      <!--      </el-col>-->
    </el-row>
    <el-table
      :data="tableData"
      border
      ref="refTable"
      :row-key="row => `${row.facilityPid}_${row.facilityId}`"
      :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
      @row-click="(row)=>$refs.refTable.toggleRowExpansion(row)"
      size="mini"
      style="width: 100%">
      <el-table-column
        prop="facilityName"
        label="设施名称"
        :show-overflow-tooltip="true"
        width="300">
      </el-table-column>
      <el-table-column
        prop="facilityType"
        label="设施类型"
        width="120"
        align="center">
        <template slot-scope="scope">
          {{ scope.row.facilityType === "yy_yc" ? "排放口" : scope.row.facilityType === "yy_fj" ? "排放风机" : "净化器" }}
        </template>
      </el-table-column>
      <el-table-column
        prop="status"
        label="当前状态"
        width="120"
        align="center">
        <template slot-scope="scope">
          <span v-if="!scope.row.amount && scope.row.facilityType==='yy_yc'">-</span>
          <span v-else-if="scope.row.amount && scope.row.facilityType!=='yy_yc'">
              {{ scope.row.amount > scope.row.threshold ? "开启" : "关闭" }}
          </span>
          <span v-else-if="!scope.row.amount && scope.row.facilityType!=='yy_yc'">关闭</span>
        </template>
      </el-table-column>
      <el-table-column
        prop="memo"
        label="备注">
      </el-table-column>
      <el-table-column
        label="操作">
        <template slot-scope="scope">
          <div v-if="scope.row.facilityType==='yy_yc'">
            <el-button
              type="warning"
              size="mini"
              @click.stop="handleUpdate(scope.row)">
              修改排放口
            </el-button>
            <el-button
              type="danger"
              size="mini"
              @click.stop="handleDelete(scope.row)">
              删除排放口
            </el-button>
            <el-button
              type="primary"
              size="mini"
              @click.stop="exhaustAdd(scope.row)">
              新增排放风机
            </el-button>
          </div>
          <div v-else-if="scope.row.facilityType==='yy_fj'&& scope.row.depthLevel ===2">
            <el-button
              type="warning"
              size="mini"
              @click.stop="exhaustUpdate(scope.row)">
              修改风机
            </el-button>
            <el-button
              type="danger"
              size="mini"
              @click.stop="handleDelete(scope.row)">
              删除风机
            </el-button>
            <el-button
              type="primary"
              size="mini"
              @click.stop="equipAdd(scope.row)">
              新增净化器
            </el-button>
          </div>
          <div v-else-if="scope.row.facilityType==='yy_fj' && scope.row.depthLevel > 2">
            <el-button
              type="warning"
              size="mini"
              @click.stop="exhaustUpdate(scope.row)">
              修改风机
            </el-button>
            <el-button
              type="danger"
              size="mini"
              @click.stop="handleDelete(scope.row)">
              删除风机
            </el-button>
            <el-button
              type="primary"
              size="mini"
              @click.stop="equipAdd(scope.row)">
              新增净化器
            </el-button>
            <el-button
              type="success"
              size="mini"
              @click.stop="relationPur(scope.row)">
              关联其他净化器
            </el-button>
          </div>
          <div v-else>
            <el-button
              type="warning"
              size="mini"
              @click.stop="equipUpdate(scope.row)">
              修改净化器
            </el-button>
            <el-button
              type="danger"
              size="mini"
              @click.stop="handleDelete(scope.row)">
              删除净化器
            </el-button>
            <!--            <el-button-->
            <!--              type="success"-->
            <!--              size="mini"-->
            <!--              @click.stop="relationFan(scope.row)">-->
            <!--              关联其他风机-->
            <!--            </el-button>-->
            <el-button
              type="primary"
              size="mini"
              @click.stop="exhaustAdd(scope.row)">
              新增排放风机
            </el-button>
          </div>
        </template>
      </el-table-column>
    </el-table>
    <portTem :values="protValue" v-if="protVisible" :title="protTitle" :visible.sync="protVisible"
             :collectList="collectList"
             @successReq="successReq"></portTem>
    <exhaustTem :values="exhaustValue" v-if="exhaustVisible" :title="exhaustTitle" :visible.sync="exhaustVisible"
                :collectList="collectList" :level="level" :pid="pid" @successReq="successReq"></exhaustTem>
    <equipTem :values="equipValue" v-if="equipVisible" :title="equipTitle" :visible.sync="equipVisible"
              :collectList="collectList" :level="level" :pid="pid" @successReq="successReq"></equipTem>
    <relaTem :values="relaValue" v-if="relaVisible" :visible.sync="relaVisible" :list="oList"
             @relaSuccess="relaSuccess"></relaTem>
    <flow-real v-if="flowRealShow" type="youyan" :visible.sync="flowRealShow"></flow-real>
  </div>
</template>

<script>
  import portTem from "./port";
  import exhaustTem from "./exhaust";
  import equipTem from "./equip";
  import relaTem from "./rela";
  import {yyFacilityList, yySelect, yyFacilityDel, yySamelevel} from "@/api/youyan";
  import FlowReal from "../../../../components/flowReal/index";

  export default {
    components: {FlowReal, portTem, exhaustTem, equipTem, relaTem},
    data: () => ({
      selectRows: [],
      tableData: [],
      protValue: {},
      protTitle: "",
      protVisible: false,
      exhaustValue: {},
      exhaustTitle: "",
      exhaustVisible: false,
      equipValue: {},
      equipTitle: "",
      equipVisible: false,
      relaVisible: false,
      relaValue: {},
      collectList: [],
      oList: [],
      selectF: [],
      page: {
        pageNum: 1,
        pageSize: 20
      },
      pid: "",
      level: 0,
      flowRealShow: false,
      sourceId: ""
    }),
    computed: {
      single() {
        return this.selectRows.length === 1;
      },
      multiple() {
        return !this.selectRows.length;
      }
    },
    created() {
      this.getlist();
      this.getCollectList();
    },
    methods: {
      getlist() {
        yyFacilityList(this.urlParameter(this.page)).then(res => {
          if (res.code === 200) {
            this.tableData = res.data || res.rows;
          }
        });
      },
      getCollectList() {
        yySelect().then(res => {
          this.collectList = res.rows;
        });
      },
      //新增风机
      exhaustAdd(v) {
        this.pid = v.facilityId;
        this.level = v.depthLevel + 1;
        this.exhaustTitle = "新增排放风机";
        this.exhaustValue = {};
        this.exhaustVisible = true;
      },
      exhaustUpdate(v) {
        this.exhaustTitle = "修改排放风机";
        this.exhaustValue = v;
        this.exhaustVisible = true;
      },
      //治理设施
      equipAdd(v) {
        this.pid = v.facilityId;
        this.level = v.depthLevel + 1;
        this.equipTitle = "新增净化器";
        this.equipValue = {};
        this.equipVisible = true;
      },
      equipUpdate(v) {
        this.equipTitle = "修改净化器";
        this.equipValue = v;
        this.equipVisible = true;
      },
      //新增排放口
      handleAdd() {
        this.protTitle = "新增排放口";
        this.protValue = {};
        this.protVisible = true;
      },
      handleUpdate(v) {
        this.protTitle = "编辑排放口";
        this.protValue = v;
        this.protVisible = true;
      },
      handleDelete(v) {
        if (v.children.length > 0 || (v.facilityPids && v.facilityPids.split(",").length > 1)) {
          this.msgError("该条数据存在其他关联设施,无法删除!");
        } else {
          this.$confirm("是否删除该条数据?", "警告", {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning"
          })
            .then(() => {
              return yyFacilityDel(v.facilityId);
            })
            .then(() => {
              this.msgSuccess("删除成功!");
              this.getlist();
            })
            .catch((e) => {
            });
        }
      },
      relationPur(v) {
        let oId = v.facilityId;
        this.relaValue.pid = v.facilityPids.split(",").map(Number);
        this.relaValue.id = oId;
        yySamelevel(oId).then(res => {
          if (res.code === 200) {
            this.oList = res.data;
            this.relaVisible = true;
          } else {
            this.msgError(res.message);
          }
        });
      },
      successReq(v) {
        v === 1 ? this.protVisible = false : v === 2 ? this.exhaustVisible = false : this.equipVisible = false;
        this.getlist();
      },
      relaSuccess() {
        this.relaValue = {};
        this.relaVisible = false;
        this.getlist();
      },
      showFlowReal() {
        this.flowRealShow = true;
      }
    }
  };
</script>

<style lang="scss">
  .yy-style {
    width: 100%;
    height: 100%;
    overflow: hidden;
    display: flex;
    flex-direction: column;
  }
</style>
