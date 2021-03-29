package com.ruoyi.project.enterprise.service;

import com.ruoyi.framework.web.domain.BaseEntity;

import java.util.List;
import java.util.Map;

public interface ITestScoreService {
    List<Map> getTownScore(BaseEntity entity);
    List<Map> getTownWaterTaskSummary();
    List<Map> getTownGasTaskSummary();
    List<Map> getTownAllTaskSummary();
}
