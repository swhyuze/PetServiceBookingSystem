import request from '@/utils/request'

// 查询店员管理列表
export function listMan_clerk(query) {
  return request({
    url: '/system/man_clerk/list',
    method: 'get',
    params: query
  })
}

// 查询店员管理详细
export function getMan_clerk(clid) {
  return request({
    url: '/system/man_clerk/' + clid,
    method: 'get'
  })
}

// 新增店员管理
export function addMan_clerk(data) {
  return request({
    url: '/system/man_clerk',
    method: 'post',
    data: data
  })
}

// 修改店员管理
export function updateMan_clerk(data) {
  return request({
    url: '/system/man_clerk',
    method: 'put',
    data: data
  })
}

// 删除店员管理
export function delMan_clerk(clid) {
  return request({
    url: '/system/man_clerk/' + clid,
    method: 'delete'
  })
}
