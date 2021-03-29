package com.ruoyi.project.youyan.service.impl;

import java.util.List;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.youyan.domain.YYDeleteEntity;
import com.ruoyi.project.youyan.domain.YYNameIdEntity;
import com.ruoyi.project.youyan.domain.YYVideoEntity;
import com.ruoyi.project.youyan.mapper.YYVideoMapper;
import com.ruoyi.project.youyan.service.YYVideoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@DataSource(value = DataSourceType.YOUYAN)
@Service
public class YYVideoServiceImpl implements YYVideoService {

    @Autowired
    private YYVideoMapper videoMapper;

    @Override
    public List<YYVideoEntity> selectVideo(YYVideoEntity entity) {
        return videoMapper.selectVideo(entity);
    }

    @Override
    public List<YYNameIdEntity> selectVideoType() {
        return videoMapper.selectVideoType();
    }

    @Override
    public int insertVideo(YYVideoEntity entity) {
        return videoMapper.insertVideo(entity);
    }

    @Override
    public int updateVideo(YYVideoEntity entity) {
        return videoMapper.updateVideo(entity);
    }

    @Override
    public int deleteVideo(String[] videoIds) {
        return videoMapper.deleteVideo(new YYDeleteEntity(videoIds, SecurityUtils.getUsername()));
    }

    @Override
    public YYVideoEntity videoModel(String id) {
        return videoMapper.videoModel(id);
    }
}
