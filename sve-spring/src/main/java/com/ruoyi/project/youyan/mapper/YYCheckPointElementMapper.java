package com.ruoyi.project.youyan.mapper;

import java.util.List;

import com.ruoyi.project.youyan.domain.YYCPElementEntity;
import com.ruoyi.project.youyan.domain.YYDeleteEntity;
import com.ruoyi.project.youyan.domain.YYNameIdEntity;
import org.apache.ibatis.annotations.Param;

public interface YYCheckPointElementMapper {

    public List<YYCPElementEntity> selectCPElement(YYCPElementEntity entity);

    public List<YYNameIdEntity> selectElement(YYNameIdEntity entity);

    public int insertCPElement(YYCPElementEntity entity);

    public int updateCPElement(YYCPElementEntity entity);

    public int deleteCPElement(YYDeleteEntity entity);

    public int repeatCheck(YYCPElementEntity entity);

    //查找因子id
    public String selectCPElementIdByCondition(YYCPElementEntity entity);

    //查询因子是否已存在
    public YYCPElementEntity selectCPElementByCondition(YYCPElementEntity entity);

    //把无关联且已经重复的因子关联到生产线上
    public int updateCPElementByFacilityId(YYCPElementEntity entity);

    //删除因子
    public int deleteCPElementByFacilityId(@Param("facilityId") Integer facilityId, @Param("updatedBy") String updatedBy);

    //查询旧因子
    public List<YYCPElementEntity> selectCPElemIdByIdfacilityId(Integer facilityId);

    //通过cpElemId查询旧因子
    public YYCPElementEntity selectCPElementByElemId(Integer cpElemId);

    //删除因子
    public int deleteCPElementById(YYCPElementEntity entity);

}
