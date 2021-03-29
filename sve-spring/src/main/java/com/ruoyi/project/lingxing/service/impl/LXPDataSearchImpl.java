package com.ruoyi.project.lingxing.service.impl;

import com.ruoyi.common.domain.*;
import com.ruoyi.common.utils.DataSearchUtils;
import com.ruoyi.common.utils.DateStringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.lingxing.mapper.LXPDataSearchMapper;
import com.ruoyi.project.lingxing.service.LXPDataSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@DataSource(value = DataSourceType.LINGXING)
@Service
public class LXPDataSearchImpl implements LXPDataSearchService {

    @Autowired
    private LXPDataSearchMapper dataSearchMapper;

    @Override
    public int getTotal(DataSearchParam entity) {
        return dataSearchMapper.getTotal(entity);
    }

    @Override
    public List<DataSearchEntity> getLastData(String sourceId) {
        return dataSearchMapper.getLastData(sourceId);
    }

    @Override
    public int getRealCount(DataSearchParam entity) {
        return dataSearchMapper.getRealCount(entity);
    }

    @Override
    public ResultEntity getReal(DataSearchParam entity) {
        Date startTime = DateStringUtils.stringToDate("yyyy-MM-dd HH:mm", entity.getStartDate());
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startTime);
        calendar.add(Calendar.HOUR_OF_DAY, 1);
        Date endTime = calendar.getTime();
        entity.setEndDate(DateStringUtils.dateToString("yyyy-MM-dd HH:mm", endTime));
        List<DataSearchEntity> list = dataSearchMapper.getReal(entity);
        List<DataSearchColEntity> cols = dataSearchMapper.getDataCol(entity.getSourceId());
        cols.add(0, new DataSearchColEntity("时间", "date", "200"));
        List<Map<String, Object>> groupCols = DataSearchUtils.searchDataCol(cols);
        return ResultEntity.success(new DataSearchResult(groupCols, DataSearchUtils.searchData(list)));
    }

    @Override
    public ResultEntity getMinute(DataSearchParam entity) {
        Date startTime = DateStringUtils.stringToDate("yyyy-MM-dd", entity.getStartDate());
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startTime);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date endTime = calendar.getTime();
        entity.setEndDate(DateStringUtils.dateToString("yyyy-MM-dd", endTime));
        List<DataSearchEntity> list = dataSearchMapper.getMinute(entity);
        List<DataSearchColEntity> cols = dataSearchMapper.getDataCol(entity.getSourceId());
        cols.add(0, new DataSearchColEntity("时间", "date", "200"));
        List<Map<String, Object>> groupCols = DataSearchUtils.searchDataCol(cols);
        return ResultEntity.success(new DataSearchResult(groupCols, DataSearchUtils.searchData(list, "yyyy-MM-dd HH:mm")));
    }

    @Override
    public ResultEntity getHour(DataSearchParam entity) {
        Date startTime = DateStringUtils.stringToDate("yyyy-MM-dd", entity.getStartDate());
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startTime);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date endTime = calendar.getTime();
        entity.setEndDate(DateStringUtils.dateToString("yyyy-MM-dd", endTime));
        List<DataSearchEntity> list = dataSearchMapper.getHour(entity);
        List<DataSearchColEntity> cols = dataSearchMapper.getDataCol(entity.getSourceId());
        cols.add(0, new DataSearchColEntity("时间", "date", "200"));
        List<Map<String, Object>> groupCols = DataSearchUtils.searchDataCol(cols);
        return ResultEntity.success(new DataSearchResult(groupCols, DataSearchUtils.searchData(list, "yyyy-MM-dd HH:mm")));
    }

    @Override
    public ResultEntity getDay(DataSearchParam entity) {
        List<DataSearchEntity> list = dataSearchMapper.getDay(entity);
        List<DataSearchColEntity> cols = dataSearchMapper.getDataCol(entity.getSourceId());
        cols.add(0, new DataSearchColEntity("时间", "date", "150"));
        List<Map<String, Object>> groupCols = DataSearchUtils.searchDataCol(cols);
        return ResultEntity.success(new DataSearchResult(groupCols, DataSearchUtils.searchData(list, "yyyy-MM-dd")));
    }

}
