package com.ruoyi.project.lingxing.service;

import com.ruoyi.project.lingxing.domain.LXNameIdEntity;
import com.ruoyi.project.lingxing.domain.LXVideoEntity;

import java.util.List;

public interface LXVideoService {

    public List<LXVideoEntity> selectVideo(LXVideoEntity entity);

    public List<LXNameIdEntity> selectVideoType();

    public int insertVideo(LXVideoEntity entity);

    public int updateVideo(LXVideoEntity entity);

    public int deleteVideo(String[] videoIds);

    public LXVideoEntity videoModel(String id);

}
