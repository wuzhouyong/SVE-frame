package com.ruoyi.project.voc.service.impl;

//import com.mchange.lang.IntegerUtils;
import com.ruoyi.common.utils.HashMapUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.common.domain.flowChart;
import com.ruoyi.project.voc.domain.*;
import com.ruoyi.project.voc.mapper.VocBasFacilityMapper;
import com.ruoyi.project.voc.mapper.VocCheckPointElementMapper;
import com.ruoyi.project.voc.service.VocBasFacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Array;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author ruoyi
 * @date 2020-08-12
 */
@DataSource(value = DataSourceType.VOC)
@Service
public class VocBasFacilityServiceImpl implements VocBasFacilityService {
    @Autowired
    private VocBasFacilityMapper vocBasFacilityMapper;

    @Autowired
    private VocCheckPointElementMapper cpElementMapper;

    //查询生产线管理
    @Override
    public List<VocBasFacility> selectProductionList(VocBasFacility basFacility) {
        String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        basFacility.setSourceId(sourceId);
        List<VocBasFacility> list = vocBasFacilityMapper.selectProductionList(basFacility);
        for (VocBasFacility facilityEntity : list) {
            Integer facilityId = facilityEntity.getFacilityId();
            List<VocCPElementEntity> entity = cpElementMapper.selectCPElementList(facilityId);
            facilityEntity.setEntity(entity);
        }
        return list;
    }

    // 新增生产线管理
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertProduction(VocBasFacility basFacility, List<VocCPElementEntity> vocCPElementEntity) throws SQLException {
        basFacility.setCreatedBy(SecurityUtils.getUsername());
        basFacility.setUpdatedBy(SecurityUtils.getUsername());
        basFacility.setFacilityType("voc_scss");
        String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        basFacility.setSourceId(sourceId);
        Integer isAcc = vocBasFacilityMapper.insertBasFacility(basFacility);//新增生产线管理
        VocBasFacilityrelation vocRelation = new VocBasFacilityrelation();
        vocRelation.setFacilityId(basFacility.getFacilityId());
        vocRelation.setUpdatedBy(SecurityUtils.getUsername());
        vocBasFacilityMapper.insertBasFacilityrelation(vocRelation);//新增关联因子

        if (vocCPElementEntity != null && !vocCPElementEntity.isEmpty()) {
            for (VocCPElementEntity entity : vocCPElementEntity) {
                int has = cpElementMapper.repeatCheck(entity);
                if (has != 0) {
                    List<VocCPElementEntity> related = cpElementMapper.selectCPElementBySourceId(sourceId);
//                    List<VocCPElementEntity> norelated = cpElementMapper.selectCPElementByCondition(sourceId);
                    boolean match = related.stream().filter(f -> f.getCollectId().equals(entity.getCollectId()))
                            .filter(f -> f.getEleCode().equals(entity.getEleCode()))
                            .anyMatch(f -> f.getEleTypeId().equals(entity.getEleTypeId()));
                    if (!match) {
                        entity.setFacilityId(basFacility.getFacilityId());
                        cpElementMapper.updateCPElementByFacilityId(entity);//关联因子
                    } else throw new SQLException("因子编码不能重复");
                }
                if (has == 0) {
                    entity.setFacilityId(basFacility.getFacilityId());
                    entity.setCreatedBy(SecurityUtils.getUsername());
                    entity.setUpdatedBy(SecurityUtils.getUsername());
                    cpElementMapper.insertCPElement(entity);//新增因子管理
                }
            }
        } else throw new SQLException("请选择因子");
        return isAcc;
    }

    // 修改生产线管理
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateProduction(VocBasFacility basFacility, List<VocCPElementEntity> vocCPElementEntity) throws SQLException {
        basFacility.setUpdatedBy(SecurityUtils.getUsername());
        String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        basFacility.setSourceId(sourceId);
        int isAcc = vocBasFacilityMapper.updateBasFacility(basFacility);//修改生产线管理
        List<VocCPElementEntity> add = vocCPElementEntity.stream().filter(f -> f.getCpElemId() == null).collect(Collectors.toList());//差集 新增
        List<VocCPElementEntity> noadd = vocCPElementEntity.stream().filter(f -> f.getCpElemId() != null).collect(Collectors.toList());

        List<VocCPElementEntity> oldCPElemIds = cpElementMapper.selectCPElemIdByIdfacilityId(basFacility.getFacilityId()); //旧的因子ids
        List<VocCPElementEntity> modify = noadd.stream().filter(
                p -> oldCPElemIds.stream().anyMatch(a -> a.getCpElemId().equals(p.getCpElemId()))).collect(Collectors.toList());//并集 修改
        List<VocCPElementEntity> delete = oldCPElemIds.stream().filter(
                p -> !noadd.stream().anyMatch(a -> a.getCpElemId().equals(p.getCpElemId()))).collect(Collectors.toList());//差集 删除
        for (VocCPElementEntity entity : modify) { //修改因子
            VocCPElementEntity entity_t = cpElementMapper.selectCPElementByElemId(entity.getCpElemId());
            if (StringUtils.isNull(entity_t)) throw new SQLException("修改因子不存在");
            if (!StringUtils.equals(entity_t.getEleCode(), entity.getEleCode())) {
                int has = cpElementMapper.repeatCheck(entity);
                if (has != 0) {
                    List<VocCPElementEntity> related = cpElementMapper.selectCPElementBySourceId(sourceId);
//                    List<VocCPElementEntity> norelated = cpElementMapper.selectCPElementByCondition(sourceId);
                    boolean match = related.stream().filter(f -> f.getCollectId().equals(entity.getCollectId()))
                            .filter(f -> f.getEleCode().equals(entity.getEleCode()))
                            .anyMatch(f -> f.getEleTypeId().equals(entity.getEleTypeId()));
                    if (!match) {
                        entity.setFacilityId(basFacility.getFacilityId());
                        cpElementMapper.updateCPElementByFacilityId(entity);//关联因子
                    } else throw new SQLException("因子编码不能重复");
                }
                if (has == 0) {
                    entity.setUpdatedBy(SecurityUtils.getUsername());
                    cpElementMapper.updateCPElement(entity); //更新因子
                }
            } else {
                entity.setUpdatedBy(SecurityUtils.getUsername());
                int isAcc_t = cpElementMapper.updateCPElement(entity);//修改因子管理
                if (isAcc_t < 1) throw new SQLException("因子修改失败");
            }
        }
        for (VocCPElementEntity entity : add) { //新增因子
            int has = cpElementMapper.repeatCheck(entity);
            if (has != 0) {
                List<VocCPElementEntity> related = cpElementMapper.selectCPElementBySourceId(sourceId);
//                    List<VocCPElementEntity> norelated = cpElementMapper.selectCPElementByCondition(sourceId);
                boolean match = related.stream().filter(f -> f.getCollectId().equals(entity.getCollectId()))
                        .filter(f -> f.getEleCode().equals(entity.getEleCode()))
                        .anyMatch(f -> f.getEleTypeId().equals(entity.getEleTypeId()));
                if (!match) {
                    entity.setFacilityId(basFacility.getFacilityId());
                    cpElementMapper.updateCPElementByFacilityId(entity);//关联因子
                } else throw new SQLException("因子编码不能重复");
            }
            if (has == 0) {
                entity.setFacilityId(basFacility.getFacilityId());
                entity.setCreatedBy(SecurityUtils.getUsername());
                entity.setUpdatedBy(SecurityUtils.getUsername());
                cpElementMapper.insertCPElement(entity);//新增因子管理
            }
        }
        for (VocCPElementEntity entity : delete) {
            entity.setFacilityId(basFacility.getFacilityId());
            entity.setUpdatedBy(SecurityUtils.getUsername());
            cpElementMapper.deleteCPElementById(entity);//删除因子
        }
        return isAcc;
    }

    // 删除生产线管理
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteProduction(Integer[] facilityIds) {
        VocBasFacility basFacility = new VocBasFacility();
        basFacility.setFacilityPid(facilityIds);
        String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        basFacility.setSourceId(sourceId);
        String updatedBy = SecurityUtils.getUsername();
        basFacility.setUpdatedBy(updatedBy);
        int isAcc = vocBasFacilityMapper.deleteBasFacilityByIds(basFacility);//删除生产线管理失败
        cpElementMapper.deleteCPElementByFacilityIds(facilityIds, updatedBy);//删除因子失败
        return isAcc;
    }

