<template>
  <div style="margin-top:10px;position: relative">
    <el-button type="warning" icon="el-icon-upload2" size="mini" @click="exportTable"
               style="position: absolute;right:0px;top:-30px;">导出表格
    </el-button>
    <el-table
      :data="tableData"
      style="width: 100%"
      class="completTable"
      max-height="600">
      <el-table-column
        type="index"
        align="center">
      </el-table-column>
      <el-table-column
        prop="town_name"
        align="center"
        width="90"
        label="镇街">
      </el-table-column>
      <el-table-column label="合计">
        <el-table-column
          align="center"
          label="考核名单任务量">
          <template slot-scope="scope">
            <p v-if="scope.row.town_name==='合计'">{{scope.row.in_total}}</p>
            <el-link type="primary" v-else class="isLinkStyle" @click="clickNum(13,'0',scope.row.town_code,scope.row)">{{
                scope.row.in_total
              }}
            </el-link>
          </template>
        </el-table-column>
        <el-table-column
          align="center"
          label="考核名单完成量">
          <template slot-scope="scope">
            <p v-if="scope.row.town_name==='合计'">{{scope.row.in_list}}</p>
            <el-link type="primary" v-else class="isLinkStyle" @click="clickNum(14,'0',scope.row.town_code,scope.row)">{{
                scope.row.in_list
              }}
            </el-link>
          </template>
        </el-table-column>
        <el-table-column
          prop="rate"
          align="center"
          label="完成率%">
        </el-table-column>
        <el-table-column
          align="center"
          label="考核名单未完成量">
          <template slot-scope="scope">
            <p v-if="scope.row.town_name==='合计'">{{scope.row.unfinish_cnt}}</p>
            <el-link type="primary" v-else class="isLinkStyle" @click="clickNum(15,'0',scope.row.town_code,scope.row)">{{
                scope.row.unfinish_cnt
              }}
            </el-link>
          </template>
        </el-table-column>
        <el-table-column
          align="center"
          label="非考核名单完成量">
          <template slot-scope="scope">
            <p v-if="scope.row.town_name==='合计'">{{scope.row.out_list}}</p>
            <el-link type="primary" v-else class="isLinkStyle" @click="clickNum(16,'0',scope.row.town_code,scope.row)">{{
                scope.row.out_list
              }}
            </el-link>
          </template>
        </el-table-column>
      </el-table-column>
      <!--      排水户-->
      <el-table-column label="排水户">
        <el-table-column
          align="center"
          label="考核名单任务量">
          <template slot-scope="scope">
            <p v-if="scope.row.town_name==='合计'">{{scope.row.in_total1012}}</p>
            <el-link type="primary" v-else class="isLinkStyle" @click="clickNum(13,'1012',scope.row.town_code,scope.row)">
              {{ scope.row.in_total1012 }}
            </el-link>
          </template>
        </el-table-column>
        <el-table-column
          align="center"
          label="考核名单完成量">
          <template slot-scope="scope">
            <p v-if="scope.row.town_name==='合计'">{{scope.row.in_list1012}}</p>
            <el-link type="primary" v-else class="isLinkStyle" @click="clickNum(14,'1012',scope.row.town_code,scope.row)">
              {{ scope.row.in_list1012 }}
            </el-link>
          </template>
        </el-table-column>
        <el-table-column
          prop="rate1012"
          align="center"
          label="完成率%">
        </el-table-column>
        <el-table-column
          align="center"
          label="考核名单未完成量">
          <template slot-scope="scope">
            <p v-if="scope.row.town_name==='合计'">{{scope.row.unfinish1012}}</p>
            <el-link type="primary" v-else class="isLinkStyle" @click="clickNum(15,'1012',scope.row.town_code,scope.row)">
              {{ scope.row.unfinish1012 }}
            </el-link>
          </template>
        </el-table-column>
        <el-table-column
          align="center"
          label="非考核名单完成量">
          <template slot-scope="scope">
            <p v-if="scope.row.town_name==='合计'">{{scope.row.out_list1012}}</p>
            <el-link type="primary" v-else class="isLinkStyle" @click="clickNum(16,'1012',scope.row.town_code,scope.row)">
              {{ scope.row.out_list1012 }}
            </el-link>
          </template>
        </el-table-column>
      </el-table-column>
      <!--      零星废水-->
      <el-table-column label="零星废水">
        <el-table-column
          align="center"
          label="考核名单任务量">
          <template slot-scope="scope">
            <p v-if="scope.row.town_name==='合计'">{{scope.row.in_total1003}}</p>
            <el-link type="primary" v-else class="isLinkStyle" @click="clickNum(13,'1003',scope.row.town_code,scope.row)">{{
                scope.row.in_total1003
              }}
            </el-link>
          </template>
        </el-table-column>
        <el-table-column
          align="center"
          label="考核名单完成量">
          <template slot-scope="scope">
            <p v-if="scope.row.town_name==='合计'">{{scope.row.in_list1003}}</p>
            <el-link type="primary" v-else class="isLinkStyle" @click="clickNum(14,'1003',scope.row.town_code,scope.row)">{{
                scope.row.in_list1003
              }}
            </el-link>
          </template>
        </el-table-column>
        <el-table-column
          prop="rate1003"
          align="center"
          label="完成率%">
        </el-table-column>
        <el-table-column
          align="center"
          label="考核名单未完成量">
          <template slot-scope="scope">
            <p v-if="scope.row.town_name==='合计'">{{scope.row.unfinish1003}}</p>
            <el-link type="primary" v-else class="isLinkStyle" @click="clickNum(15,'1003',scope.row.town_code,scope.row)">{{
                scope.row.unfinish1003
              }}
            </el-link>
          </template>
        </el-table-column>
        <el-table-column
          align="center"
          label="非考核名单完成量">
          <template slot-scope="scope">
            <p v-if="scope.row.town_name==='合计'">{{scope.row.out_list1003}}</p>
            <el-link type="primary" v-else class="isLinkStyle" @click="clickNum(16,'1003',scope.row.town_code,scope.row)">{{
                scope.row.out_list1003
              }}
            </el-link>
          </template>
        </el-table-column>
      </el-table-column>
      <!--      四大流域-->
      <el-table-column label="四大流域">
        <el-table-column
          align="center"
          label="考核名单任务量">
          <template slot-scope="scope">
            <p v-if="scope.row.town_name==='合计'">{{scope.row.in_total1002}}</p>
            <el-link type="primary" v-else class="isLinkStyle" @click="clickNum(13,'1002',scope.row.town_code,scope.row)">{{
                scope.row.in_total1002
              }}
            </el-link>
          </template>
        </el-table-column>
        <el-table-column
          align="center"
          label="考核名单完成量">
          <template slot-scope="scope">
            <p v-if="scope.row.town_name==='合计'">{{scope.row.in_list1002}}</p>
            <el-link type="primary" v-else class="isLinkStyle" @click="clickNum(14,'1002',scope.row.town_code,scope.row)">{{
                scope.row.in_list1002
              }}
            </el-link>
          </template>
        </el-table-column>
        <el-table-column
          prop="rate1002"
          align="center"
          label="完成率%">
        </el-table-column>
        <el-table-column
          align="center"
          label="考核名单未完成量">
          <template slot-scope="scope">
            <p v-if="scope.row.town_name==='合计'">{{scope.row.unfinish1002}}</p>
            <el-link type="primary" v-else class="isLinkStyle" @click="clickNum(15,'1002',scope.row.town_code,scope.row)">{{
                scope.row.unfinish1002
              }}
            </el-link>
          </template>
        </el-table-column>
        <el-table-column
          prop="out_list1002"
          align="center"
          label="非考核名单完成量">
          <template slot-scope="scope">
            <p v-if="scope.row.town_name==='合计'">{{scope.row.out_list1002}}</p>
            <el-link type="primary" v-else class="isLinkStyle" @click="clickNum(16,'1002',scope.row.town_code,scope.row)">{{
                scope.row.out_list1002
              }}
            </el-link>
          </template>
        </el-table-column>
      </el-table-column>
      <!--      造纸-->
      <el-table-column label="造纸">
        <el-table-column
          align="center"
          label="考核名单任务量">
          <template slot-scope="scope">
            <p v-if="scope.row.town_name==='合计'">{{scope.row.in_total1019}}</p>
            <el-link type="primary" v-else class="isLinkStyle" @click="clickNum(13,'1019',scope.row.town_code,scope.row)">{{
                scope.row.in_total1019
              }}
            </el-link>
          </template>
        </el-table-column>
        <el-table-column
          align="center"
          label="考核名单完成量">
          <template slot-scope="scope">
            <p v-if="scope.row.town_name==='合计'">{{scope.row.in_list1019}}</p>
            <el-link type="primary" v-else class="isLinkStyle" @click="clickNum(14,'1019',scope.row.town_code,scope.row)">{{
                scope.row.in_list1019
              }}
            </el-link>
          </template>
        </el-table-column>
        <el-table-column
          prop="rate1019"
          align="center"
          label="完成率%">
        </el-table-column>
        <el-table-column
          align="center"
          label="考核名单未完成量">
          <template slot-scope="scope">
            <p v-if="scope.row.town_name==='合计'">{{scope.row.unfinish1019}}</p>
            <el-link type="primary" v-else class="isLinkStyle" @click="clickNum(15,'1019',scope.row.town_code,scope.row)">{{
                scope.row.unfinish1019
              }}
            </el-link>
          </template>
        </el-table-column>
        <el-table-column
          align="center"
          label="非考核名单完成量">
          <template slot-scope="scope">
            <p v-if="scope.row.town_name==='合计'">{{scope.row.out_list1019}}</p>
            <el-link type="primary" v-else class="isLinkStyle" @click="clickNum(16,'1019',scope.row.town_code,scope.row)">{{
                scope.row.out_list1019
              }}
            </el-link>
          </template>
        </el-table-column>
      </el-table-column>
      <!--      金属-->
      <el-table-column label="金属表面">
        <el-table-column
          align="center"
          label="考核名单任务量">
          <template slot-scope="scope">
            <p v-if="scope.row.town_name==='合计'">{{scope.row.in_total1015}}</p>
            <el-link type="primary" v-else class="isLinkStyle" @click="clickNum(13,'1015',scope.row.town_code,scope.row)">{{
                scope.row.in_total1015
              }}
            </el-link>
          </template>
        </el-table-column>
        <el-table-column
          align="center"
          label="考核名单完成量">
          <template slot-scope="scope">
            <p v-if="scope.row.town_name==='合计'">{{scope.row.in_list1015}}</p>
            <el-link type="primary" v-else class="isLinkStyle" @click="clickNum(14,'1015',scope.row.town_code,scope.row)">{{
                scope.row.in_list1015
              }}
            </el-link>
          </template>
        </el-table-column>
        <el-table-column
          prop="rate1015"
          align="center"
          label="完成率%">
        </el-table-column>
        <el-table-column
          align="center"
          label="考核名单未完成量">
          <template slot-scope="scope">
            <p v-if="scope.row.town_name==='合计'">{{scope.row.unfinish1015}}</p>
            <el-link type="primary" v-else class="isLinkStyle" @click="clickNum(15,'1015',scope.row.town_code,scope.row)">{{
                scope.row.unfinish1015
              }}
            </el-link>
          </template>
        </el-table-column>
        <el-table-column
          align="center"
          label="非考核名单完成量">
          <template slot-scope="scope">
            <p v-if="scope.row.town_name==='合计'">{{scope.row.out_list1015}}</p>
            <el-link type="primary" v-else class="isLinkStyle" @click="clickNum(16,'1015',scope.row.town_code,scope.row)">{{
                scope.row.out_list1015
              }}
            </el-link>
          </template>
        </el-table-column>
      </el-table-column>
      <!--      一体化-->
      <el-table-column label="樟村一体化">
        <el-table-column
          align="center"
          label="考核名单任务量">
          <template slot-scope="scope">
            <p v-if="scope.row.town_name==='合计'">{{scope.row.in_total1016}}</p>
            <el-link type="primary" v-else class="isLinkStyle" @click="clickNum(13,'1016',scope.row.town_code,scope.row)">{{
                scope.row.in_total1016
              }}
            </el-link>
          </template>
        </el-table-column>
        <el-table-column
          align="center"
          label="考核名单完成量">
          <template slot-scope="scope">
            <p v-if="scope.row.town_name==='合计'">{{scope.row.in_list1016}}</p>
            <el-link type="primary" v-else class="isLinkStyle" @click="clickNum(14,'1016',scope.row.town_code,scope.row)">{{
                scope.row.in_list1016
              }}
            </el-link>
          </template>
        </el-table-column>
        <el-table-column
          prop="rate1016"
          align="center"
          label="完成率%">
        </el-table-column>
        <el-table-column
          align="center"
          label="考核名单未完成量">
          <template slot-scope="scope">
            <p v-if="scope.row.town_name==='合计'">{{scope.row.unfinish1016}}</p>
            <el-link type="primary" v-else class="isLinkStyle" @click="clickNum(15,'1016',scope.row.town_code,scope.row)">{{
                scope.row.unfinish1016
              }}
            </el-link>
          </template>
        </el-table-column>
        <el-table-column
          align="center"
          label="非考核名单完成量">
          <template slot-scope="scope">
            <p v-if="scope.row.town_name==='合计'">{{scope.row.out_list1016}}</p>
            <el-link type="primary" v-else class="isLinkStyle" @click="clickNum(16,'1016',scope.row.town_code,scope.row)">{{
                scope.row.out_list1016
              }}
            </el-link>
          </template>
        </el-table-column>
      </el-table-column>
      <!--      加油站-->
      <el-table-column label="加油站">
        <el-table-column
          align="center"
          label="考核名单任务量">
          <template slot-scope="scope">
            <p v-if="scope.row.town_name==='合计'">{{scope.row.in_total1014}}</p>
            <el-link type="primary" v-else class="isLinkStyle" @click="clickNum(13,'1014',scope.row.town_code,scope.row)">{{
                scope.row.in_total1014
              }}
            </el-link>
          </template>
        </el-table-column>
        <el-table-column
          align="center"
          label="考核名单完成量">
          <template slot-scope="scope">
            <p v-if="scope.row.town_name==='合计'">{{scope.row.in_list1014}}</p>
            <el-link type="primary" v-else class="isLinkStyle" @click="clickNum(14,'1014',scope.row.town_code,scope.row)">{{
                scope.row.in_list1014
              }}
            </el-link>
          </template>
        </el-table-column>
        <el-table-column
          prop="rate1014"
          align="center"
          label="完成率%">
        </el-table-column>
        <el-table-column
          align="center"
          label="考核名单未完成量">
          <template slot-scope="scope">
            <p v-if="scope.row.town_name==='合计'">{{scope.row.unfinish1014}}</p>
            <el-link type="primary" v-else class="isLinkStyle" @click="clickNum(15,'1014',scope.row.town_code,scope.row)">{{
                scope.row.unfinish1014
              }}
            </el-link>
          </template>
        </el-table-column>
        <el-table-column
          align="center"
          label="非考核名单完成量">
          <template slot-scope="scope">
            <p v-if="scope.row.town_name==='合计'">{{scope.row.out_list1014}}</p>
            <el-link type="primary" v-else class="isLinkStyle" @click="clickNum(16,'1014',scope.row.town_code,scope.row)">{{
                scope.row.out_list1014
              }}
            </el-link>
          </template>
        </el-table-column>
      </el-table-column>
      <!--      餐饮单位-->
      <el-table-column label="餐饮单位">
        <el-table-column
          align="center"
          label="考核名单任务量">
          <template slot-scope="scope">
            <p v-if="scope.row.town_name==='合计'">{{scope.row.in_total1006}}</p>
            <el-link type="primary" v-else class="isLinkStyle" @click="clickNum(13,'1006',scope.row.town_code,scope.row)">{{
                scope.row.in_total1006
              }}
            </el-link>
          </template>
        </el-table-column>
        <el-table-column
          align="center"
          label="考核名单完成量">
          <template slot-scope="scope">
            <p v-if="scope.row.town_name==='合计'">{{scope.row.in_list1006}}</p>
            <el-link type="primary" v-else class="isLinkStyle" @click="clickNum(14,'1006',scope.row.town_code,scope.row)">{{
                scope.row.in_list1006
              }}
            </el-link>
          </template>
        </el-table-column>
        <el-table-column
          prop="rate1006"
          align="center"
          label="完成率%">
        </el-table-column>
        <el-table-column
          align="center"
          label="考核名单未完成量">
          <template slot-scope="scope">
            <p v-if="scope.row.town_name==='合计'">{{scope.row.unfinish1006}}</p>
            <el-link type="primary" v-else class="isLinkStyle" @click="clickNum(15,'1006',scope.row.town_code,scope.row)">{{
                scope.row.unfinish1006
              }}
            </el-link>
          </template>
        </el-table-column>
        <el-table-column
          align="center"
          label="非考核名单完成量">
          <template slot-scope="scope">
            <p v-if="scope.row.town_name==='合计'">{{scope.row.out_list1006}}</p>
            <el-link type="primary" v-else class="isLinkStyle" @click="clickNum(16,'1006',scope.row.town_code,scope.row)">{{
                scope.row.out_list1006
              }}
            </el-link>
          </template>
        </el-table-column>
      </el-table-column>
      <!--      汽修-->
      <el-table-column label="汽修企业">
        <el-table-column
          align="center"
          label="考核名单任务量">
          <template slot-scope="scope">
            <p v-if="scope.row.town_name==='合计'">{{scope.row.in_total1013}}</p>
            <el-link type="primary" v-else class="isLinkStyle" @click="clickNum(13,'1013',scope.row.town_code,scope.row)">{{
                scope.row.in_total1013
              }}
            </el-link>
          </template>
        </el-table-column>
        <el-table-column
          align="center"
          label="考核名单完成量">
          <template slot-scope="scope">
            <p v-if="scope.row.town_name==='合计'">{{scope.row.in_list1013}}</p>
            <el-link type="primary" v-else class="isLinkStyle" @click="clickNum(14,'1013',scope.row.town_code,scope.row)">{{
                scope.row.in_list1013
              }}
            </el-link>
          </template>
        </el-table-column>
        <el-table-column
          prop="rate1013"
          align="center"
          label="完成率%">
        </el-table-column>
        <el-table-column
          align="center"
          label="考核名单未完成量">
          <template slot-scope="scope">
            <p v-if="scope.row.town_name==='合计'">{{scope.row.unfinish1013}}</p>
            <el-link type="primary" v-else class="isLinkStyle" @click="clickNum(15,'1013',scope.row.town_code,scope.row)">{{
                scope.row.unfinish1013
              }}
            </el-link>
          </template>
        </el-table-column>
        <el-table-column
          align="center"
          label="非考核名单完成量">
          <template slot-scope="scope">
            <p v-if="scope.row.town_name==='合计'">{{scope.row.out_list1013}}</p>
            <el-link type="primary" v-else class="isLinkStyle" @click="clickNum(16,'1013',scope.row.town_code,scope.row)">{{
                scope.row.out_list1013
              }}
            </el-link>
          </template>
        </el-table-column>
      </el-table-column>
      <el-table-column label="涉VOCs重点工业企业">
        <el-table-column
          align="center"
          label="考核名单任务量">
          <template slot-scope="scope">
            <p v-if="scope.row.town_name==='合计'">{{scope.row.in_total1004}}</p>
            <el-link type="primary" v-else class="isLinkStyle" @click="clickNum(13,'1004',scope.row.town_code,scope.row)">{{
                scope.row.in_total1004
              }}
            </el-link>
          </template>
        </el-table-column>
        <el-table-column
          align="center"
          label="考核名单完成量">
          <template slot-scope="scope">
            <p v-if="scope.row.town_name==='合计'">{{scope.row.in_list1004}}</p>
            <el-link type="primary" v-else class="isLinkStyle" @click="clickNum(14,'1004',scope.row.town_code,scope.row)">{{
                scope.row.in_list1004
              }}
            </el-link>
          </template>
        </el-table-column>
        <el-table-column
          prop="rate1004"
          align="center"
          label="完成率%">
        </el-table-column>
        <el-table-column
          align="center"
          label="考核名单未完成量">
          <template slot-scope="scope">
            <p v-if="scope.row.town_name==='合计'">{{scope.row.unfinish1004}}</p>
            <el-link type="primary" v-else class="isLinkStyle" @click="clickNum(15,'1004',scope.row.town_code,scope.row)">{{
                scope.row.unfinish1004
              }}
            </el-link>
          </template>
        </el-table-column>
        <el-table-column
          align="center"
          label="非考核名单完成量">
          <template slot-scope="scope">
            <p v-if="scope.row.town_name==='合计'">{{scope.row.out_list1004}}</p>
            <el-link type="primary" v-else class="isLinkStyle" @click="clickNum(16,'1004',scope.row.town_code,scope.row)">{{
                scope.row.out_list1004
              }}
            </el-link>
          </template>
        </el-table-column>
      </el-table-column>
      <el-table-column label="水重点排污">
        <el-table-column
          align="center"
          label="考核名单任务量">
          <template slot-scope="scope">
            <p v-if="scope.row.town_name==='合计'">{{scope.row.in_total1017}}</p>
            <el-link type="primary" v-else class="isLinkStyle" @click="clickNum(13,'1017',scope.row.town_code,scope.row)">{{
                scope.row.in_total1017
              }}
            </el-link>
          </template>
        </el-table-column>
        <el-table-column
          align="center"
          label="考核名单完成量">
          <template slot-scope="scope">
            <p v-if="scope.row.town_name==='合计'">{{scope.row.in_list1017}}</p>
            <el-link type="primary" v-else class="isLinkStyle" @click="clickNum(14,'1017',scope.row.town_code,scope.row)">{{
                scope.row.in_list1017
              }}
            </el-link>
          </template>
        </el-table-column>
        <el-table-column
          prop="rate1017"
          align="center"
          label="完成率%">
        </el-table-column>
        <el-table-column
          align="center"
          label="考核名单未完成量">
          <template slot-scope="scope">
            <p v-if="scope.row.town_name==='合计'">{{scope.row.unfinish1017}}</p>
            <el-link type="primary" v-else class="isLinkStyle" @click="clickNum(15,'1017',scope.row.town_code,scope.row)">{{
                scope.row.unfinish1017
              }}
            </el-link>
          </template>
        </el-table-column>
        <el-table-column
          prop="out_list1017"
          align="center"
          label="非考核名单完成量">
          <template slot-scope="scope">
            <p v-if="scope.row.town_name==='合计'">{{scope.row.out_list1017}}</p>
            <el-link type="primary" v-else class="isLinkStyle" @click="clickNum(16,'1017',scope.row.town_code,scope.row)">{{
                scope.row.out_list1017
              }}
            </el-link>
          </template>
        </el-table-column>
      </el-table-column>
    </el-table>
    <nSource :isBoxShow="isBoxShow" :values="value" @closeBox="isBoxShow = false" :showTitle="showTitle"></nSource>
  </div>
