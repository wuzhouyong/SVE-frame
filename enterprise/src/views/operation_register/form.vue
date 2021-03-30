<template>
  <form-dialog
    :title="title"
    :values.sync="formData"
    :rules="rules"
    label-width="130px"
    :visible.sync="dialogVisible"
    @submit="submitForm"
    :width="'500px'"
  >
    <el-row :gutter="15">
      <el-form
        :model="formData"
        :rules="rules"
        size="medium"
        label-width="110px"
        ref="TankForm"
      >
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="合同标题" prop="projectTitle">
            <el-input
              v-model="formData.projectTitle"
              placeholder="合同标题"
              :style="{ width: '100%' }"
              size="mini"
            ></el-input>
          </el-form-item>
        </el-col>

        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="运维单位" prop="opSourceId">
            <el-select
              v-model="formData.opSourceId"
              placeholder="请选择运维单位"
              filterable
              :style="{ width: '100%' }"
              size="mini"
            >
              <el-option
                v-for="item in operatUnit"
                :key="item.opSourceId"
                :label="item.fullName"
                :value="item.opSourceId"
              >
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="监测任务" prop="tagHeadId">
            <el-select
              v-model="formData.tagHeadId"
              @change="tagChange"
              placeholder="请选择监测任务"
              :style="{ width: '100%' }"
              size="mini"
            >
              <el-option
                v-for="item in tagHeadsArr"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :lg="24" :md="24" :sm="24" v-if="isCollect">
          <el-form-item label="检测井" prop="collectId">
            <el-select
              v-model="formData.collectId"
              placeholder="请选择检测井"
              :style="{ width: '100%' }"
              size="mini"
            >
              <el-option
                v-for="item in collectList"
                :key="item.id"
                :label="item.name"
                :value="item.id"
              >
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <!--        <el-col :lg="24" :md="24" :sm="24">-->
        <!--          <el-form-item label="因子管理" prop="elements">-->
        <!--            <el-select-->
        <!--              filterable-->
        <!--              multiple-->
        <!--              v-model="formData.elements"-->
        <!--              placeholder="因子管理"-->
        <!--              :style="{ width: '100%' }"-->
        <!--              @change="onChange"-->
        <!--              collapse-tags-->
        <!--              size="mini"-->
        <!--            >-->
        <!--              <el-checkbox v-model="checked" @change="selectAll" class="allSelect">全选</el-checkbox>-->
        <!--              <el-option-->
        <!--                v-for="item in elementList"-->
        <!--                :key="item.cpElemId"-->
        <!--                :label="`【 ${item.collectName} 】${item.elementDesc}(${item.elementCode})`"-->
        <!--                :value="item.cpElemId"-->
        <!--              >-->
        <!--              </el-option>-->
        <!--            </el-select>-->
        <!--          </el-form-item>-->
        <!--        </el-col>-->
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="合同开始时间" prop="startDate">
            <el-date-picker
              value-format="yyyy-MM-dd"
              v-model="formData.startDate"
              style="width:100%"
              size="mini"
              type="date"
              placeholder="合同开始时间"
            >
            </el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="合同结束时间" prop="endDate">
            <el-date-picker
              value-format="yyyy-MM-dd"
              v-model="formData.endDate"
              style="width:100%"
              size="mini"
              type="date"
              placeholder="合同结束时间"
            >
            </el-date-picker>
          </el-form-item>
        </el-col>

        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="备注" prop="memo">
            <el-input
              type="textarea"
              v-model="formData.memo"
              placeholder="备注"
              :style="{ width: '100%' }"
              size="mini"
            ></el-input>
          </el-form-item>
        </el-col>
      </el-form>
    </el-row>
    <n-upload
      ref="nUpLoad"
    ></n-upload>
  </form-dialog>
</template>
<script>
import {
  operateRegister_add,
  operateRegister_edit,
  operateUnitList,
  elementUnitList
} from "@/api/operation_unit";
import {outwaterList} from "@/api/collect";
import FormDialog from "@/views/components/form-dialog";
import {getSourceTags} from "@/api/menu";
import nUpload from "./upload";

