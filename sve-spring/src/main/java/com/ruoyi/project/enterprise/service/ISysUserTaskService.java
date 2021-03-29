package com.ruoyi.project.enterprise.service;

import com.ruoyi.project.enterprise.domain.PfBasSource;
import com.ruoyi.project.enterprise.domain.user_task.BatchUserTaskModel;
import com.ruoyi.project.enterprise.domain.user_task.SysUserTask;

import java.sql.SQLException;
import java.util.List;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2020-08-04
 */
public interface ISysUserTaskService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param userSourceId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public SysUserTask selectSysUserTaskById(String userSourceId);

    //  查询平台企业名列表 左
    public List<PfBasSource> selectPlatformLeftDate(SysUserTask sysUserTask);

    //  查询平台企业名列表 右
    public List<PfBasSource> selectPlatformRightDate(SysUserTask sysUserTask);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param sysUserTask 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<SysUserTask> selectSysUserTaskList(SysUserTask sysUserTask);

    //新增关联关系
    public int insertSysUserTask(SysUserTask sysUserTask) throws SQLException;

    //删除关联关系
    public int removeSysUserTask(SysUserTask sysUserTask);
    /**
     * 修改【请填写功能名称】
     * 
     * @param sysUserTask 【请填写功能名称】
     * @return 结果
     */
    public int updateSysUserTask(SysUserTask sysUserTask);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param userSourceIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    public int deleteSysUserTaskByIds(String[] userSourceIds);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param userSourceId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteSysUserTaskById(String userSourceId);

    int batchUserTask(BatchUserTaskModel model);
}
