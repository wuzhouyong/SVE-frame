package com.ruoyi.framework.web.domain;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ruoyi.project.common.domain.PfBasIndustry;

/**
 * Treeselect树结构实体类
 *
 * @author ruoyi
 */
public class BaseTree implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 节点ID */
    private String id;

    /** 节点名称 */
    private String label;

    /** 节点类型 */
    private String type;

    /** 子节点 */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<BaseTree> children;

    public BaseTree()
    {

    }


    public BaseTree(PfBasIndustry ind)
    {
        this.id = ind.getIndustryCode();
        this.label = ind.getIndustryName();
        this.type = ind.getParentCode();
        this.children = ind.getChildren().stream().map(BaseTree::new).collect(Collectors.toList());
    }


    public String getId()
    {
        return id;
    }

    public void setId(String id)
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

    public List<BaseTree> getChildren()
    {
        return children;
    }

    public void setChildren(List<BaseTree> children)
    {
        this.children = children;
    }
    public void setAddChildren(List<BaseTree> children)
    {
        if(!children.isEmpty()) {
            for (BaseTree ts : this.children) {
                children.add(ts);
            }
            this.children = children;
        }
    }

    public String getType(){return this.type;}
    public void setType(String type){this.type = type;}
}

