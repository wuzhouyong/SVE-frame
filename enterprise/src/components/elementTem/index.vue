<template>
  <div class="element-form-wk">
    <el-button
      icon="el-icon-plus"
      circle
      type="primary"
      title="增加"
      @click="addEl"
      class="addEl"
    ></el-button>
<!--    v-if="elList.length < eloptions.length && isAdd || selectType=='voc_hxt'"-->
    <el-divider content-position="left">因子管理</el-divider>
    <div class="element-form-con">
      <div class="element-form-label">
        <p>因子类型</p>
        <p>因子名称</p>
        <p>因子编码</p>
        <p>数采名称</p>
        <p>安装位置</p>
      </div>
      <div class="elAssem-wk" v-for="(o, index) in this.elList" :key="index">
        <elAssem
          v-bind="$attrs"
          @change="onChange"
          :i="index"
          :value="o"
          :eloption="newData"
        ></elAssem>
        <i
          class="el-icon-remove reduceEl"
          title="减少"
          @click="reduceEl(index)"
          v-show="elList.length > 1 && index !== 0"
        ></i>
      </div>
    </div>
  </div>
</template>
<script>
import elAssem from "./elAssem";
export default {
  components: { elAssem },
  model: {
    prop: "value",
    event: "change",
  },
  props: {
    isAdd: {
      type: Boolean,
      default: true,
    },
    value: {
      type: Array,
    },
    eloptions: {
      type: Array,
      default: () => {
        [];
      },
    },
    selectType: {
      type: String,
    },
  },
  data() {
    return {
      elList: this.value || [{}],
      eloption: [],
      newData: [],
    };
  },
  watch: {
    eloptions: {
      handler(v) {
        this.eloption = v;
        this.newData = JSON.parse(JSON.stringify(this.eloption));
      },
      immediate: true,
      deep: true,
    },
  },
  methods: {
    onChange(v) {
      console.log("selectTypeselectType", this.selectType);
      this.elList.forEach((i) => {
        if (i.cpElemId) i.id = i.cpElemId;
      });
      this.elList[v.index] = v;
      let o = JSON.parse(JSON.stringify(this.eloption));

      if (this.selectType == "voc_hxt") {
        o.forEach((m) => {
          // m.disabled = this.elList.some((o) => o.eleTypeId === m.value);
        });
      } else {
        o.forEach((m) => {
          m.disabled = this.elList.some((o) => o.eleTypeId === m.value);
        });
      }

      this.newData = o;
      console.log("this.newData", this.newData);
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
  },
};
</script>
<style lang="scss">
.element-form-wk {
  position: relative;
  button.addEl {
    position: absolute;
    right: 5px;
    top: -16px;
    font-weight: bold;
    font-size: 14px;
    z-index: 99;
    cursor: pointer;
  }
  .element-form-con {
    width: 100%;
    height: 100%;
    padding-right: 20px;
    .element-form-label {
      width: 100%;
      height: 100%;
      display: flex;
      p {
        flex: 1;
        margin: 0;
        margin-right: 10px;
        font-size: 12px;
        &:before {
          content: "*";
          color: #ff4949;
          margin-right: 4px;
        }
        &:last-child {
          margin-right: 0px;
          &:before {
            content: "";
            color: transparent;
            margin-right: 0px;
          }
        }
      }
    }
  }
  .elAssem-wk {
    width: 100%;
    height: auto;
    position: relative;
    i.reduceEl {
      font-size: 20px;
      position: absolute;
      right: -26px;
      top: 4px;
      color: #f56c6c;
      cursor: pointer;
    }
  }
}
</style>
