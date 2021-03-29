package com.ruoyi.project.zaozhi.service;

import java.util.List;

import com.ruoyi.project.zaozhi.domain.ZZNameIdEntity;
import com.ruoyi.project.zaozhi.domain.ZZVideoEntity;

public interface ZZVideoService {
    public List<ZZVideoEntity> selectVideo(ZZVideoEntity entity);

    public List<ZZNameIdEntity> selectVideoType();

    public int insertVideo(ZZVideoEntity entity);

    public int updateVideo(ZZVideoEntity entity);

    public int deleteVideo(String[] videoIds);

    public ZZVideoEntity videoModel(String id);
}
