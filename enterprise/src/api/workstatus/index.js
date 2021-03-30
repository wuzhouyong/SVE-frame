import request from '@/utils/request'

export function workStatusList(query) {
  return request({
    url: `/work_status/list`,
    method: 'post',
    data: query
  })
}

export function workStatusAdd(query) {
  return request({
    url: `/work_status/add`,
    method: 'post',
    data: query
  })
}

export function workStatusEdit(query) {
  return request({
    url: `/work_status/edit`,
    method: 'post',
    data: query
  })
}

export function workStatusDel(id) {
  return request({
    url: `/work_status/remove/${id}`,
    method: 'delete'
  })
}

//申请变更时间
export function workStatusApplyChangeTime(query) {
  return request({
    url: `/work_status/apply_change_time`,
    method: 'post',
    data: query
  })
}