</template>

<script>
import {taskScoreAll, taskExportAll} from "@/api/analysis";
import nSource from "./source";

export default {
  components: {nSource},
  data () {
    return {
      tableData: [],
      // 分页
      page: {
        pageNum: 1,
        pageSize: 30
      },
      total: 0,
      isBoxShow: false,
      value: {},
      showTitle: ""
    };
  },
  created () {
    taskScoreAll().then(res => {
      if (res.code === 200) {
        this.tableData = res.data;
      }
    });
  },
  methods: {
    exportTable () {
      let _that = this;
      this.$confirm("是否导出数据?", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(function () {
          return taskExportAll();
        })
        .then((response) => {
          this.download(response.msg);
        })
        .catch(function () {
        });
    },
    clickNum (l, t, c, v) {
      let oVal = {
        listType: l,
        tagHeadId: null,
        townCode: null
      };
      let oType = {
        "13": "考核名单任务量",
        "14": "考核名单完成量",
        "15": "考核名单未完成量",
        "16": "非考核名单完成量"
      };
      let tagId = {
        "1012": "排水户",
        "1003": "零星废水",
        "1002": "四大流域",
        "1019": "造纸",
        "1015": "金属表面",
        "1016": "樟村一体化",
        "1014": "加油站",
        "1006": "餐饮单位",
        "1013": "汽修企业",
        "1004": "涉VOCs重点工业企业",
        "1017": "水重点污染"
      };
      let one = oType[l];
      let two = "";
      if (t !== "0") {
        oVal.tagHeadId = t;
        two = tagId[t];
      } else {
        two = "合计";
      }
      let townString = v.town_name === "合计" ? "所有镇街" : v.town_name;
      this.showTitle = `${one} (${two}) (${townString})`;
      if (c) oVal.townCode = c;
      this.value = oVal;
      this.isBoxShow = true;
    }
  }
};
</script>

<style lang="scss" scoped>
.completTable {
  background-color: transparent;
  margin-top: 5px;

  .isLinkStyle {
    color: #00ccff;
  }

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

  ///deep/ .el-table__body-wrapper {
  //  overflow: hidden;
  //
  //  &:hover {
  //    overflow: auto;
  //  }
  //}

  /deep/ .el-table__row {
    a.el-link {
      font-size: 12px;
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
</style>
