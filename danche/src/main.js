import Vue from 'vue'
import './plugins/axios'
import App from './App.vue'
import router from './router'
import store from './store'
import './plugins/vant.js'
import './css/resetvant.css'
import axios from 'axios';
//允许请求携带cookie
axios.defaults.withCredentials = true  //Access-Control-Allow-Origin 字段必须指定域名，不能为* ;Access-Control-Allow-Credentials为true
Vue.config.productionTip = false

//添加请求拦截器 在请求头中加token
axios.interceptors.request.use(
  config => {
  // 判断是否存在token，如果存在的话，则每个http header都加上token
   let token = sessionStorage.getItem('JWT_token')
    if (!config.headers.hasOwnProperty('JWT_token') || token) {
      
      config.headers.common['token'] =token ;
  }
    return config;
  },
  error => {
    return Promise.reject(error);
  });
  
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
