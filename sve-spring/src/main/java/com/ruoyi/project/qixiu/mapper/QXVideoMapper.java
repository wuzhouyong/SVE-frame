package com.ruoyi.project.qixiu.mapper;

import com.ruoyi.project.qixiu.domain.QXDeleteEntity;
import com.ruoyi.project.qixiu.domain.QXNameIdEntity;
import com.ruoyi.project.qixiu.domain.QXVideoEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QXVideoMapper {

    public List<QXVideoEntity> selectVideo(QXVideoEntity entity);

    public int insertVideo(QXVideoEntity entity);

    public int updateVideo(QXVideoEntity entity);

    public int deleteVideo(QXDeleteEntity entity);

    public List<QXNameIdEntity> selectVideoType();

    public QXVideoEntity videoModel(@Param("id") String id);
}
