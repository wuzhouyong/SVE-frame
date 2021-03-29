package com.ruoyi.project.enterprise.mapper;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.enterprise.domain.PfBasElementRef;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PfBasElementRefMapper {

    @DataSource(value = DataSourceType.MASTER)
    public int insertPfBasElementRef(PfBasElementRef entity);

    @DataSource(value = DataSourceType.MASTER)
    public int updatePfBasElementRef(PfBasElementRef entity);

    @DataSource(value = DataSourceType.MASTER)
    public int deletePfBasElementRef(@Param("tagHeadId") String tagHeadId, @Param("cpElemId") String cpElemId);

    @DataSource(value = DataSourceType.MASTER)
    public List<PfBasElementRef> selectPfBasElementRef(PfBasElementRef entity);
}
