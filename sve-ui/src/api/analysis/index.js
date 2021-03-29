import request from "@/utils/request";

//镇街任务统计
export function countTown (query) {
  return request({
    url: `/task/count/town`,
    method: "get",
    params: query
  });
};

//施工单位任务统计
export function countConstruct (query) {
  return request({
    url: `/task/count/construct`,
    method: "get",
    params: query
  });
};

//饼状图汇总
export function countSummary (query) {
  return request({
    url: `/task/count/summary`,
    method: "get",
    params: query
  });
};

//监控任务排名
export function countSchedule (query) {
  return request({
    url: `/task/count/schedule`,
    method: "get",
    params: query
  });
};

//我的排名
export function countCompleted (query) {
  return request({
    url: `/task/count/completed`,
    method: "get",
    params: query
  });
};

//总排名
export function countLeaderboard () {
  return request({
    url: `/task/count/leaderboard?listTypes=1,2,3,4,6`,
    method: "get"
  });
};

//未登录
export function notlogin (query) {
  return request({
    url: `/task/count/notlogin`,
    method: "get",
    params: query
  });
};

//考评
export function taskCountScore () {
  return request({
    url: `/task/count/score`,
    method: "get"
  });
};

export function taskCountStatusCount () {
  return request({
    url: `/task/count/status_count`,
    method: "get"
  });
};

export function taskCountMission (v) {
  return request({
    url: `/task/count/mission?listType=${v}`,
    method: "get"
  });
};

//统计未完成(饼图)
export function taskCountMissionBlame (query) {
  return request({
    url: `/task/count/mission_blame`,
    method: "post",
    data: query
  });
};

//统计已完成(饼图)
export function taskCountAccepted (query) {
  return request({
    url: `/task/count/accepted`,
    method: "post",
    data: query
  });
};

//统计左侧
export function taskCountAddition (query) {
  return request({
    url: `/task/count/addition_number`,
    method: "get",
    params: query
  });
};

//统计全部(饼图)
export function taskCountAggregate (query) {
  return request({
    url: `/task/count/aggregate_number`,
    method: "post",
    data: query
  });
};

//市局看板气表格
// export function taskScoreGas() {
//   return request({
//     url: `/task/count/score_gas_list`,
//     method: "get"
//   });
// };

export function taskScoreGas () {
  return request({
    url: `/test_score/town_gas_summary`,
    method: "get"
  });
};

//市局看板水表格
// export function taskScoreWater() {
//   return request({
//     url: `/task/count/score_water_list`,
//     method: "get"
//   });
// };

export function taskScoreWater () {
  return request({
    url: `/test_score/town_water_summary`,
    method: "get"
  });
};
//市局看板总表格
// export function taskScoreAll() {
//   return request({
//     url: `/task/count/score_list`,
//     method: "get"
//   });
// };

export function taskScoreAll () {
  return request({
    url: `/test_score/town_all_summary`,
    method: "get"
  });
};

// 气导出
export function taskExportGas () {
  return request({
    url: `/test_score/export_town_gas_summary`,
    method: "get"
  });
};

//水导出
export function taskExportWater () {
  return request({
    url: `/test_score/export_town_water_summary`,
    method: "get"
  });
};

//全部导出
export function taskExportAll () {
  return request({
    url: `/test_score/export_town_all_summary`,
    method: "get"
  });
};

//饼图点击企业
export function taskAggregate (query, page) {
  return request({
    url: `/task/count/aggregate?${page}`,
    method: "post",
    data: query
  });
};

//排放趋势分析数采拉下
export function currencyCheckpoint (tag, source_id) {
  return request({
    url: `/${tag}/checkpoint/${source_id}`,
    method: "get"
  });
}

//排放趋势分析因子下拉
export function currencyElement (tag, elId) {
  return request({
    url: `/${tag}/statistical/element_end_discharge/${elId}`,
    method: "get"
  });
}

//排放趋势分析图表数据
export function currencyTrend (tag, query) {
  return request({
    url: `/${tag}/statistical/discharge_trend`,
    method: "get",
    params: query
  });
}
