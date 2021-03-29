<template>
  <div class="task-wk">
    <!-- 搜索表单 -->
    <el-form :model="queryForm" ref="queryForm" :inline="true" class="qixiu-serchForm">
      <el-form-item label="污染源" prop="fullName">
        <el-input
          v-model="queryForm.fullName"
          placeholder="请输入污染源"
          clearable
          size="mini"
          style="width: 180px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
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
    <!--    <el-row :gutter="10" class="mb8">-->
    <!--      <el-col :span="1.5">-->
    <!--        <el-button-->
    <!--          type="primary"-->
    <!--          icon="el-icon-plus"-->
    <!--          size="mini"-->
    <!--          @click="handleAdd"-->
    <!--          v-hasPermi="[`${platform}:video:add`]"-->
    <!--        >新增-->
    <!--        </el-button>-->
    <!--      </el-col>-->
    <!--      <el-col :span="1.5">-->
    <!--        <el-button-->
    <!--          type="success"-->
    <!--          icon="el-icon-edit"-->
    <!--          size="mini"-->
    <!--          :disabled="single"-->
    <!--          @click="handleUpdate"-->
    <!--          v-hasPermi="[`${platform}:video:edit`]"-->
    <!--        >修改-->
    <!--        </el-button>-->
    <!--      </el-col>-->
    <!--      <el-col :span="1.5">-->
    <!--        <el-button-->
    <!--          type="danger"-->
    <!--          icon="el-icon-delete"-->
    <!--          size="mini"-->
    <!--          :disabled="multiple"-->
    <!--          @click="handleDelete"-->
    <!--          v-hasPermi="[`${platform}:video:del`]"-->
    <!--        >删除-->
    <!--        </el-button>-->
    <!--      </el-col>-->
    <!--    </el-row>-->
    <!-- 表格 -->
    <el-table
      v-loading="loading"
      :data="tableData"
      style="width: 100%;flex:1"
      row-key="videoId"
      @selection-change="handleSelectionChange"
      @row-click="(row)=>$refs.oTable.toggleRowSelection(row)"
      height="100%"
      ref="oTable"
      border
    >
      <el-table-column type="selection" width="55" align="center"></el-table-column>
      <el-table-column prop="fullName" label="污染源" min-width="220"></el-table-column>
      <el-table-column prop="videoDesc" label="视频名称" min-width="180"></el-table-column>
      <el-table-column prop="outletName" label="关联排放口" v-if="$route.path.includes('qixiu')"></el-table-column>
      <el-table-column prop="videoType" label="视频类型" :formatter="videoFormat" width="120"></el-table-column>
      <el-table-column prop="videoAddress" label="视频地址" min-width="200"></el-table-column>
      <el-table-column label="操作" width="100" align="center">
        <template slot-scope="scope">
          <el-link
            type="primary"
            :underline="false"
            style="font-size:12px"
            @click.native.stop="videoPlay(scope.row)"
          >视频播放
          </el-link>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="page.pageNum"
      :limit.sync="page.pageSize"
      @pagination="getList(queryForm)"
    />
    <!-- 弹出框 -->
    <VideoForm
      :title="title"
      :values="editValues"
      :visible.sync="visible"
      :isPlatName="platform"
      :platformName="platform"
      @successRequest="handleQuery"
      v-if="visible"
    ></VideoForm>
    <!-- 视频播放 -->
    <play-dialog v-bind="videoOption" :visible.sync="videoVisible"  v-if="videoVisible" @videoHistory="videoHistory"></play-dialog>
  </div>
</template>
<script>
import {videoList, videoDel, getToken, getVideoWS} from "@/api/video";
import VideoForm from "./form";
import PlayDialog from "./play";

const platformList = [
  "zaozhi",
  "guocheng",
  "jinshu",
  "qixiu",
  "lingxing",
  "voe",
  "youyan",
  "jiayou",
  "outwater",
  "dgom"
];
export default {
  components: {VideoForm, PlayDialog},
  data() {
    return {
      // 查询参数
      page: {
        pageNum: 1,
        pageSize: 30
      },
      total: 0,
      //查询表单
      queryForm: {
        fullName: undefined,
        videoDesc: undefined,
        videoAddress: undefined
      },
      platform: "",
      editValues: {},
      editVisable: false,
      title: undefined,
      // 是否显示弹出层
      open: false,
      //选中详情
      selectRows: [],
      sSelectRows: {},
      // 弹出层标题
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      //表格数据
      loading: false,
      tableData: [],
      videoOption: {
        title: null,
        url: null,
        token: null,
        videoType: null,
        isCloud: false
      },
      videoVisible: false,
      visible: false,
      //视频类型
      videoTypes: [],
      videoType: null,
      isHistory: false,
      historyTime: null,
      videoDetails: {},
    };
  },
  computed: {
    ids() {
      return this.selectRows.map((item) => item.videoId);
    }
  },
  created() {
    //过滤获取平台
    let routePath = this.$route.path;
    platformList.forEach((i) => {
      if (routePath.includes(i)) {
        this.platform = i;
        this.getList();
        return;
      }
    });
    this.getDicts("ty_video_type").then((res) => {
      this.videoTypes = res.data;
    });
  },
  methods: {
    /** 获取列表 **/
    getList(v = {}) {
      this.loading = true;
      videoList(v, this.platform, this.urlParameter(this.page)).then((res) => {
        if (res.code === 200) {
          this.tableData = res.rows;
          this.total = res.total;
        }
        this.loading = false;
      });
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.page.pageNum = 1;
      this.getList(this.queryForm);
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.visible = true;
      this.editValues = {};
      this.title = "新增视频设备";
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.selectRows = selection;
      this.single = selection.length != 1;
      this.multiple = !selection.length;
    },
    /** 修改按钮操作 */
    handleUpdate() {
      this.visible = true;
      this.editValues = this.selectRows[0];
      this.title = "修改视频设备";
    },
    /** 删除按钮操作 */
    handleDelete() {
      const postIds = this.ids.join(",");
      this.$confirm("是否确认删除选中数据数据项?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          return videoDel(postIds, this.platform);
        })
        .then(() => {
          this.getList(this.queryForm);
          this.msgSuccess("删除成功");
        })
        .catch(function () {
        });
    },
    //视频播放
    async videoPlay(v) {
      this.videoDetails = v;
      this.videoOption.title = v.videoDesc;
      this.videoType = v.videoType;
      if (v.videoType === "FluorideCloud") {
        try {
          const res = await getToken(v.videoId, this.platform);
          if (res.code !== -1) {
            this.videoOption.url = v.videoAddress;
            this.videoOption.videoType = v.videoType;
            this.videoOption.token = res.data;
            this.videoVisible = true;
            this.videoOption.isCloud = true;
          }
        } catch (error) {
        }
      } else {
        try {
          // let oV = {
          //   id: v.videoId
          // }
          // if (this.historyTime) {
          //   let oTime = this.historyTime;
          //   oV.time = oTime;
          // }
          const res = await getVideoWS(this.platform, v.videoId, this.historyTime);
          this.videoOption.url = res.data;
          this.videoOption.videoType = v.videoType;
          this.videoVisible = true;
          this.videoOption.isCloud = false;
        } catch (error) {
        }
      }
    },
    // 视频类型字典翻译
    videoFormat(row, column) {
      return this.selectDictLabel(this.videoTypes, row.videoType);
    },
    videoHistory(v) {
      if (v) {
        this.historyTime = v;
        this.isHistory = true;
      } else {
        this.historyTime = null;
        this.isHistory = false;
      }
      this.videoPlay(this.videoDetails);
    }
  }
};
</script>
