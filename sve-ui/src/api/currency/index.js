//企业异常情况汇总
import request from "@/utils/request";

export function abnormalEnterprise(task, query) {
  return request({
    url: `/${task}/statistical/enterprise`,
    method: "post",
    data: query
  });
}

export function exportEnterprise(task, query) {
  return request({
    url: `/${task}/statistical/export_enterprise`,
    method: "post",
    data: query
  });
}

//镇街异常情况汇总
export function abnormalTown(task, query) {
  return request({
    url: `/${task}/statistical/town`,
    method: "post",
    data: query
  });
}

export function exportTown(task, query) {
  return request({
    url: `/${task}/statistical/export_town`,
    method: "post",
    data: query
  });
}


//当月异常统计
export function thisMonthWarn(task, query) {
  return request({
    url: `/${task}/statistical/calculate`,
    method: 'get',
    params: query
  })
}

//设备品牌统计
export function equipBrandList(task) {
  return request({
    url: `/${task}/statistical/equipment`,
    method: 'get',
  })
}

//设备品牌统计
export function equipConstructionList(task) {
  return request({
    url: `/${task}/statistical/construction`,
    method: 'get',
  })
}

//企业异常情况统计
export function abSourceList(task, query, page) {
  return request({
    url: `/${task}/statistical/source?${page}`,
    method: 'post',
    data: query
  })
}

//异常处理情况统计
export function abSourceTownCount(taskid) {
  return request({
    url: `/enterprise/statistical/town_count/${taskid}`,
    method: 'get'
  })
}

//各镇街污水排放量
export function emissionTownWaste(task,query) {
  return request({
    url: `/${task}/statistical/town_waste`,
    method: 'get',
    params: query
  })
}

//全市各月污水排放量
export function emissionMonthWaste(task,query) {
  return request({
    url: `/${task}/statistical/month_waste`,
    method: 'get',
    params: query
  })
}
//镇街各月污水排放量
export function emissionTownMonth(task,query) {
  return request({
    url: `/${task}/statistical/town_month_waste`,
    method: 'get',
    params: query
  })
}

//各镇街工业用电量
export function stTownelEctric(task,query) {
  return request({
    url: `/${task}/statistical/town_electric`,
    method: 'get',
    params: query
  })
}

//全市各月工业用电量
export function stMonthElectric(task,query) {
  return request({
    url: `/${task}/statistical/month_electric`,
    method: 'get',
    params: query
  })
}
//镇街各月工业用电量
export function stTownMonthElectric(task,query) {
  return request({
    url: `/${task}/statistical/town_month_electric`,
    method: 'get',
    params: query
  })
}

//各镇街工业用水量
export function stTownelWater(task,query) {
  return request({
    url: `/${task}/statistical/town_water`,
    method: 'get',
    params: query
  })
}

//全市各月工业用水量
export function stMonthWater(task,query) {
  return request({
    url: `/${task}/statistical/month_water`,
    method: 'get',
    params: query
  })
}
//镇街各月工业用水量
export function stTownMonthWater(task,query) {
  return request({
    url: `/${task}/statistical/town_month_water`,
    method: 'get',
    params: query
  })
}

//联网情况汇总
export function stTownNetwork(task,query) {
  return request({
    url: `/${task}/statistical/town_network`,
    method: 'get',
    params: query
  })
}

//企业联网统计
export function stEnterpriseNetwork(task,query,page) {
  return request({
    url: `/${task}/statistical/enterprise_network?${page}`,
    method: 'post',
    data: query
  })
}
//企业联网统计导出
export function stExportNetwork(task,query) {
  return request({
    url: `/${task}/statistical/export_enterprise_network`,
    method: 'post',
    data: query
  })
}

export function stTownChcekOnOff(task) {
  return request({
    url: `/${task}/statistical/town_checkpoint`,
    method: 'get'
  })
}
