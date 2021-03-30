<template>
  <div class="el-assem">
    <ul>
      <li>
        <el-select
          v-model="obj.eleTypeId"
          placeholder="请选择"
          size="mini"
          :style="{ width: '100%' }"
          @change="eleTypeChange"
        >
          <el-option
            v-for="item in eloption"
            :key="item.value"
            :label="item.label + '(' + item.value + ')'"
            :value="item.value"
          ></el-option>
<!--          :disabled="new Set(isRe).has(obj.eleTypeId)"-->
          <!--          :disabled="new Set(isRe).has(item.value) || item.disabled"-->
        </el-select>
      </li>
      <li>
        <el-input
          v-model="obj.eleDesc"
          placeholder="因子名称"
          size="mini"
          :style="{ width: '100%' }"
          @change="onChange"
        ></el-input>
      </li>
      <li>
        <el-input
          v-model="obj.eleCode"
          placeholder="因子编码"
          size="mini"
          maxlength="6"
          show-word-limit
          :style="{ width: '100%' }"
          @change="onChange"
        ></el-input>
      </li>
      <li>
        <el-select
          v-model="obj.collectId"
          placeholder="请选择数采"
          size="mini"
          :style="{ width: '100%' }"
          @change="onChange"
        >
          <el-option
            v-for="item in collects"
            :key="item.collectId"
            :label="item.cpName"
            :value="item.collectId"
          ></el-option>
        </el-select>
      </li>
      <li>
        <el-input
          v-model="obj.installLocation"
          placeholder="安装位置"
          size="mini"
          :style="{ width: '100%' }"
          @change="onChange"
        ></el-input>
      </li>
    </ul>
  </div>
</template>
<script>
export default {
  props: {
    eloption: {
      type: Array,
      default: () => {
        [];
      },
    },
    collects: {
      type: Array,
      default: () => {
        [];
      },
    },
    i: {
      type: Number,
      default: 0,
    },
    value: {
      type: Object,
      default: () => {
      },
    },
  },
  watch: {
    value: {
      handler () {
        this.isValue();
      },
      immediate: true,
      deep: true,
    },
  },
  data () {
    return {
      obj: {
        eleTypeId: "",
        eleDesc: "",
        eleCode: "",
        installLocation: "",
        collectId: "",
      },
      isRe: ["e702", "g184"],
    };
  },
  methods: {
    eleTypeChange (v) {
      this.obj.eleCode = v;
      this.onChange();
    },
    onChange () {
      let elementData = {
        eleTypeId: this.obj.eleTypeId,
        eleDesc: this.obj.eleDesc,
        eleCode: this.obj.eleCode,
        installLocation: this.obj.installLocation,
        collectId: this.obj.collectId,
        index: this.i,
      };
      if (this.value.cpElemId) {
        let oVal = {...{index: this.i}, ...this.value, ...this.obj};
        this.$emit("change", oVal);
      } else {
        elementData.id =
          this.value && this.value.cpElemId ? this.value.cpElemId : this.i;
        this.$emit("change", elementData);
      }

      // elementData.cpElemId =
      //   this.value && this.value.cpElemId ? this.value.cpElemId : this.i;
      // this.$emit("change", elementData);
    },
    isValue () {
      if (this.value) {
        this.obj = {...{}, ...this.obj, ...this.value};
      }
    },
  },
};
</script>
<style lang="scss">
.el-assem {
  ul {
    padding: 0;
    margin: 0;
    display: flex;

    li {
      list-style-type: none;
      flex: 1;
      margin: 0;
      margin-right: 10px;
      font-size: 12px;

      &:last-child {
        margin-right: 0px;
      }
    }
  }
}
</style>
