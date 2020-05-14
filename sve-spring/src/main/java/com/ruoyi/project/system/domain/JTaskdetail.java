package com.ruoyi.project.system.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * 任务明细对象 j_taskdetail
 * 
 * @author ruoyi
 * @date 2020-05-12
 */
@Data
public class JTaskdetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 任务明细ID */
    private Long ztaskdid;

    /** 任务主表ID */
    @Excel(name = "任务主表ID")
    private String ztaskhid;

    /** 1 处理,2 知会，3 确认 */
    @Excel(name = "1 处理,2 知会，3 确认")
    private Integer zproctype;

    /** 主办人（可以是UserId 或 UserGroup） */
    @Excel(name = "主办人", readConverterExp = "可=以是UserId,或=,U=serGroup")
    private String zprocuserid;

    /** 实际处理人 */
    @Excel(name = "实际处理人")
    private String zhandleduserid;

    /** 0 未读，1 已读 */
    @Excel(name = "0 未读，1 已读")
    private Integer zisread;

    /** 反馈内容 */
    @Excel(name = "反馈内容")
    private String zfeedback;

    /** 处理日期 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Excel(name = "处理日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date zprocdate;

    /** 是否删除， 1为删除 */
    @Excel(name = "是否删除， 1为删除")
    private Integer zisdeleted;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date zdatecreated;

    /** 创建人 */
    @Excel(name = "创建人")
    private String zcreatedby;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date zdateupdated;

    /** 更新人 */
    @Excel(name = "更新人")
    private String zupdatedby;

    /** 处理人类型 */
    @Excel(name = "处理人类型")
    private Integer zprocusertype;

    public void setZtaskdid(Long ztaskdid) 
    {
        this.ztaskdid = ztaskdid;
    }

    public Long getZtaskdid() 
    {
        return ztaskdid;
    }
    public void setZtaskhid(String ztaskhid) 
    {
        this.ztaskhid = ztaskhid;
    }

    public String getZtaskhid() 
    {
        return ztaskhid;
    }
    public void setZproctype(Integer zproctype)
    {
        this.zproctype = zproctype;
    }

    public Integer getZproctype()
    {
        return zproctype;
    }
    public void setZprocuserid(String zprocuserid) 
    {
        this.zprocuserid = zprocuserid;
    }

    public String getZprocuserid() 
    {
        return zprocuserid;
    }
    public void setZhandleduserid(String zhandleduserid) 
    {
        this.zhandleduserid = zhandleduserid;
    }

    public String getZhandleduserid() 
    {
        return zhandleduserid;
    }
    public void setZisread(Integer zisread)
    {
        this.zisread = zisread;
    }

    public Integer getZisread()
    {
        return zisread;
    }
    public void setZfeedback(String zfeedback) 
    {
        this.zfeedback = zfeedback;
    }

    public String getZfeedback() 
    {
        return zfeedback;
    }
    public void setZprocdate(Date zprocdate) 
    {
        this.zprocdate = zprocdate;
    }

    public Date getZprocdate() 
    {
        return zprocdate;
    }
    public void setZisdeleted(Integer zisdeleted)
    {
        this.zisdeleted = zisdeleted;
    }

    public Integer getZisdeleted()
    {
        return zisdeleted;
    }
    public void setZdatecreated(Date zdatecreated) 
    {
        this.zdatecreated = zdatecreated;
    }

    public Date getZdatecreated() 
    {
        return zdatecreated;
    }
    public void setZcreatedby(String zcreatedby) 
    {
        this.zcreatedby = zcreatedby;
    }

    public String getZcreatedby() 
    {
        return zcreatedby;
    }
    public void setZdateupdated(Date zdateupdated) 
    {
        this.zdateupdated = zdateupdated;
    }

    public Date getZdateupdated() 
    {
        return zdateupdated;
    }
    public void setZupdatedby(String zupdatedby) 
    {
        this.zupdatedby = zupdatedby;
    }

    public String getZupdatedby() 
    {
        return zupdatedby;
    }
    public void setZprocusertype(Integer zprocusertype)
    {
        this.zprocusertype = zprocusertype;
    }

    public Integer getZprocusertype()
    {
        return zprocusertype;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("ztaskdid", getZtaskdid())
            .append("ztaskhid", getZtaskhid())
            .append("zproctype", getZproctype())
            .append("zprocuserid", getZprocuserid())
            .append("zhandleduserid", getZhandleduserid())
            .append("zisread", getZisread())
            .append("zfeedback", getZfeedback())
            .append("zprocdate", getZprocdate())
            .append("zisdeleted", getZisdeleted())
            .append("zdatecreated", getZdatecreated())
            .append("zcreatedby", getZcreatedby())
            .append("zdateupdated", getZdateupdated())
            .append("zupdatedby", getZupdatedby())
            .append("zprocusertype", getZprocusertype())
            .toString();
    }
}
