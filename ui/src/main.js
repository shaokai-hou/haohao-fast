import Vue from 'vue'

import 'normalize.css/normalize.css' // A modern alternative to CSS resets

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import locale from 'element-ui/lib/locale/lang/en' // lang i18n

import '@/styles/index.scss' // global css

import App from './App'
import store from './store'
import router from './router'

import '@/icons' // icon
import '@/permission' // permission control

// 分页组件
import Pagination from '@/components/Pagination/Index'
// 角色选择组件
import RoleSelect from '@/components/RoleSelect/Index'
// 全局组件挂载
Vue.component('Pagination', Pagination)
Vue.component('RoleSelect', RoleSelect)

// set ElementUI lang to EN
// Vue.use(ElementUI, { locale })
// 如果想要中文版 element-ui，按如下方式声明
Vue.use(ElementUI)

// 配置 dialog组件 组件点击遮罩层不关闭
ElementUI.Dialog.props.closeOnClickModal.default = false
Vue.config.productionTip = false

new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})
