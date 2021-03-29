package com.ruoyi.project.enterprise.controller;


import com.ruoyi.common.utils.*;
import com.ruoyi.framework.redis.RedisCache;
import com.ruoyi.framework.security.LoginUser;
import com.ruoyi.framework.security.service.TokenService;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.enterprise.domain.PfBasSourceUser;
import com.ruoyi.project.enterprise.service.IPfBasSourceUserService;
import com.ruoyi.project.system.domain.ChangePwd;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
//import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/enter/mobile")
public class MobileController {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private IPfBasSourceUserService sourceUserService;

    @Autowired
    private RedisCache redisCache;

    private static final Logger logger = LoggerFactory.getLogger(Threads.class);
    /**
     * 获取短信验证码
     *
     * @param mobile 手机号  /enter/mobile/mobile_captcha
     * @return 验证码
     */
    @GetMapping("/mobile_captcha/{mobile}")
    public AjaxResult mobileCaptcha(@PathVariable String mobile) {
        String code_t = redisCache.getCacheObject(mobile);
        if (StringUtils.isNotEmpty(code_t)) return AjaxResult.error("请勿频繁获取验证码");
        String messageCode = "";
        try {
            SecureRandom random = new SecureRandom();
            int min = 0;
            int max = 999999;
            int t_code = random.nextInt(max - min + 1) + min;
            messageCode = String.format("%06d", t_code);
            RandomStringUtils.randomNumeric(6);
            //encryptString=手机号#验证码 encryptKey=Dfj8*K9+
            String s = encryptDES(mobile + "#" + messageCode, "Dfj8*K9+");
            Map<String, String> param = new HashMap<>();
            param.put("decryptString", s);
            String jsonString = HttpClientUtils.doGet("http://dgepb.dg.gov.cn/dgom/home/sendSMS", param);
            if (!StringUtils.equals(jsonString, "0000")) {
                logger.error(jsonString);
                return AjaxResult.error("短信发送失败");
            }
            redisCache.setCacheObject(mobile, messageCode, 60, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("短信发送失败");
        }
        return AjaxResult.success("短信发送成功");
    }

    @PostMapping("/check_mobile/{mobile}") //  /enter/mobile/check_mobile
    public AjaxResult checkMobile(@PathVariable String mobile, String code) {
        String code_t = redisCache.getCacheObject(mobile);
        if (StringUtils.isEmpty(code_t)) return AjaxResult.error("请重新获取验证码");
        if (StringUtils.equals(code_t, code)) {
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
            if (loginUser == null) return AjaxResult.error("请刷新浏览器再试");
            if (mobile.equals(loginUser.getUser().getMobile())) {
                return AjaxResult.error("身份验证失败");
            }
            return AjaxResult.success("身份验证成功");
        }
        return AjaxResult.error("验证码输入错误");
    }

    /**
     * 绑定手机
     */
    @PostMapping("/binding_mobile/{mobile}")
    public AjaxResult bindMobile(@PathVariable String mobile, String code) {
        String code_t = redisCache.getCacheObject(mobile);
        if (StringUtils.isEmpty(code_t)) return AjaxResult.error("请重新获取验证码");
        if (StringUtils.equals(code_t, code)) {  //绑定成功
            LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
            if (loginUser == null) return AjaxResult.error("请刷新浏览器再试");
            // 判断手机号是否与其他账号绑定
            int count = sourceUserService.selectIsBinding(mobile);
            if (count > 0) return AjaxResult.error("手机号已绑定其他账号");
            if (loginUser.getUser().getSourceId() == null) return AjaxResult.error("企业信息获取失败");
            int count_t = sourceUserService.bindingForPhone(mobile, loginUser.getUser().getSourceId());
            if (count_t < 1) return AjaxResult.error("手机绑定失败");
            return AjaxResult.success("手机绑定成功");
        }
        return AjaxResult.error("验证码输入错误");

    }

    /**
     * 解除绑定
     *
     * @return
     */
    @GetMapping("/disassociate")
    public AjaxResult disassociate() {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        if (loginUser.getUser().getSourceId() == null) return AjaxResult.error("企业信息获取失败");
        int count_t = sourceUserService.unbindingForPhone(loginUser.getUser().getSourceId());
        if (count_t < 1) return AjaxResult.error("手机解除绑定失败");
        return AjaxResult.success("手机解除绑定成功");
    }

    @PostMapping("/forget_password/{mobile}")
    public AjaxResult forgetPassword(@PathVariable String mobile, String code) {
        String code_t = redisCache.getCacheObject(mobile);
        if (StringUtils.isEmpty(code_t)) return AjaxResult.error("请重新获取验证码");
        if (!StringUtils.equals(code, code_t)) return AjaxResult.error("验证码输入错误");
        // 判断手机号是否与其他账号绑定
        int count = sourceUserService.selectIsBinding(mobile);
        if (count < 1) return AjaxResult.error("手机号未绑定任何账号");
        return AjaxResult.success(true);
    }

    @PostMapping("/reset_password/{mobile}")
    public AjaxResult resetPassword(@PathVariable String mobile, @RequestBody ChangePwd model) {
        if (!StringUtils.equals(model.getNewPassword(), model.getConfirmPassword())) return AjaxResult.error("两次输入的密码不一致");
        // 根据手机号获取用户信息
        PfBasSourceUser user = sourceUserService.selectEnterpriseUserByPhone(mobile);
        if (user == null) return AjaxResult.success("该手机号没有绑定企业");
        Base64.Decoder decoder = Base64.getDecoder();
        String password = new String(decoder.decode(model.getNewPassword()), StandardCharsets.UTF_8);
        if (sourceUserService.changePasswordByName(user.getUserName(), SecurityUtils.encryptPassword(password)) > 0) {
            return AjaxResult.success("密码修改成功");
        }
        return AjaxResult.error("密码修改失败");
    }

    public static String encryptDES(String encryptString, String encryptKey) throws Exception {
        IvParameterSpec zeroIv = new IvParameterSpec(new byte[8]);
        SecretKeySpec key = new SecretKeySpec(encryptKey.getBytes(), "DES");
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
        byte[] encryptedData = cipher.doFinal(encryptString.getBytes());
        return Base64.getEncoder().encodeToString(encryptedData);
    }


}
