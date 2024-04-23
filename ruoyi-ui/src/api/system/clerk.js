import request from '@/utils/request'

// 查询店员列表
export function listClerk(query) {
  return request({
    url: '/system/clerk/list',
    method: 'get',
    params: query
  })
}

// 查询店员详细
export function getClerk(clid) {
  return request({
    url: '/system/clerk/' + clid,
    method: 'get'
  })
}

// 新增店员
export function addClerk(data) {
  return request({
    url: '/system/clerk',
    method: 'post',
    data: data
  })
}

// 修改店员
export function updateClerk(data) {
  return request({
    url: '/system/clerk',
    method: 'put',
    data: data
  })
}

// 删除店员
export function delClerk(clid) {
  return request({
    url: '/system/clerk/' + clid,
    method: 'delete'
  })
}
