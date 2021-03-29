import request from '@/utils/request'

// 获取路由
export const getRouters = () => {
  return request({
    url: '/getRouters',
    method: 'get'
  })
}

export const getRoutersBySystem = (query) => {
  return request({
    url: '/getRoutersBySystem',
    method: 'get',
    params: query
  })
}

export const getRoutersSystem = (query) => {
  return request({
    url: '/getRoutersSystem',
    method: 'get',
    params: query
  })
}
