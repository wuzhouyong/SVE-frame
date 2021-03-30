<template>
  <form-dialog
    :title="title"
    :values.sync="form"
    :rules="rules"
    label-width="130px"
    :visible.sync="dialogVisible"
    @submit="submitForm"
    :labelPosition="'right'"
  >
    <el-form-item label="数据来源" prop="isOther">
      <el-radio-group v-model="form.isOther" @change="isOtherChange">
        <el-radio :label="true">其他项目</el-radio>
        <el-radio :label="false">非其他项目</el-radio>
      </el-radio-group>
    </el-form-item>
    <el-form-item label="数据来源项目" prop="srcTagHeadId" v-if="form.isOther">
      <el-select
        v-model="form.srcTagHeadId"
        placeholder="数据来源项目"
        style="width: 100%"
        @change="sourceChange"
      >
        <el-option
          :label="i.label"
          :value="i.value"
          v-for="i in otherSource"
          :key="i.value"
        ></el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="数采关联" prop="srcCollectId" v-if="form.isOther">
      <el-select
        v-model="form.srcCollectId"
        placeholder="数采关联"
        style="width: 100%"
        @change="miningChange"
      >
        <el-option
          :label="i.text"
          :value="i.value"
          v-for="i in miningArr"
          :key="i.value"
        ></el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="因子关联" prop="srcElemId" v-if="form.isOther">
      <el-select
        v-model="form.srcElemId"
        placeholder="因子关联"
        style="width: 100%"
      >
        <el-option
          :label="i.text"
          :value="i.value"
          v-for="i in factorArr"
          :key="i.value"
        ></el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="数采名称" prop="collectId">
      <!-- <SearchSelect v-model="form.collectId" place="请输入数采名称" :isType="isType"></SearchSelect> -->
      <el-select
        v-model="form.collectId"
        placeholder="请输入数采名称"
        style="width: 100%"
      >
        <el-option
          :label="i.name"
          :value="i.id"
          v-for="i in collects"
          :key="i.id"
        ></el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="因子类型" prop="eleTypeId">
      <el-select
        v-model="form.eleTypeId"
        placeholder="请选择因子类型"
        style="width: 100%"
        @change="eleTypeChange"
      >
        <el-option
          :label="`${i.name} ( ${i.id} )`"
          :value="i.id"
          v-for="i in elemType"
          :key="i.id"
        ></el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="因子编码" prop="eleCode">
      <el-input v-model="form.eleCode" placeholder="请输入因子编码">
        <!-- <template slot="prepend">{{form.eleTypeId}}</template> -->
      </el-input>
    </el-form-item>
    <el-form-item label="因子名称" prop="eleDesc">
      <el-input v-model="form.eleDesc" placeholder="请输入因子名称"/>
    </el-form-item>
    <el-form-item label="安装位置" prop="installLocation">
      <el-input
        v-model="form.installLocation"
        autocomplete="off"
        placeholder="安装位置"
      ></el-input>
    </el-form-item>
  </form-dialog>
</template>
<script>
import {
  dropdown,
  elementType,
  add,
  edit,
  factorList,
  paperEle_ref,
} from "@/api/element";
import FormDialog from "@/views/components/form-dialog";
import {Minninglist} from "@/api/collect";

