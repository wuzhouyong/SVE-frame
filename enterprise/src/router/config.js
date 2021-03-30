export const base_routes = ids => {
  const platforms = sys_pattern.filter(p => ids.some(s => s === p.id));
  const names = [
    ...new Set(platforms.reduce((arr, cur) => arr.concat(cur.paths), []))
  ];
  const children = base_pattern
    .filter(p => names.some(s => s === p.name))
    .map(m => {
      var route = {
        name: m.name,
        path: m.name,
        meta: { title: m.title, icon: "education" }
      };
      if (m.children) {
        route.component = () => import("@/views/blank");
        route.children = m.children.map(c => {
          c.path = c.name;
          c.meta = { title: c.title, icon: "education" };
          c.component = () => import("@/layout/base");
          c.children = platforms.map(x => {
            const oPath = `${c.ctm}/${x.name}`;
            return {
              path: x.name,
              hidden: true,
              component: oPath
            };
          });
          return c;
        });
      } else {
        route.component = () => import("@/layout/base");
        route.children = platforms.map(x => {
          const path = `${m.component}/${x.name}`;
          return {
            path: x.name,
            hidden: true,
            component: path
          };
        });
      }
      return route;
    });
  const base = {
    path: "/base",
    component: () => import("@/layout"),
    meta: { title: "基础资料", icon: "log" },
    children: children
  };
  return [base];
};

const sys_pattern = [
  { id: "1002", name: "guocheng", paths: ["collect", "element", "video"] },
  {
    id: "1003",
    name: "lingxing",
    paths: ["collect", "element", "video", "lxfs", "tank", "watermeter","transshipment","sewagemeter"]
  },
  {
    id: "1004",
    name: "voc",
    paths: [
      "collect",
      "element",
      "vocs",
      "production",
      "gather",
      "govern",
      "discharge",
      "disport"
    ]
  },
  { id: "1006", name: "youyan", paths: ["collect", "element", "yy", "port"] },
  { id: "1012", name: "outwater", paths: ["collect"] },
  {
    id: "1013",
    name: "qixiu",
    paths: ["collect", "outlet", "element", "video"]
  },
  { id: "1014", name: "jiayou", paths: ["collect", "outlet", "element", "video"] },
  { id: "1015", name: "jinshu", paths: ["collect", "element", "video"] },
  { id: "1019", name: "zaozhi", paths: ["collect", "element", "video"] },
  { id: "1017", name: "shui", paths: [] },
  { id: "1021", name: "qi", paths: [] },
  { id: "1001", name: "dgom", paths: ["video", "om", "checkpoint", "device", "element","apply","report","operation","facilities","task"] }
];

const base_pattern = [
  { name: "collect", title: "数采管理", component: "base/collect" },
  { name: "outlet", title: "排放口管理", component: "base/outlet" },
  { name: "element", title: "因子管理", component: "base/element" },
  { name: "video", title: "视频管理", component: "base/video" },
  {
    name: "vocs",
    title: "VOCs监控",
    children: [
      {
        name: "production",
        title: "生产线电表管理",
        ctm: "base/vocs/production"
      },
      {
        name: "gather",
        title: "收集风机管理",
        ctm: "base/vocs/gather"
      },
      {
        name: "govern",
        title: "治理设施管理",
        ctm: "base/vocs/govern"
      },
      {
        name: "disport",
        title: "排放口管理",
        ctm: "base/vocs/disport"
      },
      {
        name: "discharge",
        title: "排放风机管理",
        ctm: "base/vocs/discharge"
      }
    ]
  },
  {
    name: "lxfs",
    title: "零星废水监控",
    children: [
      {
        name: "tank",
        title: "废水收集池(桶)管理",
        ctm: "base/lxfs/tank"
      },
      {
        name: "watermeter",
        title: "工业用水流量计",
        ctm: "base/lxfs/watermeter"
      },
      {
        name: "sewagemeter",
        title: "污水排放流量计",
        ctm: "base/lxfs/sewagemeter"
      },
      {
        name: "transshipment",
        title: "转运管理",
        ctm: "base/lxfs/transshipment"
      }
    ]
  },
  {
    name: "yy",
    title: "油烟监控",
    children: [
      {
        name: "port",
        title: "设施管理",
        ctm: "base/yy/port"
      }
    ]
  },
  {
    name: "om",
    title: "在线监控",
    children: [
      {
        name: "checkpoint",
        title: "排放口管理",
        ctm: "base/om/checkpoint"
      },
      {
        name: "device",
        title: "设备管理",
        ctm: "base/om/device"
      },
      {
        name: "element",
        title: "因子管理",
        ctm: "base/om/element"
      },
      {
        name: "apply",
        title: "联网申请",
        ctm: "base/om/apply"
      },
      {
        name: "report",
        title: "报告管理",
        ctm: "base/om/report"
      },
      {
        name: "operation",
        title: "运维管理",
        ctm: "base/om/operation"
      },
      {
        name: "facilities",
        title: "设备状态变更",
        ctm: "base/om/facilities"
      },
      {
        name: "task",
        title: "任务管理",
        ctm: "base/om/task"
      },
      {
        name: "acceptance",
        title: "验收备案",
        ctm: "base/om/acceptance"
      }
    ]
  }
];

