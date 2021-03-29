package com.ruoyi.project.voc.service;

import java.util.List;
import com.ruoyi.project.voc.domain.VocBasTechnology;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2020-08-12
 */
public interface VocBasTechnologyService
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param techId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public VocBasTechnology selectBasSettletechnologyById(String techId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param basTechnology 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<VocBasTechnology> selectBasSettletechnologyList(VocBasTechnology basTechnology);

    /**
     * 新增【请填写功能名称】
     * 
     * @param basTechnology 【请填写功能名称】
     * @return 结果
     */
    public int insertBasSettletechnology(VocBasTechnology basTechnology);

    /**
     * 修改【请填写功能名称】
     * 
     * @param basTechnology 【请填写功能名称】
     * @return 结果
     */
    public int updateBasSettletechnology(VocBasTechnology basTechnology);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param techIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    public int deleteBasSettletechnologyByIds(String[] techIds);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param techId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteBasSettletechnologyById(String techId);
}
