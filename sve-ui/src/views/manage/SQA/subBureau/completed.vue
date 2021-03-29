<template>
  <div style="height: calc(100% - 54px);">
<!--    <ul class="online-build">-->
<!--      <li>-->
<!--        <p>涉水污染源在线监控建设</p>-->
<!--        <p>责考评分(扣分)</p>-->
<!--        <p>{{ water.score }}</p>-->
<!--        <p>全市排名</p>-->
<!--        <p>{{ water.rank }}</p>-->
<!--      </li>-->
<!--      <li>-->
<!--        <p>涉气污染源在线监控建设</p>-->
<!--        <p>责考评分(得分)</p>-->
<!--        <p>{{ gas.score }}</p>-->
<!--        <p>全市排名</p>-->
<!--        <p>{{ gas.rank }}</p>-->
<!--      </li>-->
<!--    </ul>-->
    <el-checkbox-group v-model="checkState" size="mini" style="margin:5px 0;" @change="onChange">
      <el-checkbox-button :key="1" label="1">施工中</el-checkbox-button>
      <el-checkbox-button :key="2" label="2">已接入</el-checkbox-button>
      <el-checkbox-button :key="6" label="6">已验收</el-checkbox-button>
    </el-checkbox-group>
    <el-table :data="tableData" style="width: 100%" border class="completTable" height="96%">
      <el-table-column prop="tagHeadName" label="监控任务" align="right"></el-table-column>
      <el-table-column prop="cnt" label="完成量" align="right"></el-table-column>
      <el-table-column prop="cntrank" label="完成量排名" align="right"></el-table-column>
      <el-table-column prop="rate" label="完成率(%)" align="right"></el-table-column>
      <el-table-column prop="raterank" label="完成率排名" align="right"></el-table-column>
    </el-table>
  </div>
</template>
<script>
import { countCompleted, taskCountScore } from "@/api/analysis";
import { task_statistics } from "@/config";

export default {
  created() {
    this.getCountCompleted();
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
  data() {
    return {
      tableData: [],
      checkState: ["1", "2", "6"],
      water: {},
      gas: {}
    };
  },
  methods: {
    getCountCompleted(v = {}) {
      countCompleted(v).then((res) => {
        if (res.code === 200) {
          let oRes = res.data;
          let oArr = [];
          task_statistics.forEach((i) => {
            oArr.push(oRes.find((j) => j.tagHeadId === i.id));
          });
          this.tableData = oArr;
        }
      });
    },
    onChange(v) {
      if (v.length === 0) {
        this.getCountCompleted();
      } else {
        if (v.indexOf("2") > -1) {
          let oVal = v;
          oVal = oVal.concat(["3", "4"]);
          this.getCountCompleted({ listTypes: oVal.join(",") });
        } else {
          this.getCountCompleted({ listTypes: v.join(",") });
        }
      }
    }
  }
};
</script>
<style lang="scss">
//ul.online-build {
//  padding: 0;
//
//  li {
//    list-style-type: none;
//    display: flex;
//    background-color: #996633;
//
//    p {
//      flex: 1;
//      border: 1px solid #ddd;
//      padding: 10px;
//      font-size: 13px;
//      color: #fff;
//      text-align: center;
//      margin: 0 0 -1px -1px;
//    }
//  }
//}

.completTable {
  background-color: transparent;
  margin-top: 5px;

  .el-table__header-wrapper {
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

  .el-table__body-wrapper {
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
</style>
