package com.ruoyi.project.jinshu.service.impl;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.jinshu.domain.JSDeleteEntity;
import com.ruoyi.project.jinshu.domain.JSNameIdEntity;
import com.ruoyi.project.jinshu.domain.JSVideoEntity;
import com.ruoyi.project.jinshu.mapper.JSVideoMapper;
import com.ruoyi.project.jinshu.service.JSVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@DataSource(value = DataSourceType.JINSHU)
@Service
public class JSVideoServiceImpl implements JSVideoService {

    @Autowired
    private JSVideoMapper videoMapper;

    @Override
    public List<JSVideoEntity> selectVideo(JSVideoEntity entity) {
        return videoMapper.selectVideo(entity);
    }

    @Override
    public List<JSNameIdEntity> selectVideoType() {
        return videoMapper.selectVideoType();
    }

    @Override
    public int insertVideo(JSVideoEntity entity) {
        return videoMapper.insertVideo(entity);
    }

    @Override
    public int updateVideo(JSVideoEntity entity) {
        return videoMapper.updateVideo(entity);
    }

    @Override
    public int deleteVideo(String[] videoIds) {
        return videoMapper.deleteVideo(new JSDeleteEntity(videoIds, SecurityUtils.getUsername()));
    }

    @Override
    public JSVideoEntity videoModel(String id) {
        return videoMapper.videoModel(id);
    }
}
