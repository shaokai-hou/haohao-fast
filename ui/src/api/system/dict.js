import request from '@/utils/request'

export function getDictPage(query) {
  return request({
    url: '/api/dict/page',
    method: 'get',
    params: query
  })
}

export function getDictList(query) {
  return request({
    url: '/api/dict/list',
    method: 'get',
    params: query
  })
}
