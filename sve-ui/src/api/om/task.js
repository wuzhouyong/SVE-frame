import request from "@/utils/request";
//省督办任务

//任务发布

//新增任务
export function dgomSuperviseAdd (query) {
  return request({
    url: `/dgom/supervise/add`,
    method: "post",
    data: query
  });
}

//状态为草稿的时候可修改
export function dgomSuperviseEdit (query) {
  return request({
    url: `/dgom/supervise/edit`,
    method: "post",
    data: query
  });
}

//删除
export function dgomSuperviseDel (ids) {
  return request({
    url: `/dgom/supervise/delete/${ids}`,
    method: "delete"
  });
}

//导入
export function dgomSuperviseImport (query) {
  return request({
    url: `/dgom/supervise/import_task`,
    method: "post",
    data: query
  });
}

//确认表格
export function dgomBatchAdd (query) {
  return request({
    url: `/dgom/supervise/batch_add`,
    method: "post",
    data: query
  });
}

//列表
export function dgomSuperviseList (query, page) {
  return request({
    url: `/dgom/supervise/task_list?${page}`,
    method: "post",
    data: query
  });
}

//分局处理列表(带镇街权限)
export function dgomDivideList (query, page) {
  return request({
    url: `/dgom/supervise/divide_list?${page}`,
    method: "post",
    data: query
  });
}

//分局处理,支持批量
export function dgomDivideHandle (query) {
  return request({
    url: `/dgom/supervise/divide_edit`,
    method: "post",
    data: query
  });
}

//数据处理,支持批量
export function dgomCityHandle (query) {
  return request({
    url: `/dgom/supervise/municipal_edit`,
    method: "post",
    data: query
  });
}

//导出表格
export function dgomexportSupervise (query) {
  return request({
    url: `/com/export/supervise`,
    method: "post",
    data: query
  });
}

//任务处理列表
export function omAbnorTaskList (data, page) {
  return request({
    url: `/dgom/abnor_task/list?${page}`,
    method: "post",
    data: data
  });
}

//任务处理列表导出
export function omAbnorTaskExport (data) {
  return request({
    url: `/dgom/abnor_task/export_excel`,
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

//分局同意
export function omAbnorTaskBranchProc (taskHeadIds, data) {
  return request({
    url: `/dgom/abnor_task/branch_proc/${taskHeadIds}`,
    method: "post",
    data: data
  });
}

//分局退回
export function omAbnorTaskBranchReturn (taskHeadIds, data) {
  return request({
    url: `/dgom/abnor_task/branch_return/${taskHeadIds}`,
    method: "post",
    data: data
  });
}

//市局退回

export function omAbnorTaskCityReturn (taskHeadIds, data) {
  return request({
    url: `/dgom/abnor_task/city_return/${taskHeadIds}`,
    method: "post",
    data: data
  });
}

//历史
export function omAbnorTaskProcDetails (taskHeadIds) {
  return request({
    url: `/dgom/abnor_task/proc_details/${taskHeadIds}`,
    method: "get"
  });
}
