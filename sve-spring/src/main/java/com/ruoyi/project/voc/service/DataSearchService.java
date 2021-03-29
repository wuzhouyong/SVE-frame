package com.ruoyi.project.voc.service;

import com.ruoyi.common.domain.*;

import java.util.List;

public interface DataSearchService {
    public List<DataSearchEntity> getElemList(DataSearchParam entity);

    public int getTotal(DataSearchParam entity);

    public List<DataSearchEntity> getLastData(String sourceId);

    public int getRealCount(DataSearchParam entity);

    public DataSearchResult getReal(DataSearchParam entity);

    public DataSearchResult getMinute(DataSearchParam entity);

    public DataSearchResult getHour(DataSearchParam entity);

    public DataSearchResult getDay(DataSearchParam entity);

    public String getFullNameBySourceId(String sourceId);

    public ResultEntity getHourSummary(DataSearchParam entity);

    public ResultEntity getDaySummary(DataSearchParam entity);

    //日传输率
    public List<TransferRateEntity> getTransferDayRateList(DataSearchParam entity);
    //月传输率
    public List<TransferRateEntity> getTransferMonthRateList(DataSearchParam entity);

    // VOC近30天传输率曲线图数据-Real
    public ResultEntity getRealDataList(String sourceId);

    // VOC近30天传输率曲线图数据-Day
    public ResultEntity getDayDataList(String sourceId);

    // VOC近30天传输率曲线图数据-Hour
    public ResultEntity getHourDataList(String sourceId);

    // VOC近30天传输率曲线图数据-Minute
    public ResultEntity getMinuteDataList(String sourceId);

    // 获取VOC近30天传输率图总数据,分钟,小时,天 数据
    public ResultEntity getTransferCharts(String sourceId);

    // 获取企业联网状态总条数
    public Integer getProcStateCount(String sourceId);

}
