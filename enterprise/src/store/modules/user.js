import {
  login,
  logout,
  getInfo,
  loginMobile,
  appLogin
} from '@/api/login'
import {
  getToken,
  setToken,
  removeToken
} from '@/utils/auth'
import Cookies from "js-cookie";
const user = {
  state: {
    token: getToken(),
    name: '',
    avatar: '',
    sourceId: '',
    permissions: [],
    isBinding: '',
    mobile: '',
    clickStatus: false
  },

  mutations: {
    SET_TOKEN: (state, token) => {
      state.token = token
    },
    SET_NAME: (state, name) => {
      state.name = name.split(":")[1]
    },
    SET_AVATAR: (state, avatar) => {
      state.avatar = avatar
    },
    SET_SOURCEID: (state, sourceId) => {
      state.sourceId = sourceId
    },
    SET_PERMISSIONS: (state, permissions) => {
      state.permissions = permissions
    },
    SET_BINDING: (state, isBinding) => {
      state.isBinding = isBinding
    },
    SET_MOBILE: (state, mobile) => {
      state.mobile = mobile
    },
    SET_CLICK: (state, clickStatus) => {
      state.clickStatus = clickStatus
    },
  },

  actions: {
    //跨平台登录通过url登录凭证去登录
    advanceLogin({
      commit
    }, authCode) {
      return new Promise((resolve, reject) => {
        appLogin(authCode).then(res => {
          if (res.code === 200) {
            setToken(res.token)
            commit('SET_TOKEN', res.token)
            resolve(true)
          }
        }).catch(error => {
          if (error.code === 440) {
            Cookies.set("authCode", authCode, {
              expires: 30
            });
            resolve(false)
          } else {
            reject(error.msg)
          }

        })
      })
    },

    // 登录
    Login({
      commit
    }, userInfo) {
      const username = userInfo.username.trim()
      const password = userInfo.password
      const code = userInfo.code
      const uuid = userInfo.uuid
      const authCode = Cookies.get("authCode");
      return new Promise((resolve, reject) => {
        login(username, password, code, uuid, authCode).then(res => {
          setToken(res.token)
          commit('SET_TOKEN', res.token)
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },
    //手机登录
    Mobile_login({
      commit
    }, userInfo) {
      const username = userInfo.username.trim()
      const password = userInfo.password
      const code = userInfo.code
      const uuid = userInfo.uuid
      const authCode = userInfo.authCod
      return new Promise((resolve, reject) => {
        loginMobile(username, password, code, uuid, authCode).then(res => {
          setToken(res.token)
          commit('SET_TOKEN', res.token)
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },
    // 获取用户信息
    GetInfo({
      commit,
      state
    }) {
      return new Promise((resolve, reject) => {
        getInfo(state.token).then(res => {
          const user = res.user
          const enterUser = res.enterUser
          const avatar = user.avatar == "" ? require("@/assets/image/profile.jpg") : process.env.VUE_APP_BASE_API + user.avatar;
          if (user.sourceId) { // 验证返回的sourceId是否非空
            commit('SET_SOURCEID', user.sourceId)
          }
          commit('SET_NAME', user.nickName)
          commit('SET_AVATAR', avatar)
          commit('SET_BINDING', enterUser.isBinding)
          commit('SET_MOBILE', enterUser.mobile)

          resolve(res)
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 退出系统
    LogOut({
      commit,
      state
    }) {
      return new Promise((resolve, reject) => {
        logout(state.token).then(() => {
          commit('SET_TOKEN', '')
          commit('SET_SOURCEID', '')
          removeToken()
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 前端 登出
    FedLogOut({
      commit
    }) {
      return new Promise(resolve => {
        commit('SET_TOKEN', '')
        removeToken()
        resolve()
      })
    },

    // 设置首页公告弹窗
    SetClcikStauts({
      commit
    }) {
      return new Promise(resolve => {
        commit('SET_CLICK', true)

        resolve()
      })
    }
  }
}

export default user
