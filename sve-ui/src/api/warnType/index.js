import request from "@/utils/request";

export function warnList (query, page) {
  return request({
    url: `/warn/list?${page}`,
    method: "post",
    data: query
  });
}

export function warnDetails (warnId) {
  return request({
    url: `/warn/detail/${warnId}`,
    method: "get",
  });
}

export function warnHistory (warnId) {
  return request({
    url: `/warn/history/${warnId}`,
    method: "get",
  });
}

export function warnProc (query) {
  return request({
    url: `/warn/proc`,
    method: "post",
    data: query
  });
}
