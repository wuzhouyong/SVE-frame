package com.ruoyi.project.common.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;

/**
 * 企业行业对象 pf_bas_industry
 *
 * @author ruoyi
 * @date 2020-05-20
 */
public class PfBasIndustry extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号   -- GUID */
    private String industryId;

    /** 行业代码 */
    @Excel(name = "行业代码")
    private String industryCode;

    /** 行业名称 */
    @Excel(name = "行业名称")
    private String industryName;

    /** 父级代码 */
    @Excel(name = "父级代码")
    private String parentCode;

    /** 删除标记 */
    @Excel(name = "删除标记")
    private Long isDeleted;

    /** $column.columnComment */
    @Excel(name = "删除标记")
    private String createdBy;

    /** $column.columnComment */
    @Excel(name = "删除标记")
    private String updatedBy;

    /** $column.columnComment */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "删除标记", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dateCreated;

    /** $column.columnComment */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "删除标记", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dateUpdated;


    /** 子部门 */
    private List<PfBasIndustry> children = new ArrayList<PfBasIndustry>();

    public void setIndustryId(String industryId)
    {
        this.industryId = industryId;
    }

    public String getIndustryId()
    {
        return industryId;
    }
    public void setIndustryCode(String industryCode)
    {
        this.industryCode = industryCode;
    }

    public String getIndustryCode()
    {
        return industryCode;
    }
    public void setIndustryName(String industryName)
    {
        this.industryName = industryName;
    }

    public String getIndustryName()
    {
        return industryName;
    }
    public void setParentCode(String parentCode)
    {
        this.parentCode = parentCode;
    }

    public String getParentCode()
    {
        return parentCode;
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
            .append("industryId", getIndustryId())
            .append("industryCode", getIndustryCode())
            .append("industryName", getIndustryName())
            .append("parentCode", getParentCode())
            .append("isDeleted", getIsDeleted())
            .append("createdBy", getCreatedBy())
            .append("updatedBy", getUpdatedBy())
            .append("dateCreated", getDateCreated())
            .append("dateUpdated", getDateUpdated())
            .toString();
    }

    public List<PfBasIndustry> getChildren() {
        return children;
    }

    public void setChildren(List<PfBasIndustry> children) {
        this.children = children;
    }
}
