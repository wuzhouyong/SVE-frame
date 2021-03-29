package com.ruoyi.project.enterprise.mapper;

import com.ruoyi.project.enterprise.domain.PfBasSourceUser;

import java.util.List;

/**
 * 企业用户Mapper接口
 *
 * @author ruoyi
 * @date 2020-05-18
 */
public interface PfBasSourceUserMapper
{
    /**
     * 查询企业用户
     *
     * @param sourceUserId 企业用户ID
     * @return 企业用户
     */
    public PfBasSourceUser selectPfBasSourceUserById(Long sourceUserId);
    public PfBasSourceUser selectPfBasSourceUserByUserId(String userName);
    public PfBasSourceUser selectPfBasSourceUserByMobile(String mobile);
    /**
     * 查询企业用户列表
     *
     * @param pfBasSourceUser 企业用户
     * @return 企业用户集合
     */
    public List<PfBasSourceUser> selectPfBasSourceUserList(PfBasSourceUser pfBasSourceUser);

    public String generateUserName();

    /**
     * 新增企业用户
     *
     * @param pfBasSourceUser 企业用户
     * @return 结果
     */
    public int insertPfBasSourceUser(PfBasSourceUser pfBasSourceUser);

    /**
     * 修改企业用户
     *
     * @param pfBasSourceUser 企业用户
     * @return 结果
     */
    public int updatePfBasSourceUser(PfBasSourceUser pfBasSourceUser);

    public int resetPasswordBySourceId(PfBasSourceUser pfBasSourceUser);

    /**
     * 删除企业用户
     *
     * @param sourceUserId 企业用户ID
     * @return 结果
     */
    public int deletePfBasSourceUserById(Long sourceUserId);

    /**
     * 批量删除企业用户
     *
     * @param sourceUserIds 需要删除的数据ID
     * @return 结果
     */
    public int deletePfBasSourceUserByIds(Long[] sourceUserIds);

    public int changePasswordByName(String userName, String password);

    public int selectIsBinding(String phone);

    public int bindingForPhone(String phone,String sourceId);

    public int unbindingForPhone(String sourceId);

    public PfBasSourceUser selectEnterpriseUserByPhone(String phone);
}
