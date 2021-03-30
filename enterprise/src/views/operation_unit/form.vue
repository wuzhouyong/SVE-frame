<template>
  <form-dialog
    :title="title"
    :values.sync="formData"
    :rules="rules"
    :submit="submitSatus"
    :visible.sync="dialogVisible"
    @submit="submitForm('TankForm')"
    :width="'500px'"
  >
    <el-row :gutter="15">
      <el-form
        :model="formData"
        :rules="rules"
        size="medium"
        label-width="80px"
        ref="TankForm"
      >
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="运维单位" prop="devopsName">
            <el-input
              v-model="formData.devopsName"
              placeholder="运维单位"
              :style="{ width: '100%' }"
              size="mini"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="运维类型" prop="devopsType">
            <el-select
              v-model="formData.devopsType"
              placeholder="请选择申请类型"
              :style="{ width: '100%' }"
              size="mini"
            >
              <el-option
                v-for="item in operatetype"
                :key="item.value"
                :label="item.label"
                :value="item.value"
              >
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>

        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="联系人" prop="contacts">
            <el-input
              v-model="formData.contacts"
              placeholder="联系人"
              :style="{ width: '100%' }"
              size="mini"
            ></el-input>
          </el-form-item>
        </el-col>

        <el-col :lg="24" :md="24" :sm="24">
          <el-form-item label="联系电话" prop="contactNum">
            <el-input
              v-model="formData.contactNum"
              placeholder="联系电话"
              :style="{ width: '100%' }"
              size="mini"
            ></el-input>
          </el-form-item>
        </el-col>


        <!-- <el-col :lg="12" :md="12" :sm="24">
          <el-form-item label="申请时间" prop="applydate">
            <el-date-picker
              value-format="yyyy-MM-dd"
              v-model="formData.applydate"
              type="date"
              placeholder="选择日期"
            >
            </el-date-picker>
          </el-form-item>
        </el-col> -->
      </el-form>
    </el-row>

    <!-- <n-upload
      v-if="uploadStatus"
      :submitSatus="submitSatus"
      :zoperationid="formData.applyid"
      ref="nUpLoad"
    ></n-upload> -->
    <!-- <el-dialog
      width="30%"
      title="信息"
      v-if="innerVisible"
      :visible.sync="innerVisible"
      append-to-body
    >
      <el-form
        :rules="rulesOther"
        :model="formMsg"
        size="medium"
        label-width="160px"
        ref="message"
      >
        <el-form-item label="排放口名称" prop="checkpointid">
          <el-select
            @change="checkpointidChange"
            v-model="formMsg.checkpointid"
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
        </el-form-item>
        <el-form-item label="设备名称" prop="datadevid">
          <el-select
            @change="equipmentChange"
            v-model="formMsg.datadevid"
            placeholder="请选择设备名称"
            :style="{ width: '100%' }"
            size="mini"
          >
            <el-option
              v-for="item in equipmentEmisson"
              :key="item.datadevid"
              :label="item.datadevdesc"
              :value="item.datadevid"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="其他" prop="other">
          <el-input
            v-model="formMsg.other"
            placeholder="其他"
            :style="{ width: '100%' }"
            size="mini"
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button size="mini" @click="innerVisible = false">取 消</el-button>
        <el-button size="mini" type="primary" @click="selectEquiemnt('message')"
          >确 定</el-button
        >
      </div>
    </el-dialog> -->
  </form-dialog>
</template>
<script>
import {
  operateUnit_add,
  operateUnit_edit,
} from '@/api/operation_unit'
import FormDialog from '@/views/components/form-dialog'
import nUpload from './upload'

