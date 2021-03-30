
export function warnHandleList (prefix, query, page) {
  return request({
    url: `/${prefix}/warnmsg/enterprise_list?${page}`,
    method: "post",
    data: query
  });
}

export function warnLists (prefix, query, page) {
  return request({
    url: `/${prefix}/warnmsg/list?${page}`,
    method: "post",
    data: query
  });
}

export function factorylist (prefix, query, page) {
  return request({
    url: `/${prefix}/warn/factorylist?${page}`,
    method: "post",
    data: query
  });
}

export function process (prefix, query) {
  return request({
    url: `/${prefix}/warnmsg/enterprise_proc`,
    method: "post",
    data: query
  });
}

//异常详情
export function warnProclist (prefix, id) {
  return request({
    url: `/${prefix}/warnmsg/process/list/${id}`,
    method: "get"
  });
}


import request from "@/utils/request";

export function warnList (query, page) {
  return request({
    url: `/warn/list?${page}`,
    method: "post",
    data: query
  });
}

export function warnDetails (prefix, warnId) {
  return request({
    url: `/warn/detail/${prefix}/${warnId}`,
    method: "get",
  });
}

export function warnHistory (prefix, warnId) {
  return request({
    url: `/warn/history/${prefix}/${warnId}`,
    method: "get",
  });
}

export function warnProc (prefix, query) {
  return request({
    url: `/warn/proc/${prefix}`,
    method: "post",
    data: query
  });
}
