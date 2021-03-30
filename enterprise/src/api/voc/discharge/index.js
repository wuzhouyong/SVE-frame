import request from "@/utils/request";
const getBlowerList = () => {
  return request({
    url: `/voc/facility/emission_blower/list`,
    method: "get"
  });
};
const getProduction = () => {
  return request({
    url: `/voc/facility/production/list`,
    method: "get"
  });
};
const addProduction = param => {
  return request({
    url: `/voc/facility/production/add`,
    method: "post",
    data: param
  });
};
const getcollectId = (query, page) => {
  return request({
    url: `/voc/checkpoint/list?${page}`,
    method: "post",
    data: query
  });
};

const queryEmissionPoint = () => {
  return request({
    url: `/voc/facility/emission_point/list`,
    method: "get"
  });
};

const queryCollection = () => {
  return request({
    url: "/voc/facility/collect_blower/list",
    method: "get"
  });
};

const addCollection = param => {
  return request({
    url: "/voc/facility/collect_blower/add",
    method: "post",
    data: param
  });
};

//删除
const handelDelete = [removeProduction];
const removeProduction = ids => {
  return request({
    url: `/voc/facility/production/delete/${ids}`,
    method: "delete"
  });
};
const removrCollection = ids => {
  return request({
    url: `/voc/facility/collect_blower/delete/${ids}`,
    method: "delete"
  });
};
const removrDisport = ids => {
  return request({
    url: `/voc/facility/emission_point/delete/${ids}`,
    method: "delete"
  });
};
const removrDisCharge = ids => {
  return request({
    url: `/voc/facility/emission_blower/delete/${ids}`,
    method: "delete"
  });
};

const editProduct = param => {
  return request({
    url: `/voc/facility/production/modify`,
    method: "post",
    data: param
  });
};
const editCollect = param => {
  return request({
    url: `/voc/facility/collect_blower/modify`,
    method: "post",
    data: param
  });
};

//排放风机管理
const editEmissionBlower = param => {
  return request({
    url: `/voc/facility/emission_blower/modify`,
    method: "post",
    data: param
  });
};
const addEmissionBlower = function(param) {
  return request({
    url: `/voc/facility/emission_blower/add`,
    method: "post",
    data: param
  });
};

//排放口
const editEmissionPoint = param => {
  return request({
    url: `/voc/facility/emission_point/modify`,
    method: "post",
    data: param
  });
};
const addEmissionPoint = param => {
  return request({
    url: `/voc/facility/emission_point/add`,
    method: "post",
    data: param
  });
};

//治理设施
const addFacility = query => {
  return request({
    url: `/voc/facility/handle/add`,
    method: "post",
    data: query
  });
};

const editFacility = query => {
  return request({
    url: `/voc/facility/handle/modify`,
    method: "post",
    data: query
  });
};

const facilityList = () => {
  return request({
    url: `/voc/facility/handle/list`,
    method: "get"
  });
};
const delFacility = ids => {
  return request({
    url: `/voc/facility/handle/delete/${ids}`,
    method: "delete"
  });
};

// 查询已有因子
const queryAlready = query => {
  return request({
    url: `/voc/cp_element/list`,
    method: "post",
    data: query
  });
};


export default {
  getBlowerList,
  addEmissionBlower,
  getProduction,
  addProduction,
  getcollectId,
  addFacility,
  facilityList,
  queryEmissionPoint,
  addEmissionPoint,
  queryCollection,
  addCollection,
  removeProduction,
  removrCollection,
  removrDisport,
  delFacility,
  editProduct,
  editCollect,
  editEmissionPoint,
  editEmissionBlower,
  removrDisCharge,
  editFacility,
  queryAlready
};
