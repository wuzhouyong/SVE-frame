import request from "@/utils/request";

//企业列表
export function getSourceList(query, page) {
  return request({
    url: `/outwater/source/list?${page}`,
    method: "post",
    data: query
  });
}

//排水户企业列表(地图)
export const getMapSourceList = parm => {
  return request({
    url: `/enterprise/source/listMap?${parm}`,
    method: "get"
  });
};

//数采新增
export function collectAdd(query) {
  return request({
    url: `/outwater/basCollect/add`,
    method: "post",
    data: query
  });
}

//数采修改
export function collectEdit(query) {
  return request({
    url: `/outwater/basCollect/edit`,
    method: "post",
    data: query
  });
}

//删除
export function collectDel(id) {
  return request({
    url: "/outwater/basCollect/" + id,
    method: "delete"
  });
}

//数采列表
export function collectList(query, page) {
  return request({
    url: `/outwater/basCollect/list?${page}`,
    method: "post",
    data: query
  });
}

//数采下拉接口
export function collectSelectList(query) {
  return request({
    url: `/outwater/basCollect/idNameList`,
    method: "post",
    data: query
  });
}

//检测井列表
export function checkPointList(query, page) {
  return request({
    url: `/outwater/checkpoint/list?${page}`,
    method: "post",
    data: query
  });
}

//检测井新增
export function checkPointAdd(query) {
  return request({
    url: `/outwater/checkpoint/add`,
    method: "post",
    data: query
  });
}

//检测井编辑
export function checkPointEdit(query) {
  return request({
    url: `/outwater/checkpoint/edit`,
    method: "post",
    data: query
  });
}

//检测井删除
export function checkPointDel(id) {
  return request({
    url: "/outwater/checkpoint/" + id,
    method: "delete"
  });
}

//检测井下拉
export function pointSelectList(query) {
  return request({
    url: `/outwater/checkpoint/idNameList`,
    method: "post",
    data: query
  });
}

//因子列表
export function elementList(query, page) {
  return request({
    url: `/outwater/checkPointElement/list?${page}`,
    method: "post",
    data: query
  });
}

//因子新增
export function elementAdd(query) {
  return request({
    url: `/outwater/checkPointElement/add`,
    method: "post",
    data: query
  });
}

//因子编辑
export function elementEdit(query) {
  return request({
    url: `/outwater/checkPointElement/edit`,
    method: "post",
    data: query
  });
}

//因子删除
export function elementDel(id) {
  return request({
    url: "/outwater/checkPointElement/" + id,
    method: "delete"
  });
}

//因子类型
export function elementType(query) {
  return request({
    url: `/outwater/checkPointElement/elementIdNameList`,
    method: "post",
    data: query
  });
}

export function warnList(query, page) {
  return request({
    url: `/outwater/warn/list?${page}`,
    method: "post",
    data: query
  });
}

//获取mn
export function getMn() {
  return request({
    url: `/outwater/checkpoint/mn`,
    method: "get"
  });
}

//导出
export const exportTable = query => {
  return request({
    url: `/com/export/outwater`,
    method: "post",
    data: query
  });
};

//看板
//镇街异常排名
export function townWarnRank() {
  return request({
    url: `/outwater/monitor/warning_rank`,
    method: "get"
  });
}

//近30天预警数
export function warningMonth() {
  return request({
    url: `/outwater/monitor/warning_month`,
    method: "get"
  });
}

//预警数
export function warningQuantity() {
  return request({
    url: `/outwater/monitor/warning_quantity`,
    method: "get"
  });
}

//设备数量
export function equipmentQuantity() {
  return request({
    url: `/outwater/monitor/equipment_quantity`,
    method: "get"
  });
}

//预警产生趋势
export function warningTrend() {
  return request({
    url: `/outwater/monitor/warning_trend`,
    method: "get"
  });
}

//污染最严重企业top10
export function warningPollution() {
  return request({
    url: `/outwater/monitor/warning_pollution`,
    method: "get"
  });
}

//近24小时
export function pollutionAnalysis() {
  return request({
    url: `/outwater/monitor/pollution_analysis`,
    method: "get"
  });
}

//异常阈值列表

export function pshBoundaryList(query = {}) {
  return request({
    url: `/outwater/except/boundaryList`,
    method: "post",
    data: query
  });
};

export function pshBoundaryEdit(query) {
  return request({
    url: `/outwater/except/edit`,
    method: "post",
    data: query
  });
};

//不报警异常
export function pshExceptSpecList(query, page) {
  return request({
    url: `/outwater/except/specList?${page}`,
    method: "post",
    data: query
  });
};

//镇街异常情况汇总
export function abnormalTown(query) {
  return request({
    url: `/outwater/abnormal/town`,
    method: "post",
    data: query
  });
}

//企业异常情况汇总
export function abnormalEnterprise(query) {
  return request({
    url: `/outwater/abnormal/enterprise`,
    method: "post",
    data: query
  });
}

export function exportEnterprise(query) {
  return request({
    url: `/outwater/abnormal/export_enterprise`,
    method: "post",
    data: query
  });
}

export function exportTown(query) {
  return request({
    url: `/outwater/abnormal/export_town`,
    method: "post",
    data: query
  });
}

export const exportSpecListTable = query => {
  return request({
    url: `/outwater/except/specList_export`,
    method: "post",
    data: query
  });
};

export function vocTownWarnAmt(query) {
  return request({
    url: `/voc/statistical/town_warn_amt`,
    method: "get",
    params: query
  });
};

export function vocTownWarnEnterprise(townName) {
  return request({
    url: `/voc/statistical/town_warn_enterprise?townName=${townName}`,
    method: "get",
  });
};
