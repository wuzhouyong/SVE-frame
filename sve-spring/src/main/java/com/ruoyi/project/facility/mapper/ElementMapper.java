package com.ruoyi.project.facility.mapper;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.facility.domain.ElementEntity;
import com.ruoyi.project.facility.domain.ElementParam;
import com.ruoyi.project.facility.domain.ElementResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ElementMapper {
    List<ElementResult> getElementList(ElementParam param);
    @DataSource(value = DataSourceType.LINGXING)
    List<ElementResult> getElementListForLingXing(ElementParam param);
    List<ElementResult> getElementListForVOC(ElementParam param);
    int addElement(ElementEntity entity);
    int editElement(ElementEntity entity);
    int removeElement(@Param("cpElemIds") List<Integer> cpElemIds, @Param("updatedBy") String updatedBy);

}
