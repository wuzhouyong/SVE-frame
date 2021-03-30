import request from "@/utils/request";

export function progressModel(prefix) {
  return request({
    url: `/${prefix}/construction/model/0`,
    method: "get"
  });
}

export function hasContract(prefix) {
  return request({
    url: `/${prefix}/construction/has_contract`,
    method: "get"
  });
}

export function constructionRegister(prefix, data) {
  return request({
    url: `/${prefix}/construction/add`,
    method: "post",
    data: data
  });
}
//修改施工登记单位信息
export function constructionUpdate(prefix, data) {
  return request({
    url: `/${prefix}/construction/update`,
    method: "post",
    data: data
  });
}
//查看驳回自主验收登记信息列表
export function constructionDismiss(data) {
  return request({
    url: `/enterprise/monitor_task/reject_list`,
    method: "post",
    data: data
  });
}
export function monitorRejectList (tagHeadId,sourceId) {
  return request({
    url: `/construction/accept_record/${tagHeadId}/${sourceId}`,
    method: "get",
  });
}

export function constructionComplete(prefix, data) {
  return request({
    url: `/${prefix}/construction/complete`,
    method: "post",
    data: data
  });
}

export function constructionAccept(prefix, data) {
  return request({
    url: `/${prefix}/construction/accept`,
    method: "post",
    data: data
  });
}
