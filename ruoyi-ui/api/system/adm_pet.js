import request from '@/utils/request'

// 查询宠物管理列表
export function listAdm_pet(query) {
  return request({
    url: '/system/adm_pet/list',
    method: 'get',
    params: query
  })
}

// 查询宠物管理详细
export function getAdm_pet(pid) {
  return request({
    url: '/system/adm_pet/' + pid,
    method: 'get'
  })
}

// 新增宠物管理
export function addAdm_pet(data) {
  return request({
    url: '/system/adm_pet',
    method: 'post',
    data: data
  })
}

// 修改宠物管理
export function updateAdm_pet(data) {
  return request({
    url: '/system/adm_pet',
    method: 'put',
    data: data
  })
}

// 删除宠物管理
export function delAdm_pet(pid) {
  return request({
    url: '/system/adm_pet/' + pid,
    method: 'delete'
  })
}
