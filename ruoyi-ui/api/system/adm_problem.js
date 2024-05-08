import request from '@/utils/request'

// 查询问题反馈列表
export function listAdm_problem(query) {
  return request({
    url: '/system/adm_problem/list',
    method: 'get',
    params: query
  })
}

// 查询问题反馈详细
export function getAdm_problem(proid) {
  return request({
    url: '/system/adm_problem/' + proid,
    method: 'get'
  })
}

// 新增问题反馈
export function addAdm_problem(data) {
  return request({
    url: '/system/adm_problem',
    method: 'post',
    data: data
  })
}

// 修改问题反馈
export function updateAdm_problem(data) {
  return request({
    url: '/system/adm_problem',
    method: 'put',
    data: data
  })
}

// 删除问题反馈
export function delAdm_problem(proid) {
  return request({
    url: '/system/adm_problem/' + proid,
    method: 'delete'
  })
}
