package com.ruoyi.project.voc.service.impl;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.voc.domain.VocDeleteEntity;
import com.ruoyi.project.voc.domain.VocNameIdEntity;
import com.ruoyi.project.voc.domain.VocVideoEntity;
import com.ruoyi.project.voc.mapper.VocVideoMapper;
import com.ruoyi.project.voc.service.VocVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@DataSource(value = DataSourceType.VOC)
@Service
public class VocVideoServiceImpl implements VocVideoService {

    @Autowired
    private VocVideoMapper videoMapper;

    @Override
    public List<VocVideoEntity> selectVideo(VocVideoEntity entity) {
        return videoMapper.selectVideo(entity);
    }

    @Override
    public List<VocNameIdEntity> selectVideoType() {
        return videoMapper.selectVideoType();
    }

    @Override
    public int insertVideo(VocVideoEntity entity) {
        return videoMapper.insertVideo(entity);
    }

    @Override
    public int updateVideo(VocVideoEntity entity) {
        return videoMapper.updateVideo(entity);
    }

    @Override
    public int deleteVideo(String[] videoIds) {
        return videoMapper.deleteVideo(new VocDeleteEntity(videoIds, SecurityUtils.getUsername()));
    }

    @Override
    public VocVideoEntity videoModel(String id) {
        return videoMapper.videoModel(id);
    }
}
