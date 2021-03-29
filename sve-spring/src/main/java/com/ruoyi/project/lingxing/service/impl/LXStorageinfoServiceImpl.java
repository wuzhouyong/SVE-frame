package com.ruoyi.project.lingxing.service.impl;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;
import com.google.common.util.concurrent.AtomicDouble;
import com.ruoyi.common.utils.DateStringUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.enterprise.domain.PfBasElementRef;
import com.ruoyi.project.enterprise.service.PfBasElementRefService;
import com.ruoyi.project.lingxing.domain.*;
import com.ruoyi.project.lingxing.mapper.LXCheckPointElementMapper;
import com.ruoyi.project.lingxing.mapper.LXStorageinfoMapper;
import com.ruoyi.project.lingxing.service.LXStorageinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author ruoyi
 * @date 2020-08-12
 */
@DataSource(value = DataSourceType.LINGXING)
@Service
public class LXStorageinfoServiceImpl implements LXStorageinfoService {
    @Autowired
    private LXStorageinfoMapper storageinfoMapper;

    @Autowired
    private LXCheckPointElementMapper cpElementMapper;

    @Autowired
    private PfBasElementRefService elementRefService;

    //查询废水收集池
    @Override
    public List<LXStorageinfo> selectStorageinfoList(LXStorageinfo storageinfo) {
        String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        storageinfo.setSourceId(sourceId);
        List<LXStorageinfo> list = storageinfoMapper.selectStorageinfoList(storageinfo);
        for (LXStorageinfo storage : list) {
            String storageId = storage.getStorageId();
            List<LXCPElementEntity> entity = cpElementMapper.selectCPElementById(storageId);
            storage.setEntity(entity);
        }
        return list;
    }

    //新增废水收集池
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertStorageinfo(LXStorageinfo storageinfo) throws SQLException {
        String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        storageinfo.setSourceId(sourceId);
        storageinfo.setCreatedBy(SecurityUtils.getUsername());
        storageinfo.setUpdatedBy(SecurityUtils.getUsername());
        storageinfo.setStorageId(UUID.randomUUID().toString());
        int isAcc = storageinfoMapper.insertStorageinfo(storageinfo);
        if (isAcc < 0) {
            throw new SQLException("新增失败");
        }
        List<LXCPElementEntity> list = storageinfo.getEntity();
        if (list != null && !list.isEmpty()) {
            for (LXCPElementEntity entity : list) {
                //新增因子管理
                if (cpElementMapper.repeatCheck(entity) == 0) {
                    entity.setFacilityId(storageinfo.getStorageId());
                    entity.setCreatedBy(SecurityUtils.getUsername());
                    entity.setUpdatedBy(SecurityUtils.getUsername());
                    int count = cpElementMapper.insertCPElementByEle(entity);
                    if (count < 0) {
                        throw new SQLException("新增因子失败");
                    }
                } else {
                    throw new SQLException("新增因子失败:因子编码已存在");
                }
            }
        } else throw new SQLException("请选择因子");

        return isAcc;
    }

    //编辑废水收集池
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateStorageinfo(LXStorageinfo storageinfo) throws SQLException {
        String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        storageinfo.setSourceId(sourceId);
        storageinfo.setUpdatedBy(SecurityUtils.getUsername());
        int isAcc = storageinfoMapper.updateStorageinfo(storageinfo);
        if (isAcc < 0) {
            throw new SQLException("编辑失败");
        }
        //查询旧的关联数采
        String collectId = cpElementMapper.selectCPElementByStorageId(storageinfo.getStorageId());
        if (!storageinfo.getCollectId().equals(collectId)) { //删除旧的数采
            LXCPElementEntity entity = new LXCPElementEntity();
            entity.setUpdatedBy(SecurityUtils.getUsername());
            entity.setCollectId(collectId);
            entity.setFacilityId(storageinfo.getStorageId());
            int count = cpElementMapper.delectCPElementByStorageId(entity);
            if (count < 0) {
                throw new SQLException("删除数采失败");
            }
            List<LXCPElementEntity> list = storageinfo.getEntity();
            for (LXCPElementEntity entity1 : list) {
                //新增因子管理
                if (cpElementMapper.repeatCheck(entity) == 0) {
                    entity1.setFacilityId(storageinfo.getStorageId());
                    entity1.setCreatedBy(SecurityUtils.getUsername());
                    entity1.setUpdatedBy(SecurityUtils.getUsername());
                    int count1 = cpElementMapper.insertCPElementByEle(entity1);
                    if (count1 < 0) {
                        throw new SQLException("新增数采失败");
                    }
                } else {
                    throw new SQLException("新增因子失败:因子编码已存在");
                }
            }
        }
        return isAcc;
    }

