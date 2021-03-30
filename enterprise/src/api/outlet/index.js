import request from "@/utils/request";

//排放口列表
export function list(prefix, query, page) {
  return request({
    url: `/${prefix}/outlet/list?${page}`,
    method: "post",
    data: query
  });
}

//排放口新增
export function add(prefix, query) {
  return request({
    url: `/${prefix}/outlet/add`,
    method: "post",
    data: query
  });
}

//排放口编辑
export function edit(prefix, query) {
  return request({
    url: `/${prefix}/outlet/edit`,
    method: "post",
    data: query
  });
}

//排放口删除
export function remove(prefix, id) {
  return request({
    url: `/${prefix}/outlet/${id}`,
    method: "delete"
  });
}

//排放口
export function dgomCheckPoint() {
  return request({
    url: `/dgom/data_search/checkpoint/0`,
    method: "get"
  });
}

//youyan排放口
export function yyOutletList() {
  return request({
    url: `/youyan/data_search/facility?sourceId=`,
    method: "get"
  });
}

//加油站加油枪
export function jyOutletList() {
  return request({
    url: `/jiayou/outlet/0`,
    method: "get"
  });
}
