import request from "@/utils/request";

//数采列表
export function list(prefix, query, page) {
  return request({
    url: `/${prefix}/checkpoint/list?${page}`,
    method: "post",
    data: query
  });
}

//数采列表下拉框
export function Minninglist(prefix,query) {
  return request({
    url: `/${prefix}/checkpoint/dropdown`,
    method: "post",
    data: query  
  });
}

export function checkConstruction(prefix) {
  return request({
    url: `/${prefix}/checkpoint/checkcon`,
    method: "get"
  });
}

//数采新增
export function add(prefix, query) {
  return request({
    url: `/${prefix}/checkpoint/add`,
    method: "post",
    data: query
  });
}

//数采编辑
export function edit(prefix, query) {
  return request({
    url: `/${prefix}/checkpoint/edit`,
    method: "post",
    data: query
  });
}

//数采删除
export function remove(prefix, id) {
  return request({
    url: `/${prefix}/checkpoint/${id}`,
    method: "delete"
  });
}

export function outwaterList() {
  return request({
    url: `/outwater/checkpoint/idNameList`,
    method: "post",
    data: {}
  });
}

export function getMN() {
  return request({
    url: `/outwater/checkpoint/mn`,
    method: "get"
  });
}

//修改密码
export function ChangePassword(platform, query) {
  return request({
    url: `/${platform}/checkpoint/modify_pass`,
    method: "post",
    data: query
  });
}
