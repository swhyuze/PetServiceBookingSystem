import request from '@/utils/request'

// 查询宠物健康列表
export function listAdm_petstate(query) {
  return request({
    url: '/system/adm_petstate/list',
    method: 'get',
    params: query
  })
}

// 查询宠物健康详细
export function getAdm_petstate(pstid) {
  return request({
    url: '/system/adm_petstate/' + pstid,
    method: 'get'
  })
}

// 新增宠物健康
export function addAdm_petstate(data) {
  return request({
    url: '/system/adm_petstate',
    method: 'post',
    data: data
  })
}

// 修改宠物健康
export function updateAdm_petstate(data) {
  return request({
    url: '/system/adm_petstate',
    method: 'put',
    data: data
  })
}

// 删除宠物健康
export function delAdm_petstate(pstid) {
  return request({
    url: '/system/adm_petstate/' + pstid,
    method: 'delete'
  })
}
