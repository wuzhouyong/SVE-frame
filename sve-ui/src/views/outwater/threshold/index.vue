<template>
  <div class="task-wk">
    <div class="warn-type-wk">
      <div class="warn-type-style">
        <div class="warn-type-name">
          <p>一级异常</p>
        </div>
        <ul class="warn-ul">
          <el-row>
            <el-col :lg="4" :md="4" :sm="4">
              <li class="borderLeft fW">异常类型</li>
            </el-col>
            <el-col :lg="18" :md="18" :sm="18">
              <li class="fW">判定条件</li>
            </el-col>
            <el-col :lg="2" :md="2" :sm="2">
              <li class="fW" style="text-align: center">操作</li>
            </el-col>
          </el-row>
          <el-row v-for="i in warnOneData.single" :key="i.id">
            <el-col :lg="4" :md="4" :sm="4">
              <li class="borderLeft">
                {{ i.warnDesc }}
              </li>
            </el-col>
            <el-col :lg="18" :md="18" :sm="18">
              <li>{{ i.changeDesc }}</li>
            </el-col>
            <el-col :lg="2" :md="2" :sm="2">
              <li style="text-align: center">
                <el-link type="primary" style="font-size:12px;" @click="editMes(i,true)">修改</el-link>
              </li>
            </el-col>
          </el-row>
          <el-row v-for="j in warnOneData.group" :key="j.id">
            <el-col :lg="4" :md="4" :sm="4">
              <li class="borderLeft">
                {{ j.warnDesc }}
              </li>
            </el-col>
            <el-col :lg="18" :md="18" :sm="18">
              <li>
                {{ j.changeDesc }}
              </li>
            </el-col>
            <el-col :lg="2" :md="2" :sm="2">
              <li style="text-align: center">
                <el-link type="primary" style="font-size:12px;" @click="editMes(j,false)">修改</el-link>
              </li>
            </el-col>
          </el-row>
        </ul>
      </div>
      <!--    二级异常-->
      <div class="warn-type-style">
        <div class="warn-type-name">
          <p>二级异常</p>
        </div>
        <ul class="warn-ul">
          <el-row>
            <el-col :lg="4" :md="4" :sm="4">
              <li class="borderLeft fW">异常类型</li>
            </el-col>
            <el-col :lg="18" :md="18" :sm="18">
              <li class="fW">判定条件</li>
            </el-col>
            <el-col :lg="2" :md="2" :sm="2">
              <li class="fW" style="text-align: center">操作</li>
            </el-col>
          </el-row>
          <el-row v-for="i in warnTwoData.single" :key="i.id">
            <el-col :lg="4" :md="4" :sm="4">
              <li class="borderLeft">
                {{ i.warnDesc }}
              </li>
            </el-col>
            <el-col :lg="18" :md="18" :sm="18">
              <li>{{ i.changeDesc }}</li>
            </el-col>
            <el-col :lg="2" :md="2" :sm="2">
              <li style="text-align: center">
                <el-link type="primary" style="font-size:12px;" @click="editMes(i,true)">修改</el-link>
              </li>
            </el-col>
          </el-row>
          <el-row v-for="j in warnTwoData.group" :key="j.id">
            <el-col :lg="4" :md="4" :sm="4">
              <li class="borderLeft">
                {{ j.warnDesc }}
              </li>
            </el-col>
            <el-col :lg="18" :md="18" :sm="18">
              <li>
                {{ j.changeDesc }}
              </li>
            </el-col>
            <el-col :lg="2" :md="2" :sm="2">
              <li style="text-align: center">
                <el-link type="primary" style="font-size:12px;" @click="editMes(j,false)">修改</el-link>
              </li>
            </el-col>
          </el-row>
        </ul>
      </div>
      <!--    三级异常-->
      <div class="warn-type-style">
        <div class="warn-type-name">
          <p>三级异常</p>
        </div>
        <ul class="warn-ul">
          <el-row>
            <el-col :lg="4" :md="4" :sm="4">
              <li class="borderLeft fW">异常类型</li>
            </el-col>
            <el-col :lg="18" :md="18" :sm="18">
              <li class="fW">判定条件</li>
            </el-col>
            <el-col :lg="2" :md="2" :sm="2">
              <li class="fW" style="text-align: center">操作</li>
            </el-col>
          </el-row>
          <el-row v-for="i in warnThreeData.single" :key="i.id">
            <el-col :lg="4" :md="4" :sm="4">
              <li class="borderLeft">
                {{ i.warnDesc }}
              </li>
            </el-col>
            <el-col :lg="18" :md="18" :sm="18">
              <li>{{ i.changeDesc }}</li>
            </el-col>
            <el-col :lg="2" :md="2" :sm="2">
              <li style="text-align: center">
                <el-link type="primary" style="font-size:12px;" @click="editMes(i,true)">修改</el-link>
              </li>
            </el-col>
          </el-row>
          <el-row v-for="j in warnThreeData.group" :key="j.id">
            <el-col :lg="4" :md="4" :sm="4">
              <li class="borderLeft">
                {{ j.warnDesc }}
              </li>
            </el-col>
            <el-col :lg="18" :md="18" :sm="18">
              <li>
                {{ j.changeDesc }}
              </li>
            </el-col>
            <el-col :lg="2" :md="2" :sm="2">
              <li style="text-align: center">
                <el-link type="primary" style="font-size:12px;" @click="editMes(j,false)">修改</el-link>
              </li>
            </el-col>
          </el-row>
        </ul>
      </div>
    </div>
    <el-dialog
      title="修改异常阈值条件"
      :visible.sync="visible"
      :close-on-click-modal="false"
      width="30%">
      <n-Form :oData="ClickData" ref="nFormRef" @successMes="successMes" v-if="isSingle&&visible"></n-Form>
      <n-Group v-if="!isSingle&&visible" :oData="ClickData" ref="nGroupRef" @successMes="successMes"></n-Group>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="isSingle?$refs.nFormRef.submit():$refs.nGroupRef.submit()">提 交</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { pshBoundaryList } from "@/api/outwater"
