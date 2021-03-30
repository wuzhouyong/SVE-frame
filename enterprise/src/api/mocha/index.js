import request from "@/utils/request";

//开启的问卷列表
export function mochaQuestionAireTheme () {
  return request({
    url: `/questionnaire/theme_list`,
    method: "get"
  });
}

//问题
export function mochaQuestionnaireList (id) {
  return request({
    url: `/questionnaire/list/${id}`,
    method: "get"
  });
}

//评分
export function mochaScoreAdd (query) {
  return request({
    url: `/devops/score/add`,
    method: "post",
    data: query
  });
}

