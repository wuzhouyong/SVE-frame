import request from "@/utils/request";

//企业列表
export function sourceList(query, page) {
  return request({
    url: `/voc/source/list?${page}`,
    method: "post",
    data: query
  });
}

//排放口列表
export function outletList(query, page) {
  return request({
    url: `/voc/outlet/list?${page}`,
    method: "post",
    data: query
  });
}

//排放口新增
export function outletAdd(query) {
  return request({
    url: `/voc/outlet/add`,
    method: "post",
    data: query
  });
}

//排放口编辑
export function outletEdit(query) {
  return request({
    url: `/voc/outlet/edit`,
    method: "post",
    data: query
  });
}

//排放口删除
export function outletDel(id) {
  return request({
    url: "/voc/outlet/" + id,
    method: "delete"
  });
}

//检测点列表
export function checkpointList(query, page) {
  return request({
    url: `/voc/checkpoint/list?${page}`,
    method: "post",
    data: query
  });
}

//检测点新增
export function checkpointAdd(query) {
  return request({
    url: `/voc/checkpoint/add`,
    method: "post",
    data: query
  });
}

//检测点编辑
export function checkpointEdit(query) {
  return request({
    url: `/voc/checkpoint/edit`,
    method: "post",
    data: query
  });
}

//检测点删除
export function checkpointDel(id) {
  return request({
    url: "/voc/checkpoint/" + id,
    method: "delete"
  });
}

//检测点下拉
export function checkpointSelect(source_id) {
  return request({
    url: `/voc/checkpoint/${source_id}`,
    method: "get"
  });
}

//因子列表
export function elementList(query, page) {
  return request({
    url: `/voc/cp_element/list?${page}`,
    method: "post",
    data: query
  });
}

//因子新增
export function elementAdd(query) {
  return request({
    url: `/voc/cp_element/add`,
    method: "post",
    data: query
  });
}

//因子编辑
export function elementEdit(query) {
  return request({
    url: `/voc/cp_element/edit`,
    method: "post",
    data: query
  });
}

//因子删除
export function elementDel(id) {
  return request({
    url: "/voc/cp_element/" + id,
    method: "delete"
  });
}

//因子类型
export function elementType(query) {
  return request({
    url: `/voc/cp_element/ele_type`,
    method: "post",
    data: query || {}
  });
}

//视频列表
export function videoList(query, page) {
  return request({
    url: `/voc/video/list?${page}`,
    method: "post",
    data: query
  });
}

//视频新增
export function videoAdd(query) {
  return request({
    url: `/voc/video/add`,
    method: "post",
    data: query
  });
}

//视频编辑
export function videoEdit(query) {
  return request({
    url: `/voc/video/edit`,
    method: "post",
    data: query
  });
}

//视频删除
export function videoDel(id) {
  return request({
    url: "/voc/video/" + id,
    method: "delete"
  });
}

export function getToken(id) {
  return request({
    url: "/voc/video/get_token?id=" + id,
    method: "get"
  });
}

export function getVideoWS(id) {
  return request({
    url: "/voc/video/get_ws_address?id=" + id,
    method: "get"
  });
}

//实时数据查询
export function searchReal(query) {
  return request({
    url: `/voc/data_search/real`,
    method: "post",
    data: query
  });
}

//分钟数据查询
export function searchMinute(query) {
  return request({
    url: `/voc/data_search/minute`,
    method: "post",
    data: query
  });
}

//小时数据查询
export function searchHour(query) {
  return request({
    url: `/voc/data_search/hour`,
    method: "post",
    data: query
  });
}
//小时数据查询(汇总)
export function searchHourSum(query) {
  return request({
    url: `/voc/data_search/hoursummary`,
    method: "post",
    data: query
  });
}

//日数据查询
export function searchDay(query) {
  return request({
    url: `/voc/data_search/day`,
    method: "post",
    data: query
  });
}

//日数据查询(汇总)
export function searchDaySum(query) {
  return request({
    url: `/voc/data_search/daysummary`,
    method: "post",
    data: query
  });
}

export function warnList(query, page) {
  return request({
    url: `/voc/warn/list?${page}`,
    method: "post",
    data: query
  });
}

export function workingCondition(query) {
  return request({
    url: `/voc/facility/working_condition/details`,
    method: "get",
    params: query
  });
}

export function workingGetinfo(query) {
  return request({
    url: `/voc/facility/working_condition/getinfo`,
    method: "get",
    params: query
  });
}

//生成线列表
export function ProductionList(query) {
  return request({
    url: `/voc/facility/production/list`,
    method: "get",
    params: query
  });
}

//收集风机列表
export function collectBlowerList(query) {
  return request({
    url: `/voc/facility/collect_blower/list`,
    method: "get",
    params: query
  });
}

//治理设施列表
export function FacilityList(query) {
  return request({
    url: `/voc/facility/handle/list`,
    method: "get",
    params: query
  });
}


//排放风机列表
export function EmissionList(query) {
    return request({
      url: `/voc/facility/emission_blower/list`,
      method: "get",
      params: query
    });
  }

//排放口列表
export function EmissionPointList(query) {
    return request({
      url: `/voc/facility/emission_point/list`,
      method: "get",
      params: query
    });
  }


  // 排放统计
export function vocTownEmission(query) {
  return request({
    url: `/voc/statistical/town_emission`,
    method: "get",
    params: query
  });
}

// 镇街排放统计
export function vocEnterpriseEmission(query) {
  return request({
    url: `/voc/statistical/enterprise_emission`,
    method: "get",
    params: query
  });
}
