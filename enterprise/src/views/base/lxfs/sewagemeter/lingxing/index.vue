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
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
        >删除
        </el-button>
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
      <el-table-column type="index" label="序号" width="50" align="center"></el-table-column>
      <el-table-column prop="meterName" label="水表名称"></el-table-column>
      <el-table-column prop="amount" label="水表读数">
        <template slot-scope="scope">{{ scope.row.amount || "-" }}</template>
      </el-table-column>
      <el-table-column prop="dataDate" label="读取时间">
        <template slot-scope="scope">{{ scope.row.dataDate || "-" }}</template>
      </el-table-column>
      <el-table-column prop="dateCreated" label="创建时间"></el-table-column>
    </el-table>
    <!-- 弹窗 -->
    <el-dialog :title="title" :visible.sync="visible" width="800px" :close-on-click-modal="false">
      <formTem
        ref="formTem"
        :collects="collects"
        :values="values"
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
import {
  lxMeterinfoList,
  lxMeterinfoDel,
  lxMeterinfoData,
} from "@/api/lingxing";
import {list} from "@/api/collect";

export default {
  components: {formTem},
  data () {
    return {
      visible: false,
      tableData: [],
      selectRows: [],
      collects: [],
      values: {},
      title: "",
      page: {
        pageNum: 1,
        pageSize: 30,
      },
    };
  },
  computed: {
    single () {
      return this.selectRows.length === 1;
    },
    multiple () {
      return !this.selectRows.length;
    },
    ids () {
      return this.selectRows.map((item) => item.meterId);
    },
  },
  created () {
    this.getList();
  },
  methods: {
    getList () {
      lxMeterinfoList({meterType: 1}, this.urlParameter(this.page)).then((res) => {
        if (res.code === 200) {
          this.tableData = res.data || res.rows;
        }
      });
    },
    /** 获取数采 **/
    getCollectList (v = {}) {
      list("lingxing", v, this.urlParameter({pageNum: 1, pageSize: 30})).then(
        (res) => {
          if (res.code === 200) {
            this.collects = res.data || res.rows;
          }
        }
      );
    },
    // 新增
    handleAdd () {
      this.getCollectList();
      this.title = "新增排放流量计";
      this.values = {};
      this.visible = true;
    },
    //修改
    handleUpdate () {
      this.getCollectList();
      this.title = "编辑排放流量计";
      this.values = this.selectRows[0];
      this.visible = true;
      // lxMeterinfoData({meterid: this.selectRows[0].meterid}).then((res) => {
      //   if (res.code === 200) {
      //     this.values = res.data || res.rows;
      //     this.visible = true;
      //   }
      // });
    },
    //删除
    handleDelete () {
      this.$confirm("是否确认删除选中数据数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          return lxMeterinfoDel(this.ids);
        })
        .then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
        .catch((e) => {
        });
    },
    handleSelectionChange (selection) {
      this.selectRows = selection;
    },
    completed () {
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
