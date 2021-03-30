import { default_system_config } from "./system";
import { getDict } from "@/api/source";
import { parseTime, addDay } from "@/utils/ruoyi";

const date = new Date();
const start = parseTime(addDay(date, -7)).substr(0, 10);
const end = parseTime(date).substr(0, 10);

const items = [
    // { field: "constructionUnitName", label: "施工单位", type: "input" },
    { field: "startTime", label: "选择日期", type: "daterange", value: [start, end] },
];

const cols = [
    { dataIndex: "warnLevelName", title: "异常级别", width: "100" },
    { dataIndex: "procStateName", title: "处理状态", width: "100" },
    { dataIndex: "startDate", title: "异常开始时间", width: "100" },
    { dataIndex: "endDate", title: "异常结束时间", width: "100" },
    { dataIndex: "warnTypeName", title: "异常类型", width: "160" },
    { dataIndex: "warnDesc", title: "异常描述" }
];

const config = {
    "1002": {
        items: [
            ...items,
            // {
            //     field: "constructionStatus", label: "施工验收状态", type: "select", data: {
            //         api: getDict.bind(this, "gc_construct_state"),
            //         id: "dictValue",
            //         name: "dictLabel"
            //     }
            // },
            {
                field: "procState", label: "处理状态", type: "select", data: {
                    api: getDict.bind(this, "gc_proc_state"),
                    id: "dictValue",
                    name: "dictLabel"
                }
            },
            {
                field: "warnLevel", label: "异常级别", type: "select", data: {
                    api: getDict.bind(this, "ty_warn_level"),
                    id: "dictValue",
                    name: "dictLabel"
                }
            },
            {
                field: "warnType", label: "异常类型", type: "select", data: {
                    api: getDict.bind(this, "gc_warn_type"),
                    id: "dictValue",
                    name: "dictLabel"
                }
            }
        ],
        cols
    },
    "1003": {
        items: [
            ...items,
            // {
            //     field: "constructionStatus", label: "施工验收状态", type: "select", data: {
            //         api: getDict.bind(this, "lx_construct_state"),
            //         id: "dictValue",
            //         name: "dictLabel"
            //     }
            // },
            {
                field: "procState", label: "处理状态", type: "select", data: {
                    api: getDict.bind(this, "lx_proc_state"),
                    id: "dictValue",
                    name: "dictLabel"
                }
            },
            {
                field: "warnLevel", label: "异常级别", type: "select", data: {
                    api: getDict.bind(this, "ty_warn_level"),
                    id: "dictValue",
                    name: "dictLabel"
                }
            },
            {
                field: "warnType", label: "异常类型", type: "select", data: {
                    api: getDict.bind(this, "lx_warn_type"),
                    id: "dictValue",
                    name: "dictLabel"
                }
            }
        ],
        cols
    },
    "1018": {
        items: [
            ...items,
            // {
            //     field: "constructionStatus", label: "施工验收状态", type: "select", data: {
            //         api: getDict.bind(this, "ty_construct_state"),
            //         id: "dictValue",
            //         name: "dictLabel"
            //     }
            // },
            {
                field: "procState", label: "处理状态", type: "select", data: {
                    api: getDict.bind(this, "ty_proc_state"),
                    id: "dictValue",
                    name: "dictLabel"
                }
            },
            {
                field: "warnLevel", label: "异常级别", type: "select", data: {
                    api: getDict.bind(this, "ty_warn_level"),
                    id: "dictValue",
                    name: "dictLabel"
                }
            },
            {
                field: "warnType", label: "异常类型", type: "select", data: {
                    api: getDict.bind(this, "lxp_warn_type"),
                    id: "dictValue",
                    name: "dictLabel"
                }
            }
        ],
        cols
    },
    "1004": {
        items: [
            ...items,
            // {
            //     field: "constructionStatus", label: "施工验收状态", type: "select", data: {
            //         api: getDict.bind(this, "voc_construct_state"),
            //         id: "dictValue",
            //         name: "dictLabel"
            //     }
            // },
            {
                field: "procState", label: "处理状态", type: "select", data: {
                    api: getDict.bind(this, "voc_proc_state"),
                    id: "dictValue",
                    name: "dictLabel"
                }
            },
            {
                field: "warnLevel", label: "异常级别", type: "select", data: {
                    api: getDict.bind(this, "ty_warn_level"),
                    id: "dictValue",
                    name: "dictLabel"
                }
            },
            {
                field: "warnType", label: "异常类型", type: "select", data: {
                    api: getDict.bind(this, "voc_warn_type"),
                    id: "dictValue",
                    name: "dictLabel"
                }
            }
        ],
        cols
    },
    "1006": {
        items: [
            ...items,
            // {
            //     field: "constructionStatus", label: "施工验收状态", type: "select", data: {
            //         api: getDict.bind(this, "yy_construct_state"),
            //         id: "dictValue",
            //         name: "dictLabel"
            //     }
            // },
            {
                field: "procState", label: "处理状态", type: "select", data: {
                    api: getDict.bind(this, "yy_proc_state"),
                    id: "dictValue",
                    name: "dictLabel"
                }
            },
            {
                field: "warnLevel", label: "异常级别", type: "select", data: {
                    api: getDict.bind(this, "ty_warn_level"),
                    id: "dictValue",
                    name: "dictLabel"
                }
            },
            {
                field: "warnType", label: "异常类型", type: "select", data: {
                    api: getDict.bind(this, "yy_warn_type"),
                    id: "dictValue",
                    name: "dictLabel"
                }
            }
        ],
        cols
    },
    "1012": {
        items: [
            ...items,
            // {
            //     field: "constructionStatus", label: "施工验收状态", type: "select", data: {
            //         api: getDict.bind(this, "psh_construct_state"),
            //         id: "dictValue",
            //         name: "dictLabel"
            //     }
            // },
            {
                field: "procState", label: "处理状态", type: "select", data: {
                    api: getDict.bind(this, "psh_proc_state"),
                    id: "dictValue",
                    name: "dictLabel"
                }
            },
            {
                field: "warnLevel", label: "异常级别", type: "select", data: {
                    api: getDict.bind(this, "ty_warn_level"),
                    id: "dictValue",
                    name: "dictLabel"
                }
            },
            {
                field: "warnType", label: "异常类型", type: "select", data: {
                    api: getDict.bind(this, "psh_warn_type"),
                    id: "dictValue",
                    name: "dictLabel"
                }
            }
        ],
        cols
    },
    "1013": {
        items: [
            ...items,
            // {
            //     field: "constructionStatus", label: "施工验收状态", type: "select", data: {
            //         api: getDict.bind(this, "qx_construct_state"),
            //         id: "dictValue",
            //         name: "dictLabel"
            //     }
            // },
            {
                field: "procState", label: "处理状态", type: "select", data: {
                    api: getDict.bind(this, "qx_proc_state"),
                    id: "dictValue",
                    name: "dictLabel"
                }
            },
            {
                field: "warnLevel", label: "异常级别", type: "select", data: {
                    api: getDict.bind(this, "ty_warn_level"),
                    id: "dictValue",
                    name: "dictLabel"
                }
            },
            {
                field: "warnType", label: "异常类型", type: "select", data: {
                    api: getDict.bind(this, "qx_warn_type"),
                    id: "dictValue",
                    name: "dictLabel"
                }
            }
        ],
        cols
    },
    "1014": {
        items: [
            ...items,
            // {
            //     field: "constructionStatus", label: "施工验收状态", type: "select", data: {
            //         api: getDict.bind(this, "jy_construct_state"),
            //         id: "dictValue",
            //         name: "dictLabel"
            //     }
            // },
            {
                field: "procState", label: "处理状态", type: "select", data: {
                    api: getDict.bind(this, "jy_proc_state"),
                    id: "dictValue",
                    name: "dictLabel"
                }
            },
            {
                field: "warnLevel", label: "异常级别", type: "select", data: {
                    api: getDict.bind(this, "ty_warn_level"),
                    id: "dictValue",
                    name: "dictLabel"
                }
            },
            {
                field: "warnType", label: "异常类型", type: "select", data: {
                    api: getDict.bind(this, "jy_warn_type"),
                    id: "dictValue",
                    name: "dictLabel"
                }
            }
        ],
        cols
    },
    "1015": {
        items: [
            ...items,
            // {
            //     field: "constructionStatus", label: "施工验收状态", type: "select", data: {
            //         api: getDict.bind(this, "js_construct_state"),
            //         id: "dictValue",
            //         name: "dictLabel"
            //     }
            // },
            {
                field: "procState", label: "处理状态", type: "select", data: {
                    api: getDict.bind(this, "js_proc_state"),
                    id: "dictValue",
                    name: "dictLabel"
                }
            },
            {
                field: "warnLevel", label: "异常级别", type: "select", data: {
                    api: getDict.bind(this, "ty_warn_level"),
                    id: "dictValue",
                    name: "dictLabel"
                }
            },
            {
                field: "warnType", label: "异常类型", type: "select", data: {
                    api: getDict.bind(this, "js_warn_type"),
                    id: "dictValue",
                    name: "dictLabel"
                }
            }
        ],
        cols
    },
    "1019": {
        items: [
            ...items,
            // {
            //     field: "constructionStatus", label: "施工验收状态", type: "select", data: {
            //         api: getDict.bind(this, "zz_construct_state"),
            //         id: "dictValue",
            //         name: "dictLabel"
            //     }
            // },
            {
                field: "procState", label: "处理状态", type: "select", data: {
                    api: getDict.bind(this, "zz_proc_state"),
                    id: "dictValue",
                    name: "dictLabel"
                }
            },
            {
                field: "warnLevel", label: "异常级别", type: "select", data: {
                    api: getDict.bind(this, "ty_warn_level"),
                    id: "dictValue",
                    name: "dictLabel"
                }
            },
            {
                field: "warnType", label: "异常类型", type: "select", data: {
                    api: getDict.bind(this, "zz_warn_type"),
                    id: "dictValue",
                    name: "dictLabel"
                }
            }
        ],
        cols
    }
};

export const warn_config = Object.keys(default_system_config).reduce((accumulator, current) => {
    accumulator[current] = Object.assign({}, default_system_config[current], config[current]);
    return accumulator;
}, {});
