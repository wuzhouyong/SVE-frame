import request from '@/utils/request'

// 获取污染源标签
export const getSourceTags = () => {
  return request({
    url: '/enterprise/source/detailList',
    method: 'get'
  })
}