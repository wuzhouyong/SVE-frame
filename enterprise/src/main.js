import Vue from "vue";

import Cookies from "js-cookie";

import "normalize.css/normalize.css"; // a modern alternative to CSS resets

import Element from "element-ui";
import "./assets/styles/element-variables.scss";

import "@/assets/styles/index.scss"; // global css
import "@/assets/styles/ruoyi.scss"; // ruoyi css
import App from "./App";
import store from "./store";
import router from "./router";
import permission from "./directive/permission";

import "./assets/icons"; // icon
import "./permission"; // permission control
import { getDicts } from "@/api/system/dict/data";
import {
  parseTime,
  resetForm,
  addDateRange,
  selectDictLabel,
  download,
  handleTree,
  urlParameter
} from "@/utils/ruoyi";
import Pagination from "@/components/Pagination";

// 全局方法挂载
Vue.prototype.getDicts = getDicts;
Vue.prototype.parseTime = parseTime;
Vue.prototype.resetForm = resetForm;
Vue.prototype.addDateRange = addDateRange;
Vue.prototype.selectDictLabel = selectDictLabel;
Vue.prototype.download = download;
Vue.prototype.handleTree = handleTree;
Vue.prototype.urlParameter = urlParameter;
Vue.prototype.msgSuccess = function (msg) {
  this.$message({ showClose: true, message: msg, type: "success" });
};

Vue.prototype.msgError = function (msg) {
  this.$message({ showClose: true, message: msg, type: "error" });
};

Vue.prototype.msgInfo = function (msg) {
  this.$message.info(msg);
};

Vue.prototype.$alertMsgBox = function alert(
  //这些都是参数 不传参的时候默认显示以下定义的
  msg = "是否确认新建",
  title = "系统提示",
  title1 = "此项为新建一名用户此项为新建一名用户此项为新建一名用户此项为新建一名用户此项为新建一名用户此项为新建一名用户此项为新建一名用户",
  iconType = "el-icon-warning", //图标类型
  iconColor = "#ccc", //图标颜色
  settings = {}
) {
  Object.assign(settings, {
    //合并对象
    confirmButtonText: "确定",
    cancelButtonText: "取消",
    dangerouslyUseHTMLString: true //允许确认框内容为html格式
  });
  return this.$confirm(
    //定义样式已经设置为html格式，可以自定义( 这里我直接写了内联，哈哈哈哈 )
    `
          <div style="height:94px;margin:auto;border-bottom:1px solid #ccc;display:flex;justify-content: center;">
           
            <span style="display:block;align-self: center;margin-left:5px">
              <p style="font-weight:bold;color: #6C6C6C;font-size: 15px;">${msg}</p>
              <p style="color: #ABA5A5;">${title1}</p>
            </span>
          </div>
        `,
    //把定义的参数反出去
    title,
    settings,
    title1,
    iconType,
    iconColor
  );
};
// 全局组件挂载
Vue.component("Pagination", Pagination);

Vue.use(permission);

/**
 * If you don't want to use mock-server
 * you want to use MockJs for mock api
 * you can execute: mockXHR()
 *
 * Currently MockJs will be used in the production environment,
 * please remove it before going online! ! !
 */

Vue.use(Element, {
  size: Cookies.get("size") || "medium" // set element-ui default size
});

Vue.config.productionTip = false;

new Vue({
  el: "#app",
  router,
  store,
  render: h => h(App)
});
