import request from "@/utils/request";

//实时数据查询
export function real(prefix, query, isDgom = true) {
  let data = {};
  if (isDgom) {
    let date = {
      startDate: query.date + " " + query.time
    };
    data = Object.assign({}, query, date);
  } else {
    data = Object.assign({}, query);
  }

  return request({
    url: `/${prefix}/data_search/real`,
    method: "post",
    data: data
  });
}

//分钟数据查询
export function minute(prefix, query) {
  const date = { startDate: query.startTime };
  const data = Object.assign({}, query, date);
  return request({
    url: `/${prefix}/data_search/minute`,
    method: "post",
    data: data
  });
}

//小时数据查询
export function hour(prefix, query) {
  const date = { startDate: query.startTime };
  const data = Object.assign({}, query, date);
  return request({
    url: `/${prefix}/data_search/hour`,
    method: "post",
    data: data
  });
}
//小时数据查询(汇总)
export function hour_summary(prefix, query) {
  const date = { startDate: query.startTime };
  const data = Object.assign({}, query, date);
  return request({
    url: `/${prefix}/data_search/hoursummary`,
    method: "post",
    data: data
  });
}

//日数据查询
export function day(prefix, query) {
  let date = {
    startDate: query.startTime[0],
    endDate: query.startTime[1]
  };
  const data = Object.assign({}, query, date);
  return request({
    url: `/${prefix}/data_search/day`,
    method: "post",
    data: data
  });
}

//日数据查询(汇总)
export function day_summary(prefix, query) {
  let date = {
    startDate: query.startTime[0],
    endDate: query.startTime[1]
  };
  const data = Object.assign({}, query, date);
  return request({
    url: `/${prefix}/data_search/daysummary`,
    method: "post",
    data: data
  });
}

export function warn(query, page) {
  return request({
    url: `/${prefix}/warn/list?${page}`,
    method: "post",
    data: query
  });
}
//实时数据表头
export function dgomHearderReal(query) {
  return request({
    url: `/dgom/data_search/header_real`,
    method: "post",
    data: query
  });
}

//分钟,小时数据表头
export function dgomHearderHM(query) {
  return request({
    url: `/dgom/data_search/header_hour`,
    method: "post",
    data: query
  });
}
//日
export function dgomHearderDay(query) {
  return request({
    url: `/dgom/data_search/header_day`,
    method: "post",
    data: query
  });
}