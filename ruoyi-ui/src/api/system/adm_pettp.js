import request from '@/utils/request'

// 查询宠物品种列表
export function listAdm_pettp(query) {
  return request({
    url: '/system/adm_pettp/list',
    method: 'get',
    params: query
  })
}

// 查询宠物品种详细
export function getAdm_pettp(ptid) {
  return request({
    url: '/system/adm_pettp/' + ptid,
    method: 'get'
  })
}

// 新增宠物品种
export function addAdm_pettp(data) {
  return request({
    url: '/system/adm_pettp',
    method: 'post',
    data: data
  })
}

// 修改宠物品种
export function updateAdm_pettp(data) {
  return request({
    url: '/system/adm_pettp',
    method: 'put',
    data: data
  })
}

// 删除宠物品种
export function delAdm_pettp(ptid) {
  return request({
    url: '/system/adm_pettp/' + ptid,
    method: 'delete'
  })
}
