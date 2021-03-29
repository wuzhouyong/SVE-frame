package com.ruoyi.project.youyan.service.impl;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.common.utils.CollectUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.common.domain.flowChart;
import com.ruoyi.project.youyan.domain.YYCPElementEntity;
import com.ruoyi.project.youyan.domain.YYFacility;
import com.ruoyi.project.youyan.mapper.YYCheckPointElementMapper;
import com.ruoyi.project.youyan.mapper.YYFacilityMapper;
import com.ruoyi.project.youyan.service.YYFacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 油烟治理设施信息Service业务层处理
 *
 * @author ruoyi
 * @date 2020-09-22
 */
@DataSource(value = DataSourceType.YOUYAN)
@Service
public class YYFacilityServiceImpl implements YYFacilityService {
    @Autowired
    private YYFacilityMapper facilityMapper;

    @Autowired
    private YYCheckPointElementMapper elementMapper;

    //查询治理设施
    public List<YYFacility> selectBasFacilityList(YYFacility facility) {
        String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        facility.setSourceId(sourceId);
        //查询出所有的排放口
        List<YYFacility> list = facilityMapper.selectBasFacilityPointList(facility);
        for (YYFacility entity : list) {
            List<YYFacility> list_t = getChildrenFacility(entity.getFacilityId(), entity.getDepthLevel());
            entity.setChildren(list_t);
        }
        return list;
    }

    //新增治理设施
    @Transactional(rollbackFor = Exception.class)
    public ResultEntity insertBasFacility(YYFacility facility) throws SQLException {
        String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        facility.setSourceId(sourceId);
        facility.setCreatedBy(SecurityUtils.getUsername());
        facility.setUpdatedBy(SecurityUtils.getUsername());
        int isAcc = facilityMapper.insertBasFacility(facility); //新增治理设施
        if (isAcc < 1) throw new SQLException("新增失败");
        facilityMapper.insertBasFacilityRelation(facility); //新增关联表
        String facilityType = facility.getFacilityType();
        String cpElemId = null;
        List<YYCPElementEntity> yyCPElementEntity = facility.getEntity(); //新增关联因
        if (StringUtils.isNotEmpty(yyCPElementEntity)) {
            //获取排放口id
            String facilityId = facilityMapper.selectFacilityId(facility.getFacilityId().toString());
            for (YYCPElementEntity entity : yyCPElementEntity) {
                entity.setOutletId(facilityId);//当前设施的顶级id
                entity.setFacilityId(facility.getFacilityId()); //当前设施的id
                int add = checkCheckpointElement(entity, facility, "add");
                if (add < 1) throw new SQLException("因子编码不能重复");
                if (StringUtils.isNotEmpty(facilityType)) {
                    if (facilityType.equals("yy_fj") || facilityType.equals("yy_jhq"))
                        cpElemId = elementMapper.selectCPElementIdByCondition(entity);//查询因子id
                }
            }
        } else throw new SQLException("请选择因子");

        if (StringUtils.isNotEmpty(cpElemId)) {
            facility.setCpElemId(Integer.parseInt(cpElemId));
            facilityMapper.updateBasFacilityElemId(facility); //给数据库表中因子id赋值
        }
        return ResultEntity.success(facility.getFacilityId(), "新增成功");
    }