    //查询处理设施
    @Override
    public List<VocBasFacility> selectHandleList(VocBasFacility basFacility) {
        String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        basFacility.setSourceId(sourceId);
        List<VocBasFacility> list = vocBasFacilityMapper.selectHandleList(basFacility);
        for (VocBasFacility facilityEntity : list) {
            Integer facilityId = facilityEntity.getFacilityId();
            Integer[] facilitypid = vocBasFacilityMapper.selectFacilityPidById(facilityId);
            facilityEntity.setFacilityPid(facilitypid);
            String[] pidName = vocBasFacilityMapper.selectFacilityPidNameById(facilitypid);
            facilityEntity.setPidName(pidName);
            List<VocBasTechnology> techno = vocBasFacilityMapper.selectVocBasTechnologyById(facilityId);
            if (techno != null && !techno.isEmpty()) {
                for (VocBasTechnology vocBasTechnology : techno) {
                    String techId = vocBasTechnology.getTechId();
                    List<VocCPElementEntity> entity = cpElementMapper.selectCPElementListByTechId(facilityId, techId);
                    vocBasTechnology.setEntity(entity);
                }
                facilityEntity.setTechno(techno);
            }
            //查询电表
            List<VocBasFacilityElecmeter> elecmeter = vocBasFacilityMapper.selectFacilityElecmeterById(facilityId);
            if (elecmeter != null && !elecmeter.isEmpty()) {
                for (VocBasFacilityElecmeter elecm : elecmeter) {
                    String elecmeterId = elecm.getElecmeterId();
                    List<VocCPElementEntity> entity = cpElementMapper.selectCPElementListByTechId(facilityId, elecmeterId);
                    elecm.setEntity(entity);
                }
                facilityEntity.setElecmeter(elecmeter);
            }
        }
        return list;
    }

    // 新增处理设施
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertHandleFacility(VocBasFacility basFacility, List<VocBasTechnology> techno) throws SQLException {
        basFacility.setCreatedBy(SecurityUtils.getUsername());
        basFacility.setUpdatedBy(SecurityUtils.getUsername());
        basFacility.setFacilityType("voc_clss");
        String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        basFacility.setSourceId(sourceId);
        int isAcc = vocBasFacilityMapper.insertBasFacility(basFacility);//新增治理设施
        Integer[] facilityPid = basFacility.getFacilityPid();//关联生产线
        if (facilityPid != null && facilityPid.length != 0) {
            VocBasFacilityrelation vocRelation = new VocBasFacilityrelation();
            vocRelation.setFacilityId(basFacility.getFacilityId());
            vocRelation.setUpdatedBy(SecurityUtils.getUsername());
            for (int i = 0; i < facilityPid.length; i++) {
                vocRelation.setFacilityPid(facilityPid[i]);
                vocBasFacilityMapper.insertBasFacilityrelation(vocRelation);//新增生产线
            }
        } else throw new SQLException("请选择关联生产线");

        //新增因子管理
        if (techno != null && !techno.isEmpty()) {
            for (VocBasTechnology technology : techno) {
                technology.setTechId(java.util.UUID.randomUUID().toString());
                technology.setUpdatedBy(SecurityUtils.getUsername());
                technology.setFacilityId(basFacility.getFacilityId());
                int count = vocBasFacilityMapper.insertTechnology(technology);
                if (count <= 0) {
                    throw new SQLException("新增治理设施工艺失败");
                }
                List<VocCPElementEntity> vocCPElementEntity = technology.getEntity();
                if (vocCPElementEntity != null && !vocCPElementEntity.isEmpty()) {
                    for (VocCPElementEntity entity : vocCPElementEntity) {
                        int has = cpElementMapper.repeatCheck(entity);
                        if (has != 0) {
                            List<VocCPElementEntity> related = cpElementMapper.selectCPElementBySourceId(sourceId);
                            boolean match = related.stream().filter(f -> f.getCollectId().equals(entity.getCollectId()))
                                    .filter(f -> f.getEleCode().equals(entity.getEleCode()))
                                    .anyMatch(f -> f.getEleTypeId().equals(entity.getEleTypeId()));
                            if (!match) {
                                entity.setFacilityId(basFacility.getFacilityId());
                                cpElementMapper.updateCPElementByFacilityId(entity);//关联因子
                            } else throw new SQLException("因子编码不能重复");
                        }
                        if (has == 0) {
                            entity.setFacilityId(basFacility.getFacilityId());
                            entity.setCreatedBy(SecurityUtils.getUsername());
                            entity.setUpdatedBy(SecurityUtils.getUsername());
                            entity.setTechId(technology.getTechId());
                            cpElementMapper.insertCPElement(entity);//新增因子管理
                        }
                    }
                } else throw new SQLException("请选择因子");
            }
        } else throw new SQLException("请选择治理设施工艺");

        //新增电表管理
        List<VocBasFacilityElecmeter> elecmeter = basFacility.getElecmeter();
        if (elecmeter != null && !elecmeter.isEmpty()) {
            for (VocBasFacilityElecmeter elecm : elecmeter) {
                elecm.setElecmeterId(java.util.UUID.randomUUID().toString());
                elecm.setUpdatedBy(SecurityUtils.getUsername());
                elecm.setCreatedBy(SecurityUtils.getUsername());
                elecm.setFacilityId(basFacility.getFacilityId());
                vocBasFacilityMapper.insertFacilityElecmeter(elecm); //新增治理电表
                List<VocCPElementEntity> vocCPElementEntity = elecm.getEntity();
                if (vocCPElementEntity != null && !vocCPElementEntity.isEmpty()) {
                    for (VocCPElementEntity entity : vocCPElementEntity) {
                        int has = cpElementMapper.repeatCheck(entity);
                        if (has != 0) {
                            List<VocCPElementEntity> related = cpElementMapper.selectCPElementBySourceId(sourceId);
//                    List<VocCPElementEntity> norelated = cpElementMapper.selectCPElementByCondition(sourceId);
                            boolean match = related.stream().filter(f -> f.getCollectId().equals(entity.getCollectId()))
                                    .filter(f -> f.getEleCode().equals(entity.getEleCode()))
                                    .anyMatch(f -> f.getEleTypeId().equals(entity.getEleTypeId()));
                            if (!match) {
                                entity.setFacilityId(basFacility.getFacilityId());
                                cpElementMapper.updateCPElementByFacilityId(entity);//关联因子
                            } else throw new SQLException("因子编码不能重复");
                        }
                        if (has == 0) {
                            entity.setFacilityId(basFacility.getFacilityId());
                            entity.setCreatedBy(SecurityUtils.getUsername());
                            entity.setUpdatedBy(SecurityUtils.getUsername());
                            entity.setTechId(elecm.getElecmeterId());
                            cpElementMapper.insertCPElement(entity);//新增因子管理
                        }
                    }
                } else throw new SQLException("请选择因子");
            }
        }
        return isAcc;
    }

