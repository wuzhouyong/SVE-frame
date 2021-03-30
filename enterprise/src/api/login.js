import request from "@/utils/request";

// 登录方法
export function login(username, password, code, uuid,authCode) {
  const data = {
    username,
    password,
    code,
    uuid,
    type: 1,
  };
  let url = authCode?`/enterprise_login?authCode=${authCode}`:`/enterprise_login`
  return request({
    url:url,
    method: "post",
    data: data
  });
}

// 别的平台跳转登录
export function appLogin(authCode) {
  return request({
    url: `/app_login?authCode=${authCode}`,
    method: "get"
  });
}


//手机登录
export function loginMobile(username, password, code, uuid,authCode) {
  const data = {
    username,
    password,
    code,
    uuid,
    type: 1,
    authCode
  };
  return request({
    url: "/mobile_login",
    method: "post",
    data: data
  });
}

export function changePwd(data) {
  // /system/user/profile
  return request({
    url: "/system/user/profile/changePwd",
    method: "put",
    data: data
  });
}

// 获取用户详细信息
export function getInfo() {
  return request({
    url: "/getInfo",
    method: "get"
  });
}

// 退出方法
export function logout() {
  return request({
    url: "/logout",
    method: "post"
  });
}

// 获取验证码
export function getCodeImg() {
  return request({
    url: "/captchaImage",
    method: "get"
  });
}

//验证初始密码
export function verifyPwd(data) {
  return request({
    url: "/system/user/profile/verifyPwd",
    method: "post",
    data: data
  });
}
//修改密码
export function newModifyPwd(data) {
  return request({
    url: "/system/user/profile/modifyPwd",
    method: "post",
    data: data
  });
}

