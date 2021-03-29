import request from "@/utils/request";

//巡检任务导入
export function inspectImport(query) {
  return request({
    url: `/inspect/import_task`,
    method: "post",
    data: query
  });
};

export function inspectBatchAdd(query) {
  return request({
    url: `/inspect/batch_add`,
    method: "post",
    data: query
  });
};
export function inspectTaskList(query,page) {
  return request({
    url: `/inspect/task_list?${page}`,
    method: "post",
    data: query
  });
};

export function inspectEdit(query) {
  return request({
    url: `/inspect/edit`,
    method: "post",
    data: query
  });
};

export function inspectRecordList(id) {
  return request({
    url: `/inspect/record_list/${id}`,
    method: "get",
  });
};

//导出
export function inspectExport(query) {
  return request({
    url: `/inspect/export_task`,
    method: "post",
    data: query
  });
};

export function inspectDelete(id) {
  return request({
    url: `/inspect/delete/${id}`,
    method: "delete",
  });
};

export function inspectAdd(query) {
  return request({
    url: `/inspect/add`,
    method: "post",
    data: query
  });
};
export function inspectTaskEdit(query) {
  return request({
    url: `/inspect/task_edit`,
    method: "post",
    data: query
  });
};