    // 修改治理设施管理
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateHandle(VocBasFacility basFacility, List<VocBasTechnology> techno) throws SQLException {
        basFacility.setUpdatedBy(SecurityUtils.getUsername());
        String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        basFacility.setSourceId(sourceId);
        int isAcc = vocBasFacilityMapper.updateBasFacility(basFacility);//修改治理设施
        // 更新关联关系平台任务
        List<Integer> listNew = Stream.of(basFacility.getFacilityPid()).collect(Collectors.toList()); //前段传递过来的任务ids
        Integer[] oldPid = vocBasFacilityMapper.selectFacilityPidById(basFacility.getFacilityId()); //旧的任务ids
        List<Integer> listOld = Stream.of(oldPid).collect(Collectors.toList());
        List<Integer> tempList = listNew.stream().filter(num -> listOld.contains(num))
                .collect(Collectors.toList());
        List<Integer> addList = listNew.stream().filter(num -> !tempList.contains(num))
                .collect(Collectors.toList());
        List<Integer> removeList = listOld.stream().filter(num -> !tempList.contains(num))
                .collect(Collectors.toList());
        if (removeList != null && !removeList.isEmpty()) { //清除关联关系
            VocBasFacilityrelation entity = new VocBasFacilityrelation();
            entity.setFacilityId(basFacility.getFacilityId());
            entity.setUpdatedBy(SecurityUtils.getUsername());
            entity.setIsDeleted(1);
            for (Integer item : removeList) {
                entity.setFacilityPid(item);
                vocBasFacilityMapper.updateBasFacilityRelation(entity); //修改关联管理
            }
        }
        if (addList != null && !addList.isEmpty()) {
            for (Integer item : addList) {
                //通过id和pid去查找数据库中是否存在,而且已经被删除了的数
                int count = vocBasFacilityMapper.selectBasFacilityRelation(basFacility.getFacilityId(), item);
                VocBasFacilityrelation entity = new VocBasFacilityrelation();
                entity.setFacilityId(basFacility.getFacilityId());
                entity.setUpdatedBy(SecurityUtils.getUsername());
                if (count == 1) { //存在就更新,
                    entity.setFacilityPid(item);
                    entity.setIsDeleted(0);
                    vocBasFacilityMapper.updateBasFacilityRelation(entity);//修改关联管理
                } else { //不存在就添加
                    entity.setFacilityPid(item);
                    vocBasFacilityMapper.insertBasFacilityrelation(entity); //添加关联管理
                }
            }
        }

        //修改处理工艺
        List<VocBasTechnology> oldTechno = vocBasFacilityMapper.selectVocBasTechnologyById(basFacility.getFacilityId());//旧工艺
//        Map<String, List<VocBasTechnology>> tMap = CollectUtils.toOperate(techno, oldTechno, "getTechId");
        List<VocBasTechnology> modify = techno.stream().filter(p -> oldTechno.stream()
                .anyMatch(a -> a.getTechId().equals(p.getTechId()))).collect(Collectors.toList());//并集 修改
        List<VocBasTechnology> add = techno.stream().filter(p -> !oldTechno.stream()
                .anyMatch(a -> a.getTechId().equals(p.getTechId()))).collect(Collectors.toList());//差集 新增
        List<VocBasTechnology> delete = oldTechno.stream().filter(p -> !techno.stream().filter(f -> f.getTechId() != null)
                .anyMatch(a -> a.getTechId().equals(p.getTechId()))).collect(Collectors.toList());//差集 删除

        for (VocBasTechnology entity : modify) {
            entity.setUpdatedBy(SecurityUtils.getUsername());
            vocBasFacilityMapper.updateTechnology(entity);//修改治理设施
        }
        for (VocBasTechnology entity : add) {
            entity.setTechId(java.util.UUID.randomUUID().toString());
            entity.setUpdatedBy(SecurityUtils.getUsername());
            entity.setFacilityId(basFacility.getFacilityId());
            vocBasFacilityMapper.insertTechnology(entity); //新增治理设施工艺
        }
        for (VocBasTechnology entity : delete) {
            entity.setFacilityId(basFacility.getFacilityId());
            entity.setUpdatedBy(SecurityUtils.getUsername());
            vocBasFacilityMapper.deleteTechnologyById(entity); //删除治理设施工艺
        }

        if (!techno.isEmpty()) {
            for (VocBasTechnology technology : techno) {
                List<VocCPElementEntity> vocCPElementEntity = technology.getEntity();
                List<VocCPElementEntity> add1 = vocCPElementEntity.stream().filter(p -> p.getCpElemId() == null).collect(Collectors.toList());
                List<VocCPElementEntity> noadd1 = vocCPElementEntity.stream().filter(p -> p.getCpElemId() != null).collect(Collectors.toList());

                List<VocCPElementEntity> oldCPElemIds1 = cpElementMapper.selectCPElementListByTechId(basFacility.getFacilityId(), technology.getTechId()); //旧因子
                List<VocCPElementEntity> modify1 = noadd1.stream().filter(p -> oldCPElemIds1.stream()
                        .anyMatch(a -> a.getCpElemId().equals(p.getCpElemId()))).collect(Collectors.toList());//并集 修改
                List<VocCPElementEntity> delete1 = oldCPElemIds1.stream().filter(p -> !noadd1.stream()
                        .anyMatch(a -> a.getCpElemId().equals(p.getCpElemId()))).collect(Collectors.toList());//差集 删除
                for (VocCPElementEntity entity : modify1) {
                    VocCPElementEntity entity_t = cpElementMapper.selectCPElementByElemId(entity.getCpElemId());
                    if (StringUtils.isNull(entity_t)) throw new SQLException("修改因子不存在");
                    if (!StringUtils.equals(entity_t.getEleCode(), entity.getEleCode())) {
                        int has = cpElementMapper.repeatCheck(entity);
                        if (has != 0) {
                            //查询当前企业的治理工艺下所有关联的因子
                            List<VocCPElementEntity> related = cpElementMapper.selectCPElementBySourceId(sourceId);
                            boolean match = related.stream().filter(f -> f.getCollectId().equals(entity.getCollectId()))
                                    .filter(f -> f.getEleCode().equals(entity.getEleCode()))
                                    .anyMatch(f -> f.getEleTypeId().equals(entity.getEleTypeId()));
                            if (!match) {
                                entity.setFacilityId(basFacility.getFacilityId());
                                cpElementMapper.updateCPElementByFacilityId(entity);//关联因子
                            } else throw new SQLException("因子编码不能重复");
                        }
                        if (has == 0) {
                            entity.setUpdatedBy(SecurityUtils.getUsername());
                            cpElementMapper.updateCPElement(entity);//更新因子
                        }
                    } else {
                        entity.setUpdatedBy(SecurityUtils.getUsername());
                        int isAcc_t = cpElementMapper.updateCPElement(entity);//修改因子管理
                        if (isAcc_t < 1) throw new SQLException("因子修改失败");
                    }
                }
                for (VocCPElementEntity entity : add1) {
                    int has = cpElementMapper.repeatCheck(entity);
                    if (has != 0) {
                        List<VocCPElementEntity> related = cpElementMapper.selectCPElementBySourceId(sourceId);
                        boolean match = related.stream().filter(f -> f.getCollectId().equals(entity.getCollectId()))
                                .filter(f -> f.getEleCode().equals(entity.getEleCode()))
                                .anyMatch(f -> f.getEleTypeId().equals(entity.getEleTypeId()));
                        if (!match) {
                            entity.setFacilityId(basFacility.getFacilityId());
                            cpElementMapper.updateCPElementByFacilityId(entity);//关联因子
                        } else throw new SQLException("因子编码不能重复");
                    }
                    if (has == 0) {
                        entity.setFacilityId(basFacility.getFacilityId());
                        entity.setTechId(technology.getTechId());
                        entity.setCreatedBy(SecurityUtils.getUsername());
                        cpElementMapper.insertCPElement(entity);//新增因子管理
                    }
                }
                for (VocCPElementEntity entity : delete1) {
                    entity.setFacilityId(basFacility.getFacilityId());
                    entity.setUpdatedBy(SecurityUtils.getUsername());
                    cpElementMapper.deleteCPElementById(entity);//删除因子
                }
            }
        }
        //修改处理电表
        List<VocBasFacilityElecmeter> newElec = basFacility.getElecmeter(); //新的电表
        List<VocBasFacilityElecmeter> oldElec = vocBasFacilityMapper.selectFacilityElecmeterById(basFacility.getFacilityId());//旧电表

        List<VocBasFacilityElecmeter> modify1 = newElec.stream().filter(p -> oldElec.stream()
                .anyMatch(a -> a.getElecmeterId().equals(p.getElecmeterId()))).collect(Collectors.toList());//并集 修改
        List<VocBasFacilityElecmeter> add1 = newElec.stream().filter(p -> !oldElec.stream()
                .anyMatch(a -> a.getElecmeterId().equals(p.getElecmeterId()))).collect(Collectors.toList());//差集 新增
        List<VocBasFacilityElecmeter> delete1 = oldElec.stream().filter(p -> !newElec.stream().filter(f -> f.getElecmeterId() != null)
                .anyMatch(a -> a.getElecmeterId().equals(p.getElecmeterId()))).collect(Collectors.toList());//差集 删除

        for (VocBasFacilityElecmeter entity : modify1) {
            entity.setUpdatedBy(SecurityUtils.getUsername());
            vocBasFacilityMapper.updateFacilityElecmeter(entity);//修改治理设施电表
        }
        for (VocBasFacilityElecmeter elecm : add1) {
            elecm.setElecmeterId(java.util.UUID.randomUUID().toString());
            elecm.setUpdatedBy(SecurityUtils.getUsername());
            elecm.setCreatedBy(SecurityUtils.getUsername());
            elecm.setFacilityId(basFacility.getFacilityId());
            vocBasFacilityMapper.insertFacilityElecmeter(elecm);//新增治理电表失败
        }
        for (VocBasFacilityElecmeter entity : delete1) {
            entity.setFacilityId(basFacility.getFacilityId());
            entity.setUpdatedBy(SecurityUtils.getUsername());
            vocBasFacilityMapper.deleteFacilityElecmeterById(entity);//删除治理电表
        }

        if (!newElec.isEmpty()) {
            for (VocBasFacilityElecmeter technology : newElec) {
                List<VocCPElementEntity> vocCPElementEntity = technology.getEntity();
                List<VocCPElementEntity> add2 = vocCPElementEntity.stream().filter(p -> p.getCpElemId() == null).collect(Collectors.toList());
                List<VocCPElementEntity> noadd1 = vocCPElementEntity.stream().filter(p -> p.getCpElemId() != null).collect(Collectors.toList());

                List<VocCPElementEntity> oldCPElemIds1 = cpElementMapper.selectCPElementListByTechId(basFacility.getFacilityId(), technology.getElecmeterId()); //旧因子
                List<VocCPElementEntity> modify2 = noadd1.stream().filter(p -> oldCPElemIds1.stream()
                        .anyMatch(a -> a.getCpElemId().equals(p.getCpElemId()))).collect(Collectors.toList());//并集 修改
                List<VocCPElementEntity> delete2 = oldCPElemIds1.stream().filter(p -> !noadd1.stream()
                        .anyMatch(a -> a.getCpElemId().equals(p.getCpElemId()))).collect(Collectors.toList());//差集 删除
                for (VocCPElementEntity entity : modify2) { //修改因子
                    VocCPElementEntity entity_t = cpElementMapper.selectCPElementByElemId(entity.getCpElemId());
                    if (StringUtils.isNull(entity_t)) throw new SQLException("修改因子不存在");
                    if (!StringUtils.equals(entity_t.getEleCode(), entity.getEleCode())) {
                        int has = cpElementMapper.repeatCheck(entity);
                        if (has != 0) {
                            List<VocCPElementEntity> related = cpElementMapper.selectCPElementBySourceId(sourceId);
                            boolean match = related.stream().filter(f -> f.getCollectId().equals(entity.getCollectId()))
                                    .filter(f -> f.getEleCode().equals(entity.getEleCode()))
                                    .anyMatch(f -> f.getEleTypeId().equals(entity.getEleTypeId()));
                            if (!match) {
                                entity.setFacilityId(basFacility.getFacilityId());
                                cpElementMapper.updateCPElementByFacilityId(entity);//关联因子
                            } else throw new SQLException("因子编码不能重复");
                        }
                        if (has == 0) {
                            entity.setUpdatedBy(SecurityUtils.getUsername());
                            cpElementMapper.updateCPElement(entity);//修改因子管理
                        }
                    } else {
                        entity.setUpdatedBy(SecurityUtils.getUsername());
                        int isAcc_t = cpElementMapper.updateCPElement(entity);//修改因子管理
                        if (isAcc_t < 1) throw new SQLException("因子修改失败");
                    }
                }
                for (VocCPElementEntity entity : add2) {
                    int has = cpElementMapper.repeatCheck(entity);
                    if (has != 0) {
                        List<VocCPElementEntity> related = cpElementMapper.selectCPElementBySourceId(sourceId);
                        boolean match = related.stream().filter(f -> f.getCollectId().equals(entity.getCollectId()))
                                .filter(f -> f.getEleCode().equals(entity.getEleCode()))
                                .anyMatch(f -> f.getEleTypeId().equals(entity.getEleTypeId()));
                        if (!match) {
                            entity.setFacilityId(basFacility.getFacilityId());
                            cpElementMapper.updateCPElementByFacilityId(entity);//关联因子
                        } else throw new SQLException("因子编码不能重复");
                    }
                    if (has == 0) {
                        entity.setFacilityId(basFacility.getFacilityId());
                        entity.setTechId(technology.getElecmeterId());
                        entity.setCreatedBy(SecurityUtils.getUsername());
                        cpElementMapper.insertCPElement(entity);//新增因子管理
                    }
                }
                for (VocCPElementEntity entity : delete2) {
                    entity.setFacilityId(basFacility.getFacilityId());
                    entity.setUpdatedBy(SecurityUtils.getUsername());
                    cpElementMapper.deleteCPElementById(entity);//删除因子
                }
            }
        }
        return isAcc;
    }

