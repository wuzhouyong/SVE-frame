package com.ruoyi.project.system.service;

import java.util.List;
import com.ruoyi.project.system.domain.LawTaskDetail;

/**
 * 移动执法任务 - 明细Service接口
 * 
 * @author ruoyi
 * @date 2020-05-07
 */
public interface ILawTaskDetailService 
{
    /**
     * 查询移动执法任务 - 明细
     * 
     * @param taskDetailId 移动执法任务 - 明细ID
     * @return 移动执法任务 - 明细
     */
    public LawTaskDetail selectLawTaskDetailById(String taskDetailId);

    /**
     * 查询移动执法任务 - 明细列表
     * 
     * @param lawTaskDetail 移动执法任务 - 明细
     * @return 移动执法任务 - 明细集合
     */
    public List<LawTaskDetail> selectLawTaskDetailList(LawTaskDetail lawTaskDetail);

    /**
     * 新增移动执法任务 - 明细
     * 
     * @param lawTaskDetail 移动执法任务 - 明细
     * @return 结果
     */
    public int insertLawTaskDetail(LawTaskDetail lawTaskDetail);

    /**
     * 修改移动执法任务 - 明细
     * 
     * @param lawTaskDetail 移动执法任务 - 明细
     * @return 结果
     */
    public int updateLawTaskDetail(LawTaskDetail lawTaskDetail);

    /**
     * 批量删除移动执法任务 - 明细
     * 
     * @param taskDetailIds 需要删除的移动执法任务 - 明细ID
     * @return 结果
     */
    public int deleteLawTaskDetailByIds(String[] taskDetailIds);

    /**
     * 删除移动执法任务 - 明细信息
     * 
     * @param taskDetailId 移动执法任务 - 明细ID
     * @return 结果
     */
    public int deleteLawTaskDetailById(String taskDetailId);

    //查询所有 移动执法任务的数据
    List<LawTaskDetail> selectLawTaskDetailALL();
}
