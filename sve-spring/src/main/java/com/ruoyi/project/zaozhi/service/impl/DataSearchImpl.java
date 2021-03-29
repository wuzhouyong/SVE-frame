package com.ruoyi.project.zaozhi.service.impl;

import com.ruoyi.common.domain.*;
import com.ruoyi.common.utils.DataSearchUtils;
import com.ruoyi.common.utils.DateStringUtils;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.enterprise.domain.work_status.WorkStatusDateParam;
import com.ruoyi.project.enterprise.domain.work_status.WorkStatusDateResult;
import com.ruoyi.project.enterprise.service.IWorkStatusService;
import com.ruoyi.project.zaozhi.mapper.DataSearchMapper;
import com.ruoyi.project.zaozhi.service.DataSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@DataSource(value = DataSourceType.ZAOZHI)
@Service("zz2_zaozhi")
public class DataSearchImpl implements DataSearchService {

    @Autowired
    private DataSearchMapper dataSearchMapper;

    @Autowired
    private IWorkStatusService workStatusService;

    @Override
    public List<DataSearchEntity> getElemList(DataSearchParam entity) {
        return dataSearchMapper.getElemList(entity);
    }

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
    public String getFullNameBySourceId(String sourceId) {
        return dataSearchMapper.getFullNameBySourceId(sourceId);
    }

