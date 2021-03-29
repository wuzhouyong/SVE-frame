package com.ruoyi.project.outwater.service.impl;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.outwater.domain.BasCheckPoint;
import com.ruoyi.project.outwater.domain.entity.BasCollectEntity;
import com.ruoyi.project.outwater.domain.entity.CheckPointElementEntity;
import com.ruoyi.project.outwater.domain.entity.CollectCheckPointEntity;
import com.ruoyi.project.outwater.domain.entity.NameAndIdEntity;
import com.ruoyi.project.outwater.mapper.BasCollectMapper;
import com.ruoyi.project.outwater.service.BasCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@DataSource(value = DataSourceType.OUTWATER)
@Service
public class BasCollectServiceImpl implements BasCollectService {

    @Autowired
    private BasCollectMapper basCollectMapper;

    @Override
    public int insertBasCollect(BasCollectEntity entity) {
        return basCollectMapper.insertBasCollect(entity);
    }

    @Override
    public int updateBasCollect(BasCollectEntity entity) {
        return basCollectMapper.updateBasCollect(entity);
    }

    @Override
    public String getSourceId(String collectId) {
        return basCollectMapper.getSourceId(collectId);
    }

    @Override
    public List<NameAndIdEntity> selectBasCollectIdNameList(String collectName, String collectId) {
        return basCollectMapper.selectBasCollectIdNameList(collectName, collectId);
    }

    @Override
    public int deleteBasCollectById(String collectId, String updatedBy) {
        return basCollectMapper.deleteBasCollectById(collectId, updatedBy);
    }

    @Override
    public int getTotal(BasCollectEntity entity) {
        return basCollectMapper.getTotal(entity);
    }

    @Override
    public List<BasCollectEntity> selectBasCollectList(BasCollectEntity entity) {
        return basCollectMapper.selectBasCollectList(entity);
    }

    @Override
    @Transactional
    public int insertData(CollectCheckPointEntity entity) {
        int res = 0;
        if (StringUtils.isEmpty(entity.getIpAddress())) {
            entity.setIpAddress("");
        }
        res += basCollectMapper.insertCollect(entity);
        res += basCollectMapper.insertBasCheckPoint(entity);
        if (entity.getCheckPointType().equals("1")) {
            res += basCollectMapper.insertCheckPointElementEa70(entity);
            res += basCollectMapper.insertCheckPointElementEa71(entity);
        } else {
            res += basCollectMapper.insertCheckPointElementEa73(entity);
            res += basCollectMapper.insertCheckPointElementEa74(entity);
        }
        res += basCollectMapper.insertCheckPointElementPs00(entity);
        return res;
    }

    @Override
    public int modifyPassWord(Map<String,String> param) {
        return basCollectMapper.modifyPassWord(param);
    }

    @Override
    @Transactional
    public int updateData(CollectCheckPointEntity entity) {
        int res = 0;
        List<BasCheckPoint> checkPoint = basCollectMapper.selectCheckPointType(entity.getCheckPointId());
        res += basCollectMapper.updateCollect(entity);
//        res += basCollectMapper.updateBasCheckPoint(entity);
        if (!checkPoint.get(0).getCheckPointType().equals(entity.getCheckPointType())) {
            List<CheckPointElementEntity> elementList = basCollectMapper.selectCheckPointElement(entity.getCheckPointId());
            for (int i = 0; i < elementList.size(); i++) {
                if (!elementList.get(i).getElemTypeId().equals("ps00")) {
                    CheckPointElementEntity cpeEntity = new CheckPointElementEntity();
                    if (entity.getCheckPointType().equals("1")) {
                        if (elementList.get(i).getElemTypeId().equals("ea73")) {
                            cpeEntity.setElemTypeId("ea70");
                            cpeEntity.setElemCode("ea7001");
                            cpeEntity.setElemName("污水监测井pH值");
                        } else if (elementList.get(i).getElemTypeId().equals("ea74")) {
                            cpeEntity.setElemTypeId("ea71");
                            cpeEntity.setElemCode("ea7101");
                            cpeEntity.setElemName("污水监测井电导率");
                        }
                    } else {
                        if (elementList.get(i).getElemTypeId().equals("ea70")) {
                            cpeEntity.setElemTypeId("ea73");
                            cpeEntity.setElemCode("ea7301");
                            cpeEntity.setElemName("雨水监测井pH值");
                        } else if (elementList.get(i).getElemTypeId().equals("ea71")) {
                            cpeEntity.setElemTypeId("ea74");
                            cpeEntity.setElemCode("ea7401");
                            cpeEntity.setElemName("雨水监测井电导率");
                        }
                    }
                    cpeEntity.setCpElementId(elementList.get(i).getCpElementId());
                    cpeEntity.setUpdatedBy(SecurityUtils.getUsername());
                    res += basCollectMapper.updateCheckPointElement(cpeEntity);
                }
            }
        }
        return res;
    }

    @Override
    public BasCollectEntity getMN() {
        return basCollectMapper.getMN();
    }

    @Override
    public List<BasCollectEntity> checkMN(String mn) {
        return basCollectMapper.checkMN(mn);
    }
}
