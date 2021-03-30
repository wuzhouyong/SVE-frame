import request from "@/utils/request";


export function omAbnorTaskList (data, page) {
  return request({
    url: `/dgom/abnor_task/list?${page}`,
    method: "post",
    data: data
  });
}

export function omAbnorTaskTable (taskIds) {
  return request({
    url: `/dgom/abnor_task/elements/${taskIds}`,
    method: "get",
  });
}

//企业处理
export function omAbnoEnProc (query,ids) {
  return request({
    url: `/dgom/abnor_task/enterprise_proc/${ids}`,
    method: "post",
    data: query

  });
}

//历史
export function omAbnorTaskProcDetails (taskHeadIds) {
  return request({
    url: `/dgom/abnor_task/proc_details/${taskHeadIds}`,
    method: "get"
  });
}
