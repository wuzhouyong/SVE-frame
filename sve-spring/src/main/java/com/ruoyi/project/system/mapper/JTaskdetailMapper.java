package com.ruoyi.project.system.mapper;

import java.util.List;
import com.ruoyi.project.system.domain.JTaskdetail;

/**
 * 任务明细Mapper接口
 * 
 * @author ruoyi
 * @date 2020-05-12
 */
public interface JTaskdetailMapper 
{
    /**
     * 查询任务明细
     * 
     * @param ztaskdid 任务明细ID
     * @return 任务明细
     */
    public JTaskdetail selectJTaskdetailById(Long ztaskdid);

    /**
     * 查询任务明细列表
     * 
     * @param jTaskdetail 任务明细
     * @return 任务明细集合
     */
    public List<JTaskdetail> selectJTaskdetailList(JTaskdetail jTaskdetail);

    /**
     * 新增任务明细     -------------886-----------
     * 
     * @param jTaskdetail 任务明细
     * @return 结果
     */
    public int insertJTaskdetail(JTaskdetail jTaskdetail);

    /**
     * 修改任务明细
     * 
     * @param jTaskdetail 任务明细
     * @return 结果
     */
    public int updateJTaskdetail(JTaskdetail jTaskdetail);

    /**
     * 删除任务明细
     * 
     * @param ztaskdid 任务明细ID
     * @return 结果
     */
    public int deleteJTaskdetailById(Long ztaskdid);

    /**
     * 批量删除任务明细
     * 
     * @param ztaskdids 需要删除的数据ID
     * @return 结果
     */
    public int deleteJTaskdetailByIds(Long[] ztaskdids);
}
