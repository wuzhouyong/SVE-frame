package com.ruoyi.project.enterprise.mapper;

import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TestScoreMapper {
    List<Map> getTownScore(BaseEntity entity);
    List<Map> getTownWaterTaskSummary();
    List<Map> getTownGasTaskSummary();
    List<Map> getTownAllTaskSummary();
    Map getTownTotalSummary();
}
