package com.ruoyi.project.jiayou.service.impl;

import java.util.List;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.jiayou.domain.JYDeleteEntity;
import com.ruoyi.project.jiayou.domain.JYNameIdEntity;
import com.ruoyi.project.jiayou.domain.JYVideoEntity;
import com.ruoyi.project.jiayou.mapper.JYVideoMapper;
import com.ruoyi.project.jiayou.service.JYVideoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@DataSource(value = DataSourceType.JIAYOU)
@Service
public class JYVideoServiceImpl implements JYVideoService {

    @Autowired
    private JYVideoMapper videoMapper;

    @Override
    public List<JYVideoEntity> selectVideo(JYVideoEntity entity) {
        return videoMapper.selectVideo(entity);
    }

    @Override
    public List<JYNameIdEntity> selectVideoType() {
        return videoMapper.selectVideoType();
    }

    @Override
    public int insertVideo(JYVideoEntity entity) {
        return videoMapper.insertVideo(entity);
    }

    @Override
    public int updateVideo(JYVideoEntity entity) {
        return videoMapper.updateVideo(entity);
    }

    @Override
    public int deleteVideo(String[] videoIds) {
        return videoMapper.deleteVideo(new JYDeleteEntity(videoIds, SecurityUtils.getUsername()));
    }

    @Override
    public JYVideoEntity videoModel(String id) {
        return videoMapper.videoModel(id);
    }
}
