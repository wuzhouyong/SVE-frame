package com.ruoyi.project.voc.mapper;

import com.ruoyi.project.voc.domain.VocDeleteEntity;
import com.ruoyi.project.voc.domain.VocNameIdEntity;
import com.ruoyi.project.voc.domain.VocVideoEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VocVideoMapper {

    public List<VocVideoEntity> selectVideo(VocVideoEntity entity);

    public int insertVideo(VocVideoEntity entity);

    public int updateVideo(VocVideoEntity entity);

    public int deleteVideo(VocDeleteEntity entity);

    public List<VocNameIdEntity> selectVideoType();

    public VocVideoEntity videoModel(@Param("id") String id);
}