export default {
  name: "element-form",
  components: {FormDialog},
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
    otherSource: {
      type: Array,
      default: () => [],
    },
  },
  data () {
    const validateElement = (rule, value, callback) => {
      if (value.substring(0, 4) !== this.form.eleTypeId) {
        callback(new Error("因子编码前四位必须和因子类型对应!"));
      } else if (value.length !== 6) {
        callback(new Error("因子编码长度为6位!"));

      } else {
        callback();
      }
    };
    return {
      collects: [],
      //因子类型
      elemType: [],
      form: Object.assign(
        {srcTagHeadId: "", srcCollectId: "", srcElemId: "", isOther: false},
        this.values
      ),
      // 表单校验
      rules: {
        collectId: [
          {
            required: true,
            message: "数采名称不能为空",
            trigger: "blur",
          },
        ],
        eleDesc: [
          {
            required: true,
            message: "因子名称不能为空",
            trigger: "blur",
          },
        ],
        eleTypeId: [
          {
            required: true,
            message: "因子类型不能为空",
            trigger: "blur",
          },
        ],
        eleCode: [
          {
            required: true,
            message: "因子编码不能为空",
            trigger: "blur",
          },
          {validator: validateElement},
        ],
        srcTagHeadId: [
          {required: true, message: "请选择数据来源项目", trigger: "change"},
        ],
        srcCollectId: [
          {required: true, message: "请选择数采关联", trigger: "change"},
        ],
        srcElemId: [
          {required: true, message: "请选择因子关联", trigger: "change"},
        ],
        isOther: [
          {required: true, message: "请选择是或否", trigger: "change"},
        ],
      },
      isOther: "2",
      page: {
        pageNum: 1,
        pageSize: 100,
      },
      // 数采组
      miningArr: [],
      //因子组
      factorArr: [],
    };
  },
  computed: {
    sys_name () {
      return this.$route.path.substr(this.$route.path.lastIndexOf("/") + 1);
    },
    dialogVisible: {
      get () {
        return this.visible || false;
      },
      set (v) {
        this.$emit("update:visible", v);
      },
    },
  },
  watch: {
    values () {
      this.form = Object.assign({}, this.values);
      this.getCollects();
      elementType(this.sys_name).then((res) => {
        if (res.code === 200) this.elemType = res.rows || res.data;
      });
    },
  },
  methods: {
    getCollects () {
      dropdown(this.sys_name).then((res) => {
        if (res.code === 200) this.collects = res.rows || res.data;
      });
    },
    getelementType () {
      elementType(this.sys_name).then((res) => {
        if (res.code === 200) this.elemType = res.rows || res.data;
      });
    },
    eleTypeChange (v) {
      this.$set(this.form, "eleCode", v);
    },
    submitForm (v) {
      if (!this.form.cpElemId) {
        add(this.sys_name, this.form).then((res) => {
          if (res.code === 200) {
            this.msgSuccess("添加成功");
            this.$emit("completed");
          } else {
            this.msgError(res.message);
          }
        });
      } else {
        edit(this.sys_name, this.form).then((res) => {
          if (res.code === 200) {
            this.msgSuccess("修改成功");
            this.$emit("completed");
          } else {
            this.msgError(res.message);
          }
        });
      }
    },
    sourceChange (value) {
      let {otherSource} = this;
      this.TargtPath = otherSource.filter((item) => {
        return item.value == value;
      })[0].path;
      // 初始化
      this.miningArr = [];
      this.factorArr = [];
      this.form.srcCollectId = "";
      this.form.srcElemId = "";

      Minninglist(this.TargtPath, {}).then((res) => {
        if (res.code === 200) {
          this.miningArr = res.rows || res.data;
        }
      });
    },
    miningChange (value) {
      // console.log(value);
      let {TargtPath} = this;
      this.form.srcElemId = "";
      let params = {};
      if (TargtPath == "lingxing" || TargtPath == "guocheng" || TargtPath == "outwater") {
        params.collectId = value;
      } else if (TargtPath == "dgom") {
        params.checkpointid = value;
      }
      factorList(TargtPath, params).then((res) => {
        if (res.code === 200) {
          this.factorArr = res.rows || res.data;
          this.factorArr.forEach(i => {
            i.value = i.value + "";
          });
        }
      });
    },
    isOtherChange (value) {
      if (!value && !this.form.cpElemId) {
        this.form = Object.assign(this.form, {
          srcTagHeadId: "",
          srcCollectId: "",
          srcElemId: "",
        });
        this.form.eleTypeId = null;
        this.form.eleCode = null;
      }
    },
  },
  mounted () {
    // 判断是编辑还是新增
    if (Object.keys(this.values).length !== 0) {
      // console.log("this.values", this.values);
      let {cpElemId} = this.values;

      paperEle_ref(cpElemId).then((res) => {
        let {isOther} = res.data;
        isOther && this.sourceChange(res.data.srcTagHeadId);
        isOther && this.miningChange(res.data.srcCollectId);
        if (!isOther) {
          this.form = Object.assign(this.form, {
            srcTagHeadId: "",
            srcCollectId: "",
            srcElemId: "",
            isOther: res.data.isOther,
          });
        } else {
          this.form = Object.assign(this.form, {
            srcTagHeadId: res.data.srcTagHeadId + "",
            srcCollectId: res.data.srcCollectId + "",
            srcElemId: res.data.srcElemId,
            isOther: res.data.isOther,
          });
        }
      });
    }

    this.getCollects();
    this.getelementType();
  },
};
</script>
