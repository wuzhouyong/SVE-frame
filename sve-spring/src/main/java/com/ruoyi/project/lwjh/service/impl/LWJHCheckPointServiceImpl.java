package com.ruoyi.project.lwjh.service.impl;

import java.util.List;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.lwjh.domain.CheckPointData;
import com.ruoyi.project.lwjh.domain.LWJHCheckPointElementEntity;
import com.ruoyi.project.lwjh.domain.LWJHCheckPointEntity;
import com.ruoyi.project.lwjh.mapper.LWJHCheckPointElementMapper;
import com.ruoyi.project.lwjh.mapper.LWJHCheckPointMapper;
import com.ruoyi.project.lwjh.service.LWJHCheckPointService;
import com.ruoyi.project.outwater.domain.entity.SqlInEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@DataSource(value = DataSourceType.LWJH)
@Service
public class LWJHCheckPointServiceImpl implements LWJHCheckPointService {

    @Autowired
    private LWJHCheckPointMapper checkPointMapper;

    @Autowired
    private LWJHCheckPointElementMapper elementMapper;

    @Override
    public ResultEntity selectCheckPoint(String sourceId) {
        List<LWJHCheckPointEntity> cpList = checkPointMapper.selectCheckPoint(new String[]{sourceId});
        for (int i = 0; i < cpList.size(); i++) {
            List<LWJHCheckPointElementEntity> eList = elementMapper.selectCheckPointElement(cpList.get(i).getCheckpointId());
            for (int j = 0; j < eList.size(); j++) {
                eList.get(j).setAccessDate(StringUtils.getYear(eList.get(j).getAccessDate()));
            }
            cpList.get(i).setElements(eList);
        }
        return ResultEntity.success(cpList);
    }

    @Override
    @Transactional
    public ResultEntity insertCheckPoint(LWJHCheckPointEntity entity) {
        String userName = SecurityUtils.getUsername();
        entity.setCheckpointId(java.util.UUID.randomUUID().toString());
        entity.setCreatedBy(userName);
        entity.setUpdatedBy(userName);
        int res = checkPointMapper.insertCheckPoint(entity);
        if (res == 1) {
            List<LWJHCheckPointElementEntity> cpElements = entity.getElements();
            for (int j = 0; j < cpElements.size(); j++) {
                LWJHCheckPointElementEntity eEntity = cpElements.get(j);
                eEntity.setCpelemId(java.util.UUID.randomUUID().toString());
                eEntity.setCheckpointId(entity.getCheckpointId());
                eEntity.setCreatedBy(userName);
                eEntity.setUpdatedBy(userName);
                res = elementMapper.insertCheckPointElement(eEntity);
            }
        }
        if (res == 1)
            return ResultEntity.success("数据添加成功");
        else
            return ResultEntity.error("数据添加失败");
    }

    @Override
    @Transactional
    public ResultEntity updateCheckPoint(LWJHCheckPointEntity entity) {
        int res = 0;
        String userName = SecurityUtils.getUsername();
        entity.setUpdatedBy(userName);
        List<LWJHCheckPointEntity> cpType = checkPointMapper.selectCheckPointType(entity.getCheckpointId());
        if (entity.getCheckpointType().equals(cpType.get(0).getCheckpointType())) {
            for (int i = 0; i < entity.getElements().size(); i++) {
                LWJHCheckPointElementEntity eEntity = entity.getElements().get(i);
                if (eEntity.getAccessDate().length() < 5) {
                    eEntity.setAccessDate(eEntity.getAccessDate() + "-01-01");
                }
                if (StringUtils.isEmpty(eEntity.getCpelemId()) && !StringUtils.isEmpty(eEntity.getLawStandard())) {
                    eEntity.setCpelemId(java.util.UUID.randomUUID().toString());
                    eEntity.setCheckpointId(entity.getCheckpointId());
                    eEntity.setCreatedBy(userName);
                    eEntity.setUpdatedBy(userName);
                    res = elementMapper.insertCheckPointElement(eEntity);
                } else if (!StringUtils.isEmpty(eEntity.getCpelemId()) && StringUtils.isEmpty(eEntity.getLawStandard())) {
                    eEntity.setUpdatedBy(userName);
                    res = elementMapper.removeCPE(eEntity);
                } else if (!StringUtils.isEmpty(eEntity.getCpelemId()) && !StringUtils.isEmpty(eEntity.getLawStandard())) {
                    eEntity.setUpdatedBy(userName);
                    res = elementMapper.updateCheckPointElement(eEntity);
                }
            }
            //List<LWJHCheckPointElementEntity> oldList = elementMapper.selectCheckPointElement(entity.getCheckpointId());
        } else {
            String[] cpIdArray = new String[]{entity.getCheckpointId()};
            res = elementMapper.removeCheckPointElement(new SqlInEntity(cpIdArray, userName));
            if (res > -1) {
                for (int i = 0; i < entity.getElements().size(); i++) {
                    LWJHCheckPointElementEntity eEntity = entity.getElements().get(i);
                    if (eEntity.getAccessDate().length() < 5) {
                        eEntity.setAccessDate(eEntity.getAccessDate() + "-1-1");
                    }
                    eEntity.setCpelemId(java.util.UUID.randomUUID().toString());
                    eEntity.setCheckpointId(entity.getCheckpointId());
                    eEntity.setCreatedBy(userName);
                    eEntity.setUpdatedBy(userName);
                    res = elementMapper.insertCheckPointElement(eEntity);
                }
            }
        }
        res = checkPointMapper.updateCheckPoint(entity);
        if (res > 0)
            return ResultEntity.success("数据修改成功");
        else
            return ResultEntity.error("数据修改失败");
    }

    @Override
    public ResultEntity removeCheckPoint(LWJHCheckPointEntity entity) {
        String userName = SecurityUtils.getUsername();
        entity.setUpdatedBy(userName);
        int res = checkPointMapper.removeCheckPoint(entity);
        if (res == 1) {
            String[] cpIdArray = new String[]{entity.getCheckpointId()};
            res = elementMapper.removeCheckPointElement(new SqlInEntity(cpIdArray, userName));
        }
        if (res > -1)
            return ResultEntity.success("数据删除成功");
        else
            return ResultEntity.error("数据删除失败");
    }

    @Override
    public List<CheckPointData> selectCheckPointData(String sourceId, String accessDate) {
        return checkPointMapper.selectCheckPointData(sourceId, accessDate);
    }


}
