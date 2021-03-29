package com.ruoyi.project.enterprise.service;

import com.ruoyi.project.enterprise.domain.PfBasSourceUser;

import java.util.List;

/**
 * 企业用户Service接口
 *
 * @author ruoyi
 * @date 2020-05-18
 */
public interface IPfBasSourceUserService
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
     * 批量删除企业用户
     *
     * @param sourceUserIds 需要删除的企业用户ID
     * @return 结果
     */
    public int deletePfBasSourceUserByIds(Long[] sourceUserIds);

    public int changePasswordByName(String userName, String password);


    //验证手机号码是否被绑定
    public int selectIsBinding(String phone);

    //绑定手机号码
    public int bindingForPhone(String phone,String sourceId);

    //解除绑定手机号码
    public int unbindingForPhone(String sourceId);

    /**
     * 通过手机查询用户
     *
     * @param phone 用户名
     * @return 用户对象信息
     */
    public PfBasSourceUser selectEnterpriseUserByPhone(String phone);
}