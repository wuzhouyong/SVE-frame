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
        <el-form-item label="治理设施名称" prop="facilityName">
          <el-input
            v-model="form.facilityName"
            placeholder="请输入治理设施名称"
            size="mini"
          />
        </el-form-item>
        <el-form-item label="关联生产线电表" prop="facilityPid">
          <el-checkbox-group v-model="form.facilityPid">
            <el-checkbox
              v-for="i in productList"
              :label="i.facilityId"
              :key="i.facilityId"
              >{{ i.facilityName }}</el-checkbox
            >
          </el-checkbox-group>
        </el-form-item>
        <el-form-item label="备注" prop="memo">
          <el-input
            v-model="form.memo"
            type="textarea"
            placeholder="请输入备注"
            size="mini"
          />
        </el-form-item>

        <!-- 电表 -->
        <el-divider content-position="left" class="addBtn-zl">
          电表
          <div class="zl-addBtn" title="添加电表" @click="addWatt">
            <el-button type="danger" icon="el-icon-plus" circle></el-button>
          </div>
        </el-divider>
        <div v-for="(item, index) in form.elecmeter" :key="'_' + index">
          <el-row :gutter="24" class="elRow-con-1">
            <el-col :span="16" class="elCol-form-1 elCol-form-2">
              <el-form-item label="电表名称">
                <el-input
                  v-model="item.elecmeterName"
                  placeholder="请输入电表名称"
                  size="mini"
                  style="width: 100%"
                />
              </el-form-item>
            </el-col>
            <el-col
              :span="4"
              class="elCol-form-1"
              v-if="form.elecmeter[index].elecmeterName !== ''"
            >
              <el-button
                type="primary"
                size="mini"
                @click="handleFacility(index, 'watt')"
                >因子管理</el-button
              >
            </el-col>
            <el-col
              :span="4"
              class="elCol-form-1"
              v-if="form.elecmeter.length > 1"
              style="text-align: center"
            >
              <i
                class="el-icon-remove reduceEl"
                title="移除电表"
                @click="deleteWatt(item, index)"
              ></i>
            </el-col>
          </el-row>
        </div>
        <!-- 治理设施 -->
        <el-divider content-position="left" class="addBtn-zl">
          治理工艺
          <div class="zl-addBtn" title="添加工艺" @click="addTech">
            <el-button type="danger" icon="el-icon-plus" circle></el-button>
          </div>
        </el-divider>
        <el-row :gutter="24" class="elRow-title">
          <el-col :span="3">序号</el-col>
          <el-col :span="6">工艺类型</el-col>
          <el-col :span="8">备注</el-col>
          <el-col :span="5"></el-col>
        </el-row>
        <div v-for="(item, index) in form.techno" :key="index">
          <el-row :gutter="24" class="elRow-con">
            <el-col :span="3" class="elCol-form">
              <el-form-item
                :prop="'techno.' + index + '.techNo'"
                :style="{ width: '100%' }"
                label-width="0"
                :rules="{
                  required: true,
                  message: '序号必填',
                  trigger: 'blur',
                }"
              >
                <el-input
                  v-model="item.techNo"
                  placeholder="序号"
                  size="mini"
                />
              </el-form-item>
            </el-col>
            <el-col :span="6" class="elCol-form">
              <el-form-item
                :prop="'techno.' + index + '.techType'"
                label-width="0"
                :rules="[
                  {
                    required: true,
                    message: '工艺类型必选',
                    trigger: 'change',
                  },
                ]"
              >
                <el-select
                  v-model="item.techType"
                  placeholder="请选择工艺类型"
                  :style="{ width: '100%' }"
                  size="mini"
                  :disabled="item.entity && item.entity.length > 1"
                  @change="onChangeTech($event, index)"
                >
                  <el-option
                    v-for="i in techTypeList"
                    :key="i.value"
                    :label="i.label"
                    :value="i.value"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="8" class="elCol-form">
              <el-form-item label-width="0">
                <el-input
                  v-model="item.memo"
                  placeholder="请输入备注"
                  size="mini"
                />
              </el-form-item>
            </el-col>
            <el-col :span="3" class="elCol-form">
              <el-button
                type="primary"
                size="mini"
                v-if="form.techno[index].techType !== ''"
                @click="handleFacility(index, form.techno[index].techType)"
                >因子管理</el-button
              >
            </el-col>
            <el-col
              :span="4"
              class="elCol-form"
              v-if="form.techno.length > 1"
              style="text-align: center"
            >
              <i
                class="el-icon-remove reduceEl"
                title="移除工艺"
                @click="deleteItem(item, index)"
              ></i>
            </el-col>
          </el-row>
        </div>
      </div>
    </form-dialog>
    <AddElTem
      v-if="elVisible"
      :elVisible.sync="elVisible"
      :elIndex="elIndex"
      @formElData="formElData"
      :selectType="selectType"
      :values="elvalues"
    ></AddElTem>
  </div>
