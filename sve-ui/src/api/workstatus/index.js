import request from "@/utils/request";

//申报列表
export function workStatusList(query,page) {
  return request({
    url: `/work_status/list?${page}`,
    method: 'post',
    data: query
  })
}

//变更时间
export function workStatusChangeList(query,page) {
  return request({
    url: `/work_status/change_list?${page}`,
    method: 'post',
    data: query
  })
}




//审核
export function workStatusAudit(query) {
  return request({
    url: `/work_status/audit`,
    method: 'post',
    data: query
  })
}

//审核
export function workStatusAuditChangeTime(query) {
  return request({
    url: `/work_status/audit_change_time`,
    method: 'post',
    data: query
  })
}