    //删除废水收集池
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteStorageinfo(String[] storageIds) throws SQLException {
        LXStorageinfo storageinfo = new LXStorageinfo();
        storageinfo.setStorageIds(storageIds);
        String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        storageinfo.setSourceId(sourceId);
        String updatedBy = SecurityUtils.getUsername();
        storageinfo.setUpdatedBy(updatedBy);
        int isAcc = storageinfoMapper.deleteStorageinfo(storageinfo);
        if (isAcc < 0) {
            throw new SQLException("删除失败");
        }
        //查询因子
        List<LXCPElementEntity> list = cpElementMapper.selectCPElementByIds(storageIds);
        if (list != null && !list.isEmpty()) {
            for (LXCPElementEntity entity : list) {
                entity.setUpdatedBy(SecurityUtils.getUsername());
                //删除因子
                int count = cpElementMapper.deleteCPElementById(entity);
                if (count < 0) {
                    throw new SQLException("删除因子失败");
                }
            }
        }
        return isAcc;
    }

    //查询废水表数据
    @Override
    public List<LXMeterinfo> selectMeterinfoList(LXMeterinfo meterinfo) {
        String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        meterinfo.setSourceId(sourceId);
        List<LXMeterinfo> list = storageinfoMapper.selectMeterinfoList(meterinfo);
        for (LXMeterinfo temp : list) {
            String meterId = temp.getMeterid();
            List<LXCPElementEntity> entity = cpElementMapper.selectCPElementById(meterId);
            temp.setEntity(entity);
        }
        return list;
    }

    //查询废水表
    @Override
    public LXMeterinfo selectMeterinfoById(LXMeterinfo meterinfo) {
        String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        meterinfo.setSourceId(sourceId);
        LXMeterinfo entity = storageinfoMapper.selectMeterinfoById(meterinfo);
        //查询因子
        List<LXCPElementEntity> element = cpElementMapper.selectCPElementById(meterinfo.getMeterid());
        entity.setEntity(element);
        return entity;
    }

    private void relElement(LXCPElementEntity entity) {
        PfBasElementRef model = new PfBasElementRef();
        model.setSrcTagHeadId(entity.getSrcTagHeadId());
        model.setSrcMN(entity.getSrcCollectId());
        model.setSrcElemId(entity.getSrcElemId());
        model.setDstTagHeadId(1003);
        model.setDstElemId(entity.getCpElemId());
        model.setCreatedBy(SecurityUtils.getUsername());
        model.setUpdatedBy(SecurityUtils.getUsername());
        elementRefService.insertPfBasElementRef(model);
    }

