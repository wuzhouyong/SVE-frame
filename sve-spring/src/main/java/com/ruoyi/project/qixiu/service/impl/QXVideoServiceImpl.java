package com.ruoyi.project.qixiu.service.impl;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.qixiu.domain.QXDeleteEntity;
import com.ruoyi.project.qixiu.domain.QXNameIdEntity;
import com.ruoyi.project.qixiu.domain.QXVideoEntity;
import com.ruoyi.project.qixiu.mapper.QXVideoMapper;
import com.ruoyi.project.qixiu.service.QXVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@DataSource(value = DataSourceType.QIXIU)
@Service
public class QXVideoServiceImpl implements QXVideoService {

    @Autowired
    private QXVideoMapper videoMapper;

    @Override
    public List<QXVideoEntity> selectVideo(QXVideoEntity entity) {
        return videoMapper.selectVideo(entity);
    }

    @Override
    public List<QXNameIdEntity> selectVideoType() {
        return videoMapper.selectVideoType();
    }

    @Override
    public int insertVideo(QXVideoEntity entity) {
        return videoMapper.insertVideo(entity);
    }

    @Override
    public int updateVideo(QXVideoEntity entity) {
        return videoMapper.updateVideo(entity);
    }

    @Override
    public int deleteVideo(String[] videoIds) {
        return videoMapper.deleteVideo(new QXDeleteEntity(videoIds, SecurityUtils.getUsername()));
    }

    @Override
    public QXVideoEntity videoModel(String id) {
        return videoMapper.videoModel(id);
    }
}
