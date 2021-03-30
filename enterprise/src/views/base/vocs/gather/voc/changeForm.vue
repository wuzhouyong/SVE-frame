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
        <el-form-item label="风机名称" prop="facilityName">
          <el-input v-model="form.facilityName" placeholder="请输入风机名称" />
        </el-form-item>
        <el-form-item label="关联生产线电表" prop="facilityPid">
          <el-checkbox-group v-model="form.facilityPid">
            <el-checkbox
              :label="data.facilityId"
              v-for="(data, index) in facilityP"
              :key="index"
              >{{ data.facilityName }}</el-checkbox
            >
          </el-checkbox-group>
        </el-form-item>
        <el-form-item label="备注" prop="procUserName">
          <el-input
            v-model="form.memo"
            placeholder="请输入备注"
            type="textarea"
          />
        </el-form-item>
        <el-form-item label-width="0" prop="entity">
          <!--           
          <elementTem
            v-model="form.entity"
            :collects="collects"
            :eloptions="factor_type_arr"
            :isAdd="false"
            v-if="visible"
          ></elementTem> -->
          <el-divider content-position="left">因子管理</el-divider>
          <div v-for="(item,index) in [1, 2]" :key="index">
            <elForm :ref="'elForm' + item" :propsKey="index" ></elForm>
          </div>
        </el-form-item>
      </div>
    </form-dialog>
  </div>
</template>
<script>
import FormDialog from "@/views/components/form-dialog";
import elementTem from "@/components/elementTem";
import FactorType from "@/api/voc/discharge";
import elForm from "@/components/elementTem/elForm.vue";
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
      form: {
        facilityPid: [],
        entity: [{ eleTypeId: "e602", eleCode: "e602" }],
      },
      rules: {
        facilityName: [
          { required: true, message: "风机名称必填", trigger: "blur" },
        ],
        facilityPid: [
          { required: true, message: "关联生产线必选", trigger: "blur" },
        ],
        // entity: [{ validator: validateElType }],
      },
      collects: [],
      facilityP: [],
      page: {
        pageNum: 1,
        pageSize: 30,
      },
      factor_type_arr: [
        {
          label: "VOCs收集设施电流",
          value: "e602",
        },
      ],
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
  created() {
    this.getProductionList();
    FactorType.getcollectId({}, this.urlParameter(this.page)).then((res) => {
      if (res.code == 200) {
        this.collects = res.rows || res.data;
      }
    });
  },

  components: {
    FormDialog,
    elementTem,
    elForm,
  },
  watch: {
    values: {
      handler(v) {
        if (v)
          this.form = Object.assign(
            {
              facilityPid: [],
              entity: [{ eleTypeId: "e602", eleCode: "e602" }],
            },
            v
          );
      },
      immediate: true,
    },
  },
  methods: {
    //关联生产线
    getProductionList() {
      // this.addValues=this.editValues
      FactorType.getProduction().then((res) => {
        if (res.code == 200) {
          this.facilityP = res.data || res.rows;
        }
      });
    },
    submitForm() {
      // if (this.form.facilityId) {
      //   FactorType.editCollect(this.form).then((res) => {
      //     if (res.code === 200) {
      //       this.msgSuccess("修改成功");
      //       this.$emit("completed");
      //     } else {
      //       this.msgError(res.message);
      //     }
      //   });
      // } else {
      //   FactorType.addCollection(this.form).then((res) => {
      //     if (res.code === 200) {
      //       this.msgSuccess("添加成功");
      //       this.$emit("completed");
      //     } else {
      //       this.msgError(res.message);
      //     }
      //   });
      // }

      // 获取到组件中的form
      console.log("this.$refs.ruleForm1", this.$refs);
      let _that = this;
      let arr = [];
      Object.getOwnPropertyNames(_that.$refs).forEach(function (key) {
        // console.log(JSON.stringify(_that.$refs[key]));
        console.log(_that.$refs[key])
        arr.push(_that.$refs[key][0])
      });
      let arrrrs= [] 
      for(let i = 0;i<arr.length;i++){
        arrrrs.push(arr[i].$refs[`elForm${i}`]);
        console.log(arr[i])
      }
      console.log("arrrrs",arrrrs)
      console.log("arr",arr)
      // console.log("this.$refs[0]",this.$refs.elForm1.$refs.ruleForm1)
      // const personForm = this.$refs.elForm1.$refs.ruleForm1;
      // const adsForm = this.$refs[1];
      Promise.all(arrrrs.map(this.getFormPromise)).then(
        (res) => {
          console.log("res",res)
          const validateResult = res.every((item) => !!item);
          if (validateResult) {
            console.log("两个表单都校验通过");
          } else {
            console.log("两个表单未校验通过");
          }
        }
      ).catch((err)=>{
        console.log(err)
      })
    },
    getFormPromise(form) {
      console.log("form", form);
      return new Promise((resolve) => {
        form.validate((res) => {
          resolve(res);
        });
      });
    },
  },
};
</script>
<style>
.el-form .el-row .el-col .el-form-item .el-form-item__content {
  margin: 0 !important;
}
</style>
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