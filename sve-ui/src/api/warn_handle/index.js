import request from "@/utils/request";

//值守核实列表
// export function warnDutyList (query, platform, page) {
//   return request({
//     url: `/${platform}/warn/dutylist?${page}`,
//     method: "post",
//     data: query
//   });
// }
export function warnDutyList (query, platform, page) {
  return request({
    url: `/${platform}/warnmsg/duty_list?${page}`,
    method: "post",
    data: query
  });
}

//值守核实提交
export function warnDutyHandle (platform, query) {
  return request({
    url: `/${platform}/warnmsg/duty_proc`,
    method: "post",
    data: query
  });
}

//分局处理列表
export function warnSubstationList (query, platform, page) {
  return request({
    url: `/${platform}/warn/substationlist?${page}`,
    method: "post",
    data: query
  });
}

//分局处理提交
export function warnSubstationHandle (platform, query) {
  return request({
    url: `/${platform}/warnmsg/sub_station_proc`,
    method: "post",
    data: query
  });
}

//异常查询列表
// export function warnList(query, platform, page) {
//   return request({
//     url: `/${platform}/warn/list?${page}`,
//     method: "post",
//     data: query
//   });
// }
export function warnList (query, platform, page) {
  return request({
    url: `/${platform}/warnmsg/list?${page}`,
    method: "post",
    data: query
  });
}

//异常详情
// export function warnProclist(id, platform) {
//   return request({
//     url: `/${platform}/warnpro/proclist?id=${id}`,
//     method: "get"
//   });
// }
export function warnProclist (id, platform) {
  return request({
    url: `/${platform}/warnmsg/process/list/${id}`,
    method: "get"
  });
}

//导出
export function exportWarn (platform, query) {
  return request({
    url: `/${platform}/warnmsg/export`,
    method: "post",
    data: query
  });
}
