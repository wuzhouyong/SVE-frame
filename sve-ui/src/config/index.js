import {getDicts} from "@/api/system/dict/data";

export const platform_list = [
  {
    name: "重点排水户",
    id: "1012",
    path: "outwater",
    ref_type: "psh_sysfile_type",
    warn_type: getDicts.bind(this, "psh_warn_type"),
    warn_levels: getDicts.bind(this, "ty_warn_level"),
    proc_state: getDicts.bind(this, "ty_proc_state")
  },
  {
    name: "零星废水产生企业",
    id: "1003",
    path: "lingxing",
    ref_type: "lx_sysfile_type",
    warn_type: getDicts.bind(this, "lx_warn_type"),
    warn_levels: getDicts.bind(this, "ty_warn_level"),
    proc_state: getDicts.bind(this, "ty_proc_state")
  },
  // {
  //   name: "零星废水处置企业",
  //   id: "1018",
  //   path: "lingxingp",
  //   ref_type: "lxp_sysfile_type",
  //   warn_type: getDicts.bind(this, "lxp_warn_type"),
  //   warn_levels: getDicts.bind(this, "ty_warn_level"),
  //   proc_state: getDicts.bind(this, "ty_proc_state")
  // },
  {
    name: "四大流域过程监控",
    id: "1002",
    path: "guocheng",
    ref_type: "gc_sysfile_type",
    warn_type: getDicts.bind(this, "gc_warn_type"),
    warn_levels: getDicts.bind(this, "ty_warn_level"),
    proc_state: getDicts.bind(this, "ty_proc_state")
  },
  {
    name: "金属表面处理",
    id: "1015",
    path: "jinshu",
    ref_type: "js_sysfile_type",
    warn_type: getDicts.bind(this, "js_warn_type"),
    warn_levels: getDicts.bind(this, "ty_warn_level"),
    proc_state: getDicts.bind(this, "ty_proc_state")
  },
  {
    name: "造纸行业",
    id: "1019",
    path: "zaozhi",
    ref_type: "zz_sysfile_type",
    warn_type: getDicts.bind(this, "zz_warn_type"),
    warn_levels: getDicts.bind(this, "ty_warn_level"),
    proc_state: getDicts.bind(this, "ty_proc_state")
  },
  {
    name: "VOCs",
    id: "1004",
    path: "voc",
    ref_type: "voc_sysfile_type",
    warn_type: getDicts.bind(this, "voc_warn_type"),
    warn_levels: getDicts.bind(this, "ty_warn_level"),
    proc_state: getDicts.bind(this, "ty_proc_state")
  },
  {
    name: "餐饮行业",
    id: "1006",
    path: "youyan",
    ref_type: "yy_sysfile_type",
    warn_type: getDicts.bind(this, "yy_warn_type"),
    warn_levels: getDicts.bind(this, "ty_warn_level"),
    proc_state: getDicts.bind(this, "ty_proc_state")
  },
  {
    name: "汽修行业",
    id: "1013",
    path: "qixiu",
    ref_type: "qx_sysfile_type",
    warn_type: getDicts.bind(this, "qx_warn_type"),
    warn_levels: getDicts.bind(this, "ty_warn_level"),
    proc_state: getDicts.bind(this, "ty_proc_state")
  },
  {
    name: "加油站",
    id: "1014",
    path: "jiayou",
    ref_type: "jy_sysfile_type",
    warn_type: getDicts.bind(this, "jy_warn_type"),
    warn_levels: getDicts.bind(this, "ty_warn_level"),
    proc_state: getDicts.bind(this, "ty_proc_state")
  }
  // {
  //   name: "水重点",
  //   id: "1017",
  //   path: "water",
  //   ref_type: "water_sysfile_type",
  //   warn_type: getDicts.bind(this, "water_warn_type"),
  //   warn_levels: getDicts.bind(this, "water_warn_level"),
  //   proc_state: getDicts.bind(this, "water_proc_state")
  // },
  // {
  //   name: "气重点",
  //   id: "1021",
  //   path: "gas",
  //   ref_type: "gas_sysfile_type",
  //   warn_type: getDicts.bind(this, "gas_warn_type"),
  //   warn_levels: getDicts.bind(this, "gas_warn_level"),
  //   proc_state: getDicts.bind(this, "gas_proc_state")
  // }
];

