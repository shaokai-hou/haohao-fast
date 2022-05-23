import request from '@/utils/request'

export function getRolePage(query) {
  return request({
    url: '/api/role/page',
    method: 'get',
    params: query
  })
}

export function getRoleList(query) {
  return request({
    url: '/api/role/list',
    method: 'get',
    params: query
  })
}