    // 删除治理设施管理
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteHandle(Integer[] facilityIds) {
        VocBasFacility basFacility = new VocBasFacility();
        basFacility.setFacilityPid(facilityIds);
        String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        basFacility.setSourceId(sourceId);
        String updatedBy = SecurityUtils.getUsername();
        basFacility.setUpdatedBy(updatedBy);
        int isAcc = vocBasFacilityMapper.deleteBasFacilityByIds(basFacility);//删除生产线管理
        vocBasFacilityMapper.deleteBasFacilityRelation(facilityIds, updatedBy);//删除关联关系
        cpElementMapper.deleteCPElementByFacilityIds(facilityIds, updatedBy);//删除治理设施管理
        vocBasFacilityMapper.deleteFacilityElecmeterByIds(facilityIds, updatedBy);//删除电表
        cpElementMapper.deleteCPElementByElecmeterIds(facilityIds, updatedBy);//删除电表下的因子
        vocBasFacilityMapper.deleteTechnologyByFacilityIds(facilityIds, updatedBy);//删除工艺
        cpElementMapper.deleteCPElementByTechIds(facilityIds, updatedBy);//删除工艺下的因子
        return isAcc;
    }

    //查询收集风机管理
    @Override
    public List<VocBasFacility> selectCollectList(VocBasFacility basFacility) {
        String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        basFacility.setSourceId(sourceId);
        List<VocBasFacility> list = vocBasFacilityMapper.selectCollectList(basFacility);
        for (VocBasFacility facilityEntity : list) {
            Integer facilityId = facilityEntity.getFacilityId();
            Integer[] facilitypid = vocBasFacilityMapper.selectFacilityPidById(facilityId);
            facilityEntity.setFacilityPid(facilitypid);
            String[] pidName = vocBasFacilityMapper.selectFacilityPidNameById(facilitypid);
            facilityEntity.setPidName(pidName);
            List<VocCPElementEntity> entity = cpElementMapper.selectCPElementList(facilityId);
            facilityEntity.setEntity(entity);
        }
        return list;
    }

    // 新增收集风机管理
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertCollect(VocBasFacility basFacility, List<VocCPElementEntity> vocCPElementEntity) throws
            SQLException {
        basFacility.setCreatedBy(SecurityUtils.getUsername());
        basFacility.setUpdatedBy(SecurityUtils.getUsername());
        basFacility.setFacilityType("voc_sjfj");
        String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        basFacility.setSourceId(sourceId);
        int isAcc = vocBasFacilityMapper.insertBasFacility(basFacility);//新增收集风机管理
        Integer[] facilityPid = basFacility.getFacilityPid();
        if (facilityPid != null && facilityPid.length != 0) {
            VocBasFacilityrelation vocRelation = new VocBasFacilityrelation();
            vocRelation.setFacilityId(basFacility.getFacilityId());
            vocRelation.setUpdatedBy(SecurityUtils.getUsername());
            for (int i = 0; i < facilityPid.length; i++) {
                vocRelation.setFacilityPid(facilityPid[i]);
                vocBasFacilityMapper.insertBasFacilityrelation(vocRelation);//新增关联生产线
            }
        }
        //新增因子管理
        if (vocCPElementEntity != null && !vocCPElementEntity.isEmpty()) {
            for (VocCPElementEntity entity : vocCPElementEntity) {
                int has = cpElementMapper.repeatCheck(entity);
                if (has != 0) {
                    List<VocCPElementEntity> related = cpElementMapper.selectCPElementBySourceId(sourceId);
//                    List<VocCPElementEntity> norelated = cpElementMapper.selectCPElementByCondition(sourceId);
                    boolean match = related.stream().filter(f -> f.getCollectId().equals(entity.getCollectId()))
                            .filter(f -> f.getEleCode().equals(entity.getEleCode()))
                            .anyMatch(f -> f.getEleTypeId().equals(entity.getEleTypeId()));
                    if (!match) {
                        entity.setFacilityId(basFacility.getFacilityId());
                        cpElementMapper.updateCPElementByFacilityId(entity);//关联因子
                    } else throw new SQLException("因子编码不能重复");
                }
                if (has == 0) {
                    entity.setFacilityId(basFacility.getFacilityId());
                    entity.setCreatedBy(SecurityUtils.getUsername());
                    entity.setUpdatedBy(SecurityUtils.getUsername());
                    cpElementMapper.insertCPElement(entity);//新增因子管理
                }
            }
        } else throw new SQLException("请选择因子");
        return isAcc;
    }

