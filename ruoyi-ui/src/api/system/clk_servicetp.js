import request from '@/utils/request'

// 查询服务资质列表
export function listClk_servicetp(query) {
  return request({
    url: '/system/clk_servicetp/list',
    method: 'get',
    params: query
  })
}

// 查询服务资质详细
export function getClk_servicetp(stid) {
  return request({
    url: '/system/clk_servicetp/' + stid,
    method: 'get'
  })
}

// 新增服务资质
export function addClk_servicetp(data) {
  return request({
    url: '/system/clk_servicetp',
    method: 'post',
    data: data
  })
}

// 修改服务资质
export function updateClk_servicetp(data) {
  return request({
    url: '/system/clk_servicetp',
    method: 'put',
    data: data
  })
}

// 删除服务资质
export function delClk_servicetp(stid) {
  return request({
    url: '/system/clk_servicetp/' + stid,
    method: 'delete'
  })
}
