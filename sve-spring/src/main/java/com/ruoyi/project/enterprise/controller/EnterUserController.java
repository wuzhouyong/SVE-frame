package com.ruoyi.project.enterprise.controller;

import java.util.List;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.PasswordUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.security.LoginBody;
import com.ruoyi.framework.security.service.SysLoginService;
import com.ruoyi.project.enterprise.domain.NewUserEntity;
import com.ruoyi.project.enterprise.domain.PfBasSource;
import com.ruoyi.project.enterprise.domain.PfBasSourceUser;
import com.ruoyi.project.enterprise.service.IPfBasSourceService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.enterprise.service.IPfBasSourceUserService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 企业用户Controller
 *
 * @author ruoyi
 * @date 2020-05-18
 */
@RestController
@RequestMapping("/enterprise/user")
public class EnterUserController extends BaseController
{
    @Autowired
    private IPfBasSourceService pfBasSourceService;

    @Autowired
    private IPfBasSourceUserService pfBasSourceUserService;

    @Autowired
    private SysLoginService loginService;

    /**
     * 查询企业用户列表
     */
    @PreAuthorize("@ss.hasPermi('enterprise:user:list')")
    @GetMapping("/list")
    public AjaxResult list(PfBasSourceUser user)
    {
        List<PfBasSourceUser> users = pfBasSourceUserService.selectPfBasSourceUserList(user);
        return AjaxResult.success(users);
    }

    /**
     * 导出企业用户列表
     */
    @PreAuthorize("@ss.hasPermi('enterprise:user:export')")
    @Log(title = "企业用户", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(PfBasSourceUser pfBasSourceUser)
    {
        List<PfBasSourceUser> list = pfBasSourceUserService.selectPfBasSourceUserList(pfBasSourceUser);
        ExcelUtil<PfBasSourceUser> util = new ExcelUtil<PfBasSourceUser>(PfBasSourceUser.class);
        return util.exportExcel(list, "user");
    }

    /**
     * 获取企业用户详细信息
     */
    @PreAuthorize("@ss.hasPermi('enterprise:user:query')")
    @GetMapping(value = "/{sourceUserId}")
    public AjaxResult getInfo(@PathVariable("sourceUserId") Long sourceUserId)
    {
        return AjaxResult.success(pfBasSourceUserService.selectPfBasSourceUserById(sourceUserId));
    }

    /**
     * 新增企业用户
     */
    @PreAuthorize("@ss.hasPermi('enterprise:user:add')")
    @Log(title = "企业用户", businessType = BusinessType.INSERT)
    @PostMapping(value = "/add")
    public AjaxResult add(@RequestBody PfBasSourceUser pfBasSourceUser)
    {
        PfBasSource pfBasSource = pfBasSourceService.selectPfBasSourceById(pfBasSourceUser.getSourceId());
        if (pfBasSource == null) {
            return AjaxResult.error("企业已删除或不存在");
        }
        if (pfBasSource.getUserName() != null) {
            return AjaxResult.error("企业账号已存在");
        }
        String userName = pfBasSourceUserService.generateUserName();
        String password = PasswordUtils.GeneratePassword();
        pfBasSourceUser.setUserName(userName);
        pfBasSourceUser.setNickName(pfBasSource.getFullName());
        pfBasSourceUser.setPassword(SecurityUtils.encryptPassword(password));
        int res = pfBasSourceUserService.insertPfBasSourceUser(pfBasSourceUser);
        if (res == 0) {
            return AjaxResult.error("企业账号生成失败");
        }
        return AjaxResult.success(new NewUserEntity(userName, password));
    }

    /**
     * 修改企业用户
     */
    @PreAuthorize("@ss.hasPermi('enterprise:user:edit')")
    @Log(title = "企业用户", businessType = BusinessType.UPDATE)
    @PutMapping(value = "/reset")
    public AjaxResult reset(@RequestBody PfBasSourceUser pfBasSourceUser)
    {
        PfBasSource pfBasSource = pfBasSourceService.selectPfBasSourceById(pfBasSourceUser.getSourceId());
        if (pfBasSource == null) {
            return AjaxResult.error("企业已删除或不存在");
        }
        String password = PasswordUtils.GeneratePassword();
        pfBasSourceUser.setPassword(SecurityUtils.encryptPassword(password));
        int res = pfBasSourceUserService.resetPasswordBySourceId(pfBasSourceUser);
        if (res == 0) {
            return AjaxResult.error("密码重置失败");
        }
        return AjaxResult.success(new NewUserEntity(password));
    }

    /**
     * 删除企业用户
     */
    @PreAuthorize("@ss.hasPermi('enterprise:user:remove')")
    @Log(title = "企业用户", businessType = BusinessType.DELETE)
	@DeleteMapping("/{sourceUserIds}")
    public AjaxResult remove(@PathVariable Long[] sourceUserIds)
    {
        return toAjax(pfBasSourceUserService.deletePfBasSourceUserByIds(sourceUserIds));
    }


    /**
     * 企业用户登录方法
     *
     * @param loginBody 用户
     * @return 结果
     */

    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody)
    {
        AjaxResult ajax = AjaxResult.success();
        PfBasSourceUser user = pfBasSourceUserService.selectPfBasSourceUserByUserId(loginBody.getUsername());
        if(user != null)
        {
            if(user.getPassword() == SecurityUtils.encryptPassword(user.getPassword())) {

            }
        }
        // 生成令牌
        String token = loginService.login("admin", "abc123...", loginBody.getCode(),
                loginBody.getUuid());
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }
}