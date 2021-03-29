package com.ruoyi.project.lwjh.service.impl;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.lwjh.domain.*;
import com.ruoyi.project.lwjh.mapper.LWJHCheckPointElementMapper;
import com.ruoyi.project.lwjh.mapper.LWJHCheckPointMapper;
import com.ruoyi.project.lwjh.mapper.LWJHSourceMapper;
import com.ruoyi.project.lwjh.service.LWJHSourceService;
import com.ruoyi.project.outwater.domain.entity.NameAndIdEntity;
import com.ruoyi.project.outwater.domain.entity.SqlInEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@DataSource(value = DataSourceType.LWJH)
@Service
public class LWJHSourceServiceImpl implements LWJHSourceService {

    @Autowired
    private LWJHSourceMapper sourceMapper;

    @Autowired
    private LWJHCheckPointMapper checkPointMapper;

    @Autowired
    private LWJHCheckPointElementMapper elementMapper;

    @Override
    public ResultEntity insertSource(SourceListEntity entity) {
        entity.setUserName(SecurityUtils.getUsername());
        entity.setSourceId(java.util.UUID.randomUUID().toString());
        int res = sourceMapper.insertSource(entity);
        if (res == 1)
            return ResultEntity.success("数据添加成功");
        else
            return ResultEntity.error("数据添加失败");
    }

    @Override
    public List<SourceListEntity> selectSource(SourceListEntity entity) {
        return sourceMapper.selectSource(entity);
    }

    @Override
    public int getTotal(LWJHSourceEntity entity) {
        return sourceMapper.getTotal(entity);
    }

    @Override
    public ResultEntity updateSource(SourceListEntity entity) {
        entity.setUserName(SecurityUtils.getUsername());
        int res = sourceMapper.updateSource(entity);
        if (res == 1)
            return ResultEntity.success("数据修改成功");
        else
            return ResultEntity.error("数据修改失败");
    }

    @Override
    public ResultEntity removeSource(String[] sourceIds) {
        String userName = SecurityUtils.getUsername();
        int res = sourceMapper.removeSource(new SqlInEntity(sourceIds, userName));
        if (res > 0) {
            List<LWJHCheckPointEntity> cpList = checkPointMapper.selectCheckPoint(sourceIds);
            res = checkPointMapper.removeCheckPointBySourceId(new SqlInEntity(sourceIds, userName));
            if (res > 0) {
                String[] cpIdArray = new String[cpList.size()];
                for (int i = 0; i < cpList.size(); i++) {
                    cpIdArray[i] = cpList.get(i).getCheckpointId();
                }
                res = elementMapper.removeCheckPointElement(new SqlInEntity(cpIdArray, userName));
            }
            if (res > -1)
                return ResultEntity.success("数据删除成功");
        }
        return ResultEntity.error("数据删除失败");
    }

    @Override
    public List<SourceData> selectSourceData(LWJHSourceEntity entity) {
        return sourceMapper.selectSourceData(entity);
    }

    @Override
    public List<SourceCityData> selectSourceCityData(LWJHSourceEntity entity) {
        return sourceMapper.selectSourceCityData(entity);
    }

    @Override
    public List<SourceListEntity> selectSourceInfo(SourceListEntity entity) {
        return sourceMapper.selectSourceInfo(entity);
    }

    @Override
    public List<NameAndIdEntity> selectSourceAddList(SourceListEntity entity) {
        return sourceMapper.selectSourceAddList(entity);
    }


}
