<template>
  <div style="display:inline-block">
    <el-form-item v-for="item in formItem" :label="item.label" :prop="item.prop" :key="item.prop">
      <!-- 日期 -->
      <el-date-picker
        v-if="item.type==='Date'"
        type="date"
        placeholder="选择日期"
        v-model="formData[item.prop]"
        :clearable="false"
        :picker-options="pickerOptions"
        value-format="yyyy-MM-dd"
        style="width:150px"
      ></el-date-picker>
      <!-- 时间 -->
      <el-time-select
        v-if="item.type==='Time'"
        placeholder="选择时间"
        :clearable="false"
        v-model="formData[item.prop]"
        style="width:120px"
        :picker-options="{
            start: '00:00',
            step: '00:30',
            end: '23:30'
          }"
      ></el-time-select>
      <!-- 区间日期 -->
      <el-date-picker
        v-if="item.type==='DateRange'"
        v-model="formData[item.prop]"
        :clearable="false"
        style="width:280px"
        :picker-options="pickerOptions"
        value-format="yyyy-MM-dd"
        type="daterange"
        range-separator="至"
        start-placeholder="开始日期"
        end-placeholder="结束日期"
      ></el-date-picker>
      <!-- 下拉 -->
      <el-select
        v-model="formData[item.prop]"
        :placeholder="item.placeholder"
        size="mini"
        v-if="item.type==='Select'"
      >
        <el-option :label="i.name" :value="i.id" v-for="i in item.list" :key="i.id"></el-option>
      </el-select>
    </el-form-item>
  </div>
</template>
<script>
export default {
  props: {
    formItem: {
      type: Array,
      default: () => []
    },
    formData: {
      type: Object,
      default: () => ({})
    }
  },
  data() {
    return {
      pickerOptions: {
        disabledDate: this.disabledDate
      }
    };
  },
  methods: {
    disabledDate(time) {
      return time.getTime() > Date.now() - 8.64e6;
    }
  }
};
</script>