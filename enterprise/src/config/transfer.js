import { index_system_config } from "./system";
import { procstate, chart, real, minute, hour, day } from "@/api/transfer"

// const data_types = [
//     { value: 0, name: "实时数据" },
//     { value: 1, name: "分钟数据" },
//     { value: 2, name: "小时数据" },
//     { value: 3, name: "日数据" }
// ];

// const apis = [real, minute, hour, day];

// const config = {
//     "1012": {
//         data_types: [...data_types].filter(f => f.value !== 0)
//     }
// };

export const transfer_config = Object.keys(index_system_config)
    .map(m => {
        let cfg = JSON.parse(JSON.stringify(Object.assign({}, index_system_config[m])));
        const { path } = cfg;
        cfg.procstate = procstate.bind(this, path);
        cfg.chart = chart.bind(this, path);
        // cfg.data_types.forEach(e => {
        //     let api = apis[e.value];
        //     e.api = api.bind(this, path);
        // });
        return { [m]: cfg };
    })
    .reduce((accumulator, current) => ({ ...accumulator, ...current }), {});