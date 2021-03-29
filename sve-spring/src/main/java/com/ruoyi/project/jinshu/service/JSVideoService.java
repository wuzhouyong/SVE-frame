package com.ruoyi.project.jinshu.service;

import com.ruoyi.project.jinshu.domain.JSNameIdEntity;
import com.ruoyi.project.jinshu.domain.JSVideoEntity;

import java.util.List;

public interface JSVideoService {

    public List<JSVideoEntity> selectVideo(JSVideoEntity entity);

    public List<JSNameIdEntity> selectVideoType();

    public int insertVideo(JSVideoEntity entity);

    public int updateVideo(JSVideoEntity entity);

    public int deleteVideo(String[] videoIds);

    public JSVideoEntity videoModel(String id);

}
