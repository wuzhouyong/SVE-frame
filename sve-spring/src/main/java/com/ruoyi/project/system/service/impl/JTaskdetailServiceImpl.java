package com.ruoyi.project.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.JTaskdetailMapper;
import com.ruoyi.project.system.domain.JTaskdetail;
import com.ruoyi.project.system.service.IJTaskdetailService;

/**
 * 任务明细Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-05-12
 */
@Service
public class JTaskdetailServiceImpl implements IJTaskdetailService 
{
    @Autowired
    private JTaskdetailMapper jTaskdetailMapper;

    /**
     * 查询任务明细
     * 
     * @param ztaskdid 任务明细ID
     * @return 任务明细
     */
    @Override
    public JTaskdetail selectJTaskdetailById(Long ztaskdid)
    {
        return jTaskdetailMapper.selectJTaskdetailById(ztaskdid);
    }

    /**
     * 查询任务明细列表
     * 
     * @param jTaskdetail 任务明细
     * @return 任务明细
     */
    @Override
    public List<JTaskdetail> selectJTaskdetailList(JTaskdetail jTaskdetail)
    {
        return jTaskdetailMapper.selectJTaskdetailList(jTaskdetail);
    }

    /**
     * 新增任务明细
     * 
     * @param jTaskdetail 任务明细
     * @return 结果
     */
    @Override
    public int insertJTaskdetail(JTaskdetail jTaskdetail)
    {
        return jTaskdetailMapper.insertJTaskdetail(jTaskdetail);
    }

    /**
     * 修改任务明细
     * 
     * @param jTaskdetail 任务明细
     * @return 结果
     */
    @Override
    public int updateJTaskdetail(JTaskdetail jTaskdetail)
    {
        return jTaskdetailMapper.updateJTaskdetail(jTaskdetail);
    }

    /**
     * 批量删除任务明细
     * 
     * @param ztaskdids 需要删除的任务明细ID
     * @return 结果
     */
    @Override
    public int deleteJTaskdetailByIds(Long[] ztaskdids)
    {
        return jTaskdetailMapper.deleteJTaskdetailByIds(ztaskdids);
    }

    /**
     * 删除任务明细信息
     * 
     * @param ztaskdid 任务明细ID
     * @return 结果
     */
    @Override
    public int deleteJTaskdetailById(Long ztaskdid)
    {
        return jTaskdetailMapper.deleteJTaskdetailById(ztaskdid);
    }
}
