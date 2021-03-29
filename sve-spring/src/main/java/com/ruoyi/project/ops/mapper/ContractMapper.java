package com.ruoyi.project.ops.mapper;


import com.ruoyi.project.ops.domain.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author ruoyi
 * @date 2020-08-25
 */
@Mapper
public interface ContractMapper {

    public List<OpProjectEntity> selectContractList(OpProjectEntity entity);

    public List<Integer> selectElementId(@Param("opProjectId") Integer opProjectId);

    public String selectdbUserByProjectId(Integer opProjectId);

    public List<Map> selectElementList(@Param("opProjectId") Integer opProjectId, @Param("dbUser") String dbUser);

    public int insertContract(OpProjectEntity entity);

    public int insertContractElement(ContractElement entity);

    public int updateContract(OpProjectEntity entity);

    public int deleteContractByIds(@Param("opProjectIds") Integer[] contractIds, @Param("updatedBy") String updatedBy);

    public int deleteContractEleByIds(@Param("opProjectIds") Integer[] contractIds, @Param("updatedBy") String updatedBy);

    public List<Map> selectDevopsBysourceId();

    public String selectDbUserById(@Param("tagHeadId") Integer tagHeadId);

    public List<Map> selectCollectNameById(Map parm);

    public String selectCollectName(Map parm);

    public List<Map> selectElementDescById(Map parm);

}