    // 修改收集风机管理
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateCollectBlowerModify(VocBasFacility basFacility, List<VocCPElementEntity> vocCPElementEntity) throws
            SQLException {
        String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        basFacility.setSourceId(sourceId);
        basFacility.setUpdatedBy(SecurityUtils.getUsername());
        int isAcc = vocBasFacilityMapper.updateBasFacility(basFacility);//修改治理设施管理
        // 更新关联关系平台任务
        List<Integer> listNew = Stream.of(basFacility.getFacilityPid()).collect(Collectors.toList()); //前段传递过来的任务ids
        Integer[] oldPid = vocBasFacilityMapper.selectFacilityPidById(basFacility.getFacilityId()); //旧的任务ids
        List<Integer> listOld = Stream.of(oldPid).collect(Collectors.toList());
        //交集
        List<Integer> tempList = listNew.stream().filter(num -> listOld.contains(num))
                .collect(Collectors.toList());
        List<Integer> addList = listNew.stream().filter(num -> !tempList.contains(num))
                .collect(Collectors.toList());
        List<Integer> removeList = listOld.stream().filter(num -> !tempList.contains(num))
                .collect(Collectors.toList());
        if (removeList != null && !removeList.isEmpty()) { //清除关联关系
            VocBasFacilityrelation entity = new VocBasFacilityrelation();
            entity.setFacilityId(basFacility.getFacilityId());
            entity.setUpdatedBy(SecurityUtils.getUsername());
            entity.setIsDeleted(1);
            for (Integer item : removeList) {
                entity.setFacilityPid(item);
                vocBasFacilityMapper.updateBasFacilityRelation(entity);//修改关联管理
            }
        }
        if (addList != null && !addList.isEmpty()) {
            for (Integer item : addList) {
                //通过id和pid去查找数据库中是否存在,而且已经被删除了的数
                int count = vocBasFacilityMapper.selectBasFacilityRelation(basFacility.getFacilityId(), item);
                VocBasFacilityrelation entity = new VocBasFacilityrelation();
                entity.setFacilityId(basFacility.getFacilityId());
                entity.setUpdatedBy(SecurityUtils.getUsername());
                if (count == 1) { //存在就更新,
                    entity.setFacilityPid(item);
                    entity.setIsDeleted(0);
                    vocBasFacilityMapper.updateBasFacilityRelation(entity);//修改关联管理
                } else { //不存在就添加
                    entity.setFacilityPid(item);
                    vocBasFacilityMapper.insertBasFacilityrelation(entity);//添加关联管理
                }
            }
        }

        List<VocCPElementEntity> add = vocCPElementEntity.stream().filter(f -> f.getCpElemId() == null).collect(Collectors.toList());//差集 新增
        List<VocCPElementEntity> noadd = vocCPElementEntity.stream().filter(f -> f.getCpElemId() != null).collect(Collectors.toList());

        List<VocCPElementEntity> oldCPElemIds = cpElementMapper.selectCPElemIdByIdfacilityId(basFacility.getFacilityId()); //旧的因子ids
        List<VocCPElementEntity> modify = noadd.stream().filter(
                p -> oldCPElemIds.stream().anyMatch(a -> a.getCpElemId().equals(p.getCpElemId()))).collect(Collectors.toList());//并集 修改
        List<VocCPElementEntity> delete = oldCPElemIds.stream().filter(
                p -> !noadd.stream().anyMatch(a -> a.getCpElemId().equals(p.getCpElemId()))).collect(Collectors.toList());//差集 删除
        for (VocCPElementEntity entity : modify) { //修改因子
            VocCPElementEntity entity_t = cpElementMapper.selectCPElementByElemId(entity.getCpElemId());
            if (StringUtils.isNull(entity_t)) throw new SQLException("修改因子不存在");
            if (!StringUtils.equals(entity_t.getEleCode(), entity.getEleCode())) {
                int has = cpElementMapper.repeatCheck(entity);
                if (has != 0) {
                    List<VocCPElementEntity> related = cpElementMapper.selectCPElementBySourceId(sourceId);
//                    List<VocCPElementEntity> norelated = cpElementMapper.selectCPElementByCondition(sourceId);
                    boolean match = related.stream().filter(f -> f.getCollectId().equals(entity.getCollectId()))
                            .filter(f -> f.getEleCode().equals(entity.getEleCode()))
                            .anyMatch(f -> f.getEleTypeId().equals(entity.getEleTypeId()));
                    if (!match) {
                        entity.setFacilityId(basFacility.getFacilityId());
                        cpElementMapper.updateCPElementByFacilityId(entity);//关联因子
                    } else throw new SQLException("因子编码不能重复");
                }
                if (has == 0) {
                    entity.setUpdatedBy(SecurityUtils.getUsername());
                    cpElementMapper.updateCPElement(entity);//修改因子管理
                }
            } else {
                entity.setUpdatedBy(SecurityUtils.getUsername());
                int isAcc_t = cpElementMapper.updateCPElement(entity);//修改因子管理
                if (isAcc_t < 1) throw new SQLException("因子修改失败");
            }
        }
        for (VocCPElementEntity entity : add) { //新增因子
            int has = cpElementMapper.repeatCheck(entity);
            if (has != 0) {
                List<VocCPElementEntity> related = cpElementMapper.selectCPElementBySourceId(sourceId);
//                    List<VocCPElementEntity> norelated = cpElementMapper.selectCPElementByCondition(sourceId);
                boolean match = related.stream().filter(f -> f.getCollectId().equals(entity.getCollectId()))
                        .filter(f -> f.getEleCode().equals(entity.getEleCode()))
                        .anyMatch(f -> f.getEleTypeId().equals(entity.getEleTypeId()));
                if (!match) {
                    entity.setFacilityId(basFacility.getFacilityId());
                    cpElementMapper.updateCPElementByFacilityId(entity);//关联因子
                } else throw new SQLException("因子编码不能重复");
            }
            if (has == 0) {
                entity.setFacilityId(basFacility.getFacilityId());
                entity.setCreatedBy(SecurityUtils.getUsername());
                entity.setUpdatedBy(SecurityUtils.getUsername());
                cpElementMapper.insertCPElement(entity);//新增因子管理
            }
        }
        for (VocCPElementEntity entity : delete) {
            entity.setFacilityId(basFacility.getFacilityId());
            entity.setUpdatedBy(SecurityUtils.getUsername());
            cpElementMapper.deleteCPElementById(entity);//删除因子

        }
        return isAcc;
    }

    // 删除收集风机管理
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteCollectBlower(Integer[] facilityIds) {
        VocBasFacility basFacility = new VocBasFacility();
        basFacility.setFacilityPid(facilityIds);
        String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        basFacility.setSourceId(sourceId);
        String updatedBy = SecurityUtils.getUsername();
        basFacility.setUpdatedBy(updatedBy);
        int isAcc = vocBasFacilityMapper.deleteBasFacilityByIds(basFacility);//删除生产线管理
        vocBasFacilityMapper.deleteBasFacilityRelation(facilityIds, updatedBy);//删除关联
        cpElementMapper.deleteCPElementByFacilityIds(facilityIds, updatedBy);//删除因子

        return isAcc;
    }

    //查询排放口管理
    @Override
    public List<VocBasFacility> selectEmissionPointList(VocBasFacility basFacility) {
        String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        basFacility.setSourceId(sourceId);
        List<VocBasFacility> list = vocBasFacilityMapper.selectEmissionPointList(basFacility);
        for (VocBasFacility facilityEntity : list) {
            Integer facilityId = facilityEntity.getFacilityId();
            Integer[] facilitypid = vocBasFacilityMapper.selectFacilityPidById(facilityId);
            facilityEntity.setFacilityPid(facilitypid);
            facilityEntity.setPidName(null);
            String[] pidName = vocBasFacilityMapper.selectFacilityPidNameById(facilitypid);
            facilityEntity.setPidName(pidName);
            List<VocCPElementEntity> entity = cpElementMapper.selectCPElementList(facilityId);
            facilityEntity.setEntity(entity);
        }
        return list;
    }

    // 新增排放口管理
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertEmissionPoint(VocBasFacility basFacility, List<VocCPElementEntity> vocCPElementEntity) throws
            SQLException {
        basFacility.setCreatedBy(SecurityUtils.getUsername());
        basFacility.setUpdatedBy(SecurityUtils.getUsername());
        basFacility.setFacilityType("voc_pfk");
        String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        basFacility.setSourceId(sourceId);
        int isAcc = vocBasFacilityMapper.insertBasFacility(basFacility);//新增排放口管理

        Integer[] facilityPid = basFacility.getFacilityPid();
        if (facilityPid != null && facilityPid.length != 0) {
            VocBasFacilityrelation vocRelation = new VocBasFacilityrelation();
            vocRelation.setFacilityId(basFacility.getFacilityId());
            vocRelation.setUpdatedBy(SecurityUtils.getUsername());
            for (int i = 0; i < facilityPid.length; i++) {
                vocRelation.setFacilityPid(facilityPid[i]);
                vocBasFacilityMapper.insertBasFacilityrelation(vocRelation);//新增关联治理
            }
        }
        if (vocCPElementEntity != null && !vocCPElementEntity.isEmpty()) {
            for (VocCPElementEntity entity : vocCPElementEntity) {
                int has = cpElementMapper.repeatCheck(entity);
                if (has != 0) {
                    List<VocCPElementEntity> related = cpElementMapper.selectCPElementBySourceId(sourceId);
//                    List<VocCPElementEntity> norelated = cpElementMapper.selectCPElementByCondition(sourceId);
                    boolean match = related.stream().filter(f -> f.getCollectId().equals(entity.getCollectId()))
                            .filter(f -> f.getEleCode().equals(entity.getEleCode()))
                            .anyMatch(f -> f.getEleTypeId().equals(entity.getEleTypeId()));
                    if (!match) {
                        entity.setFacilityId(basFacility.getFacilityId());
                        cpElementMapper.updateCPElementByFacilityId(entity);//关联因子
                    } else throw new SQLException("因子编码不能重复");
                }
                if (has == 0) {
                    entity.setFacilityId(basFacility.getFacilityId());
                    entity.setCreatedBy(SecurityUtils.getUsername());
                    entity.setUpdatedBy(SecurityUtils.getUsername());
                    cpElementMapper.insertCPElement(entity);//新增因子管理
                }
            }
        } else throw new SQLException("请选择因子");

        return isAcc;
    }

