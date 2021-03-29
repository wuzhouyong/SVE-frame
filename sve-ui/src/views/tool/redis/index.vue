<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" label-width="68px" @submit.native.prevent>
      <el-form-item label="关键字" prop="key">
        <el-input
          v-model="queryParams.key"
          placeholder="请输入 Redis key 查询关键字"
          clearable
          style="width: 240px;"
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
      </el-form-item>
    </el-form>
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-plus"
          size="mini"
          @click="updateuser"
          v-hasPermi="['tool:redis:updateuser']"
        >更新用户数据
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-plus"
          size="mini"
          @click="updatedept"
          v-hasPermi="['tool:redis:updatedept']"
        >更新部门数据
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-plus"
          size="mini"
          @click="updatedeptUseratree"
          v-hasPermi="['tool:redis:updatedeptUseratree']"
        >更新用户部门树数据
        </el-button>
      </el-col>
    </el-row>
    <hr/>
    <json-viewer
      :value="setTree"
      copyable
      boxed
      sort/>

  </div>
</template>

<script>
  import {getCacheList, updateCacheDeptUserTree,updateCacheDept} from "@/api/system/redis";
  import JsonViewer from 'vue-json-viewer'
  import { MessageBox } from 'element-ui'

  export default {
    name: "Redis",
    components: {
      JsonViewer
    },
    data() {
      return {
        // 遮罩层
        loading: true,
        // 查询参数
        queryParams: {
          key: undefined
        },
        setTree: []
      };
    },
    created() {
      this.getList();
      this.getDicts("sys_common_status").then(response => {
        this.statusOptions = response.data;
      });
    },
    methods: {
      /** 查询登录日志列表 */
      getList() {
        getCacheList({key: this.queryParams.key}).then(res => {
          //console.log(res);
          this.setTree = res.rows;
        });
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.getList();
      },
      updatedept() {

      },
      updateuser() {
         updateCacheDept().then(res => {
          //console.log(res);
          MessageBox.confirm(
            (res.code === 200 ? 'redis 更新用户成功！':'redis 更新用户失败！'),
            '系统提示',
            {
              cancelButtonText: '取消',
              type: 'warning'
            }
          )
        })

      },
      updatedeptUseratree() {
        updateCacheDeptUserTree().then(res => {
          //console.log(res);
          MessageBox.confirm(
            (res.code === 200 ? 'redis 部门用户树更新成功！':'redis 部门用户树更新失败！'),
            '系统提示',
            {
              cancelButtonText: '取消',
              type: 'warning'
            }
          )
        })
      }
    }
  };
</script>

