<template>
  <div>
    <form-dialog
      title="关联其他净化器"
      :values.sync="form"
      :rules="rules"
      label-width="110px"
      :visible.sync="dialogVisible"
      :width="width"
      @submit="submitForm"
    >
      <el-form-item label="其他净化器">
        <el-checkbox-group v-model="form.pid">
          <el-checkbox :label="i.facilityId" :key="i.facilityId" v-for="i in list">{{ i.facilityName }}</el-checkbox>
        </el-checkbox-group>
      </el-form-item>
    </form-dialog>
  </div>
</template>

<script>
import FormDialog from "@/views/components/form-dialog";
import { yyFacilityRelated } from "@/api/youyan";

export default {
  components: { FormDialog },
  props: {
    values: {
      type: Object,
      default: () => ({})
    },
    visible: {
      type: Boolean,
      default: false
    },
    list: {
      default: () => []
    }
  },
  data: () => ({
    width: "600px",
    form: {},
    rules: {}

  }),
  watch: {
    values: {
      handler(v) {
        if (v) {
          this.form = Object.assign({}, v);
        }
      },
      immediate: true
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
  methods: {
    submitForm() {
      if (this.form.pid.length > 0) {
        let oVal = {
          facilityId: this.form.id,
          facilityPids: this.form.pid.join(",")
        };
        yyFacilityRelated(oVal).then(res => {
          if (res.code === 200) {
            this.$emit("relaSuccess");
            this.msgSuccess("净化器关联成功!");
          }
        });
      } else {
        this.msgError("请至少选择一个关联净化器");
      }
    }
  }
};
</script>

<style lang="scss">

</style>
