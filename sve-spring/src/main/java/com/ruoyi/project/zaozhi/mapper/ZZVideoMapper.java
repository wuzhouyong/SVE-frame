package com.ruoyi.project.zaozhi.mapper;

import java.util.List;

import com.ruoyi.project.zaozhi.domain.ZZDeleteEntity;
import com.ruoyi.project.zaozhi.domain.ZZNameIdEntity;
import com.ruoyi.project.zaozhi.domain.ZZVideoEntity;

import org.apache.ibatis.annotations.Param;

public interface ZZVideoMapper {
    public List<ZZVideoEntity> selectVideo(ZZVideoEntity entity);

    public int insertVideo(ZZVideoEntity entity);

    public int updateVideo(ZZVideoEntity entity);

    public int deleteVideo(ZZDeleteEntity entity);

    public List<ZZNameIdEntity> selectVideoType();

    public ZZVideoEntity videoModel(@Param("id") String id);
}
