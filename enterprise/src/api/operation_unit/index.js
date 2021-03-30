import request from '@/utils/request'

//运维单位列表
export function operateUnit_list(data,page) {
    return request({
      url: `/contract/list?${page}`,
      method: 'post',
      data
    })
  }

//运维单位新增
export function operateUnit_add(data) {
    return request({
      url: `/contract/add`,
      method: 'post',
      data
    })
  }

//运维单位编辑
export function operateUnit_edit(data) {
    return request({
      url: `/contract/edit`,
      method: 'post',
      data
    })
  }

//删除运维单位列表
export function operateUnit_del(data) {
    return request({
      url: `/contract/delete/${data}`,
      method: 'delete'
    })
  }


  //运维单位列表
export function operateRegister_list(data,page) {
    return request({
      url: `/contract/list?${page}`,
      method: 'post',
      data
    })
  }

  //运维单位新增
export function operateRegister_add(data) {
    return request({
      url: `/contract/add`,
      method: 'post',
      data
    })
  }

//运维单位编辑
export function operateRegister_edit(data) {
    return request({
      url: `/contract/edit`,
      method: 'post',
      data
    })
  }

//删除运维单位列表
export function operateRegister_del(data) {
    return request({
      url: `/contract/delete/${data}`,
      method: 'delete'
    })
  }

  // 查询运维单位
  export function operateUnitList() {
    return request({
      url: `/contract/devops_name`,
      method: 'get'
    })
  }
  // 查询数采
  // export function collectUnitList(data) {
  //   return request({
  //     url: `/contract/collect_name`,
  //     method: 'post',
  //     data
  //   })
  // }
   // 查询因子
   export function elementUnitList(data) {
    return request({
      url: `/contract/element_desc`,
      method: 'post',
      data
    })
  }
