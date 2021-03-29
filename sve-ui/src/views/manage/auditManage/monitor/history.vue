<template>
  <div class="history-style">
    <el-form label-width="80px">
      <el-form-item label="企业名称">
        {{ clickData.sourceName }}
      </el-form-item>
      <el-form-item label="监控任务">
        {{ clickData.tagHeadName }}
      </el-form-item>
    </el-form>
    <el-card class="box-card" v-for="i in historyData" :key="i.return_id" style="margin-bottom:5px;">
      <el-form label-width="80px">
        <el-form-item label="审核结果">
          {{ i.return_result === 1 ? "验收通过" : "验收退回" }}
        </el-form-item>
        <template v-if="i.return_result===0">
          <el-form-item label="审核人">
            {{ i.contact_name }}
          </el-form-item>
          <el-form-item label="联系电话">
            {{ i.contact_phone }}
          </el-form-item>
          <el-form-item label="退回原因">
            {{ i.return_reason }}
          </el-form-item>
          <el-form-item label="备注">
            {{ i.memo }}
          </el-form-item>
        </template>
        <template v-else>
          <el-form-item label="操作人">
            {{ i.created_by }}
          </el-form-item>
          <el-form-item label="操作时间">
            {{ i.date_created }}
          </el-form-item>
        </template>
      </el-form>
    </el-card>
  </div>
</template>

<script>
export default {
  props: {
    historyData: {
      type: Array,
      default: () => []
    },
    clickData: {
      type: Object
    }
  }
};
</script>

<style lang="scss" scoped>
.history-style {
  /deep/ .el-form-item {
    margin-bottom: 5px;
  }

  /deep/ .el-card__body {
    padding: 5px 10px 0;
  }
}

</style>
