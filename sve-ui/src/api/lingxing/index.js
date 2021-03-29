import request from "@/utils/request";

//企业列表
export function sourceList (query, page) {
  return request({
    url: `/lingxing/source/list?${page}`,
    method: "post",
    data: query
  });
}

//检测点列表
export function checkpointList (query, page) {
  return request({
    url: `/lingxing/checkpoint/list?${page}`,
    method: "post",
    data: query
  });
}

//检测点新增
export function checkpointAdd (query) {
  return request({
    url: `/lingxing/checkpoint/add`,
    method: "post",
    data: query
  });
}

//检测点编辑
export function checkpointEdit (query) {
  return request({
    url: `/lingxing/checkpoint/edit`,
    method: "post",
    data: query
  });
}

//检测点删除
export function checkpointDel (id) {
  return request({
    url: "/lingxing/checkpoint/" + id,
    method: "delete"
  });
}

//检测点下拉
export function checkpointSelect (source_id) {
  return request({
    url: `/lingxing/checkpoint/${source_id}`,
    method: "get"
  });
}

//因子列表
export function elementList (query, page) {
  return request({
    url: `/lingxing/cp_element/list?${page}`,
    method: "post",
    data: query
  });
}

//因子新增
export function elementAdd (query) {
  return request({
    url: `/lingxing/cp_element/add`,
    method: "post",
    data: query
  });
}

//因子编辑
export function elementEdit (query) {
  return request({
    url: `/lingxing/cp_element/edit`,
    method: "post",
    data: query
  });
}

//因子删除
export function elementDel (id) {
  return request({
    url: "/lingxing/cp_element/" + id,
    method: "delete"
  });
}

//因子类型
export function elementType (query) {
  return request({
    url: `/lingxing/cp_element/ele_type`,
    method: "post",
    data: query || {}
  });
}

//视频列表
export function videoList (query, page) {
  return request({
    url: `/lingxing/video/list?${page}`,
    method: "post",
    data: query
  });
}

//视频新增
export function videoAdd (query) {
  return request({
    url: `/lingxing/video/add`,
    method: "post",
    data: query
  });
}

//视频编辑
export function videoEdit (query) {
  return request({
    url: `/lingxing/video/edit`,
    method: "post",
    data: query
  });
}

//视频删除
export function videoDel (id) {
  return request({
    url: "/lingxing/video/" + id,
    method: "delete"
  });
}

export function getToken (id) {
  return request({
    url: "/lingxing/video/get_token?id=" + id,
    method: "get"
  });
}

export function getVideoWS (id) {
  return request({
    url: "/lingxing/video/get_ws_address?id=" + id,
    method: "get"
  });
}

//实时数据查询
export function searchReal (query) {
  return request({
    url: `/lingxing/data_search/real`,
    method: "post",
    data: query
  });
}

//分钟数据查询
export function searchMinute (query) {
  return request({
    url: `/lingxing/data_search/minute`,
    method: "post",
    data: query
  });
}

//小时数据查询
export function searchHour (query) {
  return request({
    url: `/lingxing/data_search/hour`,
    method: "post",
    data: query
  });
}

//小时数据查询(汇总)
export function searchHourSum (query) {
  return request({
    url: `/lingxing/data_search/hoursummary`,
    method: "post",
    data: query
  });
}

//日数据查询
export function searchDay (query) {
  return request({
    url: `/lingxing/data_search/day`,
    method: "post",
    data: query
  });
}

//日数据查询(汇总)
export function searchDaySum (query) {
  return request({
    url: `/lingxing/data_search/daysummary`,
    method: "post",
    data: query
  });
}

export function warnList (query, page) {
  return request({
    url: `/lingxing/warn/list?${page}`,
    method: "post",
    data: query
  });
}

//水池水桶列表
export function lxStorageList (query) {
  return request({
    url: `/lingxing/storage/list?`,
    method: "get",
    params: query
  });
}

//水表列表
export function lxMeterinfoList (query, page) {
  return request({
    url: `/lingxing/meter/list?${page}`,
    method: "post",
    data: query
  });
}

//处置企业列表
export function tankcarEnterprise (query) {
  return request({
    url: `/lingxing/tankcar/enterprise`,
    method: "get",
    params: query
  });
}

//车辆列表
export function tankcarList (query, page) {
  return request({
    url: `/lingxing/tankcar/list?${page}`,
    method: "post",
    data: query
  });
}

//车辆新增
export function tankcarAdd (query) {
  return request({
    url: `/lingxing/tankcar/add`,
    method: "post",
    data: query
  });
}

//车辆修改
export function tankcarEdit (query) {
  return request({
    url: `/lingxing/tankcar/edit`,
    method: "post",
    data: query
  });
}

//车辆删除
export function tankcarDel (id) {
  return request({
    url: "/lingxing/tankcar/delete/" + id,
    method: "delete"
  });
}

//水罐列表
export function truckList (query, page) {
  return request({
    url: `/lingxing/tankcar/truck_list?${page}`,
    method: "post",
    data: query
  });
}

//水罐新增
export function truckAdd (query) {
  return request({
    url: `/lingxing/tankcar/truck_add`,
    method: "post",
    data: query
  });
}

