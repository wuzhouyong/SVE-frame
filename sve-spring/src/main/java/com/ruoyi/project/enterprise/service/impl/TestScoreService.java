package com.ruoyi.project.enterprise.service.impl;

import com.ruoyi.common.utils.HashMapUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.framework.web.domain.BaseEntity;
import com.ruoyi.project.enterprise.mapper.TestScoreMapper;
import com.ruoyi.project.enterprise.service.ITestScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
@DataSource(value = DataSourceType.MASTER)
public class TestScoreService implements ITestScoreService {
    @Autowired
    private TestScoreMapper mapper;

    @Override
    public List<Map> getTownScore(BaseEntity entity) {
        return HashMapUtils.toLower(mapper.getTownScore(entity));
    }

    @Override
    public List<Map> getTownWaterTaskSummary() {
        List<Map> list = HashMapUtils.toLower(mapper.getTownWaterTaskSummary());
        list.stream().forEach(x -> {
            if (x.get("rate") != null) {
                BigDecimal a = (BigDecimal) x.get("rate");
                String rate = a.multiply(new BigDecimal(100)).stripTrailingZeros().toPlainString();
                x.replace("rate", new BigDecimal(rate));
            }
        });
        return list;
    }

    @Override
    public List<Map> getTownGasTaskSummary() {
        List<Map> list = HashMapUtils.toLower(mapper.getTownGasTaskSummary());
        list.stream().forEach(x -> {
            if (x.get("rate") != null) {
                BigDecimal a = (BigDecimal) x.get("rate");
                String rate = a.multiply(new BigDecimal(100)).stripTrailingZeros().toPlainString();
                x.replace("rate", new BigDecimal(rate));
            }
        });
        return list;
    }

    @Override
    public List<Map> getTownAllTaskSummary() {
        List<Map> list = HashMapUtils.toLower(mapper.getTownAllTaskSummary());
        Map total = HashMapUtils.toLower(mapper.getTownTotalSummary());
        list.add(total);
        list.stream().forEach(x -> {
            if (x.get("rate") != null) {
                BigDecimal a = (BigDecimal) x.get("rate");
                String rate = a.multiply(new BigDecimal(100)).stripTrailingZeros().toPlainString();
                x.replace("rate", new BigDecimal(rate));
            }
        });
        return list;
    }
}
