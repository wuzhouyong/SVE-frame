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
        <el-divider content-position="left">车辆信息</el-divider>
        <el-form-item label="处置企业" prop="sourceId">
          <el-select v-model="form.sourceId" placeholder="请选择处置企业" style="width:100%">
            <el-option
              :label="i.fullName"
              :value="i.sourceId"
              v-for="i in sourceList"
              :key="i.sourceId"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="车牌号" prop="carTag">
          <el-input v-model="form.carTag" placeholder="车牌号:(粤SXXXXX)" size="mini"/>
        </el-form-item>
        <el-form-item label="MN码" prop="mn">
          <el-input v-model="form.mn" placeholder="MN码" size="mini"/>
        </el-form-item>
        <el-form-item label="车辆类型" prop="carType">
          <el-input v-model="form.carType" placeholder="车辆类型" size="mini"/>
        </el-form-item>
        <el-form-item label="道路运输证" prop="transcert">
          <el-input v-model="form.transcert" placeholder="道路运输证" size="mini"/>
        </el-form-item>
        <el-form-item label="车辆尺寸(长*宽*高)" prop="carSize">
          <el-input v-model="form.carSize" placeholder="车辆尺寸(长*宽*高)" size="mini"/>
        </el-form-item>
        <el-form-item label="载重" prop="maxLoadamt">
          <el-input v-model="form.maxLoadamt" placeholder="载重范围为(0~99999999.99)" size="mini"/>
        </el-form-item>
        <el-form-item label="容积" prop="fullVolume">
          <el-input v-model="form.fullVolume" placeholder="容积范围为(0~99999999.99)" size="mini"/>
        </el-form-item>
        <el-form-item label="DTU IP" prop="dtuIP">
          <el-input v-model="form.dtuIP" placeholder="车辆DTU的IP地址" size="mini"/>
        </el-form-item>
        <el-form-item label="是否完成信息化改造" prop="isSetup">
          <el-radio-group v-model="form.isSetup" size="mini">
            <el-radio-button label="0">否</el-radio-button>
            <el-radio-button label="1">是</el-radio-button>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="备注">
          <el-input v-model="form.memo" type="textarea" size="mini"/>
        </el-form-item>
      </div>
    </form-dialog>
  </div>
</template>
<script>
import FormDialog from "@/views/components/form-dialog";
import { tankcarAdd, tankcarEdit } from "@/api/lingxing";
import { validateRange } from "@/utils/rules.js";

export default {
  components: { FormDialog },
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
    },
    sourceList: {
      type: Array,
      default: () => []
    }
  },
  data() {
    return {
      width: "500px",
      form: {
        isSetup: "0"
      },
      rules: {
        sourceId: [
          {
            required: true,
            message: "处置企业必填",
            trigger: "blur"
          }
        ],
        carTag: [
          {
            required: true,
            message: "车牌号必填",
            trigger: "blur"
          }
        ],
        carType: [
          {
            required: true,
            message: "车辆类型必填",
            trigger: "blur"
          }
        ],
        transcert: [
          {
            required: true,
            message: "道路运输证必填",
            trigger: "blur"
          }
        ],
        maxLoadamt: [
          {
            required: true,
            message: "载重必填",
            trigger: "blur"
          },
          {
            validator: validateRange,
            trigger: "blur"
          }
        ],
        fullVolume: [
          {
            required: true,
            message: "容积必填",
            trigger: "blur"
          },
          {
            validator: validateRange,
            trigger: "blur"
          }
        ],
        carSize: [
          {
            required: true,
            message: "车辆尺寸必填",
            trigger: "blur"
          }
        ],
        dtuIP: [{
          required: true,
          message: "车辆DTUIP必填",
          trigger: "blur"
        }],
        isSetup: [
          {
            required: true,
            message: "必填",
            trigger: "blur"
          }
        ],
        mn: [
          {
            required: true,
            message: "MN码必填",
            trigger: "blur"
          }
        ]
      }
    };
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
  watch: {
    values: {
      handler(v) {
        if (v) {
          let obj = {
            isSetup: "0"
          };
          this.form = Object.assign(obj, v);
        }
      },
      immediate: true
    }
  },
  methods: {
    submitForm() {
      if (this.form.carId) {
        tankcarEdit(this.form).then((res) => {
          if (res.code === 200) {
            this.msgSuccess("修改成功");
            this.$emit("completed");
          } else {
            this.msgError(res.message);
          }
        });
      } else {
        tankcarAdd(this.form).then((res) => {
          if (res.code === 200) {
            this.msgSuccess("新增成功");
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
