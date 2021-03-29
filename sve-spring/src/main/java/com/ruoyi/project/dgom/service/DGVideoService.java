package com.ruoyi.project.dgom.service;

import com.ruoyi.project.dgom.domain.DGVideoEntity;

import java.util.List;

public interface DGVideoService {

    public List<DGVideoEntity> selectVideo(DGVideoEntity entity);

    public int insertVideo(DGVideoEntity entity);

    public int updateVideo(DGVideoEntity entity);

    public int updateToken(DGVideoEntity entity);

    public int deleteVideo(String[] videoIds);

    public DGVideoEntity videoModel(String id);

}
