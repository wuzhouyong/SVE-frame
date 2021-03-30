<template>
  <div class="container">
    <form-dialog
      :title="title"
      :values.sync="form"
      :rules="rules"
      label-width="130px"
      :visible.sync="dialogVisible"
      :width="width"
      @submit="submitForm"
    >
      <div class="info_container">
        <el-form-item label="排放口名称" prop="facilityName">
          <el-input v-model="form.facilityName" placeholder="请输入排放口名称" size="mini"/>
        </el-form-item>
        <el-form-item label="关联治理设施" prop="facilityPid">
          <el-checkbox-group v-model="form.facilityPid" size="mini">
            <el-checkbox
              :label="data.facilityId"
              v-for="(data,index) in facilityP"
              :key="index"
            >{{ data.facilityName }}
            </el-checkbox>
          </el-checkbox-group>
        </el-form-item>
        <el-form-item label="备注" prop="memo">
          <el-input v-model="form.memo" placeholder="请输入备注" type="textarea" size="mini"/>
        </el-form-item>
        <el-form-item label-width="0px" prop="entity">
          <elementTem
            v-model="form.entity"
            :eloptions="eloptions"
            :collects="collects"
            v-if="visible"
          ></elementTem>
        </el-form-item>
      </div>
    </form-dialog>
  </div>
</template>
<script>
import FormDialog from "@/views/components/form-dialog";
import FactorType from "@/api/voc/discharge";
import elementTem from "@/components/elementTem";
import { validateElType } from "@/utils/rules.js";

export default {
  props: {
    title: {
      type: String
    },
    values: {
      type: Object,
      default: () => ({})
    },
    visible: {
      type: Boolean,
      default: false
    }
  },
  computed: {
    dialogVisible: {
      get() {
        return this.visible || false;
      },
      set(v) {
        this.$emit("update:visible", v);
      }
    }
  },
  data() {
    return {
      collectas: [{ collectId: "123", collectName: "数采1" }],
      width: "50%",
      form: { facilityName: "", memo: "", facilityPid: [], entity: [] },
      facilityP: [],
      rules: {
        facilityName: [
          { required: true, message: "排放口名称必填", trigger: "blur" }
        ],
        facilityPid: [
          { required: true, message: "关联治理设施必选", trigger: "blur" }
        ],
        entity: [{ required: true, message: "必填", trigger: "blur" }, { validator: validateElType }]
      },
      eloptions: [
        { label: "VOCs浓度", value: "g290" },
        { label: "VOCs排放瞬时流量", value: "g201" },
        { label: "VOCs排放温度", value: "g280" },
        { label: "VOCs排放压力", value: "g282" },
        { label: "非甲烷总烃浓度", value: "g292" },
        { label: "甲烷浓度", value: "g293" },
        { label: "苯浓度", value: "g294" }
      ],
      collects: [],
      page: {
        pageNum: 1,
        pageSize: 30
      }
    };
  },
  watch: {
    values: {
      handler(v) {
        if (v) this.form = Object.assign({ facilityPid: [] }, v);
      },
      immediate: true
    }
  },
  components: {
    FormDialog,
    elementTem
  },
  created() {
    this.FacilityList();
    this.initCollectId();
  },
  methods: {
    //关联治理设施
    FacilityList() {
      FactorType.facilityList().then((res) => {
        if (res.code == 200) {
          this.facilityP = res.rows || res.data;
        }
      });
    },
    //数采
    initCollectId() {
      FactorType.getcollectId({}, this.urlParameter(this.page)).then((res) => {
        if (res.code == 200) {
          this.collects = res.rows || res.data;
        }
      });
    },
    submitForm() {
      if (this.form.facilityId) {
        FactorType.editEmissionPoint(this.form).then((res) => {
          if (res.code === 200) {
            this.msgSuccess("修改成功");
            this.$emit("completed");
          } else {
            this.msgError(res.message);
          }
        });
      } else {
        FactorType.addEmissionPoint(this.form).then((res) => {
          if (res.code === 200) {
            this.msgSuccess("添加成功");
            this.$emit("completed");
          } else {
            this.msgError(res.message);
          }
        });
      }
    }
  }
};
</script>
<!--<style>-->
<!--.el-form .el-row .el-col .el-form-item .el-form-item__content {-->
<!--  margin: 0 !important;-->
<!--}-->
<!--</style>-->
<style lang="scss">
.add {
  padding-left: 20px;
  margin-top: 5px;
  /* margin-left: 3px; */
}

.info_container {
  display: flex;
  flex-direction: column;
  padding: 5px;
  width: 100%;
}

.title {
  margin-bottom: 20px;
}
</style>
