package com.ruoyi.project.lingxing.mapper;


import com.ruoyi.project.lingxing.domain.LXDriverinfo;
import com.ruoyi.project.lingxing.domain.LXTransjobdetail;
import com.ruoyi.project.lingxing.domain.LXTransjobhead;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 司机联单Mapper接口及运输车司机Service接口
 * 
 * @author ruoyi
 * @date 2020-08-26
 */
public interface LXTransjobheadMapper
{
    // 查询工作单列表
    public List<LXTransjobhead> selectTransjobheadList(LXTransjobhead transjobhead);

    // 查询司机联单列表
    public List<LXTransjobdetail> selectTransjobdetailList(String jobHeadid);

    //查询产污企业联单列表
    public List<LXTransjobdetail> selectJobDetailListBySourceId(LXTransjobdetail jobDetail);

    //查找工作单id
    public List<String> selectTransHeadList(String receiveDate,Integer carId);

    //查询产污企业运水联单详情
    public List<Map> selectTransBillinfo(@Param("transbillid") String transbillid);

    //查询因子编码
    public Map<String, Object>  selectElementDescById(@Param("cpElemId") String cpElemId);

    //零星废水工况图
    public List<Map> selectWorkingConditionInfo(@Param("sourceId") String sourceId);

     //零星废水工况分钟数
    public List<Map> selectWorkingMinuteInfo(@Param("cpElemId") String cpElemId);

    /**
     * 查询运输车司机
     *
     * @param driverId 运输车司机ID
     * @return 运输车司机
     */
    public LXDriverinfo selectDriverinfoById(String driverId);

    /**
     * 查询运输车司机列表
     *
     * @param driverinfo 运输车司机
     * @return 运输车司机集合
     */
    public List<LXDriverinfo> selectDriverinfoList(LXDriverinfo driverinfo);

    /**
     * 新增运输车司机
     *
     * @param driverinfo 运输车司机
     * @return 结果
     */
    public int insertDriverinfo(LXDriverinfo driverinfo);

    /**
     * 修改运输车司机
     *
     * @param driverinfo 运输车司机
     * @return 结果
     */
    public int updateDriverinfo(LXDriverinfo driverinfo);

    /**
     * 删除运输车司机
     *
     * @param driverId 运输车司机ID
     * @return 结果
     */
    public int deleteDriverinfoById(String driverId);

    /**
     * 批量删除运输车司机
     *
     * @param driverIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteDriverinfoByIds(String[] driverIds,String updatedBy);

     //查询运输车司机不能重复
    public int repeatCheck(LXDriverinfo driverinfo);
}