export const home_system_list = [
  {
    name: "重点排水户",
    id: "1012",
    path: "outwater",
    warn_type: getDicts.bind(this, "psh_warn_type"),
    warn_levels: getDicts.bind(this, "ty_warn_level"),
    proc_state: getDicts.bind(this, "ty_proc_state")
  },
  {
    name: "零星废水产生企业",
    id: "1003",
    path: "lingxing",
    warn_type: getDicts.bind(this, "lx_warn_type"),
    warn_levels: getDicts.bind(this, "ty_warn_level"),
    proc_state: getDicts.bind(this, "ty_proc_state")
  },
  {
    name: "零星废水处置企业",
    id: "1018",
    path: "lingxingp",
    warn_type: getDicts.bind(this, "lxp_warn_type"),
    warn_levels: getDicts.bind(this, "ty_warn_level"),
    proc_state: getDicts.bind(this, "ty_proc_state")
  },
  {
    name: "四大流域过程监控",
    id: "1002",
    path: "guocheng",
    warn_type: getDicts.bind(this, "gc_warn_type"),
    warn_levels: getDicts.bind(this, "ty_warn_level"),
    proc_state: getDicts.bind(this, "ty_proc_state")
  },
  {
    name: "金属表面处理",
    id: "1015",
    path: "jinshu",
    warn_type: getDicts.bind(this, "js_warn_type"),
    warn_levels: getDicts.bind(this, "ty_warn_level"),
    proc_state: getDicts.bind(this, "ty_proc_state")
  },
  {
    name: "造纸行业",
    id: "1019",
    path: "zaozhi",
    warn_type: getDicts.bind(this, "zz_warn_type"),
    warn_levels: getDicts.bind(this, "ty_warn_level"),
    proc_state: getDicts.bind(this, "ty_proc_state")
  },
  {
    name: "VOCs",
    id: "1004",
    path: "voc",
    warn_type: getDicts.bind(this, "voc_warn_type"),
    warn_levels: getDicts.bind(this, "ty_warn_level"),
    proc_state: getDicts.bind(this, "ty_proc_state")
  },
  {
    name: "餐饮行业",
    id: "1006",
    path: "youyan",
    warn_type: getDicts.bind(this, "yy_warn_type"),
    warn_levels: getDicts.bind(this, "ty_warn_level"),
    proc_state: getDicts.bind(this, "ty_proc_state")
  },
  {
    name: "汽修行业",
    id: "1013",
    path: "qixiu",
    warn_type: getDicts.bind(this, "qx_warn_type"),
    warn_levels: getDicts.bind(this, "ty_warn_level"),
    proc_state: getDicts.bind(this, "ty_proc_state")
  },
  {
    name: "加油站",
    id: "1014",
    path: "jiayou",
    warn_type: getDicts.bind(this, "jy_warn_type"),
    warn_levels: getDicts.bind(this, "ty_warn_level"),
    proc_state: getDicts.bind(this, "ty_proc_state")
  },
  {
    name: "在线监控",
    id: "1001",
    path: "dgom",
    warn_type: getDicts.bind(this, "dgom_warn_type"),
    warn_levels: getDicts.bind(this, "ty_warn_level"),
    proc_state: getDicts.bind(this, "ty_proc_state")
  }
];

//地图首页任务选择处使用
export const task_statistics = [
  {
    name: "涉水重点排污单位",
    path: "water",
    id: "1017",
    ref_type: "water_sysfile_type"
  },
  {
    name: "涉气重点排污单位",
    path: "gas",
    id: "1021",
    ref_type: "gas_sysfile_type"
  },
  {
    name: "重点排水户末端监控",
    path: "outwater",
    id: "1012",
    ref_type: "psh_sysfile_type"
  },
  {
    name: "零星废水产生企业",
    path: "lingxing",
    id: "1003",
    ref_type: "lx_sysfile_type"
  },
  {
    name: "零星废水处置企业",
    path: "lingxingp",
    id: "1018",
    ref_type: "lxp_sysfile_type"
  },
  {
    name: "四大流域过程监控",
    path: "guocheng",
    id: "1002",
    ref_type: "gc_sysfile_type"
  },
  {
    name: "金属表面处理末端监控",
    path: "jinshu",
    id: "1015",
    ref_type: "js_sysfile_type"
  },
  {
    name: "造纸行业过程监控",
    path: "zaozhi",
    id: "1019",
    ref_type: "zz_sysfile_type"
  },
  {
    name: "一体化污水处理",
    path: "yitihua",
    id: "1016",
    ref_type: "yth_sysfile_type"
  },
  {
    name: "水重点排污监控(加装视频)",
    path: "waterv",
    id: "1023",
    ref_type: "waterv_sysfile_type"
  },
  {
    name: "VOCs过程监控",
    path: "voc",
    id: "1004",
    ref_type: "voc_sysfile_type"
  },
  {
    name: "餐饮行业末端监控",
    path: "youyan",
    id: "1006",
    ref_type: "yy_sysfile_type"
  },
  {
    name: "汽修行业过程监控",
    path: "qixiu",
    id: "1013",
    ref_type: "qx_sysfile_type"
  },
  {
    name: "加油站",
    path: "jiayou",
    id: "1014",
    ref_type: "jy_sysfile_type"
  },
  {
    name: "VOCs重点排放源自动监控",
    path: "gasv",
    id: "1022",
    ref_type: "gasv_sysfile_type"
  }
];