export default {
  name: 'element-form',
  components: { FormDialog, nUpload },
  props: {
    title: {
      type: String
    },
    handleType: {
      type: Boolean
    },
    values: {
      type: Object,
      default: () => ({})
    },
    visible: {
      type: Boolean,
      default: false
    },
    submitSatus: {
      type: Boolean,
      default: true
    }
  },
  data() {
    var checkphone = (rule, value, callback) => {
      // let phoneReg = /(^1[3|4|5|6|7|8|9]\d{9}$)|(^09\d{8}$)/;
      if (value == '') {
        callback(new Error('请输入手机号'))
      } else if (!this.isCellPhone(value)) {
        //引入methods中封装的检查手机格式的方法
        callback(new Error('请输入正确的手机号!'))
      } else {
        callback()
      }
    }
    return {
      // 文件id
      id: null,
      // 企业名称下拉
      enterprise: [],
      // 运维类型
      operatetype: [
        {
          value: '0',
          label: '施工单位'
        },
        {
          value: '1',
          label: '施工运维单位'
        },
        {
          value: '2',
          label: '运维单位'
        }
      ],
      formData: {
        // applydate: "",
        devopsType: '',
        devopsName: '',
        contacts: '',
        contactNum: ''
      },
      formMsg: {},

      // 表单校验
      rules: {
        devopsType: [
          {
            required: true,
            message: '运维类型不能为空',
            trigger: 'change'
          }
        ],
        devopsName: [
          {
            required: true,
            message: '运维单位不能为空',
            trigger: 'blur'
          }
        ],
        contacts: [
          {
            required: true,
            message: '联系人不能为空',
            trigger: 'blur'
          }
        ],
        contactNum: [
          {
            required: true,
            message: '联系电话不能为空',
            trigger: 'blur'
          },
          { validator: checkphone, trigger: 'blur' }
          // { validator: validateElement },
        ]
      },

      tableData: [],
      loading: false,
      innerVisible: false
    }
  },
  computed: {
    sys_name() {
      return this.$route.path.substr(this.$route.path.lastIndexOf('/') + 1)
    },
    dialogVisible: {
      get() {
        return this.visible || false
      },
      set(v) {
        this.$emit('update:visible', v)
      }
    }
  },

  mounted() {
    this.init()
  },
  methods: {
    init() {
      // console.log(this.handleType);

      if (!this.handleType) {
        // this.guid();
        // this.formData.applydate = parseTime(new Date()).substr(0, 10);
      } else {
        // this.id = this.values.contractId;

        this.formData = Object.assign(this.formData, this.values)
        // this.zoperationtypeChange(this.formData.zoperationtype);

        console.log('this.formData', this.formData)
      }
      // this.uploadStatus = true;
    },

    submitForm(formName) {
      // console.log(this.formData);
      // return;
      // if (this.$refs.nUpLoad.isFile() && this.formData.detail.length != 0) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          if (!this.formData.devopsId) {
            operateUnit_add(this.formData).then((res) => {
              if (res.code === 200) {
                this.msgSuccess('添加成功')
                this.$emit('completed')
              } else {
                this.msgError(res.message)
              }
            })
          } else {
            operateUnit_edit(this.formData).then((res) => {
              if (res.code === 200) {
                this.msgSuccess('修改成功')
                this.$emit('completed')
              } else {
                this.msgError(res.message)
              }
            })
          }
        }
      })
    },
    // checkpointidChange(value) {
    //   let { emissions } = this;

    //   let filterObj = emissions.find(function (elem) {
    //     return elem.checkpointid == value;
    //   });

    //   console.log("filterObj", filterObj);
    //   this.formMsg = Object.assign(this.formMsg, filterObj);
    //   getEquipmentByEmissions(filterObj.checkpointid).then((res) => {
    //     this.equipmentEmisson = res.data;
    //   });
    // },
    // equipmentChange(value) {
    //   let { equipmentEmisson } = this;
    //   let filterObj = equipmentEmisson.find(function (elem) {
    //     return elem.datadevid == value;
    //   });
    //   this.formMsg = Object.assign(this.formMsg, filterObj);
    // },
    // selectEquiemnt(formName) {
    //   console.log("formMsg", this.formMsg);
    //   let _that = this;
    //   let { formMsg } = this;
    //   this.$refs[formName].validate((valid) => {
    //     if (valid) {
    //       // 判断tableData是否有重复的 排放口和设备
    //       // console.log("this.tableData", this.tableData);
    //       if (_that.tableData.length != 0) {
    //         let select = _that.tableData.some(function (ele, index) {
    //           return (
    //             ele.datadevid == formMsg.datadevid &&
    //             ele.checkpointid == formMsg.checkpointid
    //           );
    //         });
    //         // console.log("select", select);
    //         if (!select) {
    //           _that.tableData.push(formMsg);
    //         }
    //       } else {
    //         _that.tableData.push(formMsg);
    //       }
    //       _that.formData.detail = _that.tableData;
    //       _that.innerVisible = false;
    //       _that.formMsg = {};
    //     }
    //     if (valid) {
    //     }
    //   });
    // },
    // del(row) {
    //   console.log("row", row);
    //   let arr = this.tableData;
    //   console.log("arr", arr);

    //   for (let i = 0; i < arr.length; i++) {
    //     if (
    //       arr[i].datadevid == row.datadevid &&
    //       arr[i].checkpointid == row.checkpointid
    //     ) {
    //       arr.splice(i, 1);
    //     }
    //   }
    //   this.tableData = arr;
    // },
    guid() {
      let s = []
      let hexDigits = '0123456789abcdef'
      for (var i = 0; i < 36; i++) {
        s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1)
      }
      s[14] = '4' // bits 12-15 of the time_hi_and_version field to 0010
      s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1) // bits 6-7 of the clock_seq_hi_and_reserved to 01
      s[8] = s[13] = s[18] = s[23] = '-'
      this.formData.applyid = s.join('')
    },
    // applyChange(val) {
    //   getEuqiumentList(val).then((res) => {
    //     console.log(res);
    //   });
    // },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.selectRows = selection
    },
    // handleAddEquipment() {
    //   this.innerVisible = true;
    // },
    //检查手机号
    isCellPhone(val) {
      if (!/^1(3|4|5|6|7|8)\d{9}$/.test(val)) {
        return false
      } else {
        return true
      }
    }
  }
}
</script>
