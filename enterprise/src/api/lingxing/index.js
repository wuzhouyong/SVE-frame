import request from "@/utils/request";

//收集池列表
export function lxStorageList (page) {
  return request({
    url: `/lingxing/storage/list?${page}`,
    method: "get"
  });
}

//新增收集池
export function lxStorageAdd (query) {
  return request({
    url: `/lingxing/storage/add`,
    method: "post",
    data: query
  });
}

//编辑收集池
export function lxStorageEdit (query) {
  return request({
    url: `/lingxing/storage/edit`,
    method: "post",
    data: query
  });
}

//删除收集池
export function lxStorageDel (id) {
  return request({
    url: `/lingxing/storage/delete/${id}`,
    method: "delete"
  });
}

// 水表api
//列表
export function lxMeterinfoList (query, page) {
  return request({
    url: `/lingxing/meter/list?${page}`,
    method: "post",
    data: query
  });
}

//查询数据
export function lxMeterinfoData (query) {
  return request({
    url: `/lingxing/meterinfo/data`,
    method: "get",
    params: query
  });
}

//新增
export function lxMeterinfoAdd (query) {
  return request({
    url: `/lingxing/meter/add`,
    method: "post",
    data: query
  });
}

//编辑
export function lxMeterinfoEdit (query) {
  return request({
    url: `/lingxing/meter/edit`,
    method: "post",
    data: query
  });
}

//删除
export function lxMeterinfoDel (id) {
  return request({
    url: `/lingxing/meter/remove/${id}`,
    method: "delete"
  });
}

//获取因子最大值
export function lxGetInfo (query) {
  return request({
    url: `/lingxing/getinfo`,
    method: "get",
    params: query
  });
}

//转运合同模块获取处置单位下拉
export function getHandleUnitList (query) {
  return request({
    url: `/lingxing/tankcar/enterprise`,
    method: "get",
    params: query
  });
}

//转运合同模块列表
export function UnitList (query) {
  return request({
    url: `/lingxing/contract/list`,
    method: "post",
    data: query
  });
}

//转运合同模块新增
export function addTransUnit (query) {
  return request({
    url: `/lingxing/contract/add`,
    method: "post",
    data: query
  });
}

//转运合同模块编辑
export function editTransUnit (query) {
  return request({
    url: `/lingxing/contract/edit`,
    method: "post",
    data: query
  });
}

//转运合同模块编辑
export function delTransUnit (ids) {
  return request({
    url: `/lingxing/contract/delete/${ids}`,
    method: "delete"
  });
}
