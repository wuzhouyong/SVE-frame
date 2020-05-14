package com.ruoyi.project.basecenter.service.impl;

import java.util.List;

import com.ruoyi.project.basecenter.domain.PfBasSource;
import com.ruoyi.project.basecenter.mapper.PfBasSourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.basecenter.service.IPfBasSourceService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-05-09
 */
@Service
public class PfBasSourceServiceImpl implements IPfBasSourceService 
{
    @Autowired
    private PfBasSourceMapper pfBasSourceMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param sourceId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public PfBasSource selectPfBasSourceById(String sourceId)
    {
        return pfBasSourceMapper.selectPfBasSourceById(sourceId);
    }

    /**
     * 查询污染源公司列表    -----------886-------
     *
     * @param fullName 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<PfBasSource> selectPfBasSourceListByFullName(String fullName)
    {
        return pfBasSourceMapper.selectPfBasSourceListByFullName(fullName);
    }


    /**
     * 查询污染源公司列表
     * 
     * @param pfBasSource 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<PfBasSource> selectPfBasSourceList(PfBasSource pfBasSource)
    {
        return pfBasSourceMapper.selectPfBasSourceList(pfBasSource);
    }


    /**
     * 新增【请填写功能名称】
     * 
     * @param pfBasSource 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertPfBasSource(PfBasSource pfBasSource)
    {
        return pfBasSourceMapper.insertPfBasSource(pfBasSource);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param pfBasSource 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updatePfBasSource(PfBasSource pfBasSource)
    {
        return pfBasSourceMapper.updatePfBasSource(pfBasSource);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param sourceIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deletePfBasSourceByIds(String[] sourceIds)
    {
        return pfBasSourceMapper.deletePfBasSourceByIds(sourceIds);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param sourceId 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deletePfBasSourceById(String sourceId)
    {
        return pfBasSourceMapper.deletePfBasSourceById(sourceId);
    }
}
