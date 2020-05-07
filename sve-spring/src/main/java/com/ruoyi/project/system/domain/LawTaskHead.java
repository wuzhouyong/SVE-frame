package com.ruoyi.project.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * 移动执法任务主对象 law_task_head
 * 
 * @author ruoyi
 * @date 2020-05-07
 */
public class LawTaskHead extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @Excel(name = "主键")
    private String taskHeadId;

    /** 任务状态，0 未完成，1 已完成 */
    @Excel(name = "任务状态，0 未完成，1 已完成")
    private Long taskStatus;

    /** 紧急程度，1 普通，2 紧急，3 特急 */
    @Excel(name = "紧急程度，1 普通，2 紧急，3 特急")
    private Long taskUrgentLevel;

    /** 任务类型 ref pf_bas_data_dict */
    @Excel(name = "任务类型 ref pf_bas_data_dict")
    private String taskType;

    /** 任务标题 */
    @Excel(name = "任务标题")
    private String taskTitle;

    /** 任务内容 */
    @Excel(name = "任务内容")
    private String taskContent;

    /** 任务到期时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "任务到期时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date taskDueDate;

    /** 污染源编号 */
    @Excel(name = "污染源编号")
    private String sourceId;

    /** 父级任务编号 */
    @Excel(name = "父级任务编号")
    private String parentId;

    /** 任务发布人编号 */
    @Excel(name = "任务发布人编号")
    private String issuedUserId;

    /** 任务发布人姓名 */
    @Excel(name = "任务发布人姓名")
    private String issuedUserName;

    /** 任务发布人部门编号 */
    @Excel(name = "任务发布人部门编号")
    private String issuedOrgId;

    /** 任务发布人部门名称 */
    @Excel(name = "任务发布人部门名称")
    private String issuedOrgName;

    /** 任务发布时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "任务发布时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date issuedDate;

    /** 实际处理人id */
    @Excel(name = "实际处理人id")
    private String handledUserId;

    /** 实际处理人姓名 */
    @Excel(name = "实际处理人姓名")
    private String handledUserName;

    /** 实际处理人部门id */
    @Excel(name = "实际处理人部门id")
    private String handledOrgId;

    /** 实际处理人部门名称 */
    @Excel(name = "实际处理人部门名称")
    private String handledOrgName;

    /** 实际处理日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "实际处理日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date handledDate;

    /** 处理情况 */
    @Excel(name = "处理情况")
    private String handledDesc;

    /** 删除标记 */
    @Excel(name = "删除标记")
    private Long isDeleted;

    /** 创建人 */
    @Excel(name = "创建人")
    private String createdBy;

    /** 更新人 */
    @Excel(name = "更新人")
    private String updatedBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dateCreated;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dateUpdated;

    public void setTaskHeadId(String taskHeadId) 
    {
        this.taskHeadId = taskHeadId;
    }

    public String getTaskHeadId() 
    {
        return taskHeadId;
    }
    public void setTaskStatus(Long taskStatus) 
    {
        this.taskStatus = taskStatus;
    }

    public Long getTaskStatus() 
    {
        return taskStatus;
    }
    public void setTaskUrgentLevel(Long taskUrgentLevel) 
    {
        this.taskUrgentLevel = taskUrgentLevel;
    }

    public Long getTaskUrgentLevel() 
    {
        return taskUrgentLevel;
    }
    public void setTaskType(String taskType) 
    {
        this.taskType = taskType;
    }

    public String getTaskType() 
    {
        return taskType;
    }
    public void setTaskTitle(String taskTitle) 
    {
        this.taskTitle = taskTitle;
    }

    public String getTaskTitle() 
    {
        return taskTitle;
    }
    public void setTaskContent(String taskContent) 
    {
        this.taskContent = taskContent;
    }

    public String getTaskContent() 
    {
        return taskContent;
    }
    public void setTaskDueDate(Date taskDueDate) 
    {
        this.taskDueDate = taskDueDate;
    }

    public Date getTaskDueDate() 
    {
        return taskDueDate;
    }
    public void setSourceId(String sourceId) 
    {
        this.sourceId = sourceId;
    }

    public String getSourceId() 
    {
        return sourceId;
    }
    public void setParentId(String parentId) 
    {
        this.parentId = parentId;
    }

    public String getParentId() 
    {
        return parentId;
    }
    public void setIssuedUserId(String issuedUserId) 
    {
        this.issuedUserId = issuedUserId;
    }

    public String getIssuedUserId() 
    {
        return issuedUserId;
    }
    public void setIssuedUserName(String issuedUserName) 
    {
        this.issuedUserName = issuedUserName;
    }

    public String getIssuedUserName() 
    {
        return issuedUserName;
    }
    public void setIssuedOrgId(String issuedOrgId) 
    {
        this.issuedOrgId = issuedOrgId;
    }

    public String getIssuedOrgId() 
    {
        return issuedOrgId;
    }
    public void setIssuedOrgName(String issuedOrgName) 
    {
        this.issuedOrgName = issuedOrgName;
    }

    public String getIssuedOrgName() 
    {
        return issuedOrgName;
    }
    public void setIssuedDate(Date issuedDate) 
    {
        this.issuedDate = issuedDate;
    }

    public Date getIssuedDate() 
    {
        return issuedDate;
    }
    public void setHandledUserId(String handledUserId) 
    {
        this.handledUserId = handledUserId;
    }

    public String getHandledUserId() 
    {
        return handledUserId;
    }
    public void setHandledUserName(String handledUserName) 
    {
        this.handledUserName = handledUserName;
    }

    public String getHandledUserName() 
    {
        return handledUserName;
    }
    public void setHandledOrgId(String handledOrgId) 
    {
        this.handledOrgId = handledOrgId;
    }

    public String getHandledOrgId() 
    {
        return handledOrgId;
    }
    public void setHandledOrgName(String handledOrgName) 
    {
        this.handledOrgName = handledOrgName;
    }

    public String getHandledOrgName() 
    {
        return handledOrgName;
    }
    public void setHandledDate(Date handledDate) 
    {
        this.handledDate = handledDate;
    }

    public Date getHandledDate() 
    {
        return handledDate;
    }
    public void setHandledDesc(String handledDesc) 
    {
        this.handledDesc = handledDesc;
    }

    public String getHandledDesc() 
    {
        return handledDesc;
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
            .append("taskHeadId", getTaskHeadId())
            .append("taskStatus", getTaskStatus())
            .append("taskUrgentLevel", getTaskUrgentLevel())
            .append("taskType", getTaskType())
            .append("taskTitle", getTaskTitle())
            .append("taskContent", getTaskContent())
            .append("taskDueDate", getTaskDueDate())
            .append("sourceId", getSourceId())
            .append("parentId", getParentId())
            .append("issuedUserId", getIssuedUserId())
            .append("issuedUserName", getIssuedUserName())
            .append("issuedOrgId", getIssuedOrgId())
            .append("issuedOrgName", getIssuedOrgName())
            .append("issuedDate", getIssuedDate())
            .append("handledUserId", getHandledUserId())
            .append("handledUserName", getHandledUserName())
            .append("handledOrgId", getHandledOrgId())
            .append("handledOrgName", getHandledOrgName())
            .append("handledDate", getHandledDate())
            .append("handledDesc", getHandledDesc())
            .append("isDeleted", getIsDeleted())
            .append("createdBy", getCreatedBy())
            .append("updatedBy", getUpdatedBy())
            .append("dateCreated", getDateCreated())
            .append("dateUpdated", getDateUpdated())
            .toString();
    }
}
