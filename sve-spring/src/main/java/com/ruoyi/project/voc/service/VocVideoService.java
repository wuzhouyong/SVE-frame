package com.ruoyi.project.voc.service;

import com.ruoyi.project.voc.domain.VocNameIdEntity;
import com.ruoyi.project.voc.domain.VocVideoEntity;

import java.util.List;

public interface VocVideoService {

    public List<VocVideoEntity> selectVideo(VocVideoEntity entity);

    public List<VocNameIdEntity> selectVideoType();

    public int insertVideo(VocVideoEntity entity);

    public int updateVideo(VocVideoEntity entity);

    public int deleteVideo(String[] videoIds);

    public VocVideoEntity videoModel(String id);

}
