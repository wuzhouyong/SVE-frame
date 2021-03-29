package com.ruoyi.project.enterprise.service.impl;

import com.ruoyi.common.utils.IdUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.enterprise.domain.PfBasSource;
import com.ruoyi.project.enterprise.domain.user_task.BatchUserTaskModel;
import com.ruoyi.project.enterprise.domain.user_task.SysUserTask;
import com.ruoyi.project.enterprise.mapper.SysUserTaskMapper;
import com.ruoyi.project.enterprise.service.ISysUserTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author ruoyi
 * @date 2020-08-04
 */
@Service
@DataSource(value = DataSourceType.MASTER)
public class SysUserTaskServiceImpl implements ISysUserTaskService {
    @Autowired
    private SysUserTaskMapper sysUserTaskMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param userSourceId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public SysUserTask selectSysUserTaskById(String userSourceId) {
        return sysUserTaskMapper.selectSysUserTaskById(userSourceId);
    }

    //  查询平台企业名列表 左
    @Override
    public List<PfBasSource> selectPlatformLeftDate(SysUserTask sysUserTask) {
        return sysUserTaskMapper.selectPlatformLeftDate(sysUserTask);
    }

    //  查询平台企业名列表 右
    @Override
    public List<PfBasSource> selectPlatformRightDate(SysUserTask sysUserTask) {
        return sysUserTaskMapper.selectPlatformRightDate(sysUserTask);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param sysUserTask 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<SysUserTask> selectSysUserTaskList(SysUserTask sysUserTask) {
        return sysUserTaskMapper.selectSysUserTaskList(sysUserTask);
    }

    //新增关联关系
    @Override
    public int insertSysUserTask(SysUserTask sysUserTask) throws SQLException {
        String[] sourceIds = sysUserTask.getSourceIds();
        int row = 0;
        if (sourceIds != null && sourceIds.length != 0) {
            sysUserTask.setCreatedBy(SecurityUtils.getUsername());
            sysUserTask.setUpdatedBy(SecurityUtils.getUsername());
            for (int i = 0; i < sourceIds.length; i++) {
                //新增之前需要查询是否已经存在,如果存在就更新,如果不存在就新增
                sysUserTask.setIsDeleted(0l);
                sysUserTask.setSourceId(sourceIds[i]);
                int isAcc = sysUserTaskMapper.selectSysUserTaskCount(sysUserTask);
                if (isAcc > 0) { //存在就更新
                    sysUserTask.setUpdatedBy(SecurityUtils.getUsername());
                    sysUserTask.setDateUpdated(new Date());
                    sysUserTaskMapper.updateSysUserTask(sysUserTask);
                } else { //新增
                    sysUserTask.setUserSourceId(IdUtils.randomUUID());
                    sysUserTaskMapper.insertSysUserTask(sysUserTask);
                }
             row++;
            }
        } else throw new SQLException("请选择企业");
        return row;
    }

    //删除关联关系
    @Override
    public int removeSysUserTask(SysUserTask sysUserTask) {
        return sysUserTaskMapper.updateSysUserTask(sysUserTask);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param sysUserTask 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateSysUserTask(SysUserTask sysUserTask) {
        return sysUserTaskMapper.updateSysUserTask(sysUserTask);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param userSourceIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteSysUserTaskByIds(String[] userSourceIds) {
        return sysUserTaskMapper.deleteSysUserTaskByIds(userSourceIds);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param userSourceId 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteSysUserTaskById(String userSourceId) {
        return sysUserTaskMapper.deleteSysUserTaskById(userSourceId);
    }

    @Override
    public int batchUserTask(BatchUserTaskModel model) {
        if (model.getType() == 1) {
            return sysUserTaskMapper.addUserTaskByTagId(model);
        } else {
            return sysUserTaskMapper.removeUserTaskByTagId(model);
        }
    }
}
