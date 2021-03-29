package com.ruoyi.project.dgom.mapper;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.dgom.domain.DGVideoEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@DataSource(value = DataSourceType.HBONLINE)
public interface DGVideoMapper {

    public List<DGVideoEntity> selectVideo(DGVideoEntity entity);

    public int insertVideo(DGVideoEntity entity);

    public int updateVideo(DGVideoEntity entity);

    public int updateToken(DGVideoEntity entity);

    public int deleteVideo(@Param("videoIds") String[] videoIds, @Param("userName") String userName);

    public DGVideoEntity videoModel(@Param("id") String id);
}
