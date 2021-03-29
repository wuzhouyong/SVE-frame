import request from "@/utils/request";

//运维单位列表
export function mochaDevopsList (query, page) {
  return request({
    url: `/devops/list?${page}`,
    method: "post",
    data: query
  });
}

export function mochaDevopsAdd (query) {
  return request({
    url: `/devops/add`,
    method: "post",
    data: query
  });
}

export function mochaDevopsEdit (query) {
  return request({
    url: `/devops/edit`,
    method: "post",
    data: query
  });
}

export function mochaDevopsDel (ids) {
  return request({
    url: `/devops/delete/${ids}`,
    method: "delete"
  });
}

//问题列表
export function mochaQuestionDevopsList (query) {
  return request({
    url: `/question/list`,
    method: "post",
    data: query
  });
}

export function mochaQuestionDevopsAdd (query) {
  return request({
    url: `/question/add`,
    method: "post",
    data: query
  });
}

export function mochaQuestionDevopsEdit (query) {
  return request({
    url: `/question/edit`,
    method: "post",
    data: query
  });
}

export function mochaQuestionDevopsDel (ids) {
  return request({
    url: `/question/delete/${ids}`,
    method: "delete"
  });
}

//新增评分邀请记录
export function mochaQuestionaireAdd (query) {
  return request({
    url: `/questionnaire/add`,
    method: "post",
    data: query
  });
}

//评分邀请记录列表
export function mochaQuestionaireList () {
  return request({
    url: `/questionnaire/list`,
    method: "get"
  });
}

//获取评分列表
export function mochaScoreList (id, page) {
  return request({
    url: `/score/list/${id}?${page}`,
    method: "get",
  });
}

//列表
export function operateUnitList (query, page) {
  return request({
    url: `/contract/list?${page}`,
    method: "post",
    data: query
  });
}

//获取运维合同因子
// export function devopsEleList (id) {
//   return request({
//     url: `/contract/eleList?opProjectId=${id}`,
//     method: "get",
//   });
// }

//导出
export function scoreExportList (id) {
  return request({
    url: `/score/export_list/${id}`,
    method: "get",
  });
}