import nForm from "./nForm"
import nGroup from "./nGroup"

export default {
  components: { nForm, nGroup },
  data() {
    return {
      warnOneData: [],
      warnTwoData: [],
      warnThreeData: [],
      warnType: {
        "122": {
          typeDesc: "水质严重异常",
          level: 1
        },
        "222": {
          typeDesc: "水质异常",
          level: 2
        },
        "322": {
          typeDesc: "水质轻微异常",
          level: 3
        },
        "250": {
          typeDesc: "数据严重缺失",
          level: 2
        },
        "350": {
          typeDesc: "数据缺失",
          level: 3
        },
        "140": {
          typeDesc: "设备异常",
          level: 1
        },
        "340": {
          typeDesc: "其他异常",
          level: 3
        }
      },
      terms: ["大于上限", "小于下限", "同时大于下限小于上限", "小于下限或大于上限"],
      visible: false,
      ClickData: {},
      isSingle: true
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      pshBoundaryList().then(res => {
        this.warnOneData = this.formatData(res.data.one);
        this.warnTwoData = this.formatData(res.data.two);
        this.warnThreeData = this.formatData(res.data.three);
      })
    },
    format(item) {
      return item.termsTemp
        .replace("{minValue}", item.minValue)
        .replace("{maxValue}", item.maxValue)
    },
    formatData(v) {
      if (v.group && v.group.length > 0) {
        v.group.forEach(i => {
          let allDesc = "";
          i.list.forEach((o, inx) => {
            o.changeDesc = this.format(o);
            if (inx === 1) {
              allDesc += ",且" + this.format(o);
            } else {
              allDesc += this.format(o);
            }
          })
          i.changeDesc = allDesc;
        })
      }
      if (v.single && v.single.length > 0) {
        v.single.forEach(j => {
          j.changeDesc = this.format(j);
        })
      }
      return v;
    },
    editMes(v, b) {
      this.visible = true;
      this.isSingle = b;
      this.ClickData = v;
    },
    successMes() {
      this.getList();
      this.visible = false;
    }
  }
}
</script>

<style lang="scss" scoped>
.warn-type-style {
  margin-bottom: 20px;

  .warn-type-name {
    width: 100%;
    border: 1px solid #DDD;
    margin-bottom: 5px;

    p {
      margin: 0;
      width: 200px;
      font-size: 15px;
      font-weight: bold;
      padding: 5px 0;
      text-align: center;
      border-right: 1px solid #ddd;
      background-color: #1AAB8A;
      color: #fff;
      letter-spacing: 4px;
    }
  }

  .warn-ul {
    padding: 0px;
    margin: 0px;

    li {
      list-style-type: none;
      font-size: 14px;
      border: 1px solid #ddd;
      border-left: none;
      padding: 8px 10px;
      letter-spacing: 1px;
    }

    li.borderLeft {
      border-left: 1px solid #ddd;
    }
  }
}

.warn-type-wk {
  width: 100%;
  height: 100%;
  overflow-y: auto;
}

.fW {
  font-weight: bold;
  background-color: #f8f8f9;
}
</style>
