<template>
  <!--  水平衡统计分析-->
  <div class="task-wk">
    <div class="balance-wk">
      <div class="balance-left">
        <el-table
          :data="tableData"
          size="mini"
          height="100%"
          border
          style="width: 100%">
          <el-table-column
            prop="date"
            label="镇街"
            width="80">
          </el-table-column>
          <el-table-column
            prop="name"
            label="用水量"
            width="80">
          </el-table-column>
          <el-table-column
            prop="address"
            label="产废量">
          </el-table-column>
          <el-table-column
            prop="address"
            label="历史产废系数"
            width="110">
          </el-table-column>
          <el-table-column
            prop="address"
            label="产废量预估值"
            width="110">
          </el-table-column>
          <el-table-column
            prop="address"
            label="偏离度">
          </el-table-column>
          <el-table-column
            prop="address"
            label="是否异常">
          </el-table-column>
        </el-table>
      </div>
      <div class="balance-right">
        <!-- 搜索表单 -->
        <el-form :model="queryForm" ref="queryForm" :inline="true">
          <el-form-item label="企业名称" prop="sourceName">
            <el-input
              v-model="queryForm.sourceName"
              placeholder="企业名称"
              clearable
              size="mini"
              style="width: 180px"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="异常类型" prop="sourceName">
            <el-input
              v-model="queryForm.sourceName"
              placeholder="异常类型"
              clearable
              size="mini"
              style="width: 180px"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
            <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
          </el-form-item>
        </el-form>
        <el-table
          :data="tableData"
          size="mini"
          border
          height="100%"
          style="width: 100%;flex:1">
          <el-table-column
            prop="date"
            label="企业名称"
            min-width="180">
          </el-table-column>
          <el-table-column
            prop="name"
            label="用水量"
            width="80">
          </el-table-column>
          <el-table-column
            prop="address"
            label="产废量"
            width="80">
          </el-table-column>
          <el-table-column
            prop="address"
            label="历史产废系数"
            width="110">
          </el-table-column>
          <el-table-column
            prop="address"
            label="产废量预估值"
            width="110">
          </el-table-column>
          <el-table-column
            prop="address"
            label="偏离度"
            width="90">
          </el-table-column>
          <el-table-column
            prop="address"
            label="异常类型"
            width="110">
          </el-table-column>
        </el-table>
        <!-- 分页 -->
        <pagination
          :total="total"
          :page.sync="page.pageNum"
          :limit.sync="page.pageSize"
        />
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      queryForm: {},
      tableData: [],
      // 分页
      page: {
        pageNum: 1,
        pageSize: 30
      },
      total: 0,
    }
  },
  methods: {
    handleQuery() {
    },
    resetQuery() {
    }
  }
}
</script>

<style lang="scss" scoped>
.balance-wk {
  width: 100%;
  height: 100%;
  overflow: hidden;
  display: flex;

  .balance-left {
    width: 660px;
    height: 100%;
    overflow: hidden;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
    border: 1px solid #e6ebf5;
  }

  .balance-right {
    flex: 1;
    height: 100%;
    overflow: hidden;
    margin-left: 5px;
    display: flex;
    flex-direction: column;

    /deep/ .el-form {
      padding-left: 10px;

      .el-form-item {
        margin-bottom: 5px;
      }
    }
  }
}
</style>
