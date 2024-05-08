import request from '@/utils/request'

// 查询问题反馈列表
export function listClk_problem(query) {
  return request({
    url: '/system/clk_problem/list',
    method: 'get',
    params: query
  })
}

// 查询问题反馈详细
export function getClk_problem(proid) {
  return request({
    url: '/system/clk_problem/' + proid,
    method: 'get'
  })
}

// 新增问题反馈
export function addClk_problem(data) {
  return request({
    url: '/system/clk_problem',
    method: 'post',
    data: data
  })
}

// 修改问题反馈
export function updateClk_problem(data) {
  return request({
    url: '/system/clk_problem',
    method: 'put',
    data: data
  })
}

// 删除问题反馈
export function delClk_problem(proid) {
  return request({
    url: '/system/clk_problem/' + proid,
    method: 'delete'
  })
}
