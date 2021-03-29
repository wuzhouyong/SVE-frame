package com.ruoyi.project.outwater.service;

import com.ruoyi.project.outwater.domain.entity.BasCollectEntity;
import com.ruoyi.project.outwater.domain.entity.CollectCheckPointEntity;
import com.ruoyi.project.outwater.domain.entity.NameAndIdEntity;

import java.util.List;
import java.util.Map;


public interface BasCollectService {
    
    public int insertBasCollect(BasCollectEntity entity);

    public int updateBasCollect(BasCollectEntity entity);

    public String getSourceId(String collectId);

    public List<NameAndIdEntity> selectBasCollectIdNameList(String collectName, String collectId);

    public int deleteBasCollectById (String collectId, String updatedBy);

    public int getTotal(BasCollectEntity entity);

    public List<BasCollectEntity> selectBasCollectList(BasCollectEntity entity);

    public int insertData(CollectCheckPointEntity entity);

    public int modifyPassWord(Map<String,String> param);

    public int updateData(CollectCheckPointEntity entity);

    public BasCollectEntity getMN();

    public List<BasCollectEntity> checkMN(String mn);
}
