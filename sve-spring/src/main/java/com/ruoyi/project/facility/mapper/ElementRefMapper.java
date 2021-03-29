package com.ruoyi.project.facility.mapper;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.facility.domain.ElementRefEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
@DataSource(value = DataSourceType.MASTER)
public interface ElementRefMapper {
    @DataSource(value = DataSourceType.MASTER)
    int addElementRef(ElementRefEntity entity);
    @DataSource(value = DataSourceType.MASTER)
    int removeElementRef(@Param("tagHeadId") int tagHeadId, @Param("elementIds") List<Integer> elementIds, @Param("updatedBy") String updatedBy);
}
