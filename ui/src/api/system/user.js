import request from '@/utils/request'

export function getUserPage(query) {
  return request({
    url: '/api/user/page',
    method: 'get',
    params: query
  })
}

export function getUserList(query) {
  return request({
    url: '/api/user/list',
    method: 'get',
    params: query
  })
}

export function getUserInfo(userId) {
  return request({
    url: '/api/user/' + userId,
    method: 'get'
  })
}

export function saveUser(data) {
  return request({
    url: '/api/user',
    method: 'post',
    data: data
  })
}

export function updateUser(data) {
  return request({
    url: '/api/user',
    method: 'put',
    data: data
  })
}

export function updateState(userId) {
  return request({
    url: '/api/user/state/' + userId,
    method: 'put'
  })
}

