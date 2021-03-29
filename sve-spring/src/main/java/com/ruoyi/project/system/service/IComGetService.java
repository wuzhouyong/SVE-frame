package com.ruoyi.project.system.service;

import com.ruoyi.framework.web.domain.BaseTree;
import com.ruoyi.project.common.domain.PfBasIndustry;
import com.ruoyi.project.common.domain.PfFileAttachment;

import java.util.List;
import java.util.Map;

/**
 * 参数配置 服务层
 *
 * @author ruoyi
 */
public interface IComGetService {
    /**
     * 查子区域
     * @param code
     * @return
     */
    public List<Map> selectRegionByCode(String code);
    public List<Map> selectRegionsByIds(String ids);

    /**
     * 查询所有行业
     * @return
     */
    public List<PfBasIndustry> selectAllIndustry();

    /**
     * 构建行业树
     *
     * @param depts 部门列表
     * @return 下拉树结构列表
     */
    public List<BaseTree> buildDeptTreeSelect(List<PfBasIndustry> depts);


    //附件
    public List<PfFileAttachment> selectPfFileAttachmentList(PfFileAttachment pfFileAttachment);
    public List<Map> selectHashFileAttachmentList(PfFileAttachment pfFileAttachment);
    public int insertPfFileAttachment(PfFileAttachment pfFileAttachment);
    public PfFileAttachment selectPfFileAttachmentById(String attachmentId);
    public List<PfFileAttachment> selectPfFileAttachmentByIds(String[] attachmentIds);
    public List<PfFileAttachment> selectPfFileAttachmentsByRef(String refType, String[] refIds);
    public int updatePfFileAttachment(PfFileAttachment pfFileAttachment);
}
