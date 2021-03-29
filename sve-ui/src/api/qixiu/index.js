import request from "@/utils/request";

//企业列表
export function sourceList(query, page) {
  return request({
    url: `/qixiu/source/list?${page}`,
    method: "post",
    data: query
  });
}

//排放口列表
export function outletList(query, page) {
  return request({
    url: `/qixiu/outlet/list?${page}`,
    method: "post",
    data: query
  });
}

//排放口新增
export function outletAdd(query) {
  return request({
    url: `/qixiu/outlet/add`,
    method: "post",
    data: query
  });
}

//排放口编辑
export function outletEdit(query) {
  return request({
    url: `/qixiu/outlet/edit`,
    method: "post",
    data: query
  });
}

//排放口删除
export function outletDel(id) {
    return request({
      url: "/qixiu/outlet/" + id,
      method: "delete"
    });
  }

//检测点列表
export function checkpointList(query, page) {
  return request({
    url: `/qixiu/checkpoint/list?${page}`,
    method: "post",
    data: query
  });
}

//检测点新增
export function checkpointAdd(query) {
  return request({
    url: `/qixiu/checkpoint/add`,
    method: "post",
    data: query
  });
}

//检测点编辑
export function checkpointEdit(query) {
  return request({
    url: `/qixiu/checkpoint/edit`,
    method: "post",
    data: query
  });
}

//检测点删除
export function checkpointDel(id) {
  return request({
    url: "/qixiu/checkpoint/" + id,
    method: "delete"
  });
}

//检测点下拉
export function checkpointSelect(source_id) {
  return request({
    url: `/qixiu/checkpoint/${source_id}`,
    method: "get"
  });
}

//因子列表
export function elementList(query, page) {
  return request({
    url: `/qixiu/cp_element/list?${page}`,
    method: "post",
    data: query
  });
}

//因子新增
export function elementAdd(query) {
  return request({
    url: `/qixiu/cp_element/add`,
    method: "post",
    data: query
  });
}

//因子编辑
export function elementEdit(query) {
  return request({
    url: `/qixiu/cp_element/edit`,
    method: "post",
    data: query
  });
}

//因子删除
export function elementDel(id) {
  return request({
    url: "/qixiu/cp_element/" + id,
    method: "delete"
  });
}

//因子类型
export function elementType(query) {
  return request({
    url: `/qixiu/cp_element/ele_type`,
    method: "post",
    data: query || {}
  });
}

//视频列表
export function videoList(query, page) {
  return request({
    url: `/qixiu/video/list?${page}`,
    method: "post",
    data: query
  });
}

//视频新增
export function videoAdd(query) {
  return request({
    url: `/qixiu/video/add`,
    method: "post",
    data: query
  });
}

//视频编辑
export function videoEdit(query) {
  return request({
    url: `/qixiu/video/edit`,
    method: "post",
    data: query
  });
}

//视频删除
export function videoDel(id) {
  return request({
    url: "/qixiu/video/" + id,
    method: "delete"
  });
}

export function getToken(id) {
  return request({
    url: "/qixiu/video/get_token?id=" + id,
    method: "get"
  });
}

export function getVideoWS(id) {
  return request({
    url: "/qixiu/video/get_ws_address?id=" + id,
    method: "get"
  });
}

//实时数据查询
export function searchReal(query) {
  return request({
    url: `/qixiu/data_search/real`,
    method: "post",
    data: query
  });
}

//分钟数据查询
export function searchMinute(query) {
  return request({
    url: `/qixiu/data_search/minute`,
    method: "post",
    data: query
  });
}

//小时数据查询
export function searchHour(query) {
  return request({
    url: `/qixiu/data_search/hour`,
    method: "post",
    data: query
  });
}
//小时数据查询(汇总)
export function searchHourSum(query) {
  return request({
    url: `/qixiu/data_search/hoursummary`,
    method: "post",
    data: query
  });
}

//日数据查询
export function searchDay(query) {
  return request({
    url: `/qixiu/data_search/day`,
    method: "post",
    data: query
  });
}

//日数据查询(汇总)
export function searchDaySum(query) {
  return request({
    url: `/qixiu/data_search/daysummary`,
    method: "post",
    data: query
  });
}

export function warnList(query, page) {
  return request({
    url: `/qixiu/warn/list?${page}`,
    method: "post",
    data: query
  });
}


// 排放统计
export function qxTownEmission(query) {
  return request({
    url: `/qixiu/statistical/town_emission`,
    method: "get",
    params: query
  });
}

// 镇街排放统计
export function qxEnterpriseEmission(query) {
  return request({
    url: `/qixiu/statistical/enterprise_emission`,
    method: "get",
    params: query
  });
}
