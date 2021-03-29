package com.ruoyi.project.lingxing.mapper;

import com.google.common.util.concurrent.AtomicDouble;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.lingxing.domain.LXDataSearch;
import com.ruoyi.project.lingxing.domain.LXMeterinfo;
import com.ruoyi.project.lingxing.domain.LXStorageinfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2020-08-12
 */
@Mapper
@DataSource(value = DataSourceType.LINGXING)
public interface LXStorageinfoMapper {

    //查询废水收集池
    @DataSource(value = DataSourceType.LINGXING)
    public List<LXStorageinfo> selectStorageinfoList(LXStorageinfo storageinfo);

    //新增废水收集池
    @DataSource(value = DataSourceType.LINGXING)
    public int insertStorageinfo(LXStorageinfo storageinfo);

    //编辑废水收集池
    @DataSource(value = DataSourceType.LINGXING)
    public int updateStorageinfo(LXStorageinfo storageinfo);

    //删除废水收集池
    @DataSource(value = DataSourceType.LINGXING)
    public int deleteStorageinfo(LXStorageinfo storageinfo);

    //查询废水表
    @DataSource(value = DataSourceType.LINGXING)
    public LXMeterinfo selectMeterinfoById(LXMeterinfo meterinfo);

    //查询废水表数据
    @DataSource(value = DataSourceType.LINGXING)
    public List<LXMeterinfo> selectMeterinfoList(LXMeterinfo meterinfo);

    //新增废水表
    @DataSource(value = DataSourceType.LINGXING)
    public int insertMeterinfo(LXMeterinfo meterinfo);

    //编辑废水表
    @DataSource(value = DataSourceType.LINGXING)
    public int updateMeterinfo(LXMeterinfo meterinfo);

    //删除废水表
    @DataSource(value = DataSourceType.LINGXING)
    public int deleteMeterinfo(LXMeterinfo meterinfo);

    //查询数采
    @DataSource(value = DataSourceType.LINGXING)
    public String selectGetinfo(@Param("collectId")String collectId,@Param("eleTypeId")String eleTypeId);

    //水表统计
    @DataSource(value = DataSourceType.LINGXING)
    public List<LXDataSearch> selectMeterinfoDay(String sourceId, String beginTime, String endTime);

    //水池统计分钟数
    @DataSource(value = DataSourceType.LINGXING)
    public List<LXDataSearch> selectStoragefoMinute(String sourceId, String beginTime, String endTime);

    //水池统计分钟总数
    @DataSource(value = DataSourceType.LINGXING)
    public List<LXDataSearch> selectStoragefoToalMinute(String sourceId, String beginTime);

    //水池统计小时数
    @DataSource(value = DataSourceType.LINGXING)
    public List<LXDataSearch> selectStoragefoHour(String sourceId, String beginTime, String endTime);

    //水池统计小时总数
    @DataSource(value = DataSourceType.LINGXING)
    public List<LXDataSearch> selectStoragefoToalHour(String sourceId, String beginTime);

 }