    //新增废水表
    @Override
//    @Transactional(rollbackFor = Exception.class)
    public int insertMeterinfo(LXMeterinfo meterinfo) throws SQLException {
        String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        meterinfo.setSourceId(sourceId);
        meterinfo.setCreatedBy(SecurityUtils.getUsername());
        meterinfo.setUpdatedBy(SecurityUtils.getUsername());
        meterinfo.setMeterid(UUID.randomUUID().toString());
        int isAcc = storageinfoMapper.insertMeterinfo(meterinfo);
        if (isAcc < 0) {
            throw new SQLException("新增失败");
        }
        List<LXCPElementEntity> list = meterinfo.getEntity();
        if (list != null && !list.isEmpty()) {
            for (LXCPElementEntity entity : list) {
                //新增因子管理
                entity.setFacilityId(meterinfo.getMeterid());
                entity.setCreatedBy(SecurityUtils.getUsername());
                entity.setUpdatedBy(SecurityUtils.getUsername());
                int count = cpElementMapper.insertCPElementByEle(entity);
                if (count < 0) {
                    throw new SQLException("新增因子失败");
                }
                if (meterinfo.getIsOther()) {
                    relElement(entity);
                }
            }
        } else throw new SQLException("请选择因子");
        return isAcc;
    }

    //编辑废水表
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateMeterinfo(LXMeterinfo meterinfo) throws SQLException {
        String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        meterinfo.setSourceId(sourceId);
        meterinfo.setUpdatedBy(SecurityUtils.getUsername());
        int isAcc = storageinfoMapper.updateMeterinfo(meterinfo);
        if (isAcc < 0) {
            throw new SQLException("编辑失败");
        }
        //查询旧的关联数采
        String collectId = cpElementMapper.selectCPElementByStorageId(meterinfo.getMeterid());

        if (!meterinfo.getCollectId().equals(collectId)) { //删除旧的数采
            LXCPElementEntity entity = new LXCPElementEntity();
            entity.setUpdatedBy(SecurityUtils.getUsername());
            entity.setCollectId(collectId);
            entity.setFacilityId(meterinfo.getMeterid());
            int count = cpElementMapper.delectCPElementByStorageId(entity);
            if (count < 0) {
                throw new SQLException("删除数采失败");
            }
            List<LXCPElementEntity> list = meterinfo.getEntity();
            for (LXCPElementEntity entity1 : list) {
                //新增因子管理
                entity1.setFacilityId(meterinfo.getMeterid());
                entity1.setCreatedBy(SecurityUtils.getUsername());
                entity1.setUpdatedBy(SecurityUtils.getUsername());
                int count1 = cpElementMapper.insertCPElementByEle(entity1);
                if (count1 < 0) {
                    throw new SQLException("新增数采失败");
                }
            }
        }
        return isAcc;
    }

    //删除废水表
    @Override
//    @Transactional(rollbackFor = Exception.class)
    public int deleteMeterinfo(String[] meterids) throws SQLException {
        LXMeterinfo meterinfo = new LXMeterinfo();
        meterinfo.setMeterids(meterids);
        String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        meterinfo.setSourceId(sourceId);
        String updatedBy = SecurityUtils.getUsername();
        meterinfo.setUpdatedBy(updatedBy);
        int isAcc = storageinfoMapper.deleteMeterinfo(meterinfo);
        if (isAcc < 0) {
            throw new SQLException("删除失败");
        }
        //查询因子
        List<LXCPElementEntity> list = cpElementMapper.selectCPElementByIds(meterids);
        if (list != null && !list.isEmpty()) {
            for (LXCPElementEntity entity : list) {
                entity.setUpdatedBy(SecurityUtils.getUsername());
                //删除因子
                int count = cpElementMapper.deleteCPElementById(entity);
                if (count < 0) {
                    throw new SQLException("删除因子失败");
                }
            }
        }
        return isAcc;
    }

    //查询数采
    @Override
    public String selectGetinfo(String collectId, String eleTypeId) {
        return storageinfoMapper.selectGetinfo(collectId, eleTypeId);
    }

