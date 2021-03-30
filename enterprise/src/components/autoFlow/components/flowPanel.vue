<template>
  <div class="flow-panel" ref="flowPanel">
    <div class="flow-title">{{chart.name}}</div>
    <div class="flow-box" ref="flowBox" :style="{transform:'scale('+flowSize.scale+')'}">
      <div v-for="(row,rowIndex) in boxMap" v-if="rowIndex < maxY" :key="'line_'+rowIndex" class="element-line"
           :class="rowIndex%2 == 0? 'flow-line':'tag-line'">
        <div v-for="(col,colIndex) in row" :key="rowIndex+'-'+colIndex" v-if="colIndex < maxX" class="element-point">
          <select-point :status="eleStatus['E'+col.id]" :point="col" v-if="col.type.indexOf('_')>0"/>
          <select-pipe :pipe="col" :status="getPipeStatus(col.pid)" :where="{row:rowIndex,col:colIndex}"
                       v-if="col.type == 'pipe'"/>
          <select-tags :tags="getFacTags(col.pid)" :pid="col.pid" v-if="col.type == 'tag'"></select-tags>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import SelectPoint from "./selectPoint";
  import SelectPipe from "./selectPipe";
  import SelectTags from "./selectTags";
  import {getFlowRealData} from "../../../api/flowReal/charData";

  export default {
    name: "flowPanel",
    props: ["chart","type"],
    components: {SelectTags, SelectPoint, SelectPipe},
    data() {
      return {
        flowData: {},
        eleStatus: {"E0": true, "E22": false, "E12": true, "E23": true, "E13": false, "E2": true},
        pipeStatus: {"P0": true, "P22": true, "P12": true, "P23": true, "P13": true, "P2": true},
        tagValues: [],
        boxMap: [],
        arrayIn: {col: 0, row: -2},
        timer: {},
        flowSize: {scale: 1, left: 0, top: 0},
        facility: null,
        platform: null,
        maxX: 0,
        maxY: 0
      }
    },
    mounted() {
      let width = this.$refs.flowPanel.clientWidth - 20;
      //let height = this.$refs.flowPanel.clientHeight;
      //console.log("width:" + width);
      if (width < this.maxX * 150) {
        this.flowSize.scale = width / (this.maxX * 150);
        //console.log(this.$refs.flowBox.clientHeight);
        //this.flowSize.scale = 1;
        //this.$refs.flowPanel.style.height = ((this.$refs.flowPanel.clientHeight - 25) * this.flowSize.scale) + "px";
        // console.log(width);
      }

      //数据刷新
      this.updateStatus();
      this.timer = setInterval(this.updateStatus, 10000);
    },
    created() {
      //console.log(this.chart);
      this.flowData = this.chart;
      this.facility = this.flowData.id;
      this.platform = this.type;

      //组装数据
      this.boxMap = this.buildArray(12, 12);
      this.readTree(this.flowData);
      this.setLine(this.boxMap);
      this.getMaxXY();
      this.showBoxMap();


    },
    beforeDestroy() {
      clearInterval(this.timer);
    },
    methods: {
      updateStatus() {
        //console.log("------update------");
        // let refList = this.$refs;
        // for (let key in refList) {
        //   console.log(key);
        //   // refList[key][0].status = !refList[key][0].status;
        // }

        //set eleStatus
        getFlowRealData({key: this.facility, platform: this.platform}).then(res => {
          //console.log(res);
          //this.tagValues = res.data;

          //set setRunStatus
          this.setRunStatus(res.data.runStatus);
          //console.log(this.pipeStatus);

          //set tagsValues
          this.setTagValues(res.data.tags);
          console.log(this.eleStatus);
        });
      },
      setRunStatus(v) {
        for (let el of v) {
          this.pipeStatus["P" + el.id] = el.run == 1 ? true : false;
          this.eleStatus["E" + el.id] = el.run == 1 ? true : false;
        }
      },
      setTagValues(v) {
        this.tagValues = v;
        // for (let tag of this.tagValues) {
        //   tag.val = Math.floor(Math.random() * (100 - 1)) + 1;
        // }
      },
      buildArray(l, n) {
        let a = new Array();
        for (let i = 0; i < l; i++) {
          a[i] = new Array();
          for (let j = 0; j < n; j++) {
            //a[i][j] = {id: i+"/"+j};
            a[i][j] = {id: null, pid: "", type: "", name: "", position: {row: i, col: j}};
          }
        }
        return a;
      },
      readTree(tree) {
        if (tree.list.length > 0) {
          for (let n of tree.list) {
            n.pid = tree.id;
            this.readTree(n);
          }
        } else {
          this.arrayIn.row += 2;
          this.arrayIn.col = 0;
        }

        Object.assign(this.boxMap[this.arrayIn.row][this.arrayIn.col], {
          id: tree.id,
          pid: tree.pid,
          type: tree.type,
          name: tree.name
        });

        this.setTag(this.arrayIn, tree.id);

        this.arrayIn.col += 2;
      },
      setTag(now, key) {
        if (key != "0" && key != "-1") {
          Object.assign(this.boxMap[now.row + 1][now.col], {
            id: '-1',
            type: "tag",
            pid: key
          });
        }
      },
      setLine() {
        for (let i = 0; i < this.boxMap.length; i++) {
          for (let j = 0; j < this.boxMap[i].length; j++) {
            let cell = this.boxMap[i][j];
            if (this.boxMap[i][j].pid) {
              let cWhere = {col: j, row: i};
              let pWhere = this.findWhereParent(cell.pid);
              if (pWhere != null) {
                //console.log("getPath");
                this.creatPathPoint(this.boxMap[i][j].id, cWhere, pWhere);
              }
            }
          }
        }
      },
      findWhereParent(key) {
        for (let i = 0; i < this.boxMap.length; i++) {
          for (let j = 0; j < this.boxMap[i].length; j++) {
            if (this.boxMap[i][j].id == key) {
              return {col: j, row: i}
            }
          }
        }
        return null;
      },
      creatPathPoint(key, start, end) {
        //console.log(start);
        //console.log(end);
        //同行
        if (start.row - end.row == 0) {
          for (let i = start.col + 1; i <= end.col; i++) {
            if (i != end.col) {
              Object.assign(this.boxMap[start.row][i], {id: -1, type: "pipe", name: "right"});
              this.boxMap[start.row][i].pid += "P" + key;
            }
          }
        } else {
          //非同行
          let h = end.row - start.row;
          //走横行
          for (let i = start.col + 1; i <= end.col; i++) {
            if (i != end.col) {
              Object.assign(this.boxMap[start.row][i], {id: -1, type: "pipe", name: "right"});
            } else {
              if (h > 0)
                Object.assign(this.boxMap[start.row][i], {id: -1, type: "pipe", name: "right_down"});
              else if (h < 0)
                Object.assign(this.boxMap[start.row][i], {id: -1, type: "pipe", name: "right_up"});
            }
            this.boxMap[start.row][i].pid += "P" + key;
          }

          //走竖行
          let v = start.row - end.row;
          //console.log(v);
          if (v < 0) {
            for (let i = start.row + 1; i < end.row; i++) {
              Object.assign(this.boxMap[i][end.col], {id: -1, type: "pipe", name: "down"});
              this.boxMap[i][end.col].pid += "P" + key;
            }
          } else {
            for (let i = start.row - 1; i > end.row; i++) {
              Object.assign(this.boxMap[i][end.col], {id: -1, type: "pipe", name: "up"});
              this.boxMap[i][end.col].pid += "P" + key;
            }
          }
        }

        //this.showBoxMap();
      },
      showBoxMap() {
        let maxX = 0, maxY = 0;
        for (let row of this.boxMap) {
          let a = "";
          for (let col of row) {
            if (col.type == "pipe") {
              // col.pid = col.pid.substring(0, col.pid.length - 1);
              a += col.name + "(" + col.pid + ")" + " # ";
            } else if (col.type == "tag") {
              a += "T" + col.pid + " # ";
            } else
              a += col.id + " # ";
          }
          console.log(a);
        }
      },
      isEmptyRow(row) {
        for (let col of row) {
          if (col.id != null)
            return true;
        }
        return false;
      },
      getFacTags(facKey) {
        let tags = [];
        for (let tag of this.tagValues) {
          //console.log(tag.pid + "########" + facKey);
          if (tag.pid == facKey) {
            tags.push(tag);
          }
        }
        return tags;
        console.log(tags);
      },
      getPipeStatus(pids) {
        let ids = pids.split("P");
        if (ids.length > 1) {
          let stat = false;
          for (let id of ids) {
            if (this.pipeStatus['P' + id] == true)
              stat = true;
          }
          return stat;
        } else
          return this.pipeStatus[pids];
      },
      getMaxXY() {
        for (let i = 11; i >= 0; i--) {
          if (this.isEmptyRow(this.boxMap[i])) {
            if (this.maxY == 0)
              this.maxY = i + 1;
          }
          for (let j = 11; j >= 0; j--) {
            if (this.boxMap[i][j].id != null) {
              if (this.maxX < j) {
                this.maxX = j;
              }
              break;
            }
          }
        }
        this.maxX += 1;
        // console.log(this.maxX);
        // console.log(this.maxY);
      }
    }
  }
</script>

<style lang="scss" scoped>
  $differ: 50px;

  .flow-panel {
    width: 100%;
    /*display: inline-grid;*/
    position: relative;
    padding: 10px;
    background-color: #fbfbfb;
    border: 2px solid #f7f5f5;

    .flow-title {
      font-size: 15px;
      color: #00BCD4;
      padding-left: 20px;
      border-bottom: 1px #efeeee solid;
      padding-bottom: 5px;
    }

    .flow-box {
      /*background-color: red;*/
      /*position: absolute;*/
      transform-origin: 0 0;
    }

    .element-line {
      /*width: 1800px;*/
      display: inline-flex;

      .element-point {
        width: 150px;
        /*height: 150px;*/
        float: left;
        /*border: 1px #e4e4e473 dashed;*/
        /*box-sizing: border-box;*/
      }
    }

    .flow-line {
      .element-point {
        height: 150px;
      }
    }

    .tag-line {
      .element-point {
        /*min-height: 50px !important;*/
        height: 150px - $differ;
      }
    }
  }
</style>
