package com.ruoyi.project.youyan.mapper;

import java.util.List;

import com.ruoyi.project.youyan.domain.YYDeleteEntity;
import com.ruoyi.project.youyan.domain.YYNameIdEntity;
import com.ruoyi.project.youyan.domain.YYVideoEntity;

import org.apache.ibatis.annotations.Param;

public interface YYVideoMapper {

    public List<YYVideoEntity> selectVideo(YYVideoEntity entity);

    public int insertVideo(YYVideoEntity entity);

    public int updateVideo(YYVideoEntity entity);

    public int deleteVideo(YYDeleteEntity entity);

    public List<YYNameIdEntity> selectVideoType();

    public YYVideoEntity videoModel(@Param("id") String id);
}
