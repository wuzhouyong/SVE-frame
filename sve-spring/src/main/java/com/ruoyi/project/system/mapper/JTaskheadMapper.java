package com.ruoyi.project.system.mapper;

import java.util.List;
import com.ruoyi.project.system.domain.JTaskhead;

/**
 * 任务Mapper接口
 * 
 * @author ruoyi
 * @date 2020-05-12
 */
public interface JTaskheadMapper 
{
    /**
     * 查询任务
     * 
     * @param ztaskhid 任务ID
     * @return 任务
     */
    public JTaskhead selectJTaskheadById(String ztaskhid);

    /**
     * 查询任务列表
     * 
     * @param jTaskhead 任务
     * @return 任务集合
     */
    public List<JTaskhead> selectJTaskheadList(JTaskhead jTaskhead);

    /**
     * 新增任务           -------------886---------
     * 
     * @param jTaskhead 任务
     * @return 结果
     */
    public int insertJTaskhead(JTaskhead jTaskhead);

    /**
     * 修改任务
     * 
     * @param jTaskhead 任务
     * @return 结果
     */
    public int updateJTaskhead(JTaskhead jTaskhead);

    /**
     * 删除任务
     * 
     * @param ztaskhid 任务ID
     * @return 结果
     */
    public int deleteJTaskheadById(String ztaskhid);

    /**
     * 批量删除任务
     * 
     * @param ztaskhids 需要删除的数据ID
     * @return 结果
     */
    public int deleteJTaskheadByIds(String[] ztaskhids);
}
