package com.ruoyi.project.system.service;

import java.util.List;
import com.ruoyi.project.system.domain.JTaskhead;

/**
 * 任务Service接口
 * 
 * @author ruoyi
 * @date 2020-05-12
 */
public interface IJTaskheadService 
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
     * 新增任务
     * 
     * @param jTaskhead 任务
     * @return 结果
     */
    public int insertJTaskhead(JTaskhead jTaskhead);

    // 新增任务及任务详情  ------886--------
    void insertJTaskheadAndJTaskDetail(JTaskhead jTaskhead) throws Exception;
    /**
     * 修改任务
     * 
     * @param jTaskhead 任务
     * @return 结果
     */
    public int updateJTaskhead(JTaskhead jTaskhead);

    /**
     * 批量删除任务
     * 
     * @param ztaskhids 需要删除的任务ID
     * @return 结果
     */
    public int deleteJTaskheadByIds(String[] ztaskhids);

    /**
     * 删除任务信息
     * 
     * @param ztaskhid 任务ID
     * @return 结果
     */
    public int deleteJTaskheadById(String ztaskhid);

}
