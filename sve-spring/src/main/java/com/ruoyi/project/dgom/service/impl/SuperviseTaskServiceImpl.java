package com.ruoyi.project.dgom.service.impl;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.dgom.domain.ExportSuperviseEntity;
import com.ruoyi.project.dgom.domain.SuperviseEntity;
import com.ruoyi.project.dgom.mapper.SuperviseTaskMapper;
import com.ruoyi.project.dgom.service.SuperviseTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author ruoyi
 * @date 2020-08-25
 */
@Service
@DataSource(value = DataSourceType.HBONLINE)
public class SuperviseTaskServiceImpl implements SuperviseTaskService {
    @Autowired
    private SuperviseTaskMapper taskMapper;

    //查询督办任务列表
    @Override
    public List<SuperviseEntity> selectSuperviseTaskList(SuperviseEntity entity) {
        return taskMapper.selectSuperviseTaskList(entity);
    }

    //查询分局处理列表
    @Override
    public List<SuperviseEntity> selectDivideTaskList(SuperviseEntity entity) {
        return taskMapper.selectDivideTaskList(entity);
    }

    //新增督办任务
    @Override
    @Transactional
    public ResultEntity insertSuperviseTask(SuperviseEntity entity) {
        if (entity.getTaskStatus() == null) return ResultEntity.error("督办状态不能为空");
        int isAcc = 0;
        if (entity.getTaskStatus() == 2 || entity.getTaskStatus() == 1) {
            //如果状态为1:草稿 保存 不生成结束和开始时间 ,可再次编辑
            //如果状态为2:待处理 提交 生成开始结束时间，不可再次编辑
            entity.setCreatedby(SecurityUtils.getUsername());
            entity.setUpdatedby(SecurityUtils.getUsername());
            if (entity.getTaskStatus() == 2) {
                Date beginTime = new Date();
                String end = DateUtils.getAddDay(beginTime, 3);
                Date endTime = DateUtils.getTime(end);
                entity.setStartDate(beginTime);
                entity.setEndDate(endTime);
            }
            isAcc = taskMapper.insertSuperviseTask(entity);
        }
        if (isAcc < 1) return ResultEntity.error("添加督办失败");
        return ResultEntity.success("成功添加" + isAcc + "条");
    }

    //修改督办任务，状态由1变成2,只能编辑状态为1的数据
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int editSuperviseTask(SuperviseEntity entity) throws SQLException {
        if (entity.getTaskStatus() == null) throw new SQLException("督办状态不能为空");
        SuperviseEntity entity_t = taskMapper.selectSuperviseTaskById(entity.getSuptaskId());
        if (entity_t.getTaskStatus() != 1) throw new SQLException("该任务不是草稿状态，不能编辑");
        int isAcc = 0;
        if (entity.getTaskStatus() == 2 || entity.getTaskStatus() == 1) {
            //如果状态为1:草稿 保存 不生成结束和开始时间 ,可再次编辑
            //如果状态为2:待处理 提交 生成开始结束时间，不可再次编辑
            entity.setCreatedby(SecurityUtils.getUsername());
            entity.setUpdatedby(SecurityUtils.getUsername());
            if (entity.getTaskStatus() == 2) {
                Date beginTime = new Date();
                String end = DateUtils.getAddDay(beginTime, 3);
                Date endTime = DateUtils.getTime(end);
                entity.setStartDate(beginTime);
                entity.setEndDate(endTime);
            }
            isAcc = taskMapper.editSuperviseTask(entity); //修改督办任务，状态由1变成2
        }
        if (isAcc < 1) throw new SQLException("修改督办任务失败");
        return isAcc;
    }

    //分局批量处理 状态2,3,5 变成4
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int editDivideTask(List<SuperviseEntity> entitys) throws SQLException {
        int count = 0;
        for (SuperviseEntity entity : entitys) {
            if (entity.getTaskStatus() == null) throw new SQLException("督办状态不能为空");
            SuperviseEntity entity_t = taskMapper.selectSuperviseTaskById(entity.getSuptaskId());
            List<Integer> list = Arrays.asList(new Integer[]{2, 3, 5});
            if (!list.contains(entity_t.getTaskStatus())) {
                throw new SQLException("该任务不是待处理或超时或值守退回，不能处理");
            }
            entity.setUpdatedby(SecurityUtils.getUsername());
            entity.setTaskStatus(4);
            int isAcc = taskMapper.editSuperviseTask(entity); //分局处理状态2,3,5 变成4
            if (isAcc < 1) throw new SQLException("修改督办任务失败");
            count++;
        }
        return count;
    }

