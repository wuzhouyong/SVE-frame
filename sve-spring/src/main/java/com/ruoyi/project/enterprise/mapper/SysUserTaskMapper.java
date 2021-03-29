package com.ruoyi.project.enterprise.mapper;

import com.ruoyi.project.enterprise.domain.PfBasSource;
import com.ruoyi.project.enterprise.domain.user_task.BatchUserTaskModel;
import com.ruoyi.project.enterprise.domain.user_task.SysUserTask;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2020-08-04
 */
public interface SysUserTaskMapper 
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
    public int insertSysUserTask(SysUserTask sysUserTask);

    //删除关联关系
    public int updateSysUserTask(SysUserTask sysUserTask);

    //查询关联关系数量
    public int selectSysUserTaskCount(SysUserTask sysUserTask);

    /**
     * 删除【请填写功能名称】
     * 
     * @param userSourceId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteSysUserTaskById(String userSourceId);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param userSourceIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysUserTaskByIds(String[] userSourceIds);

    int addUserTaskByTagId(BatchUserTaskModel model);
    int removeUserTaskByTagId(BatchUserTaskModel model);
}
