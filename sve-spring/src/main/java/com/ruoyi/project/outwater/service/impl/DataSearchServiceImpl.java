package com.ruoyi.project.outwater.service.impl;

import com.ruoyi.common.domain.*;
import com.ruoyi.common.utils.*;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.enterprise.domain.work_status.WorkStatusDateParam;
import com.ruoyi.project.enterprise.domain.work_status.WorkStatusDateResult;
import com.ruoyi.project.enterprise.service.IWorkStatusService;
import com.ruoyi.project.outwater.domain.BasCheckPoint;
import com.ruoyi.project.outwater.domain.entity.ElemDataEntity;
import com.ruoyi.project.outwater.mapper.BasCollectMapper;
import com.ruoyi.project.outwater.mapper.DataSearchMapper;
import com.ruoyi.project.outwater.service.DataSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@DataSource(value = DataSourceType.OUTWATER)
@Service("ow2_outwater")
public class DataSearchServiceImpl implements DataSearchService {

    @Autowired
    private DataSearchMapper dataSearchMapper;

    @Autowired
    private BasCollectMapper basCollectMapper;

    @Autowired
    private IWorkStatusService workStatusService;

    @Override
    public String getFullNameBySourceId(String sourceId) {
        return dataSearchMapper.getFullNameBySourceId(sourceId);
    }