    //水表统计
    @Override
    public List<Map<String, Object>> selectMeterinfoDay(String sourceId, String beginTime, String endTime) {
        if (beginTime == null && endTime == null) {
            beginTime = DateStringUtils.dateToString("yyyy-MM-dd", new Date((new Date().getTime() - 7 * 24 * 60 * 60 * 1000)));
            endTime = DateStringUtils.dateToString("yyyy-MM-dd", new Date());
        }
        List<LXDataSearch> searche = storageinfoMapper.selectMeterinfoDay(sourceId, beginTime, endTime);
        LinkedHashMap<String, List<LXDataSearch>> map = searche.stream().collect(Collectors.groupingBy(LXDataSearch::getDataDate,
                LinkedHashMap::new, Collectors.toList()));
        List<Map<String, Object>> outletList = new ArrayList<>();
        for (Map.Entry<String, List<LXDataSearch>> entry : map.entrySet()) {
            Map<String, Object> temp = new HashMap<>();
            temp.put("date", entry.getKey());
            List<LXDataSearch> value = entry.getValue();
            AtomicDouble sum = new AtomicDouble(0);
            value.forEach(x -> {
                        temp.put(x.getMeterName(), x.getCouAmt());
                        if (x.getCouAmt() == null) {
                            x.setCouAmt(0.0);
                        }
                        sum.addAndGet(Double.valueOf(x.getCouAmt()));
                    }
            );
            temp.put("count", sum.doubleValue());
            outletList.add(temp);
        }
        return outletList;
    }

    //水池统计分钟数
    @Override
    public List<Map<String, Object>> selectStoragefoMinute(String sourceId, String beginTime, String endTime) {
        if (beginTime == null && endTime == null) {
            beginTime = DateStringUtils.dateToString("yyyy-MM-dd HH:mm", new Date((new Date().getTime() - 7 * 24 * 60 * 60 * 1000)));
            endTime = DateStringUtils.dateToString("yyyy-MM-dd HH:mm", new Date());
        }
        List<LXDataSearch> searche = storageinfoMapper.selectStoragefoMinute(sourceId, beginTime, endTime);
        LinkedHashMap<String, List<LXDataSearch>> map = searche.stream().collect(Collectors.groupingBy(
                LXDataSearch::getStorageName, LinkedHashMap::new, Collectors.toList()));
        List<Map<String, Object>> outletList = new ArrayList<>();
        for (Map.Entry<String, List<LXDataSearch>> entry : map.entrySet()) {
            Map<String, Object> temp = new HashMap<>();
            temp.put("name", entry.getKey());
            List<LXDataSearch> value = entry.getValue();
            LinkedHashMap<String, List<LXDataSearch>> map1 = value.stream().collect(Collectors.groupingBy(LXDataSearch::getDataDate,
                    LinkedHashMap::new, Collectors.toList()));
            List<Map<String, Object>> lineList = new ArrayList<>();
            for (Map.Entry<String, List<LXDataSearch>> entry1 : map1.entrySet()) {
                Map<String, Object> temp1 = new HashMap<>();
                temp1.put("date", entry1.getKey());
                List<LXDataSearch> value1 = entry1.getValue();
                value1.forEach(x -> temp1.put(x.getElementDesc(), x.getCouAmt()));
                lineList.add(temp1);
            }
            temp.put("data", lineList);
            outletList.add(temp);
        }
        return outletList;
    }

    //水池统计分钟总数
    @Override
    public List<Map<String, Object>> selectStoragefoToalMinute(String sourceId, String beginTime) {
        if (beginTime == null) {
            beginTime = DateStringUtils.dateToString("yyyy-MM-dd HH:mm", new Date());
        }
        List<LXDataSearch> searche = storageinfoMapper.selectStoragefoToalMinute(sourceId, beginTime);

        LinkedHashMap<String, List<LXDataSearch>> map = searche.stream().collect(Collectors.groupingBy(LXDataSearch::getDataDate,
                LinkedHashMap::new, Collectors.toList()));
        List<Map<String, Object>> outletList = new ArrayList<>();
        for (Map.Entry<String, List<LXDataSearch>> entry : map.entrySet()) {
            Map<String, Object> temp = new HashMap<>();
            temp.put("date", entry.getKey());
            List<LXDataSearch> value = entry.getValue();
            AtomicDouble sum = new AtomicDouble(0);
            value.forEach(x -> {
                        temp.put(x.getStorageName(), x.getCouAmt());
                        temp.put("elementDesc", x.getElementDesc());
                        if (x.getCouAmt() == null) {
                            x.setCouAmt(0.0);
                        }
                        sum.addAndGet(Double.valueOf(x.getCouAmt()));
                    }
            );
            temp.put("count", sum.doubleValue());
            outletList.add(temp);
        }
        return outletList;
    }

