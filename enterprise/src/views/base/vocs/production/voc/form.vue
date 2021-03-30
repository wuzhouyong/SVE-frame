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
        <el-form-item label="生产线电表名称" prop="facilityName">
          <el-input v-model="form.facilityName" placeholder="请输入生产线电表名称" size="mini" />
        </el-form-item>
        <el-form-item label="备注" prop="memo">
          <el-input v-model="form.memo" type="textarea" placeholder="请输入备注" size="mini" />
        </el-form-item>
        <el-form-item label-width="0" prop="entity">
          <elementTem
            v-model="form.entity"
            :collects="collects"
            :eloptions="factor_type_arr"
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
  watch: {
    values: {
      handler(v) {
        if (v)
          this.form = Object.assign(
            {
              entity: [
                { eleTypeId: "e300", eleCode: "e300" },
                { eleTypeId: "e301", eleCode: "e301" },
              ],
            },
            v
          );
      },
      immediate: true,
    },
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
  data() {
    return {
      width: "50%",
      form: {
        facilityName: "",
        memo: "",
        entity: [
          { eleTypeId: "e300", eleCode: "e300" },
          { eleTypeId: "e301", eleCode: "e301" },
        ],
      },
      rules: {
        facilityName: [
          { required: true, message: "生产线电表名称必填", trigger: "blur" },
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
          label: "涉VOCs生产用电量",
          value: "e300",
        },
        {
          label: "涉VOCs生产用电功率",
          value: "e301",
        },
      ],
    };
  },
  created() {
    this.initCollectId();
  },
  components: {
    FormDialog,
    elementTem,
  },
  methods: {
    initCollectId() {
      FactorType.getcollectId({}, this.urlParameter(this.page)).then((res) => {
        if (res.code == 200) {
          this.collects = res.rows || res.data;
        }
      });
    },
    submitForm() {
      if (this.form.facilityId) {
        FactorType.editProduct(this.form).then((res) => {
          if (res.code === 200) {
            this.msgSuccess("修改成功");
            this.$emit("completed");
          } else {
            this.msgError(res.message);
          }
        });
      } else {
        FactorType.addProduction(this.form).then((res) => {
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
<style lang="scss" scope>
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
