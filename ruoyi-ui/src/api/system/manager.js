import request from '@/utils/request'

// 查询宠物店管理列表
export function listManager(query) {
  return request({
    url: '/system/manager/list',
    method: 'get',
    params: query
  })
}

// 查询宠物店管理详细
export function getManager(mid) {
  return request({
    url: '/system/manager/' + mid,
    method: 'get'
  })
}

// 新增宠物店管理
export function addManager(data) {
  return request({
    url: '/system/manager',
    method: 'post',
    data: data
  })
}

// 修改宠物店管理
export function updateManager(data) {
  return request({
    url: '/system/manager',
    method: 'put',
    data: data
  })
}

// 删除宠物店管理
export function delManager(mid) {
  return request({
    url: '/system/manager/' + mid,
    method: 'delete'
  })
}
