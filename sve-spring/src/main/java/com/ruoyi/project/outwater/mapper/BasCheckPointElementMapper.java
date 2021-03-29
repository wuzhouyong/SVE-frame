package com.ruoyi.project.outwater.mapper;

import com.ruoyi.project.outwater.domain.BasCheckPointElement;
import com.ruoyi.project.outwater.domain.entity.NameAndIdEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface BasCheckPointElementMapper {

    public int insertCheckPointElement(BasCheckPointElement entity);

    public int repeatCheck(BasCheckPointElement entity);

    public int deleteCheckPointElementById(@Param("cpElementId")String cpElementId, @Param("updatedBy")String updatedBy);

    public int updateCheckPointElement(BasCheckPointElement entity);

    public List<NameAndIdEntity> selectElementIdNameListList(@Param("elemTypeDesc")String elemTypeDesc, @Param("elemTypeId")String elemTypeId);

    public List<BasCheckPointElement> selectCheckPointElementList(BasCheckPointElement entity);

    public int getTotal(BasCheckPointElement entity);
}
