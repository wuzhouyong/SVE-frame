import request from "@/utils/request";

//企业列表
export function dgomEnterprise () {
  return request({
    url: `/dgom/data_search/enterprise`,
    method: "get"
  });
}

//排放口
export function dgomCheckPoint (id) {
  return request({
    url: `/dgom/data_search/checkpoint/${id}`,
    method: "get"
  });
}

//因子
export function dgomElement () {
  return request({
    url: `/dgom/data_search/element`,
    method: "get"
  });
}

//镇街
export function dgomTown () {
  return request({
    url: `/dgom/data_search/townname`,
    method: "get"
  });
}

//任务列表
export function dgomTaskList (query, page) {
  return request({
    url: `/dgom/data_search/tasklist?${page}`,
    method: "post",
    data: query
  });
}

//任务列表详情
export function dgomTaskInfo (taskheadid) {
  return request({
    url: `/dgom/data_search/taskInfo/${taskheadid}`,
    method: "get"
  });
}

//实时数据表头
export function dgomMonitorHearderReal (query) {
  return request({
    url: `/dgom/data_search/header_real`,
    method: "post",
    data: query
  });
}

//实时数据
export function dgomMonitorReal (query) {
  return request({
    url: `/dgom/data_search/real`,
    method: "post",
    data: query
  });
}

//分钟,小时数据表头
export function dgomMonitorHearderHM (query) {
  return request({
    url: `/dgom/data_search/header_hour`,
    method: "post",
    data: query
  });
}

//日数据表头
export function dgomMonitorHearderDay (query) {
  return request({
    url: `/dgom/data_search/header_day`,
    method: "post",
    data: query
  });
}

//分钟,小时,日数据(minute,hour,day)
export function dgomMonitorData (query, type) {
  return request({
    url: `/dgom/data_search/${type}`,
    method: "post",
    data: query
  });
}

//废水排放查询
export function dgomSuperviseSearch (query) {
  return request({
    url: `/dgom/supervise/disposal`,
    method: "post",
    data: query
  });
}

//超标数据
export function dgomZerovalueH (query, page) {
  return request({
    url: `/dgom/data_search/zerovalue_hour?${page}`,
    method: "post",
    data: query
  });
}

//缺失数据
export function dgomMissingH (query, page) {
  return request({
    url: `/dgom/data_search/missing_hour?${page}`,
    method: "post",
    data: query
  });
}

//排放口列表
export function dgomCheckpoint (data, page) {
  return request({
    url: `/dgom/checkpoint/list?${page}`,
    method: "post",
    data: data
  });
}

//设备列表
export function dgomDevice (data, page) {
  return request({
    url: `/dgom/device/list?${page}`,
    method: "post",
    data: data
  });
}

//因子列表
export function dgomElementList (data, page) {
  return request({
    url: `/dgom/element/list?${page}`,
    method: "post",
    data: data
  });
}

export function dgomTowns () {
  return request({
    url: `/dgom/util/towns`,
    method: "get"
  });
}

export function dgomIndustrys () {
  return request({
    url: `/dgom/util/industrys`,
    method: "get"
  });
}

//详情
export function dgomModel (id) {
  return request({
    url: `/dgom/networking/model/${id}`,
    method: "get"
  });
}

//附件列表
export function dgomannexList (data) {
  return request({
    url: `/dgom/networking/annex/list`,
    method: "post",
    data: data
  });
}

export function dgomUpload (data) {
  return request({
    url: `/dgom/networking/upload`,
    method: "post",
    data: data
  });
}

//异常报告列表
export function dgomReportList (query, page) {
  return request({
    url: `/enterprise/adjust/report_list?${page}`,
    method: "post",
    data: query
  });
}

export function dgomReportAudit (query) {
  return request({
    url: `/enterprise/adjust/report_audit`,
    method: "post",
    data: query
  });
}

//信息备案
export function dgomFacility (query, page) {
  return request({
    url: `/dgom/facility/list?${page}`,
    method: "post",
    data: query
  });
}

//气重点设备列表
export function dgomOpgDevice (id) {
  return request({
    url: `dgom/facility/opgdevice_list/${id}`,
    method: "get"
  });
}

//水重点设备列表
export function dgomOpwdevice (id) {
  return request({
    url: `/dgom/facility/opwdevice_list/${id}`,
    method: "get"
  });
}

//运维管理
export function dgomFacilityRecord (query, page) {
  return request({
    url: `/dgom/facility/record_list?${page}`,
    method: "post",
    data: query
  });
}

//设备状态变更列表
export function dgomDeviceReplaceList (query, page) {
  return request({
    url: `/dgom/device_replace/list?${page}`,
    method: "post",
    data: query
  });
}

//设备状态变更详情
export function dgomDeviceDetail (id) {
  return request({
    url: `/dgom/device_replace/detail_list/${id}`,
    method: "get",
  });
}

