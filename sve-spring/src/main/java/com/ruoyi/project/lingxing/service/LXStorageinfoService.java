package com.ruoyi.project.lingxing.service;

import com.ruoyi.project.lingxing.domain.LXDataSearch;
import com.ruoyi.project.lingxing.domain.LXMeterinfo;
import com.ruoyi.project.lingxing.domain.LXStorageinfo;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 【请填写功能名称】Service接口
 *
 * @author ruoyi
 * @date 2020-08-12
 */
public interface LXStorageinfoService {

    //查询废水收集池
   public List<LXStorageinfo> selectStorageinfoList(LXStorageinfo storageinfo);

    //新增废水收集池
    public int insertStorageinfo(LXStorageinfo storageinfo) throws SQLException;

    //编辑废水收集池
    public int updateStorageinfo(LXStorageinfo storageinfo) throws SQLException;

    //删除废水收集池
    public int deleteStorageinfo(String[] storageIds) throws SQLException;

    //查询废水表
    public LXMeterinfo selectMeterinfoById(LXMeterinfo meterinfo);

    //查询废水表数据
    public List<LXMeterinfo> selectMeterinfoList(LXMeterinfo meterinfo);

    //新增废水表
    public int insertMeterinfo(LXMeterinfo meterinfo) throws SQLException;

   //编辑废水表
 public int updateMeterinfo(LXMeterinfo meterinfo) throws SQLException;

    //删除废水收集池
    public int deleteMeterinfo(String[] meterids) throws SQLException;

    //查询数采
    public String selectGetinfo(String collectId,String eleTypeId);

    //水表统计
    public List<Map<String, Object>> selectMeterinfoDay(String sourceId, String beginTime, String endTime);

    //水池统计分钟数
    public List<Map<String, Object>> selectStoragefoMinute(String sourceId, String beginTime, String endTime);

    //水池统计分钟总数
    public List<Map<String, Object>> selectStoragefoToalMinute(String sourceId, String beginTime);

    //水池统计小时数
    public List<Map<String, Object>> selectStoragefoHour(String sourceId, String beginTime, String endTime);

    //水池统计小时总数
    public List<Map<String, Object>> selectStoragefoToalHour(String sourceId, String beginTime);
}
