import request from "@/utils/request";

//企业列表
export function sourceList(query, page) {
  return request({
    url: `/zaozhi/source/list?${page}`,
    method: "post",
    data: query
  });
}

//检测点列表
export function checkpointList(query, page) {
  return request({
    url: `/zaozhi/checkpoint/list?${page}`,
    method: "post",
    data: query
  });
}

//检测点新增
export function checkpointAdd(query) {
  return request({
    url: `/zaozhi/checkpoint/add`,
    method: "post",
    data: query
  });
}

//检测点编辑
export function checkpointEdit(query) {
  return request({
    url: `/zaozhi/checkpoint/edit`,
    method: "post",
    data: query
  });
}

//检测点删除
export function checkpointDel(id) {
  return request({
    url: "/zaozhi/checkpoint/" + id,
    method: "delete"
  });
}

//检测点下拉
export function checkpointSelect(source_id) {
  return request({
    url: `/zaozhi/checkpoint/${source_id}`,
    method: "get"
  });
}

//因子列表
export function elementList(query, page) {
  return request({
    url: `/zaozhi/cp_element/list?${page}`,
    method: "post",
    data: query
  });
}

//因子新增
export function elementAdd(query) {
  return request({
    url: `/zaozhi/cp_element/add`,
    method: "post",
    data: query
  });
}

//因子编辑
export function elementEdit(query) {
  return request({
    url: `/zaozhi/cp_element/edit`,
    method: "post",
    data: query
  });
}

//因子删除
export function elementDel(id) {
  return request({
    url: "/zaozhi/cp_element/" + id,
    method: "delete"
  });
}

//因子类型
export function elementType(query) {
  return request({
    url: `/zaozhi/cp_element/ele_type`,
    method: "post",
    data: query || {}
  });
}

//视频列表
export function videoList(query, page) {
  return request({
    url: `/zaozhi/video/list?${page}`,
    method: "post",
    data: query
  });
}

//视频新增
export function videoAdd(query) {
  return request({
    url: `/zaozhi/video/add`,
    method: "post",
    data: query
  });
}

//视频编辑
export function videoEdit(query) {
  return request({
    url: `/zaozhi/video/edit`,
    method: "post",
    data: query
  });
}

//视频删除
export function videoDel(id) {
  return request({
    url: "/zaozhi/video/" + id,
    method: "delete"
  });
}

export function getToken(id) {
  return request({
    url: "/zaozhi/video/get_token?id=" + id,
    method: "get"
  });
}

export function getVideoWS(id) {
  return request({
    url: "/zaozhi/video/get_ws_address?id=" + id,
    method: "get"
  });
}
export function warnList(query, page) {
  return request({
    url: `/zaozhi/warn/list?${page}`,
    method: "post",
    data: query
  });
}



//生产用水量统计
export function eterpriseWater(query) {
  return request({
    url: `/zaozhi/statistical/product_water`,
    method: "get",
    params: query
  });
}

//生产用电量统计
export function eterpriseElect(query) {
  return request({
    url: `/zaozhi/statistical/product_elect`,
    method: "get",
    params: query
  });
}
//废水治理设施相关设备实时状态统计
export function eterpriseRealElect(query) {
  return request({
    url: `/zaozhi/statistical/real_elect`,
    method: "get",
    params: query
  });
}
//企业回用水量统计
export function eterpriseReuseWater(query) {
  return request({
    url: `/zaozhi/statistical/reuse_water`,
    method: "get",
    params: query
  });
}
//企业污水排放流量统计
export function eterpriseSewageAmt(query) {
  return request({
    url: `/zaozhi/statistical/sewage_amt`,
    method: "get",
    params: query
  });
}
