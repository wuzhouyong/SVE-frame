package com.ruoyi.project.lingxing.mapper;


import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.lingxing.domain.LXBasContract;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author ruoyi
 * @date 2020-08-25
 */
public interface LXBasContractlMapper {

    //查询转运合同管理
    public List<LXBasContract> getContractList(LXBasContract contract);

    //新增转运合同管理
    public int insertBasContract(LXBasContract contract);
    public int selectContractCount(LXBasContract contract);

    //编辑转运合同管理
    public int updateBasContract(LXBasContract contract);

    //删除转运合同管理
    @DataSource(value = DataSourceType.LINGXING)
    public int deleteBasContractByIds(@Param("contractIds") String[] contractIds,
                                      @Param("updatedBy") String updatedBy);

    //删除转运合同附件
    @DataSource(value = DataSourceType.MASTER)
    public int deleteContractAttFile(@Param("contractIds") String[] contractIds,
                                     @Param("updatedBy") String updatedBy);


}
