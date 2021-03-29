<template>
  <div class="workCondit-wk">
    <div class="workCondit-left">
      <SourceLeft @selectSource="selectSource" tagHeadId="1004"></SourceLeft>
    </div>
    <div class="workCondit-right">
      <div class="real-workCondit">
        <div class="real-work-con">
          <ul v-if="governList.length>0">
            <li v-for="(p,index) in governList" :key="p.facilityId">
              <el-divider content-position="left">{{ p.name }}</el-divider>
              <div class="wCondit-tech">
                <!-- 生产线 -->
                <div class="wCondit-production" v-if="p.line.length>0">
                  <div class="db-out"></div>
                  <div class="db-contain">
                    <template>
                      <div class="wCondit-pro-wk" v-for="pro in p.line" :key="p.id+'_'+pro.id+'-'+index">
                        <div class="wCondit-pro-right">
                          <h4 :title="pro.name">{{ pro.name }}</h4>
                          <p>
                            <span>用电量：</span>
                            <span>{{ pro.e300 || "-" }}</span>
                          </p>
                          <p>
                            <span>电功率：</span>
                            <span>{{ pro.e301 || "-" }}</span>
                          </p>
                        </div>
                      </div>
                    </template>
                  </div>
                  <!--                  <div style="font-size:13px;padding:5px;" v-else>暂无关联生产线</div>-->
                </div>

                <div class="wCondit-line" v-if="p.gather.length>0 && p.line.length>0"></div>

                <!-- 收集风机 -->
                <div class="wCondit-discharge" v-if="p.gather.length>0">
                  <template>
                    <div
                      class="wCondit-dis-wk"
                      :title="g.status?'风机状态:开启':'风机状态:关闭'"
                      v-for="(g,i) in p.gather"
                      :key="p.id+'_'+g.id+'-'+index+'-'+i"
                    >
                      <div class="voc-fans" :class="{'stop':!g.status}"></div>
                      <h4 :title="g.name">{{ g.name }}</h4>
                    </div>
                  </template>
                </div>

                <div class="wCondit-line" v-if="p.line.length>0&&p.tech.length>0"></div>

                <!-- 治理设施 -->
                <div class="wCondit-facilities">
                  <div class="wCondit-facil">
                    <div class="wCondit-fac-wk" v-for="t in p.tech" :key="p.id+'-'+t.id+'-'+index">
                      <el-card class="box-card" :class="{'stop':!t.status}" shadow="hover" v-if="t.name==='UV光解'">
                        <div class="fac-top">
                          <img :src="t.status?uvopen:uvclose"/>
                          <span>{{ t.status ? "开启" : "关闭" }}</span>
                        </div>
                        <p>{{ t.name }}</p>
                      </el-card>
                      <el-card class="box-card" shadow="hover" :class="{'stop':!t.status}" v-else-if="t.name==='活性炭吸附'">
                        <div class="fac-top">
                          <img :src="t.status?xfopen:xfclose"/>
                          <span>{{ t.status ? t.amount + "KPa" : "关闭" }}</span>
                        </div>
                        <p>{{ t.name }}</p>
                      </el-card>
                      <el-card class="box-card" shadow="hover" :class="{'stop':!t.status}" v-else-if="t.name==='催化燃烧'">
                        <div class="fac-top">
                          <img :src="t.status?huoopen:huoclose"/>
                          <span>{{ t.amount ? t.amount + "℃" : "关闭" }}</span>
                        </div>
                        <p>{{ t.name }}</p>
                      </el-card>
                      <el-card class="box-card" shadow="hover" :class="{'stop':!t.status}" v-else-if="t.name==='喷淋'">
                        <div class="fac-top">
                          <img :src="t.status?plopen:plclose"/>
                          <span>{{ t.status ? "开启" : "关闭" }}</span>
                        </div>
                        <p>{{ t.name }}</p>
                      </el-card>
                      <el-card class="box-card" shadow="hover" :class="{'stop':!t.status}" v-else-if="t.name==='等离子'">
                        <div class="fac-top">
                          <img :src="t.status?dlzopen:dlzclose"/>
                          <span>{{ t.status ? "开启" : "关闭" }}</span>
                        </div>
                        <p>{{ t.name }}</p>
                      </el-card>
                      <el-card class="box-card" shadow="hover" :class="{'stop':!t.status}" v-else-if="t.name==='静电吸附'">
                        <div class="fac-top">
                          <img :src="t.status?jdxfopen:jdxfclose"/>
                          <span>{{ t.status ? "开启" : "关闭" }}</span>
                        </div>
                        <p>{{ t.name }}</p>
                      </el-card>
                      <el-card class="box-card" shadow="hover" :class="{'stop':!t.status}" v-else-if="t.name==='冷凝回收'">
                        <div class="fac-top">
                          <img :src="t.status?lnhsopen:lnhsclose"/>
                          <span>{{ t.status ? "开启" : "关闭" }}</span>
                        </div>
                        <p>{{ t.name }}</p>
                      </el-card>
                      <el-card class="box-card" shadow="hover" :class="{'stop':!t.amount}" v-else>
                        <div class="fac-top">
                          <img :src="t.status?tsopen:tsclose"/>
                          <span>{{ t.amount ? t.amount : "关闭" }}</span>
                        </div>
                        <p>{{ t.name }}</p>
                      </el-card>
                    </div>
                  </div>
                  <!-- <h4>VOCs治理设施#1</h4> -->
                </div>

                <div class="wCondit-line" v-if="p.discharge.length>0"></div>

                <!-- 排放风机 -->
                <div class="wCondit-discharge" v-if="p.discharge.length>0">
                  <template>
                    <div
                      class="wCondit-dis-wk"
                      :title="d.status?'(风机状态:开启)':'(风机状态:关闭)'"
                      v-for="(d,i) in p.discharge"
                      :key="p.id+'-'+d.id+'-'+i"
                    >
                      <div class="voc-fans" :class="{'stop':!d.status}"></div>
                      <h4 :title="d.name">{{ d.name }}</h4>
                    </div>
                  </template>
                </div>

                <div class="wCondit-line" v-if="p.outlet.length>0"></div>
                <!-- 排放口 -->
                <div class="wCondit-port" v-if="p.outlet.length>0">
                  <div class="smoke-out"></div>
                  <div class="smoke-contain">
                    <template>
                      <div class="wCondit-port-wk" v-for="o in p.outlet" :key="o.id+'-'+index">
                        <!--                      <el-card class="box-card wFlex" shadow="hover">-->
                        <div class="wCondit-prot-right">
                          <h4 :title="o.name">{{ o.name }}</h4>
                          <p>
                            <span>浓度:</span>
                            <span :title='(o.g290 !== undefined && o.g290 !== null) ? o.g290 + "mg/m³" : "-"'>{{
                                (o.g290 !== undefined && o.g290 !== null) ? o.g290 + "mg/m³" : "-"
                              }}</span>
                          </p>
                          <p>
                            <span>温度:</span>
                            <span :title='(o.g280 !== undefined && o.g280 !== null) ? o.g280 + "℃" : "-"'>{{
                                (o.g280 !== undefined && o.g280 !== null) ? o.g280 + "℃" : "-"
                              }}</span>
                          </p>
                          <p>
                            <span>压力:</span>
                            <span :title='(o.g282 !== undefined && o.g282 !== null) ? o.g282 + "KPa" : "-"'>{{
                                (o.g282 !== undefined && o.g282 !== null) ? o.g282 + "KPa" : "-"
                              }}</span>
                          </p>
                          <p>
                            <span>流速:</span>
                            <span :title='(o.g201 !== undefined && o.g201 !== null) ? o.g201 + "m/s" : "-"'>{{
                                (o.g201 !== undefined && o.g201 !== null) ? o.g201 + "m/s" : "-"
                              }}</span>
                          </p>
                        </div>
                        <!--                        <img src="../../../assets/image/yan.png"/>-->
                      </div>
                      <!--                      </el-card>-->
                    </template>
                  </div>
                  <!--                  <div style="font-size:13px;padding:5px;text-align:center" v-else>暂无关联排放口</div>-->
                </div>
              </div>
            </li>
          </ul>
          <div
            v-else
            style="width:100%;height:100%;display:flex;align-item:center;align-items: center;
    justify-content: center;font-size:14px;color:#999"
          >无工况图数据.
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import SourceLeft from "@/components/sourceLeft";
import {workingCondition} from "@/api/voc";