// export const routes = [
//     {
//         path: "/base",
//         component: Layout,
//         redirect: "collect",
//         meta: { title: "基础资料", icon: "bug" },
//         children: [
//             {
//                 name: "collect",
//                 path: "collect",
//                 meta: { title: "数采管理", icon: "bug" },
//                 component: () => import("@/layout/base"),
//                 children: [
//                     {
//                         path: "guocheng",
//                         hidden: true,
//                         component: resolve =>
//                             require(["@/views/base/collect/guocheng"], resolve)
//                     },
//                     {
//                         path: "lingxing",
//                         hidden: true,
//                         component: resolve =>
//                             require(["@/views/base/collect/lingxing"], resolve)
//                     },
//                     {
//                         path: "voc",
//                         hidden: true,
//                         component: resolve => require(["@/views/base/collect/voc"], resolve)
//                     },
//                     {
//                         path: "youyan",
//                         hidden: true,
//                         component: resolve =>
//                             require(["@/views/base/collect/youyan"], resolve)
//                     },
//                     {
//                         path: "outwater",
//                         hidden: true,
//                         component: resolve =>
//                             require(["@/views/base/collect/outwater"], resolve)
//                     },
//                     {
//                         path: "qixiu",
//                         hidden: true,
//                         component: resolve =>
//                             require(["@/views/base/collect/qixiu"], resolve)
//                     },
//                     {
//                         path: "jiayou",
//                         hidden: true,
//                         component: resolve =>
//                             require(["@/views/base/collect/jiayou"], resolve)
//                     },
//                     {
//                         path: "jinshu",
//                         hidden: true,
//                         component: resolve =>
//                             require(["@/views/base/collect/jinshu"], resolve)
//                     },
//                     {
//                         path: "zaozhi",
//                         hidden: true,
//                         component: resolve =>
//                             require(["@/views/base/collect/zaozhi"], resolve)
//                     }
//                 ]
//             },
//             {
//                 name: "outlet",
//                 path: "outlet",
//                 meta: { title: "排放口管理", icon: "bug" },
//                 component: () => import("@/layout/base"),
//                 children: [
//                     {
//                         path: "voc",
//                         hidden: true,
//                         component: resolve => require(["@/views/base/outlet/voc"], resolve)
//                     },
//                     {
//                         path: "qixiu",
//                         hidden: true,
//                         component: resolve =>
//                             require(["@/views/base/outlet/qixiu"], resolve)
//                     }
//                 ]
//             },
//             {
//                 name: "element",
//                 path: "element",
//                 meta: { title: "因子管理", icon: "bug" },
//                 component: () => import("@/layout/base"),
//                 children: [
//                     {
//                         path: "guocheng",
//                         hidden: true,
//                         component: resolve =>
//                             require(["@/views/base/element/guocheng"], resolve)
//                     },
//                     {
//                         path: "lingxing",
//                         hidden: true,
//                         component: resolve =>
//                             require(["@/views/base/element/lingxing"], resolve)
//                     },
//                     {
//                         path: "voc",
//                         hidden: true,
//                         component: resolve => require(["@/views/base/element/voc"], resolve)
//                     },
//                     {
//                         path: "youyan",
//                         hidden: true,
//                         component: resolve =>
//                             require(["@/views/base/element/youyan"], resolve)
//                     },
//                     {
//                         path: "qixiu",
//                         hidden: true,
//                         component: resolve =>
//                             require(["@/views/base/element/qixiu"], resolve)
//                     },
//                     {
//                         path: "jiayou",
//                         hidden: true,
//                         component: resolve =>
//                             require(["@/views/base/element/jiayou"], resolve)
//                     },
//                     {
//                         path: "jinshu",
//                         hidden: true,
//                         component: resolve =>
//                             require(["@/views/base/element/jinshu"], resolve)
//                     },
//                     {
//                         path: "zaozhi",
//                         hidden: true,
//                         component: resolve =>
//                             require(["@/views/base/element/zaozhi"], resolve)
//                     }
//                 ]
//             },
//             {
//                 name: "video",
//                 path: "video",
//                 meta: { title: "视频管理", icon: "bug" },
//                 component: () => import("@/layout/base"),
//                 children: [
//                     {
//                         path: "guocheng",
//                         hidden: true,
//                         component: resolve =>
//                             require(["@/views/base/video/guocheng"], resolve)
//                     },
//                     {
//                         path: "lingxing",
//                         hidden: true,
//                         component: resolve =>
//                             require(["@/views/base/video/lingxing"], resolve)
//                     },
//                     {
//                         path: "voc",
//                         hidden: true,
//                         component: resolve => require(["@/views/base/video/voc"], resolve)
//                     },
//                     {
//                         path: "qixiu",
//                         hidden: true,
//                         component: resolve => require(["@/views/base/video/qixiu"], resolve)
//                     },
//                     {
//                         path: "jiayou",
//                         hidden: true,
//                         component: resolve =>
//                             require(["@/views/base/video/jiayou"], resolve)
//                     },
//                     {
//                         path: "jinshu",
//                         hidden: true,
//                         component: resolve =>
//                             require(["@/views/base/video/jinshu"], resolve)
//                     },
//                     {
//                         path: "zaozhi",
//                         hidden: true,
//                         component: resolve =>
//                             require(["@/views/base/video/zaozhi"], resolve)
//                     }
//                 ]
//             }
//         ]
//     }
// ];
