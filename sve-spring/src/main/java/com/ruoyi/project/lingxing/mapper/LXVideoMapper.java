package com.ruoyi.project.lingxing.mapper;

import com.ruoyi.project.lingxing.domain.LXDeleteEntity;
import com.ruoyi.project.lingxing.domain.LXNameIdEntity;
import com.ruoyi.project.lingxing.domain.LXVideoEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LXVideoMapper {

    public List<LXVideoEntity> selectVideo(LXVideoEntity entity);

    public int insertVideo(LXVideoEntity entity);

    public int updateVideo(LXVideoEntity entity);

    public int deleteVideo(LXDeleteEntity entity);

    public List<LXNameIdEntity> selectVideoType();

    public LXVideoEntity videoModel(@Param("id") String id);
}
