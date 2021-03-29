package com.ruoyi.project.system.domain.ext;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 代码生成业务对象 sys_dept_user
 *
 * @author ruoyi
 * @date 2020-05-15
 */
public class SysDeptUser extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @Excel(name = "编号")
    private Long deptUserId;

    /**
     * 部门ID
     */
    @Excel(name = "部门ID")
    private Long deptId;

    /**
     * 用户ID
     */
    @Excel(name = "用户ID")
    private Long userId;

    /**
     * 删除标记
     */
    private String delFlag;

    /**
     * 创建用户
     */
    private String createBy;
    private String updateBy;

    public SysDeptUser() {
    }

    public void setDeptUserId(Long deptUserId) {
        this.deptUserId = deptUserId;
    }

    public Long getDeptUserId() {
        return deptUserId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelFlag() {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("deptUserId", getDeptUserId())
                .append("deptId", getDeptId())
                .append("userId", getUserId())
                .append("delFlag", getDelFlag())
                .append("updateBy", getUpdateBy())
                .append("createBy", getCreateBy())
                .toString();
    }

    @Override
    public String getCreateBy() {
        return createBy;
    }

    @Override
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Override
    public String getUpdateBy() {
        return updateBy;
    }

    @Override
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }
}