import request from '@/utils/request'

// 查询宠物服务列表
export function listAdm_petser(query) {
  return request({
    url: '/system/adm_petser/list',
    method: 'get',
    params: query
  })
}

// 查询宠物服务详细
export function getAdm_petser(psid) {
  return request({
    url: '/system/adm_petser/' + psid,
    method: 'get'
  })
}

// 新增宠物服务
export function addAdm_petser(data) {
  return request({
    url: '/system/adm_petser',
    method: 'post',
    data: data
  })
}

// 修改宠物服务
export function updateAdm_petser(data) {
  return request({
    url: '/system/adm_petser',
    method: 'put',
    data: data
  })
}

// 删除宠物服务
export function delAdm_petser(psid) {
  return request({
    url: '/system/adm_petser/' + psid,
    method: 'delete'
  })
}
