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
      <el-table-column prop="shortName" label="处置企业"></el-table-column>
      <el-table-column prop="startDate" label="合同开始时间"></el-table-column>
      <el-table-column prop="endDate" label="合同结束时间"></el-table-column>
    </el-table>
    <!-- 弹窗 -->
    <el-dialog :title="title" :visible.sync="visible" width="500px">
      <formTem
        ref="formTem"
        :values="values"
        @completed="completed"
        v-if="visible"
      ></formTem>
      <span slot="footer" class="dialog-footer">
        <el-button size="mini" @click="visible = false">取 消</el-button>
        <el-button type="primary" size="mini" @click="$refs.formTem.submitForm()">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>
<script>
import formTem from './form'
import {
  UnitList,
  delTransUnit
} from '@/api/lingxing'
import { remove } from '@/api/outlet'

export default {
  components: { formTem },
  data() {
    return {
      visible: false,
      tableData: [],
      selectRows: [],
      values: {},
      title: '',
      page: {
        pageNum: 1,
        pageSize: 30
      }
    }
  },
  computed: {
    single() {
      return this.selectRows.length === 1
    },
    multiple() {
      return !this.selectRows.length
    },
    ids() {
      return this.selectRows.map((item) => item.contractId)
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      UnitList(this.page).then((res) => {
        if (res.code === 200) {
          this.tableData = res.data || res.rows
        }
      })
    },
    // 新增
    handleAdd() {
      this.title = '新增转运合同'
      this.values = {}
      this.visible = true
    },
    //修改
    handleUpdate() {
      this.title = '编辑转运合同'
      this.values = this.selectRows[0]
      this.visible = true
    },
    handleSelectionChange(selection) {
      this.selectRows = selection
    },
    completed() {
      this.getList()
      this.visible = false
      this.values = {}
    },
    handleDelete() {
      this.$confirm("是否确认删除选中数据数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() =>  {
          return delTransUnit(this.ids.join(","));
        })
        .then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
        .catch(() =>  {});
    }
  }
}
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
