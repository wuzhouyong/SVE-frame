import request from '@/utils/request'

export function dgomEnterprise() {
  return request({
    url: `/dgom/data_search/enterprise`,
    method: 'get'
  })
}

//排放口列表
export function dgomCheckpoint(data, page) {
  return request({
    url: `/dgom/checkpoint/list?${page}`,
    method: 'post',
    data: data
  })
}

//设备列表
export function dgomDevice(data, page) {
  return request({
    url: `/dgom/device/list?${page}`,
    method: 'post',
    data: data
  })
}

//因子列表
export function dgomElement(data, page) {
  return request({
    url: `/dgom/element/list?${page}`,
    method: 'post',
    data: data
  })
}

export function dgomTowns() {
  return request({
    url: `/dgom/util/towns`,
    method: 'get'
  })
}

export function dgomIndustrys() {
  return request({
    url: `/dgom/util/industrys`,
    method: 'get'
  })
}

//申请列表
export function dgomNetworkList(data) {
  return request({
    url: `/dgom/networking/list`,
    method: 'post',
    data: data
  })
}

//提交申请
export function dgomRegister(data) {
  return request({
    url: `/dgom/networking/register`,
    method: 'post',
    data: data
  })
}

//导出申请表
export function dgomExport(id) {
  return request({
    url: `/dgom/networking/export/${id}`,
    method: 'get'
  })
}

//详情
export function dgomModel(id) {
  return request({
    url: `/dgom/networking/model/${id}`,
    method: 'get'
  })
}

export function dgomDelete(id) {
  return request({
    url: `/dgom/networking/delete/${id}`,
    method: 'delete'
  })
}

export function dgomUpload(data) {
  return request({
    url: `/dgom/networking/upload`,
    method: 'post',
    data: data
  })
}
export function dgomDel(data) {
  return request({
    url: `/dgom/util/delete/${data}`,
    method: 'delete',

  })
}
export function dgomUploadList(data) {
  return request({
    url: `/dgom/util/annex`,
    method: 'post',
    data: data
  })
}

export function dgomannexList(data) {
  return request({
    url: `/dgom/networking/annex/list`,
    method: 'post',
    data: data
  })
}

// 获取报告id
export function getReportId() {
  return request({
    url: `/enterprise/adjust/report_add`,
    method: 'get'
    // data: data
  })
}

// 获取报告id
export function getReportList(data, page) {
  return request({
    url: `/enterprise/adjust/report_list?${page}`,
    method: 'post',
    data: data
  })
}

// 运维管理列表
export function getOperationList(data, page) {
  return request({
    url: `/dgom/facility/list?${page}`,
    method: 'post',
    data: data
  })
}

// 新增监测点下拉框
export function getMonitorDropList(type) {
  return request({
    url: `/dgom/facility/checkpoint_list/${type}`,
    method: 'get'

  })
}

export function dgomDeviceList(cpId) {
  return request({
    url: `/dgom/facility/device_list/${cpId}`,
    method: 'get'

  })
}

// 获取气设施详情
export function getGasImportantDetail(checkpointid) {
  return request({
    url: `/dgom/facility/opgdevice_list/${checkpointid}`,
    method: 'get'

  })
}

// 获取水设施详情
export function getWaterImportantDetail(checkpointid) {
  return request({
    url: `/dgom/facility/opwdevice_list/${checkpointid}`,
    method: 'get'

  })
}

// 新增水设施
export function addWaterEquipment(data) {
  return request({
    url: `/dgom/facility/opwdevice_add`,
    method: 'post',
    data
  })
}

// 水列表
export function getWaterImportantList(checkpointid) {
  return request({
    url: `/dgom/facility/opgdevice_list/${checkpointid}`,
    method: 'get',

  })
}
// 气列表
export function getGasImportantList(checkpointid) {
  return request({
    url: `/dgom/facility/opgdevice_list/${checkpointid}`,
    method: 'get',

  })
}


export function editWaterEquipment(data) {
  return request({
    url: `/dgom/facility/opwdevice_edit`,
    method: 'post',
    data
  })
}

// 新增气设施
export function addGasEquipment(data) {
  return request({
    url: `/dgom/facility/opgdevice_add `,
    method: 'post',
    data
  })
}

// 编辑气设施
export function editGasEquipment(data) {
  return request({
    url: `/dgom/facility/opgdevice_edit`,
    method: 'post',
    data
  })
}

// 管理页面
export function getAdminList(data, page) {
  return request({
    url: `/dgom/facility/record_list?${page}`,
    method: 'post',
    data
  })
}

// 新增运维管理
export function addOperation(data) {
  return request({
    url: `/dgom/facility/record_add`,
    method: 'post',
    data
  })
}

// 运维管理获取设备
export function getEuqiumentList(data) {
  return request({
    url: `/dgom/facility/devdef_list/${data}`,
    method: 'get'

  })
}

// 编辑运维管理
export function editOperation(data) {
  return request({
    url: `/dgom/facility/record_edit`,
    method: 'post',
    data
  })
}

// 删除运维管理
export function delOperation(data) {
  return request({
    url: `/dgom/facility/record_delete/${data}`,
    method: 'delete'

  })
}


// 设备获取镇街
export function getTownList() {
  return request({
    url: `/dgom/util/towns`,
    method: 'get'

  })
}


// 设备维修，更改,停用列表
export function getEquipmentComlist(data,page) {
  return request({
    url: `/dgom/device_replace/list?${page}`,
    method: 'post',
    data
  })
}
//根据企业获取排放口列表
export function getEmissions() {
  return request({
    url: `/dgom/util/checkpoint`,
    method: 'get',

  })
}

//根据企业获取排放口列表
export function getEquipmentByEmissions(checkpointid) {
  return request({
    url: `/dgom/util/datadev/${checkpointid}`,
    method: 'get',

  })
}

//新增设备
export function device_replaceAdd(data) {
  return request({
    url: `/dgom/device_replace/add`,
    method: 'post',
    data
  })
}

// 当前申请下的排放口设备列表
export function device_detail_list(applyid) {
  return request({
    url: `/dgom/device_replace/detail_list/${applyid}`,
    method: 'get',

  })
}


// 设备删除
export function device_del_list(applyid) {
  return request({
    url: `/dgom/device_replace/delete/${applyid}`,
    method: 'delete',

  })
}
