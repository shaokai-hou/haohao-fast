import request from '@/utils/request'

export function getMenuTree(query) {
  return request({
    url: '/api/menu/tree',
    method: 'get',
    params: query
  })
}

export function getMenuList(query) {
  return request({
    url: '/api/menu/list',
    method: 'get',
    params: query
  })
}
