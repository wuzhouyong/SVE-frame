package com.ruoyi.project.guocheng.service;

import com.ruoyi.project.guocheng.domain.GCNameIdEntity;
import com.ruoyi.project.guocheng.domain.GCVideoEntity;

import java.util.List;

public interface VideoService {

    public List<GCVideoEntity> selectVideo(GCVideoEntity entity);

    public List<GCNameIdEntity> selectVideoType();

    public int insertVideo(GCVideoEntity entity);

    public int updateVideo(GCVideoEntity entity);

    public int deleteVideo(String[] videoIds);

    public GCVideoEntity videoModel(String id);

}
