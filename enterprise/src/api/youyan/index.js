import request from "@/utils/request";

//油烟数采下拉
export function yySelect() {
  return request({
    url: `/youyan/checkpoint/list?pageNum=1&pageSize=30`,
    method: "post",
    data: {}
  });
}

//列表
export function yyFacilityList(page) {
  return request({
    url: `/youyan/facility/list?${page}`,
    method: "post",
    data: {}
  });
}

//新增
export function yyFacilityAdd(query) {
  return request({
    url: `/youyan/facility/add`,
    method: "post",
    data: query
  });
}

//修改
export function yyFacilityEdit(query) {
  return request({
    url: `/youyan/facility/edit`,
    method: "post",
    data: query
  });
}

//删除
export function yyFacilityDel(facilityId) {
  return request({
    url: `/youyan/facility/delete/${facilityId}`,
    method: "delete"
  });
}

//获取可关联数据
export function yySamelevel(facilityId) {
  return request({
    url: `/youyan/facility/samelevel/${facilityId}`,
    method: "get"
  });
}

//关联
export function yyFacilityRelated(query) {
  return request({
    url: `/youyan/facility/related`,
    method: "get",
    params: query
  });
}
