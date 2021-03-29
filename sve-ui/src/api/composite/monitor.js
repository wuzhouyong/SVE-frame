import request from "@/utils/request";

//企业列表
export function monitorTask (query, page) {
  return request({
    url: `/enterprise/monitor_task/source?${page}`,
    method: "post",
    data: query
  });
}

//待审核列表
export function monitorExamineList (query, page) {
  return request({
    url: `/construction/list?${page}`,
    method: "post",
    data: query
  });
}

//待审核列表
export function monitorTaskReview (query, page) {
  return request({
    url: `/enterprise/monitor_task/task_changes?${page}`,
    method: "post",
    data: query
  });
}

//合同审核
export function monitorAudit (query) {
  return request({
    url: `/enterprise/monitor_task/audit`,
    method: "get",
    params: query
  });
}

//验收审核
export function monitorAccept (query) {
  return request({
    url: `/construction/confirm`,
    method: "post",
    data: query
  });
}

//验收历史记录
export function monitorRejectList (tagHeadId, sourceId) {
  return request({
    url: `/construction/accept_record/${tagHeadId}/${sourceId}`,
    method: "get",
  });
}

//任务调整申请
export function adjustAdd (query) {
  return request({
    url: `/enterprise/adjust/add`,
    method: "post",
    data: query
  });
}

//任务调整申请审核
export function adjustAgree (query) {
  return request({
    url: `/enterprise/adjust/agree`,
    method: "post",
    data: query
  });
}

//任务调整申请数据
export function adjustModel (query) {
  return request({
    url: `/enterprise/adjust/model`,
    method: "get",
    params: query
  });
}

//审核明细
export function adjustList (query) {
  return request({
    url: `/enterprise/adjust/list`,
    method: "get",
    params: query
  });
}

//零星废水审核
export function LXadjustAgree (query) {
  return request({
    url: `/enterprise/adjust/audit`,
    method: "post",
    data: query
  });
}

//零星废水审核明细
export function LXadjustResult (id) {
  return request({
    url: `/enterprise/adjust/result/${id}`,
    method: "get"
  });
}

//零星废水审核历史
export function LXadjustDetail (pfid, tgid) {
  return request({
    url: `/enterprise/adjust/detail?pfSourceId=${pfid}&tagHeadId=${tgid}`,
    method: "get"
  });
}

//退回验收
export function monitorReject (query) {
  return request({
    url: `/construction/retreat`,
    method: "post",
    data: query
  });
}

//导出表格
export function exportMonitor (query) {
  return request({
    url: `/com/export/monitor_task`,
    method: "post",
    data: query
  });
};

