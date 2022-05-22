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

export function updateState(userId) {
  return request({
    url: '/api/user/state/' + userId,
    method: 'put'
  })
}

