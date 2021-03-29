package com.ruoyi.project.zaozhi.service.impl;

import java.util.List;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.zaozhi.domain.ZZDeleteEntity;
import com.ruoyi.project.zaozhi.domain.ZZNameIdEntity;
import com.ruoyi.project.zaozhi.domain.ZZVideoEntity;
import com.ruoyi.project.zaozhi.mapper.ZZVideoMapper;
import com.ruoyi.project.zaozhi.service.ZZVideoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@DataSource(value = DataSourceType.ZAOZHI)
@Service
public class ZZVideoServiceImpl implements ZZVideoService {
    @Autowired
    private ZZVideoMapper videoMapper;

    @Override
    public List<ZZVideoEntity> selectVideo(ZZVideoEntity entity) {
        return videoMapper.selectVideo(entity);
    }

    @Override
    public List<ZZNameIdEntity> selectVideoType() {
        return videoMapper.selectVideoType();
    }


    @Override
    public int insertVideo(ZZVideoEntity entity) {
        return videoMapper.insertVideo(entity);
    }

    @Override
    public int updateVideo(ZZVideoEntity entity) {
        return videoMapper.updateVideo(entity);
    }

    @Override
    public int deleteVideo(String[] videoIds) {
        return videoMapper.deleteVideo(new ZZDeleteEntity(videoIds, SecurityUtils.getUsername()));
    }

    @Override
    public ZZVideoEntity videoModel(String id) {
        return videoMapper.videoModel(id);
    }
}
