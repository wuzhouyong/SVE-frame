import request from "@/utils/request";

//实时数据查询
export function searchReal(query) {
  return request({
    url: `/outwater/data_search/real`,
    method: "post",
    data: query
  });
}

//分钟数据查询
export function searchMinute(query) {
  return request({
    url: `/outwater/data_search/minute`,
    method: "post",
    data: query
  });
}

//小时数据查询
export function searchHour(query) {
  return request({
    url: `/outwater/data_search/hour`,
    method: "post",
    data: query
  });
}
//小时数据查询(汇总)
export function searchHourSum(query) {
  return request({
    url: `/outwater/data_search/hourSum`,
    method: "post",
    data: query
  });
}

//日数据查询
export function searchDay(query) {
  return request({
    url: `/outwater/data_search/day`,
    method: "post",
    data: query
  });
}

//日数据查询(汇总)
export function searchDaySum(query) {
  return request({
    url: `/outwater/data_search/daySum`,
    method: "post",
    data: query
  });
}
