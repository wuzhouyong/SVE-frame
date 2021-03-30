<template>
  <div class="base-list n-facil">
    <el-form
      :model="queryForm"
      ref="queryForm"
      :inline="true"
      class="search-form"
    >
      <el-form-item label="镇街" prop="townId">
        <el-select
          size="mini"
          v-model="queryForm.townId"
          placeholder="请选择镇街"
        >
          <el-option
            v-for="item in townList"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="申请状态" prop="status">
        <el-select
          size="mini"
          v-model="queryForm.status"
          placeholder="请选择申请状态"
        >
          <el-option
            v-for="item in applyStatus"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="日期范围" prop="dateRange">
        <el-date-picker
          v-model="queryForm.dateRange"
          type="daterange"
          size="mini"
          style="width: 220px"
          value-format="yyyy-MM-dd"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
        >搜索
        </el-button
        >
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
        >重置
        </el-button
        >
      </el-form-item>
    </el-form>
    <!-- 操作按钮 -->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
        >新增
        </el-button
        >
      </el-col>
      <!--      <el-col :span="1.5">-->
      <!--        <el-button-->
      <!--          type="success"-->
      <!--          icon="el-icon-edit"-->
      <!--          size="mini"-->
      <!--          :disabled="!single"-->
      <!--          @click="handleUpdate"-->
      <!--        >修改-->
      <!--        </el-button-->
      <!--        >-->
      <!--      </el-col>-->
<!--            <el-col :span="1.5">-->
<!--              <el-button-->
<!--                type="danger"-->
<!--                icon="el-icon-delete"-->
<!--                size="mini"-->
<!--                :disabled="multiple"-->
<!--                @click="handleDelete"-->
<!--              >删除-->
<!--              </el-button-->
<!--              >-->
<!--            </el-col>-->
    </el-row>
    <el-table
      v-loading="loading"
      :data="tableData"
      style="width: 100%; flex: 1"
      @selection-change="handleSelectionChange"
      ref="oTable"
      height="100%"
      size="mini"
      border
    >
      <el-table-column
        type="selection"
        width="55"
        align="center"
      ></el-table-column>
      <el-table-column prop="sourceName" label="企业名称" width="220px" show-overflow-tooltip></el-table-column>
      <el-table-column prop="townName" label="镇街" width="100px"></el-table-column>

      <el-table-column prop="applyType" label="申请类型" width="100px">
        <template slot-scope="scope">{{
            scope.row.isOnline === "1" ? "维修(或更换)" : "停用(或拆除)"
          }}
        </template>
      </el-table-column>
      <el-table-column prop="status" label="申请状态" width="80px">
        <template slot-scope="scope">
          <span>{{ scope.row.status ? (applyStatus.filter(i => i.value === scope.row.status))[0].label : "-" }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="contactMan" label="联系人" width="100px"></el-table-column>
      <el-table-column prop="contactTel" label="联系电话" width="100px"></el-table-column>
      <el-table-column
        show-overflow-tooltip
        width="180px"
        prop="changeReason"
        label="更换原因"
      ></el-table-column>
      <el-table-column prop="applyDate" label="申请时间" width="140px"></el-table-column>
      <el-table-column prop="approvedBy" label="经办人" width="100px"></el-table-column>
      <el-table-column prop="approvedDate" label="批复时间" width="140px"></el-table-column>
      <el-table-column
        :show-overflow-tooltip="true"
        min-width="180px"
        prop="approvalDesc"
        label="批复意见"
      ></el-table-column>
      <el-table-column label="操作" width="80px" align="center">
        <template slot-scope="scope">
          <el-button type="text" style="font-size:12px;" @click="queryDetail(scope.row)">查看详情
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <pagination
      :total="total"
      :page.sync="page.pageNum"
      :limit.sync="page.pageSize"
      @pagination="getList(queryForm)"
    />
    <el-dialog
      :title="title"
      :visible.sync="dialogVisible"
      custom-class="n-form-dialog"
      :close-on-click-modal="false"
      width="30%">
      <n-form v-if="dialogVisible" @completed="completed" ref="nForm" :values="values"></n-form>
      <span slot="footer" class="dialog-footer" v-if="!values.applyId">
        <el-button size="mini" @click="dialogVisible=false">取 消</el-button>
        <el-button type="primary" size="mini" @click="subAdd">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import {device_del_list, getEquipmentComlist, getTownList} from "@/api/dgom";
import nForm from "./nForm";

export default {
  components: {nForm},
  data () {
    return {
      // 申请类型,
      applyStatus: [
        {
          value: 2,
          label: "待受理",
        },
        {
          value: 3,
          label: "受理",
        },
        {
          value: 4,
          label: "不受理",
        },
        {
          value: 5,
          label: "同意",
        },
        {
          value: 6,
          label: "拒绝",
        },
        {
          value: 7,
          label: "已处理",
        },
      ],
      tableData: [],
      townList: [],
      //查询表单
      queryForm: {},
      // 查询参数
      page: {
        pageNum: 1,
        pageSize: 30,
      },
      total: 0,
      selectRows: [],
      loading: false,
      dialogVisible: false,
      title: null,
      values: {},
    };
  },
  created () {
    this.getList();
    this.getTown();
    let a = this.applyStatus.filter(i => i.value === 5);
  },
  computed: {
    ids () {
      return this.selectRows.map((item) => item.applyid);
    },
    single () {
      return this.selectRows.length === 1;
    },
    multiple () {
      return !this.selectRows.length;
    },
  },
  methods: {
    /** 获取列表 **/
    getList (v = {}) {
      getEquipmentComlist(v, this.urlParameter(this.page)).then((res) => {
        if (res.code === 200) {
          this.tableData = res.rows || res.data;
          this.total = res.total;
          this.loading = false;
        }
      });
    },
    getTown () {
      getTownList().then((res) => {
        if (res.code === 200) {
          this.townList = res.rows || res.data;
        }
      });
    },
    handleAdd () {
      this.title = "设备状态变更申请";
      this.values = {};
      this.dialogVisible = true;
    },
    handleUpdate () {
    },
    // 多选框选中数据
    handleSelectionChange (selection) {
      this.selectRows = selection;
    },
    handleQuery () {
      this.queryForm
    },
    resetQuery () {
    },
    queryDetail (v) {
      this.title = "设备状态变更详情";
      this.dialogVisible = true;
      this.values = v;
    },
    completed () {
      this.dialogVisible = false;
      this.getList();
    },
    subAdd () {
      this.$refs.nForm.submitForm();
    },
    handleDelete () {
      this.$confirm("是否确认删除选中数据数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          return device_del_list(this.ids);
        })
        .then(() => {
          this.getList(this.queryForm);
          this.msgSuccess("删除成功");
        })
        .catch(() => {
        });
    },
  }
};
</script>

<style lang='scss' scoped>
.n-facil {
  /deep/ .n-form-dialog {
    width: 100%;
    height: auto;
    max-height: calc(100% - 12vh);
    display: flex;
    flex-direction: column;
    overflow: hidden;

    .el-dialog__body {
      flex: 1;
      overflow-y: auto;
    }
  }
}
</style>
