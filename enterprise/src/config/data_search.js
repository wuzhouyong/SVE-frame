import { parseTime, addDay } from "@/utils/ruoyi";
import { outwaterList } from "@/api/collect";
import { list as outletList, dgomCheckPoint, yyOutletList, jyOutletList } from "@/api/outlet";
import { system_config } from "./system";

const date = new Date();
const start = parseTime(addDay(date, -7)).substr(0, 10);
const end = parseTime(date).substr(0, 10);
const time = (date.getHours() - 1).toString().padStart(2, "0");

const data_types = [
  {
    value: 0,
    name: "实时数据",
    items: [
      { field: "date", label: "选择日期", type: "date", value: end },
      { field: "time", label: "选择时间", type: "time", value: `${time}:00` }
    ]
  },
  {
    value: 1,
    name: "分钟数据",
    items: [{ field: "startTime", label: "选择日期", type: "date", value: end }]
  },
  {
    value: 2,
    name: "小时数据",
    items: [{ field: "startTime", label: "选择日期", type: "date", value: end }]
  },
  {
    value: 3,
    name: "日数据",
    items: [
      {
        field: "startTime",
        label: "选择日期",
        type: "daterange",
        value: [start, end]
      }
    ]
  }
];

const config = {
  "1002": {
    data_types
  },
  "1003": {
    data_types
  },
  "1018": {
    data_types
  },
  "1004": {
    data_types
    // items: [
    //   {
    //     field: "outletId",
    //     label: "排放口",
    //     type: "select",
    //     data: {
    //       api: outletList.bind(this, "voc", {}),
    //       id: "outletId",
    //       name: "outletName"
    //     }
    //   }
    // ]
  },
  "1006": {
    data_types,
    items: [
      {
        field: "outletId",
        label: "排放口",
        type: "select",
        data: {
          api: yyOutletList,
          id: "facilityId",
          name: "facilityName"
        }
      }
    ]
  },
  "1012": {
    no_summary: true,
    data_types: data_types.filter(f => f.value != 0),
    items: [
      {
        field: "checkPointId",
        label: "检测井",
        type: "select",
        data: {
          api: outwaterList,
          id: "id",
          name: "name"
        }
      }
    ]
  },
  "1013": {
    data_types,
    items: [
      {
        field: "outletId",
        label: "排放口",
        type: "select",
        data: {
          api: outletList.bind(this, "qixiu", {}, "pageNum=1&pageSize=100"),
          id: "outletId",
          name: "outletName"
        }
      }
    ]
  },
  "1014": {
    data_types,
    items: [
      {
        field: "outletId",
        label: "加油枪",
        type: "select",
        data: {
          api: jyOutletList,
          id: "outletId",
          name: "outletName"
        }
      }
    ]
  },
  "1015": {
    data_types
  },
  "1019": {
    data_types
  },
  "1001": {
    data_types: [
      {
        value: 0,
        name: "实时数据",
        items: []
      },
      {
        value: 1,
        name: "分钟数据",
        items: [
          { field: "startTime", label: "选择日期", type: "date", value: end }
        ]
      },
      {
        value: 2,
        name: "小时数据",
        items: [
          { field: "startTime", label: "选择日期", type: "date", value: end }
        ]
      },
      {
        value: 3,
        name: "日数据",
        items: [
          {
            field: "startTime",
            label: "选择日期",
            type: "daterange",
            value: [start, end]
          }
        ]
      }
    ],
    items: [
      {
        field: "checkpointid",
        label: "排放口",
        type: "select",
        data: {
          api: dgomCheckPoint,
          id: "zcheckpointid",
          name: "zcheckpointdesc"
        }
      }
    ]
  }
};

export const data_search_config = Object.keys(system_config).reduce(
  (accumulator, current) => {
    accumulator[current] = Object.assign(
      {},
      system_config[current],
      config[current]
    );
    return accumulator;
  },
  {}
);
