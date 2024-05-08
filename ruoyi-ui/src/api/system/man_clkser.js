import request from '@/utils/request'

// 查询店员服务列表
export function listMan_clkser(query) {
  return request({
    url: '/system/man_clkser/list',
    method: 'get',
    params: query
  })
}

// 查询店员服务详细
export function getMan_clkser(clser) {
  return request({
    url: '/system/man_clkser/' + clser,
    method: 'get'
  })
}

// 新增店员服务
export function addMan_clkser(data) {
  return request({
    url: '/system/man_clkser',
    method: 'post',
    data: data
  })
}

// 修改店员服务
export function updateMan_clkser(data) {
  return request({
    url: '/system/man_clkser',
    method: 'put',
    data: data
  })
}

// 删除店员服务
export function delMan_clkser(clser) {
  return request({
    url: '/system/man_clkser/' + clser,
    method: 'delete'
  })
}
