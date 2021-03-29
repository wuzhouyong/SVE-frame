package com.ruoyi.project.dgom.service;


import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.project.dgom.domain.ExportSuperviseEntity;
import com.ruoyi.project.dgom.domain.SuperviseEntity;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 【请填写功能名称】Service接口
 *
 * @author ruoyi
 * @date 2020-08-25
 */
public interface SuperviseTaskService {

    //查询督办任务列表
    public List<SuperviseEntity> selectSuperviseTaskList(SuperviseEntity entity);

    //查询分局处理列表
    public List<SuperviseEntity> selectDivideTaskList(SuperviseEntity entity);

    //新增督办任务
    public ResultEntity insertSuperviseTask(SuperviseEntity entity);

    //批量上传督办任务
    public ResultEntity batchAddSuperviseTask(List<SuperviseEntity> list) throws SQLException;

    //修改督办任务
    public int editSuperviseTask(SuperviseEntity entity) throws SQLException;

    //分局批量处理
    public int editDivideTask(List<SuperviseEntity> entitys) throws SQLException;

    // 市局批量处理 只修改状态 驳回5 同意6
    public int editMunicipalTask(List<SuperviseEntity> entitys) throws SQLException;

    //删除督办任务
    public int deleteSuperviseTaskByIds(Integer[] suptaskIds) throws SQLException;

    //导入督办任务
    public List<SuperviseEntity> importSuperviseTask(List<SuperviseEntity> superviseList);

    //导出督办任务
//    public Map<String,Object> getExportSuperviseList(SuperviseEntity entity);


}
