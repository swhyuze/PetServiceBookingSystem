import request from '@/utils/request'

// 查询服务种类列表
export function listAdm_servicetp(query) {
  return request({
    url: '/system/adm_servicetp/list',
    method: 'get',
    params: query
  })
}

// 查询服务种类详细
export function getAdm_servicetp(stid) {
  return request({
    url: '/system/adm_servicetp/' + stid,
    method: 'get'
  })
}

// 新增服务种类
export function addAdm_servicetp(data) {
  return request({
    url: '/system/adm_servicetp',
    method: 'post',
    data: data
  })
}

// 修改服务种类
export function updateAdm_servicetp(data) {
  return request({
    url: '/system/adm_servicetp',
    method: 'put',
    data: data
  })
}

// 删除服务种类
export function delAdm_servicetp(stid) {
  return request({
    url: '/system/adm_servicetp/' + stid,
    method: 'delete'
  })
}

// 查询所有的服务
export function selectAllAdmServicetp() {
  return request({
    url: '/system/adm_servicetp/selectAllAdmServicetp',
    method: 'get',
  })
}