    //水池统计小时数
    @Override
    public List<Map<String, Object>> selectStoragefoHour(String sourceId, String beginTime, String endTime) {
        if (beginTime == null && endTime == null) {
            beginTime = DateStringUtils.dateToString("yyyy-MM-dd", new Date((new Date().getTime() - 7 * 24 * 60 * 60 * 1000)));
            endTime = DateStringUtils.dateToString("yyyy-MM-dd", new Date());
        }
        List<LXDataSearch> searche = storageinfoMapper.selectStoragefoHour(sourceId, beginTime, endTime);
        LinkedHashMap<String, List<LXDataSearch>> map = searche.stream().collect(Collectors.groupingBy(
                LXDataSearch::getStorageName, LinkedHashMap::new, Collectors.toList()));
        List<Map<String, Object>> outletList = new ArrayList<>();
        for (Map.Entry<String, List<LXDataSearch>> entry : map.entrySet()) {
            Map<String, Object> temp = new HashMap<>();
            temp.put("name", entry.getKey());
            List<LXDataSearch> value = entry.getValue();
            LinkedHashMap<String, List<LXDataSearch>> map1 = value.stream().collect(Collectors.groupingBy(LXDataSearch::getDataDate,
                    LinkedHashMap::new, Collectors.toList()));
            List<Map<String, Object>> lineList = new ArrayList<>();
            for (Map.Entry<String, List<LXDataSearch>> entry1 : map1.entrySet()) {
                Map<String, Object> temp1 = new HashMap<>();
                temp1.put("date", entry1.getKey());
                List<LXDataSearch> value1 = entry1.getValue();
                value1.forEach(x -> temp1.put(x.getElementDesc(), x.getCouAmt()));
                lineList.add(temp1);
            }
            temp.put("data", lineList);
            outletList.add(temp);
        }
        return outletList;
    }

    //水池统计小时总数
    @Override
    public List<Map<String, Object>> selectStoragefoToalHour(String sourceId, String beginTime) {
        if (beginTime == null) {
            beginTime = DateStringUtils.dateToString("yyyy-MM-dd", new Date());
        }
        List<LXDataSearch> searche = storageinfoMapper.selectStoragefoToalHour(sourceId, beginTime);
        LinkedHashMap<String, List<LXDataSearch>> map = searche.stream().collect(Collectors.groupingBy(LXDataSearch::getDataDate,
                LinkedHashMap::new, Collectors.toList()));
        List<Map<String, Object>> outletList = new ArrayList<>();
        for (Map.Entry<String, List<LXDataSearch>> entry : map.entrySet()) {
            Map<String, Object> temp = new HashMap<>();
            temp.put("date", entry.getKey());
            List<LXDataSearch> value = entry.getValue();
            AtomicDouble sum = new AtomicDouble(0);
            value.forEach(x -> {
                        temp.put(x.getStorageName(), x.getCouAmt());
                        temp.put("elementDesc", x.getElementDesc());
                        if (x.getCouAmt() == null) {
                            x.setCouAmt(0.0);
                        }
                        sum.addAndGet(Double.valueOf(x.getCouAmt()));
                    }
            );
            temp.put("count", sum.doubleValue());
            outletList.add(temp);
        }
        return outletList;
    }

}
