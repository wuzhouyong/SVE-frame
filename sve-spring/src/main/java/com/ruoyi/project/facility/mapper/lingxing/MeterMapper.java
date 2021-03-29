package com.ruoyi.project.facility.mapper.lingxing;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.facility.domain.lingxing.meter.MeterEntity;
import com.ruoyi.project.facility.domain.lingxing.meter.MeterParam;
import com.ruoyi.project.facility.domain.lingxing.meter.MeterResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MeterMapper {
    @DataSource(value = DataSourceType.LINGXING)
    List<MeterResult> getMeterList(MeterParam param);
    @DataSource(value = DataSourceType.LINGXING)
    int addMeter(MeterEntity entity);
    @DataSource(value = DataSourceType.LINGXING)
    int editMeter(MeterEntity entity);
    @DataSource(value = DataSourceType.LINGXING)
    int removeMeter(@Param("meterIds") String[] meterIds, @Param("updatedBy") String updatedBy);
}
