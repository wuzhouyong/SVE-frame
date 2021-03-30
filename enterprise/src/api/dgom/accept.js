import request from "@/utils/request";

//验收备案列表
export function dgomEntAcceptList (data) {
  return request({
    url: `/dgom/ent_accept/list`,
    method: "post",
    data: data
  });
}

// 添加主记录
export function dgomEntAcceptAddHead (data) {
  return request({
    url: `/dgom/ent_accept/add/head`,
    method: "post",
    data: data
  });
}

export function dgomEntAcceptEditHead (data) {
  return request({
    url: `/dgom/ent_accept/edit/head`,
    method: "post",
    data: data
  });
}

export function dgomDevicesList (applyId) {
  return request({
    url: `/dgom/ent_accept/devices/${applyId}`,
    method: "get"
  });
}

export function dgomEntAcceptAddDevice (data) {
  return request({
    url: `/dgom/ent_accept/add/device`,
    method: "post",
    data: data
  });
}

export function dgomEntAcceptEditDevice (data) {
  return request({
    url: `/dgom/ent_accept/edit/device`,
    method: "post",
    data: data
  });
}

export function dgomEntAcceptDeleteHead(id) {
  return request({
    url: `/dgom/ent_accept/delete/head/${id}`,
    method: "delete"
  });
}

export function dgomEntAcceptDeleteDevice (id) {
  return request({
    url: `/dgom/ent_accept/delete/device/${id}`,
    method: "delete"
  });
}
export function dgomAcceptSubmit(applyId) {
  return request({
    url: `/dgom/ent_accept/submit/${applyId}`,
    method: "put"
  });
}