    // 修改排放口管理
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateEmissionPointrModify(VocBasFacility basFacility,
                                          List<VocCPElementEntity> vocCPElementEntity) throws SQLException {
        String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        basFacility.setSourceId(sourceId);
        basFacility.setUpdatedBy(SecurityUtils.getUsername());
        int isAcc = vocBasFacilityMapper.updateBasFacility(basFacility);//修改治理设施管理
        List<Integer> listNew = Stream.of(basFacility.getFacilityPid()).collect(Collectors.toList()); //前段传递过来的任务ids
        Integer[] oldPid = vocBasFacilityMapper.selectFacilityPidById(basFacility.getFacilityId()); //旧的任务ids
        List<Integer> listOld = Stream.of(oldPid).collect(Collectors.toList());
        List<Integer> tempList = listNew.stream().filter(num -> listOld.contains(num))
                .collect(Collectors.toList());
        List<Integer> addList = listNew.stream().filter(num -> !tempList.contains(num))
                .collect(Collectors.toList());
        List<Integer> removeList = listOld.stream().filter(num -> !tempList.contains(num))
                .collect(Collectors.toList());
        if (removeList != null && !removeList.isEmpty()) { //清除关联关系
            VocBasFacilityrelation entity = new VocBasFacilityrelation();
            entity.setFacilityId(basFacility.getFacilityId());
            entity.setUpdatedBy(SecurityUtils.getUsername());
            entity.setIsDeleted(1);
            for (Integer item : removeList) {
                entity.setFacilityPid(item);
                vocBasFacilityMapper.updateBasFacilityRelation(entity);//修改关联管理
            }
        }
        if (addList != null && !addList.isEmpty()) {
            for (Integer item : addList) {
                //通过id和pid去查找数据库中是否存在,而且已经被删除了的数
                int count = vocBasFacilityMapper.selectBasFacilityRelation(basFacility.getFacilityId(), item);
                VocBasFacilityrelation entity = new VocBasFacilityrelation();
                entity.setFacilityId(basFacility.getFacilityId());
                entity.setUpdatedBy(SecurityUtils.getUsername());
                if (count == 1) { //存在就更新,
                    entity.setFacilityPid(item);
                    entity.setIsDeleted(0);
                    vocBasFacilityMapper.updateBasFacilityRelation(entity);//修改关联管理
                } else { //不存在就添加
                    entity.setFacilityPid(item);
                    vocBasFacilityMapper.insertBasFacilityrelation(entity);//新增关联管理

                }
            }
        }
        List<VocCPElementEntity> add = vocCPElementEntity.stream().filter(f -> f.getCpElemId() == null).collect(Collectors.toList());//差集 新增
        List<VocCPElementEntity> noadd = vocCPElementEntity.stream().filter(f -> f.getCpElemId() != null).collect(Collectors.toList());
        List<VocCPElementEntity> oldCPElemIds = cpElementMapper.selectCPElemIdByIdfacilityId(basFacility.getFacilityId()); //旧的因子ids
        List<VocCPElementEntity> modify = noadd.stream().filter(
                p -> oldCPElemIds.stream().anyMatch(a -> a.getCpElemId().equals(p.getCpElemId()))).collect(Collectors.toList());//并集 修改
        List<VocCPElementEntity> delete = oldCPElemIds.stream().filter(
                p -> !noadd.stream().anyMatch(a -> a.getCpElemId().equals(p.getCpElemId()))).collect(Collectors.toList());//差集 删除
        for (VocCPElementEntity entity : modify) {//修改因子
            VocCPElementEntity entity_t = cpElementMapper.selectCPElementByElemId(entity.getCpElemId());
            if (StringUtils.isNull(entity_t)) throw new SQLException("修改因子不存在");
            if (!StringUtils.equals(entity_t.getEleCode(), entity.getEleCode())) {
                int has = cpElementMapper.repeatCheck(entity);
                if (has != 0) {
                    List<VocCPElementEntity> related = cpElementMapper.selectCPElementBySourceId(sourceId);
//                    List<VocCPElementEntity> norelated = cpElementMapper.selectCPElementByCondition(sourceId);
                    boolean match = related.stream().filter(f -> f.getCollectId().equals(entity.getCollectId()))
                            .filter(f -> f.getEleCode().equals(entity.getEleCode()))
                            .anyMatch(f -> f.getEleTypeId().equals(entity.getEleTypeId()));
                    if (!match) {
                        entity.setFacilityId(basFacility.getFacilityId());
                        cpElementMapper.updateCPElementByFacilityId(entity);//关联因子
                    } else throw new SQLException("因子编码不能重复");
                }
                if (has == 0) {
                    entity.setUpdatedBy(SecurityUtils.getUsername());
                    cpElementMapper.updateCPElement(entity);//修改因子管理
                }
            } else {
                entity.setUpdatedBy(SecurityUtils.getUsername());
                int isAcc_t = cpElementMapper.updateCPElement(entity);//修改因子管理
                if (isAcc_t < 1) throw new SQLException("因子修改失败");
            }
        }
        for (VocCPElementEntity entity : add) { //新增因子
            int has = cpElementMapper.repeatCheck(entity);
            if (has != 0) {
                List<VocCPElementEntity> related = cpElementMapper.selectCPElementBySourceId(sourceId);
//                    List<VocCPElementEntity> norelated = cpElementMapper.selectCPElementByCondition(sourceId);
                boolean match = related.stream().filter(f -> f.getCollectId().equals(entity.getCollectId()))
                        .filter(f -> f.getEleCode().equals(entity.getEleCode()))
                        .anyMatch(f -> f.getEleTypeId().equals(entity.getEleTypeId()));
                if (!match) {
                    entity.setFacilityId(basFacility.getFacilityId());
                    cpElementMapper.updateCPElementByFacilityId(entity);//关联因子
                } else throw new SQLException("因子编码不能重复");
            }
            if (has == 0) {
                entity.setFacilityId(basFacility.getFacilityId());
                entity.setCreatedBy(SecurityUtils.getUsername());
                entity.setUpdatedBy(SecurityUtils.getUsername());
                cpElementMapper.insertCPElement(entity);//新增因子管理
            }
        }
        for (VocCPElementEntity entity : delete) {
            entity.setFacilityId(basFacility.getFacilityId());
            entity.setUpdatedBy(SecurityUtils.getUsername());
            cpElementMapper.deleteCPElementById(entity);//删除因子
        }
        return isAcc;
    }

    // 删除排放口管理
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteEmissionPointr(Integer[] facilityIds) {
        VocBasFacility basFacility = new VocBasFacility();
        basFacility.setFacilityPid(facilityIds);
        String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        basFacility.setSourceId(sourceId);
        String updatedBy = SecurityUtils.getUsername();
        basFacility.setUpdatedBy(updatedBy);
        int isAcc = vocBasFacilityMapper.deleteBasFacilityByIds(basFacility);//删除关联关系
        vocBasFacilityMapper.deleteBasFacilityRelation(facilityIds, updatedBy);//删除关联
        cpElementMapper.deleteCPElementByFacilityIds(facilityIds, updatedBy);//删除因子
        return isAcc;
    }

    //查询排放风机管理
    @Override
    public List<VocBasFacility> selectBlowerList(VocBasFacility basFacility) {
        String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        basFacility.setSourceId(sourceId);
        List<VocBasFacility> list = vocBasFacilityMapper.selectBlowerList(basFacility);
        for (VocBasFacility facilityEntity : list) {
            Integer facilityId = facilityEntity.getFacilityId();
            Integer[] facilitypid = vocBasFacilityMapper.selectFacilityPidById(facilityId);
            facilityEntity.setFacilityPid(facilitypid);
            String[] pidName = vocBasFacilityMapper.selectFacilityPidNameById(facilitypid);
            facilityEntity.setPidName(pidName);
            List<VocCPElementEntity> entity = cpElementMapper.selectCPElementList(facilityId);
            facilityEntity.setEntity(entity);
        }
        return list;
    }

    // 新增排放风机管理
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertBlower(VocBasFacility basFacility, List<VocCPElementEntity> vocCPElementEntity) throws
            SQLException {
        basFacility.setCreatedBy(SecurityUtils.getUsername());
        basFacility.setUpdatedBy(SecurityUtils.getUsername());
        basFacility.setFacilityType("voc_pffj");
        String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        basFacility.setSourceId(sourceId);
        int isAcc = vocBasFacilityMapper.insertBasFacility(basFacility);//新增排放风机管理
        Integer[] facilityPid = basFacility.getFacilityPid();
        if (facilityPid != null && facilityPid.length != 0) {
            VocBasFacilityrelation vocRelation = new VocBasFacilityrelation();
            vocRelation.setFacilityId(basFacility.getFacilityId());
            vocRelation.setUpdatedBy(SecurityUtils.getUsername());
            for (int i = 0; i < facilityPid.length; i++) {
                vocRelation.setFacilityPid(facilityPid[i]);
                vocBasFacilityMapper.insertBasFacilityrelation(vocRelation);//新增排放口
            }
        }
        //新增因子管理
        if (vocCPElementEntity != null && !vocCPElementEntity.isEmpty()) {
            for (VocCPElementEntity entity : vocCPElementEntity) {
                int has = cpElementMapper.repeatCheck(entity);
                if (has != 0) {
                    List<VocCPElementEntity> related = cpElementMapper.selectCPElementBySourceId(sourceId);
//                    List<VocCPElementEntity> norelated = cpElementMapper.selectCPElementByCondition(sourceId);
                    boolean match = related.stream().filter(f -> f.getCollectId().equals(entity.getCollectId()))
                            .filter(f -> f.getEleCode().equals(entity.getEleCode()))
                            .anyMatch(f -> f.getEleTypeId().equals(entity.getEleTypeId()));
                    if (!match) {
                        entity.setFacilityId(basFacility.getFacilityId());
                        cpElementMapper.updateCPElementByFacilityId(entity);//关联因子
                    } else throw new SQLException("因子编码不能重复");
                }
                if (has == 0) {
                    entity.setFacilityId(basFacility.getFacilityId());
                    entity.setCreatedBy(SecurityUtils.getUsername());
                    entity.setUpdatedBy(SecurityUtils.getUsername());
                    cpElementMapper.insertCPElement(entity);//新增因子管理
                }
            }
        } else throw new SQLException("请选择因子");
        return isAcc;
    }

