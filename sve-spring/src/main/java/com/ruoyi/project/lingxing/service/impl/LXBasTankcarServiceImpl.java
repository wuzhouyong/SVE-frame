package com.ruoyi.project.lingxing.service.impl;

import com.ruoyi.common.utils.HashMapUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.lingxing.domain.LXBasTankcar;
import com.ruoyi.project.lingxing.domain.LXBasTrucktank;
import com.ruoyi.project.lingxing.domain.LXDatCargps;
import com.ruoyi.project.lingxing.domain.LXTransjobhead;
import com.ruoyi.project.lingxing.mapper.LXBasTankcarMapper;
import com.ruoyi.project.lingxing.mapper.LXTransjobheadMapper;
import com.ruoyi.project.lingxing.service.LXBasTankcarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author ruoyi
 * @date 2020-08-25
 */
@Service
@DataSource(value = DataSourceType.LINGXING)
public class LXBasTankcarServiceImpl implements LXBasTankcarService {
    @Autowired
    private LXBasTankcarMapper basTankcarMapper;

    @Autowired
    private LXTransjobheadMapper transjobheadMapper;

    //查询处置企业
    public List<Map> selectEnterpriseList() {
        return HashMapUtils.toCamel(basTankcarMapper.selectEnterpriseList());
    }

    //查询运输车辆
    @Override
    public List<LXBasTankcar> selectBasTankcarList(LXBasTankcar basTankcar) {
        return basTankcarMapper.selectBasTankcarList(basTankcar);
    }

    //新增运输车辆
    @Override
    public int insertBasTankcar(LXBasTankcar basTankcar) throws SQLException {
        basTankcar.setCreatedBy(SecurityUtils.getUsername());
        basTankcar.setUpdatedBy(SecurityUtils.getUsername());
        int isAcc = basTankcarMapper.insertBasTankcar(basTankcar);
        if (isAcc < 0) {
            throw new SQLException("新增运输车辆失败");
        }
        return isAcc;
    }

    //编辑运输车辆
    @Override
    public int updateBasTankcar(LXBasTankcar basTankcar) throws SQLException {
        basTankcar.setUpdatedBy(SecurityUtils.getUsername());
        int isAcc = basTankcarMapper.updateBasTankcar(basTankcar);
        if (isAcc < 0) {
            throw new SQLException("新增运输车辆失败");
        }
        return isAcc;
    }

    //删除运输车辆
    @Override
    public int deleteBasTankcarByIds(Integer[] carIds) throws SQLException {
        String updatedBy = SecurityUtils.getUsername();
        int count = basTankcarMapper.deleteBasTankcarByIds(carIds, updatedBy);
        if (count < 0) {
            throw new SQLException("删除运输车辆失败");
        }
        return count;
    }

    /**
     * 查询【请填写功能名称】
     *
     * @param carId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public LXBasTankcar selectBasTankcarById(Integer carId) {
        return basTankcarMapper.selectBasTankcarById(carId);
    }

    //查询车辆水箱
    @Override
    public List<LXBasTrucktank> selectTrucktankList(LXBasTrucktank trucktank) {
        return basTankcarMapper.selectTrucktankList(trucktank);
    }

    //新增车辆水箱
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertTrucktank(LXBasTrucktank trucktank) throws SQLException {
        trucktank.setCreatedBy(SecurityUtils.getUsername());
        trucktank.setUpdatedBy(SecurityUtils.getUsername());
        int isAcc = basTankcarMapper.insertTrucktank(trucktank);
        if (isAcc < 0) {
            throw new SQLException("新增车辆水箱失败");
        }

        return isAcc;
    }

    //编辑车辆水箱
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateTrucktank(LXBasTrucktank trucktank) throws SQLException {
        trucktank.setUpdatedBy(SecurityUtils.getUsername());
        int isAcc = basTankcarMapper.updateTrucktank(trucktank);
        if (isAcc < 0) {
            throw new SQLException("新增车辆水箱失败");
        }
        return isAcc;
    }

    //删除车辆水箱
    @Override
    public int deleteBasTrucktankByIds(Integer[] tankIds) throws SQLException {
        String updatedBy = SecurityUtils.getUsername();
        int count = basTankcarMapper.deleteBasTrucktankByIds(tankIds, updatedBy);
        if (count < 0) {
            throw new SQLException("删除车辆水箱失败");
        }
        return count;
    }

    //查找车牌
    @Override
    public List<LXBasTankcar> selectTankcarList(String sourceId) {
        return basTankcarMapper.selectTankcarList(sourceId);
    }

    //查找工作单id
    @Override
    public List<String> selectTransHeadList(String receiveDate,Integer carId) {
        return transjobheadMapper.selectTransHeadList(receiveDate,carId);
    }

    //查询车辆轨迹列表
    @Override
    public Map<String, Object> selectTruckTrackList(String jobHeadid) {
        List<LXDatCargps> lxDatCargps = basTankcarMapper.selectDatCargpsList(jobHeadid);
        List<LXDatCargps> datCargps = basTankcarMapper.selectTruckTrackList(jobHeadid);
        Map<String,Object> map =new HashMap<>();
        map.put("firm",datCargps);
        map.put("path",lxDatCargps);
        return map;
    }

}
