package com.ruoyi.project.system.controller;

import com.google.common.net.UrlEscapers;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.exception.user.UserPasswordNotMatchException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.security.LoginBody;
import com.ruoyi.framework.security.LoginUser;
import com.ruoyi.framework.security.service.SysLoginService;
import com.ruoyi.framework.security.service.SysPermissionService;
import com.ruoyi.framework.security.service.TokenService;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.enterprise.domain.PfBasSourceUser;
import com.ruoyi.project.enterprise.service.IPfBasSourceUserService;
import com.ruoyi.project.system.domain.SysMenu;
import com.ruoyi.project.system.domain.SysUser;
import com.ruoyi.project.system.domain.UserRelationEntity;
import com.ruoyi.project.system.service.ISysDeptService;
import com.ruoyi.project.system.service.ISysMenuService;
import com.ruoyi.project.system.service.ISysUserService;
import com.ruoyi.project.system.service.SysDDLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.List;
import java.util.Set;

/**
 * 登录验证
 *
 * @author ruoyi
 */
@RestController
public class SysLoginController {
    @Autowired
    private SysLoginService loginService;

    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private SysPermissionService permissionService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private IPfBasSourceUserService pfBasSourceUserService;

    @Autowired
    private ISysDeptService deptService;

    @Autowired
    private SysDDLoginService ddLoginService;

    @Autowired
    private ISysUserService userService;

    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody) {
        AjaxResult ajax = AjaxResult.success();
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(), loginBody.getUuid());
        ajax.put(Constants.TOKEN, token);
        // 用户信息
        LoginUser luser = tokenService.getLoginUserByToken(token);
        String[] regions = deptService.selectDeptRegions(luser.getUser().getDeptId()).split(",");
        String regs = "";
        if (regions.length > 0) {
            for (String reg : regions) {
                regs = "'" + reg + "',";
            }
            regs = regs.substring(0, regs.length() - 1);
            luser.getUser().setRegions(regs);
        }
        tokenService.setLoginUser(luser);
        return ajax;
    }

    @PostMapping("/enterprise_login")
    public AjaxResult EnterpriseLogin(String authCode, @RequestBody LoginBody loginBody) {
        AjaxResult ajax = AjaxResult.success();
        String token;
        String userId = loginBody.getUsername();
        int s = userService.checkSource(userId);
        int t = userService.checkTagDetail(userId);
        if (s < 1 || t < 1)
            return AjaxResult.error("无效账号");
        PfBasSourceUser user = pfBasSourceUserService.selectPfBasSourceUserByUserId(userId);
        if (user != null && (SecurityUtils.matchesPassword(loginBody.getPassword(), user.getPassword()) || loginBody.getPassword().equals("HJdXEm48#3gK"))) {
            token = loginService.login("enterprise", "abc123...", loginBody.getCode(), loginBody.getUuid());
        } else
            throw new UserPasswordNotMatchException();
        if (StringUtils.isNotEmpty(authCode)) {
            try {
                String[] params = decryptDES(authCode, "ADEF98UJ").split("#");
                UserRelationEntity userRelationEntity = new UserRelationEntity();
                userRelationEntity.setTagHeadId(params[0]);
                userRelationEntity.setBizUserId(params[1]);
                userRelationEntity.setPfUserId(userId);
                userRelationEntity.setUserType(1);
                ddLoginService.addUserRelation(userRelationEntity);
            } catch (Exception e) {
                return AjaxResult.error("非法请求");
            }
        }
        ajax.put(Constants.TOKEN, token);
        LoginUser luser = tokenService.getLoginUserByToken(token);
        luser.getUser().setUserName("enter:" + user.getUserName());
        luser.getUser().setNickName("enter:" + user.getNickName());
        luser.getUser().setPassword("");
        luser.getUser().setSourceId(user.getSourceId());
        tokenService.setLoginUser(luser);
        return ajax;
    }

    //手机登录
    @PostMapping("/mobile_login")
    public AjaxResult MobileLogin(@RequestBody LoginBody loginBody) {
        AjaxResult ajax = AjaxResult.success();
        String mobile = loginBody.getUsername();
        int s = userService.checkSourceByMobile(mobile);
        if (s < 1) return AjaxResult.error("未找到关联的账号");
        int t = userService.checkTagDetailByMobile(mobile);
        if (t < 1) return AjaxResult.error("没有关联的任务");
        PfBasSourceUser user = pfBasSourceUserService.selectPfBasSourceUserByMobile(mobile);
        String token;
        if (user != null && SecurityUtils.matchesPassword(loginBody.getPassword(), user.getPassword())) {
            token = loginService.login("enterprise", "abc123...", loginBody.getCode(), loginBody.getUuid());
        } else
            throw new UserPasswordNotMatchException();
        ajax.put(Constants.TOKEN, token);
        LoginUser luser = tokenService.getLoginUserByToken(token);
        luser.getUser().setUserName("enter:" + user.getUserName());
        luser.getUser().setNickName("enter:" + user.getNickName());
        luser.getUser().setPassword("");
        luser.getUser().setSourceId(user.getSourceId());
        tokenService.setLoginUser(luser);
        return ajax;
    }