//水罐修改
export function truckEdit (query) {
  return request({
    url: `/lingxing/tankcar/truck_edit`,
    method: "post",
    data: query
  });
}

//水罐删除
export function truckDel (id) {
  return request({
    url: "/lingxing/tankcar/truck_delete/" + id,
    method: "delete"
  });
}

//水量统计,水表
export function meterinfoDay (query) {
  return request({
    url: `/lingxing/meterinfo/day`,
    method: "get",
    params: query
  });
}

//废水池接口(小时,细分)
export function storageHour (query) {
  return request({
    url: `/lingxing/storage/hour`,
    method: "get",
    params: query
  });
}

//废水池接口(小时,总)
export function storageTotalHour (query) {
  return request({
    url: `/lingxing/storage_total/hour`,
    method: "get",
    params: query
  });
}

//废水池接口(分钟,细分)
export function storageMinute (query) {
  return request({
    url: `/lingxing/storage/minute`,
    method: "get",
    params: query
  });
}

//废水池接口(分钟,总)
export function storageTotalMinute (query) {
  return request({
    url: `/lingxing/storage_total/minute`,
    method: "get",
    params: query
  });
}

//零星废水轨迹车牌查询
export function getTankCar (sourceId) {
  return request({
    url: `/lingxing/tankcar/list/${sourceId}`,
    method: "get"
  });
}

//零星废水联单查询
export function getTankCarTrans (query) {
  return request({
    url: `/lingxing/tankcar/trans`,
    method: "get",
    params: query
  });
}

//车辆轨迹
export function getTrail (jobHeadid) {
  return request({
    url: `/lingxing/tankcar/track_list/${jobHeadid}`,
    method: "get"
  });
}

//工作单列表
export function getTransJobHead (query, page) {
  return request({
    url: `/lingxing/trans_jobhead/list?${page}`,
    method: "post",
    data: query
  });
}

//联单列表
export function getJobdetailList (v) {
  return request({
    url: `/lingxing/trans_jobdetail/${v}`,
    method: "get"
  });
}

// 司机资料

export function driverinfoList (query, page) {
  return request({
    url: `/lingxing/driverinfo/list?${page}`,
    method: "post",
    data: query
  });
}

export function driverinfoAdd (query) {
  return request({
    url: `/lingxing/driverinfo/add`,
    method: "post",
    data: query
  });
}

export function driverinfoEdit (query) {
  return request({
    url: `/lingxing/driverinfo/edit`,
    method: "post",
    data: query
  });
}

export function driverinfoDel (id) {
  return request({
    url: "/lingxing/driverinfo/" + id,
    method: "delete"
  });
}

//联单明细
export function transBillinfo (v) {
  return request({
    url: `/lingxing/trans_billinfo/${v}`,
    method: "get"
  });
}

export function transjobList (query) {
  return request({
    url: `/lingxing/trans_jobdetail/list`,
    method: "get",
    params: query
  });
}

//工况图
export function workingCondition (v) {
  return request({
    url: `/lingxing/working_condition/${v}`,
    method: "get"
  });
}

//工况图6小时数据
export function workingMinute (v) {
  return request({
    url: `/lingxing/working_minute/${v}`,
    method: "get"
  });
}

//危废转移联单
export function lxBillinfoList (query, page) {
  return request({
    url: `/lingxing/source/billinfo_list?${page}`,
    method: "post",
    data: query
  });
}

//零废转移统计
export function lxTransferAmount (query, page) {
  return request({
    url: `/lingxing/statistical/transfer_amount?${page}`,
    method: "post",
    data: query
  });
}

//生产用水量统计
export function lxEterpriseWater (query) {
  return request({
    url: `/lingxing/statistical/enterprise_water`,
    method: "get",
    params: query
  });
}

//水池占用比统计
export function lxStorageRate (query) {
  return request({
    url: `/lingxing/statistical/storage_rate`,
    method: "get",
    params: query
  });
}

//零废接收量统计
export function lxTransBillReceive (query) {
  return request({
    url: `/lingxingp/statistical/trans_bill_receive`,
    method: "get",
    params: query
  });
}

//进水量统计
export function lxpInflowAmt (query) {
  return request({
    url: `/lingxingp/statistical/inflow_amt`,
    method: "get",
    params: query
  });
}

//排放水量统计
export function lxpEmissionsAmt (query) {
  return request({
    url: `/lingxingp/statistical/emissions_amt`,
    method: "get",
    params: query
  });
}

//用电量统计
export function lxpElectAmt (query) {
  return request({
    url: `/lingxingp/statistical/elect_amt`,
    method: "get",
    params: query
  });
}

//车辆实时水量统计
export function lxpCarWaterAmt (query) {
  return request({
    url: `/lingxingp/statistical/car_water_amt`,
    method: "post",
    data: query
  });
}

//转运合同管理
export function lxContractList (query, page) {
  return request({
    url: `/lingxing/contract/list?${page}`,
    method: "post",
    data: query
  });
}

//排放口水质统计
export function lxpWaterQualityAmt (query) {
  return request({
    url: `/lingxingp/statistical/water_quality_amt`,
    method: "get",
    params: query
  });
}
