package com.ruoyi.project.outwater.mapper;

import com.ruoyi.project.outwater.domain.ExceptformulaEntity;
import com.ruoyi.project.outwater.domain.SpecwarnmsgEntity;
import com.ruoyi.project.outwater.domain.entity.SpecwarnmsgExport;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author solang
 * @date 2020-11-23 9:33
 */
@Repository
public interface BoundaryExceptMapper {

    List<ExceptformulaEntity> selectBoundaryExceptList(ExceptformulaEntity entity);
    List<ExceptformulaEntity> selectBoundarySingle(@Param("warnLevel") String warnLevel);
    List<ExceptformulaEntity> selectBoundarygGroup(@Param("warnLevel") String warnLevel);
    List<SpecwarnmsgEntity> selectSpecwarnmsgList(SpecwarnmsgEntity entity);
    List<SpecwarnmsgExport> selectSpecwarnmsgExport(SpecwarnmsgEntity entity);

    int editBoundaryExcept(ExceptformulaEntity entity);


}
