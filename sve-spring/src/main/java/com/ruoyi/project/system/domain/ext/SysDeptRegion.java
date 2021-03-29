package com.ruoyi.project.system.domain.ext;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 代码生成业务对象 SysDeptRegion
 *
 * @author ruoyi
 * @date 2020-05-15
 */
public class SysDeptRegion extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @Excel(name = "部门ID")
    private Long deptId;

    /**
     * 部门ID
     */
    @Excel(name = "区域代码")
    private String regionCode;


    public SysDeptRegion() {
    }


    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getDeptId() {
        return deptId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("deptId", getDeptId())
                .append("regionCode",getRegionCode())
                .toString();
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }
}