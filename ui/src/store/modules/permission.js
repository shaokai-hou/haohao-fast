import { constantRoutes } from '@/router'
import { getRouters } from '@/api/login'
import Layout from '@/layout'

export function filterAsyncRoutes(routes) {
  routes.forEach(item => {
    item.component = item.component ? loadView(item.component) : Layout
    if (item.children) {
      filterAsyncRoutes(item.children)
    }
  })
}

const state = {
  routes: [],
  addRoutes: []
}

const mutations = {
  SET_ROUTES: (state, routes) => {
    state.addRoutes = routes
    state.routes = constantRoutes.concat(routes)
  }
}

const actions = {
  // 生成路由
  generateRoutes({ commit }) {
    return new Promise(resolve => {
      // 向后端请求路由数据
      getRouters().then(res => {
        const rewriteRoutes = res.data
        filterAsyncRoutes(rewriteRoutes)
        rewriteRoutes.push({ path: '*', redirect: '/404', hidden: true })
        commit('SET_ROUTES', rewriteRoutes)
        resolve(rewriteRoutes)
      })
    })
  }
}

export const loadView = (view) => { // 路由懒加载
  return (resolve) => require([`@/views/${view}`], resolve)
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
