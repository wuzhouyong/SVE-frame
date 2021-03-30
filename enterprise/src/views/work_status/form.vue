<template>
  <form-dialog
    :title="title"
    :values.sync="form"
    :rules="rules"
    label-width="130px"
    :visible.sync="dialogVisible"
    @submit="submitForm"
  >
    <el-form-item label="监控任务" prop="tagHeadId">
      <el-select
        :disabled="isEdit"
        v-model="form.tagHeadId"
        placeholder="请选择监控任务"
        :style="{width: '100%'}"
        size="mini"
        @change="tagChange"
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
    <el-form-item label="排放口名称" prop="collectId" v-if="isDgOm">
      <el-select
        v-if="!isEdit"
        v-model="form.collectId"
        placeholder="请选择排放口名称"
        :style="{ width: '100%' }"
        size="mini"
      >
        <el-option
          v-for="item in emissions"
          :key="item.checkpointid"
          :label="item.checkpointdesc"
          :value="item.checkpointid"
        >
        </el-option>
      </el-select>
      <el-input v-else :disabled="isEdit" v-model="form.collectName" size="mini"/>
    </el-form-item>
    <el-form-item label="标记状态" prop="workStatus">
      <el-select
        :disabled="isEdit"
        v-model="form.workStatus"
        placeholder="请选择标记状态"
        :style="{width: '100%'}"
        @change="workStatusChange"
        size="mini"
      >
        <el-option v-for="i in wrokStatusDict" :key="Number(i.dictValue)" :label="i.dictLabel"
                   :value="Number(i.dictValue)"></el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="时间区间" prop="oTime">
      <el-date-picker
        size="mini"
        style="width:100%"
        v-model="form.oTime"
        :picker-options="isFault?pickerOptions:defaultOptions"
        value-format="yyyy-MM-dd HH"
        format="yyyy-MM-dd HH"
        type="datetimerange"
        range-separator="至"
        start-placeholder="开始日期"
        end-placeholder="结束日期"
      >
      </el-date-picker>
    </el-form-item>
    <el-form-item label="申报说明" prop="explain">
      <el-input type="textarea" v-model="form.explain" placeholder="请输入申报说明" size="mini"/>
    </el-form-item>
    <n-upload :refId="refId" ref="nUpload"></n-upload>
  </form-dialog>
</template>
<script>
import FormDialog from "@/views/components/form-dialog";
import {getSourceTags} from "@/api/menu";
import {workStatusAdd, workStatusEdit} from "@/api/workstatus";
import {task_status_systems} from "@/config";
import {getEmissions} from "@/api/dgom";
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
      defaultOptions: {
        disabledDate: () => {
          return false;
        }
      },
      choiceDate: "",
      isFault: false,
      refId: null,
      // 表单参数
      form: {
        tagHeadId: null,
        workStatus: null,
        oTime: null,
        explain: null
      },
      isEdit: false,
      tagHeadsArr: [],
      emissions: [],
      wrokStatusDict: [],
      isDgOm: false,
      // 表单校验
      rules: {
        tagHeadId: [
          {
            required: true,
            message: "监控任务不能为空",
            trigger: "blur"
          }
        ],
        collectId: [
          {
            required: true,
            message: "排放口必选",
            trigger: "blur"
          }
        ],
        workStatus: [
          {
            required: true,
            message: "工况状态不能为空",
            trigger: "blur"
          }
        ],
        oTime: [
          {
            required: true,
            message: "时间区间不能为空",
            trigger: "blur"
          }
        ],
        explain: [
          {
            required: true,
            message: "申报说明不能为空",
            trigger: "blur"
          }
        ]
      }
    };
  },
  computed: {
    //设置选择日期最大区间,上下5天,故障不能选今天之后
    pickerOptions () {
      let that = this;
      return {
        onPick ({maxDate, minDate}) {
          that.choiceDate = minDate.getTime();
          if (maxDate) {
            that.choiceDate = "";
          }
        },
        disabledDate (time) {
          if (!that.isNull(that.choiceDate)) {
            const one = 4 * 24 * 3600 * 1000;
            const minTime = that.choiceDate - one;
            const maxTime = that.choiceDate + one;
            return time.getTime() < minTime || time.getTime() > maxTime;
          } else {
            return time.getTime() > Date.now() - 8.64e6;
          }
        }
      };
    },
    dialogVisible: {
      get () {
        return this.visible || false;
      },
      set (v) {
        this.$emit("update:visible", v);
      }
    }
  },
  watch: {
    values: {
      handler (v) {
        this.refId = v.workId;
        if (v.tagHeadId === 1001) {
          this.isDgOm = true;
          getEmissions().then((res) => {
            this.emissions = res.data;
          });
        } else {
          this.isDgOm = false;
        }
        if (v.workId) {
          this.form = Object.assign({}, v);
          this.form.oTime = [v.startTime, v.endTime];
          this.isEdit = true;
        } else {
          this.isEdit = false;
        }
      },
      immediate: true
    }
  },
  created () {
    this.init();
    this.getDicts("work_status").then((res) => {
      this.wrokStatusDict = res.data;
    });
  },
  methods: {
    isNull (arg1) {
      return !arg1 && arg1 !== 0 && typeof arg1 !== "boolean" ? true : false;
    },
    workStatusChange (v) {
      this.isFault = v === 2 ? true : false;
      this.form.oTime = null;
    },
    submitForm () {
      if (this.$refs.nUpload.isUpload()) {
        if (this.form.workId) {
          let oV = JSON.parse(JSON.stringify(this.form));
          oV.startTime = this.form.oTime[0];
          oV.endTime = this.form.oTime[1];
          workStatusEdit(oV).then(res => {
            if (res.code === 200) {
              this.msgSuccess("编辑成功");
              this.$emit("completed");
            }
          });
        } else {
          let {tagHeadId, workStatus, oTime, explain} = this.form;
          let oV = {
            tagHeadId: tagHeadId,
            workStatus: workStatus,
            startTime: oTime[0],
            endTime: oTime[1],
            explain: explain,
            auditStatus: 1
          };
          if (this.form.collectId) {
            oV.collectId = this.form.collectId;
            let collectName = this.emissions.filter(i => i.checkpointid === this.form.collectId)[0].checkpointdesc;
            oV.collectName = collectName;
          }
          workStatusAdd(oV).then(res => {
            if (res.code === 200) {
              this.$refs.nUpload.uploadSub(res.data);
              this.msgSuccess("添加成功");
              this.$emit("completed");
            }
          });
        }
      } else {
        this.msgError("附件必选上传");
      }
    },
    init () {
      getSourceTags().then((res) => {
        this.tagHeadsArr = Object.keys(task_status_systems)
          .filter((f) => res.data.some((s) => s === f))
          .map(function (item) {
            let obj = {};
            obj.value = parseInt(item);
            obj.label = task_status_systems[item].name;
            obj.path = task_status_systems[item].path;
            return obj;
          });
      });
    },
    //监听如果是在线监控的,让其选排放口
    tagChange (v) {
      if (v === 1001) {
        this.isDgOm = true;
        getEmissions().then((res) => {
          this.emissions = res.data;
        });
      } else {
        this.isDgOm = false;
      }
    }
  }
};
</script>
