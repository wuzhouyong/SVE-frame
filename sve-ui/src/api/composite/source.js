import request from "@/utils/request";

// 获取污染源标签
export const getSource = sourceId => {
  return request({
    url: "/enterprise/source/" + sourceId,
    method: "get"
  });
};

//企业列表
export const getSourceList = parm => {
  return request({
    url: `/enterprise/source/list?${parm}`,
    method: "get"
  });
};

export function getSourceListPost (query, page) {
  return request({
    url: `/enterprise/source/list?${page}`,
    method: "post",
    data: query
  });
};

export function getSourceByTag (query, page) {
  return request({
    url: `/enterprise/source/by_tag?${page}`,
    method: "post",
    data: query
  });
};

//企业编辑
export const sourceEdit = source => {
  return request({
    url: "/enterprise/source/edit",
    method: "put",
    data: source
  });
};

//删除
export function sourceDel (id) {
  return request({
    url: "/enterprise/source/" + id,
    method: "delete"
  });
}

//企业新增
export const sourceAdd = query => {
  return request({
    url: "/enterprise/source/add",
    method: "post",
    data: query
  });
};

export const getDict = code => {
  return request({
    url: "/system/dict/data/dictType/" + code,
    method: "get"
  });
};

//权限镇街
export const getRegion = code => {
  return request({
    url: "/com/region/" + code,
    method: "get"
  });
};

export const GetRegionPower = () => {
  return request({
    url: "/com/dgregion",
    method: "get"
  });
};

export const getIndustry = () => {
  return request({
    url: "/com/industryTree/",
    method: "get"
  });
};

//平台列表
export function getSourcetagList (query) {
  return request({
    url: `/enterprise/source/tagList`,
    method: "get",
    params: query
  });
}

//权限任务列表
export function getTagList (query) {
  return request({
    url: `/enterprise/source/tagNameList`,
    method: "get",
    params: query
  });
}

//平台列表关联
export function tagDetailList (sourceId) {
  return request({
    url: `/enterprise/source/detailList?sourceId=${sourceId}`,
    method: "get"
  });
}

export const getSourceTags = () => {
  return request({
    url: "/enterprise/source/detailList",
    method: "get"
  });
};

//企业关联平台
export const sourcecorrelation = query => {
  return request({
    url: "/enterprise/source/correlation",
    method: "post",
    data: query
  });
};

//企业关联平台
export const sourcecorrelationfj = query => {
  return request({
    url: "/enterprise/source/correlationfj",
    method: "post",
    data: query
  });
};

//企业关联平台
export const sourcetDList = query => {
  return request({
    url: "/enterprise/source/tDList",
    method: "post",
    data: query
  });
};

//新增企业账号
export const sourceUserAdd = query => {
  return request({
    url: "/enterprise/user/add",
    method: "post",
    data: query
  });
};

//重置企业密码
export const sourceUserReset = query => {
  return request({
    url: "/enterprise/user/reset",
    method: "put",
    data: query
  });
};

//涉水
export function sourceWade () {
  return request({
    url: `/enterprise/source/wade`,
    method: "get"
  });
}

//涉气
export function sourceGas () {
  return request({
    url: `/enterprise/source/gas`,
    method: "get"
  });
}

//企业资料变更审核列表
export function dataModify (query) {
  return request({
    url: "/enterprise/modify/list",
    method: "post",
    data: query
  });
}

//同意或拒绝资料变更
export const modifyAgree = query => {
  return request({
    url: "/enterprise/modify/agree",
    method: "post",
    data: query
  });
};

//修改平台名字
export function updateTagName (tagId, query) {
  return request({
    url: `/enterprise/source/update_tag_name/${tagId}`,
    method: "get",
    params: query
  });
}