export default {
  components: {SourceLeft},
  data () {
    return {
      governList: [],
      uvopen: require("../../../assets/autoFlow/voc_uvgj_on.png"),//uv光解
      uvclose: require("../../../assets/autoFlow/voc_uvgj.png"),
      xfopen: require("../../../assets/autoFlow/voc_hxt_on.png"),//活性炭吸附
      xfclose: require("../../../assets/autoFlow/voc_hxt.png"),
      huoopen: require("../../../assets/autoFlow/voc_chrs_on.png"),//催化燃烧
      huoclose: require("../../../assets/autoFlow/voc_chrs.png"),
      plopen: require("../../../assets/autoFlow/voc_pl_on.png"),//喷淋
      plclose: require("../../../assets/autoFlow/voc_pl.png"),
      tsopen: require("../../../assets/image/ts_open.png"),//其他
      tsclose: require("../../../assets/image/ts_close.png"),
      dlzopen: require("../../../assets/autoFlow/voc_dlz_on.png"),//等离子
      dlzclose: require("../../../assets/autoFlow/voc_dlz.png"),
      jdxfopen: require("../../../assets/autoFlow/voc_jdxf_on.png"),//静电吸附
      jdxfclose: require("../../../assets/autoFlow/voc_jdxf.png"),
      lnhsopen: require("../../../assets/autoFlow/voc_lnhs_on.png"),//冷凝回收
      lnhsclose: require("../../../assets/autoFlow/voc_lnhs.png"),
    };
  },
  methods: {
    selectSource (v) {
      workingCondition({sourceId: v.sourceId}).then((res) => {
        if (res.code === 200) {
          this.governList = res.data;
        }
      });
    },
    isMove (v) {
      return !(v.some(i => i.status === true));
    }
  }
};
</script>
<style lang="scss" scoped>

