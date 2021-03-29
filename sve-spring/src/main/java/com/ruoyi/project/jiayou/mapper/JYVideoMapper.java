package com.ruoyi.project.jiayou.mapper;

import com.ruoyi.project.jiayou.domain.JYDeleteEntity;
import com.ruoyi.project.jiayou.domain.JYNameIdEntity;
import com.ruoyi.project.jiayou.domain.JYVideoEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JYVideoMapper {

    public List<JYVideoEntity> selectVideo(JYVideoEntity entity);

    public int insertVideo(JYVideoEntity entity);

    public int updateVideo(JYVideoEntity entity);

    public int deleteVideo(JYDeleteEntity entity);

    public List<JYNameIdEntity> selectVideoType();

    public JYVideoEntity videoModel(@Param("id") String id);
}
