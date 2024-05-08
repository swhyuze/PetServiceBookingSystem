import request from '@/utils/request'

// 查询顾客管理列表
export function listAdm_customer(query) {
  return request({
    url: '/system/adm_customer/list',
    method: 'get',
    params: query
  })
}

// 查询顾客管理详细
export function getAdm_customer(cuid) {
  return request({
    url: '/system/adm_customer/' + cuid,
    method: 'get'
  })
}

// 新增顾客管理
export function addAdm_customer(data) {
  return request({
    url: '/system/adm_customer',
    method: 'post',
    data: data
  })
}

// 修改顾客管理
export function updateAdm_customer(data) {
  return request({
    url: '/system/adm_customer',
    method: 'put',
    data: data
  })
}

// 删除顾客管理
export function delAdm_customer(cuid) {
  return request({
    url: '/system/adm_customer/' + cuid,
    method: 'delete'
  })
}
