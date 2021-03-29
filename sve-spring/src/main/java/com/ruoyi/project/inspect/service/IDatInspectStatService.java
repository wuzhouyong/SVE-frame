package com.ruoyi.project.inspect.service;


import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.project.inspect.domain.DatInspectStat;
import com.ruoyi.project.inspect.domain.DatInspectParm;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IDatInspectStatService {

    List<DatInspectStat> importInspectStatTask(List<DatInspectStat> inspectList);
    ResultEntity batchAddInspectStatTask(List<DatInspectStat> inspectList) throws SQLException;
    ResultEntity addInspectStatTask(DatInspectStat inspectList) throws SQLException;
    List<DatInspectStat> selectInspectStatTaskList(DatInspectParm entity);
    int editInspectStatRecordTask(DatInspectStat entity) throws SQLException;
    int editInspectStatTask(DatInspectStat entity) throws SQLException;
    List<Map> selectStatrRecordTaskListById(Integer statId);
    int deleteInspectStatTaskById(Integer statId) throws SQLException;


}
