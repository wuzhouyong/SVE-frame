package com.ruoyi.project.system.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.security.LoginUser;
import com.ruoyi.project.system.domain.JTaskdetail;
import com.ruoyi.project.system.domain.vo.TaskVo;
import com.ruoyi.project.system.mapper.JTaskdetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.JTaskheadMapper;
import com.ruoyi.project.system.domain.JTaskhead;
import com.ruoyi.project.system.service.IJTaskheadService;
import org.springframework.transaction.annotation.Transactional;


/**
 * 任务Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-05-12
 */
@Service
public class JTaskheadServiceImpl implements IJTaskheadService 
{
    @Autowired
    private JTaskheadMapper jTaskheadMapper;

    @Autowired
    private JTaskdetailMapper jTaskdetailMapper;
    /**
     * 查询任务
     * 
     * @param ztaskhid 任务ID
     * @return 任务
     */
    @Override
    public JTaskhead selectJTaskheadById(String ztaskhid)
    {
        return jTaskheadMapper.selectJTaskheadById(ztaskhid);
    }

    /**
     * 查询任务列表
     * 
     * @param jTaskhead 任务
     * @return 任务
     */
    @Override
    public List<JTaskhead> selectJTaskheadList(JTaskhead jTaskhead)
    {
        return jTaskheadMapper.selectJTaskheadList(jTaskhead);
    }

    /**
     * 新增任务
     * @param jTaskhead 任务
     * @return 结果
     */
    @Override
    public int insertJTaskhead(JTaskhead jTaskhead)
    {
        return jTaskheadMapper.insertJTaskhead(jTaskhead);
    }

    //新增任务及任务详情  ------886--------
    @Transactional
    public void insertJTaskheadAndJTaskDetail(JTaskhead jTaskhead) throws Exception {

        LoginUser loginUser = SecurityUtils.getLoginUser();
        // 1.如果新增任务失败，抛自定义异常给controller，controller在把这异常抛给前端，后续任务详情就不在处理
        jTaskhead.setZtaskstatus(1);
        jTaskhead.setZtaskdate(new Date());
        String userId = String.valueOf(loginUser.getUser().getUserId());
        jTaskhead.setZowneruserid(userId);
        Integer deptId = loginUser.getUser().getDeptId().intValue();
        jTaskhead.setZownerdeptid(deptId);
        jTaskhead.setZisdeleted(0);
        jTaskhead.setZdatecreated(new Date());
        jTaskhead.setZcreatedby(loginUser.getUser().getUserName());
        jTaskhead.setZdateupdated(new Date());

        int insertJTaskhead = jTaskheadMapper.insertJTaskhead(jTaskhead);
        if (insertJTaskhead <= 0) {
            throw new Exception("新增任务失败");
        }

        //2.新增任务详情
        JTaskdetail taskdetail = new JTaskdetail();
        taskdetail.setZtaskhid(jTaskhead.getZtaskhid());
        taskdetail.setZisread(0);
        taskdetail.setZisdeleted(0);
        taskdetail.setCreateTime(new Date());
        taskdetail.setZcreatedby(loginUser.getUser().getUserName());
        taskdetail.setZdateupdated(new Date());

        List<TaskVo> taskVoList = jTaskhead.getTaskVoList();
        if(taskVoList != null  && !taskVoList.isEmpty()){
            System.out.println(taskVoList.toString() + taskVoList.isEmpty());
            for (TaskVo taskVo : taskVoList) {

                if (taskVo.getZproctype() == 1) {
                    //zprocusertype  zprocuserid;  zproctype
                    taskdetail.setZproctype(taskVo.getZproctype());
                    taskdetail.setZprocuserid(taskVo.getZprocuserid());
                    taskdetail.setZprocusertype(taskVo.getZprocusertype());
                    int insertJTaskdetail = jTaskdetailMapper.insertJTaskdetail(taskdetail);
                    if (insertJTaskdetail <= 0) {
                        throw new Exception("新增任务详情失败");
                    }

                } else {
                    //zprocusertype  zprocuserid;  zproctype
                    taskdetail.setZproctype(taskVo.getZproctype());
                    taskdetail.setZprocuserid(taskVo.getZprocuserid());
                    taskdetail.setZprocusertype(taskVo.getZprocusertype());
                    int insertJTaskdetail = jTaskdetailMapper.insertJTaskdetail(taskdetail);
                    if (insertJTaskdetail <= 0) {
                        throw new Exception("新增任务详情失败");
                    }
                }

            }

        }else {
            throw new Exception("请选择处理类型");
        }

    }
    /**
     * 修改任务
     * 
     * @param jTaskhead 任务
     * @return 结果
     */
    @Override
    public int updateJTaskhead(JTaskhead jTaskhead)
    {
        return jTaskheadMapper.updateJTaskhead(jTaskhead);
    }

    /**
     * 批量删除任务
     * 
     * @param ztaskhids 需要删除的任务ID
     * @return 结果
     */
    @Override
    public int deleteJTaskheadByIds(String[] ztaskhids)
    {
        return jTaskheadMapper.deleteJTaskheadByIds(ztaskhids);
    }

    /**
     * 删除任务信息
     * 
     * @param ztaskhid 任务ID
     * @return 结果
     */
    @Override
    public int deleteJTaskheadById(String ztaskhid)
    {
        return jTaskheadMapper.deleteJTaskheadById(ztaskhid);
    }
}