export default {
  components: {FormDialog, nUpload},
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
    }
  },
  data () {
    return {
      // 企业名称下拉
      enterprise: [],
      tagHeadsArr: [],
      collectList: [],
      isCollect: false,
      operatUnit: [],
      // 运营单位列表
      unitList: [],
      // 因子列表
      elementList: [],
      formData: {
        tagHeadId: "",
        opSourceId: ""
      },
      formMsg: {},
      checked: false,
      // 表单校验
      rules: {
        projectTitle: [
          {
            required: true,
            message: "合同标题不能为空",
            trigger: "blur"
          }
        ],
        opSourceId: [
          {
            required: true,
            message: "运维单位不能为空",
            trigger: "change"
          }
        ],
        tagHeadId: [
          {
            required: true,
            message: "监测任务不能为空",
            trigger: "change"
          }
        ],
        collectId: [
          {
            required: true,
            message: "检测井必填",
            trigger: "blur"
          }
        ],
        startDate: [
          {
            required: true,
            message: "必填",
            trigger: "change"
          }
        ],
        endDate: [
          {
            required: true,
            message: "必填",
            trigger: "change"
          }
        ]
      },
      sysItems: {
        1002: {
          name: "四大流域过程监控",
          path: "guocheng"
        },
        1003: {
          name: "零星废水产生单位监控",
          path: "lingxing"
        },
        1004: {
          name: "市级以上VOCs过程监控",
          path: "voc"
        },
        1006: {
          name: "餐饮行业过程及末端监控",
          path: "youyan"
        },
        1012: {
          name: "重点排水户末端监控",
          path: "outwater"
        },
        1013: {
          name: "汽修行业过程监控",
          path: "qixiu"
        },
        1014: {
          name: "加油站",
          path: "jiayou"
        },
        1015: {
          name: "金属表面处理行业",
          path: "jinshu"
        },
        1019: {
          name: "造纸行业过程监控",
          path: "guocheng"
        }
      },
      tableData: [],
      selectedArray: [],
      loading: false
    };
  },
  computed: {
    dialogVisible: {
      get () {
        return this.visible || false;
      },
      set (v) {
        this.$emit("update:visible", v);
      }
    }
  },
  mounted () {
    this.init();
  },
  methods: {
    init () {
      let _that = this;
      getSourceTags().then((res) => {
        this.tagHeadsArr = Object.keys(_that.sysItems)
          .filter((f) => res.data.some((s) => s === f))
          .map(function (item) {
            let obj = {};
            obj.value = parseInt(item);
            obj.label = _that.sysItems[item].name;
            obj.path = _that.sysItems[item].path;
            return obj;
          });
      });
      operateUnitList().then((res) => {
        this.operatUnit = res.data;
      });
    },
    submitForm () {
      if (!this.$refs.nUpLoad.isFile()) {
        this.msgError("附件必须上传!");
        return;
      }
      this.$confirm("运维合同登记提交后无法修改,确定提交吗?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "info"
      })
        .then(() => {
          this.$refs["TankForm"].validate((valid) => {
            if (!valid) return;
            operateRegister_add(this.formData).then((res) => {
              if (res.code === 200) {
                this.msgSuccess("添加成功");
                this.$refs.nUpLoad.isUpload(res.data);
                this.$emit("completed");
              } else {
                this.msgError(res.message);
              }
            });
          });
        });
    },
    tagChange (v) {
      if (v === 1012) {
        this.isCollect = true;
        this.getCollect();
      } else {
        this.isCollect = false;
        this.collectList = [];
      }
    },
    getCollect () {
      outwaterList().then(res => {
        this.collectList = res.rows;
      });
    },
    // tagHeadChange () {
    //   this.checked = false;
    //   let {tagHeadId} = this.formData;
    //   elementUnitList({tagHeadId}).then((res) => {
    //     this.formData.elements = [];
    //     this.elementList = res.data;
    //   });
    // },
    // selectAll () {
    //   this.selectedArray = [];
    //   this.formData.elements = [];
    //   if (this.checked) {
    //     this.elementList.map((item) => {
    //       this.selectedArray.push(item.cpElemId);
    //     });
    //     this.formData.elements = this.selectedArray;
    //   } else {
    //     this.selectedArray = [];
    //     this.formData.elements = [];
    //   }
    // },
    // onChange (v) {
    //   this.checked = v.length === this.elementList.length ? true : false;
    // }
  }
};
</script>
<style lang="scss" scoped>
.allSelect {
  text-align: right;
  width: 100%;
  padding-right: 10px;
}
</style>
