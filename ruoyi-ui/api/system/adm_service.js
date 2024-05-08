import request from '@/utils/request'

// 查询服务记录列表
export function listAdm_service(query) {
  return request({
    url: '/system/adm_service/list',
    method: 'get',
    params: query
  })
}

// 查询服务记录详细
export function getAdm_service(serid) {
  return request({
    url: '/system/adm_service/' + serid,
    method: 'get'
  })
}

// 新增服务记录
export function addAdm_service(data) {
  return request({
    url: '/system/adm_service',
    method: 'post',
    data: data
  })
}

// 修改服务记录
export function updateAdm_service(data) {
  return request({
    url: '/system/adm_service',
    method: 'put',
    data: data
  })
}

// 删除服务记录
export function delAdm_service(serid) {
  return request({
    url: '/system/adm_service/' + serid,
    method: 'delete'
  })
}
