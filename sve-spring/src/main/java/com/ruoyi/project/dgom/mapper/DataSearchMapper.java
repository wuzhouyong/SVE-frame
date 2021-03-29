package com.ruoyi.project.dgom.mapper;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.dgom.domain.DataReturnEntity;
import com.ruoyi.project.dgom.domain.DataSearchEnt;
import com.ruoyi.project.dgom.domain.DataSearchPar;
import com.ruoyi.project.dgom.domain.DataTableEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author ruoyi
 * @date 2020-08-25
 */
@Repository("online_monitor3")
public interface DataSearchMapper {

    //查找企业列表
    public List<Map> selectEnterpriseList(@Param("sourceId") String sourceId, @Param("fullName") String fullName);

    // 查找bizSourceId
    @DataSource(value = DataSourceType.MASTER)
    public String checkBizSourceId(@Param("sourceId") String sourceId);

    // 查找sourceId
    @DataSource(value = DataSourceType.MASTER)
    public String checkSourceId(@Param("bizSourceId") String bizSourceId);

    //查找sourceId
    public String getSourceId(@Param("checkpointid") Integer checkpointid);

    //查找排放口
    public List<Map> selectEmissionList(@Param("sourceId") String sourceId);

    //查找因子
    public List<Map> selectElementList();
    public List<Map> getElementListByCheckpointId(String checkpointId);

    //查找街镇
    public List<Map> selectTownNameList();

    //查找任务列表
    public List<DataSearchPar> selectTasklistByCondition(DataSearchPar entity);

    //查找任务详情
    public Map selectAbnortaskheadInfo(String taskheadid);

    //查找因子类型
    public List<Map> selectAbnortaskElementList(String taskheadid);

    //表头时时数据
    public List<DataTableEntity> getHeaderReal(DataSearchPar entity);

    //表头分钟数据
    public List<DataTableEntity> getHeaderMinute(DataSearchPar entity);

    // 表头小时数据
    public List<DataTableEntity> getHeaderHour(DataSearchPar entity);

    // 表头日数据
    public List<DataTableEntity> getHeaderDay(DataSearchPar entity);

    // 实时数据
    public List<DataSearchEnt> getReal(DataSearchPar entity);

    //查询停运时间
    public List<Date> getStopTime(DataSearchPar entity);

    // 分钟数据
    public List<DataSearchEnt> getMinute(DataSearchPar entity);

    // 小时数据
    public List<DataSearchEnt> getHour(DataSearchPar entity);

    // 日数据
    public List<DataSearchEnt> getDay(DataSearchPar entity);

    // 缺失数据(小时)
    public List<DataReturnEntity> getMissingHour(DataSearchPar entity);

    // 零值数据和超标数据(小时和日)
    public List<DataReturnEntity> getZerovalueHour(DataSearchPar entity);


}