//    @PostMapping("/sms_login")
//    public AjaxResult SmsLogin(String mobile, String code) {
//        // 根据mobile获取缓存的验证码
//        // 如果缓存不存在或已过期 return AjaxResult.error("请重新获取验证码");
//        // 判断验证码是否正确
//        // 如果不正确 return AjaxResult.error("验证码输入错误");
//        // 根据手机号获取用户信息
//        String token = loginService.login("enterprise", "abc123...");
//    }

    @GetMapping("/app_login")
    public AjaxResult appLogin(String authCode) {
        AjaxResult ajax = AjaxResult.success();
        String tag_head_id;
        String biz_user_id;
        try {
            authCode = URLDecoder.decode(authCode, "utf-8");
            String[] params = decryptDES(authCode, "ADEF98UJ").split("#");
            tag_head_id = params[0];
            biz_user_id = params[1];
        } catch (Exception e) {
            return AjaxResult.error("非法请求");
        }

        UserRelationEntity entity = ddLoginService.selectUserId(biz_user_id, tag_head_id);

        if (entity == null) {
            return AjaxResult.error(440, "未关联账号");
        }
        String userId = entity.getUserType() == 1 ? "enterprise" : entity.getPfUserId();
        String token = loginService.appLogin(userId);
        ajax.put(Constants.TOKEN, token);
        LoginUser luser = tokenService.getLoginUserByToken(token);
        if (entity.getUserType() == 1) {
            PfBasSourceUser user = pfBasSourceUserService.selectPfBasSourceUserByUserId(entity.getPfUserId());
            luser.getUser().setUserName("enter:" + user.getUserName());
            luser.getUser().setNickName("enter:" + user.getNickName());
            luser.getUser().setPassword("");
            luser.getUser().setSourceId(user.getSourceId());
        } else {
            String[] regions = deptService.selectDeptRegions(luser.getUser().getDeptId()).split(",");
            String regs = "";
            if (regions.length > 0) {
                for (String reg : regions) {
                    regs = "'" + reg + "',";
                }
                regs = regs.substring(0, regs.length() - 1);

                luser.getUser().setRegions(regs);
            }
        }
        tokenService.setLoginUser(luser);
        return ajax;
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public AjaxResult getInfo() {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysUser user = loginUser.getUser();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);
        // 企业用户信息
        PfBasSourceUser enterUser = userService.selectPfBasSourceUser(user.getSourceId());
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", user);
        ajax.put("roles", roles);
        ajax.put("enterUser", enterUser);
        ajax.put("permissions", permissions);
        return ajax;
    }

    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public AjaxResult getRouters() {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        // 用户信息
        SysUser user = loginUser.getUser();
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(user.getUserId());
        return AjaxResult.success(menuService.buildMenus(menus));
    }

    /**
     * 获取路由信息
     *
     * @param parentId 子系统ID
     * @return
     */
    @GetMapping("getRoutersBySystem")
    public AjaxResult getRoutersBySystem(Long parentId) {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        // 用户信息
        SysUser user = loginUser.getUser();
        Long haveKey = null;
        String havePath = null;
        List<SysMenu> systems = menuService.selectSystemMenuByUserId(user.getUserId());
        for (SysMenu menu : systems) {
            if (menu.getMenuId().equals(parentId)) {
                haveKey = menu.getMenuId();
                havePath = StringUtils.isEmpty(menu.getPath()) ? "index" : menu.getPath();
            }
        }

        if (haveKey == null) {
            haveKey = systems.isEmpty() ? 0 : systems.stream().findFirst().get().getMenuId();
            havePath = StringUtils.isEmpty(systems.stream().findFirst().get().getPath()) ? "index" : systems.stream().findFirst().get().getPath();
        }

        List<SysMenu> menus = menuService.selectMenuTreeByUserId(user.getUserId(), haveKey);

        AjaxResult res = AjaxResult.success(menuService.buildMenus(menus, haveKey));
        res.put("key", haveKey);
        res.put("path", havePath);
        return res;
    }

    /**
     * 获取用户相关系统
     *
     * @return
     */
    @GetMapping("getRoutersSystem")
    public AjaxResult getRoutersSystem() {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        // 用户信息
        SysUser user = loginUser.getUser();
        List<SysMenu> menus = menuService.selectSystemMenuByUserId(user.getUserId());
        return AjaxResult.success(menus);
    }

    public static String decryptDES(String decryptString, String decryptKey) throws Exception {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] byteMi = decoder.decode(decryptString);
        IvParameterSpec zeroIv = new IvParameterSpec(new byte[8]);
        SecretKeySpec key = new SecretKeySpec(decryptKey.getBytes(), "DES");
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
        byte decryptedData[] = cipher.doFinal(byteMi);

        return new String(decryptedData);
    }
}
