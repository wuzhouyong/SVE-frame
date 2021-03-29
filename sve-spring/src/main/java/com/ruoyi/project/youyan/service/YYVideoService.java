package com.ruoyi.project.youyan.service;

import java.util.List;

import com.ruoyi.project.youyan.domain.YYNameIdEntity;
import com.ruoyi.project.youyan.domain.YYVideoEntity;

public interface YYVideoService {

    public List<YYVideoEntity> selectVideo(YYVideoEntity entity);

    public List<YYNameIdEntity> selectVideoType();

    public int insertVideo(YYVideoEntity entity);

    public int updateVideo(YYVideoEntity entity);

    public int deleteVideo(String[] videoIds);

    public YYVideoEntity videoModel(String id);

}
