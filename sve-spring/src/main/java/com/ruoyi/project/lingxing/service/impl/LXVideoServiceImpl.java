package com.ruoyi.project.lingxing.service.impl;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.lingxing.domain.LXDeleteEntity;
import com.ruoyi.project.lingxing.domain.LXNameIdEntity;
import com.ruoyi.project.lingxing.domain.LXVideoEntity;
import com.ruoyi.project.lingxing.mapper.LXVideoMapper;
import com.ruoyi.project.lingxing.service.LXVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@DataSource(value = DataSourceType.LINGXING)
@Service
public class LXVideoServiceImpl implements LXVideoService {

    @Autowired
    private LXVideoMapper videoMapper;

    @Override
    public List<LXVideoEntity> selectVideo(LXVideoEntity entity) {
        return videoMapper.selectVideo(entity);
    }

    @Override
    public List<LXNameIdEntity> selectVideoType() {
        return videoMapper.selectVideoType();
    }

    @Override
    public int insertVideo(LXVideoEntity entity) {
        return videoMapper.insertVideo(entity);
    }

    @Override
    public int updateVideo(LXVideoEntity entity) {
        return videoMapper.updateVideo(entity);
    }

    @Override
    public int deleteVideo(String[] videoIds) {
        return videoMapper.deleteVideo(new LXDeleteEntity(videoIds, SecurityUtils.getUsername()));
    }

    @Override
    public LXVideoEntity videoModel(String id) {
        return videoMapper.videoModel(id);
    }
}
