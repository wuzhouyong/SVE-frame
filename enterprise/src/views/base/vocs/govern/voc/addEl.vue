<template>
  <div>
    <form-dialog
      title="因子管理"
      :values.sync="formEl"
      :rules="rules"
      label-width="130px"
      :visible.sync="eleVisible"
      width="50%"
      @submit="submitForm"
    >
      <el-form-item label-width="0" prop="entity">
        <elementTem
          v-model="formEl.entity"
          :collects="collects"
          :eloptions="eloptions"
          :selectType="selectType"
          v-if="elVisible"
        ></elementTem>
      </el-form-item>
    </form-dialog>
  </div>
</template>
<script>
import FormDialog from "@/views/components/form-dialog";
import elementTem from "@/components/elementTem";
import { validateElType } from "@/utils/rules.js";
import FactorType from "@/api/voc/discharge";
export default {
  components: { FormDialog, elementTem },
  props: {
    title: {
      type: String,
    },
    values: {
      type: Array,
      default: () => [],
    },
    elVisible: {
      type: Boolean,
      default: false,
    },
    elIndex: {
      type: Number,
    },
    selectType: {
      type: String,
    },
  },
  watch: {
    values: {
      handler (v) {
        this.formEl.entity = v;
        // console.log("this.formEl",this.formEl)
      },
      deep: true,
      immediate: true,
    },
    selectType: {
      handler (v) {
        if (
          new Set([
            "voc_uvgj",
            "voc_pl",
            "voc_dlz",
            "voc_lnhs",
            "voc_jdxf",
          ]).has(v)
        ) {
          this.eloptions = [
            {
              label: "VOCs治理设施电流",
              value: "e702",
            },
          ];
        } else if (v === "voc_hxt") {
          this.eloptions = [
            {
              label: "处理温度",
              value: "g180",
            },
            {
              label: "处理湿度",
              value: "g181",
            },
            {
              label: "活性炭吸附进出口压力差",
              value: "g184",
            },
          ];
        } else if (v === "voc_chrs") {
          this.eloptions = [
            {
              label: "VOCs治理设施电流",
              value: "e702",
            },
            {
              label: "处理温度",
              value: "g180",
            },
          ];
        } else if (v === "watt") {
          this.eloptions = [
            {
              label: "治理设施用电量",
              value: "e700",
            },
            {
              label: "治理设施功率",
              value: "e701",
            },
          ];
        }
      },
      immediate: true,
    },
  },
  computed: {
    eleVisible: {
      get () {
        return this.elVisible || false;
      },
      set (v) {
        this.$emit("update:elVisible", v);
      },
    },
  },
  created () {
    this.initCollectId();
  },
  data () {
    return {
      formEl: {},
      rules: {
        entity: [{ validator: validateElType }],
      },
      collects: [],
      page: {
        pageNum: 1,
        pageSize: 20,
      },
      eloptions: [],
    };
  },
  methods: {
    submitForm () {
      this.formEl.num = this.elIndex;
      this.formEl.type = this.selectType;
      this.$emit("formElData", this.formEl);
    },
    initCollectId () {
      FactorType.getcollectId({}, this.urlParameter(this.page)).then((res) => {
        if (res.code == 200) {
        
          this.collects = res.rows || res.data;
            // console.log("collects", this.collects)
        }
      });
    },
  },
};
</script>