    //修改治理设施
    @Transactional(rollbackFor = Exception.class)
    public ResultEntity updateBasFacility(YYFacility facility) throws SQLException {
        String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
//        String sourceId = "999999999999999";
        facility.setSourceId(sourceId);
        facility.setUpdatedBy(SecurityUtils.getUsername());
        int isAcc = facilityMapper.updateBasFacility(facility);
        if (isAcc < 1) throw new SQLException("新增失败");
        List<YYCPElementEntity> oldCPElemIds = elementMapper.selectCPElemIdByIdfacilityId(facility.getFacilityId()); //旧的因子ids
        Map<String, List<YYCPElementEntity>> map = CollectUtils.toOperate(facility.getEntity(), oldCPElemIds, "getCpElemId");
        //获取排放口id
        String facilityId = facilityMapper.selectFacilityId(facility.getFacilityId().toString());
        for (YYCPElementEntity entity : map.get("add")) { //新增
            entity.setOutletId(facilityId);
            entity.setFacilityId(facility.getFacilityId());
            int add = checkCheckpointElement(entity, facility, "add");
            if (add < 1) throw new SQLException("因子编码不能重复");
        }

        for (YYCPElementEntity entity : map.get("modify")) { //修改
            entity.setOutletId(facilityId);
            entity.setFacilityId(facility.getFacilityId());
            YYCPElementEntity entity_t = elementMapper.selectCPElementByElemId(entity.getCpElemId());
            if (StringUtils.isNull(entity_t)) throw new SQLException("修改因子不存在");
            if (!StringUtils.equals(entity_t.getEleCode(), entity.getEleCode())) {
                int update = checkCheckpointElement(entity, facility, "update");
                if (update < 1) throw new SQLException("因子编码不能重复");
            } else {
                entity.setUpdatedBy(SecurityUtils.getUsername());
                int isAcc_t = elementMapper.updateCPElement(entity);//修改因子管理
                if (isAcc_t < 1) throw new SQLException("因子修改失败");
            }
        }
        for (YYCPElementEntity entity : map.get("delete")) {  //删除
            entity.setFacilityId(facility.getFacilityId());
            entity.setUpdatedBy(SecurityUtils.getUsername());
            elementMapper.deleteCPElementById(entity);//删除因子
        }
        return ResultEntity.success(isAcc);
    }

    //删除治理设施
    @Transactional(rollbackFor = Exception.class)
    public ResultEntity deleteBasFacilityById(Integer facilityId) throws SQLException {
        YYFacility facility = new YYFacility();
        String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        String updatedBy = SecurityUtils.getUsername();
        facility.setUpdatedBy(updatedBy);
        facility.setSourceId(sourceId);
        Integer facilityId_t = facilityMapper.selectFacilityPid(facilityId);//查找子,有子集不能删除
        int isAcc = 0;
        if (facilityId_t == null) {
            facility.setFacilityId(facilityId);
            isAcc = facilityMapper.deleteBasFacilityById(facility);//删除治理设施
            if (isAcc < 1) throw new SQLException("删除失败");
            facilityMapper.deleteBasFacilityRelation(facilityId, updatedBy);//删除关联
            elementMapper.deleteCPElementByFacilityId(facilityId, updatedBy);//删除因子
        } else ResultEntity.error("不能删除有关联的治理设施");
        return ResultEntity.success(isAcc);
    }

    //根据id查找父级对象
    public ResultEntity selectSameLevelByfacilityId(Integer facilityId) {
        String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        List<YYFacility> list = facilityMapper.selectBasFacilityById(facilityId, sourceId);
        return ResultEntity.success(list);
    }

    //同步关联子级
    public ResultEntity updateBasFacilityRelation(Integer[] facilityPids, Integer facilityId) {
        Integer[] olePids = facilityMapper.selectFacilityPidById(facilityId);
        Map<String, List<Integer>> map = CollectUtils.toOperate(facilityPids, olePids);
        YYFacility facility = new YYFacility();
        facility.setUpdatedBy(SecurityUtils.getUsername());
        facility.setFacilityId(facilityId);
        for (Integer facilityPid : map.get("add")) {
            Integer count = facilityMapper.selectBasFacilityRelation(facilityPid, facilityId);
            facility.setFacilityPid(facilityPid);
            if (count == 0) {
                facilityMapper.insertBasFacilityRelation(facility);//新增关联表
            }
        }
        for (Integer facilityPid : map.get("delete")) {
            facility.setFacilityPid(facilityPid);
            facilityMapper.updateBasFacilityRelation(facility);//更新关联表
        }
        return ResultEntity.success("关联成功");
    }

