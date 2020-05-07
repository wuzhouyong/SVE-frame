package com.ruoyi.project.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * 移动执法任务 - 明细对象 law_task_detail
 * 
 * @author ruoyi
 * @date 2020-05-07
 */
public class LawTaskDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @Excel(name = "主键")
    private String taskDetailId;

    /** 任务id ref law_task_head */
    @Excel(name = "任务id ref law_task_head")
    private String taskHeadId;

    /** 处理类型 1 主办，2 知会 */
    @Excel(name = "处理类型 1 主办，2 知会")
    private Long procType;

    /** 处理人类型： 1 用户，2 部门 */
    @Excel(name = "处理人类型： 1 用户，2 部门")
    private Long procUserType;

    /** 处理人id */
    @Excel(name = "处理人id")
    private String procUserId;

    /** 处理人名称 */
    @Excel(name = "处理人名称")
    private String procUserName;

    /** 处理部门id */
    @Excel(name = "处理部门id")
    private String procDeptId;

    /** 处理部门名称 */
    @Excel(name = "处理部门名称")
    private String procDeptName;

    /** 是否阅读 */
    @Excel(name = "是否阅读")
    private Long isRead;

    /** 删除标记 */
    @Excel(name = "删除标记")
    private Long isDeleted;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createdBy;

    /** 修改人 */
    @Excel(name = "修改人")
    private String updatedBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dateCreated;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dateUpdated;

    public void setTaskDetailId(String taskDetailId) 
    {
        this.taskDetailId = taskDetailId;
    }

    public String getTaskDetailId() 
    {
        return taskDetailId;
    }
    public void setTaskHeadId(String taskHeadId) 
    {
        this.taskHeadId = taskHeadId;
    }

    public String getTaskHeadId() 
    {
        return taskHeadId;
    }
    public void setProcType(Long procType) 
    {
        this.procType = procType;
    }

    public Long getProcType() 
    {
        return procType;
    }
    public void setProcUserType(Long procUserType) 
    {
        this.procUserType = procUserType;
    }

    public Long getProcUserType() 
    {
        return procUserType;
    }
    public void setProcUserId(String procUserId) 
    {
        this.procUserId = procUserId;
    }

    public String getProcUserId() 
    {
        return procUserId;
    }
    public void setProcUserName(String procUserName) 
    {
        this.procUserName = procUserName;
    }

    public String getProcUserName() 
    {
        return procUserName;
    }
    public void setProcDeptId(String procDeptId) 
    {
        this.procDeptId = procDeptId;
    }

    public String getProcDeptId() 
    {
        return procDeptId;
    }
    public void setProcDeptName(String procDeptName) 
    {
        this.procDeptName = procDeptName;
    }

    public String getProcDeptName() 
    {
        return procDeptName;
    }
    public void setIsRead(Long isRead) 
    {
        this.isRead = isRead;
    }

    public Long getIsRead() 
    {
        return isRead;
    }
    public void setIsDeleted(Long isDeleted) 
    {
        this.isDeleted = isDeleted;
    }

    public Long getIsDeleted() 
    {
        return isDeleted;
    }
    public void setCreatedBy(String createdBy) 
    {
        this.createdBy = createdBy;
    }

    public String getCreatedBy() 
    {
        return createdBy;
    }
    public void setUpdatedBy(String updatedBy) 
    {
        this.updatedBy = updatedBy;
    }

    public String getUpdatedBy() 
    {
        return updatedBy;
    }
    public void setDateCreated(Date dateCreated) 
    {
        this.dateCreated = dateCreated;
    }

    public Date getDateCreated() 
    {
        return dateCreated;
    }
    public void setDateUpdated(Date dateUpdated) 
    {
        this.dateUpdated = dateUpdated;
    }

    public Date getDateUpdated() 
    {
        return dateUpdated;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("taskDetailId", getTaskDetailId())
            .append("taskHeadId", getTaskHeadId())
            .append("procType", getProcType())
            .append("procUserType", getProcUserType())
            .append("procUserId", getProcUserId())
            .append("procUserName", getProcUserName())
            .append("procDeptId", getProcDeptId())
            .append("procDeptName", getProcDeptName())
            .append("isRead", getIsRead())
            .append("isDeleted", getIsDeleted())
            .append("createdBy", getCreatedBy())
            .append("updatedBy", getUpdatedBy())
            .append("dateCreated", getDateCreated())
            .append("dateUpdated", getDateUpdated())
            .toString();
    }
}
