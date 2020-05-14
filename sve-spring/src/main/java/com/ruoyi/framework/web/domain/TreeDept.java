package com.ruoyi.framework.web.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ruoyi.project.system.domain.SysDept;
import com.ruoyi.project.system.domain.SysUser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author solang
 * @date 2020-05-09 8:46
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(value = {"handler"})
public class TreeDept implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 节点ID */
    private Long id;

    /**
     * 是否是部门
     */
    private String isdept;

    /** 节点名称 */
    private String label;

    /**
     * 组织的层级，第一层为0
     */
    private Long nodeLevel;

    /** 子节点 */
    private List<TreeDept> children;

    /** 部门下的用户 ------------886---  */
    private List<SysUser> user = new ArrayList<SysUser>();

    public TreeDept(Long id, String label, List<TreeDept> children, List<SysUser> user, String isdept) {

        this.id = id;
        this.label = label;
        this.children = children;
        this.user = user;
        this.isdept=isdept;
    }

    public TreeDept(SysDept dept)
    {

        this.id = dept.getDeptId();
        this.label = dept.getDeptName();
        this.children = dept.getChildren().stream().map(TreeDept::new).collect(Collectors.toList());
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getLabel()
    {
        return label;
    }

    public void setLabel(String label)
    {
        this.label = label;
    }

    public List<TreeDept> getChildren()
    {
        return children;
    }

    public void setChildren(List<TreeDept> children)
    {
        this.children = children;
    }

    public List<SysUser> getUser() {
        return user;
    }

    public void setUser(List<SysUser> sysUser) {
        this.user = user;
    }


}
