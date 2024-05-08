import request from '@/utils/request'

// 查询宠物管理列表
export function listCum_pet(query) {
  return request({
    url: '/system/cum_pet/list',
    method: 'get',
    params: query
  })
}

// 查询宠物管理详细
export function getCum_pet(pid) {
  return request({
    url: '/system/cum_pet/' + pid,
    method: 'get'
  })
}

// 新增宠物管理
export function addCum_pet(data) {
  return request({
    url: '/system/cum_pet',
    method: 'post',
    data: data
  })
}

// 修改宠物管理
export function updateCum_pet(data) {
  return request({
    url: '/system/cum_pet',
    method: 'put',
    data: data
  })
}

// 删除宠物管理
export function delCum_pet(pid) {
  return request({
    url: '/system/cum_pet/' + pid,
    method: 'delete'
  })
}
