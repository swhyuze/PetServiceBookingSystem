import request from '@/utils/request'

// 查询店员服务列表
export function listAdm_clkser(query) {
  return request({
    url: '/system/adm_clkser/list',
    method: 'get',
    params: query
  })
}

// 查询店员服务详细
export function getAdm_clkser(clser) {
  return request({
    url: '/system/adm_clkser/' + clser,
    method: 'get'
  })
}

// 新增店员服务
export function addAdm_clkser(data) {
  return request({
    url: '/system/adm_clkser',
    method: 'post',
    data: data
  })
}

// 修改店员服务
export function updateAdm_clkser(data) {
  return request({
    url: '/system/adm_clkser',
    method: 'put',
    data: data
  })
}

// 删除店员服务
export function delAdm_clkser(clser) {
  return request({
    url: '/system/adm_clkser/' + clser,
    method: 'delete'
  })
}
