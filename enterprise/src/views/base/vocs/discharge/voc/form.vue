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
        <el-form-item label="排放风机名称" prop="facilityName">
          <el-input v-model="form.facilityName" placeholder="排放风机名称" size="mini" />
        </el-form-item>
        <el-form-item label="关联排放口" prop="facilityPid">
          <el-select
            v-model="form.facilityPid"
            placeholder="请选择关联排放口"
            clearable
            :style="{width: '100%'}"
            size="mini"
          >
            <el-option
              v-for="item in disportList"
              :key="item.facilityId"
              :label="item.facilityName"
              :value="item.facilityId"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="备注">
          <el-input v-model="form.memo" placeholder="请输入备注" type="textarea" size="mini" />
        </el-form-item>
        <el-form-item label-width="0" prop="entity">
          <elementTem
            v-model="form.entity"
            :collects="collects"
            :eloptions="factor_type_arr"
            :isAdd="false"
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
      type: String,
    },
    values: {
      type: Object,
      default: () => ({}),
    },
    visible: {
      type: Boolean,
      default: false,
    },
  },
  data() {
    return {
      width: "50%",
      form: { entity: [{ eleTypeId: "e802", eleCode: "e802" }] },
      rules: {
        facilityName: [
          { required: true, message: "排放风机名称必填", trigger: "blur" },
        ],
        facilityPid: [
          { required: true, message: "关联排放口名称必填", trigger: "blur" },
        ],
        entity: [{ validator: validateElType }],
      },
      collects: [],
      page: {
        pageNum: 1,
        pageSize: 20,
      },
      factor_type_arr: [
        {
          label: "VOCs排放设施电流",
          value: "e802",
        },
      ],
      disportList: [],
    };
  },
  computed: {
    dialogVisible: {
      get() {
        return this.visible || false;
      },
      set(v) {
        this.$emit("update:visible", v);
      },
    },
  },
  watch: {
    values: {
      handler(v) {
        if (v) {
          this.form = Object.assign(
            { entity: [{ eleTypeId: "e802", eleCode: "e802" }] },
            v
          );
          if (this.form.facilityPid && this.form.facilityPid.length === 1) {
            this.form.facilityPid = this.form.facilityPid[0];
          }
        }
      },
      immediate: true,
    },
  },
  created() {
    this.queryDisport();
    FactorType.getcollectId({}, this.urlParameter(this.page)).then((res) => {
      if (res.code == 200) {
        this.collects = res.rows || res.data;
      }
    });
  },
  components: {
    FormDialog,
    elementTem,
  },
  methods: {
    //关联排放口
    queryDisport() {
      FactorType.queryEmissionPoint().then((res) => {
        this.disportList = res.rows;
      });
    },
    submitForm() {
      if (this.form.facilityId) {
        let oVal = { ...{}, ...this.form };
        oVal.facilityPid = [this.form.facilityPid];
        FactorType.editEmissionBlower(oVal).then((res) => {
          if (res.code === 200) {
            this.msgSuccess("修改成功");
            this.$emit("completed");
          } else {
            this.msgError(res.message);
          }
        });
      } else {
        let oVal = {
          facilityName: this.form.facilityName,
          facilityPid: [this.form.facilityPid],
          memo: this.form.memo ? this.form.memo : "",
          entity: this.form.entity,
        };
        FactorType.addEmissionBlower(oVal).then((res) => {
          if (res.code === 200) {
            this.msgSuccess("添加成功");
            this.$emit("completed");
          } else {
            this.msgError(res.message);
          }
        });
      }
    },
  },
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
}
.title {
  margin-bottom: 20px;
}
</style>