    //根据父id和等级寻找它的下一级
    public List<YYFacility> getChildrenFacility(Integer facilityId, Integer depthLevel) {
        //根据父id和等级(自动加1)在他下面的一级查
        List<YYFacility> list = facilityMapper.selectEmissionByfacilityId(facilityId, depthLevel + 1);
        for (YYFacility entity : list) {
            entity.setChildren(getChildrenFacility(entity.getFacilityId(), entity.getDepthLevel()));
        }
        return list;
    }

    //检查因子是否重复
    public int checkCheckpointElement(YYCPElementEntity entity, YYFacility facility, String operating) {
        int has = elementMapper.repeatCheck(entity);
        if (has > 0) return 0;
        if (StringUtils.equals(operating, "add")) {
            YYCPElementEntity related = elementMapper.selectCPElementByCondition(entity);
            if (related != null) {
                related.setFacilityId(facility.getFacilityId());
                related.setOutletId(entity.getOutletId());
                int ount = elementMapper.updateCPElementByFacilityId(related);//关联因子
                if (ount < 0) return 0;
                facility.setCpElemId(related.getCpElemId());
                facilityMapper.updateBasFacilityElemId(facility); //给数据库表中因子id赋值
            } else {
                entity.setCreatedBy(SecurityUtils.getUsername());
                entity.setUpdatedBy(SecurityUtils.getUsername());
                elementMapper.insertCPElement(entity);//新增因子管理
                facility.setCpElemId(entity.getCpElemId());
                facilityMapper.updateBasFacilityElemId(facility); //给数据库表中因子id赋值
            }
        } else {
            entity.setUpdatedBy(SecurityUtils.getUsername());
            elementMapper.updateCPElement(entity);//修改因子管理
        }
        return 1;
    }

    /**
     * 根据企业ID生成设施树结构
     *
     * @param sourceID
     * @return
     */
    public List<flowChart> selectBasFacilityBySourceId(String sourceID) {
        List<Map> list = facilityMapper.selectBasFacilityBySourceId(sourceID);
        List<flowChart> tree = new ArrayList<>();
        if (list.size() > 0) {
            for (Map map : list) {
                if (map.get("PID") == null) {
                    flowChart newPoint = new flowChart();
                    newPoint.setId(map.get("ID").toString());
                    newPoint.setType(map.get("TYPE").toString());
                    newPoint.setName(map.get("NAME").toString());
                    tree.add(newPoint);
                    facility(newPoint, list);
                }
            }
        }

        return tree;
    }

    /**
     * 生成设施树
     */
    public void facility(flowChart inPoint, List<Map> data) {
        for (Map map : data) {
            if (map.get("PID") != null) {
                if (map.get("PID").toString().equals(String.valueOf(inPoint.getId()))) {
                    flowChart newPoint = new flowChart();
                    newPoint.setId(map.get("ID").toString());
                    newPoint.setType(map.get("TYPE").toString());
                    newPoint.setName(map.get("NAME").toString());
                    inPoint.addForList(newPoint);

                    facility(newPoint, data);
                }
            }
        }

        //添加出口模拟设施
        if (inPoint.getList().size() <= 0) {
            flowChart newPoint = new flowChart();
            newPoint.setId("0");
            newPoint.setType("yy_yt_0");
            newPoint.setName("源头排放");
            inPoint.addForList(newPoint);
        }
    }

    /**
     * 根据设施获取设施所有因子实时数据
     *
     * @param facID
     * @return
     */
    public List<Map> selectFacilityRealElementData(int facID) {
        return facilityMapper.selectFacilityRealElementData(facID);
    }

    /**
     * 根据设施获取所有设备运行情况
     *
     * @param facID
     * @return
     */
    public List<Map> selectFacilityRunStatus(int facID) {
        return facilityMapper.selectFacilityRunStatus(facID);
    }
}
