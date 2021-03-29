<template>
  <div class="om-search-wk">
    <div class="box-show" title="展开标签" @click="isLabel=true" v-if="!isLabel">
      <i class="el-icon-d-arrow-right"></i>
    </div>
    <transition name="omLabel">
      <div class="om-fold" v-show="isLabel">
        <el-card class="box-card" shadow="always">
          <h4>
            标签
            <i class="el-icon-d-arrow-left hidden-icon" title="隐藏标签" v-if="isLabel"
               @click="isLabel=false"></i>
          </h4>
          <el-tree
            :data="res"
            show-checkbox
            node-key="id"
          >
          </el-tree>
        </el-card>
      </div>
    </transition>
    <div class="om-content">
      <div class="om-content-search">
        <el-form :model="queryForm" ref="queryForm" :inline="true" class="guocheng-serchForm">
          <el-form-item label="企业名称" prop="sourceName">
            <el-select v-model="queryForm.sourceName" placeholder="请选择企业">
              <el-option label="企业一" value="shanghai"></el-option>
              <el-option label="企业二" value="beijing"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="因子" prop="cpName">
            <el-select v-model="queryForm.cpName" placeholder="请选择因子">
              <el-option label="因子一" value="shanghai"></el-option>
              <el-option label="因子二" value="beijing"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="日期范围" prop="mn">
            <el-date-picker
              v-model="queryForm.time"
              type="daterange"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期">
            </el-date-picker>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
          </el-form-item>
        </el-form>
      </div>
      <div class="om-content-text">
        <el-table
          :data="tableData"
          border
          style="width: 100%">
          <el-table-column
            prop="address"
            label="企业名称">
          </el-table-column>
          <el-table-column
            prop="date"
            label="日期"
            width="180">
          </el-table-column>
          <el-table-column
            prop="name"
            label="镇街"
            width="180">
          </el-table-column>
          <el-table-column
            prop="address1"
            label="行业">
          </el-table-column>
          <el-table-column
            prop="address2"
            label="监控点">
          </el-table-column>
          <el-table-column
            prop="address3"
            label="因子">
          </el-table-column>
        </el-table>
      </div>
      <!-- 分页 -->
      <pagination
        :total="total"
        :page.sync="page.pageNum"
        :limit.sync="page.pageSize"
      />
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      isLabel: true,
      queryForm: {},
      tableData: [],
      total: 1,
      page: {
        pageNum: 1,
        pageSize: 30
      },
      res: [{
        id: 1,
        label: "一级 1",
        children: [{
          id: 4,
          label: "二级 1-1",
          children: [{
            id: 9,
            label: "三级 1-1-1"
          }, {
            id: 10,
            label: "三级 1-1-2"
          }]
        }]
      }, {
        id: 2,
        label: "一级 2",
        children: [{
          id: 5,
          label: "二级 2-1"
        }, {
          id: 6,
          label: "二级 2-2"
        }]
      }, {
        id: 3,
        label: "一级 3",
        children: [{
          id: 7,
          label: "二级 3-1"
        }, {
          id: 8,
          label: "二级 3-2"
        }]
      }]
    }
  },
  methods: {
    handleQuery() {}
  }
}
</script>
<style lang="scss">
.om-search-wk {
  width: 100%;
  height: 100%;
  display: flex;
  overflow: hidden;
  position: relative;

  .box-show {
    height: 60px;
    width: 16px;
    position: absolute;
    border-radius: 0 15px 15px 0;
    top: 50%;
    border: 1px solid #e2e2e2;
    background-color: #f2f2f2;
    z-index: 999;
    cursor: pointer;
    display: flex;
    align-items: center;
    margin-top: -30px;

    &:hover {
      background-color: #999;

      i {
        color: #fff;
      }
    }

    i {
      font-size: 12px;
    }
  }

  .om-fold {
    width: 240px;
    height: 100%;
    overflow: hidden;
    display: flex;
    align-items: center;
  }

  .box-card {
    width: 100%;
    height: 98%;

    .el-card__body {
      padding: 10px 15px 15px 15px;
    }

    h4 {
      width: 208px;
      text-align: center;
      border-bottom: 1px solid #e6ebf5;
      margin: 0;
      padding-bottom: 5px;
      margin-bottom: 8px;
      letter-spacing: 2px;

      .hidden-icon {
        float: right;
        cursor: pointer;
        font-size: 14px;

        &:hover {
          color: #46a6ff;
        }
      }
    }
  }
}

.om-content {
  flex: 1;
  height: 100%;
  display: flex;
  flex-direction: column;
  overflow: hidden;
  padding: 0 10px 5px;

  .om-content-search {
    width: 100%;
    height: 40px;
    display: flex;
    align-items: center;

    .el-form {
      margin-left: 10px;

      .el-form-item {
        margin-bottom: 0px;
      }
    }

  }

  .om-content-text {
    flex: 1;
    overflow: hidden;
    width: 100%;

  }

}

.omLabel-leave-active, .omLabel-enter-active {
  transition: all 1s ease;
}

.omLabel-leave-active, .omLabel-enter {
  width: 0px !important;
}

.omLabel-leave, .omLabel-enter-active {
  width: 240px;
}
</style>
