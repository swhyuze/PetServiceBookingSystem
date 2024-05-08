import request from '@/utils/request'

// 查询订单管理列表
export function listMan_service(query) {
  return request({
    url: '/system/man_service/list',
    method: 'get',
    params: query
  })
}

// 查询订单管理详细
export function getMan_service(serid) {
  return request({
    url: '/system/man_service/' + serid,
    method: 'get'
  })
}

// 新增订单管理
export function addMan_service(data) {
  return request({
    url: '/system/man_service',
    method: 'post',
    data: data
  })
}

// 修改订单管理
export function updateMan_service(data) {
  return request({
    url: '/system/man_service',
    method: 'put',
    data: data
  })
}

// 删除订单管理
export function delMan_service(serid) {
  return request({
    url: '/system/man_service/' + serid,
    method: 'delete'
  })
}
