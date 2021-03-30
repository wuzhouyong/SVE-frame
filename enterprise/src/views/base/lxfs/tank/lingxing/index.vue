<template>
  <div class="lingxing-cust-style">
    <!-- 操作按钮 -->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAdd">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="!single"
          @click="handleUpdate"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
        >删除</el-button>
      </el-col>
    </el-row>
    <!-- 表格 -->
    <el-table
      :data="tableData"
      style="width: 100%"
      border
      size="mini"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center"></el-table-column>
      <el-table-column prop="storageName" label="废水收集池(桶)名称"></el-table-column>
      <el-table-column prop="storageType" label="储罐类型">
        <template slot-scope="scope">{{tankName[scope.row.storageType]}}</template>
      </el-table-column>
      <!-- 水量预警线/废水收集桶高度 -->
      <el-table-column prop="storageType" label="预警桶占比(%)">
        <template slot-scope="scope">
          <span>{{(Number(scope.row.warnRate)/Number(scope.row.maxHeight)).toFixed(2)}}</span>
        </template>
      </el-table-column>
      <el-table-column prop="fullVolume" label="容积(L)"></el-table-column>
      <el-table-column prop="sectionArea" label="截面积(㎡)"></el-table-column>
      <el-table-column prop="density" label="密度(g/cm³)"></el-table-column>
      <el-table-column prop="memo" label="备注"></el-table-column>
    </el-table>
    <!-- 弹窗 -->
    <el-dialog :title="title" :visible.sync="visible" width="50%" :close-on-click-modal="false">
      <formTem
        ref="formTem"
        :values="values"
        :collects="collects"
        @completed="completed"
        v-if="visible"
      ></formTem>
      <span slot="footer" class="dialog-footer">
        <el-button size="mini" @click="visible = false">取 消</el-button>
        <el-button type="primary" size="mini" @click="$refs.formTem.submit()">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>
<script>
import formTem from "./form";
import { lxStorageList, lxStorageDel } from "@/api/lingxing";
import { list } from "@/api/collect";
export default {
  components: { formTem },
  data() {
    return {
      visible: false,
      tableData: [],
      selectRows: [],
      collects: [],
      values: {},
      tankName: {
        cct: "储存桶",
        zjc: "自建池",
        qt: "其他",
      },
      title: "",
      page: {
        pageNum: 1,
        pageSize: 30,
      },
    };
  },
  computed: {
    single() {
      return this.selectRows.length === 1;
    },
    multiple() {
      return !this.selectRows.length;
    },
    ids() {
      return this.selectRows.map((item) => item.storageId);
    },
  },
  created() {
    this.getList();
    this.getCollectList();
  },
  methods: {
    getList() {
      lxStorageList(this.urlParameter(this.page)).then((res) => {
        if (res.code === 200) {
          this.tableData = res.data || res.rows;
        }
      });
    },
    /** 获取数采 **/
    getCollectList(v = {}) {
      list("lingxing", v, this.urlParameter({ pageNum: 1, pageSize: 30 })).then(
        (res) => {
          if (res.code === 200) {
            this.collects = res.data || res.rows;
            // console.log("this.collects",this.collects )
          }
        }
      );
    },
    // 新增
    handleAdd() {
      this.title = "新增废水收集池(桶)";
      this.values = {};
      this.visible = true;
    },
    //修改
    handleUpdate() {
      this.title = "编辑废水收集池(桶)";
      this.values = JSON.parse(JSON.stringify(this.selectRows[0]));
      this.visible = true;
    },
    //删除
    handleDelete() {
      this.$confirm("是否确认删除选中数据数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          return lxStorageDel(this.ids);
        })
        .then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
        .catch((e) => {});
    },
    handleSelectionChange(selection) {
      this.selectRows = selection;
    },
    completed() {
      this.getList();
      this.visible = false;
      this.values = {};
    },
  },
};
</script>
<style lang="scss">
.lingxing-cust-style {
  width: 100%;
  height: 100%;
  overflow: hidden;
  display: flex;
  flex-direction: column;
}
</style>
