package com.ruoyi.project.lingxing.service.impl;

import com.ruoyi.common.utils.HashMapUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.lingxing.domain.LXDriverinfo;
import com.ruoyi.project.lingxing.domain.LXTransjobdetail;
import com.ruoyi.project.lingxing.domain.LXTransjobhead;
import com.ruoyi.project.lingxing.mapper.LXTransjobheadMapper;
import com.ruoyi.project.lingxing.service.LXTransjobheadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 司机联单Service业务层处理及运输车司机Service业务层处理
 *
 * @author ruoyi
 * @date 2020-08-26
 */
@DataSource(value = DataSourceType.LINGXING)
@Service
public class LXTransjobheadServiceImpl implements LXTransjobheadService {
    @Autowired
    private LXTransjobheadMapper transjobheadMapper;

    // 查询工作单列表
    @Override
    public List<LXTransjobhead> selectTransjobheadList(LXTransjobhead transjobhead) {
        return transjobheadMapper.selectTransjobheadList(transjobhead);
    }

    // 查询司机联单列表
    @Override
    public List<LXTransjobdetail> selectTransjobdetailList(String jobHeadid) {
        return transjobheadMapper.selectTransjobdetailList(jobHeadid);
    }

    //查询产污企业联单列表
    @Override
    public List<LXTransjobdetail> selectJobDetailListBySourceId(LXTransjobdetail jobDetail) {
        return transjobheadMapper.selectJobDetailListBySourceId(jobDetail);
    }

    // 查询产污企业运水联单详情
    @Override
    public List<Map> selectTransBillinfo(String transbillid) {
        List<Map> mapList = HashMapUtils.toCamel(transjobheadMapper.selectTransBillinfo(transbillid));
        for (Map map : mapList) {
            map.forEach((k, v) -> {
                if (v != null) {
                    if (v.getClass().getSimpleName().equals("Timestamp")) {
                        map.replace(k, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(v));
                    }
                }
            });
        }
        return mapList;
    }

    //零星废水工况图
    @Override
    public Map selectWorkingConditionInfo(String sourceId) {
        List<Map> mapList = HashMapUtils.toCamel(transjobheadMapper.selectWorkingConditionInfo(sourceId));
        Map tempMap = new HashMap();
        for (Map map : mapList) {
            Date datadate = (Date) map.get("datadate");
/*            long diff = 0l;
            if (datadate != null) {
                diff = (new Date().getTime() - datadate.getTime()) / 1000 / 60;
            }*/
            if (datadate == null) {
                tempMap.put("E" + map.get("cpElemId"), "*");
                tempMap.put("SW" + map.get("cpElemId"), false);
            } else {
                tempMap.put("E" + map.get("cpElemId"), map.get("amount"));
            }
            if (map.get("rangeMax") != null) {
                tempMap.put("SW" + map.get("cpElemId"),
                        map.get("amount") != null && Double.parseDouble(map.get("amount").toString()) > Double.parseDouble(map.get("rangeMax").toString()) ? true : false);
            }
        }
        tempMap.put("datadate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        return tempMap;
    }

    //零星废水工况分钟数
    @Override
    public Map<String, Object> selectWorkingMinuteInfo(String cpElemId) {
        Map<String, Object> tempMap = new HashMap<>();
        Map<String, Object> desc = transjobheadMapper.selectElementDescById(cpElemId);
        tempMap.put("elementDesc", desc.get("UNIT") != null ? desc.get("ELEMENT_DESC") + "(" + desc.get("UNIT") + ")" : desc.get("ELEMENT_DESC"));
        List<Map> mapList = HashMapUtils.toCamel(transjobheadMapper.selectWorkingMinuteInfo(cpElemId));
        for (Map map : mapList) {
            map.forEach((k, v) -> {
                if (v != null) {
                    if (v.getClass().getSimpleName().equals("Timestamp")) {
                        map.replace(k, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(v));
                    }
                }
            });
        }
        tempMap.put("data", mapList);
        return tempMap;
    }

    /**
     * 查询运输车司机
     *
     * @param driverId 运输车司机ID
     * @return 运输车司机
     */
    @Override
    public LXDriverinfo selectDriverinfoById(String driverId) {
        return transjobheadMapper.selectDriverinfoById(driverId);
    }

    /**
     * 查询运输车司机列表
     *
     * @param driverinfo 运输车司机
     * @return 运输车司机
     */
    @Override
    public List<LXDriverinfo> selectDriverinfoList(LXDriverinfo driverinfo) {
        return transjobheadMapper.selectDriverinfoList(driverinfo);
    }

    /**
     * 新增运输车司机
     *
     * @param driverinfo 运输车司机
     * @return 结果
     */
    @Override
    public int insertDriverinfo(LXDriverinfo driverinfo) throws SQLException {
        int has = transjobheadMapper.repeatCheck(driverinfo);
        if (has == 0) {
            driverinfo.setCreatedBy(SecurityUtils.getUsername());
            driverinfo.setUpdatedBy(SecurityUtils.getUsername());
            int isAcc = transjobheadMapper.insertDriverinfo(driverinfo);
            if (isAcc < 0) {
                throw new SQLException("新增运输司机失败");
            }
            return isAcc;
        } else throw new SQLException("身份证和用户名不能重复");
    }

    /**
     * 修改运输车司机
     *
     * @param driverinfo 运输车司机
     * @return 结果
     */
    @Override
    public int updateDriverinfo(LXDriverinfo driverinfo) throws SQLException {
        int has = transjobheadMapper.repeatCheck(driverinfo);
        if (has == 0) {
            driverinfo.setUpdatedBy(SecurityUtils.getUsername());
            int isAcc = transjobheadMapper.updateDriverinfo(driverinfo);
            if (isAcc < 0) {
                throw new SQLException("修改运输车司机失败");
            }
            return isAcc;
        } else throw new SQLException("身份证和用户名不能重复");
    }

    /**
     * 批量删除运输车司机
     *
     * @param driverIds 需要删除的运输车司机ID
     * @return 结果
     */
    @Override
    public int deleteDriverinfoByIds(String[] driverIds) throws SQLException {
        String updatedBy = SecurityUtils.getUsername();
        int count = transjobheadMapper.deleteDriverinfoByIds(driverIds, updatedBy);
        if (count < 0) {
            throw new SQLException("删除运输车辆失败");
        }
        return count;
    }

    /**
     * 删除运输车司机信息
     *
     * @param driverId 运输车司机ID
     * @return 结果
     */
    @Override
    public int deleteDriverinfoById(String driverId) {
        return transjobheadMapper.deleteDriverinfoById(driverId);
    }

}
