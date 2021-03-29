package com.ruoyi.project.enterprise.service.impl;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.framework.security.LoginUser;
import com.ruoyi.framework.security.service.TokenService;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.enterprise.domain.*;
import com.ruoyi.project.enterprise.domain.source.BasSourceParam;
import com.ruoyi.project.enterprise.domain.source.SourceResult;
import com.ruoyi.project.enterprise.mapper.PfBasSourceMapper;
import com.ruoyi.project.enterprise.mapper.SourceModifyMapper;
import com.ruoyi.project.enterprise.service.IPfBasSourceService;
import com.ruoyi.project.outwater.domain.entity.NameAndIdEntity;
import com.ruoyi.project.system.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 企业信息Service业务层处理
 *
 * @author ruoyi
 * @date 2020-05-19
 */
@Service
@DataSource(value = DataSourceType.MASTER)
public class PfBasSourceServiceImpl implements IPfBasSourceService {
    @Autowired
    private PfBasSourceMapper pfBasSourceMapper;

    @Autowired
    private SourceModifyMapper sourceModifyMapper;

    @Autowired
    private TokenService tokenService;

    @Override
    public List<SourceResult> getSourceList(BasSourceParam param) {
        return pfBasSourceMapper.getSourceList(param);
    }

    /**
     * 查询企业信息
     *
     * @param sourceId 企业信息ID
     * @return 企业信息
     */
    @Override
    public PfBasSource selectPfBasSourceById(String sourceId) {
        return pfBasSourceMapper.selectPfBasSourceById(sourceId);
    }

    @Override
    public List<SourceByTag> getSourceListByTag(SourceByTag model) {
        return pfBasSourceMapper.getSourceListByTag(model);
    }

    /**
     * 查询企业信息列表
     *
     * @param pfBasSource 企业信息
     * @return 企业信息
     */
    @Override
    public List<PfBasSource> selectPfBasSourceList(PfBasSource pfBasSource) {
        return pfBasSourceMapper.selectPfBasSourceListByTag(pfBasSource);
    }

    // 根据tagId修改tagName
    @Override
    public int updateTagName(String tagId, String tagName, Integer orderBy) {
        PfBasSource pfBasSource = new PfBasSource();
        pfBasSource.setTagHeadId(tagId);
        pfBasSource.setFullName(tagName);
        pfBasSource.setIsHasVOCs(orderBy);
        pfBasSource.setUpdatedBy(SecurityUtils.getUsername());
        pfBasSource.setDateUpdated(new Date());
        return pfBasSourceMapper.updateTagName(pfBasSource);
    }

    @Override
    @DataSource(value = DataSourceType.OUTWATER)
    public List<PfBasSource> selectSourceListMap(PfBasSource pfBasSource) {
        return pfBasSourceMapper.selectSourceListMap(pfBasSource);
    }

    @Override
    public int getTotal(PfBasSource pfBasSource) {
        return pfBasSourceMapper.getTotal(pfBasSource);
    }

    @Override
    public List<PfBasSource> selectSourceHomeIndex(SourceParam entity) {
        return pfBasSourceMapper.selectSourceHomeIndex(entity);
    }

    @Override
    public List<String> selectDetailListById(PfBasSource pfBasSource) {
        List<PfBasTagDetail> pfList = pfBasSourceMapper.selectDetailListById(pfBasSource);
        List<String> list = pfList.stream().map(PfBasTagDetail::getTagHeadId).collect(Collectors.toList());
        if (list.contains("1016") || list.contains("1017") || list.contains("1021"))
            list.add("1001");
        return list;
    }

    @Override
    public int isHasVOCs(String sourceId) {
        return pfBasSourceMapper.isHasVOCs(sourceId);
    }

    @Override
    public SourceModuleOne selectModuleOne(String sourceId) {
        return pfBasSourceMapper.selectModuleOne(sourceId);
    }

    @Override
    public SourceModuleTwo selectModuleTwo(String sourceId) {
        return pfBasSourceMapper.selectModuleTwo(sourceId);
    }

    @Override
    public SourceVocsParamEntity selectSourceVocsParam(String sourceId) {
        return pfBasSourceMapper.selectSourceVocsParam(sourceId);
    }

    @Override
    public List<LeftMenuEntity> selectLeftMenuSourceCount(SourceParam entity) {
        return pfBasSourceMapper.selectLeftMenuSourceCount(entity);
    }

    @Override
    public int selectSourceSum(SourceParam entity) {
        return pfBasSourceMapper.selectSourceSum(entity);
    }

    @Override
    public int selectWarnCount(SourceParam entity) {
        return pfBasSourceMapper.selectWarnCount(entity);
    }

