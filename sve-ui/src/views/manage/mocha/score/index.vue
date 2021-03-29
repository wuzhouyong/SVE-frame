<template>
  <div class="score-wk">
    <div class="score-left">
      <el-divider content-position="center"><i class="el-icon-date" style="padding-right:5px"></i>问卷主题</el-divider>
      <div class="score-theme">
        <el-card class="box-card"
                 v-for="item in oQuestionAire"
                 :key="item.questionnaireId"
                 shadow="hover"
                 :class="[queryForm.questionnaireId===item.questionnaireId?'check-tag-style':'']">
          <p class="score-p"
             @click="clickQue(item.questionnaireId)"
             :title="`${item.questionnaireName} ( 考核时间：${item.startDate} ~ ${item.endDate} )`">
            <i class="el-icon-document" style="padding-right:5px"></i>{{ item.questionnaireName }}
            ({{ item.coefficient }})
          </p>
        </el-card>
      </div>
    </div>
    <div class="score-source-right">
      <el-table
        v-loading="loading"
        :data="tableData"
        height="100%"
        size="mini"
        border
        style="width: 100%">
        <el-table-column
          prop="opSourceName"
          label="运维单位"
          min-width="250px"
          show-overflow-tooltip>
        </el-table-column>
        <el-table-column
          prop="sourceCnt"
          label="评分企业数"
          show-overflow-tooltip>
        </el-table-column>
        <el-table-column
          prop="avgScore"
          label="平均分">
        </el-table-column>
        <el-table-column
          label="最终得分(平均分*系数)"
          prop="finalScore"
          width="350px">
        </el-table-column>
        <el-table-column
          label="操作"
          align="center"
          width="150">
          <template slot="header" slot-scope="scope">
            <div class="downloadScore">
              <span>操作</span>
              <el-button icon="el-icon-upload2" @click="uploadData">导出数据</el-button>
            </div>
          </template>
          <template slot-scope="scope">
            <el-popover
              placement="right"
              width="100%"
              trigger="click">
              <h4 style="padding:0;margin-bottom:5px;margin-top:5px;">问卷评分详情</h4>
              <div v-for="(i,index) in scope.row.details" :key="index">
                {{ index + 1 }}.{{ i.questionDesc }}({{ i.score }})
              </div>
              <!--              <el-table :data="scope.row.details" border>-->
              <!--                <el-table-column width="150" property="questionDesc" label="问题"></el-table-column>-->
              <!--                <el-table-column width="100" property="score" label="分数"></el-table-column>-->
              <!--              </el-table>-->
              <el-button type="text" size="small" slot="reference">查看详情</el-button>
            </el-popover>
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
    </div>
  </div>
</template>

<script>
import {mochaQuestionaireList, mochaScoreList, scoreExportList} from "@/api/mocha";
import Cookies from "js-cookie";
import {stExportNetwork} from "@/api/currency";

export default {
  data () {
    return {
      tableData: [],
      loading: false,
      oQuestionAire: [],
      queryForm: {questionnaireId: null},
      // 分页
      page: {
        pageNum: 1,
        pageSize: 30
      },
      total: 0,
    };
  },
  created () {
    this.getQuestAire();
  },
  methods: {
    getQuestAire () {
      mochaQuestionaireList().then(res => {
        this.oQuestionAire = res.data;
        if (res.data.length > 0) {
          this.queryForm.questionnaireId = res.data[0].questionnaireId;
          this.getList(this.queryForm);
        }
      });
    },
    getList (v = {}) {
      mochaScoreList(v.questionnaireId, this.urlParameter(this.page)).then(res => {
        this.tableData = res.rows;
        this.total = res.total;
      });
    },
    handleQuery () {
    },
    clickQue (v) {
      this.queryForm.questionnaireId = v;
      this.getList(this.queryForm);
    },
    uploadData () {
      this.$confirm("是否导出数据?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          return scoreExportList(this.queryForm.questionnaireId);
        })
        .then((response) => {
          this.download(response.msg);
        })
        .catch(function () {
        });
    }
  }
};
</script>

<style lang='scss' scoped>
.score-wk {
  width: 100%;
  height: 100%;
  overflow: hidden;
  display: flex;
  padding: 10px;

  .score-left {
    width: 200px;
    height: 100%;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
    border-radius: 4px;
    border: 1px solid #e6ebf5;
    display: flex;
    flex-direction: column;
    overflow: hidden;

    ::-webkit-scrollbar {
      display: none;
    }
  }

  .score-source-right {
    flex: 1;
    width: 100%;
    display: flex;
    flex-direction: column;
    overflow: hidden;
    margin-left: 10px;
  }

  .downloadScore {
    display: flex;
    align-items: center;
    justify-content: space-between;
  }
}

.score-theme {
  padding: 5px;
  flex: 1;
  overflow-y: auto;


  /deep/ .box-card {
    margin-bottom: 6px;
    cursor: pointer;
    display: flex;
    width: 100%;

    .el-card__body {
      width: 100%;
      padding: 0px;
      font-size: 12px;

      &:hover {
        color: #42b983;
        font-weight: bold;
      }
    }
  }

  .check-tag-style {
    color: #42b983;
    font-weight: bold;
  }

  .score-p {
    margin: 0;
    padding: 6px;
    font-size: 12px;
    display: block;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
  }

}
</style>
