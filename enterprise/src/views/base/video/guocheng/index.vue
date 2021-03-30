<template>
  <div class="base-list">
    <!-- 搜索表单 -->
    <el-form :model="queryForm" ref="queryForm" :inline="true" class="search-form">
      <el-form-item label="视频名称" prop="videoDesc">
        <el-input
          v-model="queryForm.videoDesc"
          placeholder="请输入视频名称"
          clearable
          size="mini"
          style="width: 180px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="视频地址" prop="videoAddress">
        <el-input
          v-model="queryForm.videoAddress"
          placeholder="请输入视频地址"
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
      v-loading="loading"
      :data="tableData"
      height="100%"
      style="width: 100%; flex:1;"
      @selection-change="handleSelectionChange"
      ref="oTable"
    >
      <el-table-column type="selection" width="55" align="center"></el-table-column>
      <el-table-column prop="fullName" label="污染源" width="220"></el-table-column>
      <el-table-column prop="videoDesc" label="视频名称"></el-table-column>
      <el-table-column prop="dictLabel" label="视频类型"></el-table-column>
      <el-table-column prop="videoAddress" label="视频地址"></el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-link
            type="primary"
            :underline="false"
            style="font-size:12px"
            @click="videoPlay(scope.row)"
          >视频播放
          </el-link>
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
    <!-- 新增修改 -->
    <video-form
      :title="title"
      :values="editValues"
      :visible.sync="editVisible"
      @completed="successRequest"
    ></video-form>
    <play-dialog v-bind="videoOption" :visible.sync="videoVisible"></play-dialog>
  </div>
</template>
<script>
import { list, remove, getToken, getVideoWS } from "@/api/video";
import PlayDialog from "@/views/components/play-dialog";
import VideoForm from "./form";

export default {
  components: { PlayDialog, VideoForm },
  data() {
    return {
      loading: true,
      title: undefined,
      editVisible: false,
      editValues: {},
      //查询表单
      queryForm: {
        fullName: undefined,
        videoDesc: undefined,
        videoAddress: undefined
      },
      // 查询参数
      page: {
        pageNum: 1,
        pageSize: 20
      },
      total: 0,
      tableData: [],
      selectRows: [],
      videoOption: {
        title: null,
        url: null,
        token: null,
        videoType: null,
        isCloud: false
      },
      videoVisible: false,
      videoTypeList: {
        omAgent: "在线二期视频代理",
        twAgent: "海康网络相机代理",
        FluorideCloud: "萤石云"
      }
    };
  },
  computed: {
    sys_name() {
      return this.$route.path.substr(
        this.$route.path.lastIndexOf("/") + 1
      );
    },
    ids() {
      return this.selectRows.map(item => item.videoId);
    },
    single() {
      return this.selectRows.length === 1;
    },
    multiple() {
      return !this.selectRows.length;
    }
  },
  created() {
    this.getList();
  },
  methods: {
    /** 获取列表 **/
    getList(v = {}) {
      list(this.sys_name, v, this.urlParameter(this.page)).then(res => {
        if (res.code === 200) {
          this.tableData = res.rows || res.data;
          this.total = res.total;
        }
      });
      this.loading = false;
    },
    /** 搜索按钮操作 */
    handleQuery() {
      // this..pageNum = 1;-if
      this.getList(this.queryForm);
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.selectRows = selection;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.editVisible = true;
      this.editValues = {};
      this.title = "添加视频设备";
    },
    /** 修改按钮操作 */
    handleUpdate() {
      this.editVisible = true;
      this.editValues = this.selectRows[0];
      this.title = "修改视频设备";
    },
    //操作成功
    successRequest() {
      this.getList();
      this.editVisible = false;
      this.editValues = {};
    },
    /** 删除按钮操作 */
    handleDelete() {
      this.$confirm("是否确认删除选中数据数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          return remove(this.sys_name, this.ids);
        })
        .then(() => {
          this.getList(this.queryForm);
          this.msgSuccess("删除成功");
        })
        .catch(() => {});
    },
    async videoPlay(v) {
      // this.videoTitle = v.videoDesc;
      this.videoOption.title = v.videoDesc;
      if (v.videoType === "FluorideCloud") {
        // this.isVideo = true;
        try {
          const res = await getToken(this.sys_name, v.videoId);
          if (res.code !== -1) {
            this.videoOption.url = v.videoAddress;
            this.videoOption.token = res.rows || res.data;
            this.videoVisible = true;
            this.videoOption.isCloud = true;
          }
        } catch (error) {}
      } else {
        try {
          const res = await getVideoWS(this.sys_name, v.videoId);
          this.videoOption.url = res.rows || res.data;
          this.videoVisible = true;
          this.videoOption.isCloud = false;
        } catch (error) {}
      }
    }
  }
};
</script>
