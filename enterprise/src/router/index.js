import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

/* Layout */
import Layout from "@/layout";
import Base from "@/layout/base";

/**
 * Note: 路由配置项
 *
 * hidden: true                   // 当设置 true 的时候该路由不会再侧边栏出现 如401，login等页面，或者如一些编辑页面/edit/1
 * alwaysShow: true               // 当你一个路由下面的 children 声明的路由大于1个时，自动会变成嵌套的模式--如组件页面
 *                                // 只有一个时，会将那个子路由当做根路由显示在侧边栏--如引导页面
 *                                // 若你想不管路由下面的 children 声明的个数都显示你的根路由
 *                                // 你可以设置 alwaysShow: true，这样它就会忽略之前定义的规则，一直显示根路由
 * redirect: noRedirect           // 当设置 noRedirect 的时候该路由在面包屑导航中不可被点击
 * name:'router-name'             // 设定路由的名字，一定要填写不然使用<keep-alive>时会出现各种问题
 * meta : {
    roles: ['admin','editor']    // 设置该路由进入的权限，支持多个权限叠加
    title: 'title'               // 设置该路由在侧边栏和面包屑中展示的名字
    icon: 'svg-name'             // 设置该路由的图标，对应路径src/icons/svg
    breadcrumb: false            // 如果设置为false，则不会在breadcrumb面包屑中显示
  }
 */

