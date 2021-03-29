import request from "@/utils/request";
//数据查询
export function searchData(query, platform, type) {
  return request({
    url: `/${platform}/data_search/${type}`,
    method: "post",
    data: query
  });
}

//企业列表
export function sourceList(query, page, platform) {
  return request({
    url: `/${platform}/source/list?${page}`,
    method: "post",
    data: query
  });
}

//检测井下拉
export function pointSelectList(query) {
  return request({
    url: `/outwater/checkpoint/idNameList`,
    method: "post",
    data: query
  });
}

//导出
export function searchDataExport(query, platform, type) {
  return request({
    url: `/${platform}/data_search/export_${type}`,
    method: "post",
    data: query
  });
}
