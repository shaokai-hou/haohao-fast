import axios from 'axios'
import { MessageBox, Message } from 'element-ui'
import store from '@/store'
import { getToken } from '@/utils/auth'
import da from 'element-ui/src/locale/lang/da'

axios.defaults.headers['Content-Type'] = 'application/json;charset=utf-8'
// 创建axios实例
const service = axios.create({
  // axios中请求配置有baseURL选项，表示请求URL公共部分
  baseURL: process.env.VUE_APP_BASE_API,
  // 超时
  timeout: 5000
})

// request拦截器
service.interceptors.request.use(config => {
    if (store.getters.token) {
      config.headers['Authorization'] = 'Bearer_' + getToken()
    }
    return config
  },
  error => {
    // do something with request error
    console.log(error) // for debug
    return Promise.reject(error)
  }
)

// 响应拦截器
service.interceptors.response.use(response => {
    // 返回数据
    const data = response.data
    if (data.code !== 200) {
      Message({ message: data.message, type: 'error', duration: 5 * 1000 })
      return Promise.reject(new Error(data.message))
    } else {
      return data
    }
  },
  error => {
    console.log('err' + error) // for debug
    Message({
      message: error.message,
      type: 'error',
      duration: 5 * 1000
    })
    return Promise.reject(error)
  }
)

export default service
