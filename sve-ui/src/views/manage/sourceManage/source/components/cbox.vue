<template>
  <div style="height:33px">
    <el-col :span="8">
      <!-- <el-checkbox-group v-model="checkList">
        <el-checkbox :label="oI.id"></el-checkbox>
      </el-checkbox-group>-->
      <el-checkbox v-model="isCheck" @change="chekcChange" style="width:100%">{{oI.tagHeadName}}</el-checkbox>
    </el-col>
    <el-col :span="12" v-if="isCheck" style="text-align: right;">
      <!-- <el-input v-model="reSystemId" placeholder="业务子系统id" @change="oInputBlur" v-show="false"></el-input> -->
      <el-radio-group v-model="listType" size="mini" @change="oInputBlur">
        <el-radio-button :label="0">考核名单</el-radio-button>
        <el-radio-button :label="1">非考核名单</el-radio-button>
      </el-radio-group>
    </el-col>
  </div>
</template>
<script>
export default {
  props: {
    value: {
      type: Object
    },
    oI: {
      type: Object
    },
    relationSourceId: {
      type: String
    }
  },

  watch: {
    value: {
      handler() {
        this.isValue();
      },
      immediate: true
      // this.isCheck = v.tagHeadId === this.oI.id ? true : false;
      // this.reSystemId = v.bizSourceId;
    }
  },
  data() {
    return {
      isCheck: false,
      reSystemId: "",
      listType: 1
    };
  },
  methods: {
    isValue() {
      if (this.value !== undefined) {
        this.isCheck = this.value.tagHeadId === this.oI.tagHeadId ? true : false;
        this.reSystemId = this.value.bizSourceId;
        this.listType = this.value.listType;
      } else {
        this.listType = 1;
      }
    },
    chekcChange(v) {
      this.isCheck = v;
      this.onChange();
    },
    oInputBlur(e) {
      this.reSystemId = e;
      this.onChange();
    },
    onChange() {
      let obj = {
        bizSourceId: this.reSystemId,
        pfSourceId: this.relationSourceId,
        tagHeadId: this.oI.tagHeadId,
        isCheck: this.isCheck,
        listType: this.listType
      };
      this.$emit("change", obj);
      this.$parent.$emit("validate", obj);
    }
  }
};
</script>
