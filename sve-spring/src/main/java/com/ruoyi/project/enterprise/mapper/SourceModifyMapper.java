package com.ruoyi.project.enterprise.mapper;

import com.ruoyi.project.enterprise.domain.SourceModifyEntity;
import com.ruoyi.project.enterprise.domain.SourceOtherColumn;
import com.ruoyi.project.enterprise.domain.SourceVocsParamEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SourceModifyMapper {

    public List<SourceModifyEntity> selectSourceModify(SourceModifyEntity entity);

    public List<SourceModifyEntity> selectModifyHistory(@Param("sourceId") String sourceId);

    String getOriginSourceName(@Param("sourceId") String sourceId);

    public int checkForPresence(String sourceId);

    public SourceModifyEntity getSourceEntity(@Param("sourceId") String sourceId);

    public int insertSourceModify(SourceModifyEntity entity);

    public int updateSourceModify(SourceModifyEntity entity);

    public int updateSourceUser(SourceModifyEntity entity);

    public int agree(SourceModifyEntity entity);

    public int updateModify(@Param("modifyId") String modifyId, @Param("updatedBy") String updatedBy);

    public int updateBasSource(SourceOtherColumn entity);

    public int updateVocs(SourceVocsParamEntity entity);
}