    //市局批量处理 只修改状态前端传状态过来 驳回5 同意6
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int editMunicipalTask(List<SuperviseEntity> entitys) throws SQLException {
        int count = 0;
        for (SuperviseEntity entity : entitys) {
            if (entity.getTaskStatus() == null) throw new SQLException("督办状态不能为空");
            List<Integer> list = Arrays.asList(new Integer[]{5, 6});
            if (!list.contains(entity.getTaskStatus())) {
                throw new SQLException("该任务不是驳回或同意，不能处理");
            }
            entity.setUpdatedby(SecurityUtils.getUsername());
            int isAcc = taskMapper.editMunicipalTask(entity); //市局驳回5 同意6
            if (isAcc < 1) throw new SQLException("修改督办任务失败");
            count++;
        }
        return count;
    }

    //删除督办任务
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteSuperviseTaskByIds(Integer[] suptaskIds) throws SQLException {
        if (suptaskIds == null || suptaskIds.length == 0) {
            throw new SQLException("请选择督办任务");
        }
        String creatBy = SecurityUtils.getUsername();
        List<SuperviseEntity> list = taskMapper.selectSuperviseTaskByIds(suptaskIds);
        boolean b = list.stream().map(SuperviseEntity::getCreatedby).anyMatch(x -> !x.equals(creatBy));
        if (b) throw new SQLException("不同用户不能删除");
        int count = taskMapper.deleteSuperviseTaskByIds(suptaskIds, creatBy);
        if (count < 1) throw new SQLException("删除督办任务失败");
        return count;
    }

    @Override
    public List<SuperviseEntity> importSuperviseTask(List<SuperviseEntity> superviseList) {
        if (StringUtils.isNull(superviseList) || superviseList.size() == 0) {
            throw new CustomException("导入任务数据不能为空！");
        }
        List<SuperviseEntity> entities = superviseList.stream().filter(x -> StringUtils.isNotEmpty(x.getTownName())).collect(Collectors.toList());
        for (SuperviseEntity entity : entities) {
            if (StringUtils.isNotEmpty(entity.getTownName())) {
                Integer omtownId = taskMapper.selectTownIdByTownName(entity.getTownName());
                entity.setOmtownId(omtownId);
                Map map = taskMapper.selectTownCodeByOmtownId(omtownId); //查找中心平台id及名字
                if (map.get("TOWN_CODE") != null) entity.setUntownId(Integer.parseInt(map.get("TOWN_CODE").toString()));
                if (map.get("TOWN_NAME") != null) entity.setTownName(map.get("TOWN_NAME").toString());
            }
            entity.setTaskStatus(2);
            Date beginTime = new Date();
            String end = DateUtils.getAddDay(beginTime, 3);
            Date endTime = DateUtils.getTime(end);
            entity.setStartDate(beginTime);
            entity.setEndDate(endTime);
        }
        return entities;
    }

    //批量新增督办任务
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultEntity batchAddSuperviseTask(List<SuperviseEntity> list) throws SQLException {
        String creatBy = SecurityUtils.getUsername();
        int count = 0;
        for (SuperviseEntity entity : list) {
            entity.setCreatedby(creatBy);
            entity.setUpdatedby(creatBy);
            int isAcc = taskMapper.insertSuperviseTask(entity);
            if (isAcc < 1) throw new SQLException("新增督办任务失败");
            count++;
        }
        if (count < list.size()) {
            int i = list.size() - count;
            return ResultEntity.success(i + "条添加失败," + count + "条添加成功");
        }
        return ResultEntity.success("成功添加" + count + "条");
    }

//    @Override
//    public Map getExportSuperviseList(SuperviseEntity entity) {
//        List<SuperviseEntity> list = taskMapper.selectSuperviseTaskList(entity);
//        if (list == null || list.isEmpty()) return new HashMap();
//        for (SuperviseEntity s : list) { //转为中心平台的街镇名称
//            Map map = taskMapper.selectTownCodeByOmtownId(s.getOmtownId()); //查找中心平台id及名字
//            if (map.get("TOWN_NAME") != null) s.setTownName(map.get("TOWN_NAME").toString());
//        }
////        Map r_map = new HashMap();
////        Date minStartDate = list.stream().filter(x -> x.getStartDate() != null).map(SuperviseEntity::getStartDate).min(Date::compareTo).get();
////        Date maxEndDate = list.stream().filter(x -> x.getEndDate() != null).map(SuperviseEntity::getEndDate).max(Date::compareTo).get();
////        String min = DateUtils.parseDate(minStartDate, "yyyy年MM月dd日");
////        String max = DateUtils.parseDate(maxEndDate, "yyyy年MM月dd日");
////        r_map.put("min", min);
////        r_map.put("max", max);
////        List<ExportSuperviseEntity> supervise = list.stream().map(ExportSuperviseEntity::new).collect(Collectors.toList());
////        int count = 0;
////        for (ExportSuperviseEntity s : supervise) {
////            s.setCityName("东莞市");
////            count += 1;
////            s.setCount(count);
////        }
//        r_map.put("data", supervise);
//        return r_map;
//    }

}
