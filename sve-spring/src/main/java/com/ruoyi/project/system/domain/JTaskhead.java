package com.ruoyi.project.system.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.project.system.domain.vo.TaskVo;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 任务对象 j_taskhead
 * 
 * @author ruoyi
 * @date 2020-05-12
 */
@Data
public class JTaskhead extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 任务主键 */
    @Excel(name = "任务主键")
    private String ztaskhid;

    /** 任务上级ID */
    @Excel(name = "任务上级ID")
    private String zparenthid;

    /** 任务类型 */
    @Excel(name = "任务类型")
    private Integer ztasktype;

    /** 紧急程度  1 普通,2 紧急 */
    @Excel(name = "紧急程度  1 普通,2 紧急")
    private Integer zurgencylevel;

    /** 任务状态  1 未处理，2 处理中，3 已处理 */
    @Excel(name = "任务状态  1 未处理，2 处理中，3 已处理")
    private Integer ztaskstatus;

    /** 任务标题 */
    @Excel(name = "任务标题")
    private String ztasktitle;

    /** 任务内容 */
    @Excel(name = "任务内容")
    private String ztaskcontent;

    /** 任务下达时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Excel(name = "任务下达时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date ztaskdate;

    /** 任务开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Excel(name = "任务开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date zbegintime;

    /** 任务完成时间 */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Excel(name = "任务完成时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date zendtime;

    /** 任务发起人ID */
    @Excel(name = "任务发起人ID")
    private String zowneruserid;

    /** 任务发起人部门ID */
    @Excel(name = "任务发起人部门ID")
    private Integer zownerdeptid;

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

    /** 污染源ID */
    @Excel(name = "污染源ID")
    private Integer zsourceid;

    /** 其它推送平台使用 */
    @Excel(name = "其它推送平台使用")
    private String zmainrecordid;

    /** 其它推送平台使用 */
    @Excel(name = "其它推送平台使用")
    private Integer ztasksourceid;

    /** 其它推送平台使用 */
    @Excel(name = "其它推送平台使用")
    private Integer zoldsourceid;

    /** 任务vo类集合 */
    private List<TaskVo> taskVoList=new ArrayList<>();

    public void setZtaskhid(String ztaskhid) 
    {
        this.ztaskhid = ztaskhid;
    }

    public String getZtaskhid() 
    {
        return ztaskhid;
    }
    public void setZparenthid(String zparenthid) 
    {
        this.zparenthid = zparenthid;
    }

    public String getZparenthid() 
    {
        return zparenthid;
    }
    public void setZtasktype(Integer ztasktype) 
    {
        this.ztasktype = ztasktype;
    }

    public Integer getZtasktype() 
    {
        return ztasktype;
    }
    public void setZurgencylevel(Integer zurgencylevel) 
    {
        this.zurgencylevel = zurgencylevel;
    }

    public Integer getZurgencylevel() 
    {
        return zurgencylevel;
    }
    public void setZtaskstatus(Integer ztaskstatus) 
    {
        this.ztaskstatus = ztaskstatus;
    }

    public Integer getZtaskstatus() 
    {
        return ztaskstatus;
    }
    public void setZtasktitle(String ztasktitle) 
    {
        this.ztasktitle = ztasktitle;
    }

    public String getZtasktitle() 
    {
        return ztasktitle;
    }
    public void setZtaskcontent(String ztaskcontent) 
    {
        this.ztaskcontent = ztaskcontent;
    }

    public String getZtaskcontent() 
    {
        return ztaskcontent;
    }
    public void setZtaskdate(Date ztaskdate) 
    {
        this.ztaskdate = ztaskdate;
    }

    public Date getZtaskdate() 
    {
        return ztaskdate;
    }
    public void setZbegintime(Date zbegintime) 
    {
        this.zbegintime = zbegintime;
    }

    public Date getZbegintime() 
    {
        return zbegintime;
    }
    public void setZendtime(Date zendtime) 
    {
        this.zendtime = zendtime;
    }

    public Date getZendtime() 
    {
        return zendtime;
    }
    public void setZowneruserid(String zowneruserid) 
    {
        this.zowneruserid = zowneruserid;
    }

    public String getZowneruserid() 
    {
        return zowneruserid;
    }
    public void setZownerdeptid(Integer zownerdeptid) 
    {
        this.zownerdeptid = zownerdeptid;
    }

    public Integer getZownerdeptid() 
    {
        return zownerdeptid;
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
    public void setZsourceid(Integer zsourceid) 
    {
        this.zsourceid = zsourceid;
    }

    public Integer getZsourceid() 
    {
        return zsourceid;
    }
    public void setZmainrecordid(String zmainrecordid) 
    {
        this.zmainrecordid = zmainrecordid;
    }

    public String getZmainrecordid() 
    {
        return zmainrecordid;
    }
    public void setZtasksourceid(Integer ztasksourceid) 
    {
        this.ztasksourceid = ztasksourceid;
    }

    public Integer getZtasksourceid() 
    {
        return ztasksourceid;
    }
    public void setZoldsourceid(Integer zoldsourceid) 
    {
        this.zoldsourceid = zoldsourceid;
    }

    public Integer getZoldsourceid() 
    {
        return zoldsourceid;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("ztaskhid", getZtaskhid())
            .append("zparenthid", getZparenthid())
            .append("ztasktype", getZtasktype())
            .append("zurgencylevel", getZurgencylevel())
            .append("ztaskstatus", getZtaskstatus())
            .append("ztasktitle", getZtasktitle())
            .append("ztaskcontent", getZtaskcontent())
            .append("ztaskdate", getZtaskdate())
            .append("zbegintime", getZbegintime())
            .append("zendtime", getZendtime())
            .append("zowneruserid", getZowneruserid())
            .append("zownerdeptid", getZownerdeptid())
            .append("zisdeleted", getZisdeleted())
            .append("zdatecreated", getZdatecreated())
            .append("zcreatedby", getZcreatedby())
            .append("zdateupdated", getZdateupdated())
            .append("zupdatedby", getZupdatedby())
            .append("zsourceid", getZsourceid())
            .append("zmainrecordid", getZmainrecordid())
            .append("ztasksourceid", getZtasksourceid())
            .append("zoldsourceid", getZoldsourceid())
            .append("zprocusertype", getTaskVoList())
            .toString();
    }
}
