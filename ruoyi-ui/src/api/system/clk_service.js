import request from '@/utils/request'

// 查询订单查询列表
export function listClk_service(query) {
  return request({
    url: '/system/clk_service/list',
    method: 'get',
    params: query
  })
}

// 查询订单查询详细
export function getClk_service(serid) {
  return request({
    url: '/system/clk_service/' + serid,
    method: 'get'
  })
}

// 新增订单查询
export function addClk_service(data) {
  return request({
    url: '/system/clk_service',
    method: 'post',
    data: data
  })
}

// 修改订单查询
export function updateClk_service(data) {
  return request({
    url: '/system/clk_service',
    method: 'put',
    data: data
  })
}

// 删除订单查询
export function delClk_service(serid) {
  return request({
    url: '/system/clk_service/' + serid,
    method: 'delete'
  })
}
