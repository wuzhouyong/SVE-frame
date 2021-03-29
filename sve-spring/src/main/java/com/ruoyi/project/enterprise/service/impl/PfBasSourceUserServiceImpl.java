package com.ruoyi.project.enterprise.service.impl;


import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.enterprise.domain.PfBasSourceUser;
import com.ruoyi.project.enterprise.mapper.PfBasSourceUserMapper;
import com.ruoyi.project.enterprise.service.IPfBasSourceUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 企业用户Service业务层处理
 *
 * @author ruoyi
 * @date 2020-05-18
 */
@Service
@DataSource(value = DataSourceType.MASTER)
public class PfBasSourceUserServiceImpl implements IPfBasSourceUserService
{
    @Autowired
    private PfBasSourceUserMapper pfBasSourceUserMapper;

    /**
     * 查询企业用户
     *
     * @param sourceUserId 企业用户ID
     * @return 企业用户
     */
    @Override
    public PfBasSourceUser selectPfBasSourceUserById(Long sourceUserId)
    {
        return pfBasSourceUserMapper.selectPfBasSourceUserById(sourceUserId);
    }
    @Override
    public PfBasSourceUser selectPfBasSourceUserByUserId(String userName)
    {
        return pfBasSourceUserMapper.selectPfBasSourceUserByUserId(userName);
    }
    @Override
    public PfBasSourceUser selectPfBasSourceUserByMobile(String mobile)
    {
        return pfBasSourceUserMapper.selectPfBasSourceUserByMobile(mobile);
    }
    /**
     * 查询企业用户列表
     *
     * @param pfBasSourceUser 企业用户
     * @return 企业用户
     */
    @Override
    public List<PfBasSourceUser> selectPfBasSourceUserList(PfBasSourceUser pfBasSourceUser)
    {
        return pfBasSourceUserMapper.selectPfBasSourceUserList(pfBasSourceUser);
    }

    public String generateUserName() {
        return pfBasSourceUserMapper.generateUserName();
    }

    /**
     * 新增企业用户
     *
     * @param pfBasSourceUser 企业用户
     * @return 结果
     */
    @Override
    public int insertPfBasSourceUser(PfBasSourceUser pfBasSourceUser)
    {
        pfBasSourceUser.setCreateTime(DateUtils.getNowDate());
        return pfBasSourceUserMapper.insertPfBasSourceUser(pfBasSourceUser);
    }

    /**
     * 修改企业用户
     *
     * @param pfBasSourceUser 企业用户
     * @return 结果
     */
    @Override
    public int updatePfBasSourceUser(PfBasSourceUser pfBasSourceUser)
    {
        pfBasSourceUser.setUpdateTime(DateUtils.getNowDate());
        return pfBasSourceUserMapper.updatePfBasSourceUser(pfBasSourceUser);
    }

    public int resetPasswordBySourceId(PfBasSourceUser pfBasSourceUser) {
        return pfBasSourceUserMapper.resetPasswordBySourceId(pfBasSourceUser);
    }

    /**
     * 批量删除企业用户
     *
     * @param sourceUserIds 需要删除的企业用户ID
     * @return 结果
     */
    @Override
    public int deletePfBasSourceUserByIds(Long[] sourceUserIds)
    {
        return pfBasSourceUserMapper.deletePfBasSourceUserByIds(sourceUserIds);
    }

    @Override
    public int changePasswordByName(String userName, String password) {
        return pfBasSourceUserMapper.changePasswordByName(userName, password);
    }

    public int selectIsBinding(String phone) {
        return pfBasSourceUserMapper.selectIsBinding(phone);
    }

    public int bindingForPhone(String phone, String sourceId) {
        return pfBasSourceUserMapper.bindingForPhone(phone, sourceId);
    }

    public int unbindingForPhone(String sourceId) {
        return pfBasSourceUserMapper.unbindingForPhone(sourceId);
    }

    public PfBasSourceUser selectEnterpriseUserByPhone(String phone) {
        return pfBasSourceUserMapper.selectEnterpriseUserByPhone(phone);
    }
}
