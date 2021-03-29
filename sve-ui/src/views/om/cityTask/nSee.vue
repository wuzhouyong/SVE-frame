<template>
  <div>
    <el-divider content-position="left">严重超标情况明细</el-divider>
    <el-form label-width="110px" class="show-form">
      <el-row :gutter="15">
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="企业名称：">
            <p class="oTextHidden" :title="sData.factoryName">{{ sData.factoryName }}</p>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="监控点：">
            <p class="oTextHidden" :title="sData.checkpointName">{{ sData.checkpointName }}</p>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="监测因子：">
            <p class="oTextHidden" :title="sData.elementName">{{ sData.elementName }}</p>
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="镇街：">
            {{ sData.townName }}
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="超标天数：">
            {{ sData.overDays }}
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="排放浓度：">
            {{ sData.amount }}
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="排放标准：">
            {{ sData.standardval }}
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="超标倍数：">
            {{ sData.overTimes }}
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="监测时间：">
            {{ sData.monitorTime }}
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="任务状态：">
            市局待核实
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="开始时间：">
            {{ sData.startDate }}
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="结束时间：">
            {{ sData.endDate }}
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <el-divider content-position="left">超标核实反馈</el-divider>
    <el-form label-width="110px" class="show-form">
      <el-row :gutter="15">
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="处理人">
            {{ sData.handleby }}
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="联系方式">
            {{ sData.contactPhone }}
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="处理时间" prop="handleTime">
            {{ sData.handleTime }}
          </el-form-item>
        </el-col>
        <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="超标是否属实" prop="istrue">
            {{ sData.istrue === 0 ? "否" : sData.istrue === 1 ? "是" : "-" }}
          </el-form-item>
        </el-col>
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="超标原因" prop="reason">
            {{ sData.reason }}
          </el-form-item>
        </el-col>
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="整改情况" prop="recityinfo">
            {{ sData.recityinfo }}
          </el-form-item>
        </el-col>
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="查处情况" prop="situation">
            {{ sData.situation }}
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <el-divider content-position="left">附件列表</el-divider>
    <el-table :data="fileList" stripe style="width: 100%" max-height="300px" border>
      <el-table-column prop="showName" label="附件名" show-overflow-tooltip></el-table-column>
      <el-table-column prop="address" label="操作" width="100" align="center">
        <template slot-scope="scope">
          <el-link
            type="primary"
            style="font-size:12px;"
            :underline="false"
            target="_blank"
            :href="getlink(scope.row.Id)"
          >下载
          </el-link>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import { fileList } from "@/api/upload";
import { dgomCityHandle } from "@/api/om/task"

export default {
  props: {
    sData: {
      type: Object,
      default: () => {}
    }
  },
  data() {
    return {
      fileList: [],
      statusType: ["", "草稿", "分局待处理", "超时未处理", "市局待核实", "市局退回", "已完成"]
    }
  },
  watch: {
    "sData.suptaskId": {
      handler(v) {
        if (v) this.getList(v);
      },
      immediate: true
    }
  },
  methods: {
    getList(v) {
      let ref = {
        refId: v,
        refType: "dgom_branch_task"
      };
      fileList(ref).then((res) => {
        if (res.code === 200) {
          this.fileList = res.data;
        }
      });
    },
    cityHandle(v) {
      let oV = [{ suptaskId: this.sData.suptaskId, taskStatus: v }];
      dgomCityHandle(oV).then(res => {
        if (res.code === 200) {
          this.msgSuccess("任务审核完成");
          this.$emit("completed");
        }
      })
    },
    getlink(ids) {
      return `${process.env.VUE_APP_BASE_API}/com/getfile/${ids}`;
    }
  }
}
</script>

<style lang="scss" scoped>
p.oTextHidden {
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  margin: 0;
  padding: 0;
}

.show-form {
  /deep/ .el-form-item {
    margin-bottom: 5px;
  }
}
</style>
