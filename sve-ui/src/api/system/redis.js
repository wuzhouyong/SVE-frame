import request from '@/utils/request'

// 查询岗位列表
export function getCacheList(data) {
  return request({
    url: '/system/tool/redis/getCacheList',
    method: 'post',
    params: data
  })
}

//更新用户部门树
export function updateCacheDeptUserTree() {
  return request({
    url: '/system/tool/redis/getCacheDeptUserTree',
    method: 'get'
  })
}




//更新部门
export function updateCacheDept() {
  return request({
    url: '/system/tool/redis/getCacheDept',
    method: 'get'
  })
}