//在线监控通用附件
export function dgomUtilAnnex (query) {
  return request({
    url: `/dgom/util/annex`,
    method: "post",
    data: query
  });
}

//受理
export function dgomDeviceAccept (query) {
  return request({
    url: `/dgom/device_replace/accept`,
    method: "post",
    data: query
  });
}

//审批
export function dgomDeviceApproval (query) {
  return request({
    url: `/dgom/device_replace/approval`,
    method: "post",
    data: query
  });
}

//审批
export function dgomDeviceDuty (query) {
  return request({
    url: `/dgom/device_replace/duty`,
    method: "post",
    data: query
  });
}

//联网申请列表
export function dgomNetworkingList (query, page) {
  return request({
    url: `/dgom/networking/list?${page}`,
    method: "post",
    data: query
  });
}

//值守日记
export function dgomDutyDiary (query) {
  return request({
    url: `/dgom/duty/duty_diary`,
    method: "get",
    params: query
  });
}

//导出
export function dgomExportDiary (query) {
  return request({
    url: `/dgom/duty/export_duty_diary`,
    method: "get",
    params: query
  });
}

//异常处理结果
export function dgomDutyAbnorProcessResult (query) {
  return request({
    url: `/dgom/duty/abnor_process_result`,
    method: "get",
    params: query
  });
}

//导出
export function dgomDutyAbnorProcessResultExport (query) {
  return request({
    url: `/dgom/duty/abnor_process_result_export`,
    method: "get",
    params: query
  });
}

export function dgomDutyAbnorSummary (query) {
  return request({
    url: `/dgom/duty/abnor_summary`,
    method: "get",
    params: query
  });
}

export function dgomDutyAbnorSummaryExport (query) {
  return request({
    url: `/dgom/duty/abnor_summary_export`,
    method: "get",
    params: query
  });
}

//异常企业统计
export function dgomDutyAbnorEnter (query) {
  return request({
    url: `/dgom/duty/abnor_enterprise_summary`,
    method: "get",
    params: query
  });
}

//导出
export function dgomDutyAbnorEnterExport (query) {
  return request({
    url: `/dgom/duty/abnor_enterprise_summary_export`,
    method: "get",
    params: query
  });
}

// 日均值超标统计
export function dgomDutyDailyAvq (query) {
  return request({
    url: `/dgom/duty/daily_avg_statistic`,
    method: "get",
    params: query
  });
}

//导出
export function dgomDutyDailyAvqExport (query) {
  return request({
    url: `/dgom/duty/daily_avg_statistic_export`,
    method: "get",
    params: query
  });
}

//传输率与超标统计
export function dgomDutysubWeekly (query) {
  return request({
    url: `/dgom/duty/substation_weekly_report`,
    method: "get",
    params: query
  });
}

//导出
export function dgomDutysubWeeklyExport (query) {
  return request({
    url: `/dgom/duty/substation_weekly_report_export`,
    method: "get",
    params: query
  });
}

//超标任务统计
export function dgomDutyEntWeekly (query) {
  return request({
    url: `/dgom/duty/enterprise_weekly_report`,
    method: "get",
    params: query
  });
}

//导出
export function dgomDutyEntWeeklyExport (query) {
  return request({
    url: `/dgom/duty/enterprise_weekly_report_export`,
    method: "get",
    params: query
  });
}

//完整了低于90%
export function dgomDutytransRate (query) {
  return request({
    url: `/dgom/duty/trans_rate_less_90`,
    method: "get",
    params: query
  });
}

//导出
export function dgomDutytransRateExport (query) {
  return request({
    url: `/dgom/duty/trans_rate_less_90_export`,
    method: "get",
    params: query
  });
}

//联网申请分局审核
export function applySubstation (query) {
  return request({
    url: `/dgom/networking/substation_approval`,
    method: "post",
    data: query
  });
}

//联网申请市局审核
export function applyMunicipal (query) {
  return request({
    url: `/dgom/networking/municipal_approval`,
    method: "post",
    data: query
  });
}

//联网申请值守确认
export function applyMunicipalDuty (query) {
  return request({
    url: `/dgom/networking/municipal_duty`,
    method: "post",
    data: query
  });
}

//监督检查列表
export function dgomCheckReport (query, page) {
  return request({
    url: `/dgom/check_report/list?${page}`,
    method: "post",
    data: query
  });
}

//监督检查录入
export function dgomCheckEdit (query) {
  return request({
    url: `/dgom/check_report/edit`,
    method: "post",
    data: query
  });
}

//验收备案列表
export function dgomEntAcceptList (data, page) {
  return request({
    url: `/dgom/ent_accept/list?${page}`,
    method: "post",
    data: data
  });
}
//审批
export function dgomEntAcceptApproval (data) {
  return request({
    url: `/dgom/ent_accept/approval`,
    method: "post",
    data: data
  });
}
