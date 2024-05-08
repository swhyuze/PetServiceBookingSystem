import request from '@/utils/request'

// 查询服务种类列表
export function listCum_servicetp(query) {
  return request({
    url: '/system/cum_servicetp/list',
    method: 'get',
    params: query
  })
}

// 查询服务种类详细
export function getCum_servicetp(stid) {
  return request({
    url: '/system/cum_servicetp/' + stid,
    method: 'get'
  })
}

// 新增服务种类
export function addCum_servicetp(data) {
  return request({
    url: '/system/cum_servicetp',
    method: 'post',
    data: data
  })
}

// 修改服务种类
export function updateCum_servicetp(data) {
  return request({
    url: '/system/cum_servicetp',
    method: 'put',
    data: data
  })
}

// 删除服务种类
export function delCum_servicetp(stid) {
  return request({
    url: '/system/cum_servicetp/' + stid,
    method: 'delete'
  })
}
