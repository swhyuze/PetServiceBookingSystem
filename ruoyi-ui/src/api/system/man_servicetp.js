import request from '@/utils/request'

// 查询服务种类列表
export function listMan_servicetp(query) {
  return request({
    url: '/system/man_servicetp/list',
    method: 'get',
    params: query
  })
}

// 查询服务种类详细
export function getMan_servicetp(stid) {
  return request({
    url: '/system/man_servicetp/' + stid,
    method: 'get'
  })
}

// 新增服务种类
export function addMan_servicetp(data) {
  return request({
    url: '/system/man_servicetp',
    method: 'post',
    data: data
  })
}

// 修改服务种类
export function updateMan_servicetp(data) {
  return request({
    url: '/system/man_servicetp',
    method: 'put',
    data: data
  })
}

// 删除服务种类
export function delMan_servicetp(stid) {
  return request({
    url: '/system/man_servicetp/' + stid,
    method: 'delete'
  })
}
