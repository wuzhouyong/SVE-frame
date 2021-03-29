import request from "@/utils/request"

// 获取标签结构
export function labelByUser() {
  return request({
    url: "/label/by_user",
    method: "get"
  })
}

// 新增
export function labelAdd(query) {
  return request({
    url: "/label/add",
    method: "post",
    data: query
  })
}

// 编辑
export function labelEdit(query) {
  return request({
    url: "/label/edit",
    method: "post",
    data: query
  })
}

export function labelRemove(id) {
  return request({
    url: `/label/remove/${id}`,
    method: "delete",
  })
}

//企业列表
export function labelSourceList(query, page) {
  return request({
    url: `/label/source_list?${page}`,
    method: "post",
    data: query
  })
}

//标签挂企业列表 {type:left|right,labelHeadId}
export function sourceListByLabel(query,page) {
  return request({
    url: `/label/source_list_by_label?${page}`,
    method: "post",
    data: query
  })
}


//关联企业
export function labelRelatioSource(query,id) {
  return request({
    url: `/label/relation_source/${id}`,
    method: "post",
    data: query
  })
}


//移除企业
export function labelUnrelationSource(query,id) {
  return request({
    url: `/label/unrelation_source/${id}`,
    method: "post",
    data: query
  })
}

// 企业贴标签
export function labelRelatioLabel(query,id) {
  return request({
    url: `/label/relation_label/${id}`,
    method: "post",
    data: query
  })
}

//企业移除标签
export function labelUnrelationLabel(query,id) {
  return request({
    url: `/label/unrelation_label/${id}`,
    method: "post",
    data: query
  })
}
