<template>
  <div class="base-list">
    <!-- 搜索表单 -->
    <el-form
      :model="queryForm"
      ref="queryForm"
      :inline="true"
      class="search-form"
    >
      <el-form-item label="排污单位" prop="sourcename">
        <el-input
          v-model="queryForm.sourcename"
          placeholder="请输入排污单位"
          clearable
          size="mini"
          style="width: 180px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="排放类型" prop="checkpointtype">
        <el-select
          v-model="queryForm.checkpointtype"
          placeholder="请选择排放类型"
        >
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
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
          @click="handleAdd(1)"
        >新增水设施
        </el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd(2)"
        >新增气设施
        </el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="!single"
          @click="handleUpdate"
        >修改
        </el-button
        >
      </el-col>
      <!-- <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          >删除</el-button
        >
      </el-col> -->
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-edit"
          size="mini"
          :disabled="!single"
          @click="exportWord"
        >导出
        </el-button
        >
      </el-col>
    </el-row>
    <!-- 表格 -->
    <el-table
      v-loading="loading"
      :data="tableData"
      style="width: 100%; flex: 1"
      @selection-change="handleSelectionChange"
      ref="oTable"
      height="100%"
      border
    >
      <el-table-column
        type="selection"
        width="55"
        align="center"
      ></el-table-column>
      <el-table-column prop="checkpointtype" label="排放类型">
        <template slot-scope="scope">{{
            scope.row.checkpointtype == '1' ? '水' : '气'
          }}
        </template>
      </el-table-column>
      <el-table-column
        prop="sourceenvhead"
        label="排污单位环保负责人"
        width="140"
      ></el-table-column>
      <el-table-column
        prop="sourceenvheadinfo"
        label="联系方式"
        width="140"
      ></el-table-column>
      <el-table-column prop="opcompanyname" label="运营单位" width="140"></el-table-column>
      <el-table-column
        prop="opcompanyhead"
        label="运维单位负责人"
        width="120"
      ></el-table-column>
      <el-table-column
        prop="opcompanyheadinfo"
        label="联系方式"
        width="140"
      ></el-table-column>
      <el-table-column
        prop="checkpointdesc"
        label="排污口名称"
        width="140"
      ></el-table-column>
      <el-table-column
        prop="oppersonname"
        label="运维人员姓名"
        width="120"
      ></el-table-column>
      <el-table-column prop="oppersontel" label="联系电话" width="140"></el-table-column>
      <el-table-column
        prop="opstartdate"
        label="运营开始日期"
        width="120"
      ></el-table-column>
      <el-table-column prop="openddate" label="运营结束日期" width="120"></el-table-column>
      <el-table-column prop="oplicensenum" label="运营证编号" width="140"></el-table-column>
      <el-table-column prop="oplicissueunit" label="发证单位" width="140"></el-table-column>
      <el-table-column prop="oplicissuedate" label="发证时间" width="140"></el-table-column>
      <el-table-column
        prop="opnolicreason"
        label="无证明原因"
        width="140"
      ></el-table-column>

      <!-- <el-table-column prop="checkDate" label="检测时间">
        <template slot-scope="scope">{{
          scope.row.checkDate ? scope.row.checkDate : "-"
        }}</template>
      </el-table-column>
      <el-table-column prop="isOnline" align="center" label="是否在线">
        <template slot-scope="scope">{{
          scope.row.isOnline === "1" ? "在线" : "离线"
        }}</template>
      </el-table-column> -->
    </el-table>
    <!-- 分页 -->
    <pagination
      :total="total"
      :page.sync="page.pageNum"
      :limit.sync="page.pageSize"
      @pagination="getList(queryForm)"
    />
    <elementwater-form
      v-if="editVisible && equipType == '1'"
      :equipType="equipType"
      :title="title"
      :values="editValues"
      :visible.sync="editVisible"
      @completed="successRequest()"
    ></elementwater-form>

    <elementgas-form
      v-if="editVisible && equipType == '2'"
      :equipType="equipType"
      :title="title"
      :values="editValues"
      :visible.sync="editVisible"
      @completed="successRequest()"
    ></elementgas-form>
  </div>
</template>
<script>
import ElementwaterForm from './waterform'
import ElementgasForm from './gasform'
import { getOperationList, getWaterImportantList } from '@/api/dgom'

export default {
  components: { ElementwaterForm, ElementgasForm },
  data() {
    return {
      //   添加是水1设施或气2设施
      equipType: '',

      value: '',
      options: [
        {
          value: 1,
          label: '水'
        },
        {
          value: 2,
          label: '气'
        }
      ],
      loading: false,
      title: undefined,
      editVisible: false,
      editValues: {},
      //查询表单
      queryForm: {
        cpName: undefined,
        elemName: undefined
      },
      // 查询参数
      page: {
        pageNum: 1,
        pageSize: 30
      },
      total: 0,
      tableData: [],
      selectRows: []
    }
  },
  computed: {
    sys_name() {
      return this.$route.path.substr(this.$route.path.lastIndexOf('/') + 1)
    },
    ids() {
      return this.selectRows.map((item) => item.checkpointid)
    },
    single() {
      return this.selectRows.length === 1
    },
    multiple() {
      return !this.selectRows.length
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 获取列表 **/
    getList(v = {}) {
      getOperationList(this.queryForm, this.urlParameter(this.page)).then(
        (res) => {
          if (res.code === 200) {
            this.tableData = res.rows || res.data
            this.total = res.total
          }
        }
      )
      this.loading = false
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.page.pageNum = 1
      this.getList(this.queryForm)
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm('queryForm')
      this.handleQuery()
    },
    /** 新增按钮操作 */
    handleAdd(type) {
      this.equipType = type
      this.editVisible = true
      this.editValues = {}
      this.title = this.equipType == 1 ? '添加水设施' : '添加气设施'
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.selectRows = selection
    },
    /** 修改按钮操作 */
    handleUpdate() {
      this.editValues = this.selectRows[0]


      this.equipType = this.editValues.checkpointtype

      this.title = this.equipType == 1 ? '修改水设施' : '修改气设施'
      this.editVisible = true
    },
    //操作成功
    successRequest() {
      this.getList()
      this.editVisible = false
    },
    /** 删除按钮操作 */

    // handleDelete() {
    //   this.$confirm("是否确认删除选中数据数据项?", "警告", {
    //     confirmButtonText: "确定",
    //     cancelButtonText: "取消",
    //     type: "warning",
    //   })
    //     .then(() => {
    //       return remove(this.sys_name, this.ids);
    //     })
    //     .then(() => {
    //       this.getList(this.queryForm);
    //       this.msgSuccess("删除成功");
    //     })
    //     .catch(() => {});
    // },
    exportWord() {
      console.log(123)

      let checkpointid = this.selectRows[0].checkpointid
      let checkpointtype = this.selectRows[0].checkpointtype

      let elA = document.createElement('a')
      elA.target = '_new'
      // 判断是水还是气的导出
      if (checkpointtype == 1) {
        elA.href = `/dev-api/dgom/facility/export_opw/${checkpointid}`
      } else {
        elA.href = `/dev-api/dgom/facility/export_opg/${checkpointid}`
      }

      elA.click()
    }
  }
}
</script>
<style lang="scss" >
.base-list {
  height: 700px;
}
</style>