</template>
<script>
import FormDialog from "@/views/components/form-dialog";
import vocApi from "@/api/voc/discharge";
import AddElTem from "./addEl";
export default {
  components: { FormDialog, AddElTem },
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
  data () {
    return {
      width: "50%",
      form: {
        facilityPid: [],
        techno: [{ techNo: "", techType: "", memo: "", entity: [{}] }],
        elecmeter: [{ elecmeterName: "", entity: [{}, {}] }],
      },
      rules: {
        facilityName: [
          { required: true, message: "治理设施名称必选", trigger: "blur" },
        ],
        facilityPid: [
          { required: true, message: "关联生产线必选", trigger: "blur" },
        ],
      },
      productList: [],
      techTypeList: [
        { value: "voc_uvgj", label: "UV光解" },
        { value: "voc_hxt", label: "活性炭吸附" },
        { value: "voc_chrs", label: "催化燃烧(蓄热燃烧)" },
        { value: "voc_pl", label: "喷淋" },
        { value: "voc_dlz", label: "等离子" },
        { value: "voc_lnhs", label: "冷凝回收" },
        { value: "voc_jdxf", label: "静电吸附" },
      ],
      elVisible: false,
      elIndex: 0,
      elvalues: [],
      selectType: "",
    };
  },
  watch: {
    values: {
      handler (v) {
        if (v) {
          let defaultForm = {
            facilityPid: [],
            techno: [{ techNo: "", techType: "", memo: "", entity: [{}] }],
            elecmeter: [
              {
                elecmeterName: "",
                entity: [
                  { eleTypeId: "e700", eleCode: "e700" },
                  { eleTypeId: "e701", eleCode: "e701" },
                ],
              },
            ],
          };

          this.form = Object.assign(defaultForm, v);
          console.log("form.techno", this.form.techno)
        }
      },
      immediate: true,
    },
  },
  computed: {
    dialogVisible: {
      get () {
        return this.visible || false;
      },
      set (v) {
        this.$emit("update:visible", v);
      },
    },
  },
  created () {
    this.getProductionList();
  },
  methods: {
    submitForm () {
      if (this.valiData()) {
        this.msgError("治理工艺因子信息填写不完整,请完善信息");
      } else {
        if (this.form.elecmeter.every((s) => !!s.elecmeterName)) {
          if (
            this.form.elecmeter.every((s) =>
              s.entity.every(
                (e) =>
                  !!e.eleTypeId && !!e.eleDesc && !!e.eleCode && !!e.collectId
              )
            )
          ) {
            if (this.form.facilityId) {
              vocApi.editFacility(this.form).then((res) => {
                if (res.code === 200) {
                  this.msgSuccess("修改成功");
                  this.$emit("completed");
                } else {
                  this.msgError(res.message);
                }
              });
            } else {
              vocApi.addFacility(this.form).then((res) => {
                if (res.code === 200) {
                  this.msgSuccess("新增成功");
                  this.$emit("completed");
                } else {
                  this.msgError(res.message);
                }
              });
            }
          } else {
            this.msgError("电表因子信息填写不完整");
            return;
          }
        } else {
          this.form.elecmeter = this.form.elecmeter.filter(
            (i) => !!i.elecmeterName
          );
          if (this.form.facilityId) {
            vocApi.editFacility(this.form).then((res) => {
              if (res.code === 200) {
                this.msgSuccess("修改成功");
                this.$emit("completed");
              } else {
                this.msgError(res.message);
              }
            });
          } else {
            vocApi.addFacility(this.form).then((res) => {
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
    },
    valiData () {
      return this.form.techno.some((item) => {
        return item.entity.some((o) => {
          return Object.keys(o).length === 0;
        });
      });
    },
    getProductionList () {
      vocApi.getProduction().then((res) => {
        if (res.code === 200) {
          this.productList = res.data || res.rows;
        }
      });
    },
    addTech () {
      this.form.techno.push({
        techNo: "",
        techType: "",
        memo: "",
        entity: [{}],
      });
    },
    deleteItem (item, index) {
      this.form.techno.splice(index, 1);
    },
    addWatt () {
      this.form.elecmeter.push({
        elecmeterName: "",
        entity: [
          { eleTypeId: "e700", eleCode: "e700" },
          { eleTypeId: "e701", eleCode: "e701" },
        ],
      });
    },
    deleteWatt (item, index) {
      this.form.elecmeter.splice(index, 1);
    },
    handleFacility (index, type) {
      // console.log("index",index)
      // console.log("type",type)
      this.selectType = type;
      this.elIndex = index;
      if (this.selectType === "watt") {
        // if (!this.form.facilityId) {
        //   this.form.elecmeter[index].entity[0].eleTypeId = "e700";
        //   this.form.elecmeter[index].entity[0].eleCode = "e700";
        //   this.form.elecmeter[index].entity[1].eleTypeId = "e701";
        //   this.form.elecmeter[index].entity[1].eleCode = "e701";
        // }
        this.elvalues = JSON.parse(
          JSON.stringify(this.form.elecmeter[index].entity)
        );
      } else {
        let oTechType;
        if (
          new Set([
            "voc_uvgj",
            "voc_pl",
            "voc_dlz",
            "voc_lnhs",
            "voc_jdxf",
            "voc_chrs",
          ]).has(this.selectType)
        ) {
          oTechType = "e702";
        } else if (this.selectType === "voc_hxt") {
          oTechType = "g184";
        }
        if (Object.keys(this.form.techno[index].entity[0]).length === 0) {
          this.form.techno[index].entity[0].eleTypeId = oTechType;
          this.form.techno[index].entity[0].eleCode = oTechType;
        } else if (this.form.techno[index].entity[0].eleTypeId !== oTechType) {
          this.form.techno[index].entity[0].eleTypeId = oTechType;
          this.form.techno[index].entity[0].eleCode = oTechType;
        }

        this.elvalues = JSON.parse(
          JSON.stringify(this.form.techno[index].entity)
        );
      }

      this.elVisible = true;
    },
    formElData (v) {
      if (v.type === "watt") {
        this.form.elecmeter[v.num].entity = v.entity;
      } else {
        this.form.techno[v.num].entity = v.entity;
      }
      this.elVisible = false;
    },
    // selectChangeVi(e, i) {
    //   if (e && this.form.techno[i].techType !== "") {
    //     this.$message({
    //       message: "更换工艺类型,将会清除相关联因子,请谨慎操作",
    //       type: "error",
    //       duration: "10000",
    //       center: true,
    //     });
    //   }
    // },
    onChangeTech (e, i) {
      this.selectType = e;
      console.log("e",e)
      // if (this.form.techno[i].entity.length > 1) {
      // }
      // this.form.techno[i].entity = [{}];
    },
  },
};
</script>
 <style lang="scss" scope>
.info_container {
  display: flex;
  flex-direction: column;
  padding: 5px;
}
.elRow-title {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
  div.el-col:nth-child(1) {
    &:before {
      content: "*";
      color: #ff4949;
      margin-right: 4px;
    }
  }
  div.el-col:nth-child(2) {
    &:before {
      content: "*";
      color: #ff4949;
      margin-right: 4px;
    }
  }
}
.elRow-con {
  display: flex;
  align-items: center;
  margin-bottom: 15px;
}
.elCol-form {
  height: 100%;
  i.reduceEl {
    font-size: 18px;
    color: #f56c6c;
    cursor: pointer;
  }
  .el-form-item {
    margin-bottom: 0px;
  }
}

.elRow-con-1 {
  display: flex;
  align-items: center;
  margin-bottom: 15px;
}
.elCol-form-1 {
  height: 100%;
  .el-form-item {
    display: flex;
  }
  i.reduceEl {
    font-size: 18px;
    color: #f56c6c;
    cursor: pointer;
  }
  .el-form-item {
    margin-bottom: 0px;
  }
}
.elCol-form-2 {
  .el-form-item {
    display: flex;
    .el-form-item__content {
      flex: 1;
      margin-left: 10px !important;
    }
  }
}
.addBtn-zl {
  .el-divider__text {
    position: relative;
    .zl-addBtn {
      position: absolute;
      right: 20px;
      top: -4px;
      button {
        padding: 4px;
        i {
          font-size: 14px;
        }
      }
    }
  }
}
</style>
