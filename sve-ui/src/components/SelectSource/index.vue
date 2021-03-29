<template>
  <el-select
    :value="value"
    remote
    filterable
    reserve-keyword
    placeholder="输入关键字搜索企业"
    :remote-method="remoteMethod"
    size="mini"
    style="width:100%"
    @change="onChange"
  >
    <el-option v-for="item in options" :key="item.id" :label="item.name" :value="item.id"></el-option>
  </el-select>
</template>
<script>
// import { getSourceList } from "@/api/outwater";
import { mapState, mapActions } from "vuex";
import prevent from "@/utils/preventReClick";
import Cookies from "js-cookie";

export default {
  props: {
    value: {
      type: String
    },
    data: {
      type: Function
    },
    isPlatName: {
      type: String,
      default: ""
    }
  },
  data() {
    return {
      loading: true,
      options: [],
      pagination: {
        pageNum: 1,
        pageSize: 30
      }
    };
  },
  computed: {
    ...mapState("company", ["searchCompanyList"])
  },
  watch: {
    value: {
      immediate: true,
      handler(v) {
        if (v) {
          this.getSource({ sourceId: v });
        }
      }
    },
    isPlatName: {
      handler(v) {
        this.options = this.searchCompanyList.filter((c) => c.sys === v);
        // let systemKey = Cookies.get("system-key");
        // console.log(systemKey);
        // this.options = this.searchCompanyList.filter(
        //   (c) => c.sys === systemKey
        // );
      },
      immediate: true
    }
  },
  // created() {
  //   let systemKey = Cookies.get("system-key");
  //   this.options = this.searchCompanyList.filter((c) => c.sys === systemKey);
  // },
  methods: {
    ...mapActions("company", ["SaveSearchCompany"]),
    //获取企业列表
    getSource(v = {}, isSearch = false) {
      this.data(v, this.urlParameter(this.pagination), this.isPlatName).then(
        (res) => {
          if (res.code === 200) {
            if (isSearch === true) {
              this.options = res.rows;
            } else {
              let oV = res.rows[0];
              let vDetails = {
                name: oV.name,
                id: oV.id,
                sys: this.isPlatName
              };
              this.SaveSearchCompany(vDetails);
              this.options = this.searchCompanyList.filter(
                (c) => c.sys === this.isPlatName
              );
            }
          }
        }
      );
      // let systemKey = Cookies.get("system-key");
      // if (this.isPlatName !== "") {
      //   this.data(v, this.urlParameter(this.pagination), this.isPlatName).then(
      //     (res) => {
      //       if (res.code === 200) {
      //         if (isSearch === true) {
      //           this.options = res.rows;
      //         } else {
      //           let oV = res.rows[0];
      //           let vDetails = {
      //             name: oV.name,
      //             id: oV.id,
      //             sys: this.isPlatName,
      //           };
      //           this.SaveSearchCompany(vDetails);
      //           this.options = this.searchCompanyList.filter(
      //             (c) => c.sys === systemKey
      //           );
      //         }
      //       }
      //     }
      //   );
      // } else {
      //   this.data(v, this.urlParameter(this.pagination)).then((res) => {
      //     if (res.code === 200) {
      //       if (isSearch === true) {
      //         this.options = res.rows;
      //       } else {
      //         let oV = res.rows[0];
      //         let vDetails = {
      //           name: oV.name,
      //           id: oV.id,
      //           sys: this.isPlatName,
      //         };
      //         this.SaveSearchCompany(vDetails);
      //         this.options = this.searchCompanyList.filter(
      //           (c) => c.sys === systemKey
      //         );
      //       }
      //     }
      //   });
      // }
    },
    //动态污染源搜索
    // remoteMethod: prevent.throttle(function(query) {
    //   if (query !== "") {
    //     let oVal = { sourceName: query };
    //     this.getSource(oVal, true);
    //   } else {
    //     // let systemKey = Cookies.get("system-key");
    //     this.options = this.searchCompanyList.filter(
    //       (c) => c.sys === this.isPlatName
    //     );
    //   }
    // }, 80),
    remoteMethod(query){
      if (query !== "") {
        let oVal = { sourceName: query };
        this.getSource(oVal, true);
      } else {
        this.options = this.searchCompanyList.filter(
          (c) => c.sys === this.isPlatName
        );
      }
    },
    onChange(v) {
      // let systemKey = Cookies.get("system-key");
      let oV = this.ergodic(v);
      let vDetails = {
        name: oV.name,
        id: oV.id,
        sys: this.isPlatName
      };
      this.SaveSearchCompany(vDetails);
      this.$emit("input", v);
      this.$parent.$emit("validate", v);
    },
    //遍历获取企业数据
    ergodic(v) {
      let obj = {};
      obj = this.options.find((item) => {
        return item.id == v; //筛选出匹配数据
      });
      return obj;
    }
  }
};
</script>