// 排放口
.wCondit-port {
  padding: 10px;
  width: 153px;
  height: auto;
  overflow: hidden;
  border: 1px solid transparent;
  background: linear-gradient(white, white) padding-box, repeating-linear-gradient(-45deg, #42b983 0, #42b983 25%, white 0, white 50%) 0/.6em .6em;
  animation: ants 12s linear infinite;
  display: flex;
  flex-direction: column;

  .smoke-out {
    margin-top: 5px;
    height: 85px;
    width: 120px;
    background: url("../../../assets/autoFlow/soot_out.gif") no-repeat top center;
    background-size: 120px 172px;
  }

  .smoke-contain {
    height: auto;
    max-height: 266px;
    overflow-y: auto;
    overflow-x: hidden;
    margin-top: 5px;
  }


  .wCondit-port-wk {
    font-size: 12px;
    //width: 153px;
    border-radius: .8em;
    padding: 3px 8px;
    margin: 5px;
    background: #00bcd417;
    font: 100%/1.5 sans-serif;


    .el-card__body {
      display: flex;
      padding: 3px;
      align-items: center;

      img {
        height: 48px;
        width: 48px;
      }
    }

    .wCondit-prot-right {
      flex: 1;
      padding: 5px;
      padding-bottom: 0;

      h4 {
        font-size: 12px;
        width: 95px;
        overflow: hidden;
        white-space: nowrap;
        text-overflow: ellipsis;
      }

      p {
        display: flex;
        overflow: hidden;

        span:first-child {
          color: #00bcd4;
          width: 30px;
        }

        span:nth-child(2) {
          color: #03A9F4;
          flex: 1;
          overflow: hidden;
          white-space: nowrap;
          text-overflow: ellipsis;
        }
      }
    }

    &:last-child {
      margin-bottom: 0px;
    }

    h4,
    p,
    span {
      font-size: 12px;
      margin: 0;
      margin-bottom: 3px;
    }
  }
}

// 生产线
.wCondit-production {
  padding: 5px;
  width: 162px;
  height: auto;
  overflow: hidden;
  border: 1px solid transparent;
  background: linear-gradient(white, white) padding-box, repeating-linear-gradient(-45deg, #42b983 0, #42b983 25%, white 0, white 50%) 0/.6em .6em;
  animation: ants 12s linear infinite;
  display: flex;
  flex-direction: column;

  .db-out {
    margin-top: 5px;
    height: 85px;
    width: 100%;
    background: url("../../../assets/autoFlow/db.png") no-repeat center center;
    background-size: 55% 88%;
  }

  .db-contain {
    width: 100%;
    height: auto;
    max-height: 237px;
    overflow-y: auto;
    overflow-x: hidden;
    //margin-top: 5px;

    .wCondit-pro-right {
      p {
        display: flex;
        overflow: hidden;

        span:first-child {
          color: #00bcd4;
          width: 48px;
        }

        span:nth-child(2) {
          color: #03A9F4;
          flex: 1;
          overflow: hidden;
          white-space: nowrap;
          text-overflow: ellipsis;
        }
      }
    }
  }

  .wCondit-pro-wk {
    font-size: 12px;
    width: 140px;
    border-radius: .8em;
    padding: 3px 8px;
    margin: 5px;
    background: #00bcd417;
    font: 100%/1.5 sans-serif;
    // padding: 10px 5px 3px;
    // border: 1px solid #ddd;
    .el-card__body {
      display: flex;
      padding: 3px;
      align-items: center;

      img {
        height: 32px;
      }

      .wCondit-pro-right {
        flex: 1;
        padding-left: 5px;
        border-left: 1px solid #e6ebf5;
      }
    }

    cursor: pointer;
    margin-bottom: 5px;

    &:last-child {
      margin-bottom: 0px;
    }

    p,
    span {
      font-size: 12px;
      margin: 0;
      margin-bottom: 3px;
    }

    p {
      &:last-child {
        margin-bottom: 0;
      }
    }

    h4 {
      font-size: 12px;
      margin: 0;
      margin-bottom: 5px;

      i {
        padding-right: 5px;
      }
    }
  }
}

//风机
.voc-fans {
  height: 69px;
  width: 60px;
  background-size: 60px 139px;
  margin: 0 auto;
  background-image: url("../../../assets/autoFlow/fan.gif");
  background-position: top center;

  &.stop {
    background-position: bottom center !important;
  }
}

.workCondit-wk {
  width: 100%;
  height: 100%;
  overflow: hidden;
  display: flex;


  .workCondit-left {
    width: 300px;
    height: 100%;
  }

  .workCondit-right {
    flex: 1;
    height: 100%;
    //min-width: 800px;
    padding: 10px;
    overflow: hidden;


    .real-workCondit {
      width: 100%;
      height: 100%;
      overflow: auto;

      .real-work-con {
        width: 100%;
        //min-width: 1340px;
        height: 100%;


        ul {
          padding: 0;
          margin: 0 auto;
          overflow: hidden;
          width: 1300px;

          .el-divider--horizontal {
            width: 100%;
          }

          li {
            list-style-type: none;
            margin-bottom: 10px;
            margin-top: 10px;
            display: flex;
            flex-direction: column;
            overflow: hidden;
            width: 1300px;
            overflow: hidden;

            &:last-child {
              margin-bottom: 0px;
            }

            .wCondit-tech {
              width: 1300px;
              overflow-x: auto;
              display: flex;
              align-items: center;
              //justify-content: center;

              .el-card__body {
                padding: 8px;
              }
            }

            .wCondit-line {
              width: 80px;
              height: 10px;
              background-image: url("../../../assets/autoFlow/pipe_right.gif");
              background-color: #42b983;

              &.stop {
                background-image: none !important;
                background-color: #9e9e9e !important;
              }
            }

            // 排放风机
            .wCondit-discharge {
              padding: 2px;
              width: 100px;
              height: auto;
              max-height: 312px;
              overflow: hidden;
              overflow-y: auto;
              border: 1px solid transparent;
              background: linear-gradient(white, white) padding-box, repeating-linear-gradient(-45deg, #42b983 0, #42b983 25%, white 0, white 50%) 0/.6em .6em;
              animation: ants 12s linear infinite;

              .wCondit-dis-wk {
                font-size: 12px;
                //flex: 1 1 81px; /*  flex-grow: 1 ，表示自动延展到最大宽度 */
                //flex: 0 1 81px;
                margin: 5px;
                cursor: pointer;
                display: inline-block;

                h4 {
                  width: 80px;
                  font-size: 12px;
                  margin: 5px 0;
                  white-space: nowrap;
                  overflow: hidden;
                  text-overflow: ellipsis;
                  text-align: center;
                }

                .el-card__body {
                  display: flex;
                  flex-direction: column;
                  align-items: center;
                  justify-content: center;
                }
              }
            }


            //治理设施
            .wCondit-facilities {
              flex: 1;
              max-width: 422px;
              overflow: hidden;
              border: 1px solid transparent;
              background: linear-gradient(white, white) padding-box, repeating-linear-gradient(-45deg, #42b983 0, #42b983 25%, white 0, white 50%) 0/.6em .6em;
              animation: ants 12s linear infinite;
              padding: 5px;

              h4 {
                margin: 0;
                text-align: center;
                font-size: 13px;
              }

              .wCondit-facil {
                padding: 5px;
                width: 100%;
                flex-wrap: wrap;
                display: flex;
                justify-content: flex-start;

                .wCondit-fac-wk {
                  font-size: 12px;
                  cursor: pointer;
                  height: 100px;
                  width: 90px;
                  margin: 0 5px 5px;

                  .el-card {
                    height: 100%;
                    border-color: #74cfa6;

                    &.stop {
                      border-color: #e6ebf5 !important;
                    }
                  }

                  .el-card__body {
                    display: flex;
                    flex-direction: column;
                    align-items: center;
                    height: 100%;
                    padding: 3px;
                  }

                  .fac-top {
                    display: flex;
                    align-items: center;
                    justify-content: center;
                    flex-direction: column;
                    flex: 1;
                    width: 100%;

                    img {
                      width: 40px;
                      height: auto;
                    }

                    span {
                      margin-top: 10px;
                    }
                  }

                  p {
                    width: 100%;
                    margin: 0;
                    text-align: center;
                    // margin-top: 5px;
                    height: 20px;
                    line-height: 20px;
                  }
                }
              }
            }
          }
        }
      }
    }
  }
}

.n_animation_picture {
  text-align: center;
}

@-webkit-keyframes rotation {
  from {
    -webkit-transform: rotate(0deg);
  }
  to {
    -webkit-transform: rotate(360deg);
  }
}

.n_picture_adm {
  -webkit-transform: rotate(360deg);
  animation: rotation 0.8s linear infinite;
  -moz-animation: rotation 0.8s linear infinite;
  -webkit-animation: rotation 0.8s linear infinite;
  -o-animation: rotation 0.8s linear infinite;
}

.smoke-contain, .db-contain, .wCondit-discharge {
  &::-webkit-scrollbar {
    width: 4px !important;
  }

  &::-webkit-scrollbar-thumb {
    border-radius: 10px;
    -webkit-box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.2);
    background: rgba(53, 195, 61, 0.3);
  }

  &::-webkit-scrollbar-track {
    -webkit-box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.2);
    border-radius: 0;
    background: rgba(0, 0, 0, 0.1);
  }
}
</style>
