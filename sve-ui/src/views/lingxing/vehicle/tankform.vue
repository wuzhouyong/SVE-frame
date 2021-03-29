<template>
  <div class="container">
    <form-dialog
      :title="title"
      :values.sync="form"
      :rules="rules"
      label-width="150px"
      :visible.sync="dialogVisible"
      :width="width"
      @submit="submitForm"
    >
      <div class="info-container">
        <el-form-item label="储水罐名称" prop="tankName">
          <el-input v-model="form.tankName" placeholder="储水罐名称" size="mini" />
        </el-form-item>
        <el-form-item label="截面积" prop="sectionArea">
          <el-input v-model="form.sectionArea" placeholder="截面积范围为(0~99999999.99)" size="mini" />
        </el-form-item>
        <el-form-item label="体积" prop="fullVolume">
          <el-input v-model="form.fullVolume" placeholder="体积范围为(0~99999999.99)" size="mini" />
        </el-form-item>
        <el-form-item label="报警液位高度" prop="warnRate">
          <el-input v-model="form.warnRate" placeholder="报警液位高度范围为(0~99.999)" size="mini" />
        </el-form-item>
        <el-form-item label="备注">
          <el-input v-model="form.memo" type="textarea" size="mini" />
        </el-form-item>
      </div>
    </form-dialog>
  </div>
</template>
<script>
import FormDialog from "@/views/components/form-dialog";
import { truckAdd, truckEdit } from "@/api/lingxing";
import { validateRange, validateWarnRate } from "@/utils/rules.js";
export default {
  components: { FormDialog },
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
    carId: {
      type: Number,
    },
  },
  data() {
    return {
      width: "500px",
      form: {},
      rules: {
        tankName: [
          {
            required: true,
            message: "储水罐名称必填",
            trigger: "blur",
          },
        ],
        sectionArea: [
          {
            required: true,
            message: "截面积必填",
            trigger: "blur",
          },
          {
            validator: validateRange,
            trigger: "blur",
          },
        ],
        fullVolume: [
          {
            required: true,
            message: "容积必填",
            trigger: "blur",
          },
          {
            validator: validateRange,
            trigger: "blur",
          },
        ],
        warnRate: [
          {
            required: true,
            message: "报警液位高度必填",
            trigger: "blur",
          },
          {
            validator: validateWarnRate,
            trigger: "blur",
          },
        ],
      },
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
          this.form = Object.assign({}, v);
        }
      },
      immediate: true,
    },
  },
  methods: {
    submitForm() {
      if (this.form.tankId) {
        truckEdit(this.form).then((res) => {
          if (res.code === 200) {
            this.msgSuccess("修改成功");
            this.$emit("tankSuccess");
          } else {
            this.msgError(res.message);
          }
        });
      } else {
        this.form.carId = this.carId;
        truckAdd(this.form).then((res) => {
          if (res.code === 200) {
            this.msgSuccess("新增成功");
            this.$emit("tankSuccess");
          } else {
            this.msgError(res.message);
          }
        });
      }
    },
  },
};
</script>
<style lang="scss" scope>
.el-style {
  display: flex;
  .box-card {
    flex: 1;
    margin: 0 2px;
    .el-card__body {
      padding: 10px;
      .el-form-item {
        margin-bottom: 5px;
      }
    }
  }
}
</style>
