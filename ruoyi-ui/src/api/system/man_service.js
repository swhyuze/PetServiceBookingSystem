import request from '@/utils/request'

// 查询订单管理列表
export function listMan_service(query) {
  return request({
    url: '/system/man_service/list',
    method: 'get',
    params: query
  })
}

// 查询订单管理详细
export function getMan_service(serid) {
  return request({
    url: '/system/man_service/' + serid,
    method: 'get'
  })
}

// 新增订单管理
export function addMan_service(data) {
  return request({
    url: '/system/man_service',
    method: 'post',
    data: data
  })
}

// 修改订单管理
export function updateMan_service(data) {
  return request({
    url: '/system/man_service',
    method: 'put',
    data: data
  })
}

// 删除订单管理
export function delMan_service(serid) {
  return request({
    url: '/system/man_service/' + serid,
    method: 'delete'
  })
}

export function selectCumstomer() {
  return request({
    url: '/system/man_service/selectCumstomer',
    method: 'get',
  })
}

export function selectPet(query) {
  return request({
    url: '/system/man_service/selectPet',
    method: 'get',
    params: query
  })
}

export function selectSerivetp(query) {
  return request({
    url: '/system/man_service/selectSerivetp',
    method: 'get',
    params: query
  })
}

export function selectClerk(query) {
  return request({
    url: '/system/man_service/selectClerk',
    method: 'get',
    params: query
  })
}

export function getMid() {
  return request({
    url: '/system/man_service/getMid',
    method: 'get',
  })
}

export function selectTime(query) {
  return request({
    url: '/system/man_service/selectTime',
    method: 'get',
    params: query
  })
}