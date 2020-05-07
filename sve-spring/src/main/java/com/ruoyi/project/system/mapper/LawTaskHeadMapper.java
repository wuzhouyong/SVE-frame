package com.ruoyi.project.system.mapper;

import java.util.List;
import com.ruoyi.project.system.domain.LawTaskHead;

/**
 * 移动执法任务主Mapper接口
 * 
 * @author ruoyi
 * @date 2020-05-07
 */
public interface LawTaskHeadMapper 
{
    /**
     * 查询移动执法任务主
     * 
     * @param taskHeadId 移动执法任务主ID
     * @return 移动执法任务主
     */
    public LawTaskHead selectLawTaskHeadById(String taskHeadId);

    /**
     * 查询移动执法任务主列表
     * 
     * @param lawTaskHead 移动执法任务主
     * @return 移动执法任务主集合
     */
    public List<LawTaskHead> selectLawTaskHeadList(LawTaskHead lawTaskHead);

    /**
     * 新增移动执法任务主
     * 
     * @param lawTaskHead 移动执法任务主
     * @return 结果
     */
    public int insertLawTaskHead(LawTaskHead lawTaskHead);

    /**
     * 修改移动执法任务主
     * 
     * @param lawTaskHead 移动执法任务主
     * @return 结果
     */
    public int updateLawTaskHead(LawTaskHead lawTaskHead);

    /**
     * 删除移动执法任务主
     * 
     * @param taskHeadId 移动执法任务主ID
     * @return 结果
     */
    public int deleteLawTaskHeadById(String taskHeadId);

    /**
     * 批量删除移动执法任务主
     * 
     * @param taskHeadIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteLawTaskHeadByIds(String[] taskHeadIds);
}
