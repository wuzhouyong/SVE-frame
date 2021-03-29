<template>
  <div class="rank-table">
    <el-radio-group v-model="showType" @change="onChange">
      <el-radio-button label="0">全部</el-radio-button>
      <el-radio-button label="1">涉水责考评分</el-radio-button>
      <el-radio-button label="2">涉气责考评分</el-radio-button>
    </el-radio-group>
    <ul class="online-build">
      <li v-if="showType==='1'">
        <p>涉水污染源在线监控建设</p>
        <p>责考评分(扣分)</p>
        <p>{{ water.score > 2 ? 2 : water.score }}</p>
        <p>全市排名</p>
        <p>{{ water.rank }}</p>
      </li>
      <li v-else-if="showType==='2'">
        <p>涉气污染源在线监控建设</p>
        <p>责考评分(得分)</p>
        <p>{{ gas.score > 3 ? 3 : gas.score }}</p>
        <p>全市排名</p>
        <p>{{ gas.rank }}</p>
      </li>
    </ul>
    <div class="cTable">
      <el-table
        :data="tableData"
        border
        size="mini"
        class="completTable"
        max-height="545"
        style="width: 100%;">
        <el-table-column
          prop="tagHeadName"
          label="监控任务"
          align="center">
        </el-table-column>
        <el-table-column
          prop="totalcnt"
          label="考核名单任务量"
          align="center">
        </el-table-column>
        <el-table-column
          prop="inlist"
          align="center"
          label="考核名单完成量"
          width="180">
        </el-table-column>
        <el-table-column
          prop="outlist"
          align="center"
          label="非考核名单完成量">
        </el-table-column>
        <el-table-column
          prop="cntrank"
          align="center"
          label="完成量排名">
        </el-table-column>
        <el-table-column
          prop="rate"
          align="center"
          label="完成率(%)">
        </el-table-column>
        <el-table-column
          prop="raterank"
          align="center"
          label="完成率排名">
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
import { taskCountMission, taskCountScore } from "@/api/analysis"

export default {
  data() {
    return {
      showType: "0",
      tableData: [],
      water: {},
      gas: {}
    }
  },
  created() {
    this.getRank(this.showType);
    this.getTaskCount();
  },
  methods: {
    getRank(v) {
      taskCountMission(v).then(res => {
        if (res.code === 200) {
          this.tableData = res.data;
        }
      })
    },
    getTaskCount() {
      taskCountScore().then(res => {
        if (res.code === 200) {
          let oRes = res.data;
          oRes.forEach(i => {
            if (i.rankType === 1) {
              this.water = {
                rank: i.rank,
                score: i.score
              }
            } else if (i.rankType === 2) {
              this.gas = {
                rank: i.rank,
                score: i.score
              }
            }
          })
        }
      })
    },
    onChange(v) {
      this.getRank(v);
    }
  }
}
</script>

<style lang="scss" scoped>
.rank-table {
  padding: 10px 0;
  flex: 1;
  display: flex;
  flex-direction: column;
  overflow: hidden;

  .cTable {
    flex: 1;
  }

  /deep/ .el-loading-mask {
    background-color: rgba(255, 255, 255, .1);
  }

  .completTable {
    background-color: transparent;
    margin-top: 5px;

    /deep/ .el-table__header-wrapper {
      tr {
        background-color: transparent;

        th {
          background-color: transparent;
          color: #fff;
        }

        td {
          background-color: transparent;
          color: #fff;
        }
      }
    }

    /deep/ .el-table__body-wrapper {
      tr {
        background-color: transparent;

        &:hover {
          td {
            background-color: rgba(0, 0, 0, 0.1);
          }
        }

        td {
          background-color: transparent;
          color: #fff;
        }
      }
    }
  }

  ul.online-build {
    padding: 0;

    li {
      list-style-type: none;
      display: flex;
      background-color: #996633;

      p {
        flex: 1;
        border: 1px solid #ddd;
        padding: 10px;
        font-size: 13px;
        color: #fff;
        text-align: center;
        margin: 0 0 -1px -1px;
      }
    }
  }
}
</style>