    @Override
    public DataSearchResult getReal(DataSearchParam entity) {
        Date startTime = DateStringUtils.stringToDate("yyyy-MM-dd HH:mm", entity.getStartDate());
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startTime);
        calendar.add(Calendar.HOUR_OF_DAY, 1);
        Date endTime = calendar.getTime();
        entity.setEndDate(DateStringUtils.dateToString("yyyy-MM-dd HH:mm", endTime));
        List<DataSearchEntity> list = dataSearchMapper.getReal(entity);
        List<DataSearchColEntity> cols=dataSearchMapper.getDataCol(entity.getSourceId());
        cols.add(0, new DataSearchColEntity("时间", "date","200"));
        List<Map<String, Object>> groupCols = DataSearchUtils.searchDataCol(cols);
        DataSearchResult result = new DataSearchResult(groupCols, DataSearchUtils.searchData(list));
        return result;
    }

    @Override
    public DataSearchResult getMinute(DataSearchParam entity) {
        Date startTime = DateStringUtils.stringToDate("yyyy-MM-dd", entity.getStartDate());
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startTime);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date endTime = calendar.getTime();
        entity.setEndDate(DateStringUtils.dateToString("yyyy-MM-dd", endTime));
        List<DataSearchEntity> list = dataSearchMapper.getMinute(entity);
        List<DataSearchColEntity> cols=dataSearchMapper.getDataCol(entity.getSourceId());
        cols.add(0, new DataSearchColEntity("时间", "date","200"));
        List<Map<String, Object>> groupCols = DataSearchUtils.searchDataCol(cols);
        DataSearchResult result = new DataSearchResult(groupCols, DataSearchUtils.searchData(list, "yyyy-MM-dd HH:mm"));
        return result;
    }

    @Override
    public DataSearchResult getHour(DataSearchParam entity) {
        Date startTime = DateStringUtils.stringToDate("yyyy-MM-dd", entity.getStartDate());
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startTime);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date endTime = calendar.getTime();
        entity.setEndDate(DateStringUtils.dateToString("yyyy-MM-dd", endTime));
        List<DataSearchEntity> list = dataSearchMapper.getHour(entity);
        List<DataSearchColEntity> cols=dataSearchMapper.getDataCol(entity.getSourceId());
        cols.add(0, new DataSearchColEntity("标记状态", "work_status_name", "100"));
        cols.add(0, new DataSearchColEntity("时间", "date","200"));
        List<Map<String, Object>> groupCols = DataSearchUtils.searchDataCol(cols);
        WorkStatusDateParam param = new WorkStatusDateParam();
        param.setTagHeadId(1019);
        param.setSourceId(entity.getSourceId());
        param.setStartTime(startTime);
        param.setEndTime(endTime);
        List<WorkStatusDateResult> statusDates = workStatusService.getWorkStatusDate(param);
        List<Map<String, Object>> data = DataSearchUtils.searchData(list, "yyyy-MM-dd HH:mm", (date, map) -> {
            Optional<WorkStatusDateResult> optional = statusDates.stream().filter(p -> DateUtils.isSameInstant(p.getDataDate(), date)).findFirst();
            if (optional.isPresent()) {
                WorkStatusDateResult result = optional.get();
                map.put("work_status", result.getWorkStatus());
                map.put("work_status_name", result.getWorkStatusName());
                return;
            }
            map.put("work_status", -1);
            map.put("work_status_name", "正常");
        });
        return new DataSearchResult(groupCols, data);
    }
    @Override
    public DataSearchResult getDay(DataSearchParam entity) {
        List<DataSearchEntity> list = dataSearchMapper.getDay(entity);
        List<DataSearchColEntity> cols=dataSearchMapper.getDataCol(entity.getSourceId());
        cols.add(0, new DataSearchColEntity("时间", "date","150"));
        List<Map<String, Object>> groupCols = DataSearchUtils.searchDataCol(cols);
        DataSearchResult result = new DataSearchResult(groupCols, DataSearchUtils.searchData(list, "yyyy-MM-dd"));
        return result;
    }

    @Override
    public ResultEntity getHourSummary(DataSearchParam entity) {
        Date startTime = DateStringUtils.stringToDate("yyyy-MM-dd", entity.getStartDate());
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startTime);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date endTime = calendar.getTime();
        entity.setEndDate(DateStringUtils.dateToString("yyyy-MM-dd", endTime));

        List<DataSearchEntity> list = dataSearchMapper.getHourSummary(entity);
        // String dataType="Summary";
        List<DataSearchColEntity> cols=dataSearchMapper.getDataSummaryCol(entity.getSourceId());
        cols.add(0, new DataSearchColEntity("时间", "date","200"));

        List<Map<String, Object>> data = DataSearchUtils.searchData(list,"yyyy-MM-dd HH:mm");
        return ResultEntity.success(new DataSearchResult(cols, data));
    }

    @Override
    public ResultEntity getDaySummary(DataSearchParam entity) {
        List<DataSearchEntity> list = dataSearchMapper.getDaySummary(entity);

        List<DataSearchColEntity> cols=dataSearchMapper.getDataSummaryCol(entity.getSourceId());
        cols.add(0, new DataSearchColEntity("时间", "date","150"));
        List<Map<String, Object>> data = DataSearchUtils.searchData(list,"yyyy-MM-dd");
        return ResultEntity.success(new DataSearchResult(cols, data));
    }

    @Override
    public List<DataSearchColEntity> getDataCol(String sourceId) {
        return dataSearchMapper.getDataCol(sourceId);
    }

    @Override
    public List<DataSearchColEntity> getDataSummaryCol(String sourceId) {
        return dataSearchMapper.getDataSummaryCol(sourceId);
    }

    @Override //日传输率
    @DataScope(regionAlias = "s")
    public List<TransferRateEntity> getTransferDayRateList(DataSearchParam entity) {
        if (entity.getStartDate() == null || entity.getEndDate() == null) {
            String startDate = DateStringUtils.dateToString("yyyy-MM-dd", new Date((new Date().getTime() - 7 * 24 * 60 * 60 * 1000)));
            String endDate = DateStringUtils.dateToString("yyyy-MM-dd", new Date());
            entity.setStartDate(startDate);
            entity.setEndDate(endDate);
        }
        List<TransferRateEntity> list = dataSearchMapper.transferDayRateForSource(entity);
        for (TransferRateEntity transferRate : list) {
            entity.setSourceId(transferRate.getSourceId());
            String date = DateStringUtils.dateToString("yyyy-MM-dd", transferRate.getDataDate());
            entity.setStartDate(date);
            entity.setEndDate(date);
            List<TransferRateEntity> entityList = dataSearchMapper.transferDayRateForCheckpoint(entity);
            transferRate.setChildren(entityList);
        }
        return list;
    }

    @Override  //月传输率
    @DataScope(regionAlias = "s")
    public List<TransferRateEntity> getTransferMonthRateList(DataSearchParam entity) {
        if (entity.getStartDate() == null || entity.getEndDate() == null) {
            Calendar c = Calendar.getInstance();
            c.setTime(new Date());
            c.add(Calendar.MONTH, -1); //过去一月
            String startDate = DateStringUtils.dateToString("yyyy-MM", c.getTime());
            String endDate = DateStringUtils.dateToString("yyyy-MM", new Date());
            entity.setStartDate(startDate);
            entity.setEndDate(endDate);
        }
        List<TransferRateEntity> list = dataSearchMapper.transferMonthRateForSource(entity);
        for (TransferRateEntity transferRate : list) {
            entity.setSourceId(transferRate.getSourceId());
            String date = DateStringUtils.dateToString("yyyy-MM", transferRate.getDataDate());
            transferRate.setDataDate(DateStringUtils.stringToDate("yyyy-MM",date));
            entity.setStartDate(date);
            entity.setEndDate(date);
            List<TransferRateEntity> entityList = dataSearchMapper.transferMonthRateForCheckpoint(entity);
            transferRate.setChildren(entityList);
        }
        return list;
    }

    @Override  // 造纸业近30天传输率曲线图数据-Real
    public ResultEntity getRealDataList(String sourceId) {
        List<DataSearchEntity> day = dataSearchMapper.getRealDataList(sourceId);
        List<Map<String, Object>> tList = DataSearchUtils.searchData(day, "yyyy-MM-dd");
        List<DataSearchColEntity> dataList = dataSearchMapper.getColEntity(sourceId);
        return ResultEntity.success(new DataSearchResult(dataList, tList));
    }

    @Override  // 造纸业近30天传输率曲线图数据-Day
    public ResultEntity getDayDataList(String sourceId) {
        List<DataSearchEntity> day = dataSearchMapper.getDayDataList(sourceId);
        List<Map<String, Object>> tList = DataSearchUtils.searchData(day, "yyyy-MM-dd");
        List<DataSearchColEntity> dataList = dataSearchMapper.getColEntity(sourceId);
        return ResultEntity.success(new DataSearchResult(dataList, tList));
    }

    @Override  // 造纸业近30天传输率曲线图数据-Hour
    public ResultEntity getHourDataList(String sourceId) {
        List<DataSearchEntity> day = dataSearchMapper.getHourDataList(sourceId);
        List<Map<String, Object>> tList = DataSearchUtils.searchData(day, "yyyy-MM-dd");
        List<DataSearchColEntity> dataList = dataSearchMapper.getColEntity(sourceId);
        return ResultEntity.success(new DataSearchResult(dataList, tList));
    }

    @Override  // 造纸业近30天传输率曲线图数据-Minute
    public ResultEntity getMinuteDataList(String sourceId) {
        List<DataSearchEntity> day = dataSearchMapper.getMinuteDataList(sourceId);
        List<Map<String, Object>> tList = DataSearchUtils.searchData(day, "yyyy-MM-dd");
        List<DataSearchColEntity> dataList = dataSearchMapper.getColEntity(sourceId);
        return ResultEntity.success(new DataSearchResult(dataList, tList));
    }

    @Override  // 获取造纸业近30天传输率图总数据,分钟,小时,天 数据
    public ResultEntity getTransferCharts(String sourceId) {
        List<DataSearchEntity> day = dataSearchMapper.getTransferCharts(sourceId);
        List<Map<String, Object>> tList = DataSearchUtils.searchData(day, "yyyy-MM-dd");

        List<DataSearchColEntity> dataList = dataSearchMapper.getTransfer();
        return ResultEntity.success(new DataSearchResult(dataList, tList));
    }

    @Override  // 获取企业联网状态总条数
    public Integer getProcStateCount(String sourceId) {
        return dataSearchMapper.getProcStateCount(sourceId);
    }

}
