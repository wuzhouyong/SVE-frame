package com.ruoyi.project.jinshu.mapper;

import com.ruoyi.project.jinshu.domain.JSDeleteEntity;
import com.ruoyi.project.jinshu.domain.JSNameIdEntity;
import com.ruoyi.project.jinshu.domain.JSVideoEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JSVideoMapper {

    public List<JSVideoEntity> selectVideo(JSVideoEntity entity);

    public int insertVideo(JSVideoEntity entity);

    public int updateVideo(JSVideoEntity entity);

    public int deleteVideo(JSDeleteEntity entity);

    public List<JSNameIdEntity> selectVideoType();

    public JSVideoEntity videoModel(@Param("id") String id);
}
