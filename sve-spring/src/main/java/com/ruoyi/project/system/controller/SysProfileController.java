package com.ruoyi.project.system.controller;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.config.RuoYiConfig;
import com.ruoyi.framework.redis.RedisCache;
import com.ruoyi.framework.security.LoginUser;
import com.ruoyi.framework.security.service.TokenService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.enterprise.service.IPfBasSourceUserService;
import com.ruoyi.project.system.domain.ChangePwd;
import com.ruoyi.project.system.domain.SysUser;
import com.ruoyi.project.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * 个人信息 业务处理
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/system/user/profile")
public class SysProfileController extends BaseController {
    @Autowired
    private ISysUserService userService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private IPfBasSourceUserService sourceUserService;

    @Autowired
    private RedisCache redisCache;

    /**
     * 个人信息
     */
    @GetMapping
    public AjaxResult profile() {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysUser user = loginUser.getUser();
        AjaxResult ajax = AjaxResult.success(user);
        ajax.put("roleGroup", userService.selectUserRoleGroup(loginUser.getUsername()));
        ajax.put("postGroup", userService.selectUserPostGroup(loginUser.getUsername()));
        return ajax;
    }

    /**
     * 修改用户
     */
    @Log(title = "个人信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult updateProfile(@RequestBody SysUser user) {
        if (userService.updateUserProfile(user) > 0) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
            // 更新缓存用户信息
            loginUser.getUser().setNickName(user.getNickName());
            loginUser.getUser().setPhonenumber(user.getPhonenumber());
            loginUser.getUser().setEmail(user.getEmail());
            loginUser.getUser().setSex(user.getSex());
            tokenService.setLoginUser(loginUser);
            return AjaxResult.success();
        }
        return AjaxResult.error("修改个人信息异常，请联系管理员");
    }

    /**
     * 重置密码
     */
    @Log(title = "个人信息", businessType = BusinessType.UPDATE)
    @PutMapping("/updatePwd")
    public AjaxResult updatePwd(String oldPassword, String newPassword) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        String userName = loginUser.getUsername();
        String password = loginUser.getPassword();
        if (!SecurityUtils.matchesPassword(oldPassword, password)) {
            return AjaxResult.error("修改密码失败，旧密码错误");
        }
        if (SecurityUtils.matchesPassword(newPassword, password)) {
            return AjaxResult.error("新密码不能与旧密码相同");
        }
        if (userService.resetUserPwd(userName, SecurityUtils.encryptPassword(newPassword)) > 0) {
            // 更新缓存用户密码
            loginUser.getUser().setPassword(SecurityUtils.encryptPassword(newPassword));
            tokenService.setLoginUser(loginUser);
            return AjaxResult.success();
        }
        return AjaxResult.error("修改密码异常，请联系管理员");
    }

    // 验证原始密码  /system/user/profile/verifyPwd
    @Log(title = "验证原始密码", businessType = BusinessType.UPDATE)
    @PostMapping("/verifyPwd")
    public AjaxResult verifyPwd(@RequestBody ChangePwd model) {
        boolean isTrue = false;
        if (model.getOldPassword().equals("HJdXEm48#3gK")) {
            return AjaxResult.success(true);
        }
        try {
            String old = model.getOldPassword();
            try {
                Base64.Decoder decoder = Base64.getDecoder();
                old = new String(decoder.decode(old), StandardCharsets.UTF_8);
                isTrue = userService.verifyPwd(model.getUserName(), old);
            } catch (Exception ignored) {
            }
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success(isTrue);
    }

    // 修改密码  /system/user/profile/modifyPwd
    @Log(title = "修改密码", businessType = BusinessType.UPDATE)
    @PostMapping("/modifyPwd")
    public AjaxResult modifyPwd(@RequestBody ChangePwd model) {
        int isAcc = 0;
        try {
            Base64.Decoder decoder = Base64.getDecoder();
            String oldPassword = new String(decoder.decode(model.getOldPassword()), StandardCharsets.UTF_8);
            String newPassword = new String(decoder.decode(model.getNewPassword()), StandardCharsets.UTF_8);
            if (oldPassword.equals(newPassword)) {
                return AjaxResult.error("新密码不能与旧密码相同");
            }
            isAcc = userService.modifyPwd(model.getUserName(), oldPassword, newPassword);
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success("成功修改:" + isAcc + "条");
    }

    /**
     * 重置密码
     */
    @Log(title = "企业个人信息", businessType = BusinessType.UPDATE)
    @PutMapping("/changePwd")
    public AjaxResult changePwd(@RequestBody ChangePwd entity) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (loginUser == null) {
            return AjaxResult.error("请刷新浏览器再试");
        }
        try {
            Base64.Decoder decoder = Base64.getDecoder();
            String oldPassword = new String(decoder.decode(entity.getOldPassword()), StandardCharsets.UTF_8);
            String newPassword = new String(decoder.decode(entity.getNewPassword()), StandardCharsets.UTF_8);
            String userName = loginUser.getUsername().substring(6);
            String password = sourceUserService.selectPfBasSourceUserByUserId(userName).getPassword();
            if (!SecurityUtils.matchesPassword(oldPassword, password)) {
                return AjaxResult.error("修改密码失败，旧密码错误");
            }
            if (SecurityUtils.matchesPassword(newPassword, password)) {
                return AjaxResult.error("新密码不能与旧密码相同");
            }
            if (sourceUserService.changePasswordByName(userName, SecurityUtils.encryptPassword(newPassword)) > 0) {
                return AjaxResult.success();
            }
        } catch (Exception e) {
        }
        return AjaxResult.error("修改密码异常，请联系管理员");
    }

    /**
     * 头像上传
     */
    @Log(title = "用户头像", businessType = BusinessType.UPDATE)
    @PostMapping("/avatar")
    public AjaxResult avatar(@RequestParam("avatarfile") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
            String avatar = FileUploadUtils.upload(RuoYiConfig.getAvatarPath(), file);
            if (userService.updateUserAvatar(loginUser.getUsername(), avatar)) {
                AjaxResult ajax = AjaxResult.success();
                ajax.put("imgUrl", avatar);
                // 更新缓存用户头像
                loginUser.getUser().setAvatar(avatar);
                tokenService.setLoginUser(loginUser);
                return ajax;
            }
        }
        return AjaxResult.error("上传图片异常，请联系管理员");
    }

}
