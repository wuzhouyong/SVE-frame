package com.ruoyi.project.guocheng.service.impl;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.guocheng.domain.DeleteEntity;
import com.ruoyi.project.guocheng.domain.GCNameIdEntity;
import com.ruoyi.project.guocheng.domain.GCVideoEntity;
import com.ruoyi.project.guocheng.mapper.VideoMapper;
import com.ruoyi.project.guocheng.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@DataSource(value = DataSourceType.GUOCHENG)
@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoMapper videoMapper;

    @Override
    public List<GCVideoEntity> selectVideo(GCVideoEntity entity) {
        return videoMapper.selectVideo(entity);
    }

    @Override
    public List<GCNameIdEntity> selectVideoType() {
        return videoMapper.selectVideoType();
    }

    @Override
    public int insertVideo(GCVideoEntity entity) {
        return videoMapper.insertVideo(entity);
    }

    @Override
    public int updateVideo(GCVideoEntity entity) {
        return videoMapper.updateVideo(entity);
    }

    @Override
    public int deleteVideo(String[] videoIds) {
        return videoMapper.deleteVideo(new DeleteEntity(videoIds, SecurityUtils.getUsername()));
    }

    @Override
    public GCVideoEntity videoModel(String id) {
        return videoMapper.videoModel(id);
    }
}