    @Override
    public DataSearchResultT<ElemDataEntity> getRealDataList(DataSearchParam entity) {
        Date startTime = DateStringUtils.stringToDate("yyyy-MM-dd HH:mm", entity.getStartDate());
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startTime);
        calendar.add(Calendar.HOUR_OF_DAY, 2);
        Date endTime = calendar.getTime();
        entity.setEndDate(DateStringUtils.dateToString("yyyy-MM-dd HH:mm", endTime));
        List<BasCheckPoint> checkPoint = basCollectMapper.selectCheckPointType(entity.getCheckPointId());
        List<ElemDataEntity> dataList = null;
        if (checkPoint.get(0).getCheckPointType().equals("1"))
            dataList = dataSearchMapper.selectRealDataS(entity);
        else
            dataList = dataSearchMapper.selectRealDataR(entity);
        List<DataSearchColEntity> tList = DataMapUtils.getTitle(checkPoint.get(0).getCheckPointType(), "REAL");
        DataSearchResultT<ElemDataEntity> resultT = new DataSearchResultT<>(tList, dataList);
        return resultT;
    }

    @Override
    public DataSearchResultT<ElemDataEntity> getMinuteDataList(DataSearchParam entity) {
        Date startTime = DateStringUtils.stringToDate("yyyy-MM-dd", entity.getStartDate());
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startTime);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date endTime = calendar.getTime();
        entity.setEndDate(DateStringUtils.dateToString("yyyy-MM-dd", endTime));
        if (!StringUtils.isEmpty(entity.getCheckPointId())) {
            List<BasCheckPoint> checkPoint = basCollectMapper.selectCheckPointType(entity.getCheckPointId());
            List<ElemDataEntity> dataList = null;
            if (checkPoint.get(0).getCheckPointType().equals("1"))
                dataList = dataSearchMapper.selectMinuteDataS(entity);
            else
                dataList = dataSearchMapper.selectMinuteDataR(entity);
            List<DataSearchColEntity> tList = DataMapUtils.getTitle(checkPoint.get(0).getCheckPointType(), "MINUTE");
            DataSearchResultT<ElemDataEntity> resultT = new DataSearchResultT<>(tList, dataList);
            return resultT;
        }
        return null;
    }

    @Override
    public DataSearchResultT<ElemDataEntity> getHourDataList(DataSearchParam entity) {
        Date startTime = DateStringUtils.stringToDate("yyyy-MM-dd", entity.getStartDate());
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startTime);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date endTime = calendar.getTime();
        entity.setEndDate(DateStringUtils.dateToString("yyyy-MM-dd", endTime));
        if (!StringUtils.isEmpty(entity.getCheckPointId())) {
            List<BasCheckPoint> checkPoint = basCollectMapper.selectCheckPointType(entity.getCheckPointId());
            List<ElemDataEntity> dataList = null;

            if (checkPoint.get(0).getCheckPointType().equals("1"))
                dataList = dataSearchMapper.selectHourDataS(entity);
            else
                dataList = dataSearchMapper.selectHourDataR(entity);
            WorkStatusDateParam param = new WorkStatusDateParam();
            param.setTagHeadId(1012);
            param.setSourceId(entity.getSourceId());
            param.setStartTime(startTime);
            param.setEndTime(endTime);
            List<WorkStatusDateResult> statusDates = workStatusService.getWorkStatusDate(param);
            dataList.forEach(e -> {
                Date date = e.getDataDate();
                Optional<WorkStatusDateResult> optional = statusDates.stream().filter(p -> DateUtils.isSameInstant(p.getDataDate(), date)).findFirst();
                if (optional.isPresent()) {
                    WorkStatusDateResult result = optional.get();
                    e.setWorkStatus(result.getWorkStatus());
                    e.setWorkStatusName(result.getWorkStatusName());
                    return;
                }
                e.setWorkStatus(-1);
                e.setWorkStatusName("正常");
            });
            List<DataSearchColEntity> tList = DataMapUtils.getTitle(checkPoint.get(0).getCheckPointType(), "HOUR");
            return new DataSearchResultT<>(tList, dataList);
        }
        return null;
    }

    @Override
    public DataSearchResultT<ElemDataEntity> getDayDataList(DataSearchParam entity) {
        if (!StringUtils.isEmpty(entity.getCheckPointId())) {
            List<BasCheckPoint> checkPoint = basCollectMapper.selectCheckPointType(entity.getCheckPointId());
            List<ElemDataEntity> dataList = null;

            if (checkPoint.get(0).getCheckPointType().equals("1"))
                dataList = dataSearchMapper.selectDayDataS(entity);
            else
                dataList = dataSearchMapper.selectDayDataR(entity);
            for (ElemDataEntity e : dataList) {
                e.setDataDateD(e.getDataDate());
            }
            List<DataSearchColEntity> tList = DataMapUtils.getTitle(checkPoint.get(0).getCheckPointType(), "DAY");
            DataSearchResultT<ElemDataEntity> resultT = new DataSearchResultT<>(tList, dataList);
            return resultT;
        }
        return null;
    }

    @Override  //日传输率
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

    @Override  // 排水户近30天传输率曲线图数据-Day
    public ResultEntity getDay(String sourceId) {
        List<DataSearchEntity> day = dataSearchMapper.getDay(sourceId);
        List<Map<String, Object>> tList = DataSearchUtils.searchData(day, "yyyy-MM-dd");
        List<DataSearchColEntity> dataList = dataSearchMapper.getColEntity(sourceId);
        return ResultEntity.success(new DataSearchResult(dataList, tList));

    }

    @Override  // 排水户近30天传输率曲线图数据-Hour
    public ResultEntity getHour(String sourceId) {
        List<DataSearchEntity> day = dataSearchMapper.getHour(sourceId);
        List<Map<String, Object>> tList = DataSearchUtils.searchData(day, "yyyy-MM-dd");
        List<DataSearchColEntity> dataList = dataSearchMapper.getColEntity(sourceId);
        return ResultEntity.success(new DataSearchResult(dataList, tList));
    }

    @Override  // 排水户近30天传输率曲线图数据-Minute
    public ResultEntity getMinute(String sourceId) {
        List<DataSearchEntity> day = dataSearchMapper.getMinute(sourceId);
        List<Map<String, Object>> tList = DataSearchUtils.searchData(day, "yyyy-MM-dd");
        List<DataSearchColEntity> dataList = dataSearchMapper.getColEntity(sourceId);
        return ResultEntity.success(new DataSearchResult(dataList, tList));
    }

    @Override  // 获取排水户近30天传输率图总数据,分钟,小时,天 数据
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