    // 修改排放风机管理
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateEmissionBlowerModify(VocBasFacility basFacility,
                                          List<VocCPElementEntity> vocCPElementEntity) throws SQLException {
        String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        basFacility.setSourceId(sourceId);
        basFacility.setUpdatedBy(SecurityUtils.getUsername());
        int isAcc = vocBasFacilityMapper.updateBasFacility(basFacility);//修改治理设施管理
        // 更新关联关系平台任务
        List<Integer> listNew = Stream.of(basFacility.getFacilityPid()).collect(Collectors.toList()); //前段传递过来的任务ids
        Integer[] oldPid = vocBasFacilityMapper.selectFacilityPidById(basFacility.getFacilityId()); //旧的任务ids
        List<Integer> listOld = Stream.of(oldPid).collect(Collectors.toList());
        List<Integer> tempList = listNew.stream().filter(num -> listOld.contains(num))
                .collect(Collectors.toList());
        List<Integer> addList = listNew.stream().filter(num -> !tempList.contains(num))
                .collect(Collectors.toList());
        List<Integer> removeList = listOld.stream().filter(num -> !tempList.contains(num))
                .collect(Collectors.toList());
        if (removeList != null && !removeList.isEmpty()) { //清除关联关系
            VocBasFacilityrelation entity = new VocBasFacilityrelation();
            entity.setFacilityId(basFacility.getFacilityId());
            entity.setUpdatedBy(SecurityUtils.getUsername());
            entity.setIsDeleted(1);
            for (Integer item : removeList) {
                entity.setFacilityPid(item);
                vocBasFacilityMapper.updateBasFacilityRelation(entity);//修改关联管理
            }
        }
        if (addList != null && !addList.isEmpty()) {
            for (Integer item : addList) {
                //通过id和pid去查找数据库中是否存在,而且已经被删除了的数
                int count = vocBasFacilityMapper.selectBasFacilityRelation(basFacility.getFacilityId(), item);
                VocBasFacilityrelation entity = new VocBasFacilityrelation();
                entity.setFacilityId(basFacility.getFacilityId());
                entity.setUpdatedBy(SecurityUtils.getUsername());
                if (count == 1) { //存在就更新,
                    entity.setFacilityPid(item);
                    entity.setIsDeleted(0);
                    vocBasFacilityMapper.updateBasFacilityRelation(entity);//修改关联管理
                } else { //不存在就添加
                    entity.setFacilityPid(item);
                    vocBasFacilityMapper.insertBasFacilityrelation(entity);//新增关联管理
                }
            }
        }
        List<VocCPElementEntity> add = vocCPElementEntity.stream().filter(f -> f.getCpElemId() == null).collect(Collectors.toList());//差集 新增
        List<VocCPElementEntity> noadd = vocCPElementEntity.stream().filter(f -> f.getCpElemId() != null).collect(Collectors.toList());
        List<VocCPElementEntity> oldCPElemIds = cpElementMapper.selectCPElemIdByIdfacilityId(basFacility.getFacilityId()); //旧的因子ids
        List<VocCPElementEntity> modify = noadd.stream().filter(
                p -> oldCPElemIds.stream().anyMatch(a -> a.getCpElemId().equals(p.getCpElemId()))).collect(Collectors.toList());//并集 修改
        List<VocCPElementEntity> delete = oldCPElemIds.stream().filter(
                p -> !noadd.stream().anyMatch(a -> a.getCpElemId().equals(p.getCpElemId()))).collect(Collectors.toList());//差集 删除
        for (VocCPElementEntity entity : modify) { //修改因子
            VocCPElementEntity entity_t = cpElementMapper.selectCPElementByElemId(entity.getCpElemId());
            if (StringUtils.isNull(entity_t)) throw new SQLException("修改因子不存在");
            if (!StringUtils.equals(entity_t.getEleCode(), entity.getEleCode())) {
                int has = cpElementMapper.repeatCheck(entity);
                if (has != 0) {
                    List<VocCPElementEntity> related = cpElementMapper.selectCPElementBySourceId(sourceId);
//                    List<VocCPElementEntity> norelated = cpElementMapper.selectCPElementByCondition(sourceId);
                    boolean match = related.stream().filter(f -> f.getCollectId().equals(entity.getCollectId()))
                            .filter(f -> f.getEleCode().equals(entity.getEleCode()))
                            .anyMatch(f -> f.getEleTypeId().equals(entity.getEleTypeId()));
                    if (!match) {
                        entity.setFacilityId(basFacility.getFacilityId());
                        cpElementMapper.updateCPElementByFacilityId(entity);//关联因子
                    } else throw new SQLException("因子编码不能重复");
                }
                if (has == 0) {
                    entity.setUpdatedBy(SecurityUtils.getUsername());
                    cpElementMapper.updateCPElement(entity);//修改因子管理
                }
            } else {
                entity.setUpdatedBy(SecurityUtils.getUsername());
                int isAcc_t = cpElementMapper.updateCPElement(entity);//修改因子管理
                if (isAcc_t < 1) throw new SQLException("因子修改失败");
            }
        }
        for (VocCPElementEntity entity : add) { //新增因子
            int has = cpElementMapper.repeatCheck(entity);
            if (has != 0) {
                List<VocCPElementEntity> related = cpElementMapper.selectCPElementBySourceId(sourceId);
//                    List<VocCPElementEntity> norelated = cpElementMapper.selectCPElementByCondition(sourceId);
                boolean match = related.stream().filter(f -> f.getCollectId().equals(entity.getCollectId()))
                        .filter(f -> f.getEleCode().equals(entity.getEleCode()))
                        .anyMatch(f -> f.getEleTypeId().equals(entity.getEleTypeId()));
                if (!match) {
                    entity.setFacilityId(basFacility.getFacilityId());
                    cpElementMapper.updateCPElementByFacilityId(entity);//关联因子
                } else throw new SQLException("因子编码不能重复");
            }
            if (has == 0) {
                entity.setFacilityId(basFacility.getFacilityId());
                entity.setCreatedBy(SecurityUtils.getUsername());
                entity.setUpdatedBy(SecurityUtils.getUsername());
                cpElementMapper.insertCPElement(entity);//新增因子管理
            }
        }
        for (VocCPElementEntity entity : delete) {
            entity.setFacilityId(basFacility.getFacilityId());
            entity.setUpdatedBy(SecurityUtils.getUsername());
            cpElementMapper.deleteCPElementById(entity);//删除因子
        }
        return isAcc;
    }

    // 删除排放风机管理
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteEmissionBlower(Integer[] facilityIds) {
        VocBasFacility basFacility = new VocBasFacility();
        basFacility.setFacilityPid(facilityIds);
        String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        basFacility.setSourceId(sourceId);
        String updatedBy = SecurityUtils.getUsername();
        basFacility.setUpdatedBy(updatedBy);
        int isAcc = vocBasFacilityMapper.deleteBasFacilityByIds(basFacility);//删除生产线管理
        vocBasFacilityMapper.deleteBasFacilityRelation(facilityIds, updatedBy);//删除关联失败
        cpElementMapper.deleteCPElementByFacilityIds(facilityIds, updatedBy);//删除因子
        return isAcc;
    }

