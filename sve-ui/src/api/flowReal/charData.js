import request from '@/utils/request'

export function getFlowChartData(query) {
  return request({
    url: '/flowreal/chart',
    method: 'get',
    params:query
    // params: {
    //   key: 100009421,
    //   platform: 'youyan'
    // }
  })
}


export function getFlowRealData(query) {
  return request({
    url: '/flowreal/realdata',
    method: 'get',
    params:query
  })
}
