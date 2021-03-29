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
