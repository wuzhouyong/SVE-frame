package com.ruoyi.project.guocheng.mapper;

import com.ruoyi.project.guocheng.domain.DeleteEntity;
import com.ruoyi.project.guocheng.domain.GCNameIdEntity;
import com.ruoyi.project.guocheng.domain.GCVideoEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VideoMapper {

    public List<GCVideoEntity> selectVideo(GCVideoEntity entity);

    public int insertVideo(GCVideoEntity entity);

    public int updateVideo(GCVideoEntity entity);

    public int deleteVideo(DeleteEntity entity);

    public List<GCNameIdEntity> selectVideoType();

    public GCVideoEntity videoModel(@Param("id") String id);
}
