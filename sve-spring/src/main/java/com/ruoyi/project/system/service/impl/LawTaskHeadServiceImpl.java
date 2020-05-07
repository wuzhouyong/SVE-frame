package com.ruoyi.project.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.LawTaskHeadMapper;
import com.ruoyi.project.system.domain.LawTaskHead;
import com.ruoyi.project.system.service.ILawTaskHeadService;

/**
 * 移动执法任务主Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-05-07
 */
@Service
public class LawTaskHeadServiceImpl implements ILawTaskHeadService 
{
    @Autowired
    private LawTaskHeadMapper lawTaskHeadMapper;

    /**
     * 查询移动执法任务主
     * 
     * @param taskHeadId 移动执法任务主ID
     * @return 移动执法任务主
     */
    @Override
    public LawTaskHead selectLawTaskHeadById(String taskHeadId)
    {
        return lawTaskHeadMapper.selectLawTaskHeadById(taskHeadId);
    }

    /**
     * 查询移动执法任务主列表
     * 
     * @param lawTaskHead 移动执法任务主
     * @return 移动执法任务主
     */
    @Override
    public List<LawTaskHead> selectLawTaskHeadList(LawTaskHead lawTaskHead)
    {
        return lawTaskHeadMapper.selectLawTaskHeadList(lawTaskHead);
    }

    /**
     * 新增移动执法任务主
     * 
     * @param lawTaskHead 移动执法任务主
     * @return 结果
     */
    @Override
    public int insertLawTaskHead(LawTaskHead lawTaskHead)
    {
        return lawTaskHeadMapper.insertLawTaskHead(lawTaskHead);
    }

    /**
     * 修改移动执法任务主
     * 
     * @param lawTaskHead 移动执法任务主
     * @return 结果
     */
    @Override
    public int updateLawTaskHead(LawTaskHead lawTaskHead)
    {
        return lawTaskHeadMapper.updateLawTaskHead(lawTaskHead);
    }

    /**
     * 批量删除移动执法任务主
     * 
     * @param taskHeadIds 需要删除的移动执法任务主ID
     * @return 结果
     */
    @Override
    public int deleteLawTaskHeadByIds(String[] taskHeadIds)
    {
        return lawTaskHeadMapper.deleteLawTaskHeadByIds(taskHeadIds);
    }

    /**
     * 删除移动执法任务主信息
     * 
     * @param taskHeadId 移动执法任务主ID
     * @return 结果
     */
    @Override
    public int deleteLawTaskHeadById(String taskHeadId)
    {
        return lawTaskHeadMapper.deleteLawTaskHeadById(taskHeadId);
    }
}
