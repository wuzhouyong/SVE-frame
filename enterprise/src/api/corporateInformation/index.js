import request from "@/utils/request";
export function modify(data) {// 变更状态
  return request({
      url: `/enterprise/modify/add`,
      method: "post",
      data: data
  });
}

export function history() {//  查看变更的历史记录
  return request({
      url: `/enterprise/modify/history`,
      method: "get",
  });
}

export function getVoc() {//  voc
  return request({
      url: `/enterprise/source/vocs`,
      method: "get",
  });
}

export function getmodule1() {// 基本信息
  return request({
      url: `/enterprise/source/module1`,
      method: "get",
  });
}

export function getmodule2() {// 环保信息
  return request({
      url: `/enterprise/source/module2`,
      method: "get",
  });
}

export function changeEnProtection(data) {// 环保信息
  return request({
      url: `/enterprise/modify/source`,
      method: "post",
      data: data
  });
}
export function changeVoc(data) {// 修改voc
  return request({
      url: `/enterprise/modify/vocs`,
      method: "post",
      data: data
  });
}

export function exportVOCsWord() {// 修改voc
  return request({
      url: `/enterprise/modify/word`,
      method: "get",
  });
}

export function getModifyId() {// getModifyId
  return request({
      url: `/enterprise/modify/getid`,
      method: "get",
  });
}
