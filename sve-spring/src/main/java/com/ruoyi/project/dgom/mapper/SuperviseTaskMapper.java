package com.ruoyi.project.dgom.mapper;


import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.dgom.domain.SuperviseEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author ruoyi
 * @date 2020-08-25
 */
@Mapper
public interface SuperviseTaskMapper {

    //查询督办任务列表
    public List<SuperviseEntity> selectSuperviseTaskList(SuperviseEntity entity);

    // 查询分局处理列表
    public List<SuperviseEntity> selectDivideTaskList(SuperviseEntity entity);

    public SuperviseEntity selectSuperviseTaskById(Integer suptaskId);

    public List<SuperviseEntity> selectSuperviseTaskByIds(@Param("suptaskIds") Integer[] suptaskIds);

    //新增督办任务
    public int insertSuperviseTask(SuperviseEntity entity);

    //查找在线监控平台街镇id
    @DataSource(value = DataSourceType.HBONLINE)
    public Integer selectTownIdByTownName(String townName);

    //查找中心平台街镇id和名称
    @DataSource(value = DataSourceType.MASTER)
    public Map selectTownCodeByOmtownId(Integer omtownId);
    public Integer getOMTownIdByCode(@Param("townCode") String townCode);

    //批量新增督办任务
    public Integer insertBatchSuperviseTask(@Param("list") List<SuperviseEntity> list);

    //修改督办任务
    public int editSuperviseTask(SuperviseEntity entity);

    //市局批量处理任务，只改状态
    public int editMunicipalTask(SuperviseEntity entity);

    //删除督办任务
    public int deleteSuperviseTaskByIds(@Param("suptaskIds") Integer[] suptaskIds, @Param("updatedBy") String updatedBy);


}
