<template>
  <div class="tank-style">
    <!-- 搜索表单 -->
    <el-form :model="tankForm" ref="queryForm" :inline="true" class="outwater-serchForm">
      <el-form-item prop="tankName">
        <el-input
          v-model="tankForm.tankName"
          placeholder="储水罐名称"
          clearable
          size="mini"
          style="width: 300px"
          @clear="onClear"
        >
          <el-button slot="append" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        </el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-plus" size="mini" @click="tankAdd" v-hasPermi="['lingxing:tank:add']">新增</el-button>
      </el-form-item>
    </el-form>
    <el-table
      v-loading="loading"
      :data="tankData"
      style="width: 100%;flex:1;"
      ref="tankTable"
      height="100%"
      border
      size="mini"
    >
      <el-table-column type="index" label="序号" width="50" align="center"></el-table-column>
      <el-table-column prop="tankName" label="储水罐名称" show-overflow-tooltip min-width="200px"></el-table-column>
      <el-table-column prop="sectionArea" label="截面积" width="100px;"></el-table-column>
      <el-table-column prop="warnRate" label="报警液位高度" width="100px;"></el-table-column>
      <el-table-column prop="fullVolume" label="体积" width="100px;"></el-table-column>
      <el-table-column prop="memo" label="备注" width="120px;"></el-table-column>
      <el-table-column label="操作" width="110px" align="center">
        <template slot-scope="scope">
          <el-link
            type="primary"
            :underline="false"
            style="font-size:12px;"
            v-hasPermi="['lingxing:tank:edit']"
            @click="tankUpdate(scope.row)"
          >编辑</el-link>
          <el-divider direction="vertical"></el-divider>
          <el-link
            type="primary"
            :underline="false"
            style="font-size:12px;"
            v-hasPermi="['lingxing:tank:del']"
            @click="tankDelete(scope.row.tankId)"
          >删除</el-link>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <!-- <pagination
      v-show="total>0"
      :total="total"
      :page.sync="page.pageNum"
      :limit.sync="page.pageSize"
      @pagination="getList(tankForm)"
    />-->
    <tankForm
      :title="title"
      :values="values"
      :visible.sync="visible"
      :carId="carId"
      @tankSuccess="tankSuccess"
    ></tankForm>
  </div>
</template>
<script>
import tankForm from "./tankform";
import { truckList, truckDel } from "@/api/lingxing";
export default {
  components: { tankForm },
  props: {
    carId: {
      type: Number,
    },
  },
  data() {
    return {
      tankForm: {},
      loading: false,
      tankData: [],
      selectRows: [],
      values: {},
      visible: false,
      title: "",
      total: 0,
      page: {
        pageNum: 1,
        pageSize: 50,
      },
      tankId: null,
    };
  },
  watch: {
    carId: {
      handler(v) {
        if (v) {
          this.tankForm.carId = v;
          this.getList(this.tankForm);
        }
      },
      immediate: true,
    },
  },
  methods: {
    getList(v = {}) {
      this.loading = true;
      truckList(v, this.urlParameter(this.page)).then((res) => {
        if (res.code === 200) {
          this.tankData = res.data || res.rows;
          this.total = res.total;
        }
        this.loading = false;
      });
    },
    tankAdd() {
      this.title = "新增储水罐";
      this.values = {};
      this.visible = true;
    },
    tankUpdate(v) {
      this.title = "修改储水罐";
      this.values = v;
      this.visible = true;
    },
    tankDelete(v) {
      this.$confirm("是否确认删除数据数据?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(function () {
          return truckDel(v);
        })
        .then(() => {
          this.getList(this.tankForm);
          this.msgSuccess("删除成功");
        })
        .catch(function () {});
    },
    handleQuery() {
      this.getList(this.tankForm);
    },
    tankSuccess() {
      this.getList(this.tankForm);
      this.visible = false;
      this.values = {};
    },
    onClear() {
      this.getList(this.tankForm);
    },
  },
};
</script>
<style lang="scss" scope>
.tank-dialog-wk {
  // height: calc(100% - 20vh);
  height: 50%;
  display: flex;
  flex-direction: column;
  overflow: hidden;
  .el-dialog__body {
    flex: 1;
    overflow: hidden;
    .tank-style {
      height: 100%;
      width: 100%;
      display: flex;
      flex-direction: column;
    }
  }
}
</style>
