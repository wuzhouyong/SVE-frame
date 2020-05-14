package com.ruoyi.project.basecenter.mapper;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.basecenter.domain.PfBasSource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2020-05-09
 */

public interface PfBasSourceMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param sourceId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public PfBasSource selectPfBasSourceById(String sourceId);


    /**
     * 查询污染源公司列表    -----------886-------
     *
     * @param fullName 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    @DataSource(value = DataSourceType.SLAVE)
    public List<PfBasSource> selectPfBasSourceListByFullName(@Param(value="fullName")String fullName);
    /**
     * 查询污染源公司列表
     * 
     * @param pfBasSource 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    @DataSource(value = DataSourceType.SLAVE)
    public List<PfBasSource> selectPfBasSourceList(PfBasSource pfBasSource);

    /**
     * 新增【请填写功能名称】
     * 
     * @param pfBasSource 【请填写功能名称】
     * @return 结果
     */
    public int insertPfBasSource(PfBasSource pfBasSource);

    /**
     * 修改【请填写功能名称】
     * 
     * @param pfBasSource 【请填写功能名称】
     * @return 结果
     */
    public int updatePfBasSource(PfBasSource pfBasSource);

    /**
     * 删除【请填写功能名称】
     * 
     * @param sourceId 【请填写功能名称】ID
     * @return 结果
     */
    public int deletePfBasSourceById(String sourceId);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param sourceIds 需要删除的数据ID
     * @return 结果
     */
    public int deletePfBasSourceByIds(String[] sourceIds);


}
