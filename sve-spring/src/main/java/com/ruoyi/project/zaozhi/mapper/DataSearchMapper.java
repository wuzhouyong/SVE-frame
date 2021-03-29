package com.ruoyi.project.zaozhi.mapper;

import java.util.List;

import com.ruoyi.common.domain.DataSearchColEntity;
import com.ruoyi.common.domain.DataSearchParam;
import com.ruoyi.common.domain.DataSearchEntity;
import com.ruoyi.common.domain.TransferRateEntity;
import org.springframework.stereotype.Repository;

@Repository("zz3_zaozhi")
public interface DataSearchMapper {
    public List<DataSearchEntity> getElemList(DataSearchParam entity);

    public int getTotal(DataSearchParam entity);

    public List<DataSearchEntity> getLastData(String sourceId);

    public int getRealCount(DataSearchParam entity);

    public String getFullNameBySourceId(String sourceId);

    public List<DataSearchColEntity> getDataCol(String sourceId);

    public List<DataSearchColEntity> getDataSummaryCol(String sourceId);

    public List<DataSearchEntity> getReal(DataSearchParam entity);

    public List<DataSearchEntity> getMinute(DataSearchParam entity);

    public List<DataSearchEntity> getHour(DataSearchParam entity);

    public List<DataSearchEntity> getDay(DataSearchParam entity);

    public List<DataSearchEntity> getHourSummary(DataSearchParam entity);

    public List<DataSearchEntity> getDaySummary(DataSearchParam entity);

    //日传输率
    public List<TransferRateEntity> transferDayRateForSource(DataSearchParam entity);
    //日传输率
    public List<TransferRateEntity> transferDayRateForCheckpoint(DataSearchParam entity);
    //月传输率
    public List<TransferRateEntity> transferMonthRateForSource(DataSearchParam entity);
    //月传输率
    public List<TransferRateEntity> transferMonthRateForCheckpoint(DataSearchParam entity);

    // 造纸业近30天传输率曲线图数据-Real
    public List<DataSearchEntity> getRealDataList(String sourceId);

    // 造纸业近30天传输率曲线图数据-Day
    public List<DataSearchEntity> getDayDataList(String sourceId);

    // 造纸业近30天传输率曲线图数据-Hour
    public List<DataSearchEntity> getHourDataList(String sourceId);

    // 造纸业近30天传输率曲线图数据-Minute
    public List<DataSearchEntity> getMinuteDataList(String sourceId);

    // 传输率统计头
    public List<DataSearchColEntity> getColEntity(String sourceId);

    // 获取造纸业近30天传输率图总数据,分钟,小时,天 数据
    public List<DataSearchEntity> getTransferCharts(String sourceId);

    // 统计传输率统计头
    public List<DataSearchColEntity> getTransfer();

    // 获取企业联网状态总条数
    public int getProcStateCount(String sourceId);

}
