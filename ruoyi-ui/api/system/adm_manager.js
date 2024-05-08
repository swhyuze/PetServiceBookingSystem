import request from '@/utils/request'

// 查询宠物店管理列表
export function listAdm_manager(query) {
  return request({
    url: '/system/adm_manager/list',
    method: 'get',
    params: query
  })
}

// 查询宠物店管理详细
export function getAdm_manager(mid) {
  return request({
    url: '/system/adm_manager/' + mid,
    method: 'get'
  })
}

// 新增宠物店管理
export function addAdm_manager(data) {
  return request({
    url: '/system/adm_manager',
    method: 'post',
    data: data
  })
}

// 修改宠物店管理
export function updateAdm_manager(data) {
  return request({
    url: '/system/adm_manager',
    method: 'put',
    data: data
  })
}

// 删除宠物店管理
export function delAdm_manager(mid) {
  return request({
    url: '/system/adm_manager/' + mid,
    method: 'delete'
  })
}
