<template>
  <el-dialog :visible.sync="checkInfo" class="pub_dialog" :title="'企业基本信息修改记录'">
    <div class="check" v-for="(item,index) in infor" :key="index">
      <el-form>
        <div>
          <el-form-item label="企业名称:">{{ item.fullName }}</el-form-item>
          <el-form-item label="排污许可证代码:">{{ item.emissionPermitCode }}</el-form-item>
          <el-form-item label="经度:">{{ item.longitude }}</el-form-item>
          <el-form-item label="企业地址:">{{ item.address }}</el-form-item>
          <el-form-item label="行业类别:">{{ item.industryName }}</el-form-item>
          <el-form-item label="法定代表人:">{{ item.legalPersonName }}</el-form-item>
        </div>
        <div>
          <el-form-item label="社会统一信用码:">{{ item.socialCreditCode }}</el-form-item>
          <el-form-item label="所属镇街:">{{ item.townName }}</el-form-item>
          <el-form-item label="维度:">{{ item.latitude }}</el-form-item>
          <el-form-item label="企业经营状态:">{{ getStatusName(item.sourceStatus) }}</el-form-item>
          <el-form-item label="企业规模:">{{ getScaleName(item.sourceScale) }}</el-form-item>
          <el-form-item label="法人联系电话:">{{ item.legalPersonPhone }}</el-form-item>
        </div>
      </el-form>
      <div v-for="(i,kk) of item.file" :key="kk" class="fileList">
        <span>{{ i.showName }}</span>
        <el-link :href="getlink(i.Id)" type="primary" :underline="false" target="_blank">下载</el-link>
      </div>
      <div class="auditState">
        审批状态:
        <span style="color:rgb(0,0,0,.5)" v-if="item.auditState==0">待审批</span>
        <span style="color:rgb(178, 226, 88)" v-else-if="item.auditState==1">已同意</span>
        <span style="color:rgba(235, 49, 49,0.692)" v-else-if="item.auditState==2">已拒绝</span>
      </div>
    </div>
  </el-dialog>
</template>
<script>
export default {
  props: {
    sourceScaleOptions: {
      type: Array,
      default: function () {
        return [];
      }
    },
    sourceStatusOptions: {
      type: Array,
      default: function () {
        return [];
      }
    },
    infor: {
      type: Array,
      default: function () {
        return [];
      }
    },
  },
  data () {
    return {
      checkInfo: false
    };
  },
  methods: {
    getlink (id) {
      return `${process.env.VUE_APP_BASE_API}/com/getfile/${id}`;
    },
    getStatusName (code) {//筛选企业状态
      const dict = this.sourceStatusOptions.find(
        p => p.dictValue === code
      );
      return dict ? dict.dictLabel : null;
    },
    getScaleName (code) {// 筛选企业规模
      const dict = this.sourceScaleOptions.find(
        p => p.dictValue === code
      );
      return dict ? dict.dictLabel : null;
    },
  }
};
</script>
<style scoped>
.check {
  display: flex;
  flex-direction: column;
  width: 100%;
  padding: 0;
  margin-bottom: 30px;
  border: 1px solid rgb(214, 214, 214);
  box-shadow: 0 0 5px rgb(153, 153, 153);
  /* border-radius: 10px; */
}

.check form {
  width: 90%;
  margin: 0 auto;
  display: flex;
  justify-content: space-around;
}

.check form div {
  width: 50%;
}

.check .auditState {
  text-align: center;
  line-height: 64px;
  height: 64px;
  font-weight: 600;
  border-top: 2px solid rgb(214, 214, 214);
}

.check form >>> .el-form-item {
  margin-right: 0;
  margin-bottom: 8px;
  width: 100%;
}

.check form >>> .el-form-item .el-form-item__label {
  width: 120px;
}

.check form >>> .el-form-item,
.info form >>> .el-col .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
}

.pub_dialog {
  overflow: hidden;
}

.fileList {
  display: flex;
  justify-content: space-between;
  padding: 0 30px;
  border-top: 1px solid rgb(214, 214, 214)
}

.fileList span, .fileList a {
  display: inline-block;
  height: 40px;
  line-height: 40px;
}

.fileList span {
  color: rgb(135, 172, 221);
}
</style>
