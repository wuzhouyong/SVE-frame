package com.ruoyi.project.outwater.service;

import com.ruoyi.common.domain.DataSearchParam;
import com.ruoyi.common.domain.DataSearchResultT;
import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.common.domain.TransferRateEntity;
import com.ruoyi.project.outwater.domain.entity.ElemDataEntity;

import java.util.List;


public interface DataSearchService {
    public DataSearchResultT<ElemDataEntity> getRealDataList(DataSearchParam entity);

    public DataSearchResultT<ElemDataEntity> getMinuteDataList(DataSearchParam entity);

    public DataSearchResultT<ElemDataEntity> getHourDataList(DataSearchParam entity);

    public DataSearchResultT<ElemDataEntity> getDayDataList(DataSearchParam entity);

    public String getFullNameBySourceId(String sourceId);

      //日传输率
    public List<TransferRateEntity> getTransferDayRateList(DataSearchParam entity);
      //月传输率
    public List<TransferRateEntity> getTransferMonthRateList(DataSearchParam entity);

    // 排水户近30天传输率曲线图数据-Day
    public ResultEntity getDay(String sourceId);

    // 排水户近30天传输率曲线图数据-Hour
    public ResultEntity getHour(String sourceId);

    // 排水户近30天传输率曲线图数据-Minute
    public ResultEntity getMinute(String sourceId);

    // 获取排水户近30天传输率图总数据,分钟,小时,天 数据
    public ResultEntity getTransferCharts(String sourceId);

    // 获取企业联网状态总条数
    public Integer getProcStateCount(String sourceId);
}
