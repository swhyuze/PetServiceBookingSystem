import request from '@/utils/request'

// 查询店员管理列表
export function listAdm_clerk(query) {
  return request({
    url: '/system/adm_clerk/list',
    method: 'get',
    params: query
  })
}

// 查询店员管理详细
export function getAdm_clerk(clid) {
  return request({
    url: '/system/adm_clerk/' + clid,
    method: 'get'
  })
}

// 新增店员管理
export function addAdm_clerk(data) {
  return request({
    url: '/system/adm_clerk',
    method: 'post',
    data: data
  })
}

// 修改店员管理
export function updateAdm_clerk(data) {
  return request({
    url: '/system/adm_clerk',
    method: 'put',
    data: data
  })
}

// 删除店员管理
export function delAdm_clerk(clid) {
  return request({
    url: '/system/adm_clerk/' + clid,
    method: 'delete'
  })
}
