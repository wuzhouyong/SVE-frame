import request from '@/utils/request'

// 获取污染源标签
export const getSource = (sourceId) => {
  return request({
    url: '/enterprise/source/' + sourceId,
    method: 'get'
  })
}

export const sourceEdit = (source) => {
    return request({
        url: "/enterprise/source/edit",
        method: "put",
        data: source
    })
}

export const getDict = (code) => {
    return request({
        url: "/system/dict/data/dictType/" + code,
        method: "get",
    })
}

export const getRegion = (code) => {
    return request({
        url: "/com/region/" + code,
        method: "get",
    })
}

export const getIndustry = () => {
    return request({
        url: "/com/industryTree/",
        method: "get",
    })
}