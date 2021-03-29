package com.ruoyi.project.outwater.service;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.project.outwater.domain.ExceptformulaEntity;
import com.ruoyi.project.outwater.domain.SpecwarnmsgEntity;
import com.ruoyi.project.outwater.domain.entity.SpecwarnmsgExport;

import java.util.List;
import java.util.Map;

/**
 * @author solang
 * @date 2020-11-23 9:31
 */
public interface BoundaryExceptService {

    public Map selectBoundaryExceptList(ExceptformulaEntity entity);
    public Map selectBoundaryExceptList1(ExceptformulaEntity entity);
    public List<SpecwarnmsgEntity> selectSpecwarnmsgList(SpecwarnmsgEntity entity);
    public List<SpecwarnmsgExport> selectSpecwarnmsgExport(SpecwarnmsgEntity entity);

    public ResultEntity editBoundaryExcept(List<ExceptformulaEntity> entity);



}
