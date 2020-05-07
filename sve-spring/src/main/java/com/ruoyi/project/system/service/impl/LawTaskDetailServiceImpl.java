package com.ruoyi.project.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.LawTaskDetailMapper;
import com.ruoyi.project.system.domain.LawTaskDetail;
import com.ruoyi.project.system.service.ILawTaskDetailService;

/**
 * 移动执法任务 - 明细Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-05-07
 */
@Service
public class LawTaskDetailServiceImpl implements ILawTaskDetailService 
{
    @Autowired
    private LawTaskDetailMapper lawTaskDetailMapper;

    /**
     * 查询移动执法任务 - 明细
     * 
     * @param taskDetailId 移动执法任务 - 明细ID
     * @return 移动执法任务 - 明细
     */
    @Override
    public LawTaskDetail selectLawTaskDetailById(String taskDetailId)
    {
        return lawTaskDetailMapper.selectLawTaskDetailById(taskDetailId);
    }

    /**
     * 查询移动执法任务 - 明细列表
     * 
     * @param lawTaskDetail 移动执法任务 - 明细
     * @return 移动执法任务 - 明细
     */
    @Override
    public List<LawTaskDetail> selectLawTaskDetailList(LawTaskDetail lawTaskDetail)
    {
        return lawTaskDetailMapper.selectLawTaskDetailList(lawTaskDetail);
    }

    /**
     * 新增移动执法任务 - 明细
     * 
     * @param lawTaskDetail 移动执法任务 - 明细
     * @return 结果
     */
    @Override
    public int insertLawTaskDetail(LawTaskDetail lawTaskDetail)
    {
        return lawTaskDetailMapper.insertLawTaskDetail(lawTaskDetail);
    }

    /**
     * 修改移动执法任务 - 明细
     * 
     * @param lawTaskDetail 移动执法任务 - 明细
     * @return 结果
     */
    @Override
    public int updateLawTaskDetail(LawTaskDetail lawTaskDetail)
    {
        return lawTaskDetailMapper.updateLawTaskDetail(lawTaskDetail);
    }

    /**
     * 批量删除移动执法任务 - 明细
     * 
     * @param taskDetailIds 需要删除的移动执法任务 - 明细ID
     * @return 结果
     */
    @Override
    public int deleteLawTaskDetailByIds(String[] taskDetailIds)
    {
        return lawTaskDetailMapper.deleteLawTaskDetailByIds(taskDetailIds);
    }

    /**
     * 删除移动执法任务 - 明细信息
     * 
     * @param taskDetailId 移动执法任务 - 明细ID
     * @return 结果
     */
    @Override
    public int deleteLawTaskDetailById(String taskDetailId)
    {
        return lawTaskDetailMapper.deleteLawTaskDetailById(taskDetailId);
    }

    @Override    //查询所有移动执法任务的数据
    public List<LawTaskDetail> selectLawTaskDetailALL() {
             //查询所有移动执法任务的数据
        return lawTaskDetailMapper.selectLawTaskDetailALL();
    }
}
