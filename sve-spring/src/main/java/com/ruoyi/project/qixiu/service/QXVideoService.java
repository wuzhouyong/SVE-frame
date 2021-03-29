package com.ruoyi.project.qixiu.service;

import com.ruoyi.project.qixiu.domain.QXNameIdEntity;
import com.ruoyi.project.qixiu.domain.QXVideoEntity;

import java.util.List;

public interface QXVideoService {

    public List<QXVideoEntity> selectVideo(QXVideoEntity entity);

    public List<QXNameIdEntity> selectVideoType();

    public int insertVideo(QXVideoEntity entity);

    public int updateVideo(QXVideoEntity entity);

    public int deleteVideo(String[] videoIds);

    public QXVideoEntity videoModel(String id);

}
