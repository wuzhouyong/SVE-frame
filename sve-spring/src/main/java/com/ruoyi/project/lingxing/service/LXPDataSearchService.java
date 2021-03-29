package com.ruoyi.project.lingxing.service;

import com.ruoyi.common.domain.*;

import java.util.List;

public interface LXPDataSearchService {

    public int getTotal(DataSearchParam entity);

    public List<DataSearchEntity> getLastData(String sourceId);

    public int getRealCount(DataSearchParam entity);

    public ResultEntity getReal(DataSearchParam entity);

    public ResultEntity getMinute(DataSearchParam entity);

    public ResultEntity getHour(DataSearchParam entity);

    public ResultEntity getDay(DataSearchParam entity);


}
