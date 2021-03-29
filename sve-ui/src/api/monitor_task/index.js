import request from "@/utils/request";

export function sourceSummary(query) {
  return request({
    url: "/enterprise/monitor_task/summary",
    method: "get",
    params: query
  });
}

export function sourceTask(query, page) {
  return request({
    url: `/enterprise/monitor_task/task?${page}`,
    method: "post",
    data: query
  });
}

export const exportSummary = query => {
  return request({
    url: `/com/export/summary`,
    method: "get",
    params: query
  });
};

export const exportTable = query => {
  return request({
    url: `/com/export/task`,
    method: "get",
    params: query
  });
};
