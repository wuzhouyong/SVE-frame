import request from "@/utils/request";

//企业列表
export function sourceList(query, page) {
    return request({
        url: `/jinshu/source/list?${page}`,
        method: "post",
        data: query
    });
}

//检测点列表
export function checkpointList(query, page) {
    return request({
        url: `/jinshu/checkpoint/list?${page}`,
        method: "post",
        data: query
    });
}

//检测点新增
export function checkpointAdd(query) {
    return request({
        url: `/jinshu/checkpoint/add`,
        method: "post",
        data: query
    });
}

//检测点编辑
export function checkpointEdit(query) {
    return request({
        url: `/jinshu/checkpoint/edit`,
        method: "post",
        data: query
    });
}

//检测点删除
export function checkpointDel(id) {
    return request({
        url: "/jinshu/checkpoint/" + id,
        method: "delete"
    });
}

//检测点下拉
export function checkpointSelect(source_id) {
    return request({
        url: `/jinshu/checkpoint/${source_id}`,
        method: "get"
    });
}

//因子列表
export function elementList(query, page) {
    return request({
        url: `/jinshu/cp_element/list?${page}`,
        method: "post",
        data: query
    });
}

//因子新增
export function elementAdd(query) {
    return request({
        url: `/jinshu/cp_element/add`,
        method: "post",
        data: query
    });
}

//因子编辑
export function elementEdit(query) {
    return request({
        url: `/jinshu/cp_element/edit`,
        method: "post",
        data: query
    });
}

//因子删除
export function elementDel(id) {
    return request({
        url: "/jinshu/cp_element/" + id,
        method: "delete"
    });
}

//因子类型
export function elementType(query) {
    return request({
        url: `/jinshu/cp_element/ele_type`,
        method: "post",
        data: query || {}
    });
}

//视频列表
export function videoList(query, page) {
    return request({
        url: `/jinshu/video/list?${page}`,
        method: "post",
        data: query
    });
}

//视频新增
export function videoAdd(query) {
    return request({
        url: `/jinshu/video/add`,
        method: "post",
        data: query
    });
}

//视频编辑
export function videoEdit(query) {
    return request({
        url: `/jinshu/video/edit`,
        method: "post",
        data: query
    });
}

//视频删除
export function videoDel(id) {
    return request({
        url: "/jinshu/video/" + id,
        method: "delete"
    });
}

export function getToken(id) {
    return request({
        url: "/jinshu/video/get_token?id=" + id,
        method: "get"
    });
}

export function getVideoWS(id) {
    return request({
        url: "/jinshu/video/get_ws_address?id=" + id,
        method: "get"
    });
}

//实时数据查询
export function searchReal(query) {
    return request({
        url: `/jinshu/data_search/real`,
        method: "post",
        data: query
    });
}

//分钟数据查询
export function searchMinute(query) {
    return request({
        url: `/jinshu/data_search/minute`,
        method: "post",
        data: query
    });
}

//小时数据查询
export function searchHour(query) {
    return request({
        url: `/jinshu/data_search/hour`,
        method: "post",
        data: query
    });
}
//小时数据查询(汇总)
export function searchHourSum(query) {
    return request({
        url: `/jinshu/data_search/hoursummary`,
        method: "post",
        data: query
    });
}

//日数据查询
export function searchDay(query) {
    return request({
        url: `/jinshu/data_search/day`,
        method: "post",
        data: query
    });
}

//日数据查询(汇总)
export function searchDaySum(query) {
    return request({
        url: `/jinshu/data_search/daysummary`,
        method: "post",
        data: query
    });
}

export function warnList(query, page) {
    return request({
        url: `/jinshu/warn/list?${page}`,
        method: "post",
        data: query
    });
}


//生产用水量统计
export function eterpriseWater(query) {
  return request({
    url: `/jinshu/statistical/product_water`,
    method: "get",
    params: query
  });
}

//生产用电量统计
export function eterpriseElect(query) {
  return request({
    url: `/jinshu/statistical/product_elect`,
    method: "get",
    params: query
  });
}
//废水治理设施相关设备实时状态统计
export function eterpriseRealElect(query) {
  return request({
    url: `/jinshu/statistical/real_elect`,
    method: "get",
    params: query
  });
}
//企业回用水量统计
export function eterpriseReuseWater(query) {
  return request({
    url: `/jinshu/statistical/reuse_water`,
    method: "get",
    params: query
  });
}
//企业污水排放流量统计
export function eterpriseSewageAmt(query) {
  return request({
    url: `/jinshu/statistical/sewage_amt`,
    method: "get",
    params: query
  });
}
