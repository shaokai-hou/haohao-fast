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

export function getRoleInfo(roleId) {
  return request({
    url: '/api/role/' + roleId,
    method: 'get'
  })
}

export function saveRole(data) {
  return request({
    url: '/api/role/',
    method: 'post',
    data: data
  })
}

export function updateRole(data) {
  return request({
    url: '/api/role/',
    method: 'put',
    data: data
  })
}

export function deleteRole(data) {
  return request({
    url: '/api/role/',
    method: 'delete',
    data: data
  })
}