    @Override
    public List<LeftMenuEntity> selectWarnLe1(SourceParam entity) {
        return pfBasSourceMapper.selectWarnLe1(entity);
    }

    @Override
    public List<LeftMenuEntity> selectWarnLe2(SourceParam entity) {
        return pfBasSourceMapper.selectWarnLe2(entity);
    }

    @Override
    public List<LeftMenuEntity> selectWarnLe3(SourceParam entity) {
        return pfBasSourceMapper.selectWarnLe3(entity);
    }

    @Override
    public List<LeftMenuEntity> selectConStatus(SourceParam entity) {
        return pfBasSourceMapper.selectConStatus(entity);
    }

    /**
     * 新增企业信息
     *
     * @param pfBasSource 企业信息
     * @return 结果
     */
    @Override
    public int insertPfBasSource(PfBasSource pfBasSource) {
        return pfBasSourceMapper.insertPfBasSource(pfBasSource);
    }

    /**
     * 修改企业信息
     *
     * @param pfBasSource 企业信息
     * @return 结果
     */
    @Override
    public int updatePfBasSource(PfBasSource pfBasSource) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        pfBasSource.setUpdatedBy(loginUser.getUsername());
        int res = pfBasSourceMapper.updatePfBasSource(pfBasSource);
        if (res > 0) {
            SourceModifyEntity entity = new SourceModifyEntity();
            entity.setSourceId(pfBasSource.getSourceId());
            entity.setFullName(pfBasSource.getFullName());
            entity.setUpdatedBy(loginUser.getUsername());
            sourceModifyMapper.updateSourceUser(entity);
        }
        return res;
    }

    /**
     * 批量删除企业信息
     *
     * @param sourceIds 需要删除的企业信息ID
     * @return 结果
     */
    @Override
    public int deletePfBasSourceByIds(String[] sourceIds) {
        return pfBasSourceMapper.deletePfBasSourceByIds(sourceIds);
    }

    @Override
    public List<TagHeadEntity> selectTagHeadName() {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        String userId = user.getUserId().toString();
        return pfBasSourceMapper.selectTagHeadName(userId);
    }

    @Override  // 根据sourceid查询tag名字
    public List<TagHeadEntity> getTagsBySource(String sourceId) {
        return pfBasSourceMapper.getTagsBySource(sourceId);
    }

    /**
     * 删除企业信息信息
     *
     * @param sourceId 企业信息ID
     * @return 结果
     */
    @Override
    public int deletePfBasSourceById(String sourceId) {
        return pfBasSourceMapper.deletePfBasSourceById(sourceId);
    }


    /**
     * 查找企业标签
     *
     * @param serouceId
     * @return
     */
    public List<Map> selectSourceTagById(String serouceId) {
        return pfBasSourceMapper.selectSourceTagById(serouceId);
    }

    /**
     * 获取PF_BAS_TAG_HEAD表的数据列表
     */
    @Override
    public List<NameAndIdEntity> selectTagIdNameList(Long userId) {
        List<NameAndIdEntity> list = null;
        if (SecurityUtils.isAdmin(userId)) {
            list = pfBasSourceMapper.selectTagIdNameListAll();
        } else {
            list = pfBasSourceMapper.selectTagIdNameList(userId);
        }
        return list;
    }

    @Override
    public List<TagHeadEntity> selectTagHeadInfo(String pfSourceId) {
        return pfBasSourceMapper.selectTagHeadInfo(pfSourceId);
    }

    @Override
    public int deleteTagDetailByIds(String[] sourceIds) {
        return pfBasSourceMapper.deleteTagDetailByIds(sourceIds);
    }

    @Override
    public int deleteEnterUserByIds(String[] sourceIds) {
        return pfBasSourceMapper.deleteEnterUserByIds(sourceIds);
    }

    @Override
    public List<PfBasTagDetailEntity> selectTagDetail(String pfSourceId) {
        return pfBasSourceMapper.selectTagDetail(pfSourceId);
    }

    @Override
    @Transactional
    public AjaxResult insertTagDetail(List<PfBasTagDetailEntity> list) {
        int res = 0;
        String userName = SecurityUtils.getUsername();
        for (PfBasTagDetailEntity e : list) {
            if (StringUtils.isEmpty(e.getIsCheck()))
                continue;
            if (e.getIsCheck().equals("false") && !StringUtils.isEmpty(e.getTagDetailId())) {
                ConstructioninfoEntity entity = new ConstructioninfoEntity();
                entity.setPfSourceId(e.getPfSourceId());
                entity.setTagHeadId(e.getTagHeadId());
                entity.setUpdatedBy(userName);
                e.setUpdatedBy(userName);
                res = pfBasSourceMapper.removeDetail(e);
                res = pfBasSourceMapper.removeConstructioninfo(entity);
                res = pfBasSourceMapper.removeAdjust(entity);
            }

            if (e.getIsCheck().equals("true") && !StringUtils.isEmpty(e.getTagDetailId())) {
                e.setUpdatedBy(userName);
                res = pfBasSourceMapper.updateDetail(e);
            }
            if (e.getIsCheck().equals("true") && StringUtils.isEmpty(e.getTagDetailId())) {
                int count = pfBasSourceMapper.selectTaskAdjust(e.getPfSourceId(),e.getTagHeadId());
                if (count > 0) return AjaxResult.error("该企业待审批，不能关联任务");
                String id = java.util.UUID.randomUUID().toString();
                ConstructioninfoEntity entity = new ConstructioninfoEntity();
                e.setTagDetailId(id);
                e.setCreatedBy(userName);
                e.setUpdatedBy(userName);
                e.setBizSourceId(e.getPfSourceId());
                res = pfBasSourceMapper.insertTagDetail(e);

                if (pfBasSourceMapper.existsConstruction(e) == 0) {
                    entity.setPfSourceId(e.getPfSourceId());
                    entity.setTagHeadId(e.getTagHeadId());
                    entity.setUpdatedBy(userName);
                    entity.setCreatedBy(userName);
                    entity.setConstructionUnit("未设置");
                    entity.setConstructionPerson("未设置");
                    entity.setConstructionPhone("未设置");
                    entity.setConstructionStatus("0");
                    res = pfBasSourceMapper.insertConstructioninfo(entity);
                }
            }
        }
        return AjaxResult.success(res);
    }

    @Override
    public int insertTagDetailFJ(List<PfBasTagDetailEntity> list) {
        int res = 0;
        String userName = SecurityUtils.getUsername();
        String sourceId = "";
        if (list.size() > 0) {
            sourceId = list.get(0).getPfSourceId();
        }
        List<PfBasTagDetailEntity> tdList = pfBasSourceMapper.selectTagDetailFJ(sourceId);
        for (int i = list.size() - 1; i >= 0; i--) {
            PfBasTagDetailEntity item = list.get(i);
            if (item.getListType().equals("0")) {
                list.remove(item);
            }
        }

        for (int i = 0; i < tdList.size(); i++) {
            for (int j = list.size() - 1; j >= 0; j--) {
                PfBasTagDetailEntity item = list.get(j);
                if (item.getTagHeadId().equals(tdList.get(i).getTagHeadId())) {
                    list.remove(item);
                }
            }
        }

        for (PfBasTagDetailEntity e : list) {
            if (StringUtils.isEmpty(e.getIsCheck()))
                continue;
            if (e.getIsCheck().equals("false") && !StringUtils.isEmpty(e.getTagDetailId())) {
                ConstructioninfoEntity entity = new ConstructioninfoEntity();
                entity.setPfSourceId(e.getPfSourceId());
                entity.setTagHeadId(e.getTagHeadId());
                entity.setUpdatedBy(userName);
                e.setUpdatedBy(userName);
                res = pfBasSourceMapper.removeDetail(e);
                res = pfBasSourceMapper.removeConstructioninfo(entity);
            } else if (e.getIsCheck().equals("true") && StringUtils.isEmpty(e.getTagDetailId())) {
                String id = java.util.UUID.randomUUID().toString();
                ConstructioninfoEntity entity = new ConstructioninfoEntity();
                e.setTagDetailId(id);
                e.setCreatedBy(userName);
                e.setUpdatedBy(userName);
                e.setBizSourceId(e.getPfSourceId());
                res = pfBasSourceMapper.insertTagDetail(e);

                if (pfBasSourceMapper.existsConstruction(e) == 0) {
                    entity.setPfSourceId(e.getPfSourceId());
                    entity.setTagHeadId(e.getTagHeadId());
                    entity.setUpdatedBy(userName);
                    entity.setCreatedBy(userName);
                    entity.setConstructionUnit("未设置");
                    entity.setConstructionPerson("未设置");
                    entity.setConstructionPhone("未设置");
                    entity.setConstructionStatus("0");
                    res = pfBasSourceMapper.insertConstructioninfo(entity);
                }
            }
        }
        return res;
    }

    @Override
    public List<WadeTaskSummary> selectWadeTaskSummary(WadeTaskSummary w) {
        return pfBasSourceMapper.selectWadeTaskSummary(w);
    }

    @Override
    public List<GasTaskSummary> selectGasTaskSummary(GasTaskSummary g) {
        return pfBasSourceMapper.selectGasTaskSummary(g);
    }
}