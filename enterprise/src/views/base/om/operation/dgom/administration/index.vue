<template>
  <div class="base-list">
    <!-- 搜索表单 -->
    <el-form
      :model="queryForm"
      ref="queryForm"
      :inline="true"
      class="search-form"
    >
      <el-form-item label="企业名称" prop="zdatasourname">
        <el-input
          v-model="queryForm.zdatasourname"
          placeholder="请输入排污单位"
          clearable
          size="mini"
          style="width: 180px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="台账类型" prop="zoperationtype">
        <el-select
          size="mini"
          v-model="queryForm.zoperationtype"
          placeholder="请选择台账类型"
        >
          <el-option
            v-for="item in operationtype"
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
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
        >删除
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
      <el-table-column prop="zdatasourname" label="企业名称"></el-table-column>
      <el-table-column prop="zoperationdate" label="日期"></el-table-column>

      <el-table-column label="台账类别">
        <template slot-scope="scope">
          {{ types[scope.row.zoperationtype] }}
        </template>
      </el-table-column>
      <el-table-column prop="zdatadevdesc" label="设备名称"></el-table-column>
      <!-- <el-table-column
        prop="installLocation"
        label="规格型号"
      ></el-table-column>
      <el-table-column
        prop="installLocation"
        label="设备编号"
      ></el-table-column> -->
      <el-table-column
        prop="zmaintenancecorp"
        label="维护单位"
      ></el-table-column>
      <el-table-column
        prop="zinstallplace"
        label="安装地点"
      ></el-table-column>
      <el-table-column
        prop="zmaintenancestaff"
        label="维护保养人"
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
    <element-form
      v-if="editVisible"
      :handleType="handleType"
      :title="title"
      :values="editValues"
      :visible.sync="editVisible"
      @completed="successRequest()"
    ></element-form>
  </div>
</template>
<script>
import { list, remove } from '@/api/element'
import ElementForm from './form'
import {
  delOperation,
  getAdminList

  //   addGasEquipment,
  //   getWaterImportantList,
  //   addWaterEquipment,
  //   editGasEquipment,
} from '@/api/dgom'

export default {
  components: { ElementForm },
  data() {
    return {
      value: '',
      types: ['烟气日常巡检维护记录', '烟气零漂（跨漂）校准记录', '烟气校验测试记录', '污水日常巡检维护记录', '污水设备校准记录', '污水校验测试记录', '易耗品', '流量计强检'],
      // 台账类型,
      operationtype: [
        {
          value: '1',
          label: '烟气日常巡检维护记录'
        },
        {
          value: '2',
          label: '烟气零漂（跨漂）校准记录'
        },
        {
          value: '3',
          label: '烟气校验测试记录'
        },
        {
          value: '4',
          label: '污水日常巡检维护记录'
        },
        {
          value: '5',
          label: '污水日常巡检维护记录'
        },
        {
          value: '6',
          label: '污水校验测试记录'
        },
        {
          value: '7',
          label: '易耗品'
        },
        {
          value: '8',
          label: '易耗品'
        }
      ],
      loading: false,
      title: undefined,
      editVisible: false,
      editValues: {},
      //查询表单
      queryForm: {
        zdatasourname: '',
        zoperationtype: ''
      },
      // 查询参数
      page: {
        pageNum: 1,
        pageSize: 30
      },
      total: 0,
      tableData: [],
      selectRows: [],
      handleType: ''
    }
  },
  computed: {
    sys_name() {
      return this.$route.path.substr(this.$route.path.lastIndexOf('/') + 1)
    },
    ids() {
      return this.selectRows.map((item) => item.zoperationid)
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
      getAdminList(v, this.urlParameter(this.page)).then((res) => {
        if (res.code === 200) {
          this.tableData = res.rows || res.data
          this.total = res.total
          this.loading = false
        }
      })
    },
    /** 搜索按钮操作 */
    handleQuery() {
      let form = Object.assign({}, this.queryForm)
      this.page.pageNum = 1
      if (form.dateRange) {
        var date_range = {
          beginTime: form.dateRange[0],
          endTime: form.dateRange[1]
        }
        form = Object.assign({}, form, date_range)
        // console.log(form);
        delete form.dateRange
      }
      //   console.log(form);
      this.getList(form)
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm('queryForm')
      this.handleQuery()
    },
    /** 新增按钮操作 */
    handleAdd() {
      console.log(123)
      this.handleType = false

      this.editValues = {}
      this.title = '添加'
      this.editVisible = true
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.selectRows = selection
    },
    /** 修改按钮操作 */
    handleUpdate() {
      this.handleType = true

      this.editValues = this.selectRows[0]
      this.title = '修改'
      this.editVisible = true
    },
    //操作成功
    successRequest() {
      this.getList()
      this.editVisible = false
    },
    /** 删除按钮操作 */

    handleDelete() {
      this.$confirm('是否确认删除选中数据数据项?', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          return delOperation(this.ids)
        })
        .then(() => {
          this.getList(this.queryForm)
          this.msgSuccess('删除成功')
        })
        .catch(() => {
        })
    }
  }
}
</script>
<style lang="scss">
.base-list {
  height: 700px;
}
</style>
