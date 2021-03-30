<template>
  <el-card class="box-card">
    <el-form
      :model="ruleForm"
      :rules="rules"
      :ref="'elForm' +propsKey"
      label-width="100px"
      class="demo-ruleForm"
    >
      <!-- <el-row>
        <el-col :span="10">
          <el-form-item label="因子类型" prop="type">
            <el-select
              v-model="obj.eleTypeId"
              placeholder="请选择"
              size="mini"
              :style="{ width: '100%' }"
              @change="eleTypeChange"
            >
              <el-option
                v-for="item in newData"
                :key="item.value"
                :label="item.label + '(' + item.value + ')'"
                :value="item.value"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="数采名称" prop="name">
            <el-select
              v-model="obj.collectId"
              placeholder="请选择数采"
              size="mini"
              :style="{ width: '100%' }"
              @change="eleTypeChange"
            >
              <el-option
                v-for="item in collects"
                :key="item.collectId"
                :label="item.cpName"
                :value="item.collectId"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24" v-if="miningType">
          <el-radio v-model="radio" label="1">填写新的因子</el-radio>
          <el-radio v-model="radio" label="2">选择已有因子</el-radio>
        </el-col>
      </el-row>
      <el-row v-if="radio == 1">
        <el-col :span="8">
          <el-form-item label="因子名称" prop="desc">
            <el-input size="mini" v-model="ruleForm.desc"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="因子编码" prop="desc">
            <el-input size="mini" v-model="ruleForm.desc"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="安装名称" prop="desc">
            <el-input size="mini" v-model="ruleForm.desc"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row v-if="radio == 2">
        <el-col :span="8">
          <el-form-item label="已有因子" prop="desc">
            <el-select
              v-model="obj.eleTypeId"
              placeholder="请选择"
              size="mini"
              :style="{ width: '100%' }"
              @change="eleTypeChange"
            >
              <el-option
                v-for="item in newData"
                :key="item.value"
                :label="item.label + '(' + item.value + ')'"
                :value="item.value"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row> -->

      <el-form-item label="因子名称" prop="desc">
        <el-input size="mini" v-model="ruleForm.desc"></el-input>
      </el-form-item>
       <el-form-item label="名称" prop="resource">
        <el-input size="mini" v-model="ruleForm.resource"></el-input>
      </el-form-item>
    </el-form>
  </el-card>
</template>
<script>
import FactorType from "@/api/voc/discharge";
import elAssem from "./elAssem";
export default {
  components: { elAssem },
//   model: {
//     prop: "value",
//     event: "change",
//   },
    props: {
        propsKey:{
             type:Number,    
             default: true 
        }
  //       type: Boolean,
  //       default: true,
  //     isAdd: {
  //     },
  //     value: {
  //       type: Array,
  //     },
  //     eloptions: {
  //       type: Array,
  //       default: () => {
  //         [];
  //       },
  //     },
  //     collects: {
  //       default: () => {
  //         [];
  //       },
  //     },
    },
  data() {
    return {
      elList: this.value || [{}],
      eloption: [],
      newData: [],
      obj: {},
      radio: "",
      // 选择新增因子类型
      miningType: false,
      ruleForm: {
        // name: "",
        // region: "",
        // date1: "",
        // date2: "",
        // delivery: false,
        // type: [],
        // resource: "",
        desc: "",
        resource: "",
      },
      rules: {
        // name: [
        //   { required: true, message: "请输入活动名称", trigger: "blur" },
        //   { min: 3, max: 5, message: "长度在 3 到 5 个字符", trigger: "blur" },
        // ],
        // region: [
        //   { required: true, message: "请选择活动区域", trigger: "change" },
        // ],
        // date1: [
        //   {
        //     type: "date",
        //     required: true,
        //     message: "请选择日期",
        //     trigger: "change",
        //   },
        // ],
        // date2: [
        //   {
        //     type: "date",
        //     required: true,
        //     message: "请选择时间",
        //     trigger: "change",
        //   },
        // ],
        // type: [
        //   { required: true, message: "请选择活动区域", trigger: "change" },
        // ],
        // resource: [
        //   { required: true, message: "请选择活动资源", trigger: "change" },
        // ],
         resource: [{ required: true, message: "请填写形式", trigger: "blur" }],
        desc: [{ required: true, message: "请填写活动形式", trigger: "blur" }],
      },
    };
  },
//   watch: {
//     eloptions: {
//       handler(v) {
//         this.eloption = v;
//         this.newData = JSON.parse(JSON.stringify(this.eloption));
//         this.obj.eleTypeId = this.newData[0].value;
//       },
//       immediate: true,
//       deep: true,
//     },
//   },
  methods: {
    onChange(v) {
      this.elList.forEach((i) => {
        if (i.cpElemId) i.id = i.cpElemId;
      });
      this.elList[v.index] = v;
      let o = JSON.parse(JSON.stringify(this.eloption));
      o.forEach((m) => {
        m.disabled = this.elList.some((o) => o.eleTypeId === m.value);
      });
      this.newData = o;
      this.$emit("change", this.elList);
    },
    addEl() {
      this.elList.push({});
    },
    reduceEl(i) {
      this.elList.splice(i, 1);
      let o = JSON.parse(JSON.stringify(this.eloption));
      o.forEach((m) => {
        m.disabled = this.elList.some((o) => o.eleTypeId === m.value);
      });
      this.newData = o;
    },
    eleTypeChange(v) {
      this.getMiningTyep(v, this.newData[0].value);

      this.miningType = true;
      this.radio = "1";
      this.obj.eleCode = this.newData[0].value;
      // console.log("v",v);
    },
    getMiningTyep(collectId, eleDesc) {
      FactorType.queryAlready({ collectId, eleDesc }).then((res) => {
        if (res.code == 200) {
          // this.collects = res.rows || res.data;
        }
      });
    },
  },
  mounted() {
    let propsKey = this.propsKey
    console.log(this.propsKey);
    // this['ruleForm'+propsKey] = {
    //     desc:''
    // }

  },
  created(){
        //   let prosName = this.propsKey + 'ruleForm';
        //   this[prosName] = {}
          
  }
};
</script>