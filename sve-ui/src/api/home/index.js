import request from "@/utils/request";

//企业列表
export function getMapSourceList(query, page) {
  return request({
    url: `/enterprise/source/homeindex?${page}`,
    method: "post",
    data: query
  });
}
export function homeSourceList(query, page) {
  return request({
    url: `/dashboard/source/list?${page}`,
    method: "post",
    data: query
  });
}

//统计
// export function getStatis(query) {
//   return request({
//     url: `/enterprise/source/leftmenu`,
//     method: "post",
//     data: query
//   });
// }
export function getStatis(query) {
  return request({
    url: `/dashboard/statistics/source`,
    method: "get",
    params: query
  });
}
export function getStatisWarn(query) {
  return request({
    url: `/dashboard/statistics/warn`,
    method: "post",
    data: query
  });
}
//平台列表
export function platformSource(source_id) {
  return request({
    url: `/enterprise/source/tagBySource?sourceId=${source_id}`,
    method: "get"
  });
}

//施工进度
export function getConstructionModel(platform, sourceId) {
  return request({
    url: `/${platform}/construction/model/${sourceId}`,
    method: "get"
  });
}

//异常列表
export function warnList(query, platform, page) {
  return request({
    url: `/${platform}/warnmsg/list?${page}`,
    method: "post",
    data: query
  });
}

//实时数据查询
export function searchReal(query, platform) {
  return request({
    url: `/${platform}/data_search/real`,
    method: "post",
    data: query
  });
}

//分钟数据查询
export function searchMinute(query, platform) {
  return request({
    url: `/${platform}/data_search/minute`,
    method: "post",
    data: query
  });
}

//小时数据查询
export function searchHour(query, platform) {
  return request({
    url: `/${platform}/data_search/hour`,
    method: "post",
    data: query
  });
}

//日数据查询
export function searchDay(query, platform) {
  return request({
    url: `/${platform}/data_search/day`,
    method: "post",
    data: query
  });
}
