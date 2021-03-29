package com.ruoyi.project.outwater.mapper;

import com.ruoyi.common.domain.DataSearchColEntity;
import com.ruoyi.common.domain.DataSearchParam;
import com.ruoyi.common.domain.DataSearchEntity;
import com.ruoyi.project.outwater.domain.entity.ElemDataEntity;
import com.ruoyi.project.outwater.domain.entity.ElemTitleEntity;
import com.ruoyi.common.domain.TransferRateEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("ow3_outwater")
public interface DataSearchMapper {

    public List<ElemDataEntity> selectRealDataS(DataSearchParam entity);

    public List<ElemDataEntity> selectRealDataR(DataSearchParam entity);

    public List<ElemDataEntity> selectMinuteDataS(DataSearchParam entity);

    public List<ElemDataEntity> selectMinuteDataR(DataSearchParam entity);

    public List<ElemDataEntity> selectHourDataS(DataSearchParam entity);

    public List<ElemDataEntity> selectHourDataR(DataSearchParam entity);

    public String getFullNameBySourceId(String sourceId);

    public List<ElemDataEntity> selectDayDataS(DataSearchParam entity);

    public List<ElemDataEntity> selectDayDataR(DataSearchParam entity);
    //日传输率
    public List<TransferRateEntity> transferDayRateForSource(DataSearchParam entity);
    //日传输率
    public List<TransferRateEntity> transferDayRateForCheckpoint(DataSearchParam entity);
    //月传输率
    public List<TransferRateEntity> transferMonthRateForSource(DataSearchParam entity);
    //月传输率
    public List<TransferRateEntity> transferMonthRateForCheckpoint(DataSearchParam entity);

     // 排水户近30天传输率曲线图数据-Day
    public List<DataSearchEntity> getDay(String sourceId);

     // 排水户近30天传输率曲线图数据-Hour
    public List<DataSearchEntity> getHour(String sourceId);

    // 排水户近30天传输率曲线图数据-Minute
    public List<DataSearchEntity> getMinute(String sourceId);

     // 传输率统计头
    public List<DataSearchColEntity> getColEntity(String sourceId);

    // 获取企业联网状态总条数
    public int getProcStateCount(String sourceId);

    // 获取排水户近30天传输率图总数据,分钟,小时,天 数据
    public List<DataSearchEntity> getTransferCharts(String sourceId);

    // 统计传输率统计头
    public List<DataSearchColEntity> getTransfer();
}
