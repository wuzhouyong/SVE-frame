package com.ruoyi.project.lingxing.mapper;

import com.ruoyi.common.domain.DataSearchColEntity;
import com.ruoyi.common.domain.DataSearchEntity;
import com.ruoyi.common.domain.DataSearchParam;
import com.ruoyi.common.domain.TransferRateEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LXPDataSearchMapper {

    public List<DataSearchEntity> getElemList(DataSearchParam entity);

    public int getTotal(DataSearchParam entity);

    public List<DataSearchEntity> getLastData(String sourceId);

    public int getRealCount(DataSearchParam entity);

    public List<DataSearchColEntity> getDataCol(String sourceId);

    public List<DataSearchColEntity> getDataSummaryCol(String sourceId);

    public List<DataSearchEntity> getReal(DataSearchParam entity);

    public List<DataSearchEntity> getMinute(DataSearchParam entity);

    public List<DataSearchEntity> getHour(DataSearchParam entity);

    public List<DataSearchEntity> getDay(DataSearchParam entity);


}
