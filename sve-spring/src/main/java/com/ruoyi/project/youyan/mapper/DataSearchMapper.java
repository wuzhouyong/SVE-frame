package com.ruoyi.project.youyan.mapper;

import com.ruoyi.common.domain.DataSearchColEntity;
import com.ruoyi.common.domain.DataSearchEntity;
import com.ruoyi.common.domain.DataSearchParam;
import com.ruoyi.common.domain.TransferRateEntity;
import com.ruoyi.project.youyan.domain.FacilityRelationEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("yy3_youyan")
public interface DataSearchMapper {
    public List<DataSearchEntity> getElemList(DataSearchParam entity);

    public int getTotal(DataSearchParam entity);

    public List<DataSearchEntity> getLastData(String sourceId);

    public int getRealCount(DataSearchParam entity);

    public String getFullNameBySourceId(String sourceId);

    public List<DataSearchColEntity> getDataCol(@Param("sourceId")String sourceId, @Param("outletId") String outletId);

    public List<FacilityRelationEntity> selectFacility(@Param("sourceId")String sourceId);

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

    // 油烟近30天传输率曲线图数据-Real
    public List<DataSearchEntity> getRealDataList(String sourceId);

    // 油烟近30天传输率曲线图数据-Day
    public List<DataSearchEntity> getDayDataList(String sourceId);

    // 油烟近30天传输率曲线图数据-Hour
    public List<DataSearchEntity> getHourDataList(String sourceId);

    // 油烟近30天传输率曲线图数据-Minute
    public List<DataSearchEntity> getMinuteDataList(String sourceId);

    // 传输率统计头
    public List<DataSearchColEntity> getColEntity(String sourceId);

    // 获取企业联网状态总条数
    public int getProcStateCount(String sourceId);

    // 获取油烟近30天传输率图总数据,分钟,小时,天 数据
    public List<DataSearchEntity> getTransferCharts(String sourceId);

    // 统计传输率统计头
    public List<DataSearchColEntity> getTransfer();

}
