package com.ruoyi.project.basecenter.service;

import com.ruoyi.project.basecenter.domain.PfBasSource;

import java.util.List;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2020-05-09
 */
public interface IPfBasSourceService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param sourceId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public PfBasSource selectPfBasSourceById(String sourceId);

    /**
     * 通过名字模糊查询污染源公司列表  -----------886-------
     *
     * @param fullName 【公司模糊名字】
     * @return 【30名公司】集合
     */

   public List<PfBasSource> selectPfBasSourceListByFullName(String fullName);
    /**
     * 查询污染源公司列表
     * 
     * @param pfBasSource 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
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
     * 批量删除【请填写功能名称】
     * 
     * @param sourceIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    public int deletePfBasSourceByIds(String[] sourceIds);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param sourceId 【请填写功能名称】ID
     * @return 结果
     */
    public int deletePfBasSourceById(String sourceId);

}