    // 查询治理设施工况详情列表
    @Override
    public List<Map<String, Object>> workingConditionDetails(String sourceId) {
        List<Map<String, Object>> data = new ArrayList<>();
        List<VocBasFacility> list = vocBasFacilityMapper.selectFacilityBySourceId(sourceId); //治理设施
        for (VocBasFacility facility : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", facility.getFacilityId());
            map.put("name", facility.getFacilityName());
            Integer facilityId = facility.getFacilityId();
            // 工艺
            List<Technology> techno = vocBasFacilityMapper.selectBasTechnologyByFacilityId(facilityId); //工艺
            List<Map<String, Object>> techList = new ArrayList<>();
            techno.forEach(e -> {
                Map<String, Object> techMap = new HashMap<>();
                techMap.put("id", e.getFacilityId());
                techMap.put("name", e.getFacilityName());
                techMap.put("amount", e.getAmount());
                techMap.put("status", e.isStatus());
                techList.add(techMap);
            });
            map.put("tech", techList);
            // 生产线电表
            List<Technology> line = vocBasFacilityMapper.selectLineMeterByFacilityId(facilityId); //生产线
            List<Map<String, Object>> gatherList = new ArrayList<>();
            LinkedHashMap<String, List<Technology>> lineGroup = line.stream().collect(Collectors.groupingBy(Technology::getFacilityId, LinkedHashMap::new, Collectors.toList()));
            List<Map<String, Object>> lineList = new ArrayList<>();
            lineGroup.forEach((k, v) -> {
                Map<String, Object> lineMap = new HashMap<>();
                lineMap.put("id", k);
                Optional<Technology> optional = v.stream().filter(f -> f.getFacilityId().equals(k)).findFirst();
                optional.ifPresent(technology -> lineMap.put("name", technology.getFacilityName()));
                v.forEach(e -> lineMap.put(e.getElementCode(), e.getAmount()));
                lineList.add(lineMap);

                List<Technology> gather = vocBasFacilityMapper.selectGatherFanByFacilityId(Integer.valueOf(k)); //收集风机
                gather.forEach(e -> {
                    Map<String, Object> gatherMap = new HashMap<>();
                    gatherMap.put("id", e.getFacilityId());
                    gatherMap.put("name", e.getFacilityName());
                    gatherMap.put("amount", e.getAmount());
                    gatherMap.put("status", e.isStatus());
                    gatherList.add(gatherMap);
                });
            });
            map.put("line", lineList);
            map.put("gather", gatherList.stream().distinct().collect(Collectors.toList()));

            // 排放口
            List<Technology> outlet = vocBasFacilityMapper.selectDischargeByFacilityId(facilityId); //排放口
            List<Map<String, Object>> dischargeList = new ArrayList<>();
            LinkedHashMap<String, List<Technology>> outletGroup = outlet.stream().collect(Collectors.groupingBy(Technology::getFacilityId, LinkedHashMap::new, Collectors.toList()));
            List<Map<String, Object>> outletList = new ArrayList<>();
            outletGroup.forEach((k, v) -> {
                Map<String, Object> outletMap = new HashMap<>();
                outletMap.put("id", k);
                Optional<Technology> optional = v.stream().filter(f -> f.getFacilityId().equals(k)).findFirst();
                optional.ifPresent(technology -> outletMap.put("name", technology.getFacilityName()));
                v.forEach(e -> outletMap.put(e.getElementCode(), e.getAmount()));
                outletList.add(outletMap);

                List<Technology> discharge = vocBasFacilityMapper.selectDischargeFanByFacilityId(Integer.valueOf(k)); //排放风机
                discharge.forEach(e -> {
                    Map<String, Object> dischargeMap = new HashMap<>();
                    dischargeMap.put("id", e.getFacilityId());
                    dischargeMap.put("name", e.getFacilityName());
                    dischargeMap.put("amount", e.getAmount());
                    dischargeMap.put("status", e.isStatus());
                    dischargeList.add(dischargeMap);
                });
            });
            map.put("outlet", outletList);
            map.put("discharge", dischargeList.stream().distinct().collect(Collectors.toList()));
            //排放风机
            data.add(map);
        }

        return data;
    }

    // 查询治理设施工况详情列表 条件
    @Override
    public Map<String, Object> workingConditionGetinfo(String sourceId, String time) {
        Map<String, Object> map = new HashMap();
        //生产线电表24小时均值
        List<Map> line_meter = vocBasFacilityMapper.selectLineMeterByCondition(sourceId, time);
        List<Map> temp = HashMapUtils.toCamel(line_meter);
        map.put("line_meter", temp);

        //排放风机开关状态
        List<Map> gather_fan = vocBasFacilityMapper.selectGatherFanByCondition(sourceId, time);
        List<Map> temp1 = HashMapUtils.toCamel(gather_fan);
        map.put("gather_fan", temp1);

        //排放口
        List<Map> discharge = vocBasFacilityMapper.selectDischargeByCondition(sourceId, time);
        List<Map> temp3 = HashMapUtils.toCamel(discharge);
        map.put("discharge", temp3);

        //治理设施工况 UV光解+喷淋
        List<Map> facility = vocBasFacilityMapper.selectFacilityByCondition(sourceId, time);
        List<Map> temp4 = HashMapUtils.toCamel(facility);
        map.put("facility", temp4);

        //治理设施工况 活性炭+催化燃烧
        List<Map> techno = vocBasFacilityMapper.selectTechnoByCondition(sourceId, time);
        List<Map> temp5 = HashMapUtils.toCamel(techno);
        map.put("techno", temp5);
        return map;

    }


    /**
     * 获取voc企业相关设施结构图
     *
     * @param sourceId
     * @return
     */
    @Override
    public List<flowChart> getFlowFacilityBySourceId(String sourceId) {
        List<flowChart> chart = new ArrayList<>();
        List<VocBasFacility> list = vocBasFacilityMapper.selectAllFacilityBySourceId(sourceId); //治理设施

        list.forEach(r -> {
            // 工艺
            List<Map> techno = vocBasFacilityMapper.selectTechnologyByFacilityId(r.getFacilityId());

            //获取相关排放口与排放风机
            List<Map> out = vocBasFacilityMapper.selctOutAndFanByFacilityId(r.getFacilityId());


            flowChart c = new flowChart();
            c.setId(r.getFacilityId().toString());
            c.setType(r.getFacilityType() + "_" + r.getFacilityId());
            c.setName(r.getFacilityName());
            if (!techno.isEmpty())
                c.setOther(HashMapUtils.toCamel(techno));
            if (!out.isEmpty())
                c.setExtend(HashMapUtils.toCamel(out));
            chart.add(c);

            //设施关取的生产线
            List<VocBasFacility> workshop = vocBasFacilityMapper.selectWorkshopByFacilityId(r.getFacilityId());
            //设施关取的风机
            List<Map> workfan = vocBasFacilityMapper.selectWorkFanByFacilityId(r.getFacilityId());

            //去重取风机
            ArrayList<Integer> fanIds = new ArrayList();
            workfan.forEach(f -> {
                if (!fanIds.contains(Integer.parseInt(f.get("FACILITY_ID").toString()))) {
                    fanIds.add(Integer.parseInt(f.get("FACILITY_ID").toString()));

                    flowChart fan = new flowChart();
                    fan.setId(f.get("FACILITY_ID").toString());
                    fan.setType(f.get("FACILITY_TYPE") + "_" + f.get("FACILITY_ID"));
                    fan.setName(f.get("FACILITY_NAME").toString());

                    c.getList().add(fan);
                }
            });

            ArrayList<String> works = new ArrayList<>();
            c.getList().forEach(fs -> {
                Map inFan = new HashMap();
                //风机关联到车间
                workfan.forEach(f -> {
                    if (fs.getId().equals(f.get("FACILITY_ID").toString())) {
                        //风机车间列表
                        workshop.forEach(ws -> {
                            if (ws.getFacilityId().toString().equals(f.get("FACILITY_PID").toString())) {
                                flowChart work = new flowChart();
                                work.setId(ws.getFacilityId().toString());
                                work.setType(ws.getFacilityType() + "_" + ws.getFacilityId());
                                work.setName(ws.getFacilityName());
                                fs.getList().add(work);

//                                if(works.contains(f.get("FACILITY_PID").toString()))
//                                    work.setFlag("same");

                                works.add(ws.getFacilityId().toString());
                            }
                        });
                    }
                });
            });

        });


        return chart;
    }

    /**
     * 设施获取所有因子与实时值
     *
     * @param
     * @return
     */
    public List<Map> selectVocElemntTagVal(String facilityId) {
        return vocBasFacilityMapper.selectVocElemntTagVal(Integer.valueOf(facilityId));
    }

    /**
     * 获取设施所甩设备状态
     *
     * @param facId
     * @return
     */
    public List<Map> getVocFacilityRunStatus(String facId) {
        List<Technology> techno = vocBasFacilityMapper.selectBasTechnologyByFacilityId(Integer.valueOf(facId));
        //设施工艺状态
        List<Map> trun = new ArrayList<>();
        techno.forEach(t -> {
            Map r = new HashMap();
            r.put("id", t.getFacilityId());
            r.put("run", t.isStatus());
            trun.add(r);
        });
        List<Map> run = new ArrayList<>();
        Map out = new HashMap();
        out.put("id", facId);
        out.put("run", trun);
        run.add(out);

        //车间状态，因参数不明先定义全为工作态
        List<VocBasFacility> workshop = vocBasFacilityMapper.selectWorkshopByFacilityId(Integer.valueOf(facId));
        workshop.forEach(w -> {
            Map r = new HashMap();
            r.put("id", w.getFacilityId());
            r.put("run", true);
            run.add(r);
        });

        //收集风机状态
        List<Map> sFan = vocBasFacilityMapper.getSFanRunStatus(Integer.valueOf(facId));
        sFan.forEach(s -> {
            Map r = new HashMap();
            r.put("id", s.get("ID"));
            r.put("run", s.get("RUN").toString().equals("1"));
            run.add(r);
        });

        //排放口与排放风机
        List<Map> sOut = vocBasFacilityMapper.getOutFanStatus(Integer.valueOf(facId));
        sOut.forEach(o -> {
            Map r = new HashMap();
            r.put("id", o.get("ID"));
            r.put("run", o.get("RUN").toString().equals("1"));
            run.add(r);
        });

        return run;
    }
}
