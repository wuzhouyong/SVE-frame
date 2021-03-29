package com.ruoyi.project.jiayou.service;

import com.ruoyi.project.jiayou.domain.JYNameIdEntity;
import com.ruoyi.project.jiayou.domain.JYVideoEntity;

import java.util.List;

public interface JYVideoService {

    public List<JYVideoEntity> selectVideo(JYVideoEntity entity);

    public List<JYNameIdEntity> selectVideoType();

    public int insertVideo(JYVideoEntity entity);

    public int updateVideo(JYVideoEntity entity);

    public int deleteVideo(String[] videoIds);

    public JYVideoEntity videoModel(String id);

}
