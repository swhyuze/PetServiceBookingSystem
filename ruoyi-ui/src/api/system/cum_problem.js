import request from '@/utils/request'

// 查询问题反馈列表
export function listCum_problem(query) {
  return request({
    url: '/system/cum_problem/list',
    method: 'get',
    params: query
  })
}

// 查询问题反馈详细
export function getCum_problem(proid) {
  return request({
    url: '/system/cum_problem/' + proid,
    method: 'get'
  })
}

// 新增问题反馈
export function addCum_problem(data) {
  return request({
    url: '/system/cum_problem',
    method: 'post',
    data: data
  })
}

// 修改问题反馈
export function updateCum_problem(data) {
  return request({
    url: '/system/cum_problem',
    method: 'put',
    data: data
  })
}

// 删除问题反馈
export function delCum_problem(proid) {
  return request({
    url: '/system/cum_problem/' + proid,
    method: 'delete'
  })
}
