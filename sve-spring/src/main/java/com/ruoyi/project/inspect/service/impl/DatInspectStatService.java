package com.ruoyi.project.inspect.service.impl;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.HashMapUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.inspect.domain.DatInspectStat;
import com.ruoyi.project.inspect.domain.DatInspectParm;
import com.ruoyi.project.inspect.mapper.DatInspectStatMapper;
import com.ruoyi.project.inspect.service.IDatInspectStatService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class DatInspectStatService implements IDatInspectStatService {

    @Resource
    public DatInspectStatMapper mapper;

    public List<DatInspectStat> importInspectStatTask(List<DatInspectStat> inspectList) {
        if (StringUtils.isNull(inspectList) || inspectList.size() == 0) {
            throw new CustomException("导入任务数据不能为空！");
        }
        List<DatInspectStat> entities = inspectList.stream().filter(x -> StringUtils.isNotEmpty(x.getTownName())).collect(Collectors.toList());
        for (DatInspectStat entity : entities) {
            if (StringUtils.isNotEmpty(entity.getTownName())) {
                Map map = mapper.selectTownIdByTownName(entity.getTownName()); //中心平台
                if (map != null) {
                    entity.setTownCode(map.get("TOWN_CODE").toString());
                    entity.setTownName(map.get("TOWN_NAME").toString());
                }
            }
        }
        return entities;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultEntity batchAddInspectStatTask(List<DatInspectStat> list) throws SQLException {
        String creatBy = SecurityUtils.getUsername();
        int count = 0;
        for (DatInspectStat entity : list) {
            entity.setCreatedBy(creatBy);
            int isAcc = mapper.insertInspectStatTask(entity);
            if (isAcc < 1) throw new SQLException("新增巡检任务失败");
            count++;
        }
        if (count < list.size()) {
            int i = list.size() - count;
            return ResultEntity.success(i + "条添加失败," + count + "条添加成功");
        }
        return ResultEntity.success("成功添加" + count + "条");
    }

    @Override
    public ResultEntity addInspectStatTask(DatInspectStat entity) throws SQLException {
        String creatBy = SecurityUtils.getUsername();
        entity.setCreatedBy(creatBy);
        String townName = mapper.selectTownNameByTownCode(entity.getTownCode());
        if (townName != null) entity.setTownName(townName);
        int isAcc = mapper.insertInspectStatTask(entity);
        if (isAcc < 1) throw new SQLException("新增巡检任务失败");
        return ResultEntity.success("成功添加" + isAcc + "条");
    }

    @Override
    public List<DatInspectStat> selectInspectStatTaskList(DatInspectParm entity) {
        return mapper.selectInspectStatTaskList(entity);
    }

    @Override
    public int editInspectStatRecordTask(DatInspectStat entity) throws SQLException {
        entity.setCreatedBy(SecurityUtils.getUsername());
        int isAcc = mapper.editInspectStatRecordTask(entity);
        if (isAcc < 1) throw new SQLException("修改巡检任务失败");
        int count = mapper.addInspectStatRecord(entity);
        if (count < 1) throw new SQLException("新增巡检统计整改任务失败");
        return count;
    }

    @Override
    public int editInspectStatTask(DatInspectStat entity) throws SQLException {
        List<DatInspectStat> list = mapper.selectInspectStatTaskById(entity.getStatId());
        if (list.size() != 0) throw new SQLException("整改任务有历史记录,不能编辑");
        String townName = mapper.selectTownNameByTownCode(entity.getTownCode());
        if (townName != null) entity.setTownName(townName);
        entity.setCreatedBy(SecurityUtils.getUsername());
        int isAcc = mapper.editInspectStatTask(entity);
        if (isAcc < 1) throw new SQLException("修改巡检任务失败");
        return isAcc;
    }

    @Override
    public int deleteInspectStatTaskById(Integer statId) throws SQLException {
        List<DatInspectStat> list = mapper.selectInspectStatTaskById(statId);
        if (list.size() != 0) throw new SQLException("整改任务有历史记录,不能删除");
        String createdBy = SecurityUtils.getUsername();
        int isAcc = mapper.deleteInspectStatById(statId, createdBy);
        if (isAcc < 1) throw new SQLException("删除巡检任务失败");
        return isAcc;
    }

    @Override
    public List<Map> selectStatrRecordTaskListById(Integer statId) {
        List<Map> maps = mapper.selectStatrRecordTaskListById(statId);
        List<Map> mapList = HashMapUtils.toCamel(maps);
        for (Map noMap : mapList) {
            Set<Map.Entry<String, Object>> noEntries = noMap.entrySet();
            noEntries.stream().filter(e -> e.getValue() != null).forEach(e -> {
                if (e.getValue().getClass().getSimpleName().equals("Timestamp")) {
                    noMap.replace(e.getKey(), DateUtils.dateTime((Date) e.getValue()));
                }
            });
        }
        return mapList;
    }


}