// 公共路由
export const constantRoutes = [
  {
    path: "/redirect",
    component: Layout,
    hidden: true,
    children: [
      {
        path: "/redirect/:path(.*)",
        component: resolve => require(["@/views/redirect"], resolve)
      }
    ]
  },
  {
    path: "/login",
    component: resolve => require(["@/views/login"], resolve),
    hidden: true
  },
  {
    path: "/app_login",
    component: resolve => require(["@/views/login"], resolve),
    hidden: true
  },
  {
    path: "/404",
    component: resolve => require(["@/views/error/404"], resolve),
    hidden: true
  },
  {
    path: "/401",
    component: resolve => require(["@/views/error/401"], resolve),
    hidden: true
  },
  {
    path: "/",
    component: Layout,
    redirect: "",
    children: [
      {
        path: "",
        component: resolve => require(["@/views/index"], resolve),
        name: "首页",
        meta: {title: "首页", icon: "dashboard", noCache: true, affix: true}
      }
    ]
  },
  {
    path: "/corporate_infomation",
    component: Layout,
    redirect: "",
    children: [
      {
        path: "",
        component: resolve =>
          require(["@/views/source/corporateInformation"], resolve),
        name: "corporate_infomation",
        meta: {title: "企业信息", icon: "eye-open"}
      }
    ]
  },
  // {
  //   path: "/operation_unit",
  //   component: Layout,
  //   redirect: "",
  //   children: [
  //     {
  //       path: "",
  //       component: resolve =>
  //         require(["@/views/operation_unit"], resolve),
  //       name: "operation_unit",
  //       meta: { title: "运维单位", icon: "eye-open" }
  //     }
  //   ]
  // },
  {
    path: "/operation_register",
    component: Layout,
    redirect: "list",
    children: [
      {
        path: "",
        component: resolve =>
          require(["@/views/operation_register"], resolve),
        name: "operation_register",
        meta: {title: "运维合同登记", icon: "eye-open"}
      }
    ]
  },
  {
    path: "/construction",
    component: Layout,
    children: [
      {
        path: "list",
        component: resolve => require(["@/views/construction"], resolve),
        name: "construction",
        meta: {title: "进度登记", icon: "bug"}
      }
    ]
  },
  {
    path: "/data_search",
    component: Layout,
    redirect: "",
    children: [
      {
        path: "",
        component: resolve => require(["@/views/data_search"], resolve),
        name: "data_search",
        meta: {title: "数据查询", icon: "eye-open"}
      }
    ]
  },
  {
    path: "/workStatus",
    component: Layout,
    children: [
      {
        path: "list",
        component: resolve => require(["@/views/work_status"], resolve),
        name: "workStatus",
        meta: {title: "工况停运、故障申报", icon: "bug"}
      }
    ]
  },
  // {
  //   path: "/taskWarn",
  //   component: Layout,
  //   children: [
  //     {
  //       path: "list",
  //       component: resolve => require(["@/views/taskWarn"], resolve),
  //       name: "warn_list",
  //       meta: {title: "异常处理", icon: "bug"}
  //     },
  //   ]
  // },
  {
    name: "warn",
    path: "/warn",
    component: Layout,
    meta: {title: "异常管理", icon: "bug"},
    children: [
      {
        path: "list",
        component: resolve => require(["@/views/taskWarn"], resolve),
        name: "history_warn_list",
        meta: {title: "异常处理", icon: "bug"}
      },
      {
        path: "historyList",
        component: resolve => require(["@/views/warn"], resolve),
        name: "warn_list",
        meta: {title: "历史异常", icon: "bug"}
      },
      // {
      //   path: "process",
      //   name: "warn_progress",
      //   meta: {title: "异常处理", icon: "bug"},
      //   component: Base,
      //   children: [
      //     {
      //       path: "guocheng",
      //       hidden: true,
      //       component: resolve =>
      //         require(["@/views/warn/process/currency"], resolve)
      //     },
      //     {
      //       path: "lingxing",
      //       hidden: true,
      //       component: resolve =>
      //         require(["@/views/warn/process/currency"], resolve)
      //     },
      //     {
      //       path: "lingxingp",
      //       hidden: true,
      //       component: resolve =>
      //         require(["@/views/warn/process/currency"], resolve)
      //     },
      //     {
      //       path: "voc",
      //       hidden: true,
      //       component: resolve => require(["@/views/warn/process/currency"], resolve)
      //     },
      //     {
      //       path: "youyan",
      //       hidden: true,
      //       component: resolve =>
      //         require(["@/views/warn/process/currency"], resolve)
      //     },
      //     {
      //       path: "outwater",
      //       hidden: true,
      //       component: resolve =>
      //         require(["@/views/warn/process/currency"], resolve)
      //     },
      //     {
      //       path: "qixiu",
      //       hidden: true,
      //       component: resolve =>
      //         require(["@/views/warn/process/currency"], resolve)
      //     },
      //     {
      //       path: "jiayou",
      //       hidden: true,
      //       component: resolve =>
      //         require(["@/views/warn/process/currency"], resolve)
      //     },
      //     {
      //       path: "jinshu",
      //       hidden: true,
      //       component: resolve =>
      //         require(["@/views/warn/process/currency"], resolve)
      //     },
      //     {
      //       path: "zaozhi",
      //       hidden: true,
      //       component: resolve =>
      //         require(["@/views/warn/process/currency"], resolve)
      //     }
      //   ]
      // }
    ]
  }

  // {
  //   path: "/map",
  //   component: Layout,
  //   redirect: "",
  //   children: [
  //     {
  //       path: "/track",
  //       component: resolve => require(["@/views/map"], resolve),
  //       name: "车辆轨迹",
  //       meta: { title: "车辆轨迹", icon: "dashboard", noCache: true, affix: true }
  //     }
  //   ]
  // },
  // {
  //   path: "/base",
  //   component: Layout,
  //   redirect: "collect",
  //   meta: { title: "基础资料", icon: "bug" },
  //   children: [
  //     {
  //       name: "collect",
  //       path: "collect",
  //       meta: { title: "数采管理", icon: "bug" },
  //       component: Base,
  //       children: [
  //         {
  //           path: "guocheng",
  //           hidden: true,
  //           component: resolve =>
  //             require(["@/views/base/collect/guocheng"], resolve)
  //         },
  //         {
  //           path: "lingxing",
  //           hidden: true,
  //           component: resolve =>
  //             require(["@/views/base/collect/lingxing"], resolve)
  //         },
  //         {
  //           path: "voc",
  //           hidden: true,
  //           component: resolve => require(["@/views/base/collect/voc"], resolve)
  //         },
  //         {
  //           path: "youyan",
  //           hidden: true,
  //           component: resolve =>
  //             require(["@/views/base/collect/youyan"], resolve)
  //         },
  //         {
  //           path: "outwater",
  //           hidden: true,
  //           component: resolve =>
  //             require(["@/views/base/collect/outwater"], resolve)
  //         },
  //         {
  //           path: "qixiu",
  //           hidden: true,
  //           component: resolve =>
  //             require(["@/views/base/collect/qixiu"], resolve)
  //         },
  //         {
  //           path: "jiayou",
  //           hidden: true,
  //           component: resolve =>
  //             require(["@/views/base/collect/jiayou"], resolve)
  //         },
  //         {
  //           path: "jinshu",
  //           hidden: true,
  //           component: resolve =>
  //             require(["@/views/base/collect/jinshu"], resolve)
  //         },
  //         {
  //           path: "zaozhi",
  //           hidden: true,
  //           component: resolve =>
  //             require(["@/views/base/collect/zaozhi"], resolve)
  //         }
  //       ]
  //     },
  //     {
  //       name: "outlet",
  //       path: "outlet",
  //       meta: { title: "排放口管理", icon: "bug" },
  //       component: Base,
  //       children: [
  //         {
  //           path: "voc",
  //           hidden: true,
  //           component: resolve => require(["@/views/base/outlet/voc"], resolve)
  //         },
  //         {
  //           path: "qixiu",
  //           hidden: true,
  //           component: resolve =>
  //             require(["@/views/base/outlet/qixiu"], resolve)
  //         }
  //       ]
  //     },
  //     {
  //       name: "element",
  //       path: "element",
  //       meta: { title: "因子管理", icon: "bug" },
  //       component: Base,
  //       children: [
  //         {
  //           path: "guocheng",
  //           hidden: true,
  //           component: resolve =>
  //             require(["@/views/base/element/guocheng"], resolve)
  //         },
  //         {
  //           path: "lingxing",
  //           hidden: true,
  //           component: resolve =>
  //             require(["@/views/base/element/lingxing"], resolve)
  //         },
  //         {
  //           path: "voc",
  //           hidden: true,
  //           component: resolve => require(["@/views/base/element/voc"], resolve)
  //         },
  //         {
  //           path: "youyan",
  //           hidden: true,
  //           component: resolve =>
  //             require(["@/views/base/element/youyan"], resolve)
  //         },
  //         {
  //           path: "qixiu",
  //           hidden: true,
  //           component: resolve =>
  //             require(["@/views/base/element/qixiu"], resolve)
  //         },
  //         {
  //           path: "jiayou",
  //           hidden: true,
  //           component: resolve =>
  //             require(["@/views/base/element/jiayou"], resolve)
  //         },
  //         {
  //           path: "jinshu",
  //           hidden: true,
  //           component: resolve =>
  //             require(["@/views/base/element/jinshu"], resolve)
  //         },
  //         {
  //           path: "zaozhi",
  //           hidden: true,
  //           component: resolve =>
  //             require(["@/views/base/element/zaozhi"], resolve)
  //         }
  //       ]
  //     },
  //     {
  //       name: "video",
  //       path: "video",
  //       meta: { title: "视频管理", icon: "bug" },
  //       component: Base,
  //       children: [
  //         {
  //           path: "guocheng",
  //           hidden: true,
  //           component: resolve =>
  //             require(["@/views/base/video/guocheng"], resolve)
  //         },
  //         {
  //           path: "lingxing",
  //           hidden: true,
  //           component: resolve =>
  //             require(["@/views/base/video/lingxing"], resolve)
  //         },
  //         {
  //           path: "voc",
  //           hidden: true,
  //           component: resolve => require(["@/views/base/video/voc"], resolve)
  //         },
  //         {
  //           path: "qixiu",
  //           hidden: true,
  //           component: resolve => require(["@/views/base/video/qixiu"], resolve)
  //         },
  //         {
  //           path: "jiayou",
  //           hidden: true,
  //           component: resolve =>
  //             require(["@/views/base/video/jiayou"], resolve)
  //         },
  //         {
  //           path: "jinshu",
  //           hidden: true,
  //           component: resolve =>
  //             require(["@/views/base/video/jinshu"], resolve)
  //         },
  //         {
  //           path: "zaozhi",
  //           hidden: true,
  //           component: resolve =>
  //             require(["@/views/base/video/zaozhi"], resolve)
  //         }
  //       ]
  //     }
  //   ]
  // }
];

export default new Router({
  mode: "history", // 去掉url中的#
  scrollBehavior: () => ({y: 0}),
  routes: constantRoutes
});
