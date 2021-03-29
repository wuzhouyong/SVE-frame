package com.ruoyi.project.system.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.framework.web.domain.BaseTree;
import com.ruoyi.project.common.domain.PfBasIndustry;
import com.ruoyi.project.common.domain.PfFileAttachment;
import com.ruoyi.project.system.mapper.ComGetMapper;
import com.ruoyi.project.system.service.IComGetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@DataSource(value = DataSourceType.MASTER)
public class IComGetServiceImpl implements IComGetService {

    @Autowired
    private ComGetMapper comGetMapper;


    /**
     * 查子区域
     *
     * @param code
     * @return
     */
    @Override
    public List<Map> selectRegionByCode(String code) {

        return comGetMapper.selectRegionByCode(code);
    }

    public List<Map> selectRegionsByIds(String ids) {
        return comGetMapper.selectRegionsByIds(ids);
    }


    /**
     * 查询所有行业
     *
     * @return
     */
    @Override
    public List<PfBasIndustry> selectAllIndustry() {
        return comGetMapper.selectAllIndustry();
    }

    /**
     * 构建行业树
     *
     * @param inds 行业
     * @return 下拉树结构列表
     */
    @Override
    public List<BaseTree> buildDeptTreeSelect(List<PfBasIndustry> inds) {
        List<PfBasIndustry> indTree = buildIndustryTree(inds, "");
        return indTree.stream().map(BaseTree::new).collect(Collectors.toList());
    }

    public List<PfBasIndustry> buildIndustryTree(List<PfBasIndustry> inds, String parentId) {
        List<PfBasIndustry> tree = new ArrayList<PfBasIndustry>();
        for (PfBasIndustry in : inds) {
            if (in.getParentCode() == null && parentId.isEmpty()) {
                in.setChildren(buildIndustryTree(inds, in.getIndustryCode()));
            }

            String code = in.getParentCode() == null ? "" : in.getParentCode();
            if (code.equals(parentId)) {
                in.setChildren(buildIndustryTree(inds, in.getIndustryCode()));
                tree.add(in);
            }
        }

        return tree;
    }


    //附件
    public List<PfFileAttachment> selectPfFileAttachmentList(PfFileAttachment pfFileAttachment) {
        return comGetMapper.selectPfFileAttachmentList(pfFileAttachment);
    }

    public List<Map> selectHashFileAttachmentList(PfFileAttachment pfFileAttachment) {
        List<PfFileAttachment> files = comGetMapper.selectPfFileAttachmentList(pfFileAttachment);
        List<Map> lmap = new ArrayList<>();
        for (PfFileAttachment file : files) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("Id", file.getAttachmentId());
            claims.put("showName", file.getShowName());
            claims.put("TypeName", file.getAttachmentTypeName());
            claims.put("attachType", file.getAttachmentType());
            claims.put("dateCreated", DateUtils.dateTimeS(file.getDateCreated()));
            claims.put("createdBy", file.getCreatedBy());
            lmap.add(claims);
        }
        return lmap;
    }

    public int insertPfFileAttachment(PfFileAttachment pfFileAttachment) {
        return comGetMapper.insertPfFileAttachment(pfFileAttachment);
    }

    public PfFileAttachment selectPfFileAttachmentById(String attachmentId) {
        return comGetMapper.selectPfFileAttachmentById(attachmentId);
    }

    public List<PfFileAttachment> selectPfFileAttachmentByIds(String[] attachmentIds) {
        return comGetMapper.selectPfFileAttachmentByIds(attachmentIds);
    }

    public List<PfFileAttachment> selectPfFileAttachmentsByRef(String refType, String[] refIds) {
        return comGetMapper.selectPfFileAttachmentsByRef(refType, refIds);
    }

    @Override
    public int updatePfFileAttachment(PfFileAttachment pfFileAttachment) {
        return comGetMapper.updatePfFileAttachment(pfFileAttachment);
    }

}