export const analysis = [
  {
    name: "重点排水户",
    path: "outwater",
    id: "1012",
    ref_type: "psh_sysfile_type"
  },
  {
    name: "零星废水产生单位",
    path: "lingxing",
    id: "1003",
    ref_type: "lx_sysfile_type"
  },
  {
    name: "零星废水处理单位",
    path: "lingxingp",
    id: "1018",
    ref_type: "lxp_sysfile_type"
  },
  {
    name: "四大流域过程监控",
    path: "guocheng",
    id: "1002",
    ref_type: "gc_sysfile_type"
  },
  {
    name: "金属表面处理行业",
    path: "jinshu",
    id: "1015",
    ref_type: "js_sysfile_type"
  },
  {
    name: "造纸行业",
    path: "zaozhi",
    id: "1019",
    ref_type: "zz_sysfile_type"
  },
  {
    name: "一体化污水处理",
    path: "yitihua",
    id: "1016",
    ref_type: "yth_sysfile_type"
  },
  {
    name: "涉水重点排污单位",
    path: "water",
    id: "1017",
    ref_type: "water_sysfile_type"
  },
  {
    name: "涉气重点排污单位",
    path: "gas",
    id: "1021",
    ref_type: "gas_sysfile_type"
  },
  {
    name: "VOCs重点排放源自动监控",
    path: "gasv",
    id: "1022",
    ref_type: "gasv_sysfile_type"
  },
  {
    name: "水重点排污监控(加装视频)",
    path: "waterv",
    id: "1023",
    ref_type: "waterv_sysfile_type"
  },
  {
    name: "VOCs过程监控",
    path: "voc",
    id: "1004",
    ref_type: "voc_sysfile_type"
  },
  {
    name: "餐饮行业",
    path: "youyan",
    id: "1006",
    ref_type: "yy_sysfile_type"
  },
  {
    name: "汽修行业",
    path: "qixiu",
    id: "1013",
    ref_type: "qx_sysfile_type"
  },
  {
    name: "加油站",
    path: "jiayou",
    id: "1014",
    ref_type: "jy_sysfile_type"
  },
  {
    name: "重点信访企业",
    path: "xinfang",
    id: "1024",
    ref_type: ""
  }
];

//看板处使用
export const boardSystem = [
  {
    name: "涉水重点排污单位",
    path: "water",
    id: "1017",
    ref_type: "water_sysfile_type"
  },
  {
    name: "涉气重点排污单位",
    path: "gas",
    id: "1021",
    ref_type: "gas_sysfile_type"
  },
  {
    name: "重点排水户末端监控",
    path: "outwater",
    id: "1012",
    ref_type: "psh_sysfile_type"
  },
  {
    name: "零星废水产生企业",
    path: "lingxing",
    id: "1003",
    ref_type: "lx_sysfile_type"
  },
  {
    name: "四大流域过程监控",
    path: "guocheng",
    id: "1002",
    ref_type: "gc_sysfile_type"
  },
  {
    name: "金属表面处理末端监控",
    path: "jinshu",
    id: "1015",
    ref_type: "js_sysfile_type"
  },
  {
    name: "造纸行业过程监控",
    path: "zaozhi",
    id: "1019",
    ref_type: "zz_sysfile_type"
  },
  {
    name: "一体化污水处理",
    path: "yitihua",
    id: "1016",
    ref_type: "yth_sysfile_type"
  },
  {
    name: "水重点排污监控(加装视频)",
    path: "waterv",
    id: "1023",
    ref_type: "waterv_sysfile_type"
  },
  {
    name: "零星废水处理单位",
    path: "lingxingp",
    id: "1018",
    ref_type: "lxcz_sysfile_type"
  },
  {
    name: "VOCs过程监控",
    path: "voc",
    id: "1004",
    ref_type: "voc_sysfile_type"
  },
  {
    name: "餐饮行业末端监控",
    path: "youyan",
    id: "1006",
    ref_type: "yy_sysfile_type"
  },
  {
    name: "汽修行业过程监控",
    path: "qixiu",
    id: "1013",
    ref_type: "qx_sysfile_type"
  },
  {
    name: "加油站",
    path: "jiayou",
    id: "1014",
    ref_type: "jy_sysfile_type"
  },
  {
    name: "VOCs重点排放源自动监控",
    path: "gasv",
    id: "1022",
    ref_type: "gasv_sysfile_type"
  }
];
export const contrast = {
  outwater: "psh",
  lingxing: "lx",
  youyan: "yy",
  voc: "voc",
  jinshu: "js",
  qixiu: "qx",
  guocheng: "gc",
  zaozhi: "zz",
  jiayou: "jy",
  lingxingp: "lxp"
};

export const workStatus = [
  {
    name: "重点排水户末端监控",
    id: "1012",
  },
  {
    name: "零星废水产生企业",
    id: "1003",
  },
  {
    name: "零星废水处置企业",
    id: "1018",
  },
  {
    name: "四大流域过程监控",
    id: "1002",
  },
  {
    name: "金属表面处理行业",
    id: "1015",
  },
  {
    name: "造纸行业过程监控",
    id: "1019",
  },
  {
    name: "市级以上VOCs过程监控",
    id: "1004",
  },
  {
    name: "餐饮行业过程及末端监控",
    id: "1006",
  },
  {
    name: "汽修行业过程监控",
    id: "1013",
  },
  {
    name: "加油站",
    id: "1014"
  },
  {
    name: "重点污染源在线监控",
    id: "1001"
  }
];
