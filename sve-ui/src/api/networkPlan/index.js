import request from "@/utils/request";

//企业新增
export function lwjhSourceAdd(query) {
  return request({
    url: `/lwjh/source/add`,
    method: "post",
    data: query
  });
}

//企业列表
export function lwjhSourceList(query, page) {
  return request({
    url: `/lwjh/source/list?${page}`,
    method: "post",
    data: query
  });
}

//过滤企业下拉接口(未添加检测井企业)
export function lwjhSelectSource(query) {
  return request({
    url: `/lwjh/source/addList`,
    method: "post",
    data: query
  });
}

//获取企业基础资料
export function lwjhSourceInfo(query) {
  return request({
    url: `/lwjh/source/sourceInfo`,
    method: "post",
    data: query
  });
}
//编辑企业
export function lwjhSourceEdit(query) {
  return request({
    url: `/lwjh/source/edit`,
    method: "post",
    data: query
  });
}

//删除企业
export function lwjhSourceDel(id) {
  return request({
    url: "/lwjh/source/" + id,
    method: "delete"
  });
}

//监测点列表
export function lwjhPointList(query) {
  return request({
    url: `/lwjh/point/list`,
    method: "post",
    data: query
  });
}

//监测点新增
export function lwjhPointAdd(query) {
  return request({
    url: `/lwjh/point/add`,
    method: "post",
    data: query
  });
}

//监测点删除
export function lwjhPointDel(query) {
  return request({
    url: `/lwjh/point/remove`,
    method: "post",
    data: query
  });
}

//监测点新增
export function lwjhPointEdit(query) {
  return request({
    url: `/lwjh/point/edit`,
    method: "post",
    data: query
  });
}

//省厅导出
export function exportTable(query) {
  return request({
    url: `/base/source/export`,
    method: "post",
    data: query
  });
}
//市局
export function exportCityTable(query) {
  return request({
    url: `/base/source/exportCity`,
    method: "post",
    data: query
  });
}
