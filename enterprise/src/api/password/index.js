import request from "@/utils/request";//收集池列表

import requestPsd from "@/utils/requestPsd";//收集池列表

//获取验证码
export function getVertufyCode(mobile) {
    return requestPsd({
        url: `/enter/mobile/mobile_captcha/${mobile}`,
        method: "get"
    });
}

//忘记密码前验证手机
export function forgetBindCode(mobile, query) {
    return request({
        url: `/enter/mobile/forget_password/${mobile}?code=${query.code}`,
        method: "post",

    });
}

//修改密码
export function modifypsd(mobile, data) {
    return request({
        url: `/enter/mobile/reset_password/${mobile}`,
        method: "post",
        data
    });
}

// 绑定手机
export function bindingPhone(mobile, data) {
    return request({
        url: `/enter/mobile/binding_mobile/${mobile}?code=${data.code}`,
        method: "post",

    });
}

// 绑定手机
export function verifyCode(mobile, data) {
    return request({
        url: `/enter/mobile/check_mobile/${mobile}?code=${data.code}`,
        method: "post",

    });
}

//判断用户是否已经绑定了手机号码
export function judgeUserPhone(mobile, data) {
    return request({
        url: `/enter/mobile/isbinding`,
        method: "get",

    });
}