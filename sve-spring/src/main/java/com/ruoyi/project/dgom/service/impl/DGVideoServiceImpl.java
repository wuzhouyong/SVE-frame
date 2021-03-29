package com.ruoyi.project.dgom.service.impl;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.dgom.domain.DGVideoEntity;
import com.ruoyi.project.dgom.mapper.DGVideoMapper;
import com.ruoyi.project.dgom.service.DGVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@DataSource(value = DataSourceType.HBONLINE)
@Service
public class DGVideoServiceImpl implements DGVideoService {

    @Autowired
    private DGVideoMapper videoMapper;

    @Override
    public List<DGVideoEntity> selectVideo(DGVideoEntity entity) {
        return videoMapper.selectVideo(entity);
    }

    @Override
    public int insertVideo(DGVideoEntity entity) {
        return videoMapper.insertVideo(entity);
    }

    @Override
    public int updateVideo(DGVideoEntity entity) {
        return videoMapper.updateVideo(entity);
    }

    @Override
    public int updateToken(DGVideoEntity entity) {
        return videoMapper.updateToken(entity);
    }

    @Override
    public int deleteVideo(String[] videoIds) {
        return videoMapper.deleteVideo(videoIds, SecurityUtils.getUsername());
    }

    @Override
    public DGVideoEntity videoModel(String id) {
        return videoMapper.videoModel(id);
    }
}
