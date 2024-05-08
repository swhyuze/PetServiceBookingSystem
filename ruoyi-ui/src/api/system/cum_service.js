import request from '@/utils/request'

// 查询订单查询列表
export function listCum_service(query) {
  return request({
    url: '/system/cum_service/list',
    method: 'get',
    params: query
  })
}

// 查询订单查询详细
export function getCum_service(serid) {
  return request({
    url: '/system/cum_service/' + serid,
    method: 'get'
  })
}

// 新增订单查询
export function addCum_service(data) {
  return request({
    url: '/system/cum_service',
    method: 'post',
    data: data
  })
}

// 修改订单查询
export function updateCum_service(data) {
  return request({
    url: '/system/cum_service',
    method: 'put',
    data: data
  })
}

// 删除订单查询
export function delCum_service(serid) {
  return request({
    url: '/system/cum_service/' + serid,
    method: 'delete'
  })
}
