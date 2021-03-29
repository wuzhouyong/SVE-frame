import request from "@/utils/request";

//企业列表
export const getTask = query => {
  return request({
    url: `/enterprise/monitor_task/count`,
    method: "get",
    params: query
  });
};

//导出
export const exportTable = query => {
  return request({
    url: `/com/export/count`,
    method: "get",
    params: query
  });
};